package org.casa.backend.enums;

public enum EstadoActividad {
    PENDIENTE("Pendiente de revisión"),
    AUTORIZADA("Autorizada"),
    RECHAZADA("Rechazada"),
    CONVOCATORIA_ABIERTA("Convocatoria Abierta"),
    CONVOCATORIA_CERRADA("Convocatoria Cerrada"),
    EN_CURSO("En curso"),
    FINALIZADA("Finalizada"),
    CANCELADA("Cancelada");

    private final String nombre;

    EstadoActividad(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
