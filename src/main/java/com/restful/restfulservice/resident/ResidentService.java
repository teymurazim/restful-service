package com.restful.restfulservice.resident;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.restful.restfulservice.resident.factory.ResidentRecordFactory;
import com.restful.restfulservice.resident.factory.ResidentEntityFactory;
import com.restful.restfulservice.response.ResponseHandler;

import jakarta.transaction.Transactional;

/*
 * @Service and also @Repository are special types of a @Component.
 * The major difference between these stereotypes is that they are used for different classifications.
 */
@Service
public class ResidentService {

	private final ResidentRepository residentRepository;
	private final ResponseHandler responseHandler;
	private final ResidentEntityFactory ref;
	private final ResidentRecordFactory rrf;
	
	@Autowired
	public ResidentService(ResidentRepository residentRepository, ResidentEntityFactory ref, ResidentRecordFactory rrf, ResponseHandler responseHandler) {
		this.residentRepository = residentRepository;
		this.responseHandler = responseHandler;
		this.ref = ref;
		this.rrf = rrf;
	}
	
	public ResponseEntity<Object> getResidents() {
	
		return responseHandler.generateResponse(HttpStatus.OK, getResidentsFromRepo());
	}
	
	public List<ResidentRecord> getResidentsFromRepo() {
		List<Resident> residents = residentRepository.findAll();
		return rrf.buildRecordList(residents); 
	}
	
	public ResponseEntity<Object> getResidentById(Long id) {
		Optional<Resident> resident = residentRepository.findById(id);
		
		if(!resident.isPresent())
			return responseHandler.generateResponse(HttpStatus.NOT_FOUND, null);
		
		return responseHandler.generateResponse(HttpStatus.OK, rrf.buildRecord(resident.get()));
	}

	public ResponseEntity<Object> registerNewResident(ResidentRecord residentRecord){
		
		Optional<Resident> resident = residentRepository.findResidentByEmail(residentRecord.email());
		
		if (resident.isPresent()) {
			return responseHandler.generateResponse(HttpStatus.CONFLICT, "A resident with provided email already exists"); 
		}
		
		residentRepository.saveAndFlush(ref.buildEntity(residentRecord));
		
		return responseHandler.generateResponse(HttpStatus.CREATED, getResidentsFromRepo());
	}
	
	
	@Transactional
	public ResponseEntity<Object> updateResident(Long residentId, String firstName, String lastName, String email) {
		
		Optional<Resident> resident = residentRepository.findById(residentId);
		
		if (!resident.isPresent()) 
			return responseHandler.generateResponse(HttpStatus.NOT_FOUND, null);
		
		
		if (firstName != null && firstName.length() > 0 && !Objects.equals(resident.get().getFirstName(), firstName)) {
			resident.get().setFirstName(firstName);
		}
		
		if (lastName != null && lastName.length() > 0 && !Objects.equals(resident.get().getLastName(), lastName)) {
			resident.get().setLastName(lastName);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(resident.get().getEmail(), email)) {
			
			Optional<Resident> checkResident = residentRepository.findResidentByEmail(email);
			if(checkResident.isPresent())
				return responseHandler.generateResponse(HttpStatus.CONFLICT, "A resident with provided email already exists");
				
			resident.get().setEmail(email);
		}
		
		return responseHandler.generateResponse(HttpStatus.OK, rrf.buildRecord(resident.get()));
		
	}
	
	public ResponseEntity<Object> deleteResident(Long residentId) {
		if(!residentRepository.existsById(residentId)) {
			return responseHandler.generateResponse(HttpStatus.NOT_FOUND, "A resident with provided ID does not exists");
		}
		
		residentRepository.deleteById(residentId);
		return responseHandler.generateResponse(HttpStatus.OK, "The resident has been deleted");
	}
}