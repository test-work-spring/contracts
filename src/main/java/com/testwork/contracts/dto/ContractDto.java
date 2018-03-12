package com.testwork.contracts.dto;

public class ContractDto extends BaseDto{
	
    private String name;
	
	private DocumentDto document;
	
    public String getName() {
    	return name;
    }
   
    public void setName(String name) {
    	this.name = name;
    }

    public DocumentDto getDocument() {
    	return document;
    }
   
    public void setDocument(DocumentDto document) {
    	this.document = document;
    }
}
