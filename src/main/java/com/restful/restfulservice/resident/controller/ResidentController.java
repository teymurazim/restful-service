package com.restful.restfulservice.resident.controller;

import static java.time.LocalDateTime.now;
import static java.util.Map.of;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restful.restfulservice.resident.exception.EmailAlreadyExistsException;
import com.restful.restfulservice.resident.model.ResidentRecord;
import com.restful.restfulservice.resident.service.implementation.ResidentServiceImpl;
import com.restful.restfulservice.response.model.Response;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin
@RequestMapping(path = "residents")
@RequiredArgsConstructor
public class ResidentController {

	private final ResidentServiceImpl residentServiceImpl;
	
	@GetMapping
	public ResponseEntity<Response> getResidents(){
			
		return new ResponseEntity<>(
						Response.builder()
								.timeStamp(now())
								.data(of("Residents",residentServiceImpl.getResidents()))
								.message(ResponseMessages.SUCCESS.getMessage())
								.build(),
						HttpHeaders.EMPTY,HttpStatus.OK);			
	}
	
	
	@GetMapping(path = "{residentId}")
	public ResponseEntity<Response> getResidentById(@PathVariable("residentId") Long id) {
		
		Optional<ResidentRecord> resident = residentServiceImpl.getResidentById(id);
		
		if(!resident.isPresent()) {
			return new ResponseEntity<Response>(Response.builder()
													.error(ResponseMessages.RESIDENT_DOESNT_EXIST.getMessage())
													.build(),
												HttpHeaders.EMPTY,HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<>(Response.builder()
											.timeStamp(now())
											.data(of("Resident", resident.get()))
											.message(ResponseMessages.SUCCESS.getMessage())
											.build(),
										HttpHeaders.EMPTY,HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Response> registerNewResident(@RequestBody ResidentRecord resident) {
		
		try {
			return new ResponseEntity<>(Response.builder()
										.timeStamp(now())
										.data(of("Resident", residentServiceImpl.registerResident(resident)))
										.message(ResponseMessages.REGISTRATION_SUCCESS.getMessage())
										.build(),HttpHeaders.EMPTY,HttpStatus.CREATED);
		} catch (EmailAlreadyExistsException e) {
			return new ResponseEntity<>(Response.builder()
										.timeStamp(now())
										.error(ResponseMessages.RESIDENT_EXISTS.getMessage())
										.build()
				,HttpHeaders.EMPTY,HttpStatus.CONFLICT); 
		}
	}
	
	@PutMapping(path = "{residentId}")
	public ResponseEntity<Response> updateResident(@PathVariable("residentId") Long id, @RequestBody @Validated ResidentRecord updatedResident) {
		try {
			return new ResponseEntity<>(Response.builder()
										.timeStamp(now())
										.message(ResponseMessages.UPDATE_SUCCESS.getMessage())
										.data(of("Resident", residentServiceImpl.updateResident(id,updatedResident)))
										.build()
									,HttpHeaders.EMPTY,HttpStatus.OK);
		} catch (EmailAlreadyExistsException e) {
			return new ResponseEntity<>(Response.builder()
										.timeStamp(now())
										.error(ResponseMessages.RESIDENT_EXISTS.getMessage())
										.build()
				,HttpHeaders.EMPTY,HttpStatus.CONFLICT); 
		}
	}
	
	@DeleteMapping(path = "{residentId}")
	public ResponseEntity<Response> deleteResident(@PathVariable("residentId") Long id) {
		return new ResponseEntity<>(Response.builder()
				.timeStamp(now())
				.message(ResponseMessages.DELETE_SUCCESS.getMessage())
				.data(of("Resident", residentServiceImpl.deleteResident(id)))
				.build()
			,HttpHeaders.EMPTY,HttpStatus.OK);
	}
	
	@RequiredArgsConstructor
	private enum ResponseMessages {
		
		SUCCESS("The operation has been successfully executed."),
		RESIDENT_EXISTS("A resident with provided email already exists."),
		RESIDENT_DOESNT_EXIST("A resident with provided ID does not exists."),
		REGISTRATION_SUCCESS("The resident has been successfully registered."),
		UPDATE_SUCCESS("The resident's informations has been successfully updated."),
		DELETE_SUCCESS("The resident has been successfully deleted.");
		
		private final String stringValue;
		
		public String getMessage() {
			return stringValue;
		}
	}
}