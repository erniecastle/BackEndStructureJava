/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * CalculaNominaDAOIF para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 21/01/2013 Descripción:Se cambio
 * agrego el paramtro para solo calcular el SDI sin guardar. Tambien de cambio
 * el tipo de dato del tipo contrato.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.CalculoISR;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParametrosExtra;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import java.util.Date;

public interface CalculaNominaDAOIF extends GenericDAO<CalculoISR, Long> {

    Mensaje calculaNomina(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina,
            String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago,
            String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra, int ejercicioActivo, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje calculaSalarioDiarioIntegerado(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina,
            String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago,
            String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra, boolean soloCalculo, boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje calculaSDIPorEmpleado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, String controlador, ParametrosExtra parametrosExtra, boolean soloCalculo, boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra);//JSA01

    Mensaje busquedaQueryConsultaEmpleados(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String[] valoresDatosEspeciales, String[] camposWhereExtras, String nombreFuenteDatos, Date[] rangoFechas, String ordenado, String claveRazonSocial, String controladores, String uuidCxn, String uuidCxnMaestra);

}
