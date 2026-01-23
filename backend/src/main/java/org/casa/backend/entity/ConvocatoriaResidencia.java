package org.casa.backend.entity;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.enums.TipoActividad;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "convocatoria_residencia")
@PrimaryKeyJoinColumn(name = "id_actividad")
public class ConvocatoriaResidencia extends Actividad{

    @Column(name = "bases", columnDefinition = "TEXT")
    private String bases;

    @Column(name = "premio", columnDefinition = "TEXT")
    private String premio;

    @Column(name = "convocantes", columnDefinition = "TEXT")
    private String convocantes;

    @OneToMany(mappedBy = "convocatoria")
    private List<Jurado> jurados;

    @Column(name = "fecha_inicior1")
    private LocalDate fechaInicioR1;

    @Column(name = "fecha_limiter1")
    private LocalDate fechaLimiteR1;

    public ConvocatoriaResidencia(
            String idActividad,
            String titulo,
            String descripcion,
            TipoActividad tipo,
            LocalDate fechaInicio,
            LocalDate fechaCierre,
            LocalDate fechaResultados,
            Instant fechaCreacion,
            String requisitos,
            EstadoActividad estado,
            String imagen,
            boolean requiereMuestraTrabajo,
            boolean visible,
            boolean infantil,
            String bases, String premio, String convocantes, LocalDate fechaInicioR1, LocalDate fechaLimiteR1
    )
    {
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo, visible, infantil);
        this.bases = bases;
        this.premio = premio;
        this.convocantes= convocantes;
        this.fechaInicioR1=fechaInicioR1;
        this.fechaLimiteR1=fechaLimiteR1;
    }
}
