package com.mef.erp.modelo.entidad;

public enum TipoOpcionMenu {

    INTERNO("Interno"),
    EXTERNO("Externo");
    private final String tipo;

    TipoOpcionMenu(String tipo) {
        this.tipo = tipo;
    }
}
