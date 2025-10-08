package org.casa.backend.entity;

/*@Getter
@Setter
@Entity
@Table(name = "convocatoria_residencia")
public class ConvocatoriaResidencia {
    @Id
    @Size(max = 50)
    @Column(name = "id_actividad", nullable = false, length = 50)
    private String idActividad;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_actividad", nullable = false)
    private Actividad actividad;

    @Size(max = 50)
    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "bases", length = Integer.MAX_VALUE)
    private String bases;

    @Size(max = 200)
    @Column(name = "premio", length = 200)
    private String premio;

    @Column(name = "convocantes", length = Integer.MAX_VALUE)
    private String convocantes;

    @Column(name = "fecha_inicio")
    private LocalDate fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDate fechaFin;

}*/