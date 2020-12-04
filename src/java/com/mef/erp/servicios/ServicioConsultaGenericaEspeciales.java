/**
 * @author: Jose Armando Compa��a: Macropro. Descripci�n del programa: Esta
 * clase es para las consultas especiales.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripci�n:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConsultaGenericaEspecialesDAO;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author xp
 */
public class ServicioConsultaGenericaEspeciales implements ServicioConsultaGenericaEspecialesIF {

    private ConsultaGenericaEspecialesDAO consultaGenericaEspecialesDAO;

    public Mensaje existeClave(String identificador, String[] campowhere, Object[] valoreswhere, String uuidCxn, String uuidCxnMaestra) {
        return consultaGenericaEspecialesDAO.existeClave(identificador, campowhere, valoreswhere, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangosFiltro(String identificador, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra) {
        return consultaGenericaEspecialesDAO.consultaPorRangosFiltro(identificador, inicio, rango, camposWhere, valoresWhere, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje obtenerDatosCriterioConsulta(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String uuidCxn) {
        return consultaGenericaEspecialesDAO.obtenerDatosCriterioConsulta(tablas, camposMostrar, camposWhere, valoresWhere, camposOrden, uuidCxn);
    }

    public ConsultaGenericaEspecialesDAO getConsultaGenericaEspecialesDAO() {
        return consultaGenericaEspecialesDAO;
    }

    public void setConsultaGenericaEspecialesDAO(ConsultaGenericaEspecialesDAO consultaGenericaEspecialesDAO) {
        this.consultaGenericaEspecialesDAO = consultaGenericaEspecialesDAO;
    }
}
