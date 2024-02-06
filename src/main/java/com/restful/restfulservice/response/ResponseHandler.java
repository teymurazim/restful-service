package com.restful.restfulservice.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class ResponseHandler {

	public ResponseEntity<Object> generateResponse(HttpStatus statusCode, Object responseObject) {
		
		if(responseObject == null) 
			return new ResponseEntity<Object>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		
		return new ResponseEntity<Object>(responseObject,statusCode);
	}
	
}
