package com.testwork.contracts.repositories;

import com.testwork.contracts.entities.Contract;

import org.springframework.data.repository.CrudRepository;

public interface ContractRepository extends CrudRepository<Contract, Long> {

}