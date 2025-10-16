package org.casa.backend.enums;

public enum TipoActividad {
    TALLER_PRESENCIAL("Taller presencial"),
    TALLER_EN_LINEA("Taller en línea"),
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
