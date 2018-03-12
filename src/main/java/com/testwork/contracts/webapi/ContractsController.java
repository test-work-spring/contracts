package com.testwork.contracts.webapi;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.testwork.contracts.dto.ContractDto;
import com.testwork.contracts.dto.DocumentDto;
import com.testwork.contracts.services.ContractService;

@RestController
@RequestMapping("/api/contracts")
public class ContractsController {
	
	 @Autowired
	 public ContractService contractService;	
	
	 @GetMapping
	 public Collection<ContractDto> getAll() {
		 Collection<ContractDto> contracts = contractService.findAll();
		 return contracts;
	 }

	 @PostMapping
	 @ResponseStatus(HttpStatus.CREATED)
	 public ContractDto create() {
		 ContractDto contractDto = contractService.create();
		 return contractDto;
	 }

	 @PostMapping("/{contractId}/document")
	 @ResponseStatus(HttpStatus.NO_CONTENT)
	 public void attachDocument(@PathVariable("contractId") long contractId, @RequestParam("document") MultipartFile document) throws IOException{
		 DocumentDto documentDto = new DocumentDto();
		 documentDto.setName(document.getOriginalFilename());
		 documentDto.setBytes(document.getBytes());
		 contractService.attachDocument(contractId, documentDto);
	 }
	 
	 @GetMapping("/{contractId}/document")
	 public ResponseEntity<InputStreamResource> getDocument(@PathVariable(value = "contractId")  long contractId) {
		 DocumentDto documentDto =  contractService.getDocument(contractId);
		 InputStreamResource stream = new InputStreamResource( new ByteArrayInputStream(documentDto.getBytes()));
		 return ResponseEntity
	            .ok()
	            .header(HttpHeaders.CONTENT_DISPOSITION, String.format("attachment; filename=\"%s\"", documentDto.getName()))
	            .contentType(MediaType.parseMediaType("application/octet-stream"))
	            .body(stream);
    }
}
