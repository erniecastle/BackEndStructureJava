/**
 * @author: Victor Lopez Fecha de Creación: 10/03/2012 Compañía: Macropro
 * Descripción del programa: servicio MovNominaBaseAfecta para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.MovNominaBaseAfectaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomBaseAfecta;
import java.util.List;

public class ServicioMovNominaBaseAfecta implements ServicioMovNominaBaseAfectaIF {

    private MovNominaBaseAfectaDAO movNominaBaseAfectaDAO;

    public Mensaje getMovNominaBaseAfectaAll(String uuidCxn) {

        return getMovNominaBaseAfectaDAO().getMovNominaBaseAfectaAll(uuidCxn);
    }

    public Mensaje getMovNominaBaseAfectaAsc(String uuidCxn) {

        return getMovNominaBaseAfectaDAO().getMovNominaBaseAfectaAsc(uuidCxn);
    }

    public Mensaje saveDeleteMovNominaBaseAfecta(List<MovNomBaseAfecta> AgreModif, List<MovNomBaseAfecta> Ordenados, Object[] clavesDelete,String uuidCxn) {

        return getMovNominaBaseAfectaDAO().saveDeleteMovNominaBaseAfecta(AgreModif, Ordenados, clavesDelete,uuidCxn);
    }

    public MovNominaBaseAfectaDAO getMovNominaBaseAfectaDAO() {

        return movNominaBaseAfectaDAO;
    }

    public void setMovNominaBaseAfectaDAO(MovNominaBaseAfectaDAO movNominaBaseAfectaDAO) {

        this.movNominaBaseAfectaDAO = movNominaBaseAfectaDAO;
    }
}
