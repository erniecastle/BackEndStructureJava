/**
 * @author: Victor Lopez Fecha de Creación: 29/08/2011 Compañía: Macropro.
 * Descripción del programa: clase para servicio Horario
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

import com.mef.erp.modelo.HorarioDAO;
import com.mef.erp.modelo.entidad.Horario;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonesSociales;

public class ServicioHorario implements ServicioHorarioIF {

    private HorarioDAO horarioDAO;
    

    public Mensaje agregar(Horario entity, String uuidCxn) {//JSA01
        return horarioDAO.agregar(entity, uuidCxn);
    }

    public Mensaje actualizar(Horario entity, String uuidCxn) {//JSA01
        return horarioDAO.actualizar(entity, uuidCxn);
    }

    public Mensaje eliminar(Horario entity, String uuidCxn) {//JSA01
        return horarioDAO.eliminar(entity, uuidCxn);
    }

    public Mensaje getHorarioAll(String uuidCxn) {//JSA01
        return horarioDAO.getHorarioAll(uuidCxn);
    }

    public Mensaje getHorarioPorClave(String clave, String uuidCxn) {//AAP01//JSA01
        return horarioDAO.getHorarioPorClave(clave, uuidCxn);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, String uuidCxn) {//JSA01
        return horarioDAO.consultaPorFiltrosHorario(query, campos, valores, uuidCxn);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        return horarioDAO.consultaPorRangos(inicio, rango, uuidCxn);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        return horarioDAO.existeDato(campo, valor, uuidCxn);
    }

    public Mensaje getHorariosPorRazonSocial(RazonesSociales razonSocial, String uuidCxn) {//JSA01
        return horarioDAO.getHorariosPorRazonSocial(razonSocial, uuidCxn);
    }

    public HorarioDAO getHorarioDAO() {
        return horarioDAO;
    }

    public void setHorarioDAO(HorarioDAO horarioDAO) {
        this.horarioDAO = horarioDAO;
    }
}
