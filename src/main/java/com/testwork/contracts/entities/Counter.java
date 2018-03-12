package com.testwork.contracts.entities;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Counter extends BaseEntity {
	
	@Column(nullable = false)
    private long counter;

    public long getCounter() {
    	return counter;
    }
   
    public void setCounter(long counter) {
    	this.counter = counter;
    }
}
