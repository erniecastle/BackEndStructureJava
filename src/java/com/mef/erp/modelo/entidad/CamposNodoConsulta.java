/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

import javax.swing.tree.DefaultMutableTreeNode;

public class CamposNodoConsulta extends DefaultMutableTreeNode {

    private String nombreCampoBD;
    private String nombreMostrar;
    private int sizeColumna;
    private Class tipoDato;
    private String formato;
    private String nombreRecurso;
    private String pathCamposRelacionados;
    private CamposNodoConsulta nodoPadre;
    private String datoConsulta;

    public CamposNodoConsulta() {
    }

    public Class getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(Class tipoDato) {
        this.tipoDato = tipoDato;
    }

    public String getNombreMostrar() {
        return nombreMostrar;
    }

    public void setNombreMostrar(String nombreMostrar) {
        this.nombreMostrar = nombreMostrar;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public int getSizeColumna() {
        return sizeColumna;
    }

    public void setSizeColumna(int sizeColumna) {
        this.sizeColumna = sizeColumna;
    }

    public CamposNodoConsulta getNodoPadre() {
        return nodoPadre;
    }

    public void setNodoPadre(CamposNodoConsulta nodoPadre) {
        this.nodoPadre = nodoPadre;
    }

    public String getNombreCampoBD() {
        return nombreCampoBD;
    }

    public void setNombreCampoBD(String nombreCampoBD) {
        this.nombreCampoBD = nombreCampoBD;
    }

    public String getNombreRecurso() {
        return nombreRecurso;
    }

    public void setNombreRecurso(String nombreRecurso) {
        this.nombreRecurso = nombreRecurso;
    }

    public String getPathCamposRelacionados() {
        return pathCamposRelacionados;
    }

    public void setPathCamposRelacionados(String pathCamposRelacionados) {
        this.pathCamposRelacionados = pathCamposRelacionados;
    }

    public String getDatoConsulta() {
        return datoConsulta;
    }

    public void setDatoConsulta(String datoConsulta) {
        this.datoConsulta = datoConsulta;
    }
    
}
