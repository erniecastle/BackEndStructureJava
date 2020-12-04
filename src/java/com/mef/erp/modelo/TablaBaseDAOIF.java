/**
 * @author: Victor Fecha de Creaci�n: 06/06/2011 Compa��a: Macropro Descripci�n
 * del programa: clase TablaBase para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:09/05/2013 Descripci�n:se
 * cambio el dato que regresa getTablaBasePorTipoTabla
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaBase;
import com.mef.erp.modelo.entidad.TipoTabla;

public interface TablaBaseDAOIF extends GenericDAO<TablaBase, Integer> {

    public Mensaje getTablaBaseAll(String uuidCxnMaestra);

    public Mensaje getTablaBasePorClave(String clave, String uuidCxnMaestra);

    public Mensaje agregar(TablaBase entity, String uuidCxnMaestra);

    public Mensaje actualizar(TablaBase entity, String uuidCxnMaestra);

    public Mensaje eliminar(TablaBase tablaPersonalizada, String uuidCxnMaestra);

    public Mensaje getTablaBasePorTipoTabla(TipoTabla tipoTabla, String uuidCxnMaestra);//JSA01

    Mensaje getTablaBaseSistema(String uuidCxnMaestra);
}
