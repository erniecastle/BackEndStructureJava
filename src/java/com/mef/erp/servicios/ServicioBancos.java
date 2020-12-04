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

import com.mef.erp.modelo.BancosDAO;
import com.mef.erp.modelo.entidad.Bancos;
import com.mef.erp.modelo.entidad.Contactos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioBancos implements ServicioBancosIF {

    private BancosDAO bancosDAO;

    public Mensaje agregar(Bancos entity, String uuidCxn) {//JSA01
        return bancosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Bancos entity, String uuidCxn) {//JSA01
        return bancosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Bancos entity, String uuidCxn) {//JSA01
        return bancosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getBancosAll(String uuidCxn) {//JSA01
        return bancosDAO.getBancosAll(uuidCxn);
    }

    public Mensaje getBancosPorClave(String clave, String uuidCxn) {//JSA01
        return bancosDAO.getBancosPorClave(clave, uuidCxn);
    }

    public Mensaje SaveBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn) {//JSA01
        return bancosDAO.SaveBanco(agrega, eliminados, entity, uuidCxn);
    }

    public Mensaje UpdateBanco(List<Contactos> agrega, Object[] eliminados, Bancos entity, String uuidCxn) {//JSA01
        return bancosDAO.UpdateBanco(agrega, eliminados, entity, uuidCxn);
    }

    public Mensaje DeleteBanco(Bancos entity, String uuidCxn) {//JSA01
        return bancosDAO.DeleteBanco(entity, uuidCxn);
    }

    public Mensaje consultaPorFiltrosBancos(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01
        return bancosDAO.consultaPorFiltrosBancos(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return bancosDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return bancosDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje agregarListaBancos(List<Bancos> cambios, List<Bancos> temporales, List<Contactos> cambioContactos, Object[] clavesContactosDelete, int rango, String uuidCxn) {//JSA01
        return bancosDAO.agregarListaBancos(cambios, temporales, cambioContactos, clavesContactosDelete, rango, uuidCxn);
    }

    public Mensaje existeRFC(String rfc, String claveBancos, String uuidCxn) {
        return bancosDAO.existeRFC(rfc, claveBancos, uuidCxn);
    }

    public BancosDAO getBancosDAO() {
        return bancosDAO;
    }

    public void setBancosDAO(BancosDAO bancosDAO) {
        this.bancosDAO = bancosDAO;
    }

}
