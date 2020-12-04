/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: interface
 * FiniqLiquidCncNomDAOIF para llamados a metodos de HIBERNATE Fecha:04-07-2012
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.FiniqLiquidCncNom;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface FiniqLiquidCncNomDAOIF {

    Mensaje agregar(FiniqLiquidCncNom entity, String uuidCxn);

    Mensaje actualizar(FiniqLiquidCncNom entity, String uuidCxn);

    Mensaje eliminar(FiniqLiquidCncNom entity, String uuidCxn);

    Mensaje getFiniqLiquidCncNomAll(String uuidCxn);

    Mensaje getFiniqLiquidCncNomPorFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidacion, String uuidCxn);

//    FiniqLiquidCncNom getFiniqLiquidCncNomPorEmpERegPatyRazonSoc(Empleados empleados, RegistroPatronal registroPatronal, RazonesSociales razonSocial);
//
//    List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRegPatronal(RegistroPatronal registroPatronal);
//
//    List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRazonSocial(RazonesSociales razonSocial);
    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorFiltrosFiniquitos(String query, Object[] campos, Object[] valores, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteFiniqLiquidCncNom(List<FiniqLiquidCncNom> entitysCambios, Object[] clavesDelete, String uuidCxn);
}
