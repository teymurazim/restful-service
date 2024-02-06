package com.restful.restfulservice.response.model;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.experimental.SuperBuilder;


@Data
@SuperBuilder
@JsonInclude(Include.NON_NULL)
public class Response {
	
	protected LocalDateTime timeStamp;
	protected String message;
	protected String error;
	protected String logMessage;
	protected Map<?, ?> data;
}
