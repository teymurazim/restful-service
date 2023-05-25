package com.restful.restfulservice.resident;

import java.time.LocalDate;

import com.restful.restfulservice.type.RecordObject;

public record ResidentRecord(String firstName, String lastName, String email, LocalDate dateOfBirth, Integer age) implements RecordObject {
	
	/* ResidentRecord can be an interface. Can create several types of records (with or without password for example and etc.)
	
	/*
	 * Record eliminates boilerplate code, so the purpose of the class is clear. Unlike DTOs, Records are immutable,
	 * built-in support for toString(). hashCOde() and equals(). More efficient in in memory usage and performance
	 * due to immutable nature (JVM applies inlining and compacting). But, records are not suitable for Java Persistence API entity classes. 
	 * May write constructors with validation. 
	 * 
	 */
	
}
