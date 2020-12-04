/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 02/05/2015 Descripción: se agrego el
 * metodo getCFDIEmpleadoStatusPorFiltroPorRangoPeriodosNomina para obtener los
 * timbre del empleado por un rango de fechas.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CFDIEmpleadoDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.modelo.entidad.cfdi.StatusTimbrado;
import java.util.Date;
import java.util.List;

public class ServicioCFDIEmpleado implements ServicioCFDIEmpleadoIF {

    private CFDIEmpleadoDAO cfdiEmpleadoDAO;

    public Mensaje agregar(CFDIEmpleado entity, String uuidCxn) {
        return getCfdiEmpleadoDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(CFDIEmpleado entity, String uuidCxn) {
        return getCfdiEmpleadoDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(CFDIEmpleado entity, String uuidCxn) {
        return getCfdiEmpleadoDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getCFDIEmpleadoAll(String uuidCxn) {
        return getCfdiEmpleadoDAO().getCFDIEmpleadoAll(uuidCxn);
    }

    public Mensaje getCFDIEmpleadoPorFiltro(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, StatusTimbrado statusTimbre, Object[] rangoEmpleados, String uuidCxn) {
        return getCfdiEmpleadoDAO().getCFDIEmpleadoPorFiltro(claveRazonSocial, claveTipoNomina, claveTipoCorrida, idPeriodoNomina, statusTimbre, rangoEmpleados, uuidCxn);
    }

    public Mensaje getCFDIEmpleadoStatusPorFiltro(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, List<StatusTimbrado> tiposTimbre, Object[] rangoEmpleados, String uuidCxn) {
        return getCfdiEmpleadoDAO().getCFDIEmpleadoStatusPorFiltro(claveRazonSocial, claveTipoNomina, claveTipoCorrida, idPeriodoNomina, tiposTimbre, rangoEmpleados, uuidCxn);
    }

    public Mensaje getCFDIEmpleadoStatusPorFiltroPorRangoPeriodosNomina(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Date fechaInicial, Date fechaFinal, List<StatusTimbrado> tiposTimbre, Object[] rangoEmpleados, String uuidCxn) {//JSA01
        return getCfdiEmpleadoDAO().getCFDIEmpleadoStatusPorFiltroPorRangoPeriodosNomina(claveRazonSocial, claveTipoNomina, claveTipoCorrida, fechaInicial, fechaFinal, tiposTimbre, rangoEmpleados, uuidCxn);
    }

    public Mensaje getCFDIEmpleadoStatusErrorOEnProceso(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, Object[] rangoEmpleados, String uuidCxn) {
        return getCfdiEmpleadoDAO().getCFDIEmpleadoStatusErrorOEnProceso(claveRazonSocial, claveTipoNomina, claveTipoCorrida, idPeriodoNomina, rangoEmpleados, uuidCxn);
    }
    
    
    @Override
    public Mensaje getLimpiaConStatusErrorOEnProceso(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, Object[] rangoEmpleados, String uuidCxn) {
        return getCfdiEmpleadoDAO().getLimpiaConStatusErrorOEnProceso(claveRazonSocial, claveTipoNomina, claveTipoCorrida, idPeriodoNomina, rangoEmpleados, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return getCfdiEmpleadoDAO().consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje getCFDIEmpleadoTimbrados(Long[] idsCFDIEmpleado, String uuidCxn) {
        return getCfdiEmpleadoDAO().getCFDIEmpleadoTimbrados(idsCFDIEmpleado, uuidCxn);
    }
    
    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return getCfdiEmpleadoDAO().existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteCFDIEmpleado(List<CFDIEmpleado> entitysCambios, Object[] idEliminar, int rango, String uuidCxn) {
        return getCfdiEmpleadoDAO().saveDeleteCFDIEmpleado(entitysCambios, idEliminar, rango, uuidCxn);
    }

    public Mensaje generaDatosParaTimbrado(List<Object> valoresDeFiltrado, String claveRazonSocial, String uuidCxn, String uuidCxnMaestra) {
        return getCfdiEmpleadoDAO().generaDatosParaTimbrado(valoresDeFiltrado, claveRazonSocial, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje buscaCFDIEmpleadosFiltrado(List<Object> valoresDeFiltrado, String uuidCxn) {
        return getCfdiEmpleadoDAO().buscaCFDIEmpleadosFiltrado(valoresDeFiltrado, uuidCxn);
    }

    public CFDIEmpleadoDAO getCfdiEmpleadoDAO() {
        return cfdiEmpleadoDAO;
    }

    public void setCfdiEmpleadoDAO(CFDIEmpleadoDAO cfdiEmpleadoDAO) {
        this.cfdiEmpleadoDAO = cfdiEmpleadoDAO;
    }

}
