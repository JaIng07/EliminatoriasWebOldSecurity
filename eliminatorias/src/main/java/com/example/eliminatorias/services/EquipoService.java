package com.example.eliminatorias.services;

import com.example.eliminatorias.entities.Equipo;

import java.util.List;
import java.util.Optional;

public interface EquipoService {
    //CRUD Operations
    List<Equipo> findAllEquipos();
    Optional<Equipo> findEquipoById(Long id);
    Equipo createEquipo(Equipo equipo);
    Optional<Equipo> updateEquipo(Long id, Equipo equipo);
    void deleteEquipo(Long id);

    //Query METHODS
    List<Equipo> findByNombre(String nombre);
    List<Equipo> findByBandera(String bandera);
    List<Equipo> findByDirectorTecnico(String directorTecnico);
    List<Equipo> findByNombreContaining(String nombre);
    List<Equipo> findByBanderaContaining(String bandera);
    List<Equipo> findByDirectorTecnicoContaining(String directorTecnico);
}
