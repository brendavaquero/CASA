package org.casa.backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "programa")
public class Programa {

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa", length = 20, insertable = false)
    private String idPrograma;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    public Programa(String idPrograma, String nombre, String descripcion, List<Usuario> responsables) {
        this.idPrograma = idPrograma;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.responsables = responsables;
    }

    @OneToMany(mappedBy = "programa", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<TallerDiplomado> talleres = new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "programa_responsable",
            joinColumns = @JoinColumn(name = "id_programa"),
            inverseJoinColumns = @JoinColumn(name = "id_usuario")
    )
    private List<Usuario> responsables = new ArrayList<>();

}