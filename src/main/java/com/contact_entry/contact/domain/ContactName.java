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
@Table(name = "CONTACT_NAME")
public class ContactName {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="CONTACT_NAME_ID")
	@JsonIgnore
	private Long id;
	
	@Column(name = "FIRST_NAME")
    @Size(max = 60, message = "First Name can not be greater than 60 charcters")
	@NotEmpty(message = "Please provide an First Name")
	private String firstName;

	@Column(name = "MIDDLE_NAME")
	@Size(max = 60, message = "Middle Name can not be greater than 60 charcters")
	@NotEmpty(message = "Please provide an Middle Name")
	private String middleName;

	@Column(name = "LAST_NAME")
	@Size(max = 60, message = "Last Name can not be greater than 60 charcters")
	@NotEmpty(message = "Please provide an Last Name")
	private String lastName;
	
	@JsonIgnore
	@OneToOne(mappedBy = "name")
	private Contact contact;
	
	public ContactName(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
