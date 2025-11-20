package org.casa.backend.enums;

public enum TipoActividad {
    TALLER("Taller"),
    DIPLOMADO("Diplomado"),
    CONVOCATORIA("Convocatoria"),
    RESIDENCIA("Residencia");

    private final String nombre;

    TipoActividad(String nombre) {
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
