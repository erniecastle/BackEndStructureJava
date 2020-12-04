/**
 * @author: Jose Armando Fecha de Creacion: 17/02/2014 Compañia: Macropro
 * Descripcion del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.JornadaDAO;
import com.mef.erp.modelo.entidad.Mensaje;

public class ServicioJornada implements ServicioJornadaIF {

    private JornadaDAO jornadaDAO;

    public Mensaje getJornadaAll(String uuidCxn) {

        return jornadaDAO.getJornadaAll(uuidCxn);
    }

    public JornadaDAO getJornadaDAO() {

        return jornadaDAO;
    }

    public void setJornadaDAO(JornadaDAO jornadaDAO) {

        this.jornadaDAO = jornadaDAO;
    }

}
