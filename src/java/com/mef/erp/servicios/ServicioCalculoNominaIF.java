/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: interface servicio tabla base nomina
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParametrosExtra;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import java.util.Date;

public interface ServicioCalculoNominaIF {

    /*Object*/
    Mensaje calculaNomina(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina,
            String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago,
            String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra, int ejercicioActivo, String uuidCxn, String uuidCxnMaestra);

    /*SalariosIntegrados*/
    Mensaje calculaSalarioDiarioIntegerado(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina,
            String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago,
            String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra, boolean soloCalculo, boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra);

    /*SalariosIntegrados*/
    Mensaje calculaSDIPorEmpleado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, String controlador, ParametrosExtra parametrosExtra, boolean soloCalculo, boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra);

    /* List<?>*/
    Mensaje busquedaQueryConsultaEmpleados(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String[] valoresDatosEspeciales, String[] camposWhereExtras, String nombreFuenteDatos, Date[] rangoFechas, String ordenado, String claveRazonSocial, String controladores, String uuidCxn, String uuidCxnMaestra);



}
