package com.contact_entry.contact.controller;


import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.contact_entry.contact.domain.Contact;
import com.contact_entry.contact.service.ContactService;

@RestController
public class ContactEndPointController {

    @Autowired
    private ContactService contactService;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public List<Contact> listContacts(){
    	List<Contact> conList =  new ArrayList<Contact>();
    	try {
    		conList = contactService.findAll();
    	}catch (Exception e) {
			e.printStackTrace();
		}
        return conList;
    }

    @RequestMapping(value = "/contact/{id}", method = RequestMethod.GET)
    public Contact getContactById(@PathVariable(value = "id") Long id){
        return contactService.findById(id);
    }
    
    @RequestMapping(value = "/saveContact", method = RequestMethod.POST)
    public ResponseEntity<?> save(@Valid @RequestBody Contact contact) {
    	return ResponseEntity.ok().body(contactService.save(contact));
    }
    
    @RequestMapping(value = "/updateContact/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateContact(@RequestBody Contact contact, @PathVariable Long id) {
    	Contact contct = contactService.findById(id);
    	if(contact != null) {
    		contct.setAddress(contact.getAddress());
    		contct.setName(contact.getName());
    		contct.setEmail(contact.getEmail());
    		contct.setPhone(contact.getPhone());
    		return ResponseEntity.ok().body(contactService.save(contct));
    	} else {
    		return ResponseEntity.ok().body(contactService.save(contact));
    	}
    }
    
    @RequestMapping(value = "/deleteContact/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?>  deleteContactById(@PathVariable(value = "id") Long id){
    	Contact contact = contactService.findById(id);
    	if(contact != null) {
    		contactService.delete(id);
    		return ResponseEntity.ok().build();
    	} else{
    		return ResponseEntity.notFound().build();
    	}
    }
}
