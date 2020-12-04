/**
 * @author: Victor Fecha de Creación: 06/06/2011 Compañía: Macropro Descripción
 * del programa: clase TablaBase para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Usuario;
import java.util.List;

/**
 *
 * @author Jose Armando
 */
public interface HerramientaDAOIF extends GenericDAO<Herramienta, Long> {

    public Mensaje getHerramientaAll(String uuidCxn);

    public Mensaje getHerramientaCompartidas(String uuidCxn);//JSA01

    public Mensaje getHerramientasPrincipales(Usuario usuario, String uuidCxn);//JSA01

    public Mensaje agregar(Herramienta entity, String uuidCxn);//JSA01

    public Mensaje actualizar(Herramienta entity, String uuidCxn);//JSA01

    public Mensaje eliminar(Herramienta entity, String uuidCxn);//JSA01

    public Mensaje SaveHerramientas(List<Herramienta> h, String uuidCxn);//JSA01

    public Mensaje DeleteHerramientas(List<Herramienta> h, String uuidCxn);//JSA01

    public Mensaje getHerramientasPrincipalesCompartidas(Usuario usuario, String uuidCxn);//JSA01
}
