package com.contact_entry.contact.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.contact_entry.contact.domain.Contact;


@Repository
public interface ContactDao extends CrudRepository<Contact, Long> {

}

