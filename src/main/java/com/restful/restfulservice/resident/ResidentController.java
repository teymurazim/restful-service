package com.restful.restfulservice.resident;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.restful.restfulservice.resident.ResidentService.EmailAlreadyExistsException;

@RestController
@RequestMapping(path="${apiPrefix}/resident")
public class ResidentController {

	private final ResidentService residentService;
	
	@Autowired
	public ResidentController(ResidentService residentService) {
		this.residentService = residentService;
	}
	
	@GetMapping
	public List<ResidentEntity> getresidents() {
		return residentService.getResidents();
	}
	
	@PostMapping
	public ResponseEntity<String> registerNewresident(@RequestBody ResidentEntity residentEntity) {
		try {
			residentService.addNewResident(residentEntity);
		} catch (EmailAlreadyExistsException e) {
			return new ResponseEntity<String>(e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT);
			
		}
		return new ResponseEntity<String>("Successfully registered", new HttpHeaders(), HttpStatus.CREATED);
	}
	
	//TODO:GetMappingWith resident Id
	
	@PutMapping(path = "{residentId}")
	public void updateresident(@PathVariable("residentId") Long residentId, 
							   @RequestParam(required = false) String firstName,
							   @RequestParam(required = false) String lastName,
							   @RequestParam (required = false) String email) {
		 
		residentService.updateResident(residentId, firstName, lastName, email);
		
	}
	
	
	@DeleteMapping(path = "{residentId}")
	public void deleteresident(@PathVariable("residentId") Long residentId) {
		residentService.deleteResident(residentId);
	}
}