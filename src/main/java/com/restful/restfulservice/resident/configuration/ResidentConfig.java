package com.restful.restfulservice.resident.configuration;

import java.time.Month;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.restful.restfulservice.resident.model.Resident;
import com.restful.restfulservice.resident.repository.ResidentRepository;

@Configuration
public class ResidentConfig {
	
	/*
	 * Remove ID since it is generated now by DB
	 * Hibernate is running when we invoke saveAll()
	 */
	
	@Bean
	CommandLineRunner commandLineRunner(ResidentRepository residentRepository) {
		return args -> {
			Resident teymur = new Resident("Teymur",
					"Azimzada",
					"teymur_azimzada@yahoo.com",
					LocalDate.of(1999, Month.APRIL, 14));
			
			Resident asad = new Resident("Asad",
					"Zeynalov",
					"asad_zeynal@yahoo.com",
					LocalDate.of(1997, Month.SEPTEMBER, 20));
			
			Resident yuliia = new Resident("Yuliia",
					"Melnyk",
					"melyulianyk@gmail.com",
					LocalDate.of(1995, Month.SEPTEMBER, 27));
			
			residentRepository.saveAll(List.of(teymur,asad,yuliia));
		};
	}
}