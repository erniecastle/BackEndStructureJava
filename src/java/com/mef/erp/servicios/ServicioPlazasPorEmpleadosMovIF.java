/**
 * @author: Victor Fecha de Creación: 15/06/2012 Compañía: Macropro Descripción
 * del programa: interface ServicioPlazasPorEmpleadosMovIF para llamados a
 * metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:ARMANDO Fecha:04/08/2012 Descripcion:Se cambiaron estos
 * metodos de ubicacion (se cambio a plazasporempleado) y nombre de estos
 * metodos por lo largo del nombre:
 * getPlazasPorEmpleadosMovPorEmpleadoPorRazonSocialPorFechaFinalPorFechaInicialPorTipoNomina
 * getPlazasPorEmpleadosMovPorEmpleadoPorRazonSocialPorFechaFinalPorFechaInicialPorTipoNominaPorReferencia
 * Por: getPlazasPorEmpleadosActivos getPlazasPorEmpleadosPorReferenciaActiva
 * los parametros son los filtros no es necesario poner todos solo ponerle el
 * nombre de acuerdo a la funcion que va hacer el metodo o lo que te va
 * regresar.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import java.util.Date;
import java.util.List;

/**
 *
 * @author daniel
 */
public interface ServicioPlazasPorEmpleadosMovIF {

    /*PlazasPorEmpleadosMov*/
    Mensaje agregar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn);

    /*String*/
    Mensaje actualizar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn);

    /*String*/
    Mensaje eliminar(PlazasPorEmpleadosMov entity, String uuidCxn);

    /*boolean*/
    Mensaje eliminarMovimientosPorPlaza(PlazasPorEmpleadosMov entity, List<Long> movimientos, String uuidCxn);

    /*String*/
    Mensaje eliminarPlazasMovimientos(PlazasPorEmpleadosMov entity, String uuidCxn);

    /*PlazasPorEmpleadosMov*/
    Mensaje getPlazasPorEmpleadosMovMaxPorClave(String clave, String razonSocial, String uuidCxn);

    /*PlazasPorEmpleadosMov*/
    Mensaje getPlazasPorEmpleadosMovMaxPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    public Mensaje getPlazasPorEmpleadosMovPorRazonSocial(String clave, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje getPlazasPorEmpleadosMovPorReferencia(String referencia, String claveRazonesSociales, Integer result, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje getPlazasPorEmpleadosMovAnterior(Long id, String referencia, String claveRazonesSociales, Integer result, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje getPorEmpleadoYRazonSocialVigente(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje getPorEmpleadoYRazonSocialFiniquitoVigente(String claveEmpleado, String claveRazonSocial, String claveFiniquito, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje getPorEmpleadoYRazonSocial(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje getPorEmpleadoYRazonSocialYFecha(String claveEmpleado, String claveRazonSocial, Date fecha, String uuidCxn);

    /*int*/
    Mensaje getCantidadPlazasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje agregarListaPlazasPorEmpleadosMovs(List<PlazasPorEmpleadosMov> entitys, int rango, String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    /*boolean*/
    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn);

    /*List<PlazasPorEmpleadosMov>*/
    Mensaje getEmpleadosManejaPagoPorHoras(String claveTipoNomina, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String uuidCxn);
}
