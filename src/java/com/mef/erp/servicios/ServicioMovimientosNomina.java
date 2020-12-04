/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.MovimientosNominaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public class ServicioMovimientosNomina implements ServicioMovimientosNominaIF {

    private MovimientosNominaDAO movimientosNominaDAO;

    public Mensaje getMovimientosNominaAll(String uuidCxn) {

        return getMovimientosNominaDAO().getMovimientosNominaAll(uuidCxn);
    }

    public Mensaje getMovimientosNominaAsc(String uuidCxn) {

        return getMovimientosNominaDAO().getMovimientosNominaAsc(uuidCxn);
    }

    public Mensaje getMaxNumeroMovimientoPorTipoNominaYPeriodo(String claveTipoNomina, Long idPeriodo,String uuidCxn) {

        return getMovimientosNominaDAO().getMaxNumeroMovimientoPorTipoNominaYPeriodo(claveTipoNomina, idPeriodo,uuidCxn);
    }

    public Mensaje saveDeleteMovimientosNomina(List<MovNomConcep> AgreModif, Object[] clavesDelete, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100,String uuidCxn) {

        return getMovimientosNominaDAO().saveDeleteMovimientosNomina(AgreModif, clavesDelete, valoresReestablecer, incluirEliminadoDiferenteTipoPantalla100,uuidCxn);
    }

    public Mensaje getMovimientosPorPlazaEmpleado(Object[] clavesPlazasPorEmpleado, String claveTipoCorrida, String claveRazonSocial,String uuidCxn) {

        return getMovimientosNominaDAO().getMovimientosPorPlazaEmpleado(clavesPlazasPorEmpleado, claveTipoCorrida, claveRazonSocial,uuidCxn);
    }

    public Mensaje getMovimientosPorFiltro(String[] camposWhere, Object[] valoresWhere,String uuidCxn) {

        return getMovimientosNominaDAO().getMovimientosPorFiltro(camposWhere, valoresWhere,uuidCxn);
    }

    public Mensaje eliminaListaMovimientos(String campo, Object[] valores, List<CFDIEmpleado> valoresCFDI, Object[] valoresCalculoUnidades, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100,String uuidCxn) {

        return getMovimientosNominaDAO().eliminaListaMovimientos(campo, valores, valoresCFDI, valoresCalculoUnidades, valoresReestablecer, incluirEliminadoDiferenteTipoPantalla100,uuidCxn);
    }

    public Mensaje getMovimientosPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere,String uuidCxn) {

        return getMovimientosNominaDAO().getMovimientosPorFiltroEspecifico(camposWhere, valoresWhere,uuidCxn);
    }

    public Mensaje getCalculosUnidadesPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere, List<CFDIEmpleado> listCFDIEmpleado,String uuidCxn) {

        return getMovimientosNominaDAO().getCalculosUnidadesPorFiltroEspecifico(camposWhere, valoresWhere, listCFDIEmpleado,uuidCxn);
    }

    public Mensaje buscaMovimientosNominaFiltrado(List<Object> valoresDeFiltrado, String uuidCxn) {
        return movimientosNominaDAO.buscaMovimientosNominaFiltrado(valoresDeFiltrado, uuidCxn);
    }
    
    

    public MovimientosNominaDAO getMovimientosNominaDAO() {

        return movimientosNominaDAO;
    }

    public void setMovimientosNominaDAO(MovimientosNominaDAO movimientosNominaDAO) {

        this.movimientosNominaDAO = movimientosNominaDAO;
    }
}
