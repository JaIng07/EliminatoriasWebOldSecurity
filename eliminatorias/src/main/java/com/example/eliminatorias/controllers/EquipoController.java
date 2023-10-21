package com.example.eliminatorias.controllers;

import com.example.eliminatorias.dtos.EquipoDto;
import com.example.eliminatorias.dtos.EquipoMapper;
import com.example.eliminatorias.entities.Equipo;
import com.example.eliminatorias.services.EquipoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class EquipoController {
    private final EquipoService equipoService;
    private final EquipoMapper equipoMapper;

    public EquipoController(EquipoService equipoService, EquipoMapper equipoMapper) {
        this.equipoService = equipoService;
        this.equipoMapper = equipoMapper;
    }

    // CRUD Operations

    // GET http://localhost:8088/api/equipos
    @GetMapping("/equipos")
    public ResponseEntity<List<EquipoDto>> findAll() {
        List<Equipo> equiposList = equipoService.findAllEquipos();
        
        List<EquipoDto> equipoDtoList = equiposList.stream().map(equipo -> {
            EquipoDto equipoDto = new EquipoDto();
            equipoDto.setId(equipo.getId());
            equipoDto.setNombre(equipo.getNombre());
            equipoDto.setBandera(equipo.getBandera());
            equipoDto.setDirectorTecnico(equipo.getDirectorTecnico());
            return equipoDto;
        }).collect(Collectors.toList());

        return ResponseEntity.ok().body(equipoDtoList);
    }

    // GET http://localhost:8088/api/equipos/1
    @GetMapping("/equipos/{id}")
    public ResponseEntity<EquipoDto> findEquipoById(@PathVariable Long id) {
        Optional<Equipo> equipoOptional = equipoService.findEquipoById(id);

        if (equipoOptional.isPresent()) {
            EquipoDto equipoDto = equipoMapper.equipoToEquipoDto(equipoOptional.get());
            return ResponseEntity.ok().body(equipoDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // POST http://localhost:8088/api/equipos -> body {"bandera": "URU", "directorTecnico": "Javier Figueroa","nombre": "Uruguay"}
    @PostMapping("/equipos")
    public ResponseEntity<Equipo> createEquipo(@RequestBody Equipo equipo) {
        Equipo equipoCreated = equipoService.createEquipo(equipo);


        // http://localhost:8088/api/equipos/1
        URI location = ServletUriComponentsBuilder
                        .fromCurrentRequest() // http://localhost:8088/api/
                        .path("/{id}") // 1
                        .buildAndExpand(equipoCreated.getId())
                        .toUri();
        return ResponseEntity.created(location).body(equipoCreated);
    }

    // PUT http://localhost:8088/api/equipos/1 -> body {"bandera": "VEN", "directorTecnico": "Javier Fig", "nombre": "Venezuela"}
    @PutMapping("/equipos/{id}")
    public ResponseEntity<Equipo> updateEquipo(@PathVariable Long id, @RequestBody Equipo updatedEquipo) {
        Optional<Equipo> equipo = equipoService.updateEquipo(id, updatedEquipo);

        return equipo.map(equipoUpdatedInDb -> ResponseEntity.ok().body(equipoUpdatedInDb)).orElseGet(() -> {
            Equipo equipoCreated = equipoService.createEquipo(updatedEquipo);

            // http://localhost:8088/api/equipos/1
            URI location = ServletUriComponentsBuilder
                            .fromCurrentRequest() // http://localhost:8088/api/
                            .path("/{id}") // 1
                            .buildAndExpand(equipoCreated.getId())
                            .toUri();

            return ResponseEntity.created(location).body(equipoCreated);
        });
    }

    // DELETE http://localhost:8088/api/equipos/1
    @DeleteMapping("/equipos/{id}")
    public ResponseEntity<Equipo> deleteEquipo(@PathVariable("id") Long id) {
        equipoService.deleteEquipo(id);

        return ResponseEntity.noContent().build();
    }

    // Query METHODS

    // Buscar equipo por nombre
    // GET http://localhost:8088/api/equipos/nombre?nombre=Argentina
    @GetMapping("/equipos/nombre")
    public ResponseEntity<List<Equipo>> findByNombre(@RequestParam("nombre") String nombre) {
        List<Equipo> equipos = equipoService.findByNombre(nombre);

        if (equipos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(equipos);
    }

    // Buscar equipo por bandera
    // GET http://localhost:8088/api/equipos/bandera?bandera=ARG
    @GetMapping("/equipos/bandera")
    public ResponseEntity<List<Equipo>> findByBandera(@RequestParam("bandera") String bandera) {
        List<Equipo> equipos = equipoService.findByBandera(bandera);

        if (equipos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(equipos);
    }

    // Buscar equipo por DT
    // GET http://localhost:8088/api/equipos/directorTecnico?directorTecnico=Javier Figueroa
    @GetMapping("/equipos/directorTecnico")
    public ResponseEntity<List<Equipo>> findByDirectorTecnico(@RequestParam("directorTecnico") String directorTecnico) {
        List<Equipo> equipos = equipoService.findByDirectorTecnico(directorTecnico);

        if (equipos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(equipos);
    }

    // Buscar equipo por nombre (containing)
    // GET http://localhost:8088/api/equipos/nombreContaining?nombre=Arge
    @GetMapping("/equipos/nombreContaining")
    public ResponseEntity<List<Equipo>> findByNombreContaining(@RequestParam("nombre") String nombre) {
        List<Equipo> equipos = equipoService.findByNombreContaining(nombre);

        if (equipos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(equipos);
    }

    // Buscar equipo por bandera (containing)
    // GET http://localhost:8088/api/equipos/banderaContaining?bandera=A
    @GetMapping("/equipos/banderaContaining")
    public ResponseEntity<List<Equipo>> findByBanderaContaining(@RequestParam("bandera") String bandera) {
        List<Equipo> equipos = equipoService.findByBanderaContaining(bandera);

        if (equipos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(equipos);
    }

    // Buscar equipo por DT (containing)
    // GET http://localhost:8088/api/equipos/directorTecnicoContaining?directorTecnico=J
    @GetMapping("/equipos/directorTecnicoContaining")
    public ResponseEntity<List<Equipo>> findByDirectorTecnicoContaining(@RequestParam("directorTecnico") String directorTecnico) {
        List<Equipo> equipos = equipoService.findByDirectorTecnicoContaining(directorTecnico);

        if (equipos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(equipos);
    }
}