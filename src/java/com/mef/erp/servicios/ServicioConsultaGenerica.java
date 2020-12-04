/**
 * @author: Victor Fecha de Creación: 15/03/2011 Compañía: Macropro. Descripción
 * del programa: clase SERVICIO ConsultaGenerica, para llamados a metodos de
 * objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: AAP01 Autor: Abraham Daniel Arjona Peraza Fecha: 25/08/2011
 * Descripción: Se agregó metodo consultaPorRangosFiltro() para realizar la
 * consulta general con filtros y se modificó metodo consultaPorRangos() para
 * que dentro de este metodo se mande llamar al nuevo método
 * -----------------------------------------------------------------------------
 * Clave: AAP02 Autor: Abraham Daniel Arjona Peraza Fecha: 25/08/2011
 * Descripción: Se agregó metodo getDataAllFiltro() para realizar la consulta
 * general con filtros y se modificó metodo getDataAll() para que dentro de este
 * metodo se mande llamar al nuevo método
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:VIC01 Autor:Victor Lopez Fecha:28/11/2011 Descripción:Se agregaron
 * metodos obtenerClaveStringMax y obtenerClaveNuericaMax
 * -----------------------------------------------------------------------------
 * Clave: JEVC Autor: José Ernesto Valenzuela Castillo Fecha: 22/12/2011
 * Descripción: Se Agrego metodo getFechaSistema
 * -----------------------------------------------------------------------------
 * Clave: VIC02 Autor: Victor Lopez Fecha: 19/06/2012 Descripción: Se Agrego
 * metodo consultaPorRangosOrden, consultaAllOrden
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConsultaGenericaDAO;
import com.mef.erp.modelo.entidad.CampoOrden;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;

public class ServicioConsultaGenerica implements ServicioConsultaGenericaIF {

    private ConsultaGenericaDAO genericaDAO;

    public Mensaje getDataAll(String tabla, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return genericaDAO.getDataAll(tabla, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje getDataAllFiltro(String tabla, String[] campos, Object[] valores, String uuidCxn, String uuidCxnMaestra) {//AAP02//JSA01
        return genericaDAO.getDataAllFiltro(tabla, campos, valores, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return genericaDAO.consultaPorFiltrosGenerico(query, campos, valores, inicio, rango, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(String tabla, Integer inicio, Integer rango, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return genericaDAO.consultaPorRangos(tabla, inicio, rango, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangosFiltro(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra) {//AAP01//JSA01
        return genericaDAO.consultaPorRangosFiltro(tabla, inicio, rango, camposWhere, valoresWhere, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangosFiltros(String tabla, Integer inicio, Integer rango, String[] campos, Object[] valores, String queryAntesDeFrom, String queryOrden, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.consultaPorRangosFiltros(tabla, inicio, rango, campos, valores, queryAntesDeFrom, queryOrden, campoOrden, uuidCxn,  uuidCxnMaestra);
    }

    public Mensaje existeDato(String tabla, String campo, Object valor, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return genericaDAO.existeDatoGenerico(tabla, campo, valor, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje existeDatoList(String[] tabla, String[] campo, Object[] valor, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return genericaDAO.existeDatoList(tabla, campo, valor, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje existeValoresEntidad(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.existeValoresEntidad(tabla, campo, valores, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje existeClave(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return genericaDAO.existeClaveGenerico(tabla, campo, valores, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje existenClaves(String tabla, String[] campo, Object[] valores, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return genericaDAO.existenClavesGenerico(tabla, campo, valores, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje existenClavesConOrden(String tabla, String[] campos, Object[] valores, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.existenClavesConOrden(tabla, campos, valores, campoOrden, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje selectExisteClave(String tabla, String[] campo, Object[] valores, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.selectExisteClave(tabla, campo, valores, queryAntesDeFrom, uuidCxn, uuidCxnMaestra);//JEVC
    }

//    public Object getObject(String tabla, String campo, Object valor, String uuidCxn, String uuidCxnMaestra) {//JSA01
//        return genericaDAO.getObject(tabla, campo, valor, uuidCxn,  uuidCxnMaestra);
//    }
    public Mensaje obtenerClaveStringMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra) {//VIC01
        return genericaDAO.obtenerClaveStringMax(tabla, campo, camposWhere, valoresCamposWhere, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje obtenerClaveNumericaMax(String tabla, String campo, String[] camposWhere, Object[] valoresCamposWhere, String uuidCxn, String uuidCxnMaestra) {//VIC01
        return genericaDAO.obtenerClaveNumericaMax(tabla, campo, camposWhere, valoresCamposWhere, uuidCxn, uuidCxnMaestra);
    }

    public Date getFechaSistema() {
        return genericaDAO.getFechaSistema();
    }

    public Mensaje consultaPorRangosConOrdenado(String tabla, Integer inicio, Integer rango, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.consultaPorRangosConOrdenado(tabla, inicio, rango, campoOrden, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaAllConOrden(String tabla, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.consultaAllConOrdenado(tabla, camposWhere, valoresWhere, campoOrden, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaAllConOrdenado(String tabla, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.consultaAllConOrdenado(tabla, campoOrden, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangoConFiltroYOrdenado(String tabla, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, CampoOrden campoOrden, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.consultaPorRangoConFiltroYOrdenado(tabla, inicio, rango, camposWhere, valoresWhere, campoOrden, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje getObject(String tabla, String campo, Object valor, String[] camposFiltro, Object[] valoresFiltro, String queryAntesDeFrom, String uuidCxn, String uuidCxnMaestra) {
        return genericaDAO.getObject(tabla, campo, valor, camposFiltro, valoresFiltro, queryAntesDeFrom, uuidCxn, uuidCxnMaestra);
    }

    public ConsultaGenericaDAO getConsultaGenericaDAO() {
        return genericaDAO;
    }

    public void setConsultaGenericaDAO(ConsultaGenericaDAO genericaDAO) {
        this.genericaDAO = genericaDAO;
    }

}
