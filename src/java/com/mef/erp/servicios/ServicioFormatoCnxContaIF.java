/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.contabilidad.FormatoCnxConta;
import java.util.List;

/**
 *
 * @author Desarrollo 094
 */
public interface ServicioFormatoCnxContaIF {

    public Mensaje agregar(FormatoCnxConta entity, String uuidCxn);

    public Mensaje actualizar(FormatoCnxConta entity, String uuidCxn);

    public Mensaje eliminar(FormatoCnxConta entity, String uuidCxn);

    public Mensaje getFormatoCnxContaAll(String uuidCxn);

    public Mensaje saveDeleteConfiguracionNivelCuenta(FormatoCnxConta entity, List<Long> eliminados, String uuidCxn);

    public Mensaje getFormatoCnxContaClave(String clave, String uuidCxn);
}
