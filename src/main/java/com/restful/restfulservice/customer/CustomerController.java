package com.restful.restfulservice.customer;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="${apiPrefix}/customer")
public class CustomerController {
	
	@GetMapping
	public List<Customer> getCustomers() {
		return List.of(new Customer(1L,"Teymur",
									"Azimzada",
									"teymur_azimzada@yahoo.com",
									24));
	}

}
