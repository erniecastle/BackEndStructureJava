/**
 * @author: José Ernesto Valenzuela Castillo Fecha de Creación: 30/09/2011
 * Compañía: Exito Software. Descripción del programa: Entidad de Turnos para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 02-12-2011
 * Descripción: Se agrego el campo de Razon social ya que esta informacion es
 * por empresa.
 * -----------------------------------------------------------------------------
 * Clave: Autor: Fecha: Descripción:
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto Castillo
 */
@Entity
@Table(name = "Turnos",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "clave"})})
public class Turnos implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;
    @Column(length = 30, nullable = false)
    private String clave;
    @Column(length = 255, nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private int tipoDeTurno;
    /*Definir el tipo de turno por que son 
     - 1.- Horario Fijo.
     - 2.- Horario Variable
     */

    @ManyToOne
    private Jornada Jornada;
    /*Definir el tipo de Jornada laboral por que son 
     -1.- Ninguno.
     -2.-Diurna
     -3.-Nocturna
     -4.-Mixta
     -5.-Por hora
     -6.-Reducida
     -7.-Continuada
     -8.-Partida
     -9.-Por Turnos
     */
    @Column(nullable = false)
    private Integer tipoDeJornadaIMSS;
    /*Definir el tipo de Jornada IMSS por que son 
     -1.- Ninguno.
     -2.- Semana completa.
     -3.- Semana reducida 1 día.
     -4.- Semana reducida 2 días.
     -5.-Semana reducida 3 días.
     -6.-Semana reducida 4 días.
     -7.-Semana reducida 5 días.
     -8.-Menor a 8 horas.
     */
    @Column(nullable = false)
    private Integer diasJornada;
    @Column(nullable = false)
    private float horaJornada;
    @Column(nullable = false)
    private Integer primerDiaSemana;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "turnos")
    @Cascade(org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    private List<TurnosHorariosFijos> listTurnosHorariosFijos;
    private Integer topeHorasDoblesSemanal;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getTipoDeTurno() {
        return tipoDeTurno;
    }

    public void setTipoDeTurno(int tipoDeTurno) {
        this.tipoDeTurno = tipoDeTurno;
    }

    public Jornada getJornada() {
        return Jornada;
    }

    public void setJornada(Jornada Jornada) {
        this.Jornada = Jornada;
    }

    public Integer getTipoDeJornadaIMSS() {
        return tipoDeJornadaIMSS;
    }

    public void setTipoDeJornadaIMSS(Integer tipoDeJornadaIMSS) {
        this.tipoDeJornadaIMSS = tipoDeJornadaIMSS;
    }

    public Integer getDiasJornada() {
        return diasJornada;
    }

    public void setDiasJornada(Integer diasJornada) {
        this.diasJornada = diasJornada;
    }

    public float getHoraJornada() {
        return horaJornada;
    }

    public void setHoraJornada(float horaJornada) {
        this.horaJornada = horaJornada;
    }

    public Integer getPrimerDiaSemana() {
        return primerDiaSemana;
    }

    public void setPrimerDiaSemana(Integer primerDiaSemana) {
        this.primerDiaSemana = primerDiaSemana;
    }

    public List<TurnosHorariosFijos> getListTurnosHorariosFijos() {
        return listTurnosHorariosFijos;
    }

    public void setListTurnosHorariosFijos(List<TurnosHorariosFijos> listTurnosHorariosFijos) {
        this.listTurnosHorariosFijos = listTurnosHorariosFijos;
    }

    public Integer getTopeHorasDoblesSemanal() {
        return topeHorasDoblesSemanal;
    }

    public void setTopeHorasDoblesSemanal(Integer topeHorasDoblesSemanal) {
        this.topeHorasDoblesSemanal = topeHorasDoblesSemanal;
    }

}
