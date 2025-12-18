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

    @Column(name = "premio")
    private String premio;

    @Column(name = "convocantes", columnDefinition = "TEXT")
    private String convocantes;

    @OneToMany(mappedBy = "convocatoria")
    private List<Jurado> jurados;

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
            boolean visible, String bases, String premio, String convocantes
        )
    {
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo, visible);
        this.bases = bases;
        this.premio = premio;
        this.convocantes= convocantes;
    }
}
