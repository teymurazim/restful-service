package com.restful.restfulservice.customer;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query("SELECT c FROM Customer c WHERE c.email = ?1")
	Optional<Customer> findCustomerByEmail(String email);
}