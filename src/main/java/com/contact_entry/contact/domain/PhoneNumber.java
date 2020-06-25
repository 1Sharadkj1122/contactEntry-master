package com.contact_entry.contact.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "PHONE_NUMBER")
public class PhoneNumber {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@JsonIgnore
	private Long id;
	
	
	@Column(name="NUMBER")
	@Size(max = 20, message = "Phone Number can not be greater than 20 charcters")
	@NotEmpty(message = "Please provide an Phone Number")
	private String number;
	
	@Column(name="TYPE")
	@Size(max = 15, message = "Phone Type can not be greater than 15 charcters")
	@NotEmpty(message = "Please provide an Phone Type")
	private String type;
	
	public PhoneNumber(String number, String type) {
		super();
		this.number = number;
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
