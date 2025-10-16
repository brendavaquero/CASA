package org.casa.backend.enums;

public enum GradoEstudio {
    SIN_ESTUDIOS("Sin estudios"),
    PRIMARIA("Primaria"),
    SECUNDARIA("Secundaria"),
    BACHILLERATO("Bachillerato"),
    TECNICO("Técnico"),
    LICENCIATURA("Licenciatura"),
    ESPECIALIDAD("Especialidad"),
    MAESTRIA("Maestría"),
    DOCTORADO("Doctorado"),
    POSDOCTORADO("Posdoctorado");

    private final String nombre;

    GradoEstudio(String nombre) {
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
