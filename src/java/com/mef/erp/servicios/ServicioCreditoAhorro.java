/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 16/08/2011 Compañía:
 * Exito Software. Descripción del programa: clase para servicio credito
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.CreditoAhorroDAO;
import com.mef.erp.modelo.entidad.CreditoAhorro;
import com.mef.erp.modelo.entidad.Mensaje;

public class ServicioCreditoAhorro implements ServicioCreditoAhorroIF {

    private CreditoAhorroDAO creditoAhorroDAO;

    public Mensaje agregar(CreditoAhorro entity, String uuidCxn) {//JSA01

        return getCreditoAhorroDAO().agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(CreditoAhorro entity, String uuidCxn) {//JSA01

        return getCreditoAhorroDAO().actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(CreditoAhorro entity, String uuidCxn) {//JSA01

        return getCreditoAhorroDAO().eliminar(entity, uuidCxn);
    }

    public Mensaje getCreditoAhorroAll(String claveRazonesSociales, String tipoConfiguracion, String uuidCxn) {//JSA01

        return getCreditoAhorroDAO().getCreditoAhorroAll(claveRazonesSociales, tipoConfiguracion, uuidCxn);
    }

    public Mensaje getCreditoAhorroPorClave(String clave, String claveRazonesSociales, String tipoConfiguracion, String uuidCxn) {//JSA01

        return getCreditoAhorroDAO().getCreditoAhorroPorClave(clave, claveRazonesSociales, tipoConfiguracion, uuidCxn);
    }

    public CreditoAhorroDAO getCreditoAhorroDAO() {
        return creditoAhorroDAO;
    }

    public void setCreditoAhorroDAO(CreditoAhorroDAO creditoAhorroDAO) {
        this.creditoAhorroDAO = creditoAhorroDAO;
    }
}
