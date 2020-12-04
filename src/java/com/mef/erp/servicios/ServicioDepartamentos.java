/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 16/06/2011 Compañía:
 * Exito Software. Descripción del programa: clase SERVICIO departamentos, para
 * llamados a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.DepartamentosDAO;
import com.mef.erp.modelo.entidad.Departamentos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioDepartamentos implements ServicioDepartamentosIF {

    private DepartamentosDAO departamentosDAO;


    public Mensaje agregar(Departamentos entity, String uuidCxn) {//JSA01
        return departamentosDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Departamentos entity, String uuidCxn) {//JSA01
        return departamentosDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Departamentos entity, String uuidCxn) {//JSA01
        return departamentosDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getDepartamentosAll(String claveRazonesSocial, String uuidCxn) {//JSA01
        return departamentosDAO.getDepartamentosAll(claveRazonesSocial, uuidCxn);
    }

    public Mensaje getDepartamentosPorClave(String clave, String claveRazonesSocial, String uuidCxn) {//JSA01
        return departamentosDAO.getDepartamentosPorClave(clave, claveRazonesSocial, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return departamentosDAO.consultaPorFiltrosDepartamento(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String[] camposWhere, Object[] camposWhereValores, String uuidCxn) {//JSA01
        return departamentosDAO.consultaPorRangosDepto(inicio, rango, camposWhere, camposWhereValores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return departamentosDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje saveDeleteDepartamentos(List<Departamentos> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn) {//JSA01
        return departamentosDAO.saveDeleteDepartamentos(entitysCambios, clavesDelete, rango, uuidCxn);
    }

    public DepartamentosDAO getDepartamentosDAO() {
        return departamentosDAO;
    }

    public void setDepartamentosDAO(DepartamentosDAO DepartamentosDAO) {
        this.departamentosDAO = DepartamentosDAO;
    }
}
