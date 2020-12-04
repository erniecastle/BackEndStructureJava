/**
 * @author: Victor Fecha de Creación: 15/06/2012 Compañía: Macropro Descripción
 * del programa: clase ServicioPlazasPorEmpleadosMovPorEmpleadosMov para
 * llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PlazasPorEmpleadosMovDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import java.util.Date;
import java.util.List;

public class ServicioPlazasPorEmpleadosMov implements ServicioPlazasPorEmpleadosMovIF {

    private PlazasPorEmpleadosMovDAO plazasPorEmpleadosMovDAO;

    public Mensaje agregar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.agregar(entity, salariosIntegrados, uuidCxn);
    }

    public Mensaje actualizar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.actualizar(entity, salariosIntegrados, uuidCxn);
    }

    public Mensaje eliminar(PlazasPorEmpleadosMov entity, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje eliminarMovimientosPorPlaza(PlazasPorEmpleadosMov entity, List<Long> movimientos, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.eliminarMovimientosPorPlaza(entity, movimientos, uuidCxn);
    }

    public Mensaje eliminarPlazasMovimientos(PlazasPorEmpleadosMov entity, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.eliminarPlazasMovimientos(entity, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadosMovMaxPorClave(String clave, String razonSocial, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPlazasPorEmpleadosMovMaxPorClave(clave, razonSocial, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadosMovMaxPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPlazasPorEmpleadosMovMaxPorEmpleado(claveEmpleado, razonSocial, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadosMovPorRazonSocial(String clave, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPlazasPorEmpleadosMovPorRazonSocial(clave, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadosMovPorReferencia(String referencia, String claveRazonesSociales, Integer result, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPlazasPorEmpleadosMovPorReferencia(referencia, claveRazonesSociales, result, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadosMovAnterior(Long id, String referencia, String claveRazonesSociales, Integer result, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPlazasPorEmpleadosMovAnterior(id, referencia, claveRazonesSociales, result, uuidCxn);
    }

    public Mensaje getPorEmpleadoYRazonSocialVigente(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPorEmpleadoYRazonSocialVigente(claveEmpleado, claveRazonSocial, uuidCxn);
    }

    public Mensaje getPorEmpleadoYRazonSocialFiniquitoVigente(String claveEmpleado, String claveRazonSocial, String claveFiniquito, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPorEmpleadoYRazonSocialFiniquitoVigente(claveEmpleado, claveRazonSocial, claveFiniquito, uuidCxn);
    }

    public Mensaje getPorEmpleadoYRazonSocial(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPorEmpleadoYRazonSocial(claveEmpleado, claveRazonSocial, uuidCxn);
    }

    public Mensaje getPorEmpleadoYRazonSocialYFecha(String claveEmpleado, String claveRazonSocial, Date fecha, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getPorEmpleadoYRazonSocialYFecha(claveEmpleado, claveRazonSocial, fecha, uuidCxn);
    }

    public Mensaje getCantidadPlazasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getCantidadPlazasPorEmpleado(claveEmpleado, claveRazonSocial, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.consultaPorFiltrosEmpleado(query, campos, valores, uuidCxn);
    }

    public Mensaje agregarListaPlazasPorEmpleadosMovs(List<PlazasPorEmpleadosMov> entitys, int rango, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.agregarListaPlazasPorEmpleadosMovs(entitys, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    @Override
    public Mensaje getEmpleadosManejaPagoPorHoras(String claveTipoNomina, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String uuidCxn) {
        return plazasPorEmpleadosMovDAO.getEmpleadosManejaPagoPorHoras(claveTipoNomina, claveRazonSocial, fechaInicial, fechaFinal, uuidCxn);
    }

    public PlazasPorEmpleadosMovDAO getPlazasPorEmpleadosMovDAO() {
        return plazasPorEmpleadosMovDAO;
    }

    public void setPlazasPorEmpleadosMovDAO(PlazasPorEmpleadosMovDAO plazasPorEmpleadosMovDAO) {
        this.plazasPorEmpleadosMovDAO = plazasPorEmpleadosMovDAO;
    }

}
