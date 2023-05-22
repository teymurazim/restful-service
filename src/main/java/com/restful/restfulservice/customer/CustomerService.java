package com.restful.restfulservice.customer;

import java.util.List;
import java.util.Optional;

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

	public void addNewCustomer(Customer customer) throws EmailAlreadyExistsException {
		Optional<Customer> checkCustomer = customerRepository.findCustomerByEmail(customer.getEmail());
		
		if (checkCustomer.isPresent()) {
			throw new EmailAlreadyExistsException("User with the given email already exists!");
		}
		
		customerRepository.saveAndFlush(customer);
	}
	
	class EmailAlreadyExistsException extends Exception {
		
		private static final long serialVersionUID = 1L;

		EmailAlreadyExistsException(String message) {
			super(message);
		}
	}
}