package com.restful.restfulservice.resident.model;

import java.time.LocalDate;
import java.time.Period;

import com.restful.restfulservice.type.EntityObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table
@NoArgsConstructor
public class Resident implements EntityObject{
	
	@Id
	@SequenceGenerator(name = "resident_sequence", sequenceName = "resident_sequence", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "resident_sequence")
	@Column(unique=true, nullable = false, updatable = false)
	private Long id; 
	private String firstName;
	private String lastName;
	@Column(unique=true, nullable=false, updatable=true)
	private String email;
	@Transient
	private Integer age;
	private LocalDate dateOfBirth;
	
	public Resident(String firstName, String lastName, String email, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.dateOfBirth = dateOfBirth;
		this.age = getAge();
	}
	
	public int getAge() {
		return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Resident {");
		sb.append("ID = ").append(id).append("\n");
		sb.append("First Name = ").append(firstName).append("\n");
		sb.append("Last Name = ").append(lastName).append("\n");
		sb.append("Email = ").append(email).append("\n");
		sb.append("Age = ").append(age);
		
		return sb.toString();
	}

}