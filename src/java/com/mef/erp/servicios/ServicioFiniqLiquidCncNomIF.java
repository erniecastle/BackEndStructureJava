/**
 * @author: Victor Lopez Fecha de Creación: 04/07/2012 Compañía: MacroPro.
 * Descripción del programa: interface servicio ServicioFiniqLiquidCncNomIF
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.FiniqLiquidCncNom;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ServicioFiniqLiquidCncNomIF {

    /*FiniqLiquidCncNom*/
    Mensaje agregar(FiniqLiquidCncNom entity ,  String uuidCxn);

    /*boolean*/
    Mensaje actualizar(FiniqLiquidCncNom entity ,  String uuidCxn);

    /*boolean*/
    Mensaje eliminar(FiniqLiquidCncNom entity ,  String uuidCxn);

    /*List<FiniqLiquidCncNom>*/
    Mensaje getFiniqLiquidCncNomAll( String uuidCxn);

    /*List<FiniqLiquidCncNom>*/
    Mensaje getFiniqLiquidCncNomPorFiniquitosLiquidaciones(FiniquitosLiquidaciones finiquitosLiquidacion ,  String uuidCxn);

//    FiniqLiquidCncNom getFiniqLiquidCncNomPorEmpERegPatyRazonSoc(Empleados empleados, RegistroPatronal registroPatronal, RazonesSociales razonSocial);
//
//    List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRegPatronal(RegistroPatronal registroPatronal);
//
//    List<FiniqLiquidCncNom> getFiniqLiquidCncNomPorRazonSocial(RazonesSociales razonSocial);
    /*List<FiniqLiquidCncNom>*/
    Mensaje consultaPorRangos(Integer inicio, Integer rango ,  String uuidCxn);

    /*List<FiniqLiquidCncNom>*/
    Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores ,  String uuidCxn);

    /*Boolean*/
    Mensaje existeDato(String campo, Object valor ,  String uuidCxn);

    /*List<FiniqLiquidCncNom>*/
    Mensaje saveDeleteFiniqLiquidCncNom(List<FiniqLiquidCncNom> entitysCambios, Object[] clavesDelete ,  String uuidCxn);

}
