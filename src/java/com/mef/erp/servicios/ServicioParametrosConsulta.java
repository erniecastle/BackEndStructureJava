/**
 * @author: Victor Lopez Fecha de Creación: 28/09/2012 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO ServicioParametrosConsulta, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ParametrosConsultaDAO;
import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParametrosConsulta;

public class ServicioParametrosConsulta implements ServicioParametrosConsultaIF {

    private ParametrosConsultaDAO parametrosConsultaDAO;

    public Mensaje agregar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn) {

        return parametrosConsultaDAO.agregar(entity, contenedorGrupoMenu, uuidCxn);
    }

    public Mensaje actualizar(ParametrosConsulta entity, Contenedor contenedorGrupoMenu, String uuidCxn) {

        return parametrosConsultaDAO.actualizar(entity, contenedorGrupoMenu, uuidCxn);
    }

    public Mensaje eliminar(ParametrosConsulta entity, String uuidCxn) {

        return parametrosConsultaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getParametrosConsultaAll(String uuidCxn) {

        return parametrosConsultaDAO.getParametrosConsultaAll(uuidCxn);
    }

    public Mensaje getParametrosConsultaAllEspecifico(String uuidCxn) {

        return getParametrosConsultaDAO().getParametrosConsultaAllEspecifico(uuidCxn);
    }

    public Mensaje getParametrosConsultaPorID(Long idParametrosConsulta, String uuidCxn) {

        return getParametrosConsultaDAO().getParametrosConsultaPorID(idParametrosConsulta, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {

        return parametrosConsultaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {

        return parametrosConsultaDAO.consultaPorFiltrosParametros(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {

        return parametrosConsultaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje PorGrupoMenuFuenteDatos(String fuenteDatos, Integer idContenedor, String uuidCxn) {

        return parametrosConsultaDAO.PorGrupoMenuFuenteDatos(fuenteDatos, idContenedor, uuidCxn);
    }

    public Mensaje eliminarEspecifico(Long idReporte, String uuidCxn) {

        return getParametrosConsultaDAO().eliminarEspecifico(idReporte, uuidCxn);
    }

    public ParametrosConsultaDAO getParametrosConsultaDAO() {

        return parametrosConsultaDAO;
    }

    public void setParametrosConsultaDAO(ParametrosConsultaDAO parametrosConsultaDAO) {
        this.parametrosConsultaDAO = parametrosConsultaDAO;
    }

   

}
