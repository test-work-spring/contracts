package com.testwork.contracts.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Document extends BaseEntity {
    
	@Column(length = 100, nullable = false)
	@Size(min=1)
    private String name;
     
    @Column(nullable = false)
	@Lob
    private byte[] bytes;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp 
    private Date create;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private Contract contract;
    
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
    	this.bytes = bytes;
    }

    public Date getCreate() {
    	return create;
    }
    
    public void setCreate(Date create) {
    	this.create = create;
    }
    
    public void setContract(Contract contract) {
    	this.contract = contract;
    }

    public Contract getContract() {
    	return this.contract;
    }
}
