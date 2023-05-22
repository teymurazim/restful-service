package com.restful.restfulservice.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table
public class  Customer {
	
	@Id
	@SequenceGenerator(name = "customer_sequence",
						sequenceName = "customer_sequence",
						allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "customer_sequence")
	private Long id; 
	private String firstName;
	private String lastName;
	private String email;
	private Integer age;
	
	public Customer() {
	}
	
	public Customer(String firstName, String lastName, String email, Integer age) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.age = age;
	}

}
