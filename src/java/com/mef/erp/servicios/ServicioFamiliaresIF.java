/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Familiares;
import com.mef.erp.modelo.entidad.Mensaje;

/**
 *
 * @author daniel
 */
public interface ServicioFamiliaresIF {
    
    /*Familiares*/
    public Mensaje agregar(Familiares entity ,  String uuidCxn);

    /*boolean*/
    public Mensaje actualizar(Familiares entity ,  String uuidCxn);

    /*boolean*/
    public Mensaje eliminar(Familiares entity ,  String uuidCxn);

    /*List<Familiares>*/
    public Mensaje getFamiliaresAll( String uuidCxn);

    /*Familiares*/
    public Mensaje getFamiliaresPorId(Long id ,  String uuidCxn);

    /*List<Familiares>*/
    public Mensaje getFamiliaresPorEmpleados(Empleados b ,  String uuidCxn);

    /*List<Familiares>*/
    public Mensaje getFamiliaresPorIDEmpleado(Long clave ,  String uuidCxn);
    
    /*boolean*/
    public Mensaje EliminaFamiliaresPorEmpleado(Empleados empleado , String uuidCxn);
    
   
}
