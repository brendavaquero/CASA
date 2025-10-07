package org.casa.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
    @Column(name = "id_programa", length = 20, nullable = false)
    private String idPrograma;

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @Column(name = "nombre_prog", length = 100, nullable = false)
    private String nombrePrograma;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Usuario usuario;

    @PrePersist
    protected void onCreate() {
    }

    public Programa() {
    }

    public Programa(String nombrePrograma, Usuario usuario) {
        this.nombrePrograma = nombrePrograma;
        this.usuario = usuario;
    }
}