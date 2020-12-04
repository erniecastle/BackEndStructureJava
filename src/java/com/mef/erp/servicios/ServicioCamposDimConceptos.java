/**
 * @author: Daniel Ruelas Fecha de Creación: 21/10/2015 Compañía: Exito Software.
 * Descripción del programa: clase SERVICIO Tabla Campos Dim Conceptos, para llamados a
 * metodos de objeto DAO
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CamposDimConceptosDAO;
import com.mef.erp.modelo.entidad.CamposDimConceptos;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public class ServicioCamposDimConceptos implements ServicioCamposDimConceptosIF {

    private CamposDimConceptosDAO camposDimConceptosDAO;

    @Override
    public Mensaje getCampoDimConceptoAll(String uuidCxn) {
        return camposDimConceptosDAO.getCampoDimConceptoAll(uuidCxn);
    }

    @Override
    public Mensaje guardaryEliminar(List<CamposDimConceptos> ListaGuardar, List<CamposDimConceptos> ListaEliminar, String uuidCxn) {
        return camposDimConceptosDAO.guardaryEliminar(ListaGuardar, ListaEliminar, uuidCxn);
    }

    @Override
    public Mensaje filtradoCampoDimConceptos(Long campoDim, Long Concepto, String uuidCxn) {
        return camposDimConceptosDAO.filtradoCampoDimConceptos(campoDim, Concepto, uuidCxn);
    }

    @Override
    public Mensaje filtradoCampoDim(Long campoDim, String uuidCxn) {
        return camposDimConceptosDAO.filtradoCampoDim(campoDim, uuidCxn);
    }

    @Override
    public Mensaje filtradoConceptos(Long Concepto, String uuidCxn) {
       return camposDimConceptosDAO.filtradoConceptos(Concepto, uuidCxn);
    }

    public CamposDimConceptosDAO getCamposDimConceptosDAO() {
        return camposDimConceptosDAO;
    }

    public void setCamposDimConceptosDAO(CamposDimConceptosDAO camposDimConceptosDAO) {
        this.camposDimConceptosDAO = camposDimConceptosDAO;
    }

}
