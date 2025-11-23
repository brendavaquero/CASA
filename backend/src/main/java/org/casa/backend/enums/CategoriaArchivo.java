package org.casa.backend.enums;

public enum CategoriaArchivo {
    RECURSO("Recurso"),
    EVIDENCIA("Audio o Video");

    private final String nombre;

    CategoriaArchivo(String nombre) {
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
