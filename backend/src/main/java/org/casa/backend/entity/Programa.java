package org.casa.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "programa")
public class Programa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_programa", length = 20, insertable = false)
    private String idPrograma;

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "text")
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_actividad",nullable = false)
    private TallerDiplomado idActividad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario idUsuario;

    public Programa() {
    }

    public Programa(String nombre, String descripcion, TallerDiplomado activDiplomado, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idActividad = activDiplomado;
        this.idUsuario = usuario;

    }
}