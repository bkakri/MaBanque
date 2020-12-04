package com.example.demo.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Entities.Operation;

public interface OperationRepository extends JpaRepository<Operation,Long> {
	
	@Query("select o from operation o where o.compte.codeCompte=:x order by o.dateOperation desc")
	public Page<Operation> listoperation(@Param("x")String codeCpte,Pageable pageable);

}
