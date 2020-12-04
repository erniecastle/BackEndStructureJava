/**
 * @author: Victor Fecha de Creaci?n: 06/06/2011 Compa??a: Macropro Descripci?n
 * del programa: clase TablaBase para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 08-07-2011
 * Descripci?n: Se aplicaron PDM.
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripci?n:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:09/05/2013 Descripci?n:se
 * cambio el dato que regresa getTablaBasePorTipoTabla
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.TablaBase;
import com.mef.erp.modelo.entidad.TipoTabla;

/**
 *
 * @author Jose Armando
 */
public interface ServicioTablaBaseIF {

    /*List<TablaBase> */
    public Mensaje getTablaBaseAll(String uuidCxnMaestra);

    /*TablaBase*/
    public Mensaje getTablaBasePorClave(String clave, String uuidCxnMaestra);

    /*TablaBase*/
    public Mensaje agregar(TablaBase entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje actualizar(TablaBase entity, String uuidCxnMaestra);

    /*boolean*/
    public Mensaje eliminar(TablaBase tablaPersonalizada, String uuidCxnMaestra);

    /*List<TablaBase>*/
    public Mensaje getTablaBasePorTipoTabla(TipoTabla tipoTabla, String uuidCxnMaestra);//JSA03

    /*List<TablaBase>*/
    Mensaje getTablaBaseSistema(String uuidCxnMaestra);
}
