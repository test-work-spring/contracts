package com.testwork.contracts.services;

import com.testwork.contracts.dto.ContractDto;
import com.testwork.contracts.dto.DocumentDto;

import java.util.Collection;

public interface ContractService {

    ContractDto findById(long contractId);

    Collection<ContractDto> findAll();

    ContractDto create();
    
    void attachDocument(long contractId, DocumentDto documentDto);
    
    DocumentDto getDocument(long contractId);
}