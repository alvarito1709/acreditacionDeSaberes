package com.agencia.acs.repository;


import com.agencia.acs.entities.Sector;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SectorRepository  extends JpaRepository<Sector, String> {

   Sector findById(Long id);
}
