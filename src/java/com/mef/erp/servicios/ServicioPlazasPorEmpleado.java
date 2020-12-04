/**
 * @author: Victor Fecha de Creación: 15/06/2012 Compañía: Macropro Descripción
 * del programa: clase ServicioPlazasPorEmpleado para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:ARMANDO Fecha:10/03/2014 Descripcion:Se agrego el metodo
 * getPlazasPorEmpleadoReingreso para obtener las plazasPorEmpleado que
 * fueronReingresados envase a una plaza dada de baja.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PlazasPorEmpleadoDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import java.util.Date;
import java.util.List;

public class ServicioPlazasPorEmpleado implements ServicioPlazasPorEmpleadoIF {

    private PlazasPorEmpleadoDAO plazasPorEmpleadoDAO;

    public Mensaje agregar(PlazasPorEmpleado entity, String uuidCxn) {
        return plazasPorEmpleadoDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(PlazasPorEmpleado entity, String uuidCxn) {
        return plazasPorEmpleadoDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(PlazasPorEmpleado entity, String uuidCxn) {
        return plazasPorEmpleadoDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadoPorClave(String clave, String razonSocial, String uuidCxn) {
        return plazasPorEmpleadoDAO.getPlazasPorEmpleadoPorClave(clave, razonSocial, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadoPorRazonSocialActivo(String claveEmpleado, String razonSocial, Date fecha, Integer result, String uuidCxn) {
        return plazasPorEmpleadoDAO.getPlazasPorEmpleadoPorRazonSocialActivo(claveEmpleado, razonSocial, fecha, result, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadoPorClavePorRazonSocialActivo(String clavePlaza, String claveEmpleado, String razonSocial, Date fecha, String uuidCxn) {
        return plazasPorEmpleadoDAO.getPlazasPorEmpleadoPorClavePorRazonSocialActivo(clavePlaza, claveEmpleado, razonSocial, fecha, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadoPorRazonSocial(String clave, String razonSocial, String uuidCxn) {
        return plazasPorEmpleadoDAO.getPlazasPorEmpleadoPorRazonSocial(clave, razonSocial, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadosActivos(String claveEmpleado, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String claveTipoNomina, Integer result, String uuidCxn) {
        return plazasPorEmpleadoDAO.getPlazasPorEmpleadosActivos(claveEmpleado, claveRazonSocial, fechaInicial, fechaFinal, claveTipoNomina, result, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadosPorReferenciaActiva(String claveEmpleado, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String claveTipoNomina, String claveReferencia, String uuidCxn) {
        return plazasPorEmpleadoDAO.getPlazasPorEmpleadosPorReferenciaActiva(claveEmpleado, claveRazonSocial, fechaInicial, fechaFinal, claveTipoNomina, claveReferencia, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return plazasPorEmpleadoDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {
        return plazasPorEmpleadoDAO.consultaPorFiltrosEmpleado(query, campos, valores, uuidCxn);
    }

    public Mensaje agregarListaPlazasPorEmpleados(List<PlazasPorEmpleado> entitys, int rango, String uuidCxn) {
        return plazasPorEmpleadoDAO.agregarListaPlazasPorEmpleados(entitys, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return plazasPorEmpleadoDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {
        return plazasPorEmpleadoDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public Mensaje getPlazasPorEmpleadoReingreso(String claveReingreso, String claveRazonesSociales, String uuidCxn) {
        return getPlazasPorEmpleadoDAO().getPlazasPorEmpleadoReingreso(claveReingreso, claveRazonesSociales, uuidCxn);
    }

    public PlazasPorEmpleadoDAO getPlazasPorEmpleadoDAO() {
        return plazasPorEmpleadoDAO;
    }

    public void setPlazasPorEmpleadoDAO(PlazasPorEmpleadoDAO plazasPorEmpleadoDAO) {
        this.plazasPorEmpleadoDAO = plazasPorEmpleadoDAO;
    }
}
