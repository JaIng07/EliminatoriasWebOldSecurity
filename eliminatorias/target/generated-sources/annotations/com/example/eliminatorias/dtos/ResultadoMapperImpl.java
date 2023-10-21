package com.example.eliminatorias.dtos;

import com.example.eliminatorias.entities.Resultado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-15T19:58:22-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class ResultadoMapperImpl implements ResultadoMapper {

    @Override
    public ResultadoDto resultadoToResultadoDto(Resultado resultado) {
        if ( resultado == null ) {
            return null;
        }

        ResultadoDto resultadoDto = new ResultadoDto();

        resultadoDto.setGolLocal( resultado.getGolLocal() );
        resultadoDto.setGolVisitante( resultado.getGolVisitante() );
        resultadoDto.setId( resultado.getId() );
        resultadoDto.setNroTarjetasAmarillas( resultado.getNroTarjetasAmarillas() );
        resultadoDto.setNroTarjetasRojas( resultado.getNroTarjetasRojas() );

        return resultadoDto;
    }
}
