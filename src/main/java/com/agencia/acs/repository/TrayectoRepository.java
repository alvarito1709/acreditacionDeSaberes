package com.agencia.acs.repository;


import com.agencia.acs.entities.Trayecto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrayectoRepository extends JpaRepository<Trayecto, Long> {

    List<Trayecto> findAllBySectorId(Long id);
}
