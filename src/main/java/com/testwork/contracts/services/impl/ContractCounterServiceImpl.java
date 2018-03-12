package com.testwork.contracts.services.impl;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.testwork.contracts.entities.Counter;
import com.testwork.contracts.repositories.CounterRepository;
import com.testwork.contracts.services.ContractCounterService;

@Service
public class ContractCounterServiceImpl implements  ContractCounterService{

	@Autowired
	private CounterRepository counterRepository;

	@Transactional
	@Override
	public String generateContractName(){
		long counter = getAndIncreaseContractCounter();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yy");
		return String.format("%d от %s", counter,  LocalDate.now().format(formatter) );
	}

	private long getAndIncreaseContractCounter() {
		Counter counter = counterRepository.findById(1l);
		if(counter == null) {
			counter = new Counter();
		}
		long ret = counter.getCounter() + 1;
		counter.setCounter(ret);
		counterRepository.save(counter);
		return ret;
	}
}