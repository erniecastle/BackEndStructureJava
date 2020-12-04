/**
 * @author: Daniel Fecha de Creacion: --/--/-- Compañia: FineSoft Descripcion
 * del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:23/07/2012 Descripcion:Se
 * agrego la programacion del guardado y se quedo pendiente lo del llamado del
 * Calculo del SDI.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:30/07/2012 Descripción:Se
 * agrego el metodo existeRFC para verificar que el RFC no exista aun.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:24/06/2013 Descripción:Se
 * agrego el codigo para guardar o modificar el creditoInfonavit.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Capacitaciones;
import com.mef.erp.modelo.entidad.Documentacion;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.ExperienciaLaboralExterna;
import com.mef.erp.modelo.entidad.ExperienciaLaboralInterna;
import com.mef.erp.modelo.entidad.Familiares;
import com.mef.erp.modelo.entidad.FormacionEconomica;
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import com.mef.erp.modelo.entidad.VacacionesAplicacion;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface EmpleadosDAOIF {

    Mensaje agregar(Empleados entity, String uuidCxn);

    Mensaje actualizar(Empleados entity, String uuidCxn);

    Mensaje eliminar(Empleados entity, String uuidCxn);

    Mensaje getEmpleadosAll(String claveRazonesSocial, String uuidCxn);

    Mensaje getEmpleadosPorClave(String clave, String uuidCxn);

    Mensaje getEmpleadoPorClaveRazonTipNomina(String clave, String claveRazon, String claveTipNom, Date fechaInicial, Date fechaFinal, String uuidCxn);

    Mensaje SaveEmpleado(List<Familiares> agrega, Object[] eliminados,
            List<FormacionEconomica> agregaFE, Object[] eliminadosFE,
            List<Capacitaciones> agregaCap, Object[] eliminadosCap,
            List<ExperienciaLaboralExterna> agregaELE, Object[] eliminadosELE,
            List<ExperienciaLaboralInterna> agregaELI, Object[] eliminadosELI,
            List<Documentacion> agregaDoc, Object[] eliminadosDoc, List<VacacionesAplicacion> agregaVac,
            Empleados empleados, List<PlazasPorEmpleado> listPlazasPorEmpleados, List<PlazasPorEmpleadosMov> listPlazasPorEmpleadoMov, IngresosReingresosBajas ingresosReingresosBajas, SalariosIntegrados salariosIntegrados, String uuidCxn);//JSA01

    Mensaje DeleteEmpleado(Empleados entity, String uuidCxn);

    Mensaje UpdateEmpleado(List<Familiares> agrega, Object[] eliminados,
            List<FormacionEconomica> agregaFE, Object[] eliminadosFE,
            List<Capacitaciones> agregaCap, Object[] eliminadosCap,
            List<ExperienciaLaboralExterna> agregaELE, Object[] eliminadosELE,
            List<ExperienciaLaboralInterna> agregaELI, Object[] eliminadosELI,
            List<Documentacion> agregaDoc, Object[] eliminadosDoc, List<VacacionesAplicacion> agregaVac, Empleados empleados,
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadoMov, IngresosReingresosBajas ingresosReingresosBajas, boolean calcularSDI, SalariosIntegrados salariosIntegrados, String uuidCxn);//JSA01

    public Mensaje existeRFC(String rfc, String claveEmpleado, String uuidCxn);//02JSA
}
