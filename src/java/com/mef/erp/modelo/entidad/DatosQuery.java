/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatosQuery {

    private List<String> listParametros = new ArrayList<String>();
    private String query;
    private Map<String, String> aliasTablas;
    private Map<String, Object> parametrosCampos;
    private boolean conParametros = false;

    public List<String> getListParametros() {
        return listParametros;
    }

    public void setListParametros(List<String> listParametros) {
        this.listParametros = listParametros;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public Map<String, String> getAliasTablas() {
        return aliasTablas;
    }

    public void setAliasTablas(Map<String, String> aliasTablas) {
        this.aliasTablas = aliasTablas;
    }

    public Map<String, Object> getParametrosCampos() {
        return parametrosCampos;
    }

    public void setParametrosCampos(Map<String, Object> parametrosCampos) {
        this.parametrosCampos = parametrosCampos;
    }

    public boolean isConParametros() {
        return conParametros;
    }

    public void setConParametros(boolean conParametros) {
        this.conParametros = conParametros;
    }
}
