package com.example.eliminatorias.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Equipos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Equipo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank(message = "El nombre del equipo es requerido")
    @Column(name = "Nombre")
    private String nombre;

    @NotNull
    @NotBlank(message = "La bandera del equipo es requerida")
    @Size(max = 3)
    @Column(name = "Bandera")
    private String bandera;

    @NotNull
    @NotBlank(message = "El Director Tecnico del equipo es requerido")
    @Column(name = "DirectorTecnico")
    private String directorTecnico;

    @OneToMany(mappedBy = "local")
    private List<Partido> partidosLocal;

    @OneToMany(mappedBy = "visitante")
    private List<Partido> partidosVisitante;

    public Equipo updateWith(Equipo equipo) {
        return new Equipo(this.id, equipo.nombre, equipo.bandera, equipo.directorTecnico, equipo.partidosLocal, equipo.partidosVisitante);
    }
}
