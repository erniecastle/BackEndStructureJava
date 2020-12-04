/**
 * @author: Victor Lopez Fecha de Creación: 05/12/2016 Compañía: Macropro
 * Descripción del programa: Interface para servicio InasistenciaPorHora
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.InasistenciaPorHoraDAO;
import com.mef.erp.modelo.entidad.InasistenciaPorHora;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.Date;
import java.util.List;

public class ServicioInasistenciaPorHora implements ServicioInasistenciaPorHoraIF {

    private InasistenciaPorHoraDAO inasistenciaPorHoraDAO;

    public Mensaje getInasistenciaPorHoraAll(String uuidCxn) {
        return getInasistenciaPorHoraDAO().getInasistenciaPorHoraAll(uuidCxn);
    }

    public Mensaje saveDeleteInasistenciaPorHora(List<InasistenciaPorHora> AgreModif, Object[] clavesDelete, String uuidCxn) {
        return getInasistenciaPorHoraDAO().saveDeleteInasistenciaPorHora(AgreModif, clavesDelete, uuidCxn);
    }

    @Override
    public Mensaje getInasistenciaPorNominaPeriodo(String claveTipoNomina, String claveRazonSocial, Long idPeriodo, String uuidCxn) {
        return getInasistenciaPorHoraDAO().getInasistenciaPorNominaPeriodo(claveTipoNomina, claveRazonSocial, idPeriodo, uuidCxn);
    }

    public InasistenciaPorHoraDAO getInasistenciaPorHoraDAO() {
        return inasistenciaPorHoraDAO;
    }

    public void setInasistenciaPorHoraDAO(InasistenciaPorHoraDAO inasistenciaPorHoraDAO) {
        this.inasistenciaPorHoraDAO = inasistenciaPorHoraDAO;
    }

}
