package com.testwork.contracts.entities;

import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.validation.constraints.Size;

@Entity
public class Contract extends BaseEntity {
	
	@Column(length = 100, nullable = false)
	@Size(min=1)
    private String name;
	
	@OneToMany(	cascade = CascadeType.ALL,orphanRemoval = true, mappedBy = "contract")
	@OrderBy("create DESC")
	private Collection<Document> documents;
	
    public String getName() {
    	return name;
    }
   
    public void setName(String name) {
    	this.name = name;
    }

    public Collection<Document> getDocuments() {
    	return documents;
    }
   
    public void setDocuments(Collection<Document> documents) {
    	this.documents = documents;
    }
    
    public void addDocument(Document document) {
    	document.setContract(this);
    	documents.add(document);	
    }
    
    public Document getLatestDocument() {

    	Collection<Document> documents = getDocuments();
    	
		if(documents == null || documents.isEmpty()) {
			return null; 
		}

    	return documents.iterator().next();
    }
}
