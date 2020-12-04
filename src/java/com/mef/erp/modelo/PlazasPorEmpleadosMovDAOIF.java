/**
 * @author: Victor Fecha de Creación: 15/06/2012 Compañía: Macropro Descripción
 * del programa: interface PlazasPorEmpleadosMovDAOIF para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JEVC
 * @author: Ernesto c. Fecha: 25/06/2012 Compañía: Exito Descripción:se agrego
 * interfaz getPlazasPorEmpleadosMovPorRazonSocialPorEmpleadoPorActivo
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
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import java.util.Date;
import java.util.List;

public interface PlazasPorEmpleadosMovDAOIF extends GenericDAO<PlazasPorEmpleadosMov, Long> {

    Mensaje agregar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn);

    Mensaje actualizar(PlazasPorEmpleadosMov entity, SalariosIntegrados salariosIntegrados, String uuidCxn);

    Mensaje eliminar(PlazasPorEmpleadosMov entity, String uuidCxn);

    Mensaje eliminarMovimientosPorPlaza(PlazasPorEmpleadosMov entity, List<Long> movimientos, String uuidCxn);

    Mensaje eliminarPlazasMovimientos(PlazasPorEmpleadosMov entity, String uuidCxn);

    Mensaje getPlazasPorEmpleadosMovMaxPorEmpleado(String claveEmpleado, String razonSocial, String uuidCxn);

    Mensaje getPlazasPorEmpleadosMovMaxPorClave(String clave, String razonSocial, String uuidCxn);

    Mensaje getPlazasPorEmpleadosMovPorRazonSocial(String clave, String uuidCxn);

    Mensaje getPlazasPorEmpleadosMovPorReferencia(String referencia, String claveRazonesSociales, Integer result, String uuidCxn);

    Mensaje getPlazasPorEmpleadosMovAnterior(Long id, String referencia, String claveRazonesSociales, Integer result, String uuidCxn);

    Mensaje getPorEmpleadoYRazonSocialVigente(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    Mensaje getPorEmpleadoYRazonSocialFiniquitoVigente(String claveEmpleado, String claveRazonSocial, String claveFiniquito, String uuidCxn);

    Mensaje getPorEmpleadoYRazonSocial(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    Mensaje getPorEmpleadoYRazonSocialYFecha(String claveEmpleado, String claveRazonSocial, Date fecha, String uuidCxn);

    Mensaje getCantidadPlazasPorEmpleado(String claveEmpleado, String claveRazonSocial, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosEmpleado(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje agregarListaPlazasPorEmpleadosMovs(List<PlazasPorEmpleadosMov> entitys, int rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);
    
    Mensaje getEmpleadosManejaPagoPorHoras(String claveTipoNomina, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String uuidCxn);
}
