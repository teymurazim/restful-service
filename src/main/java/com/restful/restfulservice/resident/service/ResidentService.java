package com.restful.restfulservice.resident.service;

import java.util.List;
import java.util.Optional;

import com.restful.restfulservice.resident.exception.EmailAlreadyExistsException;
import com.restful.restfulservice.resident.model.ResidentRecord;

public interface ResidentService {
	
	Optional<ResidentRecord> getResidentById(Long id);
	List<ResidentRecord> getResidents();
	ResidentRecord registerResident(ResidentRecord record) throws EmailAlreadyExistsException;
	ResidentRecord updateResident(Long id, ResidentRecord record) throws EmailAlreadyExistsException;
	ResidentRecord deleteResident(Long id);
		
}
