/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mef.erp.modelo.entidad;

/**
 *
 * @author Desarrollo 094
 */
public enum TipoDato {
C("Valor Concepto"),
G("Parte Grabable"),
E("Parte Excepta");
   
private final String text;
    
private TipoDato(final String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

}
