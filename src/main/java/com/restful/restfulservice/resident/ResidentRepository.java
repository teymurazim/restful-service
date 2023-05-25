package com.restful.restfulservice.resident;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long>{
	
	
	/*
	 * You have tell Spring to treat that query as native one. Otherwise it will try to validate it according to the JPA specification.
	 * 
	 * If you want to use map the results directly to a POJO class you would have to define the mapping, define a native query, define the method
	 * in the CrudRepository without @Query annotation
	 * 
	 */
	Optional<Resident> findResidentByEmail(String email);
}