/**
 * @author: Jose Armando Sanchez Acosta
 * Fecha de Creación: 21/05/2011
 * Compañía: Macropro
 * Descripción del programa: 
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01
 * Autor:Jose Armando   
 * Fecha:19-10-2011
 * Descripción:Se agrego la propiedad unique = true a la clave para que no sea duplicada.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Administrador
 */
@Entity
public class Ventana implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false, unique = true)//JSA01
    private Integer clave;
    @Column(length = 255, nullable = false)
    private String nombre;
    @OneToMany(fetch = FetchType.EAGER)
    private List<Herramienta> Herramientas;
    @ManyToOne
    private Sistemas sistemas;
    private TipoVentana tipoVentana;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Herramienta> getHerramientas() {
        return Herramientas;
    }

    public void setHerramientas(List<Herramienta> Herramientas) {
        this.Herramientas = Herramientas;
    }

    public Sistemas getSistemas() {
        return sistemas;
    }

    public void setSistemas(Sistemas sistemas) {
        this.sistemas = sistemas;
    }

    public TipoVentana getTipoVentana() {
        return tipoVentana;
    }

    public void setTipoVentana(TipoVentana tipoVentana) {
        this.tipoVentana = tipoVentana;
    }
    
}
