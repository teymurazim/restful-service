package com.restful.restfulservice.resident.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.restful.restfulservice.resident.exception.EmailAlreadyExistsException;
import com.restful.restfulservice.resident.factory.ResidentEntityFactory;
import com.restful.restfulservice.resident.factory.ResidentRecordFactory;
import com.restful.restfulservice.resident.model.Resident;
import com.restful.restfulservice.resident.model.ResidentRecord;
import com.restful.restfulservice.resident.repository.ResidentRepository;
import com.restful.restfulservice.resident.service.ResidentService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * @Service and also @Repository are special types of a @Component.
 * The major difference between these stereotypes is that they are used for different classifications.
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ResidentServiceImpl implements ResidentService {

	private final ResidentRepository residentRepository;
	private final ResidentRecordFactory rrf;
	private final ResidentEntityFactory ref;
	
	@Override
	public List<ResidentRecord> getResidents() {
		log.info("Fetching residents from the repository...");
		List<Resident> residents = residentRepository.findAll(PageRequest.of(0, 10)).toList();
		return residents.stream().map(r -> rrf.buildRecord(r)).toList();
	}
	
	@Override
	public Optional<ResidentRecord> getResidentById(Long id) {
		log.info("Fetching the resident({}) from the repository...", id);
		return residentRepository.findById(id).map(resident -> rrf.buildRecord(resident));
	}
	

	@Override
	public ResidentRecord registerResident(ResidentRecord residentRecord){
		
		log.info("Registering new resident: {} {}...", residentRecord.firstName(),residentRecord.lastName());
		
		if(residentEmailExists(residentRecord.email())) {
			log.info("The resident with provided email ({}) already exists.",residentRecord.email());
			throw new EmailAlreadyExistsException(residentRecord.email());
		}
		
		Resident resident = ref.buildEntity(residentRecord);
		residentRepository.saveAndFlush(resident);
		log.info("Finished registering {} {}",resident.getFirstName(), resident.getLastName());
		return rrf.buildRecord(resident);
	
	}
	
	public boolean residentEmailExists(String email) {
		return residentRepository.findByEmail(email).isPresent();
	}
	
	@Override
	public ResidentRecord updateResident(Long id, ResidentRecord residentUpdate){
		
		if(residentEmailExists(residentUpdate.email())) {
			log.info("The resident with provided email ({}) already exists.",residentUpdate.email());
			throw new EmailAlreadyExistsException(residentUpdate.email());
		}
		
		Optional<Resident> resident = residentRepository.findById(id);
		
		log.info("Updating resident {}({}) information...", resident.get().getFirstName(),id);
		
		if(residentUpdate.firstName() != null)
			resident.get().setFirstName(residentUpdate.firstName());
		
		if(residentUpdate.lastName() != null)
			resident.get().setLastName(residentUpdate.lastName());
		
		if(residentUpdate.email() != null)
			resident.get().setEmail(residentUpdate.email());
		
		residentRepository.saveAndFlush(resident.get());
		log.info("Finished updating resident {}({}) information.", resident.get().getFirstName(),id);
		return resident.map(r -> rrf.buildRecord(r)).get();
		
	}
	
	public ResidentRecord deleteResident(Long id) {
		
		log.info("Deleting resident({})...", id);
		Optional<Resident> resident = residentRepository.findById(id);
		residentRepository.deleteById(id);
		log.info("The resident ({} {}) has been deleted.", resident.get().getFirstName(), resident.get().getLastName());
		return resident.map(r -> rrf.buildRecord(r)).get();
	}
	
}