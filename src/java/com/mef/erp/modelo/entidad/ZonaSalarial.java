/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo.entidad;

/**
 *
 * @author Admin
 */
public class ZonaSalarial {
    private char zona;
    private Double salario;
    
   
    public ZonaSalarial(Object[] valorZona) {
        zona = Character.valueOf(valorZona[0].toString().charAt(0));
        salario = Double.valueOf(valorZona[1].toString());
    }

    public Double getSalario() {
        return salario;
    }

    public void setSalario(Double salario) {
        this.salario = salario;
    }

    public char getZona() {
        return zona;
    }

    public void setZona(char zona) {
        this.zona = zona;
    } 
}
