/**
 * @author: Daniel Ruelas Fecha de Creación: 27/11/2015 Compañía: Exito Software
 * Descripción del programa: clase FormatoCnxConta para llamados a metodos de
 * HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: Autor Fecha: Descripción:
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.ConfiguracionNivelCuenta;
import com.mef.erp.modelo.entidad.contabilidad.FormatoCnxConta;
import java.util.List;

/**
 *
 * @author Daniel Ruelas
 */
public interface FormatoCnxContaDAOIF extends GenericDAO<FormatoCnxConta, Long> {

    public Mensaje agregar(FormatoCnxConta entity, String uuidCxn);

    public Mensaje actualizar(FormatoCnxConta entity, String uuidCxn);

    public Mensaje eliminar(FormatoCnxConta entity, String uuidCxn);

    public Mensaje getFormatoCnxContaAll(String uuidCxn);

    public Mensaje saveDeleteConfiguracionNivelCuenta(FormatoCnxConta entity, List<Long> eliminados, String uuidCxn);

    public Mensaje getFormatoCnxContaClave(String clave, String uuidCxn);
}
