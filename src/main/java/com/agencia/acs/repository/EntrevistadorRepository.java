package com.agencia.acs.repository;

import com.agencia.acs.entities.Entrevistador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrevistadorRepository  extends JpaRepository<Entrevistador, Long> {

     List<Entrevistador> findAllByCentrosId(Long id);
}
