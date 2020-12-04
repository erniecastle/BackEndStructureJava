/**
 * @author: Ernesto castillo Fecha de Creación: 20/10/2011 Compañía: Exito
 * Software. Descripción del programa: Inteface de clase de MovimientosNomina
 * para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:06/06/2015 Descripción:Se agrego como
 * parametro el id de los calculo de unidades a eliminar en el metodo
 * eliminaListaMovimientos. Tambien se añadio la consulta para obtener los
 * calculos de unidades getCalculosUnidadesPorFiltroEspecifico.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface MovimientosNominaIF extends GenericDAO<MovNomConcep, Long> {

    Mensaje getMovimientosNominaAll(String uuidCxn);//JSA01

    Mensaje getMovimientosNominaAsc(String uuidCxn);//JSA01

    Mensaje getMaxNumeroMovimientoPorTipoNominaYPeriodo(String claveTipoNomina, Long idPeriodo, String uuidCxn);

    Mensaje saveDeleteMovimientosNomina(List<MovNomConcep> AgreModif, Object[] clavesDelete, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100, String uuidCxn);//JSA01

    Mensaje getMovimientosPorPlazaEmpleado(Object[] clavesPlazasPorEmpleado, String claveTipoCorrida, String claveRazonSocial, String uuidCxn);

    Mensaje eliminaListaMovimientos(String campo, Object[] valores, List<CFDIEmpleado> valoresCFDI, Object[] valoresCalculoUnidades, Object[] valoresReestablecer, boolean incluirEliminadoDiferenteTipoPantalla100, String uuidCxn);//JSA02

    public Mensaje getMovimientosPorFiltro(String[] camposWhere, Object[] valoresWhere, String uuidCxn);

    public Mensaje getMovimientosPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere, String uuidCxn);

    public Mensaje getCalculosUnidadesPorFiltroEspecifico(String[] camposWhere, Object[] valoresWhere, List<CFDIEmpleado> listCFDIEmpleado, String uuidCxn);//JSA02

    Mensaje buscaMovimientosNominaFiltrado(List<Object> valoresDeFiltrado, String uuidCxn);

}
