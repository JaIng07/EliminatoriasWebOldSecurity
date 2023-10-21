package com.example.eliminatorias.services;

import com.example.eliminatorias.entities.Partido;
import org.springframework.data.jpa.repository.Query;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PartidoService {
    //CRUD Operations
    List<Partido> findAllPartidos();
    Optional<Partido> findPartidoById(Long id);
    Partido createPartido(Partido partido);
    Optional<Partido> updatePartido(Long id, Partido partido);
    void deletePartido(Long id);

    //Query METHODS
    List<Partido> findByFecha(LocalDate fecha);
    List<Partido> findByEstadio(String estadio);
    @Query("SELECT p FROM Partido p WHERE p.local.id = ?1 OR p.visitante.id = ?1")
    List<Partido> findByEquipo(Long idEquipo);
    List<Partido> findByArbitroPrincipal(String arbitroPrincipal);
}

