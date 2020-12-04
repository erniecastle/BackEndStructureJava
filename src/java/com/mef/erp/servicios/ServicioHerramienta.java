/**
 * @author: Victor Fecha de Creación: 06/06/2011 Compañía: Macropro Descripción
 * del programa: clase TablaBase para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.HerramientaDAO;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Ernesto Castillo
 */
public class ServicioHerramienta implements ServicioHerramientaIF {

    private HerramientaDAO herramientaDAO;
  

    public Mensaje getHerramientaAll(String uuidCxn) {//JSA01
        return herramientaDAO.getHerramientaAll(uuidCxn);
    }

    public Mensaje getHerramientaCompartidas(String uuidCxn) {//JSA01
        return herramientaDAO.getHerramientaCompartidas(uuidCxn);
    }

    public Mensaje getHerramientasPrincipales(Usuario usuario, String uuidCxn) {//JSA01
        return herramientaDAO.getHerramientasPrincipales(usuario, uuidCxn);
    }

    public Mensaje getHerramientasPrincipalesCompartidas(Usuario usuario, String uuidCxn) {//JSA01
        return herramientaDAO.getHerramientasPrincipalesCompartidas(usuario, uuidCxn);
    }

    public Mensaje agregar(Herramienta entity, String uuidCxn) {//JSA01
        return herramientaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje eliminar(Herramienta entity, String uuidCxn) {//JSA01
        return herramientaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje actualizar(Herramienta entity, String uuidCxn) {//JSA01
        return herramientaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje SaveHerramientas(List<Herramienta> h, String uuidCxn) {//JSA01
        return herramientaDAO.SaveHerramientas(h, uuidCxn);
    }

    public Mensaje DeleteHerramientas(List<Herramienta> h, String uuidCxn) {//JSA01
        return herramientaDAO.DeleteHerramientas(h, uuidCxn);
    }

    public HerramientaDAO getHerramientaDAO() {
        return herramientaDAO;
    }

    public void setHerramientaDAO(HerramientaDAO herramientaDAO) {
        this.herramientaDAO = herramientaDAO;
    }
}
