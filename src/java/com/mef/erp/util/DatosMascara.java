/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.util;

import javax.swing.JFormattedTextField;
import javax.swing.JFormattedTextField.AbstractFormatter;

/**
 *
 * @author Admin
 */
public class DatosMascara {

    private String mascara = "";
    private String caracterMarcador = "";
    private String literalMarcador = "";
    private String tipoMascara = "";
    private JFormattedTextField.AbstractFormatter formatoMascara;

    public String getCaracterMarcador() {
        return caracterMarcador;
    }

    public void setCaracterMarcador(String caracterMarcador) {
        this.caracterMarcador = caracterMarcador;
    }

    public AbstractFormatter getFormatoMascara() {
        return formatoMascara;
    }

    public void setFormatoMascara(AbstractFormatter formatoMascara) {
        this.formatoMascara = formatoMascara;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getLiteralMarcador() {
        return literalMarcador;
    }

    public void setLiteralMarcador(String literalMarcador) {
        if (literalMarcador == null) {
            literalMarcador = "_";
        } else if (literalMarcador.trim().length() == 0) {
            literalMarcador = "_";
        }
        this.literalMarcador = literalMarcador;
    }

    public String getTipoMascara() {
        return tipoMascara;
    }

    public void setTipoMascara(String tipoMascara) {
        this.tipoMascara = tipoMascara;
    }
}
