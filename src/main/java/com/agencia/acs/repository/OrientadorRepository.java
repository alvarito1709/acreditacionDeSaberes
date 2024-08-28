package com.agencia.acs.repository;

import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Orientador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface OrientadorRepository extends JpaRepository<Orientador, Long> {


    List<Orientador> findAllByCentrosId(Long id);
}
