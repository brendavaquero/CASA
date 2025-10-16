package org.casa.backend.entity;

import java.time.LocalDateTime;

import org.casa.backend.enums.EstadoPost;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "postulacion")
public class Postulacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_postulacion", length = 50, nullable = false)
    private String idPostulacion;

    @Column(name = "num", insertable = false, updatable = false)
    private Integer num;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private Participante participante;

    @ManyToOne
    @JoinColumn(name = "id_actividad", nullable = false)
    private Actividad actividad;

    @Column(name = "postulante", length = 100)
    private String postulante;

    @Column(name = "motivo", columnDefinition = "TEXT")
    private String motivo;

    @Enumerated(EnumType.STRING)
    @Column(name = "estado", length = 50)
    private EstadoPost estadoPos = EstadoPost.PENDIENTE;

    @Column(name = "fecha_post", nullable = false)
    private LocalDateTime fechaPostulacion;

    @PrePersist
    protected void onCreate() {
        if (fechaPostulacion == null) {
            fechaPostulacion = LocalDateTime.now();
        }
        if (estadoPos == null) {
            estadoPos = EstadoPost.PENDIENTE;
        }
    }

    public Postulacion() {}
}
