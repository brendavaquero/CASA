package org.casa.backend.enums;

public enum Pais {
    // América del Norte
    ESTADOS_UNIDOS("Estados Unidos"),
    CANADA("Canadá"),
    MEXICO("México"),

    // América Central
    BELICE("Belice"),
    COSTA_RICA("Costa Rica"),
    EL_SALVADOR("El Salvador"),
    GUATEMALA("Guatemala"),
    HONDURAS("Honduras"),
    NICARAGUA("Nicaragua"),
    PANAMA("Panamá"),

    // Caribe
    ANTIGUA_Y_BARBUDA("Antigua y Barbuda"),
    BAHAMAS("Bahamas"),
    BARBADOS("Barbados"),
    CUBA("Cuba"),
    DOMINICA("Dominica"),
    GRANADA("Granada"),
    HAITI("Haití"),
    JAMAICA("Jamaica"),
    REPUBLICA_DOMINICANA("República Dominicana"),
    SAN_CRISTOBAL_Y_NIEVES("San Cristóbal y Nieves"),
    SAN_VICENTE_Y_LAS_GRANADINAS("San Vicente y las Granadinas"),
    SANTA_LUCIA("Santa Lucía"),
    TRINIDAD_Y_TOBAGO("Trinidad y Tobago"),

    // América del Sur
    ARGENTINA("Argentina"),
    BOLIVIA("Bolivia"),
    BRASIL("Brasil"),
    CHILE("Chile"),
    COLOMBIA("Colombia"),
    ECUADOR("Ecuador"),
    GUYANA("Guyana"),
    PARAGUAY("Paraguay"),
    PERU("Perú"),
    SURINAM("Surinam"),
    URUGUAY("Uruguay"),
    VENEZUELA("Venezuela"),

    // Territorios y dependencias
    GUADALUPE("Guadalupe"),
    GUYANA_FRANCESA("Guayana Francesa"),
    MARTINICA("Martinica"),
    PUERTO_RICO("Puerto Rico"),
    ISLAS_CAIMAN("Islas Caimán"),
    ISLAS_VIRGENES_BRITANICAS("Islas Vírgenes Británicas"),
    ISLAS_VIRGENES_AMERICANAS("Islas Vírgenes Americanas"),
    BERMUDAS("Bermudas"),
    GROENLANDIA("Groenlandia"),
    SAN_PEDRO_Y_MIQUELON("San Pedro y Miquelón"),
    ARUBA("Aruba"),
    CURACAO("Curazao"),
    BONAIRE("Bonaire"),
    SAN_EUSTAQUIO("San Eustaquio"),
    SABA("Saba"),
    SAN_MARTIN("San Martín"),
    SAN_MARTIN_FRANCES("San Martín (Francés)"),
    ISLAS_MALVINAS("Islas Malvinas"),
    ISLAS_GEORGIAS_DEL_SUR("Islas Georgias del Sur"),
    ISLAS_SANDWICH_DEL_SUR("Islas Sandwich del Sur");

    private final String nombre;

    Pais(String nombre) {
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
