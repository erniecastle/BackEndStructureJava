/**
 * @author: José Ernesto Valenzuela Castillo Fecha de Creación: 05/12/2011
 * Compañía: Exito software. Descripción del programa: interface Excepciones
 * para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Excepciones;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public interface ExcepcionesDAOIF {

    Mensaje agregar(Excepciones entity, String uuidCxn);

    public Mensaje agregarExcepciones(List<Excepciones> entitysCambios, String uuidCxn);

    Mensaje getExcepcionesAll(String uuidCxn);
}
