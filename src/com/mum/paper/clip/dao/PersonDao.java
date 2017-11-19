package com.mum.paper.clip.dao;

import com.mum.paper.clip.model.Customer;
import com.mum.paper.clip.model.Person;

public interface PersonDao {
	
	public Person login(Person person);
	public Person createCustomer(Customer customer);
	public boolean isUserAvailable(String username);
	public Boolean updatePersonalProfile(Customer customer);
	public Customer getPersonalData(Integer customerId);
	//public Person fogortPassword(Person person);

}
