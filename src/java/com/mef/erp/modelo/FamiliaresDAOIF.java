/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Familiares;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface FamiliaresDAOIF {

    public Mensaje agregar(Familiares entity, String uuidCxn);

    public Mensaje actualizar(Familiares entity, String uuidCxn);

    public Mensaje eliminar(Familiares entity, String uuidCxn);

    public Mensaje getFamiliaresAll(String uuidCxn);

    public Mensaje getFamiliaresPorId(Long id, String uuidCxn);

    public Mensaje getFamiliaresPorEmpleados(Empleados b, String uuidCxn);

    public Mensaje getFamiliaresPorIDEmpleado(Long clave, String uuidCxn);

    public Mensaje EliminaFamiliaresPorEmpleado(Empleados empleado, String uuidCxn);
}
