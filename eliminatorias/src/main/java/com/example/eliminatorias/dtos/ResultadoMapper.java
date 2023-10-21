package com.example.eliminatorias.dtos;

import com.example.eliminatorias.entities.Resultado;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {PartidoMapper.class})
public interface ResultadoMapper {
    ResultadoDto resultadoToResultadoDto(Resultado resultado);
}
