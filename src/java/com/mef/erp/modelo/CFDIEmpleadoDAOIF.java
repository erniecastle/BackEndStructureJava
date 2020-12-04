/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 02/05/2015 Descripción: se agrego el metodo 
 * getCFDIEmpleadoStatusPorFiltroPorRangoPeriodosNomina para obtener los timbre del empleado
 * por un rango de fechas.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.modelo.entidad.cfdi.StatusTimbrado;
import java.util.Date;
import java.util.List;

public interface CFDIEmpleadoDAOIF {

    Mensaje agregar(CFDIEmpleado entity, String uuidCxn);

    Mensaje actualizar(CFDIEmpleado entity, String uuidCxn);

    Mensaje eliminar(CFDIEmpleado entity, String uuidCxn);

    Mensaje getCFDIEmpleadoAll(String uuidCxn);

    Mensaje getCFDIEmpleadoPorFiltro(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, StatusTimbrado statusTimbre, Object[] rangoEmpleados, String uuidCxn);

    Mensaje getCFDIEmpleadoStatusPorFiltro(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, List<StatusTimbrado> tiposTimbre, Object[] rangoEmpleados, String uuidCxn);

    Mensaje getCFDIEmpleadoStatusPorFiltroPorRangoPeriodosNomina(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Date fechaInicial, Date fechaFinal, List<StatusTimbrado> tiposTimbre, Object[] rangoEmpleados, String uuidCxn);//JSA01

    Mensaje getCFDIEmpleadoStatusErrorOEnProceso(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, Object[] rangoEmpleados, String uuidCxn);
    
    Mensaje getLimpiaConStatusErrorOEnProceso(String claveRazonSocial, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, Object[] rangoEmpleados, String uuidCxn);

    Mensaje saveDeleteCFDIEmpleado(List<CFDIEmpleado> entitysCambios, Object[] idEliminar, int rango, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);
    
    Mensaje getCFDIEmpleadoTimbrados(Long[] idsCFDIEmpleado, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje generaDatosParaTimbrado(List<Object> valoresDeFiltrado, String claveRazonSocial, String uuidCxn, String uuidCxnMaestra);

    Mensaje buscaCFDIEmpleadosFiltrado(List<Object> valoresDeFiltrado, String uuidCxn);
}
