/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO Tabla base nomina, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CalculaNominaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParametrosExtra;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import java.util.Date;

public class ServicioCalculoNomina implements ServicioCalculoNominaIF {

    private CalculaNominaDAO calculaNominaDAO;

    public CalculaNominaDAO getCalculaNominaDAO() {
        return calculaNominaDAO;
    }

    public void setCalculaNominaDAO(CalculaNominaDAO calculaNominaDAO) {
        this.calculaNominaDAO = calculaNominaDAO;
    }

    public Mensaje calculaNomina(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina,
            String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago,
            String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra, int ejercicioActivo, String uuidCxn, String uuidCxnMaestra) {
        return calculaNominaDAO.calculaNomina(claveEmpIni, claveEmpFin, claveTipoNomina, claveTipoCorrida, idPeriodoNomina, clavePuesto, claveCategoriasPuestos, claveTurno, claveRazonSocial, claveRegPatronal, claveFormaDePago, claveDepto, claveCtrCosto, tipoSalario, tipoContrato, status, controlador, uso, parametrosExtra, ejercicioActivo, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje calculaSalarioDiarioIntegerado(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago, String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra, boolean soloCalculo, boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra) {
        return calculaNominaDAO.calculaSalarioDiarioIntegerado(claveEmpIni, claveEmpFin, claveTipoNomina, claveTipoCorrida, idPeriodoNomina, clavePuesto, claveCategoriasPuestos, claveTurno, claveRazonSocial, claveRegPatronal, claveFormaDePago, claveDepto, claveCtrCosto, tipoSalario, tipoContrato, status, controlador, uso, parametrosExtra, soloCalculo, peticionModuloCalculoSalarioDiarioIntegrado, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje calculaSDIPorEmpleado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, String controlador, ParametrosExtra parametrosExtra, boolean soloCalculo, boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra) {
        return calculaNominaDAO.calculaSDIPorEmpleado(plazasPorEmpleadosMov, controlador, parametrosExtra, soloCalculo, peticionModuloCalculoSalarioDiarioIntegrado, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje busquedaQueryConsultaEmpleados(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String[] valoresDatosEspeciales, String[] camposWhereExtras, String nombreFuenteDatos, Date[] rangoFechas, String ordenado, String claveRazonSocial, String controladores, String uuidCxn, String uuidCxnMaestra) {
        return calculaNominaDAO.busquedaQueryConsultaEmpleados(tablas, camposMostrar, camposWhere, valoresWhere, camposOrden, valoresDatosEspeciales, camposWhereExtras, nombreFuenteDatos, rangoFechas, ordenado, claveRazonSocial, controladores, uuidCxn, uuidCxnMaestra);
    }
}
