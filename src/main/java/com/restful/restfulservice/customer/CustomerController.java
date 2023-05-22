package com.restful.restfulservice.customer;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.restfulservice.customer.CustomerService.EmailAlreadyExistsException;

@RestController
@RequestMapping(path="${apiPrefix}/customer")
public class CustomerController {

	private final CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public List<Customer> getCustomers() {
		return customerService.getCustomers();
	}
	
	@PostMapping
	public ResponseEntity<String> registerNewCustomer(@RequestBody Customer customer) {
		try {
			customerService.addNewCustomer(customer);
		} catch (EmailAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST);
			
		}
		return new ResponseEntity<String>("Successfully registered", new HttpHeaders(), HttpStatus.OK);
	}
}