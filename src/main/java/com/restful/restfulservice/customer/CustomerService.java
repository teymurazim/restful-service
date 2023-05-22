package com.restful.restfulservice.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @Service and also @Repository are special types of a @Component.
 * The major difference between these stereotypes is that they are used for different classifications.
 */
@Service
public class CustomerService {

	private final CustomerRepository customerRepository;
	
	@Autowired
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}
}
