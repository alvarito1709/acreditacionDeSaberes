package com.agencia.acs.service;

import com.agencia.acs.entities.Sector;
import com.agencia.acs.repository.SectorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
