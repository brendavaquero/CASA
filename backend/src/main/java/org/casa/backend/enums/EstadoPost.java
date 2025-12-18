package org.casa.backend.enums;

public enum EstadoPost {
    PENDIENTE("pendiente"),
    APROBADA ("aprobada"),
    RECHAZADA("rechazada"),
    EVALUADO("evaluado");

    private final String estadoP;

    EstadoPost(String estadoP) {
        this.estadoP = estadoP;
    }

    public String getNombre() {
        return estadoP;
    }

    @Override
    public String toString() {
        return estadoP;
    }
}
