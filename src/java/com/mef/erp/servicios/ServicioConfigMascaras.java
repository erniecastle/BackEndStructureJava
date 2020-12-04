/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 04/08/2011 Compañía:
 * Exito Software. Descripción del programa: clase SERVICIO configuracion de
 * mascaras, para llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:18/02/2015 Descripción:
 * Se le agrego al metodo getSaveMascarasAfectaClaves el generar el archivo de
 * properties de las mascaras. Se agrego el metodo getFilePropertiesMascara para
 * obtener el nombre del properties de las mascara por base de datos ya que se
 * puede tener varias bds en el mismo servidor.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConfigMascarasDAO;
import com.mef.erp.modelo.entidad.Mascaras;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioConfigMascaras implements ServicioConfigMascarasIF {

    private ConfigMascarasDAO configMascarasDAO;


    public Mensaje agregar(Mascaras entity, String uuidCxn) {//JSA01
        return configMascarasDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Mascaras entity, String uuidCxn) {//JSA01
        return configMascarasDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Mascaras entity, String uuidCxn) {//JSA01
        return configMascarasDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getConfigMascarasAll(String uuidCxn) {//JSA01
        return configMascarasDAO.getConfigMascarasAll(uuidCxn);
    }

    public Mensaje getConfigMascarasPorClave(String clave, String uuidCxn) {//JSA01
        return configMascarasDAO.getConfigMascarasPorClave(clave, uuidCxn);
    }

    public Mensaje getSaveMascarasAfectaClaves(List<Mascaras> listMascaras, boolean soloAplicarReEstructuracion, byte[] propertiesMascara, String ubicacion, String uuidCxn, String uuidCxnMaestra) {//JSA03
        return configMascarasDAO.getSaveMascarasAfectaClaves(listMascaras, soloAplicarReEstructuracion, propertiesMascara, ubicacion, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return configMascarasDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return configMascarasDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje getFilePropertiesMascara(String directorioReportesDelSistema, String uuidCxn) {//JSA03
        return getConfigMascarasDAO().getFilePropertiesMascara(directorioReportesDelSistema, uuidCxn);
    }

    public ConfigMascarasDAO getConfigMascarasDAO() {
        return configMascarasDAO;
    }

    public void setConfigMascarasDAO(ConfigMascarasDAO periodicidadDAO) {
        this.configMascarasDAO = periodicidadDAO;
    }
}
