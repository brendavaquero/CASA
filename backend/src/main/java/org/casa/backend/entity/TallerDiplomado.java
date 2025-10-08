package org.casa.backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.casa.backend.enums.EstadoActividad;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity

@Table(name = "taller_diplomado")
@PrimaryKeyJoinColumn(name = "id_actividad")
public class TallerDiplomado extends Actividad {

    @Size(max = 100)
    @Column(name = "tipo", length = 100)
    private String tipo;

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

    public TallerDiplomado(
            String idActividad,
            String titulo,
            String descripcion,
            LocalDate fechaInicio,
            LocalDate fechaCierre,
            LocalDate fechaResultados,
            //Instant fechaCreacion,
            String requisitos,
            EstadoActividad estado,
            String tipo,
            Integer cupo,
            String objetivoGeneral,
            String objetivosEspecificos,
            String temas,
            String materialSol,
            String criteriosSeleccion,
            String notas,
            Integer numSesiones
    ) {
        super(idActividad, titulo, descripcion, fechaInicio, fechaCierre, fechaResultados, requisitos, estado);
        this.tipo = tipo;
        this.cupo = cupo;
        this.objetivoGeneral = objetivoGeneral;
        this.objetivosEspecificos = objetivosEspecificos;
        this.temas = temas;
        this.materialSol = materialSol;
        this.criteriosSeleccion = criteriosSeleccion;
        this.notas = notas;
        this.numSesiones = numSesiones;
    }


    /*@ManyToMany
    @JoinTable(name = "programa_taller",
            joinColumns = @JoinColumn(name = "id_taller_dip"),
            inverseJoinColumns = @JoinColumn(name = "id_programa"))
    private Set<Programa> programas = new LinkedHashSet<>();

    @OneToMany
    @JoinColumn(name = "id_taller_diplomado")
    private Set<Sesion> sesions = new LinkedHashSet<>();*/

}