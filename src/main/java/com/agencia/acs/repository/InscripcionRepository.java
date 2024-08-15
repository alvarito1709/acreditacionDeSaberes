package com.agencia.acs.repository;


import com.agencia.acs.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {


    List<Inscripcion> findAllByPostulanteId(Long id);

    List<Inscripcion> findAllByEntrevistadorId(Long id);
}
