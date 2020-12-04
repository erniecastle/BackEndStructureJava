/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfigConceptosSatDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.ConfigConceptosSat;
import java.util.List;

public class ServicioConfigConceptosSat implements ServicioConfigConceptosSatIF {

    private ConfigConceptosSatDAO configConceptosSatDAO;

    public Mensaje agregar(ConfigConceptosSat entity, String uuidCxn) {
        return configConceptosSatDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ConfigConceptosSat entity, String uuidCxn) {
        return configConceptosSatDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ConfigConceptosSat entity, String uuidCxn) {
        return configConceptosSatDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getConfigConceptosSatAll(String uuidCxn) {
        return configConceptosSatDAO.getConfigConceptosSatAll(uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return configConceptosSatDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return configConceptosSatDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteConfigConceptosSat(List<ConfigConceptosSat> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return configConceptosSatDAO.saveDeleteConfigConceptosSat(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public Mensaje eliminaPorClaveConceptoNomina(String claveConcepto, String uuidCxn) {
        return configConceptosSatDAO.eliminaPorClaveConceptoNomina(claveConcepto, uuidCxn);
    }

    public ConfigConceptosSatDAO getConfigConceptosSatDAO() {
        return configConceptosSatDAO;
    }

    public void setConfigConceptosSatDAO(ConfigConceptosSatDAO configConceptosSatDAO) {
        this.configConceptosSatDAO = configConceptosSatDAO;
    }

}
