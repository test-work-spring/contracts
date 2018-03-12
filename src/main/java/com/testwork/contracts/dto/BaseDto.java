package com.testwork.contracts.dto;

public class BaseDto {
	 
	 private long id; 
	 
	 public long getId() { 
		 return id; 
	 } 
	 
	 public void setId(long id) { 
		 this.id = id; 
	 } 
	 
	@Override
	public String toString() {
	    return this.getClass().getSimpleName() + "-" + getId();
	}	
}
