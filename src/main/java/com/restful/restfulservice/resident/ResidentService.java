package com.restful.restfulservice.resident;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

/*
 * @Service and also @Repository are special types of a @Component.
 * The major difference between these stereotypes is that they are used for different classifications.
 */
@Service
public class ResidentService {

	private final ResidentRepository residentRepository;
	
	@Autowired
	public ResidentService(ResidentRepository residentRepository) {
		this.residentRepository = residentRepository;
	}

	public List<ResidentEntity> getResidents() {
		return residentRepository.findAll();
	}

	public void addNewResident(ResidentEntity residentEntity) throws EmailAlreadyExistsException {
		Optional<ResidentEntity> checkResident = residentRepository.findResidentByEmail(residentEntity.getEmail());
		
		if (checkResident.isPresent()) {
			throw new EmailAlreadyExistsException("Resident with the given email already exists!");
		}
		
		residentRepository.saveAndFlush(residentEntity);
	}
	
	@Transactional
	public void updateResident(Long residentId, String firstName, String lastName, String email) {
		
		ResidentEntity residentEntity = residentRepository.findById(residentId)
							.orElseThrow(() -> new IllegalStateException("Resident with id:" + residentId + " does not exist."));
		
		
		if (firstName != null && firstName.length() > 0 && !Objects.equals(residentEntity.getFirstName(), firstName)) {
			residentEntity.setFirstName(firstName);
		}
		
		if (lastName != null && lastName.length() > 0 && !Objects.equals(residentEntity.getLastName(), lastName)) {
			residentEntity.setLastName(lastName);
		}
		
		if (email != null && email.length() > 0 && !Objects.equals(residentEntity.getEmail(), email)) {
			
			Optional<ResidentEntity> checkResident = residentRepository.findResidentByEmail(email);
			if(checkResident.isPresent())
				throw new IllegalStateException("This email is taken");
				
			residentEntity.setEmail(email);
		}
		
	}
	
	
	public void deleteResident(Long residentId) {
		if(!residentRepository.existsById(residentId)) {
			throw new IllegalStateException("resident with id" + residentId + "doesnot exist");
		}
		
		residentRepository.deleteById(residentId);
	}
	
	
	
	@SuppressWarnings("serial")
	class EmailAlreadyExistsException extends Exception {
		
		//private static final long serialVersionUID = 1L;  No longer needed usually now

		EmailAlreadyExistsException(String message) {
			super(message);
		}
	}
}