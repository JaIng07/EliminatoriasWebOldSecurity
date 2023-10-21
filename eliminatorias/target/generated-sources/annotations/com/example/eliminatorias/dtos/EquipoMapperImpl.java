package com.example.eliminatorias.dtos;

import com.example.eliminatorias.entities.Equipo;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-10-15T19:58:22-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class EquipoMapperImpl implements EquipoMapper {

    @Override
    public EquipoDto equipoToEquipoDto(Equipo equipo) {
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
}
