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
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:30/07/2012 Descripcion:Se
 * agrego el metodo existeRFC para verificar que el RFC no exista aun.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:24/06/2013 Descripción:Se
 * agrego el codigo para guardar o modificar el creditoInfonavit.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.EmpleadosDAO;
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
public class ServicioEmpleados implements ServicioEmpleadosIF {

    private EmpleadosDAO empleadosDAO;
   

    public Mensaje agregar(Empleados entity, String uuidCxn) {
        return empleadosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Empleados entity, String uuidCxn) {
        return empleadosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Empleados entity, String uuidCxn) {
        return empleadosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getEmpleadosAll(String claveRazonesSocial, String uuidCxn) {
        return empleadosDAO.getEmpleadosAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getEmpleadosPorClave(String clave, String uuidCxn) {
        return empleadosDAO.getEmpleadosPorClave(clave, uuidCxn);
    }

    public Mensaje SaveEmpleado(List<Familiares> agrega, Object[] eliminados,
            List<FormacionEconomica> agregaFE, Object[] eliminadosFE,
            List<Capacitaciones> agregaCap, Object[] eliminadosCap,
            List<ExperienciaLaboralExterna> agregaELE, Object[] eliminadosELE,
            List<ExperienciaLaboralInterna> agregaELI, Object[] eliminadosELI,
            List<Documentacion> agregaDoc, Object[] eliminadosDoc, List<VacacionesAplicacion> agregaVac,
            Empleados empleados, List<PlazasPorEmpleado> listPlazasPorEmpleados, List<PlazasPorEmpleadosMov> listPlazasPorEmpleadoMov, IngresosReingresosBajas ingresosReingresosBajas, SalariosIntegrados salariosIntegrados, String uuidCxn) {//JSA01
        return empleadosDAO.SaveEmpleado(agrega, eliminados,
                agregaFE, eliminadosFE,
                agregaCap, eliminadosCap,
                agregaELE, eliminadosELE,
                agregaELI, eliminadosELI,
                agregaDoc, eliminadosDoc,agregaVac,
                empleados, listPlazasPorEmpleados, listPlazasPorEmpleadoMov, ingresosReingresosBajas, salariosIntegrados, uuidCxn);//JSA01
    }

    public Mensaje UpdateEmpleado(List<Familiares> agrega, Object[] eliminados,
            List<FormacionEconomica> agregaFE, Object[] eliminadosFE,
            List<Capacitaciones> agregaCap, Object[] eliminadosCap,
            List<ExperienciaLaboralExterna> agregaELE, Object[] eliminadosELE,
            List<ExperienciaLaboralInterna> agregaELI, Object[] eliminadosELI,
            List<Documentacion> agregaDoc, Object[] eliminadosDoc, List<VacacionesAplicacion> agregaVac, Empleados empleados,
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadoMov, IngresosReingresosBajas ingresosReingresosBajas, boolean calcularSDI, SalariosIntegrados salariosIntegrados, String uuidCxn) {//JSA01
        return empleadosDAO.UpdateEmpleado(agrega, eliminados,
                agregaFE, eliminadosFE,
                agregaCap, eliminadosCap,
                agregaELE, eliminadosELE,
                agregaELI, eliminadosELI,
                agregaDoc, eliminadosDoc, agregaVac, empleados,
                listPlazasPorEmpleadoMov, ingresosReingresosBajas, calcularSDI, salariosIntegrados, uuidCxn);//JSA01
    }

    public Mensaje existeRFC(String rfc, String claveEmpleado, String uuidCxn) {//JSA02
        return empleadosDAO.existeRFC(rfc, claveEmpleado, uuidCxn);
    }

    public Mensaje getEmpleadoPorClaveRazonTipNomina(String clave, String claveRazon, String claveTipNom, Date fechaInicial, Date fechaFinal, String uuidCxn) {
       
        return empleadosDAO.getEmpleadoPorClaveRazonTipNomina(clave, claveRazon, claveTipNom, fechaInicial, fechaFinal, uuidCxn);
    }

    public Mensaje DeleteEmpleado(Empleados entity, String uuidCxn) {
        return empleadosDAO.DeleteEmpleado(entity, uuidCxn);
    }

    public EmpleadosDAO getEmpleadosDAO() {
        return empleadosDAO;
    }

    public void setEmpleadosDAO(EmpleadosDAO empleadosDAO) {
        this.empleadosDAO = empleadosDAO;
    }

   
}
