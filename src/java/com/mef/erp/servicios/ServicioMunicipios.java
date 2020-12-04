/**
 * @author: Ernesto Valenzuela Fecha de Creación: 18/03/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO Municipio, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.MunicipiosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Municipios;
import java.util.List;

public class ServicioMunicipios implements ServicioMunicipiosIF {

    private MunicipiosDAO municipiosDAO;

    public Mensaje getMunicipiosAll(String uuidCxn) {

        return municipiosDAO.getMunicipiosAll(uuidCxn);
    }

    public Mensaje agregar(Municipios entity, String uuidCxn) {

        return municipiosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje eliminar(Municipios entity, String uuidCxn) {

        return municipiosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje actualizar(Municipios entity, String uuidCxn) {

        return municipiosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje getMunicipiosPorClave(String clave, String uuidCxn) {//AAP01

        return municipiosDAO.getMunicipiosPorClave(clave, uuidCxn);
    }

    public Mensaje getMunicipiosPorEstado(String claveEstado, String uuidCxn) {

        return municipiosDAO.getMunicipiosPorEstado(claveEstado, uuidCxn);
    }

    public Mensaje getMunicipiosPorPais(String clavePais, String uuidCxn) {

        return municipiosDAO.getMunicipiosPorPais(clavePais, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {

        return municipiosDAO.consultaPorFiltrosMunicipio(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {

        return municipiosDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return municipiosDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteMunicipios(List<Municipios> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {

        return municipiosDAO.saveDeleteMunicipios(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public MunicipiosDAO getMunicipiosDAO() {

        return municipiosDAO;
    }

    public void setMunicipiosDAO(MunicipiosDAO municipiosDAO) {
        this.municipiosDAO = municipiosDAO;
    }
}
