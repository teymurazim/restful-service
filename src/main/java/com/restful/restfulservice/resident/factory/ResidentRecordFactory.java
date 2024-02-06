package com.restful.restfulservice.resident.factory;

import org.springframework.stereotype.Component;

import com.restful.restfulservice.factory.AbstractRecordFactory;
import com.restful.restfulservice.resident.model.Resident;
import com.restful.restfulservice.resident.model.ResidentRecord;

@Component
public class ResidentRecordFactory extends AbstractRecordFactory<Resident, ResidentRecord>  {

	@Override
	public ResidentRecord buildRecord(Resident resident) {
		
		if(resident == null)
			return null;
				
		return new ResidentRecord(resident.getId(), resident.getFirstName(), resident.getLastName(), resident.getEmail(), resident.getDateOfBirth(), resident.getAge());
	}
		
}