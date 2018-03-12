package com.testwork.contracts.repositories;

import com.testwork.contracts.entities.Counter;
import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, Long> {
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	Counter findById(long id);
}