/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO parametro nomina, para llamados a
 * metodos de objeto DAO
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

import com.mef.erp.modelo.ParametroConceptosDeNominaDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.ParaConcepDeNom;
import java.util.List;

public class ServicioParametroConceptosDeNomina implements ServicioParametroConceptosDeNominaIF {

    private ParametroConceptosDeNominaDAO parametroConceptosDeNominaDAO;

    public Mensaje agregar(ParaConcepDeNom entity, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(ParaConcepDeNom entity, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(ParaConcepDeNom entity, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getParametroConceptosDeNominaAll(String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.getParametroConceptosDeNominaAll(uuidCxn);
    }

    public Mensaje getParametroConceptosDeNominaPorClave(String clave, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.getParametroConceptosDeNominaPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.consultaPorFiltrosParametros(query, campos, valores, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje agregarListaParametroConceptosDeNomina(List<ParaConcepDeNom> entitys, int rango, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.agregarListaParametroConceptosDeNomina(entitys, rango, uuidCxn);
    }

    public Mensaje deleteListQuery(String tabla, String campo, Object[] valores, String uuidCxn) {//JSA01

        return parametroConceptosDeNominaDAO.deleteListQuerys(tabla, campo, valores, uuidCxn);
    }

    public ParametroConceptosDeNominaDAO getParametroConceptosDeNominaDAO() {

        return parametroConceptosDeNominaDAO;
    }

    public void setParametroConceptosDeNominaDAO(ParametroConceptosDeNominaDAO parametroConceptosDeNominaDAO) {
        this.parametroConceptosDeNominaDAO = parametroConceptosDeNominaDAO;
    }
}
