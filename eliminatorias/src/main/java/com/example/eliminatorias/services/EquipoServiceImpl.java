package com.example.eliminatorias.services;

import com.example.eliminatorias.entities.Equipo;
import com.example.eliminatorias.exceptions.EquipoNotFoundException;
import com.example.eliminatorias.repositories.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipoServiceImpl implements EquipoService {
    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public List<Equipo> findAllEquipos() {
        return equipoRepository.findAll();
    }

    @Override
    public Optional<Equipo> findEquipoById(Long id) {
        return equipoRepository.findById(id);
    }

    @Override
    public Equipo createEquipo(Equipo equipo) {
        Equipo equipoCopy = new Equipo();
        equipoCopy.setNombre(equipo.getNombre());
        equipoCopy.setBandera(equipo.getBandera());
        equipoCopy.setDirectorTecnico(equipo.getDirectorTecnico());

        return equipoRepository.save(equipoCopy);
    }

    @Override
    public Optional<Equipo> updateEquipo(Long id, Equipo newEquipo) {
        Optional<Equipo> equipoInDB = equipoRepository.findById(id);

        Optional<Equipo> equipoUpdated = equipoInDB.map(oldEquipoInDb -> {
            Equipo updated = oldEquipoInDb.updateWith(newEquipo);
            return equipoRepository.save(updated);
        });
        return equipoUpdated;
    }

    @Override
    public void deleteEquipo(Long id) {
        equipoRepository.deleteById(id);
    }

    @Override
    public List<Equipo> findByNombre(String nombre) {
        List<Equipo> equipos = equipoRepository.findByNombre(nombre);

        if(equipos.isEmpty()) {
            throw new EquipoNotFoundException("No se encontraron equipos con ese nombre");
        }
        return equipos;
    }

    @Override
    public List<Equipo> findByBandera(String bandera) {
        List<Equipo> equipos = equipoRepository.findByBandera(bandera);

        if(equipos.isEmpty()) {
            throw new EquipoNotFoundException("No se encontraron equipos con esa bandera");
        }
        return equipos;
    }

    @Override
    public List<Equipo> findByDirectorTecnico(String directorTecnico) {
        List<Equipo> equipos = equipoRepository.findByDirectorTecnico(directorTecnico);

        if(equipos.isEmpty()) {
            throw new EquipoNotFoundException("No se encontraron equipos con ese director tecnico");
        }
        return equipos;
    }

    @Override
    public List<Equipo> findByNombreContaining(String nombre) {
        List<Equipo> equipos = equipoRepository.findByNombreContaining(nombre);

        if(equipos.isEmpty()) {
            throw new EquipoNotFoundException("No se encontraron equipos que contengan esa cadena de texto en su nombre");
        }
        return equipos;
    }

    @Override
    public List<Equipo> findByBanderaContaining(String bandera) {
        List<Equipo> equipos = equipoRepository.findByBanderaContaining(bandera);

        if(equipos.isEmpty()) {
            throw new EquipoNotFoundException("No se encontraron equipos que contengan esa cadena de texto en su bandera");
        }
        return equipos;
    }

    @Override
    public List<Equipo> findByDirectorTecnicoContaining(String directorTecnico) {
        List<Equipo> equipos = equipoRepository.findByDirectorTecnicoContaining(directorTecnico);

        if(equipos.isEmpty()) {
            throw new EquipoNotFoundException("No se encontraron equipos donde su DT contenga esa cadena de texto");
        }
        return equipos;
    }
}
