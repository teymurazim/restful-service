package com.restful.restfulservice.resident.factory;

import org.springframework.stereotype.Component;

import com.restful.restfulservice.factory.AbstractRecordFactory;
import com.restful.restfulservice.resident.Resident;
import com.restful.restfulservice.resident.ResidentRecord;

@Component
public class ResidentRecordFactory extends AbstractRecordFactory<Resident, ResidentRecord>  {

	
	@Override
	public ResidentRecord buildRecord(Resident resident) {
		
		if(resident == null)
			return null;
				
		return new ResidentRecord(resident.getFirstName(),resident.getLastName(),resident.getEmail(),resident.getDateOfBirth(),resident.getAge());
	}
		
}

/*
 * @Component public class ResidentDTOFactory extends
 * AbstractDTOFactory<ResidentEntity, ResidentDTO> {
 * 
 * @Autowired private ResidentRepository residentRepository;
 * 
 * public ResidentDTO getResidentById(Long residentId) { return
 * buildDTO(residentRepository.getReferenceById(residentId)); }
 * 
 * @Override public ResidentDTO buildDTO(ResidentEntity resident) {
 * 
 * if(resident == null) return null;
 * 
 * ResidentDTO residentDTO = new ResidentDTO();
 * residentDTO.setFirstName(resident.getFirstName());
 * residentDTO.setLastName(resident.getLastName());
 * residentDTO.setEmail(resident.getEmail());
 * residentDTO.setAge(resident.getAge());
 * 
 * return residentDTO; }
 * 
 * }
 */
