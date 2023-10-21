package com.example.eliminatorias.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResultadoDto {
	@JsonProperty("id")
    private Long id;
	@JsonProperty("golLocal")
    @NotNull
    //@NotBlank(message = "La cantidad de goles del equipo local es requerida")
    private int golLocal;
	@JsonProperty("golVisitante")
	@NotNull
    //@NotBlank(message = "La cantidad de goles del equipo visitante es requerida")
    private int golVisitante;
	@JsonProperty("nroTarjetasAmarillas")
	@NotNull
    //@NotBlank(message = "La cantidad de tarjetas amarillas del partido es requerida")
    private int nroTarjetasAmarillas;
	@JsonProperty("nroTarjetasRojas")
	@NotNull
    //@NotBlank(message = "La cantidad de tarjetas rojas del partido es requerida")
    private int nroTarjetasRojas;
	/*@JsonProperty("partido")
	private PartidoDto partido;*/
}
