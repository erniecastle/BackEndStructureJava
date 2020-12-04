/**
 * @author: Ernesto Valenzuela Fecha de Creación: 27/05/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO cruce, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CruceDAO;
import com.mef.erp.modelo.entidad.Cruce;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioCruce implements ServicioCruceIF {

    private CruceDAO cruceDAO;
    

    public Mensaje agregar(String uuidCxn, Cruce entity) {//JSA01
        return cruceDAO.agregar(uuidCxn, entity);
    }

    public Mensaje actualizar(String uuidCxn, Cruce entity) {//JSA01
        return cruceDAO.actualizar(uuidCxn, entity);
    }

    public Mensaje eliminar(String uuidCxn, Cruce entity) {//JSA01
        return cruceDAO.eliminar(uuidCxn, entity);
    }

    public Mensaje getCruceAll(String uuidCxn) {//JSA01
        return cruceDAO.getCruceAll(uuidCxn);
    }

    public Mensaje existeClaveElemento(String uuidCxn, String claveelemento, String elemento, Long parametro) {//JSA01
        return cruceDAO.existeClaveElemento(uuidCxn, claveelemento, elemento, parametro);
    }

    public Mensaje getCrucePorParametros(String uuidCxn, Long claveParametro) {//JSA01
        return cruceDAO.getCrucePorParametros(uuidCxn, claveParametro);
    }

    public Mensaje getCrucePorElemento(String uuidCxn, String elemento) {//JSA01
        return cruceDAO.getCrucePorElemento(uuidCxn, elemento);
    }

    public Mensaje getCrucePorParamElemento(String uuidCxn, Long claveParametro, String elemento) {//JSA01
        return cruceDAO.getCrucePorParamElemento(uuidCxn, claveParametro, elemento);
    }

    public Mensaje getCrucePorElemeYClave(String uuidCxn, String elemento, String claveelemento) {//JSA01
        return cruceDAO.getCrucePorElemeYClave(uuidCxn, elemento, claveelemento);
    }

    public Mensaje getCrucePorParaElemeYClave(String uuidCxn, Long claveParametro, String elemento, String claveelemento) {//JSA01
        return cruceDAO.getCrucePorParaElemeYClave(uuidCxn, claveParametro, elemento, claveelemento);
    }

    public Mensaje SaveDeleteCruces(String uuidCxn, List<Cruce> AgreModif, List<Cruce> Ordenados, Object[] clavesDelete) {
       
        return cruceDAO.SaveDeleteCruces(uuidCxn, AgreModif, Ordenados, clavesDelete);
    }

    public Mensaje SaveCruces(String uuidCxn, List<Cruce> c) {//JSA01
       
        return cruceDAO.SaveCruces(uuidCxn, c);
    }

    public Mensaje DeleteCruces(String uuidCxn, List<Cruce> c) {//JSA01
        
        return cruceDAO.DeleteCruces(uuidCxn, c);
    }

    public CruceDAO getCruceDAO() {
        return cruceDAO;
    }

    public void setCruceDAO(CruceDAO cruceDAO) {
        this.cruceDAO = cruceDAO;
    }
}
