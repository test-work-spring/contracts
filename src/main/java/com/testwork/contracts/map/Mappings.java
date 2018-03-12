package com.testwork.contracts.map;

import java.util.function.Function;
import com.testwork.contracts.dto.ContractDto;
import com.testwork.contracts.dto.DocumentDto;
import com.testwork.contracts.entities.Contract;
import com.testwork.contracts.entities.Document;

public interface Mappings {
	
	Function<Document, DocumentDto> documentToDto = t-> {
		DocumentDto documentDto = new DocumentDto();
		documentDto.setId(t.getId());
		documentDto.setName(t.getName());
    	return documentDto;
	};	

	Function<Document, DocumentDto> documentToDtoWithContent = t-> {
		DocumentDto documentDto = documentToDto.apply(t);
		documentDto.setBytes(t.getBytes());
    	return documentDto;
	};	
	
	Function<DocumentDto, Document> documentDtoToDocument = t-> {
		Document document = new Document();
		document.setId(t.getId());
		document.setName(t.getName());
		document.setBytes(t.getBytes());
    	return document;
	};	

	Function<Contract, ContractDto> contractToDto = t-> {
		ContractDto contractDto = new ContractDto();
		contractDto.setId(t.getId());
		contractDto.setName(t.getName());
		
		Document document  = t.getLatestDocument();
		if(document != null) {
			contractDto.setDocument(documentToDto.apply(document));
		}
    	return contractDto;
	};

}
