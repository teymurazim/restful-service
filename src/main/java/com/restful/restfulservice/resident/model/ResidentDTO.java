package com.restful.restfulservice.resident.model;

import java.time.LocalDate;

import com.restful.restfulservice.type.RecordObject;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResidentDTO implements RecordObject {
	
	private String firstName;
	private String lastName;
	private String email;
	private LocalDate dateOfBirth;;
	private Integer age;
	
}
