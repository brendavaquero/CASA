package org.casa.backend.enums;

public enum LenguaInd {
    NINGUNA("Ninguna"),
    NAHUATL("Náhuatl"),
    MAYA("Maya"),
    TZELTAL("Tzeltal"),
    TZOTZIL("Tzotzil"),
    MIXTECO("Mixteco"),
    ZAPOTECO("Zapoteco"),
    OTOMI("Otomí"),
    TOTONACO("Totonaco"),
    MAZATECO("Mazateco"),
    CHOCHO("Chocho"),
    CHINANTECO("Chinanteco"),
    MAZAHUA("Mazahua"),
    HUICHOL("Huichol"),
    PURHEPECHA("Purépecha"),
    MAYO("Mayo"),
    YAQUI("Yaqui"),
    TARAHUMARA("Tarahumara"),
    MIXE("Mixe"),
    TRIQUI("Triqui"),
    AMUZGO("Amuzgo"),
    CHATINO("Chatino"),
    TOJOLABAL("Tojolabal"),
    HUAWE("Huave"),
    CUICATECO("Cuicateco"),
    POPOLOCA("Popoloca"),
    CHONTAL("Chontal"),
    OTRA("Otra lengua indígena");

    private final String nombre;

    LenguaInd(String nombre) {
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
