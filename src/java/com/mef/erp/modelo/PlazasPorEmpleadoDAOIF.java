/**
 * @author: Victor Fecha de Creación: 15/06/2012 Compañía: Macropro Descripción
 * del programa: interface PlazasPorEmpleadoDAOIF para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:ARMANDO Fecha:04/08/2012 Descripcion:Se cambio de ubicacion
 * y nombre de estos metodos por lo largo del nombre:
 * getPlazasPorEmpleadosMovPorEmpleadoPorRazonSocialPorFechaFinalPorFechaInicialPorTipoNomina
 * getPlazasPorEmpleadosMovPorEmpleadoPorRazonSocialPorFechaFinalPorFechaInicialPorTipoNominaPorReferencia
 * Por: getPlazasPorEmpleadosActivos getPlazasPorEmpleadosPorReferenciaActiva
 * los parametros son los filtros no es necesario poner todos solo ponerle el
 * nombre de acuerdo a la funcion que va hacer el metodo o lo que te va
 * regresar.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:ARMANDO Fecha:10/03/2014 Descripcion:Se agrego el metodo getPlazasPorEmpleadoReingreso para obtener las 
 * plazasPorEmpleado que fueronReingresados envase a una plaza dada de baja.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import java.util.Date;
import java.util.List;

public interface PlazasPorEmpleadoDAOIF extends GenericDAO<PlazasPorEmpleado, Long> {

    Mensaje agregar(PlazasPorEmpleado entity, String uuidCxn);

    Mensaje actualizar(PlazasPorEmpleado entity, String uuidCxn);

    Mensaje eliminar(PlazasPorEmpleado entity, String uuidCxn);

    Mensaje getPlazasPorEmpleadoPorClave(String clave, String razonSocial, String uuidCxn);

    Mensaje getPlazasPorEmpleadoPorRazonSocial(String clave, String razonSocial, String uuidCxn);

    Mensaje getPlazasPorEmpleadoPorRazonSocialActivo(String claveEmpleado, String razonSocial, Date fecha, Integer result, String uuidCxn);

    Mensaje getPlazasPorEmpleadoPorClavePorRazonSocialActivo(String clavePlaza, String claveEmpleado, String razonSocial, Date fecha, String uuidCxn);

    Mensaje getPlazasPorEmpleadosActivos(String claveEmpleado, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String claveTipoNomina, Integer result, String uuidCxn);//JSA01

    Mensaje getPlazasPorEmpleadosPorReferenciaActiva(String claveEmpleado, String claveRazonSocial, Date fechaInicial, Date fechaFinal, String claveTipoNomina, String claveReferencia, String uuidCxn);//JSA01

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosEmpleado(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje agregarListaPlazasPorEmpleados(List<PlazasPorEmpleado> entitys, int rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje deleteListQuerys(String tabla, String campo, Object[] valores, String uuidCxn);

    Mensaje getPlazasPorEmpleadoReingreso(String claveReingreso, String claveRazonesSociales, String uuidCxn);
}
