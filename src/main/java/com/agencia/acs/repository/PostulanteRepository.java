package com.agencia.acs.repository;

import com.agencia.acs.entities.Centro;
import com.agencia.acs.entities.Postulante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostulanteRepository extends JpaRepository<Postulante, Long> {

}
