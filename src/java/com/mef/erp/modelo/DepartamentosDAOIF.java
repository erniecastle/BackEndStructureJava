/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 15/06/2011 Compañía:
 * Exito Software. Descripción del programa: inteface de clase de Departamentos
 * para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Departamentos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface DepartamentosDAOIF {

    Mensaje getDepartamentosAll(String claveRazonesSocial, String uuidCxn);//JSA01

    Mensaje getDepartamentosPorClave(String clave, String claveRazonesSocial, String uuidCxn);//JSA01

    Mensaje agregar(Departamentos entity, String uuidCxn);//JSA01

    Mensaje actualizar(Departamentos entity, String uuidCxn);//JSA01

    Mensaje eliminar(Departamentos entity, String uuidCxn);//JSA01

    Mensaje consultaPorFiltrosDepartamento(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);//JSA01

    Mensaje consultaPorRangosDepto(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn);//JSA01

    Mensaje existeDato(String campo, Object valor, String uuidCxn);//JSA01

    Mensaje saveDeleteDepartamentos(List<Departamentos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);//JSA01
}
