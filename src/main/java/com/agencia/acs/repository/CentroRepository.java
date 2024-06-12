package com.agencia.acs.repository;

import com.agencia.acs.entities.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CentroRepository  extends JpaRepository<Centro, Long> {



}
