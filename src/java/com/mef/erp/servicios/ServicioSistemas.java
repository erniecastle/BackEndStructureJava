package com.mef.erp.servicios;

import com.mef.erp.modelo.SistemasDAO;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Sistemas;
import java.util.List;

public class ServicioSistemas implements ServicioSistemasIF {

    private SistemasDAO sistemasDAO;

    public Mensaje agregar(Sistemas entity, String uuidCxnMaestra) {
        return sistemasDAO.agregar(entity, uuidCxnMaestra);
    }

    public Mensaje actualizar(Sistemas entity, String uuidCxnMaestra) {
        return sistemasDAO.actualizar(entity, uuidCxnMaestra);
    }

    public Mensaje eliminar(Sistemas entity, String uuidCxnMaestra) {
        return sistemasDAO.eliminar(entity, uuidCxnMaestra);
    }

    public Mensaje getSistemasAll(String uuidCxnMaestra) {
        return sistemasDAO.getSistemasAll(uuidCxnMaestra);
    }

    public Mensaje getSistemasPorClave(String clave, String uuidCxnMaestra) {
        return sistemasDAO.getSistemasPorClave(clave, uuidCxnMaestra);
    }

    public Mensaje consultaPorFiltros(String query, Object[] campos, Object[] valores, Integer inicio, Integer rango, String uuidCxnMaestra) {
        return sistemasDAO.consultaPorFiltrosSistemas(query, campos, valores, inicio, rango, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxnMaestra) {
        return sistemasDAO.consultaPorRangos(inicio, rango, uuidCxnMaestra);
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxnMaestra) {
        return sistemasDAO.existeDato(campo, valor, uuidCxnMaestra);
    }

    public Mensaje saveDeleteSistemas(List<Sistemas> entitysCambios, Object[] clavesDelete, int rango, String uuidCxnMaestra) {
        return sistemasDAO.saveDeleteSistemas(entitysCambios, clavesDelete, rango, uuidCxnMaestra);
    }

    public SistemasDAO getSistemasDAO() {
        return sistemasDAO;
    }

    public void setSistemasDAO(SistemasDAO sistemasDAO) {
        this.sistemasDAO = sistemasDAO;
    }
}
