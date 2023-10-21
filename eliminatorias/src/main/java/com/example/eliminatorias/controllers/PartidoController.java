package com.example.eliminatorias.controllers;

import com.example.eliminatorias.dtos.PartidoDto;
import com.example.eliminatorias.dtos.EquipoDto;
import com.example.eliminatorias.dtos.ResultadoDto;
import com.example.eliminatorias.dtos.PartidoMapper;
import com.example.eliminatorias.entities.Partido;
import com.example.eliminatorias.services.PartidoService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PartidoController {
    private final PartidoService partidoService;
    private final PartidoMapper partidoMapper;

    public PartidoController(PartidoService partidoService, PartidoMapper partidoMapper) {
        this.partidoService = partidoService;
        this.partidoMapper = partidoMapper;
    }

    // CRUD Operations

    // GET http://localhost:8088/api/partidos
    @GetMapping("/partidos")
    public ResponseEntity<List<PartidoDto>> findAll() {
        List<Partido> partidosList = partidoService.findAllPartidos();

        List<PartidoDto> partidoDtoList = partidosList.stream().map(partido -> {
            PartidoDto partidoDto = new PartidoDto();
            partidoDto.setId(partido.getId());
            partidoDto.setFecha(partido.getFecha());
            partidoDto.setEstadio(partido.getEstadio());

            if (partido.getLocal() != null) {
                EquipoDto equipoLocalDto = new EquipoDto();
                equipoLocalDto.setId(partido.getLocal().getId());
                equipoLocalDto.setNombre(partido.getLocal().getNombre());
                equipoLocalDto.setBandera(partido.getLocal().getBandera());
                equipoLocalDto.setDirectorTecnico(partido.getLocal().getDirectorTecnico());
                partidoDto.setLocal(equipoLocalDto);
            }

            if (partido.getVisitante() != null) {
                EquipoDto equipoVisitanteDto = new EquipoDto();
                equipoVisitanteDto.setId(partido.getVisitante().getId());
                equipoVisitanteDto.setNombre(partido.getVisitante().getNombre());
                equipoVisitanteDto.setBandera(partido.getVisitante().getBandera());
                equipoVisitanteDto.setDirectorTecnico(partido.getVisitante().getDirectorTecnico());
                partidoDto.setVisitante(equipoVisitanteDto);
            }

            partidoDto.setArbitroPrincipal(partido.getArbitroPrincipal());

            if (partido.getResultado() != null) {
                ResultadoDto resultadoDto = new ResultadoDto();
                resultadoDto.setId(partido.getResultado().getId());
                resultadoDto.setGolLocal(partido.getResultado().getGolLocal());
                resultadoDto.setGolVisitante(partido.getResultado().getGolVisitante());
                resultadoDto.setNroTarjetasAmarillas(partido.getResultado().getNroTarjetasAmarillas());
                resultadoDto.setNroTarjetasRojas(partido.getResultado().getNroTarjetasRojas());
                partidoDto.setResultado(resultadoDto);
            }

            return partidoDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(partidoDtoList);
    }




    // GET http://localhost:8088/api/partidos/1
    @GetMapping("/partidos/{id}")
    public ResponseEntity<PartidoDto> findPartidoById(@PathVariable Long id) {
        Optional<Partido> partidoOptional = partidoService.findPartidoById(id);

        if (partidoOptional.isPresent()) {
            PartidoDto partidoDto = partidoMapper.partidoToPartidoDto(partidoOptional.get());
            return ResponseEntity.ok().body(partidoDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /* {
		  "fecha": "2023-09-30",
		  "estadio": "El Monumental",
		  "local": {
		    "id": 1
		  },
		  "visitante": {
		    "id": 2
		  },
		  "marcador": {
		    "golLocal": 3,
		    "golVisitante": 1,
		    "nroTarjetasAmarillas": 7,
		    "nroTarjetasRojas": 2
		  },
		  "arbitroPrincipal": "Ralph Freeman"
}

*/
    @PostMapping("/partidos")
    public ResponseEntity<Partido> createPartido(@RequestBody Partido partido) {
        Partido partidoCreated = partidoService.createPartido(partido);

        // http://localhost:8088/api/partidos/1
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest() // http://localhost:8088/api/
                .path("/{id}") // 1
                .buildAndExpand(partidoCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(partidoCreated);
    }


    /* PUT http://localhost:8088/api/partidos/1 -> body {
    	"fecha": "2023-09-23",
    	"estadio": "El monumentaal",
    	"local": {
        	"id": 1,
        	"nombre": "Argentina",
        	"bandera": "ARG",
        	"directorTecnico": "Scaloni"
    	},
    	"visitante": {
        	"id": 2,
        	"nombre": "Colombia",
        	"bandera": "COL",
        	"directorTecnico": "Nestor Lorenzo"
    	},
    	"marcador": {
        	"golLocal": 3,
        	"golVisitante": 1,
        	"nroTarjetasAmarillas": 4,
        	"nroTarjetasRojas": 1
    	},
    	"arbitroPrincipal": "Juan Pérez"
}
    */
    @PutMapping("/partidos/{id}")
    public ResponseEntity<Partido> updatePartido(@PathVariable Long id, @RequestBody Partido updatedPartido) {
        Optional<Partido> partido = partidoService.updatePartido(id, updatedPartido);

        return partido.map(partidoUpdatedInDb -> ResponseEntity.ok().body(partidoUpdatedInDb)).orElseGet(() -> {
            Partido partidoCreated = partidoService.createPartido(updatedPartido);

            // http://localhost:8088/api/partidos/1
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest() // http://localhost:8088/api/
                    .path("/{id}") // 1
                    .buildAndExpand(partidoCreated.getId())
                    .toUri();

            return ResponseEntity.created(location).body(partidoCreated);
        });
    }

    // DELETE http://localhost:8088/api/partidos/1
    @DeleteMapping("/partidos/{id}")
    public ResponseEntity<Partido> deletePartido(@PathVariable("id") Long id) {
        partidoService.deletePartido(id);

        return ResponseEntity.noContent().build();
    }

    // Query METHODS

    // Buscar partido por fecha
    // GET http://localhost:8088/api/partidos/fecha?fecha=2023-09-23
    @GetMapping("/partidos/fecha")
    public ResponseEntity<List<Partido>> findByFecha(@RequestParam("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Partido> partidos = partidoService.findByFecha(fecha);

        if (partidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(partidos);
        }
    }

    // Buscar partido por estadio
    // GET http://localhost:8088/api/partidos/estadio?estadio=El monumental
    @GetMapping("/partidos/estadio")
    public ResponseEntity<List<Partido>> findByEstadio(@RequestParam("estadio") String estadio) {
        List<Partido> partidos = partidoService.findByEstadio(estadio);

        if (partidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(partidos);
        }
    }

    // Buscar partido por equipo
    // GET http://localhost:8088/api/partidos/equipo?idEquipo=1
    @GetMapping("/partidos/equipo")
    public ResponseEntity<List<Partido>> findByEquipo(@RequestParam("idEquipo") Long idEquipo) {
        List<Partido> partidos = partidoService.findByEquipo(idEquipo);

        if (partidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(partidos);
        }
    }

    // Buscar partido por arbitro
    // GET http://localhost:8088/api/partidos/arbitroPrincipal?arbitroPrincipal=Juan Pérez
    @GetMapping("/partidos/arbitroPrincipal")
    public ResponseEntity<List<Partido>> findByArbitroPrincipal(@RequestParam("arbitro") String arbitroPrincipal) {
        List<Partido> partidos = partidoService.findByArbitroPrincipal(arbitroPrincipal);

        if (partidos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(partidos);
        }
    }
}
