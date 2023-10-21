package com.example.eliminatorias.dtos;

import com.example.eliminatorias.entities.Partido;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PartidoMapper {
    PartidoDto partidoToPartidoDto(Partido partido);
}
