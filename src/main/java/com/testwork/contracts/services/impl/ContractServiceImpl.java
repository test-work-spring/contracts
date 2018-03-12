package com.testwork.contracts.services.impl;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testwork.contracts.dto.ContractDto;
import com.testwork.contracts.dto.DocumentDto;
import com.testwork.contracts.entities.Contract;
import com.testwork.contracts.entities.Document;
import com.testwork.contracts.map.Mappings;
import com.testwork.contracts.repositories.ContractRepository;
import com.testwork.contracts.services.ContractCounterService;
import com.testwork.contracts.services.ContractService;

@Service
public class ContractServiceImpl implements  ContractService{

	@Autowired
	private ContractRepository contractRepository;

	@Autowired
	private ContractCounterService contractCounterService;

	@Transactional(readOnly=true)
	@Override
    public ContractDto findById(long contractId) {
		Contract contract = getContract(contractId);
		ContractDto contractDto =Mappings.contractToDto.apply(contract); 
		return contractDto;
    }
    
	@Transactional(readOnly=true)
	@Override
	public Collection<ContractDto> findAll(){
		Iterable<Contract> contracts =  contractRepository.findAll();
		Collection<ContractDto> contractsDto = StreamSupport.stream(contracts.spliterator(), false)
											.map(Mappings.contractToDto)
											.collect(Collectors.<ContractDto> toList());
		return contractsDto;		
    }

	@Transactional
	@Override
	public ContractDto create() {
		Contract contract = new Contract();
		contract.setName(contractCounterService.generateContractName());
		contractRepository.save(contract);
		ContractDto contractDto = Mappings.contractToDto.apply(contract) ;
		return contractDto;
    }
	
	@Transactional
	@Override
	public void attachDocument(long contractId, DocumentDto documentDto) {
		Document documentNew = Mappings.documentDtoToDocument.apply(documentDto) ;
		Contract contract = getContract(contractId);
		contract.addDocument(documentNew);
		contractRepository.save(contract);
    }
	
	@Transactional(readOnly=true)
	@Override
	public DocumentDto getDocument(long contractId) {
		Contract contract = getContract(contractId);
		Document document = contract.getLatestDocument();
		if(document == null) {
			throw new RuntimeException("Контракт не содержит документов");
		}
		DocumentDto documentDto = Mappings.documentToDtoWithContent.apply(document) ;
		return documentDto;
	}
	
	private Contract getContract(long contractId) {
		Optional<Contract> contract = contractRepository.findById(contractId);
		if(contract.isPresent() == false) {
			throw new EntityNotFoundException(String.format("Контракт %d не найден", contractId));
		}
		return contract.get();
	}
}