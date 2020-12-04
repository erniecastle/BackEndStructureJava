/**
 * @author: Daniel Fecha de Creación: --/--/-- Compañía: FineSoft Descripción
 * del programa: clase AsignaTipoReporte para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.AsignaTipoReporte;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TipoReporte;
import java.util.List;

public interface AsignaTipoReporteDAOIF extends GenericDAO<AsignaTipoReporte, Long> {

    public Mensaje agregar(AsignaTipoReporte entity, String uuidCxn);

    public Mensaje actualizar(AsignaTipoReporte entity, String uuidCxn);

    public Mensaje eliminar(AsignaTipoReporte entity, String uuidCxn);

    public Mensaje getAsignaTipoReporteAll(String uuidCxn);

    public Mensaje getAsignaPorTipoReporteDinamico(String claveReporteDinamico, String uuidCxn);

    public Mensaje getAsignaPorTipoReporte(TipoReporte tipoReporte, String uuidCxn);

    Mensaje existeDato(String campo, Object valor, String uuidCxn);

    Mensaje saveDeleteAsignaTipoReporte(List<AsignaTipoReporte> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);
}
