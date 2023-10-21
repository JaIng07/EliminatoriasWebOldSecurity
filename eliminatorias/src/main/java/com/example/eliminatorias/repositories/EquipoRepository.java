package com.example.eliminatorias.repositories;

import com.example.eliminatorias.entities.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    //Query METHODS
    List<Equipo> findByNombre(String nombre);
    List<Equipo> findByBandera(String bandera);
    List<Equipo> findByDirectorTecnico(String directorTecnico);
    List<Equipo> findByNombreContaining(String nombre);
    List<Equipo> findByBanderaContaining(String bandera);
    List<Equipo> findByDirectorTecnicoContaining(String directorTecnico);
}
