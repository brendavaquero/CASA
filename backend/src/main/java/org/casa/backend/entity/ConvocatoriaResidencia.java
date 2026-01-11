package org.casa.backend.entity;

import java.sql.Date;
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
    private Date fechaInicioR1;
    
    @Column(name = "fecha_limiter1")
    private Date fechaLimiteR1;

    @Column(name = "fecha_inicior2")
    private Date fechaInicioR2;
    
    @Column(name = "fecha_limiter2")
    private Date fechaLimiteR2;

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
            boolean visible, String bases, String premio, String convocantes, Date fechaInicioR1, Date fechaLimiteR1, Date fechaInicioR2, Date fechaLimiteR2
    )
    {
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo, visible);
        this.bases = bases;
        this.premio = premio;
        this.convocantes= convocantes;
        this.fechaInicioR1=fechaInicioR1;
        this.fechaLimiteR1=fechaLimiteR1;
        this.fechaInicioR2=fechaInicioR2;
        this.fechaLimiteR2=fechaLimiteR2;
    }
}
