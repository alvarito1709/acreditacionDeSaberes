package com.agencia.acs.repository;

import com.agencia.acs.entities.Centro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
@PersistenceContext
public interface CentroRepository  extends JpaRepository<Centro, String> {

 Centro findById(Long id);

public void deleteById(Long id);

@Query(value = "SELECT r " +
        "FROM Centro r " +
        "WHERE r.id IN :centros" )
 Set<Centro> findAllById(@Param("centros") List<Long> centros);

 @EntityGraph(value = "Centro.detail", type = EntityGraph.EntityGraphType.LOAD)
 @Query(value = "SELECT c.id, c.nombre, c.direccion, c.telefono, " +
         "c.numeroDeCentro, c.codigo, c.cue, c.tipo, c.area," +
         " c.estado, c.trayectos FROM Centro c  LEFT JOIN c.trayectos WHERE c.id = :id")
 Centro findfullCentro(Long id);

}
