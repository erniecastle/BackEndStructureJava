package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Estudios;
import com.mef.erp.modelo.entidad.Mensaje;
import java.util.List;

/**
 *
 * @author Dayane
 */
public interface EstudiosDAOIF extends GenericDAO<Estudios, String> {

    Mensaje agregar(Estudios entity, String uuidCxn);

    public Mensaje actualizar(Estudios entity, String uuidCxn);

    public Mensaje eliminar(Estudios entity, String uuidCxn);

    Mensaje getEstudioAll(String uuidCxn);

    Mensaje getEstudioPorClave(String clave, String uuidCxn);

    public Mensaje consultaPorFiltrosEstudio(String query, Object[] campos, Object[] valores, String uuidCxn);

    public Mensaje consultaPorRangosEstudio(Integer inicio, Integer rango, String uuidCxn);

    public Mensaje existeDato(String campo, Object valor, String uuidCxn);

    public Mensaje saveDeleteEstudios(List<Estudios> entitysCambios, Object[] clavesDelete, int rango, String uuidCxn);

}
