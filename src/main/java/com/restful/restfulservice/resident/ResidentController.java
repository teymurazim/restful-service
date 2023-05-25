package com.restful.restfulservice.resident;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "${apiPrefix}/resident")
public class ResidentController {

	private final ResidentService residentService;

	@Autowired
	public ResidentController(ResidentService residentService) {
		this.residentService = residentService;

	}

	@GetMapping
	public ResponseEntity<Object> getResidents() {
		return residentService.getResidents();
	}

	@GetMapping(path = "{residentId}")
	public ResponseEntity<Object> getResidentById(@PathVariable("residentId") Long id) {
		return residentService.getResidentById(id);
	}

	@PostMapping
	public ResponseEntity<Object> registerNewResident(@RequestBody ResidentRecord resident) {
		return residentService.registerNewResident(resident);
	}
	
	@PutMapping(path = "{residentId}")
	public ResponseEntity<Object> updateResident(@PathVariable("residentId") Long residentId,
			@RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) String email) {

		return residentService.updateResident(residentId, firstName, lastName, email);

	}

	@DeleteMapping(path = "{residentId}")
	public void deleteResident(@PathVariable("residentId") Long residentId) {
		residentService.deleteResident(residentId);
	}
}