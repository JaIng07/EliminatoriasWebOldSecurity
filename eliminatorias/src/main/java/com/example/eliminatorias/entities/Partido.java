package com.example.eliminatorias.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerator;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Partidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Partido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    //@NotBlank(message = "La fecha del partido es requerida")
    //@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    //@JsonFormat(pattern = "yyyy/MM/dd")
    @Column(name = "Fecha")
    private LocalDate fecha;

    @NotNull
    @NotBlank(message = "El estadio del partido es requerido")
    @Column(name = "Estadio")
    private String estadio;

    @ManyToOne
    @JoinColumn(name = "local", referencedColumnName = "id")
    private Equipo local;

    @ManyToOne
    @JoinColumn(name = "visitante", referencedColumnName = "id")
    private Equipo visitante;
    
    @OneToOne
    @JoinColumn(name = "resultado", referencedColumnName = "id")
    private Resultado resultado;

    @NotNull
    @NotBlank(message = "El arbitro principal del partido es requerido")
    @Column(name = "ArbitroPrincipal")
    private String arbitroPrincipal;

    public Partido updateWith(Partido partido) {
        return new Partido(this.id, partido.fecha, partido.estadio, partido.local, partido.visitante, partido.resultado, partido.arbitroPrincipal);
    }
}

