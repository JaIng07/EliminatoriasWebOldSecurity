package com.example.eliminatorias.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Getter
@Setter
public class PartidoDto {
	@JsonProperty("id")
    private Long id;
	@JsonProperty("fecha")
	@NotNull
    //@NotBlank(message = "La fecha del partido es requerida")
    //@JsonFormat(pattern = "yyyy/MM/dd")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate fecha;
	@JsonProperty("estadio")
	@NotNull
    @NotBlank(message = "El estadio del partido es requerido")
    private String estadio;
	@JsonProperty("local")
    private EquipoDto local;
	@JsonProperty("visitante")
    private EquipoDto visitante;
	@JsonProperty("resultado")
    private ResultadoDto resultado;
	@JsonProperty("arbitroPrincipal")
	@NotNull
    @NotBlank(message = "El arbitro principal del partido es requerido")
    private String arbitroPrincipal;
}
