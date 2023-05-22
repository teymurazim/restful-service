package com.restful.restfulservice.customer;

import java.time.Month;
import java.time.LocalDate;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomerConfig {
	
	/*
	 * Remove ID since it is generated now by DB
	 * Hibernate is running when we invoke saveAll()
	 */
	
	@Bean
	CommandLineRunner commandLineRunner(CustomerRepository customerRepository) {
		return args -> {
			Customer teymur = new Customer("Teymur",
					"Azimzada",
					"teymur_azimzada@yahoo.com",
					LocalDate.of(1999, Month.APRIL, 14));
			
			Customer asad = new Customer("Asad",
					"Zeynalov",
					"asad_zeynal@yahoo.com",
					LocalDate.of(1997, Month.SEPTEMBER, 20));
			
			customerRepository.saveAll(List.of(teymur,asad));
		};
	}
}