package com.restful.restfulservice.resident;

import java.time.LocalDate;
import java.time.Period;

import com.restful.restfulservice.type.EntityObject;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;

@Data
@Entity
@Table
public class Resident implements EntityObject{
	
	@Id
	@SequenceGenerator(name = "resident_sequence",
						sequenceName = "resident_sequence",
						allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE,
					generator = "resident_sequence")
	private Long id; 
	private String firstName;
	private String lastName;
	private String email;
	
	@Transient
	private Integer age;
	private LocalDate dateOfBirth;
	
	public Resident() {
	}
	
	public Resident(String firstName, String lastName, String email, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
	}
	
	public int getAge() {
		return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
	}

}
