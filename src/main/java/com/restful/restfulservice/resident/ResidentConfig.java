package com.restful.restfulservice.resident;

import java.time.Month;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResidentConfig {
	
	/*
	 * Remove ID since it is generated now by DB
	 * Hibernate is running when we invoke saveAll()
	 */
	
	@Bean
	CommandLineRunner commandLineRunner(ResidentRepository residentRepository) {
		return args -> {
			ResidentEntity teymur = new ResidentEntity("Teymur",
					"Azimzada",
					"teymur_azimzada@yahoo.com",
					LocalDate.of(1999, Month.APRIL, 14));
			
			ResidentEntity asad = new ResidentEntity("Asad",
					"Zeynalov",
					"asad_zeynal@yahoo.com",
					LocalDate.of(1997, Month.SEPTEMBER, 20));
			
			residentRepository.saveAll(List.of(teymur,asad));
		};
	}
}