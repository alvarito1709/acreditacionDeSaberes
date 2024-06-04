package com.agencia.acs.repository;

import com.agencia.acs.entities.Referente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReferenteRepository extends JpaRepository<Referente, Long> {
}
