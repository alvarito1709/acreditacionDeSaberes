package com.agencia.acs.service;

import com.agencia.acs.DTO.OrientadorDTO;
import com.agencia.acs.DTO.SectorDTO;
import com.agencia.acs.entities.Orientador;
import com.agencia.acs.entities.Sector;
import com.agencia.acs.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class SectorService {

    @Autowired
    SectorRepository sectorRepository;

    public List<Sector> listarSectores(){return sectorRepository.findAll();}

    public void guardarSector(Sector sector){sectorRepository.save(sector);}

    public Sector buscarSectorPorId(Long id){return sectorRepository.findById(id);}

    public void borrarSector(Long id){ Sector sector = sectorRepository.findById(id);
    sectorRepository.delete(sector);
    }

    public SectorDTO buscarSectorDTO(Long id){

        final Optional<Sector> sectorOptional = Optional.ofNullable(sectorRepository.findById(id));

        if (sectorOptional.isEmpty()){
            throw new EntityNotFoundException("Sector con id" + id + "no encontrado");
        }

        final Sector sector = sectorOptional.get();

        return new SectorDTO(sector.getId(), sector.getNombre(), sector.getDescripcion(), sector.getEstado());
    }
}
