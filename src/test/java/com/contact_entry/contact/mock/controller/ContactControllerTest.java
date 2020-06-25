package com.contact_entry.contact.mock.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.contact_entry.contact.TestUtils;
import com.contact_entry.contact.controller.ContactEndPointController;
import com.contact_entry.contact.domain.Address;
import com.contact_entry.contact.domain.Contact;
import com.contact_entry.contact.domain.ContactName;
import com.contact_entry.contact.domain.PhoneNumber;
import com.contact_entry.contact.service.ContactServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.reflect.TypeToken;

@RunWith(SpringRunner.class)
@WebMvcTest(ContactEndPointController.class)
public class ContactControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	ContactServiceImpl contactService;

	private final String URL = "/";
	
    ObjectMapper mapper = new ObjectMapper();
    
    String json = "{\"name\":{\"firstName\":\"mandy\",\"middleName\":\"KUmar\",\"lastName\":\"Rayat\"},\"address\":{\"street\":\"183 LInk ROad\",\"city\":\"Birmingham\",\"state\":\"Alabama\",\"zip\":\"32203\",},\"phone\":[{\"number\":\"302-440-4440\",\"type\":\"Work\"},],\"email\":\"abd@gmail.com\"}";

	@Test
	public void testAddContact() throws Exception {

		// prepare data and mock's behaviour
		List<PhoneNumber> pList =  new ArrayList<PhoneNumber>();
 		ContactName name = new ContactName("mandy", "KUmar", "Rayat");
		Address address =  new Address("183 LInk ROad", "Birmingham", "Alabama", "32203");
		PhoneNumber phoneNumber =  new PhoneNumber("302-440-4440", "Work");
		pList.add(phoneNumber);
		Contact contact = new Contact(name, address, pList, "abd@gmail.com");
		
		when(contactService.save(any(Contact.class))).thenReturn(contact);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post(URL+"/saveContact"). contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.CREATED.value(), status);

		// verify that service method was called once
		verify(contactService).save(any(Contact.class));

		Contact resultContact = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Contact.class);
		assertNotNull(resultContact);
		assertEquals(1l, resultContact.getId().longValue());

	}

	@Test
	public void testGetContact() throws Exception {

		List<PhoneNumber> pList =  new ArrayList<PhoneNumber>();
 		ContactName name = new ContactName("mandy", "KUmar", "Rayat");
		Address address =  new Address("183 LInk ROad", "Birmingham", "Alabama", "32203");
		PhoneNumber phoneNumber =  new PhoneNumber("302-440-4440", "Work");
		pList.add(phoneNumber);
		Contact contact = new Contact(name, address, pList, "abd@gmail.com");
		
		// prepare data and mock's behaviour
		when(contactService.findById(any(Long.class))).thenReturn(contact);

		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL +"contact/"+ "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(contactService).findById(any(Long.class));

		Contact reContact = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Contact.class);
		assertNotNull(reContact);
		assertEquals(1l, reContact.getId().longValue());
	}

	@Test
	public void testGetContactNotExist() throws Exception {

		// prepare data and mock's behaviour
		
		// execute
		MvcResult result = mockMvc
				.perform(MockMvcRequestBuilders.get(URL+"contact/"+ "{id}", new Long(1)).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.NOT_FOUND.value(), status);

		// verify that service method was called once
		verify(contactService).findById(any(Long.class));

		Contact resultContact = TestUtils.jsonToObject(result.getResponse().getContentAsString(), Contact.class);
		assertNull(resultContact);
	}

	@Test
	public void testGetAllContact() throws Exception {

		// prepare data and mock's behaviour
		List<Contact> empList = buildContacts();
		when(contactService.findAll()).thenReturn(empList);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.get(URL).accept(MediaType.APPLICATION_JSON))
				.andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(contactService).findAll();

		// get the List<Contact> from the Json response
		TypeToken<List<Contact>> token = new TypeToken<List<Contact>>() {
		};
		@SuppressWarnings("unchecked")
		List<Contact> conList = TestUtils.jsonToList(result.getResponse().getContentAsString(), token);

		assertNotNull("Contacts not found", conList);
		assertEquals("Incorrect Contacts List", empList.size(), conList.size());

	}

	@Test
	public void testDeleteContact() throws Exception {
		// prepare data and mock's behaviour
		List<PhoneNumber> pList =  new ArrayList<PhoneNumber>();
 		ContactName name = new ContactName("mandy", "KUmar", "Rayat");
		Address address =  new Address("183 LInk ROad", "Birmingham", "Alabama", "32203");
		PhoneNumber phoneNumber =  new PhoneNumber("302-440-4440", "Work");
		pList.add(phoneNumber);
		Contact contact = new Contact(name, address, pList, "abd@gmail.com");
		when(contactService.findById(any(Long.class))).thenReturn(contact);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.delete(URL +"deleteContact/"+ "{id}", new Long(1))).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.GONE.value(), status);

		// verify that service method was called once
		verify(contactService).delete(any(Long.class));

	}

	@Test
	public void testUpdateContact() throws Exception {
		// prepare data and mock's behaviour
		// here the stub is the updated Contact object with ID equal to ID of
		// Contact need to be updated
		List<PhoneNumber> pList =  new ArrayList<PhoneNumber>();
 		ContactName name = new ContactName("mandy", "KUmar", "Rayat");
		Address address =  new Address("183 LInk ROad", "Birmingham", "Alabama", "32203");
		PhoneNumber phoneNumber =  new PhoneNumber("302-440-4440", "Work");
		pList.add(phoneNumber);
		Contact contact = new Contact(name, address, pList, "abd@gmail.com");
		when(contactService.findById(any(Long.class))).thenReturn(contact);

		// execute
		MvcResult result = mockMvc.perform(MockMvcRequestBuilders.put(URL +"updateContact/"+ "{id}", new Long(1)).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(json)).andReturn();

		// verify
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(), status);

		// verify that service method was called once
		verify(contactService).save(any(Contact.class));

	}

	private List<Contact> buildContacts() {
		List<PhoneNumber> pList =  new ArrayList<PhoneNumber>();
 		ContactName name = new ContactName("mandy", "KUmar", "Rayat");
		Address address =  new Address("183 LInk ROad", "Birmingham", "Alabama", "32203");
		PhoneNumber phoneNumber =  new PhoneNumber("302-440-4440", "Work");
		pList.add(phoneNumber);
		Contact contact = new Contact(name, address, pList, "abd@gmail.com");
		
		List<Contact> contacts = Arrays.asList(contact);
		return contacts;
	}

}
