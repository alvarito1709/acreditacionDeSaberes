package com.agencia.acs.repository;

import com.agencia.acs.entities.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CentroRepository  extends JpaRepository<Centro, String> {

public Centro findById(Long id);

public void deleteById(Long id);

}
