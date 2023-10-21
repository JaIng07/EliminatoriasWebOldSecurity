package com.example.eliminatorias.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EquipoDto {
	@JsonProperty("id")
    private Long id;
	@JsonProperty("nombre")
	@NotNull
    @NotBlank(message = "El nombre del equipo es requerido")
    private String nombre;
	@JsonProperty("bandera")
	@NotNull
    @NotBlank(message = "La bandera del equipo es requerida")
    @Size(max = 3)
    private String bandera;
	@JsonProperty("directorTecnico")
	@NotNull
	@NotBlank(message = "El Director Tecnico del equipo es requerido")
    private String directorTecnico;
}
