/**
 * @author: Ernesto Fecha de Creación: 07/08/2013 Compañía: Exito Descripción
 * del programa: clase servicio para Firmas
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:DRO01 Autor:Dayane Rocha. Fecha:23/08/2013 Descripción:Se agregaron
 * servicios para Firmas.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Firmas;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface ServicioFirmasIF {

    /*List<Firmas>*/
    public Mensaje getFirmasPorFechaYRazonSocial(String razonSocial, Date fecha , String uuidCxn);//DRO01

    /*Firmas*/
    Mensaje agregar(Firmas entity , String uuidCxn);//DRO01

    /*boolean*/
    public Mensaje actualizar(Firmas entity , String uuidCxn);//DRO01

    /*boolean*/
    public Mensaje eliminar(Firmas entity , String uuidCxn);//DRO01

    /*List<Firmas>*/
    Mensaje getFirmasAll(String uuidCxn);//DRO01

    /*Firmas*/
    Mensaje getFirmasPorClave(String clave , String uuidCxn);//DRO01

    /*List<Firmas>*/
    public Mensaje consultaPorFiltrosFirmas(String query, Object[] campos, Object[] valores , String uuidCxn);//DRO01

    /*List<Firmas>*/
    public Mensaje consultaPorRangosFirmas(Integer inicio, Integer rango , String uuidCxn);//DRO01

    /*Boolean*/
    public Mensaje existeDato(String campo, Object valor , String uuidCxn);//DRO01

    /*List<Firmas>*/
    public Mensaje saveDeleteFirmas(List<Firmas> entitysCambios, Object[] clavesDelete, int rango , String uuidCxn);//DRO01

}
