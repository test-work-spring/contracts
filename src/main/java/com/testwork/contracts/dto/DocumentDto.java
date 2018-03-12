package com.testwork.contracts.dto;

public class DocumentDto extends BaseDto{
    
    private String name;
     
    private byte[] bytes;
    
    public String getName() {
    	return name;
    }
   
    public void setName(String name) {
    	this.name = name;
    }
    
    public byte[] getBytes() {
    	return bytes;
    }
    
    public void setBytes(byte[] bytes) {
    	this.bytes= bytes;
    }
}
