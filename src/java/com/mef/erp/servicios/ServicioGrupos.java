/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase grupos para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.GruposDAO;
import com.mef.erp.modelo.entidad.BaseAfectadaGrupo;
import com.mef.erp.modelo.entidad.Grupo;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author daniel
 */
public class ServicioGrupos implements ServicioGruposIF {

    private GruposDAO gruposDAO;
   

    public Mensaje agregar(Grupo entity, String uuidCxn) {//JSA01
        return gruposDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Grupo entity, String uuidCxn) {//JSA01
        return gruposDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Grupo entity, String uuidCxn) {//JSA01
        return gruposDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getGruposAll(String uuidCxn) {//JSA01
        return gruposDAO.getGruposALL(uuidCxn);
    }

    public Mensaje getGruposPorClave(String clave, String uuidCxn) {//JSA01
        return gruposDAO.getGruposPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return gruposDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return gruposDAO.consultaPorFiltrosGrupo(query, campos, valores, inicio, rango, uuidCxn);
    }

    public Mensaje agregarListaGrupos(List<Grupo> entitys, int rango, String uuidCxn) {//JSA01
        return gruposDAO.agregarListaGrupos(entitys, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return gruposDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {//JSA01
        return gruposDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public Mensaje agregaGruposBaseAfectadas(Grupo entity, List<BaseAfectadaGrupo> eliminadasAfectadaGrupos, String uuidCxn) {//JSA01
        return gruposDAO.agregaGruposBaseAfectadas(entity, eliminadasAfectadaGrupos, uuidCxn);
    }

    public GruposDAO getGruposDAO() {
        return gruposDAO;
    }

    public void setGruposDAO(GruposDAO gruposDAO) {

        this.gruposDAO = gruposDAO;
    }
}
