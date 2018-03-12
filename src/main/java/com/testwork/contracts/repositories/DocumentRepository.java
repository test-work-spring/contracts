package com.testwork.contracts.repositories;

import com.testwork.contracts.entities.Document;

import org.springframework.data.repository.CrudRepository;

public interface DocumentRepository extends CrudRepository<Document, Long> {

}