package com.example.eliminatorias.controllers;

import com.example.eliminatorias.dtos.ResultadoDto;
import com.example.eliminatorias.dtos.ResultadoMapper;
import com.example.eliminatorias.entities.Resultado;
import com.example.eliminatorias.services.ResultadoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class ResultadoController {
    private final ResultadoService resultadoService;
    private final ResultadoMapper resultadoMapper;

    public ResultadoController(ResultadoService resultadoService, ResultadoMapper resultadoMapper) {
        this.resultadoService = resultadoService;
        this.resultadoMapper = resultadoMapper;
    }

    // CRUD Operations

    // GET http://localhost:8088/api/resultados
    @GetMapping("/resultados")
    public ResponseEntity<List<ResultadoDto>> findAll() {
        List<Resultado> resultadosList = resultadoService.findAllResultados();
        
        List<ResultadoDto> resultadoDtoList = resultadosList.stream().map(resultado -> {
        	ResultadoDto resultadoDto = new ResultadoDto();
        	
        	resultadoDto.setId(resultado.getId());
        	resultadoDto.setGolLocal(resultado.getGolLocal());
        	resultadoDto.setGolVisitante(resultado.getGolVisitante());
        	resultadoDto.setNroTarjetasAmarillas(resultado.getNroTarjetasAmarillas());
        	resultadoDto.setNroTarjetasRojas(resultado.getNroTarjetasRojas());
        	return resultadoDto;
        }).collect(Collectors.toList());
        
        return ResponseEntity.ok().body(resultadoDtoList);
    }

    // GET http://localhost:8088/api/resultados/1
    @GetMapping("/resultados/{id}")
    public ResponseEntity<ResultadoDto> findResultadoById(@PathVariable Long id) {
        Optional<Resultado> resultadoOptional = resultadoService.findResultadoById(id);

        if (resultadoOptional.isPresent()) {
            ResultadoDto resultadoDto = resultadoMapper.resultadoToResultadoDto(resultadoOptional.get());
            return ResponseEntity.ok().body(resultadoDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST http://localhost:8088/api/resultados -> body {"golLocal": 2, "golVisitante": 3, "nroTarjetasAmarillas": 4, "nroTarjetasRojas": 1}
    @PostMapping("/resultados")
    public ResponseEntity<Resultado> createResultado(@RequestBody Resultado resultado) {
        Resultado resultadoCreated = resultadoService.createResultado(resultado);

        // http://localhost:8088/api/resultados/1
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // http://localhost:8088/api/
                .path("/{id}") // 1
                .buildAndExpand(resultadoCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(resultadoCreated);
    }

    // PUT http://localhost:8088/api/resultados/1 -> body {"golLocal": 2, "golVisitante": 3, "nroTarjetasAmarillas": 4, "nroTarjetasRojas": 1}
    @PutMapping("/resultados/{id}")
    public ResponseEntity<Resultado> updateResultado(@PathVariable Long id, @RequestBody Resultado updatedResultado) {
        Optional<Resultado> resultado = resultadoService.updateResultado(id, updatedResultado);

        return resultado.map(resultadoUpdatedInDb -> ResponseEntity.ok().body(resultadoUpdatedInDb)).orElseGet(() -> {
            Resultado resultadoCreated = resultadoService.createResultado(updatedResultado);

            // http://localhost:8088/api/resultados/1
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest() // http://localhost:8088/api/
                    .path("/{id}") // 1
                    .buildAndExpand(resultadoCreated.getId())
                    .toUri();

            return ResponseEntity.created(location).body(resultadoCreated);
        });
    }

    // DELETE http://localhost:8088/api/resultados/1
    @DeleteMapping("/resultados/{id}")
    public ResponseEntity<Resultado> deleteResultado(@PathVariable("id") Long id) {
        resultadoService.deleteResultado(id);

        return ResponseEntity.noContent().build();
    }

    // Query METHODS

    // Buscar resultado por goles del equipo local o goles del equipo visitante
    // GET http://localhost:8088/api/resultados/goles?golLocal=3&golVisitante=2
    @GetMapping("/resultados/goles")
    public ResponseEntity<List<Resultado>> findByGolLocalOrGolVisitante(@RequestParam("golLocal") int golLocal, @RequestParam("golVisitante") int golVisitante) {
        List<Resultado> resultados = resultadoService.findByGolLocalOrGolVisitante(golLocal, golVisitante);

        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(resultados);
        }
    }

    // Buscar por numero de tarjetas amarillas o rojas
    // GET http://localhost:8088/api/resultados/tarjetas?amarillas=4&rojas=8
    @GetMapping("/resultados/tarjetas")
    public ResponseEntity<List<Resultado>> findByNroTarjetasAmarillasOrNroTarjetasRojas(@RequestParam("amarillas") int nroTarjetasAmarillas, @RequestParam("rojas") int nroTarjetasRojas) {
        List<Resultado> resultados = resultadoService.findByNroTarjetasAmarillasOrNroTarjetasRojas(nroTarjetasAmarillas, nroTarjetasRojas);

        if (resultados.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(resultados);
        }
    }
}

