/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase Bancos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ContactosDAO;
import com.mef.erp.modelo.entidad.Bancos;
import com.mef.erp.modelo.entidad.Contactos;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public class ServicioContactos implements ServicioContactosIF {

    private ContactosDAO contactosDAO;

    public Mensaje agregar(Contactos entity, String uuidCxn) {//JSA01
        return contactosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Contactos entity, String uuidCxn) {//JSA01
        return contactosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Contactos entity, String uuidCxn) {//JSA01
        return contactosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getContactosAll(String uuidCxn) {//JSA01
        return contactosDAO.getContactosAll(uuidCxn);
    }

    public Mensaje getContactosPorId(Long id, String uuidCxn) {//JSA01
        return contactosDAO.getContactosPorId(id, uuidCxn);
    }

    public Mensaje getContactosPorBancos(Bancos b, String uuidCxn) {//JSA01
        return contactosDAO.getContactosPorBancos(b, uuidCxn);
    }

    public Mensaje getContactosPorIDBanco(Long clave, String uuidCxn) {//JSA01
        return contactosDAO.getContactosPorIDBanco(clave, uuidCxn);
    }

    public Mensaje EliminaContactosPorBanco(Bancos banco, String uuidCxn) {//JSA01
        return contactosDAO.eliminaContactosPorBanco(banco, uuidCxn);
    }

    public ContactosDAO getContactosDAO() {
        return contactosDAO;
    }

    public void setContactosDAO(ContactosDAO contactosDAO) {
        this.contactosDAO = contactosDAO;
    }
}
