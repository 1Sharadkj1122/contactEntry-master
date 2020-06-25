package com.contact_entry.contact.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ADDRESS")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ADDRESS_ID")
	@JsonIgnore
	private Long id;

	@Column
	@Size(max = 255, message = "Street can not be greater than 255 charcters")
	@NotEmpty(message = "Please provide an Street")
	private String street;

	@Column
	@Size(max = 100, message = "City can not be greater than 100 charcters")
	@NotEmpty(message = "Please provide an City")
	private String city;

	@Column
	@Size(max = 100, message = "State can not be greater than 100 charcters")
	@NotEmpty(message = "Please provide an State")
	private String state;

	@Column
	@Size(max = 12, message = "Zip can not be greater than 12 charcters")
	@NotEmpty(message = "Please provide an Zip")
	private String zip;

	@JsonIgnore
	@OneToOne(mappedBy = "address")
	private Contact contact;
	
	public Address(String street, String city, String state, String zip) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
