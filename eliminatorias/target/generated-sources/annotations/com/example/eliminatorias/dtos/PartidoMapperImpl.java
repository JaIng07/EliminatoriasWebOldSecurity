package com.example.eliminatorias.dtos;

import com.example.eliminatorias.entities.Equipo;
import com.example.eliminatorias.entities.Partido;
import com.example.eliminatorias.entities.Resultado;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-15T19:58:22-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class PartidoMapperImpl implements PartidoMapper {

    @Override
    public PartidoDto partidoToPartidoDto(Partido partido) {
        if ( partido == null ) {
            return null;
        }

        PartidoDto partidoDto = new PartidoDto();

        partidoDto.setArbitroPrincipal( partido.getArbitroPrincipal() );
        partidoDto.setEstadio( partido.getEstadio() );
        partidoDto.setFecha( partido.getFecha() );
        partidoDto.setId( partido.getId() );
        partidoDto.setLocal( equipoToEquipoDto( partido.getLocal() ) );
        partidoDto.setResultado( resultadoToResultadoDto( partido.getResultado() ) );
        partidoDto.setVisitante( equipoToEquipoDto( partido.getVisitante() ) );

        return partidoDto;
    }

    protected EquipoDto equipoToEquipoDto(Equipo equipo) {
        if ( equipo == null ) {
            return null;
        }

        EquipoDto equipoDto = new EquipoDto();

        equipoDto.setBandera( equipo.getBandera() );
        equipoDto.setDirectorTecnico( equipo.getDirectorTecnico() );
        equipoDto.setId( equipo.getId() );
        equipoDto.setNombre( equipo.getNombre() );

        return equipoDto;
    }

    protected ResultadoDto resultadoToResultadoDto(Resultado resultado) {
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
