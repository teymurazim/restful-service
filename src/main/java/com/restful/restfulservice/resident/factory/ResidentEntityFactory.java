package com.restful.restfulservice.resident.factory;

import org.springframework.stereotype.Component;

import com.restful.restfulservice.factory.AbstractEntityFactory;
import com.restful.restfulservice.resident.model.Resident;
import com.restful.restfulservice.resident.model.ResidentRecord;

@Component
public class ResidentEntityFactory extends AbstractEntityFactory<ResidentRecord, Resident> {

	@Override
	public Resident buildEntity(ResidentRecord residentRecord) {
		
		if(residentRecord == null)
			return null;
		
		Resident resident = new Resident();
		resident.setFirstName(residentRecord.firstName());
		resident.setLastName(residentRecord.lastName());
		resident.setEmail(residentRecord.email());
		resident.setDateOfBirth(residentRecord.dateOfBirth());
		
		return resident;
	}
	
}
