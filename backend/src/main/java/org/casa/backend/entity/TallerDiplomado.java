package org.casa.backend.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "taller_diplomado")
@PrimaryKeyJoinColumn(name = "id_actividad")
public class TallerDiplomado extends Actividad {

    @Column(name = "tipo", length = 100)
    private String tipo;

    @Column(name = "cupo")
    private Integer cupo;

    @Column(name = "objetivo_general", columnDefinition = "TEXT")
    private String objetivoGeneral;

    @Column(name = "objetivos_especificos", columnDefinition = "TEXT")
    private String objetivosEspecificos;

    @Column(name = "temas", columnDefinition = "TEXT")
    private String temas;

    @Column(name = "material_sol", columnDefinition = "TEXT")
    private String materialSol;

    @Column(name = "criterios_seleccion", columnDefinition = "TEXT")
    private String criteriosSeleccion;

    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

    @Column(name = "num_sesiones")
    private Integer numSesiones;

    public TallerDiplomado() {
        super();
    }

    public TallerDiplomado(String titulo, String descripcion, LocalDate fechaInicio, LocalDate fechaCierre, LocalDate fechaResultados, LocalDateTime fechaCreacion, String requisitos, String estado) {
        super(titulo,descripcion,fechaInicio,fechaCierre,fechaResultados,fechaCreacion,requisitos,estado);
    }
}
