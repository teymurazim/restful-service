package com.restful.restfulservice.customer;
//package lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class  Customer {

	private Long id; 
	private String firstName;
	private String lastName;
	private String email;
	private Integer age;
	
}
