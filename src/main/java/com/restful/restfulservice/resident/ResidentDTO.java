package com.restful.restfulservice.resident;

import java.time.LocalDate;

import com.restful.restfulservice.type.RecordObject;

import lombok.Data;

@Data
public class ResidentDTO implements RecordObject {
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfBirth;;
	private Integer age;
	
	public ResidentDTO() {
	
	}
}
