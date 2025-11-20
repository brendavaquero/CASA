package org.casa.backend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.EstadoActividad;
import org.casa.backend.enums.TipoActividad;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity

@Table(name = "taller_diplomado")
@PrimaryKeyJoinColumn(name = "id_actividad")
public class TallerDiplomado extends Actividad {

    @Column(name = "cupo")
    private Integer cupo;

    @Column(name = "objetivo_general", length = Integer.MAX_VALUE)
    private String objetivoGeneral;

    @Column(name = "objetivos_especificos", length = Integer.MAX_VALUE)
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programa", nullable = true)
    private Programa programa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_usuario")
    @JsonBackReference
    private Docente docente;

    public TallerDiplomado(
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
            Integer cupo,
            String objetivoGeneral,
            String objetivosEspecificos,
            String temas,
            String materialSol,
            String criteriosSeleccion,
            String notas,
            Integer numSesiones,
            Programa programa,
            Docente docente
    ) {
        super(idActividad, titulo, descripcion, tipo, fechaInicio, fechaCierre, fechaResultados, fechaCreacion, requisitos, estado, imagen, requiereMuestraTrabajo);
        this.cupo = cupo;
        this.objetivoGeneral = objetivoGeneral;
        this.objetivosEspecificos = objetivosEspecificos;
        this.temas = temas;
        this.materialSol = materialSol;
        this.criteriosSeleccion = criteriosSeleccion;
        this.notas = notas;
        this.numSesiones = numSesiones;
        this.programa = programa;
        this.docente = docente;
    }

    @OneToMany(mappedBy = "tallerDiplomado", cascade = CascadeType.ALL)
    private List<Sesion> sesiones = new ArrayList<>();

}
