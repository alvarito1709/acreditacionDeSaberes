package com.agencia.acs.repository;

import com.agencia.acs.entities.Entrevistador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrevistadorRepository  extends JpaRepository<Entrevistador, Long> {
}
