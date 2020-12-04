/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.GeneroDAO;
import com.mef.erp.modelo.entidad.Genero;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public class ServicioGenero implements ServicioGeneroIF {

    private GeneroDAO generoDAO;

    @Override
    public Mensaje agregar(Genero entity, String uuidCxn) {
        return getGeneroDAO().agregar(entity, uuidCxn);
    }

    @Override
    public Mensaje actualizar(Genero entity, String uuidCxn) {
        return getGeneroDAO().actualizar(entity, uuidCxn);
    }

    @Override
    public Mensaje eliminar(Genero entity, String uuidCxn) {
        return getGeneroDAO().eliminar(entity, uuidCxn);
    }

    @Override
    public Mensaje getGeneroAll(String uuidCxn) {
        return getGeneroDAO().getGeneroAll(uuidCxn);
    }

    @Override
    public Mensaje getGeneroPorClave(String clave, String uuidCxn) {

        return getGeneroDAO().getGeneroPorClave(clave, uuidCxn);
    }

    @Override
    public Mensaje consultaPorFiltrosGenero(String query, Object[] campos, Object[] valores, String uuidCxn) {

        return getGeneroDAO().consultaPorFiltrosGenero(query, campos, valores, uuidCxn);
    }

    @Override
    public Mensaje consultaPorRangosGenero(Integer inicio, Integer rango, String uuidCxn) {

        return getGeneroDAO().consultaPorRangosGenero(inicio, rango, uuidCxn);
    }

    @Override
    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return getGeneroDAO().existeDato(campo, valor, uuidCxn);
    }

    @Override
    public Mensaje saveDeleteGenero(List<Genero> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {

        return getGeneroDAO().saveDeleteGenero(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public GeneroDAO getGeneroDAO() {
        return generoDAO;
    }

    public void setGeneroDAO(GeneroDAO generoDAO) {
        this.generoDAO = generoDAO;
    }

   

}
