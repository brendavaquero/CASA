package org.casa.backend.enums;

public enum TipoArchivo {
    IMAGEN("Imagen"),
    AUDIO_VIDEO("Audio o Video"),
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
