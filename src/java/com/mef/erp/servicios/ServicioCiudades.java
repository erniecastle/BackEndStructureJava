/**
 * @author: Ernesto Valenzuela Fecha de Creación: 15/03/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO ciudades, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 21-07-2011
 * Descripción: Se cambio el tipo de dato de los metodos de eliminar y
 * actualizar.
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 29/07/2011
 * Descripción: Se cambió a String la clave de ciudad
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CiudadesDAO;
import com.mef.erp.modelo.entidad.Ciudades;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioCiudades implements ServicioCiudadesIF {

    private CiudadesDAO ciudadesDAO;

    public Mensaje agregar(Ciudades entity, String uuidCxn) {
        return ciudadesDAO.agregar(entity, uuidCxn);
    }

    //JSA01
    public Mensaje actualizar(Ciudades entity, String uuidCxn) {
        return ciudadesDAO.actualizar(entity, uuidCxn);
    }

    //JSA01
    public Mensaje eliminar(Ciudades entity, String uuidCxn) {
        return ciudadesDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getCiudadesAll(String uuidCxn) {
        return ciudadesDAO.getCiudadesAll(uuidCxn);
    }

    public Mensaje getCiudadesPorClave(String clave, String uuidCxn) {//AAP01
        return ciudadesDAO.getCiudadesPorClave(clave, uuidCxn);
    }

    public Mensaje getCiudadesPorMunicipio(String claveMunicipio, String uuidCxn) {
        return ciudadesDAO.getCiudadesPorMunicipio(claveMunicipio, uuidCxn);
    }

    public Mensaje getCiudadesPorEstado(String claveEstado, String uuidCxn) {
        return ciudadesDAO.getCiudadesPorEstado(claveEstado, uuidCxn);
    }

    public Mensaje getCiudadesPorPais(String clavePais, String uuidCxn) {
        return ciudadesDAO.getCiudadesPorPais(clavePais, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {
        return ciudadesDAO.consultaPorFiltrosCiudades(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return ciudadesDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return ciudadesDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteCiudades(List<Ciudades> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return ciudadesDAO.saveDeleteCiudades(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public CiudadesDAO getCiudadesDAO() {
        return ciudadesDAO;
    }

    public void setCiudadesDAO(CiudadesDAO ciudadesDAO) {
        this.ciudadesDAO = ciudadesDAO;
    }
}
