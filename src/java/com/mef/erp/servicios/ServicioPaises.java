/**
 * @author: Ernesto Valenzuela Fecha de Creación: 15/03/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO Paises, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 30/07/2011
 * Descripción: Se Cambió clave a tipo String
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.PaisesDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Paises;
import java.util.List;

public class ServicioPaises implements ServicioPaisesIF {

    private PaisesDAO paisesDAO;
    private String name;

    public Mensaje agregar(Paises entity, String uuidCxn) {
        return paisesDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Paises entity, String uuidCxn) {
        return paisesDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Paises entity, String uuidCxn) {
        return paisesDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getPaisAll(String uuidCxn) {
        return paisesDAO.getPaisAll(uuidCxn);
    }

    public Mensaje getPaisPorClave(String clave, String uuidCxn) {//AAP01
        return paisesDAO.getPaisPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {
        return paisesDAO.consultaPorFiltrosPais(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {
        return paisesDAO.consultaPorRangosPais(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {
        return paisesDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeletePaises(List<Paises> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {
        return paisesDAO.saveDeletePaises(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public PaisesDAO getPaisesDAO() {
        return paisesDAO;
    }

    public void setPaisesDAO(PaisesDAO paisesDAO) {
        this.paisesDAO = paisesDAO;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    
    
}
