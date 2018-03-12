package com.testwork.contracts.entities;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@MappedSuperclass
public abstract class BaseEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;

	@Version 
	long version;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
	
	@Override
	public String toString() {
	    return this.getClass().getSimpleName() + "-" + getId();
	}	
}
