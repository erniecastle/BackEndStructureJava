/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Modulo;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface ModuloDAOIF extends GenericDAO<Modulo, Integer> {

    public Mensaje getModuloAll(String uuidCxn);

    public Mensaje getModuloPorNombre(String nombreModulo, String uuidCxn);

    public Mensaje agregar(Modulo entity, String uuidCxn);

    public Mensaje actualizar(Modulo entity, String uuidCxn);

    public Mensaje eliminar(Modulo entity, String uuidCxn);

    public Mensaje getModuloPorSistemas(String id, String uuidCxn);

    public Mensaje getModuloPorClave(String clave, String uuidCxn);

    Mensaje consultaPorFiltrosModulo(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn);

    Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteModulo(List<Modulo> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
