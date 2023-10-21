package com.example.eliminatorias.repositories;

import com.example.eliminatorias.entities.Partido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface PartidoRepository extends JpaRepository<Partido, Long> {
    //Query METHODS
    List<Partido> findByFecha(LocalDate fecha);
    List<Partido> findByEstadio(String estadio);
    @Query("SELECT p FROM Partido p WHERE p.local.id = ?1 OR p.visitante.id = ?1")
    List<Partido> findByEquipo(Long idEquipo);
    List<Partido> findByArbitroPrincipal(String arbitroPrincipal);
}

