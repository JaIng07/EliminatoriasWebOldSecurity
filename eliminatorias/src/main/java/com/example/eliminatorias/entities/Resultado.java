package com.example.eliminatorias.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Resultados")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Resultado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@NotBlank(message = "La cantidad de goles del equipo local es requerida")
    @Column(name = "CantidadGolesLocal")
    private int golLocal;

    @NotNull
    //@NotBlank(message = "La cantidad de goles del equipo visitante es requerida")
    @Column(name = "CantidadGolesVisitante")
    private int golVisitante;

    @NotNull
    //@NotBlank(message = "La cantidad de tarjetas amarillas del partido es requerida")
    @Column(name = "NumeroTarjetasAmarillas")
    private int nroTarjetasAmarillas;

    @NotNull
    //@NotBlank(message = "La cantidad de tarjetas rojas del partido es requerida")
    @Column(name = "NumeroTarjetasRojas")
    private int nroTarjetasRojas;
    
    @OneToOne(mappedBy = "resultado")
    private Partido partido;
    
    /*@OneToOne
    @JoinColumn(name = "partido_id")
    private Partido partido;*/

    public Resultado updateWith(Resultado resultado) {
        return new Resultado(this.id, resultado.golLocal, resultado.golVisitante, resultado.nroTarjetasAmarillas, resultado.nroTarjetasRojas, resultado.partido);
    }
}

