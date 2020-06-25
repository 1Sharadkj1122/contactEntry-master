package com.contact_entry.contact.service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.contact_entry.contact.dao.ContactDao;
import com.contact_entry.contact.domain.Contact;

@Service(value = "userService")
public class ContactServiceImpl implements ContactService {
	
	@Autowired
	ContactDao contactDao;

	@Override
	public List<Contact> findAll() {
		List<Contact> list = new ArrayList<Contact>();
		contactDao.findAll().iterator().forEachRemaining(list::add);
		return list;
	}

	@Override
	public Contact findById(Long id) {
		return contactDao.findOne(id);
	}

	@Override
	public Contact save(Contact contact) {
		return contactDao.save(contact);
	}

	@Override
	public void delete(Long id) {
		contactDao.delete(id);
	}
}
