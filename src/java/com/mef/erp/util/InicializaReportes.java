/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.util;

import com.mef.erp.modelo.entidad.Contenedor;
import com.mef.erp.modelo.entidad.DatosConsulta;
import com.mef.erp.modelo.entidad.Herramienta;
import com.mef.erp.modelo.entidad.RazonSocial;
import com.mef.erp.modelo.entidad.ReporteCamposEncabezado;
import com.mef.erp.modelo.entidad.ReporteCamposWhere;
import com.mef.erp.modelo.entidad.ReporteDatosIncluir;
import com.mef.erp.modelo.entidad.ReporteDinamico;
import com.mef.erp.modelo.entidad.ReporteEstilos;
import com.mef.erp.modelo.entidad.ReporteFuenteDatos;
import com.mef.erp.modelo.entidad.ReporteOrdenGrupo;
import com.mef.erp.modelo.entidad.ReporteOtrosDatosEncabezado;
import com.mef.erp.modelo.entidad.ReporteTipoHoja;
import com.mef.erp.modelo.entidad.ReporteTipoOperacion;
import com.mef.erp.modelo.entidad.TipoAcciones;
import com.mef.erp.modelo.entidad.TipoElemento;
import com.mef.erp.modelo.entidad.TipoEncabezado;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class InicializaReportes {

    static String formatNumber = "|1&234|.00|";
    static String formatInteger = "|1&234|.00|";
    static String formatMoneda = "$|1&234|.00|";
    static String formatDate = "dd/MM/yyyy";
    static String fontName = "Tahoma";
    static int fontTamaño = 9;

    public static List<ReporteDinamico> inicializadorReporteDinamicos(List<ReporteFuenteDatos> listReportFuenteDatos, List<RazonSocial> listRazonSocial, List<Herramienta> listHerramienta, List<TipoElemento> listTipoElementos) {
        ReporteDinamico reporteDinamico;
        List<ReporteDinamico> listReporteDinamico = new ArrayList<ReporteDinamico>();
        List<ReporteDatosIncluir> listReporteDatosIncluir;
        ReporteDatosIncluir reporteDatosIncluir;
        List<ReporteOrdenGrupo> listReporteOrdenGrupo;
        ReporteOrdenGrupo reporteOrdenGrupo;
        List<ReporteCamposWhere> listReporteCamposWhere;
        ReporteCamposWhere reporteCamposWhere;
        List<ReporteCamposEncabezado> listReporteCamposEncabezado;
        ReporteCamposEncabezado reporteCamposEncabezado;
        DatosConsulta datosConsulta;

        //<editor-fold defaultstate="collapsed" desc="0001 ACUMULADOS POR CONCEPTOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0001");
        reporteDinamico.setNombre("ACUMULADOS POR CONCEPTOS");
        reporteDinamico.setNombreAbreviado("ACUMULADOS POR CONCEPTOS");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(1);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        Contenedor contenedor = new Contenedor();
        contenedor.setId(401);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("ACUMULADOS POR CONCEPTOS");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(1);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(1L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concep.");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setFormato("###");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("ZFM4_38_descripcionAbreviada");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setNombreBD("FDCE1_31_TotalCantidadPorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalCantidadPorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("1");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setCamposCombinar(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Naturaleza");
        datosConsulta.setNombreBD("ZFM4_47_naturaleza");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("com.mef.erp.modelo.entidad.Naturaleza");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiNaturaleza");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar(null);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteEstiloGrupo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteOrdenGrupo.setReporteEstiloEncabezado(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteOrdenGrupo.setReporteEstiloPie(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setReporteEstiloEncabezado(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 2));
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setReporteEstiloEncabezado(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 2));
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setReporteEstiloEncabezado(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 2));
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0002 ACUMULADOS Y BASES GRAVADAS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0002");
        reporteDinamico.setNombre("ACUMULADOS Y BASES GRAVADAS");
        reporteDinamico.setNombreAbreviado("ACUMULADOS Y BASES GRAVADAS");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(2);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(402);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("ACUMULADOS Y BASES GRAVADAS");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(2);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(2L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concepto");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setFormato("###");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripción");
        datosConsulta.setNombreBD("ZFM4_38_descripcionAbreviada");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        ReporteEstilos reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Base ISR Anual (V655)");
        datosConsulta.setNombreBD("@BaseISRAnual|+|BaseISRGravableAnual|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("BaseISRAnual+BaseISRGravableAnual");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Naturaleza");
        datosConsulta.setNombreBD("ZFM4_47_naturaleza");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("com.mef.erp.modelo.entidad.Naturaleza");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiNaturaleza");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        List<ReporteOtrosDatosEncabezado> listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        ReporteOtrosDatosEncabezado reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        String logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        byte[] data = new byte[logo.length() / 2];
        int i;
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(3);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(2);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("0");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0003 ANALISIS DE PERCEPCIONES Y DEDUCCIONES">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("ANALISIS DE PERCEPCIONES Y DEDUCCIONES");
        reporteDinamico.setNombreAbreviado("ANALISIS DE PERCEP. Y DEDUC.");
        reporteDinamico.setClave("0003");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(3);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(403);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("ANALISIS DE PERCEP. Y DEDUC.");
        contenedor.setOrdenId(3);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(3L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(15);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Depto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Puesto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_19_TotalConceptoGravable");
        datosConsulta.setNombreEtiqueta("TotalConceptoGravable");
        datosConsulta.setNombreMostrar("Perc. Grav.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(18);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(11);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_21_TotalConceptoExento");
        datosConsulta.setNombreEtiqueta("TotalConceptoExento");
        datosConsulta.setNombreMostrar("Perc. Ex.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(19);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(12);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_10_TotalPercepcion");
        datosConsulta.setNombreEtiqueta("TotalPercepcion");
        datosConsulta.setNombreMostrar("Total Percep.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(20);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(13);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_11_TotalDeduccion");
        datosConsulta.setNombreEtiqueta("TotalDeduccion");
        datosConsulta.setNombreMostrar("Total Deduc.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(21);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(14);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_33_NetoAPagar");
        datosConsulta.setNombreEtiqueta("NetoAPagar");
        datosConsulta.setNombreMostrar("Neto a pagar");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(22);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(15);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@ISRNetoAnual|");
        datosConsulta.setNombreEtiqueta("ISRNetoAnual");
        datosConsulta.setNombreMostrar("ISR retenido");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(23);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(16);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM4_35_clave|=|001");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Telefono);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Telefono.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0004 Antiguedad en la empresa">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0004");
        reporteDinamico.setNombre("ANTIGUEDAD EN LA EMPRESA");
        reporteDinamico.setNombreAbreviado("ANTIGUEDAD EN LA EMPRESA");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(4);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(404);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("Antiguedad en la empresa");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(4);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(4L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Emple");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Depto.");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Ingreso");
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("PlazaFechaInicial");
        datosConsulta.setFormato(formatDate);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Antig");
        datosConsulta.setNombreBD("@Empleados_PorcionAntiguedad");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("EmpleadosPorcionAntiguedad");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("DiasAG");
        datosConsulta.setNombreBD("@Antiguedad|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("Antiguedad");
        datosConsulta.setFormato("|1234||");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(1);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();

        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("0");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0005 ANTIGUEDAD POR EMPLEADO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("ANTIGUEDAD POR EMPLEADO");
        reporteDinamico.setNombreAbreviado("ANTIGUEDAD POR EMPLEADO");
        reporteDinamico.setClave("0005");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(5);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(405);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("ANTIGUEDAD POR EMPLEADO");
        contenedor.setOrdenId(5);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(5L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Depto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Puesto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-AAA");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(11);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setNombreEtiqueta("PlazaFechaInicial");
        datosConsulta.setNombreMostrar("Fecha ingreso");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@Empleados_AntiguedadExacta");
        datosConsulta.setNombreEtiqueta("EmpleadosAntiguedadExacta");
        datosConsulta.setNombreMostrar("Antiguedad");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">          
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteCamposEncabezado.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0006 ANTIGÜEDAD Y PRESTACIONES???">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("ANTIGÜEDAD Y PRESTACIONES???");
        reporteDinamico.setNombreAbreviado("ANTIGÜEDAD Y PRESTACIONES???");
        reporteDinamico.setClave("0006");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(1);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(6);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(406);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("ANTIGÜEDAD Y PRESTACIONES");
        contenedor.setOrdenId(6);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(6L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_4_nombre");
        datosConsulta.setNombreEtiqueta("EmpleadosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Depto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Puesto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(15);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_45_fechaPrestaciones");
        datosConsulta.setNombreEtiqueta("PlazasPorEmpleadoFechaPrestaciones");
        datosConsulta.setNombreMostrar("Fecha Alta");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Agui Prop???");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(22);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(22);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Vacac Prop???");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(23);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(23);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@Empleados_AntiguedadExacta");
        datosConsulta.setNombreEtiqueta("EmpleadosAntiguedadExacta");
        datosConsulta.setNombreMostrar("Antig");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@FactorDiasAguinaldo|");
        datosConsulta.setNombreEtiqueta("FactorDiasAguinaldo");
        datosConsulta.setNombreMostrar("Dias Agui");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(19);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(19);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("@Empleados_DiasVacacionesPendientes");
        datosConsulta.setNombreEtiqueta("EmpleadosDiasVacacionesPendientes");
        datosConsulta.setNombreMostrar("Vaca. x Disfr.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(21);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(21);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@FactorPrimaVacacional|");
        datosConsulta.setNombreEtiqueta("FactorPrimaVacacional");
        datosConsulta.setNombreMostrar("% Prima");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(24);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(24);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("@Empleados_PorcionDias");
        datosConsulta.setNombreEtiqueta("EmpleadosPorcionDias");
        datosConsulta.setNombreMostrar("Dias año");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(10);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(10);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">           
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">          
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0007 AUXILIAR DE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("AUXILIAR DE NOMINA");
        reporteDinamico.setNombreAbreviado("AUXILIAR DE NOMINA");
        reporteDinamico.setClave("0007");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(5);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(7);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(407);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("AUXILIAR DE NOMINA");
        contenedor.setOrdenId(7);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(7L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("FM4_17_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Concepto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_13_Percepcion");
        datosConsulta.setNombreEtiqueta("Percepcion");
        datosConsulta.setNombreMostrar("Percepciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_14_Deduccion");
        datosConsulta.setNombreEtiqueta("Deduccion");
        datosConsulta.setNombreMostrar("Deducciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FM8_24_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar(" ");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FM8_29_ApellidosNombre");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0008 AUXILIAR POR CONCEPTOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0008");
        reporteDinamico.setNombre("AUXILIAR POR CONCEPTOS");
        reporteDinamico.setNombreAbreviado("AUXILIAR POR CONCEPTOS");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(8);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(408);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("AUXILIAR POR CONCEPTOS");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("A");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(8);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(8L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Periodo");
        datosConsulta.setNombreBD("ZFM3_7_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("PeriodosNominaClave");
        datosConsulta.setFormato("###");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fecha Ini");
        datosConsulta.setNombreBD("ZFM1_30_fechaIni");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("MovNomConcepFechaIni");
        datosConsulta.setFormato(formatDate);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fecha Final");
        datosConsulta.setNombreBD("ZFM1_31_fechaCierr");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("MovNomConcepFechaCierr");
        datosConsulta.setFormato(formatDate);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setNombreBD("ZFM1_29_resultado");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Double");
        datosConsulta.setNombreEtiqueta("MovNomConcepResultado");
        datosConsulta.setFormato(formatNumber);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);

        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);

        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDatosIncluir = new ReporteDatosIncluir();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setNombreBD("ZFM5_49_valor");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("MovNomConceParamValor");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposWhere">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM4_35_clave|=|001");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        //</editor-fold>
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(3);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setFila(4);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0009 BALANCE DE PERSONAL">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0009");
        reporteDinamico.setNombre("BALANCE DE PERSONAL");
        reporteDinamico.setNombreAbreviado("BALANCE DE PERSONAL");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(9);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(409);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("BALANCE DE PERSONAL");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("B");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(9);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(9L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.CANTIDAD);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar("FDP73_327_descripcion");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(3);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0010 DATOS GENERALES DE EMPLEADO???">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("DATOS GENERALES DE EMPLEADO???");
        reporteDinamico.setNombreAbreviado("DATOS GENERALES DE EMPLEADO");
        reporteDinamico.setClave("0010");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(10);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(410);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("D");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("DATOS GENERALES DE EMPLEADO");
        contenedor.setOrdenId(10);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(10L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_4_nombre");
        datosConsulta.setNombreEtiqueta("EmpleadosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_10_domicilio");
        datosConsulta.setNombreEtiqueta("EmpleadosDomicilio");
        datosConsulta.setNombreMostrar("Domicilio");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_12_numeroExt");
        datosConsulta.setNombreEtiqueta("EmpleadosNumeroExt");
        datosConsulta.setNombreMostrar("No.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_11_colonia");
        datosConsulta.setNombreEtiqueta("EmpleadosColonia");
        datosConsulta.setNombreMostrar(" Colonia");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE3_13_clave");
        datosConsulta.setNombreEtiqueta("CpClave");
        datosConsulta.setNombreMostrar("CP");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE4_16_clave");
        datosConsulta.setNombreEtiqueta("CiudadesClave");
        datosConsulta.setNombreMostrar("#Hijos???");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FDE2_39_estadoCivil");
        datosConsulta.setNombreEtiqueta("EmpleadosEstadoCivil");
        datosConsulta.setNombreMostrar("Edo Civl");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 1)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 1)));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("(###)###-####");
        datosConsulta.setNombreBD("FDE2_11_telefono");
        datosConsulta.setNombreEtiqueta("EmpleadosTelefono");
        datosConsulta.setNombreMostrar("Tel");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0011 DIAS Y BASES DE CALCULO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0011");
        reporteDinamico.setNombre("DIAS Y BASES DE CALCULO");
        reporteDinamico.setNombreAbreviado("DIAS Y BASES DE CALCULO");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(11);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(411);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("DIAS Y BASES DE CALCULO");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("D");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(11);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(11L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Dep");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Pue");
        datosConsulta.setNombreBD("FDP80_356_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("PuestosClave");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Cot.");
        datosConsulta.setNombreBD("@DiasCotizados|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("DiasCotizados");
        datosConsulta.setFormato(formatInteger);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(3);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Lab.");
        datosConsulta.setNombreBD("@DiasLaborados|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("DiasLaborados");
        datosConsulta.setFormato("|1234||");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(3);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fal.");
        datosConsulta.setNombreBD("@TotalFaltas|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalFaltas");
        datosConsulta.setFormato(formatInteger);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(3);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Inc.");
        datosConsulta.setNombreBD("@TotalIncapacidades|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalIncapacidades");
        datosConsulta.setFormato("|1234||");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(3);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Base enf.");
        datosConsulta.setNombreBD("@HorasExtrasDobles|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("HorasExtrasDobles");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Base Inv.");
        datosConsulta.setNombreBD("@BaseEnfMaternidad|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("BaseEnfMaternidad");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(9);
        reporteDatosIncluir.setColumna(9);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Base SAR");
        datosConsulta.setNombreBD("@BaseSeguroInvalidez|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("BaseSeguroInvalidez");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(10);
        reporteDatosIncluir.setColumna(10);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Base INFO.");
        datosConsulta.setNombreBD("@BaseRTGuarderia|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("BaseRTGuarderia");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(11);
        reporteDatosIncluir.setColumna(11);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(3);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0012 EMPLEADOS POR TIPO DE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("EMPLEADOS POR TIPO DE NOMINA");
        reporteDinamico.setNombreAbreviado("EMPLEADOS POR TIPO DE NOMINA");
        reporteDinamico.setClave("0012");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(12);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(412);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("E");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("EMPLEADOS POR TIPO DE NOMINA");
        contenedor.setOrdenId(12);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(12L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setNombreEtiqueta("PlazaFechaInicial");
        datosConsulta.setNombreMostrar("Fecha inicial");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-AAA");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(11);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-UUUUUUA#");
        datosConsulta.setNombreBD("FDE2_36_CURP");
        datosConsulta.setNombreEtiqueta("EmpleadosCURP");
        datosConsulta.setNombreMostrar("CURP");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar"> 
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDE75_260_descripcion");
        datosConsulta.setNombreEtiqueta("TipoNominaDescripcion");
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(2);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(false);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre(Apellidos nombre(s))");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(3);
        reporteOrdenGrupo.setAgrupar(false);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0013 FALTAS TOTALES">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("FALTAS TOTALES");
        reporteDinamico.setNombreAbreviado("FALTAS TOTALES");
        reporteDinamico.setClave("0013");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(13);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(413);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("F");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("FALTAS TOTALES");
        contenedor.setOrdenId(13);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(13L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setNombreMostrar("Departamento");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatInteger);
        datosConsulta.setNombreBD("@Faltas|");
        datosConsulta.setNombreEtiqueta("Faltas");
        datosConsulta.setNombreMostrar("Faltas");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">           
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0014 HISTORIAL MODIFICACION DE SALARIO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("HISTORIAL MODIFICACION DE SALARIO");
        reporteDinamico.setNombreAbreviado("HISTORIAL MODIFIC. DE SALARIO");
        reporteDinamico.setClave("0014");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(14);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(414);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("H");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("HISTORIAL MODIFICACION DE SALARIO");
        contenedor.setOrdenId(14);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(14L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("ZFS1_2_fecha");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosFecha");
        datosConsulta.setNombreMostrar("Fecha");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFS1_4_salarioDiarioFijo");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosSalarioDiarioFijo");
        datosConsulta.setNombreMostrar("Nuevo Salario");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFS1_6_factorIntegracion");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosFactorIntegracion");
        datosConsulta.setNombreMostrar("Factor Int.");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFS1_7_salarioDiarioIntegrado");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosSalarioDiarioIntegrado");
        datosConsulta.setNombreMostrar("Salario Int.");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(false);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">            
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0015 LISTA DE DEPARTAMENTOS O DE CENTROS DE COSTO ">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("LISTA DE DEPARTAMENTOS O DE CENTROS DE COSTO");
        reporteDinamico.setNombreAbreviado("LISTA DEPTOS O CENTROS  COSTO ");
        reporteDinamico.setClave("0015");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(15);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(415);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("L");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("LISTA DEPTOS O CENTROS  COSTO ");
        contenedor.setOrdenId(15);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(15L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP42_191_clave");
        datosConsulta.setNombreEtiqueta("CentroDeCostoClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP42_192_descripcion");
        datosConsulta.setNombreEtiqueta("CentroDeCostoDescripcion");
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.CANTIDAD);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDP42_192_descripcion");
        datosConsulta.setNombreEtiqueta("CentroDeCostoDescripcion");
        datosConsulta.setNombreMostrar(" ");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(false);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        listReporteDinamico.addAll(cargaReportes15to28(listReportFuenteDatos, listRazonSocial, listHerramienta, listTipoElementos));
        listReporteDinamico.addAll(cargaReportes29to38(listReportFuenteDatos, listRazonSocial, listHerramienta, listTipoElementos));
        listReporteDinamico.addAll(cargaReportes39to58(listReportFuenteDatos, listRazonSocial, listHerramienta, listTipoElementos));
        ////listReporteDinamico.addAll(InicializaReportes2.inicializadorReporteDinamicos(listReportFuenteDatos, listRazonSocial, listHerramienta, listTipoElementos));
        return listReporteDinamico;
    }

    private static List<ReporteDinamico> cargaReportes15to28(List<ReporteFuenteDatos> listReportFuenteDatos, List<RazonSocial> listRazonSocial, List<Herramienta> listHerramienta, List<TipoElemento> listTipoElementos) {
        ReporteDinamico reporteDinamico;
        List<ReporteDinamico> listReporteDinamico = new ArrayList<ReporteDinamico>();
        List<ReporteDatosIncluir> listReporteDatosIncluir;
        ReporteDatosIncluir reporteDatosIncluir;
        List<ReporteOrdenGrupo> listReporteOrdenGrupo;
        ReporteOrdenGrupo reporteOrdenGrupo;
        List<ReporteCamposWhere> listReporteCamposWhere;
        ReporteCamposWhere reporteCamposWhere;
        List<ReporteCamposEncabezado> listReporteCamposEncabezado;
        ReporteCamposEncabezado reporteCamposEncabezado;
        DatosConsulta datosConsulta;
        Contenedor contenedor;
        ReporteEstilos reporteEstilos;

        //<editor-fold defaultstate="collapsed" desc="0016 LISTADO DE MOVIMIENTOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("LISTADO DE MOVIMIENTOS");
        reporteDinamico.setNombreAbreviado("LISTADO DE MOVIMIENTOS");
        reporteDinamico.setClave("0016");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(6);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(16);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(416);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("L");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("LISTADO DE MOVIMIENTOS");
        contenedor.setOrdenId(16);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(16L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Concep.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("ZFM4_38_descripcionAbreviada");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_13_Percepcion");
        datosConsulta.setNombreEtiqueta("Percepcion");
        datosConsulta.setNombreMostrar("Percepciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_14_Deduccion");
        datosConsulta.setNombreEtiqueta("Deduccion");
        datosConsulta.setNombreMostrar("Deducciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Depto.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FDP73_327_descripcion");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("#####");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FDE2_5_nombreYApellidos");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(2);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        List<ReporteOtrosDatosEncabezado> listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        ReporteOtrosDatosEncabezado reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(7);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(8);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.FechaHoraImpresion);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.FechaHoraImpresion.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(5);

        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);

        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos(formatDate);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);

        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0017 LISTADO DE NOMINA REPORTES FIJOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("LISTADO DE NOMINA REPORTES FIJOS");
        reporteDinamico.setNombreAbreviado("LISTADO NOMINA REPORTES FIJOS");
        reporteDinamico.setClave("0017");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(5);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(17);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(417);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("L");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("LISTADO NOMINA REPORTES FIJOS");
        contenedor.setOrdenId(17);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(17L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("FM4_17_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Concepto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FM4_18_descripcion");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_13_Percepcion");
        datosConsulta.setNombreEtiqueta("Percepcion");
        datosConsulta.setNombreMostrar("Percepciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_14_Deduccion");
        datosConsulta.setNombreEtiqueta("Deduccion");
        datosConsulta.setNombreMostrar("Deducciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FM8_24_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FM8_29_ApellidosNombre|FM24_108_descripcion");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        String logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        byte[] data = new byte[logo.length() / 2];
        int i;
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0018 LISTADO EMPLEADO ALFABETICO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("LISTADO EMPLEADO ALFABETICO");
        reporteDinamico.setNombreAbreviado("LISTADO EMPLEADO ALFABETICO");
        reporteDinamico.setClave("0018");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(7);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(18);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(418);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("L");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("LISTADO EMPLEADO ALFABETICO");
        contenedor.setOrdenId(18);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(18L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        //1
        datosConsulta.setFormato("#####");
        datosConsulta.setNombreBD("FM8_24_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //2
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FM8_28_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("MovimientoEmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(10);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //3
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(null);
        datosConsulta.setNombreBD("FM23_103_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(11);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //4
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FM24_108_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(12);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(10);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //5
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-AAA");
        datosConsulta.setNombreBD("FM8_53_RFC");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(13);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(11);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //6
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreBD("FM14_118_importe");
        datosConsulta.setNombreEtiqueta("PlazaSueldo");
        datosConsulta.setNombreMostrar("Sueldo");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(14);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(12);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FM8_25_nombre");
        datosConsulta.setNombreEtiqueta("EmpleadosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(false);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        //1
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);

        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0019 LISTADO EMPLEADOS1 MACRO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("LISTADO EMPLEADOS1 MACRO");
        reporteDinamico.setNombreAbreviado("LISTADO EMPLEADOS 1");
        reporteDinamico.setClave("0019");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(19);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(419);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("L");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("LISTADO EMPLEADOS 1");
        contenedor.setOrdenId(19);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(19L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Numero");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setNombreMostrar("Departamento");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Puesto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-AAA");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDE9_512_importe");
        datosConsulta.setNombreEtiqueta("PlazaSueldo");
        datosConsulta.setNombreMostrar("Sueldo");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">           
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0020 MOVIMIENTOS DE NOMINA DEL SUELDO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("MOVIMIENTOS DE NOMINA DEL SUELDO");
        reporteDinamico.setNombreAbreviado("MOV. NOM. DEL SUELDO2");
        reporteDinamico.setClave("0020");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(1);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(20);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(420);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("M");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("MOV. NOM. DEL SUELDO2");
        contenedor.setOrdenId(20);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(20L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(null);
        datosConsulta.setNombreBD("FDP42_191_clave");
        datosConsulta.setNombreEtiqueta("CentroDeCostoClave");
        datosConsulta.setNombreMostrar("C.C.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(2);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(null);
        datosConsulta.setNombreBD("FDP42_192_descripcion");
        datosConsulta.setNombreEtiqueta("CentroDeCostoDescripcion");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(null);
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleados");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(4);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(null);
        datosConsulta.setNombreBD("FDE2_4_nombre");
        datosConsulta.setNombreEtiqueta("EmpleadosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE2_41_fechaIngresoEmpresa");
        datosConsulta.setNombreEtiqueta("EmpleadosFechaIngresoEmpresa");
        datosConsulta.setNombreMostrar("F. Ingreso");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(6);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setNombreEtiqueta("PlazasPorEmpleadosMovFechaInicial");
        datosConsulta.setNombreMostrar("F. Inicial");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(6);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-AAA");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(9);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-UUUUUUA#");
        datosConsulta.setNombreBD("FDE2_36_CURP");
        datosConsulta.setNombreEtiqueta("EmpleadosCURP");
        datosConsulta.setNombreMostrar("CURP");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(12);//
        reporteDatosIncluir.setTamColumna(9);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(13);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(10);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0021 Movimientos de Sueldos">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("Movimientos de Sueldos");
        reporteDinamico.setNombreAbreviado("Movimientos de Sueldos");
        reporteDinamico.setClave("0021");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(21);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(421);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("M");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("Movimientos de Sueldos");
        contenedor.setOrdenId(21);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(21L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP42_191_clave");
        datosConsulta.setNombreEtiqueta("CentroDeCostoClave");
        datosConsulta.setNombreMostrar("C.C");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(null);
        datosConsulta.setNombreBD("FDP42_192_descripcion");
        datosConsulta.setNombreEtiqueta("CentroDeCostoDescripcion");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleados");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_4_nombre");
        datosConsulta.setNombreEtiqueta("EmpleadosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE2_41_fechaIngresoEmpresa");
        datosConsulta.setNombreEtiqueta("EmpleadosFechaIngresoEmpresa");
        datosConsulta.setNombreMostrar("F. ingreso");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("dd/MM/yy");
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setNombreEtiqueta("PlazaFechaInicial");
        datosConsulta.setNombreMostrar("F. inicial");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_44_fechaFinal");
        datosConsulta.setNombreEtiqueta("PlazaFechaFinal");
        datosConsulta.setNombreMostrar("F. final");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-AAA");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(11);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-UUUUUUA#");
        datosConsulta.setNombreBD("FDE2_36_CURP");
        datosConsulta.setNombreEtiqueta("EmpleadosCURP");
        datosConsulta.setNombreMostrar("CURP");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);//
        reporteDatosIncluir.setTamColumna(14);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(10);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(10);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_5_ConceptoImporte");
        datosConsulta.setNombreEtiqueta("ConceptoImporte");
        datosConsulta.setNombreMostrar("Sueldo");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta("SUELDO:001|");
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(11);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(11);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0022 NETO A PAGAR POR BANCO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("NETO A PAGAR POR BANCO");
        reporteDinamico.setNombreAbreviado("NETO A PAGAR POR BANCO");
        reporteDinamico.setClave("0022");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(22);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(422);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("N");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("NETO A PAGAR POR BANCO");
        contenedor.setOrdenId(22);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(22L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.CANTIDAD);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE108_501_descripcion");
        datosConsulta.setNombreEtiqueta("BancosDescripcion");
        datosConsulta.setNombreMostrar("Banco");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE9_510_cuentaBancaria");
        datosConsulta.setNombreEtiqueta("PlazaCuentaBancaria");
        datosConsulta.setNombreMostrar("Cuenta bancaria");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setNombreEtiqueta("PlazaFechaInicial");
        datosConsulta.setNombreMostrar("Fecha Ingreso");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_33_NetoAPagar");
        datosConsulta.setNombreEtiqueta("NetoAPagar");
        datosConsulta.setNombreMostrar("Neto a pagar");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDP42_192_descripcion");
        datosConsulta.setNombreEtiqueta("CentroDeCostoDescripcion");
        datosConsulta.setNombreMostrar("C.C");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0023 PARAMETROS DE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("PARAMETROS DE NOMINA");
        reporteDinamico.setNombreAbreviado("PARAMETROS DE NOMINA");
        reporteDinamico.setClave("0023");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(1);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(23);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(423);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("P");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("PARAMETROS DE NOMINA");
        contenedor.setOrdenId(23);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(23L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE2_41_fechaIngresoEmpresa");
        datosConsulta.setNombreEtiqueta("EmpleadosFechaIngresoEmpresa");
        datosConsulta.setNombreMostrar("Fecha ingreso");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(6);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_44_fechaFinal");
        datosConsulta.setNombreEtiqueta("PlazasPorEmpleadoFechaFinal");
        datosConsulta.setNombreMostrar("Fecha Baja");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(12);//
        reporteDatosIncluir.setTamColumna(6);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(12);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("ZFS1_3_tipoDeSalario");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosTipoDeSalario");
        datosConsulta.setNombreMostrar("Tipo Sdo F/V/M");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(14);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 1)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 1)));
        reporteDatosIncluir.setColumna(14);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(15);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE109_508_descripcion");
        datosConsulta.setNombreEtiqueta("FormasDePagoDescripcion");
        datosConsulta.setNombreMostrar("Forma pago");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(12);//
        reporteDatosIncluir.setTamColumna(11);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(12);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@Plazas_SueldoDiario");
        datosConsulta.setNombreEtiqueta("PlazasSueldoDiario");
        datosConsulta.setNombreMostrar("S.D.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(16);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(16);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@Empleados_SalarioMinimo");
        datosConsulta.setNombreEtiqueta("EmpleadosSalarioMinimo");
        datosConsulta.setNombreMostrar("S.M.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(17);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(17);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@FDE9_520_salarioDiarioIntegrado");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosSalarioDiarioIntegrado");
        datosConsulta.setNombreMostrar("Sdo Int");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(18);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(18);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">           
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0024 PRENOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0024");
        reporteDinamico.setNombre("PRENOMINA");
        reporteDinamico.setNombreAbreviado("PRENOMINA");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(24);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(424);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("PRENOMINA");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("P");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(24);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(24L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Codigo");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.CANTIDAD);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Puesto");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("DIAS TRAB");
        datosConsulta.setNombreBD("FDCE1_12_Etiqueta");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("Etiqueta");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(6);
        reporteDatosIncluir.setDatosEspecialesConsulta("___________");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("VACACIONES");
        datosConsulta.setNombreBD("FDCE1_12_Etiqueta");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("Etiqueta");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(9);
        reporteDatosIncluir.setColumna(9);
        reporteDatosIncluir.setTamColumna(6);
        reporteDatosIncluir.setDatosEspecialesConsulta("___________");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("FALTAS");
        datosConsulta.setNombreBD("FDCE1_12_Etiqueta");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("Etiqueta");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(10);
        reporteDatosIncluir.setColumna(10);
        reporteDatosIncluir.setTamColumna(6);
        reporteDatosIncluir.setDatosEspecialesConsulta("___________");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("PERMISOS");
        datosConsulta.setNombreBD("FDCE1_12_Etiqueta");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("Etiqueta");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(11);
        reporteDatosIncluir.setColumna(11);
        reporteDatosIncluir.setTamColumna(6);
        reporteDatosIncluir.setDatosEspecialesConsulta("___________");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("COMEDOR");
        datosConsulta.setNombreBD("FDCE1_12_Etiqueta");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("Etiqueta");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(12);
        reporteDatosIncluir.setColumna(12);
        reporteDatosIncluir.setTamColumna(6);
        reporteDatosIncluir.setDatosEspecialesConsulta("___________");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("empleados por departamento");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar("FDP73_327_descripcion");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(false);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(3);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(2);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0025 PROVISION AGUINALDO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0025");
        reporteDinamico.setNombre("PROVISION AGUINALDO");
        reporteDinamico.setNombreAbreviado("PROVISION AGUINALDO");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(25);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(425);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("PROVISION AGUINALDO");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("P");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(25);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(25L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Emple");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fec.Ingr");
        datosConsulta.setNombreBD("FDE2_41_fechaIngresoEmpresa");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("EmpleadosFechaIngresoEmpresa");
        datosConsulta.setFormato(formatDate);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("SueldoDia");
        datosConsulta.setNombreBD("@SueldoDiario|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("SueldoDiario");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(15);
        reporteDatosIncluir.setColumna(15);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Factor");
        datosConsulta.setNombreBD("@FactorDiasAguinaldo|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("FactorDiasAguinaldo");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(18);
        reporteDatosIncluir.setColumna(18);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fec.Planta");
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("PlazaFechaInicial");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("DiasLab");
        datosConsulta.setNombreBD("@PorcionDias|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("PorcionDias");
        datosConsulta.setFormato(formatInteger);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Antig");
        datosConsulta.setNombreBD("@Empleados_AntiguedadExacta");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("EmpleadosAntiguedadExacta");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(12);
        reporteDatosIncluir.setColumna(12);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("TipoEmp");
        datosConsulta.setNombreBD("FDE9_518_tipoRelacionLaboral");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Integer");
        datosConsulta.setNombreEtiqueta("PlazaTipoRelacionLaboral");
        datosConsulta.setFormato("|1234||");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(14);
        reporteDatosIncluir.setColumna(14);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("SueldoMES???");
        datosConsulta.setNombreBD("FDE9_512_importe");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("PlazaSueldo");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(16);
        reporteDatosIncluir.setColumna(16);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("SueldoMes");
        datosConsulta.setNombreBD("FDE9_512_importe");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("PlazaSueldo");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(19);
        reporteDatosIncluir.setColumna(19);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("DiasP/AG");
        datosConsulta.setNombreBD("@DiasAguinaldo|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("DiasAguinaldo");
        datosConsulta.setFormato("|1234||");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(20);
        reporteDatosIncluir.setColumna(20);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Factor");
        datosConsulta.setNombreBD("@FactorDiasAguinaldo|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("FactorDiasAguinaldo");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(21);
        reporteDatosIncluir.setColumna(21);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Prov. Ag. Anual????");
        datosConsulta.setNombreBD("@SueldoDiario|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("SueldoDiario");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(22);
        reporteDatosIncluir.setColumna(22);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(false);
        reporteOrdenGrupo.setOrden(3);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0026 RECIBO DE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("RECIBO DE NOMINA");
        reporteDinamico.setNombreAbreviado("RECIBO DE NOMINA");
        reporteDinamico.setClave("0026");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(26);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(426);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("RECIBO DE NOMINA");
        contenedor.setOrdenId(26);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(26L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Concep");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("ZFM4_37_descripcion");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcion");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(15);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_13_Percepcion");
        datosConsulta.setNombreEtiqueta("Percepcion");
        datosConsulta.setNombreMostrar("Percepciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(15);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0027 RELACION DE CUMPLEAÑOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("RELACION DE CUMPLEAÑOS");
        reporteDinamico.setNombreAbreviado("RELACION DE CUMPLEAÑOS");
        reporteDinamico.setClave("0027");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(27);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(427);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("RELACION DE CUMPLEAÑOS");
        contenedor.setOrdenId(27);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(27L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Depto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Puesto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE2_27_fechaNacimiento");
        datosConsulta.setNombreEtiqueta("EmpleadosFechaNacimiento");
        datosConsulta.setNombreMostrar("Fecha nacimiento");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("@Empleados_Cumple");
        datosConsulta.setNombreEtiqueta("EmpleadosCumple");
        datosConsulta.setNombreMostrar("Cumple");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta("SUELDO:001|");
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">          
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("MEF Logo.jpg");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|50|1|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);

        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);

        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0028 RELACION POR CONCEPTO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0028");
        reporteDinamico.setNombre("RELACION POR CONCEPTO fine");
        reporteDinamico.setNombreAbreviado("RELACION POR CONCEPTO");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(28);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(428);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("RELACION POR CONCEPTO fine");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(28);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(28L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("T.Nom.");
        datosConsulta.setNombreBD("FDE75_259_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("TipoNominaClave");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Dep");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Emple");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setNombreBD("FDCE1_23_ValorParametro");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("ValorParametro");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("1");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("ISR");
        datosConsulta.setNombreBD("FDCE1_5_ConceptoImporte");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("ConceptoImporte");
        datosConsulta.setFormato(formatNumber);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);

        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("ISR:900|");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setNombreBD("FDCE1_5_ConceptoImporte");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("ConceptoImporte");
        datosConsulta.setFormato(formatNumber);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(9);
        reporteDatosIncluir.setColumna(9);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("IMSS:899|");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(3);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setFila(4);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        return listReporteDinamico;
    }

    private static List<ReporteDinamico> cargaReportes29to38(List<ReporteFuenteDatos> listReportFuenteDatos, List<RazonSocial> listRazonSocial, List<Herramienta> listHerramienta, List<TipoElemento> listTipoElementos) {
        ReporteDinamico reporteDinamico;
        List<ReporteDinamico> listReporteDinamico = new ArrayList<ReporteDinamico>();
        List<ReporteDatosIncluir> listReporteDatosIncluir;
        ReporteDatosIncluir reporteDatosIncluir;
        List<ReporteOrdenGrupo> listReporteOrdenGrupo;
        ReporteOrdenGrupo reporteOrdenGrupo;
        List<ReporteCamposWhere> listReporteCamposWhere;
        ReporteCamposWhere reporteCamposWhere;
        List<ReporteCamposEncabezado> listReporteCamposEncabezado;
        ReporteCamposEncabezado reporteCamposEncabezado;
        DatosConsulta datosConsulta;

        //<editor-fold defaultstate="collapsed" desc="0029 REPORTE DE ACUMULADOS MENSUALES">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE DE ACUMULADOS MENSUALES");
        reporteDinamico.setNombreAbreviado("REPORTE ACUMULADOS MENSUAL");
        reporteDinamico.setClave("0029");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(29);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        Contenedor contenedor = new Contenedor();
        contenedor.setId(429);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE ACUMULADOS MENSUAL");
        contenedor.setOrdenId(29);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(29L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@TotalPercepcionesMes|");
        datosConsulta.setNombreEtiqueta("TotalPercepcionesMes");
        datosConsulta.setNombreMostrar("Percep.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@TotalDeduccionesMes|");
        datosConsulta.setNombreEtiqueta("TotalDeduccionesMes");
        datosConsulta.setNombreMostrar("Deducc.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@ISRNetoMes|");
        datosConsulta.setNombreEtiqueta("ISRNetoMes");
        datosConsulta.setNombreMostrar("ISR Neto");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@ISRSubsidioMes|");
        datosConsulta.setNombreEtiqueta("ISRSubsidioMes");
        datosConsulta.setNombreMostrar("ISR Subs.");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(10);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(10);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        ReporteEstilos reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        List<ReporteOtrosDatosEncabezado> listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        ReporteOtrosDatosEncabezado reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        String logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        byte[] data = new byte[logo.length() / 2];
        int i;
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0030 REPORTE DE ASISTENCIAS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE DE ASISTENCIAS");
        reporteDinamico.setNombreAbreviado("REPORTE DE ASISTENCIAS");
        reporteDinamico.setClave("0030");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(30);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(430);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE DE ASISTENCIAS");
        contenedor.setOrdenId(30);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(30L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFM10_70_cantidad");
        datosConsulta.setNombreEtiqueta("AsistenciasCantidad");
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("ZFM11_74_excepcion");
        datosConsulta.setNombreEtiqueta("ExcepcionesExcepcion");
        datosConsulta.setNombreMostrar("Excepcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM50_262_diasVacaciones|!=|0");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0031 REPORTE DE CONCEPTOS PROGRAMADOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0031");
        reporteDinamico.setNombre("REPORTE DE CONCEPTOS PROGRAMADOS");
        reporteDinamico.setNombreAbreviado("REPORTE DE CONCEPTOS PROGRAM");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(31);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(431);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("REPORTE DE CONCEPTOS PROGRAM");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(31);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(31L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Emp.");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(9);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(10);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setFormato("UUUU-######-AAA");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(11);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concep");
        datosConsulta.setNombreBD("ZFM4_38_descripcionAbreviada");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(12);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("F. Inicial");
        datosConsulta.setNombreBD("ZFM1_30_fechaIni");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("MovNomConcepFechaIni");
        datosConsulta.setFormato(formatDate);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(13);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("F. Final");
        datosConsulta.setNombreBD("ZFM1_31_fechaCierr");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("MovNomConcepFechaCierr");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(14);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setNombreBD("ZFM1_29_resultado");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Double");
        datosConsulta.setNombreEtiqueta("MovNomConcepResultado");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(15);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(2);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        datosConsulta.setCamposCombinar("FDE2_5_nombreYApellidos");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(3);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposWhere">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM4_35_clave|=|001");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        //</editor-fold>
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("MEF Logo.jpg");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|50|1|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(3);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(3);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0032 REPORTE DE INCAPACIDADES DETALLE">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE DE INCAPACIDADES DETALLE");
        reporteDinamico.setNombreAbreviado("REPORTE INCAPACIDADES DETALLE");
        reporteDinamico.setClave("0032");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(32);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(432);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE INCAPACIDADES DETALLE");
        contenedor.setOrdenId(32);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(32L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP42_192_descripcion");
        datosConsulta.setNombreEtiqueta("CentroDeCostoDescripcion");
        datosConsulta.setNombreMostrar("C. Costos");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FER1_2_clave");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadClave");
        datosConsulta.setNombreMostrar("Folio");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Emp.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(15);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FER1_8_fechaInicial");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadFechaInicial");
        datosConsulta.setNombreMostrar("F. Inic.");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FER1_9_fechaFinal");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadFechaFinal");
        datosConsulta.setNombreMostrar("F. Fin.");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatInteger);
        datosConsulta.setNombreBD("FER1_7_diasIncapacidad");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadDiasIncapacidad");
        datosConsulta.setNombreMostrar("Dias");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(3);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(8);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-UUUUUUA#");
        datosConsulta.setNombreBD("FDE2_36_CURP");
        datosConsulta.setNombreEtiqueta("EmpleadosCURP");
        datosConsulta.setNombreMostrar("CURP");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);//
        reporteDatosIncluir.setTamColumna(12);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_3_ramoSeguro");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadRamoSeguro");
        datosConsulta.setNombreMostrar("Ramo seguro");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(10);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(10);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_4_tipoRiesgo");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadTipoRiesgo");
        datosConsulta.setNombreMostrar("Tipo riesgo");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(11);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 1)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 1)));
        reporteDatosIncluir.setColumna(11);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_5_secuelaConsecuencia");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadSecuelaConsecuencia");
        datosConsulta.setNombreMostrar("Secuela");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(12);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(12);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_6_controlIncapacidad");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadControlIncapacidad");
        datosConsulta.setNombreMostrar("Control Inc.");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(13);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(13);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FER2_12_clave");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadClave");
        datosConsulta.setNombreMostrar("Revalúa");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(14);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(14);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|FER1_2_clave|!=|0");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0033 REPORTE DE INCAPACIDADES RESUMEN">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE DE INCAPACIDADES RESUMEN");
        reporteDinamico.setNombreAbreviado("REPORTE INCAPACIDADES RESUMEN");
        reporteDinamico.setClave("0033");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(5);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(33);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(433);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE INCAPACIDADES RESUMEN");
        contenedor.setOrdenId(33);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(33L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FER1_2_clave");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadClave");
        datosConsulta.setNombreMostrar("Folio");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FER1_8_fechaInicial");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadFechaInicial");
        datosConsulta.setNombreMostrar("Fecha inicial");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FER1_9_fechaFinal");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadFechaFinal");
        datosConsulta.setNombreMostrar("Fecha final");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatInteger);
        datosConsulta.setNombreBD("FER1_7_diasIncapacidad");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadDiasIncapacidad");
        datosConsulta.setNombreMostrar("Dias");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(3);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_3_ramoSeguro");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadRamoSeguro");
        datosConsulta.setNombreMostrar("Ramo seguro");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_4_tipoRiesgo");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadTipoRiesgo");
        datosConsulta.setNombreMostrar("Tipo Riesgo");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|FER1_2_clave|!=|0");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RangoFecha);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RangoFecha.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos(formatDate);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(6);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0034 REPORTE DE INTEGRADOS 18">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE DE INTEGRADOS 34");
        reporteDinamico.setNombreAbreviado("REPORTE DE INTEGRADOS 34");
        reporteDinamico.setClave("0034");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(5);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(34);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(434);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE DE INTEGRADOS 34");
        contenedor.setOrdenId(34);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(34L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(7);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(22);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FDE9_256_fechaInicial");
        datosConsulta.setNombreEtiqueta("PlazaFechaInicial");
        datosConsulta.setNombreMostrar("Fecha de Ingreso");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@Empleados_AntiguedadExacta");
        datosConsulta.setNombreEtiqueta("EmpleadosAntiguedadExacta");
        datosConsulta.setNombreMostrar("Antiguedad");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@Plazas_SueldoDiario");
        datosConsulta.setNombreEtiqueta("PlazasSueldoDiario");
        datosConsulta.setNombreMostrar("S.D");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(10);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(10);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDE9_520_salarioDiarioIntegrado");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosSalarioDiarioIntegrado");
        datosConsulta.setNombreMostrar("S.D.I.");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(12);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(12);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFS1_6_factorIntegracion");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosFactorIntegracion");
        datosConsulta.setNombreMostrar("Factor integracion");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(11);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(11);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-AAA");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(13);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("UUUU-######-UUUUUUA#");
        datosConsulta.setNombreBD("FDE2_36_CURP");
        datosConsulta.setNombreEtiqueta("EmpleadosCURP");
        datosConsulta.setNombreMostrar("CURP");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(17);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDP42_192_descripcion");
        datosConsulta.setNombreEtiqueta("CentroDeCostoDescripcion");
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0035 REPORTE DE INTEGRADOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0035");
        reporteDinamico.setNombre("REPORTE DE INTEGRADOS");
        reporteDinamico.setNombreAbreviado("REPORTE DE INTEGRADOS");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(false);
        reporteDinamico.setOcultarColumnas(false);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(false);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setOrden(null);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(35);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(435);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("REPORTE DE INTEGRADOS");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(35);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(35L);
        reporteDinamico.setContenedor(contenedor);
        //</editor-fold>

        ///<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Emp.");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("TM?");
        datosConsulta.setNombreBD("FDCE1_12_Etiqueta");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("Etiqueta");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("_");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fecha Mov");
        datosConsulta.setNombreBD("ZFS1_2_fecha");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosFecha");
        datosConsulta.setFormato(formatDate);
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fch.Ing.");
        datosConsulta.setNombreBD("FDE2_41_fechaIngresoEmpresa");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("EmpleadosFechaIngresoEmpresa");
        datosConsulta.setFormato("dd/MM/yy");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Ant.");
        datosConsulta.setNombreBD("@Empleados_Antiguedad");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("EmpleadosAntiguedad");
        datosConsulta.setFormato(formatInteger);
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(3);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(9);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("CURP");
        datosConsulta.setNombreBD("FDE2_36_CURP");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosCURP");
        datosConsulta.setFormato("UUUU-######-UUUUUUA#");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(13);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setNombreBD("ZFS4_26_importe");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Double");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosDetImporte");
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(11);
        reporteDatosIncluir.setColumna(10);
        reporteDatosIncluir.setTamColumna(6);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("F.Mod.");
        datosConsulta.setNombreBD("ZFS4_27_fechaCambio");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("SalariosIntegradosDetFechaCambio");
        datosConsulta.setFormato(formatDate);
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(13);
        reporteDatosIncluir.setColumna(11);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concep");
        datosConsulta.setNombreBD("ZFS5_32_descripcionAbreviada");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteEstilos.setBordes(null);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setOrden(10);
        reporteDatosIncluir.setColumna(9);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //</editor-fold>

        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Depto");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar(null);
        datosConsulta.setCamposNodoConsulta(null);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(null);
        reporteEstilos.setBordes(null);
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>

        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteEstilos.setBordes(null);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteEstilos.setBordes(null);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0036 REPORTE DE MOVIMIENTOS DE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0036");
        reporteDinamico.setNombre("REPORTE DE MOVIMIENTOS DE NOMINA");
        reporteDinamico.setNombreAbreviado("REPORTE DE MOVTOS. DE NOMINA");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(36);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(436);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("REPORTE DE MOVTOS. DE NOMINA");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(36);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(36L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setNombreBD("FM8_24_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FM8_29_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("MovimientoEmpleadosApellidosNombre");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(40);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setNombreBD("FM8_53_RFC");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setFormato("UUUU-######-AAA");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();;
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Depto.");
        datosConsulta.setNombreBD("FM23_104_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(2);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposWhere">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|FM4_17_clave|=|001");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        //</editor-fold>
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(1);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("0");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0037 REPORTE DE MOVIMIENTOS DE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0037");
        reporteDinamico.setNombre("REPORTE DE MOVIMIENTOS DE NOMINA");
        reporteDinamico.setNombreAbreviado("REPORTE DE MOVTOS. DE NOMINA");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setOrden(10);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(1);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(37);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(437);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("REPORTE DE MOVTOS. DE NOMINA");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(37);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(37L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concep");
        datosConsulta.setNombreBD("FM4_17_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setFormato("###");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FM4_19_descripcionAbreviada");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Dato C");
        datosConsulta.setNombreBD("FDCE1_15_ConceptoTipoDato");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("ConceptoTipoDato");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño)));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Percepciones");
        datosConsulta.setNombreBD("FDCE1_13_Percepcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("Percepcion");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Deducciones");
        datosConsulta.setNombreBD("FDCE1_14_Deduccion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("Deduccion");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Dato");
        datosConsulta.setNombreBD("FM5_91_valor");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("MovNomConceParamValor");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Periodo");
        datosConsulta.setNombreBD("FM3_9_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("PeriodosNominaClave");
        datosConsulta.setFormato("###");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FM8_24_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar("FM8_29_ApellidosNombre");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposWhere">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|FM4_17_clave|=|001");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
//</editor-fold>
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteEstilos.setBordes("1");
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteEstilos.setBordes("1");
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteEstilos.setBordes("1");
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0038 REPORTE DE VACACIONES">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE DE VACACIONES");
        reporteDinamico.setNombreAbreviado("REPORTE DE VACACIONES");
        reporteDinamico.setClave("0038");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(5);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(38);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(438);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE DE VACACIONES");
        contenedor.setOrdenId(38);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(38L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("VAC194_1351_salidaVacac");
        datosConsulta.setNombreEtiqueta("VacacionesDisfrutadasSalidaVacac");
        datosConsulta.setNombreMostrar("Fecha salida");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatInteger);
        datosConsulta.setNombreBD("VAC1_2227_diasVac");
        datosConsulta.setNombreEtiqueta("VacacionesAplicacionDiasVac");
        datosConsulta.setNombreMostrar("Dias");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("VAC194_1352_regresoVac");
        datosConsulta.setNombreEtiqueta("VacacionesDisfrutadasRegresoVac");
        datosConsulta.setNombreMostrar("Regreso vacaciones");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM50_262_diasVacaciones|!=|0");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RangoFecha);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RangoFecha.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos(formatDate);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);
        return listReporteDinamico;
    }

    private static List<ReporteDinamico> cargaReportes39to58(List<ReporteFuenteDatos> listReportFuenteDatos, List<RazonSocial> listRazonSocial, List<Herramienta> listHerramienta, List<TipoElemento> listTipoElementos) {
        ReporteDinamico reporteDinamico;
        List<ReporteDinamico> listReporteDinamico = new ArrayList<ReporteDinamico>();
        List<ReporteDatosIncluir> listReporteDatosIncluir;
        ReporteDatosIncluir reporteDatosIncluir;
        List<ReporteOrdenGrupo> listReporteOrdenGrupo;
        ReporteOrdenGrupo reporteOrdenGrupo;
        List<ReporteCamposWhere> listReporteCamposWhere;
        ReporteCamposWhere reporteCamposWhere;
        List<ReporteCamposEncabezado> listReporteCamposEncabezado;
        ReporteCamposEncabezado reporteCamposEncabezado;
        DatosConsulta datosConsulta;
        Contenedor contenedor;
        ReporteEstilos reporteEstilos;
        List<ReporteOtrosDatosEncabezado> listReporteOtrosDatosEncabezado;
        ReporteOtrosDatosEncabezado reporteOtrosDatosEncabezado;
        String logo;
        byte[] data;
        int i;
        //<editor-fold defaultstate="collapsed" desc="0039 REPORTE INCAPACIDADES">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE INCAPACIDADES");
        reporteDinamico.setNombreAbreviado("REPORTE INCAPACIDADES");
        reporteDinamico.setClave("0039");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(39);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(439);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE INCAPACIDADES");
        contenedor.setOrdenId(39);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(39L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FER1_2_clave");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadClave");
        datosConsulta.setNombreMostrar("Folio");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Emp.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.CANTIDAD);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FER1_8_fechaInicial");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadFechaInicial");
        datosConsulta.setNombreMostrar("Fch.Inicial");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatDate);
        datosConsulta.setNombreBD("FER1_9_fechaFinal");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadFechaFinal");
        datosConsulta.setNombreMostrar("Fch.Final");
        datosConsulta.setTipoDato(Date.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(7);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatInteger);
        datosConsulta.setNombreBD("FER1_7_diasIncapacidad");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadDiasIncapacidad");
        datosConsulta.setNombreMostrar("Dias");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(7);//
        reporteDatosIncluir.setTamColumna(3);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(7);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_3_ramoSeguro");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadRamoSeguro");
        datosConsulta.setNombreMostrar("Ramo seguro");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(8);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(8);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FER1_4_tipoRiesgo");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadTipoRiesgo");
        datosConsulta.setNombreMostrar("Tipo riesgo");
        datosConsulta.setTipoDato(Integer.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(9);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(9);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0040 REPORTE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("REPORTE NOMINA");
        reporteDinamico.setNombreAbreviado("REPORTE NOMINA");
        reporteDinamico.setClave("0040");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(40);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(440);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("REPORTE NOMINA");
        contenedor.setOrdenId(40);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(40L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("FM4_17_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Concepto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_13_Percepcion");
        datosConsulta.setNombreEtiqueta("Percepcion");
        datosConsulta.setNombreMostrar("Percepciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_14_Deduccion");
        datosConsulta.setNombreEtiqueta("Deduccion");
        datosConsulta.setNombreMostrar("Deducciones");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FM8_24_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar(" ");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FM8_29_ApellidosNombre|FM24_107_clave|FM24_108_descripcion");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0041 REPORTE NOMINA">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0041");
        reporteDinamico.setNombre("REPORTE NOMINA");
        reporteDinamico.setNombreAbreviado("REPORTE NOMINA");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(41);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(441);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("REPORTE NOMINA");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(41);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(41L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setFormato("###");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("ZFM4_37_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcion");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Dato");
        datosConsulta.setNombreBD("FDCE1_24_ValorParametroOConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("ValorParametroOConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("1");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Percep.");
        datosConsulta.setNombreBD("FDCE1_13_Percepcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("Percepcion");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Deducc.");
        datosConsulta.setNombreBD("FDCE1_14_Deduccion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("Deduccion");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Neto a pagar");
        datosConsulta.setNombreBD("FDCE1_33_NetoAPagar");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("NetoAPagar");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0042 RESUMEN DE AUSENTISMO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0042");
        reporteDinamico.setNombre("RESUMEN DE AUSENTISMO");
        reporteDinamico.setNombreAbreviado("RESUMEN DE AUSENTISMO");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(42);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(442);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("RESUMEN DE AUSENTISMO");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(42);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(42L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Emp.");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("RFC");
        datosConsulta.setNombreBD("FDE2_35_RFC");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosRFC");
        datosConsulta.setFormato("UUUU-######-AAA");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(11);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("CURP");
        datosConsulta.setNombreBD("FDE2_36_CURP");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosCURP");
        datosConsulta.setFormato("UUUU-######-UUUUUUA#");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(14);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("IMSS");
        datosConsulta.setNombreBD("FDE2_37_IMSS");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosIMSS");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fecha");
        datosConsulta.setNombreBD("FDE2_41_fechaIngresoEmpresa");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("EmpleadosFechaIngresoEmpresa");
        datosConsulta.setFormato(formatDate);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(7);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Faltas");
        datosConsulta.setNombreBD("@Ausentismo|");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("Ausentismo");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0043 RESUMEN DE PERCEPCIONES Y DEDUCCIONES">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0043");
        reporteDinamico.setNombre("RESUMEN DE PERCEPCIONES Y DEDUCCIONES");
        reporteDinamico.setNombreAbreviado("RESUMEN DE PERCEP. Y DEDUC.");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(6);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(43);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(443);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("RESUMEN DE PERCEP. Y DEDUC.");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("R");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(43);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(43L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concepto");
        datosConsulta.setNombreBD("FM4_17_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setFormato("###");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("FM4_18_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcion");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(30);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Naturaleza");
        datosConsulta.setNombreBD("FM4_23_naturaleza");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("com.mef.erp.modelo.entidad.Naturaleza");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiNaturaleza");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(3);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setFila(4);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RangoFecha.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RangoFecha);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(7);
        reporteCamposEncabezado.setFila(6);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos(formatDate);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(6);
        reporteCamposEncabezado.setFila(5);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0044 TOTAL PERCEPCIONES Y DEDUCCIONES">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0044");
        reporteDinamico.setNombre("TOTAL PERCEPCIONES Y DEDUCCIONES");
        reporteDinamico.setNombreAbreviado("TOTAL PERCEPCIONES Y DEDUCC.");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(44);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(444);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("TOTAL PERCEPCIONES Y DEDUCC.");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(44);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(44L);
//</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Percepciones");
        datosConsulta.setNombreBD("FDCE1_10_TotalPercepcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalPercepcion");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Deducciones");
        datosConsulta.setNombreBD("FDCE1_11_TotalDeduccion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalDeduccion");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Neto a pagar");
        datosConsulta.setNombreBD("FDCE1_33_NetoAPagar");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("NetoAPagar");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Depto.");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar("FDP73_327_descripcion");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0045 TOTALES DE CONCEPTOS">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("TOTALES DE CONCEPTOS");
        reporteDinamico.setNombreAbreviado("TOTALES DE CONCEPTOS");
        reporteDinamico.setClave("0045");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(45);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(445);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("TOTALES DE CONCEPTOS");
        contenedor.setOrdenId(45);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(45L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre(Nombre(s) Apellidos)");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFM1_29_resultado");
        datosConsulta.setNombreEtiqueta("MovNomConcepResultado");
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("ZFM4_37_descripcion");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre(Nombre(s) Apellidos)");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(2);
        reporteOrdenGrupo.setAgrupar(false);
        reporteOrdenGrupo.setIncluirEncabezado(false);
        reporteOrdenGrupo.setIncluirPie(false);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(6);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(7);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0046 TOTALES DIMM CONCEPTO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0046");
        reporteDinamico.setNombre("TOTALES DIMM CONCEPTO");
        reporteDinamico.setNombreAbreviado("TOTALES DIMM CONCEPTO");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(3);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(46);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(446);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("TOTALES DIMM CONCEPTO");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(46);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(46L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concepto");
        datosConsulta.setNombreBD("ZFM4_38_descripcionAbreviada");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.CANTIDAD);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(3);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0047 TOTALES DIMM EMPLEADO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0047");
        reporteDinamico.setNombre("TOTALES DIMM EMPLEADO");
        reporteDinamico.setNombreAbreviado("TOTALES DIMM EMPLEADO");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(47);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(447);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("TOTALES DIMM EMPLEADO");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(47);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(47L);
//</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Gravado");
        datosConsulta.setNombreBD("FDCE1_27_TotalImporteGravablePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImporteGravablePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Exento");
        datosConsulta.setNombreBD("FDCE1_29_TotalImporteExentoPorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImporteExentoPorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Total");
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("ZFM4_37_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcion");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(3);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0048 TOTALES EXENTO Y GRAV. POR EMPLEADO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("TOTALES EXENTO Y GRAV. POR EMPLEADO");
        reporteDinamico.setNombreAbreviado("TOTAL EXENTO Y GRAV. POR EMP");
        reporteDinamico.setClave("0048");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(48);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(448);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("TOTAL EXENTO Y GRAV. POR EMP");
        contenedor.setOrdenId(48);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(48L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("ZFM4_37_descripcion");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcion");
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFM7_59_resultadoExento");
        datosConsulta.setNombreEtiqueta("MovNomBaseAfectaResultadoExento");
        datosConsulta.setNombreMostrar("Importe Exento");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("ZFM7_58_resultado");
        datosConsulta.setNombreEtiqueta("MovNomBaseAfectaResultado");
        datosConsulta.setNombreMostrar("Importe Gravado");
        datosConsulta.setTipoDato(Double.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("FDE2_5_nombreYApellidos");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(2);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("ZFM3_7_clave");
        datosConsulta.setNombreEtiqueta("PeriodosNominaClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar("ZFM3_9_descripcion");

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(3);
        reporteOrdenGrupo.setAgrupar(false);
        reporteOrdenGrupo.setIncluirEncabezado(false);
        reporteOrdenGrupo.setIncluirPie(false);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar("Calle|NoExterior|NoInterior|Colonia");
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Domicilio);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Domicilio.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0049 TOTALES GRAVABES Y EXENTOS POR EMPLEADO 1">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0049");
        reporteDinamico.setNombre("TOTALES GRAVABES Y EXENTOS POR EMPLEADO 1");
        reporteDinamico.setNombreAbreviado("TOTAL GRAV. Y EXENTOS POR EMP");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(49);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(449);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("TOTAL GRAV. Y EXENTOS POR EMP");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(49);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(49L);
//</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Concepto");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setFormato("###");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("ZFM4_37_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcion");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setNombreBD("FDCE1_31_TotalCantidadPorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalCantidadPorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("1");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Total");
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Gravable");
        datosConsulta.setNombreBD("FDCE1_27_TotalImporteGravablePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImporteGravablePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Exento");
        datosConsulta.setNombreBD("FDCE1_29_TotalImporteExentoPorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImporteExentoPorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(9);
        reporteDatosIncluir.setColumna(9);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0050 TOTALES GRAVABLES Y EXENTOS POR CONCEPTO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0050");
        reporteDinamico.setNombre("TOTALES GRAVABLES Y EXENTOS POR CONCEPTO");
        reporteDinamico.setNombreAbreviado("TOTAL GRAV Y EXENTOS POR CONC");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(50);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(450);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("TOTAL GRAV Y EXENTOS POR CONC");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(50);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(50L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Con");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setFormato("###");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("ZFM4_38_descripcionAbreviada");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiDescripcionAbreviada");
        datosConsulta.setFormato("");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setNombreBD("FDCE1_31_TotalCantidadPorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalCantidadPorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setDatosEspecialesConsulta("1");
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Total");
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Gravable");
        datosConsulta.setNombreBD("FDCE1_27_TotalImporteGravablePorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImporteGravablePorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Exento");
        datosConsulta.setNombreBD("FDCE1_29_TotalImporteExentoPorConcepto");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalImporteExentoPorConcepto");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setOrden(6);
        reporteDatosIncluir.setColumna(6);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Naturaleza");
        datosConsulta.setNombreBD("ZFM4_47_naturaleza");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("com.mef.erp.modelo.entidad.Naturaleza");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiNaturaleza");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
//</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0051 TOTALES GRAVABLES Y EXENTOS POR EMPLEADO 2">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0051");
        reporteDinamico.setNombre("TOTALES GRAVABLES Y EXENTOS POR EMPLEADO 2");
        reporteDinamico.setNombreAbreviado("TOTALES GRAVABLES Y EXENTOS");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(51);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(451);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("TOTALES GRAVABLES Y EXENTOS");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(51);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(51L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Empleado");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("");
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Gravable");
        datosConsulta.setNombreBD("FDCE1_19_TotalConceptoGravable");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalConceptoGravable");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(7);
        reporteDatosIncluir.setColumna(7);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Exento");
        datosConsulta.setNombreBD("FDCE1_21_TotalConceptoExento");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.Float");
        datosConsulta.setNombreEtiqueta("TotalConceptoExento");
        datosConsulta.setFormato(formatNumber);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setReporteEstiloTitulo(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setReporteEstiloDetalle(new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3));
        reporteDatosIncluir.setOrden(8);
        reporteDatosIncluir.setColumna(8);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
//</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(2);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
//</editor-fold>
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0052 TOTALES POR CONCEPTO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("TOTALES POR CONCEPTO");
        reporteDinamico.setNombreAbreviado("TOTALES POR CONCEPTO");
        reporteDinamico.setClave("0052");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(3);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(52);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(452);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("TOTALES POR CONCEPTO");
        contenedor.setOrdenId(52);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(52L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("#####");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Emple.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_23_ValorParametro");
        datosConsulta.setNombreEtiqueta("ValorParametro");
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta("1");
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_326_clave");
        datosConsulta.setNombreEtiqueta("DepartamentosClave");
        datosConsulta.setNombreMostrar("Depto.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM4_35_clave|=|001");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0053 Totales por período">  
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("Totales por período");
        reporteDinamico.setNombreAbreviado("Totales por período");
        reporteDinamico.setClave("0053");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(53);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(453);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("T");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("Totales por período");
        contenedor.setOrdenId(53);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(53L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre(Nombre(s) Apellidos)");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("@TotalPercepciones|");
        datosConsulta.setNombreEtiqueta("TotalPercepciones");
        datosConsulta.setNombreMostrar("Total percep");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_11_TotalDeduccion");
        datosConsulta.setNombreEtiqueta("TotalDeduccion");
        datosConsulta.setNombreMostrar("Total deduc");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("ZFM3_9_descripcion");
        datosConsulta.setNombreEtiqueta("PeriodosNominaDescripcion");
        datosConsulta.setNombreMostrar("descripcion");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_5_nombreYApellidos");
        datosConsulta.setNombreEtiqueta("EmpleadosNombreApellidos");
        datosConsulta.setNombreMostrar("Nombre(Nombre(s) Apellidos)");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(2);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(false);
        reporteOrdenGrupo.setIncluirPie(false);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0054 VERIFICADOR DE CONCEPTOS FIJOS POR CONCEPTO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("VERIFICADOR DE CONCEPTOS FIJOS POR CONCEPTO");
        reporteDinamico.setNombreAbreviado("VERIF. CONCEP. FIJOS CONCEPTO");
        reporteDinamico.setClave("0054");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(4);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(1));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(54);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(454);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("V");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("VERIF. CONCEP. FIJOS CONCEPTO");
        contenedor.setOrdenId(54);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(54L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FM8_24_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Empleados");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(5);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FM8_29_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("MovimientoEmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(40);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FM23_104_descripcion");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setNombreMostrar("Departamento");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FM23_104_descripcion");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setNombreMostrar("Subdepartamento");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("FM4_17_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
            //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(1);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.Logo);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.Logo.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        logo = "4749463839616D001F0050000021F90400000000002C000000006D001F00870000000000330000660000990000CC0000FF002B00002B33002B66002B99002BCC002BFF0055000055330055660055990055CC0055FF0080000080330080660080990080CC0080FF00AA0000AA3300AA6600AA9900AACC00AAFF00D50000D53300D56600D59900D5CC00D5FF00FF0000FF3300FF6600FF9900FFCC00FFFF3300003300333300663300993300CC3300FF332B00332B33332B66332B99332BCC332BFF3355003355333355663355993355CC3355FF3380003380333380663380993380CC3380FF33AA0033AA3333AA6633AA9933AACC33AAFF33D50033D53333D56633D59933D5CC33D5FF33FF0033FF3333FF6633FF9933FFCC33FFFF6600006600336600666600996600CC6600FF662B00662B33662B66662B99662BCC662BFF6655006655336655666655996655CC6655FF6680006680336680666680996680CC6680FF66AA0066AA3366AA6666AA9966AACC66AAFF66D50066D53366D56666D59966D5CC66D5FF66FF0066FF3366FF6666FF9966FFCC66FFFF9900009900339900669900999900CC9900FF992B00992B33992B66992B99992BCC992BFF9955009955339955669955999955CC9955FF9980009980339980669980999980CC9980FF99AA0099AA3399AA6699AA9999AACC99AAFF99D50099D53399D56699D59999D5CC99D5FF99FF0099FF3399FF6699FF9999FFCC99FFFFCC0000CC0033CC0066CC0099CC00CCCC00FFCC2B00CC2B33CC2B66CC2B99CC2BCCCC2BFFCC5500CC5533CC5566CC5599CC55CCCC55FFCC8000CC8033CC8066CC8099CC80CCCC80FFCCAA00CCAA33CCAA66CCAA99CCAACCCCAAFFCCD500CCD533CCD566CCD599CCD5CCCCD5FFCCFF00CCFF33CCFF66CCFF99CCFFCCCCFFFFFF0000FF0033FF0066FF0099FF00CCFF00FFFF2B00FF2B33FF2B66FF2B99FF2BCCFF2BFFFF5500FF5533FF5566FF5599FF55CCFF55FFFF8000FF8033FF8066FF8099FF80CCFF80FFFFAA00FFAA33FFAA66FFAA99FFAACCFFAAFFFFD500FFD533FFD566FFD599FFD5CCFFD5FFFFFF00FFFF33FFFF66FFFF99FFFFCCFFFFFF00000000000000000000000008FF00F7091C48B0A0C18308132A5CC8B0A1C38710234A9C487019C58B18336234A2A9E032651A438A0CA9E6412882FA88459308A4A5CB973063021108E487CC9B2ED5C02118C726CE9870E0744C08E701049002A31153462FE283A750A34A9D3A339A8EA958A7427800C4E2BEA259B36E85000414C29264BDD253064AD9CA87168CCA1D3BB7EED81C34EDEA1D4B77EB0F8145FBEE15FC40C7DB8270B63EC8B152A93262C4200A564C576E5D0BFB965598BCF7695CBE2BD570263C76F3D3A10581CCFDBB4F99EBC70F55C78D0BC4884E204171AB310267B7CEBC104C03A930BC38F1E38AE33E0019D8E8E61F16A04BB720FBE9D89906030B17C876295286968347FF5CF683329068F5D0AB4F9FBE98E9AD16367DF56C54D3FAFBD1D09A3672B064DD9DFB78A7C9770A054797570E45A39A5CAC2954D4670F98D59C5166290461610725F680725B01E8DD52E0C1679450426942A22626A2289440CBE400C167D839681D855FD165016A0729A39A810D12B4A373637504CD63AF2D345C7873F1C59751202938638F6701F98045607D566142947195E103EF59D65134AE79A7905CB349B95905F46DF8C04AF5986694051FC5A9CC32CB4043A72695C159E35CA8BD054D3DFB44A3DD563112245A9A63E949E463D024D465969D6D6591932F3EE5E26616E410D755CA41A80660336EA81A109AE6A0C37057D555E840CD95E61777CA0CFFA80CA0088968944C3EB964D34AF428F7A66575057B23AB2242AAD865D65DE9A394CA61B7288107FD181CB40B59A56672CC5E2BDC9560B93AD766D88EF5038204753BE379AD7D781842CC92CB509BDE021BAA60006A181C92F826A7AC8FC572B5D26B2032E4E656DF351AA840061F46A95CB8E9661B1C0D7FF61EA8DFC2946B4BBBE1985D781558F49177D41ED4AF053A54702A57A50201410EA302EADCAD0B299365577BC237546313E9179C4561BAE66E42E549992FBE2B05FDEA424614CBDCCB10683C5173400CF5EC435B3DDA198716D063AD81ABF637A39EAD567052460B425021C02127742154C7CA6B99456E3EC550D0728174A85C4E4B14589F624249D45B508007C51BE0BCE914D46F5FE9B41B800A6932B84E2B9968B81A3F4B241AE3FBAC95F6489C771E2DE6996DEEF9E89E3BADCFBAA4A7AEFAEAACB7EEFAEBB0C72EFBECB4D71E5140003B";
        data = new byte[logo.length() / 2];
        for (i = 0; i < logo.length(); i += 2) {
            data[i / 2] = (byte) ((Character.digit(logo.charAt(i), 16) << 4)
                    + Character.digit(logo.charAt(i + 1), 16));
        }
        reporteOtrosDatosEncabezado.setLogo(data);
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        reporteOtrosDatosEncabezado.setDatos("Erp_Logo.gif");
        reporteOtrosDatosEncabezado.setOtrasPropiedades("50|30|0|2");
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0055 VERIFICADOR DE FALTAS POR EMPLEADO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0055");
        reporteDinamico.setNombre("VERIFICADOR DE FALTAS POR EMPLEADO");
        reporteDinamico.setNombreAbreviado("VERIF. FALTAS X EMPLE.??????");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(55);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(455);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("VERIF. FALTAS X EMPLE.??????");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("V");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(55);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(55L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fecha");
        datosConsulta.setNombreBD("ZFM10_68_fecha");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("AsistenciasFecha");
        datosConsulta.setFormato(formatDate);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Tipo de falta");
        datosConsulta.setNombreBD("ZFM11_74_excepcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ExcepcionesDescripcion");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Folio");
        datosConsulta.setNombreBD("FER1_2_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadClave");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        datosConsulta.setCamposCombinar("FDE2_6_ApellidosNombre");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposWhere">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM11_74_excepcion|=|Ausentismo");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("OR|ZFM11_74_excepcion|=|Falta");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("OR|ZFM11_74_excepcion|=|Incapacidad por accidente");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("OR|ZFM11_74_excepcion|=|Incapacidad por enfermedad");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("OR|ZFM11_74_excepcion|=|Incapacidad por maternidad");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("OR|ZFM11_74_excepcion|=|Otras incapacidades");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("OR|ZFM11_74_excepcion|=|Permiso con sueldo");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("OR|ZFM11_74_excepcion|=|Permiso sin sueldo ");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        //</editor-fold>
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0056 VERIFICADOR DE FALTAS POR TIPO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setClave("0056");
        reporteDinamico.setNombre("VERIFICADOR DE FALTAS POR TIPO");
        reporteDinamico.setNombreAbreviado("VERIF. FALTAS POR TIPO ????");
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));
        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(true);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(2);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(56);
        reporteDinamico.setUsaFiltroCorrida(true);
        //<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(456);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setNombre("VERIF. FALTAS POR TIPO ????");
        contenedor.setAccion("ReporteNominas");
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setKeyCode("V");
        contenedor.setModifiers("CTRL");
        contenedor.setParentId(4);
        contenedor.setOrdenId(56);
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setHabilitado(true);
        contenedor.setVisible(true);
        contenedor.setCompartir(true);
        contenedor.setIdMultiUsos(56L);
        //</editor-fold>
        reporteDinamico.setContenedor(contenedor);
        //<editor-fold defaultstate="collapsed" desc="ReporteDatosIncluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Emple");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setFormato("#####");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(2);
        reporteDatosIncluir.setColumna(2);
        reporteDatosIncluir.setTamColumna(20);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Depto.");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(3);
        reporteDatosIncluir.setColumna(3);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Fecha");
        datosConsulta.setNombreBD("ZFM10_68_fecha");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.util.Date");
        datosConsulta.setNombreEtiqueta("AsistenciasFecha");
        datosConsulta.setFormato(formatDate);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(4);
        reporteDatosIncluir.setColumna(4);
        reporteDatosIncluir.setTamColumna(10);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        reporteDatosIncluir = new ReporteDatosIncluir();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Folio");
        datosConsulta.setNombreBD("FER1_2_clave");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("RegistroIncapacidadClave");
        datosConsulta.setFormato("");
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloTitulo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteDatosIncluir.setReporteEstiloDetalle(reporteEstilos);
        reporteDatosIncluir.setOrden(5);
        reporteDatosIncluir.setColumna(5);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setRepiteInformacion(true);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        listReporteDatosIncluir.add(reporteDatosIncluir);
        //</editor-fold>
        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);
        //<editor-fold defaultstate="collapsed" desc="ReporteOrdenGrupo">
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        reporteOrdenGrupo = new ReporteOrdenGrupo();
        datosConsulta = new DatosConsulta();
        datosConsulta.setNombreMostrar("Descripcion");
        datosConsulta.setNombreBD("ZFM11_74_excepcion");
        datosConsulta.setColumna(0);
        datosConsulta.setTipoDato("java.lang.String");
        datosConsulta.setNombreEtiqueta("ExcepcionesDescripcion");
        datosConsulta.setFormato("");
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloGrupo(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloEncabezado(reporteEstilos);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteOrdenGrupo.setReporteEstiloPie(reporteEstilos);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        listReporteOrdenGrupo.add(reporteOrdenGrupo);
        //</editor-fold>
        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //<editor-fold defaultstate="collapsed" desc="ReporteCamposEncabezado">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setAutocompletar(false);
        reporteEstilos = new ReporteEstilos();
        reporteEstilos.setFont(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(2);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        reporteCamposEncabezado.setOrden(6);
        reporteCamposEncabezado.setFila(1);
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);
        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0057 VERIFICADOR DE FALTAS TOTALES finesoft">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("VERIFICADOR DE FALTAS TOTALES finesoft");
        reporteDinamico.setNombreAbreviado("VERIFICADOR DE FALTAS TOTALES????");
        reporteDinamico.setClave("0057");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(5);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(false);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(57);
        reporteDinamico.setUsaFiltroCorrida(true);
        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(457);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("V");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("VERIFICADOR DE FALTAS TOTALES????");
        contenedor.setOrdenId(57);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(57L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Emple.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setNombreMostrar("Depto.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP80_357_descripcion");
        datosConsulta.setNombreEtiqueta("PuestosDescripcion");
        datosConsulta.setNombreMostrar("Subdepto.??");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatInteger);
        datosConsulta.setNombreBD("@Faltas|");
        datosConsulta.setNombreEtiqueta("Faltas");
        datosConsulta.setNombreMostrar("Faltas");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.CANTIDAD);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(3);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(3);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        listReporteOrdenGrupo = new ArrayList<ReporteOrdenGrupo>();
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("");
        datosConsulta.setNombreBD("FDE10_48_clave");
        datosConsulta.setNombreEtiqueta("RazonesSocialesClave");
        datosConsulta.setNombreMostrar(" ");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(false);
        reporteOrdenGrupo.setIncluirEncabezado(false);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(2);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(3);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(4);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(6);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(5);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);

        //<editor-fold defaultstate="collapsed" desc="0058 VERIFICADOR DE VARIABLES POR CONCEPTO">
        reporteDinamico = new ReporteDinamico();
        reporteDinamico.setNombre("VERIFICADOR DE VARIABLES POR CONCEPTO");
        reporteDinamico.setNombreAbreviado("VERIF. VARIAB, X CONCEP. FINE");
        reporteDinamico.setClave("0058");
        reporteDinamico.setColumnasXPagina(1);
        reporteDinamico.setIncluirFechaActual(false);
        reporteDinamico.setMargenDerecho(1);
        reporteDinamico.setMargenInferior(1);
        reporteDinamico.setMargenIzquierdo(1);
        reporteDinamico.setMargenSuperior(1);
        reporteDinamico.setEspaciadoColumnas(5);
        reporteDinamico.setNoFilasEncabezado(5);
        reporteDinamico.setOrientacion(true);
        reporteDinamico.setTipoHoja(ReporteTipoHoja.CARTA);
        reporteDinamico.setRazonSocial(listRazonSocial.get(0));
        reporteDinamico.setReporteFuenteDatos(listReportFuenteDatos.get(0));//Verificar para cual fuente
        reporteDinamico.setIncluirTotalGeneral(true);
        reporteDinamico.setIncluirNoPagina(false);
        reporteDinamico.setUsaTodoAnchoPagina(true);
        reporteDinamico.setMostrarDetalleColumnas(true);
        reporteDinamico.setOcultarColumnas(true);
        reporteDinamico.setCortarTituloDetalle(true);
        reporteDinamico.setCortarDatoDetalle(true);
        reporteDinamico.setUsaCBB(false);
        reporteDinamico.setOrden(58);
        reporteDinamico.setUsaFiltroCorrida(true);

        ///<editor-fold defaultstate="collapsed" desc="Contenedor">
        contenedor = new Contenedor();
        contenedor.setId(458);
        contenedor.setAccion("ReporteNominas");
        contenedor.setCompartir(true);
        contenedor.setHabilitado(true);
        contenedor.setIcono(null);
        contenedor.setKeyCode("V");
        contenedor.setModifiers("CTRL");
        contenedor.setNombre("VERIF. VARIAB, X CONCEP. FINE");
        contenedor.setOrdenId(58);
        contenedor.setParentId(4);
        contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTE);
        contenedor.setVisible(true);
        contenedor.setHerramienta(listHerramienta.get(0));
        contenedor.setTipoElemento(listTipoElementos.get(1));
        contenedor.setVentana(null);
        contenedor.setIdMultiUsos(58L);
        reporteDinamico.setContenedor(contenedor);//4.- el de Reportes
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Datos a incluir">
        listReporteDatosIncluir = new ArrayList<ReporteDatosIncluir>();

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("#####");
        datosConsulta.setNombreBD("FDE2_3_clave");
        datosConsulta.setNombreEtiqueta("EmpleadosClave");
        datosConsulta.setNombreMostrar("Emple.");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(1);
        reporteDatosIncluir.setTamColumna(8);
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(1);
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDE2_6_ApellidosNombre");
        datosConsulta.setNombreEtiqueta("EmpleadosApellidosNombre");
        datosConsulta.setNombreMostrar("Nombre");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(2);//
        reporteDatosIncluir.setTamColumna(20);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(2);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(formatNumber);
        datosConsulta.setNombreBD("FDCE1_25_TotalImportePorConcepto");
        datosConsulta.setNombreEtiqueta("TotalImportePorConcepto");
        datosConsulta.setNombreMostrar("Importe");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(6);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño), 3)));
        reporteDatosIncluir.setColumna(6);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("|1234||");
        datosConsulta.setNombreBD("FDCE1_23_ValorParametro");
        datosConsulta.setNombreEtiqueta("ValorParametro");
        datosConsulta.setNombreMostrar("Cantidad");
        datosConsulta.setTipoDato(Float.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(ReporteTipoOperacion.SUMA);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta("1");
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(5);//
        reporteDatosIncluir.setTamColumna(5);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(5);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato(" ");
        datosConsulta.setNombreBD("FDP73_327_descripcion");
        datosConsulta.setNombreEtiqueta("DepartamentosDescripcion");
        datosConsulta.setNombreMostrar("Depto");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteDatosIncluir = new ReporteDatosIncluir();
        reporteDatosIncluir.setOperacion(null);
        reporteDatosIncluir.setDatosConsulta(datosConsulta);
        reporteDatosIncluir.setDatosEspecialesConsulta(null);
        reporteDatosIncluir.setReporteDinamico(reporteDinamico);
        reporteDatosIncluir.setOrden(4);//
        reporteDatosIncluir.setTamColumna(10);//
        reporteDatosIncluir.setReporteEstiloDetalle((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setReporteEstiloTitulo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteDatosIncluir.setColumna(4);//
        reporteDatosIncluir.setRepiteInformacion(true);
        listReporteDatosIncluir.add(reporteDatosIncluir);

        reporteDinamico.setReporteDatosIncluir(listReporteDatosIncluir);

        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Agrupar">             
        datosConsulta = new DatosConsulta();
        //datosConsulta.setColumna(0);//Campo transient
        datosConsulta.setFormato("###");
        datosConsulta.setNombreBD("ZFM4_35_clave");
        datosConsulta.setNombreEtiqueta("ConcepNomDefiClave");
        datosConsulta.setNombreMostrar("Clave");
        datosConsulta.setTipoDato(String.class.toString());
        datosConsulta.setCamposCombinar(null);

        reporteOrdenGrupo = new ReporteOrdenGrupo();
        reporteOrdenGrupo.setOrden(1);
        reporteOrdenGrupo.setAgrupar(true);
        reporteOrdenGrupo.setIncluirEncabezado(true);
        reporteOrdenGrupo.setIncluirPie(true);
        reporteOrdenGrupo.setDatosConsulta(datosConsulta);
        reporteOrdenGrupo.setReporteDinamico(reporteDinamico);
        reporteOrdenGrupo.setReporteEstiloEncabezado((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloGrupo((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        reporteOrdenGrupo.setReporteEstiloPie((new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño))));
        listReporteOrdenGrupo.add(reporteOrdenGrupo);

        reporteDinamico.setReporteOrdenGrupos(listReporteOrdenGrupo);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Datos Filtro">
        listReporteCamposWhere = new ArrayList<ReporteCamposWhere>();
        reporteCamposWhere = new ReporteCamposWhere();
        reporteCamposWhere.setCamposWhere("AND|ZFM4_35_clave|=|001");
        reporteCamposWhere.setReporteDinamico(reporteDinamico);
        listReporteCamposWhere.add(reporteCamposWhere);
        reporteDinamico.setReporteCamposWhere(listReporteCamposWhere);
        //</editor-fold>
        //-
        ///<editor-fold defaultstate="collapsed" desc="Encabezados Reporte">
        listReporteCamposEncabezado = new ArrayList<ReporteCamposEncabezado>();

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(3);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.TituloReporte);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.TituloReporte.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.MensajeFiltro);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.MensajeFiltro.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(5);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.RFC);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.RFC.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(4);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NoPagina);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NoPagina.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteOtrosDatosEncabezado = new ArrayList<ReporteOtrosDatosEncabezado>();
        reporteOtrosDatosEncabezado = new ReporteOtrosDatosEncabezado();
        reporteOtrosDatosEncabezado.setDatos("0");
        reporteOtrosDatosEncabezado.setReporteCamposEncabezado(reporteCamposEncabezado);
        listReporteOtrosDatosEncabezado.add(reporteOtrosDatosEncabezado);
        reporteCamposEncabezado.setReporteOtrosDatosEncabezado(listReporteOtrosDatosEncabezado);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteCamposEncabezado = new ReporteCamposEncabezado();
        reporteCamposEncabezado.setAutocompletar(false);
        reporteCamposEncabezado.setCamposCombinar(null);
        reporteCamposEncabezado.setOrden(2);
        reporteCamposEncabezado.setTipoEncabezado(TipoEncabezado.NombreEmpresa);
        reporteCamposEncabezado.setTitulo(TipoEncabezado.NombreEmpresa.name());
        reporteCamposEncabezado.setReporteDinamico(reporteDinamico);
        reporteCamposEncabezado.setFila(1);
        reporteEstilos = new ReporteEstilos(new Font(fontName, Font.PLAIN, fontTamaño));
        reporteEstilos.setAlineacion(1);
        reporteCamposEncabezado.setReporteEstiloEncabezado(reporteEstilos);
        listReporteCamposEncabezado.add(reporteCamposEncabezado);

        reporteDinamico.setReporteCamposEncabezado(listReporteCamposEncabezado);

        //</editor-fold>
        //</editor-fold>
        listReporteDinamico.add(reporteDinamico);
        return listReporteDinamico;
    }
}
