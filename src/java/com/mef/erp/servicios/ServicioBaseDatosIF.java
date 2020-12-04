/**
 * @author: Jose Armando Fecha de Creación: 12/09/2011 Compañía: Macropro
 * Descripción del programa: Esta clase servira para inicializar una bds.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;

public interface ServicioBaseDatosIF {

    /*boolean*/
    public Mensaje inicializarNuevaMEFMaster(String uuidCxn, int rango, String nombreBaseDatos);

    /*boolean*/
    public Mensaje inicializarNuevaMEF(String uuidCxn, int rango, String nombreBaseDatos);

    /*List<?>*/
    public Mensaje obtenerConexion(String usuario, String password, String BaseDatosConfig, String BaseDatos, String tipoServidor, String Ubicacionservidor, String puertoServidor, boolean cambiarConfiguracion);
    
    /*boolean*/
    public Mensaje validaArchivoKey(String uuidCxn, String rutaArchivoRFC);
    
     /*String*/
    String getVersion();
}
