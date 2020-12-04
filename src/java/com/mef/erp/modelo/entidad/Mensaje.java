/**
 * @author: Victor Lopez
 * Compañía: Macropro.
 * Descripción del programa: clase manejo de errores en servidor
 * Fecha: 16/03/2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:
 * Autor:
 * Fecha:
 * Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class Mensaje implements Serializable {
    private String error;
    private int noError;
    private Object resultado;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public int getNoError() {
        return noError;
    }

    public void setNoError(int noError) {
        this.noError = noError;
    }

    public Object getResultado() {
        return resultado;
    }

    public void setResultado(Object resultado) {
        this.resultado = resultado;
    }
}
