/**
 * @author: Ernesto Valenzuela Fecha de Creación: 03/05/2011 Compañía: Exito
 * Software. Descripción del programa: clase SERVICIO parametros, para llamados
 * a metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:17/07/2012 Descripción:
 * Se agrego el metodo getParametrosPorModuloYClave(String claveModulo, Object[]
 * clavesParametros);
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ParametrosDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Parametros;
import java.util.List;

public class ServicioParametros implements ServicioParametrosIF {

    private ParametrosDAO parametrosDAO;

    public Mensaje agregar(Parametros entity, String uuidCxn){//JSA01

        return getParametrosDAO().agregar(entity,uuidCxn);
    }

    public Mensaje actualizar(Parametros entity, String uuidCxn){//JSA01

        return getParametrosDAO().actualizar(entity,uuidCxn);
    }

    public Mensaje eliminar(Parametros entity, String uuidCxn){//JSA01

        return getParametrosDAO().eliminar(entity,uuidCxn);
    }

    public Mensaje getParametrosAll(String uuidCxn){//JSA01

        return getParametrosDAO().getParametrosAll(uuidCxn);
    }

    public Mensaje getParametrosPorClave(Long clave, String uuidCxn){//JSA01

        return getParametrosDAO().getParametrosPorClave(clave,uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn){//JSA01

        return getParametrosDAO().consultaPorFiltrosParametro(query, campos, valores,uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn){//JSA01

        return getParametrosDAO().consultaPorRangos(inicio, rango,uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn){//JSA01

        return getParametrosDAO().existeDato(campo, valor,uuidCxn);
    }

    public Mensaje getParametrosPorModulo(Integer id, String uuidCxn){//JSA01

        return getParametrosDAO().getParametrosPorModulo(id,uuidCxn);
    }

    public Mensaje getParametrosPorModuloAsc(Integer id, String uuidCxn){//JSA01

        return getParametrosDAO().getParametrosPorModuloAsc(id,uuidCxn);
    }

//    public void SaveParametros(List<Parametros> p, String uuidCxn){//JSA01
//        parametrosDAO.SaveParametros(p);
//    }
//
//    public void DeleteParametros(List<Parametros> p, String uuidCxn){//JSA01
//        parametrosDAO.DeleteParametros(p);
//    }
    public Mensaje getParametrosPorModuloYClave(String claveModulo, Long clave, String uuidCxn){//JSA01

        return getParametrosDAO().getParametrosPorModuloYClave(claveModulo, clave,uuidCxn);
    }

    public Mensaje getParametrosPorModuloYClaves(String claveModulo, Object[] clavesParametros, String uuidCxn){

        return getParametrosDAO().getParametrosPorModuloYClaves(claveModulo, clavesParametros,uuidCxn);
    }

    public Mensaje getParametrosYListCrucePorModuloYClaves(String claveModulo, Object[] clavesParametros, String uuidCxn){

        return getParametrosDAO().getParametrosYListCrucePorModuloYClaves(claveModulo, clavesParametros,uuidCxn);
    }

    public Mensaje getParametrosPorModuloYParametro(String nombreModulo, String nombreParametro, String uuidCxn){//JSA01
        return getParametrosDAO().getParametrosPorModuloYParametro(nombreModulo, nombreParametro,uuidCxn);
    }

    public Mensaje saveDeleteParametros(List<Parametros> AgreModif, List<Parametros> eliminados, int rango, String uuidCxn){

        return getParametrosDAO().saveDeleteParametros(AgreModif, eliminados, rango,uuidCxn);
    }

    public Mensaje existeParametro(Long claveParametros, Long idClave, String uuidCxn){

        return getParametrosDAO().existeParametro(claveParametros, idClave,uuidCxn);
    }

    public ParametrosDAO getParametrosDAO() {
        return parametrosDAO;
    }

    public void setParametrosDAO(ParametrosDAO parametrosDAO) {
        this.parametrosDAO = parametrosDAO;
    }

}
