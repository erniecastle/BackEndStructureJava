/**
 * @author: José Ernesto Valenzuela Castillo Fecha de Creación: 20/10/2011
 * Compañía: Exito Software. Descripción del programa: MovimientosNomina para
 * Hibernate
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Armando Fecha:04/08/2012 Descripción:Se cambio la entidad
 * plazasPorEmpleadoMov por plazasPorEmpleado ya que si el usuario despues de
 * guardar movimientos hace cambios en los movimientos de plazas por empleado,
 * los movimientos de nomina no tendrian el renglon mas actual y esto
 * complicaria mas este modulo.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:27/04/2015 Descripción: Se agrego el
 * codigo para obtener el CreditoMovimientos que tenga asignado el movimiento de
 * nomina.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Fecha:23/06/2015 Descripción: Se agrego el
 * codigo para obtener el CreditoMovimientos que tenga asignado el movimiento de
 * nomina.
 * -----------------------------------------------------------------------------
*/
package com.mef.erp.modelo.entidad;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

/**
 *
 * @author Ernesto
 */
@Entity
@Table(name = "MovNomConcep",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "empleado_ID", "tipoNomina_ID",
                "periodosNomina_ID", "tipoCorrida_ID", "concepNomDefi_ID", "numero", "ejercicio", "mes"})})//JSA01
public class MovNomConcep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "tipoNomina_ID", nullable = true, insertable = true, updatable = true)
    private TipoNomina tipoNomina;
    @ManyToOne
    @JoinColumn(name = "tipoCorrida_ID", nullable = true, insertable = true, updatable = true)
    private TipoCorrida tipoCorrida;
    @ManyToOne
    @JoinColumn(name = "periodosNomina_ID", nullable = true, insertable = true, updatable = true)
    private PeriodosNomina periodosNomina;
    @ManyToOne
    @JoinColumn(name = "concepNomDefi_ID", nullable = true, insertable = true, updatable = true)
//    @JoinColumns({
//        @JoinColumn(name = "clave", nullable = true, insertable = true, updatable = true),
//        @JoinColumn(name = "fecha", nullable = true, insertable = true, updatable = true)})
    private ConcepNomDefi concepNomDefi;
    @ManyToOne
    @JoinColumn(name = "centroDeCosto_ID", nullable = true, insertable = true, updatable = true)
    private CentroDeCosto centroDeCosto;
    @ManyToOne
    @JoinColumn(name = "empleado_ID", nullable = true, insertable = true, updatable = true)
    private Empleados empleado;
    @ManyToOne
    @JoinColumn(name = "plazasPorEmpleado_ID", nullable = true, insertable = true, updatable = true)//JSA01
    private PlazasPorEmpleado plazasPorEmpleado;//JSA01
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = true, insertable = true, updatable = true)
    private RazonesSociales razonesSociales;//JEVC01
    @Column(nullable = true)
    private Double resultado;
    private Double calculado;
    @Temporal(TemporalType.DATE)
    private Date fechaIni;
    @Temporal(TemporalType.DATE)
    private Date fechaCierr;
    @Column(length = 255, nullable = false)
    private Integer ordenId;
    @JoinColumn(name = "tipoPantalla", nullable = true, insertable = true, updatable = true)
    private Integer tipoPantalla;
    @JoinColumn(name = "numero", nullable = true, insertable = true, updatable = true)
    private Integer numero;
    @JoinColumn(name = "mes", nullable = true, insertable = true, updatable = true)
    private Integer mes;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "movNomConcep")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<MovNomConceParam> movNomConceParam;
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "movNomConcep")
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    private List<MovNomBaseAfecta> movNomBaseAfecta;
    private int uso;
    @OneToOne
    private FiniqLiquidCncNom finiqLiquidCncNom;
    @JoinColumn(name = "ejercicio", nullable = true, insertable = true, updatable = true)
    private int ejercicio;
    private int numMovParticion;
    @Transient//Para control de movimientos al momento de guardar JEVC
    private boolean isEnBD;
    //private Object[] isEnBdAndBackupID = new Object[]{false, null};
    @ManyToOne()
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinColumn(name = "creditoMovimientos_ID", nullable = true, insertable = true, updatable = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private CreditoMovimientos creditoMovimientos;//JSA02

    public MovNomConcep() {
    }

    public MovNomConcep(Long id) {
        this.id = id;
    }

    public static MovNomConcep copiaMovimiento(MovNomConcep mov) {
        MovNomConcep nuevo = new MovNomConcep();
        if (mov != null) {
            nuevo.setCalculado(mov.getCalculado());
            nuevo.setCentroDeCosto(mov.getCentroDeCosto());
            nuevo.setConcepNomDefi(mov.getConcepNomDefi());
            nuevo.setEjercicio(mov.getEjercicio());
            nuevo.setEmpleado(mov.getEmpleado());
            nuevo.setFechaCierr(mov.getFechaCierr());
            nuevo.setFechaIni(mov.getFechaIni());
            nuevo.setMes(mov.getMes());
            nuevo.setNumero(mov.getNumero());
            nuevo.setOrdenId(mov.getOrdenId());
            nuevo.setPeriodosNomina(mov.getPeriodosNomina());
            nuevo.setPlazasPorEmpleado(mov.getPlazasPorEmpleado());
            nuevo.setRazonesSociales(mov.getRazonesSociales());
            nuevo.setResultado(mov.getResultado());
            nuevo.setTipoCorrida(mov.getTipoCorrida());
            nuevo.setTipoNomina(mov.getTipoNomina());
            nuevo.setTipoPantalla(mov.getTipoPantalla());
            nuevo.setUso(mov.getUso());
            nuevo.setNumMovParticion(mov.getNumMovParticion());
            nuevo.setFiniqLiquidCncNom(mov.getFiniqLiquidCncNom());
            int i;
            List<MovNomBaseAfecta> listBase = new ArrayList<MovNomBaseAfecta>();
            if (mov.getMovNomBaseAfecta() != null) {
                for (i = 0; i < mov.getMovNomBaseAfecta().size(); i++) {
                    listBase.add(MovNomBaseAfecta.copiaMovBaseAfecta(mov.getMovNomBaseAfecta().get(i)));
                    listBase.get(i).setMovNomConcep(nuevo);
                }
            }
            nuevo.setMovNomBaseAfecta(listBase);
            List<MovNomConceParam> listParam = new ArrayList<MovNomConceParam>();
            if (mov.getMovNomConceParam() != null) {
                for (i = 0; i < mov.getMovNomConceParam().size(); i++) {
                    listParam.add(MovNomConceParam.copiaMovBaseAfecta(mov.getMovNomConceParam().get(i)));
                    listParam.get(i).setMovNomConcep(nuevo);
                }
            }
            nuevo.setMovNomConceParam(listParam);
        }
        return nuevo;
    }

    public Double getCalculado() {
        return calculado;
    }

    public void setCalculado(Double calculado) {
        this.calculado = calculado;
    }

    public CentroDeCosto getCentroDeCosto() {
        return centroDeCosto;
    }

    public void setCentroDeCosto(CentroDeCosto centroDeCosto) {
        this.centroDeCosto = centroDeCosto;
    }

    public ConcepNomDefi getConcepNomDefi() {
        return concepNomDefi;
    }

    public void setConcepNomDefi(ConcepNomDefi concepNomDefi) {
        this.concepNomDefi = concepNomDefi;
    }

    public int getEjercicio() {
        return ejercicio;
    }

    public void setEjercicio(int ejercicio) {
        this.ejercicio = ejercicio;
    }

    public Empleados getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleados empleado) {
        this.empleado = empleado;
    }

    public Date getFechaCierr() {
        return fechaCierr;
    }

    public void setFechaCierr(Date fechaCierr) {
        this.fechaCierr = fechaCierr;
    }

    public Date getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(Date fechaIni) {
        this.fechaIni = fechaIni;
    }

    public FiniqLiquidCncNom getFiniqLiquidCncNom() {
        return finiqLiquidCncNom;
    }

    public void setFiniqLiquidCncNom(FiniqLiquidCncNom finiqLiquidCncNom) {
        this.finiqLiquidCncNom = finiqLiquidCncNom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public List<MovNomBaseAfecta> getMovNomBaseAfecta() {
        return movNomBaseAfecta;
    }

    public void setMovNomBaseAfecta(List<MovNomBaseAfecta> movNomBaseAfecta) {
        this.movNomBaseAfecta = movNomBaseAfecta;
    }

    public List<MovNomConceParam> getMovNomConceParam() {
        return movNomConceParam;
    }

    public void setMovNomConceParam(List<MovNomConceParam> movNomConceParam) {
        this.movNomConceParam = movNomConceParam;
    }

    public int getNumMovParticion() {
        return numMovParticion;
    }

    public void setNumMovParticion(int numMovParticion) {
        this.numMovParticion = numMovParticion;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(Integer ordenId) {
        this.ordenId = ordenId;
    }

    public PeriodosNomina getPeriodosNomina() {
        return periodosNomina;
    }

    public void setPeriodosNomina(PeriodosNomina periodosNomina) {
        this.periodosNomina = periodosNomina;
    }

    public PlazasPorEmpleado getPlazasPorEmpleado() {
        return plazasPorEmpleado;
    }

    public void setPlazasPorEmpleado(PlazasPorEmpleado plazasPorEmpleado) {
        this.plazasPorEmpleado = plazasPorEmpleado;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public Double getResultado() {
        return resultado;
    }

    public void setResultado(Double resultado) {
        this.resultado = resultado;
    }

    public TipoCorrida getTipoCorrida() {
        return tipoCorrida;
    }

    public void setTipoCorrida(TipoCorrida tipoCorrida) {
        this.tipoCorrida = tipoCorrida;
    }

    public TipoNomina getTipoNomina() {
        return tipoNomina;
    }

    public void setTipoNomina(TipoNomina tipoNomina) {
        this.tipoNomina = tipoNomina;
    }

    public Integer getTipoPantalla() {
        return tipoPantalla;
    }

    public void setTipoPantalla(Integer tipoPantalla) {
        this.tipoPantalla = tipoPantalla;
    }

    public int getUso() {
        return uso;
    }

    public void setUso(int uso) {
        this.uso = uso;
    }

    public boolean isIsEnBD() {
        return isEnBD;
    }

    public void setIsEnBD(boolean isEnBD) {
        this.isEnBD = isEnBD;
    }

    public CreditoMovimientos getCreditoMovimientos() {
        return creditoMovimientos;
    }

    public void setCreditoMovimientos(CreditoMovimientos creditoMovimientos) {
        this.creditoMovimientos = creditoMovimientos;
    }

}
