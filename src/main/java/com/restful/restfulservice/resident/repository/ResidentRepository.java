package com.restful.restfulservice.resident.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restful.restfulservice.resident.model.Resident;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, Long>{
	
	Optional<Resident> findByEmail(String email);
}