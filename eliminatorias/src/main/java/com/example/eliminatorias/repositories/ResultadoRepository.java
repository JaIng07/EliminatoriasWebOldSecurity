package com.example.eliminatorias.repositories;

import com.example.eliminatorias.entities.Resultado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultadoRepository extends JpaRepository<Resultado, Long> {
    //Query METHODS
    List<Resultado> findByGolLocalOrGolVisitante(int golLocal, int golVisitante);
    List<Resultado> findByNroTarjetasAmarillasOrNroTarjetasRojas(int nroTarjetasAmarillas, int nroTarjetasRojas);
}
