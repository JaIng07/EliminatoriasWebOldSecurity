package com.example.eliminatorias.services;

import com.example.eliminatorias.entities.Resultado;
import java.util.List;
import java.util.Optional;

public interface ResultadoService {
    //CRUD Operations
    List<Resultado> findAllResultados();
    Optional<Resultado> findResultadoById(Long id);
    Resultado createResultado(Resultado resultado);
    Optional<Resultado> updateResultado(Long id, Resultado resultado);
    void deleteResultado(Long id);

    //Query METHODS
    List<Resultado> findByGolLocalOrGolVisitante(int golLocal, int golVisitante);
    List<Resultado> findByNroTarjetasAmarillasOrNroTarjetasRojas(int nroTarjetasAmarillas, int nroTarjetasRojas);
}
