/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 16/08/2011 Compañía:
 * Exito Software. Descripción del programa: Entidad de Creditos para catalogo
 * definir creditos
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
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "CreditoAhorro",
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"razonesSociales_ID", "tipoConfiguracion", "clave"})})//JSA01
public class CreditoAhorro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false/*, columnDefinition = "Id auto_incremento"*/)
    private Long id;
    @Column(length = 30, nullable = false/*, columnDefinition = "Clave de Configuracion Credito_Ahorro"*/)
    private String clave;
    @Column(length = 1, nullable = false/*, columnDefinition = "Clave de Configuracion Credito_Ahorro"*/)
    private String tipoConfiguracion;
    @Column(length = 255, nullable = false/*, columnDefinition = "Descripcion Configuracion Credito_Ahorro"*/)
    private String descripcion;
    @Column(length = 50, nullable = false/*, columnDefinition = "Descripcion abreviada Configuracion Credito_Ahorro"*/)
    private String descripcionAbrev;
    @Column(nullable = false/*, columnDefinition = "Bandera asignacion automatica de numero Credito_Ahorro"*/)
    private boolean asignaAutoNumCredAho;
    @Column(length = 30, nullable = false/*, columnDefinition = "Longitud de Credito_Ahorro"*/)
    private String longitudNumCredAho;
    @Column(length = 30, nullable = false/*, columnDefinition = "Mascara para Credito_Ahorro"*/)
    private String mascaraNumCredAho;
    @Column(nullable = false/*, columnDefinition = "Bandera inicio descuento por periodo o por fecha"*/)//JEVC01
    private boolean inicioDescuento;
    @Column(nullable = false/*, columnDefinition = "Bandera Definir Numero Empleado"*/)
    private boolean definirNumEmp;
    @Column(length = 3, nullable = false/*, columnDefinition = "Longitud Numero Empleado"*/)
    private String longitudNumEmp;
    @Column(nullable = false/*, columnDefinition = "Seleccion modo descuento"*/)
    private byte modoDescuento;
    @Column(nullable = false/*, columnDefinition = "Bandera porcentaje"*/)
    private boolean porcentaje;
    @Column(nullable = false/*, columnDefinition = "Bandera VSGM"*/)
    private boolean vsmg;
    //Modo Captura Descuento: Por periodo, Mensual, Bimestral
    private Integer modoCapturaDescuento;
    //cantidad decimales en modo descuento
    private Integer numDecimalesDescuento;
    //////////// NUEVOS CAMPOS
    //Modo Captura Descuento: Por periodo, Mensual, Bimestral
    private Integer modoCapturaDescuentoVSMG;
    //cantidad decimales en modo descuento
    private Integer numDecimalesDescuentoVSMG;
    //Modo Captura Descuento: Por periodo, Mensual, Bimestral
    private Integer modoCapturaDescuentoPorc;
    //cantidad decimales en modo descuento
    private Integer numDecimalesDescuentoPorc;
    ////////////////
    @Column(nullable = false/*, columnDefinition = "Bandera cuota fija"*/)
    private boolean cuotaFija;
    @Column(nullable = false/*, columnDefinition = "Bandera Descontar Proporcional a Dias Periodo"*/)
    private boolean descPropDiasPer;
    @Column(nullable = false/*, columnDefinition = "Bandera solicitar fecha vencimiento"*/)
    private boolean solicitarFecVen;
    @Column(nullable = false/*, columnDefinition = "es Fondo de Ahorro?"*/)
    private boolean fondoAhorro;
    @Column(nullable = false/*, columnDefinition = "Seleccion agregar Credito_Ahorro en ingresos empleado"*/)
    private byte modoAgregarCredAhoIngEmp;
    @Column(nullable = false/*, columnDefinition = "Seleccion Descontar Credito_Ahorro en finiquitos"*/)
    private byte modoDescontarCredAhoFini;
    @Column(nullable = false/*, columnDefinition = "Seleccion Descontar Credito_Ahorro en liquidaciones"*/)
    private byte modoDescontarCredAhoLiqu;
    @Column(length = 10, nullable = false/*, columnDefinition = "Tasa de interes mensual"*/)
    private String tasaIntMens;
    @Column(nullable = false/*, columnDefinition = "Corte Mes_Dia"*/)
    @Temporal(TemporalType.DATE)
    private Date corteMesDia;
    @ManyToOne
    @JoinColumn(name = "razonesSociales_ID", nullable = false, insertable = true, updatable = true)//JSA01
    private RazonesSociales razonesSociales;//JSA01
    @ManyToOne
    @JoinColumn(name = "concepNomiDefin_id", nullable = true, updatable = true)
    private ConcepNomDefi concepNomiDefin;
    @ManyToOne
    @JoinColumn(name = "cNDInteresMensual_id", nullable = true, updatable = true)
    private ConcepNomDefi cNDInteresMensual;
    @ManyToOne
    @JoinColumn(name = "cNDescuento_id", nullable = true, updatable = true)
    private ConcepNomDefi cNDescuento;
    @Column(nullable = true)
    private String conceptoDcto;
    @Column(nullable = true)
    private Integer periodicidadDescuento;
    @Column(nullable = true)
    private Integer cuandoDescontar;
    @Column(nullable = true)
    private Byte modoManejoDescuento;
    @Column(nullable = true)
    private Double importeDescuento;
//Para activar el manejo del descuento en los creditos o ahorros
    private Boolean activarManejoDescuento;
    private Byte factorProporcional;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoConfiguracion() {
        return tipoConfiguracion;
    }

    public void setTipoConfiguracion(String tipoConfiguracion) {
        this.tipoConfiguracion = tipoConfiguracion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcionAbrev() {
        return descripcionAbrev;
    }

    public void setDescripcionAbrev(String descripcionAbrev) {
        this.descripcionAbrev = descripcionAbrev;
    }

    public boolean isAsignaAutoNumCredAho() {
        return asignaAutoNumCredAho;
    }

    public void setAsignaAutoNumCredAho(boolean asignaAutoNumCredAho) {
        this.asignaAutoNumCredAho = asignaAutoNumCredAho;
    }

    public String getLongitudNumCredAho() {
        return longitudNumCredAho;
    }

    public void setLongitudNumCredAho(String longitudNumCredAho) {
        this.longitudNumCredAho = longitudNumCredAho;
    }

    public String getMascaraNumCredAho() {
        return mascaraNumCredAho;
    }

    public void setMascaraNumCredAho(String mascaraNumCredAho) {
        this.mascaraNumCredAho = mascaraNumCredAho;
    }

    public boolean isInicioDescuento() {
        return inicioDescuento;
    }

    public void setInicioDescuento(boolean inicioDescuento) {
        this.inicioDescuento = inicioDescuento;
    }

    public boolean isDefinirNumEmp() {
        return definirNumEmp;
    }

    public void setDefinirNumEmp(boolean definirNumEmp) {
        this.definirNumEmp = definirNumEmp;
    }

    public String getLongitudNumEmp() {
        return longitudNumEmp;
    }

    public void setLongitudNumEmp(String longitudNumEmp) {
        this.longitudNumEmp = longitudNumEmp;
    }

    public byte getModoDescuento() {
        return modoDescuento;
    }

    public void setModoDescuento(byte modoDescuento) {
        this.modoDescuento = modoDescuento;
    }

    public boolean isPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(boolean porcentaje) {
        this.porcentaje = porcentaje;
    }

    public boolean isVsmg() {
        return vsmg;
    }

    public void setVsmg(boolean vsmg) {
        this.vsmg = vsmg;
    }

    public boolean isCuotaFija() {
        return cuotaFija;
    }

    public void setCuotaFija(boolean cuotaFija) {
        this.cuotaFija = cuotaFija;
    }

    public boolean isDescPropDiasPer() {
        return descPropDiasPer;
    }

    public void setDescPropDiasPer(boolean descPropDiasPer) {
        this.descPropDiasPer = descPropDiasPer;
    }

    public boolean isSolicitarFecVen() {
        return solicitarFecVen;
    }

    public void setSolicitarFecVen(boolean solicitarFecVen) {
        this.solicitarFecVen = solicitarFecVen;
    }

    public boolean isFondoAhorro() {
        return fondoAhorro;
    }

    public void setFondoAhorro(boolean fondoAhorro) {
        this.fondoAhorro = fondoAhorro;
    }

    public byte getModoAgregarCredAhoIngEmp() {
        return modoAgregarCredAhoIngEmp;
    }

    public void setModoAgregarCredAhoIngEmp(byte modoAgregarCredAhoIngEmp) {
        this.modoAgregarCredAhoIngEmp = modoAgregarCredAhoIngEmp;
    }

    public byte getModoDescontarCredAhoFini() {
        return modoDescontarCredAhoFini;
    }

    public void setModoDescontarCredAhoFini(byte modoDescontarCredAhoFini) {
        this.modoDescontarCredAhoFini = modoDescontarCredAhoFini;
    }

    public byte getModoDescontarCredAhoLiqu() {
        return modoDescontarCredAhoLiqu;
    }

    public void setModoDescontarCredAhoLiqu(byte modoDescontarCredAhoLiqu) {
        this.modoDescontarCredAhoLiqu = modoDescontarCredAhoLiqu;
    }

    public String getTasaIntMens() {
        return tasaIntMens;
    }

    public void setTasaIntMens(String tasaIntMens) {
        this.tasaIntMens = tasaIntMens;
    }

    public Date getCorteMesDia() {
        return corteMesDia;
    }

    public void setCorteMesDia(Date corteMesDia) {
        this.corteMesDia = corteMesDia;
    }

    public RazonesSociales getRazonesSociales() {
        return razonesSociales;
    }

    public void setRazonesSociales(RazonesSociales razonesSociales) {
        this.razonesSociales = razonesSociales;
    }

    public ConcepNomDefi getConcepNomiDefin() {
        return concepNomiDefin;
    }

    public void setConcepNomiDefin(ConcepNomDefi concepNomiDefin) {
        this.concepNomiDefin = concepNomiDefin;
    }

    public ConcepNomDefi getcNDInteresMensual() {
        return cNDInteresMensual;
    }

    public void setcNDInteresMensual(ConcepNomDefi cNDInteresMensual) {
        this.cNDInteresMensual = cNDInteresMensual;
    }

    public ConcepNomDefi getcNDescuento() {
        return cNDescuento;
    }

    public void setcNDescuento(ConcepNomDefi cNDescuento) {
        this.cNDescuento = cNDescuento;
    }

    public String getConceptoDcto() {
        return conceptoDcto;
    }

    public void setConceptoDcto(String conceptoDcto) {
        this.conceptoDcto = conceptoDcto;
    }

    public Integer getPeriodicidadDescuento() {
        return periodicidadDescuento;
    }

    public void setPeriodicidadDescuento(Integer periodicidadDescuento) {
        this.periodicidadDescuento = periodicidadDescuento;
    }

    public Integer getCuandoDescontar() {
        return cuandoDescontar;
    }

    public void setCuandoDescontar(Integer cuandoDescontar) {
        this.cuandoDescontar = cuandoDescontar;
    }

    public Byte getModoManejoDescuento() {
        return modoManejoDescuento;
    }

    public void setModoManejoDescuento(Byte modoManejoDescuento) {
        this.modoManejoDescuento = modoManejoDescuento;
    }

    public Double getImporteDescuento() {
        return importeDescuento;
    }

    public void setImporteDescuento(Double importeDescuento) {
        this.importeDescuento = importeDescuento;
    }

    public Integer getModoCapturaDescuento() {
        return modoCapturaDescuento;
    }

    public void setModoCapturaDescuento(Integer modoCapturaDescuento) {
        this.modoCapturaDescuento = modoCapturaDescuento;
    }

    public Integer getNumDecimalesDescuento() {
        return numDecimalesDescuento;
    }

    public void setNumDecimalesDescuento(Integer numDecimalesDescuento) {
        this.numDecimalesDescuento = numDecimalesDescuento;
    }

    public Boolean isActivarManejoDescuento() {
        return activarManejoDescuento;
    }

    public void setActivarManejoDescuento(Boolean activarManejoDescuento) {
        this.activarManejoDescuento = activarManejoDescuento;
    }

    public Integer getModoCapturaDescuentoVSMG() {
        return modoCapturaDescuentoVSMG;
    }

    public void setModoCapturaDescuentoVSMG(Integer modoCapturaDescuentoVSMG) {
        this.modoCapturaDescuentoVSMG = modoCapturaDescuentoVSMG;
    }

    public Integer getNumDecimalesDescuentoVSMG() {
        return numDecimalesDescuentoVSMG;
    }

    public void setNumDecimalesDescuentoVSMG(Integer numDecimalesDescuentoVSMG) {
        this.numDecimalesDescuentoVSMG = numDecimalesDescuentoVSMG;
    }

    public Integer getModoCapturaDescuentoPorc() {
        return modoCapturaDescuentoPorc;
    }

    public void setModoCapturaDescuentoPorc(Integer modoCapturaDescuentoPorc) {
        this.modoCapturaDescuentoPorc = modoCapturaDescuentoPorc;
    }

    public Integer getNumDecimalesDescuentoPorc() {
        return numDecimalesDescuentoPorc;
    }

    public void setNumDecimalesDescuentoPorc(Integer numDecimalesDescuentoPorc) {
        this.numDecimalesDescuentoPorc = numDecimalesDescuentoPorc;
    }

    public Byte getFactorProporcional() {
        return factorProporcional;
    }

    public void setFactorProporcional(Byte factorProporcional) {
        this.factorProporcional = factorProporcional;
    }

}
