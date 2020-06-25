package com.contact_entry.contact.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "CONTACT")
public class Contact {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_ID")
	private Long id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CONTACT_NAME_ID")
	private ContactName name;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ADDRESS_ID")
	private Address address;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name="CONTACT_ID")
	private List<PhoneNumber> phone;

	@Column(name = "EMAIL")
	@Size(max = 100, message = "Email Address can not be greater than 100 charcters")
	@NotEmpty(message = "Please provide an Email Address")
	private String email;
	
	public Contact(){}
	
	public Contact(ContactName name, Address address, List<PhoneNumber> phone, String email) {
		super();
		this.name = name;
		this.address = address;
		this.phone = phone;
		this.email = email;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ContactName getName() {
		return name;
	}

	public void setName(ContactName name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<PhoneNumber> getPhone() {
		return phone;
	}

	public void setPhone(List<PhoneNumber> phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
