/**
 * @author: Victor Lopez Fecha de Creación: 27/09/2011 Compañía: MacroPro.
 * Descripción del programa: clase SERVICIO Tabla base nomina, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:29/08/2012 Descripción:Se agrego el
 * metodo getConceptoDeNominaDefinicionPorClaves para cuando se ocupe regresar
 * los conceptos actuales de algunas claves.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.ConceptoDeNominaDefinicionDAO;
import com.mef.erp.modelo.entidad.BaseAfecConcepNom;
import com.mef.erp.modelo.entidad.ConcepNomDefi;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

public class ServicioConceptoDeNominaDefinicion implements ServicioConceptoDeNominaDefinicionIF {
    
    private ConceptoDeNominaDefinicionDAO conceptoDeNominaDefinicionDAO;
    
    public Mensaje agregar(ConcepNomDefi entity, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.agregar(entity, uuidCxn);
    }
    
    public Mensaje actualizar(ConcepNomDefi entity, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.actualizar(entity, uuidCxn);
    }
    
    public Mensaje eliminar(ConcepNomDefi entity, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.eliminar(entity, uuidCxn);
    }
    
    public Mensaje getConceptoDeNominaDefinicionAll(String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.getConceptoDeNominaDefinicionAll(uuidCxn);
    }
    
    public Mensaje getConceptoDeNominaDefinicionPorClave(String clave, String claveTipoCorrida, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.getConceptoDeNominaDefinicionPorClave(clave, claveTipoCorrida, uuidCxn);
    }
    
    public Mensaje getConceptoDeNominaDefinicionPorTipoCorrida(String claveTipoCorrida, String uuidCxn) {
        return conceptoDeNominaDefinicionDAO.getConceptoDeNominaDefinicionPorTipoCorrida(claveTipoCorrida, uuidCxn);
    }
    
    @Override
    public Mensaje getConceptoDeNominaDefinicionConCuentaContable(String uuidCxn) {
        return conceptoDeNominaDefinicionDAO.getConceptoDeNominaDefinicionConCuentaContable(uuidCxn);
    }
    
    public Mensaje getConceptoDeNominaDefinicionPorClaves(Object[] claves, String uuidCxn) {//JSA02
        return conceptoDeNominaDefinicionDAO.getConceptoDeNominaDefinicionPorClaves(claves, uuidCxn);
    }
    
    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }
    
    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.consultaPorFiltrosConceptos(query, campos, valores, inicio, rango, uuidCxn);
    }
    
    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.existeDato(campo, valor, uuidCxn);
    }
    
    public Mensaje agregaConceptoNominaBaseAfectadas(ConcepNomDefi entity, List<BaseAfecConcepNom> eliminadasAfectadaConceptoNominas, String uuidCxn) {//JSA01
        return conceptoDeNominaDefinicionDAO.agregaConceptoNominaBaseAfectadas(entity, eliminadasAfectadaConceptoNominas, uuidCxn);
    }
    
    public Mensaje claveDescripcionConceptos(String uuidCxn) {
        return conceptoDeNominaDefinicionDAO.claveDescripcionConceptos(uuidCxn);
    }
    
    public ConceptoDeNominaDefinicionDAO getConceptoDeNominaDefinicionDAO() {
        return conceptoDeNominaDefinicionDAO;
    }
    
    public void setConceptoDeNominaDefinicionDAO(ConceptoDeNominaDefinicionDAO conceptoDeNominaDefinicionDAO) {
        this.conceptoDeNominaDefinicionDAO = conceptoDeNominaDefinicionDAO;
    }
    
}
