/**
 * @author: Ernesto Fecha de Creación: 07/08/2013 Compañía: Exito Descripción
 * del programa: Interfaz para clase DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:DRO01 Autor:Dayane Rocha. Fecha:23/08/2013 Descripción:Se agregaron
 * servicios para Firmas.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Firmas;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Ernesto
 */
public interface FirmasDAOIF extends GenericDAO<Firmas, Long> {

    public Mensaje getFirmasPorFechaYRazonSocial(String razonSocial, Date fecha, String uuidCxn);

    Mensaje agregar(Firmas entity, String uuidCxn);//DRO01

    public Mensaje actualizar(Firmas entity, String uuidCxn);//DRO01

    public Mensaje eliminar(Firmas entity, String uuidCxn);//DRO01

    Mensaje getFirmasAll(String uuidCxn);//DRO01

    Mensaje getFirmasPorClave(String clave, String uuidCxn);//DRO01

    public Mensaje consultaPorFiltrosFirmas(String query, Object[] campos, Object[] valores, String uuidCxn);//DRO01

    public Mensaje consultaPorRangosFirmas(Integer inicio, Integer rango, String uuidCxn);//DRO01

    public Mensaje existeDato(String campo, Object valor, String uuidCxn);//DRO01

    public Mensaje saveDeleteFirmas(List<Firmas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);//DRO01
}
