package com.agencia.acs.repository;

import com.agencia.acs.entities.Evaluador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluadorRepository extends JpaRepository<Evaluador, Long> {
}
