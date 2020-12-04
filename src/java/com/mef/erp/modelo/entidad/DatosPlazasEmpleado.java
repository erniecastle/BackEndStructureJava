/**
 * @author: Victor Fecha de Creación: 19/09/2012 Compañía: Macropro Descripción
 * del programa: Entidad de DatosPlazasEmpleado
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Fecha: 21/01/2013 Descripción:Se cambio a
 * entidad completa, es decir, para que se creara como tabla.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo.entidad;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class DatosPlazasEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String claveEmpleado;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String clavePlazaEmpleado;
    @Temporal(TemporalType.DATE)
    private Date fechaInicial;
    @Temporal(TemporalType.DATE)
    private Date fechaFinal;
    @Temporal(TemporalType.DATE)
    private Date fechaIngreso;
    private Integer horas;
    private Float importe;
    private String descripcionCentroCosto;
    private String descripcionPuesto;
    private String clavePlaza;
    @Temporal(TemporalType.DATE)
    private Date fechaFinUltReingreso;
    private String tipoNominaClave;
    private String tipoNominaDescripcion;
    @Transient
    private PlazasPorEmpleado plazasPorEmpleado;

    public DatosPlazasEmpleado() {
    }

    public DatosPlazasEmpleado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, Date fechaIngreso,
            int manejaPagosPorHora, int manejoHorasPor, int manejoSalarioDiario) {
        if (plazasPorEmpleadosMov != null) {
            Boolean manejarPagosPorHora = manejaPagosPorHora == 0 ? true : false;
            if (plazasPorEmpleadosMov.getPlazasPorEmpleado() != null) {
                if (plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados() != null) {
                    this.claveEmpleado = plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave();
                    this.nombre = plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getNombre();
                    this.apellidoMaterno = plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getApellidoMaterno();
                    this.apellidoPaterno = plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getApellidoPaterno();
                }
                this.clavePlazaEmpleado = plazasPorEmpleadosMov.getPlazasPorEmpleado().getClave();
                this.fechaFinal = plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal();
            }

            this.fechaInicial = plazasPorEmpleadosMov.getFechaInicial();
            if (plazasPorEmpleadosMov.getHoras() == null) {
                plazasPorEmpleadosMov.setHoras(0);
            }
            this.horas = plazasPorEmpleadosMov.getHoras();
            if (plazasPorEmpleadosMov.getSalarioPor() == 2) {
                this.importe = plazasPorEmpleadosMov.getImporte();

            } else {
                this.importe = plazasPorEmpleadosMov.getPuestos().getSalarioTabular();
            }

//            if (manejarPagosPorHora != null) {
//                if (manejarPagosPorHora & plazasPorEmpleadosMov.getPuestos().getCategoriasPuestos().getPagarPorHoras()) {
//                    if (manejoHorasPor == ManejoHorasPor.HORASNATURALES.getManejoHorasPor()) {
//                        if (manejoSalarioDiario == ManejoSalarioDiario.SEMANAL.getManejoSalarioDiario()) {
//                            this.importe = ((plazasPorEmpleadosMov.getHoras() / 1) * (Float) this.importe);
//                        } else if (manejoSalarioDiario == ManejoSalarioDiario.QUINCENAL.getManejoSalarioDiario()) {
//                            this.importe = ((plazasPorEmpleadosMov.getHoras() / 2) * (Float) this.importe);
//                        } else if (manejoSalarioDiario == ManejoSalarioDiario.MENSUAL.getManejoSalarioDiario()) {
//                            this.importe = ((plazasPorEmpleadosMov.getHoras() / 4) * (Float) this.importe);
//                        }
//                    }
//                } else {
//                    if (manejoSalarioDiario == ManejoSalarioDiario.SEMANAL.getManejoSalarioDiario()) {
//                        this.importe = ((Float) this.importe / 7);
//                    } else if (manejoSalarioDiario == ManejoSalarioDiario.QUINCENAL.getManejoSalarioDiario()) {
//                        this.importe = ((Float) this.importe / 15);
//                    } else if (manejoSalarioDiario == ManejoSalarioDiario.MENSUAL.getManejoSalarioDiario()) {
//                        this.importe = ((Float) this.importe / 30);
//                    }
//                }
//            } else {
//                if (manejoSalarioDiario == ManejoSalarioDiario.SEMANAL.getManejoSalarioDiario()) {
//                    this.importe = ((Float) this.importe / 7);
//                } else if (manejoSalarioDiario == ManejoSalarioDiario.QUINCENAL.getManejoSalarioDiario()) {
//                    this.importe = ((Float) this.importe / 15);
//                } else if (manejoSalarioDiario == ManejoSalarioDiario.MENSUAL.getManejoSalarioDiario()) {
//                    this.importe = ((Float) this.importe / 30);
//                }
//            }

            if (plazasPorEmpleadosMov.getCentroDeCosto() != null) {
                this.descripcionCentroCosto = plazasPorEmpleadosMov.getCentroDeCosto().getDescripcion();
            }
            if (plazasPorEmpleadosMov.getPuestos() != null) {
                this.descripcionPuesto = plazasPorEmpleadosMov.getPuestos().getDescripcion();
            }
            if (plazasPorEmpleadosMov.getPlazas() != null) {
                this.clavePlaza = plazasPorEmpleadosMov.getPlazas().getClave();
            }
            if (plazasPorEmpleadosMov.getTipoNomina() != null) {
                this.tipoNominaDescripcion = plazasPorEmpleadosMov.getTipoNomina().getDescripcion();
                this.tipoNominaClave = plazasPorEmpleadosMov.getTipoNomina().getClave();
            }
        }
        if (fechaIngreso != null) {
            this.fechaIngreso = fechaIngreso;
        }
        this.plazasPorEmpleado = plazasPorEmpleadosMov.getPlazasPorEmpleado();
        //this.fechaFinUltReingreso = 
    }

    public String getClaveEmpleado() {
        return claveEmpleado;
    }

    public void setClaveEmpleado(String claveEmpleado) {
        this.claveEmpleado = claveEmpleado;
    }

    public String getClavePlaza() {
        return clavePlaza;
    }

    public void setClavePlaza(String clavePlaza) {
        this.clavePlaza = clavePlaza;
    }

    public String getClavePlazaEmpleado() {
        return clavePlazaEmpleado;
    }

    public void setClavePlazaEmpleado(String clavePlazaEmpleado) {
        this.clavePlazaEmpleado = clavePlazaEmpleado;
    }

    public String getDescripcionCentroCosto() {
        return descripcionCentroCosto;
    }

    public void setDescripcionCentroCosto(String descripcionCentroCosto) {
        this.descripcionCentroCosto = descripcionCentroCosto;
    }

    public String getDescripcionPuesto() {
        return descripcionPuesto;
    }

    public void setDescripcionPuesto(String descripcionPuesto) {
        this.descripcionPuesto = descripcionPuesto;
    }

    public Date getFechaFinUltReingreso() {
        return fechaFinUltReingreso;
    }

    public void setFechaFinUltReingreso(Date fechaFinUltReingreso) {
        this.fechaFinUltReingreso = fechaFinUltReingreso;
    }

    public Date getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(Date fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Integer getHoras() {
        return horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getImporte() {
        return importe;
    }

    public void setImporte(Float importe) {
        this.importe = importe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoNominaClave() {
        return tipoNominaClave;
    }

    public void setTipoNominaClave(String tipoNominaClave) {
        this.tipoNominaClave = tipoNominaClave;
    }

    public String getTipoNominaDescripcion() {
        return tipoNominaDescripcion;
    }

    public void setTipoNominaDescripcion(String tipoNominaDescripcion) {
        this.tipoNominaDescripcion = tipoNominaDescripcion;
    }

    public PlazasPorEmpleado getPlazasPorEmpleado() {
        return plazasPorEmpleado;
    }

    public void setPlazasPorEmpleado(PlazasPorEmpleado plazasPorEmpleado) {
        this.plazasPorEmpleado = plazasPorEmpleado;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }
}
