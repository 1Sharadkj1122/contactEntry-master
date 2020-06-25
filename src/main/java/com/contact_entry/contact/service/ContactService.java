package com.contact_entry.contact.service;


import java.util.List;

import com.contact_entry.contact.domain.Contact;

public interface ContactService {

	public List<Contact> findAll();

	public Contact findById(Long id);

	public Contact save(Contact contact);

	public void delete(Long id);
	
}
