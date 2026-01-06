package org.casa.backend.enums;

public enum TipoArchivo {
    IMAGEN("Imagen"),
    AUDIO("Audio"),
    VIDEO("Video"),
    DOCUMENTO("Documento"),
    OTRO("Otro");

    private final String nombre;

    TipoArchivo(String nombre) {
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
