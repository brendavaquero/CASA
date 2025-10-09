package org.casa.backend.enums;

public enum Rol {
    ADMINISTRADOR("ADMINISTRADOR"),
    JURADO("JURADO"),
    PARTICIPANTE("PARTICIPANTE"),
    TRABAJADOR("TRABAJADOR"),
    INVITADO("INVITADO");

    private final String valor;
    Rol(String valor){
        this.valor=valor;
    }

    public String getValor(){
        return valor;
    }

    public static Rol fromString(String texto){
        for (Rol rol : Rol.values()) {
            if (rol.valor.equalsIgnoreCase(texto)) {
                return rol;
            }
        }
        throw new IllegalArgumentException("Rol no válido: " + texto);
    }
}
