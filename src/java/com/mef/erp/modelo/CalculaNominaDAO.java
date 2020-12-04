/**
 * @author: Victor Lopez Compañía: Macropro. Descripción del programa: clase
 * CalculaNominaDAO para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Armando Fecha: 18-05-2012 Descripción: Se cambio el
 * nombre de los parametrosw de acuerdo al archivo.
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Autor: Armando Fecha: 23-07-2012 Descripción: Se cambio la
 * consulta de busqueda del configuracion IMSS. Tambien se cambio el calculo del
 * imss.
 * -----------------------------------------------------------------------------
 * Clave: JSA03 Autor: Armando Fecha: 27-08-2012 Descripción: Se agrego el
 * metodo calculo imss patronal para el calculo patronal del IMSS.
 * -----------------------------------------------------------------------------
 * Clave: JSA04 Autor: Armando Fecha: 21/01/2013 Descripción: Se agrego la
 * programacion para obtener la ZonaGeografica. Se cambio el tipo de dato que se
 * le envia del tipo de contrato. Se mejoro el calculo del SDI. Se le agrego
 * para solo calcular el SDI y no guarde los datos.
 * -----------------------------------------------------------------------------
 * Clave: JSA05 Autor: Armando Fecha: 11/02/2013 Descripcion: Se cambiaron unos
 * nombre de las variables de los factores de integracion para mas entendimiendo
 * para identificanrlos.
 * -----------------------------------------------------------------------------
 * Clave: JSA07 Autor: Armando Fecha: 08/04/2013 Descripcion: Se agrego esta
 * linea ya que cuando habia mas de un empleado se acumulaban los valores.
 * -----------------------------------------------------------------------------
 * Clave: JSA08 Autor: Armando Fecha: 08/04/2013 Descripcion: La variable
 * "DiasPago" se le agrego el codigo para descontar dias de ausencias. Se le
 * agrego el filtro de periodicidad al parametro de ¿Pagar nomina sobre dias
 * naturales?. Tambien se le quito el codigo para pasar en automatico al modo 3
 * cuando sean quincenales o mensuales.
 * -----------------------------------------------------------------------------
 * Clave: JSA09 Autor: Armando Fecha: 23/05/2013 Descripcion: En el metodo
 * guardarIsrRetenidos se modifico para que regresara tanto el acumulado de un
 * mes como el acumulado de un periodo hacia atras del mismo año. Se le agrego
 * un "+ 1" en el metodo cargarVariablesGlobalesEmpleadoPorPlaza ya que cuando
 * entraba a este metodo cantidadDiasEntreDosFechas le faltaba 1 dia. Se
 * modifico la consulta de la configuracionIMSS para cuando no existiera dicha
 * configuracion buscara por la mas cercana a la fecha. Cuando busca el XML de
 * la tabla ISR se cambio el metodo con el que busca de
 * construyeTablaXmlPorTipoNomina a generaTablasXml. Se cambio la consulta del
 * metodo calculaImss y calculaImssPatronal. Se cambio el metodo
 * calculoDiasTranscurridos por cambios del modo 4.
 * -----------------------------------------------------------------------------
 * Clave: JSA10 Autor: Armando Fecha: 30/05/2013 Descripcion: Se cambio la
 * consulta para obtener los dias para elevar al mes, ahora derivan desde
 * movimientos de nomina antes estaban desde periodos de nomina.
 * -----------------------------------------------------------------------------
 * Clave: JSA11 Autor: Armando Fecha: 26/06/2013 Descripcion: Se agrego el
 * llamado al metodo InicializaValoresPeriodoNomina para llenar los valores de
 * las variables respectivas.
 * -----------------------------------------------------------------------------
 * Clave: JSA12 Autor: Armando Fecha: 09/08/2013 Descripcion: Se modifico para
 * que el calculo contemple la fecha de baja del finiquito o liquidacion.
 * Tambien se modifico para que se calcule el finiquito. Se modifico la variable
 * diasvacacionesPorDisfrutar para que tambien permitiera que sea proporcional.
 * -----------------------------------------------------------------------------
 * Clave: JSA13 Autor: Armando Fecha: 07/03/2014 Descripcion: se modifico la
 * consulta obtenerPlazasPorEmpleados ya que no me traia las
 * plazasporEmpleadoMov que huvo en un rango de fechas es decir entre la fecha
 * inicial y final del periodo de nomina.
 * -----------------------------------------------------------------------------
 * Clave: JSA14 Autor: Armando Fecha: 26/09/2014 Descripcion: Se agrego el
 * metodo obtenerConceptosUnicos ya que la comparacion se estaba haciendo sobre
 * el movimiento nomina y no sobre el concepto por lo cual cuando habia
 * movimientos repetidos la condicion no se cumplia. Tambien se realizo un
 * ajuste cuando se asigna el mes.
 * -----------------------------------------------------------------------------
 * Clave: JSA15 Autor: Armando Fecha: 15/10/2014 Descripcion: Se agrego la
 * aplicarMascara para ya se truncar o redonde los importes de los conceptos.
 * -----------------------------------------------------------------------------
 * Clave: JSA16 Autor: Armando Fecha: 03/12/2014 Descripcion: Se agrego
 * programacion para obtener los conceptos de ISRACargo y subsidioAlEmpleado.
 * Tambien se agrego el setCalculo ya que no se habia agregado, el cual te
 * agrega el resultado tal y como fue calculo sin aplicarle el redondeo.
 * -----------------------------------------------------------------------------
 * Clave: JSA17 Autor: Armando Fecha: 04/12/2014 Descripcion: Se hizo ajustes
 * por que se ocupaba que el ISRACargo y el SubsidioAlEmpleo tengan los datos
 * correctos proporcionados.
 * -----------------------------------------------------------------------------
 * Clave: JSA18 Autor: Armando Fecha: 04/12/2014 Descripcion: Se agrego en la
 * consulta del metodo IsrRetenidos para que me tragiera el ISR rentenido de
 * cuando da subsidio
 * -----------------------------------------------------------------------------
 * Clave: JSA19 Autor: Armando Fecha: 05/12/2014 Descripcion: Se agrego la
 * variable retenidosISRACargoYSubsidioAlEmpleoEn2Meses para cuando un periodo
 * abarque 2 meses se guarde los valores retenidos de ISRACargo y
 * SubsidioAlEmpleo del movimientos que se esta calculando ya que cuando me
 * traigo el ISR retenido guardado no me contempla el movimento recien
 * calculado.
 * -----------------------------------------------------------------------------
 * Clave: JSA20 Autor: Armando Fecha: 18/04/2015 Descripcion: Se corregio el
 * problema de obtener dias pago cuando un periodo abarca 2 meses.
 * -----------------------------------------------------------------------------
 * Clave: JSA21 Autor: Armando Fecha: 24/04/2015 Descripcion: No se habia
 * considerado el generar 2 movimientos de nomina en los creditos y ahorro
 * cuando en el periodo abarque 2 meses.
 * -----------------------------------------------------------------------------
 * Clave: JSA22 Autor: Armando Fecha: 11/05/2015 Descripcion:Se realizo un
 * ajuste ya que cuando ya existian movimientos y estos abarcaban 2 meses no se
 * volvia a generar el movimientos respectivo mes primer o segundo mes.
 * -----------------------------------------------------------------------------
 * Clave: JSA23 Autor: Armando Fecha: 11/05/2015 Descripcion:Se renombro la
 * tabla de ISRRetenido a CalculoISR para tener todos los calculos cerca y tener
 * todo lo relacionado con el resultado del calculo de la nomina en la misma
 * nomenclatura del calculo...
 *
 * Clave: JSA24 Autor: Armando Fecha: 03/06/2015 Descripcion:Se le corrigio para
 * no aplicar la tabla ISR mensual cuando se obtiene el Tipo ISR= ISRAnual.
 * Tambien se corrigio el proceso del obtener el ISRAnual.
 * -----------------------------------------------------------------------------
 * Clave: JSA25 Autor: Armando Fecha: 06/06/2015 Descripcion: Se agrego codigo
 * para guardar los calculos de unidades. Tambien se ajusto la programacion para
 * cuando existen promociones durante un periodo que abarque 2 meses.
 * -----------------------------------------------------------------------------
 * Clave: JSA26 Autor: Armando Fecha: 02/07/2015 Descripcion: Se comento estas
 * lineas getSession().clear() ya que cuando son varios empleados con varios
 * movimientos en algunos casos no me guardaba correctamente la informacion.
 * -----------------------------------------------------------------------------
 * Clave: JSA27 Autor: Armando Fecha: 02/07/2015 Descripcion: Se agrego codigo
 * para cuando se agregan bases afecta al concepto y ya existan movimientos los
 * actualice.
 * -----------------------------------------------------------------------------
 * Clave: JSA28 Autor: Armando Fecha: 07/07/2015 Descripcion: Se corrigio el
 * problema de las bases afecta cuando el concepto es deduccion, aparte cuando
 * obtienes los acumulados tambien se tienen que disminuir los acumulados de
 * deduccion.
 * -----------------------------------------------------------------------------
 * Clave: JSA29 Autor: Armando Fecha: 10/09/2015 Descripcion: se añadio
 * validacion para cuando el tipo de nomina sea diario ya que nos marcaba error
 * por que nos traia mas de un periodo.
 * -----------------------------------------------------------------------------
 * Clave: JSA30 Autor: Armando Fecha: 23/09/2015 Descripcion: se añadio un
 * parametros a los metodos de cargaValoresDiasCotizados y cargaValoresDiasPago
 * ya que cuando se cargaban los dias pago al llamar el
 * cargaValoresDiasCotizados este me lo volvia a modificar en las entidades de
 * CalculoUnidad.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.compilador.CompEjec;
import com.mef.compilador.Reg_Polish;
import com.mef.erp.modelo.entidad.AguinaldoConfiguracion;
import com.mef.erp.modelo.entidad.AguinaldoFechas;
import com.mef.erp.modelo.entidad.AguinaldoPagos;
import com.mef.erp.modelo.entidad.Asistencias;
import com.mef.erp.modelo.entidad.BaseAfecConcepNom;
import com.mef.erp.modelo.entidad.CalculoIMSS;
import com.mef.erp.modelo.entidad.CalculoIMSSPatron;
import com.mef.erp.modelo.entidad.CalculoISR;
import com.mef.erp.modelo.entidad.CalculoUnidades;
import com.mef.erp.modelo.entidad.CentroDeCosto;
import com.mef.erp.modelo.entidad.ClasificadorParametro;
import com.mef.erp.modelo.entidad.ConcepNomDefi;
import com.mef.erp.modelo.entidad.ConceptoPorTipoCorrida;
import com.mef.erp.modelo.entidad.ConfiguracionIMSS;
import com.mef.erp.modelo.entidad.CreditoAhorro;
import com.mef.erp.modelo.entidad.CreditoMovimientos;
import com.mef.erp.modelo.entidad.CreditoPorEmpleado;
import com.mef.erp.modelo.entidad.Cruce;
import com.mef.erp.modelo.entidad.DatosEspeciales;
import com.mef.erp.modelo.entidad.DatosQuery;
import com.mef.erp.modelo.entidad.Departamentos;
import com.mef.erp.modelo.entidad.DesgloseInternoISR;
import com.mef.erp.modelo.entidad.DiasAguinaldo;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.FactorIntegracion;
import com.mef.erp.modelo.entidad.FiniquitosLiquidaciones;
import com.mef.erp.modelo.entidad.IngresosReingresosBajas;
import com.mef.erp.modelo.entidad.Isr;
import com.mef.erp.modelo.entidad.ManejoHorasPor;
import com.mef.erp.modelo.entidad.ManejoSalarioDiario;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomBaseAfecta;
import com.mef.erp.modelo.entidad.MovNomConceParam;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.Naturaleza;
import com.mef.erp.modelo.entidad.ParaConcepDeNom;
import com.mef.erp.modelo.entidad.Parametros;
import com.mef.erp.modelo.entidad.ParametrosExtra;
import com.mef.erp.modelo.entidad.Periodicidad;
import com.mef.erp.modelo.entidad.PeriodosNomina;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.PtuDatosGenerales;
import com.mef.erp.modelo.entidad.PtuEmpleados;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroIncapacidad;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import com.mef.erp.modelo.entidad.SalariosIntegradosDet;
import com.mef.erp.modelo.entidad.Subsidio;
import com.mef.erp.modelo.entidad.TablaBase;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.Tipo;
import com.mef.erp.modelo.entidad.TipoAccionMascaras;
import com.mef.erp.modelo.entidad.TipoControlador;
import com.mef.erp.modelo.entidad.TipoCorrida;
import com.mef.erp.modelo.entidad.TipoDatoExcepcion;
import com.mef.erp.modelo.entidad.TipoNodoConsulta;
import com.mef.erp.modelo.entidad.TipoNomina;
import com.mef.erp.modelo.entidad.TipoTablaISR;
import com.mef.erp.modelo.entidad.TiposMovimiento;
import com.mef.erp.modelo.entidad.TiposVacaciones;
import com.mef.erp.modelo.entidad.VacacionesAplicacion;
import com.mef.erp.modelo.entidad.ValorTablaISR;
import com.mef.erp.modelo.entidad.ZonaGeografica;
import com.mef.erp.modelo.entidad.ZonaSalarial;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.modelo.entidad.cfdi.StatusTimbrado;
import com.mef.erp.util.HibernateUtil;
import com.mef.erp.util.UtilidadesXML;
import com.mef.erp.util.ConstruyeQueries;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.Utilerias;
import com.mef.funciones.ClavesParametrosModulos;
import com.mef.funciones.DatosConceptosNomina;
import com.mef.funciones.RoundingANumber;
import com.mef.funciones.TipoClasificacionFormula;
import com.sun.org.apache.xerces.internal.dom.DeferredTextImpl;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.exception.LockAcquisitionException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class CalculaNominaDAO extends GenericHibernateDAO<CalculoISR, Long>
        implements CalculaNominaDAOIF {

    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*
     * util.getContexto()
     */).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private Object[][] tablaIsr;
    private Object[][] tablaFactorIntegracion;
    private Object[][] tablaSubsidio;
    private Object[][] tablaZonaSalarial;
    private Object matrixcargaXml[][] = null;
    public static Document docXML;
    public static Element root;
    private List<ConcepNomDefi> filtroConceptosNomina;
    private List<MovNomConcep> filtroMovimientosNominas;
    private StringBuilder strQuery = new StringBuilder();
    private StringBuilder strWhere = new StringBuilder();
    private List<String> camposParametro;
    private List<Object> valoresParametro;
    private Double salarioMinimoDF;
    private ConfiguracionIMSS configuracionIMSS;
    private ZonaGeografica zonaGeografica = null;
    private CompEjec compEjec = new CompEjec();
    private CompEjec.Compilador compilador = compEjec.generaInstanciaCompilador();
    private HashMap valoresConceptosGlobales;
    private HashMap valoresConceptosEmpleados;
    private Calendar fechaActual;
    private Calendar fechaBajaFiniq;
    private PeriodosNomina periodosNomina = null;
    private PtuDatosGenerales ptuDatosGenerales = null;
    private PtuEmpleados ptuEmpleado = null;
    private double isrNormal, isrDirecto, isrAnual, isrNormalSinAjustar, isrACargoNormalSinAjustar, subsidioAlEmpleoNormalSinAjustar;
    private Double factorMensual = 0.0, factorAnual = 0.0;
    private double acumuladoNormal, acumuladoDirecto, acumuladoAnual, acumuladoImssFijo;
    private boolean calculoSeparadoISR = false, isMov2Meses, usaFiniquitos;
    private TipoTablaISR tipoTablaISR = null;
    private Double diasPago = 0.0;
    private int modoAjustarIngresosMes;
    private int versionCalculoPrestamoAhorro = 1;
    private String[][] variablesConceptos = null;
//////    private Periodicidad periodicidadAnual = null;
    private Periodicidad periodicidadTipoNomina = null;
    private Boolean manejaPagosPorHora = null;
    private Boolean manejaPagoDiasNaturales = false;
    private Boolean manejaPagoIMSSDiasNaturales = false;
    private boolean descontarFaltasModoAjustaMes = false;
    private ManejoHorasPor manejoHorasPor = null;
    private ManejoSalarioDiario manejoSalarioDiario = null;
    private CalculoISR retenido = null;//JSA23
    private CentroDeCosto centroDeCostoMovimiento = null;
    private List<DatosEspeciales> datosFormulas = null;
    private Properties propertieFuente = null;
    private TipoCorrida tipoCorrida = null;
    private RazonesSociales razonesSociales = null;
    private int tipoPantallaSistema = 100;
    private int cantidadSaveUpdate = 0, cantidadFlush = 50;
//    private MovNomConcep movNomConcepSubsidio = null;
    private List<MovNomConcep> listMovNomConcepSubsidio = null;
    private List<MovNomConcep> listMovNomConcepISRCARGO = null;//JSA16                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
    private List<MovNomConcep> listMovNomConcepSUBSIDIOALEMPLEO = null;//JSA16
    private MovNomConcep movNomConcepAjustePorRedondeo = null;
    private List<CalculoIMSS> listCalculoIMSS = null;
    private CalculoIMSSPatron calculoIMSSPatron = null;
    private CalculoISR iSRRetenido = null;//Para el concepto ISR JSA23
    private CalculoISR iSRRetenidoSubsidio = null;//Para el concepto subsidio JSA23
    private FiniquitosLiquidaciones finiquitosLiquidaciones = null;
    private List<String> camposFiniquitos;
    private List<Object> valoresCamposFiniquitos;
    private String valoresDatosEspecialesFormula;
    List<PlazasPorEmpleadosMov> filtroPlazasPorEmpleadosMov;
    private PlazasPorEmpleadosMov plazaEmpleadoaguinaldo = new PlazasPorEmpleadosMov();
    private IngresosReingresosBajas ingresosReingresosBajas = null;
    private String[] mascaraResultadoGral = new String[]{"#########", "##"};
    private Double factorRedondeoGral = .01, minimoRedondeoGral = .005;
    private TipoAccionMascaras tipoAccionMascarasGral = TipoAccionMascaras.Redondear;
    private double importeRedondeo = 0.0;
    private List<TablaDatos> tablaDatosXml = new ArrayList<TablaDatos>();
    private List<DiasAguinaldo> diasAguinaldo = new ArrayList<DiasAguinaldo>();
    private AguinaldoConfiguracion aguiConfiguracion = new AguinaldoConfiguracion();
    private List<AguinaldoFechas> aguiFechas = new ArrayList<AguinaldoFechas>();
//////    //Se usa esta variable para guardar los retenidos cuando un periodo abarca 2 meses y estos aun no han sido guardados(commit).
//////    double[] retenidosISRACargoYSubsidioAlEmpleoEn2Meses;//JSA19
    private boolean isCalculoSDI = false;
    private boolean isCalculoPTU = false;
    private boolean isCalculoAgui = false;
    //intentos para intentar obtener dato en tabla bloqueada
    private int contLockAcquisition = 0;
    private int pagarPrimero3Dias = 0;
    private double descontarDiasPago = 0.0;
    private int totalPagosAgui = 0;
    private static int ProporcionaPeriodoIndependiente = 1, ProporcionaPeriodoAjustadoMes = 2, PropPeriodoIndepDiasNaturales = 3,
            PropPeriodoIndepAjustadoAlUltimoPeriodoMes = 4, UltimoPeriodoSinAjustarMes = 5, ProporcionaTablaAnual = 6;
    private final List<String> variablesAjustadasEnDosPeriodos = new ArrayList<String>(Arrays.asList("Laborado".toUpperCase(), "HorasExtrasDobles".toUpperCase(), "HorasExtrasTriples".toUpperCase(), "IncapacidadEnfermedad".toUpperCase(), "IncapacidadAccidente".toUpperCase(), "IncapacidadMaternidad".toUpperCase(), "OtrasIncapacidad".toUpperCase(), "DiasIncapacidadEmpleado".toUpperCase(), "Faltas".toUpperCase(), "Ausentismo".toUpperCase(),
            "TExtrasDiaDescanso".toUpperCase(), "TExtrasDiaFestivo".toUpperCase(), "TExtrasDiaDomingo".toUpperCase(), "Retardos".toUpperCase(), "PermisoConSueldo".toUpperCase().toUpperCase(), "PermisoSinSueldo".toUpperCase(), "DiasFestivos".toUpperCase(), "DiasDescanso".toUpperCase(), "diasVacaciones".toUpperCase(), "diasPrima".toUpperCase(), "DiasNormalesAPagar".toUpperCase(), "DiasPago".toUpperCase(), "DiasCotizados".toUpperCase(),
            "ISRNeto".toUpperCase(), "ISRSubsidio".toUpperCase(), "ISRNeto".toUpperCase(), "ISRSubsidio".toUpperCase(), "ISRACARGO".toUpperCase(), "SubsEmpleoCalculado".toUpperCase()));
    //////"fechaSalidaVacaciones","fechaRegresoVacaciones", "tipoVacaciones", 

//////    private Date inicioTransaccion;
    public Mensaje calculaNomina(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina,
            String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago,
            String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra,
            int ejercicioActivo, String uuidCxn, String uuidCxnMaestra) {
        valoresDatosEspecialesFormula = "";
        contLockAcquisition = 0;
        Boolean band = true;
        isCalculoSDI = false;
        filtroPlazasPorEmpleadosMov = null;
        mensajeResultado = new Mensaje();
        mensajeResultado.setError("");
        mensajeResultado.setNoError(0);
        try {
            factorMensual = 0.0;
            factorAnual = 0.0;
            acumuladoNormal = 0;
            acumuladoDirecto = 0;
            acumuladoAnual = 0;
            acumuladoImssFijo = 0;
            isrNormal = 0;
            isrDirecto = 0;
            isrAnual = 0;
            isrNormalSinAjustar = 0;
            isrACargoNormalSinAjustar = 0;
            subsidioAlEmpleoNormalSinAjustar = 0;
            listMovNomConcepSubsidio = new ArrayList<MovNomConcep>();
            listMovNomConcepISRCARGO = new ArrayList<MovNomConcep>();
            listMovNomConcepSUBSIDIOALEMPLEO = new ArrayList<MovNomConcep>();
            listCalculoIMSS = null;
            calculoIMSSPatron = null;
            iSRRetenido = null;
            iSRRetenidoSubsidio = null;
            importeRedondeo = 0.0;
            valoresConceptosGlobales = new HashMap();
            valoresConceptosEmpleados = new HashMap();
            modoAjustarIngresosMes = -1;
            tipoTablaISR = null;
            manejoHorasPor = null;
            manejaPagoDiasNaturales = false;
            manejaPagoIMSSDiasNaturales = false;
            manejoSalarioDiario = null;
            manejaPagosPorHora = null;
            fechaActual = getFechaDelSistema();
            fechaActual.set(Calendar.YEAR, ejercicioActivo);
            cantidadSaveUpdate = 0;
            fechaBajaFiniq = null;
            finiquitosLiquidaciones = null;
            descontarFaltasModoAjustaMes = false;
//////            retenidosISRACargoYSubsidioAlEmpleoEn2Meses = null;
//            agregaronPlazaPorEmpleadoRestantes = false;
            List<CreditoAhorro> listCreditoAhorro;
            List<MovNomConcep> filtroMovimientosNominasCreditosAhorro;
            List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoActivo;
            List<MovNomConcep> formulaDedudCreditos;
            List<MovNomConcep> formulaDedudAhorros;
            if (parametrosExtra != null) {
                if (parametrosExtra.getFechaCalculoFiniq() != null) {
                    fechaActual.setTime(parametrosExtra.getFechaCalculoFiniq());
                }
                if (parametrosExtra.getFechaBajaFiniq() != null) {
                    fechaBajaFiniq = Calendar.getInstance();
                    fechaBajaFiniq.setTime(parametrosExtra.getFechaBajaFiniq());
                }
                if (parametrosExtra.getValoresExtras() != null) {
                    for (int i = 0; i < parametrosExtra.getValoresExtras().size(); i++) {
                        if (parametrosExtra.getValoresExtras().get(i) instanceof FiniquitosLiquidaciones) {
                            finiquitosLiquidaciones = (FiniquitosLiquidaciones) parametrosExtra.getValoresExtras().get(i);
                        }
                    }
                }
                if (parametrosExtra.getMascaraResultado() != null) {
                    mascaraResultadoGral = parametrosExtra.getMascaraResultado();
                    if (mascaraResultadoGral[1].length() > 0) {
                        String factorString = ".", minimunString = ".";
                        for (int i = 0; i < mascaraResultadoGral[1].length() - 1; i++) {
                            factorString += "0";
                        }
                        minimunString = factorString;
                        factorString += "1";
                        minimunString += "05";
                        factorRedondeoGral = Double.valueOf(factorString);
                        minimoRedondeoGral = Double.valueOf(minimunString);
                    }
                }
                if (parametrosExtra.getTipoAccionMascaras() != null) {
                    tipoAccionMascarasGral = parametrosExtra.getTipoAccionMascaras();
                }
            }
            Calendar cIni = Calendar.getInstance(), cFin = Calendar.getInstance();
            if (parametrosExtra.getFechaInicioPeriodo() == null | parametrosExtra.getFechaFinPeriodo() == null) {
                parametrosExtra.setFechaInicioPeriodo(fechaActual.getTime());
                parametrosExtra.setFechaFinPeriodo(fechaActual.getTime());
            }
            cIni.setTime(parametrosExtra.getFechaInicioPeriodo());
            cFin.setTime(parametrosExtra.getFechaFinPeriodo());
            buscaPeriodicidadesOrPeriodosNomina(claveTipoNomina, claveTipoCorrida, idPeriodoNomina, fechaActual, uuidCxn);
            buscaCalculoPTU(claveRazonSocial, ejercicioActivo, uuidCxn);

            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }
            generaTablasXml(controlador, periodicidadTipoNomina, claveRazonSocial, periodosNomina.getFechaFinal(), ejercicioActivo, uuidCxnMaestra); //mefmaster
            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }

            setSession(HibernateUtil.currentSession(uuidCxn));  //crea conexion
            getSession().beginTransaction();
            cargarVariablesConceptosCompilador();
            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }
            cargarVariablesGlobales(claveTipoNomina, claveTipoCorrida, clavePuesto, claveCategoriasPuestos, claveTurno, claveRazonSocial,
                    claveRegPatronal, claveDepto, claveCtrCosto);
            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }

            valoresConceptosGlobales.put(parametroFechaInicial, cIni);
            valoresConceptosGlobales.put(parametroFechaFinal, cFin);

            inicializaValoresPeriodoNomina(periodosNomina);
            cargaDatosCalculoIMSS(periodosNomina.getFechaCierre());
            idPeriodoNomina = periodosNomina.getId();
            listCreditoAhorro = obtenerCreditosAhorro(razonesSociales);
            consultarConfiguracionAgui();
            if (claveTipoCorrida.equalsIgnoreCase("AGI")) {
                Date fechaIniPer = quitaHrsDeFecha(periodosNomina.getFechaInicial());
                Date fechaFinPer = quitaHrsDeFecha(periodosNomina.getFechaFinal());
                for (int i = 0; i < aguiFechas.size(); i++) {
                    Date fechaAgui = quitaHrsDeFecha(aguiFechas.get(i).getFechaProgramada());
                    if ((fechaAgui.compareTo(fechaIniPer) == 0 || fechaAgui.compareTo(fechaIniPer) > 0)
                            && (fechaAgui.compareTo(fechaFinPer) == 0 || fechaAgui.compareTo(fechaFinPer) < 0)) {
                        isCalculoAgui = true;
                        break;
                    }

                }
                if (!isCalculoAgui) {
                    return mensajeResultado;
                }
            }
            filtroPlazasPorEmpleadosMov = obtenerPlazasPorEmpleados(claveEmpIni, claveEmpFin, claveTipoNomina, clavePuesto, claveCategoriasPuestos, claveTurno, claveRazonSocial, claveRegPatronal, claveDepto, claveCtrCosto, tipoSalario, tipoContrato, status, claveTipoCorrida, claveFormaDePago, parametrosExtra.getFechaInicioPeriodo(), parametrosExtra.getFechaFinPeriodo());

            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }
            if (filtroPlazasPorEmpleadosMov != null) {
                int i;
//                Utilerias.bitacora("inicia empelados " + filtroPlazasPorEmpleadosMov.size());
                for (i = 0; i < filtroPlazasPorEmpleadosMov.size(); i++) {
//                    Utilerias.bitacora("empelados no: " + i);
////////                    if (i % 100 == 0) {
////////                        System.out.println("empelados no: " + i);
////////                    }
                    if (i > 0) {//JSA07
                        if (!filtroPlazasPorEmpleadosMov.get(i - 1).getPlazasPorEmpleado().getEmpleados().getClave().equalsIgnoreCase(filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado().getEmpleados().getClave())
                                || (filtroPlazasPorEmpleadosMov.get(i - 1).getPlazasPorEmpleado().getEmpleados().getClave().equalsIgnoreCase(filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado().getEmpleados().getClave()) && !filtroPlazasPorEmpleadosMov.get(i - 1).getPlazasPorEmpleado().getClave().equalsIgnoreCase(filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado().getClave()))) {
                            acumuladoNormal = 0.0;
                            acumuladoDirecto = 0.0;
                            acumuladoAnual = 0.0;
                            acumuladoImssFijo = 0.0;
                            listMovNomConcepSubsidio.removeAll(listMovNomConcepSubsidio);
                            listMovNomConcepISRCARGO.removeAll(listMovNomConcepISRCARGO);
                            listMovNomConcepSUBSIDIOALEMPLEO.removeAll(listMovNomConcepSUBSIDIOALEMPLEO);
                            listCalculoIMSS = null;
                            calculoIMSSPatron = null;
                            iSRRetenido = null;
                            iSRRetenidoSubsidio = null;
                            importeRedondeo = 0.0;
////////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses = null;
                            valoresConceptosGlobales.put(parametroFechaInicial, cIni);
                            valoresConceptosGlobales.put(parametroFechaFinal, cFin);
                            if (!filtroPlazasPorEmpleadosMov.get(i - 1).getPlazasPorEmpleado().getEmpleados().getClave().equalsIgnoreCase(filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado().getEmpleados().getClave())) {
                                obtenerIngresosReingresosBajas(filtroPlazasPorEmpleadosMov.get(i));
//                                agregaronPlazaPorEmpleadoRestantes = false;
                            }
                            valoresConceptosEmpleados.clear();
                            if (mensajeResultado.getNoError() != 0) {
                                break;
                            }
                        }
                    } else {
                        obtenerIngresosReingresosBajas(filtroPlazasPorEmpleadosMov.get(i));
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }
                    }
                    ptuEmpleado = null;
                    if (isCalculoPTU) {
                        buscaEmpleadoPTU(claveRazonSocial, filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado().getEmpleados().getClave());
                    }
                    valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
                    List<CalculoUnidades> listCalculoUnidades = obtenerListCalculoUnidadesUtilizar(claveRazonSocial, filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado(), claveTipoNomina, periodosNomina.getId(), claveTipoCorrida);
                    List<Long> ids = new ArrayList<Long>();
                    for (int j = 0; j < listCalculoUnidades.size(); j++) {
                        //getSession().delete(listCalculoUnidades.get(j));
                        if (listCalculoUnidades.get(j).getId() != null) {
                            ids.add(listCalculoUnidades.get(j).getId());
                            listCalculoUnidades.set(j, new CalculoUnidades(listCalculoUnidades.get(j)));
                        }
//                        listCalculoUnidades.get(j).setId(null);
                    }
                    if (ids.size() > 0) {
                        deleteListQuery(CalculoUnidades.class.getSimpleName(), "id", ids.toArray());
                        getSession().flush();
                        getSession().clear();
//                        for (int j = 0; j < listCalculoUnidades.size(); j++) {
//                            listCalculoUnidades.set(j, new CalculoUnidades(listCalculoUnidades.get(j)));
//                        }
                    }
//                    System.out.println("clear()");
                    cargarVariablesGlobalesEmpleadoPorPlaza(filtroPlazasPorEmpleadosMov.get(i), true, true, TipoSueldos.SUELDODIARIOFINAL, listCalculoUnidades.get(0), null, false, null);//JSA30
                    boolean continueProsesoCal = true;
                    if (tipoCorrida.getClave().equals("VAC")) {
                        Integer diasVac = (int) valoresConceptosEmpleados.get("DiasVacaciones".toUpperCase());
                        Double diasPrima = (Double) valoresConceptosEmpleados.get("diasPrima".toUpperCase());
                        if ((diasVac == null || diasVac == 0) && (diasPrima == null || diasPrima == 0.0)) {
                            continueProsesoCal = false;
                        }
                    }
                    if (continueProsesoCal) {
                        cargaDatosVariableConfiguracionIMSS(periodosNomina.getFechaFinal());
                        if (isCalculoAgui) {
                            if (!diasAguinaldo.isEmpty()) {
                                Integer antiguedad = (Integer) valoresConceptosEmpleados.get("Antiguedad".toUpperCase());
                                for (int j = 0; j < diasAguinaldo.size(); j++) {
                                    Integer ant = diasAguinaldo.get(j).getAntiguedad().intValue();
                                    if (ant == antiguedad) {
                                        valoresConceptosEmpleados.put("FactorDiasAguinaldo".toUpperCase(), diasAguinaldo.get(j).getDias());
                                        break;
                                    } else if (antiguedad < ant) {
                                        valoresConceptosEmpleados.put("FactorDiasAguinaldo".toUpperCase(), diasAguinaldo.get(j - 1).getDias());
                                        break;
                                    }
                                }

                            }
                            Float sueldoFinal = (Float) valoresConceptosEmpleados.get("SUELDODIARIOFINAL".toUpperCase());
                            Double diasAginaldo = (Double) valoresConceptosEmpleados.get("FactorDiasAguinaldo".toUpperCase());
                            Double aguinaldoTotal = sueldoFinal * (diasAginaldo == null ? 0.0 : diasAginaldo);
                            valoresConceptosEmpleados.put("ImporteAguinaldo".toUpperCase(), aguinaldoTotal);
                        }
                        try {
                            getSession().saveOrUpdate(listCalculoUnidades.get(0));
                        } catch (Exception ex) {
                            mensajeResultado.setError(ex.getMessage());
                            mensajeResultado.setNoError(222);
                        }

                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }
                        //if (!soloPrestamos) {
//                    boolean usaNominaAsimiladosAsalarios = filtroPlazasPorEmpleadosMov.get(i).getTipoNomina().isAsimiladosAsalarios();
                        if (claveTipoCorrida.equalsIgnoreCase("FIN") || claveTipoCorrida.equalsIgnoreCase("LIQ")) {
                            filtroMovimientosNominas = new ArrayList<MovNomConcep>(0);
                            filtroMovimientosNominas.addAll(obtenerMovimientosPlazasFiniquitos(claveTipoCorrida, claveTipoNomina, idPeriodoNomina, filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado(), claveCtrCosto, claveRazonSocial, uso));
                        } else {
                            obtenerMovimientosNominaPorPlaza(claveTipoCorrida, claveTipoNomina, periodosNomina.getId(), filtroPlazasPorEmpleadosMov.get(i).getPlazasPorEmpleado(), claveCtrCosto, claveRazonSocial);
                        }
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }
                        Object[] listMovTmp = obtenerMovimientosNominaCreditoAhorro(listCreditoAhorro);
                        filtroMovimientosNominasCreditosAhorro = (List<MovNomConcep>) listMovTmp[0];
                        listMovNomConcepCreditosAhorroDescuentoActivo = (List<MovNomConcep>) listMovTmp[1];
                        formulaDedudCreditos = (List<MovNomConcep>) listMovTmp[2];
                        formulaDedudAhorros = (List<MovNomConcep>) listMovTmp[3];
                        obtenerMovimientosNominaISRACargoYSubsidioAlEmpleado();////JSA16
                        filtroMovimientosNominas.removeAll(listMovNomConcepISRCARGO);
                        filtroMovimientosNominas.removeAll(listMovNomConcepSUBSIDIOALEMPLEO);
//                    if (i == 0) {
//                        System.out.println("numero movimientos " + filtroMovimientosNominas.size());
//                    }

                        ejecutaConceptosPorMovimientoNomina(filtroMovimientosNominas, claveTipoCorrida, filtroPlazasPorEmpleadosMov.get(i), i, listCalculoUnidades);
                        if (isCalculoAgui) {
                            plazaEmpleadoaguinaldo = filtroPlazasPorEmpleadosMov.get(i);
                            calcularAguinaldo();
                        }
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }

                        if (ptuEmpleado != null) {
                            getSession().saveOrUpdate(ptuEmpleado);
                        }
                        Calendar x = Calendar.getInstance();
                        x.setTime(periodosNomina.getFechaFinal());
                        Object percepcion, deduccion;
                        percepcion = movimientosAcumuladoPorRangoMeses(TipoClasificacionFormula.DATOPERIODO, x, MovNomConcep.class.getSimpleName().concat(".resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")}, new Object[]{Naturaleza.PERCEPCION}, TipoMostrarCampo.SUMA, null, null);
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }
                        deduccion = movimientosAcumuladoPorRangoMeses(TipoClasificacionFormula.DATOPERIODO, x, MovNomConcep.class.getSimpleName().concat(".resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")}, new Object[]{Naturaleza.DEDUCCION}, TipoMostrarCampo.SUMA, null, null);
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }

                        for (int j = 0; j < filtroMovimientosNominasCreditosAhorro.size(); j++) {
                            if (filtroMovimientosNominasCreditosAhorro.get(j).getResultado() != null ? filtroMovimientosNominasCreditosAhorro.get(j).getResultado() > 0 : false) {
                                if (filtroMovimientosNominasCreditosAhorro.get(j).getConcepNomDefi().getNaturaleza().equals(Naturaleza.PERCEPCION)) {
                                    percepcion = Double.parseDouble(percepcion.toString()) - filtroMovimientosNominasCreditosAhorro.get(j).getResultado();
                                } else if (filtroMovimientosNominasCreditosAhorro.get(j).getConcepNomDefi().getNaturaleza().equals(Naturaleza.DEDUCCION)) {
                                    deduccion = Double.parseDouble(deduccion.toString()) - filtroMovimientosNominasCreditosAhorro.get(j).getResultado();
                                }
                            }
                        }
                        boolean continuarProcesos = true;
                        if ((Double.parseDouble(percepcion.toString()) - Double.parseDouble(deduccion.toString())) <= 0 | filtroMovimientosNominasCreditosAhorro.isEmpty()) {
                            continuarProcesos = false;
                        }
                        if (movNomConcepAjustePorRedondeo != null) {
                            double importeRedondeoTmp = importeRedondeo;
                            if (importeRedondeoTmp < 0) {
                                importeRedondeoTmp = -(importeRedondeoTmp);
                            }
                            importeRedondeoTmp = aplicarMascara(null, importeRedondeoTmp, true);
//                        System.out.println("importeRedondeoTmp " + importeRedondeoTmp);
                            valoresConceptosEmpleados.put("AjustePorRedondeo".toUpperCase(), importeRedondeoTmp);
                            movNomConcepAjustePorRedondeo.setResultado(ejecutaFormula(movNomConcepAjustePorRedondeo.getConcepNomDefi().getFormulaConcepto()));
                            movNomConcepAjustePorRedondeo.setCalculado(movNomConcepAjustePorRedondeo.getResultado());
                            if (movNomConcepAjustePorRedondeo.getResultado() == null) {
                                movNomConcepAjustePorRedondeo.setResultado(0.0);
                                movNomConcepAjustePorRedondeo.setCalculado(movNomConcepAjustePorRedondeo.getResultado());
                            }
                            if (movNomConcepAjustePorRedondeo.getResultado() == 0) {
                                if (movNomConcepAjustePorRedondeo.getId() != null) {
                                    eliminarMovimientosNominaBasura(new Object[]{movNomConcepAjustePorRedondeo.getId()});
//                                System.out.println("flush 1");
                                    getSession().flush();
                                    getSession().clear();
                                }
                            } else {
                                getSession().saveOrUpdate(movNomConcepAjustePorRedondeo);
                                cantidadSaveUpdate++;
                            }
                        }
                        if (continuarProcesos) {
                            Double importeNeto = (Double.parseDouble(percepcion.toString()) - Double.parseDouble(deduccion.toString()));
                            importeNeto = ejecutaDescuentosPrestamos(filtroPlazasPorEmpleadosMov.get(i), filtroMovimientosNominasCreditosAhorro, importeNeto, listMovNomConcepCreditosAhorroDescuentoActivo);
                            for (int j = 0; j < formulaDedudCreditos.size(); j++) {
                                //variablesTipoFuncion(formulaDedudCreditos.get(j).getConcepNomDefi().getFormulaConcepto().toUpperCase(), TipoClasificacionFormula.DATOFUNCION, ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)));
                                double res = ejecutaFormula(formulaDedudCreditos.get(j).getConcepNomDefi().getFormulaConcepto());
                                formulaDedudCreditos.get(j).setResultado(res);
                                getSession().saveOrUpdate(formulaDedudCreditos.get(j));
                            }
                            if (mensajeResultado.getNoError() != 0) {
                                break;
                            }
                            if (!filtroMovimientosNominasCreditosAhorro.isEmpty()) {
                                ejecutaDescuentosAhorro(filtroPlazasPorEmpleadosMov.get(i), filtroMovimientosNominasCreditosAhorro, importeNeto, listMovNomConcepCreditosAhorroDescuentoActivo);
                                for (int j = 0; j < formulaDedudAhorros.size(); j++) {
                                    //variablesTipoFuncion(formulaDedudCreditos.get(j).getConcepNomDefi().getFormulaConcepto().toUpperCase(), TipoClasificacionFormula.DATOFUNCION, ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)));
                                    double res = ejecutaFormula(formulaDedudAhorros.get(j).getConcepNomDefi().getFormulaConcepto());
                                    formulaDedudAhorros.get(j).setResultado(res);
                                    getSession().saveOrUpdate(formulaDedudAhorros.get(j));
                                }
                            }
                            if (mensajeResultado.getNoError() != 0) {
                                break;
                            }
                            if (cantidadSaveUpdate % cantidadFlush == 0 & cantidadSaveUpdate > 0) {
//                            System.out.println("flush 2");
                                getSession().flush();
                                getSession().clear();
//                        System.out.println("clear()");
                            }
                        }
                    }//validacion para el calculo de la vacaciones cuando sea por tipo de corrida vacaciones
                    valoresConceptosEmpleados.clear();

                    filtroMovimientosNominas = null;
                }
//                Utilerias.bitacora("termina calculo empelados: " + i);
                if (filtroPlazasPorEmpleadosMov.isEmpty()) {
                    mensajeResultado.setNoError(999);
                    mensajeResultado.setError("No existen empleados");
                }
            } else {
                mensajeResultado.setNoError(999);
                mensajeResultado.setError("No existen empleados");
            }
            if (mensajeResultado.getNoError() == 0) {
                getSession().getTransaction().commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calculaNomina()1_Error: ").append(ex));
            mensajeResultado.setNoError(-101);
            mensajeResultado.setError(ex.getMessage());
            getSession().getTransaction().rollback();
            band = false;
        } finally {
            if (band & mensajeResultado.getNoError() != 0) {
                if (getSession() != null) {
                    if (getSession().isOpen()) {
                        if (getSession().getTransaction() != null) {
                            if (getSession().getTransaction().isActive()) {
                                getSession().getTransaction().rollback();
                            }
                        }
                    }
                }
            }
        }
//        Utilerias.bitacora("termina");
        variablesGlobalesANull();
        return mensajeResultado;

    }

    private void calcularAguinaldo() {
        try {
            AguinaldoPagos pagos = null;
            Double AgunaldoTotal = 0.0;
            Double diaspagados = 0.0;
            Double parteExenta = 0.0;
            Double isr = 0.0;
            Integer numPagos = aguiConfiguracion.getNumPagos();
            strQuery.delete(0, strQuery.length());
            strQuery.append("FROM AguinaldoPagos a WHERE a.razonesSociales.clave=:claveRazonSocial and a.empleado.clave=:claveEmpleado");
            strQuery.append(" and a.tipoNomina.clave=:claveTipoNomina and a.periodosNomina.clave=:clavePeriodosNomina");
            camposParametro = new ArrayList<String>();
            valoresParametro = new ArrayList<Object>();
            camposParametro.add("claveRazonSocial");
            camposParametro.add("claveEmpleado");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("clavePeriodosNomina");
            valoresParametro.add(valoresConceptosGlobales.get("RazonSocial".toUpperCase()));
            valoresParametro.add(plazaEmpleadoaguinaldo.getPlazasPorEmpleado().getEmpleados().getClave());
            valoresParametro.add(valoresConceptosGlobales.get("TipoNomina".toUpperCase()));
            valoresParametro.add(periodosNomina.getClave());
            pagos = (AguinaldoPagos) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (pagos == null) {
                pagos = new AguinaldoPagos();
            }
            if (aguiConfiguracion.getPagarEnUnaSolaExhibicion() == 0) {

                for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                    if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                        pagos.setRazonesSociales(filtroMovimientosNominas.get(0).getRazonesSociales());
                        pagos.setEmpleado(filtroMovimientosNominas.get(0).getEmpleado());
                        pagos.setEjercicio(filtroMovimientosNominas.get(0).getEjercicio());
                        pagos.setPeriodosNomina(filtroMovimientosNominas.get(0).getPeriodosNomina());
                        pagos.setTipoNomina(filtroMovimientosNominas.get(0).getTipoNomina());
                        pagos.setDiasAguinaldos((Double) valoresConceptosEmpleados.get("FactorDiasAguinaldo".toUpperCase()));
                        pagos.setDiasPagados((Double) valoresConceptosEmpleados.get("FactorDiasAguinaldo".toUpperCase()));
                        pagos.setAguinaldo(filtroMovimientosNominas.get(i).getResultado());
                    } else if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().contains("CalculoISR")) {
                        pagos.setIsr(filtroMovimientosNominas.get(i).getResultado());
                        pagos.setRazonesSociales(filtroMovimientosNominas.get(0).getRazonesSociales());
                        pagos.setEmpleado(filtroMovimientosNominas.get(0).getEmpleado());
                        pagos.setEjercicio(filtroMovimientosNominas.get(0).getEjercicio());
                        pagos.setPeriodosNomina(filtroMovimientosNominas.get(0).getPeriodosNomina());
                        pagos.setTipoNomina(filtroMovimientosNominas.get(0).getTipoNomina());
                    }
                }
                getSession().saveOrUpdate(pagos);
            } else if (aguiConfiguracion.getPagarEnUnaSolaExhibicion() == 1) {
                numPagos = totalPagosAgui;
                for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                    if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                        pagos.setRazonesSociales(filtroMovimientosNominas.get(0).getRazonesSociales());
                        pagos.setEmpleado(filtroMovimientosNominas.get(0).getEmpleado());
                        pagos.setEjercicio(filtroMovimientosNominas.get(0).getEjercicio());
                        pagos.setPeriodosNomina(filtroMovimientosNominas.get(0).getPeriodosNomina());
                        pagos.setTipoNomina(filtroMovimientosNominas.get(0).getTipoNomina());
                        pagos.setDiasAguinaldos((Double) valoresConceptosEmpleados.get("FactorDiasAguinaldo".toUpperCase()));
                        diaspagados = (Double) valoresConceptosEmpleados.get("FactorDiasAguinaldo".toUpperCase());
                        AgunaldoTotal = filtroMovimientosNominas.get(i).getResultado();
                        for (int j = 0; j < filtroMovimientosNominas.get(i).getMovNomBaseAfecta().size(); j++) {
                            MovNomBaseAfecta mov = filtroMovimientosNominas.get(i).getMovNomBaseAfecta().get(j);
                            if (mov.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                                parteExenta = mov.getResultadoExento();
                            }
                        }

                    } else if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().contains("CalculoISR")) {
                        isr = filtroMovimientosNominas.get(i).getResultado();
                    }
                }
                if (aguiConfiguracion.getModoCalculo() == 0) {//Calcular parte Exenta separado
                    Double diasPagarTotal = diaspagados / numPagos;
                    Double aguiTotal = AgunaldoTotal / numPagos;
                    Double totalExenta = parteExenta / numPagos;
                    Double isrtotal = isr / numPagos;
                    pagos.setDiasPagados(diasPagarTotal);
                    pagos.setAguinaldo(aguiTotal);
                    pagos.setIsr(isrtotal);
                    for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                        if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                            filtroMovimientosNominas.get(i).setResultado(aguiTotal);
                            for (int j = 0; j < filtroMovimientosNominas.get(i).getMovNomBaseAfecta().size(); j++) {
                                MovNomBaseAfecta mov = filtroMovimientosNominas.get(i).getMovNomBaseAfecta().get(j);
                                if (mov.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                                    filtroMovimientosNominas.get(i).getMovNomBaseAfecta().get(j).setResultadoExento(totalExenta);
                                }
                            }
                        } else if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().contains("CalculoISR")) {
                            filtroMovimientosNominas.get(i).setResultado(isrtotal);
                        }
                    }
                    getSession().saveOrUpdate(pagos);
                    for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                        getSession().saveOrUpdate(filtroMovimientosNominas.get(i));
                    }

                } else if (aguiConfiguracion.getModoCalculo() == 1) {//Calcular aguinaldo total y dividir
                    Double diasPagarTotal = diaspagados / numPagos;
                    Double aguiTotal = AgunaldoTotal / numPagos;
                    Double totalExenta = parteExenta / numPagos;
                    Double isrtotal = isr / numPagos;
                    pagos.setDiasPagados(diasPagarTotal);
                    pagos.setAguinaldo(aguiTotal);
                    pagos.setIsr(isrtotal);
                    for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                        if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                            filtroMovimientosNominas.get(i).setResultado(aguiTotal);
                            for (int j = 0; j < filtroMovimientosNominas.get(i).getMovNomBaseAfecta().size(); j++) {
                                MovNomBaseAfecta mov = filtroMovimientosNominas.get(i).getMovNomBaseAfecta().get(j);
                                if (mov.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                                    filtroMovimientosNominas.get(i).getMovNomBaseAfecta().get(j).setResultadoExento(totalExenta);
                                }
                            }
                        } else if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().contains("CalculoISR")) {
                            filtroMovimientosNominas.get(i).setResultado(isrtotal);
                        }
                    }
                    getSession().saveOrUpdate(pagos);
                    for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                        getSession().saveOrUpdate(filtroMovimientosNominas.get(i));
                    }
                } else if (aguiConfiguracion.getModoCalculo() == 2) {//descontar isr en primer pago
                    boolean pagarIsr = false;
                    Double diasPagarTotal = diaspagados / numPagos;
                    Double aguiTotal = AgunaldoTotal / numPagos;
                    Double totalExenta = parteExenta / numPagos;

                    pagos.setDiasPagados(diasPagarTotal);
                    pagos.setAguinaldo(aguiTotal);

                    Date fecper = quitaHrsDeFecha(periodosNomina.getFechaInicial());
                    for (int k = 0; k < aguiFechas.size(); k++) {
                        Date fecha = quitaHrsDeFecha(aguiFechas.get(k).getFechaProgramada());
                        if (fecha.compareTo(fecper) < 0) {
                            pagarIsr = true;
                            break;
                        }
                    }
                    if (pagarIsr) {
                        pagos.setIsr(0.0);
                    } else {
                        pagos.setIsr(isr);
                    }
                    for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                        if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                            filtroMovimientosNominas.get(i).setResultado(aguiTotal);
                            for (int j = 0; j < filtroMovimientosNominas.get(i).getMovNomBaseAfecta().size(); j++) {
                                MovNomBaseAfecta mov = filtroMovimientosNominas.get(i).getMovNomBaseAfecta().get(j);
                                if (mov.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("ImporteAguinaldo".toUpperCase())) {
                                    filtroMovimientosNominas.get(i).getMovNomBaseAfecta().get(j).setResultadoExento(totalExenta);
                                }
                            }
                        } else if (filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto().contains("CalculoISR")) {
                            if (pagarIsr) {
                                filtroMovimientosNominas.get(i).setResultado(0.0);
                            } else {
                                filtroMovimientosNominas.get(i).setResultado(isr);
                            }

                        }
                    }
                    getSession().saveOrUpdate(pagos);
                    for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
                        getSession().saveOrUpdate(filtroMovimientosNominas.get(i));
                    }
                }

            }

        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calcularAguinaldo()1_Error: ").append(ex));
        }
    }

    private void consultarConfiguracionAgui() {
        strQuery.delete(0, strQuery.length());
        strQuery.append("from DiasAguinaldo d where d.razonesSociales.clave=:claveRazonsocial");
        camposParametro = new ArrayList<String>();
        valoresParametro = new ArrayList<Object>();
        camposParametro.add("claveRazonsocial");
        valoresParametro.add(valoresConceptosGlobales.get("RazonSocial".toUpperCase()));
        diasAguinaldo = (List<DiasAguinaldo>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), null);
        if (diasAguinaldo == null) {
            diasAguinaldo = new ArrayList<>();
        }
        strQuery.delete(0, strQuery.length());
        strQuery.append("from AguinaldoConfiguracion d where d.razonesSociales.clave=:claveRazonsocial");
        camposParametro = new ArrayList<String>(0);
        valoresParametro = new ArrayList<Object>(0);
        camposParametro.add("claveRazonsocial");
        valoresParametro.add(valoresConceptosGlobales.get("RazonSocial".toUpperCase()));
        aguiConfiguracion = (AguinaldoConfiguracion) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
        strQuery.delete(0, strQuery.length());
        strQuery.append("from AguinaldoFechas d where d.razonesSociales.clave=:claveRazonsocial");
        camposParametro = new ArrayList<String>(0);
        valoresParametro = new ArrayList<Object>(0);
        camposParametro.add("claveRazonsocial");
        valoresParametro.add(valoresConceptosGlobales.get("RazonSocial".toUpperCase()));
        aguiFechas = (List<AguinaldoFechas>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), null);
        if (aguiFechas == null) {
            aguiFechas = new ArrayList<>();
        }
        if (!aguiFechas.isEmpty()) {
            PeriodosNomina peraxu = null;
            for (int i = 0; i < aguiFechas.size(); i++) {
                strQuery.delete(0, strQuery.length());
                strQuery.append("FROM PeriodosNomina p ");
                strQuery.append("where p.tipoCorrida.clave=:claveCorrida and p.tipoNomina.clave=:claveNomina ");
                strQuery.append("and (:fecha BETWEEN p.fechaInicial AND p.fechaFinal + 1)");
                camposParametro = new ArrayList<String>(0);
                valoresParametro = new ArrayList<Object>(0);
                camposParametro.add("claveCorrida");
                camposParametro.add("claveNomina");
                camposParametro.add("fecha");
                valoresParametro.add(valoresConceptosGlobales.get("ClaveTipoCorrida".toUpperCase()));
                valoresParametro.add(valoresConceptosGlobales.get("TipoNomina".toUpperCase()));
                valoresParametro.add(aguiFechas.get(i).getFechaProgramada());
                PeriodosNomina per = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
                if (peraxu != null) {
                    if (peraxu != per) {
                        totalPagosAgui++;
                    }

                } else {
                    peraxu = per;
                    totalPagosAgui++;
                }
            }

        }

    }

    private void obtenerIngresosReingresosBajas(PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        camposParametro = new ArrayList<String>(0);
        valoresParametro = new ArrayList<Object>(0);
        strQuery.delete(0, strQuery.length()).append("from IngresosReingresosBajas ing where ing.empleados.clave = :claveEmpleado");
        if (plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal() != null) {
            strQuery.append(" and ing.registroPatronal.clave = :claveRegPat");
        }
        strQuery.append(" and ing.razonesSociales.clave = :claveRazonSocial");
        camposParametro.add("claveEmpleado");
        valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave());

        if (plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal() != null) {
            camposParametro.add("claveRegPat");
            valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave());
        }
        camposParametro.add("claveRazonSocial");
        valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());
        try {
            List<IngresosReingresosBajas> listIngresosReingresosBajas = (List<IngresosReingresosBajas>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            if (listIngresosReingresosBajas != null) {
                Calendar fechaActual = Calendar.getInstance();
                if (periodosNomina != null) {
                    fechaActual.setTime(periodosNomina.getFechaFinal());
                }
                for (int i = 0; i < listIngresosReingresosBajas.size(); i++) {
                    if (listIngresosReingresosBajas.get(i).getFechaBaja().compareTo(fechaActual.getTime()) <= 0) {
                        ingresosReingresosBajas = listIngresosReingresosBajas.get(i);
                        for (int j = 0; j < listIngresosReingresosBajas.size(); j++) {
                            if (listIngresosReingresosBajas.get(j).getTipoMovimiento() == IngresosReingresosBajas.TipoMovimiento.R) {
                                if (listIngresosReingresosBajas.get(j).getFechaBaja().compareTo(listIngresosReingresosBajas.get(i).getFechaBaja()) >= 0) {
                                    ingresosReingresosBajas = listIngresosReingresosBajas.get(j);
                                }
                            }
                        }
                    } else {
                        ingresosReingresosBajas = listIngresosReingresosBajas.get(i);
                    }
                }
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(168);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerIngresosReingresosBajas()1_Error: ").append(ex));
        }
    }

    private Object[] obtenerMovimientosNominaCreditoAhorro(List<CreditoAhorro> listCreditoAhorro) {
        Object[] listMovTmp = new Object[4];
        List<MovNomConcep> listMovNomConcepCreditosAhorro = new ArrayList<MovNomConcep>();
        List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoActivo = new ArrayList<MovNomConcep>();
        List<MovNomConcep> listMovNomConcepFormulaDeducCreditos = new ArrayList<MovNomConcep>();
        List<MovNomConcep> listMovNomConcepFormulaDeducAhorros = new ArrayList<MovNomConcep>();
        int i, z;
        for (i = 0; i < listCreditoAhorro.size(); i++) {
            z = 0;
            while (z < filtroMovimientosNominas.size()) {
//                System.out.println("concepto credito " + listCreditoAhorro.get(i).getConcepNomiDefin().getClave() + " concepto mov " + filtroMovimientosNominas.get(z).getConcepNomDefi().getClave());
                if (listCreditoAhorro.get(i).getConcepNomiDefin().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(z).getConcepNomDefi().getClave())) {
                    listMovNomConcepCreditosAhorro.add(filtroMovimientosNominas.get(z));
                    filtroMovimientosNominas.remove(z);
                } else if (listCreditoAhorro.get(i).isActivarManejoDescuento()) {
                    if (listCreditoAhorro.get(i).getcNDescuento().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(z).getConcepNomDefi().getClave())) {
                        if (!listCreditoAhorro.get(i).getcNDescuento().getClave().equalsIgnoreCase(listCreditoAhorro.get(i).getConcepNomiDefin().getClave())) {
                            listMovNomConcepCreditosAhorroDescuentoActivo.add(filtroMovimientosNominas.get(z));
                            filtroMovimientosNominas.remove(z);
                        } else {
                            z++;
                        }
                    } else {
                        z++;
                    }
                } else if (filtroMovimientosNominas.get(z).getConcepNomDefi().getFormulaConcepto() != null) {
                    if (filtroMovimientosNominas.get(z).getConcepNomDefi().getFormulaConcepto().toUpperCase().startsWith("DEDUCCREDITOS")) {
                        listMovNomConcepFormulaDeducCreditos.add(filtroMovimientosNominas.get(z));
                        filtroMovimientosNominas.remove(z);
                    } else if (filtroMovimientosNominas.get(z).getConcepNomDefi().getFormulaConcepto().toUpperCase().startsWith("DEDUCAHORROS")) {
                        listMovNomConcepFormulaDeducAhorros.add(filtroMovimientosNominas.get(z));
                        filtroMovimientosNominas.remove(z);
                    } else {
                        z++;
                    }
                } else {
                    z++;
                }
            }
        }
        listMovTmp[0] = listMovNomConcepCreditosAhorro;
        listMovTmp[1] = listMovNomConcepCreditosAhorroDescuentoActivo;//JSA26
        listMovTmp[2] = listMovNomConcepFormulaDeducCreditos;
        listMovTmp[3] = listMovNomConcepFormulaDeducAhorros;

        return listMovTmp;
    }

    private void obtenerMovimientosNominaISRACargoYSubsidioAlEmpleado() {//JSA16
        int i;
        boolean isISRACargo, isSubsEmpleoCalculado;
        for (i = 0; i < filtroMovimientosNominas.size(); i++) {
            isISRACargo = isConceptoEspecial(5, filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto());
            isSubsEmpleoCalculado = isConceptoEspecial(6, filtroMovimientosNominas.get(i).getConcepNomDefi().getFormulaConcepto());
            if (isISRACargo) {
                listMovNomConcepISRCARGO.add(filtroMovimientosNominas.get(i));
            } else if (isSubsEmpleoCalculado) {
                listMovNomConcepSUBSIDIOALEMPLEO.add(filtroMovimientosNominas.get(i));
            }
        }
    }

    private List<CreditoAhorro> obtenerCreditosAhorro(RazonesSociales razonesSociales) {
        List<CreditoAhorro> listCreditoPorEmpleado = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("from CreditoAhorro c ");

            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strWhere.append(" c.concepNomiDefin is not null and c.razonesSociales.clave= :claveRazonesSociales");
            camposParametro.add("claveRazonesSociales");
            valoresParametro.add(razonesSociales.getClave());

            strQuery.append(strWhere);
            listCreditoPorEmpleado = (List<CreditoAhorro>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerCreditosAhorro()1_Error: ").append(ex));
        }
        return listCreditoPorEmpleado;
    }

    private void variablesGlobalesANull() {
        datosFormulas = null;
////////////        periodicidadAnual = null;
        periodicidadTipoNomina = null;
        periodosNomina = null;
        filtroConceptosNomina = null;
        filtroMovimientosNominas = null;
        valoresConceptosEmpleados = null;
        valoresConceptosGlobales = null;
        variablesConceptos = null;
        tablaFactorIntegracion = null;
        tablaIsr = null;
        tablaSubsidio = null;
        tablaZonaSalarial = null;
        centroDeCostoMovimiento = null;
        factorMensual = null;
        factorAnual = null;
        manejoSalarioDiario = null;
    }

    private void cargarVariablesConceptosCompilador() {
        List valores = (List) ejecutaQueryList(CONSULTA_CONCEPTO_CON_NOMENCLATURA, null, null, 0);
        if (mensajeResultado.getNoError() == -100) {
            mensajeResultado.setNoError(25);
            return;
        }
        if (valores != null) {
            int i, j;
            variablesConceptos = new String[valores.size()][2];
            for (i = 0; i < valores.size(); i++) {
                List subValores = (List) valores.get(i);
                for (j = 0; j < subValores.size(); j++) {
                    variablesConceptos[i][j] = String.valueOf(subValores.get(j)).replace(' ', '_');
                }
            }
        }
    }

    private void agregaVariableConceptos() {
        if (variablesConceptos != null) {
            int j;
            for (j = 0; j < variablesConceptos.length; j++) {
                DatosConceptosNomina.addVariable(variablesConceptos[j][0].toUpperCase());
                DatosConceptosNomina.addVariable(variablesConceptos[j][1].replace(' ', '_').toUpperCase());
                compEjec.addVariableExtraNro(variablesConceptos[j][0].toUpperCase());
                compEjec.addVariableExtraNro(variablesConceptos[j][1].replace(' ', '_').toUpperCase());
            }
        }
    }

    private List<MovNomConcep> buscaMovim2Meses(List<MovNomConcep> filtroMovimientosNominas) {
        List<MovNomConcep> mncs = new ArrayList<MovNomConcep>();
        int i = 0, j;
        while (i < filtroMovimientosNominas.size()) {
            if (filtroMovimientosNominas.get(i).getNumMovParticion() == 2) {
                mncs.add(filtroMovimientosNominas.get(i));
                filtroMovimientosNominas.remove(i);
            } else {
                i++;
            }
        }
        return mncs;
    }

    private Calendar dateToCalendar(Date date) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(date);
        return fecha;
    }

    private void ejecutaDescuentosAhorro(PlazasPorEmpleadosMov plazasPorEmpleadosMov, List<MovNomConcep> filtroMovimientosNominas, Double importeNeto, List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoActivo) {
        try {
            int i, j;
            Double importeAcumulado = 0.0;
            boolean tieneMovOtrasCorridas = false;
            List<CreditoPorEmpleado> listCreditoPorEmpleado = obtenerCreditoPorEmpleado(plazasPorEmpleadosMov, "2", filtroMovimientosNominas);
//            System.out.println("*************************************");
//            System.out.println("Empleado " + plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave());
            int[] diasMeses = new int[2];
            Calendar fechaBimestre = Calendar.getInstance();
            fechaBimestre.setTime(periodosNomina.getAcumularAMes());
            diasMeses[0] = fechaBimestre.getActualMaximum(Calendar.DAY_OF_MONTH);
            if ((fechaBimestre.get(Calendar.MONTH) + 1) % 2 == 0) {
                fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) - 1);
            } else {
                fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) + 1);
            }
            diasMeses[1] = fechaBimestre.getActualMaximum(Calendar.DAY_OF_MONTH);
            Double diasMes, diasBimestre;
            diasBimestre = Double.valueOf((diasMeses[0] + diasMeses[1]));
            if (mensajeResultado.getNoError() == 0) {
                for (i = 0; i < listCreditoPorEmpleado.size(); i++) {
                    CreditoMovimientos creditoMovimientosCambioDescuento = null, creditoMovimientosBloqueo = null;
                    Boolean continuarEjecucion = true;
                    if (listCreditoPorEmpleado.get(i).getCreditoAhorro().isInicioDescuento()) {
                        if (listCreditoPorEmpleado.get(i).getPeriodosNomina().getFechaInicial().compareTo(periodosNomina.getFechaInicial()) > 0) {
                            continuarEjecucion = false;
                        }
                    } else if (listCreditoPorEmpleado.get(i).getFechaCredito().compareTo(periodosNomina.getFechaFinal()) > 0) {
                        continuarEjecucion = false;
                    }
                    creditoMovimientosBloqueo = obtenerCreditoMovimientosMax(listCreditoPorEmpleado.get(i), TiposMovimiento.Bloqueo);
                    if (mensajeResultado.getNoError() == 0) {
                        if (creditoMovimientosBloqueo != null) {
                            Double cantidad = obtenerCantidadPeriodoNominaRango(plazasPorEmpleadosMov.getTipoNomina(), creditoMovimientosBloqueo.getPeriodosNomina(), periodosNomina);
                            if (cantidad <= creditoMovimientosBloqueo.getNumeroPeriodosBloquear()) {
                                continuarEjecucion = false;
                            }
                        }
                        if (continuarEjecucion) {
                            Double montoDescuento, montoDescuentoOriginal = listCreditoPorEmpleado.get(i).getMontoDescuento();
                            creditoMovimientosCambioDescuento = obtenerCreditoMovimientosMax(listCreditoPorEmpleado.get(i), TiposMovimiento.ModificarDescuento);
                            if (mensajeResultado.getNoError() == 0 && continuarEjecucion) {
                                if (creditoMovimientosCambioDescuento != null) {
                                    montoDescuentoOriginal = creditoMovimientosCambioDescuento.getImporte();
                                }
                                if (montoDescuentoOriginal > 0) {
                                    CreditoMovimientos creditoMovimientosDescuentoSistema = null;
                                    creditoMovimientosDescuentoSistema = obtenerCreditoMovimientosPorPeriodoNomina(listCreditoPorEmpleado.get(i), TiposMovimiento.AbonoSistema);
                                    if (listCreditoPorEmpleado.get(i).getCreditoAhorro().getModoDescuento() == 2) {//Especificar Número de Parcialidades
                                        if (!obtenerNumeroParcialidadesCreditoMovimientos(listCreditoPorEmpleado.get(i), TiposMovimiento.AbonoSistema, creditoMovimientosDescuentoSistema)) {
                                            continuarEjecucion = false;
                                        }
                                    }
                                    if (continuarEjecucion) {
                                        if (creditoMovimientosDescuentoSistema == null) {
                                            creditoMovimientosDescuentoSistema = crearCreditoMovimientoSistema(listCreditoPorEmpleado.get(i), false, filtroMovimientosNominas, null);
                                        } else {
//                                            creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().setSaldo(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() - creditoMovimientosDescuentoSistema.getImporte());
                                            boolean addMovimiento = true;
                                            if (creditoMovimientosDescuentoSistema.getMovNomConceps() == null ? false : !creditoMovimientosDescuentoSistema.getMovNomConceps().isEmpty()) {
                                                int recorre;
                                                for (recorre = 0; recorre < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); recorre++) {
                                                    if (tipoCorrida.getClave().equals(creditoMovimientosDescuentoSistema.getMovNomConceps().get(recorre).getTipoCorrida().getClave())) {
                                                        addMovimiento = false;
                                                    }
                                                }
                                                recorre = 0;
                                                while (recorre < creditoMovimientosDescuentoSistema.getMovNomConceps().size()) {
                                                    if (tipoCorrida.getClave().equals(creditoMovimientosDescuentoSistema.getMovNomConceps().get(recorre).getTipoCorrida().getClave())) {
                                                        recorre++;
                                                    } else {
                                                        creditoMovimientosDescuentoSistema.getMovNomConceps().remove(recorre);
                                                        tieneMovOtrasCorridas = true;
                                                    }
                                                }
                                            }
                                            if (creditoMovimientosDescuentoSistema.getMovNomConceps() == null ? true : creditoMovimientosDescuentoSistema.getMovNomConceps().isEmpty()) {
                                                if (addMovimiento) {
                                                    creditoMovimientosDescuentoSistema = crearCreditoMovimientoSistema(listCreditoPorEmpleado.get(i), true, filtroMovimientosNominas, creditoMovimientosDescuentoSistema);
                                                } else if (isMov2Meses) {
                                                    creditoMovimientosDescuentoSistema = crearCreditoMovimientoSistema(listCreditoPorEmpleado.get(i), true, filtroMovimientosNominas, creditoMovimientosDescuentoSistema);
                                                }
                                            }
                                        }

                                        //<editor-fold defaultstate="collapsed" desc="Aqui te separa el movimiento del concepto del manejo del descuento">
                                        //Aqui te separa el movimiento del concepto del manejo del descuento.
                                        if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().isActivarManejoDescuento()) {
                                            if (!creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getConcepNomiDefin().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                                                int k = 0;
                                                while (k < creditoMovimientosDescuentoSistema.getMovNomConceps().size()) {
                                                    if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                                                        listMovNomConcepCreditosAhorroDescuentoActivo.add(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                        creditoMovimientosDescuentoSistema.getMovNomConceps().remove(k);
                                                    } else {
                                                        k++;
                                                    }
                                                }
                                            }
                                        }
//</editor-fold>
                                        Calendar cFechax = Calendar.getInstance();
                                        cFechax.setTime(periodosNomina.getAcumularAMes());
                                        diasMes = creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getFactorProporcional() == 1 ? factorMensual : cFechax.getActualMaximum(Calendar.MONTH);
                                        List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoParaGuardar = new ArrayList<MovNomConcep>();
                                        importeAcumulado = 0.0;
                                        for (int k = 0; k < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); k++) {//JSA21
//                                            System.out.println("Saldo " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo());
                                            Double importe = Double.valueOf(montoDescuentoOriginal.toString()), importeSinMascara;
                                            montoDescuento = montoDescuentoOriginal;
//                                            System.out.println("diasMes " + diasMes + " diasBimestre " + diasBimestre);
                                            int diasPeriodo = 0;
                                            if (isMov2Meses) {
                                                diasPeriodo = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual//obtener dias reales del periodo;
                                                Calendar cFecha = Calendar.getInstance();
                                                if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getNumMovParticion() == 1) {
                                                    cFecha.setTime(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getPeriodosNomina().getFechaInicial());
                                                    cFecha.set(Calendar.DATE, cFecha.getActualMaximum(Calendar.DATE));
                                                    inicializaPeriodo2Meses(periodosNomina, periodosNomina.getFechaInicial(), cFecha.getTime());
                                                    valoresConceptosGlobales.put(parametroFechaFinal, cFecha);
                                                    valoresConceptosGlobales.put(parametroFechaInicial, dateToCalendar(periodosNomina.getFechaInicial()));
                                                } else {
                                                    cFecha.setTime(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getPeriodosNomina().getFechaFinal());
                                                    cFecha.set(Calendar.DATE, 1);
                                                    inicializaPeriodo2Meses(periodosNomina, cFecha.getTime(), periodosNomina.getFechaFinal());
                                                    valoresConceptosGlobales.put(parametroFechaInicial, cFecha);
                                                    valoresConceptosGlobales.put(parametroFechaFinal, dateToCalendar(periodosNomina.getFechaFinal()));
                                                }
                                                valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
                                                cargaValoresDiasCotizados(plazasPorEmpleadosMov.getFechaIMSS(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), true, null, null, false, false);//JSA30
                                                cargaDatosVariableConfiguracionIMSS(((Calendar) valoresConceptosGlobales.get(parametroFechaFinal)).getTime());
                                            }
                                            Double diasIMSS = Double.parseDouble(valoresConceptosEmpleados.get("DiasCotizados".toUpperCase()).toString());
                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoDescuento() == 1) {//Elegir Modo al Registrar el Credito
                                                Double montoSDI = Double.parseDouble(valoresConceptosEmpleados.get("SueldoIntIMSS".toUpperCase()).toString());
                                                Double SMDF = Double.parseDouble(valoresConceptosEmpleados.get("SalarioMinDF".toUpperCase()).toString());
                                                //0.-Importe, 1.-VSM, 2.-Porcentaje
                                                if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getModoDescuentoCredito() == 1) {//VSM
                                                    if (versionCalculoPrestamoAhorro == 4 || versionCalculoPrestamoAhorro == 5) {
                                                        //Solo para nominas quincenales
                                                        if (plazasPorEmpleadosMov.getTipoNomina().getPeriodicidad().getDias() != 15) {
                                                            versionCalculoPrestamoAhorro = 1;
                                                        }
                                                    }
                                                    switch (versionCalculoPrestamoAhorro) {
                                                        case 2:
                                                            //Bimestral, Mensual y Por Periodo
                                                            importe = (SMDF * (montoDescuentoOriginal * 2)) * (diasIMSS / diasBimestre);
                                                            //Original: importe = (SMDF * (montoDescuentoOriginal * 2)) * (diasIMSS / diasBimestre);
                                                            break;
                                                        case 3:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2) || creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Bimestral, Mensual
                                                                importe = (SMDF * montoDescuentoOriginal) * (diasIMSS / diasMes);
                                                            } else {
                                                                importe = SMDF * montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = (SMDF * montoDescuentoOriginal) * (diasIMSS / diasMes);
                                                            break;
                                                        case 4:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = (SMDF * montoDescuentoOriginal) / 4;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = (SMDF * montoDescuentoOriginal) / 2;
                                                            } else {
                                                                importe = SMDF * montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = (SMDF * montoDescuentoOriginal) / 2;//mensual
                                                            break;
                                                        case 5:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = SMDF * (montoDescuentoOriginal / 4);
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = SMDF * (montoDescuentoOriginal / 2);
                                                            } else {
                                                                importe = SMDF * montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = SMDF * (montoDescuentoOriginal / 2);//mensual
                                                            break;
                                                        default:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoVSMG().equals(0)) {//Por periodo
                                                                if (isMov2Meses) {
                                                                    importe = SMDF * ((montoDescuento / diasPeriodo) * diasIMSS);
                                                                } else {
                                                                    importe = SMDF * montoDescuento;
                                                                }
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoVSMG().equals(2)) {//Bimestral
                                                                montoDescuento = (montoDescuentoOriginal / diasBimestre);
                                                                importe = (SMDF * montoDescuento) * diasIMSS;//montoDescuento debe ser mensual
                                                            } else {
                                                                montoDescuento = (montoDescuentoOriginal / diasMes);
                                                                importe = (SMDF * montoDescuento) * diasIMSS;//montoDescuento debe ser mensual
                                                            }
                                                    }
                                                } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getModoDescuentoCredito() == 2) {//Porcentaje
                                                    if (versionCalculoPrestamoAhorro == 3) {
                                                        //Solo para nominas quincenales
                                                        if (plazasPorEmpleadosMov.getTipoNomina().getPeriodicidad().getDias() != 15) {
                                                            versionCalculoPrestamoAhorro = 1;
                                                        }
                                                    }
                                                    switch (versionCalculoPrestamoAhorro) {
                                                        case 2:
                                                            Double factorIntegracion = (Double) valoresConceptosEmpleados.get("FactorIntegracion".toUpperCase());
                                                            importe = (acumuladoNormal + acumuladoDirecto + acumuladoAnual) * (montoDescuentoOriginal / 100) * factorIntegracion;
                                                            //Original: importe = (acumuladoNormal + acumuladoDirecto + acumuladoAnual) * (montoDescuentoOriginal / 100) * factorIntegracion;
                                                            break;
                                                        case 3:
                                                            //Solo para nominas quincenales
                                                            importe = ((montoSDI * diasBimestre) * (montoDescuentoOriginal / 100)) / 4;
                                                            //Original: importe = ((montoSDI * diasBimestre) * (montoDescuentoOriginal / 100)) / 4;
                                                            break;
                                                        default:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoPorc().equals(1)) {//Mensual
                                                                importe = (((montoSDI * diasMes) * (montoDescuentoOriginal / 100)) / diasMes) * diasIMSS;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoPorc().equals(2)) {//Bimestral
                                                                importe = (((montoSDI * diasBimestre) * (montoDescuentoOriginal / 100)) / diasBimestre) * diasIMSS;
                                                            } else {
                                                                importe = (montoSDI * (montoDescuentoOriginal / 100)) * diasIMSS;
                                                            }
                                                    }
//                                                    System.out.println("montoSDI " + montoSDI + " montoDescuento " + montoDescuento + " diasIMSS " + diasIMSS);
                                                } else {//Importe
                                                    if (versionCalculoPrestamoAhorro == 3) {
                                                        //Solo para nominas quincenales
                                                        if (plazasPorEmpleadosMov.getTipoNomina().getPeriodicidad().getDias() != 15) {
                                                            versionCalculoPrestamoAhorro = 1;
                                                        }
                                                    }
                                                    switch (versionCalculoPrestamoAhorro) {
                                                        case 2:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = (montoDescuentoOriginal / diasBimestre) * diasIMSS;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = ((montoDescuentoOriginal * 2) / diasBimestre) * diasIMSS;
                                                            } else {
                                                                importe = montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = ((montoDescuentoOriginal * 2) / diasBimestre) * diasIMSS;
                                                            break;
                                                        case 3:
                                                            //Solo para nominas quincenales
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = montoDescuentoOriginal / 4;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = montoDescuentoOriginal / 2;
                                                            } else {
                                                                importe = montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = montoDescuentoOriginal / 2;
                                                            break;
                                                        default:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = (montoDescuentoOriginal / diasMes) * diasIMSS;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = (montoDescuentoOriginal / diasBimestre) * diasIMSS;
                                                            } else if (isMov2Meses) {
                                                                importe = (montoDescuentoOriginal / diasPeriodo) * diasIMSS;
                                                            } else {
                                                                importe = montoDescuento;
                                                            }
                                                    }
                                                }
                                            } else if (isMov2Meses) {
                                                importe = (montoDescuento / diasPeriodo) * diasIMSS;
                                            } else {
                                                importe = montoDescuento;
                                            }
                                            List<Object> listobject = null;
                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().isActivarManejoDescuento()) {
                                                listobject = ejercutarManejoDescuento(creditoMovimientosDescuentoSistema, diasIMSS, importe, listMovNomConcepCreditosAhorroDescuentoActivo, diasMes, diasBimestre, creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                importe = (Double) listobject.get(0);
                                                listMovNomConcepCreditosAhorroDescuentoActivo = (List<MovNomConcep>) listobject.get(1);
                                                if (listobject.get(2) != null) {
                                                    listMovNomConcepCreditosAhorroDescuentoParaGuardar.add((MovNomConcep) listobject.get(2));
                                                }
                                            }
                                            importeSinMascara = importe;
                                            importe = aplicarMascara(creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi(), importe, false);
                                            importeAcumulado += importe;
                                            if (importe > 0) {
                                                if (creditoMovimientosDescuentoSistema.getId() == null) {
                                                    List<MovNomConcep> values = existeMovimientoNomina(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                    int numero = creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getNumero();
                                                    if (values != null) {
                                                        for (j = 0; j < values.size(); j++) {
                                                            if (values.get(j).getNumero() >= numero) {
                                                                numero = values.get(j).getNumero();
                                                            }
                                                        }
                                                        if (values.size() > 0) {
                                                            numero++;
                                                        }
                                                        creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setNumero(numero);
                                                    }
                                                }
                                                creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setResultado(importe);
                                                creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setCalculado(importeSinMascara);
//                                                creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().setSaldo(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getResultado());

                                                creditoMovimientosDescuentoSistema.setImporte(importeAcumulado);
//                                                System.out.println("No. Ahorro" + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getNumeroCredito()
//                                                        + " CreditoAhorro.Clave " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getClave()
//                                                        + " tipoConfiguracion " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getTipoConfiguracion()
//                                                        + " importe " + creditoMovimientosDescuentoSistema.getImporte()
//                                                        + " saldo " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo());
                                                cantidadSaveUpdate++;
//////                                                getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getId() == null) {
                                                    getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                } else {
                                                    getSession().merge(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                }
                                                cantidadSaveUpdate++;
                                                /////getSession().saveOrUpdate(creditoMovimientosDescuentoSistema);
                                            } else {
                                                importeAcumulado -= importe;
                                                if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getId() != null) {
                                                    getSession().delete(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                    creditoMovimientosDescuentoSistema.getMovNomConceps().remove(k);
                                                    cantidadSaveUpdate++;
                                                }

                                                if (creditoMovimientosDescuentoSistema.getId() != null & !tieneMovOtrasCorridas) {
                                                    if (creditoMovimientosDescuentoSistema.getMovNomConceps().isEmpty()) {
                                                        getSession().delete(creditoMovimientosDescuentoSistema);
                                                        cantidadSaveUpdate++;
                                                    }
                                                }
                                            }
                                            if (cantidadSaveUpdate % cantidadFlush == 0 & cantidadSaveUpdate > 0) {
//                                                System.out.println("flush 3");
                                                getSession().flush();
                                                getSession().clear();
//                                                System.out.println("clear()");
                                            }
                                        }
                                        if (!listMovNomConcepCreditosAhorroDescuentoParaGuardar.isEmpty()) {
                                            creditoMovimientosDescuentoSistema.getMovNomConceps().addAll(listMovNomConcepCreditosAhorroDescuentoParaGuardar);
                                            getSession().saveOrUpdate(creditoMovimientosDescuentoSistema);
                                            for (int k = 0; k < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); k++) {
                                                creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setCreditoMovimientos(creditoMovimientosDescuentoSistema);
                                                getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
//                                                System.out.println("CreditoEmpleado ID " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getCreditoMovimientos().getCreditoPorEmpleado().getId());
//                                                System.out.println("CreditoMovimientos getId " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getCreditoMovimientos().getId());
//                                                System.out.println("Resultado " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getResultado());
//                                                System.out.println("concepto clave " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getConcepNomDefi().getClave());
//                                                System.out.println("Numero " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getNumero());
//                                                System.out.println("Id " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getId());
//                                                System.out.println("periodo Id " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getPeriodosNomina().getId());
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (creditoMovimientosBloqueo != null) {
                            CreditoMovimientos creditoMovimientosDescuentoSistema = null;
                            creditoMovimientosDescuentoSistema = obtenerCreditoMovimientosPorPeriodoNomina(listCreditoPorEmpleado.get(i), TiposMovimiento.AbonoSistema);
                            if (creditoMovimientosDescuentoSistema != null) {
                                for (int k = 0; k < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); k++) {
//                                        creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().setSaldo((creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() - creditoMovimientosDescuentoSistema.getImporte()));
                                    getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado());
                                    getSession().delete(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                }
                                creditoMovimientosDescuentoSistema.getMovNomConceps().clear();
                                if (!tieneMovOtrasCorridas) {
                                    getSession().delete(creditoMovimientosDescuentoSistema);
                                }
                            }
                        }
                    }
                }
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaDescuentosAhorro()1_Error: ").append(ex));
        }
    }

    private void variablesTipoTabla(String variable) {
        Object resultado = null;
        String funcion = variable;
        String parametroFuncion = variable.substring(funcion.indexOf("(") + 1, funcion.indexOf(")")).replace("'", "");
        String funcionNombre = variable.substring(0, funcion.indexOf("("));
        String[] parametrosFuncion = parametroFuncion.split(",");
        String nombreTable;
        String valorBuscar;
        String buscarEn;
        int valorCol;
        String y;
        String x;
        Object valorb = 0;

        if (funcionNombre.equalsIgnoreCase("ValorTabla")) {
            nombreTable = parametrosFuncion[0].replace("'", "");
            valorBuscar = parametrosFuncion[1].replace("'", "");
            buscarEn = parametrosFuncion[2].replace("'", "");
            valorCol = Integer.parseInt(parametrosFuncion[3].replace("'", ""));
            nombreTable = nombreTable.replace("_", " ");
            for (int i = 0; i < tablaDatosXml.size(); i++) {
                if (tablaDatosXml.get(i).getTablaPersonalizada() != null) {
                    if (tablaDatosXml.get(i).getTablaPersonalizada().getDescripcion().toUpperCase().equals(nombreTable)) {
                        cargaValoresXMLtoTabla(tablaDatosXml.get(i));
                        tipoDatoTableXml tipo = new tipoDatoTableXml();
                        tipo = convertirDato(valorBuscar);
                        if (tipo.getTipoDato() == String.class) {
                            if ("VERDADERO".equals(valorBuscar.toUpperCase())) {
                                valorb = true;
                            } else if ("FALSO".equals(valorBuscar.toUpperCase())) {
                                valorb = false;
                            } else {
                                valorb = valoresConceptosEmpleados.get(valorBuscar.toUpperCase());
                            }
                        } else {
                            valorb = tipo.getValor();
                        }
                        resultado = buscarValorTablaXML(funcionNombre, valorb, buscarEn, valorCol, null, null);
                        break;
                    }
                } else if (tablaDatosXml.get(i).getTablaBase().getDescripcion().toUpperCase().equals(nombreTable)) {
                    cargaValoresXMLtoTabla(tablaDatosXml.get(i));
                    tipoDatoTableXml tipo = new tipoDatoTableXml();
                    tipo = convertirDato(valorBuscar);
                    if (tipo.getTipoDato() == String.class) {
                        if ("VERDADERO".equals(valorBuscar.toUpperCase())) {
                            valorb = true;
                        } else if ("FALSO".equals(valorBuscar.toUpperCase())) {
                            valorb = false;
                        } else {
                            valorb = valoresConceptosEmpleados.get(valorBuscar.toUpperCase());
                        }
                    } else {
                        valorb = tipo.getValor();
                    }
                    resultado = buscarValorTablaXML(funcionNombre, valorb, buscarEn, valorCol, null, null);
                    break;
                }
            }
        } else if (funcionNombre.equalsIgnoreCase("ValorTablaXY")) {
            nombreTable = parametrosFuncion[0].replace("'", "");
            x = parametrosFuncion[1].replace("'", "");
            y = parametrosFuncion[2].replace("'", "");
            nombreTable = nombreTable.replace("_", " ");
            for (int i = 0; i < tablaDatosXml.size(); i++) {
                if (tablaDatosXml.get(i).getTablaPersonalizada() != null) {
                    if (tablaDatosXml.get(i).getTablaPersonalizada().getDescripcion().toUpperCase().equals(nombreTable)) {
                        cargaValoresXMLtoTabla(tablaDatosXml.get(i));
                        tipoDatoTableXml tipo = new tipoDatoTableXml();
                        tipo = convertirDato(y);
                        if (tipo.getTipoDato() == String.class) {
                            if ("VERDADERO".equals(y.toUpperCase())) {
                                valorb = true;
                            } else if ("FALSO".equals(y.toUpperCase())) {
                                valorb = false;
                            } else {
                                valorb = valoresConceptosEmpleados.get(y.toUpperCase());
                            }
                        } else {
                            valorb = tipo.getValor();
                        }
                        resultado = buscarValorTablaXML(funcionNombre, null, null, 0, x, y);
                        break;
                    }
                } else if (tablaDatosXml.get(i).getTablaBase().getDescripcion().toUpperCase().equals(nombreTable)) {
                    cargaValoresXMLtoTabla(tablaDatosXml.get(i));
                    tipoDatoTableXml tipo = new tipoDatoTableXml();
                    tipo = convertirDato(y);
                    if (tipo.getTipoDato() == String.class) {
                        valorb = valoresConceptosEmpleados.get(y.toUpperCase());
                    } else {
                        valorb = tipo.getValor();
                    }
                    resultado = buscarValorTablaXML(funcionNombre, null, null, 0, x, y);
                    break;
                }
            }

        }
        funcion = funcion.replace("(", "").replace("'", "").replace(",", "").replace(")", "");
        valoresConceptosEmpleados.put(funcion, resultado);

    }

    private Object buscarValorTablaXML(String funcion, Object valorBuscar, String buscarEn, int valorCol, String x, Object y) {
        Object res = 0;
        Integer fila = null;
        int ini = 0;
        int fin = 0;
        Double valorBuscarDo = 0.0;
        Boolean valorBuscarBo = false;
        //Calendar valorBuscarCal = Calendar.getInstance();
        Date valorBuscarDa = null;
        String valorBuscarStr = "";
        try {

            if (funcion.equalsIgnoreCase("ValorTabla".toUpperCase())) {
                String[] rangod = buscarEn.split(":");
                tipoDatoTableXml tipoValorBuscar = new tipoDatoTableXml();
                tipoValorBuscar = convertirDato(String.valueOf(valorBuscar));
                if (tipoValorBuscar.getTipoDato() == Double.class) {
                    valorBuscarDo = Double.parseDouble(String.valueOf(valorBuscar));
                } else if (tipoValorBuscar.getTipoDato() == Boolean.class) {
                    valorBuscarBo = Boolean.parseBoolean(String.valueOf(valorBuscar));
                } else if (tipoValorBuscar.getTipoDato() == Date.class) {
                    valorBuscarDa = (Date) valorBuscar;
                    valorBuscarDa = quitaHrsDeFecha(valorBuscarDa);
                } else if (tipoValorBuscar.getTipoDato() == String.class) {
                    valorBuscarStr = (String) valorBuscar;
                }
                if (rangod.length == 1) {//cuando buscar por una sola columna en la funcion valor tabla
                    ini = Integer.parseInt(rangod[0]);
                    for (int i = 0; i < matrixcargaXml.length; i++) {
                        String valorExtraer = (String) matrixcargaXml[i][ini - 1];
                        tipoDatoTableXml tipo = new tipoDatoTableXml();
                        tipo = convertirDato(valorExtraer);
                        if (tipo.getTipoDato() == Double.class) {
                            if (tipo.getTipoDato() == valorBuscarDo.getClass()) {
                                Double valor = (Double) tipo.getValor();
                                if (valorBuscarDo < valor && i > 0) {
                                    fila = i - 1;
                                    break;
                                } else if (Double.compare(valorBuscarDo, valor) == 0 && i == matrixcargaXml.length - 1) {
                                    fila = i;
                                    break;
                                } else if (Double.compare(valorBuscarDo, valor) == 0 && i > 0) {
                                    fila = i;
                                    break;
                                } else if (Double.compare(valorBuscarDo, valor) == 0 && i == 0) {
                                    fila = i;
                                    break;
                                }
                            }
                        } else if (tipo.getTipoDato() == Date.class) {
                            if (tipo.getTipoDato() == valorBuscarDa.getClass()) {
                                Date valorhrs = (Date) tipo.getValor();
                                Date valor = quitaHrsDeFecha(valorhrs);
                                if (valorBuscarDa.compareTo(valor) < 0 && i > 0) {
                                    fila = i - 1;
                                    break;
                                } else if (valorBuscarDa.compareTo(valor) == 0 && i == matrixcargaXml.length) {
                                    fila = i;
                                    break;
                                } else if (valorBuscarDa.compareTo(valor) == 0 && i == 0) {
                                    fila = i;
                                    break;
                                }

                            }
                        } else if (tipo.getTipoDato() == String.class) {
                            if (tipo.getTipoDato() == valorBuscarStr.getClass()) {
                                String valor = (String) tipo.getValor();
                                if (valorBuscarStr.equals(valor)) {
                                    fila = i;
                                    break;

                                }
                            }
                        } else if (tipo.getTipoDato() == Boolean.class) {
                            if (tipo.getTipoDato() == valorBuscarBo.getClass()) {
                                Boolean valor = (Boolean) tipo.getValor();
                                if (valor == valorBuscarBo) {
                                    fila = i;
                                    break;
                                }
                            }

                        }
                    }

                } else if (rangod.length == 2) {//cuando es por rangos y es funcion valortabla
                    ini = Integer.parseInt(rangod[0]);
                    fin = Integer.parseInt(rangod[1]);
                    for (int i = 0; i < matrixcargaXml.length; i++) {
                        tipoDatoTableXml tipo = new tipoDatoTableXml();
                        tipoDatoTableXml tipo2 = new tipoDatoTableXml();
                        String valor1 = (String) matrixcargaXml[i][ini - 1];
                        String valor2 = (String) matrixcargaXml[i][fin - 1];
                        tipo = convertirDato(valor1);
                        tipo2 = convertirDato(valor2);
                        if (tipo.getTipoDato() == Double.class && tipo2.getTipoDato() == Double.class) {
                            Double valor = (Double) tipo.getValor();
                            Double valor3 = (Double) tipo2.getValor();
                            if (tipo.getTipoDato() == valorBuscarDo.getClass() && tipo2.getTipoDato() == valorBuscarDo.getClass()) {
                                if (valorBuscarDo >= valor && valorBuscarDo <= valor3) {
                                    fila = i;
                                    break;
                                }
                            }
                        } else if (tipo.getTipoDato() == Date.class && tipo2.getTipoDato() == Date.class) {
                            if (tipo.getTipoDato() == valorBuscarDa.getClass() && tipo2.getTipoDato() == valorBuscarDa.getClass()) {
                                Date valorhrs = (Date) tipo.getValor();
                                Date valorhrs2 = (Date) tipo2.getValor();
                                Date fecha = quitaHrsDeFecha(valorhrs);
                                Date fecha2 = quitaHrsDeFecha(valorhrs2);
                                if ((valorBuscarDa.compareTo(fecha) > 0 || valorBuscarDa.compareTo(fecha) == 0)
                                        && (valorBuscarDa.compareTo(fecha2) < 0 || valorBuscarDa.compareTo(fecha) == 0)) {
                                    fila = i;
                                    break;

                                }
                            }
                        }

                    }

                }
                if (fila != null) {
                    tipoDatoTableXml restipo;
                    restipo = convertirDato((String) matrixcargaXml[fila][valorCol - 1]);
                    res = restipo.getValor();
                } else {
                    res = 0;
                }
            } else if (funcion.equalsIgnoreCase("ValorTablaXY".toUpperCase())) {
                tipoDatoTableXml tipo4 = new tipoDatoTableXml();
                tipo4 = convertirDato(String.valueOf(y));
                if (tipo4.getTipoDato() == Double.class) {
                    valorBuscarDo = Double.parseDouble(String.valueOf(y));
                } else if (tipo4.getTipoDato() == Boolean.class) {
                    valorBuscarBo = Boolean.parseBoolean(String.valueOf(y));
                } else if (tipo4.getTipoDato() == Date.class) {
                    valorBuscarDa = (Date) y;
                    valorBuscarDa = quitaHrsDeFecha(valorBuscarDa);
                } else if (tipo4.getTipoDato() == String.class) {
                    valorBuscarStr = (String) y;
                }
                ini = 0;
                for (int i = 0; i < matrixcargaXml.length; i++) {
                    String valorExtraer = (String) matrixcargaXml[i][ini];
                    tipoDatoTableXml tipo = new tipoDatoTableXml();
                    tipo = convertirDato(valorExtraer);
                    if (tipo.getTipoDato() == Double.class) {
                        if (tipo.getTipoDato() == valorBuscarDo.getClass()) {
                            Double valor = (Double) tipo.getValor();
                            if (valorBuscarDo < valor && i > 0) {
                                fila = i - 1;
                                break;
                            } else if (Double.compare(valorBuscarDo, valor) == 0 && i == matrixcargaXml.length - 1) {
                                fila = i;
                                break;
                            } else if (Double.compare(valorBuscarDo, valor) == 0 && i > 0) {
                                fila = i;
                                break;
                            } else if (Double.compare(valorBuscarDo, valor) == 0 && i == 0) {
                                fila = i;
                                break;
                            }
                        }
                    } else if (tipo.getTipoDato() == Date.class) {
                        if (tipo.getTipoDato() == valorBuscarDo.getClass()) {
                            Date valorhrs = (Date) tipo.getValor();
                            Date valor = quitaHrsDeFecha(valorhrs);
                            if (valorBuscarDa.compareTo(valor) < 0 && i > 0) {
                                fila = i - 1;
                                break;
                            } else if (valorBuscarDa.compareTo(valor) == 0 && i == matrixcargaXml.length) {
                                fila = i;
                                break;
                            } else if (valorBuscarDa.compareTo(valor) == 0 && i == 0) {
                                fila = i;
                                break;
                            }

                        }
                    } else if (tipo.getTipoDato() == String.class) {
                        if (tipo.getTipoDato() == valorBuscarStr.getClass()) {
                            String valor = (String) tipo.getValor();
                            if (valorBuscarStr.equals(valor)) {
                                fila = i;
                                break;

                            }
                        }
                    } else if (tipo.getTipoDato() == Boolean.class) {
                        if (tipo.getTipoDato() == valorBuscarBo.getClass()) {
                            Boolean valor = (Boolean) tipo.getValor();
                            if (valor == valorBuscarBo) {
                                fila = i;
                                break;
                            }
                        }

                    }
                }
                if (fila != null) {
                    int col = Integer.parseInt(x);
                    tipoDatoTableXml restipo;
                    restipo = convertirDato((String) matrixcargaXml[fila][col - 1]);
                    res = restipo.getValor();

                } else {
                    res = 0;
                }
            }
        } catch (Exception e) {
            mensajeResultado.setNoError(-101);
            mensajeResultado.setError("ERROR al procesar la funcion " + funcion + " " + e.getMessage());
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaFormula()1_Error: ").append(e));
        }
        return res;

    }

    private class tipoDatoTableXml {

        private Class tipoDato;
        private Object valor;

        public Class getTipoDato() {
            return tipoDato;
        }

        public void setTipoDato(Class tipoDato) {
            this.tipoDato = tipoDato;
        }

        public Object getValor() {
            return valor;
        }

        public void setValor(Object valor) {
            this.valor = valor;
        }

        private tipoDatoTableXml() {
        }

    }

    private tipoDatoTableXml convertirDato(String valor) {
        tipoDatoTableXml dato = new tipoDatoTableXml();

        try {
            Double valor1 = Double.parseDouble((String) valor);
            //double a = (double) valor;
            dato.setTipoDato(Double.class);
            dato.setValor(valor1);
        } catch (Exception ex) {
            try {

                Date valor1 = new Date((String) valor);
                dato.setTipoDato(Date.class);
                dato.setValor(valor1);
            } catch (Exception a) {
                try {
                    Boolean valor1 = Boolean.parseBoolean(valor);
                    dato.setTipoDato(Boolean.class);
                    dato.setValor(valor1);
                } catch (Exception e) {
                    dato.setTipoDato(String.class);
                    dato.setValor(valor);

                }

            }

        }
        return dato;
    }

    private void cargaValoresXMLtoTabla(TablaDatos datos) {
        if (datos.getFileXml() != null) {
            if (datos.getFileXml() != null) {
                docXML = convierteStringToXML(datos.getFileXml());
                //docXmlToString2();
                root = docXML.getDocumentElement();
                cargaValores();
            }

        }
    }

    private Document convierteStringToXML(byte[] xmlString) {
        Document doc = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(new String(xmlString, Charset.forName("UTF-8"))));
            factory.setIgnoringElementContentWhitespace(true);
            is.setEncoding("UTF-8");
            doc = builder.parse(is);
        } catch (SAXException ex) {
            System.out.println(ex);

        } catch (IOException ex) {
            System.out.println(ex);
        } catch (ParserConfigurationException ex) {
            System.out.println(ex);
        }
        return doc;
    }

    private void cargaValores() {
        final NodeList list = root.getElementsByTagName("dato");
        if (list != null) {

            for (int i = 0; i < list.getLength(); i++) {
                Element element = (Element) list.item(i);
                NodeList list2 = element.getChildNodes();
                for (int j = 0; j < list2.getLength(); j++) {
                    if (list2.item(j) instanceof DeferredTextImpl) {
                        element.removeChild(list2.item(j));
                    }
                }
                list2 = element.getChildNodes();
                if (matrixcargaXml == null) {
                    matrixcargaXml = new Object[list.getLength()][list2.getLength()];
                }
                for (int j = 0; j < list2.getLength(); j++) {
                    Element elementcol = (Element) list2.item(j);
                    matrixcargaXml[i][j] = elementcol.getTextContent();
                    System.out.println(elementcol.getTextContent());

                }

            }
        }
    }

    private Double ejecutaDescuentosPrestamos(PlazasPorEmpleadosMov plazasPorEmpleadosMov, List<MovNomConcep> filtroMovimientosNominas, Double importeNeto, List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoActivo) {
        int i, j;
        Double importeAcumulado = 0.0;
        boolean tieneMovOtrasCorridas = false;
        try {
//            System.out.println("*************************************");
//            System.out.println("Empleado " + plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave());
            List<CreditoPorEmpleado> listCreditoPorEmpleado = obtenerCreditoPorEmpleado(plazasPorEmpleadosMov, "1", filtroMovimientosNominas);
            int[] diasMeses = new int[2];
            Calendar fechaBimestre = Calendar.getInstance();
            fechaBimestre.setTime(periodosNomina.getAcumularAMes());
            diasMeses[0] = fechaBimestre.getActualMaximum(Calendar.DAY_OF_MONTH);
            if ((fechaBimestre.get(Calendar.MONTH) + 1) % 2 == 0) {
                fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) - 1);
            } else {
                fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) + 1);
            }
            diasMeses[1] = fechaBimestre.getActualMaximum(Calendar.DAY_OF_MONTH);
            Double diasMes, diasBimestre;
            diasBimestre = Double.valueOf((diasMeses[0] + diasMeses[1]));
            if (mensajeResultado.getNoError() == 0) {
                for (i = 0; i < listCreditoPorEmpleado.size(); i++) {
                    CreditoMovimientos creditoMovimientosCambioDescuento = null, creditoMovimientosBloqueo = null;
                    Boolean continuarEjecucion = true;
                    if (listCreditoPorEmpleado.get(i).getCreditoAhorro().isInicioDescuento()) {
                        if (listCreditoPorEmpleado.get(i).getPeriodosNomina().getFechaInicial().compareTo(periodosNomina.getFechaInicial()) > 0) {
                            continuarEjecucion = false;
                        }
                    } else if (listCreditoPorEmpleado.get(i).getFechaCredito().compareTo(periodosNomina.getFechaFinal()) > 0) {
                        continuarEjecucion = false;
                    }
                    creditoMovimientosBloqueo = obtenerCreditoMovimientosMax(listCreditoPorEmpleado.get(i), TiposMovimiento.Bloqueo);
                    if (mensajeResultado.getNoError() == 0) {
                        if (creditoMovimientosBloqueo != null) {
                            Double cantidad = obtenerCantidadPeriodoNominaRango(plazasPorEmpleadosMov.getTipoNomina(), creditoMovimientosBloqueo.getPeriodosNomina(), periodosNomina);
                            if (cantidad < creditoMovimientosBloqueo.getNumeroPeriodosBloquear()) {
                                continuarEjecucion = false;
                            }
                        }
                        if (continuarEjecucion) {
                            Double montoDescuento, montoDescuentoOriginal = listCreditoPorEmpleado.get(i).getMontoDescuento();
                            creditoMovimientosCambioDescuento = obtenerCreditoMovimientosMax(listCreditoPorEmpleado.get(i), TiposMovimiento.ModificarDescuento);
                            if (mensajeResultado.getNoError() == 0 && continuarEjecucion) {
                                if (creditoMovimientosCambioDescuento != null) {
                                    montoDescuentoOriginal = creditoMovimientosCambioDescuento.getImporte();
                                }
                                if (montoDescuentoOriginal > 0) {
                                    CreditoMovimientos creditoMovimientosDescuentoSistema = null;
                                    creditoMovimientosDescuentoSistema = obtenerCreditoMovimientosPorPeriodoNomina(listCreditoPorEmpleado.get(i), TiposMovimiento.AbonoSistema);
                                    if (listCreditoPorEmpleado.get(i).getCreditoAhorro().getModoDescuento() == 2) {//Especificar Número de Parcialidades
                                        if (!obtenerNumeroParcialidadesCreditoMovimientos(listCreditoPorEmpleado.get(i), TiposMovimiento.AbonoSistema, creditoMovimientosDescuentoSistema)) {
                                            continuarEjecucion = false;
                                        }
                                    }
                                    if (continuarEjecucion) {
                                        if (creditoMovimientosDescuentoSistema == null) {
                                            creditoMovimientosDescuentoSistema = crearCreditoMovimientoSistema(listCreditoPorEmpleado.get(i), false, filtroMovimientosNominas, null);
                                        } else {
//                                            creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().setSaldo(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() + creditoMovimientosDescuentoSistema.getImporte());
                                            boolean addMovimiento = true;
                                            if (creditoMovimientosDescuentoSistema.getMovNomConceps() == null ? false : !creditoMovimientosDescuentoSistema.getMovNomConceps().isEmpty()) {
                                                int recorre;
                                                for (recorre = 0; recorre < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); recorre++) {
                                                    if (tipoCorrida.getClave().equals(creditoMovimientosDescuentoSistema.getMovNomConceps().get(recorre).getTipoCorrida().getClave())) {
                                                        addMovimiento = false;
                                                    }
                                                }
                                                recorre = 0;
                                                while (recorre < creditoMovimientosDescuentoSistema.getMovNomConceps().size()) {
                                                    if (tipoCorrida.getClave().equals(creditoMovimientosDescuentoSistema.getMovNomConceps().get(recorre).getTipoCorrida().getClave())) {
                                                        recorre++;
                                                    } else {
                                                        creditoMovimientosDescuentoSistema.getMovNomConceps().remove(recorre);
                                                        tieneMovOtrasCorridas = true;
                                                    }
                                                }
                                            }
                                            if (creditoMovimientosDescuentoSistema.getMovNomConceps() == null ? true : creditoMovimientosDescuentoSistema.getMovNomConceps().isEmpty()) {
                                                if (addMovimiento) {
                                                    creditoMovimientosDescuentoSistema = crearCreditoMovimientoSistema(listCreditoPorEmpleado.get(i), true, filtroMovimientosNominas, creditoMovimientosDescuentoSistema);
                                                } else if (isMov2Meses) {
                                                    creditoMovimientosDescuentoSistema = crearCreditoMovimientoSistema(listCreditoPorEmpleado.get(i), true, filtroMovimientosNominas, creditoMovimientosDescuentoSistema);
                                                }
                                            }
                                        }

                                        //<editor-fold defaultstate="collapsed" desc="Aqui te separa el movimiento del concepto del manejo del descuento">
                                        //Aqui te separa el movimiento del concepto del manejo del descuento.
                                        if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().isActivarManejoDescuento()) {
                                            if (!creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getConcepNomiDefin().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                                                int k = 0;
                                                while (k < creditoMovimientosDescuentoSistema.getMovNomConceps().size()) {
                                                    if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                                                        listMovNomConcepCreditosAhorroDescuentoActivo.add(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                        creditoMovimientosDescuentoSistema.getMovNomConceps().remove(k);
                                                    } else {
                                                        k++;
                                                    }
                                                }
                                            }
                                        }
//</editor-fold>
                                        Calendar cFechax = Calendar.getInstance();
                                        cFechax.setTime(periodosNomina.getAcumularAMes());
                                        diasMes = creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getFactorProporcional() == 1 ? factorMensual : cFechax.getActualMaximum(Calendar.MONTH);
                                        List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoParaGuardar = new ArrayList<MovNomConcep>();
                                        importeAcumulado = 0.0;
                                        for (int k = 0; k < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); k++) {//JSA21
//                                            System.out.println("Saldo " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo());
                                            Double importe = Double.valueOf(montoDescuentoOriginal.toString()), importeSinMascara;
                                            montoDescuento = montoDescuentoOriginal;
//                                            System.out.println("diasMes " + diasMes + " diasBimestre " + diasBimestre);
                                            int diasPeriodo = 0;
                                            if (isMov2Meses) {
                                                diasPeriodo = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual//obtener dias reales del periodo;
                                                Calendar cFecha = Calendar.getInstance();
                                                if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getNumMovParticion() == 1) {
                                                    cFecha.setTime(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getPeriodosNomina().getFechaInicial());
                                                    cFecha.set(Calendar.DATE, cFecha.getActualMaximum(Calendar.DATE));
                                                    inicializaPeriodo2Meses(periodosNomina, periodosNomina.getFechaInicial(), cFecha.getTime());
                                                    valoresConceptosGlobales.put(parametroFechaFinal, cFecha);
                                                    valoresConceptosGlobales.put(parametroFechaInicial, dateToCalendar(periodosNomina.getFechaInicial()));
                                                } else {
                                                    cFecha.setTime(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getPeriodosNomina().getFechaFinal());
                                                    cFecha.set(Calendar.DATE, 1);
                                                    inicializaPeriodo2Meses(periodosNomina, cFecha.getTime(), periodosNomina.getFechaFinal());
                                                    valoresConceptosGlobales.put(parametroFechaInicial, cFecha);
                                                    valoresConceptosGlobales.put(parametroFechaFinal, dateToCalendar(periodosNomina.getFechaFinal()));
                                                }

                                                valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
                                                cargaValoresDiasCotizados(plazasPorEmpleadosMov.getFechaIMSS(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), true, null, null, false, false);//JSA30
                                                cargaDatosVariableConfiguracionIMSS(((Calendar) valoresConceptosGlobales.get(parametroFechaFinal)).getTime());
                                            }
                                            Double diasIMSS = Double.parseDouble(valoresConceptosEmpleados.get("DiasCotizados".toUpperCase()).toString());
                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoDescuento() == 1) {//Elegir Modo al Registrar el Credito
                                                Double montoSDI = Double.parseDouble(valoresConceptosEmpleados.get("SueldoIntIMSS".toUpperCase()).toString());
                                                Double SMDF = Double.parseDouble(valoresConceptosEmpleados.get("SalarioMinDF".toUpperCase()).toString());
                                                //0.-Importe, 1.-VSM, 2.-Porcentaje

                                                if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getModoDescuentoCredito() == 1) {//VSM
                                                    if (versionCalculoPrestamoAhorro == 4 || versionCalculoPrestamoAhorro == 5) {
                                                        //Solo para nominas quincenales
                                                        if (plazasPorEmpleadosMov.getTipoNomina().getPeriodicidad().getDias() != 15) {
                                                            versionCalculoPrestamoAhorro = 1;
                                                        }
                                                    }
                                                    switch (versionCalculoPrestamoAhorro) {
                                                        case 2:
                                                            //Bimestral, Mensual y Por Periodo
                                                            importe = (SMDF * (montoDescuentoOriginal * 2)) * (diasIMSS / diasBimestre);
                                                            //Original: importe = (SMDF * (montoDescuentoOriginal * 2)) * (diasIMSS / diasBimestre);
                                                            break;
                                                        case 3:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)
                                                                    || creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Bimestral y mensual
                                                                importe = (SMDF * montoDescuentoOriginal) * (diasIMSS / diasMes);
                                                            } else {
                                                                importe = SMDF * montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = (SMDF * montoDescuentoOriginal) * (diasIMSS / diasMes);
                                                            break;
                                                        case 4:
                                                            //Solo para nominas quincenales
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = (SMDF * montoDescuentoOriginal) / 4;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = (SMDF * montoDescuentoOriginal) / 2;
                                                            } else {
                                                                importe = SMDF * montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = (SMDF * montoDescuentoOriginal) / 2;//mensual
                                                            break;
                                                        case 5:
                                                            //Solo para nominas quincenales
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = SMDF * (montoDescuentoOriginal / 4);
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = SMDF * (montoDescuentoOriginal / 2);
                                                            } else {
                                                                importe = SMDF * montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = SMDF * (montoDescuentoOriginal / 2);//mensual
                                                            break;
                                                        default:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoVSMG().equals(0)) {//Por periodo
                                                                if (isMov2Meses) {
                                                                    importe = SMDF * ((montoDescuento / diasPeriodo) * diasIMSS);
                                                                } else {
                                                                    importe = SMDF * montoDescuento;
                                                                }
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoVSMG().equals(2)) {//Bimestral
                                                                montoDescuento = (montoDescuentoOriginal / diasBimestre);
                                                                importe = (SMDF * montoDescuento) * diasIMSS;//montoDescuento debe ser mensual
                                                            } else {
                                                                montoDescuento = (montoDescuentoOriginal / diasMes);
                                                                importe = (SMDF * montoDescuento) * diasIMSS;//montoDescuento debe ser mensual
                                                            }
                                                    }
                                                } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getModoDescuentoCredito() == 2) {//Porcentaje
                                                    if (versionCalculoPrestamoAhorro == 3) {
                                                        //Solo para nominas quincenales
                                                        if (plazasPorEmpleadosMov.getTipoNomina().getPeriodicidad().getDias() != 15) {
                                                            versionCalculoPrestamoAhorro = 1;
                                                        }
                                                    }
                                                    switch (versionCalculoPrestamoAhorro) {
                                                        case 2:
                                                            Double factorIntegracion = (Double) valoresConceptosEmpleados.get("FactorIntegracion".toUpperCase());
                                                            importe = (acumuladoNormal + acumuladoDirecto + acumuladoAnual) * (montoDescuentoOriginal / 100) * factorIntegracion;
                                                            //Original: importe = (acumuladoNormal + acumuladoDirecto + acumuladoAnual) * (montoDescuentoOriginal / 100) * factorIntegracion;
                                                            break;
                                                        case 3:
                                                            //Solo para nominas quincenales
                                                            importe = ((montoSDI * diasBimestre) * (montoDescuentoOriginal / 100)) / 4;
                                                            //Original: importe = ((montoSDI * diasBimestre) * (montoDescuentoOriginal / 100)) / 4;
                                                            break;
                                                        default:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoPorc().equals(1)) {//Mensual
                                                                importe = (((montoSDI * diasMes) * (montoDescuentoOriginal / 100)) / diasMes) * diasIMSS;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuentoPorc().equals(2)) {//Bimestral
                                                                importe = (((montoSDI * diasBimestre) * (montoDescuentoOriginal / 100)) / diasBimestre) * diasIMSS;
                                                            } else {
                                                                importe = (montoSDI * (montoDescuentoOriginal / 100)) * diasIMSS;
                                                            }
                                                    }
//                                                    System.out.println("montoSDI " + montoSDI + " montoDescuento " + montoDescuento + " diasIMSS " + diasIMSS);
                                                } else {//Importe
                                                    if (versionCalculoPrestamoAhorro == 3) {
                                                        //Solo para nominas quincenales
                                                        if (plazasPorEmpleadosMov.getTipoNomina().getPeriodicidad().getDias() != 15) {
                                                            versionCalculoPrestamoAhorro = 1;
                                                        }
                                                    }
                                                    switch (versionCalculoPrestamoAhorro) {
                                                        case 2:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = (montoDescuentoOriginal / diasBimestre) * diasIMSS;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = ((montoDescuentoOriginal * 2) / diasBimestre) * diasIMSS;
                                                            } else {
                                                                importe = montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = ((montoDescuentoOriginal * 2) / diasBimestre) * diasIMSS;
                                                            break;
                                                        case 3:
                                                            //Solo para nominas quincenales
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = montoDescuentoOriginal / 4;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = montoDescuentoOriginal / 2;
                                                            } else {
                                                                importe = montoDescuentoOriginal;
                                                            }
                                                            //Original: importe = montoDescuentoOriginal / 2;
                                                            break;
                                                        default:
                                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(1)) {//Mensual
                                                                importe = (montoDescuentoOriginal / diasMes) * diasIMSS;
                                                            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoCapturaDescuento().equals(2)) {//Bimestral
                                                                importe = (montoDescuentoOriginal / diasBimestre) * diasIMSS;
                                                            } else if (isMov2Meses) {
                                                                importe = (montoDescuentoOriginal / diasPeriodo) * diasIMSS;
                                                            } else {
                                                                importe = montoDescuento;
                                                            }
                                                    }
                                                }
                                            } else if (isMov2Meses) {
                                                importe = (montoDescuento / diasPeriodo) * diasIMSS;
                                            } else {
                                                importe = montoDescuento;
                                            }
                                            List<Object> listobject = null;
                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().isActivarManejoDescuento()) {
                                                listobject = ejercutarManejoDescuento(creditoMovimientosDescuentoSistema, diasIMSS, importe, listMovNomConcepCreditosAhorroDescuentoActivo, diasMes, diasBimestre, creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                importe = (Double) listobject.get(0);
                                                listMovNomConcepCreditosAhorroDescuentoActivo = (List<MovNomConcep>) listobject.get(1);
                                                if (listobject.get(2) != null) {
                                                    listMovNomConcepCreditosAhorroDescuentoParaGuardar.add((MovNomConcep) listobject.get(2));
                                                }
                                            }
                                            importeSinMascara = importe;
                                            importe = aplicarMascara(creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi(), importe, false);
                                            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getTotalCredito() > 0) {
                                                if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() - importe < 0) {
                                                    importe = importe + (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() - importe);
                                                }
                                            }
                                            importeAcumulado += importe;
                                            if (importe > 0) {
                                                if (creditoMovimientosDescuentoSistema.getId() == null) {
                                                    List<MovNomConcep> values = existeMovimientoNomina(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                    int numero = creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getNumero();
                                                    if (values != null) {
                                                        for (j = 0; j < values.size(); j++) {
                                                            if (values.get(j).getNumero() >= numero) {
                                                                numero = values.get(j).getNumero();
                                                            }
                                                        }
                                                        if (values.size() > 0) {
                                                            numero++;
                                                        }
                                                        creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setNumero(numero);
                                                    }
                                                }
                                                creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setResultado(importe);
                                                creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setCalculado(importeSinMascara);
//                                                creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().setSaldo(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() - creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getResultado());
                                                creditoMovimientosDescuentoSistema.setImporte(importeAcumulado);

//                                                System.out.println("No. Credito " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getNumeroCredito()
//                                                        + " CreditoAhorro.Clave " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getClave()
//                                                        + " tipoConfiguracion " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getTipoConfiguracion()
//                                                        + " importe " + creditoMovimientosDescuentoSistema.getImporte()
//                                                        + " saldo " + creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo());
                                                cantidadSaveUpdate++;
                                                creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setCreditoMovimientos(creditoMovimientosDescuentoSistema);
//                                                getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
//                                                try {
                                                if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getId() == null) {
                                                    getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                } else {
                                                    getSession().merge(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                }
                                                cantidadSaveUpdate++;
                                                ////getSession().saveOrUpdate(creditoMovimientosDescuentoSistema);
//                                                } catch (HibernateException ex) {
//                                                    mensajeResultado.setError(ex.getMessage());
//                                                    mensajeResultado.setNoError(27);
//                                                    System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaDescuentosPrestamos()1_Error: ").append(ex));
//                                                }
                                            } else {
                                                importeAcumulado -= importe;
                                                if (creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getId() != null) {
                                                    getSession().delete(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                                    creditoMovimientosDescuentoSistema.getMovNomConceps().remove(k);
                                                    cantidadSaveUpdate++;
                                                }

                                                if (creditoMovimientosDescuentoSistema.getId() != null & !tieneMovOtrasCorridas) {
                                                    if (creditoMovimientosDescuentoSistema.getMovNomConceps().isEmpty()) {
                                                        getSession().delete(creditoMovimientosDescuentoSistema);
                                                        cantidadSaveUpdate++;
                                                    }
                                                }
                                            }
                                            if (cantidadSaveUpdate % cantidadFlush == 0 & cantidadSaveUpdate > 0) {
//                                                System.out.println("flush 4");
                                                getSession().flush();
                                                getSession().clear();
//                                                System.out.println("clear()");
                                            }
                                        }//termina for conceptos
                                        if (creditoMovimientosDescuentoSistema != null) {
                                            if (creditoMovimientosDescuentoSistema.getId() != null) {
                                                ////getSession().refresh(creditoMovimientosDescuentoSistema);//JEVC02
                                            }
                                        }
                                        if (!listMovNomConcepCreditosAhorroDescuentoParaGuardar.isEmpty()) {
                                            creditoMovimientosDescuentoSistema.getMovNomConceps().addAll(listMovNomConcepCreditosAhorroDescuentoParaGuardar);
                                            getSession().saveOrUpdate(creditoMovimientosDescuentoSistema);
                                            for (int k = 0; k < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); k++) {
                                                creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).setCreditoMovimientos(creditoMovimientosDescuentoSistema);
                                                getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
//                                                System.out.println("CreditoEmpleado ID " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getCreditoMovimientos().getCreditoPorEmpleado().getId());
//                                                System.out.println("CreditoMovimientos getId " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getCreditoMovimientos().getId());
//                                                System.out.println("Resultado " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getResultado());
//                                                System.out.println("concepto clave " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getConcepNomDefi().getClave());
//                                                System.out.println("Numero " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getNumero());
//                                                System.out.println("Id " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getId());
//                                                System.out.println("periodo Id " + creditoMovimientosDescuentoSistema.getMovNomConceps().get(k).getPeriodosNomina().getId());
                                            }
                                        }
                                    }
                                }
                            }
                        } else if (creditoMovimientosBloqueo != null) {
                            CreditoMovimientos creditoMovimientosDescuentoSistema = null;
                            creditoMovimientosDescuentoSistema = obtenerCreditoMovimientosPorPeriodoNomina(listCreditoPorEmpleado.get(i), TiposMovimiento.AbonoSistema);
                            if (creditoMovimientosDescuentoSistema != null) {
                                for (int k = 0; k < creditoMovimientosDescuentoSistema.getMovNomConceps().size(); k++) {
//                                        creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().setSaldo((creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getSaldo() + creditoMovimientosDescuentoSistema.getImporte()));
                                    getSession().saveOrUpdate(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado());
                                    getSession().delete(creditoMovimientosDescuentoSistema.getMovNomConceps().get(k));
                                }
                                creditoMovimientosDescuentoSistema.getMovNomConceps().clear();
                                if (!tieneMovOtrasCorridas) {
                                    getSession().delete(creditoMovimientosDescuentoSistema);
                                }
                            }
                        }
                    }

                }
//                if (!listMovNomConcepCreditosAhorroDescuentoPendienteDeEliminar.isEmpty()) {
//                    for (int k = 0; k < listMovNomConcepCreditosAhorroDescuentoPendienteDeEliminar.size(); k++) {
////                        getSession().delete(listMovNomConcepCreditosAhorroDescuentoPendienteDeEliminar.get(k));
//                    }
//                }
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaDescuentosPrestamos()1_Error: ").append(ex));
        }
        return importeNeto - importeAcumulado;
    }

    private List<Object> ejercutarManejoDescuento(CreditoMovimientos creditoMovimientosDescuentoSistema, Double diasIMSS, Double importe, List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoActivo, double factorMensual, double diasBimestre, MovNomConcep movNomConcepAbarca2Meses) {
        double importeDescuento;
        int indice = -1;
        try {
            importeDescuento = creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getImporteDescuento();
            int dias = 0;
            if (isMov2Meses) {
                dias = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual//obtener dias reales del periodo;
            }
            if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getPeriodicidadDescuento() == 0) {//Al descontar XXXXX
                if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getModoManejoDescuento() == 2) {//Por importe fijo
                    if (isMov2Meses) {
                        importeDescuento = (importeDescuento / dias) * diasIMSS;
                    }
                    if (!creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                        //<editor-fold defaultstate="collapsed" desc="Cuando son diferentes conceptos tanto para el credito principal como para el descuento, se tiene que buscar el movimiento, si no construirlo">
                        indice = obtenerMovNomConcepCreditosAhorroDescuentoActivo(listMovNomConcepCreditosAhorroDescuentoActivo, creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento(), creditoMovimientosDescuentoSistema, movNomConcepAbarca2Meses);
                        if (indice > -1) {
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setCalculado(importeDescuento);
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setResultado(importeDescuento);
                        }
                        //</editor-fold>
                    }
                    importe += importeDescuento;
                }
            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getPeriodicidadDescuento() == 1) {//Mensual
                if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 0) {// Descontar proporcionalmente cada periodo de nómina
                    importeDescuento = (importeDescuento / factorMensual) * diasIMSS;
                    if (!creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                        //<editor-fold defaultstate="collapsed" desc="Cuando son diferentes conceptos tanto para el credito principal como para el descuento, se tiene que buscar el movimiento, si no construirlo">
                        indice = obtenerMovNomConcepCreditosAhorroDescuentoActivo(listMovNomConcepCreditosAhorroDescuentoActivo, creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento(), creditoMovimientosDescuentoSistema, movNomConcepAbarca2Meses);
                        if (indice > -1) {
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setCalculado(importeDescuento);
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setResultado(importeDescuento);
                        }
                        //</editor-fold>
                    }
                    importe += importeDescuento;
                } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 1 //Descontar en el primer periodo de nómina del mes
                        || creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 2/*descontar en el último periodo del mes*/) {
                    boolean continuar = false;
                    if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 1) {//descontar en el primer periodo del mes
                        if (eresPeriodoDelMes(periodosNomina, true, false)) {
                            continuar = true;
                        }
                    } else if (periodosNomina.isCierreMes()) {//descontar en el último periodo del mes
                        continuar = true;
                    }
                    if (continuar) {
                        if (isMov2Meses) {
                            importeDescuento = (importeDescuento / factorMensual) * diasIMSS;
                        }
                        if (!creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                            //<editor-fold defaultstate="collapsed" desc="Cuando son diferentes conceptos tanto para el credito principal como para el descuento, se tiene que buscar el movimiento, si no construirlo">
                            indice = obtenerMovNomConcepCreditosAhorroDescuentoActivo(listMovNomConcepCreditosAhorroDescuentoActivo, creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento(), creditoMovimientosDescuentoSistema, movNomConcepAbarca2Meses);
                            if (indice > -1) {
                                listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setCalculado(importeDescuento);
                                listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setResultado(importeDescuento);
                            }
                            //</editor-fold>
                        }
                        importe += importeDescuento;
                    }
                }
            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getPeriodicidadDescuento() == 2) {//Bimestral
                if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 0) {// Descontar proporcionalmente cada periodo de nómina
                    importeDescuento = (importeDescuento / diasBimestre) * diasIMSS;
                    if (!creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                        //<editor-fold defaultstate="collapsed" desc="Cuando son diferentes conceptos tanto para el credito principal como para el descuento, se tiene que buscar el movimiento, si no construirlo">
                        indice = obtenerMovNomConcepCreditosAhorroDescuentoActivo(listMovNomConcepCreditosAhorroDescuentoActivo, creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento(), creditoMovimientosDescuentoSistema, movNomConcepAbarca2Meses);
                        if (indice > -1) {
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setCalculado(importeDescuento);
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setResultado(importeDescuento);
                        }
                        //</editor-fold>
                    }
                    importe += importeDescuento;
                } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 1 //Descontar en el primer periodo de nómina del bimestral
                        || creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 2/*descontar en el último periodo del bimestral*/) {
                    boolean continuar = false;
                    if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getCuandoDescontar() == 1) {//descontar en el primer periodo del bimestral
                        if (eresPeriodoDelMes(periodosNomina, false, false)) {
                            continuar = true;
                        }
                    } else if (eresPeriodoDelMes(periodosNomina, false, true)) {//descontar en el último periodo del bimestral
                        continuar = true;
                    }
                    if (continuar) {
                        if (isMov2Meses) {
                            importeDescuento = (importeDescuento / diasBimestre) * diasIMSS;
                        }
                        if (!creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                            //<editor-fold defaultstate="collapsed" desc="Cuando son diferentes conceptos tanto para el credito principal como para el descuento, se tiene que buscar el movimiento, si no construirlo">
                            indice = obtenerMovNomConcepCreditosAhorroDescuentoActivo(listMovNomConcepCreditosAhorroDescuentoActivo, creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento(), creditoMovimientosDescuentoSistema, movNomConcepAbarca2Meses);
                            if (indice > -1) {
                                listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setCalculado(importeDescuento);
                                listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setResultado(importeDescuento);
                            }
                            //</editor-fold>
                        }
                        importe += importeDescuento;
                    }
                }
            } else if (creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getPeriodicidadDescuento() == 3) {//Unico
                boolean existeDescuentoAplicado = buscarManejoDescuentoUnicoExistente(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getEmpleados().getId(),
                        creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getEmpleados().getRazonesSociales().getId(), tipoCorrida.getId(), periodosNomina.getTipoNomina().getId(), periodosNomina.getAño(),
                        creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getId(),
                        creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getId(), null);
                if (!existeDescuentoAplicado) {
                    if (isMov2Meses) {
                        importeDescuento = (importeDescuento / dias) * diasIMSS;
                    }
                    if (!creditoMovimientosDescuentoSistema.getMovNomConceps().get(0).getConcepNomDefi().getClave().equalsIgnoreCase(creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento().getClave())) {
                        //<editor-fold defaultstate="collapsed" desc="Cuando son diferentes conceptos tanto para el credito principal como para el descuento, se tiene que buscar el movimiento, si no construirlo">
                        indice = obtenerMovNomConcepCreditosAhorroDescuentoActivo(listMovNomConcepCreditosAhorroDescuentoActivo, creditoMovimientosDescuentoSistema.getCreditoPorEmpleado().getCreditoAhorro().getcNDescuento(), creditoMovimientosDescuentoSistema, movNomConcepAbarca2Meses);
                        if (indice > -1) {
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setCalculado(importeDescuento);
                            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setResultado(importeDescuento);
                        }
                        //</editor-fold>
                    }
                    importe += importeDescuento;
                }
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejercutarManejoDescuento()1_Error: ").append(ex));
        }
        if (indice > -1) {
//            for (int l = 0; l < listMovNomConcepCreditosAhorroDescuentoPendienteDeEliminar.size(); l++) {
//                if (listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).getId() != null) {
//                    if (listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).getId().equals(listMovNomConcepCreditosAhorroDescuentoPendienteDeEliminar.get(l).getId())) {
//                        listMovNomConcepCreditosAhorroDescuentoPendienteDeEliminar.remove(l);
//                    }
//                }
//            }
            listMovNomConcepCreditosAhorroDescuentoActivo.get(indice).setCreditoMovimientos(creditoMovimientosDescuentoSistema);
        }
        List<Object> listobject = new ArrayList<Object>();
        listobject.add(importe);
        listobject.add(listMovNomConcepCreditosAhorroDescuentoActivo);
        listobject.add(indice > -1 ? listMovNomConcepCreditosAhorroDescuentoActivo.get(indice) : null);//envio el movimiento que fue modificado
        return listobject;
    }

    private boolean eresPeriodoDelMes(PeriodosNomina periodosNominaEjecutandose, boolean isMes/*es para saber si buscara por mes o bimestre*/, boolean ultimoPeriodoBimestre/*Para saber si va evaluar si es el ultimo periodo del bimestre y claro tiene que venir false isMes*/) {
        boolean esPeriodoCorrecto = false;
        try {
            List<PeriodosNomina> listPeriodosNominas = null;
            if (isMes) {
                strQuery.delete(0, strQuery.length()).append("SELECT p ");
                strQuery.append("FROM PeriodosNomina p INNER JOIN p.tipoNomina t INNER JOIN p.tipoCorrida c INNER JOIN t.periodicidad pd WHERE ");
                if (HibernateUtil.usaTypeBigInt) {
                    strQuery.append("(p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as int)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn WHERE pn.clave < cast(:clavePeriodoNomina as int) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND pn.tipoCorrida.clave = :claveTipoCorrida) ");
                } else {
                    strQuery.append("(p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as long)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn WHERE pn.clave < cast(:clavePeriodoNomina as long) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND pn.tipoCorrida.clave = :claveTipoCorrida) ");
                }

                camposParametro = new ArrayList<String>(0);
                valoresParametro = new ArrayList<Object>(0);
                if (HibernateUtil.usaTypeBigInt) {
                    strQuery.append("AND p.clave <= (SELECT CASE WHEN (count(pn) > 0) THEN MIN(CAST(pn.clave as int)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn WHERE pn.clave > cast(:clavePeriodoNomina as int) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND pn.tipoCorrida.clave = :claveTipoCorrida)) ");
                } else {
                    strQuery.append("AND p.clave <= (SELECT CASE WHEN (count(pn) > 0) THEN MIN(CAST(pn.clave as long)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn WHERE pn.clave > cast(:clavePeriodoNomina as long) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND pn.tipoCorrida.clave = :claveTipoCorrida)) ");
                }

                strQuery.append("AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND c.clave = :claveTipoCorrida ");//AND :fechaIngresoEmp <= p.fechaFinal AND :fechaFinEmp >= p.fechaInicial");
                strQuery.append(" ORDER BY p.clave ");
                camposParametro.add("clavePeriodoNomina");
                camposParametro.add("claveTipoNomina");
                camposParametro.add("yearPeriodo");
                camposParametro.add("claveTipoCorrida");
                valoresParametro.add(valoresConceptosEmpleados.get("NumPeriodo".toUpperCase()));
                valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
                valoresParametro.add(valoresConceptosEmpleados.get("AnioPeriodo".toUpperCase()));
                valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
                listPeriodosNominas = (List<PeriodosNomina>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 1);
            } else {
                Calendar fechaBimestre = Calendar.getInstance();
                fechaBimestre.setTime(periodosNomina.getAcumularAMes());
                Integer[] meses = new Integer[2];
                meses[0] = (fechaBimestre.get(Calendar.MONTH) + 1);
                if ((fechaBimestre.get(Calendar.MONTH) + 1) % 2 == 0) {
                    fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) - 1);
                } else {
                    fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) + 1);
                }
                meses[1] = (fechaBimestre.get(Calendar.MONTH) + 1);

                //Bimestral       
                fechaBimestre.getTime();
                strQuery.delete(0, strQuery.length()).append("SELECT p ");
                strQuery.append("FROM PeriodosNomina p INNER JOIN p.tipoNomina t INNER JOIN p.tipoCorrida c WHERE t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND c.clave = :claveTipoCorrida AND (MONTH(p.AcumularAMes) in(:valoresMeses) AND YEAR(p.AcumularAMes) = :yearPeriodo) ");

                camposParametro = new ArrayList<String>(0);
                valoresParametro = new ArrayList<Object>(0);
                strQuery.append(" ORDER BY p.clave ");
                camposParametro.add("claveTipoNomina");
                camposParametro.add("yearPeriodo");
                camposParametro.add("claveTipoCorrida");
                camposParametro.add("valoresMeses");
                valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
                valoresParametro.add(valoresConceptosEmpleados.get("AnioPeriodo".toUpperCase()));
                valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
                valoresParametro.add(meses);
                listPeriodosNominas = (List<PeriodosNomina>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            }

            listPeriodosNominas = (listPeriodosNominas == null) ? new ArrayList<PeriodosNomina>() : listPeriodosNominas;
            if (!listPeriodosNominas.isEmpty()) {
                if (ultimoPeriodoBimestre) {
                    if (listPeriodosNominas.get(listPeriodosNominas.size() - 1).getId().equals(periodosNominaEjecutandose.getId())) {
                        esPeriodoCorrecto = true;
                    }
                } else if (listPeriodosNominas.get(0).getId().equals(periodosNominaEjecutandose.getId())) {
                    esPeriodoCorrecto = true;
                }
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eresPeriodoDelMes()1_Error: ").append(ex));
        }
        return esPeriodoCorrecto;
    }

    private boolean buscarManejoDescuentoUnicoExistente(Long idEmpleado, Long idRazonesSociales, Long idTipoCorrida, Long idTipoNomina, int ejercicio, Long idConcepto, Long idCreditoPorEmpleado, Long idMovimientosDescuentoExistente) {
        boolean existe = false;
        try {
            List<MovNomConcep> values = null;
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);

            strQuery.delete(0, strQuery.length()).append("from MovNomConcep o");

            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strWhere.append(" o.razonesSociales.id = :razonesSociales and o.empleado.id = :empleado and o.tipoNomina.id = :tipoNomina and ");
            strWhere.append(" o.tipoCorrida.id = :tipoCorrida and o.concepNomDefi.id = :concepNomDefi and o.ejercicio = :ejercicio and o.creditoMovimientos.creditoPorEmpleado.id = :creditoPorEmpleado ");
            camposParametro.add("razonesSociales");
            valoresParametro.add(idRazonesSociales);
            camposParametro.add("empleado");
            valoresParametro.add(idEmpleado);
            camposParametro.add("tipoNomina");
            valoresParametro.add(idTipoNomina);
            camposParametro.add("tipoCorrida");
            valoresParametro.add(idTipoCorrida);
            camposParametro.add("concepNomDefi");
            valoresParametro.add(idConcepto);
            camposParametro.add("ejercicio");
            valoresParametro.add(ejercicio);
            camposParametro.add("creditoPorEmpleado");
            valoresParametro.add(idCreditoPorEmpleado);
            strQuery.append(strWhere);
            values = (List<MovNomConcep>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            if (values == null ? false : !values.isEmpty()) {
                existe = true;
            }
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarManejoDescuentoUnicoExistente()1_Error: ").append(ex));
        }
        return existe;
    }

    private int obtenerMovNomConcepCreditosAhorroDescuentoActivo(List<MovNomConcep> listMovNomConcepCreditosAhorroDescuentoActivo, ConcepNomDefi conceptoDescuento, CreditoMovimientos creditoMovimientos, MovNomConcep movNomConcepAbarca2Meses) {
        int indice = -1;
        boolean continuar = false;
        MovNomConcep movNomConcepTmp = null;//utilizado para cuando en 2 creditosAhorros tienen el mismo concepto.
        int numero = 1;
        for (int i = 0; i < listMovNomConcepCreditosAhorroDescuentoActivo.size(); i++) {
            if (listMovNomConcepCreditosAhorroDescuentoActivo.get(i).getConcepNomDefi().getClave().equalsIgnoreCase(conceptoDescuento.getClave())) {
                continuar = true;
                if (listMovNomConcepCreditosAhorroDescuentoActivo.get(i).getNumero() > numero) {
                    numero = listMovNomConcepCreditosAhorroDescuentoActivo.get(i).getNumero();
                }
                if (listMovNomConcepCreditosAhorroDescuentoActivo.get(i).getCreditoMovimientos() != null) {
                    if (!listMovNomConcepCreditosAhorroDescuentoActivo.get(i).getCreditoMovimientos().getCreditoPorEmpleado().getId().equals(creditoMovimientos.getCreditoPorEmpleado().getId())) {
                        continuar = false;
                        movNomConcepTmp = listMovNomConcepCreditosAhorroDescuentoActivo.get(i);
                    } else {
                        movNomConcepTmp = null;
                    }
                }
                if (continuar) {
                    if (movNomConcepAbarca2Meses != null) {
                        if (!movNomConcepAbarca2Meses.getMes().equals(listMovNomConcepCreditosAhorroDescuentoActivo.get(i).getMes())) {
                            continuar = false;
                            movNomConcepTmp = listMovNomConcepCreditosAhorroDescuentoActivo.get(i);
                        } else {
                            movNomConcepTmp = null;
                        }
                    }
                }
                if (continuar) {
                    indice = i;
                    break;
                }
            }
        }
        if (movNomConcepTmp != null) {
            movNomConcepTmp = creaMovNomConcep(movNomConcepTmp.getConcepNomDefi(), movNomConcepTmp.getPlazasPorEmpleado(), periodosNomina, tipoCorrida, razonesSociales, centroDeCostoMovimiento);
            movNomConcepTmp.setCreditoMovimientos(creditoMovimientos);
            movNomConcepTmp.setNumero(numero + 1);
            listMovNomConcepCreditosAhorroDescuentoActivo.add(movNomConcepTmp);
            indice = listMovNomConcepCreditosAhorroDescuentoActivo.size() - 1;
            if (evaluaPeriodoMovAbarca2Meses(movNomConcepTmp.getPeriodosNomina())) {
                MovNomConcep newMov = MovNomConcep.copiaMovimiento(movNomConcepTmp);
                Calendar fechaPeriodo = Calendar.getInstance(), fechaPromocion = Calendar.getInstance();
                fechaPromocion.setTime(newMov.getPeriodosNomina().getFechaInicial());
                fechaPeriodo.setTime(newMov.getPeriodosNomina().getFechaFinal());
                newMov.setMes(fechaPeriodo.get(Calendar.MONTH) + 1);
                newMov.setNumMovParticion(2);
                listMovNomConcepCreditosAhorroDescuentoActivo.add(newMov);
                Integer mesUno = fechaPromocion.get(Calendar.MONTH) + 1;
                listMovNomConcepCreditosAhorroDescuentoActivo.get(listMovNomConcepCreditosAhorroDescuentoActivo.size() - 1).setEjercicio(periodosNomina.getAño());
                listMovNomConcepCreditosAhorroDescuentoActivo.get(listMovNomConcepCreditosAhorroDescuentoActivo.size() - 1).setMes(fechaPromocion.get(Calendar.MONTH) + 1);
                if (mesUno.equals(fechaPromocion.get(Calendar.MONTH) + 1)) {
                    listMovNomConcepCreditosAhorroDescuentoActivo.get(listMovNomConcepCreditosAhorroDescuentoActivo.size() - 1).setNumMovParticion(1);
                } else {
                    listMovNomConcepCreditosAhorroDescuentoActivo.get(listMovNomConcepCreditosAhorroDescuentoActivo.size() - 1).setNumMovParticion(2);
                }
            }
        }
        return indice;
    }

    private List<MovNomConcep> existeMovimientoNomina(MovNomConcep movNomConcep) {
        List<MovNomConcep> values = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);

            strQuery.delete(0, strQuery.length()).append("from MovNomConcep o");

            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strWhere.append(" o.razonesSociales.clave = :razonesSociales and o.empleado.clave = :empleado and o.tipoNomina.clave = :tipoNomina and o.periodosNomina.id = :periodosNomina and ");
            strWhere.append(" o.tipoCorrida.clave = :tipoCorrida and o.concepNomDefi.clave = :concepNomDefi and o.ejercicio = :ejercicio and o.mes = :mes ");
            camposParametro.add("razonesSociales");
            valoresParametro.add(movNomConcep.getRazonesSociales().getClave());
            camposParametro.add("empleado");
            valoresParametro.add(movNomConcep.getEmpleado().getClave());
            camposParametro.add("tipoNomina");
            valoresParametro.add(movNomConcep.getTipoNomina().getClave());
            camposParametro.add("periodosNomina");
            valoresParametro.add(movNomConcep.getPeriodosNomina().getId());
            camposParametro.add("tipoCorrida");
            valoresParametro.add(movNomConcep.getTipoCorrida().getClave());
            camposParametro.add("concepNomDefi");
            valoresParametro.add(movNomConcep.getConcepNomDefi().getClave());
            camposParametro.add("ejercicio");
            valoresParametro.add(movNomConcep.getEjercicio());
            camposParametro.add("mes");
            valoresParametro.add(movNomConcep.getMes());

            strQuery.append(strWhere);
            values = (List<MovNomConcep>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existeMovimientoNomina()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        }
        return values;
    }

    private CreditoMovimientos crearCreditoMovimientoSistema(CreditoPorEmpleado creditoPorEmpleado, boolean soloAgregarMovimientosNomina, List<MovNomConcep> filtroMovimientosNominas, CreditoMovimientos creditoMovimientos) {
        List<MovNomConcep> listMovNomConcepCreditosAhorro = new ArrayList<MovNomConcep>();
        int z = 0;
        boolean continuar = false;
        MovNomConcep movNomConcepTmp = null;//utilizado para cuando en 2 creditosAhorros tienen el mismo concepto.
        int numero = 1;
        while (z < filtroMovimientosNominas.size()) {
            if (creditoPorEmpleado.getCreditoAhorro().getConcepNomiDefin().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(z).getConcepNomDefi().getClave())) {
                continuar = true;
                if (filtroMovimientosNominas.get(z).getNumero() > numero) {
                    numero = filtroMovimientosNominas.get(z).getNumero();
                }
                if (filtroMovimientosNominas.get(z).getCreditoMovimientos() != null) {
                    if (!filtroMovimientosNominas.get(z).getCreditoMovimientos().getCreditoPorEmpleado().getId().equals(creditoPorEmpleado.getId())) {
                        continuar = false;
                        movNomConcepTmp = filtroMovimientosNominas.get(z);
                    } else {
                        movNomConcepTmp = null;
                    }
                } else {
                    movNomConcepTmp = null;
                }
            }
            if (continuar) {
//                System.out.println("concepto " + filtroMovimientosNominas.get(z).getConcepNomDefi().getDescripcion() + " indice " + z + " clave " + filtroMovimientosNominas.get(z).getConcepNomDefi().getClave());
                listMovNomConcepCreditosAhorro.add(filtroMovimientosNominas.get(z));
//                filtroMovimientosNominas.remove(z);
                continuar = false;
            }
            //else 
            {
                z++;
            }
        }
        if (movNomConcepTmp != null) {
            movNomConcepTmp = creaMovNomConcep(movNomConcepTmp.getConcepNomDefi(), movNomConcepTmp.getPlazasPorEmpleado(), periodosNomina, tipoCorrida, razonesSociales, centroDeCostoMovimiento);
            movNomConcepTmp.setCreditoMovimientos(creditoMovimientos);
            movNomConcepTmp.setNumero(numero + 1);
            listMovNomConcepCreditosAhorro.add(movNomConcepTmp);
            if (evaluaPeriodoMovAbarca2Meses(movNomConcepTmp.getPeriodosNomina())) {
                MovNomConcep newMov = MovNomConcep.copiaMovimiento(movNomConcepTmp);
                Calendar fechaPeriodo = Calendar.getInstance(), fechaPromocion = Calendar.getInstance();
                fechaPromocion.setTime(newMov.getPeriodosNomina().getFechaInicial());
                fechaPeriodo.setTime(newMov.getPeriodosNomina().getFechaFinal());
                newMov.setMes(fechaPeriodo.get(Calendar.MONTH) + 1);
                newMov.setNumMovParticion(2);
                listMovNomConcepCreditosAhorro.add(newMov);
                Integer mesUno = fechaPromocion.get(Calendar.MONTH) + 1;
                listMovNomConcepCreditosAhorro.get(listMovNomConcepCreditosAhorro.size() - 1).setEjercicio(periodosNomina.getAño());
                listMovNomConcepCreditosAhorro.get(listMovNomConcepCreditosAhorro.size() - 1).setMes(fechaPromocion.get(Calendar.MONTH) + 1);
                if (mesUno.equals(fechaPromocion.get(Calendar.MONTH) + 1)) {
                    listMovNomConcepCreditosAhorro.get(listMovNomConcepCreditosAhorro.size() - 1).setNumMovParticion(1);
                } else {
                    listMovNomConcepCreditosAhorro.get(listMovNomConcepCreditosAhorro.size() - 1).setNumMovParticion(2);
                }
            }
        }
        Collections.sort(listMovNomConcepCreditosAhorro, new Comparator() {

            @Override
            public int compare(Object o1, Object o2) {
                MovNomConcep e1 = (MovNomConcep) o1;
                MovNomConcep e2 = (MovNomConcep) o2;
                int resultado = Double.compare(e1.getEjercicio(), e2.getEjercicio());
                if (resultado != 0) {
                    return resultado;
                }
                resultado = Double.compare(e1.getMes(), e2.getMes());
                if (resultado != 0) {
                    return resultado;
                }
                return resultado;
            }
        });
        if (soloAgregarMovimientosNomina) {
            creditoMovimientos.setMovNomConceps(listMovNomConcepCreditosAhorro);
        } else {
            creditoMovimientos = new CreditoMovimientos();
            creditoMovimientos.setCreditoPorEmpleado(creditoPorEmpleado);
            creditoMovimientos.setTiposMovimiento(TiposMovimiento.AbonoSistema);
            creditoMovimientos.setFecha(periodosNomina.getFechaFinal());
            creditoMovimientos.setMovNomConceps(listMovNomConcepCreditosAhorro);
            creditoMovimientos.setImporte(creditoPorEmpleado.getMontoDescuento());
        }
        return creditoMovimientos;
    }

    private List<CreditoPorEmpleado> obtenerCreditoPorEmpleado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, String tipoConfiguracion, List<MovNomConcep> listMovNomConcep) {
        List<CreditoPorEmpleado> listCreditoPorEmpleado = null;
        try {
            List<String> listClaveConcep = new ArrayList<String>();
            for (int i = 0; i < listMovNomConcep.size(); i++) {
                listClaveConcep.add(listMovNomConcep.get(i).getConcepNomDefi().getClave());
            }
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("select cred from CreditoPorEmpleado cred ");
            strQuery.append(" inner join cred.razonesSociales rs inner join  cred.creditoAhorro creaho inner join creaho.concepNomiDefin concep inner join  cred.empleados em ");

            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strWhere.append(" em.clave =:claveEmpleado AND ");
            camposParametro.add("claveEmpleado");
            valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave());

            strWhere.append(" creaho.tipoConfiguracion = :tipoConfiguracion AND ");
            camposParametro.add("tipoConfiguracion");
            valoresParametro.add(tipoConfiguracion);

            strWhere.append(" rs.clave = :claveRazonSocial AND ");
            camposParametro.add("claveRazonSocial");
            valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());

            strWhere.append(" cred.fechaAutorizacion <= :fechaAutorizacion AND cred.inicioDescuento <= :fechaAutorizacion AND ");
            camposParametro.add("fechaAutorizacion");
            valoresParametro.add(periodosNomina.getFechaFinal());

            strWhere.append(" cred.fechaVence >= :fechaVence AND ");
            camposParametro.add("fechaVence");
            valoresParametro.add(periodosNomina.getFechaInicial());

            strWhere.append(" concep.clave in (:clavesConceptos) ");
            camposParametro.add("clavesConceptos");
            valoresParametro.add(listClaveConcep.toArray());

            // strWhere.append(" AND  cred.saldo > 0  ");
            strWhere.append("ORDER BY em.clave ");
            strQuery.append(strWhere);
            listCreditoPorEmpleado = (List<CreditoPorEmpleado>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerCreditoPorEmpleado()1_Error: ").append(ex));
        }
        return listCreditoPorEmpleado;
    }

    private CreditoMovimientos obtenerCreditoMovimientosMax(CreditoPorEmpleado creditoPorEmpleado, TiposMovimiento tiposMovimiento) {
        CreditoMovimientos creditoMovimientos = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("select credMov from CreditoMovimientos credMov inner join credMov.creditoPorEmpleado credEm ");
            strWhere.delete(0, strWhere.length()).append("WHERE ");
            strWhere.append("credMov.fecha= ( select max(credMov2.fecha) from CreditoMovimientos credMov2 inner join credMov2.creditoPorEmpleado credEm2 ");
            strWhere.append("WHERE credEm2.id = :creditoPorEmpleadoID AND credMov2.tiposMovimiento = :tiposMovimiento AND credMov2.fecha <= :fechaFinal ) ");
            strWhere.append("AND credEm.id = :creditoPorEmpleadoID AND credMov.tiposMovimiento = :tiposMovimiento ");

            camposParametro.add("creditoPorEmpleadoID");
            valoresParametro.add(creditoPorEmpleado.getId());

            camposParametro.add("tiposMovimiento");
            valoresParametro.add(tiposMovimiento);

            camposParametro.add("fechaFinal");
            valoresParametro.add(periodosNomina.getFechaFinal());

            strQuery.append(strWhere);
            creditoMovimientos = (CreditoMovimientos) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerCreditoMovimientosBloqueosYCambioDescuentos()1_Error: ").append(ex));
        }
        return creditoMovimientos;
    }

    private Boolean obtenerNumeroParcialidadesCreditoMovimientos(CreditoPorEmpleado creditoPorEmpleado, TiposMovimiento tiposMovimiento, CreditoMovimientos creditoMovimientosDescuentoSistema) {
        Boolean continuar = false;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("select count(credMov) from CreditoMovimientos credMov inner join credMov.creditoPorEmpleado credEm ");
            strWhere.delete(0, strWhere.length()).append("WHERE ");
            strWhere.append("credEm.id = :creditoPorEmpleadoID AND credMov.tiposMovimiento = :tiposMovimiento ");

            camposParametro.add("creditoPorEmpleadoID");
            valoresParametro.add(creditoPorEmpleado.getId());

            camposParametro.add("tiposMovimiento");
            valoresParametro.add(tiposMovimiento);

            strQuery.append(strWhere);
            Long numeroCreditoMovimientos = (Long) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (numeroCreditoMovimientos < creditoPorEmpleado.getNumeroParcialidades()) {
                continuar = true;
            } else if (creditoMovimientosDescuentoSistema != null) {
                if (creditoMovimientosDescuentoSistema.getId() != null) {
                    continuar = true;
                }
            }
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerNumeroParcialidadesCreditoMovimientos()1_Error: ").append(ex));
        }
        return continuar;
    }

    private CreditoMovimientos obtenerCreditoMovimientosPorPeriodoNomina(CreditoPorEmpleado creditoPorEmpleado, TiposMovimiento tiposMovimiento) {
        CreditoMovimientos creditoMovimientos = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("select credMov from CreditoMovimientos credMov inner join credMov.creditoPorEmpleado credEm ");
            strWhere.delete(0, strWhere.length()).append("WHERE ");
            strWhere.append("credMov.fecha BETWEEN  :fechaInicial AND :fechaFinal ");
            strWhere.append("AND credEm.id = :creditoPorEmpleadoID AND credMov.tiposMovimiento = :tiposMovimiento ");

            camposParametro.add("creditoPorEmpleadoID");
            valoresParametro.add(creditoPorEmpleado.getId());

            camposParametro.add("tiposMovimiento");
            valoresParametro.add(tiposMovimiento);

            camposParametro.add("fechaInicial");
            valoresParametro.add(periodosNomina.getFechaInicial());
            camposParametro.add("fechaFinal");
            valoresParametro.add(periodosNomina.getFechaFinal());

            strQuery.append(strWhere);
            creditoMovimientos = (CreditoMovimientos) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerCreditoMovimientosPorPeriodoNomina()1_Error: ").append(ex));
        }
        return creditoMovimientos;
    }

    private Double obtenerCantidadPeriodoNominaRango(TipoNomina tipoNomina, PeriodosNomina periodoInicial, PeriodosNomina periodoActual) {
        Long cantidad = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("select count(p) from PeriodosNomina p ");

            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strWhere.append("p.tipoNomina.clave = :tipoNomina AND ");
            camposParametro.add("tipoNomina");
            valoresParametro.add(tipoNomina.getClave());

            strWhere.append("p.id > :periodoInicialID AND ");
            camposParametro.add("periodoInicialID");
            valoresParametro.add(periodoInicial.getId());

            strWhere.append("p.id <= :periodoActualID ");
            camposParametro.add("periodoActualID");
            valoresParametro.add(periodoActual.getId());
            strQuery.append(strWhere);
            cantidad = (Long) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());

            camposParametro = null;
            valoresParametro = null;
            return cantidad.doubleValue();
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerCreditoMovimientosBloqueosYCambioDescuentos()1_Error: ").append(ex));
            return 0.0;
        }
    }

    private void ejecutaConceptosPorMovimientoNomina(List<MovNomConcep> filtroMovimientosNominas, String claveTipoCorrida, PlazasPorEmpleadosMov plazasPorEmpleadosMov, int posicionPlazaPorEmpleadoMovEjecutandose, List<CalculoUnidades> listCalculoUnidadesTmp) {
        if (filtroMovimientosNominas != null) {
            int j, i, k, indicePlazasPorEmpleadoMov;
            boolean b = false;
            MovNomConcep movimientosNomina;
            boolean tempPagoDiasNat;
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadosMovOficial = new ArrayList<PlazasPorEmpleadosMov>();
//            System.out.println("Empleado    " + filtroMovimientosNominas.get(0).getEmpleado().getClave() + " *******************************total concep " + filtroMovimientosNominas.size());
            boolean cargoSueldoDiarioX = false, guardarCambiosCalculosUnidades = true;
            try {
                for (j = 0; j < filtroMovimientosNominas.size(); j++) {
                    movimientosNomina = filtroMovimientosNominas.get(j);
                    if (movimientosNomina.getConcepNomDefi().getFormulaConcepto() == null) {
                        movimientosNomina.getConcepNomDefi().setFormulaConcepto("");
                    }
                    listPlazasPorEmpleadosMovOficial.removeAll(listPlazasPorEmpleadosMovOficial);
                    listPlazasPorEmpleadosMovOficial.add(plazasPorEmpleadosMov);
                    boolean configuracionSueldoDiarioVigente = false, configuracionPercepcion_Plaza = false, configuracionPercepcion_Plaza_Vigente = false;
                    //<editor-fold defaultstate="collapsed" desc="Esta programacion es especial ya que es solo para cuando vienen las variables:SueldoDiarioVigente,percep_plaza_vigente,SueldoDiarioInicial,percep_plaza y SueldoDiarioFinal (este ultimo no tiene ya que desde el metodo obtenerPlazasPorEmpleados ya viene con el movimiento maximo en el periodo)">
                    if (movimientosNomina.getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("SueldoDiarioVigente".toUpperCase())) {
                        configuracionSueldoDiarioVigente = true;
                    } else if (movimientosNomina.getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("SueldoDiarioInicial".toUpperCase()) & !cargoSueldoDiarioX) {//Te busca el minimo movimiento de la plaza por empleado que tuvo dentro de periodo.
                        List<PlazasPorEmpleadosMov> listPromocionesDentroPeriodo = obtenerMinimoPlazasPorEmpleadosMovDentroPeriodo(tipoCorrida.getClave(), periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal(), plazasPorEmpleadosMov);
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }
                        cargoSueldoDiarioX = true;
                        if (listPromocionesDentroPeriodo == null ? false : (!listPromocionesDentroPeriodo.isEmpty())) {
                            cargaDatosSalarioDiario(listPromocionesDentroPeriodo.get(0), TipoSueldos.SUELDODIARIOINICIAL);
                        } else {
                            cargaDatosSalarioDiario(plazasPorEmpleadosMov, TipoSueldos.SUELDODIARIOINICIAL);
                        }
                    } else if (movimientosNomina.getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("percep_plaza_vigente".toUpperCase())) {
                        configuracionPercepcion_Plaza_Vigente = true;
                    } else if (movimientosNomina.getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("percep_plaza".toUpperCase())) {
                        configuracionPercepcion_Plaza = true;
                    }
                    //</editor-fold>
                    List<MovNomConcep> listMovNomConcepPromocional = new ArrayList<MovNomConcep>();
                    if (configuracionPercepcion_Plaza | configuracionPercepcion_Plaza_Vigente | configuracionSueldoDiarioVigente) {
                        List<Object> tmp = obtenerModificacionesDePlazasPorEmpleadoMov(configuracionSueldoDiarioVigente, configuracionPercepcion_Plaza, configuracionPercepcion_Plaza_Vigente, movimientosNomina, plazasPorEmpleadosMov);
                        listPlazasPorEmpleadosMovOficial = (List<PlazasPorEmpleadosMov>) tmp.get(0);
                        listMovNomConcepPromocional = (List<MovNomConcep>) tmp.get(1);
                        if (listPlazasPorEmpleadosMovOficial.size() > listCalculoUnidadesTmp.size()) {
                            for (k = listCalculoUnidadesTmp.size(); k < listPlazasPorEmpleadosMovOficial.size(); k++) {
                                CalculoUnidades calculoUnidades = crearCalculoUnidades(listMovNomConcepPromocional.get(k).getPlazasPorEmpleado());
                                calculoUnidades.setMes(listMovNomConcepPromocional.get(k).getMes());
                                calculoUnidades.setNumero(listMovNomConcepPromocional.get(k).getNumero());
                                calculoUnidades.setNumMovParticion(listMovNomConcepPromocional.get(k).getNumMovParticion());
                                calculoUnidades.setEjercicio(listMovNomConcepPromocional.get(k).getEjercicio());
                                listCalculoUnidadesTmp.add(calculoUnidades);
                            }
                        } else if (listPlazasPorEmpleadosMovOficial.size() < listCalculoUnidadesTmp.size() & listPlazasPorEmpleadosMovOficial.size() > 1) {
                            while (listCalculoUnidadesTmp.size() > listMovNomConcepPromocional.size()) {
                                getSession().delete(listCalculoUnidadesTmp.get(listCalculoUnidadesTmp.size() - 1));
                                listCalculoUnidadesTmp.remove(listCalculoUnidadesTmp.size() - 1);
                            }
//                            System.out.println("flush 5");
                            getSession().flush();
                            getSession().clear();
                        }
                        if (listPlazasPorEmpleadosMovOficial.size() > 1) {
                            for (int l = 0; l < listMovNomConcepPromocional.size(); l++) {
                                listCalculoUnidadesTmp.get(l).setMes(listMovNomConcepPromocional.get(l).getMes());
                                listCalculoUnidadesTmp.get(l).setEjercicio(listMovNomConcepPromocional.get(l).getEjercicio());
                                listCalculoUnidadesTmp.get(l).setNumMovParticion(listMovNomConcepPromocional.get(l).getNumMovParticion());
                                listCalculoUnidadesTmp.get(l).setNumero(listMovNomConcepPromocional.get(l).getNumero());
                                listCalculoUnidadesTmp.get(l).setUso(listMovNomConcepPromocional.get(l).getUso());
                            }
                        }
                    } else {
                        listMovNomConcepPromocional.add(movimientosNomina);
                    }
                    {
                        indicePlazasPorEmpleadoMov = 0;
                        boolean aumentarIndicePlazasPorEmpleadoMov = true;
                        for (i = 0; i < listMovNomConcepPromocional.size(); i++) {
                            movimientosNomina = listMovNomConcepPromocional.get(i);
                            //<editor-fold defaultstate="collapsed" desc="validacion de Bases afecta y parametros, esto se agrego por los movimientos que ya existen ">
                            if ((movimientosNomina.getConcepNomDefi().getBaseAfecConcepNomi() == null ? 0 : movimientosNomina.getConcepNomDefi().getBaseAfecConcepNomi().isEmpty() ? 0
                                    : movimientosNomina.getConcepNomDefi().getBaseAfecConcepNomi().size())
                                    != (movimientosNomina.getMovNomBaseAfecta() == null ? 0 : movimientosNomina.getMovNomBaseAfecta().isEmpty() ? 0
                                    : movimientosNomina.getMovNomBaseAfecta().size())) {//JSA27
                                movimientosNomina.setMovNomBaseAfecta(creaMovimBaseAfectar(movimientosNomina.getConcepNomDefi().getBaseAfecConcepNomi(), movimientosNomina));
                            }

                            if ((movimientosNomina.getConcepNomDefi().getParametroConceptosDeNominas() == null ? 0 : movimientosNomina.getConcepNomDefi().getParametroConceptosDeNominas().isEmpty() ? 0
                                    : movimientosNomina.getConcepNomDefi().getParametroConceptosDeNominas().size())
                                    != (movimientosNomina.getMovNomConceParam() == null ? 0 : movimientosNomina.getMovNomConceParam().isEmpty() ? 0
                                    : movimientosNomina.getMovNomConceParam().size())) {//JSA27
                                movimientosNomina.setMovNomConceParam(creaMovNomConceParam(movimientosNomina.getConcepNomDefi(), movimientosNomina));
                            }
                            //</editor-fold>
//                            System.out.println("Movimientos " + movimientosNomina.getConcepNomDefi().getDescripcion() + " formula " + movimientosNomina.getConcepNomDefi().getFormulaConcepto());
                            isMov2Meses = false;
                            tempPagoDiasNat = manejaPagoDiasNaturales;    // respalda parametro pago dias naturales
                            if (evaluaPeriodoMovAbarca2Meses(movimientosNomina.getPeriodosNomina())) {
                                isMov2Meses = true;
                                manejaPagoDiasNaturales = true;
////////                                if (retenidosISRACargoYSubsidioAlEmpleoEn2Meses == null) {
////////                                    retenidosISRACargoYSubsidioAlEmpleoEn2Meses = new double[9];
////////                                }
                            }
                            if (listMovNomConcepPromocional.size() > 1) {
                                guardarCambiosCalculosUnidades = false;
                                //<editor-fold defaultstate="collapsed" desc="programacion para cuando existan modificaciones salariales">
                                if (i > 0) {
                                    movimientosNomina.setNumero(i + 1);
                                    Set<String> keys = valoresConceptosEmpleados.keySet();
                                    TipoClasificacionFormula tfc;
//////                                System.out.println("ENTRO FOR KEY PROMO");
                                    for (final String key : keys) {
                                        tfc = TipoClasificacionFormula.getEnum(propertieFuente.getProperty(key.concat("_TipoDato")));
                                        if (tfc == TipoClasificacionFormula.DATOCALCULO) {
                                            valoresConceptosEmpleados.put(key, "");
                                        }
                                    }
                                }
                                if (configuracionPercepcion_Plaza || configuracionPercepcion_Plaza_Vigente) {
                                    if (indicePlazasPorEmpleadoMov + 1 <= listPlazasPorEmpleadosMovOficial.size() - 1) {
                                        if (listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov + 1).getPlazasPorEmpleado().getClave().equalsIgnoreCase(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getPlazasPorEmpleado().getClave())) {
                                            cargaValoresDiasPago(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov), false, listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov + 1), listCalculoUnidadesTmp.get(i), false, true);
//                                            System.out.println("*********getDiasTrabajados 1 " + listCalculoUnidadesTmp.get(i).getDiasTrabajados());
                                            if (listCalculoUnidadesTmp.get(i).getId() == null) {
                                                getSession().saveOrUpdate(listCalculoUnidadesTmp.get(i));
                                            } else {
                                                getSession().merge(listCalculoUnidadesTmp.get(i));
                                            }
                                        } else {
                                            cargaValoresDiasPago(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov), false, null, listCalculoUnidadesTmp.get(i), false, true);//JSA30
//                                            System.out.println("*********getDiasTrabajados 2 " + listCalculoUnidadesTmp.get(i).getDiasTrabajados());
                                            if (listCalculoUnidadesTmp.get(i).getId() == null) {
                                                getSession().saveOrUpdate(listCalculoUnidadesTmp.get(i));
                                            } else {
                                                getSession().merge(listCalculoUnidadesTmp.get(i));
                                            }
                                        }
                                    } else {
                                        cargaValoresDiasPago(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov), true, null, listCalculoUnidadesTmp.get(i), false, true);//JSA30
//                                        System.out.println("*********getDiasTrabajados 3 " + listCalculoUnidadesTmp.get(i).getDiasTrabajados());
                                        if (listCalculoUnidadesTmp.get(i).getId() == null) {
                                            getSession().saveOrUpdate(listCalculoUnidadesTmp.get(i));
                                        } else {
                                            getSession().merge(listCalculoUnidadesTmp.get(i));
                                        }
                                    }
                                } else if (isMov2Meses) {
                                    Calendar fechaInicio = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
                                    if (movimientosNomina.getNumMovParticion() == 1) {
                                        if (indicePlazasPorEmpleadoMov == 0) {
                                            fechaInicio.setTime(periodosNomina.getFechaInicial());
                                        } else {
                                            fechaInicio.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial());
                                        }
                                        if (listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial().compareTo(periodosNomina.getFechaInicial()) < 0) {
                                            fechaFinal.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov + 1).getFechaInicial());
                                            fechaFinal.set(Calendar.DATE, fechaFinal.get(Calendar.DATE) - 1);
                                        } else if (indicePlazasPorEmpleadoMov + 1 < listPlazasPorEmpleadosMovOficial.size()) {
                                            fechaFinal.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov + 1).getFechaInicial());
                                            Calendar c = Calendar.getInstance();
                                            c.setTime(periodosNomina.getFechaFinal());
                                            if (fechaFinal.get(Calendar.MONTH) == c.get(Calendar.MONTH)) {
                                                fechaFinal.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial());
                                                fechaFinal.set(Calendar.DATE, fechaFinal.getActualMaximum(Calendar.DATE));
                                            } else {
                                                fechaFinal.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov + 1).getFechaInicial());
                                                fechaFinal.set(Calendar.DATE, fechaFinal.get(Calendar.DATE) - 1);
                                            }
                                        } else if (indicePlazasPorEmpleadoMov + 1 == listPlazasPorEmpleadosMovOficial.size()) {
                                            fechaFinal.setTime(periodosNomina.getFechaFinal());
                                        }
                                    } else {
                                        if (i == 0 ? false : listMovNomConcepPromocional.get(i).getMes() != listMovNomConcepPromocional.get(i - 1).getMes()) {
                                            fechaInicio.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial());
                                            fechaInicio.set(Calendar.DATE, fechaInicio.getActualMinimum(Calendar.DATE));
                                            if (fechaInicio.getTime().compareTo(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial()) > 0) {
                                                fechaInicio.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial());
                                            } else {
                                                aumentarIndicePlazasPorEmpleadoMov = false;
                                            }
                                        } else {
                                            fechaInicio.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial());
                                        }
                                        if (fechaInicio.getTime().compareTo(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial()) < 0) {
                                            fechaFinal.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaInicial());
                                            fechaFinal.set(Calendar.DATE, fechaFinal.get(Calendar.DATE) - 1);
                                        } else if (indicePlazasPorEmpleadoMov + 1 < listPlazasPorEmpleadosMovOficial.size()) {
                                            fechaFinal.setTime(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov + 1).getFechaInicial());
                                            fechaFinal.set(Calendar.DATE, fechaFinal.get(Calendar.DATE) - 1);
                                        } else {
                                            fechaFinal.setTime(periodosNomina.getFechaFinal());
                                        }
                                    }
                                    inicializaPeriodo2Meses(periodosNomina, fechaInicio.getTime(), fechaFinal.getTime());
                                    valoresConceptosGlobales.put(parametroFechaFinal, fechaFinal);
                                    valoresConceptosGlobales.put(parametroFechaInicial, fechaInicio);
//                                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//                                        System.out.println("fechaInicio " + formato.format(fechaInicio.getTime()) + " fechaFinal " + formato.format(fechaFinal.getTime()));
                                    valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
                                    cargaValoresDiasPago(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov), indicePlazasPorEmpleadoMov < 1,
                                            null, listCalculoUnidadesTmp.get(i), i == 0 ? false : listMovNomConcepPromocional.get(i).getMes() != listMovNomConcepPromocional.get(i - 1).getMes(), true);//JSA30
                                    cargaValoresDiasCotizados(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getFechaIMSS(), listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getPlazasPorEmpleado().getFechaFinal(), (listPlazasPorEmpleadosMovOficial.size() <= 1), null, null, i == 0 ? false : listMovNomConcepPromocional.get(i).getMes() != listMovNomConcepPromocional.get(i - 1).getMes(), false);//JSA30
                                    cargaDatosVariableConfiguracionIMSS(((Calendar) valoresConceptosGlobales.get(parametroFechaFinal)).getTime());
//                                        System.out.println("*********getDiasTrabajados 5 " + listCalculoUnidadesTmp.get(i).getDiasTrabajados());
                                    if (listCalculoUnidadesTmp.get(i).getId() == null) {
                                        getSession().saveOrUpdate(listCalculoUnidadesTmp.get(i));
                                    } else {
                                        getSession().merge(listCalculoUnidadesTmp.get(i));
                                    }
                                } else {
                                    cargaValoresDiasPago(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov), false, i + 1 < listPlazasPorEmpleadosMovOficial.size() ? listPlazasPorEmpleadosMovOficial.get(i + 1) : null, listCalculoUnidadesTmp.get(i), false, true);//JSA30
//                                        System.out.println("*********getDiasTrabajados 6 " + listCalculoUnidadesTmp.get(i).getDiasTrabajados());
                                    if (listCalculoUnidadesTmp.get(i).getId() == null) {
                                        getSession().saveOrUpdate(listCalculoUnidadesTmp.get(i));
                                    } else {
                                        getSession().merge(listCalculoUnidadesTmp.get(i));
                                    }
                                }
                                if (configuracionSueldoDiarioVigente) {
                                    cargaDatosSalarioDiario(indicePlazasPorEmpleadoMov < listPlazasPorEmpleadosMovOficial.size() ? listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov) : listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov - 1), TipoSueldos.SUELDODIARIOVIGENTE);
                                } else if (configuracionPercepcion_Plaza) {
                                    cargaDatosSalarioDiario(indicePlazasPorEmpleadoMov < listPlazasPorEmpleadosMovOficial.size() ? listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov) : listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov - 1), TipoSueldos.PERCEP_PLAZA);
                                } else {
                                    cargaDatosSalarioDiario(indicePlazasPorEmpleadoMov < listPlazasPorEmpleadosMovOficial.size() ? listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov) : listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov - 1), TipoSueldos.PERCEP_PLAZA_VIGENTE);
                                }
//</editor-fold>
                            } else if (configuracionSueldoDiarioVigente | configuracionPercepcion_Plaza_Vigente) {
                                if (configuracionSueldoDiarioVigente) {
                                    cargaDatosSalarioDiario(listPlazasPorEmpleadosMovOficial.get(0), TipoSueldos.SUELDODIARIOVIGENTE);
                                } else {
                                    cargaDatosSalarioDiario(listPlazasPorEmpleadosMovOficial.get(0), TipoSueldos.PERCEP_PLAZA_VIGENTE);
                                }
                            }
                            if (configuracionPercepcion_Plaza_Vigente | configuracionPercepcion_Plaza) {
                                movimientosNomina.setPlazasPorEmpleado(listPlazasPorEmpleadosMovOficial.get(indicePlazasPorEmpleadoMov).getPlazasPorEmpleado());
                            }
                            //<editor-fold defaultstate="collapsed" desc="Esta programacion es para saber si el periodo abarco 2 meses">                            
                            if (isMov2Meses & listPlazasPorEmpleadosMovOficial.size() == 1) {
                                if (!configuracionSueldoDiarioVigente || !configuracionPercepcion_Plaza || !configuracionPercepcion_Plaza_Vigente) {
                                    guardarCambiosCalculosUnidades = false;
                                }
                                Calendar cFecha = Calendar.getInstance();
                                Integer indiceCalculoUnidad;
                                if (movimientosNomina.getNumMovParticion() == 1) {
                                    cFecha.setTime(movimientosNomina.getPeriodosNomina().getFechaInicial());
                                    cFecha.set(Calendar.DATE, cFecha.getActualMaximum(Calendar.DATE));
                                    inicializaPeriodo2Meses(periodosNomina, periodosNomina.getFechaInicial(), cFecha.getTime());
                                    valoresConceptosGlobales.put(parametroFechaFinal, cFecha);
                                    valoresConceptosGlobales.put(parametroFechaInicial, dateToCalendar(periodosNomina.getFechaInicial()));
                                } else {
                                    cFecha.setTime(movimientosNomina.getPeriodosNomina().getFechaFinal());
                                    cFecha.set(Calendar.DATE, 1);
                                    inicializaPeriodo2Meses(periodosNomina, cFecha.getTime(), periodosNomina.getFechaFinal());
                                    valoresConceptosGlobales.put(parametroFechaInicial, cFecha);
                                    valoresConceptosGlobales.put(parametroFechaFinal, dateToCalendar(periodosNomina.getFechaFinal()));
                                }
                                indiceCalculoUnidad = obtenerPosicionCalculoUnidades(listCalculoUnidadesTmp, movimientosNomina);
                                valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
                                cargaValoresDiasPago(plazasPorEmpleadosMov, (listPlazasPorEmpleadosMovOficial.size() <= 1), null, listCalculoUnidadesTmp.get(indiceCalculoUnidad), false, true);//JSA30
                                cargaValoresDiasCotizados(plazasPorEmpleadosMov.getFechaIMSS(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), (listPlazasPorEmpleadosMovOficial.size() <= 1), null, null, false, false);//JSA30
                                cargaDatosVariableConfiguracionIMSS(((Calendar) valoresConceptosGlobales.get(parametroFechaFinal)).getTime());
                                if (guardarCambiosCalculosUnidades) {
//                                    System.out.println("*********getDiasTrabajados 10 " + listCalculoUnidadesTmp.get(indiceCalculoUnidad).getDiasTrabajados());
                                    if (listCalculoUnidadesTmp.get(indiceCalculoUnidad).getId() == null) {
                                        getSession().saveOrUpdate(listCalculoUnidadesTmp.get(indiceCalculoUnidad));
                                    } else {
                                        getSession().merge(listCalculoUnidadesTmp.get(indiceCalculoUnidad));
                                    }
                                }
//                                
                            } else {
                                manejaPagoDiasNaturales = tempPagoDiasNat;
                            }

//</editor-fold>
                            operacionConceptos(movimientosNomina, claveTipoCorrida, plazasPorEmpleadosMov);
                            if (mensajeResultado.getNoError() != 0) {
                                break;
                            }
                            if (b) {
//                                System.out.println("*********getDiasTrabajados 8 " + listCalculoUnidadesTmp.get(i).getDiasTrabajados());
                                if (listCalculoUnidadesTmp.get(i).getId() == null) {
                                    getSession().saveOrUpdate(listCalculoUnidadesTmp.get(i));
                                } else {
                                    getSession().merge(listCalculoUnidadesTmp.get(i));
                                }
                                break;
                            }
                            if (aumentarIndicePlazasPorEmpleadoMov) {
                                indicePlazasPorEmpleadoMov++;
                            } else {
                                aumentarIndicePlazasPorEmpleadoMov = true;
                            }
                        }
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }
                        if (listMovNomConcepPromocional.size() > 1) {
                            cargaValoresDiasPago(plazasPorEmpleadosMov, true, null, null, false, true);//JSA30
                            cargaValoresDiasCotizados(plazasPorEmpleadosMov.getFechaIMSS(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), true, null, null, false, false);//JSA30
                            if (isMov2Meses) {
                                cargaDatosVariableConfiguracionIMSS(plazasPorEmpleadosMov.getFechaIMSS());
                            }
                        }
                        i = 0;
                    }
                }
            } catch (HibernateException ex) {
                mensajeResultado.setError("ERROR ejecutaConceptosPorMovimientoNomina " + ex.getMessage());
                mensajeResultado.setNoError(-101);
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaConceptosPorMovimientoNomina()1_Error: ").append(ex));
            }
        }
    }

    private List<Object> obtenerModificacionesDePlazasPorEmpleadoMov(boolean configuracionSueldoDiarioVigente, boolean configuracionPercepcion_Plaza, boolean configuracionPercepcion_Plaza_Vigente, MovNomConcep movimientosNomina, PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        List<Object> listObject = new ArrayList<Object>();
        List<MovNomConcep> listMovNomConcepPromocional = new ArrayList<MovNomConcep>();
        List<PlazasPorEmpleadosMov> listPlazasPorEmpleadosMovOficial = new ArrayList<PlazasPorEmpleadosMov>();
        int i;
        if (configuracionSueldoDiarioVigente) {
            //<editor-fold defaultstate="collapsed" desc="Programacion para cuando se aplica modificaciones salariales, aqui se obtienen los movimientos del empleado dentro del periodo">
            listMovNomConcepPromocional.add(movimientosNomina);
            Calendar fechaIni = Calendar.getInstance(), fechaFin = Calendar.getInstance();
            fechaIni.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
            fechaFin.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
            if (periodosNomina != null) {
                fechaIni.setTime(periodosNomina.getFechaInicial());
                fechaFin.setTime(periodosNomina.getFechaFinal());
            }
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadosMovTmp = new ArrayList<PlazasPorEmpleadosMov>();
            listPlazasPorEmpleadosMovTmp.addAll(obtenerPlazasPorEmpleadosMovDentroPeriodo(tipoCorrida.getClave(), periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal(), new Object[]{plazasPorEmpleadosMov}));
            List<MovNomConcep> listMovNomTmp = new ArrayList<MovNomConcep>();
            for (i = 0; i < filtroMovimientosNominas.size(); i++) {
                if (movimientosNomina.getConcepNomDefi().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(i).getConcepNomDefi().getClave())) {
                    boolean continuar = false;
                    if (movimientosNomina.getId() != filtroMovimientosNominas.get(i).getId()) {
                        continuar = true;
                        if (movimientosNomina.getPlazasPorEmpleado().getId().equals(filtroMovimientosNominas.get(i).getPlazasPorEmpleado().getId())
                                & movimientosNomina.getNumero().equals(filtroMovimientosNominas.get(i).getNumero())) {
                            if (!movimientosNomina.getMes().equals(filtroMovimientosNominas.get(i).getMes())) {
                                continuar = false;
                            }
                        }
                    }
                    if (continuar) {
                        listMovNomTmp.add(filtroMovimientosNominas.get(i));
                    }
                }
            }
            filtroMovimientosNominas.removeAll(listMovNomTmp);
            listMovNomConcepPromocional.addAll(listMovNomTmp);
            int numero = 1;
            listMovNomConcepPromocional.get(0).setNumero(1);
            if (listMovNomConcepPromocional.size() < listPlazasPorEmpleadosMovTmp.size() + 1) {
                for (i = 0; i < listPlazasPorEmpleadosMovTmp.size(); i++) {
                    numero++;
                    listMovNomConcepPromocional.add(duplicarMovNomConcep(listMovNomConcepPromocional.get(0), numero, listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado()));
                    if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                        Integer mesUno;
                        Calendar fechaPromocion = Calendar.getInstance(), fechaInicio = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
                        fechaInicio.setTime(filtroMovimientosNominas.get(0).getPeriodosNomina().getFechaInicial());
                        fechaFinal.setTime(filtroMovimientosNominas.get(0).getPeriodosNomina().getFechaFinal());
                        fechaPromocion.setTime(listPlazasPorEmpleadosMovTmp.get(i).getFechaInicial());
                        mesUno = fechaInicio.get(Calendar.MONTH) + 1;
                        listMovNomConcepPromocional.get(listMovNomConcepPromocional.size() - 1).setEjercicio(periodosNomina.getAño());
                        listMovNomConcepPromocional.get(listMovNomConcepPromocional.size() - 1).setMes(fechaPromocion.get(Calendar.MONTH) + 1);
                        if (mesUno.equals(fechaPromocion.get(Calendar.MONTH) + 1)) {
                            listMovNomConcepPromocional.get(listMovNomConcepPromocional.size() - 1).setNumMovParticion(1);
                        } else {
                            listMovNomConcepPromocional.get(listMovNomConcepPromocional.size() - 1).setNumMovParticion(2);
                        }
                    }
                }
            }
            listPlazasPorEmpleadosMovOficial.addAll(listPlazasPorEmpleadosMovTmp);
            listPlazasPorEmpleadosMovOficial.add(plazasPorEmpleadosMov);
            if (listPlazasPorEmpleadosMovOficial.get(0).getFechaInicial().compareTo(fechaIni.getTime()) > 0) {
                boolean agregarMovNomConcepPromocional = true;
                if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                    for (i = 0; i < listPlazasPorEmpleadosMovOficial.size(); i++) {
                        if (i + 1 < listPlazasPorEmpleadosMovOficial.size()) {
                            Calendar fechaUno = Calendar.getInstance(), fechaDos = Calendar.getInstance();
                            fechaUno.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                            fechaDos.setTime(listPlazasPorEmpleadosMovOficial.get(i + 1).getFechaInicial());
                            if (fechaUno.get(Calendar.MONTH) != fechaDos.get(Calendar.MONTH)) {
//                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//                                System.out.println("fechaUno " + formato.format(fechaUno.getTime()) + " fechaDos " + formato.format(fechaDos.getTime()));
                                fechaUno.setTime(fechaDos.getTime());
                                fechaUno.set(Calendar.DATE, fechaUno.getActualMinimum(Calendar.DAY_OF_MONTH));//Obtener dia 1 del mes.
                                if (fechaDos.getTime().compareTo(fechaUno.getTime()) > 0) {
                                    if (listMovNomConcepPromocional.size() > listPlazasPorEmpleadosMovOficial.size()) {

                                    }
                                }
                            }

                        }
                    }
                } else if (listPlazasPorEmpleadosMovOficial.size() + 1 == listMovNomConcepPromocional.size()) {
                    agregarMovNomConcepPromocional = false;
                }

                listPlazasPorEmpleadosMovOficial.addAll(0, obtenerAnteriorPlazasPorEmpleadosMov(tipoCorrida.getClave(), periodosNomina.getFechaInicial(), listPlazasPorEmpleadosMovOficial.get(0)));
                if (agregarMovNomConcepPromocional) {
                    listMovNomConcepPromocional.add(duplicarMovNomConcep(listMovNomConcepPromocional.get(0), listMovNomConcepPromocional.get(listMovNomConcepPromocional.size() - 1).getNumero() + 1, listPlazasPorEmpleadosMovOficial.get(0).getPlazasPorEmpleado()));
                }
            }
//</editor-fold>
        } else if (configuracionPercepcion_Plaza) {
            //<editor-fold defaultstate="collapsed" desc="Programacion para cuando se aplica modificaciones salariales, aqui se obtienen los movimientos del empleado dentro del periodo">
            Calendar fechaIni = Calendar.getInstance(), fechaFin = Calendar.getInstance();
            fechaIni.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
            fechaFin.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
            if (periodosNomina != null) {
                fechaIni.setTime(periodosNomina.getFechaInicial());
                fechaFin.setTime(periodosNomina.getFechaFinal());
            }
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadosMovTmp = new ArrayList<PlazasPorEmpleadosMov>();
            listPlazasPorEmpleadosMovTmp.addAll(obtenerPlazasPorEmpleadosMovRestantes(plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getId(), periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal(), plazasPorEmpleadosMov));
            List<MovNomConcep> listMovNomTmp = new ArrayList<MovNomConcep>();
            for (i = 0; i < filtroMovimientosNominas.size(); i++) {
                if (movimientosNomina.getConcepNomDefi().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(i).getConcepNomDefi().getClave())) {
                    if (movimientosNomina.getId() != filtroMovimientosNominas.get(i).getId()) {
                        listMovNomTmp.add(filtroMovimientosNominas.get(i));
                    }
                }
            }
            for (i = 0; i < listPlazasPorEmpleadosMovTmp.size(); i++) {
                listMovNomTmp.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(movimientosNomina.getTipoCorrida().getClave(), movimientosNomina.getTipoNomina().getClave(), movimientosNomina.getPeriodosNomina().getId(), listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado(), movimientosNomina.getCentroDeCosto() == null ? null : movimientosNomina.getCentroDeCosto().getClave(), Tipo.AUTOMATICO, movimientosNomina.getRazonesSociales().getClave(), null, -1, movimientosNomina.getConcepNomDefi().getClave()));
                listMovNomTmp.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(movimientosNomina.getTipoCorrida().getClave(), movimientosNomina.getTipoNomina().getClave(), movimientosNomina.getPeriodosNomina().getId(), listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado(), movimientosNomina.getCentroDeCosto() == null ? null : movimientosNomina.getCentroDeCosto().getClave(), Tipo.PERIODO, movimientosNomina.getRazonesSociales().getClave(), null, -1, movimientosNomina.getConcepNomDefi().getClave()));
                listMovNomTmp.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(movimientosNomina.getTipoCorrida().getClave(), movimientosNomina.getTipoNomina().getClave(), movimientosNomina.getPeriodosNomina().getId(), listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado(), movimientosNomina.getCentroDeCosto() == null ? null : movimientosNomina.getCentroDeCosto().getClave(), Tipo.REPETITIVO, movimientosNomina.getRazonesSociales().getClave(), null, -1, movimientosNomina.getConcepNomDefi().getClave()));
            }
            filtroMovimientosNominas.removeAll(listMovNomTmp);
            listMovNomConcepPromocional.addAll(listMovNomTmp);
            int numero = 1;
            movimientosNomina.setNumero(1);
            if (listMovNomConcepPromocional.size() < listPlazasPorEmpleadosMovTmp.size()) {
                for (i = 0; i < listPlazasPorEmpleadosMovTmp.size(); i++) {
                    numero++;
                    listMovNomConcepPromocional.add(duplicarMovNomConcep(movimientosNomina, numero, listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado()));
                }
            }
            listMovNomConcepPromocional.add(movimientosNomina);
            listPlazasPorEmpleadosMovOficial.addAll(listPlazasPorEmpleadosMovTmp);
            listPlazasPorEmpleadosMovOficial.add(plazasPorEmpleadosMov);
//</editor-fold>
        } else if (configuracionPercepcion_Plaza_Vigente) {
            //<editor-fold defaultstate="collapsed" desc="Programacion para cuando se aplica modificaciones salariales, aqui se obtienen los movimientos del empleado dentro del periodo">
            listMovNomConcepPromocional.add(movimientosNomina);
            Calendar fechaIni = Calendar.getInstance(), fechaFin = Calendar.getInstance();
            fechaIni.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
            fechaFin.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
            if (periodosNomina != null) {
                fechaIni.setTime(periodosNomina.getFechaInicial());
                fechaFin.setTime(periodosNomina.getFechaFinal());
            }
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadosMovTmp = new ArrayList<PlazasPorEmpleadosMov>();
            listPlazasPorEmpleadosMovTmp.addAll(obtenerPlazasPorEmpleadosMovRestantes(plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getId(), periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal(), plazasPorEmpleadosMov));
            List<MovNomConcep> listMovNomTmp = new ArrayList<MovNomConcep>();
            for (i = 0; i < listPlazasPorEmpleadosMovTmp.size(); i++) {
                listMovNomTmp.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(movimientosNomina.getTipoCorrida().getClave(), movimientosNomina.getTipoNomina().getClave(), movimientosNomina.getPeriodosNomina().getId(), listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado(), movimientosNomina.getCentroDeCosto() == null ? null : movimientosNomina.getCentroDeCosto().getClave(), Tipo.AUTOMATICO, movimientosNomina.getRazonesSociales().getClave(), null, -1, movimientosNomina.getConcepNomDefi().getClave()));
                listMovNomTmp.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(movimientosNomina.getTipoCorrida().getClave(), movimientosNomina.getTipoNomina().getClave(), movimientosNomina.getPeriodosNomina().getId(), listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado(), movimientosNomina.getCentroDeCosto() == null ? null : movimientosNomina.getCentroDeCosto().getClave(), Tipo.PERIODO, movimientosNomina.getRazonesSociales().getClave(), null, -1, movimientosNomina.getConcepNomDefi().getClave()));
                listMovNomTmp.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(movimientosNomina.getTipoCorrida().getClave(), movimientosNomina.getTipoNomina().getClave(), movimientosNomina.getPeriodosNomina().getId(), listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado(), movimientosNomina.getCentroDeCosto() == null ? null : movimientosNomina.getCentroDeCosto().getClave(), Tipo.REPETITIVO, movimientosNomina.getRazonesSociales().getClave(), null, -1, movimientosNomina.getConcepNomDefi().getClave()));
            }
            listPlazasPorEmpleadosMovTmp.add(plazasPorEmpleadosMov);
            listPlazasPorEmpleadosMovTmp.addAll(obtenerPlazasPorEmpleadosMovDentroPeriodo(tipoCorrida.getClave(), periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal(), listPlazasPorEmpleadosMovTmp.toArray()));
            for (i = 0; i < filtroMovimientosNominas.size(); i++) {
                if (movimientosNomina.getConcepNomDefi().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(i).getConcepNomDefi().getClave())) {
                    if (movimientosNomina.getId() != filtroMovimientosNominas.get(i).getId()) {
                        listMovNomTmp.add(filtroMovimientosNominas.get(i));
                    }
                }
            }
            filtroMovimientosNominas.removeAll(listMovNomTmp);
            List<Object> clavesMovEliminados = new ArrayList<Object>();
            int limite = listMovNomTmp.size();
            for (int j = 0; j < limite; j++) {
                if (listMovNomTmp.get(j).getId() != null) {
                    clavesMovEliminados.add(listMovNomTmp.get(j).getId());
                }
            }
            if (!clavesMovEliminados.isEmpty()) {
                eliminarMovimientosNominaBasura(clavesMovEliminados.toArray());
//                System.out.println("flush 6");
                getSession().flush();
            }
            listMovNomTmp.removeAll(listMovNomTmp);
            if (listPlazasPorEmpleadosMovTmp.size() > 1) {
                Collections.sort(listPlazasPorEmpleadosMovTmp, new rowComparator());
            }
            listMovNomConcepPromocional.get(0).setNumero(1);
            int numero = 1;
            if (listMovNomConcepPromocional.size() < listPlazasPorEmpleadosMovTmp.size()) {
                for (i = 1; i < listPlazasPorEmpleadosMovTmp.size(); i++) {
                    numero++;
                    listMovNomConcepPromocional.add(duplicarMovNomConcep(listMovNomConcepPromocional.get(0), numero, listPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado()));
                }
                if (listMovNomConcepPromocional.size() > listPlazasPorEmpleadosMovTmp.size()) {
                    for (MovNomConcep movNomConcep : listMovNomConcepPromocional) {
                        if (movNomConcep.getId() == null) {
                            if (movNomConcep.getPlazasPorEmpleado().getId() == plazasPorEmpleadosMov.getPlazasPorEmpleado().getId()) {
                                listMovNomConcepPromocional.remove(movNomConcep);
                                break;
                            }
                        }
                    }
                }
            }
            listPlazasPorEmpleadosMovOficial.addAll(listPlazasPorEmpleadosMovTmp);
            String clavePlazaPorEmpleado = "";
            for (i = 0; i < listPlazasPorEmpleadosMovOficial.size(); i++) {
                if (!listPlazasPorEmpleadosMovOficial.get(i).getPlazasPorEmpleado().getClave().equalsIgnoreCase(clavePlazaPorEmpleado)) {
                    clavePlazaPorEmpleado = listPlazasPorEmpleadosMovOficial.get(i).getPlazasPorEmpleado().getClave();
                    if (listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial().compareTo(fechaIni.getTime()) > 0) {
                        if (i == 0) {
                            listPlazasPorEmpleadosMovOficial.addAll(0, obtenerAnteriorPlazasPorEmpleadosMov(tipoCorrida.getClave(), periodosNomina.getFechaInicial(), listPlazasPorEmpleadosMovOficial.get(i)));
                        } else {
                            listPlazasPorEmpleadosMovOficial.addAll(i - 1, obtenerAnteriorPlazasPorEmpleadosMov(tipoCorrida.getClave(), periodosNomina.getFechaInicial(), listPlazasPorEmpleadosMovOficial.get(i)));
                        }
                        if (listPlazasPorEmpleadosMovOficial.size() != listMovNomConcepPromocional.size()) {
                            listMovNomConcepPromocional.add(duplicarMovNomConcep(listMovNomConcepPromocional.get(0), listMovNomConcepPromocional.get(listMovNomConcepPromocional.size() - 1).getNumero() + 1, listPlazasPorEmpleadosMovOficial.get(i).getPlazasPorEmpleado()));
                        }
                    }
                }
            }
//            listPlazasPorEmpleadosMovOficial.add(plazasPorEmpleadosMov);
//            plazasPorEmpleadosMov.addAll(0, listPlazasPorEmpleadosMovTmp);
//</editor-fold>
        } else {
            listPlazasPorEmpleadosMovOficial.add(plazasPorEmpleadosMov);
            listMovNomConcepPromocional.removeAll(listMovNomConcepPromocional);
            listMovNomConcepPromocional.add(movimientosNomina);
        }
        boolean continuar = false;
        if (listMovNomConcepPromocional.size() > listPlazasPorEmpleadosMovOficial.size()) {
            continuar = true;
            if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                for (i = 0; i < listPlazasPorEmpleadosMovOficial.size(); i++) {
                    {
                        if (i + 1 < listPlazasPorEmpleadosMovOficial.size()) {
                            Calendar fechaUno = Calendar.getInstance(), fechaDos = Calendar.getInstance();
                            fechaUno.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                            fechaDos.setTime(listPlazasPorEmpleadosMovOficial.get(i + 1).getFechaInicial());
                            if (fechaUno.get(Calendar.MONTH) != fechaDos.get(Calendar.MONTH)) {
//                                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//                                System.out.println("fechaUno " + formato.format(fechaUno.getTime()) + " fechaDos " + formato.format(fechaDos.getTime()));
                                fechaUno.setTime(periodosNomina.getFechaInicial());
                                if (fechaUno.get(Calendar.MONTH) != fechaDos.get(Calendar.MONTH)) {
                                    fechaUno.setTime(fechaDos.getTime());
                                    fechaUno.set(Calendar.DATE, fechaUno.getActualMinimum(Calendar.DAY_OF_MONTH));//Obtener dia 1 del mes.
                                    if (fechaDos.getTime().compareTo(fechaUno.getTime()) > 0) {
                                        if (listMovNomConcepPromocional.size() == listPlazasPorEmpleadosMovOficial.size() + 1) {
                                            continuar = false;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
        }
        if (continuar) {
            List<Object> clavesMovEliminados = new ArrayList<Object>();
            int contador = 0;
            for (int j = 0; j < listPlazasPorEmpleadosMovOficial.size(); j++) {
                if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                    for (int k = 0; k < listMovNomConcepPromocional.size(); k++) {
                        if (listMovNomConcepPromocional.get(k).getPlazasPorEmpleado().getId().equals(listPlazasPorEmpleadosMovOficial.get(j).getPlazasPorEmpleado().getId())) {
                            contador++;
                            if (contador > 2) {
                                clavesMovEliminados.add(listMovNomConcepPromocional.get(k).getId());
                            }
                        }
                    }
                } else {
                    if (listMovNomConcepPromocional.get(listPlazasPorEmpleadosMovOficial.size()).getId() != null) {
                        clavesMovEliminados.add(listMovNomConcepPromocional.get(listPlazasPorEmpleadosMovOficial.size()).getId());
                    }
                    listMovNomConcepPromocional.remove(listPlazasPorEmpleadosMovOficial.size());
                }
            }
            if (!clavesMovEliminados.isEmpty()) {
                eliminarMovimientosNominaBasura(clavesMovEliminados.toArray());
//                System.out.println("flush 7");
                getSession().flush();
                getSession().clear();
            }
        }
        if (listPlazasPorEmpleadosMovOficial.size() > 1) {
            if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                boolean mesUnoEncontrado = false, repetir = true;
                int indiceMovimientoNomina = 0;
                Calendar fechaPromocion = Calendar.getInstance(), fechaUno = Calendar.getInstance(), fechaDos = Calendar.getInstance();
                for (i = 0; i < listPlazasPorEmpleadosMovOficial.size(); i++) {
                    mesUnoEncontrado = false;
                    if (i + 1 < listPlazasPorEmpleadosMovOficial.size()) {
                        fechaUno.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                        if (fechaUno.getTime().compareTo(periodosNomina.getFechaInicial()) < 0) {
                            fechaPromocion.setTime(periodosNomina.getFechaInicial());
                        } else {
                            fechaPromocion.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                        }
                        fechaUno.setTime(periodosNomina.getFechaInicial());
                        if (fechaPromocion.get(Calendar.MONTH) == fechaUno.get(Calendar.MONTH)) {
                            mesUnoEncontrado = true;
                        }
                        listMovNomConcepPromocional.get(indiceMovimientoNomina).setEjercicio(periodosNomina.getAño());
                        listMovNomConcepPromocional.get(indiceMovimientoNomina).setMes(fechaPromocion.get(Calendar.MONTH) + 1);
                        if (mesUnoEncontrado) {
                            listMovNomConcepPromocional.get(indiceMovimientoNomina).setNumMovParticion(1);
                        } else {//Existe el mesDos generare el mesUno
                            listMovNomConcepPromocional.get(indiceMovimientoNomina).setNumMovParticion(2);
                        }

                        fechaUno.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                        fechaDos.setTime(listPlazasPorEmpleadosMovOficial.get(i + 1).getFechaInicial());
                        fechaPromocion.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                        boolean incremento = false;
                        if (fechaUno.get(Calendar.MONTH) != fechaDos.get(Calendar.MONTH)) {
//                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//                            System.out.println("fechaUno " + formato.format(fechaUno.getTime()) + " fechaDos " + formato.format(fechaDos.getTime()));
                            fechaUno.setTime(periodosNomina.getFechaInicial());
                            if (fechaUno.get(Calendar.MONTH) != fechaDos.get(Calendar.MONTH)) {
                                fechaUno.setTime(fechaDos.getTime());
                                fechaUno.set(Calendar.DATE, fechaUno.getActualMinimum(Calendar.DAY_OF_MONTH));//Obtener dia 1 del mes.
                                if (fechaDos.getTime().compareTo(fechaUno.getTime()) > 0) {
                                    fechaPromocion.setTime(fechaUno.getTime());
                                    indiceMovimientoNomina++;
                                    incremento = true;
                                    listMovNomConcepPromocional.get(indiceMovimientoNomina).setEjercicio(periodosNomina.getAño());
                                    listMovNomConcepPromocional.get(indiceMovimientoNomina).setMes(fechaPromocion.get(Calendar.MONTH) + 1);
                                    listMovNomConcepPromocional.get(indiceMovimientoNomina).setNumMovParticion(2);
                                }
                            }
                        }
                        if (!incremento) {
                            indiceMovimientoNomina++;
                            incremento = false;
                        }
                    } else {
                        fechaUno.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                        if (fechaUno.getTime().compareTo(periodosNomina.getFechaInicial()) < 0) {
                            fechaPromocion.setTime(periodosNomina.getFechaInicial());
                        } else {
                            fechaPromocion.setTime(listPlazasPorEmpleadosMovOficial.get(i).getFechaInicial());
                        }
                        fechaUno.setTime(periodosNomina.getFechaInicial());
                        if (fechaPromocion.get(Calendar.MONTH) == fechaUno.get(Calendar.MONTH)) {
                            mesUnoEncontrado = true;
                        }
                        listMovNomConcepPromocional.get(indiceMovimientoNomina).setEjercicio(periodosNomina.getAño());
                        listMovNomConcepPromocional.get(indiceMovimientoNomina).setMes(fechaPromocion.get(Calendar.MONTH) + 1);
                        if (mesUnoEncontrado) {
                            listMovNomConcepPromocional.get(indiceMovimientoNomina).setNumMovParticion(1);
                        } else {//Existe el mesDos generare el mesUno
                            listMovNomConcepPromocional.get(indiceMovimientoNomina).setNumMovParticion(2);
                        }
                    }
                }
            }
        }

        listObject.add(listPlazasPorEmpleadosMovOficial);
        listObject.add(listMovNomConcepPromocional);
        return listObject;
    }

    private int eliminarMovimientosNominaBasura(Object[] valores) {
        int exito = 1;
        consulta = new StringBuilder();
        consulta.append("delete ");
        String[] campo = new String[]{"valores"};
        try {
            consulta.delete(7, consulta.length());
            //Elimina Bases Afectadas de Movimientos por Conceptos
            consulta.append("MovNomBaseAfecta o where o.movNomConcep.id in(:valores)");
            q = getSession().createQuery(consulta.toString());
            q.setParameterList("valores", valores);
            exito = q.executeUpdate();
////////            System.out.println("exito " + exito);
            consulta.delete(7, consulta.length());

            //Elimina Movimientos Por parametros de Movimientos por Conceptos
            consulta.append("MovNomConceParam o where o.movNomConcep.id in(:valores)");
            q = getSession().createQuery(consulta.toString());
            q.setParameterList("valores", valores);
            exito = q.executeUpdate();
////////            System.out.println("exito " + exito);
            consulta.delete(7, consulta.length());

            //Elimina Movimientos ISRRetenidos
            consulta.append(CalculoISR.class.getSimpleName()).append(" o where o.movNomConcep.id in(:valores)");//JSA23
            q = getSession().createQuery(consulta.toString());
            q.setParameterList("valores", valores);
            exito = q.executeUpdate();
////////            System.out.println("exito " + exito);
            consulta.delete(7, consulta.length());

            //Elimina Movimientos CalculoIMSS
            consulta.append(CalculoIMSS.class.getSimpleName()).append(" o where o.movNomConcep.id in(:valores)");
            q = getSession().createQuery(consulta.toString());
            q.setParameterList("valores", valores);
            exito = q.executeUpdate();
////////            System.out.println("exito " + exito);
            consulta.delete(7, consulta.length());

            //Elimina Movimientos CalculoIMSSPatron
            consulta.append(CalculoIMSSPatron.class.getSimpleName()).append(" o where o.movNomConcep.id in(:valores)");
            q = getSession().createQuery(consulta.toString());
            q.setParameterList("valores", valores);
            exito = q.executeUpdate();
////////            System.out.println("exito " + exito);
            consulta.delete(7, consulta.length());

            //Elimina Movimientos por Conceptos
            consulta.append(MovNomConcep.class.getSimpleName()).append(" where id in(:valores)");
            q = getSession().createQuery(consulta.toString());
            q.setParameterList("valores", valores);
            exito = q.executeUpdate();
            mensajeResultado.setError("");
            mensajeResultado.setNoError(0);
////////            System.out.println("exito " + exito);
        } catch (LockAcquisitionException ex) {
            contLockAcquisition++;
            mensajeResultado.setError("eliminarMovimientosNominaBasura " + ex.getMessage());
            mensajeResultado.setNoError(-200);
            if (contLockAcquisition <= 3) {
                try {
                    Thread.sleep(3000);
                    exito = eliminarMovimientosNominaBasura(valores);
                    ///////System.out.println("contLockAcquisition " + contLockAcquisition + " exito " + exito);
                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }

                if (mensajeResultado.getNoError() == 0) {
                    contLockAcquisition = 0;
                } else {
                    contLockAcquisition++;
                }
            } else {
                ////System.out.println(" contLockAcquisition " + contLockAcquisition);
                exito = -1;
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminarMovimientosNominaBasura()1_Error: ").append(ex));
                contLockAcquisition = 0;
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError("eliminarMovimientosNominaBasura " + ex.getMessage());
            mensajeResultado.setNoError(-200);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminarMovimientosNominaBasura()1_Error: ").append(ex));
            exito = -1;
        }
        return exito;
    }

    class rowComparator implements Comparator<PlazasPorEmpleadosMov> {

        @Override
        public int compare(PlazasPorEmpleadosMov o1, PlazasPorEmpleadosMov o2) {

            int resultado = Double.compare(o1.getPlazasPorEmpleado().getId(), o2.getPlazasPorEmpleado().getId());
            if (resultado != 0) {
                return resultado;
            }
            resultado = o1.getFechaInicial().compareTo(o2.getFechaInicial()) > 0 ? 1 : -1;
            if (resultado != 0) {
                return resultado;
            }
            return resultado;
        }
    }

    private void operacionConceptos(MovNomConcep movimientosNomina, String claveTipoCorrida, PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        boolean isISR = false, isIMSS = false, isISRSubsidio = false, isImssPatronal = false, omitirMovimiento = false, isISRACargo = false, isSubsEmpleoCalculado = false;
        isISR = isConceptoEspecial(1, movimientosNomina.getConcepNomDefi().getFormulaConcepto());
        isIMSS = isConceptoEspecial(2, movimientosNomina.getConcepNomDefi().getFormulaConcepto());
        isISRSubsidio = isConceptoEspecial(3, movimientosNomina.getConcepNomDefi().getFormulaConcepto());
        isImssPatronal = isConceptoEspecial(4, movimientosNomina.getConcepNomDefi().getFormulaConcepto());
        isISRACargo = isConceptoEspecial(5, movimientosNomina.getConcepNomDefi().getFormulaConcepto());
        isSubsEmpleoCalculado = isConceptoEspecial(6, movimientosNomina.getConcepNomDefi().getFormulaConcepto());
        if (isImssPatronal) {
            isIMSS = false;
        }
        if (isISR) {
            //<editor-fold defaultstate="collapsed" desc="Calculo ISR">
            isISR = false;
            Double valorISR;
            MovNomConcep movNomConcepSubsidio = null;
            if (isMov2Meses & listMovNomConcepSubsidio.size() > 0) {
                int pos = movimientosNomina.getNumMovParticion() == 1 ? 0 : 1;
                movNomConcepSubsidio = listMovNomConcepSubsidio.get(pos);
            } else if (listMovNomConcepSubsidio.size() > 0) {
                movNomConcepSubsidio = listMovNomConcepSubsidio.get(0);
            }
            if (movNomConcepSubsidio == null) {
                camposParametro = new ArrayList<String>();
                valoresParametro = new ArrayList<Object>();
                strQuery.delete(0, strQuery.length()).append("SELECT cdn FROM ConcepNomDefi cdn INNER JOIN cdn.conceptoPorTipoCorrida ctc INNER JOIN ctc.tipoCorrida tc  ");
                strWhere.delete(0, strWhere.length()).append(" WHERE tc.clave = :claveTipoCorrida AND cdn.activado = true and cdn.formulaConcepto LIKE CONCAT('%', :formulaConcepto, '%') ");
                strWhere.append(" AND cdn.fecha =(SELECT MAX(fecha) FROM ConcepNomDefi c WHERE c.formulaConcepto LIKE CONCAT('%', :formulaConcepto, '%')) ");
                strQuery.append(strWhere);
                camposParametro.add("claveTipoCorrida");
                valoresParametro.add(claveTipoCorrida);
                camposParametro.add("formulaConcepto");
                valoresParametro.add("ISRSubsidio");
                ConcepNomDefi c = (ConcepNomDefi) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
                if (c == null) {
                    mensajeResultado.setError("Concepto Subsidio no encontrado en la corrida " + claveTipoCorrida);
                    mensajeResultado.setNoError(36);
                    System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("operacionConceptos()1_Error: ").append("Concepto Subsidio no encontradoen la corrida ").append(claveTipoCorrida));
                    return;
                } else {
                    movNomConcepSubsidio = creaMovNomConceptoSubsidio(movimientosNomina, c);
                }
            }
            if (claveTipoCorrida.equalsIgnoreCase("FIN") | claveTipoCorrida.equalsIgnoreCase("LIQ")) {
                valorISR = calculoISRFiniquitos(movimientosNomina.getTipoCorrida());
                if (valorISR > 0) {
                    movimientosNomina.setResultado(aplicarMascara(movimientosNomina.getConcepNomDefi(), valorISR, false));
                } else {
                    movimientosNomina.setResultado(0.0);
                }
                movimientosNomina.setCalculado(valorISR > 0 ? valorISR : 0.0);
                IsrRetenidos(movimientosNomina);
            } else if (modoAjustarIngresosMes == ProporcionaTablaAnual) {
                valorISR = calculoISPTAnual(movimientosNomina);
            } else if (calculoSeparadoISR) {
                valorISR = calculaISPTSeparado(movimientosNomina);
            } else {
                valorISR = calculaISPT(movimientosNomina);
            }
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            if (valorISR > 0) {
                movimientosNomina.setResultado(aplicarMascara(movimientosNomina.getConcepNomDefi(), valorISR, false));
                if (movNomConcepSubsidio != null) {//JSA12
                    movNomConcepSubsidio.setResultado(0.0);
                }
            } else {
                movimientosNomina.setResultado(0.0);
                if (movNomConcepSubsidio != null) {//JSA12
                    movNomConcepSubsidio = ejecutarParametroCondicionYFormula(movNomConcepSubsidio, claveTipoCorrida);
                    agregaParametrosConceptosNomina(movNomConcepSubsidio.getMovNomConceParam());
                }
            }
//            System.out.println("CONCEPTO ISR " + movimientosNomina.getResultado() + " ID " + movimientosNomina.getId());
            movimientosNomina.setCalculado(valorISR > 0 ? valorISR : 0.0);
            agregaParametrosConceptosNomina(movimientosNomina.getMovNomConceParam());
            if (movNomConcepSubsidio != null) {
                if (movNomConcepSubsidio.getResultado() == null ? true : movNomConcepSubsidio.getResultado() == 0 ? true : false) {//JSA12
                    if (movNomConcepSubsidio.getId() != null) {
                        eliminarMovimientosNominaBasura(new Object[]{movNomConcepSubsidio.getId()});
//                        System.out.println("flush 8");
                        getSession().flush();
                        iSRRetenidoSubsidio = null;
                    }
                } else {
                    //Se guarda el concepto Subsidio..El del ISR se guarda abajo.
                    if (movNomConcepSubsidio.getId() == null) {
                        cantidadSaveUpdate++;
                        getSession().saveOrUpdate(movNomConcepSubsidio);
                    } else {
                        getSession().merge(movNomConcepSubsidio);
                    }
                    iSRRetenidoSubsidio.setMovNomConcep(movNomConcepSubsidio);
                    getSession().saveOrUpdate(iSRRetenidoSubsidio);
                    getSession().flush();
                    getSession().clear();
                    cantidadSaveUpdate++;
                }
                movNomConcepSubsidio = null;
                iSRRetenidoSubsidio = null;
            }

            if (listMovNomConcepISRCARGO != null) {
                if (isMov2Meses & listMovNomConcepISRCARGO.size() > 0) {
                    int pos = movimientosNomina.getNumMovParticion() == 1 ? 0 : 1;
                    movNomConcepSubsidio = listMovNomConcepISRCARGO.get(pos);
                } else if (listMovNomConcepISRCARGO.size() > 0) {
                    movNomConcepSubsidio = listMovNomConcepISRCARGO.get(0);
                }
                if (movNomConcepSubsidio != null) {
                    movNomConcepSubsidio = ejecutarParametroCondicionYFormula(movNomConcepSubsidio, claveTipoCorrida);
                    saveOrUpdateOrDeleteMovimientosNomina(movNomConcepSubsidio, false, isISRSubsidio);
                    if (mensajeResultado.getNoError() == -101) {
                        mensajeResultado.setNoError(54);
                        return;
                    }
                    movNomConcepSubsidio = null;
                }
            }
            if (listMovNomConcepSUBSIDIOALEMPLEO != null) {
                if (isMov2Meses & listMovNomConcepSUBSIDIOALEMPLEO.size() > 0) {
                    int pos = movimientosNomina.getNumMovParticion() == 1 ? 0 : 1;
                    movNomConcepSubsidio = listMovNomConcepSUBSIDIOALEMPLEO.get(pos);
                } else if (listMovNomConcepSUBSIDIOALEMPLEO.size() > 0) {
                    movNomConcepSubsidio = listMovNomConcepSUBSIDIOALEMPLEO.get(0);
                }
                if (movNomConcepSubsidio != null) {
                    movNomConcepSubsidio = ejecutarParametroCondicionYFormula(movNomConcepSubsidio, claveTipoCorrida);
                    saveOrUpdateOrDeleteMovimientosNomina(movNomConcepSubsidio, false, isISRSubsidio);
                    if (mensajeResultado.getNoError() == -101) {
                        mensajeResultado.setNoError(54);
                        return;
                    }
                    movNomConcepSubsidio = null;
                }
            }
            if (movimientosNomina.getResultado() == null ? true : movimientosNomina.getResultado() == 0 ? true : false) {
                if (movimientosNomina.getId() != null) {
                    eliminarMovimientosNominaBasura(new Object[]{movimientosNomina.getId()});
//                    System.out.println("flush 9");
                    getSession().flush();
                }
            } else {
                if (movimientosNomina.getId() == null) {
                    cantidadSaveUpdate++;
                    getSession().saveOrUpdate(movimientosNomina);
                }
                iSRRetenido.setMovNomConcep(movimientosNomina);
                cantidadSaveUpdate++;
                getSession().saveOrUpdate(iSRRetenido);
                iSRRetenido = null;
                omitirMovimiento = true;
            }
            if (mensajeResultado.getNoError() == -101) {
                mensajeResultado.setNoError(54);
                return;
            }
            //</editor-fold>
        } else if (isIMSS) {
            //<editor-fold defaultstate="collapsed" desc="Calculo IMSS">
            //calcula seguro social calculaSalarioDiarioIntegerado(); 100 es el valor diario integrado
            isIMSS = false;
            agregaParametrosConceptosNomina(movimientosNomina.getMovNomConceParam());
            double resultadoImss = calculaImss((Double) valoresConceptosEmpleados.get("SueldoIntIMSS".toUpperCase()), (Double) valoresConceptosGlobales.get("SalarioMinDF".toUpperCase()), movimientosNomina, plazasPorEmpleadosMov);
            movimientosNomina.setResultado(aplicarMascara(movimientosNomina.getConcepNomDefi(), resultadoImss, false));
            movimientosNomina.setCalculado(resultadoImss);
            if (movimientosNomina.getId() == null) {
                cantidadSaveUpdate++;
                getSession().saveOrUpdate(movimientosNomina);
            }
            for (int i = 0; i < listCalculoIMSS.size(); i++) {
                listCalculoIMSS.get(i).setMovNomConcep(movimientosNomina);
                cantidadSaveUpdate++;
                getSession().saveOrUpdate(listCalculoIMSS.get(i));
            }

            omitirMovimiento = true;
            if (movimientosNomina.getResultado() == null ? true : movimientosNomina.getResultado() == 0 ? true : false) {
                if (movimientosNomina.getId() != null) {
                    ejecutaQueryExecuteUpdate("Delete CalculoIMSS mov Where mov.movNomConcep.id = :id", new String[]{"id"}, new Object[]{movimientosNomina.getId()});
//                    System.out.println("flush 10");
                    getSession().flush();
                }
            }
//</editor-fold>
        } else if (isImssPatronal) {
            //<editor-fold defaultstate="collapsed" desc="Calculo IMSSPatronal">
            agregaParametrosConceptosNomina(movimientosNomina.getMovNomConceParam());
            //calcula seguro social Patronal calculaSalarioDiarioIntegerado(); 100 es el valor diario integrado
            double resultadoImssPat = calculaImssPatronal((Double) valoresConceptosEmpleados.get("SueldoIntIMSS".toUpperCase()), (Double) valoresConceptosGlobales.get("SalarioMinDF".toUpperCase()), movimientosNomina, plazasPorEmpleadosMov);
            movimientosNomina.setResultado(resultadoImssPat);
            if (movimientosNomina.getId() == null) {
                cantidadSaveUpdate++;
                getSession().saveOrUpdate(movimientosNomina);
            }
            calculoIMSSPatron.setMovNomConcep(movimientosNomina);
            cantidadSaveUpdate++;
            getSession().saveOrUpdate(calculoIMSSPatron);
            omitirMovimiento = true;
            if (movimientosNomina.getResultado() == null ? true : movimientosNomina.getResultado() == 0 ? true : false) {
                if (movimientosNomina.getId() != null) {
                    ejecutaQueryExecuteUpdate("Delete CalculoIMSSPatron mov Where mov.movNomConcep.id = :id", new String[]{"id"}, new Object[]{movimientosNomina.getId()});
//                    System.out.println("flush 11");
                    getSession().flush();
                }
            }
//</editor-fold>
        } else if (isISRSubsidio) {
            listMovNomConcepSubsidio.add(movimientosNomina);

            omitirMovimiento = true;
        } else if (isISRACargo) {
            listMovNomConcepISRCARGO.add(movimientosNomina);
            omitirMovimiento = true;
        } else if (isSubsEmpleoCalculado) {
            listMovNomConcepSUBSIDIOALEMPLEO.add(movimientosNomina);
            omitirMovimiento = true;
        } else if (movimientosNomina.getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("AjustePorRedondeo".toUpperCase())) {//Ajuste por redondeo
            movNomConcepAjustePorRedondeo = movimientosNomina;
            omitirMovimiento = true;
        } else {
            movimientosNomina = ejecutarParametroCondicionYFormula(movimientosNomina, claveTipoCorrida);
        }
        if (mensajeResultado.getNoError() != 0) {
            return;
        }
        if (movimientosNomina.getResultado() == null) {
            movimientosNomina.setResultado(0.0);
            movimientosNomina.setCalculado(0.0);
        }
        saveOrUpdateOrDeleteMovimientosNomina(movimientosNomina, omitirMovimiento, isISRSubsidio);
        omitirMovimiento = false;
        if (mensajeResultado.getNoError() == -101) {
            mensajeResultado.setNoError(54);
            return;
        }
    }

    private boolean isConceptoEspecial(Integer tipoConceptoEspecial, String formula) {
        boolean isConceptoEspecial = false;
        if (formula == null) {
            formula = "";
        }
        switch (tipoConceptoEspecial) {
            case 1:
                if (formula.contains("CalculoISR")) {
                    isConceptoEspecial = true;
                }
                break;
            case 2:
                if (formula.contains("CalculoIMSS")) {
                    isConceptoEspecial = true;
                }
                break;
            case 3:
                if (formula.contains("ISRSubsidio")) {//ConceptoSubsidioEmpleado
                    isConceptoEspecial = true;
                }
                break;
            case 4:
                if (formula.contains("CalculoIMSSPatronal")) {
                    isConceptoEspecial = true;
                }
                break;
            case 5:
                if (formula.contains("ISRACargo")) {
                    isConceptoEspecial = true;
                }
                break;
            case 6:
                if (formula.contains("SubsEmpleoCalculado")) {
                    isConceptoEspecial = true;
                }
                break;
        }

        return isConceptoEspecial;
    }

    private MovNomConcep ejecutarParametroCondicionYFormula(MovNomConcep movimientosNomina, String claveTipoCorrida) {
        boolean condicion;
        try {
            agregaParametrosConceptosNomina(movimientosNomina.getMovNomConceParam());
            if (movimientosNomina.getConcepNomDefi().getCondicionConcepto().length() == 0) {
                condicion = true;
            } else {
                condicion = ejecutaFormula(movimientosNomina.getConcepNomDefi().getCondicionConcepto()) == 0.0 ? false : true;
            }
            if (condicion) {
                Double resultado;
                if (claveTipoCorrida.equalsIgnoreCase("FIN") | claveTipoCorrida.equalsIgnoreCase("LIQ")) {
                    resultado = movimientosNomina.getResultado();
                    if (movimientosNomina.getResultado() <= 0) {//esto es para cuando en el modulo del finiquito le ingresen un importe al concepto.
                        resultado = ejecutaFormula(movimientosNomina.getConcepNomDefi().getFormulaConcepto());
                        movimientosNomina.setResultado(aplicarMascara(movimientosNomina.getConcepNomDefi(), resultado, false));
                    }
                } else {
                    resultado = ejecutaFormula(movimientosNomina.getConcepNomDefi().getFormulaConcepto());
                    movimientosNomina.setResultado(aplicarMascara(movimientosNomina.getConcepNomDefi(), resultado, false));
                }
                movimientosNomina.setCalculado(resultado);
//                System.out.println("movimientosNomina " + movimientosNomina.getConcepNomDefi().getDescripcion() + " getResultado " + movimientosNomina.getResultado());
                calculaConceptosBaseAfecta(movimientosNomina.getMovNomBaseAfecta(), movimientosNomina.getResultado());
            }
        } catch (Exception ex) {
            mensajeResultado.setError("ERROR ejecutarParametroCondicionYFormula " + ex.getMessage());
            mensajeResultado.setNoError(-101);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutarParametroCondicionYFormula()1_Error: ").append(ex));
        }
        return movimientosNomina;
    }

    private MovNomConcep saveOrUpdateOrDeleteMovimientosNomina(MovNomConcep movimientosNomina, boolean omitirMovimiento, boolean isISRSubsidio) {
//        System.out.println("Concepto " + movimientosNomina.getConcepNomDefi().getDescripcion() + " Resultado " + movimientosNomina.getResultado() + " omitirMovimiento " + omitirMovimiento);
        try {
            if (movimientosNomina.getResultado() == 0 & !isISRSubsidio) {
                if (movimientosNomina.getId() != null) {
                    eliminarMovimientosNominaBasura(new Object[]{movimientosNomina.getId()});
//                    System.out.println("flush 12");
                    getSession().flush();
                }
            } else {
                if (!omitirMovimiento) {
                    cantidadSaveUpdate++;
                    getSession().saveOrUpdate(movimientosNomina);
                }
                if (cantidadSaveUpdate % cantidadFlush == 0 & cantidadSaveUpdate > 0) {
//                    System.out.println("flush 13");
                    getSession().flush();
                    getSession().clear();
//                    System.out.println("clear()");
                }
            }
        } catch (Exception ex) {
            mensajeResultado.setError("ERROR saveOrUpdateOrDeleteMovimientosNomina " + ex.getMessage());
            mensajeResultado.setNoError(-101);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("saveOrUpdateOrDeleteMovimientosNomina()1_Error: ").append(ex));
        }
//        System.out.println("--------------------------------FINAL saveOrUpdateOrDeleteMovimientosNomina");
        return movimientosNomina;
    }

    //Modificado agrego razon social y plaza empleado
    private void IsrRetenidos(MovNomConcep movimientosNomina) {
        try {
            Object[] retenidosISR = new Object[9];
            if (tipoTablaISR == TipoTablaISR.NORMAL && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes | modoAjustarIngresosMes == ProporcionaTablaAnual)) {
                strQuery.delete(0, strQuery.length()).append("SELECT ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrRetenidoNormal IS NULL) THEN 0.0 ELSE (isr.isrRetenidoNormal) END)) END , ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrRetenidoDirecto IS NULL) THEN 0.0 ELSE (isr.isrRetenidoDirecto) END)) END, ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrRetenidoAnual IS NULL) THEN 0.0 ELSE (isr.isrRetenidoAnual) END)) END, ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrACargoNormal IS NULL) THEN 0.0 ELSE (isr.isrACargoNormal) END)) END , ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrACargoDirecto IS NULL) THEN 0.0 ELSE (isr.isrACargoDirecto) END)) END, ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrACargoAnual IS NULL) THEN 0.0 ELSE (isr.isrACargoAnual) END)) END, ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.subsidioEmpleoNormal IS NULL) THEN 0.0 ELSE (isr.subsidioEmpleoNormal) END)) END, ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.subsidioEmpleoDirecto IS NULL) THEN 0.0 ELSE (isr.subsidioEmpleoDirecto) END)) END, ");
                strQuery.append(" CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.subsidioEmpleoAnual IS NULL) THEN 0.0 ELSE (isr.subsidioEmpleoAnual) END)) END");
                strQuery.append(" FROM ").append(CalculoISR.class.getSimpleName()).append(" isr INNER JOIN isr.movNomConcep mov ");//JSA23
                strQuery.append(" WHERE mov.id in ");
                strQuery.append(" (SELECT m.id FROM MovNomConcep m INNER JOIN m.periodosNomina p ");
                strQuery.append(" INNER JOIN m.empleado em INNER JOIN m.concepNomDefi c ");
                strQuery.append(" INNER JOIN m.tipoCorrida tc INNER JOIN m.razonesSociales rs ");
                strQuery.append(" INNER JOIN m.plazasPorEmpleado pemp ");
                if (modoAjustarIngresosMes == ProporcionaTablaAnual) {
                    strQuery.append(" INNER JOIN m.tipoNomina tn ");
                }
                strQuery.append(" WHERE m.uso = 0 ");
                strQuery.append(" AND em.clave = :claveEmp  ");
                strQuery.append(" AND tc.clave = :claveTipoCorrida ");
                strQuery.append(" AND (c.formulaConcepto LIKE CONCAT('%', :formulaConcepto, '%') OR c.formulaConcepto LIKE CONCAT('%', :formulaConcepto1, '%'))");
                strQuery.append(" AND rs.clave = :claveRazonSocial AND p.tipoCorrida.clave = :claveTipoCorrida ");
//            strQuery.append(" AND pemp.clave = :clavePlazaEmpleado ");
//////            if (isMov2Meses) {
//////                strQuery.append(" AND m.mes = :mesMovim ");
//////            }
                if (HibernateUtil.usaTypeBigInt) {
                    strQuery.append(" AND (p.clave < cast(:clavePeriodoNomina as int) ");
                    if (modoAjustarIngresosMes != ProporcionaTablaAnual) {
                        strQuery.append(" AND p.clave > ");
                        strQuery.append(" (SELECT CASE WHEN (count(pn) > 0) THEN");
                        strQuery.append(" MAX(CAST(pn.clave as int)) ");
                        strQuery.append(" ELSE 0 END ");
                        strQuery.append(" FROM PeriodosNomina pn INNER JOIN pn.tipoNomina t ");
                        strQuery.append(" WHERE pn.clave < cast(:clavePeriodoNomina as int) ");
                        strQuery.append(" AND t.clave = :claveTipoNomina ");
                        strQuery.append(" AND pn.cierreMes = true ");
                        strQuery.append(" AND pn.año = :yearPeriodo");
                        strQuery.append(" AND pn.tipoCorrida.clave = :claveTipoCorrida)");
                    } else {
                        strQuery.append(" AND tn.clave = :claveTipoNomina ");
                        strQuery.append(" AND p.año = :yearPeriodo ");
                        strQuery.append(" AND p.tipoCorrida.clave = :claveTipoCorrida ");
                    }
                    strQuery.append(" )) AND mov.ejercicio=:yearPeriodo");
                } else {
                    strQuery.append(" AND (p.clave < cast(:clavePeriodoNomina as long) ");
                    if (modoAjustarIngresosMes != ProporcionaTablaAnual) {
                        strQuery.append(" AND p.clave > ");
                        strQuery.append(" (SELECT CASE WHEN (count(pn) > 0) THEN");
                        strQuery.append(" MAX(CAST(pn.clave as long)) ");
                        strQuery.append(" ELSE 0 END ");
                        strQuery.append(" FROM PeriodosNomina pn INNER JOIN pn.tipoNomina t ");
                        strQuery.append(" WHERE pn.clave < cast(:clavePeriodoNomina as long) ");
                        strQuery.append(" AND t.clave = :claveTipoNomina ");
                        strQuery.append(" AND pn.cierreMes = true ");
                        strQuery.append(" AND pn.año = :yearPeriodo");
                        strQuery.append(" AND pn.tipoCorrida.clave = :claveTipoCorrida)");
                    } else {
                        strQuery.append(" AND tn.clave = :claveTipoNomina ");
                        strQuery.append(" AND p.año = :yearPeriodo ");
                        strQuery.append(" AND p.tipoCorrida.clave = :claveTipoCorrida ");
                    }
                    strQuery.append(" )) AND mov.ejercicio=:yearPeriodo");
                }

                camposParametro = new ArrayList<String>(0);
                valoresParametro = new ArrayList<Object>(0);
//////            if (isMov2Meses) {
//////                camposParametro.add("mesMovim");
//////                valoresParametro.add(movimientosNomina.getMes());
//////            }
                camposParametro.add("claveEmp");
                valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
                camposParametro.add("claveTipoCorrida");
                valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
                camposParametro.add("formulaConcepto");
                valoresParametro.add("CalculoISR"); //ConceptoISR
                camposParametro.add("formulaConcepto1");
                valoresParametro.add("ISRSubsidio"); //ConceptoISRSubsidio
                camposParametro.add("clavePeriodoNomina");
                valoresParametro.add(valoresConceptosEmpleados.get("NumPeriodo".toUpperCase()));
                camposParametro.add("claveRazonSocial");
                valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
//            camposParametro.add("clavePlazaEmpleado");
//            valoresParametro.add(valoresConceptosEmpleados.get("PlazaEmpleado".toUpperCase()));
                camposParametro.add("claveTipoNomina");
                valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
                camposParametro.add("yearPeriodo");
                valoresParametro.add(getFechaDelSistema().get(Calendar.YEAR));
                retenidosISR = (Object[]) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
                if (mensajeResultado.getNoError() == 100) {
                    mensajeResultado.setNoError(42);
                    return;
                }
            }
            //Busca el ISRRetenido del concepto ISR que ya existe para modificarlo
            if (movimientosNomina.getId() != null) {
                iSRRetenido = (CalculoISR) ejecutaQueryUnico("From " + CalculoISR.class.getSimpleName() + " isr Where isr.movNomConcep.id = :id", new String[]{"id"}, new Object[]{movimientosNomina.getId()});//JSA23

            }
            if (mensajeResultado.getNoError() != 0) {
                mensajeResultado.setNoError(43);
                return;
            }
            if (iSRRetenido == null) {
                iSRRetenido = new CalculoISR();//JSA23
            }
            MovNomConcep movNomConcepSubsidio = null;
            if (isMov2Meses & listMovNomConcepSubsidio.size() > 0) {
                int pos = movimientosNomina.getNumMovParticion() == 1 ? 0 : 1;
                movNomConcepSubsidio = listMovNomConcepSubsidio.get(pos);
            } else if (listMovNomConcepSubsidio.size() > 0) {
                movNomConcepSubsidio = listMovNomConcepSubsidio.get(0);
            }

            if (movNomConcepSubsidio != null) {
                if (movNomConcepSubsidio.getId() != null) {
                    //Busca el ISRRetenido del concepto Subsidio que ya existe para modificarlo
                    iSRRetenidoSubsidio = (CalculoISR) ejecutaQueryUnico("From " + CalculoISR.class.getSimpleName() + " isr Where isr.movNomConcep.id = :id", new String[]{"id"}, new Object[]{movNomConcepSubsidio.getId()});//JSA23
                }
            }
            if (mensajeResultado.getNoError() != 0) {
                mensajeResultado.setNoError(43);
                return;
            }

            if (iSRRetenidoSubsidio == null) {
                iSRRetenidoSubsidio = new CalculoISR();//JSA23
            }
//            //("***************************DESCONTANDO ACUMULADO    ***************");
//////            Calendar c = null;
//////            if (isMov2Meses) {
//////                c = Calendar.getInstance();
//////                c.setTime(periodosNomina.getFechaFinal());
//////            }
            if (isrNormal != 0.0) {
//                System.out.println("Retenido ISRNORMAL " + retenidosISR[0]);
                if (tipoTablaISR == TipoTablaISR.NORMAL && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)) {
                    if (isMov2Meses) {
                        int diasTotales;
                        if (manejaPagoDiasNaturales) {
                            diasTotales = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
                        } else {
                            diasTotales = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
                        }
                        isrNormal = isrNormal - (retenidosISR[0] == null ? 0.0 : (Double.valueOf(retenidosISR[0].toString()) * diasPago) / diasTotales);
                        retenido.setIsrACargoNormal(retenido.getIsrACargoNormal() - (retenidosISR[3] == null ? 0.0 : (Double.valueOf(retenidosISR[3].toString()) * diasPago) / diasTotales));
                        retenido.setSubsidioEmpleoNormal(retenido.getSubsidioEmpleoNormal() - (retenidosISR[6] == null ? 0.0 : (Double.valueOf(retenidosISR[6].toString()) * diasPago) / diasTotales));
                    } else {
                        if (descontarFaltasModoAjustaMes & periodosNomina.isCierreMes() & descontarDiasPago > 0) {
                            isrNormal = (isrNormal / factorMensual) * diasPago;
                        }
                        isrNormal = isrNormal - (retenidosISR[0] == null ? 0.0 : Double.valueOf(retenidosISR[0].toString()));
                        retenido.setIsrACargoNormal(retenido.getIsrACargoNormal() - (retenidosISR[3] == null ? 0.0 : Double.valueOf(retenidosISR[3].toString())));
                        retenido.setSubsidioEmpleoNormal(retenido.getSubsidioEmpleoNormal() - (retenidosISR[6] == null ? 0.0 : Double.valueOf(retenidosISR[6].toString())));
                    }
                }

//                else {
//                    retenido.setIsrACargoNormal(retenido.getIsrACargoNormal() - (retenidosISR[3] == null ? 0.0 : Double.valueOf(retenidosISR[3].toString())));
//                    retenido.setSubsidioEmpleoNormal(retenido.getSubsidioEmpleoNormal() - (retenidosISR[6] == null ? 0.0 : Double.valueOf(retenidosISR[6].toString())));
//                }
//////////                if (isMov2Meses) {
//////////                    if (movimientosNomina.getNumMovParticion() == 1) {
//////////                        if (retenidosISRACargoYSubsidioAlEmpleoEn2Meses != null) {
//////////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[0] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[0] + (isrNormal);
//////////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[3] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[3] + (retenido.getIsrACargoNormal());
//////////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[6] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[6] + (retenido.getSubsidioEmpleoNormal());
//////////                        }
//////////                    } else if (movimientosNomina.getNumMovParticion() == 2) {
//////////                        if (retenidosISRACargoYSubsidioAlEmpleoEn2Meses != null) {
//////////                            isrNormal = isrNormal - retenidosISRACargoYSubsidioAlEmpleoEn2Meses[0];
//////////                            retenido.setIsrACargoNormal(retenido.getIsrACargoNormal() - (retenidosISRACargoYSubsidioAlEmpleoEn2Meses[3]));
//////////                            retenido.setSubsidioEmpleoNormal(retenido.getSubsidioEmpleoNormal() - (retenidosISRACargoYSubsidioAlEmpleoEn2Meses[6]));
//////////                        }
//////////                    }
//////////                }
            } else {
                isrNormal = 0.0;
                retenido.setIsrACargoNormal(0.0);
                retenido.setSubsidioEmpleoNormal(0.0);

            }
            if (tipoCorrida.getClave().equalsIgnoreCase("FIN") || tipoCorrida.getClave().equalsIgnoreCase("LIQ")) {
//                Double valor = movimientosNomina.getResultado() / 3;
                isrNormal = movimientosNomina.getResultado();
//                isrDirecto = valor;
//                isrAnual = valor;
            }
            if (isrNormal >= 0) {
                retenido.setIsrNetoNormal(isrNormal);
                retenido.setIsrSubsidioNormal(0.0);
            } else {
                retenido.setIsrNetoNormal(0.0);
                retenido.setIsrSubsidioNormal(isrNormal * -1);
            }
            if (isrDirecto != 0.0) {
                if (tipoTablaISR == TipoTablaISR.NORMAL && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)) {
                    if (isMov2Meses) {
                        int diasPeriodo;
                        if (manejaPagoDiasNaturales) {
                            diasPeriodo = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
                        } else {
                            diasPeriodo = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
                        }
                        int diasPagoTotal = (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase());
                        isrDirecto = isrDirecto - (retenidosISR[1] == null ? 0.0 : (Double.valueOf(retenidosISR[1].toString()) * diasPagoTotal) / diasPeriodo);
                        retenido.setIsrACargoDirecto(retenido.getIsrACargoDirecto() - (retenidosISR[4] == null ? 0.0 : (Double.valueOf(retenidosISR[4].toString()) * diasPagoTotal) / diasPeriodo));
                        retenido.setSubsidioEmpleoDirecto(retenido.getSubsidioEmpleoDirecto() - (retenidosISR[7] == null ? 0.0 : (Double.valueOf(retenidosISR[7].toString()) * diasPagoTotal) / diasPeriodo));
                    } else {
                        isrDirecto = isrDirecto - (retenidosISR[1] == null ? 0.0 : Double.valueOf(retenidosISR[1].toString()));
                        retenido.setIsrACargoDirecto(retenido.getIsrACargoDirecto() - (retenidosISR[4] == null ? 0.0 : Double.valueOf(retenidosISR[4].toString())));
                        retenido.setSubsidioEmpleoDirecto(retenido.getSubsidioEmpleoDirecto() - (retenidosISR[7] == null ? 0.0 : Double.valueOf(retenidosISR[7].toString())));
                    }
                }
////////                if (isMov2Meses) {
////////                    if (movimientosNomina.getNumMovParticion() == 1) {
////////                        if (retenidosISRACargoYSubsidioAlEmpleoEn2Meses != null) {
////////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[1] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[1] + (isrDirecto);
////////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[4] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[4] + (retenido.getIsrACargoDirecto());
////////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[7] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[7] + (retenido.getSubsidioEmpleoDirecto());
////////                        }
////////                    } else if (movimientosNomina.getNumMovParticion() == 2) {
////////                        if (retenidosISRACargoYSubsidioAlEmpleoEn2Meses != null) {
////////                            isrDirecto = isrDirecto - retenidosISRACargoYSubsidioAlEmpleoEn2Meses[1];
////////                            retenido.setIsrACargoDirecto(retenido.getIsrACargoDirecto() - (retenidosISRACargoYSubsidioAlEmpleoEn2Meses[4]));
////////                            retenido.setSubsidioEmpleoDirecto(retenido.getSubsidioEmpleoDirecto() - (retenidosISRACargoYSubsidioAlEmpleoEn2Meses[7]));
////////                        }
////////                    }
////////                }
            } else {
                isrDirecto = 0.0;
                retenido.setIsrACargoDirecto(0.0);
                retenido.setSubsidioEmpleoDirecto(0.0);
            }
            if (isrDirecto >= 0) {
                retenido.setIsrNetoDirecto(isrDirecto);
                retenido.setIsrSubsidioDirecto(0.0);
            } else {
                retenido.setIsrNetoDirecto(0.0);
                retenido.setIsrSubsidioDirecto(isrDirecto * -1);
            }
            if (isrAnual != 0.0) {
                if (tipoTablaISR == TipoTablaISR.NORMAL && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes | modoAjustarIngresosMes == ProporcionaTablaAnual)) {
                    if (isMov2Meses) {
                        int diasTotales;
                        if (manejaPagoDiasNaturales) {
                            diasTotales = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
                        } else {
                            diasTotales = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
                        }
                        int diasPagoTotal = (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase());
                        isrAnual = isrAnual - (retenidosISR[2] == null ? 0 : (Double.valueOf(retenidosISR[2].toString()) * diasPagoTotal) / diasTotales);
                        retenido.setIsrACargoAnual(retenido.getIsrACargoAnual() - (retenidosISR[5] == null ? 0.0 : (Double.valueOf(retenidosISR[5].toString()) * diasPagoTotal) / diasTotales));
                        retenido.setSubsidioEmpleoAnual(retenido.getSubsidioEmpleoAnual() - (retenidosISR[8] == null ? 0.0 : (Double.valueOf(retenidosISR[8].toString()) * diasPagoTotal) / diasTotales));
                    } else {
                        isrAnual = isrAnual - (retenidosISR[2] == null ? 0 : Double.valueOf(retenidosISR[2].toString()));
                        retenido.setIsrACargoAnual(retenido.getIsrACargoAnual() - (retenidosISR[5] == null ? 0.0 : Double.valueOf(retenidosISR[5].toString())));
                        retenido.setSubsidioEmpleoAnual(retenido.getSubsidioEmpleoAnual() - (retenidosISR[8] == null ? 0.0 : Double.valueOf(retenidosISR[8].toString())));
                    }
                }
//////                if (isMov2Meses) {
//////                    if (movimientosNomina.getNumMovParticion() == 1) {
//////                        if (retenidosISRACargoYSubsidioAlEmpleoEn2Meses != null) {
//////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[2] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[2] + (isrAnual);
//////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[5] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[5] + (retenido.getIsrACargoAnual());
//////                            retenidosISRACargoYSubsidioAlEmpleoEn2Meses[8] = retenidosISRACargoYSubsidioAlEmpleoEn2Meses[8] + (retenido.getSubsidioEmpleoAnual());
//////                        }
//////                    } else if (movimientosNomina.getNumMovParticion() == 2) {
//////                        if (retenidosISRACargoYSubsidioAlEmpleoEn2Meses != null) {
//////                            isrAnual = isrAnual - retenidosISRACargoYSubsidioAlEmpleoEn2Meses[2];
//////                            retenido.setIsrACargoAnual(retenido.getIsrACargoAnual() - (retenidosISRACargoYSubsidioAlEmpleoEn2Meses[5]));
//////                            retenido.setSubsidioEmpleoAnual(retenido.getSubsidioEmpleoAnual() - (retenidosISRACargoYSubsidioAlEmpleoEn2Meses[8]));
//////                        }
//////                    }
//////                }
            } else {
                isrAnual = 0.0;
                retenido.setIsrACargoAnual(0.0);
                retenido.setSubsidioEmpleoAnual(0.0);
            }
            if (isrAnual >= 0) {
                retenido.setIsrNetoAnual(isrAnual);
                retenido.setIsrSubsidioAnual(0.0);
            } else {
                retenido.setIsrNetoAnual(0.0);
                retenido.setIsrSubsidioAnual(isrAnual * -1);
            }
            if (isrNormal + isrDirecto + isrAnual >= 0) {
                valoresConceptosEmpleados.put("ISRNeto".toUpperCase(), isrNormal + isrDirecto + isrAnual);
                valoresConceptosEmpleados.put("ISRSubsidio".toUpperCase(), 0.0);
            } else {
                valoresConceptosEmpleados.put("ISRNeto".toUpperCase(), 0.0);
                valoresConceptosEmpleados.put("ISRSubsidio".toUpperCase(), (isrNormal + isrDirecto + isrAnual) * -1);
            }
            valoresConceptosEmpleados.put("ISRACARGO".toUpperCase(), retenido.getIsrACargoNormal() + retenido.getIsrACargoDirecto() + retenido.getIsrACargoAnual());
            valoresConceptosEmpleados.put("SubsEmpleoCalculado".toUpperCase(), retenido.getSubsidioEmpleoNormal() + retenido.getSubsidioEmpleoDirecto() + retenido.getSubsidioEmpleoAnual());
            //Aqui se llena los datos del ISR del concepto ISR
            iSRRetenido = contruirISRRetenido(iSRRetenido);
            //Aqui se llena los datos del ISR del concepto Subsidio
            iSRRetenidoSubsidio = contruirISRRetenido(iSRRetenidoSubsidio);
//            System.out.println("isrNormal " + isrNormal + " isrDirecto " + isrDirecto + " isrAnual " + isrAnual);
        } catch (Exception ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(41);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("guardarIsrRetenidos()1_Error: ").append(ex));
        }
        retenido = null;
        valoresParametro = null;
        camposParametro = null;
    }

    private void agregaParametrosConceptosNomina(List<MovNomConceParam> movNomConceParametros) {
        if (movNomConceParametros != null) {
            int j;
            for (j = 0; j < movNomConceParametros.size(); j++) {
                DatosConceptosNomina.addVariable(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase());
                /////compEjec.addVariableExtasignarValorAParametroraNro(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase());
                if (movNomConceParametros.get(j).getParaConcepDeNom().getTipo().equalsIgnoreCase("INTEGER") | movNomConceParametros.get(j).getParaConcepDeNom().getMascara().contains("#")) {
                    if (movNomConceParametros.get(j).getParaConcepDeNom().getClasificadorParametro() == ClasificadorParametro.ENTRADA) {
                        compEjec.addVariableExtraNro("Param".concat(String.valueOf(movNomConceParametros.get(j).getParaConcepDeNom().getNumero())).toUpperCase());
                    } else {
                        compEjec.addVariableExtraNro(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase());
                    }
                    ///compEjec.addVariableExtraNro(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase());
                } else if (movNomConceParametros.get(j).getParaConcepDeNom().getClasificadorParametro() == ClasificadorParametro.ENTRADA) {
                    compEjec.addVariableExtraStr("Param".concat(String.valueOf(movNomConceParametros.get(j).getParaConcepDeNom().getNumero())).toUpperCase());
                } else {
                    compEjec.addVariableExtraStr(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase());
                } ///compEjec.addVariableExtraStr(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase());
                if (movNomConceParametros.get(j).getParaConcepDeNom().getClasificadorParametro() == ClasificadorParametro.ENTRADA) {
////////                    if (isMov2Meses) {
////////                        Calendar cFecha = Calendar.getInstance();
////////                        int diasPagoPeriodo;
////////                        if (movNomConceParametros.get(j).getMovNomConcep().getMes() == 1) {
////////                            cFecha.setTime(movNomConceParametros.get(j).getMovNomConcep().getPeriodosNomina().getFechaInicial());
////////                            cFecha.set(Calendar.DATE, cFecha.getActualMaximum(Calendar.DATE));
////////                            diasPagoPeriodo = (cantidadDiasEntreDosFechas(movNomConceParametros.get(j).getMovNomConcep().getPeriodosNomina().getFechaInicial(), cFecha.getTime()) + 1);
////////                        } else {
////////                            cFecha.setTime(movNomConceParametros.get(j).getMovNomConcep().getPeriodosNomina().getFechaFinal());
////////                            cFecha.set(Calendar.DATE, 1);
////////                            diasPagoPeriodo = (cantidadDiasEntreDosFechas(cFecha.getTime(), movNomConceParametros.get(j).getMovNomConcep().getPeriodosNomina().getFechaFinal()) + 1);
////////                        }
////////                        valoresConceptosEmpleados.put("Param".concat(String.valueOf(movNomConceParametros.get(j).getParaConcepDeNom().getNumero())).toUpperCase(), ((diasPagoPeriodo * Double.valueOf(movNomConceParametros.get(j).getValor())) / (Integer) valoresConceptosGlobales.get("PeriodicidadEnDias".toUpperCase())));
////////                    } else {
////////                        valoresConceptosEmpleados.put("Param".concat(String.valueOf(movNomConceParametros.get(j).getParaConcepDeNom().getNumero())).toUpperCase(), movNomConceParametros.get(j).getValor());
////////                    }
                    valoresConceptosEmpleados.put("Param".concat(String.valueOf(movNomConceParametros.get(j).getParaConcepDeNom().getNumero())).toUpperCase(), movNomConceParametros.get(j).getValor());
                } else {
                    boolean x = false;//Solo el primer parametro y solo aplica para la cantidad en los finiquitos o liquidaciones.
                    if ((movNomConceParametros.get(j).getMovNomConcep().getTipoCorrida().getClave().equalsIgnoreCase("FIN")
                            | movNomConceParametros.get(j).getMovNomConcep().getTipoCorrida().getClave().equalsIgnoreCase("LIQ")) & j == 0) {
                        if (movNomConceParametros.get(j).getParaConcepDeNom().getTipo().equalsIgnoreCase("INTEGER") | movNomConceParametros.get(j).getParaConcepDeNom().getMascara().contains("#")) {
                            if (Float.parseFloat(movNomConceParametros.get(j).getValor()) > 0) {
                                x = true;
                            }
                        } else if (!movNomConceParametros.get(j).getValor().isEmpty()) {
                            x = true;
                        }
                    }
                    if (x) {
                        valoresConceptosEmpleados.put(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase(), movNomConceParametros.get(j).getValor());
                    } else {
                        valoresConceptosEmpleados.put(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase(), ejecutaFormula(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion()));
                    }

                    if (valoresConceptosEmpleados.containsKey(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase())) {
                        String valor = valoresConceptosEmpleados.get(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase()).toString();
                        movNomConceParametros.get(j).setValor(valor);
                    }
                }

                ////valoresConceptosEmpleados.put(movNomConceParametros.get(j).getParaConcepDeNom().getDescripcion().toUpperCase(), movNomConceParametros.get(j).getValor());
            }
        }
    }

    public boolean evaluaPeriodoMovAbarca2Meses(PeriodosNomina mov) {
        Calendar fechaInicio = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
        fechaInicio.setTime(mov.getFechaInicial());
        fechaFinal.setTime(mov.getFechaFinal());
        if (fechaInicio.get(Calendar.MONTH) != fechaFinal.get(Calendar.MONTH)) {
            return true;
        }
        return false;
    }

    //Modificado usando plazas y razon social
    private List<MovNomConcep> creaMovimientosPlazasConceptosAutomaticos(PlazasPorEmpleado plazaPorEmpleado, PeriodosNomina periodosNominas, String claveTipoCorrida, String claveRazonSocial, String claveCentroCosto) {
        List<MovNomConcep> movNomConceptos = new ArrayList<MovNomConcep>(0);
        try {
            if (filtroConceptosNomina != null) {
                int j;
                for (j = 0; j < filtroConceptosNomina.size(); j++) {
                    MovNomConcep movNomConcep;
                    Calendar fechaPeriodo = Calendar.getInstance();
//                    System.out.println("Concep " + filtroConceptosNomina.get(j).getDescripcion() + " formu " + filtroConceptosNomina.get(j).getFormulaConcepto());
                    movNomConcep = creaMovNomConcep(filtroConceptosNomina.get(j), plazaPorEmpleado, periodosNominas, tipoCorrida, razonesSociales, centroDeCostoMovimiento);
                    movNomConceptos.add(movNomConcep);
                    if (evaluaPeriodoMovAbarca2Meses(movNomConcep.getPeriodosNomina())) {
                        MovNomConcep newMov = MovNomConcep.copiaMovimiento(movNomConcep);
                        fechaPeriodo.setTime(newMov.getPeriodosNomina().getFechaFinal());
                        newMov.setMes(fechaPeriodo.get(Calendar.MONTH) + 1);
                        newMov.setNumMovParticion(2);
                        movNomConceptos.add(newMov);
                    }
                }
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(33);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("creaMovimientosPlazasConceptosAutomaticos()1_Error: ").append(ex));
        }
        return movNomConceptos;
    }

    private MovNomConcep creaMovNomConcep(ConcepNomDefi concepNomDefi, PlazasPorEmpleado plazaPorEmpleado, PeriodosNomina periodosNominas, TipoCorrida tipoCorrida, RazonesSociales razonesSociales, CentroDeCosto centroDeCosto) {
        Calendar fechaPeriodo = Calendar.getInstance();
        MovNomConcep movNomConcep = new MovNomConcep();
        movNomConcep.setEmpleado(plazaPorEmpleado.getEmpleados());
        movNomConcep.setPlazasPorEmpleado(plazaPorEmpleado);
        movNomConcep.setPeriodosNomina(periodosNominas);
        movNomConcep.setConcepNomDefi(concepNomDefi);
        movNomConcep.setTipoCorrida(tipoCorrida);
        movNomConcep.setTipoNomina(periodosNominas.getTipoNomina());
        movNomConcep.setCentroDeCosto(centroDeCosto);
        movNomConcep.setRazonesSociales(razonesSociales);
        if (concepNomDefi.getBaseAfecConcepNomi() != null) {
            movNomConcep.setMovNomBaseAfecta(creaMovimBaseAfectar(concepNomDefi.getBaseAfecConcepNomi(), movNomConcep));
        }

        if (movNomConcep.getConcepNomDefi().getParametroConceptosDeNominas() == null ? false : movNomConcep.getConcepNomDefi().getParametroConceptosDeNominas().isEmpty() ? false : true) {
            movNomConcep.setMovNomConceParam(creaMovNomConceParam(movNomConcep.getConcepNomDefi(), movNomConcep));
        }
        movNomConcep.setFechaCierr(periodosNominas.getFechaCierre());
        movNomConcep.setFechaIni(periodosNominas.getFechaInicial());
        movNomConcep.setTipoPantalla(tipoPantallaSistema);
        movNomConcep.setOrdenId(0);
        movNomConcep.setResultado(0.0);
        movNomConcep.setNumero(1);
        movNomConcep.setCalculado(0.0);
        fechaPeriodo.setTime(movNomConcep.getPeriodosNomina().getFechaInicial());
        movNomConcep.setEjercicio(periodosNominas.getAño());
        movNomConcep.setMes(fechaPeriodo.get(Calendar.MONTH) + 1);
        movNomConcep.setNumMovParticion(1);
        movNomConcep.setUso(0);
        return movNomConcep;
    }

    private MovNomConcep duplicarMovNomConcep(MovNomConcep movNomConcepTmp, int numero, PlazasPorEmpleado plazasPorEmpleado) {
        MovNomConcep movNomConcep = new MovNomConcep();
        movNomConcep.setEmpleado(movNomConcepTmp.getPlazasPorEmpleado().getEmpleados());
        movNomConcep.setPlazasPorEmpleado(plazasPorEmpleado == null ? movNomConcepTmp.getPlazasPorEmpleado() : plazasPorEmpleado);
        movNomConcep.setPeriodosNomina(movNomConcepTmp.getPeriodosNomina());
        movNomConcep.setConcepNomDefi(movNomConcepTmp.getConcepNomDefi());
        movNomConcep.setTipoCorrida(movNomConcepTmp.getTipoCorrida());
        movNomConcep.setTipoNomina(movNomConcepTmp.getTipoNomina());
        movNomConcep.setCentroDeCosto(movNomConcepTmp.getCentroDeCosto());
        movNomConcep.setRazonesSociales(movNomConcepTmp.getRazonesSociales());
        if (movNomConcepTmp.getConcepNomDefi().getBaseAfecConcepNomi() != null) {
            movNomConcep.setMovNomBaseAfecta(creaMovimBaseAfectar(movNomConcepTmp.getConcepNomDefi().getBaseAfecConcepNomi(), movNomConcep));
        }

        if (movNomConcepTmp.getConcepNomDefi().getParametroConceptosDeNominas() == null ? false : movNomConcepTmp.getConcepNomDefi().getParametroConceptosDeNominas().isEmpty() ? false : true) {
            movNomConcep.setMovNomConceParam(creaMovNomConceParam(movNomConcepTmp.getConcepNomDefi(), movNomConcep));
        }
        movNomConcep.setFechaCierr(movNomConcepTmp.getPeriodosNomina().getFechaCierre());
        movNomConcep.setFechaIni(movNomConcepTmp.getPeriodosNomina().getFechaInicial());
        movNomConcep.setTipoPantalla(tipoPantallaSistema);
        movNomConcep.setOrdenId(movNomConcepTmp.getOrdenId());
        movNomConcep.setResultado(0.0);
        movNomConcep.setNumero(numero);
        movNomConcep.setCalculado(0.0);
        movNomConcep.setEjercicio(movNomConcepTmp.getEjercicio());
        movNomConcep.setMes(movNomConcepTmp.getMes());
        movNomConcep.setNumMovParticion(movNomConcepTmp.getNumMovParticion());
        movNomConcep.setUso(movNomConcepTmp.getUso());
        return movNomConcep;
    }

    private List<MovNomBaseAfecta> creaMovimBaseAfectar(List<BaseAfecConcepNom> baseAfecConcepNominas, MovNomConcep mnc) {
        List<MovNomBaseAfecta> movNominaBaseAfectas = new ArrayList<MovNomBaseAfecta>(0);
        MovNomBaseAfecta m;
        if (mnc.getMovNomBaseAfecta() == null ? true : mnc.getMovNomBaseAfecta().isEmpty()) {
            for (BaseAfecConcepNom afecConcepNom : baseAfecConcepNominas) {
                m = new MovNomBaseAfecta();
                m.setBaseAfecConcepNom(afecConcepNom);
                m.setMovNomConcep(mnc);
                m.setUso(0);
                movNominaBaseAfectas.add(m);
            }
        } else if (baseAfecConcepNominas.isEmpty()) {
            if (!mnc.getMovNomBaseAfecta().isEmpty()) {
                for (int j = 0; j < mnc.getMovNomBaseAfecta().size(); j++) {
                    getSession().delete(mnc.getMovNomBaseAfecta().get(j));
                }
            }
        } else {
            List<MovNomBaseAfecta> movNominaBaseAfectasTmp = new ArrayList<MovNomBaseAfecta>();
            movNominaBaseAfectasTmp.addAll(mnc.getMovNomBaseAfecta());
            if (movNominaBaseAfectasTmp == null) {
                movNominaBaseAfectasTmp = new ArrayList<MovNomBaseAfecta>();
                mnc.setMovNomBaseAfecta(movNominaBaseAfectasTmp);
            }
            for (int i = 0; i < baseAfecConcepNominas.size(); i++) {
                for (int j = 0; j < mnc.getMovNomBaseAfecta().size(); j++) {
                    boolean existe = false;
                    if (baseAfecConcepNominas.get(i).getBaseNomina().getClave().equalsIgnoreCase(mnc.getMovNomBaseAfecta().get(j).getBaseAfecConcepNom().getBaseNomina().getClave())) {
                        existe = true;
                    }
                    if (existe) {
                        movNominaBaseAfectas.add(mnc.getMovNomBaseAfecta().get(j));
                        movNominaBaseAfectasTmp.remove(mnc.getMovNomBaseAfecta().get(j));
                    } else {
                        m = new MovNomBaseAfecta();
                        m.setBaseAfecConcepNom(baseAfecConcepNominas.get(i));
                        m.setMovNomConcep(mnc);
                        m.setUso(0);
                        movNominaBaseAfectas.add(m);
                    }
                }
            }
            if (!movNominaBaseAfectasTmp.isEmpty()) {
                for (int j = 0; j < movNominaBaseAfectasTmp.size(); j++) {
                    getSession().delete(movNominaBaseAfectasTmp.get(j));
                }
            }
        }
        return movNominaBaseAfectas;
    }

    private double aplicarMascara(ConcepNomDefi concepNomDefi, double resultado, boolean obligarTruncar) {//JSA15
        String[] mascaraSeparada = null;
        String[] resultadoSeparado;
        TipoAccionMascaras tipoAccionMascaras = null;
        double factor = factorRedondeoGral, minimun = minimoRedondeoGral;
        if (concepNomDefi == null ? false : (concepNomDefi.getTipoAccionMascaras() != null)) {
            tipoAccionMascaras = concepNomDefi.getTipoAccionMascaras();
        } else {
            tipoAccionMascaras = tipoAccionMascarasGral;
        }
        Double resultWithMask;
        if (concepNomDefi == null ? false : (concepNomDefi.getMascara() == null ? false : concepNomDefi.getMascara().length() > 0)) {
            if (concepNomDefi.getMascara().contains(".")) {
                mascaraSeparada = concepNomDefi.getMascara().split("\\.");
            } else {
                mascaraSeparada = new String[]{concepNomDefi.getMascara(), ""};
            }
            if (mascaraSeparada[1].length() > 0) {
                String factorString = ".", minimunString = ".";
                for (int i = 0; i < mascaraSeparada[1].length() - 1; i++) {
                    factorString += "0";
                }
                minimunString = factorString;
                factorString += "1";
                minimunString += "05";
                factor = Double.valueOf(factorString);
                minimun = Double.valueOf(minimunString);
            }
        } else if (mascaraResultadoGral != null) {
            mascaraSeparada = mascaraResultadoGral;
        }
        if (tipoAccionMascaras == TipoAccionMascaras.Redondear & !obligarTruncar) {
            resultWithMask = RoundingANumber.round(resultado, factor, minimun);
            if (resultado != resultWithMask) {
                if (String.valueOf(resultado).contains(".")) {
                    resultadoSeparado = String.valueOf(resultado).split("\\.");
                } else {
                    resultadoSeparado = new String[]{String.valueOf(resultado), ""};
                }
                if (resultadoSeparado[1].length() > mascaraSeparada[1].length()) {
                    resultadoSeparado[1] = resultadoSeparado[1].substring(0, mascaraSeparada[1].length());
                }
                StringBuilder builder = new StringBuilder(resultadoSeparado[0]);
                if (resultadoSeparado[1].length() > 0) {
                    builder.append(".").append(resultadoSeparado[1]);
                }
//              if (resultado > 0) {
//                  System.out.println("resultWithMask " + resultWithMask + " resultado " + resultado);
//              }
                resultado = Double.valueOf(builder.toString());
//              if (resultado > 0) {
//                  System.out.println("2 resultWithMask " + resultWithMask + " resultado " + resultado);
//              }
                importeRedondeo += resultWithMask - resultado;
            }
        } else {
            if (String.valueOf(resultado).contains(".")) {
                resultadoSeparado = String.valueOf(resultado).split("\\.");
            } else {
                resultadoSeparado = new String[]{String.valueOf(resultado), ""};
            }
            if (resultadoSeparado[1].length() > mascaraSeparada[1].length()) {
                resultadoSeparado[1] = resultadoSeparado[1].substring(0, mascaraSeparada[1].length());
            }
            StringBuilder builder = new StringBuilder(resultadoSeparado[0]);
            if (resultadoSeparado[1].length() > 0) {
                builder.append(".").append(resultadoSeparado[1]);
            }
            resultWithMask = Double.valueOf(builder.toString());
        }
        return resultWithMask;
    }

    private String convierteClaveFormulaANombre(String formula) {
        if (propertieFuente == null) {
            propertieFuente = compEjec.getFuenteDatos();
        }

        String[] variables = obtieneVariablesFormula(formula);
        String variable;
        StringBuilder variablesFormula = new StringBuilder();
        for (String str : variables) {
            if (propertieFuente.containsKey(str)) {
                if (propertieFuente.containsKey(str.concat("_TipoDato"))) {
                    variable = str;
                } else if (propertieFuente.containsKey(str.concat("_TipoNodo"))) {
                    variable = str;
                } else {
                    variable = propertieFuente.getProperty(str);
                }
                variablesFormula.append(variable);
            } else {
                variablesFormula.append(str);
            }
        }
        return variablesFormula.toString();
    }

    private String eliminaCaracteresEspacios(String texto) {
        StringBuilder sb = new StringBuilder();
        texto = texto == null ? "" : texto;

        for (int i = 0; i < texto.length(); i++) {
            if (texto.charAt(i) == ' ') {
                sb.append(texto.charAt(i));
            } else if (!Character.isWhitespace(texto.charAt(i))) {
                sb.append(texto.charAt(i));
            }
        }
        return sb.toString();
    }

    private String[] obtieneVariablesFormula(String strFormula) {
        strFormula = strFormula == null ? "" : strFormula;
        List<String> variablesFormulas = new ArrayList<String>();
        if (strFormula.length() > 0) {
            strFormula = eliminaCaracteresEspacios(strFormula);
            Integer i;
            StringBuilder valor = new StringBuilder();
            for (i = 0; i < strFormula.length(); i++) {
                if (Character.isLetterOrDigit(strFormula.charAt(i)) | strFormula.charAt(i) == '.' | strFormula.charAt(i) == '_') {
                    valor.append(strFormula.charAt(i));
                } else {
                    if (valor.length() > 0) {
                        if (valor.toString().equalsIgnoreCase("IF") | valor.toString().equalsIgnoreCase("SI")) {
                            valor.append(" ");
                        } else if (valor.toString().equalsIgnoreCase("ELSE") | valor.toString().equalsIgnoreCase("THEN") | valor.toString().equalsIgnoreCase("SINO") | valor.toString().equalsIgnoreCase("ENTONCES")
                                | valor.toString().equalsIgnoreCase("AND") | valor.toString().equalsIgnoreCase("Y") | valor.toString().equalsIgnoreCase("OR") | valor.toString().equalsIgnoreCase("o")
                                | valor.toString().equalsIgnoreCase("NO") | valor.toString().equalsIgnoreCase("NOT")) {
                            valor.insert(0, " ");
                            valor.append(" ");
                        }
                        variablesFormulas.add(valor.toString());
                        valor.delete(0, valor.length());
                    }
                    if (!Character.isWhitespace(strFormula.charAt(i))) {
                        variablesFormulas.add(String.valueOf(strFormula.charAt(i)));
                    }
                }
            }
            if (valor.length() > 0) {
                variablesFormulas.add(valor.toString());
            }
        }

        return variablesFormulas.toArray(new String[]{});
    }

    private Double ejecutaFormula(String formulaCalculo) {
        int ren;
        Object[] valores;
        double resultado = 0;
        boolean variableTipoConcepto = false, usaVariable2Periodos = false;
        if (formulaCalculo.length() > 0) {
//            System.out.println("ejecutaFormula------------------------");
            try {
                agregaVariableConceptos();
                if (!isCalculoPTU && (formulaCalculo.contains("PTUDIAS".toUpperCase())
                        || formulaCalculo.contains("PTUPERCEPCIONES".toUpperCase())
                        || formulaCalculo.contains("PTUTOTAL".toUpperCase()))) {
                    return 0.0;
                }
                formulaCalculo = convierteClaveFormulaANombre(formulaCalculo);
                valores = compilador.compila(formulaCalculo.toUpperCase(), new Reg_Polish[]{}, "", false);
                TipoClasificacionFormula tfc;
                ///TipoAcumulado tipoAcumulado;
                String var, tipoDato = "S";
                boolean formulaEsFuncion = false;
                if ((Boolean) valores[2]) {
                    String[] identificadores = ((Set<String>) valores[3]).toArray(new String[]{});
                    for (ren = 0; ren < identificadores.length; ren++) {
////                    if (valoresConceptosEmpleados.get(identificadores[ren]) == null) {
                        identificadores[ren] = identificadores[ren].trim();
                        if (identificadores[ren].startsWith("CONCEP")) {
                            buscaFormulaConceptos(identificadores[ren]);
                            variableTipoConcepto = true;
                        } else {
                            var = formulaCalculo.substring(formulaCalculo.toUpperCase().indexOf(identificadores[ren]), formulaCalculo.toUpperCase().indexOf(identificadores[ren]) + identificadores[ren].length());
                            tfc = TipoClasificacionFormula.getEnum(propertieFuente.getProperty(var.concat("_TipoDato")));
                            tipoDato = propertieFuente.getProperty(var.concat("_Tipo"));
                            if (tfc == TipoClasificacionFormula.DATOCALCULO | tfc == TipoClasificacionFormula.DATOPERIODO | tfc == TipoClasificacionFormula.DATOMENSUAL | tfc == TipoClasificacionFormula.DATOBIMESTRAL | tfc == TipoClasificacionFormula.DATOANUAL) {
                                if (tfc != TipoClasificacionFormula.DATOCALCULO) {
                                    valoresConceptosEmpleados.put(identificadores[ren], "");
                                }
//                                System.out.println("identificadores[ren] " + identificadores[ren] + "" + tfc);
                                /////tipoAcumulado = tipoAcumuladoPorClaveVariable(identificadores[ren]);
                                buscaVaricablesCalcular(identificadores[ren], tfc);
                            } else if (tfc == TipoClasificacionFormula.DATOFUNCION) {
                                formulaEsFuncion = true;
                                String funcion = formulaCalculo.toUpperCase();
                                int posParenAb = -1, posParenCerr = 0, inicioFun = funcion.toUpperCase().indexOf(identificadores[ren]);
                                for (int i = inicioFun; i < funcion.length(); i++) {
                                    if (funcion.charAt(i) == '(') {
                                        posParenAb = i;
                                    } else if (funcion.charAt(i) == ')' & posParenAb > -1) {
                                        posParenCerr = i;
                                        break;
                                    }
                                }
                                funcion = funcion.substring(funcion.indexOf(identificadores[ren]), posParenCerr + 1);
                                buscaVaricablesCalcular(funcion, tfc);
                            } else if (tfc == TipoClasificacionFormula.DATOTABLA) {
                                String funcion = formulaCalculo.toUpperCase();
                                variablesTipoTabla(funcion);
                            }
                            ///buscaVaricablesCalcular(identificadores[ren], TipoAcumulado.NORMAL);
                        }
//////                    }

                        if (valoresConceptosEmpleados.containsKey(identificadores[ren])) {
                            Object valor = valoresConceptosEmpleados.get(identificadores[ren]);
                            if (valor == null) {
                                valoresConceptosEmpleados.put(identificadores[ren], tipoDato.equalsIgnoreCase("N") ? 0.0 : "");
                            }
                        } else {
                            valoresConceptosEmpleados.put(identificadores[ren], tipoDato.equalsIgnoreCase("N") ? 0.0 : "");
                        }

                        if (variablesAjustadasEnDosPeriodos.contains(identificadores[ren])) {
                            usaVariable2Periodos = true;
                        }
//                        if (identificadores[ren].equalsIgnoreCase("ISRSubsidio")) {
//                            if (valoresConceptosEmpleados.containsKey("ISRSubsidio".toUpperCase())) {
//                                Object valor = valoresConceptosEmpleados.get("ISRSubsidio".toUpperCase());
//                                if (valor == null) {
//                                    valoresConceptosEmpleados.put("ISRSubsidio".toUpperCase(), 0.0);
//                                }
//                            } else {
//                                valoresConceptosEmpleados.put("ISRSubsidio".toUpperCase(), 0.0);
//                            }
//                        }
                    }

                    if (variableTipoConcepto) { //si Contiene variables de tipo Concepto vuelve a compilar formula
                        agregaVariableConceptos();
                        valores = compilador.compila(formulaCalculo.toUpperCase(), new Reg_Polish[]{}, "", false);
                    }

                    resultado = compilador.calcula((Reg_Polish[]) valores[0], valoresConceptosEmpleados, resultado);
//////                    if (!usaVariable2Periodos & !formulaEsFuncion) {
//////                        if (valoresConceptosEmpleados.containsKey("DiasPago".toUpperCase()) & periodosNomina != null) {
//////                            int diasTotales;
//////                            if (manejaPagoDiasNaturales) {
//////                                diasTotales = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
//////                            } else {
//////                                diasTotales = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
//////                            }
//////                            resultado = (resultado * (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase()) / diasTotales);
//////                        }
//////                    }
                } else {
                    resultado = 0;
                }

            } catch (Exception ex) {
                mensajeResultado.setNoError(-101);
                mensajeResultado.setError("ERROR al procesar la formula " + formulaCalculo + (valoresConceptosEmpleados.containsKey("NumEmpleado".toUpperCase()) ? " del empleado " + valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()) : ""));
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaFormula()1_Error: ").append(ex));
            }
        } else {
            resultado = 0;
        }
        return resultado;
    }

    private void calculaConceptosBaseAfecta(List<MovNomBaseAfecta> afecConcepNominas, Double resultadoConcepto) {
        int base;
        double calculo;
        if (afecConcepNominas != null) {
            MovNomBaseAfecta movNominaBaseAfecta;
            for (base = 0; base < afecConcepNominas.size(); base++) {
                movNominaBaseAfecta = afecConcepNominas.get(base);
                if (movNominaBaseAfecta.getBaseAfecConcepNom() != null) {
                    if (movNominaBaseAfecta.getBaseAfecConcepNom().getFormulaExenta() != null) {
                        if (afecConcepNominas.get(base).getBaseAfecConcepNom().getFormulaExenta().length() > 0) {
//                            System.out.println("Formula Exenta " + afecConcepNominas.get(base).getBaseAfecConcepNom().getFormulaExenta());
                            calculo = ejecutaFormula(afecConcepNominas.get(base).getBaseAfecConcepNom().getFormulaExenta());
                            if (calculo >= resultadoConcepto) {
                                movNominaBaseAfecta.setResultado(0.0);
                                movNominaBaseAfecta.setResultadoExento(resultadoConcepto);
                            } else {
                                movNominaBaseAfecta.setResultado(resultadoConcepto - calculo);
                                movNominaBaseAfecta.setResultadoExento(calculo);
                            }
                        } else {
                            movNominaBaseAfecta.setResultado(resultadoConcepto);
                            movNominaBaseAfecta.setResultadoExento(0.0);
                        }
                    } else {
                        movNominaBaseAfecta.setResultado(resultadoConcepto);
                        movNominaBaseAfecta.setResultadoExento(0.0);
                    }
//                    System.out.println("BaseAfecta getResultado" + movNominaBaseAfecta.getResultado() + " getResultadoExento " + movNominaBaseAfecta.getResultadoExento());
                    if (movNominaBaseAfecta.getBaseAfecConcepNom().getBaseNomina().getClave().equals(ClavesParametrosModulos.claveBaseNominaISR) & movNominaBaseAfecta.getResultado() > 0) {
                        boolean modificoResultado = false;
                        switch (movNominaBaseAfecta.getBaseAfecConcepNom().getTipoAfecta()) {
                            case 0:
                                if (movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getNaturaleza().equals(Naturaleza.PERCEPCION)) {
                                    /// para cuando desconte faltas y es de tipo ajustar al mes el calculo del isr
                                    if (descontarFaltasModoAjustaMes & periodosNomina.isCierreMes() && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)
                                            & movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("DiasPago".toUpperCase())) {
                                        modificoResultado = true;
                                        acumuladoNormal = acumuladoNormal + ((movNominaBaseAfecta.getResultado() / (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase()) * (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase())));
                                    }
                                    if (!modificoResultado) {
                                        acumuladoNormal = acumuladoNormal + movNominaBaseAfecta.getResultado();
                                    }
                                } else if (movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getNaturaleza().equals(Naturaleza.DEDUCCION)) {
                                    /// para cuando desconte faltas y es de tipo ajustar al mes el calculo del isr
                                    if (descontarFaltasModoAjustaMes & periodosNomina.isCierreMes() && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)
                                            & movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("DiasPago".toUpperCase())) {
                                        modificoResultado = true;
                                        acumuladoNormal = acumuladoNormal - ((movNominaBaseAfecta.getResultado() / (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase()) * (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase())));
                                    }

                                    if (!modificoResultado) {
                                        acumuladoNormal = acumuladoNormal - movNominaBaseAfecta.getResultado();
                                    }
                                }
                                break;
                            case 1:
                                if (movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getNaturaleza().equals(Naturaleza.PERCEPCION)) {
                                    /// para cuando desconte faltas y es de tipo ajustar al mes el calculo del isr
                                    if (descontarFaltasModoAjustaMes & periodosNomina.isCierreMes() && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)
                                            & movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("DiasPago".toUpperCase())) {
                                        modificoResultado = true;
                                        acumuladoDirecto = acumuladoDirecto + ((movNominaBaseAfecta.getResultado() / (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase()) * (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase())));
                                    }

                                    if (!modificoResultado) {
                                        acumuladoDirecto = acumuladoDirecto + movNominaBaseAfecta.getResultado();
                                    }
                                } else if (movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getNaturaleza().equals(Naturaleza.DEDUCCION)) {
                                    /// para cuando desconte faltas y es de tipo ajustar al mes el calculo del isr
                                    if (descontarFaltasModoAjustaMes & periodosNomina.isCierreMes() && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)
                                            & movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("DiasPago".toUpperCase())) {
                                        modificoResultado = true;
                                        acumuladoDirecto = acumuladoDirecto - ((movNominaBaseAfecta.getResultado() / (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase()) * (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase())));
                                    }

                                    if (!modificoResultado) {
                                        acumuladoDirecto = acumuladoDirecto - movNominaBaseAfecta.getResultado();
                                    }
                                }
                                break;
                            case 2:
                                if (movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getNaturaleza().equals(Naturaleza.PERCEPCION)) {
                                    /// para cuando desconte faltas y es de tipo ajustar al mes el calculo del isr
                                    if (descontarFaltasModoAjustaMes & periodosNomina.isCierreMes() && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)
                                            & movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("DiasPago".toUpperCase())) {
                                        modificoResultado = true;
                                        acumuladoAnual = acumuladoAnual + ((movNominaBaseAfecta.getResultado() / (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase()) * (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase())));
                                    }

                                    if (!modificoResultado) {
                                        acumuladoAnual = acumuladoAnual + movNominaBaseAfecta.getResultado();
                                    }
                                } else if (movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getNaturaleza().equals(Naturaleza.DEDUCCION)) {
                                    /// para cuando desconte faltas y es de tipo ajustar al mes el calculo del isr
                                    if (descontarFaltasModoAjustaMes & periodosNomina.isCierreMes() && (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)
                                            & movNominaBaseAfecta.getMovNomConcep().getConcepNomDefi().getFormulaConcepto().toUpperCase().contains("DiasPago".toUpperCase())) {
                                        modificoResultado = true;
                                        acumuladoAnual = acumuladoAnual - ((movNominaBaseAfecta.getResultado() / (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase()) * (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase())));
                                    }

                                    if (!modificoResultado) {
                                        acumuladoAnual = acumuladoAnual - movNominaBaseAfecta.getResultado();
                                    }
                                }
                                break;
                        }
                    } else if (movNominaBaseAfecta.getBaseAfecConcepNom().getBaseNomina().getClave().equals(ClavesParametrosModulos.claveBaseNominaIMSS)) {
                        switch (movNominaBaseAfecta.getBaseAfecConcepNom().getTipoAfecta()) {
                            case 0:
                                acumuladoImssFijo = acumuladoImssFijo + movNominaBaseAfecta.getResultado();
                                break;
                            case 1:
                                //acumuladoDirecto = acumuladoDirecto + movNominaBaseAfecta.getResultado();
                                break;
                        }
                    }
                    ejecutaQueryExecuteUpdate("Update MovNomBaseAfecta mov Set mov.resultado = :resultado Where mov.id = :id", new String[]{"resultado", "id"}, new Object[]{movNominaBaseAfecta.getResultado(), movNominaBaseAfecta.getId()});
                    if (mensajeResultado.getNoError() == -101) {
                        mensajeResultado.setNoError(54);
                    }
                }
            }
        }
    }

    private void cargarVariablesGlobales(String claveTipoNomina, String claveTipoCorrida, String clavePuesto,
            String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveDepto,
            String claveCtrCosto) {
        try {
            salarioMinimoDF = buscaSalarioPorZona('A').getSalario();
            valoresConceptosGlobales.put("SalarioMinDF".toUpperCase(), salarioMinimoDF);

            valoresConceptosGlobales.put(RazonesSociales.class.getSimpleName().toUpperCase(), claveRazonSocial);
            valoresConceptosGlobales.put(TipoCorrida.class.getSimpleName().toUpperCase(), claveTipoCorrida);
            valoresConceptosGlobales.put(CentroDeCosto.class.getSimpleName().toUpperCase(), claveCtrCosto);
            valoresConceptosGlobales.put("RazonSocial".toUpperCase(), claveRazonSocial);
            valoresConceptosGlobales.put("NumCentroCostos".toUpperCase(), claveCtrCosto);
            valoresConceptosGlobales.put("ClaveTipoCorrida".toUpperCase(), claveTipoCorrida);
            valoresConceptosGlobales.put("NumPuesto".toUpperCase(), clavePuesto);
            valoresConceptosGlobales.put("NumCategoria".toUpperCase(), claveCategoriasPuestos);
            valoresConceptosGlobales.put("NumDepartamento".toUpperCase(), claveDepto);
            valoresConceptosGlobales.put("NumTurno".toUpperCase(), claveTurno == null ? "" : claveTurno);
            valoresConceptosGlobales.put("TipoNomina".toUpperCase(), claveTipoNomina);
            valoresConceptosGlobales.put("NumRegistroPatronal".toUpperCase(), claveRegPatronal);
            tipoCorrida = (TipoCorrida) ejecutaQueryUnico("From TipoCorrida tc Where tc.clave = :clave", new String[]{"clave"}, new Object[]{claveTipoCorrida});
            valoresConceptosGlobales.put("TipoCorridaAlfa".toUpperCase(), tipoCorrida == null ? "" : tipoCorrida.getDescripcion());
            razonesSociales = (RazonesSociales) ejecutaQueryUnico("From RazonesSociales rs Where rs.clave = :clave", new String[]{"clave"}, new Object[]{claveRazonSocial});
            salarioMinimoDF = null;
            configuracionIMSS = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(26);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargarVariablesGlobales()1_Error: ").append(ex));
        }
    }

    private void inicializaValoresPeriodoNomina(PeriodosNomina periodo) {
        valoresConceptosGlobales.put("IdPeriodo".toUpperCase(), periodo == null ? 0L : periodo.getId());
        valoresConceptosGlobales.put("NumPeriodo".toUpperCase(), periodo == null ? "" : periodo.getClave());
        Calendar fecha = Calendar.getInstance();
        fecha.set(1900, Calendar.JANUARY, 1);
        valoresConceptosGlobales.put("FechaInicioPeriodo".toUpperCase(), periodo == null ? fecha.getTime() : periodo.getFechaInicial());
        valoresConceptosGlobales.put("FechaFinalPeriodo".toUpperCase(), periodo == null ? fecha.getTime() : periodo.getFechaFinal());
        valoresConceptosGlobales.put("FechaPago".toUpperCase(), periodo == null ? fecha.getTime() : periodo.getFechaPago());
        GregorianCalendar c = new GregorianCalendar();
        if (periodo != null) {
            c.setTime(periodosNomina.getAcumularAMes());
        }
        valoresConceptosGlobales.put("NumMesAfectar".toUpperCase(), periodo == null ? 0 : c.get(Calendar.MONTH));
        valoresConceptosGlobales.put("DiasNaturalesDelPeriodo".toUpperCase(), periodo == null ? 0 : (cantidadDiasEntreDosFechas(periodo.getFechaInicial(), periodo.getFechaFinal()) + 1));
        valoresConceptosGlobales.put("PeriodicidadEnDias".toUpperCase(), periodo == null ? 0 : Integer.parseInt(periodo.getTipoNomina().getPeriodicidad().getDias().toString()));
        valoresConceptosGlobales.put("AnioPeriodo".toUpperCase(), periodo == null ? c.get(Calendar.YEAR) : periodo.getAño());

        valoresConceptosGlobales.put("TipoPeriodicidadNumerico".toUpperCase(), periodo == null ? "" : periodo.getTipoNomina().getPeriodicidad().getClave());
        valoresConceptosGlobales.put("AnioActualNumerico".toUpperCase(), periodo == null ? "" : periodo.getAño());
        if (periodo != null) {
            Calendar a = Calendar.getInstance();
            a.setTime(periodo.getFechaInicial());
            int mes = a.get(Calendar.MONTH) + 1;
            SimpleDateFormat mesLar = new SimpleDateFormat("MMMMM");
            SimpleDateFormat mesCor = new SimpleDateFormat("MMM");
            String mesNomLar = mesLar.format(a.getTime());
            String mesNomCor = mesCor.format(a.getTime());
            valoresConceptosGlobales.put("DiasMesNumerico".toUpperCase(), mes);
            valoresConceptosGlobales.put("MesAlfanumCompleto".toUpperCase(), mesNomLar);
            valoresConceptosGlobales.put("MesAlfanumCorto".toUpperCase(), mesNomCor);

            Calendar fecActual = Calendar.getInstance();
            fecActual.setTime(periodo.getFechaInicial());
            int anio = fecActual.get(Calendar.YEAR);
            if ((anio % 4 == 0) && ((anio % 100 != 0) || (anio % 400 == 0))) {
                valoresConceptosGlobales.put("DiasPrimerSemestre".toUpperCase(), 182);
            } else {
                valoresConceptosGlobales.put("DiasPrimerSemestre".toUpperCase(), 181);
            }
            valoresConceptosGlobales.put("DiasSegundoSemestre".toUpperCase(), 184);
            //para sacar el numero de semestre
            Calendar fecha1 = Calendar.getInstance();
            Calendar fecha2 = Calendar.getInstance();
            fecha1.set(fecActual.get(Calendar.YEAR), 0, 1);
            fecha2.set(fecActual.get(Calendar.YEAR), 5, 30);
            Date fecAct = quitaHrsDeFecha(fecActual.getTime());
            Date fech1 = quitaHrsDeFecha(fecha1.getTime());
            Date fech2 = quitaHrsDeFecha(fecha2.getTime());
            if ((fecAct.compareTo(fech1) == 0 || fecAct.compareTo(fech1) == 1)
                    && (fecAct.compareTo(fech2) == 0 || fecAct.compareTo(fech2) == -1)) {
                valoresConceptosGlobales.put("NumeroSemestre".toUpperCase(), 1);
            } else {
                valoresConceptosGlobales.put("NumeroSemestre".toUpperCase(), 2);
            }
        }

    }

    private Date quitaHrsDeFecha(Date fecha) {
        Calendar fSinHoras = Calendar.getInstance();
        fSinHoras.setTime(fecha);
        fSinHoras.set(Calendar.HOUR_OF_DAY, 0);
        fSinHoras.set(Calendar.MINUTE, 0);
        fSinHoras.set(Calendar.SECOND, 0);
        fSinHoras.set(Calendar.MILLISECOND, 0);
        return fSinHoras.getTime();
    }

    private void inicializaPeriodo2Meses(PeriodosNomina periodo, Date fechaInicial, Date fechaFinal) {
        valoresConceptosGlobales.put("IdPeriodo".toUpperCase(), periodo == null ? 0L : periodo.getId());
        valoresConceptosGlobales.put("NumPeriodo".toUpperCase(), periodo.getClave());
        valoresConceptosGlobales.put("FechaInicioPeriodo".toUpperCase(), fechaInicial);
        valoresConceptosGlobales.put("FechaFinalPeriodo".toUpperCase(), fechaFinal);
        valoresConceptosGlobales.put("FechaPago".toUpperCase(), periodo.getFechaPago());
        GregorianCalendar c = new GregorianCalendar();
        c.setTime(periodosNomina.getAcumularAMes());
        valoresConceptosGlobales.put("NumMesAfectar".toUpperCase(), c.get(Calendar.MONTH));
        valoresConceptosGlobales.put("DiasNaturalesDelPeriodo".toUpperCase(), (cantidadDiasEntreDosFechas(fechaInicial, fechaFinal) + 1));
        valoresConceptosGlobales.put("PeriodicidadEnDias".toUpperCase(), Integer.parseInt(periodo.getTipoNomina().getPeriodicidad().getDias().toString()));
        valoresConceptosGlobales.put("AnioPeriodo".toUpperCase(), periodo == null ? c.get(Calendar.YEAR) : periodo.getAño());
        valoresConceptosGlobales.put("TipoPeriodicidadNumerico".toUpperCase(), periodo == null ? "" : periodo.getTipoNomina().getPeriodicidad().getClave());
        valoresConceptosGlobales.put("AnioActualNumerico".toUpperCase(), periodo == null ? "" : periodo.getAño());
        Calendar a = Calendar.getInstance();
        a.setTime(periodo.getFechaAsistenciInicial());
        int mes = a.get(Calendar.MONTH) + 1;
        SimpleDateFormat mesLar = new SimpleDateFormat("MMMMM");
        SimpleDateFormat mesCor = new SimpleDateFormat("MMM");
        String mesNomLar = mesLar.format(a.getTime());
        String mesNomCor = mesCor.format(a.getTime());
        valoresConceptosGlobales.put("DiasMesNumerico".toUpperCase(), mes);
        valoresConceptosGlobales.put("MesAlfanumCompleto".toUpperCase(), mesNomLar);
        valoresConceptosGlobales.put("MesAlfanumCorto".toUpperCase(), mesNomCor);
    }

    private void buscaCalculoPTU(String claveRazonsocial, Integer ejercicio, String uuidCxn) {
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            strQuery.delete(0, strQuery.length()).append("Select ptu ");
            strQuery.append(" from PtuDatosGenerales ptu WHERE ptu.razonesSociales.clave = :claveRazonsocial ");
            strQuery.append("AND ptu.ejercicio = :ejercicio  ");
            ptuDatosGenerales = (PtuDatosGenerales) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveRazonsocial", "ejercicio"},
                    new Object[]{claveRazonsocial, ejercicio});
            if (ptuDatosGenerales != null) {
                Date fechaDeCalculo = ptuDatosGenerales.getFechaCalculo(),
                        fechaPeriodoInicial = periodosNomina.getFechaInicial(),
                        fechaPeriodoFinal = periodosNomina.getFechaFinal();
                if ((fechaDeCalculo.compareTo(fechaPeriodoInicial) > 0
                        || fechaDeCalculo.compareTo(fechaPeriodoInicial) == 0)
                        && (fechaDeCalculo.compareTo(fechaPeriodoFinal) < 0
                        || fechaDeCalculo.compareTo(fechaPeriodoFinal) == 0)) {
                    isCalculoPTU = true;
                }
            }
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaCalculoPTU()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        } finally {
            setSession(null);
        }

    }

    private void buscaEmpleadoPTU(String claveRazonsocial, String claveEmpleado) {
        try {
            strQuery.delete(0, strQuery.length()).append("Select ptuEm ");
            strQuery.append(" from PtuEmpleados ptuEm WHERE ptuEm.razonesSociales.clave = :claveRazonsocial ");
            strQuery.append("AND ptuEm.empleados.clave = :claveEmpleado  ");
            ptuEmpleado = (PtuEmpleados) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveRazonsocial", "claveEmpleado"},
                    new Object[]{claveRazonsocial, claveEmpleado});
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaEmpleadoPTU()1_Error: ").append(ex));
        }
    }

    private void buscaPeriodicidadesOrPeriodosNomina(String claveTipoNomina, String claveTipoCorrida, Long idPeriodoNomina, Calendar cFin, String uuidCxn) {
        try {
            Throwable t = new Throwable();//JEVC03 para saber de que metodo se mando a llamar y tomar evaluaciones
            StackTraceElement[] elements = t.getStackTrace();
            String callMethod = elements[1].getMethodName();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
////////            if (periodicidadAnual == null) {
////////                periodicidadAnual = (Periodicidad) ejecutaQueryUnico("FROM Periodicidad p WHERE p.dias = :dias", new String[]{"dias"}, new Object[]{365L});
////////            }
            claveTipoNomina = claveTipoNomina == null ? "" : claveTipoNomina;
            if (claveTipoNomina.trim().length() > 0 | periodicidadTipoNomina == null) {
                periodicidadTipoNomina = (Periodicidad) ejecutaQueryUnico("Select t.periodicidad from TipoNomina t Where t.clave = :clave", new String[]{"clave"}, new Object[]{claveTipoNomina});
            } else {
            }
            if (periodosNomina == null) {
                if (idPeriodoNomina == null && callMethod.equals("calculaNomina")) {
                    strQuery.delete(0, strQuery.length()).append("Select p ");
                    strQuery.append(" from PeriodosNomina p inner join p.tipoNomina  t ");
                    strQuery.append(" Where (:fecha BETWEEN p.fechaInicial AND p.fechaFinal + 1) ");
                    strQuery.append(" and t.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida");
                    periodosNomina = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveTipoNomina", "fecha", "claveTipoCorrida"},
                            new Object[]{claveTipoNomina, fechaActual.getTime(), claveTipoCorrida});
                } else if (isCalculoSDI) {
                    strQuery.delete(0, strQuery.length()).append("Select p ");
                    strQuery.append(" from PeriodosNomina p inner join p.tipoNomina  t ");
                    strQuery.append(" Where (:fecha BETWEEN p.fechaInicial AND p.fechaFinal + 1) ");
                    strQuery.append(" and t.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida");
                    periodosNomina = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveTipoNomina", "fecha", "claveTipoCorrida"},
                            new Object[]{claveTipoNomina, fechaActual.getTime(), claveTipoCorrida});
                } else {
                    strQuery.delete(0, strQuery.length()).append("Select p ");
                    strQuery.append(" from PeriodosNomina p inner join p.tipoNomina  t ");
                    strQuery.append(" Where p.id = :idPeriodoNomina ");
                    periodosNomina = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), new String[]{"idPeriodoNomina"}, new Object[]{idPeriodoNomina});
                }
            }
            if (periodosNomina == null && !callMethod.equals("calculaSalarioDiarioIntegerado")) {
                mensajeResultado.setNoError(-157);
                mensajeResultado.setError("Favor de verificar que existen periodos de nomina ya que no se encontraron");
            }
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaPeriodicidadPorTipoNomina()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        } finally {
            setSession(null);
        }
    }

    private void cargarVariablesGlobalesEmpleado(Empleados empleado) {
        try {
            valoresConceptosEmpleados.put("NumEmpleado".toUpperCase(), empleado.getClave());
            valoresConceptosEmpleados.put("Estatus".toUpperCase(), empleado.isStatus());
            Calendar fechaNac = Calendar.getInstance();
            fechaNac.setTime(empleado.getFechaNacimiento());
            valoresConceptosEmpleados.put("FechaNacimiento".toUpperCase(), fechaNac);
            valoresConceptosEmpleados.put("Edad".toUpperCase(), calcularEdad(fechaNac.getTime()));
            SimpleDateFormat formato = new SimpleDateFormat("dd/MMM");
            valoresConceptosEmpleados.put("Cumple".toUpperCase(), formato.format(fechaNac.getTime()).toUpperCase());
            valoresConceptosEmpleados.put("MesNacimiento".toUpperCase(), fechaNac.get(Calendar.MONTH));
            valoresConceptosEmpleados.put("DiaNacimiento".toUpperCase(), fechaNac.get(Calendar.DATE));

        } catch (HibernateException ex) {
            mensajeResultado.setNoError(28);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargarVariablesGlobalesEmpleado()1_Error: ").append(ex));
        }
    }

    private int cantidadDiasEntreDosFechas(Date fechaInicio, Date fechaFin) {
        if (fechaFin.compareTo(fechaInicio) != 0) {
            Calendar cResultado = Calendar.getInstance();
            Calendar cInicio = Calendar.getInstance(), cFin = Calendar.getInstance();
            cInicio.setTime(fechaInicio);
            cFin.setTime(fechaFin);
            cResultado.setTimeInMillis(cFin.getTime().getTime() - cInicio.getTime().getTime());
            return cResultado.get(Calendar.DAY_OF_YEAR);
        } else {
            return 0;
        }
//////        long fechaInicialMs = fechaInicio.getTime();
//////        long fechaFinalMs = fechaFin.getTime();
//////        long diferencia = fechaFinalMs - fechaInicialMs;
//////        double dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
//////        return (int) dias;
    }

    //Usando PlazasPorEmpleado
    private void cargarVariablesGlobalesEmpleadoPorPlaza(PlazasPorEmpleadosMov plazasPorEmpleadosMov, boolean factor, boolean zonaSalario, TipoSueldos tipoSueldos, CalculoUnidades calculoUnidades, Boolean modificarDiasTrabajados, Boolean modificarDiasCotizacion, Calendar fechaActualCalculoSDI) {//JSA30
        try {

            if (!isCalculoSDI) {
                cargarVariablesGlobalesEmpleado(plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados());
            } else {
                valoresConceptosEmpleados.put("NumEmpleado".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave());
                valoresConceptosEmpleados.put("Estatus".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().isStatus());
            }
            valoresConceptosEmpleados.put("PlazaEmpleado".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getClave());
            if (!isCalculoSDI) {
                valoresConceptosEmpleados.put("Antiguedad".toUpperCase(), calcularAntiguedadExacta(plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.ANTIGUEDADENTERO));
                valoresConceptosEmpleados.put("AntiguedadExacta".toUpperCase(), calcularAntiguedadExacta(plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.ANTIGUEDADEXACTA));//JSA12
                valoresConceptosEmpleados.put("PorcionAntiguedad".toUpperCase(), calcularAntiguedadExacta(plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.PORCIONANTIGUEDAD));//JSA12
                valoresConceptosEmpleados.put("PorcionDias".toUpperCase(), calcularAntiguedadExacta(plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.PORCIONDIAS));//JSA12
            }
            if (plazasPorEmpleadosMov.getDepartamentos() == null) {
                valoresConceptosEmpleados.put("NumDepartamento".toUpperCase(), "");
                valoresConceptosEmpleados.put("NumSubcuenta".toUpperCase(), "");
                valoresConceptosEmpleados.put("DepartamentoEmpleadoAlfa".toUpperCase(), "");
            } else {
                valoresConceptosEmpleados.put("NumDepartamento".toUpperCase(), plazasPorEmpleadosMov.getDepartamentos().getClave());
                valoresConceptosEmpleados.put("NumSubcuenta".toUpperCase(), plazasPorEmpleadosMov.getDepartamentos().getSubCuenta());
                valoresConceptosEmpleados.put("DepartamentoEmpleadoAlfa".toUpperCase(), plazasPorEmpleadosMov.getDepartamentos().getDescripcion());
            }
            if (plazasPorEmpleadosMov.getTurnos() == null) {
                valoresConceptosEmpleados.put("NumTurno".toUpperCase(), "");
                valoresConceptosEmpleados.put("TipoTurno".toUpperCase(), -1);
                valoresConceptosEmpleados.put("HrsTurno".toUpperCase(), 0);
                valoresConceptosEmpleados.put("DiasJornada".toUpperCase(), 0);
            } else {
                valoresConceptosEmpleados.put("NumTurno".toUpperCase(), plazasPorEmpleadosMov.getTurnos().getClave());
                valoresConceptosEmpleados.put("TipoTurno".toUpperCase(), plazasPorEmpleadosMov.getTurnos().getTipoDeTurno());
                valoresConceptosEmpleados.put("HrsTurno".toUpperCase(), plazasPorEmpleadosMov.getTurnos().getHoraJornada());
                valoresConceptosEmpleados.put("DiasJornada".toUpperCase(), plazasPorEmpleadosMov.getTurnos().getDiasJornada() == null ? 0 : plazasPorEmpleadosMov.getTurnos().getDiasJornada());
            }

            valoresConceptosEmpleados.put("FormaPago".toUpperCase(), plazasPorEmpleadosMov.getFormasDePago() == null ? "" : plazasPorEmpleadosMov.getFormasDePago().getClave());
            if (plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal() != null) {//JDRA01
                valoresConceptosEmpleados.put("NumDelegacion".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getDelegacion());
                valoresConceptosEmpleados.put("NumSubdelegacion".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getSubdelegacion());

            }
            if (factor) {
                FactorIntegracion factorIntegracion = buscaFactorIntegracion(plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaPrestaciones());
                if (factorIntegracion != null) {
                    valoresConceptosEmpleados.put("AntiguedadEntero".toUpperCase(), factorIntegracion.getAntiguedad());//JSA05
                    valoresConceptosEmpleados.put("FactorDiasAguinaldo".toUpperCase(), factorIntegracion.getDiasAguinaldo());//JSA05
                    valoresConceptosEmpleados.put("FactorIntegracion".toUpperCase(), factorIntegracion.getFactor());
                    valoresConceptosEmpleados.put("FactorDiasVacaciones".toUpperCase(), factorIntegracion.getDiasVacaciones());//JSA05
                    valoresConceptosEmpleados.put("FactorPrimaVacacional".toUpperCase(), factorIntegracion.getPrimaVacacional());//JSA05
                    valoresConceptosEmpleados.put("DiasVacacionesTotales".toUpperCase(), factorIntegracion.getDiasVacacionesTotales());//JSA05
                } else {
                    mensajeResultado.setNoError(25);
                    mensajeResultado.setError("No existen el factor de integracion capturado, favor de verificarlo");
                }
            }
            int diasAguinaldo = 0, diasTrabajados, factorDiasAguinaldo = 0;
            double diasAguinaldoExacta, porcionAguinaldo = 0.0;
            if (valoresConceptosEmpleados.get("FactorDiasAguinaldo".toUpperCase()) != null) {
                factorDiasAguinaldo = Integer.valueOf(valoresConceptosEmpleados.get("FactorDiasAguinaldo".toUpperCase()).toString());
            }
            diasTrabajados = Integer.parseInt(calcularAntiguedadExacta(plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.PORCIONDIAS).toString());
            diasAguinaldoExacta = factorDiasAguinaldo * diasTrabajados / 365.0;
            diasAguinaldo = (int) diasAguinaldoExacta;

            porcionAguinaldo = diasAguinaldoExacta - diasAguinaldo;
            valoresConceptosEmpleados.put("DiasAguinaldo".toUpperCase(), diasAguinaldo);
            valoresConceptosEmpleados.put("PorcionAguinaldo".toUpperCase(), porcionAguinaldo);
            String claveRazonSocial = valoresConceptosEmpleados.get("RazonSocial".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("RazonSocial".toUpperCase()).toString();
            if (claveRazonSocial.isEmpty()) {
                valoresConceptosEmpleados.put("RazonSocial".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());
                razonesSociales = razonesSociales = (RazonesSociales) ejecutaQueryUnico("From RazonesSociales rs Where rs.clave = :clave", new String[]{"clave"}, new Object[]{claveRazonSocial});
            }
            centroDeCostoMovimiento = plazasPorEmpleadosMov.getCentroDeCosto();
            cargaDatosSalarioDiario(plazasPorEmpleadosMov, tipoSueldos);
            valoresConceptosEmpleados.put("FechaAlta".toUpperCase(), ingresosReingresosBajas == null ? plazasPorEmpleadosMov.getFechaInicial() : ingresosReingresosBajas.getFechaIngreso());
            valoresConceptosEmpleados.put("FechaBaja".toUpperCase(), fechaBajaFiniq == null ? plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal() : fechaBajaFiniq.getTime());//JSA12
            valoresConceptosEmpleados.put("FechaAltaIMSS".toUpperCase(), obtenerPrimerPlazasPorEmpleadosMov(plazasPorEmpleadosMov));
            if (plazasPorEmpleadosMov.getZonaGeografica() != null) {
                String emplZonaGeog = plazasPorEmpleadosMov.getZonaGeografica() == ZonaGeografica.ZonaGeograficaA ? "A" : "B";//JSA04
                valoresConceptosEmpleados.put("AreaGeografica".toUpperCase(), emplZonaGeog);
                valoresConceptosEmpleados.put("NumZonaSalario".toUpperCase(), emplZonaGeog);
                if (zonaSalario) {
                    valoresConceptosEmpleados.put("SalarioMin".toUpperCase(), buscaSalarioPorZona(emplZonaGeog.charAt(0)).getSalario());
                }
            } else {
                valoresConceptosEmpleados.put("SalarioMin".toUpperCase(), 0.0);
                valoresConceptosEmpleados.put("AreaGeografica".toUpperCase(), "");
                valoresConceptosEmpleados.put("NumZonaSalario".toUpperCase(), "");
            }
            if (plazasPorEmpleadosMov.getCentroDeCosto() != null) {
                valoresConceptosEmpleados.put("NumCentroCostos".toUpperCase(), plazasPorEmpleadosMov.getCentroDeCosto().getClave());
                valoresConceptosEmpleados.put("CentroCostoAlfa".toUpperCase(), plazasPorEmpleadosMov.getCentroDeCosto().getDescripcion());
            } else {
                valoresConceptosEmpleados.put("NumCentroCostos".toUpperCase(), "");
                valoresConceptosEmpleados.put("CentroCostoAlfa".toUpperCase(), "");
            }
            if (plazasPorEmpleadosMov.getPuestos() != null) {
                valoresConceptosEmpleados.put("NumPuesto".toUpperCase(), plazasPorEmpleadosMov.getPuestos().getClave());
                valoresConceptosEmpleados.put("SueldoPuesto".toUpperCase(), plazasPorEmpleadosMov.getPuestos().getSalarioTabular());
                valoresConceptosEmpleados.put("TopeSalarial".toUpperCase(), plazasPorEmpleadosMov.getPuestos().getMaximo());
                if (plazasPorEmpleadosMov.getPuestos().getCategoriasPuestos() != null) {
                    valoresConceptosEmpleados.put("NumCategoria".toUpperCase(), plazasPorEmpleadosMov.getPuestos().getCategoriasPuestos().getClave());
                    valoresConceptosEmpleados.put("CategoriaEmpleadoAlfanum".toUpperCase(), plazasPorEmpleadosMov.getPuestos().getCategoriasPuestos().getDescripcion());
                }
            } else {
                valoresConceptosEmpleados.put("NumPuesto".toUpperCase(), "");
                valoresConceptosEmpleados.put("SueldoPuesto".toUpperCase(), 0.0);
                valoresConceptosEmpleados.put("TopeSalarial".toUpperCase(), 0.0);
                valoresConceptosEmpleados.put("NumCategoria".toUpperCase(), "");
                valoresConceptosEmpleados.put("CategoriaEmpleadoAlfanum".toUpperCase(), "");
            }
            //JDRA01
            if (plazasPorEmpleadosMov.getTipoNomina() != null) {
                valoresConceptosEmpleados.put("TipoNomina".toUpperCase(), plazasPorEmpleadosMov.getTipoNomina().getClave());
                valoresConceptosEmpleados.put("TipoNominaEntidad".toUpperCase(), plazasPorEmpleadosMov.getTipoNomina());
                valoresConceptosEmpleados.put("TipoNominaAlfa".toUpperCase(), plazasPorEmpleadosMov.getTipoNomina().getDescripcion());
            }
            if (plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal() != null) {
                valoresConceptosEmpleados.put("NumRegistroPatronal".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave());
            }
            strQuery = new StringBuilder("select s FROM ").append(SalariosIntegrados.class.getSimpleName()).append(" s ");
            strQuery.append(" inner join s.empleados e ");
            strQuery.append(" WHERE s.fecha = ");
            strQuery.append(" ( select max(sdi.fecha) FROM ").append(SalariosIntegrados.class.getSimpleName()).append(" sdi ");
            strQuery.append(" WHERE sdi.empleados.id = e.id AND sdi.registroPatronal.id = s.registroPatronal.id AND sdi.fecha <= :fecha ");
            if (tipoCorrida != null) {
                if (tipoCorrida.getClave().equalsIgnoreCase("FIN")) {
                    strQuery.append("  and sdi.fecha not in  ");
                    strQuery.append(" ( select max(sdix.fecha) FROM SalariosIntegrados sdix ");
                    strQuery.append(" WHERE sdix.empleados.id = e.id AND sdix.registroPatronal.id = s.registroPatronal.id) ");
                }
            }

            strQuery.append(" ) and e.clave = :claveEmpleado AND s.registroPatronal.clave = :claveRegPatronal  and e.razonesSociales.clave = :claveRazonesSociales ");
            Date fecha = periodosNomina == null ? null : periodosNomina.getFechaFinal();
            if (isCalculoSDI) {
                if (periodosNomina == null) {
                    fecha = fechaActualCalculoSDI.getTime();
                }
            }

            SalariosIntegrados salariosIntegrados = (SalariosIntegrados) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveEmpleado", "claveRegPatronal", "claveRazonesSociales", "fecha"},
                    new Object[]{plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal() == null ? "" : plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave(), fecha});//JDRA01
            if (salariosIntegrados == null) {
                valoresConceptosEmpleados.put("SueldoIntIMSS".toUpperCase(), 0.0);
            } else {
                valoresConceptosEmpleados.put("SueldoIntIMSS".toUpperCase(), salariosIntegrados.getSalarioDiarioIntegrado());
                valoresConceptosEmpleados.put("FechaSDI".toUpperCase(), salariosIntegrados.getFecha());
            }

            Date fechaIngreso = quitaHrsDeFecha(plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getFechaIngresoEmpresa());
            if (periodosNomina == null) {
                valoresConceptosEmpleados.put("DiasPeriodoEmpleado".toUpperCase(), 0);
            } else {
                Date fechaInicioPer = quitaHrsDeFecha(periodosNomina.getFechaInicial());
                Date fechaFinalPer = quitaHrsDeFecha(periodosNomina.getFechaFinal());
                if (fechaIngreso.compareTo(fechaInicioPer) == 1) {

                    Long diferencia = fechaFinalPer.getTime() - fechaIngreso.getTime();
                    double diasdelPer = Math.floor(diferencia / (1000 * 60 * 60 * 24));
                    int diasPer = (int) diasdelPer + 1;
                    valoresConceptosEmpleados.put("DiasPeriodoEmpleado".toUpperCase(), diasPer);
                }
            }
            valoresConceptosEmpleados.put("TipoRelacionLabEmpleado".toUpperCase(), plazasPorEmpleadosMov.getTipoRelacionLaboral());
            valoresConceptosEmpleados.put("TipoContratoEmpleado".toUpperCase(), plazasPorEmpleadosMov.getTipoContrato().getDescripcion());
            valoresConceptosEmpleados.put("RegimenContratacion".toUpperCase(), plazasPorEmpleadosMov.getRegimenContratacion());

            int tipoSalario = plazasPorEmpleadosMov.getPlazas() == null ? 0 : plazasPorEmpleadosMov.getPlazas().getTipoSalario();
            if (tipoSalario == 1) {
                valoresConceptosEmpleados.put("TipoSalarioAlfa".toUpperCase(), "Fijo");
            } else if (tipoSalario == 2) {
                valoresConceptosEmpleados.put("TipoSalarioAlfa".toUpperCase(), "Variable");
            } else if (tipoSalario == 3) {
                valoresConceptosEmpleados.put("TipoSalarioAlfa".toUpperCase(), "Mixto");
            }

            valoresConceptosEmpleados.put("FormaPagoEmpleadoAlfa".toUpperCase(), plazasPorEmpleadosMov.getFormasDePago() == null ? "" : plazasPorEmpleadosMov.getFormasDePago().getDescripcion());
            boolean sindicalizacion = plazasPorEmpleadosMov.getTipoContrato().getEsSindicalizado();
            if (sindicalizacion) {
                valoresConceptosEmpleados.put("TipoSindicalizadoAlfa".toUpperCase(), "Sindicalizado");
            } else {
                valoresConceptosEmpleados.put("TipoSindicalizadoAlfa".toUpperCase(), "No Sindicalizado");
            }
            int estadocivil = plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getEstadoCivil();
            if (estadocivil == 1) {
                valoresConceptosEmpleados.put("EstadoCivilAlfa".toUpperCase(), "Casado");
            } else if (estadocivil == 2) {
                valoresConceptosEmpleados.put("EstadoCivilAlfa".toUpperCase(), "Soltero");
            } else if (estadocivil == 3) {
                valoresConceptosEmpleados.put("EstadoCivilAlfa".toUpperCase(), "Divorciado");
            } else if (estadocivil == 4) {
                valoresConceptosEmpleados.put("EstadoCivilAlfa".toUpperCase(), "Viudo");
            } else if (estadocivil == 5) {
                valoresConceptosEmpleados.put("EstadoCivilAlfa".toUpperCase(), "UnionLibre");
            } else if (estadocivil == 6) {
                valoresConceptosEmpleados.put("EstadoCivilAlfa".toUpperCase(), "Separado");
            }

            valoresConceptosEmpleados.put("FechaPrestaciones".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaPrestaciones());

            strQuery = new StringBuilder("SELECT  ce.modoDescuentoCredito  FROM ").append(CreditoPorEmpleado.class.getSimpleName()).append(" ce ");
            strQuery.append("WHERE ce.empleados.clave=:claveEmpleado and ce.razonesSociales.clave=:claveRazonSocial and ce.creditoAhorro.clave='005'");
            Integer modoDescuentoInfonavit = (Integer) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveEmpleado", "claveRazonSocial"},
                    new Object[]{plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave()});
            if (modoDescuentoInfonavit == null) {
                valoresConceptosEmpleados.put("ModoDescuentoInfonavitAlfa".toUpperCase(), "");
            } else if (modoDescuentoInfonavit == 0) {
                valoresConceptosEmpleados.put("ModoDescuentoInfonavitAlfa".toUpperCase(), "Importe Fijo");
            } else if (modoDescuentoInfonavit == 1) {
                valoresConceptosEmpleados.put("ModoDescuentoInfonavitAlfa".toUpperCase(), "VSM");
            } else if (modoDescuentoInfonavit == 2) {
                valoresConceptosEmpleados.put("ModoDescuentoInfonavitAlfa".toUpperCase(), "Porcentaje");
            }

            strQuery = new StringBuilder("select count(*) FROM ").append(PlazasPorEmpleado.class.getSimpleName()).append(" p ");
            strQuery.append("where p.empleados.clave=:claveEmpleado and p.razonesSociales.clave=:claveRazon");
            Long numPlazas = (Long) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveEmpleado", "claveRazon"},
                    new Object[]{plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave()});
            if (numPlazas == null) {
                valoresConceptosEmpleados.put("NumeroPlazasEmpleado".toUpperCase(), 0);
            } else {
                valoresConceptosEmpleados.put("NumeroPlazasEmpleado".toUpperCase(), numPlazas);
            }
            int cont = 0;
            for (int i = 0; i < plazasPorEmpleadosMov.getTurnos().getListTurnosHorariosFijos().size(); i++) {
                if (plazasPorEmpleadosMov.getTurnos().getListTurnosHorariosFijos().get(i).getStatusDia() == 1) {
                    cont++;
                    if (cont == 1) {
                        valoresConceptosEmpleados.put("DiaDescanso1".toUpperCase(), plazasPorEmpleadosMov.getTurnos().getListTurnosHorariosFijos().get(i).getDiaSemana());
                    } else if (cont == 2) {
                        valoresConceptosEmpleados.put("DiaDescanso2".toUpperCase(), plazasPorEmpleadosMov.getTurnos().getListTurnosHorariosFijos().get(i).getDiaSemana());
                        break;
                    }
                }
            }
            valoresConceptosEmpleados.put("FechaIngresoEmpresa".toUpperCase(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getFechaIngresoEmpresa());

            strQuery = new StringBuilder("SELECT pm.fechaInicial FROM ").append(PlazasPorEmpleadosMov.class.getSimpleName()).append(" pm ");
            strQuery.append("WHERE pm.plazasPorEmpleado.empleados.clave=:claveEmpleado ");
            strQuery.append("and pm.plazasPorEmpleado.razonesSociales.clave=:claveRazonSocial ");
            strQuery.append("and pm.cambioSalarioPor=1  and pm.fechaInicial BETWEEN :fechaInicial and :fechaFinal ");
            strQuery.append("order by pm.fechaInicial desc");
            if (periodosNomina == null) {
                valoresConceptosEmpleados.put("FechaUltimoCambioSueldo".toUpperCase(), "");
            } else {
                Object fechaUltimoCambisueldo = ejecutaQueryUnico(strQuery.toString(),
                        new String[]{"claveEmpleado", "claveRazonSocial", "fechaInicial", "fechaFinal"},
                        new Object[]{plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(),
                            plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave(),
                            periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()});
                if (fechaUltimoCambisueldo != null) {
                    valoresConceptosEmpleados.put("FechaUltimoCambioSueldo".toUpperCase(), fechaUltimoCambisueldo);
                }
            }

            cargaValoresDiasPago(plazasPorEmpleadosMov, true, null, calculoUnidades, false, modificarDiasTrabajados);//JSA30
            cargaValoresDiasCotizados(plazasPorEmpleadosMov.getFechaIMSS(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), true, null, calculoUnidades, false, modificarDiasCotizacion);//JSA30
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(28);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargarVariablesGlobalesEmpleadoPorPlaza()1_Error: ").append(ex));
        }
    }

    private void cargaDatosSalarioDiario(PlazasPorEmpleadosMov plazasPorEmpleadosMov, TipoSueldos tipoSueldos) {
        if (plazasPorEmpleadosMov.getSalarioPor() == 2) {
            valoresConceptosEmpleados.put("SUELDODIARIO", plazasPorEmpleadosMov.getImporte());
        } else {
            valoresConceptosEmpleados.put("SUELDODIARIO", plazasPorEmpleadosMov.getPuestos().getSalarioTabular());
        }
        if (plazasPorEmpleadosMov.getPuestos() != null) {
            if (plazasPorEmpleadosMov.getPuestos().getCategoriasPuestos() != null) {
                if (plazasPorEmpleadosMov.getPuestos().getCategoriasPuestos().getPagarPorHoras()) {
                    Float sueldo = (Float) valoresConceptosEmpleados.get("SUELDODIARIO");
                    sueldo = (sueldo * plazasPorEmpleadosMov.getHoras()) / periodosNomina.getDiasPago();
                    valoresConceptosEmpleados.put("SUELDODIARIO", sueldo);
                }
            }
        }
        //if (tipoSueldos == TipoSueldos.SUELDODIARIOFINAL || tipoSueldos == TipoSueldos.PERCEP_PLAZA) {
        valoresConceptosEmpleados.put("SUELDODIARIOFINAL", (Float) valoresConceptosEmpleados.get("SUELDODIARIO"));
        valoresConceptosEmpleados.put("percep_plaza".toUpperCase(), (Float) valoresConceptosEmpleados.get("SUELDODIARIO"));
        //} else if (tipoSueldos == TipoSueldos.SUELDODIARIOINICIAL) {
        valoresConceptosEmpleados.put("SUELDODIARIOINICIAL", (Float) valoresConceptosEmpleados.get("SUELDODIARIO"));
        //} else if (tipoSueldos == TipoSueldos.SUELDODIARIOVIGENTE) {
        valoresConceptosEmpleados.put("SUELDODIARIOVIGENTE", (Float) valoresConceptosEmpleados.get("SUELDODIARIO"));
        //} else if (tipoSueldos == TipoSueldos.PERCEP_PLAZA_VIGENTE) {
        valoresConceptosEmpleados.put("percep_plaza_vigente".toUpperCase(), (Float) valoresConceptosEmpleados.get("SUELDODIARIO"));
        //}
        strQuery = new StringBuilder("SELECT pm.importe FROM ").append(PlazasPorEmpleadosMov.class.getSimpleName()).append(" pm ");
        strQuery.append("WHERE pm.plazasPorEmpleado.empleados.clave=:claveEmpleado ");
        strQuery.append("and pm.plazasPorEmpleado.razonesSociales.clave=:claveRazonSocial ");
        strQuery.append("and pm.cambioSalarioPor=1  and pm.fechaInicial BETWEEN :fechaInicial and :fechaFinal ");
        strQuery.append("order by pm.fechaInicial desc");

        if (periodosNomina == null) {
            valoresConceptosEmpleados.put("SUELDODIARIOFINAL".toUpperCase(), 0.0);
        } else {
            Object salariofinal = ejecutaQueryUnico(strQuery.toString(),
                    new String[]{"claveEmpleado", "claveRazonSocial", "fechaInicial", "fechaFinal"},
                    new Object[]{plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(),
                        plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave(),
                        periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()});
            if (salariofinal != null) {
                if (salariofinal.getClass().equals(Double.class)) {
                    Double valor = (Double) salariofinal;
                    valoresConceptosEmpleados.put("SUELDODIARIOFINAL".toUpperCase(), valor.floatValue());
                } else {
                    valoresConceptosEmpleados.put("SUELDODIARIOFINAL".toUpperCase(), salariofinal);
                }
            }
        }
        strQuery = new StringBuilder("SELECT pm.importe FROM ").append(PlazasPorEmpleadosMov.class.getSimpleName()).append(" pm ");
        strQuery.append("WHERE pm.plazasPorEmpleado.empleados.clave=:claveEmpleado ");
        strQuery.append("and pm.plazasPorEmpleado.razonesSociales.clave=:claveRazonSocial ");
        strQuery.append("and pm.plazasPorEmpleado.plazaPrincipal=1 and pm.fechaInicial < :fechaMov ");
        strQuery.append("order by pm.fechaInicial desc");

        Object sueldoAnteriorObj = ejecutaQueryUnico(strQuery.toString(),
                new String[]{"claveEmpleado", "claveRazonSocial", "fechaMov"},
                new Object[]{plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(),
                    plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave(),
                    plazasPorEmpleadosMov.getFechaInicial()});

        if (sueldoAnteriorObj != null) {
            System.out.println(sueldoAnteriorObj.getClass());
            if (sueldoAnteriorObj.getClass().equals(Double.class)) {
                Double valor = (Double) sueldoAnteriorObj;
                valoresConceptosEmpleados.put("SueldoAnterior".toUpperCase(), valor.floatValue());
            } else {
                valoresConceptosEmpleados.put("SueldoAnterior".toUpperCase(), sueldoAnteriorObj);
            }
        }

    }

    private void cargaDatosVariableConfiguracionIMSS(Date fechaPeriodoFinal) {
        try {
            if (valoresConceptosGlobales == null) {
                valoresConceptosGlobales = new HashMap();
            }
            // * ***********************************IMSS******************************************************************
            configuracionIMSS = (ConfiguracionIMSS) ejecutaQueryUnico("from ConfiguracionIMSS where fechaAplica = ( select max(fechaAplica) from ConfiguracionIMSS where fechaAplica <= :fecha ) ", new String[]{"fecha"}, new Object[]{fechaPeriodoFinal});//JSA01
            if (mensajeResultado.getNoError() == -100 || configuracionIMSS == null) {
                configuracionIMSS = (ConfiguracionIMSS) ejecutaQueryUnico("from ConfiguracionIMSS where fechaAplica = ( select min(fechaAplica) from ConfiguracionIMSS where fechaAplica >= :fecha ) ", new String[]{"fecha"}, new Object[]{fechaPeriodoFinal});
            }
            valoresConceptosGlobales.put("TasaExcedenteEmp".toUpperCase(), configuracionIMSS.getTasaEspecieEnfermeMater());
            valoresConceptosGlobales.put("TasaGtosPensEmp".toUpperCase(), configuracionIMSS.getTasaGastosPension());
            valoresConceptosGlobales.put("TasaPrestDinEmp".toUpperCase(), configuracionIMSS.getTasaDineEnfermeMater());
            valoresConceptosGlobales.put("TasaInvYVidaEmp".toUpperCase(), configuracionIMSS.getTasaInvalidezVida());
            valoresConceptosGlobales.put("TasaCesYVejezEmp".toUpperCase(), configuracionIMSS.getTasaCesantiaVejez());

            valoresConceptosGlobales.put("TasaInfonavit".toUpperCase(), configuracionIMSS.getTasaAportacionInfonavitPatron());
            valoresConceptosGlobales.put("TasaPrestDinPat".toUpperCase(), configuracionIMSS.getTasaPrestDinePatron());
            valoresConceptosGlobales.put("TasaCesYVejezPat".toUpperCase(), configuracionIMSS.getTasaCesanVejezPatron());
            valoresConceptosGlobales.put("TasaFijaPatron".toUpperCase(), configuracionIMSS.getTasaFijaPatron());
            valoresConceptosGlobales.put("TasaExcedentePat".toUpperCase(), configuracionIMSS.getTasaExcedentePatron());
            valoresConceptosGlobales.put("TasaGuarderiasPat".toUpperCase(), configuracionIMSS.getTasaGuarderiaPatron());
            valoresConceptosGlobales.put("TasaInvYVidaPat".toUpperCase(), configuracionIMSS.getTasaInvaliVidaPatron());
            valoresConceptosGlobales.put("TasaGtosPensPat".toUpperCase(), configuracionIMSS.getTasaGastosPensPatron());
            valoresConceptosGlobales.put("TasaRetiro".toUpperCase(), configuracionIMSS.getTasaAportacionRetiroPatron());
            valoresConceptosGlobales.put("TasaRiesgoTrabajoPat".toUpperCase(), configuracionIMSS.getTasaRiesgosPatron());

            valoresConceptosGlobales.put("TopeEnfermedadYMat".toUpperCase(), configuracionIMSS.getTopeEnfermedadMaternidad());
            valoresConceptosGlobales.put("TopeRiesgoTrabGuarderia".toUpperCase(), configuracionIMSS.getTopeRiesgoTrabajoGuarderias());
            valoresConceptosGlobales.put("TopeInvalidezYVida".toUpperCase(), configuracionIMSS.getTopeCesanVejezInvaliVida());
            valoresConceptosGlobales.put("TopeRetiro".toUpperCase(), configuracionIMSS.getTopeRetiro());
            valoresConceptosGlobales.put("TopeCuotaInfonavit".toUpperCase(), configuracionIMSS.getTopeInfonavit());
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(28);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargaDatosVariableConfiguracionIMSS()1_Error: ").append(ex));
        }
    }

    private String buscaTipoControladorTablaBase(String claveTablaBase, List<TablaBase> tablasBaseSistema) {
        for (TablaBase tablaBase : tablasBaseSistema) {
            if (claveTablaBase.equalsIgnoreCase(tablaBase.getClave())) {
                return tablaBase.getControladores();
            }
        }
        return "";
    }

    private List<TipoControlador> getTipoControlador(String[] valorTipoControlador) {
        List<TipoControlador> tipoControladores = new ArrayList<TipoControlador>();
        for (String tipo : valorTipoControlador) {
            tipoControladores.add(TipoControlador.getEnum(tipo));
        }
        return tipoControladores;
    }

    private List<TipoControlador> obtieneTipoContoladorTablaBase(String claveTablaBase, List<TablaBase> tablasBaseSistema) {
        String valorTipoControladores = buscaTipoControladorTablaBase(claveTablaBase, tablasBaseSistema);
        List<TipoControlador> tipoControladores = null;
        if (valorTipoControladores.length() > 0) {
            tipoControladores = getTipoControlador(valorTipoControladores.split(","));
        } else {
            mensajeResultado.setNoError(1000);
            mensajeResultado.setResultado(0);
        }
        return tipoControladores;
    }

    public List<TablaBase> getCargarTablaBaseSistema() {//JSA03
        List<TablaBase> listTablaBase = null;
        try {
            q = getSession().createQuery("SELECT new TablaBase(tb.clave, tt.nombre, tb.controladores) FROM TablaBase tb INNER JOIN tb.tipoTabla tt WHERE tt.sistema = true");
            listTablaBase = (List<TablaBase>) q.list();
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(99);
            mensajeResultado.setResultado(0);
        }
        return listTablaBase;
    }

    //Generera extructura xml
    private void generaTablasXml(String controlador, Periodicidad periodicidadTipoNomina, String claveRazonSocial, Date fechaFinal, Integer ejercicio, String uuidCxnMaestra) {
        try {
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            obtenerFactores(claveRazonSocial);
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            List<TablaBase> tablasBaseSistema = getCargarTablaBaseSistema();
            List<TipoControlador> tipoControladores;

            if (tipoTablaISR == TipoTablaISR.PERIODICIDAD) {
                if (periodicidadTipoNomina != null) {
                    String controladorPeriodicidad = Periodicidad.class.getSimpleName().concat(periodicidadTipoNomina.getClave()).concat("%");
                    tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaISRPeriodicidad.toString(), tablasBaseSistema);

                    if (mensajeResultado.getNoError() != 0) {
                        mensajeResultado.setError("no encontro controladores en la tabla ISR por periodicidad");
                        return;
                    }
                    tablaIsr = construyeTablaXmlPorTipoNomina(ClavesParametrosModulos.claveTipoTablaISRPeriodicidad.toString(), controladorPeriodicidad, tipoControladores, fechaFinal, ejercicio);

                    if (mensajeResultado.getNoError() == -10) {
                        mensajeResultado.setNoError(11);
                    }
                    if (tablaIsr == null & mensajeResultado.getNoError() == 0) {
                        mensajeResultado.setError("no encontro datos de la tabla ISR por periodicidad");
                        mensajeResultado.setNoError(1000);
                        mensajeResultado.setResultado(0);
                    }

                    if (mensajeResultado.getNoError() != 0) {
                        return;
                    }
                    tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaSubsidioPeriodicidad.toString(), tablasBaseSistema);

                    if (mensajeResultado.getNoError() != 0) {
                        mensajeResultado.setError("no encontro controladores en la tabla Subsidio por periodicidad");
                        return;
                    }
                    tablaSubsidio = construyeTablaXmlPorTipoNomina(ClavesParametrosModulos.claveTipoTablaSubsidioPeriodicidad.toString(), controladorPeriodicidad, tipoControladores, fechaFinal, ejercicio);

                    if (mensajeResultado.getNoError() == -10) {
                        mensajeResultado.setNoError(12);
                    }
                    if (tablaSubsidio == null & mensajeResultado.getNoError()
                            == 0) {
                        mensajeResultado.setError("no encontro datos de la tabla Subsidio por periodicidad");
                        mensajeResultado.setNoError(1000);
                        mensajeResultado.setResultado(0);
                    }

                    if (mensajeResultado.getNoError() != 0) {
                        return;
                    }
                }
            } else if (modoAjustarIngresosMes == ProporcionaTablaAnual) {

                tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaISRAnual.toString(), tablasBaseSistema);
                if (mensajeResultado.getNoError() != 0) {
                    mensajeResultado.setError("no encontro controladores en la tabla ISR anual");
                    return;
                }
                tablaIsr = construyeTablaXmlPorTipoNomina(ClavesParametrosModulos.claveTipoTablaISRAnual.toString(), "", tipoControladores, fechaFinal, ejercicio);
                if (mensajeResultado.getNoError() == -10) {
                    mensajeResultado.setNoError(13);
                }
                if (tablaIsr == null & mensajeResultado.getNoError() == 0) {
                    mensajeResultado.setError("no encontro datos de la tabla ISR Anual");
                    mensajeResultado.setNoError(1000);
                    mensajeResultado.setResultado(0);
                }
                if (mensajeResultado.getNoError() != 0) {
                    return;
                }

                tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaSUBSIDIOAnual.toString(), tablasBaseSistema);
                if (mensajeResultado.getNoError() != 0) {
                    mensajeResultado.setError("no encontro controladores en la tabla Subsidio anual");
                    return;
                }
                tablaSubsidio = construyeTablaXml(ClavesParametrosModulos.claveTipoTablaSUBSIDIOAnual.toString(), "", tipoControladores, fechaFinal, ejercicio);
                if (mensajeResultado.getNoError() == -10) {
                    mensajeResultado.setNoError(14);
                }
                if (tablaSubsidio == null & mensajeResultado.getNoError() == 0) {
                    mensajeResultado.setError("no encontro datos de la tabla Subsidio Anual");
                    mensajeResultado.setNoError(1000);
                    mensajeResultado.setResultado(0);
                }
                if (mensajeResultado.getNoError() != 0) {
                    return;
                }
            } else {
                tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaISR.toString(), tablasBaseSistema);
                if (mensajeResultado.getNoError() != 0) {
                    mensajeResultado.setError("no encontro controladores en la tabla ISR");
                    return;
                }
                tablaIsr = construyeTablaXml(ClavesParametrosModulos.claveTipoTablaISR.toString(), controlador, tipoControladores, fechaFinal, ejercicio);
                if (mensajeResultado.getNoError() == -10) {
                    mensajeResultado.setNoError(21);
                }
                if (tablaIsr == null & mensajeResultado.getNoError() == 0) {
                    mensajeResultado.setError("no encontro datos de la tabla ISR");
                    mensajeResultado.setNoError(1000);
                    mensajeResultado.setResultado(0);
                }
                if (mensajeResultado.getNoError() != 0) {
                    return;
                }
                tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaSubsidio.toString(), tablasBaseSistema);
                if (mensajeResultado.getNoError() != 0) {
                    mensajeResultado.setError("no encontro controladores en la tabla Subsidio");
                    return;
                }
                tablaSubsidio = construyeTablaXml(ClavesParametrosModulos.claveTipoTablaSubsidio.toString(), controlador, tipoControladores, fechaFinal, ejercicio);
                if (mensajeResultado.getNoError() == -10) {
                    mensajeResultado.setNoError(22);
                }
                if (tablaSubsidio == null & mensajeResultado.getNoError() == 0) {
                    mensajeResultado.setError("no encontro datos de la tabla Subsidio");
                    mensajeResultado.setNoError(1000);
                    mensajeResultado.setResultado(0);
                }
                if (mensajeResultado.getNoError() != 0) {
                    return;
                }
            }

            tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaZonaZalarial.toString(), tablasBaseSistema);
            if (mensajeResultado.getNoError() != 0) {
                mensajeResultado.setError("no encontro controladores en la tabla zona salarial");
                return;
            }
            tablaZonaSalarial = construyeTablaXml(ClavesParametrosModulos.claveTipoTablaZonaZalarial.toString(), controlador, tipoControladores, fechaFinal, ejercicio);
            if (mensajeResultado.getNoError() == -10) {
                mensajeResultado.setNoError(23);
            }
            if (tablaZonaSalarial == null & mensajeResultado.getNoError() == 0) {
                mensajeResultado.setError("no encontro datos de la tabla zona salarial");
                mensajeResultado.setNoError(1000);
                mensajeResultado.setResultado(0);
            }
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            if (mensajeResultado.getNoError() != 0) {
                mensajeResultado.setError("no encontro controladores en la tabla factor de integracion");
                return;
            }
            tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaFactorIntegracion.toString(), tablasBaseSistema);
            tablaFactorIntegracion = construyeTablaXml(ClavesParametrosModulos.claveTipoTablaFactorIntegracion.toString(), controlador, tipoControladores, fechaFinal, ejercicio);
            if (mensajeResultado.getNoError() == -10) {
                mensajeResultado.setNoError(24);
            }
            if (tablaFactorIntegracion == null & mensajeResultado.getNoError() == 0) {
                mensajeResultado.setError("no encontro datos de la tabla factor de integracion");
                mensajeResultado.setNoError(1000);
                mensajeResultado.setResultado(0);
            }
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            String queryTabla = "from TablaDatos";
            tablaDatosXml = (List<TablaDatos>) ejecutaQueryList(queryTabla, null, null, null);
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("generaTablasXml()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        } finally {
            setSession(null);
        }
    }

    //Modificado
    private void obtenerFactores(String claveRazonSocial) {
        try {
            Object[] valores = new Object[]{
                (Long) ClavesParametrosModulos.claveParametroFactorAplicaciónTablaAnual,
                (Long) ClavesParametrosModulos.claveParametroPagosPorHora,
                (Long) ClavesParametrosModulos.claveParametroManejarHorasPor,
                (Long) ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor,
                (Long) ClavesParametrosModulos.claveParametroTipoTablaISRAUtilizar,
                (Long) ClavesParametrosModulos.claveParametroFactorAplicaciónTablaMensual,
                (Long) ClavesParametrosModulos.claveParametroModoAjustarIngresosAlMes,
                (Long) ClavesParametrosModulos.claveParametroDesgloseInternoISR,
                (Long) ClavesParametrosModulos.clavePagarNominaDiasNaturales,
                (Long) ClavesParametrosModulos.claveParametroVersionCalculoPrestamoAhorro,
                (Long) ClavesParametrosModulos.claveParametroPagarIMSSDiasNaturales,
                (Long) ClavesParametrosModulos.claveParametroDescontarFaltasModoAjusteMes};
            List<Object[]> listParametros;
            mensajeResultado = getParametrosYListCrucePorModuloYClaves((String) ClavesParametrosModulos.claveModuloGlobal, valores);
            if (mensajeResultado.getNoError() == 0) {
                listParametros = (List<Object[]>) mensajeResultado.getResultado();
            } else {
                return;
            }
            Object[] parametroManejarSalarioDiarioPor = null;
            DesgloseInternoISR desgloseInternoISR = DesgloseInternoISR.DESGLOSEISRNORMALANUAL;
            for (int i = 0; i < listParametros.size(); i++) {
                if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroFactorAplicaciónTablaAnual)) {
                    if (factorAnual == null ? true : factorAnual == 0) {
                        factorAnual = parametroFactorAplicacionTablaAnual((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                    }
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroPagosPorHora)) {
                    if (manejaPagosPorHora == null) {
                        Object[] object = parametroPagosPorHora((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                        manejaPagosPorHora = (Boolean) object[0];
                    }
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroManejarHorasPor)) {
                    if (manejaPagosPorHora == null) {
                        manejoHorasPor = parametroManejarHorasPor((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                    }
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor)) {
                    parametroManejarSalarioDiarioPor = listParametros.get(i);
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroTipoTablaISRAUtilizar)) {
                    if (tipoTablaISR == null) {
                        tipoTablaISR = parametroTipoTablaISRAUtilizar((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                    }
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroFactorAplicaciónTablaMensual)) {
                    factorMensual = parametroFactorAplicacionTablaMensual((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroModoAjustarIngresosAlMes)) {
                    modoAjustarIngresosMes = parametroModoAjustarIngresosAlMes((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroDesgloseInternoISR)) {
                    desgloseInternoISR = parametroDesgloseInternoISR((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.clavePagarNominaDiasNaturales)) {
                    if (periodicidadTipoNomina != null) {
                        manejaPagoDiasNaturales = pagarNominaDiasNaturales((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial), new ValoresElementosAplicacion(Periodicidad.class, periodicidadTipoNomina.getClave())}));
                    } else {
                        manejaPagoDiasNaturales = pagarNominaDiasNaturales((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                    }
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroVersionCalculoPrestamoAhorro)) {
                    versionCalculoPrestamoAhorro = parametroVersionCalculoPrestamoAhorro((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroPagarIMSSDiasNaturales)) {
                    manejaPagoIMSSDiasNaturales = pagarIMSSDiasNaturales((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                } else if (((Parametros) (listParametros.get(i))[0]).getClave().equals((Long) ClavesParametrosModulos.claveParametroDescontarFaltasModoAjusteMes)) {
                    descontarFaltasModoAjustaMes = descontarFaltasModoAjustaMes((Parametros) listParametros.get(i)[0], (List<Cruce>) listParametros.get(i)[1], Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
                }
            }
            manejoSalarioDiario = parametroManejarSalarioDiarioPor((Parametros) parametroManejarSalarioDiarioPor[0], (List<Cruce>) parametroManejarSalarioDiarioPor[1], manejaPagoDiasNaturales, Arrays.asList(new ValoresElementosAplicacion[]{new ValoresElementosAplicacion(RazonesSociales.class, claveRazonSocial)}));
            if (modoAjustarIngresosMes != ProporcionaTablaAnual) {
                if (desgloseInternoISR == DesgloseInternoISR.DESGLOSEISRNORMALANUAL) {
                    calculoSeparadoISR = true;
                }
            }
        } catch (Exception ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(1);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerFactores()1_Error: ").append(ex));
        }
    }

    //Modificado
    private Object[][] construyeTablaXml(String claveTablaBase, String controlador, List<TipoControlador> tipoControladores, Date fechaFinal, Integer ejercicio) {
        Object[][] valores = null;
        try {
            controlador = (controlador == null) ? "" : controlador;
            boolean controlFecha = false, controlAño = false, controlEntidad = false;
            strQuery.delete(0, strQuery.length()).append("SELECT o.fileXml FROM TablaDatos o WHERE  o.tablaBase.clave  = :claveTablaBase ");
            strQuery.append("AND o.id =  (SELECT MAX(t.id) FROM TablaDatos t WHERE t.tablaBase.id  = o.tablaBase.id ");

            for (TipoControlador control : tipoControladores) {
                if (control == TipoControlador.CONTROLPORFECHA) {
                    controlFecha = true;
                    strQuery.append("AND t.controlPorFecha <= :fechaActual ");
                } else if (control == TipoControlador.CONTROLPORAÑO) {
                    strQuery.append("AND t.controlPorAnio <= :year ");
                    controlAño = true;
                } else if (control == TipoControlador.CONTROLADORENTIDAD) {
                    controlEntidad = true;
                }
            }
            if (!controlador.isEmpty() & controlEntidad) {
                strQuery.append("AND t.controladores = :controlador");
            }
            strQuery.append(")");

            Query query = getSession().createQuery(strQuery.toString());
            query.setString("claveTablaBase", claveTablaBase);
            if (controlFecha) {
                query.setParameter("fechaActual", fechaFinal);
            }
            if (controlAño) {
                query.setParameter("year", ejercicio);
            }
            if (!controlador.isEmpty() & controlEntidad) {
                query.setString("controlador", controlador);
                controlEntidad = true;
            }
            byte[] result = (byte[]) query.uniqueResult();  // busca con varios controladores definidos

            if (result == null) {
                strQuery.delete(0, strQuery.length()).append("SELECT o.fileXml FROM TablaDatos o WHERE  o.tablaBase.clave  = :claveTablaBase ");
                strQuery.append("AND o.id =  (SELECT MAX(t.id) FROM TablaDatos t WHERE t.tablaBase.id  = o.tablaBase.id ");
                controlFecha = false;

                for (TipoControlador control : tipoControladores) {
                    if (control == TipoControlador.CONTROLPORFECHA) {
                        controlFecha = true;
                        strQuery.append("AND t.controlPorFecha <= :fechaActual) ");
                        break;
                    } else if (control == TipoControlador.CONTROLPORAÑO) {
                        strQuery.append("AND t.controlPorAnio <= :year) ");
                        controlAño = true;
                        break;
                    } else if (control == TipoControlador.CONTROLADORENTIDAD) {
                        if (!controlador.isEmpty()) {
                            String[] controladores = controlador.split("#");
                            controlador = controladores[0];
                        }
                        strQuery.append("AND t.controladores = :controlador)");
                        break;
                    }
                }
                query = getSession().createQuery(strQuery.toString());
                query.setString("claveTablaBase", claveTablaBase);
                if (controlFecha) {
                    query.setParameter("fechaActual", getFechaDelSistema().getTime());
                } else if (controlAño) {
                    query.setParameter("year", getFechaDelSistema().get(Calendar.YEAR));
                } else if (!controlador.isEmpty() & controlEntidad) {
                    query.setString("controlador", controlador);
                }
                result = (byte[]) query.uniqueResult();  // busca con controlador necesario nomas
            }

            if (result != null) {
                valores = UtilidadesXML.extraeValoresNodos(UtilidadesXML.convierteBytesToXML(result));
                if (UtilidadesXML.ERROR_XML > 0) {
                    errorEstructuraXML(UtilidadesXML.ERROR_XML);
                    return null;
                }
            }
//            //strQuery = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(-10);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("construyeTablaXml()1_Error: ").append(ex));
        }
        return valores;
    }

    //Modificado
    private Object[][] construyeTablaXmlPorTipoNomina(String claveTablaBase, String clavePeriodicidad, List<TipoControlador> tipoControladores, Date fechaFinal, Integer ejercicio) {
        Object[][] valores = null;
        try {
            strQuery.delete(0, strQuery.length()).append("SELECT o.fileXml FROM TablaDatos o ");
            strQuery.append("WHERE o.tablaBase.clave  = :claveTablaBase ");
            strQuery.append("  AND o.id =  (SELECT MAX(t.id) FROM TablaDatos t WHERE t.tablaBase.id  = o.tablaBase.id ");
            boolean controlFecha = false, controlAño = false;
            for (TipoControlador controlador : tipoControladores) {
                if (controlador == TipoControlador.CONTROLADORENTIDAD) {
                    strQuery.append("AND t.controladores LIKE :controlador ");
                } else if (controlador == TipoControlador.CONTROLPORFECHA) {
                    controlFecha = true;
                    strQuery.append("AND t.controlPorFecha <= :fechaActual ");
                } else if (controlador == TipoControlador.CONTROLPORAÑO) {
                    strQuery.append("AND t.controlPorAnio <= :year ");
                    controlAño = true;
                }
            }
            strQuery.append(")");

            Query query = getSession().createQuery(strQuery.toString());
            query.setString("claveTablaBase", claveTablaBase);
            if (clavePeriodicidad.length() > 0) {
                query.setString("controlador", clavePeriodicidad);
            }
            if (controlFecha) {
                query.setParameter("fechaActual", fechaFinal);
            }
            if (controlAño) {
                query.setParameter("year", ejercicio);
            }
            byte[] result = (byte[]) query.uniqueResult();
            if (result == null) {
                if (controlFecha) {
                    query.setParameter("fechaActual", getFechaDelSistema().getTime());
                }
                if (controlAño) {
                    query.setParameter("year", getFechaDelSistema().get(Calendar.YEAR));
                }
                result = (byte[]) query.uniqueResult();
            }
            if (result != null) {
                valores = UtilidadesXML.extraeValoresNodos(UtilidadesXML.convierteBytesToXML(result));
                if (UtilidadesXML.ERROR_XML > 0) {
                    errorEstructuraXML(UtilidadesXML.ERROR_XML);
                    return null;
                }
            }
//            //strQuery = null;
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(-10);
            mensajeResultado.setError(ex.getMessage());
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("construyeTablaXmlPorTipoNomina()1_Error: ").append(ex));
        }
        return valores;
    }

    private void errorEstructuraXML(int error) {
        switch (error) {
            case 1:
                mensajeResultado.setNoError(15);
                break;
            case 2:
                mensajeResultado.setNoError(16);
                break;
            case 3:
                mensajeResultado.setNoError(17);
                break;
            case 4:
                mensajeResultado.setNoError(18);
                break;
            case 5:
                mensajeResultado.setNoError(19);
                break;
            case 6:
                mensajeResultado.setNoError(20);
                break;
        }
    }

    //Usando PlazasPorEmpleado
    private List<PlazasPorEmpleadosMov> obtenerPlazasPorEmpleados(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String clavePuesto,
            String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveDepto,
            String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String claveTipoCorrida, String claveFormaPago, Date fechaInicioPeriodo, Date fechaFinPeriodo) {
        List<PlazasPorEmpleadosMov> filtroPlazasPorEmpleadosMov = null, filtroPlazasPorEmpleadosMovTmp;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);

            strQuery.delete(0, strQuery.length()).append("SELECT pMov ");
            strQuery.append("from PlazasPorEmpleadosMov pMov inner join pMov.plazasPorEmpleado pemp where pMov.id IN ");
            strQuery.append(" (Select MAX(pMovX.id) from PlazasPorEmpleadosMov pMovX ");
            strQuery.append("INNER JOIN pMovX.plazasPorEmpleado pe ");
            strQuery.append("INNER JOIN pe.empleados em ");
            strWhere.delete(0, strWhere.length()).append(" WHERE ");

            claveRazonSocial = (claveRazonSocial == null ? "" : claveRazonSocial);
            if (!claveRazonSocial.isEmpty()) {
                strQuery.append(" INNER JOIN pe.razonesSociales rs ");
                strWhere.append(" rs.clave = :claveRazonSocial ");
                camposParametro.add("claveRazonSocial");
                valoresParametro.add(claveRazonSocial);
            }

            claveTurno = (claveTurno == null ? "" : claveTurno);
            if (!claveTurno.isEmpty()) {
                strQuery.append(" LEFT OUTER JOIN pMovX.turnos tu ");
                strWhere.append(" AND tu.clave = :claveTurno ");
                camposParametro.add("claveTurno");
                valoresParametro.add(claveTurno);
            }

            claveTipoNomina = (claveTipoNomina == null ? "" : claveTipoNomina);
            if (!claveTipoNomina.isEmpty()) {
                strQuery.append(" LEFT OUTER JOIN pMovX.tipoNomina t ");
                strWhere.append(" AND t.clave = :claveTipoNomina ");
                camposParametro.add("claveTipoNomina");
                valoresParametro.add(claveTipoNomina);
            }

            claveRegPatronal = (claveRegPatronal == null ? "" : claveRegPatronal);
            if (!claveRegPatronal.isEmpty()) {
                strQuery.append(" LEFT OUTER JOIN pe.registroPatronal rp ");
                strWhere.append(" AND rp.clave = :claveRegPatronal ");
                camposParametro.add("claveRegPatronal");
                valoresParametro.add(claveRegPatronal);
            }

            claveDepto = (claveDepto == null ? "" : claveDepto);
            if (!claveDepto.isEmpty()) {
                strQuery.append(" LEFT OUTER JOIN pMovX.departamentos dp ");
                strWhere.append(" AND dp.clave = :claveDepto ");
                camposParametro.add("claveDepto");
                valoresParametro.add(claveDepto);
            }

            claveCtrCosto = (claveCtrCosto == null ? "" : claveCtrCosto);
            if (!claveCtrCosto.isEmpty()) {
                strQuery.append(" LEFT OUTER JOIN pMovX.centroDeCosto cc ");
                strWhere.append(" AND cc.clave = :claveCtrCosto ");
                camposParametro.add("claveCtrCosto");
                valoresParametro.add(claveCtrCosto);
            }

            clavePuesto = (clavePuesto == null ? "" : clavePuesto);
            if (!clavePuesto.isEmpty()) {
                strQuery.append(" LEFT OUTER JOIN pMovX.puestos pu ");
                strWhere.append(" AND pu.clave = :clavePuesto ");
                camposParametro.add("clavePuesto");
                valoresParametro.add(clavePuesto);
            }

            claveCategoriasPuestos = (claveCategoriasPuestos == null ? "" : claveCategoriasPuestos);
            if (!claveCategoriasPuestos.isEmpty()) {
                if (clavePuesto.isEmpty()) {
                    strQuery.append(" LEFT OUTER JOIN pMovX.puestos pu ");
                }
                strQuery.append(" LEFT OUTER JOIN pu.categoriasPuestos cp ");
                strWhere.append(" AND cp.clave = :claveCategoriasPuestos ");
                camposParametro.add("claveCategoriasPuestos");
                valoresParametro.add(claveCategoriasPuestos);
            }

            claveFormaPago = (claveFormaPago == null ? "" : claveFormaPago);
            if (!claveFormaPago.isEmpty()) {
                strQuery.append(" LEFT OUTER JOIN pMovX.formasDePago fp ");
                strWhere.append(" AND fp.clave = :claveFormaPago ");
                camposParametro.add("claveFormaPago");
                valoresParametro.add(claveFormaPago);
            }

            if (tipoContrato != null) {
                //if (tipoContrato != -1) {
                strQuery.append(" LEFT OUTER JOIN pMovX.tipoContrato tc ");
                strWhere.append(" AND tc.clave = :tipoContrato ");
                camposParametro.add("tipoContrato");
                valoresParametro.add(tipoContrato);
                //}
            }

            if (status != null) {
                strWhere.append(" AND em.status = :status ");
                camposParametro.add("status");
                valoresParametro.add(status);
            }

            if (tipoSalario != null) {
                strQuery.append(",SalariosIntegrados si ");
            }

            if (tipoSalario != null) {  ///pendiente modifcar cambio a tabla salario diario integrado
                strWhere.append("AND  si.fecha = (SELECT MAX (s.fecha) FROM SalariosIntegrados s WHERE s.fecha <= :fechaActual AND s.empleados.id = si.empleados.id AND s.empleados.id = pe.empleados.id) ");
                strWhere.append("AND si.tipoDeSalario = :tipoSalario ");
                camposParametro.add("tipoSalario");
                valoresParametro.add(tipoSalario);
                camposParametro.add("fechaActual");
                if (claveTipoCorrida.equalsIgnoreCase("FIN")) {
                    valoresParametro.add(fechaBajaFiniq);
                } else {
                    valoresParametro.add(fechaFinPeriodo);
                }
            }

            strWhere.append(" AND ((pMovX.fechaInicial <= :fechaInicialPeriodo ) OR (pMovX.fechaInicial between :fechaInicialPeriodo AND :fechaFinalPeriodo ))  ");
            strWhere.append(" AND ((pMovX.plazasPorEmpleado.fechaFinal >= :fechaFinalPeriodo ) OR   (pMovX.plazasPorEmpleado.fechaFinal between :fechaInicialPeriodo AND :fechaFinalPeriodo )) ");
            camposParametro.add("fechaInicialPeriodo");
            if (claveTipoCorrida.equalsIgnoreCase("FIN")) {
                valoresParametro.add(fechaBajaFiniq);
            } else {
                valoresParametro.add(fechaInicioPeriodo);
            }
            camposParametro.add("fechaFinalPeriodo");
            valoresParametro.add(fechaFinPeriodo);

            if (claveEmpIni.length() > 0 & claveEmpFin.length() > 0) {
                strWhere.append(" AND (em.clave BETWEEN :claveEmpIni AND :claveEmpFin) ");
                camposParametro.add("claveEmpIni");
                valoresParametro.add(claveEmpIni);
                camposParametro.add("claveEmpFin");
                valoresParametro.add(claveEmpFin);
            } else if (claveEmpIni.length() > 0) {
                strWhere.append(" AND em.clave >= :claveEmpIni ");
                camposParametro.add("claveEmpIni");
                valoresParametro.add(claveEmpIni);
            } else if (claveEmpFin.length() > 0) {
                strWhere.append(" AND em.clave <= :claveEmpFin");
                camposParametro.add("claveEmpFin");
                valoresParametro.add(claveEmpFin);
            }
            strWhere.append(" GROUP BY pe.clave) ");
            if (!claveRazonSocial.isEmpty()) {
                strWhere.append(" AND pemp.id not in (Select px.reIngreso.id from PlazasPorEmpleado px Where px.razonesSociales.clave = :claveRazonSocial AND px.reIngreso != null ) ");
            } else {
                strWhere.append(" AND pemp.id not in (Select px.reIngreso.id from PlazasPorEmpleado px Where px.reIngreso != null ) ");
            }
            if (!isCalculoSDI) {
                strWhere.append(" AND pemp.empleados.clave not in ( ");
                claveTipoNomina = (claveTipoNomina == null ? "" : claveTipoNomina);
                if (!claveTipoNomina.isEmpty()) {
                    strWhere.append("select o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave from CFDIEmpleado o where o.razonesSociales.clave = :claveRazonSocial and o.tipoNomina.clave = :claveTipoNomina ");
                } else {
                    strWhere.append("select o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave from CFDIEmpleado o where o.razonesSociales.clave = :claveRazonSocial ");

                }
                if (!claveTipoCorrida.isEmpty()) {
                    strWhere.append(" and o.tipoCorrida.clave = :claveTipoCorrida ");
                    camposParametro.add("claveTipoCorrida");
                    valoresParametro.add(claveTipoCorrida);
                }
                if (periodosNomina != null) {
                    strWhere.append(" and o.periodosNomina.id = :idPeriodoNomina ");
                    camposParametro.add("idPeriodoNomina");
                    valoresParametro.add(periodosNomina.getId());
                }

                if (claveEmpIni.length() > 0 & claveEmpFin.length() > 0) {
                    strWhere.append(" AND (o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave BETWEEN :claveEmpIni AND :claveEmpFin) ");
                } else if (claveEmpIni.length() > 0) {
                    strWhere.append(" AND o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave >= :claveEmpIni ");
                } else if (claveEmpFin.length() > 0) {
                    strWhere.append(" AND o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave <= :claveEmpFin");
                }

                strWhere.append(" AND o.cfdiRecibo.statusTimbrado = :statusTimbre ");
                camposParametro.add("statusTimbre");
                valoresParametro.add(StatusTimbrado.TIMBRADO);

                strWhere.append(" ) ");
            }
            if (claveTipoCorrida.equalsIgnoreCase("FIN")) {
                strWhere.append(" AND pemp.clave IN (SELECT flp.plazasPorEmpleado.clave FROM FiniqLiquidPlazas flp WHERE pemp.clave = flp.plazasPorEmpleado.clave ");
                strWhere.append(" AND flp.incluir = true) ");
            }
            strWhere.append(" AND pemp.plazaPrincipal = true");
            strWhere.append(" ORDER BY pemp.empleados.clave, pemp.clave");
            strQuery.append(strWhere);

            filtroPlazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
//            if (filtroPlazasPorEmpleadosMovTmp == null) {
//                filtroPlazasPorEmpleadosMovTmp = new ArrayList<PlazasPorEmpleadosMov>();
//            }
//            filtroPlazasPorEmpleadosMov = new ArrayList<PlazasPorEmpleadosMov>();
//            for (int i = 0; i < filtroPlazasPorEmpleadosMovTmp.size(); i++) {
//                if (i + 1 > filtroPlazasPorEmpleadosMovTmp.size()) {
//                    if (!filtroPlazasPorEmpleadosMovTmp.get(i).getPlazasPorEmpleado().getEmpleados().getClave().equalsIgnoreCase(filtroPlazasPorEmpleadosMovTmp.get(i + 1).getPlazasPorEmpleado().getEmpleados().getClave())) {
//                        filtroPlazasPorEmpleadosMov.add(filtroPlazasPorEmpleadosMovTmp.get(i));
//                    }
//                } else {
//                    if (i == 0) {
//                        filtroPlazasPorEmpleadosMov.add(filtroPlazasPorEmpleadosMovTmp.get(i));
//                    }
//                }
//            }

            camposParametro = null;
            valoresParametro = null;
//            //strQuery = null;
            //strWhere = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerPlazasPorEmpleados()1_Error: ").append(ex));
        }
        return filtroPlazasPorEmpleadosMov;
    }

    //Usando PlazasPorEmpleado
    private List<PlazasPorEmpleadosMov> obtenerPlazasPorEmpleadosMovRestantes(String claveRazonSocial, Long idRegPatronal, Date fechaInicioPeriodo, Date fechaFinPeriodo, PlazasPorEmpleadosMov plazasPorEmpleadosMovEjecutandose) {
        List<PlazasPorEmpleadosMov> filtroPlazasPorEmpleadosMov = null, filtroPlazasPorEmpleadosMovTmp;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT pMov ");
            strQuery.append("from PlazasPorEmpleadosMov pMov inner join pMov.plazasPorEmpleado pemp where pMov.id IN ");
            strQuery.append(" (Select MAX(pMovX.id) from PlazasPorEmpleadosMov pMovX ");
            strQuery.append("INNER JOIN pMovX.plazasPorEmpleado pe ");
            strQuery.append("INNER JOIN pe.empleados em ");
            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strQuery.append(" LEFT OUTER JOIN pe.razonesSociales rs ");
            strWhere.append(" rs.clave = :claveRazonSocial ");
            camposParametro.add("claveRazonSocial");
            valoresParametro.add(claveRazonSocial);
            strQuery.append(" LEFT OUTER JOIN pe.registroPatronal rp ");
            strWhere.append(" AND rp.id = :claveRegPatronal ");
            camposParametro.add("claveRegPatronal");
            valoresParametro.add(idRegPatronal);

            strWhere.append(" AND ((pMovX.fechaInicial <= :fechaInicialPeriodo ) OR (pMovX.fechaInicial between :fechaInicialPeriodo AND :fechaFinalPeriodo ))  ");
            strWhere.append(" AND ((pMovX.plazasPorEmpleado.fechaFinal >= :fechaFinalPeriodo ) OR   (pMovX.plazasPorEmpleado.fechaFinal between :fechaInicialPeriodo AND :fechaFinalPeriodo )) ");
            camposParametro.add("fechaInicialPeriodo");
            if (tipoCorrida.getClave().equalsIgnoreCase("FIN")) {
                valoresParametro.add(fechaBajaFiniq);
            } else {
                valoresParametro.add(fechaInicioPeriodo);
            }
            camposParametro.add("fechaFinalPeriodo");
            valoresParametro.add(fechaFinPeriodo);
            strWhere.append(" AND em in (:listEmpleado)");
            camposParametro.add("listEmpleado");
            valoresParametro.add(new Empleados[]{plazasPorEmpleadosMovEjecutandose.getPlazasPorEmpleado().getEmpleados()});
            strWhere.append(" GROUP BY pe.clave) ");
            if (!claveRazonSocial.isEmpty()) {
                strWhere.append(" AND pemp.id not in (Select px.reIngreso.id from PlazasPorEmpleado px Where px.razonesSociales.clave = :claveRazonSocial AND px.reIngreso != null ) ");
            } else {
                strWhere.append(" AND pemp.id not in (Select px.reIngreso.id from PlazasPorEmpleado px Where px.reIngreso != null ) ");
            }
            strWhere.append(" AND pemp.empleados.clave not in ( ");
            strWhere.append("select o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave from CFDIEmpleado o where o.razonesSociales.clave = :claveRazonSocial  ");
            if (tipoCorrida != null) {
                strWhere.append(" and o.tipoCorrida.clave = :claveTipoCorrida ");
                camposParametro.add("claveTipoCorrida");
                valoresParametro.add(tipoCorrida.getClave());
            }
            if (periodosNomina != null) {
                strWhere.append(" and o.periodosNomina.id = :idPeriodoNomina ");
                camposParametro.add("idPeriodoNomina");
                valoresParametro.add(periodosNomina.getId());
            }
            strWhere.append(" AND o.plazaPorEmpleadoMov.plazasPorEmpleado.empleados in (:listEmpleado)");
            strWhere.append(" AND o.cfdiRecibo.statusTimbrado = :statusTimbre ");
            camposParametro.add("statusTimbre");
            valoresParametro.add(StatusTimbrado.TIMBRADO);
            strWhere.append(" ) ");
            if (tipoCorrida.getClave().equalsIgnoreCase("FIN")) {
                strWhere.append(" AND pemp.clave IN (SELECT flp.plazasPorEmpleado.clave FROM FiniqLiquidPlazas flp WHERE pemp.clave = flp.plazasPorEmpleado.clave ");
                strWhere.append(" AND flp.incluir = true) ");
            }
            strWhere.append(" AND pMov.id != :plazaPorEmpleadoMov_ID");
            camposParametro.add("plazaPorEmpleadoMov_ID");
            valoresParametro.add(plazasPorEmpleadosMovEjecutandose.getId());
            strWhere.append(" ORDER BY pemp.empleados.clave, pemp.clave");
            strQuery.append(strWhere);
            filtroPlazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerPlazasPorEmpleadosMovRestantes()1_Error: ").append(ex));
        }
        return filtroPlazasPorEmpleadosMov;
    }

    private List<PlazasPorEmpleadosMov> obtenerPlazasPorEmpleadosMovDentroPeriodo(String claveTipoCorrida, Date fechaInicioPeriodo, Date fechaFinPeriodo, Object[] plazasPorEmpleadosMov) {//JSA13
        List<PlazasPorEmpleadosMov> filtroPlazasPorEmpleadosMov = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT pmov ");
            strQuery.append(" FROM PlazasPorEmpleadosMov pmov ");
            strQuery.append(" INNER JOIN pmov.plazasPorEmpleado pemp");
            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strWhere.append("  (pmov.fechaInicial BETWEEN :fechaInicialPeriodo and :fechaFinalPeriodo) AND pemp.fechaFinal >= :fechaInicialPeriodo");
            strWhere.append(" AND  pemp.fechaFinal >= :fechaInicialPeriodo and pmov.fechaInicial <= :fechaFinalPeriodo");
            camposParametro.add("fechaInicialPeriodo");
            if (claveTipoCorrida.equalsIgnoreCase("FIN")) {
                valoresParametro.add(fechaBajaFiniq.getTime());
            } else {
                valoresParametro.add(fechaInicioPeriodo);
            }
            camposParametro.add("fechaFinalPeriodo");
            valoresParametro.add(fechaFinPeriodo);

            strWhere.append(" AND pmov.id not in (:idPlazaPorEmpleadoMov)");
            camposParametro.add("idPlazaPorEmpleadoMov");
            List<Object> listIdPlazaPorEmpleadoMov = new ArrayList<Object>(), listIdPlazasPorEmpleado = new ArrayList<Object>();
            int i;
            for (i = 0; i < plazasPorEmpleadosMov.length; i++) {
                listIdPlazaPorEmpleadoMov.add(((PlazasPorEmpleadosMov) plazasPorEmpleadosMov[i]).getId());
                listIdPlazasPorEmpleado.add(((PlazasPorEmpleadosMov) plazasPorEmpleadosMov[i]).getPlazasPorEmpleado().getId());
            }
            valoresParametro.add(listIdPlazaPorEmpleadoMov.toArray());
            strWhere.append(" AND pemp.id in( :IdPlazasPorEmpleado)");
            camposParametro.add("IdPlazasPorEmpleado");
            valoresParametro.add(listIdPlazasPorEmpleado.toArray());
            strWhere.append(" AND pmov.cambioSalarioPor = :salarioPor ");
            camposParametro.add("salarioPor");
            valoresParametro.add(true);
            strQuery.append(strWhere.toString());
            filtroPlazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            camposParametro = null;
            valoresParametro = null;
//            //strQuery = null;
            //strWhere = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerPlazasPorEmpleadosMovDentroPeriodo()1_Error: ").append(ex));
        }
        return filtroPlazasPorEmpleadosMov;
    }

    private List<PlazasPorEmpleadosMov> obtenerMinimoPlazasPorEmpleadosMovDentroPeriodo(String claveTipoCorrida, Date fechaInicioPeriodo, Date fechaFinPeriodo, PlazasPorEmpleadosMov plazasPorEmpleadosMov) {//JSA13
        List<PlazasPorEmpleadosMov> filtroPlazasPorEmpleadosMov = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT pmov ");
            strQuery.append(" FROM PlazasPorEmpleadosMov pmov ");
            strQuery.append(" INNER JOIN pmov.plazasPorEmpleado pemp");
            strWhere.delete(0, strWhere.length()).append(" WHERE ");

            camposParametro.add("fechaInicialPeriodo");
            if (claveTipoCorrida.equalsIgnoreCase("FIN")) {
                valoresParametro.add(fechaBajaFiniq.getTime());
            } else {
                valoresParametro.add(fechaInicioPeriodo);
            }
            camposParametro.add("fechaFinalPeriodo");
            valoresParametro.add(fechaFinPeriodo);

            strWhere.append(" pmov.id != :idPlazaPorEmpleadoMov");
            camposParametro.add("idPlazaPorEmpleadoMov");
            valoresParametro.add(plazasPorEmpleadosMov.getId());

            strWhere.append(" AND pemp.id = :IdPlazasPorEmpleado");
            camposParametro.add("IdPlazasPorEmpleado");
            valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getId());
            strWhere.append(" AND pmov.fechaInicial = (SELECT MIN(pmov.fechaInicial) FROM PlazasPorEmpleadosMov pmov INNER JOIN pmov.plazasPorEmpleado pemp ");
            strWhere.append(" WHERE  (pmov.fechaInicial BETWEEN :fechaInicialPeriodo and :fechaFinalPeriodo) AND pemp.fechaFinal >= :fechaInicialPeriodo");
            strWhere.append(" AND  pemp.fechaFinal >= :fechaInicialPeriodo and pmov.fechaInicial <= :fechaFinalPeriodo");
            strWhere.append(" AND pmov.id != :idPlazaPorEmpleadoMov");
            strWhere.append(" AND pemp.id = :IdPlazasPorEmpleado )");
            strWhere.append(" AND pmov.cambioSalarioPor = :salarioPor ");
            camposParametro.add("salarioPor");
            valoresParametro.add(true);
            strQuery.append(strWhere.toString());
            filtroPlazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            camposParametro = null;
            valoresParametro = null;
//            //strQuery = null;
            //strWhere = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerPlazasPorEmpleados()1_Error: ").append(ex));
        }
        return filtroPlazasPorEmpleadosMov;
    }

    private List<PlazasPorEmpleadosMov> obtenerAnteriorPlazasPorEmpleadosMov(String claveTipoCorrida, Date fechaInicioPeriodo, PlazasPorEmpleadosMov plazasPorEmpleadosMov) {//JSA13
        List<PlazasPorEmpleadosMov> filtroPlazasPorEmpleadosMov = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT pmov ");
            strQuery.append(" FROM PlazasPorEmpleadosMov pmov ");
            strQuery.append(" INNER JOIN pmov.plazasPorEmpleado pemp");
            strWhere.delete(0, strWhere.length()).append(" WHERE ");

            camposParametro.add("fechaInicialPeriodo");
            valoresParametro.add(plazasPorEmpleadosMov.getFechaInicial());

            strWhere.append(" pmov.id != :idPlazaPorEmpleadoMov");
            camposParametro.add("idPlazaPorEmpleadoMov");
            valoresParametro.add(plazasPorEmpleadosMov.getId());

            strWhere.append(" AND pemp.id = :IdPlazasPorEmpleado");
            camposParametro.add("IdPlazasPorEmpleado");
            valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getId());
            strWhere.append(" AND pmov.fechaInicial = (SELECT MAX(pmovx.fechaInicial) FROM PlazasPorEmpleadosMov pmovx INNER JOIN pmovx.plazasPorEmpleado pempx ");
            strWhere.append(" WHERE  pempx.fechaFinal >= :fechaInicialPeriodo");
            strWhere.append(" AND  pmovx.fechaInicial <= :fechaInicialPeriodo");
            strWhere.append(" AND pmovx.id != :idPlazaPorEmpleadoMov");
            strWhere.append(" AND pempx.id = :IdPlazasPorEmpleado )");
//            strWhere.append(" AND pmov.cambioSalarioPor = :salarioPor ");
//            camposParametro.add("salarioPor");
//            valoresParametro.add(true);
            strQuery.append(strWhere.toString());
            filtroPlazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 1);
            camposParametro = null;
            valoresParametro = null;
//            //strQuery = null;
            //strWhere = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerAnteriorPlazasPorEmpleadosMov()1_Error: ").append(ex));
        }
        return filtroPlazasPorEmpleadosMov;
    }

    private Date obtenerPrimerPlazasPorEmpleadosMov(PlazasPorEmpleadosMov plazasPorEmpleadosMov) {//JSA13
        List<PlazasPorEmpleadosMov> filtroPlazasPorEmpleadosMov = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT pmov ");
            strQuery.append(" FROM PlazasPorEmpleadosMov pmov ");
            strQuery.append(" INNER JOIN pmov.plazasPorEmpleado pemp");
            strWhere.delete(0, strWhere.length()).append(" WHERE ");
            strWhere.append(" pemp.id = :IdPlazasPorEmpleado");
            camposParametro.add("IdPlazasPorEmpleado");
            valoresParametro.add(plazasPorEmpleadosMov.getPlazasPorEmpleado().getId());
            strWhere.append(" AND pmov.fechaInicial = (SELECT MIN(pmovx.fechaInicial) FROM PlazasPorEmpleadosMov pmovx INNER JOIN pmovx.plazasPorEmpleado pemp ");
            strWhere.append(" WHERE  pemp.id = :IdPlazasPorEmpleado )");
            strQuery.append(strWhere.toString());
            filtroPlazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 1);
            camposParametro = null;
            valoresParametro = null;
//            //strQuery = null;
            //strWhere = null;
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerPrimerPlazasPorEmpleadosMov()1_Error: ").append(ex));
        }
        if (filtroPlazasPorEmpleadosMov == null ? true : filtroPlazasPorEmpleadosMov.isEmpty() ? true : false) {
            return plazasPorEmpleadosMov.getFechaIMSS();
        } else {
            return filtroPlazasPorEmpleadosMov.get(0).getFechaIMSS();
        }
    }

    private List<SalariosIntegrados> obtenerSalariosIntegradosDentroDelPeriodo(Date fechaInicial, Date fechaFinal, String claveEmpleado, String claveRegistroPatronal, String claveRazonesSociales) {
        List<SalariosIntegrados> listSalariosIntegrados = null;
        try {
            strQuery = new StringBuilder("FROM ").append(SalariosIntegrados.class.getSimpleName()).append(" s ");
            strQuery.append(" WHERE s.fecha between :fechaInicialPeriodo AND :fechaFinalPeriodo  and s.empleados.clave = :claveEmpleado ");
            strQuery.append(" AND s.registroPatronal.clave = :claveRegPatronal and s.empleados.razonesSociales.clave = :claveRazonesSociales");
            listSalariosIntegrados = (List<SalariosIntegrados>) ejecutaQueryList(strQuery.toString(), new String[]{"fechaInicialPeriodo", "fechaFinalPeriodo", "claveEmpleado", "claveRegPatronal", "claveRazonesSociales"},
                    new Object[]{fechaInicial, fechaFinal, claveEmpleado, claveRegistroPatronal, claveRazonesSociales}, 0);
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerSalariosIntegradosDentroDelPeriodo()1_Error: ").append(ex));
        }
        if (listSalariosIntegrados == null) {
            listSalariosIntegrados = new ArrayList<SalariosIntegrados>();
        }
        return listSalariosIntegrados;
    }

    private List<SalariosIntegrados> obtenerAnteriorSalariosIntegrados(Date fechaInicial, String claveEmpleado, String claveRegistroPatronal, String claveRazonesSociales) {//JSA13
        List<SalariosIntegrados> listSalariosIntegrados = null;
        try {
            strQuery = new StringBuilder("FROM ").append(SalariosIntegrados.class.getSimpleName()).append(" s ");
            strQuery.append(" WHERE s.fecha < :fechaInicialPeriodo and s.empleados.clave = :claveEmpleado ");
            strQuery.append(" AND s.registroPatronal.clave = :claveRegPatronal and s.empleados.razonesSociales.clave = :claveRazonesSociales order by s.fecha desc");
            listSalariosIntegrados = (List<SalariosIntegrados>) ejecutaQueryList(strQuery.toString(), new String[]{"fechaInicialPeriodo", "claveEmpleado", "claveRegPatronal", "claveRazonesSociales"},
                    new Object[]{fechaInicial, claveEmpleado, claveRegistroPatronal, claveRazonesSociales}, 1);
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerAnteriorSalariosIntegrados()1_Error: ").append(ex));
        }
        if (listSalariosIntegrados == null ? true : listSalariosIntegrados.isEmpty() ? true : false) {
            listSalariosIntegrados = new ArrayList<SalariosIntegrados>();
        } else if (listSalariosIntegrados.get(0).getFiniquitosLiquidaciones() != null) {
            listSalariosIntegrados.removeAll(listSalariosIntegrados);
        }
        return listSalariosIntegrados;
    }

    private SalariosIntegrados obtenerSalariosIntegradosActual(Date fechaInicial, String claveEmpleado, String claveRegistroPatronal, String claveRazonesSociales) {
        SalariosIntegrados s = null;
        try {
            strQuery = new StringBuilder("SELECT s FROM ").append(SalariosIntegrados.class.getSimpleName()).append(" s ");
            strQuery.append(" WHERE s.fecha <= :fechaInicialPeriodo and s.empleados.clave = :claveEmpleado ");
            strQuery.append(" AND s.registroPatronal.clave = :claveRegPatronal and s.empleados.razonesSociales.clave = :claveRazonesSociales  order by s.fecha desc");
            s = (SalariosIntegrados) ejecutaQueryUnico(strQuery.toString(), new String[]{"fechaInicialPeriodo", "claveEmpleado", "claveRegPatronal", "claveRazonesSociales"},
                    new Object[]{fechaInicial, claveEmpleado, claveRegistroPatronal, claveRazonesSociales});
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(27);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerSalariosIntegradosDentroDelPeriodo()1_Error: ").append(ex));
        }
        if (s != null) {
            if (s.getFiniquitosLiquidaciones() != null) {
                s = null;
            }
        }
        return s;
    }

    //Usando plazas
    private void obtenerMovimientosNominaPorPlaza(String claveTipoCorrida, String claveTipoNomina, Long idPeriodoNomina, PlazasPorEmpleado plazaPorEmpleado,
            String claveCtrCosto, String claveRazonSocial) {
        try {
            filtroMovimientosNominas = new ArrayList<MovNomConcep>(0);
            filtroConceptosNomina = new ArrayList<ConcepNomDefi>(0);
            //busca conceptos automaticos en movimientos
            filtroMovimientosNominas.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(claveTipoCorrida, claveTipoNomina, idPeriodoNomina, plazaPorEmpleado, claveCtrCosto, Tipo.AUTOMATICO, claveRazonSocial, null, -1, null));
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            buscaConceptosTipoAutomatico(claveTipoCorrida);
//            System.out.println("tamaños filtroMovimientosNominas" + filtroMovimientosNominas.size() + " filtroConceptosNomina " + filtroConceptosNomina.size());
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            int i = 0, j = 0;
            if (obtenerConceptosUnicos() != filtroConceptosNomina.size()) {//JSA14
                while (i < filtroMovimientosNominas.size()) {
                    while (j < filtroConceptosNomina.size()) {
                        if (filtroMovimientosNominas.get(i).getConcepNomDefi().getClave().equalsIgnoreCase(filtroConceptosNomina.get(j).getClave())) {
                            filtroConceptosNomina.remove(j);
                            break;
                        } else {
                            j++;
                        }
                    }
                    j = 0;
                    i++;
                }
                filtroMovimientosNominas.addAll(creaMovimientosPlazasConceptosAutomaticos(plazaPorEmpleado, periodosNomina, claveTipoCorrida, claveRazonSocial, claveCtrCosto));
                if (mensajeResultado.getNoError() != 0) {
                    return;
                }
            }
            //busca conceptos del periodo en los movimientos
            filtroMovimientosNominas.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(claveTipoCorrida, claveTipoNomina, idPeriodoNomina, plazaPorEmpleado, claveCtrCosto, Tipo.PERIODO, claveRazonSocial, null, -1, null));
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            //busca conceptos repetitivos en los movimientos.
            filtroMovimientosNominas.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(claveTipoCorrida, claveTipoNomina, idPeriodoNomina, plazaPorEmpleado, claveCtrCosto, Tipo.REPETITIVO, claveRazonSocial, null, -1, null));
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            ordenarMovimientosNomina();
            generarMovimientosAbarca2Meses();
            filtroConceptosNomina = null;
        } catch (Exception ex) {
            mensajeResultado.setNoError(34);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerMovimientosNominaPorPlaza()1_Error: ").append(ex));
        }
    }

    private void generarMovimientosAbarca2Meses() {
        if (!filtroMovimientosNominas.isEmpty()) {
            if (evaluaPeriodoMovAbarca2Meses(filtroMovimientosNominas.get(0).getPeriodosNomina())) {
                //Este codigo vuelve a generar los movimientos de nomina que no tengan su 2do movimiento ya que el periodo
                //abarca 2 meses y se tienen que calcular la informacion por cada mes.
                List<MovNomConcep> listTmp = new ArrayList<MovNomConcep>();
                Integer mesUno;
                Calendar fechaInicio = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
                fechaInicio.setTime(filtroMovimientosNominas.get(0).getPeriodosNomina().getFechaInicial());
                fechaFinal.setTime(filtroMovimientosNominas.get(0).getPeriodosNomina().getFechaFinal());
                mesUno = fechaInicio.get(Calendar.MONTH) + 1;
                int i = 0;
                while (i < filtroMovimientosNominas.size()) {
//                System.out.println("claves concep " + filtroMovimientosNominas.get(i).getConcepNomDefi().getDescripcion() + " " + filtroMovimientosNominas.get(i).getConcepNomDefi().getClave() + " - " + filtroMovimientosNominas.get(i + 1).getConcepNomDefi().getClave());
                    if (!filtroMovimientosNominas.get(i).getConcepNomDefi().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(i + 1).getConcepNomDefi().getClave())) {
                        MovNomConcep newMov = duplicarMovNomConcep(filtroMovimientosNominas.get(i), filtroMovimientosNominas.get(i).getNumero(), filtroMovimientosNominas.get(i).getPlazasPorEmpleado());
                        if (filtroMovimientosNominas.get(i).getMes().equals(mesUno)) {//Existe el mesUno generare el mesDos
                            fechaInicio.setTime(newMov.getPeriodosNomina().getFechaFinal());
                            newMov.setMes(fechaInicio.get(Calendar.MONTH) + 1);
                            newMov.setNumMovParticion(2);
                            filtroMovimientosNominas.get(i).setNumMovParticion(1);
                        } else {//Existe el mesDos generare el mesUno
                            fechaInicio.setTime(newMov.getPeriodosNomina().getFechaInicial());
                            newMov.setMes(fechaInicio.get(Calendar.MONTH) + 1);
                            newMov.setNumMovParticion(1);
                            filtroMovimientosNominas.get(i).setNumMovParticion(2);
                        }
                        listTmp.add(newMov);
                        i++;
                        if (i + 1 >= filtroMovimientosNominas.size()) {
                            newMov = duplicarMovNomConcep(filtroMovimientosNominas.get(i), filtroMovimientosNominas.get(i).getNumero(), filtroMovimientosNominas.get(i).getPlazasPorEmpleado());
                            if (filtroMovimientosNominas.get(i).getMes().equals(mesUno)) {//Existe el mesUno generare el mesDos
                                fechaInicio.setTime(newMov.getPeriodosNomina().getFechaFinal());
                                newMov.setMes(fechaInicio.get(Calendar.MONTH) + 1);
                                newMov.setNumMovParticion(2);
                                filtroMovimientosNominas.get(i).setNumMovParticion(1);
                            } else {//Existe el mesDos generare el mesUno
                                fechaInicio.setTime(newMov.getPeriodosNomina().getFechaInicial());
                                newMov.setMes(fechaInicio.get(Calendar.MONTH) + 1);
                                newMov.setNumMovParticion(1);
                                filtroMovimientosNominas.get(i).setNumMovParticion(2);
                            }
                            listTmp.add(newMov);
                            break;
                        }
                    } else {
                        List<Object> clavesMovEliminados = new ArrayList<Object>();
                        int x = i + 2;
                        if (filtroMovimientosNominas.size() > x) {
                            while (filtroMovimientosNominas.get(x).getConcepNomDefi().getClave().equalsIgnoreCase(filtroMovimientosNominas.get(i).getConcepNomDefi().getClave())) {
//                            System.out.println("claves concep xx " + filtroMovimientosNominas.get(x).getConcepNomDefi().getClave() + " - " + filtroMovimientosNominas.get(i).getConcepNomDefi().getClave());
                                clavesMovEliminados.add(filtroMovimientosNominas.get(x).getId());
                                filtroMovimientosNominas.remove(x);
                            }
                        }
                        if (!clavesMovEliminados.isEmpty()) {
                            eliminarMovimientosNominaBasura(clavesMovEliminados.toArray());
//                            System.out.println("flush 14");
                            if (mensajeResultado.getNoError() != 0) {
                                break;
                            }
                            getSession().flush();
                            getSession().clear();
                        }
                        i = x;//+ 1;
                    }
                }
                filtroMovimientosNominas.addAll(listTmp);
                ordenarMovimientosNomina();
            }
        }
    }

    private List<CalculoUnidades> obtenerListCalculoUnidadesUtilizar(String claveRazonSocial, PlazasPorEmpleado plazaPorEmpleado, String claveTipoNomina, Long ideriodoNomina, String claveTipoCorrida) {
        List<CalculoUnidades> listCalculoUnidades = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT mn FROM ").append(CalculoUnidades.class.getSimpleName());
            strQuery.append(" mn INNER JOIN mn.empleado em INNER JOIN mn.plazasPorEmpleado pem INNER JOIN mn.tipoCorrida tc INNER JOIN mn.tipoNomina tn INNER JOIN mn.razonesSociales rs ");
            strWhere = new StringBuilder("INNER JOIN mn.periodosNomina pn WHERE mn.uso = 0 AND pn.id = :ideriodoNomina AND pn.año = :yearPeriodo AND tc.clave = :claveTipoCorrida AND tn.clave = :claveTipoNomina AND em.clave = :claveEmpleado AND rs.clave = :claveRazonSocial AND pem.clave = :clavePlazaEmpleado ");
            camposParametro.add("ideriodoNomina");
            valoresParametro.add(ideriodoNomina);
            camposParametro.add("yearPeriodo");
            Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
            valoresParametro.add(fecha.get(Calendar.YEAR));

            camposParametro.add("claveTipoCorrida");
            valoresParametro.add(claveTipoCorrida);
            camposParametro.add("claveTipoNomina");
            valoresParametro.add(claveTipoNomina);
            camposParametro.add("claveEmpleado");
            valoresParametro.add(plazaPorEmpleado.getEmpleados().getClave());
            camposParametro.add("claveRazonSocial");
            valoresParametro.add(claveRazonSocial);
            camposParametro.add("clavePlazaEmpleado");
            valoresParametro.add(plazaPorEmpleado.getClave());

            strQuery.append(strWhere);
            strQuery.append(" order by rs.clave,em.clave,tn.clave,pn.clave,tc.clave,mn.numero,mn.ejercicio,mn.mes ");
            listCalculoUnidades = (List<CalculoUnidades>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);

            if (listCalculoUnidades.isEmpty()) {
                listCalculoUnidades.add(crearCalculoUnidades(plazaPorEmpleado));
                if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                    CalculoUnidades calculoUnidades = crearCalculoUnidades(plazaPorEmpleado);
                    Calendar fechaFinal = Calendar.getInstance();
                    fechaFinal.setTime(periodosNomina.getFechaFinal());
                    calculoUnidades.setMes(fechaFinal.get(Calendar.MONTH) + 1);
                    calculoUnidades.setNumMovParticion(2);
                    listCalculoUnidades.add(calculoUnidades);
                }
            } else if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                List<CalculoUnidades> listTmp = new ArrayList<CalculoUnidades>();
                Integer mesUno, mesDos;
                Calendar fechaInicio = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
                fechaInicio.setTime(periodosNomina.getFechaInicial());
                fechaFinal.setTime(periodosNomina.getFechaFinal());
                mesUno = fechaInicio.get(Calendar.MONTH) + 1;
                mesDos = fechaFinal.get(Calendar.MONTH) + 1;
                boolean mesUnoEncontrado, mesDosEncontrado;
                int i = 0;
                while (i < listCalculoUnidades.size()) {
                    mesUnoEncontrado = false;
                    mesDosEncontrado = false;
                    for (int j = 0; j < listCalculoUnidades.size(); j++) {
                        if (listCalculoUnidades.get(i).getNumero().equals(listCalculoUnidades.get(j).getNumero())) {
                            if (listCalculoUnidades.get(j).getMes().equals(mesUno)) {
                                mesUnoEncontrado = true;
                            } else if (listCalculoUnidades.get(j).getMes().equals(mesDos)) {
                                {
                                    mesDosEncontrado = true;
                                }
                            }

                        }
                    }
                    if (!mesUnoEncontrado || !mesDosEncontrado) {
                        CalculoUnidades newMov = crearCalculoUnidades(plazaPorEmpleado);
                        if (!mesUnoEncontrado) {
                            fechaInicio.setTime(newMov.getPeriodosNomina().getFechaInicial());
                            newMov.setMes(fechaInicio.get(Calendar.MONTH) + 1);
                            newMov.setNumMovParticion(1);
                        } else {//Existe el mesDos generare el mesUno
                            fechaInicio.setTime(newMov.getPeriodosNomina().getFechaFinal());
                            newMov.setMes(fechaInicio.get(Calendar.MONTH) + 1);
                            newMov.setNumMovParticion(2);
                        }
                        listTmp.add(newMov);
                    }
                    i++;
                }
                listCalculoUnidades.addAll(listTmp);
                Collections.sort(listCalculoUnidades, new Comparator() {

                    public int compare(Object o1, Object o2) {
                        CalculoUnidades e1 = (CalculoUnidades) o1;
                        CalculoUnidades e2 = (CalculoUnidades) o2;

                        int resultado = e1.getNumero().compareTo(e2.getNumero());
                        if (resultado != 0) {
                            return resultado;
                        }
                        resultado = Double.compare(e1.getEjercicio(), e2.getEjercicio());
                        if (resultado != 0) {
                            return resultado;
                        }
                        return Double.compare(e1.getMes(), e2.getMes());
                    }
                });
            }
        } catch (Exception ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerListCalculoUnidadesUtilizar()1_Error: ").append(ex));
        }
        return listCalculoUnidades;
    }

    private CalculoUnidades crearCalculoUnidades(PlazasPorEmpleado plazaPorEmpleado) {
        Calendar fechaPeriodo = Calendar.getInstance();
        CalculoUnidades calculoUnidades = new CalculoUnidades();
        calculoUnidades.setEmpleado(plazaPorEmpleado.getEmpleados());
        calculoUnidades.setPlazasPorEmpleado(plazaPorEmpleado);
        calculoUnidades.setPeriodosNomina(periodosNomina);
        calculoUnidades.setTipoCorrida(tipoCorrida);
        calculoUnidades.setTipoNomina(periodosNomina.getTipoNomina());
        calculoUnidades.setRazonesSociales(razonesSociales);
        calculoUnidades.setNumero(1);
        fechaPeriodo.setTime(calculoUnidades.getPeriodosNomina().getFechaInicial());
        calculoUnidades.setEjercicio(periodosNomina.getAño());
        calculoUnidades.setMes(fechaPeriodo.get(Calendar.MONTH) + 1);
        calculoUnidades.setNumMovParticion(1);
        calculoUnidades.setUso(0);
        return calculoUnidades;
    }

    private Integer obtenerPosicionCalculoUnidades(List<CalculoUnidades> listCalculoUnidades, MovNomConcep movNomConcep) {
        Integer posicion = 0;
        for (int i = 0; i < listCalculoUnidades.size(); i++) {
            if (movNomConcep.getNumero().equals(listCalculoUnidades.get(i).getNumero()) & movNomConcep.getNumMovParticion() == listCalculoUnidades.get(i).getNumMovParticion()) {
                posicion = i;
                break;
            }
        }
        return posicion;
    }

    private void ordenarMovimientosNomina() {
        Collections.sort(filtroMovimientosNominas, new Comparator() {

            public int compare(Object o1, Object o2) {
                MovNomConcep e1 = (MovNomConcep) o1;
                MovNomConcep e2 = (MovNomConcep) o2;
                return e1.getConcepNomDefi().getPrioridadDeCalculo().compareTo(e2.getConcepNomDefi().getPrioridadDeCalculo());
            }
        });
    }

    private Integer obtenerConceptosUnicos() {//JSA14
        List<ConcepNomDefi> conceptosUnicos = new ArrayList<ConcepNomDefi>();
        for (int i = 0; i < filtroMovimientosNominas.size(); i++) {
            if (!conceptosUnicos.contains(filtroMovimientosNominas.get(i).getConcepNomDefi())) {
                conceptosUnicos.add(filtroMovimientosNominas.get(i).getConcepNomDefi());
            }
        }
        return conceptosUnicos.size();
    }

    //Modificado
    private void buscaConceptosTipoAutomatico(String claveTipoCorrida) {
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT cdn FROM ConcepNomDefi cdn INNER JOIN cdn.conceptoPorTipoCorrida ctc INNER JOIN ctc.tipoCorrida tc  ");
            strWhere.delete(0, strWhere.length()).append(" WHERE tc.clave = :claveTipoCorrida AND cdn.activado = true ");
            camposParametro.add("claveTipoCorrida");
            valoresParametro.add(claveTipoCorrida);
//            if (usaNominaAsimiladosAsalarios) {
//                strWhere.append(" AND cdn.nominaAsimilados = :nominaAsimilados ");
//                camposParametro.add("nominaAsimilados");
//                valoresParametro.add(true);
//            }
            strWhere.append(" AND cdn.fecha =(SELECT MAX(fecha) FROM ConcepNomDefi c WHERE c.clave= cdn.clave) AND cdn.tipo = :automatico ");
            camposParametro.add("automatico");
            valoresParametro.add(Tipo.AUTOMATICO);
            strQuery.append(strWhere);

            filtroConceptosNomina.addAll((List<ConcepNomDefi>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0));
            if (mensajeResultado.getNoError() == 100) {
                mensajeResultado.setNoError(32);
                return;
            }
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(32);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaConceptosTipoAutomatico()1_Error: ").append(ex));
        }

    }

    //Modificado usando plazas
    private List<MovNomConcep> buscaMovimientosPlazasPorTipoYBaseAfecta(String claveTipoCorrida, String claveTipoNomina, Long idPeriodoNomina, PlazasPorEmpleado plazaPorEmpleado,
            String claveCtrCosto, Tipo tipo, String claveRazonSocial, String claveBaseNomina, int tipoBaseAfecta, String claveConcepto) {
        List<MovNomConcep> movNomConceptos = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT mn FROM MovNomConcep mn INNER JOIN mn.concepNomDefi con INNER JOIN mn.empleado em INNER JOIN mn.plazasPorEmpleado pem INNER JOIN mn.tipoCorrida tc INNER JOIN mn.tipoNomina tn INNER JOIN mn.razonesSociales rs ");
            boolean usaBaseNomina = false;
            if (claveBaseNomina != null & tipoBaseAfecta > -1) {
                usaBaseNomina = true;
                strQuery.append("INNER JOIN mn.movNomBaseAfecta mba INNER JOIN  mba.baseAfecConcepNom bac INNER JOIN bac.baseNomina bn ");
            }
            //si no usa tipo de concepto
            if (tipo == null) {
                strWhere = new StringBuilder("INNER JOIN mn.periodosNomina pn WHERE mn.uso = 0 AND pn.id = :idPeriodoNomina AND tc.clave = :claveTipoCorrida AND tn.clave = :claveTipoNomina AND em.clave = :claveEmpleado AND rs.clave = :claveRazonSocial AND pem.clave = :clavePlazaEmpleado ");
                camposParametro.add("idPeriodoNomina");
                valoresParametro.add(idPeriodoNomina);
            } else if (tipo == Tipo.REPETITIVO) {
                strWhere.delete(0, strWhere.length()).append("INNER JOIN mn.periodosNomina pn WHERE tc.clave = :claveTipoCorrida AND tn.clave = :claveTipoNomina AND em.clave = :claveEmpleado AND mn.uso = 0 AND rs.clave = :claveRazonSocial AND pem.clave = :clavePlazaEmpleado AND pn.tipoCorrida.clave = :claveTipoCorrida ");
                //.append("AND (mn.fechaIni Is Null or  mn.fechaCierr is NULL) or (current_date() between mn.fechaIni and mn.fechaCierr)");

                /*
                 * SELECT mn FROM MovNomConcep mn INNER JOIN mn.concepNomDefi
                 * con INNER JOIN mn.periodosNomina pn INNER JOIN mn.empleado em
                 * WHERE con.tipo = 2 AND em.clave = 1 And (mn.fechaIni Is Null
                 * or mn.fechaCierr is NULL) or (current_date() between
                 * mn.fechaIni and mn.fechaCierr)
                 */
            } else {
                strWhere.delete(0, strWhere.length()).append("INNER JOIN mn.periodosNomina pn WHERE mn.uso = 0 AND pn.id = :idPeriodoNomina AND tc.clave = :claveTipoCorrida AND tn.clave = :claveTipoNomina AND em.clave = :claveEmpleado and con.tipo = :tipo AND rs.clave = :claveRazonSocial AND pem.clave = :clavePlazaEmpleado ");
                camposParametro.add("idPeriodoNomina");
                valoresParametro.add(idPeriodoNomina);
            }
            if (tipo != null) {
                strWhere.append("AND con.tipo = :tipo ");
                camposParametro.add("tipo");
                valoresParametro.add(tipo);
            }
            if (usaBaseNomina) {
                strWhere.append("AND bn.clave = :claveBaseNomina AND bac.tipoAfecta = :tipoBaseAfecta ");
                camposParametro.add("claveBaseNomina");
                valoresParametro.add(claveBaseNomina);
                camposParametro.add("tipoBaseAfecta");
                valoresParametro.add(tipoBaseAfecta);
            }
//            if (usaNominaAsimiladosAsalarios) {
//                strWhere.append("AND con.nominaAsimilados = :nominaAsimilados ");
//                camposParametro.add("nominaAsimilados");
//                valoresParametro.add(true);
//            }
            camposParametro.add("claveTipoCorrida");
            valoresParametro.add(claveTipoCorrida);
            camposParametro.add("claveTipoNomina");
            valoresParametro.add(claveTipoNomina);
            camposParametro.add("claveEmpleado");
            valoresParametro.add(plazaPorEmpleado.getEmpleados().getClave());
            camposParametro.add("claveRazonSocial");
            valoresParametro.add(claveRazonSocial);
            camposParametro.add("clavePlazaEmpleado");
            valoresParametro.add(plazaPorEmpleado.getClave());

            claveCtrCosto = (claveCtrCosto == null ? "" : claveCtrCosto);
            if (!claveCtrCosto.isEmpty()) {
                strQuery.append("LEFT OUTER JOIN mn.centroDeCosto cc ");
                strWhere.append("AND cc.clave = :claveCtrCosto ");
                camposParametro.add("claveCtrCosto");
                valoresParametro.add(claveCtrCosto);
            }
            claveConcepto = (claveConcepto == null ? "" : claveConcepto);
            if (!claveConcepto.isEmpty()) {
                strWhere.append(" AND con.clave = :claveConcepto ");
                camposParametro.add("claveConcepto");
                valoresParametro.add(claveConcepto);
            }
//            //Se agrego este codigo para que no se agregaran los conceptos que ya fueron asignados por los creditos o ahorros
//            strWhere.append(" AND mn.id not in  (select mov.id from CreditoMovimientos credMov inner join credMov.movNomConcep mov ");
//            strWhere.append(" inner join credMov.creditoPorEmpleado credEm inner join credEm.empleados em inner join credEm.razonesSociales rs ");
//            strWhere.append(" where em.clave = :claveEmpleado and rs.clave = :claveRazonSocial) ");
            strQuery.append(strWhere);
            if (tipo == Tipo.REPETITIVO) {
                strQuery.append(" AND (pn.clave <= :clavePeriodoNomina AND pn.año = :yearPeriodo AND pn.tipoCorrida.clave = :claveTipoCorrida) Order by con.id, pn.id");
                camposParametro.add("clavePeriodoNomina");
                valoresParametro.add(periodosNomina.getClave());
                camposParametro.add("yearPeriodo");
                valoresParametro.add(periodosNomina.getAño());
            }

            movNomConceptos = (List<MovNomConcep>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);

            if (mensajeResultado.getNoError() == - 100) {
                if (tipo == Tipo.AUTOMATICO) {
                    mensajeResultado.setNoError(29);
                } else if (tipo == Tipo.PERIODO) {
                    mensajeResultado.setNoError(30);
                } else {
                    mensajeResultado.setNoError(31);
                }
                return null;
            }
            movNomConceptos = (movNomConceptos == null ? new ArrayList<MovNomConcep>(0) : movNomConceptos);
            if (movNomConceptos.isEmpty() | tipo == Tipo.REPETITIVO) {
                //codigi cardex nomina
                /**
                 * *********************FILTRA REPETITIVOS
                 * VALIDOS*****************************
                 */
                if (tipo == Tipo.REPETITIVO & !movNomConceptos.isEmpty()) {
                    //DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
                    int i;
                    List<MovNomConcep> movimientosRepetitivos = new ArrayList<MovNomConcep>(0);
                    MovNomConcep movRepetitivo;
//                    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//                    Collections.sort(movNomConceptos, new Comparator() {
//
//                        public int compare(Object o1, Object o2) {
//                            MovNomConcep e1 = (MovNomConcep) o1;
//                            MovNomConcep e2 = (MovNomConcep) o2;
//                            return e1.getPeriodosNomina().getId().compareTo(e2.getPeriodosNomina().getId());
//                        }
//                    });
                    for (i = 0; i < movNomConceptos.size(); i++) {
                        movRepetitivo = movNomConceptos.get(i);
//                        System.out.println("concep clave " + movRepetitivo.getConcepNomDefi().getClave() + " descripcion " + movRepetitivo.getConcepNomDefi().getDescripcion());
//                        System.out.println("fechaActual " + formato.format(fechaActual.getTime()) + " getFechaIni " + formato.format(movRepetitivo.getFechaIni()) + " getFechaCierr " + formato.format(movRepetitivo.getFechaCierr()));
                        if ((fechaActual.getTime().after(movRepetitivo.getFechaIni()) | fechaActual.getTime().equals(movRepetitivo.getFechaIni()))
                                && (fechaActual.getTime().before(movRepetitivo.getFechaCierr()) | fechaActual.getTime().equals(movRepetitivo.getFechaCierr()))) {
                            if (movimientosRepetitivos.size() >= 1) {
                                int x = 0;
                                boolean noExisteMov = true;
                                while (x < movimientosRepetitivos.size()) {
                                    //movimientosRepetitivos.get(x).getId() < movRepetitivo.getId() & 
                                    if (movimientosRepetitivos.get(x).getConcepNomDefi().getClave().equalsIgnoreCase(movRepetitivo.getConcepNomDefi().getClave())) {
                                        noExisteMov = false;
                                        if (movRepetitivo.getPeriodosNomina().getId().equals(periodosNomina.getId())) {
                                            if (movimientosRepetitivos.get(x).getPeriodosNomina().getId().equals(periodosNomina.getId())) {
                                                movimientosRepetitivos.add(movRepetitivo);
                                            } else {
                                                movimientosRepetitivos.set(x, movRepetitivo);
                                            }
                                            break;
                                        }
                                        break;
                                    }
                                    x++;
                                }
                                if (noExisteMov) {
                                    movimientosRepetitivos.add(movRepetitivo);
                                }
                            } else {
                                movimientosRepetitivos.add(movRepetitivo);
                            }
                        } else //si no pasa y existe mismo concepto remplazarlo
                        if (movimientosRepetitivos.size() >= 1) {
                            int x = 0;
                            boolean noExisteMov = true;
                            while (x < movimientosRepetitivos.size()) {
//                                    System.out.println("ID x " + movimientosRepetitivos.get(x).getId() + " < " + movRepetitivo.getId());
//                                    System.out.println("Concep x " + movimientosRepetitivos.get(x).getConcepNomDefi().getClave() + " == " + movRepetitivo.getConcepNomDefi().getClave());
//                                    System.out.println("Periodo x " + movRepetitivo.getPeriodosNomina().getId() + " == " + periodosNomina.getId());
                                ///movimientosRepetitivos.get(x).getId() < movRepetitivo.getId() &
                                if (movimientosRepetitivos.get(x).getConcepNomDefi().getClave().equalsIgnoreCase(movRepetitivo.getConcepNomDefi().getClave())) {
                                    noExisteMov = false;
                                    if (movRepetitivo.getPeriodosNomina().getId().equals(periodosNomina.getId())) {
                                        if (movimientosRepetitivos.get(x).getPeriodosNomina().getId().equals(periodosNomina.getId())) {
                                            movimientosRepetitivos.add(movRepetitivo);
                                        } else {
                                            movimientosRepetitivos.set(x, movRepetitivo);
                                        }
                                        break;
                                    }
                                    break;
                                }
                                x++;
                            }
                            if (noExisteMov) {
                                movimientosRepetitivos.add(movRepetitivo);
                            }
                        } else {
                            movimientosRepetitivos.add(movRepetitivo);
                        }
                    }
                    movNomConceptos = movimientosRepetitivos;
                    for (i = 0; i < movNomConceptos.size(); i++) {
                        if (!movNomConceptos.get(i).getPeriodosNomina().getId().equals(periodosNomina.getId())) {
                            MovNomConcep mnc = movNomConceptos.get(i);
                            movNomConceptos.set(i, creaMovNomConcep(movNomConceptos.get(i).getConcepNomDefi(), plazaPorEmpleado, periodosNomina, tipoCorrida, razonesSociales, centroDeCostoMovimiento));
                            if (mnc.getMovNomConceParam() != null) {
                                int j;
                                for (j = 0; j < mnc.getMovNomConceParam().size(); j++) {
                                    movNomConceptos.get(i).getMovNomConceParam().get(j).setValor(mnc.getMovNomConceParam().get(j).getValor());
                                }
                            }

                        }
                    }
                }
                /*
                 * ********************TERMINA FILTRAdo REPETITIVOS VALIDOS
                 */
            }
            camposParametro = null;
            valoresParametro = null;
//            //strQuery = null;
            //strWhere = null;
        } catch (Exception ex) {
            mensajeResultado.setError(ex.getMessage());
            if (tipo == Tipo.AUTOMATICO) {
                mensajeResultado.setNoError(29);
            } else if (tipo == Tipo.PERIODO) {
                mensajeResultado.setNoError(30);
            } else {
                mensajeResultado.setNoError(31);
            }
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaMovimientosTipoPeriodo()1_Error: ").append(ex));
        }
        return (movNomConceptos == null ? new ArrayList<MovNomConcep>(0) : movNomConceptos);
    }

    //Modificado usando plazas
    private List<MovNomConcep> obtenerMovimientosPlazasFiniquitos(String claveTipoCorrida, String claveTipoNomina, Long idPeriodoNomina, PlazasPorEmpleado plazaPorEmpleado,
            String claveCtrCosto, String claveRazonSocial, int uso) {
        List<MovNomConcep> movNomConceptos = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            if (finiquitosLiquidaciones == null) {
                strQuery.delete(0, strQuery.length()).append("SELECT mn FROM MovNomConcep mn INNER JOIN mn.empleado em INNER JOIN mn.plazasPorEmpleado pem INNER JOIN mn.tipoCorrida tc INNER JOIN mn.tipoNomina tn INNER JOIN mn.periodosNomina pn INNER JOIN mn.razonesSociales rs ");
                strWhere.delete(0, strWhere.length()).append(" WHERE mn.uso = :uso AND pn.id = :idPeriodoNomina AND tc.clave = :claveTipoCorrida AND tn.clave = :claveTipoNomina AND em.clave = :claveEmpleado AND rs.clave = :claveRazonSocial AND pem.clave = :clavePlazaEmpleado ");
                camposParametro.add("idPeriodoNomina");
                valoresParametro.add(idPeriodoNomina);
                camposParametro.add("claveTipoCorrida");
                valoresParametro.add(claveTipoCorrida);
                camposParametro.add("claveTipoNomina");
                valoresParametro.add(claveTipoNomina);
                camposParametro.add("claveEmpleado");
                valoresParametro.add(plazaPorEmpleado.getEmpleados().getClave());
                camposParametro.add("claveRazonSocial");
                valoresParametro.add(claveRazonSocial);
                camposParametro.add("clavePlazaEmpleado");
                valoresParametro.add(plazaPorEmpleado.getClave());
                camposParametro.add("uso");
                valoresParametro.add(uso);
//                if (usaNominaAsimiladosAsalarios) {
//                    strWhere.append("AND mn.concepNomDefi.nominaAsimilados = :nominaAsimilados ");
//                    camposParametro.add("nominaAsimilados");
//                    valoresParametro.add(true);
//                }
                claveCtrCosto = (claveCtrCosto == null ? "" : claveCtrCosto);
                if (!claveCtrCosto.isEmpty()) {
                    strQuery.append("INNER JOIN mn.centroDeCosto cc ");
                    strWhere.append("AND cc.clave = :claveCtrCosto ");
                    camposParametro.add("claveCtrCosto");
                    valoresParametro.add(claveCtrCosto);
                }
            } else {
                strQuery.delete(0, strQuery.length()).append("SELECT mn FROM MovNomConcep mn INNER JOIN mn.finiqLiquidCncNom finiCnc INNER JOIN finiCnc.finiquitosLiquidacion fini ");
                strWhere.delete(0, strWhere.length()).append(" WHERE fini.id = :finiquitosLiquidacion_ID ");
                camposParametro.add("finiquitosLiquidacion_ID");
                valoresParametro.add(finiquitosLiquidaciones.getId());
            }
            strWhere.append("Order By mn.concepNomDefi.prioridadDeCalculo ");
            strQuery.append(strWhere);
            movNomConceptos = (List<MovNomConcep>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);

            if (mensajeResultado.getNoError() == - 100) {
                mensajeResultado.setNoError(31);
                return null;
            }
            camposParametro = null;
            valoresParametro = null;
//            //strQuery = null;
            //strWhere = null;
        } catch (Exception ex) {
            mensajeResultado.setError(ex.getMessage());
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaMovimientosTipoPeriodo()1_Error: ").append(ex));
        }
        return (movNomConceptos == null ? new ArrayList<MovNomConcep>(0) : movNomConceptos);
    }

    //checado
    private Double calculaImss(Double salarioDiarioIntegrado, Double salarioMinimoDF, MovNomConcep movNominaImss, PlazasPorEmpleadosMov plazasPorEmpleadosMov) {
        Double acumuladoIMSS = 0.0;
        List<SalariosIntegrados> listSalariosIntegrados = null;
        try {
            Double valorEspecieEnfermeMater = 0.0, valorDineEnfermeMater = 0.0, valorGastosPension = 0.0, valorInvalidezVida = 0.0, valorCesantiaVejez = 0.0;
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("FROM CalculoIMSS imss WHERE imss.movNomConcep.id = :idMovNom ");
            camposParametro.add("idMovNom");
            valoresParametro.add(movNominaImss.getId());
            listCalculoIMSS = (List<CalculoIMSS>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            if (listCalculoIMSS == null ? true : listCalculoIMSS.isEmpty() ? true : false) {
                if (listCalculoIMSS == null) {
                    listCalculoIMSS = new ArrayList<CalculoIMSS>();
                }
                CalculoIMSS calculoIMSS = new CalculoIMSS();
                calculoIMSS.setConfiguracionIMSS(configuracionIMSS);
                listCalculoIMSS.add(calculoIMSS);
            }

            if (salarioDiarioIntegrado > 0) {
                if ((Double.parseDouble(valoresConceptosEmpleados.get("SUELDODIARIO".toUpperCase()).toString())) > salarioMinimoDF) {
                    try {
                        boolean buscaSDIAnterior = true;
                        if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                            Calendar cFecha = Calendar.getInstance();
                            if (movNominaImss.getNumMovParticion() == 2) {
                                cFecha.setTime(periodosNomina.getFechaFinal());
                                cFecha.set(Calendar.DATE, 1);
                                listSalariosIntegrados = obtenerSalariosIntegradosDentroDelPeriodo(cFecha.getTime(), periodosNomina.getFechaFinal(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());
                                buscaSDIAnterior = false;
                            } else {
                                cFecha.setTime(periodosNomina.getFechaInicial());
                                cFecha.set(Calendar.DATE, cFecha.getActualMaximum(Calendar.DATE));
                                listSalariosIntegrados = obtenerSalariosIntegradosDentroDelPeriodo(periodosNomina.getFechaInicial(), cFecha.getTime(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());
                                if (listSalariosIntegrados.isEmpty()) {
                                    buscaSDIAnterior = false;
                                    listSalariosIntegrados.addAll(0, obtenerAnteriorSalariosIntegrados(periodosNomina.getFechaInicial(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave()));
                                }
                            }
                        } else {
                            listSalariosIntegrados = obtenerSalariosIntegradosDentroDelPeriodo(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());
                        }
                        if (!listSalariosIntegrados.isEmpty()) {
                            if (listSalariosIntegrados.get(0).getFecha().compareTo(periodosNomina.getFechaInicial()) > 0 & buscaSDIAnterior) {
                                listSalariosIntegrados.addAll(0, obtenerAnteriorSalariosIntegrados(periodosNomina.getFechaInicial(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave()));
                            }
                        } else {
                            SalariosIntegrados salariosIntegrados = new SalariosIntegrados();
                            salariosIntegrados.setSalarioDiarioIntegrado(salarioDiarioIntegrado);
                            listSalariosIntegrados.add(salariosIntegrados);
                        }
                        int i;
                        if (listCalculoIMSS.size() < listSalariosIntegrados.size()) {
                            int cantidad = listSalariosIntegrados.size() - listCalculoIMSS.size();
                            for (i = 0; i < cantidad; i++) {
                                CalculoIMSS calculoIMSS = new CalculoIMSS();
                                calculoIMSS.setConfiguracionIMSS(configuracionIMSS);
                                listCalculoIMSS.add(calculoIMSS);
                            }
                        }
                        for (i = 0; i < listSalariosIntegrados.size(); i++) {
                            salarioDiarioIntegrado = listSalariosIntegrados.get(i).getSalarioDiarioIntegrado();
                            if (listSalariosIntegrados.size() > 1) {
                                //<editor-fold defaultstate="collapsed" desc="programacion para cuando existan modificaciones salariales">
                                if (i + 1 <= listSalariosIntegrados.size() - 1) {
                                    cargaValoresDiasCotizados(listSalariosIntegrados.get(i).getFecha(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), false, listSalariosIntegrados.get(i + 1), null, false, false);//JSA30
                                } else {
                                    cargaValoresDiasCotizados(listSalariosIntegrados.get(i).getFecha(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), false, null, null, false, false);//JSA30
                                }
//</editor-fold>
                            } else {
                                cargaValoresDiasCotizados(plazasPorEmpleadosMov.getFechaIMSS(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), true, null, null, false, false);//JSA30
                            }
                            if ((salarioMinimoDF * configuracionIMSS.getExcedenteEspecie()) > salarioDiarioIntegrado) {
                                valorEspecieEnfermeMater = 0.0;
                            } else {
                                valorEspecieEnfermeMater = ((configuracionIMSS.getTasaEspecieEnfermeMater() / 100) * (salarioDiarioIntegrado - (salarioMinimoDF * configuracionIMSS.getExcedenteEspecie())));
                            }
                            valorDineEnfermeMater = ((configuracionIMSS.getTasaDineEnfermeMater() / 100) * salarioDiarioIntegrado);
                            valorGastosPension = ((configuracionIMSS.getTasaGastosPension() / 100) * salarioDiarioIntegrado);
                            valorInvalidezVida = ((configuracionIMSS.getTasaInvalidezVida() / 100) * salarioDiarioIntegrado);
                            valorCesantiaVejez = ((configuracionIMSS.getTasaCesantiaVejez() / 100) * salarioDiarioIntegrado);
                            acumuladoIMSS += ((valorEspecieEnfermeMater + valorDineEnfermeMater + valorGastosPension + valorInvalidezVida + valorCesantiaVejez) * (Double.parseDouble(valoresConceptosEmpleados.get("DiasCotizados".toUpperCase()).toString())));//JSA08;
//                          System.out.println("CALCULO IMSS " + (valorEspecieEnfermeMater + valorDineEnfermeMater + valorGastosPension + valorInvalidezVida + valorCesantiaVejez) + " DIAS COTIZADOS " + (valoresConceptosEmpleados.get("DiasCotizados".toUpperCase()).toString()));
                            listCalculoIMSS.get(i).setValorEspecieEnfermeMater(valorEspecieEnfermeMater);
                            listCalculoIMSS.get(i).setValorDineEnfermeMater(valorDineEnfermeMater);
                            listCalculoIMSS.get(i).setValorGastosPension(valorGastosPension);
                            listCalculoIMSS.get(i).setValorInvalidezVida(valorInvalidezVida);
                            listCalculoIMSS.get(i).setValorCesantiaVejez(valorCesantiaVejez);
                            listCalculoIMSS.get(i).setDiasCotizados((Double.parseDouble(valoresConceptosEmpleados.get("DiasCotizados".toUpperCase()).toString())));
                        }
                        if (listSalariosIntegrados.size() > 1) {
                            cargaValoresDiasCotizados(listSalariosIntegrados.get(0).getFecha(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getFechaFinal(), true, null, null, false, false);//JSA30
                        }
                    } catch (HibernateException ex) {
                        mensajeResultado.setNoError(88);
                        mensajeResultado.setError(concatena.delete(0, concatena.length()).append(msgError).append("calculaImss()1_Error: ").append(ex).toString());
                        System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calculaImss()1_Error: ").append(ex));
                    }
                    if (listCalculoIMSS.size() > listSalariosIntegrados.size()) {
                        List<Object> clavesMovEliminados = new ArrayList<Object>();
                        int limite = listCalculoIMSS.size() - listSalariosIntegrados.size();
                        for (int j = 0; j < limite; j++) {
                            if (listCalculoIMSS.get(listCalculoIMSS.size()).getId() != null) {
                                clavesMovEliminados.add(listCalculoIMSS.get(listCalculoIMSS.size()).getId());
                            }
                            listCalculoIMSS.remove(listCalculoIMSS.size());
                        }
                        if (!clavesMovEliminados.isEmpty()) {
                            consulta = new StringBuilder();
                            consulta.append("delete ");
                            //Elimina Movimientos CalculoIMSS
                            consulta.append(CalculoIMSS.class.getSimpleName()).append(" o where o.movNomConcep.id in(:valores)");
                            q = getSession().createQuery(consulta.toString());
                            q.setParameterList("valores", clavesMovEliminados.toArray());
                            q.executeUpdate();
//                            System.out.println("flush 15");
                            getSession().flush();
                        }
                    }
                }
            }

            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(50);
            mensajeResultado.setError(ex.getMessage());
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calculaImss()2_Error: ").append(ex));
        }
        return acumuladoIMSS.doubleValue();
    }

    private Double calculaImssPatronal(Double salarioDiarioIntegrado, Double salarioMinimoDF, MovNomConcep movNominaImss, PlazasPorEmpleadosMov plazasPorEmpleadosMov) {//JSA03
        Double calculoImss = 0.0;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("FROM CalculoIMSSPatron imss WHERE imss.movNomConcep.id = :idMovNom AND imss.configuracionIMSS.id = :idConfigIMSS");
            camposParametro.add("idMovNom");
            valoresParametro.add(movNominaImss.getId());
            camposParametro.add("idConfigIMSS");
            valoresParametro.add(configuracionIMSS.getId());

            calculoIMSSPatron = (CalculoIMSSPatron) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (calculoIMSSPatron == null) {
                calculoIMSSPatron = new CalculoIMSSPatron();
                calculoIMSSPatron.setConfiguracionIMSS(configuracionIMSS);
            }
            if (salarioDiarioIntegrado > 0) {
                try {
                    if (evaluaPeriodoMovAbarca2Meses(periodosNomina)) {
                        Calendar cFecha = Calendar.getInstance();
                        if (movNominaImss.getNumMovParticion() == 2) {
                            cFecha.setTime(periodosNomina.getFechaFinal());
                            cFecha.set(Calendar.DATE, 1);
                            SalariosIntegrados salariosIntegrados = obtenerSalariosIntegradosActual(periodosNomina.getFechaFinal(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());
                            if (salariosIntegrados != null) {
                                salarioDiarioIntegrado = salariosIntegrados.getSalarioDiarioIntegrado();
                            }
                        } else {
                            cFecha.setTime(periodosNomina.getFechaInicial());
                            cFecha.set(Calendar.DATE, cFecha.getActualMaximum(Calendar.DATE));
                            SalariosIntegrados salariosIntegrados = obtenerSalariosIntegradosActual(cFecha.getTime(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRazonesSociales().getClave());
                            if (salariosIntegrados != null) {
                                salarioDiarioIntegrado = salariosIntegrados.getSalarioDiarioIntegrado();
                            }
                        }
                    }

                    calculoIMSSPatron.setValorTasaFijaPatron((configuracionIMSS.getTasaFijaPatron() / 100) * salarioMinimoDF);
                    if ((salarioMinimoDF * configuracionIMSS.getTasaExcedentePatron()) > salarioDiarioIntegrado) {
                        calculoIMSSPatron.setValorTasaExcedentePatron(0.0);
                    } else {
                        calculoIMSSPatron.setValorTasaExcedentePatron((configuracionIMSS.getTasaExcedentePatron() / 100) * (salarioDiarioIntegrado - (salarioMinimoDF * configuracionIMSS.getTasaExcedentePatron())));
                    }
                    calculoIMSSPatron.setValorTasaPrestDinePatron((configuracionIMSS.getTasaPrestDinePatron() / 100) * salarioDiarioIntegrado);
                    calculoIMSSPatron.setValorTasaInvaliVidaPatron((configuracionIMSS.getTasaInvaliVidaPatron() / 100) * salarioDiarioIntegrado);
                    calculoIMSSPatron.setValorTasaCesanVejezPatron((configuracionIMSS.getTasaCesanVejezPatron() / 100) * salarioDiarioIntegrado);
                    calculoIMSSPatron.setValorTasaGastosPensPatron((configuracionIMSS.getTasaGastosPensPatron() / 100) * salarioDiarioIntegrado);
                    calculoIMSSPatron.setValorTasaRiesgosPatron((configuracionIMSS.getTasaRiesgosPatron() / 100) * salarioDiarioIntegrado);
                    calculoIMSSPatron.setValorTasaGuarderiaPatron((configuracionIMSS.getTasaGuarderiaPatron() / 100) * salarioDiarioIntegrado);
                    calculoIMSSPatron.setValorTasaAportacionRetiroPatron((configuracionIMSS.getTasaAportacionRetiroPatron() / 100) * salarioDiarioIntegrado);
                    calculoIMSSPatron.setValorTasaAportacionInfonavitPatron((configuracionIMSS.getTasaAportacionInfonavitPatron() / 100) * salarioDiarioIntegrado);
                    calculoImss = calculoIMSSPatron.getValorTasaFijaPatron() + calculoIMSSPatron.getValorTasaExcedentePatron() + calculoIMSSPatron.getValorTasaPrestDinePatron() + calculoIMSSPatron.getValorTasaInvaliVidaPatron()
                            + calculoIMSSPatron.getValorTasaCesanVejezPatron() + calculoIMSSPatron.getValorTasaGastosPensPatron() + calculoIMSSPatron.getValorTasaRiesgosPatron() + calculoIMSSPatron.getValorTasaGuarderiaPatron()
                            + calculoIMSSPatron.getValorTasaAportacionRetiroPatron() + calculoIMSSPatron.getValorTasaAportacionInfonavitPatron();

                    camposParametro = null;
                    valoresParametro = null;
                } catch (HibernateException ex) {
                    mensajeResultado.setNoError(88);
                    mensajeResultado.setError(concatena.delete(0, concatena.length()).append(msgError).append("creaCalculoIMSSPatronal()2_Error: ").append(ex).toString());
                    System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("creaCalculoIMSSPatronal()2_Error: ").append(ex));
                }
                calculoImss = calculoImss * ((Integer) valoresConceptosEmpleados.get("DiasCotizados".toUpperCase()));// - calculoDiasAusenciaIncapacidad() - diasDif);
            } else {
                calculoIMSSPatron.setValorTasaFijaPatron(0.0);
                calculoIMSSPatron.setValorTasaExcedentePatron(0.0);
                calculoIMSSPatron.setValorTasaPrestDinePatron(0.0);
                calculoIMSSPatron.setValorTasaInvaliVidaPatron(0.0);
                calculoIMSSPatron.setValorTasaCesanVejezPatron(0.0);
                calculoIMSSPatron.setValorTasaGastosPensPatron(0.0);
                calculoIMSSPatron.setValorTasaRiesgosPatron(0.0);
                calculoIMSSPatron.setValorTasaGuarderiaPatron(0.0);
                calculoIMSSPatron.setValorTasaAportacionRetiroPatron(0.0);
                calculoIMSSPatron.setValorTasaAportacionInfonavitPatron(0.0);
            }
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(50);
            mensajeResultado.setError(ex.getMessage());
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calculaImssPatronal()1_Error: ").append(ex));
        }
        return calculoImss.doubleValue();
    }

    //checado NORMAL Y ANUAL
    private double calculaISPTSeparado(MovNomConcep movimientosNomina) {
        Object[][] acumulados = acumuladosPorTipoISR();
        retenido = new CalculoISR();
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        double acumNorm = 0.0, acumDir = 0.0, acumAnu = 0.0;
        double dias;
        if (tipoTablaISR != TipoTablaISR.PERIODICIDAD) {
            int i;
            for (i = 0; i < acumulados.length; i++) {
                if (acumulados[i][1].equals(0)) {
                    acumNorm = (Double) acumulados[0][1];
                } else if (acumulados[i][1].equals(1)) {
                    acumDir = (Double) acumulados[0][1];
                } else if (acumulados[i][1].equals(2)) {
                    acumAnu = (Double) acumulados[0][1];
                }
            }
            dias = 30.4;
        } else {
            dias = periodosNomina.getTipoNomina().getPeriodicidad().getDias().doubleValue();
        }
        //calculoDiasDepago();
        //double baseGravableNormal = ((acumuladoNormal + (acumulados.isEmpty() ? 0 : acumulados.get(0))) / diasPago) * factorMensual;
        double baseGravableNormal = calculoISRNormal(acumNorm, movimientosNomina);
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        calculoISRDirecto(baseGravableNormal, acumDir, movimientosNomina.getTipoCorrida());
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        calculoISRAnual(baseGravableNormal, acumuladoDirecto + (acumDir), acumAnu, dias, movimientosNomina.getTipoCorrida());
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        isrNormal = 0;
        isrNormalSinAjustar = 0;
        isrACargoNormalSinAjustar = 0;
        subsidioAlEmpleoNormalSinAjustar = 0;
        if (movimientosNomina != null) {
            IsrRetenidos(movimientosNomina);
            if (mensajeResultado.getNoError() != 0) {
                return 0.0;
            }
        }
        return isrNormal + isrDirecto + isrAnual;
    }

    //checado NORMAL, DIRECTO y ANUAL
    private double calculaISPT(MovNomConcep movimientosNomina) {
        retenido = new CalculoISR();
        Object[][] acumulados;
        double acumNorm = 0.0, acumDir = 0.0, acumAnu = 0.0;
        double dias;
        if (tipoTablaISR != TipoTablaISR.PERIODICIDAD) {
            acumulados = acumuladosPorTipoISR();
            if (mensajeResultado.getNoError() != 0) {
                return 0.0;
            }
            int i;
            for (i = 0; i < acumulados.length; i++) {
                if (acumulados[i][1].equals(0)) {
                    acumNorm = (Double) acumulados[i][0];
                } else if (acumulados[i][1].equals(1)) {
                    acumDir = (Double) acumulados[i][0];
                } else if (acumulados[i][1].equals(2)) {
                    acumAnu = (Double) acumulados[i][0];
                }
            }
            dias = 30.4;
        } else {
            dias = periodosNomina.getTipoNomina().getPeriodicidad().getDias().doubleValue();
        }

        double baseGravableNormal = calculoISRNormal(acumNorm, movimientosNomina);
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        calculoISRDirecto(baseGravableNormal, acumDir, movimientosNomina.getTipoCorrida());
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        calculoISRAnual(baseGravableNormal, acumuladoDirecto + acumDir, acumAnu, dias, movimientosNomina.getTipoCorrida());
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        if (movimientosNomina != null) {
            IsrRetenidos(movimientosNomina);
            if (mensajeResultado.getNoError() != 0) {
                return 0.0;
            }
        }
        return isrNormal + isrDirecto + isrAnual;
    }

    /*
     * suma acumulados de periodos regresando los acumulados segun el tipo de
     * ISR modificado agrego razon social y plaza empleado
     */
    private Object[][] acumuladosPorTipoISR() {
        strQuery.delete(0, strQuery.length()).append("SELECT SUM(CASE WHEN (c.naturaleza = 0) THEN CASE WHEN (mba.resultado is NULL) THEN 0.0 ELSE (mba.resultado * 1.0) END ELSE 0.0 END) - ");//JSA28
        strQuery.append("SUM(CASE WHEN (c.naturaleza = 1) THEN CASE WHEN (mba.resultado is NULL) THEN 0.0 ELSE (mba.resultado * 1.0) END ELSE 0.0 END) ");
        strQuery.append(", ba.tipoAfecta FROM MovNomConcep m INNER JOIN m.periodosNomina p INNER JOIN m.empleado em INNER JOIN m.concepNomDefi c INNER JOIN m.tipoCorrida tc INNER JOIN m.movNomBaseAfecta mba INNER JOIN mba.baseAfecConcepNom ba INNER JOIN ba.baseNomina bn INNER JOIN m.tipoNomina t INNER JOIN m.razonesSociales rs INNER JOIN m.plazasPorEmpleado pemp ");
        if (HibernateUtil.usaTypeBigInt) {
            strQuery.append("WHERE m.uso = 0 AND (p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as int)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn WHERE pn.clave < cast(:clavePeriodoNomina as int) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND pn.tipoCorrida.clave = :claveTipoCorrida) AND p.clave < CAST(:clavePeriodoNomina as int)) ");
        } else {
            strQuery.append("WHERE m.uso = 0 AND (p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as long)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn WHERE pn.clave < cast(:clavePeriodoNomina as long) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND pn.tipoCorrida.clave = :claveTipoCorrida) AND p.clave < CAST(:clavePeriodoNomina as long)) ");
        }

        strQuery.append("AND em.clave = :claveEmp  AND tc.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND p.tipoCorrida.clave = :claveTipoCorrida AND t.clave = :claveTipoNomina AND bn.clave = :claveBaseNomina AND rs.clave = :claveRazonSocial AND pemp.clave = :clavePlazaEmpleado GROUP BY ba.tipoAfecta");
        camposParametro = new ArrayList<String>(0);
        valoresParametro = new ArrayList<Object>(0);
        camposParametro.add("claveEmp");
        camposParametro.add("claveTipoCorrida");
        camposParametro.add("clavePeriodoNomina");
        camposParametro.add("claveTipoNomina");
        camposParametro.add("claveBaseNomina");
        camposParametro.add("claveRazonSocial");
        camposParametro.add("clavePlazaEmpleado");
        camposParametro.add("yearPeriodo");
        valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("NumPeriodo".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
        valoresParametro.add(ClavesParametrosModulos.claveBaseNominaISR);//baseNomina ISR
        valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("PlazaEmpleado".toUpperCase()));
        valoresParametro.add(getFechaDelSistema().get(Calendar.YEAR));
        List valores = (List) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
        if (mensajeResultado.getNoError() == -100) {
            mensajeResultado.setNoError(44);
            return null;
        }
        int i, j;
        Object[][] acumulados = new Object[valores.size()][2];
        Object[] acumulad;
        for (i = 0; i < valores.size(); i++) {
            acumulad = (Object[]) valores.get(i);
            for (j = 0; j < acumulad.length; j++) {
                acumulados[i][j] = acumulad[j];
            }
        }
        return acumulados;
    }

    //tipo isr 0 - normal, 1 - directo, 2 - anual checado
    private ValorTablaISR aplicacionTablaISR(Double ingresoGravado, boolean tipoISRANUAL, TipoCorrida corrida) {//JSA24
        Double ispt;
        ValorTablaISR valorTablaISR = new ValorTablaISR();
        try {
            Isr isrDato = buscaISR(ingresoGravado);
            if (mensajeResultado.getNoError() != 0) {
                return null;
            }
//            System.out.println("aplicacionTablaISR");
            ispt = (ingresoGravado - isrDato.getLimiteInferior()) * (isrDato.getPorcentaje() / 100) + isrDato.getCuotaFija();
//            System.out.println("ISRACARGO " + ispt + " =(ingresoGravado " + ingresoGravado + " - isrDato.getLimiteInferior) " + isrDato.getLimiteInferior() + " * (isrDato.getPorcentaje() / 100) " + (isrDato.getPorcentaje() / 100) + " + isrDato.getCuotaFija " + isrDato.getCuotaFija());
            valoresConceptosEmpleados.put("ISRACARGO".toUpperCase(), ispt);
            Double subsidioAlEmpleado = 0.0;
            valorTablaISR.setIsrCausado(ispt);
            if (!tipoISRANUAL) {
                if (corrida.getClave().equalsIgnoreCase("ASI")) {
                    valorTablaISR.setSubsidioAlEmpleo(0.0);
                    valoresConceptosEmpleados.put("SubsEmpleoCalculado".toUpperCase(), 0.0);
                } else {
                    subsidioAlEmpleado = aplicacionTablaSubsidio(ingresoGravado);
                    valorTablaISR.setSubsidioAlEmpleo(subsidioAlEmpleado);
                }

            }
            ispt = ispt - subsidioAlEmpleado;
            valorTablaISR.setIsrNeto(ispt);
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(34);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("aplicacionTablaISR()1_Error: ").append(ex));
        } catch (Exception e) {
            mensajeResultado.setNoError(34);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("aplicacionTablaISR()1_Error: ").append(e));
        }
        return valorTablaISR;
    }

    private Double aplicacionTablaSubsidio(Double ingresoGravado) {
        Double subsidio = 0.0;
        //ValorTablaISR valorTablaISR = new ValorTablaISR();
        try {
            if (!tipoCorrida.getClave().equalsIgnoreCase("FIN") & !tipoCorrida.getClave().equalsIgnoreCase("LIQ")) {//Segun el diagrama del calculo ISR FINIQUITO no lleva subsidio.
                Subsidio subsidioDato = buscaSubsidio(ingresoGravado);
                if (mensajeResultado.getNoError() != 0) {
                    return null;
                }
                valoresConceptosEmpleados.put("SubsEmpleoCalculado".toUpperCase(), subsidioDato.getCuota());
                if (subsidioDato.getCuota() != 0) {
                    subsidio = subsidioDato.getCuota();
                } else {
                    valoresConceptosEmpleados.put("SubsEmpleoCalculado".toUpperCase(), 0.0);
                    subsidio = 0.0;
                }
            } else {
                valoresConceptosEmpleados.put("SubsEmpleoCalculado".toUpperCase(), 0.0);
                subsidio = 0.0;
            }
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(34);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("aplicacionTablaSubsidio()1_Error: ").append(ex));
        } catch (Exception e) {
            mensajeResultado.setNoError(34);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("aplicacionTablaSubsidio()1_Error: ").append(e));
        }
//        System.out.println("aplicacionTablaSubsidio " + subsidio);
        return subsidio;
    }

    //checado
    private Double calculoISRNormal(Double acumuladoPeriodosNormal, MovNomConcep movimientosNomina) {
        double baseGravable = 0;
        try {
            double diasMes = 1;  ////por que -1 pendiente                DiasPago       
            int diasPagoTotal = (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase());
            if (tipoTablaISR == TipoTablaISR.PERIODICIDAD) { //parametro por periodicidad activado utiliza tabla segun la periodicidad
//////                if (periodosNomina.getTipoNomina().getPeriodicidad().getDias() == diasPagoTotal) {
                baseGravable = acumuladoNormal;
//////                } else {
//////                    diasPago = ((Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase())).longValue();
//////                    baseGravable = (acumuladoNormal / periodosNomina.getTipoNomina().getPeriodicidad().getDias()) * diasPago;
//////                }
            } else //parametro por periodicidad desactivado utiliza tabla mensual//JSA08
            if (modoAjustarIngresosMes == ProporcionaPeriodoIndependiente | modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes) {//& !periodoAjustadoMes) { 
                if (modoAjustarIngresosMes == ProporcionaPeriodoIndependiente | (!periodosNomina.isCierreMes() & modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes)
                        | (periodosNomina.isCierreMes() & modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes & descontarFaltasModoAjustaMes)) {
                    calculoDiasDepago(manejaPagoDiasNaturales, null, modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes);
                    if (mensajeResultado.getNoError() == -100) {
                        mensajeResultado.setNoError(47);
                        return 0.0;
                    }
                }
                if (modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes) {
                    if (periodosNomina.isCierreMes()) {
                        baseGravable = acumuladoNormal + acumuladoPeriodosNormal;
                    } else {
                        baseGravable = ((acumuladoNormal + acumuladoPeriodosNormal) / diasPago) * factorMensual;
                    }
                } else {
                    baseGravable = (acumuladoNormal / diasPago) * factorMensual;
                }
            } else if (modoAjustarIngresosMes == PropPeriodoIndepDiasNaturales | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes) {// & !periodoAjustadoMes) {
                if (modoAjustarIngresosMes == PropPeriodoIndepDiasNaturales | (!periodosNomina.isCierreMes() & modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes)
                        | (periodosNomina.isCierreMes() & modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes & descontarFaltasModoAjustaMes)) {
                    calculoDiasDepago(manejaPagoDiasNaturales, null, modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes);
                    if (mensajeResultado.getNoError() == -100) {
                        mensajeResultado.setNoError(47);
                        return 0.0;
                    }

                    Calendar fecha = Calendar.getInstance();
                    fecha.setTime(periodosNomina.getFechaInicial());
                    diasMes = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
//////                        if (isMov2Meses) {
//////                            if (movimientosNomina.getNumMovParticion() == 1) {
//////                                fecha.setTime(periodosNomina.getFechaInicial());
//////                                diasMes = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
//////                            } else {
//////                                fecha.setTime(periodosNomina.getFechaFinal());
//////                                diasMes = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
//////                            }
//////                        } else {
//////                            fecha.setTime(periodosNomina.getFechaInicial());
//////                            diasMes = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
//////                            //diasMes = calculoDiasDeMes(true);  //los dos usa dias naturales del mes w
//////
////////////                    if (mensajeResultado.getNoError() == -100) {
////////////                        mensajeResultado.setNoError(48);
////////////                        return 0.0;
////////////                    }
//////                        }
                }
                if (modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes) {
                    if (periodosNomina.isCierreMes()) {
                        baseGravable = acumuladoNormal + acumuladoPeriodosNormal;
                    } else {
                        baseGravable = ((acumuladoNormal + acumuladoPeriodosNormal) / diasPago) * diasMes;
                    }
                } else {
                    baseGravable = ((acumuladoNormal) / diasPago) * diasMes;
                }
            } else if (periodosNomina.isCierreMes() & modoAjustarIngresosMes == UltimoPeriodoSinAjustarMes) {// | periodoAjustadoMes) {
                baseGravable = acumuladoNormal + acumuladoPeriodosNormal;
            } else {  // periodosNomina no es cierre de mes & modoAjustarIngresosMes == UltimoPeriodoSinAjustarMes
                calculoDiasDepago(manejaPagoDiasNaturales, null, modoAjustarIngresosMes == UltimoPeriodoSinAjustarMes);
                ///baseGravable = ((acumuladoNormal) / diasPago) * factorMensual;  //opc1
                baseGravable = ((acumuladoNormal + acumuladoPeriodosNormal) / diasPago) * factorMensual;  // opc 2
            }
            if (baseGravable == Double.NaN || baseGravable == Double.POSITIVE_INFINITY || baseGravable == Double.NEGATIVE_INFINITY) {
                baseGravable = 0.0;
            }
//            System.out.println("baseGravable " + baseGravable + " acumuladoNormal " + acumuladoNormal + " acumuladoPeriodosNormal " + acumuladoPeriodosNormal + " diasPago " + diasPago);
            if (baseGravable > 0 & !calculoSeparadoISR) {
                ValorTablaISR valorTablaISR = aplicacionTablaISR(baseGravable, false, movimientosNomina.getTipoCorrida());
                if (mensajeResultado.getNoError() != 0) {
                    return 0.0;
                }
                Calendar c = null;
                if (isMov2Meses) {
                    c = Calendar.getInstance();
                    c.setTime(periodosNomina.getFechaFinal());
                }
                isrNormalSinAjustar = valorTablaISR.getIsrNeto();
                isrACargoNormalSinAjustar = valorTablaISR.getIsrCausado();
                subsidioAlEmpleoNormalSinAjustar = valorTablaISR.getSubsidioAlEmpleo();
                if (tipoTablaISR == TipoTablaISR.PERIODICIDAD) {
                    if (periodosNomina.getTipoNomina().getPeriodicidad().getDias() == diasPagoTotal) {
                        isrNormal = valorTablaISR.getIsrNeto();
                        retenido.setIsrACargoNormal(valorTablaISR.getIsrCausado());
                        retenido.setSubsidioEmpleoNormal(valorTablaISR.getSubsidioAlEmpleo());
                    } else {
                        isrNormal = (valorTablaISR.getIsrNeto() * diasPagoTotal) / periodosNomina.getTipoNomina().getPeriodicidad().getDias();
                        retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() * diasPagoTotal) / periodosNomina.getTipoNomina().getPeriodicidad().getDias());
                        retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() * diasPagoTotal) / periodosNomina.getTipoNomina().getPeriodicidad().getDias());
                    }
                } else if (modoAjustarIngresosMes == ProporcionaPeriodoIndependiente | modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes) {// & !periodoAjustadoMes) {
                    if (isMov2Meses) {
                        ///if (!movimientosNomina.getMes().equals(c.get(Calendar.MONTH) + 1)) {
                        int diasTotales;
                        if (manejaPagoDiasNaturales) {
                            diasTotales = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
                        } else {
                            diasTotales = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
                        }
                        calculoDiasDepago(manejaPagoDiasNaturales, diasPagoTotal, !periodosNomina.isCierreMes() & modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes);
                        ///}
                        if (periodosNomina.isCierreMes() & modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes) {
                            isrNormal = (valorTablaISR.getIsrNeto() * diasPago) / diasTotales;
                            retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() * diasPago) / diasTotales);
                            retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() * diasPago) / diasTotales);
                        } else {
                            isrNormal = (valorTablaISR.getIsrNeto() / factorMensual) * diasPago;
                            retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() / factorMensual) * diasPago);
                            retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() / factorMensual) * diasPago);
                        }
                    } else if (periodosNomina.isCierreMes() & modoAjustarIngresosMes == ProporcionaPeriodoAjustadoMes) {
                        isrNormal = valorTablaISR.getIsrNeto();
                        retenido.setIsrACargoNormal(valorTablaISR.getIsrCausado());
                        retenido.setSubsidioEmpleoNormal(valorTablaISR.getSubsidioAlEmpleo());
                    } else {
                        isrNormal = (valorTablaISR.getIsrNeto() / factorMensual) * diasPago;
                        retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() / factorMensual) * diasPago);
                        retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() / factorMensual) * diasPago);
                    }
                } else if (modoAjustarIngresosMes == PropPeriodoIndepDiasNaturales | modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes) {// & !periodoAjustadoMes) {
                    if (isMov2Meses) {
                        /// if (!movimientosNomina.getMes().equals(c.get(Calendar.MONTH) + 1)) {
                        int diasTotales;
                        if (manejaPagoDiasNaturales) {
                            diasTotales = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
                        } else {
                            diasTotales = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
                        }

                        calculoDiasDepago(manejaPagoDiasNaturales, diasPagoTotal, !periodosNomina.isCierreMes() & modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes);
                        //}
                        if (periodosNomina.isCierreMes() & modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes) {
                            isrNormal = (valorTablaISR.getIsrNeto() * diasPago) / diasTotales;
                            retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() * diasPago) / diasTotales);
                            retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() * diasPago) / diasTotales);
                        } else {
                            isrNormal = (valorTablaISR.getIsrNeto() / diasMes) * diasPago;
                            retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() / diasMes) * diasPago);
                            retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() / diasMes) * diasPago);
                        }
                    } else if (periodosNomina.isCierreMes() & modoAjustarIngresosMes == PropPeriodoIndepAjustadoAlUltimoPeriodoMes) {
                        isrNormal = valorTablaISR.getIsrNeto();
                        retenido.setIsrACargoNormal(valorTablaISR.getIsrCausado());
                        retenido.setSubsidioEmpleoNormal(valorTablaISR.getSubsidioAlEmpleo());
                    } else {
                        isrNormal = (valorTablaISR.getIsrNeto() / diasMes) * diasPago;
                        retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() / diasMes) * diasPago);
                        retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() / diasMes) * diasPago);
                    }
                } else if (periodosNomina.isCierreMes() & modoAjustarIngresosMes == UltimoPeriodoSinAjustarMes) {// | periodoAjustadoMes) {
                    if (isMov2Meses) { //& (c == null ? false : !movimientosNomina.getMes().equals(c.get(Calendar.MONTH) + 1))
                        calculoDiasDepago(manejaPagoDiasNaturales, diasPagoTotal, modoAjustarIngresosMes == UltimoPeriodoSinAjustarMes);
                        diasMes = calculoDiasDeMes(true);
                        isrNormal = (valorTablaISR.getIsrNeto() / diasMes) * diasPago;
                        retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() / diasMes) * diasPago);
                        retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() / diasMes) * diasPago);
                    } else {
                        isrNormal = valorTablaISR.getIsrNeto();
                        retenido.setIsrACargoNormal(valorTablaISR.getIsrCausado());
                        retenido.setSubsidioEmpleoNormal(valorTablaISR.getSubsidioAlEmpleo());
                    }
                } else // periodosNomina no es cierre de mes & modoAjustarIngresosMes == UltimoPeriodoSinAjustarMes
                if (isMov2Meses) {
                    ///if (!movimientosNomina.getMes().equals(c.get(Calendar.MONTH) + 1)) {
                    calculoDiasDepago(manejaPagoDiasNaturales, diasPagoTotal, modoAjustarIngresosMes == UltimoPeriodoSinAjustarMes);
                    ///}
                    isrNormal = (valorTablaISR.getIsrNeto() / factorMensual) * diasPago;
                    retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() / factorMensual) * diasPago);
                    retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() / factorMensual) * diasPago);
                } else {
                    isrNormal = (valorTablaISR.getIsrNeto() / factorMensual) * diasPago;
                    retenido.setIsrACargoNormal((valorTablaISR.getIsrCausado() / factorMensual) * diasPago);
                    retenido.setSubsidioEmpleoNormal((valorTablaISR.getSubsidioAlEmpleo() / factorMensual) * diasPago);
                }
//                System.out.println("isrNormal " + isrNormal + " valorTablaISR.getIsrNeto() " + valorTablaISR.getIsrNeto());
            } else if (baseGravable == 0) {
                retenido.setIsrACargoNormal(0.0);
                retenido.setSubsidioEmpleoNormal(0.0);
                retenido.setIsrNetoNormal(0.0);
                retenido.setIsrSubsidioNormal(0.0);
                isrNormal = 0;
            }
        } catch (Exception e) {
            mensajeResultado.setNoError(45);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calculoISRNormal()1_Error: ").append(e));
        }
        return baseGravable;
    }

    //checado
    private Integer calculoDiasDeMes(boolean calculaDiasNaturales) {
        strQuery.delete(0, strQuery.length()).append("SELECT p ");
        strQuery.append("FROM PeriodosNomina p INNER JOIN p.tipoNomina t INNER JOIN p.tipoCorrida tc INNER JOIN t.periodicidad pd WHERE ");

        if (HibernateUtil.usaTypeBigInt) {
            strQuery.append("(p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as int)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn INNER JOIN pn.tipoCorrida c WHERE pn.clave < cast(:clavePeriodoNomina as int) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND c.clave = :claveTipoCorrida) ");
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            if (periodosNomina.isCierreMes()) {
                strQuery.append("AND p.clave <= cast(:clavePeriodoNomina as int)) AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND tc.clave = :claveTipoCorrida AND :fechaIngresoEmp <= p.fechaFinal AND :fechaFinEmp >= p.fechaInicial");
            } else {
                strQuery.append("AND p.clave <= (SELECT CASE WHEN (count(pn) > 0) THEN MIN(CAST(pn.clave as int)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn INNER JOIN pn.tipoCorrida c WHERE pn.clave > cast(:clavePeriodoNomina as int) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND c.clave = :claveTipoCorrida)) ");
                strQuery.append("AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND tc.clave = :claveTipoCorrida AND  :fechaIngresoEmp <= p.fechaFinal AND :fechaFinEmp >= p.fechaInicial");
            }
        } else {
            strQuery.append("(p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as long)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn INNER JOIN pn.tipoCorrida c WHERE pn.clave < cast(:clavePeriodoNomina as long) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND c.clave = :claveTipoCorrida) ");
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            if (periodosNomina.isCierreMes()) {
                strQuery.append("AND p.clave <= cast(:clavePeriodoNomina as long)) AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND tc.clave = :claveTipoCorrida AND :fechaIngresoEmp <= p.fechaFinal AND :fechaFinEmp >= p.fechaInicial");
            } else {
                strQuery.append("AND p.clave <= (SELECT CASE WHEN (count(pn) > 0) THEN MIN(CAST(pn.clave as long)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn INNER JOIN pn.tipoCorrida c WHERE pn.clave > cast(:clavePeriodoNomina as long) AND tn.clave = :claveTipoNomina AND pn.cierreMes = true AND pn.año = :yearPeriodo AND c.clave = :claveTipoCorrida)) ");
                strQuery.append("AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND tc.clave = :claveTipoCorrida AND :fechaIngresoEmp <= p.fechaFinal AND :fechaFinEmp >= p.fechaInicial");
            }
        }

        camposParametro.add("clavePeriodoNomina");
        camposParametro.add("claveTipoNomina");
        camposParametro.add("yearPeriodo");
        camposParametro.add("claveTipoCorrida");
        camposParametro.add("fechaIngresoEmp");
        camposParametro.add("fechaFinEmp");
        valoresParametro.add(valoresConceptosEmpleados.get("NumPeriodo".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("AnioPeriodo".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("FechaAlta".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("FechaBaja".toUpperCase()));

        List<PeriodosNomina> periodosNominas = (List<PeriodosNomina>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
        periodosNominas = (periodosNominas == null) ? new ArrayList<PeriodosNomina>() : periodosNominas;
        int dias = 0;
        for (PeriodosNomina p : periodosNominas) {
//            System.out.println("periodo " + p.getClave() + " año " + p.getAño() + " inicio " + p.getFechaInicial() + " final " + p.getFechaFinal() + " cierre " + p.isCierreMes());
            if (!calculaDiasNaturales) {
                dias = dias + p.getTipoNomina().getPeriodicidad().getDias().intValue();
            } else {
                dias = dias + (cantidadDiasEntreDosFechas(p.getFechaInicial(), p.getFechaFinal()) + 1); //+1 para contar el dia actual
            }
        }
        //("calculoDiasDeMes " + dias);
        return dias;
    }

    //checado
    private void calculoDiasDepago(boolean calculaDiasNaturales, Integer diasDivPor2Meses, boolean acumularPeriodos) {
        List<PeriodosNomina> listPeriodosNominas;
        Date fechaAlta = (Date) valoresConceptosEmpleados.get("FechaAlta".toUpperCase());
        Date fechaBaja = (Date) valoresConceptosEmpleados.get("FechaBaja".toUpperCase());
        if (acumularPeriodos) {
            if (HibernateUtil.usaTypeBigInt) {
                strQuery.delete(0, strQuery.length()).append("SELECT DISTINCT p ");//JSA10
                strQuery.append(" FROM MovNomConcep mov INNER JOIN mov.periodosNomina p INNER JOIN p.tipoNomina t INNER JOIN p.tipoCorrida c INNER JOIN mov.empleado emp  WHERE  ");
                strQuery.append(" (p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as int)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn INNER JOIN pn.tipoCorrida tc ");
                strQuery.append(" WHERE pn.clave < cast(:clavePeriodoNomina as int) AND tn.clave = :claveTipoNomina AND tc.clave = :claveTipoCorrida ");
                strQuery.append(" AND pn.cierreMes = true AND pn.año = :yearPeriodo) AND p.clave < cast(:clavePeriodoNomina as int)) AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND c.clave = :claveTipoCorrida ");
                strQuery.append(" AND :fechaIngresoEmp  <= p.fechaFinal AND  :fechaFinEmp  >= p.fechaInicial ");
                strQuery.append(" AND emp.clave = :empleado ");
            } else {
                strQuery.delete(0, strQuery.length()).append("SELECT DISTINCT p ");//JSA10
                strQuery.append(" FROM MovNomConcep mov INNER JOIN mov.periodosNomina p INNER JOIN p.tipoNomina t INNER JOIN p.tipoCorrida c INNER JOIN mov.empleado emp  WHERE  ");
                strQuery.append(" (p.clave > (SELECT CASE WHEN (count(pn) > 0) THEN MAX(CAST(pn.clave as long)) ELSE 0 END FROM PeriodosNomina pn INNER JOIN pn.tipoNomina tn INNER JOIN pn.tipoCorrida tc");
                strQuery.append(" WHERE pn.clave < cast(:clavePeriodoNomina as long) AND tn.clave = :claveTipoNomina AND tc.clave = :claveTipoCorrida");
                strQuery.append(" AND pn.cierreMes = true AND pn.año = :yearPeriodo) AND p.clave < cast(:clavePeriodoNomina as long)) AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND c.clave = :claveTipoCorrida ");
                strQuery.append(" AND :fechaIngresoEmp  <= p.fechaFinal AND  :fechaFinEmp  >= p.fechaInicial ");
                strQuery.append(" AND emp.clave = :empleado ");
            }

            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            camposParametro.add("clavePeriodoNomina");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("claveTipoCorrida");
            camposParametro.add("yearPeriodo");
            camposParametro.add("fechaIngresoEmp");
            camposParametro.add("fechaFinEmp");
            camposParametro.add("empleado");
            valoresParametro.add(valoresConceptosEmpleados.get("NumPeriodo".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
            valoresParametro.add(getFechaDelSistema().get(Calendar.YEAR));
            valoresParametro.add(fechaAlta);
            valoresParametro.add(fechaBaja);
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));

            listPeriodosNominas = (List<PeriodosNomina>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            listPeriodosNominas = (listPeriodosNominas == null) ? new ArrayList<PeriodosNomina>() : listPeriodosNominas;
        } else {
            listPeriodosNominas = new ArrayList<PeriodosNomina>();
        }
        Long dias = 0L;
        listPeriodosNominas.add(periodosNomina);
        int i;
        for (i = 0; i < listPeriodosNominas.size(); i++) {
            if (!calculaDiasNaturales) {
                if (diasDivPor2Meses != null & i == listPeriodosNominas.size() - 1) {
                    dias = dias + diasDivPor2Meses;
                } else {
                    dias = dias + listPeriodosNominas.get(i).getTipoNomina().getPeriodicidad().getDias();
                }
            } else if (diasDivPor2Meses != null & i == listPeriodosNominas.size() - 1) {
                dias = dias + diasDivPor2Meses;
            } else {
                dias = dias + (cantidadDiasEntreDosFechas(listPeriodosNominas.get(i).getFechaInicial(), listPeriodosNominas.get(i).getFechaFinal()) + 1); //+1 para contar el dia actual
            }
            if (fechaAlta.after(listPeriodosNominas.get(i).getFechaInicial())) {
                dias = dias - cantidadDiasEntreDosFechas(listPeriodosNominas.get(i).getFechaInicial(), fechaAlta);
            }
            if (fechaBaja.before(listPeriodosNominas.get(i).getFechaFinal())) {
                dias = dias - cantidadDiasEntreDosFechas(fechaBaja, listPeriodosNominas.get(i).getFechaFinal());
            }
        }
        diasPago = dias - descontarDiasPago; //*faltas incidencias etc solo se usa para calculoDiasDepago en este metodo**/
    }

    //checado
    private void calculoISRDirecto(Double baseGravableNormal, Double acumuladoPeriodosDirecto, TipoCorrida tipoCorrida) {
        if (acumuladoDirecto != 0.0) {
            ValorTablaISR valorTablaISR = aplicacionTablaISR(baseGravableNormal + acumuladoDirecto + acumuladoPeriodosDirecto, false, tipoCorrida);
            retenido.setIsrACargoDirecto((Double) valoresConceptosEmpleados.get("ISRACARGO".toUpperCase()));
            retenido.setSubsidioEmpleoDirecto((Double) (valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase()) == null ? 0.0 : valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase())));
            //retenido.setIsrNetoDirecto((Double) valoresConceptosEmpleados.get("ISRNeto".toUpperCase()));
            //retenido.setIsrSubsidioDirecto((Double) valoresConceptosEmpleados.get("ISRSubsidio".toUpperCase()));
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            isrDirecto = valorTablaISR.getIsrNeto() - isrNormalSinAjustar;
            retenido.setIsrACargoDirecto(retenido.getIsrACargoDirecto() - isrACargoNormalSinAjustar);
            retenido.setSubsidioEmpleoDirecto(retenido.getSubsidioEmpleoDirecto() - subsidioAlEmpleoNormalSinAjustar);
            if (isMov2Meses) {
                int diasPagoTotal = (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase());
                int diasPeriodo;
                if (manejaPagoDiasNaturales) {
                    diasPeriodo = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
                } else {
                    diasPeriodo = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
                }
                isrDirecto = (isrDirecto * diasPagoTotal) / diasPeriodo;
                retenido.setIsrACargoDirecto((retenido.getIsrACargoDirecto() * diasPagoTotal) / diasPeriodo);
                retenido.setSubsidioEmpleoDirecto((retenido.getSubsidioEmpleoDirecto() * diasPagoTotal) / diasPeriodo);
            }
        } else {
            retenido.setIsrACargoDirecto(0.0);
            retenido.setSubsidioEmpleoDirecto(0.0);
            retenido.setIsrNetoDirecto(0.0);
            retenido.setIsrSubsidioDirecto(0.0);
            isrDirecto = 0.0;
        }
    }

    //checado
    private void calculoISRAnual(Double baseGravableNormal, Double baseGravableDirecto, Double acumuladosEnPeriodoAnual, double dias, TipoCorrida tipoCorrida) {
        if (acumuladoAnual != 0.0) {
            //Double acumuladoPeriodoAnual = acumuladosPorTipoISRAnual();
            //se calcula cuando es el ultimo periodo del año
            double baseGravableGlobal, baseGravableMensual;
            baseGravableGlobal = baseGravableNormal + baseGravableDirecto + acumuladoAnual + acumuladosEnPeriodoAnual;
            baseGravableMensual = (((baseGravableGlobal / 365) * dias));
            ValorTablaISR valorTablaISR = aplicacionTablaISR(baseGravableMensual, true, tipoCorrida);//JSA24
            retenido.setIsrACargoAnual((Double) valoresConceptosEmpleados.get("ISRACARGO".toUpperCase()));
            retenido.setSubsidioEmpleoAnual((Double) (valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase()) == null ? 0.0 : valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase())));
            //retenido.setIsrNetoAnual((Double) valoresConceptosEmpleados.get("ISRNeto".toUpperCase()));
            //retenido.setIsrSubsidioAnual((Double) valoresConceptosEmpleados.get("ISRSubsidio".toUpperCase()));
            if (mensajeResultado.getNoError() != 0) {
                return;
            }
            isrAnual = (valorTablaISR.getIsrNeto() / baseGravableGlobal);//Se obtiene un factor el cual se multiplica por la basegravableanualtotal.
            isrAnual = acumuladoAnual * isrAnual;
            retenido.setIsrACargoAnual((retenido.getIsrACargoAnual() / baseGravableMensual) * acumuladoAnual);
            retenido.setSubsidioEmpleoAnual((retenido.getSubsidioEmpleoAnual() / baseGravableMensual) * acumuladoAnual);
            if (isMov2Meses) {
                int diasPagoTotal = (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase());
                int diasPeriodo;
                if (manejaPagoDiasNaturales) {
                    diasPeriodo = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
                } else {
                    diasPeriodo = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
                }
                isrAnual = (isrAnual * diasPagoTotal) / diasPeriodo;
                retenido.setIsrACargoAnual((retenido.getIsrACargoAnual() * diasPagoTotal) / diasPeriodo);
                retenido.setSubsidioEmpleoAnual((retenido.getSubsidioEmpleoAnual() * diasPagoTotal) / diasPeriodo);
            }
        } else {
            acumuladoAnual = 0.0;
            retenido.setIsrACargoAnual(0.0);
            retenido.setSubsidioEmpleoAnual(0.0);
            retenido.setIsrNetoAnual(0.0);
            retenido.setIsrSubsidioAnual(0.0);
        }
    }

    //checado
    private void calculoDiasTranscurridos() {
        try {
            strQuery.delete(0, strQuery.length()).append("SELECT  DISTINCT p FROM MovNomConcep mov INNER JOIN mov.periodosNomina p INNER JOIN mov.tipoNomina t INNER JOIN ");//JSA10
            strQuery.append(" mov.empleado emp INNER JOIN p.tipoCorrida c ");
            if (HibernateUtil.usaTypeBigInt) {
                strQuery.append(" WHERE p.clave < CAST(:clavePeriodoNomina as int) AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND c.clave =:claveTipoCorrida AND emp.clave = :empleado ");
            } else {
                strQuery.append(" WHERE p.clave < CAST(:clavePeriodoNomina as long) AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo AND c.clave =:claveTipoCorrida AND emp.clave = :empleado ");
            }

            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            camposParametro.add("clavePeriodoNomina");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("claveTipoCorrida");
            camposParametro.add("yearPeriodo");
            camposParametro.add("empleado");
            valoresParametro.add(valoresConceptosEmpleados.get("NumPeriodo".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
            valoresParametro.add(getFechaDelSistema().get(Calendar.YEAR));
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            Long dias = 0L;
            Date fechaAlta = (Date) valoresConceptosEmpleados.get("FechaAlta".toUpperCase());
            Date fechaBaja = (Date) valoresConceptosEmpleados.get("FechaBaja".toUpperCase());
            List<PeriodosNomina> periodosNominas = (List<PeriodosNomina>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            periodosNominas = (periodosNominas == null) ? new ArrayList<PeriodosNomina>() : periodosNominas;
            periodosNominas.add(periodosNomina);
            for (PeriodosNomina p : periodosNominas) {
                dias = dias + p.getTipoNomina().getPeriodicidad().getDias();
                if (fechaAlta.after(p.getFechaInicial())) {
                    dias = dias - cantidadDiasEntreDosFechas(p.getFechaInicial(), fechaAlta) - 1;
                }
                if (fechaBaja.before(p.getFechaFinal())) {
                    dias = dias - cantidadDiasEntreDosFechas(fechaBaja, p.getFechaFinal()) - 1;
                }
            }
            diasPago = dias.doubleValue();
        } catch (Exception e) {
            mensajeResultado.setError(e.getMessage());
            mensajeResultado.setNoError(35);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calculoDiasTranscurridos()1_Error: ").append(e));
        }
    }

    /*
     * suma acumulados de periodos regresando los acumulados segun el tipo de
     * ISR modificado agrega razon social y plaza empleado
     */
    private Double acumuladosPorTipoISRAnual() {
        try {
            strQuery.delete(0, strQuery.length()).append("SELECT SUM(CASE WHEN (mba.resultado is NULL) THEN 0.0 ELSE (mba.resultado * 1.0) END) FROM MovNomConcep m INNER JOIN m.periodosNomina p INNER JOIN p.tipoCorrida c INNER JOIN m.empleado em ");
            strQuery.append("INNER JOIN m.tipoCorrida tc INNER JOIN m.movNomBaseAfecta mba INNER JOIN mba.baseAfecConcepNom ba INNER JOIN ba.baseNomina bn INNER JOIN m.tipoNomina t INNER JOIN m.razonesSociales rs INNER JOIN m.plazasPorEmpleado pemp ");
            if (HibernateUtil.usaTypeBigInt) {
                strQuery.append("WHERE m.uso = 0 AND p.clave < CAST(:clavePeriodoNomina as int) AND em.clave = :claveEmp  AND tc.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND t.clave = :claveTipoNomina AND bn.clave = :claveBaseNomina ");
            } else {
                strQuery.append("WHERE m.uso = 0 AND p.clave < CAST(:clavePeriodoNomina as long) AND em.clave = :claveEmp  AND tc.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND t.clave = :claveTipoNomina AND bn.clave = :claveBaseNomina ");
            }
            strQuery.append("AND rs.clave = :claveRazonSocial AND pemp.clave = :clavePlazaEmpleado ");
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            camposParametro.add("claveEmp");
            camposParametro.add("claveTipoCorrida");
            camposParametro.add("clavePeriodoNomina");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("claveBaseNomina");
            camposParametro.add("claveRazonSocial");
            camposParametro.add("clavePlazaEmpleado");
            camposParametro.add("yearPeriodo");
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("NumPeriodo".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(ClavesParametrosModulos.claveBaseNominaISR); //BaseNomina ISR
            valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("PlazaEmpleado".toUpperCase()));
            valoresParametro.add(getFechaDelSistema().get(Calendar.YEAR));
            Double acumuladosAnual = (Double) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            acumuladosAnual = acumuladosAnual == null ? 0.0 : acumuladosAnual;
            return acumuladosAnual;
        } catch (Exception e) {
            mensajeResultado.setError(e.getMessage());
            mensajeResultado.setNoError(36);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("acumuladosPorTipoISRAnual()1_Error: ").append(e));
        }
        return 0.0;
    }

    //checado
    private double calculoISPTAnual(MovNomConcep movimientosNomina) {
        retenido = new CalculoISR();
        calculoDiasTranscurridos();
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        Double acumuladoPeriodoAnual = acumuladosPorTipoISRAnual();
        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }

        double baseGravableAnual = ((acumuladoNormal + acumuladoDirecto + acumuladoAnual + acumuladoPeriodoAnual) / diasPago) * factorAnual;
        ValorTablaISR valorTablaISR = aplicacionTablaISR(baseGravableAnual, false, movimientosNomina.getTipoCorrida());
        retenido.setIsrACargoAnual((Double) valoresConceptosEmpleados.get("ISRACARGO".toUpperCase()));
        retenido.setSubsidioEmpleoAnual((Double) (valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase()) == null ? 0.0 : valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase())));

        retenido.setIsrACargoNormal(0.0);
        retenido.setSubsidioEmpleoNormal(0.0);
        retenido.setIsrNetoNormal(0.0);
        retenido.setIsrSubsidioNormal(0.0);

        retenido.setIsrACargoDirecto(0.0);
        retenido.setSubsidioEmpleoDirecto(0.0);
        retenido.setIsrNetoDirecto(0.0);
        retenido.setIsrSubsidioDirecto(0.0);

        if (mensajeResultado.getNoError() != 0) {
            return 0.0;
        }
        isrAnual = (valorTablaISR.getIsrNeto() / factorAnual) * diasPago;
        if (isMov2Meses) {
            int diasPagoTotal = (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase());
            int diasPeriodo;
            if (manejaPagoDiasNaturales) {
                diasPeriodo = (cantidadDiasEntreDosFechas(periodosNomina.getFechaInicial(), periodosNomina.getFechaFinal()) + 1); //+1 para contar el dia actual
            } else {
                diasPeriodo = periodosNomina.getTipoNomina().getPeriodicidad().getDias().intValue();
            }
            isrAnual = (isrAnual * diasPagoTotal) / diasPeriodo;
        }
        isrNormal = 0;
        isrDirecto = 0;
        if (movimientosNomina != null) {
            IsrRetenidos(movimientosNomina);
            if (mensajeResultado.getNoError() != 0) {
                return 0.0;
            }
        }
        return isrNormal + isrDirecto + isrAnual;
    }

    private Object ejecutaQueryUnico(String strQuery, String[] camposParametro, Object[] valoresParametro) {
        Object result = null;
        try {
            Query query = getSession().createQuery(strQuery);
            int i;
            if (camposParametro != null & valoresParametro != null) {
                for (i = 0; i < camposParametro.length; i++) {
                    if (valoresParametro[i] instanceof Object[]) {
                        query.setParameterList(camposParametro[i], (Object[]) valoresParametro[i]);
                    } else if (valoresParametro[i] instanceof ArrayList) {
                        query.setParameterList(camposParametro[i], ((ArrayList) valoresParametro[i]).toArray());
                    } else if (valoresParametro[i] instanceof Calendar) {
                        Calendar c = (Calendar) valoresParametro[i];
                        query.setParameter(camposParametro[i], c.getTime());
                    } else {
                        query.setParameter(camposParametro[i], valoresParametro[i]);
                    }
                }
            }
            query.setMaxResults(1);//JSA02
            result = query.uniqueResult();
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(-100);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryUnico()1_Error: ").append(ex));
        } catch (Exception e) {
            mensajeResultado.setError(e.getMessage());
            mensajeResultado.setNoError(-100);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryUnico()1_Error: ").append(e));
        }
        return result;
    }

    private List<?> ejecutaQueryList(String strQuery, String[] camposParametro, Object[] valoresParametro, Integer maxResultados) {
        List<Object> result = null;
        try {
            Query query = getSession().createQuery(strQuery);

            int i;
            if (camposParametro != null & valoresParametro != null) {
                for (i = 0; i < camposParametro.length; i++) {
                    if (valoresParametro[i] instanceof Object[]) {
                        query.setParameterList(camposParametro[i], (Object[]) valoresParametro[i]);
                    } else if (valoresParametro[i] instanceof ArrayList) {
                        query.setParameterList(camposParametro[i], ((ArrayList) valoresParametro[i]).toArray());
                    } else if (valoresParametro[i] instanceof Calendar) {
                        Calendar c = (Calendar) valoresParametro[i];
                        query.setParameter(camposParametro[i], c.getTime());
                    } else {
                        query.setParameter(camposParametro[i], valoresParametro[i]);
                    }
                }
            }
            if (maxResultados == null) {
                maxResultados = 0;
            }

            if (maxResultados > 0) {
                query.setMaxResults(maxResultados);
            }

            result = query.list();
            if (result == null) {
                result = new ArrayList<>();
            }
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(-100);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(ex));
        } catch (Exception e) {
            mensajeResultado.setError(e.getMessage());
            mensajeResultado.setNoError(-100);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(e));
        }
        return result;
    }

    private List<?> ejecutaQueryList(String consulta, boolean conParametros, List<String> listParametros, Map<String, Object> parametrosCampos, Integer maxResultados) {
        List<Object> result = null;
        try {
            Query query = getSession().createQuery(consulta);

            int i;
            Object valor;
            String parametro;
            if (conParametros & listParametros != null) {
                for (i = 0; i < listParametros.size(); i++) {
                    parametro = listParametros.get(i);
                    valor = parametrosCampos.get(parametro);
                    if (valor instanceof Calendar) {
                        valor = ((Calendar) valor).getTime();
                    }
                    if (valor instanceof Object[]) {
                        query.setParameterList(parametro, (Object[]) valor);
                    } else if (valor instanceof ArrayList) {
                        query.setParameterList(parametro, ((ArrayList) valor).toArray());
                    } else {
                        query.setParameter(parametro, valor);
                    }
                }
            }
            if (maxResultados == null) {
                maxResultados = 0;
            }

            if (maxResultados > 0) {
                query.setMaxResults(maxResultados);
            }

            result = query.list();
        } catch (HibernateException ex) {
            mensajeResultado.setError(ex.getMessage());
            mensajeResultado.setNoError(-100);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(ex));
        } catch (Exception e) {
            mensajeResultado.setError(e.getMessage());
            mensajeResultado.setNoError(-100);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryList()1_Error: ").append(e));
        }
        return result;
    }

    private int ejecutaQueryExecuteUpdate(String strQuery, String[] camposParametro, Object[] valoresParametro) {
        int result = 0;
        try {
            Query query = getSession().createQuery(strQuery);
            int i;
            if (camposParametro != null & valoresParametro != null) {
                for (i = 0; i < camposParametro.length; i++) {
                    query.setParameter(camposParametro[i], valoresParametro[i]);
                }
            }

            result = query.executeUpdate();
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(-101);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaQueryExecuteUpdate()1_Error: ").append(ex));
        }
        return result;
    }

    private ZonaSalarial buscaSalarioPorZona(char zona) {
        ZonaSalarial salario = null;
        try {
            int i;
            for (i = 0; i < tablaZonaSalarial.length; i++) {
                if (tablaZonaSalarial[i][0].toString().equalsIgnoreCase(String.valueOf(zona))) {
                    salario = new ZonaSalarial(tablaZonaSalarial[i]);
                    break;
                }
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(40);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaISR()1_Error: ").append(ex));
        }
        return salario;
    }

    private Isr buscaISR(Double ingresoGravado) {
        Isr isrDato = null;
        try {
            int i;
            for (i = 0; i < tablaIsr.length; i++) {
                if (ingresoGravado < Double.valueOf(tablaIsr[i][0].toString())) {
                    isrDato = new Isr(tablaIsr[i - 1]);
                    break;
                }
            }
            if (isrDato == null) {
                isrDato = new Isr(tablaIsr[tablaIsr.length - 1]);
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(35);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaISR()1_Error: ").append(ex));
        }
        return isrDato;
    }

    private Subsidio buscaSubsidio(Double ingresoGravado) {
        Subsidio subsidioDato = null;
        try {
            int i;
            for (i = 0; i < tablaSubsidio.length; i++) {
                if (ingresoGravado < Double.valueOf(tablaSubsidio[i][0].toString())) {
                    subsidioDato = new Subsidio(tablaSubsidio[i - 1]);
                    break;
                }
            }
            if (subsidioDato == null) {
                subsidioDato = new Subsidio(tablaSubsidio[tablaSubsidio.length - 1]);
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(38);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaSubsidio()1_Error: ").append(ex));
        }
        return subsidioDato;
    }

    private FactorIntegracion buscaFactorIntegracion(Date fechaIngreso) {
        FactorIntegracion factorDato = null;//factorDatoTmp
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(fechaIngreso);
            int i, anioIngresoTmp = Integer.valueOf(calcularAntiguedadExacta(fechaIngreso, TipoAntiguedad.ANTIGUEDADENTERO).toString()), anioIngreso;
            anioIngreso = anioIngresoTmp;
            if (anioIngreso < 1) {
                anioIngreso = 1;
            }
            Double diasTotalesDeVacaciones = 0.0, y;//JSA12
            for (i = 0; i < tablaFactorIntegracion.length; i++) {
                if (anioIngreso == Integer.valueOf(tablaFactorIntegracion[i][0].toString())) {
                    int x = 0;
                    if (anioIngresoTmp > 0) {
                        x = Integer.parseInt(calcularAntiguedadExacta(fechaIngreso, TipoAntiguedad.PORCIONDIAS).toString());
                    }
                    if (x > 0) {
                        factorDato = new FactorIntegracion(tablaFactorIntegracion[i]);
//                        factorDatoTmp = new FactorIntegracion(tablaFactorIntegracion[i + 1]);
//                        factorDato.setFactor(factorDatoTmp.getFactor());
                    } else {
                        factorDato = new FactorIntegracion(tablaFactorIntegracion[i]);
                    }
                    diasTotalesDeVacaciones += factorDato.getDiasVacaciones();
                    break;
                } else if (anioIngreso < Integer.valueOf(tablaFactorIntegracion[i][0].toString())) {
                    factorDato = new FactorIntegracion(tablaFactorIntegracion[i - 1]);
                    diasTotalesDeVacaciones += factorDato.getDiasVacaciones();
                    break;
                } else if (i == tablaFactorIntegracion.length - 1 & anioIngreso > Integer.valueOf(tablaFactorIntegracion[i][0].toString())) {
                    factorDato = new FactorIntegracion(tablaFactorIntegracion[i]);
                    diasTotalesDeVacaciones += factorDato.getDiasVacaciones();
                    break;
                } else {
                    diasTotalesDeVacaciones += Integer.valueOf(tablaFactorIntegracion[i][3].toString());
                }
            }
            if (factorDato != null) {
                diasTotalesDeVacaciones = diasTotalesDeVacaciones - factorDato.getDiasVacaciones();
                int x = Integer.parseInt(calcularAntiguedadExacta(fechaIngreso, TipoAntiguedad.PORCIONDIAS).toString());
                y = factorDato.getDiasVacaciones() * x / 365.0;
                diasTotalesDeVacaciones += y;
                factorDato.setDiasVacacionesTotales(diasTotalesDeVacaciones);
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(39);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaSubsidio()1_Error: ").append(ex));
        }
        return factorDato;
    }

    private void buscaFormulaConceptos(String concepto) {
        strQuery.delete(0, strQuery.length()).append("SELECT c.formulaConcepto FROM ConcepNomDefi c WHERE ");
        String variableConcepto = concepto.substring(concepto.indexOf("_") + 1);
        if (isNumericaString(variableConcepto)) {
            strQuery.append("c.clave = :valorBusqueda ");
        } else {
            variableConcepto = variableConcepto.replace('_', ' ');
            strQuery.append("c.descripcion = :valorBusqueda ");
        }
        strQuery.append("AND c.fecha = (SELECT MAX(cc.fecha) FROM ConcepNomDefi cc WHERE cc.clave = c.clave)");
        camposParametro = new ArrayList<String>(0);
        valoresParametro = new ArrayList<Object>(0);
        camposParametro.add("valorBusqueda");
        valoresParametro.add(variableConcepto);
        String formula = (String) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
        if (formula != null) {
            Double resultado = ejecutaFormula(formula);
            valoresConceptosEmpleados.put(concepto, (Double) resultado);
            int i;
            for (i = 0; i < variablesConceptos.length; i++) {
                if (variablesConceptos[i][0].equalsIgnoreCase(concepto)) {
                    valoresConceptosEmpleados.put(concepto, (Double) resultado);
                    break;
                } else if (variablesConceptos[i][1].equalsIgnoreCase(concepto)) {
                    valoresConceptosEmpleados.put(concepto, (Double) resultado);
                    break;
                }
            }
        }
        valoresParametro = null;
        camposParametro = null;
    }

    private boolean isNumericaString(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private boolean isDoubleString(String cadena) {
        try {
            Double.parseDouble(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private void buscaVaricablesCalcular(String variable, TipoClasificacionFormula tipoAcumulado) {
        StringBuilder query = new StringBuilder(0);
        Object resultado;
        String nombreOriginal = variable;
        variable = variable.toUpperCase();
        /*
         * pendiente
         */
//////        resultado = valoresConceptosEmpleados.get(variable);
//////        resultado = resultado == null ? "" : resultado;
//////        if (resultado.toString().length() > 0) {
//////            return;
//////        }

        /**
         * *****
         */
        if (tipoAcumulado == TipoClasificacionFormula.DATOANUAL | tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL | tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL | tipoAcumulado == TipoClasificacionFormula.DATOPERIODO | tipoAcumulado == TipoClasificacionFormula.DATOFUNCION) {
            buscaVaricablesTipoAcumulados(nombreOriginal, tipoAcumulado);
        } else if (variable.equalsIgnoreCase("NumeroBimestre")) {
            Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
            if ((fecha.get(Calendar.MONTH) + 1) % 2 == 1) {
                resultado = (fecha.get(Calendar.MONTH) + 2) / 2;
            } else {
                resultado = (fecha.get(Calendar.MONTH) + 1) / 2;
            }
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("FechaInicioBimestre")) {
            Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
            if ((fecha.get(Calendar.MONTH) + 1) % 2 == 0) {
                fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);

            }
            fecha.set(Calendar.DATE, fecha.getActualMinimum(Calendar.DAY_OF_MONTH));
            resultado = fecha.getTime();
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("FechaFinalBimestre")) {
            Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
            if ((fecha.get(Calendar.MONTH) + 1) % 2 == 1) {
                fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) + 1);
            }
            fecha.set(Calendar.DATE, fecha.getActualMaximum(Calendar.DAY_OF_MONTH));
            resultado = fecha.getTime();
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DiasBimestre")) {
            Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
            resultado = fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
            if ((fecha.get(Calendar.MONTH) + 1) % 2 == 1) {
                fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) + 1);
            } else {
                fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            }
            resultado = (Integer) resultado + fecha.getActualMaximum(Calendar.DAY_OF_MONTH);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("ISRACargo") | variable.equalsIgnoreCase("ISRSubsidio") | variable.equalsIgnoreCase("ISRNeto")
                | variable.equalsIgnoreCase("SubsEmpleoCalculado")) {
            calculaISPT(null);
        } else if (variable.equalsIgnoreCase("FALTAS") | variable.equalsIgnoreCase("HorasExtrasDobles".toUpperCase()) | variable.equalsIgnoreCase("HorasExtrasTriples".toUpperCase())
                | variable.equalsIgnoreCase("IncapacidadEnfermedad".toUpperCase()) | variable.equalsIgnoreCase("IncapacidadAccidente".toUpperCase()) | variable.equalsIgnoreCase("IncapacidadMaternidad".toUpperCase()) | variable.equalsIgnoreCase("OtrasIncapacidad".toUpperCase())
                | variable.equalsIgnoreCase("DiasIncapacidadEmpleado".toUpperCase()) | variable.equalsIgnoreCase("Ausentismo".toUpperCase()) | variable.equalsIgnoreCase("TExtrasDiaDescanso".toUpperCase()) | variable.equalsIgnoreCase("TExtrasDiaFestivo".toUpperCase())
                | variable.equalsIgnoreCase("TExtrasDiaDomingo".toUpperCase()) | variable.equalsIgnoreCase("Retardos".toUpperCase()) | variable.equalsIgnoreCase("PermisoConSueldo".toUpperCase()) | variable.equalsIgnoreCase("PermisoSinSueldo".toUpperCase())
                | variable.equalsIgnoreCase("DiasFestivos".toUpperCase()) | variable.equalsIgnoreCase("DiasDescanso".toUpperCase()) | variable.equalsIgnoreCase("FALTAS")) {
            if (!valoresConceptosEmpleados.containsKey(variable) ? true : (valoresConceptosEmpleados.get(variable) == null ? true : (valoresConceptosEmpleados.get(variable).toString().isEmpty() ? true : false))) {
                cargarVariablesEmpleadoAsistencias(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime(), ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime(), null, null);//JSA30
            }
        } else if (variable.equalsIgnoreCase("DiasCreditoPeriodo".toUpperCase())) { //Pendiente
            resultado = ejecutaQueryUnico("Select count(a) from Asistencias a inner join a.excepciones ex Where ex.excepcion = ''",
                    null, null);
            valoresConceptosEmpleados.put(variable, (Long) resultado);
        } else if (variable.equalsIgnoreCase("FechaSalidaVacacion".toUpperCase()) | variable.equalsIgnoreCase("FechaRegresoVacacion".toUpperCase())) {
            query.delete(0, query.length());
            query.append("Select rv.salidaVacac,rv.regresoVac,va.diasVac,va.diasPrima from VacacionesAplicacion va inner join va.vacacionesDisfrutadas rv  inner join rv.empleados em ");
            query.append("inner join rv.periodoAplicacion p inner join rv.razonesSociales rs where p.id = :idPeriodo and em.clave = :claveEmp and rs.clave = :claveRazonSocial");
            Object[] vacaciones = (Object[]) ejecutaQueryUnico(query.toString(), new String[]{"idPeriodo", "claveEmp", "claveRazonSocial"}, new Object[]{valoresConceptosEmpleados.get("IdPeriodo".toUpperCase()), valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()), valoresConceptosEmpleados.get("RazonSocial".toUpperCase())});
            if (vacaciones == null) {
                vacaciones = new Object[]{null, null, 0, 0};
            }
            valoresConceptosEmpleados.put("FechaSalidaVacacion".toUpperCase(), (Date) vacaciones[0]);
            valoresConceptosEmpleados.put("FechaRegresoVacacion".toUpperCase(), (Date) vacaciones[1]);
            valoresConceptosEmpleados.put("DiasVacaciones".toUpperCase(), (Integer) vacaciones[2]);
            valoresConceptosEmpleados.put("DiasPrima".toUpperCase(), (Double) vacaciones[3]);
        } else if (variable.equalsIgnoreCase("TipoVacaciones".toUpperCase())) {
            resultado = buscarValoresVacaciones("tiposVacaciones.nombre", TipoMostrarCampo.NORMAL, EntidadesVacaciones.DISFRUTADAS);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("TotalDiasVacaciones".toUpperCase())) {
            resultado = buscarValoresVacaciones("diasVacaciones", TipoMostrarCampo.SUMA, EntidadesVacaciones.DEVENGADAS);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DiasVacaciones".toUpperCase())) {
            boolean calcula = false;
            if (valoresConceptosEmpleados.containsKey("DiasVacaciones".toUpperCase())) {
                Object valor = valoresConceptosEmpleados.get("DiasVacaciones".toUpperCase());
                if (valor == null) {
                    calcula = true;
                }
            }
            if (calcula) {
                resultado = buscarValoresVacaciones("diasVac", TipoMostrarCampo.NORMAL, EntidadesVacaciones.APLICACION);
                valoresConceptosEmpleados.put(variable, resultado);
            }
        } else if (variable.equalsIgnoreCase("DiasPrima".toUpperCase())) {
            boolean calcula = false;
            if (valoresConceptosEmpleados.containsKey("DiasPrima".toUpperCase())) {
                Object valor = valoresConceptosEmpleados.get("DiasPrima".toUpperCase());
                if (valor == null) {
                    calcula = true;
                }
            }
            if (calcula) {
                resultado = buscarValoresVacaciones("diasPrima", TipoMostrarCampo.NORMAL, EntidadesVacaciones.APLICACION);
                valoresConceptosEmpleados.put(variable, resultado);
            }
        } else if (variable.equalsIgnoreCase("DiasVacacionesPendientes".toUpperCase())) {
            resultado = buscarValoresVacaciones("DiasVacacionesPendientes", TipoMostrarCampo.OPERACION, EntidadesVacaciones.APLICACION);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PTUDIAS".toUpperCase())
                || variable.equalsIgnoreCase("PTUPERCEPCIONES".toUpperCase())
                || variable.equalsIgnoreCase("PTUTOTAL".toUpperCase())) {
            if (isCalculoPTU && ptuEmpleado != null) {
                resultado = buscarValoresPTU(variable);
                valoresConceptosEmpleados.put(variable, resultado);
            } else {
                resultado = 0;
            }

        }

    }

    private Object buscarValoresVacaciones(String campo, TipoMostrarCampo tmc, EntidadesVacaciones entidadesVacaciones) {
        StringBuilder query = new StringBuilder(0);
        Object resultado;
        String alias = "";
        if (EntidadesVacaciones.APLICACION == entidadesVacaciones) {
            alias = "va";
        } else if (EntidadesVacaciones.DEVENGADAS == entidadesVacaciones) {
            alias = "vd";
        } else {
            alias = "rv";
        }

        query.append("SELECT ");
        if (tmc == TipoMostrarCampo.COUNT) {
            query.append("COUNT(").append(alias).append(".").append(campo).append(") ");
        } else if (tmc == TipoMostrarCampo.SUMA) {
            query.append("CASE WHEN (COUNT(").append(alias).append(") = 0) THEN 0 ELSE SUM(").append(alias).append(".").append(campo).append(") END ");
        } else if (tmc == TipoMostrarCampo.OPERACION) {
            if (campo.equalsIgnoreCase("DiasVacacionesPendientes")) {
                query.append("CASE WHEN (COUNT(vd) = 0) THEN 0 ELSE sum(CASE WHEN (vd.diasVacaciones IS NULL) THEN 0 ELSE vd.diasVacaciones END) END-CASE WHEN (COUNT(va) = 0) THEN 0 ELSE sum(CASE WHEN (va.diasVac IS NULL) THEN 0 ELSE va.diasVac END) END");
            } else {
                return 0; //no hay campo a validar
            }

        } else {
            query.append(alias).append(".").append(campo).append(" ");
        }

        List<String> camposWhere = new ArrayList<String>();
        List<Object> valoresWhere = new ArrayList<Object>();

        query.append("FROM VacacionesAplicacion va INNER JOIN va.vacacionesDisfrutadas rv INNER JOIN rv.empleados em INNER JOIN rv.razonesSociales rz INNER JOIN rv.periodoAplicacion pn "
                + "RIGHT OUTER JOIN va.vacacionesDevengadas vd INNER JOIN vd.razonesSociales drz INNER JOIN vd.plazasPorEmpleado pem INNER JOIN pem.empleados dem");
        if (tmc == TipoMostrarCampo.OPERACION) {
            camposWhere.addAll(Arrays.asList(new String[]{"claveEmp", "claveRazonSocial"}));
            valoresWhere.addAll(Arrays.asList(new Object[]{valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()), valoresConceptosEmpleados.get("RazonSocial".toUpperCase())}));
            query.append("WHERE drz.clave = :claveRazonSocial AND dem.clave = :claveEmp  OR  (rz.clave = :claveRazonSocial AND em.clave =:claveEmp)");
        } else if (EntidadesVacaciones.APLICACION == entidadesVacaciones || EntidadesVacaciones.DISFRUTADAS == entidadesVacaciones) {
            camposWhere.addAll(Arrays.asList(new String[]{"claveEmp", "claveRazonSocial", "idPeriodo"}));
            valoresWhere.addAll(Arrays.asList(new Object[]{valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()), valoresConceptosEmpleados.get("RazonSocial".toUpperCase()), valoresConceptosEmpleados.get("IdPeriodo".toUpperCase())}));
            query.append("WHERE em.clave = :claveEmp AND rz.clave = :claveRazonSocial AND pn.id = :idPeriodo ");
        } else {
            camposWhere.addAll(Arrays.asList(new String[]{"claveEmp", "claveRazonSocial"}));
            valoresWhere.addAll(Arrays.asList(new Object[]{valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()), valoresConceptosEmpleados.get("RazonSocial".toUpperCase())}));
            query.append("WHERE dem.clave = :claveEmp AND drz.clave = :claveRazonSocial ");
        }

        resultado = (Object) ejecutaQueryUnico(query.toString(), camposWhere.toArray(new String[]{}), valoresWhere.toArray());

        if (resultado == null) {
            try {
                if (campo.contains(TiposVacaciones.class.getSimpleName())) {
                    return "";
                }
                Field field = VacacionesAplicacion.class.getDeclaredField(campo);
                resultado = inicializaValorPorTipoDatoNulo(field.getType());
            } catch (NoSuchFieldException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarValoresVacaciones()1_Error: ").append(ex));
            } catch (SecurityException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarValoresVacaciones()1_Error: ").append(ex));
            }
        }
        return resultado;

    }

    private Object buscarValoresPTU(String campo) {
        Object resultado = null;
        try {
            StringBuilder query = new StringBuilder(0);
            String alias = "ptu";
            if (campo.equals("PTUDIAS")) {
                campo = "ptuDias";
                ptuEmpleado.setPeriodoPtuDias(periodosNomina);
                ptuEmpleado.setTipoCorridaPtuDias(tipoCorrida);
                ptuEmpleado.setTipoNominaPtuDias((TipoNomina) valoresConceptosEmpleados.get("TIPONOMINAENTIDAD"));
            } else if (campo.equals("PTUPERCEPCIONES")) {
                campo = "ptuPercepciones";
                ptuEmpleado.setPeriodoPtuPercep(periodosNomina);
                ptuEmpleado.setTipoCorridaPtuPercep(tipoCorrida);
                ptuEmpleado.setTipoNominaPtuPercep((TipoNomina) valoresConceptosEmpleados.get("TIPONOMINAENTIDAD"));
            } else if (campo.equals("PTUTOTAL")) {
                campo = "ptuDias+ptuPercepciones";
                ptuEmpleado.setPeriodoPtuDias(periodosNomina);
                ptuEmpleado.setPeriodoPtuPercep(periodosNomina);
                ptuEmpleado.setTipoCorridaPtuDias(tipoCorrida);
                ptuEmpleado.setTipoCorridaPtuPercep(tipoCorrida);
                ptuEmpleado.setTipoNominaPtuDias((TipoNomina) valoresConceptosEmpleados.get("TIPONOMINAENTIDAD"));
                ptuEmpleado.setTipoNominaPtuPercep((TipoNomina) valoresConceptosEmpleados.get("TIPONOMINAENTIDAD"));
            }

            query.append("SELECT ");
            query.append(alias).append(".").append(campo).append(" ");
            query.append("FROM PtuEmpleados ptu WHERE ptu.razonesSociales.clave= :claveRazonsocial ");
            query.append("AND ptu.ejercicio= :ejercicio AND ptu.empleados.clave= :claveEmpleado ");
            List<String> camposWhere = new ArrayList<>();
            List<Object> valoresWhere = new ArrayList<>();
            camposWhere.addAll(Arrays.asList(new String[]{"claveRazonsocial", "ejercicio", "claveEmpleado"}));
            valoresWhere.addAll(Arrays.asList(new Object[]{valoresConceptosEmpleados.get("RazonSocial".toUpperCase()), valoresConceptosGlobales.get("AnioPeriodo".toUpperCase()), valoresConceptosEmpleados.get("NumEmpleado".toUpperCase())}));
            resultado = (Object) ejecutaQueryUnico(query.toString(), camposWhere.toArray(new String[]{}), valoresWhere.toArray());
            if (resultado == null) {
                return 0;
            }
        } catch (Exception e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarValoresPTU()1_Error: ").append(e));
        }
        return resultado;
    }

    enum EntidadesVacaciones {

        APLICACION(0), DEVENGADAS(1), DISFRUTADAS(2);
        private final int vacaciones;

        EntidadesVacaciones(int vacaciones) {
            this.vacaciones = vacaciones;
        }

        public int getVacaciones() {
            return vacaciones;
        }
    }

    private void buscaVaricablesTipoAcumulados(String variable, TipoClasificacionFormula tipoAcumulado) {
        Object resultado;

        List<String> variablesExtras = new ArrayList<String>();
        List<Object> valoresExtras = new ArrayList<Object>();
////        BaseOtrosPeriodo_Datos=Dato22
        if (propertieFuente != null) {
            if (propertieFuente.containsKey(variable.concat("_Datos"))) {
                String[] datosExtras = propertieFuente.getProperty(variable.concat("_Datos")).split(","), valores = valoresDatosEspecialesFormula.split("\\|");
                Class tipoDato;
                int i;
                for (i = 0; i < datosExtras.length; i++) {
                    if (propertieFuente.containsKey(datosExtras[i].concat("_Path"))) {
                        variablesExtras.add(propertieFuente.getProperty(datosExtras[i].concat("_Path")));
                        tipoDato = Utilerias.buscarTipoDatoCampo(propertieFuente.getProperty(datosExtras[i].concat("_Path")));
                        valoresExtras.add(Utilerias.castStringTo(tipoDato.getSimpleName(), valores[i]));
                    }
                }
                valoresDatosEspecialesFormula = "";
                for (i = datosExtras.length; i < valores.length; i++) {
                    valoresDatosEspecialesFormula = valoresDatosEspecialesFormula.concat(valores[i]).concat("|");
                }
                if (valoresDatosEspecialesFormula.length() > 0) {
                    if (valoresDatosEspecialesFormula.charAt(valoresDatosEspecialesFormula.length() - 1) == '|') {
                        valoresDatosEspecialesFormula = valoresDatosEspecialesFormula.substring(0, valoresDatosEspecialesFormula.length() - 1);
                    }
                }
            }
        }
        clavePeriodoFuncion = "";
        variable = variable.toUpperCase();
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
        if (tipoAcumulado == TipoClasificacionFormula.DATOFUNCION) {
            variablesTipoFuncion(variable, tipoAcumulado, fecha);
        } else if (variable.equalsIgnoreCase("PercepcionesGravablesNor")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION, ClavesParametrosModulos.tipoBaseAfectaISRNormal, ClavesParametrosModulos.claveBaseNominaISR};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesGravablesDirTabla")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION, ClavesParametrosModulos.tipoBaseAfectaISRDirecto, ClavesParametrosModulos.claveBaseNominaISR};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesGravablesAnual")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION, ClavesParametrosModulos.tipoBaseAfectaISRAnual, ClavesParametrosModulos.claveBaseNominaISR};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DiasPagoPeriodo") | variable.equalsIgnoreCase("DiasPagoMes") | variable.equalsIgnoreCase("DiasPagoBim") | variable.equalsIgnoreCase("DiasPagoAnual")) {
            resultado = periodioAcumuladoPorRangoMeses(tipoAcumulado, fecha, variable);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DiasLaboradosPeriodo") | variable.equalsIgnoreCase("DiasLaboradosMes") | variable.equalsIgnoreCase("DiasLaboradosBim") | variable.equalsIgnoreCase("DiasLaboradosAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionLaborado});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DiasCotizadosPeriodo") | variable.equalsIgnoreCase("DiasCotizadosMes") | variable.equalsIgnoreCase("DiasCotizadosBim") | variable.equalsIgnoreCase("DiasCotizadosAnual")) {
            resultado = periodioAcumuladoPorRangoMeses(tipoAcumulado, fecha, variable);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PermisosPeriodo") | variable.equalsIgnoreCase("PermisosMes") | variable.equalsIgnoreCase("PermisosBim") | variable.equalsIgnoreCase("PermisosAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionPermisoConSueldo, ClavesParametrosModulos.claveExcepcionPermisoSinSueldo});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("IncapacidadEnfermedadPeriodo") | variable.equalsIgnoreCase("IncapacidadEnfermedadMes") | variable.equalsIgnoreCase("IncapacidadEnfermedadBim") | variable.equalsIgnoreCase("IncapacidadEnfermedadAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionIncapacidadPorEnfermedad});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("IncapacidadAccidentePeriodo") | variable.equalsIgnoreCase("IncapacidadAccidenteMes") | variable.equalsIgnoreCase("IncapacidadAccidenteBim") | variable.equalsIgnoreCase("IncapacidadAccidenteAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionIncapacidadPorAccidente});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("IncapacidadMaternidadPeriodo") | variable.equalsIgnoreCase("IncapacidadMaternidadMes") | variable.equalsIgnoreCase("IncapacidadMaternidadBim") | variable.equalsIgnoreCase("IncapacidadMaternidadAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionIncapacidadPorMaternidad});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("TotalIncapacidadesPeriodo") | variable.equalsIgnoreCase("TotalIncapacidadesMes") | variable.equalsIgnoreCase("TotalIncapacidadesBim") | variable.equalsIgnoreCase("TotalIncapacidadesAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionIncapacidadPorAccidente, ClavesParametrosModulos.claveExcepcionIncapacidadPorEnfermedad, ClavesParametrosModulos.claveExcepcionIncapacidadPorMaternidad/*, ClavesParametrosModulos.claveExcepcionOtrasIncapacidades*/});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("TotalFaltasPeriodo") | variable.equalsIgnoreCase("TotalFaltasMes") | variable.equalsIgnoreCase("TotalFaltasBim") | variable.equalsIgnoreCase("TotalFaltasAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionIncapacidadPorAccidente, ClavesParametrosModulos.claveExcepcionIncapacidadPorEnfermedad, ClavesParametrosModulos.claveExcepcionIncapacidadPorMaternidad, /*ClavesParametrosModulos.claveExcepcionOtrasIncapacidades,*/ ClavesParametrosModulos.claveExcepcionAusentismo,
                ClavesParametrosModulos.claveExcepcionFalta, ClavesParametrosModulos.claveExcepcionPermisoConSueldo, ClavesParametrosModulos.claveExcepcionPermisoSinSueldo});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("FaltasPeriodo") | variable.equalsIgnoreCase("FaltasMes") | variable.equalsIgnoreCase("FaltasBim") | variable.equalsIgnoreCase("FaltasAnual")) {
            resultado = asistenciasAcumuladoPorRangoMeses(tipoAcumulado, fecha, new Object[]{ClavesParametrosModulos.claveExcepcionFalta});
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("ISRACargoPeriodo") | variable.equalsIgnoreCase("ISRACargoMes") | variable.equalsIgnoreCase("ISRACargoBim") | variable.equalsIgnoreCase("ISRACargoAnual")) {
            resultado = isrAcumuladoPorRangoMeses(tipoAcumulado, fecha, "isrACargo");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("ISRSubsidioPeriodo") | variable.equalsIgnoreCase("ISRSubsidioMes") | variable.equalsIgnoreCase("ISRSubsidioBim") | variable.equalsIgnoreCase("ISRSubsidioAnual")) {
            resultado = isrAcumuladoPorRangoMeses(tipoAcumulado, fecha, "isrSubsidio");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("ISRNetoPeriodo") | variable.equalsIgnoreCase("ISRNetoMes") | variable.equalsIgnoreCase("ISRNetoBim") | variable.equalsIgnoreCase("ISRNetoAnual") | variable.equalsIgnoreCase("ISRTotal")) {
            resultado = isrAcumuladoPorRangoMeses(tipoAcumulado, fecha, "isrNeto");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("TotalPercepcionesPeriodo") | variable.equalsIgnoreCase("TotalPercepcionesMes") | variable.equalsIgnoreCase("TotalPercepcionesBim") | variable.equalsIgnoreCase("TotalPercepcionesAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"),}, new Object[]{
                Naturaleza.PERCEPCION}, TipoMostrarCampo.SUMA, MovNomConcep.class.getSimpleName().concat(".concepNomDefi.formulaConcepto#LIKE"), "TotalPercepciones");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("TotalDeduccionesPeriodo") | variable.equalsIgnoreCase("TotalDeduccionesMes") | variable.equalsIgnoreCase("TotalDeduccionesBim") | variable.equalsIgnoreCase("TotalDeduccionesAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")}, new Object[]{
                Naturaleza.DEDUCCION}, TipoMostrarCampo.SUMA, MovNomConcep.class.getSimpleName().concat(".concepNomDefi.formulaConcepto#LIKE"), "TotalDeducciones");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRPeriodo") | variable.equalsIgnoreCase("BaseISRMes") | variable.equalsIgnoreCase("BaseISRBim") | variable.equalsIgnoreCase("BaseISRAnual")) {
            String suma = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado").concat("+").concat(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"));
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, suma, new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRNormalPeriodo") | variable.equalsIgnoreCase("BaseISRNormalMes") | variable.equalsIgnoreCase("BaseISRNormalBim") | variable.equalsIgnoreCase("BaseISRNormalAnual")) {
            String suma = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado").concat("+").concat(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"));
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, suma, new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRDirectoPeriodo") | variable.equalsIgnoreCase("BaseISRDirectoMes") | variable.equalsIgnoreCase("BaseISRDirectoBim") | variable.equalsIgnoreCase("BaseISRDirectoAnual")) {
            String suma = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado").concat("+").concat(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"));
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, suma, new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRAnualPeriodo") | variable.equalsIgnoreCase("BaseISRAnualMes") | variable.equalsIgnoreCase("BaseISRAnualBim") | variable.equalsIgnoreCase("BaseISRAnualAnual")) {
            String suma = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado").concat("+").concat(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"));
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, suma, new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRGravablePeriodo") | variable.equalsIgnoreCase("BaseISRGravableMes") | variable.equalsIgnoreCase("BaseISRGravableBim") | variable.equalsIgnoreCase("BaseISRGravableAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRGravableNormalPeriodo") | variable.equalsIgnoreCase("BaseISRGravableNormalMes") | variable.equalsIgnoreCase("BaseISRGravableNormalBim") | variable.equalsIgnoreCase("BaseISRGravableNormalAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR, ClavesParametrosModulos.tipoBaseAfectaISRNormal}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRGravableDirectoPeriodo") | variable.equalsIgnoreCase("BaseISRGravableDirectoMes") | variable.equalsIgnoreCase("BaseISRGravableDirectoBim") | variable.equalsIgnoreCase("BaseISRGravableDirectoAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR, ClavesParametrosModulos.tipoBaseAfectaISRDirecto}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRGravableAnualPeriodo") | variable.equalsIgnoreCase("BaseISRGravableAnualMes") | variable.equalsIgnoreCase("BaseISRGravableAnualBim") | variable.equalsIgnoreCase("BaseISRGravableAnualAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR, ClavesParametrosModulos.tipoBaseAfectaISRAnual}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRExentoPeriodo") | variable.equalsIgnoreCase("BaseISRExentoMes") | variable.equalsIgnoreCase("BaseISRExentoBim") | variable.equalsIgnoreCase("BaseISRExentoAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRExentoNormalPeriodo") | variable.equalsIgnoreCase("BaseISRExentoNormalMes") | variable.equalsIgnoreCase("BaseISRExentoNormalBim") | variable.equalsIgnoreCase("BaseISRExentoNormalAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR, ClavesParametrosModulos.tipoBaseAfectaISRNormal}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRExentoDirectoPeriodo") | variable.equalsIgnoreCase("BaseISRExentoDirectoMes") | variable.equalsIgnoreCase("BaseISRExentoDirectoBim") | variable.equalsIgnoreCase("BaseISRExentoDirectoAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR, ClavesParametrosModulos.tipoBaseAfectaISRDirecto}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseISRExentoAnualPeriodo") | variable.equalsIgnoreCase("BaseISRExentoAnualMes") | variable.equalsIgnoreCase("BaseISRExentoAnualBim") | variable.equalsIgnoreCase("BaseISRExentoAnualAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR, ClavesParametrosModulos.tipoBaseAfectaISRAnual}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSPeriodo") | variable.equalsIgnoreCase("BaseIMSSMes") | variable.equalsIgnoreCase("BaseIMSSBim") | variable.equalsIgnoreCase("BaseIMSSAnual")) {
            String suma = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado").concat("+").concat(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"));
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, suma, new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSFijaPeriodo") | variable.equalsIgnoreCase("BaseIMSSFijaMes") | variable.equalsIgnoreCase("BaseIMSSFijaBim") | variable.equalsIgnoreCase("BaseIMSSFijaAnual")) {
            String suma = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado").concat("+").concat(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"));
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, suma, new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSVariablePeriodo") | variable.equalsIgnoreCase("BaseIMSSVariableMes") | variable.equalsIgnoreCase("BaseIMSSVariableBim") | variable.equalsIgnoreCase("BaseIMSSVariableAnual")) {
            String suma = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado").concat("+").concat(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"));
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, suma, new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISR}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSGravadoPeriodo") | variable.equalsIgnoreCase("BaseIMSSGravableMes") | variable.equalsIgnoreCase("BaseIMSSGravableBim") | variable.equalsIgnoreCase("BaseIMSSGravableAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaIMSS}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSGravadoFijaPeriodo") | variable.equalsIgnoreCase("BaseIMSSGravableFijaMes") | variable.equalsIgnoreCase("BaseIMSSGravableFijaBim") | variable.equalsIgnoreCase("BaseIMSSGravableFijaAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaIMSS, ClavesParametrosModulos.tipoBaseAfectaIMSSFijo}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSGravadoVariablePeriodo") | variable.equalsIgnoreCase("BaseIMSSGravableVariableMes") | variable.equalsIgnoreCase("BaseIMSSGravableVariableBim") | variable.equalsIgnoreCase("BaseIMSSGravableVariableAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaIMSS, ClavesParametrosModulos.tipoBaseAfectaIMSSVariable}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSExentoPeriodo") | variable.equalsIgnoreCase("BaseIMSSExentoMes") | variable.equalsIgnoreCase("BaseIMSSExentoBim") | variable.equalsIgnoreCase("BaseIMSSExentoAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaIMSS}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSExentoFijaPeriodo") | variable.equalsIgnoreCase("BaseIMSSExentoFijaMes") | variable.equalsIgnoreCase("BaseIMSSExentoFijaBim") | variable.equalsIgnoreCase("BaseIMSSExentoFijaAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaIMSS, ClavesParametrosModulos.tipoBaseAfectaIMSSFijo}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseIMSSExentoVariablePeriodo") | variable.equalsIgnoreCase("BaseIMSSExentoVariableMes") | variable.equalsIgnoreCase("BaseIMSSExentoVariableBim") | variable.equalsIgnoreCase("BaseIMSSExentoVariableAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaIMSS, ClavesParametrosModulos.tipoBaseAfectaIMSSVariable}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseInfonavitPeriodo") | variable.equalsIgnoreCase("BaseInfonavitMes") | variable.equalsIgnoreCase("BaseInfonavitBim") | variable.equalsIgnoreCase("BaseInfonavitAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaInfonavit}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BasePTUPeriodo") | variable.equalsIgnoreCase("BasePTUMes") | variable.equalsIgnoreCase("BasePTUBim") | variable.equalsIgnoreCase("BasePTUAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaPTU}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseImpuestoNominaPeriodo") | variable.equalsIgnoreCase("BaseImpuestoNominaMes") | variable.equalsIgnoreCase("BaseImpuestoNominaBim") | variable.equalsIgnoreCase("BaseImpuestoNominaAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaISN}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseDespensaPeriodo") | variable.equalsIgnoreCase("BaseDespensaMes") | variable.equalsIgnoreCase("BaseDespensaBim") | variable.equalsIgnoreCase("BaseDespensaAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaDespensa}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseFondoAhorroPeriodo") | variable.equalsIgnoreCase("BaseFondoAhorroMes") | variable.equalsIgnoreCase("BaseFondoAhorroBim") | variable.equalsIgnoreCase("BaseFondoAhorroAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaFondoAhorro}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseAguinaldoPeriodo") | variable.equalsIgnoreCase("BaseAguinaldoMes") | variable.equalsIgnoreCase("BaseAguinaldoBim") | variable.equalsIgnoreCase("BaseAguinaldoAnual")) {
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), new String[]{MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")}, new Object[]{
                ClavesParametrosModulos.claveBaseNominaAguinaldo}, TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("BaseOtrosPeriodo") | variable.equalsIgnoreCase("BaseOtrosMes") | variable.equalsIgnoreCase("BaseOtrosBim") | variable.equalsIgnoreCase("BaseOtrosAnual")) {
            variablesExtras.add(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.reservado"));
            valoresExtras.add(false);
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), variablesExtras.toArray(new String[]{}), valoresExtras.toArray(), TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesExentas")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesGravables")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesExentas")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesGravadasPer")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesGravadasMesActual")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);

        } else if (variable.equalsIgnoreCase("PercepcionesGravadasMesAnterior")) {
            clavePeriodoFuncion = "Anterior";
            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesExentaPer")) {
//            clavePeriodoFuncion = "Anterior";
//            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesExentaMesActual")) {
//            clavePeriodoFuncion = "Anterior";
//            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesExentaMesAnterior")) {
            clavePeriodoFuncion = "Anterior";
            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesGravadasPer")) {
//            clavePeriodoFuncion = "Anterior";
//            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesGravadasMesActual")) {
//            clavePeriodoFuncion = "Anterior";
//            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesGravadasMesAnterior")) {
            clavePeriodoFuncion = "Anterior";
            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesExentasPer")) {
//            clavePeriodoFuncion = "Anterior";
//            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesExentasMesActual")) {
//            clavePeriodoFuncion = "Anterior";
//            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("DeduccionesExentasMesAnterior")) {
            clavePeriodoFuncion = "Anterior";
            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
            Object[] valoresParam = new Object[]{Naturaleza.DEDUCCION};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("ISRNormal")) {
            resultado = isrAcumuladoPorRangoMeses(tipoAcumulado, fecha, "isrNetoNormal");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("ISRAnual")) {
            resultado = isrAcumuladoPorRangoMeses(tipoAcumulado, fecha, "isrNetoAnual");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("ISRDirectoTabla")) {
            resultado = isrAcumuladoPorRangoMeses(tipoAcumulado, fecha, "isrNetoDirecto");
            valoresConceptosEmpleados.put(variable, resultado);
        } else if (variable.equalsIgnoreCase("PercepcionesGravablesNor")) {
            String[] camposParam = new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.tipoAfecta"),
                MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave")};
            Object[] valoresParam = new Object[]{Naturaleza.PERCEPCION, ClavesParametrosModulos.tipoBaseAfectaISRNormal, ClavesParametrosModulos.claveBaseNominaISR};
            resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fecha, MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), camposParam, valoresParam,
                    TipoMostrarCampo.SUMA, null, null);
            valoresConceptosEmpleados.put(variable, resultado);
        }
    }

    private void variablesTipoFuncion(String variable, TipoClasificacionFormula tipoAcumulado, Calendar fechaPeriodo) {
        Object resultado;
        String funcion = variable;
        String parametroFuncion = variable.substring(funcion.indexOf("(") + 1, funcion.indexOf(")")).replace("'", "");
        String funcionNombre = variable.substring(0, funcion.indexOf("("));
        String[] parametrosFuncion = parametroFuncion.split(",");
        List<String> camposFuncion = new ArrayList<>();
        List<Object> valoresFuncion = new ArrayList<>();
        int posAcum = 1;
        clavePeriodoFuncion = "";
        boolean usaBase = false;
        String nombreBase = "";
        if (funcionNombre.equalsIgnoreCase("ACUMCNC")) {
            //Concepto
            camposFuncion.add(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.clave"));
            valoresFuncion.add(parametrosFuncion[0].replace("'", ""));
        } else if (funcionNombre.equalsIgnoreCase("ACUMBASE")) {
            //Base
            usaBase = true;
            nombreBase = parametrosFuncion[0];
        } else if (funcionNombre.equalsIgnoreCase("ACUMCNCBASE")) {
            //Concepto,Base
            camposFuncion.add(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.clave"));
            valoresFuncion.add(parametrosFuncion[0]);
            nombreBase = parametrosFuncion[1];
            posAcum = 2;
        } else if (funcionNombre.equalsIgnoreCase("DEDUCCREDITOS")) {
            //creditos
            camposFuncion.add(MovNomConcep.class.getSimpleName().concat(".creditoMovimientos.creditoPorEmpleado.creditoAhorro.concepNomiDefin.clave"));
            camposFuncion.add(MovNomConcep.class.getSimpleName().concat(".creditoMovimientos.creditoPorEmpleado.creditoAhorro.tipoConfiguracion"));
            valoresFuncion.add(parametrosFuncion[0]);
            valoresFuncion.add("1");
        } else if (funcionNombre.equalsIgnoreCase("DEDUCAHORROS")) {
            //Ahorros
            camposFuncion.add(MovNomConcep.class.getSimpleName().concat(".creditoMovimientos.creditoPorEmpleado.creditoAhorro.concepNomiDefin.clave"));
            camposFuncion.add(MovNomConcep.class.getSimpleName().concat(".creditoMovimientos.creditoPorEmpleado.creditoAhorro.tipoConfiguracion"));
            valoresFuncion.add(parametrosFuncion[0]);
            valoresFuncion.add("2");
        }

        if (usaBase) {
            camposFuncion.add(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.baseAfecConcepNom.baseNomina.clave"));

            if (nombreBase.equalsIgnoreCase("ISR")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaISR);
            } else if (nombreBase.equalsIgnoreCase("IMSS")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaIMSS);
            } else if (nombreBase.equalsIgnoreCase("INF")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaInfonavit);
            } else if (nombreBase.equalsIgnoreCase("PTU")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaPTU);
            } else if (nombreBase.equalsIgnoreCase("ISN")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaISN);
            } else if (nombreBase.equalsIgnoreCase("DES")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaDespensa);
            } else if (nombreBase.equalsIgnoreCase("AHO")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaFondoAhorro);
            } else if (nombreBase.equalsIgnoreCase("AGUI")) {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaAguinaldo);
            } else {
                valoresFuncion.add(ClavesParametrosModulos.claveBaseNominaISR);
            }
        }
        ////TipoAcumulado
        if (parametrosFuncion[posAcum].equalsIgnoreCase("MENSUAL")) {
            tipoAcumulado = TipoClasificacionFormula.DATOMENSUAL;
        } else if (parametrosFuncion[posAcum].equalsIgnoreCase("BIMESTRAL")) {
            tipoAcumulado = TipoClasificacionFormula.DATOBIMESTRAL;
        } else if (parametrosFuncion[posAcum].equalsIgnoreCase("ANUAL")) {
            tipoAcumulado = TipoClasificacionFormula.DATOANUAL;
        } else {
            tipoAcumulado = TipoClasificacionFormula.DATOPERIODO;
        }

        ///Ejercicio
        if (parametrosFuncion[posAcum + 1].equalsIgnoreCase("ANTERIOR")) {
            clavePeriodoFuncion = parametrosFuncion[posAcum + 1];
            fechaPeriodo.set(Calendar.YEAR, fechaPeriodo.get(Calendar.YEAR) - 1);
        } else if (isNumericaString(parametrosFuncion[posAcum + 1])) {
            clavePeriodoFuncion = parametrosFuncion[posAcum + 1];
            fechaPeriodo.set(Calendar.YEAR, Integer.parseInt(parametrosFuncion[posAcum + 1]));
        }

        //Numero
        if (parametrosFuncion[posAcum + 2].equalsIgnoreCase("ANTERIOR")) {
            clavePeriodoFuncion = parametrosFuncion[posAcum + 2];
            if (tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {
                fechaPeriodo.set(Calendar.MONTH, fechaPeriodo.get(Calendar.MONTH) - 1);
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
                if ((fechaPeriodo.get(Calendar.MONTH) + 1) % 2 == 0) {
                    fechaPeriodo.set(Calendar.MONTH, fechaPeriodo.get(Calendar.MONTH) - 3);
                } else {
                    fechaPeriodo.set(Calendar.MONTH, fechaPeriodo.get(Calendar.MONTH) - 2);
                }
            }
        } else if (isNumericaString(parametrosFuncion[posAcum + 2])) {
            clavePeriodoFuncion = parametrosFuncion[posAcum + 2].replace("'", "");
            if (tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {
                fechaPeriodo.set(Calendar.MONTH, Integer.parseInt(parametrosFuncion[posAcum + 2]) - 1);
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
                int bimestre = Integer.parseInt(parametrosFuncion[posAcum + 2]);
                switch (bimestre) {
                    case 1:
                        fechaPeriodo.set(Calendar.MONTH, Calendar.JANUARY);
                        break;
                    case 2:
                        fechaPeriodo.set(Calendar.MONTH, Calendar.MARCH);
                        break;
                    case 3:
                        fechaPeriodo.set(Calendar.MONTH, Calendar.MAY);
                        break;
                    case 4:
                        fechaPeriodo.set(Calendar.MONTH, Calendar.JULY);
                        break;
                    case 5:
                        fechaPeriodo.set(Calendar.MONTH, Calendar.SEPTEMBER);
                        break;
                    case 6:
                        fechaPeriodo.set(Calendar.MONTH, Calendar.NOVEMBER);
                        break;
                }
            }
        }

        //Valor Mostrar
        String mostrar = MovNomConcep.class.getSimpleName().concat(".resultado");
        if (parametrosFuncion.length > 4) {
            if (parametrosFuncion[posAcum + 3].equalsIgnoreCase("EXENTO")) {
                mostrar = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento");
            } else if (parametrosFuncion[posAcum + 3].equalsIgnoreCase("GRAVABLE")) {
                mostrar = MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado");
            }
        }

        resultado = movimientosAcumuladoPorRangoMeses(tipoAcumulado, fechaPeriodo, mostrar, camposFuncion.toArray(new String[]{}), valoresFuncion.toArray(), TipoMostrarCampo.SUMA, null, null);
        funcion = funcion.replace("(", "").replace("'", "").replace(",", "").replace(")", "");
        valoresConceptosEmpleados.put(funcion, resultado);
    }

    private String clavePeriodoFuncion = "";

    private int calcularEdad(Date fecha) {
        Calendar birth = new GregorianCalendar();
        Calendar today = new GregorianCalendar();
        int age;
        int factor = 0;
        Date currentDate = new Date();
        birth.setTime(fecha);
        today.setTime(currentDate);
        if (today.get(Calendar.MONTH) <= birth.get(Calendar.MONTH)) {
            if (today.get(Calendar.MONTH) == birth.get(Calendar.MONTH)) {
                if (today.get(Calendar.DATE) < birth.get(Calendar.DATE)) {
                    factor = -1; //Aun no celebra su cumpleaÃ±os
                }
            } else {
                factor = -1; //Aun no celebra su cumpleaÃ±os
            }
        }
        age = (today.get(Calendar.YEAR) - birth.get(Calendar.YEAR)) + factor;
        return age;

    }

    enum TipoAntiguedad {

        ANTIGUEDADENTERO, ANTIGUEDADEXACTA, PORCIONANTIGUEDAD, PORCIONDIAS;
    }

    private Object calcularAntiguedadExacta(Date fechaInicial, TipoAntiguedad tipoAntiguedad) {
        if (valoresConceptosEmpleados == null) {
            valoresConceptosEmpleados = new HashMap();
        }
        Date fechaFinal = null;
        if (fechaBajaFiniq == null) {
            fechaFinal = new Date();
        } else {
            fechaFinal = fechaBajaFiniq.getTime();
        }
        DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        String fechaInicioString = df.format(fechaInicial);
        String fechaFinalString = df.format(fechaFinal);
        try {
            fechaInicial = df.parse(fechaInicioString);
            fechaFinal = df.parse(fechaFinalString);
            long fechaInicialMs, fechaFinalMs, diferencia;
            fechaInicialMs = fechaInicial.getTime();
            fechaFinalMs = fechaFinal.getTime();
            diferencia = fechaFinalMs - fechaInicialMs;
            double dias, antiguedad, antiguedadDias;
            dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            antiguedad = dias / 365;
            antiguedadDias = (dias % 365);
            if (tipoAntiguedad == TipoAntiguedad.ANTIGUEDADEXACTA) {
                return antiguedad;
            } else if (tipoAntiguedad == TipoAntiguedad.PORCIONANTIGUEDAD) {
                return antiguedad - (double) ((int) antiguedad);
            } else if (tipoAntiguedad == TipoAntiguedad.PORCIONDIAS) {
                return (int) antiguedadDias;
            } else {
                return (int) antiguedad;
            }
        } catch (ParseException ex) {
        }
        return 0.0;
    }

//calculo finiquitos
    private double calculoISRFiniquitos(TipoCorrida tipoCorrida) {
        Calendar fechaBajaEmpleado = new GregorianCalendar();
        Double baseGravadaFiniquitos = 0.0, isrMesAnterior = 0.0, baseGravableMesAnterior = 0.0, isrFiniquitos = 0.0, factor = 0.0, baseGravadaAnual = 0.0;
        ValorTablaISR tablaISR;
        retenido = new CalculoISR();
        if (fechaBajaFiniq != null) {
            fechaBajaEmpleado = fechaBajaFiniq;
        } else {
            fechaBajaEmpleado.setTime((Date) valoresConceptosEmpleados.get("FechaBaja".toUpperCase()));
        }
        //Date fechaBajaEmpleado = (Date) valoresConceptosEmpleados.get("FechaBaja".toUpperCase());
//        if (fechaBajaEmpleado.getTime().before(periodosNomina.getFechaFinal()) || fechaBajaEmpleado.getTime().compareTo(periodosNomina.getFechaFinal()) == 0) {   //
        baseGravadaFiniquitos = acumuladoAnual + acumuladoDirecto + acumuladoNormal;
        //****************************baja es en diciembre***********************************//
        if (fechaBajaEmpleado.get(Calendar.MONTH) == Calendar.DECEMBER) {//DiasNormalesAPagar
            double diasPagoTotal = (Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase());
            baseGravadaAnual = ((baseGravadaFiniquitos + acumuladosPorTipoISRAnual()) / diasPagoTotal) * factorAnual;
            if (baseGravadaAnual > 0) {
                tablaISR = aplicacionTablaISR(baseGravadaAnual, false, tipoCorrida);
                retenido.setIsrACargoNormal((Double) valoresConceptosEmpleados.get("ISRACARGO".toUpperCase()));
                retenido.setSubsidioEmpleoNormal((Double) (valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase() == null ? 0.0 : valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase()))));
                factor = tablaISR.getIsrCausado() / baseGravadaAnual;
                isrFiniquitos = factor * baseGravadaFiniquitos;
            } else {
                retenido.setIsrACargoNormal(0.0);
                retenido.setSubsidioEmpleoNormal(0.0);
                retenido.setIsrNetoNormal(0.0);
                retenido.setIsrSubsidioNormal(0.0);
            }
        } else {
            //****************************baja es antes de diciembre***********************************//
            //Mes Anterior a la fecha de baja
            //fechaBajaEmpleado.set(Calendar.MONTH, fechaBajaEmpleado.get(Calendar.MONTH) - 1);
            Double[] acumulados = baseGravableAcumuladaMesAnterior(fechaBajaEmpleado); //falta fecha
            baseGravableMesAnterior = acumulados[0];
            if (baseGravadaFiniquitos > baseGravableMesAnterior) {
                isrMesAnterior = acumulados[1];
                factor = isrMesAnterior / baseGravableMesAnterior;
                isrFiniquitos = baseGravadaFiniquitos * factor;
            } else if (baseGravadaFiniquitos > 0) {
                tablaISR = aplicacionTablaISR(baseGravadaFiniquitos, false, tipoCorrida);
                retenido.setIsrACargoNormal((Double) valoresConceptosEmpleados.get("ISRACARGO".toUpperCase()));
                retenido.setSubsidioEmpleoNormal((Double) (valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase()) == null ? 0.0 : valoresConceptosEmpleados.get("SubsEmpleoCalculado".toUpperCase())));
                isrFiniquitos = tablaISR.getIsrCausado(); // isrNeto
            } else {
                retenido.setIsrACargoNormal(0.0);
                retenido.setSubsidioEmpleoNormal(0.0);
                retenido.setIsrNetoNormal(0.0);
                retenido.setIsrSubsidioNormal(0.0);
            }
        }
//        }

        return isrFiniquitos;
    }

    /*
     * fechaPeriodo esta fecha se lee extrae mes para calcular mes a regresar
     * suma de sus resultados tipo que tipo a afectar es si isr, imsss, etc.
     * Modificado
     */
    private double calcularMovimientosPorMesTipoAfecta(List<PeriodosNomina> periodos, String tipo) {
        camposParametro = new ArrayList<String>();
        valoresParametro = new ArrayList<Object>();
        strQuery.delete(0, strQuery.length()).append("SELECT CASE WHEN (COUNT(m) > 0) THEN SUM(mba.resultado) ELSE 0 END * 1.0 ");
        strQuery.append("FROM MovNomConcep m INNER JOIN m.periodosNomina p INNER JOIN m.tipoNomina tn INNER JOIN m.empleado em INNER JOIN m.tipoCorrida tc INNER JOIN m.concepNomDefi c ");
        strQuery.append("INNER JOIN m.movNomBaseAfecta mba INNER JOIN mba.baseAfecConcepNom ba INNER JOIN ba.baseNomina bn INNER JOIN m.razonesSociales rs INNER JOIN m.plazasPorEmpleado pemp ");
        strQuery.append("WHERE m.uso = 0 AND tn.clave = :claveTipoNomina AND em.clave = :claveEmp AND tc.clave = :claveTipoCorrida AND bn.clave = :claveBaseNomina AND rs.clave = :claveRazonSocial AND pemp.clave = :clavePlazaEmpleado AND p.tipoCorrida.clave = :claveTipoCorrida ");
        camposParametro.add("claveEmp");
        camposParametro.add("claveTipoCorrida");
        camposParametro.add("claveTipoNomina");
        camposParametro.add("claveBaseNomina");
        camposParametro.add("claveRazonSocial");
        camposParametro.add("clavePlazaEmpleado");
        valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
        valoresParametro.add(tipo); //basenomina clave
        valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
        valoresParametro.add(valoresConceptosEmpleados.get("PlazaEmpleado".toUpperCase()));
        if (!periodos.isEmpty()) {
            int i;
            strQuery.append("AND (");
            for (i = 0; i < periodos.size(); i++) {
                strQuery.append("(p.clave = :clavePeriodo").append(i).append(" AND  p.año = :yearPeriodo").append(i).append(")");
                camposParametro.add("clavePeriodo".concat(String.valueOf(i)));
                camposParametro.add("yearPeriodo".concat(String.valueOf(i)));
                valoresParametro.add(periodos.get(i).getClave());
                valoresParametro.add(periodos.get(i).getAño());
                if (i < periodos.size() - 1) {
                    strQuery.append(" OR ");
                }
            }
            strQuery.append(") ");
        }

        double baseGravable = (Double) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
        if (mensajeResultado.getNoError() == -100) {
            mensajeResultado.setNoError(60);
            return 0.0;
        }
        return baseGravable;
    }

    //regresa conceptos acumulados del mes anterior
    private Double[] baseGravableAcumuladaMesAnterior(Calendar fechaPeriodo) {
        Double acumuladosMesAnterior, acumuladoISRMesAnterior;
        Double[] acumulados = new Double[]{0.00, 0.00};
        fechaPeriodo.set(Calendar.MONTH, fechaPeriodo.get(Calendar.MONTH) - 1);
        List<PeriodosNomina> periodos = buscarPeriodosPorRangoMeses(0, fechaPeriodo);
        acumuladosMesAnterior = calcularMovimientosPorMesTipoAfecta(periodos, ClavesParametrosModulos.claveBaseNominaISR.toString());
        acumulados[0] = acumuladosMesAnterior;
        acumuladoISRMesAnterior = ObtenerISRAcumuladoMes(periodos);
        acumulados[1] = acumuladoISRMesAnterior;
        camposParametro = null;
        valoresParametro = null;
        return acumulados;
    }

    /*
     * fechaPeriodo esta fecha se lee extrae mes para calcular mes a regresar
     * suma de sus resultados tipo que tipo a afectar es si isr, imsss, etc.
     * Modificado
     */
    private Double ObtenerISRAcumuladoMes(List<PeriodosNomina> periodos) {
        camposParametro = new ArrayList<String>();
        valoresParametro = new ArrayList<Object>();
        strQuery.delete(0, strQuery.length()).append("SELECT CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrRetenidoNormal IS NULL) THEN 0.0 ELSE (isr.isrRetenidoNormal) END)) END + ");
        strQuery.append("CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrRetenidoDirecto IS NULL) THEN 0.0 ELSE (isr.isrRetenidoDirecto) END)) END + ");
        strQuery.append("CASE WHEN (COUNT(isr) = 0 ) THEN 0.0 ELSE (SUM(CASE WHEN (isr.isrRetenidoAnual IS NULL) THEN 0.0 ELSE (isr.isrRetenidoAnual) END)) END ");
        strQuery.append("FROM ").append(CalculoISR.class.getSimpleName()).append(" isr INNER JOIN isr.movNomConcep mov WHERE mov.id in ");//JSA23
        strQuery.append("(SELECT m.id FROM MovNomConcep m INNER JOIN m.periodosNomina p INNER JOIN m.empleado em INNER JOIN m.concepNomDefi c ");
        strQuery.append("INNER JOIN m.tipoCorrida tc INNER JOIN m.razonesSociales rs INNER JOIN m.plazasPorEmpleado pemp  INNER JOIN m.tipoNomina tn ");
        strQuery.append("WHERE m.uso = 0 AND em.clave = :claveEmp AND tc.clave = :claveTipoCorrida AND c.formulaConcepto LIKE CONCAT('%', :formulaConcepto, '%') ");
        strQuery.append("AND rs.clave = :claveRazonSocial AND pemp.clave = :clavePlazaEmpleado AND tn.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida ");
        camposParametro.add("claveEmp");
        valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
        camposParametro.add("claveTipoCorrida");
        valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
        camposParametro.add("claveTipoNomina");
        valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
        camposParametro.add("claveRazonSocial");
        valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
        camposParametro.add("clavePlazaEmpleado");
        valoresParametro.add(valoresConceptosEmpleados.get("PlazaEmpleado".toUpperCase()));
        camposParametro.add("formulaConcepto");
        valoresParametro.add("CalculoISR");
        if (!periodos.isEmpty()) {
            int i;
            strQuery.append("AND (");
            for (i = 0; i < periodos.size(); i++) {
                strQuery.append("(p.clave = :clavePeriodo").append(i).append(" AND  p.año = :yearPeriodo").append(i).append(")");
                camposParametro.add("clavePeriodo".concat(String.valueOf(i)));
                camposParametro.add("yearPeriodo".concat(String.valueOf(i)));
                valoresParametro.add(periodos.get(i).getClave());
                valoresParametro.add(periodos.get(i).getAño());
                if (i < periodos.size() - 1) {
                    strQuery.append(" OR ");
                }
            }
            strQuery.append(") ");
        }
        strQuery.append(") ");

        Double baseGravable = (Double) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
        if (mensajeResultado.getNoError() == -100) {
            mensajeResultado.setNoError(60);
            return 0.0;
        }
        return baseGravable;
    }

    //busca plaza por empleado segun clave del empleado
    private List<PlazasPorEmpleadosMov> buscarPlazaPorEmpleado(String claveEmpleado, String claveTipoNomina, String claveRegPatronal, String claveDepto, String claveCtrCosto,
            int tipoSalario) {
        List<PlazasPorEmpleadosMov> plazasPorEmpleadosMov = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT pem FROM PlazasPorEmpleadosMov pem INNER JOIN pem.plazasPorEmpleado pe INNER JOIN pe.empleados em ");
            strWhere.delete(0, strWhere.length()).append("WHERE em.clave = :claveEmpleado ");
            camposParametro.add("claveEmpleado");
            valoresParametro.add(claveEmpleado);
            if (!claveTipoNomina.isEmpty()) {
                strQuery.append("INNER JOIN pem.tipoNomina tn ");
                strWhere.append("and tn.clave = :claveTipoNomina ");
                camposParametro.add("claveTipoNomina");
                valoresParametro.add(claveTipoNomina);
            }
            if (!claveRegPatronal.isEmpty()) {
                strQuery.append("INNER JOIN pe.registroPatronal rp ");
                strWhere.append("and rp.clave = :claveRegPatronal ");
                camposParametro.add("claveRegPatronal");
                valoresParametro.add(claveRegPatronal);
            }
            if (!claveDepto.isEmpty()) {
                strQuery.append("INNER JOIN pem.departamentos dp ");
                strWhere.append("and dp.clave = :claveDepto ");
                camposParametro.add("claveDepto");
                valoresParametro.add(claveDepto);
            }
            if (!claveCtrCosto.isEmpty()) {
                strQuery.append("INNER JOIN pem.centroDeCosto cc ");
                strWhere.append("and cc.clave = :claveCtrCosto ");
                camposParametro.add("claveCtrCosto");
                valoresParametro.add(claveCtrCosto);
            }
            if (tipoSalario > 0) {
                strWhere.append("and pem.tipoSalario = :tipoSalario ");
                camposParametro.add("tipoSalario");
                valoresParametro.add(tipoSalario);
            }
            strQuery.append(strWhere);
            plazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(55);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarPlazaPorEmpleado()1_Error: ").append(ex));
            getSession().getTransaction().rollback();
        }
        return plazasPorEmpleadosMov;
    }

    public Mensaje calculaSDIPorEmpleado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, String controlador, ParametrosExtra parametrosExtra, boolean soloCalculo, boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra) {
        Empleados empleado = plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados();

        return calculaSalarioDiarioIntegerado(empleado.getClave(), empleado.getClave(), plazasPorEmpleadosMov.getTipoNomina().getClave(),/*
                 * tipoCorrida
                 */ "", /*
                 * periodoNomina
                 */ null,
                /*
                 * clavePuesto
                 */ "", /*
                 * categoriaPuestos
                 */ "",/*
                 * claveTurno
                 */ "", empleado.getRazonesSociales().getClave(), plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal().getClave(),
                /*
                 * Forma Pago
                 */ "", /*
                 * Depto
                 */ "", /*
                 * Centro Costo
                 */ "", /*
                 * tipo salario
                 */ null, /*
                 * contrato
                 */ null, /*
                 * status
                 */ null, controlador, 0, parametrosExtra, soloCalculo, peticionModuloCalculoSalarioDiarioIntegrado, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje calculaSalarioDiarioIntegerado(String claveEmpIni, String claveEmpFin, String claveTipoNomina, String claveTipoCorrida, Long idPeriodo,
            String clavePuesto, String claveCategoriasPuestos, String claveTurno, String claveRazonSocial, String claveRegPatronal, String claveFormaDePago,
            String claveDepto, String claveCtrCosto, Integer tipoSalario, String tipoContrato, Boolean status, String controlador, int uso, ParametrosExtra parametrosExtra, boolean soloCalculo,
            boolean peticionModuloCalculoSalarioDiarioIntegrado, String uuidCxn, String uuidCxnMaestra) {
        double sdi = 0.0;
        manejaPagosPorHora = null;
        factorAnual = null;
        manejoHorasPor = null;
        manejoSalarioDiario = null;
        tipoTablaISR = null;
        valoresConceptosGlobales = new HashMap();
        isCalculoSDI = true;
        Calendar cIni = Calendar.getInstance(), cFin = Calendar.getInstance();
        if (parametrosExtra.getFechaInicioPeriodo() == null | parametrosExtra.getFechaFinPeriodo() == null) {
            if (parametrosExtra.getFechaCalculoSDI() == null) {
                parametrosExtra.setFechaInicioPeriodo(cIni.getTime());
                parametrosExtra.setFechaFinPeriodo(cIni.getTime());
                if (fechaActual == null) {
                    fechaActual = Calendar.getInstance();
                    fechaActual.setTime(cIni.getTime());
                }
            } else {
                parametrosExtra.setFechaInicioPeriodo(parametrosExtra.getFechaCalculoSDI());
                parametrosExtra.setFechaFinPeriodo(parametrosExtra.getFechaCalculoSDI());
                if (fechaActual == null) {
                    fechaActual = Calendar.getInstance();
                    fechaActual.setTime(parametrosExtra.getFechaCalculoSDI());
                }
            }
        }
        if (parametrosExtra.getMascaraResultado() != null) {
            mascaraResultadoGral = parametrosExtra.getMascaraResultado();
            if (mascaraResultadoGral[1].length() > 0) {
                String factorString = ".", minimunString = ".";
                for (int i = 0; i < mascaraResultadoGral[1].length() - 1; i++) {
                    factorString += "0";
                }
                minimunString = factorString;
                factorString += "1";
                minimunString += "05";
                factorRedondeoGral = Double.valueOf(factorString);
                minimoRedondeoGral = Double.valueOf(minimunString);
            }
        }
        if (parametrosExtra.getTipoAccionMascaras() != null) {
            tipoAccionMascarasGral = parametrosExtra.getTipoAccionMascaras();
        }
        cIni.setTime(parametrosExtra.getFechaInicioPeriodo());
        cFin.setTime(parametrosExtra.getFechaFinPeriodo());
        if (fechaActual == null) {
            fechaActual = Calendar.getInstance();
            fechaActual.setTime(cIni.getTime());
        }
        valoresConceptosGlobales.put(parametroFechaInicial, cIni);
        valoresConceptosGlobales.put(parametroFechaFinal, cFin);
        valoresConceptosGlobales.put("ClaveTipoCorrida".toUpperCase(), claveTipoCorrida);
        SalariosIntegrados ultimoSalarioIntegrado;
        mensajeResultado = new Mensaje();
        mensajeResultado.setNoError(0);
        mensajeResultado.setError("");
        mensajeResultado.setResultado(sdi);

        if (mensajeResultado.getNoError() != 0) {
            return mensajeResultado;
        }
        try {
            buscaPeriodicidadesOrPeriodosNomina(claveTipoNomina, claveTipoCorrida, idPeriodo, cFin, uuidCxn);
            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }
            cargaTablaFactorIntegracion(controlador, null, true, true, false, cFin.getTime(), cFin.get(Calendar.YEAR), uuidCxnMaestra);
            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            obtenerFactores(claveRazonSocial);
            if (mensajeResultado.getNoError() != 0) {
                return mensajeResultado;
            }
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            //mensajeRespuesta.setNoError(24);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargaTablaFactorIntegracion()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        } finally {
            setSession(null);
        }
        setSession(HibernateUtil.currentSession(uuidCxn));  //crea conexion
        getSession().beginTransaction();
        cargarVariablesConceptosCompilador();
        List<PlazasPorEmpleadosMov> plazasPorEmpleadosMov;
        inicializaValoresPeriodoNomina(periodosNomina);
        if (!soloCalculo) {
            plazasPorEmpleadosMov = obtenerPlazasPorEmpleados(claveEmpIni, claveEmpFin, claveTipoNomina, clavePuesto, claveCategoriasPuestos, claveTurno, claveRazonSocial, claveRegPatronal, claveDepto, claveCtrCosto, tipoSalario, tipoContrato, status, claveTipoCorrida, claveFormaDePago, parametrosExtra.getFechaInicioPeriodo(), parametrosExtra.getFechaFinPeriodo());
        } else {
            plazasPorEmpleadosMov = (List<PlazasPorEmpleadosMov>) parametrosExtra.getValoresExtras().get(0);
        }
        plazasPorEmpleadosMov = (plazasPorEmpleadosMov == null) ? new ArrayList<PlazasPorEmpleadosMov>() : plazasPorEmpleadosMov;
        if (soloCalculo) {
            if (plazasPorEmpleadosMov.isEmpty()) {
                plazasPorEmpleadosMov.add(new PlazasPorEmpleadosMov());
            }
        }

        if (!plazasPorEmpleadosMov.isEmpty() || soloCalculo) {
            try {
                //pendiente forma obtener controladores
                valoresConceptosEmpleados = new HashMap();
                Calendar fechaActualCalculoSDI = Calendar.getInstance();
                if (parametrosExtra.getFechaCalculoSDI() != null) {
                    fechaActualCalculoSDI.setTime(parametrosExtra.getFechaCalculoSDI());
                }
                int i, j;
                salarioMinimoDF = buscaSalarioPorZona('A').getSalario();
                valoresConceptosEmpleados.put("SalarioMinDF".toUpperCase(), salarioMinimoDF);
                String claveNominaTemp = "";
                List<SalariosIntegradosDet> salarioIntegradosDet = new ArrayList<SalariosIntegradosDet>();
                double sdf = 0, sdif, sdiv, factorIntegracion, sueldoDiario, diasTotalesDelPeriodo;//diasTotalesDelPeriodo nos indica los dias que tuvo el periodo para poner obtener un salario diario fijo.
                for (i = 0; i < plazasPorEmpleadosMov.size(); i++) {
                    sdif = 0;
                    sdiv = 0;
                    if (!claveNominaTemp.equalsIgnoreCase(soloCalculo ? claveTipoNomina : plazasPorEmpleadosMov.get(i).getTipoNomina().getClave())) {
                        periodosNomina = buscaPeriodoNominaActual(soloCalculo ? claveTipoNomina : plazasPorEmpleadosMov.get(i).getTipoNomina().getClave(), claveTipoCorrida, idPeriodo, fechaActualCalculoSDI);
                        claveNominaTemp = soloCalculo ? claveTipoNomina : plazasPorEmpleadosMov.get(i).getTipoNomina().getClave();
                    }
                    inicializaValoresPeriodoNomina(periodosNomina);
                    valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
                    obtenerIngresosReingresosBajas(plazasPorEmpleadosMov.get(i));
                    cargarVariablesGlobalesEmpleadoPorPlaza(plazasPorEmpleadosMov.get(i), true, true, TipoSueldos.SUELDODIARIOFINAL, null, null, null, fechaActualCalculoSDI);//JSA30

                    sueldoDiario = (Float) valoresConceptosEmpleados.get("SUELDODIARIO".toUpperCase());
                    if (valoresConceptosEmpleados.get("FactorIntegracion".toUpperCase()) == null) {
                        mensajeResultado.setNoError(25);
                        mensajeResultado.setError("No existen el factor de integracion capturado, favor de verificarlo");
                        return mensajeResultado;

                    }
                    factorIntegracion = (Double) valoresConceptosEmpleados.get("FactorIntegracion".toUpperCase());
                    diasTotalesDelPeriodo = (Integer) valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase());

                    if (peticionModuloCalculoSalarioDiarioIntegrado) {//considerar movimientos fijos //JEVC06
                        if (periodosNomina != null) {
                            sdf = salarioDiarioFijo(claveTipoCorrida, claveTipoNomina, periodosNomina.getId(), plazasPorEmpleadosMov.get(i).getPlazasPorEmpleado(), claveCtrCosto, claveRazonSocial) / diasTotalesDelPeriodo;
                        }
                    }
                    sdif = sdif + (((sdf == 0 ? sueldoDiario : sdf)) * factorIntegracion);
                    sdiv = sdiv + salarioDiarioVariableBim(fechaActualCalculoSDI);

                    ultimoSalarioIntegrado = buscarSalarioIntegradoActual(fechaActualCalculoSDI, plazasPorEmpleadosMov.get(i).getPlazasPorEmpleado().getEmpleados());
                    if (ultimoSalarioIntegrado != null ? ultimoSalarioIntegrado.getSalarioDiarioIntegrado() > -1 : false) {
                        salarioIntegradosDet = buscarSalarioIntegradosDetalle(ultimoSalarioIntegrado);
                    }

                    boolean continuar = true, continuarElimacion = true;

                    if (ultimoSalarioIntegrado != null) {
                        double sdiTrunc = Utilerias.truncateDecimal(ultimoSalarioIntegrado.getSalarioDiarioIntegrado(), 2);
                        double sdfTrunc = Utilerias.truncateDecimal(ultimoSalarioIntegrado.getSalarioDiarioFijo(), 2);
                        double sdvTrunc = Utilerias.truncateDecimal(ultimoSalarioIntegrado.getSalarioDiarioVariable(), 2);
                        double sdifTrunc = Utilerias.truncateDecimal(sdif, 2);
                        double sdivTrunc = Utilerias.truncateDecimal(sdiv, 2);
                        if (sdiTrunc == (sdifTrunc + sdivTrunc) & sdfTrunc == sdifTrunc
                                & sdvTrunc == sdivTrunc) {
                            continuar = false;
                        }
                    }
                    if (continuar) {
                        if (ultimoSalarioIntegrado.getFecha() == null ? true : ultimoSalarioIntegrado.getFecha().compareTo(fechaActualCalculoSDI.getTime()) != 0) {
                            ultimoSalarioIntegrado = null;
                            continuar = true;
                            continuarElimacion = false;
                            if (salarioIntegradosDet != null) {
                                salarioIntegradosDet.clear();
                            }
                        } else if (ultimoSalarioIntegrado.getFecha().compareTo(fechaActualCalculoSDI.getTime()) == 0 & (salarioIntegradosDet == null ? true : salarioIntegradosDet.isEmpty())) {
                            salarioIntegradosDet = buscarSalarioIntegradosDetalle(ultimoSalarioIntegrado);
                        }
                    }
                    if (continuar) {
                        if (continuarElimacion) {
                            if (filtroMovimientosNominas != null || filtroConceptosNomina != null) {
                                if ((filtroMovimientosNominas == null ? false : filtroMovimientosNominas.size() > 0) || (filtroConceptosNomina == null ? false : filtroConceptosNomina.size() > 0)) {
                                    for (int k = 0; k < salarioIntegradosDet.size(); k++) {
                                        getSession().delete(salarioIntegradosDet.get(k));
                                    }
//                                    System.out.println("flush 16");
                                    getSession().flush();
                                    getSession().clear();
                                    salarioIntegradosDet.clear();
                                }
                            } else {
                                salarioIntegradosDet.clear();
                            }
                        }

                        List<ConcepNomDefi> conceptosVarFijos = new ArrayList<>();
                        if (filtroMovimientosNominas == null ? false : !filtroMovimientosNominas.isEmpty()) {
                            Double max = 0.0;
                            String conceptoActual = filtroMovimientosNominas.get(0).getConcepNomDefi().getClave();
                            j = 0;
                            ConcepNomDefi concepNomDefi;
                            while (j <= filtroMovimientosNominas.size()) {
                                if (j == filtroMovimientosNominas.size()) {
                                    concepNomDefi = filtroMovimientosNominas.get(j - 1).getConcepNomDefi();
                                    concepNomDefi.setResultado(max);
                                    conceptosVarFijos.add(concepNomDefi);
                                    max = 0.0;
                                    break;
                                }
                                if (conceptoActual.equals(filtroMovimientosNominas.get(j).getConcepNomDefi().getClave())) {
                                    max += filtroMovimientosNominas.get(j).getResultado();
                                } else {
                                    conceptoActual = filtroMovimientosNominas.get(j).getConcepNomDefi().getClave();
                                    concepNomDefi = filtroMovimientosNominas.get(j - 1).getConcepNomDefi();
                                    concepNomDefi.setResultado(max);

                                    conceptosVarFijos.add(concepNomDefi);
                                    //
                                    max = filtroMovimientosNominas.get(j).getResultado();
                                }
                                j++;
                            }
                        }

                        //Reestructuracion de movimientos partidos
                        /////filtroMovimientosNominas = sumaJuntaMovPartidos(filtroMovimientosNominas);//JEVC01
                        for (j = 0; j < conceptosVarFijos.size(); j++) {
                            salarioIntegradosDet.add(contruyeSalarioIntegradoDetallado(plazasPorEmpleadosMov.get(i), conceptosVarFijos.get(j), null, conceptosVarFijos.get(j).getResultado(), fechaActualCalculoSDI));
//                                salarioIntegradosDet.add(contruyeSalarioIntegradoDetallado(plazasPorEmpleadosMov.get(i), filtroMovimientosNominas.get(j).getConcepNomDefi(), null, filtroMovimientosNominas.get(j).getResultado() == null ? 0.0 : filtroMovimientosNominas.get(j).getResultado(), fechaActualCalculoSDI));
                        }

                        if (filtroConceptosNomina != null) {
                            for (j = 0; j < filtroConceptosNomina.size(); j++) {
                                salarioIntegradosDet.add(contruyeSalarioIntegradoDetallado(plazasPorEmpleadosMov.get(i), filtroConceptosNomina.get(j), null, filtroConceptosNomina.get(j).getResultado(), fechaActualCalculoSDI));
                            }
                        }
                        if (mensajeResultado.getNoError() != 0) {
                            break;
                        }
                    }
                    valoresConceptosEmpleados.clear();
                    if (continuar) {
                        ultimoSalarioIntegrado = contruyeSalarioIntegrado(plazasPorEmpleadosMov.get(i), factorIntegracion, fechaActualCalculoSDI, sdif, sdiv, ultimoSalarioIntegrado);
//                        if (ultimoSalarioIntegrado.getSalarioDiarioIntegrado() != salariosIntegrados.getSalarioDiarioIntegrado() | ultimoSalarioIntegrado.getSalarioDiarioFijo() != salariosIntegrados.getSalarioDiarioFijo()
//                                | ultimoSalarioIntegrado.getSalarioDiarioVariable() != salariosIntegrados.getSalarioDiarioVariable()) {
                        if (!soloCalculo) {
                            //Guarda salario diario Integrado
                            cantidadSaveUpdate++;
                            getSession().saveOrUpdate(ultimoSalarioIntegrado);

                            //guarda conceptos nomina utilizados calculo SDI
                            for (j = 0; j < salarioIntegradosDet.size(); j++) {
                                salarioIntegradosDet.get(j).setSalarioIntegrado(ultimoSalarioIntegrado);
                                cantidadSaveUpdate++;
                                getSession().saveOrUpdate(salarioIntegradosDet.get(j));
                                if (salarioIntegradosDet.size() >= 50) {
//                                    System.out.println("flush 17");
                                    getSession().flush();
//                                    getSession().clear();
                                }
                            }
                        }
                        //}
                        mensajeResultado.setResultado(ultimoSalarioIntegrado);
//                        }
                    } else {
                        mensajeResultado.setResultado(ultimoSalarioIntegrado);
                    }
                }
                //(Double) valoresConceptosEmpleados.get("FactorIntegracion".toUpperCase()) * (Float) valoresConceptosEmpleados.get("SUELDODIARIO");
                if (!soloCalculo) {
                    getSession().getTransaction().commit();
                } else {
                    getSession().getTransaction().rollback();
                }
            } catch (HibernateException ex) {
                // mensajeRespuesta.setNoError(55);
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("calculaSalarioDiarioIntegerado()1_Error: ").append(ex));
                try {
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                    mensajeResultado.setError(ex.getLocalizedMessage());
                } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                }
                mensajeResultado.setResultado(null);
            }
            periodosNomina = null;
            zonaGeografica = null;
            salarioMinimoDF = null;
            filtroConceptosNomina = null;
            filtroMovimientosNominas = null;
            valoresConceptosEmpleados = null;
        }
        if (plazasPorEmpleadosMov.isEmpty()) {
            mensajeResultado.setNoError(999);
            claveEmpIni = claveEmpIni == null ? "" : claveEmpIni;
            claveEmpFin = claveEmpFin == null ? "" : claveEmpFin;
            if (claveEmpIni.length() > 0 & claveEmpFin.length() > 0) {
                mensajeResultado.setError("No encontro empleados");
            } else if (claveEmpIni.length() > 0 | claveEmpFin.length() > 0) {
                mensajeResultado.setError("No encontro el empleado");
            } else {
                mensajeResultado.setError("No encontro empleados");
            }
        }
        isCalculoSDI = false;
        return mensajeResultado;
    }

    //Sumatoria de movimientos partidos
    private List<MovNomConcep> sumaJuntaMovPartidos(List<MovNomConcep> listMovimientos) {//JEVC01
        double suma;
        if (listMovimientos != null) {
            for (int i = 0; i < listMovimientos.size(); i++) {
                if (listMovimientos.get(i).getNumMovParticion() == 2) {
                    MovNomConcep movPrin = listMovimientos.get(i - 1);
                    MovNomConcep movSec = listMovimientos.get(i);
                    if (movPrin != null) {
                        suma = Double.valueOf(movPrin.getResultado()) + Double.valueOf(movSec.getResultado());
                        movPrin.setResultado(suma);
                        listMovimientos.remove(i);//Elimina el movimiento partido
                        i--;
                    }
                }
            }
        }

        return listMovimientos;
    }

    private SalariosIntegrados buscarSalarioIntegradoActual(Calendar fechaCalculoSDI, Empleados empleados) {
        SalariosIntegrados salariosIntegrados = null;
        try {
            salariosIntegrados = (SalariosIntegrados) ejecutaQueryUnico("FROM SalariosIntegrados si where si.fecha <= :fecha  and si.empleados.id = :idEmpleado order by si.fecha desc", new String[]{"fecha", "idEmpleado"}, new Object[]{fechaCalculoSDI.getTime(), empleados.getId()});
        } catch (HibernateException ex) {
            //mensajeRespuesta.setNoError(-20); //pendiente
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarSalarioIntegradoActual()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        if (salariosIntegrados == null) { // solo usado para la comparacion con el sdi que se esta calculando
            salariosIntegrados = new SalariosIntegrados();
            salariosIntegrados.setSalarioDiarioIntegrado(-1);
        }
        return salariosIntegrados;
    }

    private List<SalariosIntegradosDet> buscarSalarioIntegradosDetalle(SalariosIntegrados salariosIntegrados) {
        List<SalariosIntegradosDet> salariosIntegradosDets = null;
        try {
            salariosIntegradosDets = (List<SalariosIntegradosDet>) ejecutaQueryList("FROM SalariosIntegradosDet sid where sid.salarioIntegrado = :salarioIntegrado ", new String[]{"salarioIntegrado"}, new Object[]{salariosIntegrados}, 0);
        } catch (HibernateException ex) {
            //mensajeRespuesta.setNoError(-20); //pendiente
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarSalarioIntegradosDetalle()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        return salariosIntegradosDets;
    }

    private SalariosIntegrados contruyeSalarioIntegrado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, double factorIntegracion, Calendar fechaCalculoSDI, double salarioDiarioIntFijo, double salarioDiarioIntVariable, SalariosIntegrados nuevoSalariosIntegrados) {
        SalariosIntegrados salariosIntegrados;
        if (nuevoSalariosIntegrados == null) {
            salariosIntegrados = new SalariosIntegrados();
        } else {
            salariosIntegrados = nuevoSalariosIntegrados;
        }
        double sdi;
        salariosIntegrados.setEmpleados(plazasPorEmpleadosMov.getPlazasPorEmpleado().getEmpleados());
        salariosIntegrados.setFecha(fechaCalculoSDI.getTime());
        salariosIntegrados.setFactorIntegracion(factorIntegracion);
        salariosIntegrados.setRegistroPatronal(plazasPorEmpleadosMov.getPlazasPorEmpleado().getRegistroPatronal());
        salariosIntegrados.setSalarioDiarioFijo(aplicarMascara(null, salarioDiarioIntFijo, true));
        salariosIntegrados.setSalarioDiarioVariable(aplicarMascara(null, salarioDiarioIntVariable, true));
        //tipo de Salario
        if (salariosIntegrados.getSalarioDiarioFijo() > 0 & salariosIntegrados.getSalarioDiarioVariable() > 0) {
            salariosIntegrados.setTipoDeSalario((Integer) ClavesParametrosModulos.tipoSalarioMixto);
        } else if (salariosIntegrados.getSalarioDiarioVariable() > 0) {
            salariosIntegrados.setTipoDeSalario((Integer) ClavesParametrosModulos.tipoSalarioVariable);
        } else {
            salariosIntegrados.setTipoDeSalario((Integer) ClavesParametrosModulos.tipoSalarioFijo);
        }
        sdi = salariosIntegrados.getSalarioDiarioFijo() + salariosIntegrados.getSalarioDiarioVariable();

        if (sdi > (salarioMinimoDF * 25)) {  //Tope Salario Diario Integrado
            salariosIntegrados.setSalarioDiarioIntegrado(aplicarMascara(null, salarioMinimoDF * 25, true));
        } else {
            salariosIntegrados.setSalarioDiarioIntegrado(sdi);
        }
        return salariosIntegrados;
    }

    private SalariosIntegradosDet contruyeSalarioIntegradoDetallado(PlazasPorEmpleadosMov plazasPorEmpleadosMov, ConcepNomDefi concepNomDefi, SalariosIntegrados sdi, double importe, Calendar fechaCalculoSDI) {
        SalariosIntegradosDet salariosIntegradosDet = new SalariosIntegradosDet();
        salariosIntegradosDet.setSalarioIntegrado(sdi);
        salariosIntegradosDet.setConcepNomDefi(concepNomDefi);
//////        boolean cambioValor = false;
//        if (cambioValor) {
        salariosIntegradosDet.setCambio(SalariosIntegradosDet.Cambio.CAMBIO);
        salariosIntegradosDet.setFechaCambio(fechaCalculoSDI.getTime());//pendiente
//        } else {
//            salariosIntegradosDet.setCambio(SalariosIntegradosDet.Cambio.NO_CAMBIO);
//        }
        salariosIntegradosDet.setPlazasPorEmpleado(plazasPorEmpleadosMov.getPlazasPorEmpleado());
        salariosIntegradosDet.setImporte(importe);
        if (concepNomDefi.getParametroConceptosDeNominas() != null) {
            if (concepNomDefi.getParametroConceptosDeNominas().size() > 0) {
                if (concepNomDefi.getParametroConceptosDeNominas().get(0).getUnidad().equalsIgnoreCase("HORAS")) {
                    salariosIntegradosDet.setHoras(concepNomDefi.getParametroConceptosDeNominas().get(0).getNumero());
                }
            }
        }
        return salariosIntegradosDet;
    }

    private PeriodosNomina buscaPeriodoNominaActual(Calendar fechaActualCalculo, String claveEmpleado) {
        PeriodosNomina periodo = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT p FROM PeriodosNomina p INNER JOIN p.tipoNomina t ");
            strQuery.append("WHERE (:fechaActual BETWEEN p.fechaInicial AND  p.fechaFinal + 1) AND t.clave =  AND p.tipoCorrida.clave = ");
            strQuery.append("(SELECT tn.clave FROM  PlazasPorEmpleadosMov pem INNER JOIN pem.tipoNomina tn INNER JOIN pem.plazasPorEmpleado pe INNER JOIN pe.empleados em ");
            strQuery.append("WHERE em.clave = :claveEmpleado) ");
            camposParametro.add("fechaActual");
            camposParametro.add("claveEmpleado");
            valoresParametro.add(fechaActualCalculo);
            valoresParametro.add(claveEmpleado);
            periodo = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(56);
            }
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaPeriodoNominaActual()1_Error: ").append(ex));
            mensajeResultado.setNoError(56);
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return periodo;
    }

    private PeriodosNomina buscaPeriodoNominaActual(String claveTipoNomina, String claveTipoCorrida, Long idPeriodo, Calendar fechaActual) {
        PeriodosNomina periodo = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT p FROM PeriodosNomina p INNER JOIN p.tipoNomina t INNER JOIN p.tipoCorrida c ");
            if (idPeriodo == null) {
                strQuery.append("WHERE (:fechaActual BETWEEN p.fechaInicial AND  p.fechaFinal + 1) AND t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida ");
                camposParametro.add("fechaActual");
                valoresParametro.add(fechaActual.getTime());
                camposParametro.add("claveTipoNomina");
                valoresParametro.add(claveTipoNomina);
                camposParametro.add("claveTipoCorrida");
                valoresParametro.add(claveTipoCorrida == null ? "PER" : claveTipoCorrida.trim().length() == 0 ? "PER" : claveTipoCorrida);
            } else {
                strQuery.append("WHERE p.id = :idPeriodo ");
                camposParametro.add("idPeriodo");
                valoresParametro.add(idPeriodo);
            }
            periodo = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(56);
            }
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaPeriodoNominaActual()1_Error: ").append(ex));
            mensajeResultado.setNoError(56);
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        camposParametro = null;
        valoresParametro = null;
        //strQuery = null;
        return periodo;
    }

    private List<TablaBase> buscaTablasBaseSistema() {
        List<TablaBase> tablaBases = null;
        try {
            strQuery.delete(0, strQuery.length()).append("SELECT new TablaBase(tb.clave, tt.nombre, tb.controladores) FROM TablaBase tb INNER JOIN tb.tipoTabla tt WHERE tt.sistema = true");
            Query query = getSession().createQuery(strQuery.toString());
            tablaBases = query.list();
            tablaBases = tablaBases == null ? new ArrayList<TablaBase>() : tablaBases;
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscaTablasBaseSistema()1_Error: ").append(ex));
            mensajeResultado.setNoError(-10);
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        if (tablaBases.isEmpty()) {
            mensajeResultado.setError("no encontro tablas base sistema");
            mensajeResultado.setNoError(-10);
        }
        return tablaBases;
    }

    private void cargaTablaFactorIntegracion(String controlador, List<Object> clavesElementosAplicacion, boolean factor, boolean zonaSalarial, boolean parametro, Date fechaFinal, Integer ejercicio, String uuidCxnMaestra) {
        if (factor | zonaSalarial | parametro) {
            try {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
                getSession().beginTransaction();
                List<TablaBase> tablasBaseSistema = buscaTablasBaseSistema();
                if (mensajeResultado.getNoError() != 0) {
                    return;
                }
                List<TipoControlador> tipoControladores;
                if (factor) {
                    tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaFactorIntegracion.toString(), tablasBaseSistema);
                    if (mensajeResultado.getNoError() != 0) {
                        mensajeResultado.setError("no encontro controladores en la tabla tabla factor de integracion");
                        return;
                    }
                    tablaFactorIntegracion = construyeTablaXml(ClavesParametrosModulos.claveTipoTablaFactorIntegracion.toString(), controlador, tipoControladores, fechaFinal, ejercicio);
                    if (mensajeResultado.getNoError() == -10) {
                        mensajeResultado.setNoError(24);
                    }
                    if (tablaFactorIntegracion == null & mensajeResultado.getNoError() == 0) {
                        mensajeResultado.setError("no encontro datos de la tabla factor de integracion");
                        mensajeResultado.setNoError(1000);
                        mensajeResultado.setResultado(0);
                    }
                    if (mensajeResultado.getNoError() != 0) {
                        return;
                    }
                }
                if (zonaSalarial) {
                    tipoControladores = obtieneTipoContoladorTablaBase(ClavesParametrosModulos.claveTipoTablaZonaZalarial.toString(), tablasBaseSistema);
                    if (mensajeResultado.getNoError() != 0) {
                        mensajeResultado.setError("no encontro controladores en la tabla zona salarial");
                        return;
                    }
                    tablaZonaSalarial = construyeTablaXml(ClavesParametrosModulos.claveTipoTablaZonaZalarial.toString(), "", tipoControladores, fechaFinal, ejercicio);
                    if (tablaZonaSalarial == null & mensajeResultado.getNoError() == 0) {
                        mensajeResultado.setError("no encontro datos de la tabla zona salarial");
                        mensajeResultado.setNoError(1000);
                        mensajeResultado.setResultado(0);
                    }
                    if (mensajeResultado.getNoError() != 0) {
                        return;
                    }
                }
                if (parametro & clavesElementosAplicacion != null) {
                    obtenerDatoParametros(clavesElementosAplicacion);
                }
                getSession().getTransaction().commit();
            } catch (HibernateException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargaTablaFactorIntegracion()1_Error: ").append(ex));
                mensajeResultado.setNoError(24);
                try {
                    if (getSession().getTransaction().isActive()) {
                        getSession().getTransaction().rollback();
                    }
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                    mensajeResultado.setError(ex.getLocalizedMessage());
                } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                    mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                    mensajeResultado.setError(exc.getLocalizedMessage());
                }
                mensajeResultado.setResultado(null);
            } finally {
                setSession(null);
            }
        }
    }

    private void obtenerDatoParametros(List<Object> clavesElementosAplicacion) {
        String valor;
        //factor mensual
        clavesElementosAplicacion = clavesElementosAplicacion == null ? new ArrayList<Object>() : clavesElementosAplicacion;
        if (clavesElementosAplicacion.size() > 0) {
            strQuery.delete(0, strQuery.length()).append("SELECT cr.valor ");
            strQuery.append(" FROM Cruce cr ");
            strQuery.append(" INNER JOIN cr.parametros pr ");
            strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
            strQuery.append(" WHERE pr.clave = :parametro ");
            strQuery.append(" and ea.clave = :elementoAplicacion ");
            strQuery.append(" and cr.claveElemento = :claveElemento");                                    //razon social
            valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroFactorAplicaciónTablaMensual, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, clavesElementosAplicacion.get(0)});
            if (mensajeResultado.getNoError() != 0) {
                mensajeResultado.setNoError(5);
                return;
            }
            valor = (valor == null ? "" : valor);
            if (valor.isEmpty()) {
                strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                strQuery.append(" FROM Parametros pr ");
                strQuery.append(" INNER JOIN pr.modulo m ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" AND m.clave =:modulo");
                valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroFactorAplicaciónTablaMensual, ClavesParametrosModulos.claveModuloGlobal});
                if (mensajeResultado.getNoError() != 0) {
                    mensajeResultado.setNoError(6);
                    return;
                }
            }
            factorMensual = Double.valueOf(valor);

            //factor anual
            strQuery.delete(0, strQuery.length()).append("SELECT cr.valor ");
            strQuery.append(" FROM Cruce cr ");
            strQuery.append(" INNER JOIN cr.parametros pr ");
            strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
            strQuery.append(" WHERE pr.clave = :parametro ");
            strQuery.append(" and ea.clave = :elementoAplicacion ");
            strQuery.append(" and cr.claveElemento = :claveElemento");
            valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroFactorAplicaciónTablaAnual, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, clavesElementosAplicacion.get(0)});
            if (mensajeResultado.getNoError() != 0) {
                mensajeResultado.setNoError(1);
                return;
            }
            valor = (valor == null ? "" : valor);
            if (valor.isEmpty()) {
                strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                strQuery.append(" FROM Parametros pr ");
                strQuery.append(" INNER JOIN pr.modulo m ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" AND m.clave =:modulo");
                valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroFactorAplicaciónTablaAnual, ClavesParametrosModulos.claveModuloGlobal});
                if (mensajeResultado.getNoError() != 0) {
                    mensajeResultado.setNoError(2);
                    return;
                }
            }
            factorAnual = Double.valueOf(valor);
        }

        //<editor-fold defaultstate="collapsed" desc="Manejo de Salario Diario">
        if (manejoSalarioDiario == null) {
            strQuery.delete(0, strQuery.length()).append("SELECT cr.valor ");
            strQuery.append(" FROM Cruce cr ");
            strQuery.append(" INNER JOIN cr.parametros pr ");
            strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
            strQuery.append(" WHERE pr.clave = :parametro ");
            strQuery.append(" and ea.clave = :elementoAplicacion ");
            strQuery.append(" and cr.claveElemento = :claveElemento");
            valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, clavesElementosAplicacion.get(0)});
            if (mensajeResultado.getNoError() != 0) {
                mensajeResultado.setNoError(1);
                return;
            }
            valor = (valor == null ? "" : valor);
            if (valor.isEmpty()) {
                strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                strQuery.append(" FROM Parametros pr");
                strQuery.append(" INNER JOIN pr.modulo m ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" AND m.clave =:modulo");
                valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor, ClavesParametrosModulos.claveModuloGlobal});
                if (mensajeResultado.getNoError() != 0) {
                    mensajeResultado.setNoError(2);
                    return;
                }
            }
            if (valor.equals(ClavesParametrosModulos.opcionParametroSalarioDiario)) {
                manejoSalarioDiario = ManejoSalarioDiario.DIARIO;
            } else if (valor.equals(ClavesParametrosModulos.opcionParametroSalarioSemanal)) {
                manejoSalarioDiario = ManejoSalarioDiario.SEMANAL;
            } else if (valor.equals(ClavesParametrosModulos.opcionParametroSalarioQuincenal)) {
                manejoSalarioDiario = ManejoSalarioDiario.QUINCENAL;
            } else {
                manejoSalarioDiario = ManejoSalarioDiario.MENSUAL;
            }
        }

        //</editor-fold>
    }

    private Double salarioDiarioFijo(String claveTipoCorrida, String claveTipoNomina, Long idPeriodoNomina, PlazasPorEmpleado plazaPorEmpleado,
            String claveCtrCosto, String claveRazonSocial) {
        Double salarioFijoTotal = 0.0;
        try {
            //sin tipo de concepto que afectan imss
            filtroMovimientosNominas = buscaMovimientosPlazasPorTipoYBaseAfecta(claveTipoCorrida, claveTipoNomina, idPeriodoNomina, plazaPorEmpleado, claveCtrCosto, null, claveRazonSocial, ClavesParametrosModulos.claveBaseNominaIMSS.toString(), (Integer) ClavesParametrosModulos.tipoBaseAfectaIMSSFijo, null);
            //tipoPeriodo que afectan imss  
            //filtroMovimientosNominas.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(claveTipoCorrida, claveTipoNomina, clavePeriodoNomina, plazaPorEmpleado, claveCtrCosto, Tipo.PERIODO, claveRazonSocial, ClavesParametrosModulos.claveBaseNominaIMSS.toString(), (Integer) ClavesParametrosModulos.tipoBaseAfectaIMSSFijo));
            //tipo repetitivo que afectan imss    
            //filtroMovimientosNominas.addAll(buscaMovimientosPlazasPorTipoYBaseAfecta(claveTipoCorrida, claveTipoNomina, clavePeriodoNomina, plazaPorEmpleado, claveCtrCosto, Tipo.REPETITIVO, claveRazonSocial, ClavesParametrosModulos.claveBaseNominaIMSS.toString(), (Integer) ClavesParametrosModulos.tipoBaseAfectaIMSSFijo));
            if (mensajeResultado.getNoError() != 0) {
                return 0.0;
            }
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("Select c FROM ConcepNomDefi c INNER JOIN c.baseAfecConcepNomi ba INNER JOIN ba.baseNomina bn ");
            strQuery.append("WHERE bn.clave = :claveBaseNomina AND ba.tipoAfecta = :tipoAfecta AND c.fecha = (SELECT MAX(cdn.fecha) FROM ConcepNomDefi cdn WHERE cdn.clave= c.clave) AND c.tipo = :tipo "); // AND c.clave != :concepSalarioDiario
            camposParametro.add("claveBaseNomina");
            camposParametro.add("tipoAfecta");
            camposParametro.add("tipo");
            //camposParametro.add("concepSalarioDiario");
            valoresParametro.add(ClavesParametrosModulos.claveBaseNominaIMSS); //base nomina IMSS
            valoresParametro.add(ClavesParametrosModulos.tipoBaseAfectaIMSSFijo); //tipo Afecta de base nomina FIJOS
            valoresParametro.add(Tipo.AUTOMATICO);
            //valoresParametro.add(ClavesParametrosModulos.claveConceptoSueldoDiario);
            if (filtroMovimientosNominas.size() > 0) {
                strQuery.append(" AND c.clave NOT IN(:clavesConceptos) ");
                camposParametro.add("clavesConceptos");
                Object clavesConceptos[] = new Object[filtroMovimientosNominas.size()];
                filtroConceptosNomina = new ArrayList<ConcepNomDefi>();
                int i;
                for (i = 0; i < filtroMovimientosNominas.size(); i++) {
                    if (filtroMovimientosNominas.get(i).getResultado() == null) {
                        //almacena conceptos que no han sido calculados para calcularlos posteriormente 
                        filtroConceptosNomina.add(filtroMovimientosNominas.get(i).getConcepNomDefi());
                    } else {
                        salarioFijoTotal = salarioFijoTotal + filtroMovimientosNominas.get(i).getResultado();
                    }
                    clavesConceptos[i] = filtroMovimientosNominas.get(i).getConcepNomDefi().getClave();
                }
                //conceptos imss fijos automaticos de movimientos que no se han calculado 
                salarioFijoTotal = salarioFijoTotal + ejecutaFormulaConceptosNomina(filtroConceptosNomina);
                if (mensajeResultado.getNoError() != 0) {
                    return 0.0;
                }
                valoresParametro.add(clavesConceptos);
            }
            filtroConceptosNomina = (List<ConcepNomDefi>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(60);
                return 0.0;
            }
            salarioFijoTotal = salarioFijoTotal + ejecutaFormulaConceptosNomina(filtroConceptosNomina);
            if (mensajeResultado.getNoError() != 0) {
                return 0.0;
            }
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("salarioDiarioFijo()1_Error: ").append(ex));
            mensajeResultado.setNoError(57);
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        camposParametro = null;
        valoresParametro = null;
        //strQuery = null;
        return salarioFijoTotal;
    }

    private Double ejecutaFormulaConceptosNomina(List<ConcepNomDefi> concepNomDefis) {
        Double fijo, salarioFijoTotal = 0.0, valorExento = 0.0;
        try {
            boolean condicion;
            int i;
            for (i = 0; i < concepNomDefis.size(); i++) {
                agregaParametrosConceptosNominaFormula(concepNomDefis.get(i).getParametroConceptosDeNominas());
                if (concepNomDefis.get(i).getCondicionConcepto().length() == 0) {
                    condicion = true;
                } else {
                    condicion = ejecutaFormula(concepNomDefis.get(i).getCondicionConcepto()) == 0.0 ? false : true;
                }
                if (condicion) {
                    fijo = ejecutaFormula(concepNomDefis.get(i).getFormulaConcepto());
                    for (BaseAfecConcepNom afecConcepNom : concepNomDefis.get(i).getBaseAfecConcepNomi()) {
                        if (afecConcepNom.getBaseNomina().getId() == 2 & afecConcepNom.getTipoAfecta() == 0) { // 2 -IMSS  y 0 = Fijo
                            valorExento = afecConcepNom.getFormulaExenta().isEmpty() ? 0.0 : ejecutaFormula(afecConcepNom.getFormulaExenta());
                        }
                    }
                    concepNomDefis.get(i).setResultado(fijo - valorExento);
                    salarioFijoTotal = salarioFijoTotal + (fijo - valorExento);
                } else {
                    concepNomDefis.get(i).setResultado(0.0);
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("ejecutaFormulaConceptosNomina()1_Error: ").append(ex));
            mensajeResultado.setNoError(59);
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return salarioFijoTotal;
    }

    private void agregaParametrosConceptosNominaFormula(List<ParaConcepDeNom> paraConcepDeNoms) {
        if (paraConcepDeNoms != null) {
            int j;
            for (j = 0; j < paraConcepDeNoms.size(); j++) {
                DatosConceptosNomina.addVariable(paraConcepDeNoms.get(j).getDescripcion().toUpperCase());
                compEjec.addVariableExtraNro(paraConcepDeNoms.get(j).getDescripcion().toUpperCase());
//                valoresConceptosEmpleados.put(paraConcepDeNoms.get(j).getDescripcion().toUpperCase(),paraConcepDeNoms.get(j).getValor());
            }
        }
    }

    private Double salarioDiarioVariableBim(Calendar fechaPeriodo) {
        Double acumuladoVariableBim = 0.0;
        try {
            Calendar fechaBimestre = Calendar.getInstance();
            fechaBimestre.setTime(fechaPeriodo.getTime());
            //+1 es por que enero inicia en 0
            if ((fechaBimestre.get(Calendar.MONTH) + 1) % 2 == 0) {
                fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) - 2);
            } else {
                fechaBimestre.set(Calendar.MONTH, fechaBimestre.get(Calendar.MONTH) - 1);
            }
            List<Integer> meses = new ArrayList<Integer>();
            if (fechaBimestre.get(Calendar.MONTH) == Calendar.JANUARY || fechaBimestre.get(Calendar.MONTH) == Calendar.FEBRUARY) {
                meses.add(1);
                meses.add(2);
            } else if (fechaBimestre.get(Calendar.MONTH) == Calendar.MARCH || fechaBimestre.get(Calendar.MONTH) == Calendar.APRIL) {
                meses.add(3);
                meses.add(4);
            } else if (fechaBimestre.get(Calendar.MONTH) == Calendar.MAY || fechaBimestre.get(Calendar.MONTH) == Calendar.JUNE) {
                meses.add(5);
                meses.add(6);
            } else if (fechaBimestre.get(Calendar.MONTH) == Calendar.JULY || fechaBimestre.get(Calendar.MONTH) == Calendar.AUGUST) {
                meses.add(7);
                meses.add(8);
            } else if (fechaBimestre.get(Calendar.MONTH) == Calendar.SEPTEMBER || fechaBimestre.get(Calendar.MONTH) == Calendar.OCTOBER) {
                meses.add(9);
                meses.add(10);
            } else if (fechaBimestre.get(Calendar.MONTH) == Calendar.NOVEMBER || fechaBimestre.get(Calendar.MONTH) == Calendar.DECEMBER) {
                meses.add(11);
                meses.add(12);
            }
            //Bimestral       
            List<PeriodosNomina> periodos = buscarPeriodosPorRangoMeses(-1, fechaBimestre);
            if (mensajeResultado.getNoError() != 0 || periodos.isEmpty()) {
                return 0.0;
            }
            Integer diasIMSS;//diasIMSSBimestre(clavesPeriodos, fechaPeriodo);

            Calendar cInicioImss = Calendar.getInstance(), cInicioPeriodo = Calendar.getInstance();
            cInicioImss.setTime((Date) valoresConceptosEmpleados.get("FechaAltaIMSS".toUpperCase()));
            cInicioPeriodo.setTime(fechaPeriodo.getTime());

            int diasDif = 0;
            if (cInicioImss.after(cInicioPeriodo)) {
                diasDif = cInicioImss.get(Calendar.DATE) - cInicioPeriodo.get(Calendar.DATE);
            }

            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT m ");
            strWhere.delete(0, strWhere.length()).append("FROM MovNomConcep m INNER JOIN m.periodosNomina p INNER JOIN m.tipoNomina tn INNER JOIN m.empleado em INNER JOIN m.tipoCorrida tc INNER JOIN m.concepNomDefi c INNER JOIN m.movNomBaseAfecta mba INNER JOIN mba.baseAfecConcepNom ba INNER JOIN ba.baseNomina bn INNER JOIN m.razonesSociales rs INNER JOIN m.plazasPorEmpleado pemp ");
            strWhere.append("WHERE m.uso = 0 AND m.mes IN (:meses) AND rs.clave = :claveRazonSocial AND pemp.clave = :clavePlazaEmpleado AND tn.clave = :claveTipoNomina AND em.clave = :claveEmp AND tc.clave = :claveTipoCorrida AND bn.clave = :claveBaseNomina AND ba.tipoAfecta = :tipoAfecta AND p.tipoCorrida.clave = :claveTipoCorrida ");

            if (!periodos.isEmpty()) {
                int i;
                strWhere.append("AND (");
                for (i = 0; i < periodos.size(); i++) {
                    strWhere.append("(p.clave = :clavePeriodo").append(i).append(" AND  p.año = :yearPeriodo").append(i).append(")");
                    camposParametro.add("clavePeriodo".concat(String.valueOf(i)));
                    camposParametro.add("yearPeriodo".concat(String.valueOf(i)));
                    valoresParametro.add(periodos.get(i).getClave());
                    valoresParametro.add(periodos.get(i).getAño());
                    if (i < periodos.size() - 1) {
                        strWhere.append(" OR ");
                    }
                }
                strWhere.append(") ");
            }
            camposParametro.add("claveEmp");
            camposParametro.add("claveTipoCorrida");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("claveBaseNomina");
            camposParametro.add("tipoAfecta");
            camposParametro.add("claveRazonSocial");
            camposParametro.add("clavePlazaEmpleado");
            camposParametro.add("meses");
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(ClavesParametrosModulos.claveBaseNominaIMSS); //base nomina IMSS
            valoresParametro.add(ClavesParametrosModulos.tipoBaseAfectaIMSSVariable); //tipo Afecta de base nomina VARIABLE
            valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("PlazaEmpleado".toUpperCase()));
            valoresParametro.add(meses);
            List<MovNomConcep> movNominaVariables = (List<MovNomConcep>) ejecutaQueryList(strQuery.toString().concat(strWhere.toString()), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            movNominaVariables = (movNominaVariables == null) ? new ArrayList<MovNomConcep>() : movNominaVariables;
            if (!movNominaVariables.isEmpty()) {
                if (filtroMovimientosNominas == null) {
                    filtroMovimientosNominas = movNominaVariables;
                } else {
                    filtroMovimientosNominas.addAll(movNominaVariables);
                }
                strQuery.delete(0, strQuery.length()).append("SELECT CASE WHEN (COUNT(m) > 0) THEN SUM(CASE WHEN (mba.resultado != NULL) THEN mba.resultado ELSE 0 END) ELSE 0 END * 1.0, CASE WHEN (COUNT(m) > 0) THEN  SUM(p.diasIMSS)  ELSE 0 END ");
                strQuery.append(strWhere);
                Object[] datosBimestrales = (Object[]) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
                if (mensajeResultado.getNoError() == -100) {
                    mensajeResultado.setNoError(64);
                    return 0.0;
                }
                int diasBim = datosBimestrales[1].getClass().equals(Long.class) ? ((Long) datosBimestrales[1]).intValue() : (Integer) datosBimestrales[1];
                diasBim = Utilerias.diasBimestre(fechaPeriodo.getTime());
                diasIMSS = diasBim - (Integer) (valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase()) == null ? "0" : valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase())) - (Integer) (valoresConceptosEmpleados.get("Ausentismo".toUpperCase()) == null ? "0" : valoresConceptosEmpleados.get("Ausentismo".toUpperCase())) - diasDif;

                if (diasIMSS == 0) {
                    acumuladoVariableBim = 0.0;
                } else {
                    acumuladoVariableBim = (Double) datosBimestrales[0] / diasIMSS;
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("salarioDiarioVariable()1_Error: ").append(ex));
            mensajeResultado.setNoError(65);
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        camposParametro = null;
        valoresParametro = null;
        return acumuladoVariableBim;
    }

    /**
     * Busca periodos que estan en determinado mes
     *
     * @param cantidadDeMeses especifica rango de meses a buscar positivo suma
     * mes y negativo resta mes
     * @param fechaPeriodoNomina fecha extra el mes del periodo de nomina a
     * buscar
     * @see #buscarPeriodosPorRangoMeses
     */
    private List<PeriodosNomina> buscarPeriodosPorRangoMeses(int rangoDeMes, Calendar fechaPeriodoNomina) {
        List<PeriodosNomina> periodos = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            Calendar fechaRango = Calendar.getInstance();
            fechaRango.setTime(fechaPeriodoNomina.getTime());
            fechaRango.set(Calendar.MONTH, fechaPeriodoNomina.get(Calendar.MONTH) + rangoDeMes);
            strQuery.delete(0, strQuery.length()).append("SELECT new PeriodosNomina(p.clave, p.año, p.diasPago, p.diasIMSS, p.AcumularAMes) FROM PeriodosNomina p INNER JOIN p.tipoNomina t INNER JOIN p.tipoCorrida c  WHERE ");
            int mesIni = -1, mesFin = -1;
            if (fechaPeriodoNomina.get(Calendar.YEAR) > fechaRango.get(Calendar.YEAR)) {
                strQuery.append("t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND (MONTH(p.AcumularAMes) <= :mesPeriodo) ");
                strQuery.append("OR (t.clave = :claveTipoNomina AND p.año = :yearPeriodo - 1 AND MONTH(p.AcumularAMes) = :mesEnero AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
                strQuery.append("OR (t.clave = :claveTipoNomina AND p.año = :yearPeriodo - 1 AND MONTH(p.AcumularAMes) >= :mesPeriodoRango)");
            } else if (fechaPeriodoNomina.get(Calendar.YEAR) < fechaRango.get(Calendar.YEAR)) {
                strQuery.append("t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND (MONTH(p.AcumularAMes) = :mesPeriodo) ");
                strQuery.append("OR (t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND MONTH(p.AcumularAMes) = :mesEnero AND YEAR(p.AcumularAMes) = :yearPeriodo + 1) ");
                strQuery.append("OR (t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida AND p.año = :yearPeriodo + 1 AND MONTH(p.AcumularAMes) <= :mesPeriodoRango AND YEAR(p.AcumularAMes) != :yearPeriodo + 2) ");
            } else {
                if (rangoDeMes < 0) {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                    mesIni = fechaRango.get(Calendar.MONTH) + 1;
                } else {
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                    mesFin = fechaRango.get(Calendar.MONTH) + 1;
                }

                if (fechaRango.get(Calendar.MONTH) == Calendar.JANUARY) {
                    strQuery.append("t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND (MONTH(p.AcumularAMes) <= :mesPeriodo AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
                    strQuery.append("OR (t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida AND p.año = :yearPeriodo - 1 AND MONTH(p.AcumularAMes) = :mesPeriodoRango AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                    mesFin = fechaRango.get(Calendar.MONTH) + 1;
                } else {
                    strQuery.append("t.clave = :claveTipoNomina AND c.clave = :claveTipoCorrida AND p.año = :yearPeriodo AND (MONTH(p.AcumularAMes) >= :mesPeriodo AND MONTH(p.AcumularAMes) <= :mesPeriodoRango)  ");
                }
//////                mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
//////                mesFin = fechaRango.get(Calendar.MONTH) + 1;
            }
            if (mesIni == -1 & mesFin == -1) {
                mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                mesFin = fechaRango.get(Calendar.MONTH) + 1;
                camposParametro.add("mesEnero");
                valoresParametro.add(Calendar.JANUARY);
            }
            camposParametro.add("mesPeriodo");
            camposParametro.add("mesPeriodoRango");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("claveTipoCorrida");
            camposParametro.add("yearPeriodo");
            valoresParametro.add(mesIni);
            valoresParametro.add(mesFin);
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
            valoresParametro.add(fechaPeriodoNomina.get(Calendar.YEAR));
            periodos = (List<PeriodosNomina>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(62);
            }
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarPeriodosPorRangoMeses()1_Error: ").append(ex));
            mensajeResultado.setNoError(62);
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        camposParametro = null;
        valoresParametro = null;
        //strQuery = null;
        return periodos == null ? new ArrayList<PeriodosNomina>() : periodos;
    }

    private List<Object> construyeConsultaDatosEspeciales(String campo, String valorDatoEspecial, String[] camposWhereExtras) {
        ConstruyeQueries cq = new ConstruyeQueries(propertieFuente, "");

        boolean isValorMovParametro = false;
        String pathCampoMostrar = propertieFuente.getProperty(campo);
        String[] camposMovParametrosValor = new String[]{};
        if (pathCampoMostrar.startsWith(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
            isValorMovParametro = true;
            camposMovParametrosValor = new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo"), MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro"),
                MovNomConcep.class.getSimpleName().concat(".concepNomDefi.clave")};
        }
        String[] datos;
        if (propertieFuente.containsKey(campo.concat("_Datos"))) {
            datos = propertieFuente.getProperty(campo.concat("_Datos")).split(",");
        } else {
            datos = new String[]{};
        }
        List<String> where = new ArrayList<String>();
        List<String> whereExtras = new ArrayList<String>();
        String campoDefault = MovNomConcep.class.getSimpleName().concat(".empleado.clave");
        if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Movimientos")) {
            campoDefault = MovNomConcep.class.getSimpleName().concat(".plazasPorEmpleado.clave");
        }
        TipoNodoConsulta tipoNodoConsulta = cq.getTipoNodoProperty(campo.concat("_TipoNodo"));
        int i, cont = 0, j;
        if (datos.length > 0) {
            for (i = 0; i < datos.length; i++) {
                if (tipoNodoConsulta == TipoNodoConsulta.DATOCALCULOSINPARAMETRO) {
                    String[] str = propertieFuente.getProperty(datos[i].concat("_Path")).split("\\|");
                    where.add(str[0]);
                    Naturaleza n;
                    try {
                        n = Naturaleza.valueOf(str[1].toUpperCase());
                    } catch (Exception es) {
//                        System.out.println("ERROR " + es.getMessage());
                        n = Naturaleza.getEnum(str[1].toUpperCase());
                    }
                    valorDatoEspecial = String.valueOf(n.getNaturaleza());
                } else {
                    where.add(propertieFuente.getProperty(datos[i].concat("_Path")));
                }
                cont++;
            }
        }

        whereExtras.add(MovNomConcep.class.getSimpleName().concat(".periodosNomina.fechaInicial"));
        whereExtras.add(MovNomConcep.class.getSimpleName().concat(".periodosNomina.fechaFinal"));
        Object[] claveEmpleados = (Object[]) valoresConceptosGlobales.get(Empleados.class.getSimpleName().toUpperCase());
        claveEmpleados = claveEmpleados == null ? new Object[]{} : claveEmpleados;
        if (claveEmpleados.length > 0) {
            whereExtras.add(campoDefault);
        }
        Object claveTiposNomina = (Object) valoresConceptosGlobales.get(TipoNomina.class.getSimpleName().toUpperCase());
        if (claveTiposNomina != null) {
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"));
        }
        Object claveRazonSocial = (Object) valoresConceptosGlobales.get(RazonesSociales.class.getSimpleName().toUpperCase());
        if (claveRazonSocial != null) {
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".razonesSociales.clave"));
        }
        Object claveTipoCorrida = (Object) valoresConceptosGlobales.get(TipoCorrida.class.getSimpleName().toUpperCase());
        if (claveTipoCorrida != null) {
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"));
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".periodosNomina.tipoCorrida.clave"));
        }
        Object claveUso = (Object) valoresConceptosGlobales.get("uso".toUpperCase());
        if (claveUso != null) {
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".uso"));
        }
        Object claveCentroCosto = valoresConceptosGlobales.get(CentroDeCosto.class.getSimpleName().toUpperCase());
        if (claveCentroCosto != null) {
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave"));
        }
        Object claveImprimeListado = valoresConceptosGlobales.containsKey("ImprimeListado".toUpperCase()) ? (Object) valoresConceptosGlobales.get("ImprimeListado".toUpperCase()) : null;
        if (claveImprimeListado != null) {
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.imprimirEnListadoNomina"));
        }
        Object claveImprimeRecibo = valoresConceptosGlobales.containsKey("ImprimeRecibo".toUpperCase()) ? (Object) valoresConceptosGlobales.get("ImprimeRecibo".toUpperCase()) : null;
        if (claveImprimeRecibo != null) {
            whereExtras.add(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.imprimirEnReciboNomina"));
        }
        if (usaFiniquitos) {
            if (camposFiniquitos != null) {
                whereExtras.add(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.id"));
            }
        }
        String tipoParametroConcepto = "";
        if (cont == 2 & tipoNodoConsulta == TipoNodoConsulta.DATOCALCULO) {
            tipoParametroConcepto = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo");
        }

        //pendiente where extras
        cq.generaListaTablasMapeadas(new String[]{pathCampoMostrar, campoDefault, tipoParametroConcepto}, where.toArray(new String[]{}), whereExtras.toArray(
                new String[]{}), null);

        StringBuilder sb = new StringBuilder("SELECT ");
        String tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        if (isValorMovParametro) {
            cq.mapeaTablasCampo(camposMovParametrosValor);
            String[] valoreMostrar = pathCampoMostrar.split("\\|");
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(".clave, CASE WHEN (");
            tabla = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom").replace(".", "_");
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(" IS NULL) THEN '' ELSE CASE WHEN (");
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(".tipo = 'INTEGER' AND ").append(cq.getAliasTablaOuter().get(tabla));
            tabla = valoreMostrar[0].substring(0, valoreMostrar[0].lastIndexOf(".")).replace(".", "_");
            sb.append(".clasificadorParametro = 0) THEN CAST(SUM(CAST(").append(cq.getAliasTablaOuter().get(tabla));
            sb.append(valoreMostrar[0].substring(valoreMostrar[0].lastIndexOf("."), valoreMostrar[0].length())).append(" as float)) as string) ELSE MAX(");
            sb.append(cq.getAliasTablaOuter().get(tabla));
            sb.append(valoreMostrar[0].substring(valoreMostrar[0].lastIndexOf("."), valoreMostrar[0].length())).append(") END END ");

        } else if (tipoParametroConcepto.length() > 0 & tipoNodoConsulta == TipoNodoConsulta.DATOCALCULO) {
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(".clave,");
            tabla = pathCampoMostrar.substring(0, pathCampoMostrar.lastIndexOf(".")).replace(".", "_");
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(pathCampoMostrar.substring(pathCampoMostrar.lastIndexOf("."), pathCampoMostrar.length())).append(",");
            tabla = tipoParametroConcepto.substring(0, tipoParametroConcepto.lastIndexOf(".")).replace(".", "_");
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(tipoParametroConcepto.substring(tipoParametroConcepto.lastIndexOf("."), tipoParametroConcepto.length())).append(" ");
        } else {
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(".clave, SUM(CASE WHEN (").append(cq.getAliasTablaOuter().get(MovNomConcep.class.getSimpleName())).append(" IS NULL) THEN 0.0 ELSE CASE WHEN (");
            tabla = pathCampoMostrar.substring(0, pathCampoMostrar.lastIndexOf(".")).replace(".", "_");
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(pathCampoMostrar.substring(pathCampoMostrar.lastIndexOf("."), pathCampoMostrar.length())).append(" IS NULL) THEN 0.0 ELSE ");
            sb.append(cq.getAliasTablaOuter().get(tabla)).append(pathCampoMostrar.substring(pathCampoMostrar.lastIndexOf("."), pathCampoMostrar.length())).append(" END END) ");
        }

        sb.append(cq.construyeFromConsulta(ConstruyeQueries.LEFTJOIN)).append(" ");
        if (where.size() > 0 | valorDatoEspecial.trim().length() > 0) {
            sb.append(cq.construyeWhereDatosSinParametros(where.toArray(new String[]{}), valorDatoEspecial.split("\\|"), null));
            sb.append(" AND ");
        } else {
            sb.append(" WHERE ");
        }
        tabla = MovNomConcep.class.getSimpleName().concat("_periodosNomina");

        sb.append(" ((").append(cq.getAliasTablaOuter().get(tabla)).append(".fechaInicial BETWEEN :fechaInicial AND :fechaFinal) ");
        sb.append("OR (").append(cq.getAliasTablaOuter().get(tabla)).append(".fechaFinal BETWEEN  :fechaInicial AND :fechaFinal)) ");
        cq.getParametrosQuery().put("fechaInicial", valoresConceptosGlobales.get(parametroFechaInicial));
        cq.getListParametros().add("fechaInicial");
        cq.getParametrosQuery().put("fechaFinal", valoresConceptosGlobales.get(parametroFechaFinal));
        cq.getListParametros().add("fechaFinal");
        if (claveEmpleados.length > 0) {
            tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
            sb.append(" AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".clave IN (:claveEmpleados) ");
            cq.getParametrosQuery().put("claveEmpleados", valoresConceptosGlobales.get(Empleados.class.getSimpleName().toUpperCase()));
            cq.getListParametros().add("claveEmpleados");
        }
        if (claveRazonSocial != null) {
            tabla = MovNomConcep.class.getSimpleName().concat("_razonesSociales");
            sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".clave = :claveRazonSocial ");
            cq.getParametrosQuery().put("claveRazonSocial", claveRazonSocial);
            cq.getListParametros().add("claveRazonSocial");
            ////tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }
        if (claveTiposNomina != null) {
            tabla = MovNomConcep.class.getSimpleName().concat("_tipoNomina");
            sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".clave = :claveTipoNomina ");
            cq.getParametrosQuery().put("claveTipoNomina", claveTiposNomina);
            cq.getListParametros().add("claveTipoNomina");
            ////tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }
        if (claveTipoCorrida != null) {
            tabla = MovNomConcep.class.getSimpleName().concat("_tipoCorrida");
            sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".clave = :claveTipoCorrida ");
            cq.getParametrosQuery().put("claveTipoCorrida", claveTipoCorrida);
            cq.getListParametros().add("claveTipoCorrida");
            ////tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }
        if (claveCentroCosto != null) {
            tabla = MovNomConcep.class.getSimpleName().concat("_centroDeCosto");
            sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".clave = :claveCentroCosto ");
            cq.getParametrosQuery().put("claveCentroCosto", claveCentroCosto);
            cq.getListParametros().add("claveCentroCosto");
            ////tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }
        if (claveUso != null) {
            tabla = MovNomConcep.class.getSimpleName();
            sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".uso = :claveUso ");
            cq.getParametrosQuery().put("claveUso", claveUso);
            cq.getListParametros().add("claveUso");
            ////tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }

        if (claveImprimeListado != null) {
            tabla = MovNomConcep.class.getSimpleName().concat("_concepNomDefi");
            sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".imprimirEnListadoNomina = :claveImprimeListado ");
            cq.getParametrosQuery().put("claveImprimeListado", claveUso);
            cq.getListParametros().add("claveImprimeListado");
            ////tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }

        if (claveImprimeRecibo != null) {
            tabla = MovNomConcep.class.getSimpleName().concat("_concepNomDefi");
            sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".imprimirEnReciboNomina = :claveImprimeRecibo ");
            cq.getParametrosQuery().put("claveImprimeRecibo", claveUso);
            cq.getListParametros().add("claveImprimeRecibo");
            ////tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }
        Calendar iniFecha = (Calendar) valoresConceptosGlobales.get(parametroFechaInicial);
        Calendar finFecha = (Calendar) valoresConceptosGlobales.get(parametroFechaFinal);
        int ini = iniFecha.get(Calendar.MONTH), fin = finFecha.get(Calendar.MONTH);
        List<Integer> meses = new ArrayList<Integer>();
        if (ini > fin) {
            meses.add(ini + 1);
            meses.add(fin + 1);
        } else {
            for (i = ini; i <= fin; i++) {
                meses.add(i + 1);
            }
        }
        tabla = MovNomConcep.class.getSimpleName();
        sb.append("AND ").append(cq.getAliasTablaOuter().get(tabla)).append(".mes IN(:mes) ");
        cq.getParametrosQuery().put("mes", meses);
        cq.getListParametros().add("mes");
        tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");

        if (usaFiniquitos) {
            if (camposFiniquitos != null & valoresCamposFiniquitos != null) {
                String campoFiniq;
                String aliasTabla;
                for (i = 0; i < camposFiniquitos.size(); i++) {
                    campoFiniq = getCampoFinal(camposFiniquitos.get(i));
                    aliasTabla = camposFiniquitos.get(i).substring(0, camposFiniquitos.get(i).lastIndexOf("."));
                    aliasTabla = aliasTabla.replace(".", "_");
                    aliasTabla = cq.getAliasTablaOuter().get(aliasTabla);
                    sb.append(" AND ").append(aliasTabla).append(".").append(campoFiniq).append(" = :parametro").append(i);
                    cq.getParametrosQuery().put("parametro".concat(String.valueOf(i)), valoresCamposFiniquitos.get(i));
                    cq.getListParametros().add("parametro".concat(String.valueOf(i)));
                }
                tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
            }
        }

        sb.append("GROUP BY ").append(cq.getAliasTablaOuter().get(tabla)).append(".clave ");
        if (isValorMovParametro) {
            pathCampoMostrar = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo");
            tabla = pathCampoMostrar.substring(0, pathCampoMostrar.lastIndexOf(".")).replace(".", "_");
            sb.append(",").append(cq.getAliasTablaOuter().get(tabla));
            sb.append(",").append(cq.getAliasTablaOuter().get(tabla)).append(pathCampoMostrar.substring(pathCampoMostrar.lastIndexOf("."), pathCampoMostrar.length()));
            pathCampoMostrar = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro");
            tabla = pathCampoMostrar.substring(0, pathCampoMostrar.lastIndexOf(".")).replace(".", "_");
            sb.append(",").append(cq.getAliasTablaOuter().get(tabla)).append(pathCampoMostrar.substring(pathCampoMostrar.lastIndexOf("."), pathCampoMostrar.length()));
            pathCampoMostrar = MovNomConcep.class.getSimpleName().concat(".concepNomDefi.descripcion");
            tabla = pathCampoMostrar.substring(0, pathCampoMostrar.lastIndexOf(".")).replace(".", "_");
            sb.append(",").append(cq.getAliasTablaOuter().get(tabla)).append(pathCampoMostrar.substring(pathCampoMostrar.lastIndexOf("."), pathCampoMostrar.length()));
            tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }
        if (tipoParametroConcepto.length() > 0 & tipoNodoConsulta == TipoNodoConsulta.DATOCALCULO) {
            tabla = pathCampoMostrar.substring(0, pathCampoMostrar.lastIndexOf(".")).replace(".", "_");
            sb.append(",").append(cq.getAliasTablaOuter().get(tabla)).append(pathCampoMostrar.substring(pathCampoMostrar.lastIndexOf("."), pathCampoMostrar.length()));
            tabla = tipoParametroConcepto.substring(0, tipoParametroConcepto.lastIndexOf(".")).replace(".", "_");
            sb.append(",").append(cq.getAliasTablaOuter().get(tabla)).append(".tipo ");
            tabla = campoDefault.substring(0, campoDefault.lastIndexOf(".")).replace(".", "_");
        }

        sb.append(" ORDER BY ").append(cq.getAliasTablaOuter().get(tabla)).append(".clave ");
        List<Object> resultados = (List<Object>) ejecutaQueryList(sb.toString(), true, cq.getListParametros(), cq.getParametrosQuery(), 0);

        if (tipoParametroConcepto.length() > 0 & tipoNodoConsulta == TipoNodoConsulta.DATOCALCULO) {
            ///usado para sumar o contar los valores de parametro en movimiento formato claveEmp, valorParametro, tipoDato{INTEGER o STRING}
            float operacion;
            if (resultados != null) {
                Object[] valoresNew, valores;
                List<Object[]> resultadoNew = new ArrayList<Object[]>();
                boolean add;
                for (i = 0; i < resultados.size(); i++) {
                    valores = (Object[]) resultados.get(i);
                    if (valores.length > 2) {
                        if (valores[2].equals("STRING")) {
                            valores[1] = "1";
                        }
                    }
                    add = true;
                    for (j = 0; j < resultadoNew.size(); j++) {
                        valoresNew = (Object[]) resultadoNew.get(j);
                        if (valoresNew[0].equals(valores[0])) {
                            add = false;
                            if (valores.length > 2) {
                                if (valores[2].equals("INTEGER")) {
                                    operacion = Float.valueOf(valores[1].toString()) + Float.valueOf(valoresNew[1].toString());
                                } else {
                                    operacion = Float.valueOf(valores[1].toString()) + 1;
                                }
                            } else {
                                operacion = Float.valueOf(valores[1].toString());
                            }
                            valoresNew[1] = operacion;
                            resultadoNew.set(j, valoresNew);
                        }
                    }

                    if (add) {
                        resultadoNew.add(valores);
                    }
                }
                resultados = new ArrayList<Object>(resultadoNew);
            }
        }
        ////   sb.append(cq.construyeSelectDatos(campos));
        return resultados == null ? new ArrayList<Object>() : resultados;
    }

    /*
     * usa para evaluar cuando la entidad viene de mas de una entidad evaluando
     * que las entidades medias puedan ser nulas ejemplo
     * movimientos.empleados.clave se pasa en campo movimientos.empleados y en
     * split clave
     */
    private String construyeJoinPlazaEmpleados(Map<String, String> aliasTabla, String completaAlias) {
        StringBuilder outerJoinPlazas = new StringBuilder();
        String tabla = PlazasPorEmpleadosMov.class.getSimpleName();
        outerJoinPlazas.append(tabla).append(" ").append(aliasTabla.get(tabla)).append(completaAlias).append(" LEFT OUTER JOIN ");
        outerJoinPlazas.append(aliasTabla.get(tabla)).append(completaAlias).append(".plazasPorEmpleado ").append(aliasTabla.get(tabla.concat("_plazasPorEmpleado"))).append(completaAlias).append(" LEFT OUTER JOIN ");
        outerJoinPlazas.append(aliasTabla.get(tabla.concat("_plazasPorEmpleado"))).append(completaAlias).append(".empleados ").append(aliasTabla.get(tabla.concat("_plazasPorEmpleado_empleados"))).append(completaAlias);
        return outerJoinPlazas.toString();
    }

    private String construyeWherePlazaEmpleado(Map<String, String> aliasTabla, String completaAlias) {
        StringBuilder outerPlazas = new StringBuilder();
        String tabla = PlazasPorEmpleadosMov.class.getSimpleName();
        outerPlazas.append(aliasTabla.get(tabla)).append(".id IN (SELECT ").append(aliasTabla.get(tabla)).append(completaAlias).append(".id FROM ");
        outerPlazas.append(construyeJoinPlazaEmpleados(aliasTabla, completaAlias)).append(" WHERE (:").append(parametroFechaFinal).append(" >= ");
        outerPlazas.append(aliasTabla.get(tabla)).append(completaAlias).append(".fechaInicial) AND ");
        outerPlazas.append(aliasTabla.get(tabla.concat("_plazasPorEmpleado_empleados"))).append(".clave = ");
        outerPlazas.append(aliasTabla.get(tabla.concat("_plazasPorEmpleado_empleados"))).append(completaAlias).append(".clave) ");
        return outerPlazas.toString();
    }

    private String construyeWhereSalarioIntegrado(String tablaPrincipal, Map<String, String> aliasTabla) {
        StringBuilder outerSalarioIntegrado = new StringBuilder();
        String tablaSalarioDiario = SalariosIntegrados.class.getSimpleName();
        String tablaPlazaEmpMov = PlazasPorEmpleadosMov.class.getSimpleName();
        tablaPlazaEmpMov = tablaPrincipal == null ? tablaPlazaEmpMov : tablaPrincipal.trim().length() == 0 ? tablaPlazaEmpMov : tablaPrincipal;
        outerSalarioIntegrado.append(aliasTabla.get(tablaSalarioDiario)).append(".fecha IN (SELECT MAX(s0.fecha) FROM ").append(tablaSalarioDiario);
        outerSalarioIntegrado.append(" s0 LEFT OUTER JOIN s0.empleados s1").append(" WHERE s0.fecha <= :").append(parametroFechaFinal).append(" AND s1.id =  ");
        if (tablaPrincipal.equalsIgnoreCase(MovNomConcep.class.getSimpleName())) {
            outerSalarioIntegrado.append(aliasTabla.get(tablaPlazaEmpMov.concat("_empleado"))).append(".id) ");
        } else {
            outerSalarioIntegrado.append(aliasTabla.get(tablaPlazaEmpMov.concat("_plazasPorEmpleado_empleados"))).append(".id) ");
        }
        return outerSalarioIntegrado.toString();
    }

    private List<PlazasPorEmpleadosMov> datosPlazaPorEmpleado(String[] camposWhere, Object[] valoresWhere, String[] camposWhereExtras) {

        ConstruyeQueries construyeQueries = new ConstruyeQueries();
        int i;
        List<String> camposWhereEmpleados = new ArrayList<String>();
        List<Object> valoresWhereEmpleados = new ArrayList<Object>();
        List<String> camposWhereExtrasEmpleados = new ArrayList<String>();
        boolean isEntidadSalarioDiario = false;
        for (i = 0; i < camposWhere.length; i++) {
            if (camposWhere[i].startsWith(PlazasPorEmpleadosMov.class.getSimpleName()) | camposWhere[i].startsWith("|".concat(PlazasPorEmpleadosMov.class.getSimpleName()))) {
                camposWhereEmpleados.add(camposWhere[i]);
                valoresWhereEmpleados.add(valoresWhere[i]);
            }
            if (camposWhere[i].equalsIgnoreCase(SalariosIntegrados.class.getSimpleName().concat(".tipoDeSalario"))) {
                isEntidadSalarioDiario = true;
                camposWhereEmpleados.add(camposWhere[i]);
                valoresWhereEmpleados.add(valoresWhere[i]);
                camposWhereExtrasEmpleados.add("|".concat(SalariosIntegrados.class.getSimpleName()).concat(".empleados.id|=|").concat(PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.id")));
            }
        }

        String[] campos;

        for (i = 0; i < camposWhereExtras.length; i++) {
            campos = camposWhereExtras[i].split("\\|");
            if (campos.length > 1) {
                if (campos[1].startsWith(PlazasPorEmpleadosMov.class.getSimpleName())) {
                    camposWhereExtrasEmpleados.add(camposWhereExtras[i]);
                }
            }
        }

        DatosQuery datosQuery = construyeQueries.construyeQueryDatos(new String[]{PlazasPorEmpleadosMov.class.getSimpleName()}, camposWhereEmpleados.toArray(new String[]{}), valoresWhereEmpleados.toArray(), camposWhereExtrasEmpleados.toArray(new String[]{}), new String[]{}, new String[]{"PlazasPorEmpleadosMov.plazasPorEmpleado.empleados.clave"});
        StringBuilder query = new StringBuilder(datosQuery.getQuery());
        ///int pos = query.
        query.append("  AND ").append(construyeWherePlazaEmpleado(datosQuery.getAliasTablas(), "X"));
        datosQuery.getListParametros().add(parametroFechaFinal);
        Calendar fechaFinal = (Calendar) valoresConceptosEmpleados.get(parametroFechaFinal);

        datosQuery.getParametrosCampos().put(parametroFechaFinal, fechaFinal.getTime());
        if (isEntidadSalarioDiario) {
            query = query.append(" AND ").append(construyeWhereSalarioIntegrado(PlazasPorEmpleadosMov.class.getSimpleName(), datosQuery.getAliasTablas()));
        }
        query.append(construyeQueries.construyeGroupBy(new String[]{})).append(" ");
        query.append(construyeQueries.construyeOrderBy(new String[]{"PlazasPorEmpleadosMov.plazasPorEmpleado.empleados.clave"}, ""));
        datosQuery.setQuery(query.toString());
        List<PlazasPorEmpleadosMov> plazasEmpleado = (List<PlazasPorEmpleadosMov>) ejecutaQueryList(datosQuery.getQuery(), datosQuery.isConParametros(), datosQuery.getListParametros(), datosQuery.getParametrosCampos(), 0);
        plazasEmpleado = plazasEmpleado == null ? new ArrayList<PlazasPorEmpleadosMov>() : plazasEmpleado;
        return plazasEmpleado;
    }

    private List<Object> datosPlazaPorEmpleadoMovimientos(String[] camposWhere, Object[] valoresWhere, String[] camposWhereExtras) {

        ConstruyeQueries construyeQueries = new ConstruyeQueries();
        int i, pos = 0;
        List<String> camposWhereEmpleados = new ArrayList<String>();
        List<String> camposWhereExtrasEmpleados = new ArrayList<String>();
        String[] campos;
        boolean isEntidadSalarioDiario = false;
        for (i = 0; i < camposWhere.length; i++) {
            campos = camposWhere[i].split("\\|");
            if (campos.length > 1) {
                pos = 1;
            }
            if (campos[pos].startsWith(MovNomConcep.class.getSimpleName())) {
                camposWhereEmpleados.add(camposWhere[i]);
            }
            if (campos[pos].equalsIgnoreCase(SalariosIntegrados.class.getSimpleName().concat(".tipoDeSalario"))) {
                isEntidadSalarioDiario = true;
                camposWhereEmpleados.add(campos[pos]);
                camposWhereExtrasEmpleados.add("|".concat(SalariosIntegrados.class.getSimpleName()).concat(".empleados.id|=|").concat(MovNomConcep.class.getSimpleName().concat(".plazasPorEmpleado.empleados.id")));
            }
            pos = 0;
        }

        for (i = 0; i < camposWhereExtras.length; i++) {
            campos = camposWhereExtras[i].split("\\|");
            if (campos.length > 1) {
                if (campos[1].startsWith(MovNomConcep.class.getSimpleName())) {
                    camposWhereExtrasEmpleados.add(camposWhereExtras[i]);
                }
            }
        }

        DatosQuery datosQuery = construyeQueries.construyeQueryDatos(new String[]{MovNomConcep.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave")}, camposWhereEmpleados.toArray(new String[]{}), valoresWhere, camposWhereExtrasEmpleados.toArray(new String[]{}), new String[]{}, new String[]{MovNomConcep.class.getSimpleName().concat(".plazasPorEmpleado.clave")});
        StringBuilder query = new StringBuilder(datosQuery.getQuery());

        if (isEntidadSalarioDiario) {
            /*
             * usada fecha en salario integrado buscar mas actual
             */
            datosQuery.getListParametros().add(parametroFechaFinal);
            Calendar fechaFinal = (Calendar) valoresConceptosEmpleados.get(parametroFechaFinal);

            datosQuery.getParametrosCampos().put(parametroFechaFinal, fechaFinal.getTime());
            query = query.append(" AND ").append(construyeWhereSalarioIntegrado(MovNomConcep.class.getSimpleName(), datosQuery.getAliasTablas()));
        }
        query.append(construyeQueries.construyeGroupBy(new String[]{MovNomConcep.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave")})).append(" ");
        query.append(construyeQueries.construyeOrderBy(new String[]{MovNomConcep.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave")}, ""));
        datosQuery.setQuery(query.toString());
        List<Object> plazasEmpleado = (List<Object>) ejecutaQueryList(datosQuery.getQuery(), datosQuery.isConParametros(), datosQuery.getListParametros(), datosQuery.getParametrosCampos(), 0);
        plazasEmpleado = plazasEmpleado == null ? new ArrayList<Object>() : plazasEmpleado;
        return plazasEmpleado;
    }

    private List<PlazasPorEmpleadosMov> filtraPlazaEmpleadoPorRelacionLaboral(List<PlazasPorEmpleadosMov> listPlazasEmpleado) {
        List<PlazasPorEmpleadosMov> plazasFiltradas = new ArrayList<PlazasPorEmpleadosMov>();
        int x = 0, i;
        boolean valido = true;
        ///tiporelacionlaboral 1 planta, 2 eventual, 3 confianza
        while (x < listPlazasEmpleado.size()) {
            for (i = 0; i < plazasFiltradas.size(); i++) {
                if (listPlazasEmpleado.get(x).getPlazasPorEmpleado().getEmpleados().getClave().equalsIgnoreCase(plazasFiltradas.get(i).getPlazasPorEmpleado().getEmpleados().getClave())) {
                    if (listPlazasEmpleado.get(x).getTipoRelacionLaboral() != 2 & plazasFiltradas.get(i).getTipoRelacionLaboral() != 2) {
                        if (listPlazasEmpleado.get(x).getImporte() > plazasFiltradas.get(i).getImporte()) {
                            plazasFiltradas.set(i, listPlazasEmpleado.get(x));
                        }
                    } else if (listPlazasEmpleado.get(x).getTipoRelacionLaboral() == 2 & plazasFiltradas.get(i).getTipoRelacionLaboral() == 2) {
                        if (listPlazasEmpleado.get(x).getImporte() > plazasFiltradas.get(i).getImporte()) {
                            plazasFiltradas.set(i, listPlazasEmpleado.get(x));
                        }
                    } else if (listPlazasEmpleado.get(x).getTipoRelacionLaboral() != 2 & plazasFiltradas.get(i).getTipoRelacionLaboral() == 2) {
                        plazasFiltradas.set(i, listPlazasEmpleado.get(x));
                    }
                    valido = false;
                    break;
                }
            }
            if (valido) {
                plazasFiltradas.add(listPlazasEmpleado.get(x));
            }
            valido = true;
            x++;
        }
        return plazasFiltradas;
    }
    private final String parametroFechaFinal = "FechaRangoFinal".toUpperCase();
    private final String parametroFechaInicial = "FechaRangoInicial".toUpperCase();
    private String nombreFuenteDatos = "";

    private void cargaVariablesGlobales(Object[] clavesEmpleado, Object clavesTipoNomina, Object clavesRazonSocial, Date[] rangoFechas, Object claveTipoCorrida, Object uso, Object claveCentroDeCosto, Object claveImprimeListado, Object claveImprimeRecibo) {
        if (valoresConceptosGlobales == null) {
            valoresConceptosGlobales = new HashMap();
        }
        valoresConceptosGlobales.put(Empleados.class.getSimpleName().toUpperCase(), clavesEmpleado);
        valoresConceptosGlobales.put(TipoNomina.class.getSimpleName().toUpperCase(), clavesTipoNomina);
        valoresConceptosGlobales.put(RazonesSociales.class.getSimpleName().toUpperCase(), clavesRazonSocial);
        if (claveTipoCorrida != null) {
            valoresConceptosGlobales.put(TipoCorrida.class.getSimpleName().toUpperCase(), claveTipoCorrida);
        }
        if (claveCentroDeCosto != null) {
            valoresConceptosGlobales.put(CentroDeCosto.class.getSimpleName().toUpperCase(), claveCentroDeCosto);
        }
        if (uso != null) {
            valoresConceptosGlobales.put("uso".toUpperCase(), uso);
        }
        if (claveImprimeListado != null) {
            valoresConceptosGlobales.put("ImprimeListado".toUpperCase(), claveImprimeListado);
        }
        if (claveImprimeRecibo != null) {
            valoresConceptosGlobales.put("ImprimeRecibo".toUpperCase(), claveImprimeRecibo);
        }
        Calendar cIni = Calendar.getInstance(), cFin = Calendar.getInstance();
        if (rangoFechas.length > 0) {
            cIni.setTime(rangoFechas[0]);
            cFin.setTime(rangoFechas[1]);
        }
        valoresConceptosGlobales.put(parametroFechaInicial, cIni);
        valoresConceptosGlobales.put(parametroFechaFinal, cFin);

        valoresConceptosGlobales.put("RazonSocial".toUpperCase(), clavesRazonSocial);
        valoresConceptosGlobales.put("NumCentroCostos".toUpperCase(), claveCentroDeCosto);
        valoresConceptosGlobales.put("ClaveTipoCorrida".toUpperCase(), claveTipoCorrida);
        valoresConceptosGlobales.put("TipoNomina".toUpperCase(), clavesTipoNomina);
        tipoCorrida = (TipoCorrida) ejecutaQueryUnico("From TipoCorrida tc Where tc.clave = :clave", new String[]{"clave"}, new Object[]{claveTipoCorrida});
        valoresConceptosGlobales.put("TipoCorridaAlfa".toUpperCase(), tipoCorrida == null ? "" : tipoCorrida.getDescripcion());
    }

    public Mensaje busquedaQueryConsultaEmpleados(String[] tablasRelacionadas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String[] valoresDatosEspeciales, String[] camposWhereExtras, String nombreFuenteDatos, Date[] rangoFechas, String ordenado, String claveRazonSocial, String controladores, String uuidCxn, String uuidCxnMaestra) {//VIC01
        int i, j, k;
        List<Object> resultados;
        mensajeResultado = new Mensaje();
        mensajeResultado.setNoError(0);
        mensajeResultado.setError("");
        isCalculoSDI = false;
        try {
            boolean isValorParametroOConcepto = false;
            isMov2Meses = false;
            boolean usaMesesEnQuery = false;
            if (camposMostrar.length == 0) {
                mensajeResultado.setResultado(new ArrayList<Object>());
                return mensajeResultado;
            }
            camposWhereExtras = camposWhereExtras == null ? new String[]{} : camposWhereExtras;
//        System.out.println("tiempo busquedaQueryConsultaEmpleados " + Calendar.getInstance().getTime());
            usaFiniquitos = false;
            boolean isDatoImss = false, isTablaFactorIntegracion = false, isTablaZonaSalarial = false, isParametro = true;
            nombreFuenteDatos = nombreFuenteDatos == null ? "" : nombreFuenteDatos;
            ////nombreFuenteDatos = nombreFuenteDatos;
            camposWhere = (camposWhere == null ? new String[]{} : camposWhere);
            propertieFuente = compEjec.abrirPropiedadBundle(nombreFuenteDatos);
            if (propertieFuente == null) {
                propertieFuente = compEjec.abrirPropiedadBundle("FuentesDeDatos");
            }
            String[] camposFormula;
            TipoClasificacionFormula tipoClasifFormula;
            String field;
            String[] fields;
            TipoNodoConsulta tipoNodoConsulta;
            boolean usaMovEnEmpleados = false;
            for (i = 0; i < camposMostrar.length; i++) {
                if (camposMostrar[i].contains("#")) {
                    fields = camposMostrar[i].split("#");
                    field = fields[0];
                } else if (camposMostrar[i].contains("|")) {
                    fields = camposMostrar[i].split("\\|");
                    if (fields.length > 1 & nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) {
                        for (int l = 1; l < fields.length; l++) {
                            if (fields[l].startsWith(MovNomConcep.class.getSimpleName())) {
                                usaMovEnEmpleados = true;
                            }
                        }
                    }
                    field = fields[0];
                } else {
                    field = camposMostrar[i];
                }
//////            if ("ValorParametroOConcepto_Path".equalsIgnoreCase(field)) {
//////                isValorParametroOConcepto = true;
//////            }

                if (propertieFuente != null) {
                    if (propertieFuente.containsKey(field)) {
                        if ("TotalImportePorConcepto_Path".equalsIgnoreCase(field) | "TotalImporteExentoPorConcepto_Path".equalsIgnoreCase(field) | "TotalImporteGravablePorConcepto_Path".equalsIgnoreCase(field)
                                | "TotalImporteGravablePorConceptoDato_Path".equalsIgnoreCase(field) | "TotalImportePorConceptoDato_Path".equalsIgnoreCase(field) | "TotalImporteExentoPorConceptoDato_Path".equalsIgnoreCase(field)
                                | "TotalBaseISR_Path".equalsIgnoreCase(field) | "TotalBaseISRNormal_Path".equalsIgnoreCase(field) | "TotalBaseISRDirecto_Path".equalsIgnoreCase(field) | "TotalBaseISRAnual_Path".equalsIgnoreCase(field)
                                | "TotalBaseISRGravable_Path".equalsIgnoreCase(field) | "TotalBaseISRGravableNormal_Path".equalsIgnoreCase(field) | "TotalBaseISRGravableDirecto_Path".equalsIgnoreCase(field)
                                | "TotalBaseISRGravableAnual_Path".equalsIgnoreCase(field) | "TotalBaseISRExento_Path".equalsIgnoreCase(field) | "TotalBaseISRExentoNormal_Path".equalsIgnoreCase(field) | "TotalBaseISRExentoDirecto_Path".equalsIgnoreCase(field)
                                | "TotalBaseISRExentoAnual_Path".equalsIgnoreCase(field) | "TotalBaseIMSS_Path".equalsIgnoreCase(field) | "TotalBaseIMSSFija_Path".equalsIgnoreCase(field) | "TotalBaseIMSSVariable_Path".equalsIgnoreCase(field)
                                | "TotalBaseIMSSGravado_Path".equalsIgnoreCase(field) | "TotalBaseIMSSGravadoFija_Path".equalsIgnoreCase(field) | "TotalBaseIMSSGravadoVariable_Path".equalsIgnoreCase(field) | "TotalBaseIMSSExento_Path".equalsIgnoreCase(field)
                                | "TotalBaseIMSSExentoFija_Path".equalsIgnoreCase(field) | "TotalBaseIMSSExentoVariable_Path".equalsIgnoreCase(field) | "TotalBaseInfonavit_Path".equalsIgnoreCase(field) | "TotalBasePTU_Path".equalsIgnoreCase(field)
                                | "TotalBaseImpuestoNomina_Path".equalsIgnoreCase(field) | "TotalBaseDespensa_Path".equalsIgnoreCase(field) | "TotalBaseFondoAhorro_Path".equalsIgnoreCase(field) | "TotalBaseAguinaldo_Path".equalsIgnoreCase(field)
                                | "TotalBaseOtros_Path".equalsIgnoreCase(field)) {
                            isValorParametroOConcepto = true;
                            isMov2Meses = true;
                        }
                        tipoNodoConsulta = TipoNodoConsulta.getEnum(propertieFuente.getProperty(field.concat("_TipoNodo")));
                        field = propertieFuente.getProperty(field);
                        if (field.contains("|")) {
                            fields = field.split("\\|");
                            field = fields[0];
                        }
                        if ((tipoNodoConsulta == TipoNodoConsulta.CAMPO | tipoNodoConsulta == TipoNodoConsulta.CAMPOESPECIAL) & (field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))
                                | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".resultado")) | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"))
                                | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado")))) {
                            usaMesesEnQuery = true;
                        }
                        if (field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor")) & (tipoNodoConsulta == TipoNodoConsulta.CAMPO | tipoNodoConsulta == TipoNodoConsulta.CAMPOESPECIAL)) {
                            isValorParametroOConcepto = true;
                            isMov2Meses = true;
                        } else if (field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".resultado")) | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"))
                                | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"))) {
                            isMov2Meses = true;
                        }
                    } else if (field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
                        isValorParametroOConcepto = true;
                        isMov2Meses = true;
                        usaMesesEnQuery = true;
                    } else if (field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".resultado")) | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"))
                            | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"))) {
                        isMov2Meses = true;
                        usaMesesEnQuery = true;
                    }
                } else if (field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
                    isValorParametroOConcepto = true;
                    isMov2Meses = true;
                    usaMesesEnQuery = true;
                } else if (field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".resultado")) | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"))
                        | field.equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"))) {
                    isMov2Meses = true;
                    usaMesesEnQuery = true;
                }
                if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados") & field.startsWith(MovNomConcep.class.getSimpleName())) {
                    usaMovEnEmpleados = true;
                }
                if (camposMostrar[i].startsWith("@")) {
                    ////camposFormula = obtieneVariablesCampoFormula(camposMostrar[i].substring(1));
                    camposFormula = eliminaCaracteresSeparador(camposMostrar[i].substring(1)).split("\\|");
                    for (String strCampo : camposFormula) {
                        tipoClasifFormula = TipoClasificacionFormula.SINCLASIFICAR;
                        if (strCampo.indexOf('(') != -1) {
                            strCampo = strCampo.substring(0, strCampo.indexOf('('));
                        }
                        if (propertieFuente.containsKey(strCampo.concat("_TipoDato"))) {
                            tipoClasifFormula = TipoClasificacionFormula.getEnum(propertieFuente.getProperty(strCampo.concat("_TipoDato")));
                        } else if (propertieFuente.containsKey(strCampo)) {
                            strCampo = propertieFuente.getProperty(strCampo);
                            if (propertieFuente.containsKey(strCampo.concat("_TipoDato"))) {
                                tipoClasifFormula = TipoClasificacionFormula.getEnum(propertieFuente.getProperty(strCampo.concat("_TipoDato")));
                            }
                        }
                        if (tipoClasifFormula == TipoClasificacionFormula.DATOIMSS) {
                            isDatoImss = true;
                        } else if (strCampo.equalsIgnoreCase("VacacionesPorDisfrutar")
                                | tipoClasifFormula == TipoClasificacionFormula.TABLAFACTORINTEGRACION) {
                            isTablaFactorIntegracion = true;
                        } else if (tipoClasifFormula == TipoClasificacionFormula.TABLAZONASALARIAL) {
                            isTablaZonaSalarial = true;
                        } else if (tipoClasifFormula == TipoClasificacionFormula.DATOPARAMETRO) {
                            isParametro = true;
                        }
                    }
                }
            }
            if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) {
                if (camposOrden == null ? false : camposOrden.length > 0) {
                    for (i = 0; i < camposOrden.length; i++) {

                        if (camposOrden[i].contains("#")) {
                            fields = camposOrden[i].split("#");
                            field = fields[0];
                        } else if (camposOrden[i].contains("|")) {
                            fields = camposOrden[i].split("\\|");
                            for (int l = 1; l < fields.length; l++) {
                                if (fields[l].startsWith(MovNomConcep.class.getSimpleName())) {
                                    usaMovEnEmpleados = true;
                                }
                            }
                            field = fields[0];
                        } else {
                            field = camposOrden[i];
                        }
                        if (field.startsWith(MovNomConcep.class.getSimpleName())) {
                            usaMovEnEmpleados = true;
                        }
                    }
                }
            }

            String corrida = "";
            if (!usaMovEnEmpleados & nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) {
                List<String> camposWhereFiltrado = new ArrayList<String>();
                List<Object> valoresWhereFiltrado = new ArrayList<Object>();
                String[] datos;
                String valor;
                for (i = 0; i < camposWhere.length; i++) {
                    datos = camposWhere[i].split("\\|");
                    if (datos.length > 1) {
                        valor = datos[1];
                    } else {
                        valor = datos[0];
                    }
                    if (!valor.startsWith(MovNomConcep.class.getSimpleName())) {
                        camposWhereFiltrado.add(camposWhere[i]);
                        valoresWhereFiltrado.add(valoresWhere[i]);
                    } else if (valor.toUpperCase().contains(TipoCorrida.class.getSimpleName().toUpperCase())) {
                        corrida = valoresWhere[i].toString();
                    }
                }
                camposWhere = camposWhereFiltrado.toArray(new String[]{});
                valoresWhere = valoresWhereFiltrado.toArray();

                if (camposWhereExtras != null) {
                    List<String> camposWhereFiltradoExtra = new ArrayList<String>();
                    for (i = 0; i < camposWhereExtras.length; i++) {
                        fields = camposWhereExtras[i].split("\\|");
                        if (!fields[1].startsWith(MovNomConcep.class.getSimpleName())) {
                            camposWhereFiltradoExtra.add(camposWhereExtras[i]);
                        }
                    }
                    camposWhereExtras = camposWhereFiltradoExtra.toArray(new String[]{});
                }
                if (tablasRelacionadas != null) {
                    List<String> camposTablasRelacionadas = new ArrayList<String>();
                    for (i = 0; i < tablasRelacionadas.length; i++) {
                        if (!tablasRelacionadas[i].startsWith(MovNomConcep.class.getSimpleName())) {
                            camposTablasRelacionadas.add(tablasRelacionadas[i]);
                        }
                    }
                    tablasRelacionadas = camposTablasRelacionadas.toArray(new String[]{});
                }
            }

            List<Object> clavesElementosAplicacion = new ArrayList<Object>();
            clavesElementosAplicacion.add(claveRazonSocial);
            cargaTablaFactorIntegracion(controladores, clavesElementosAplicacion, isTablaFactorIntegracion, isTablaZonaSalarial, isParametro, getFechaDelSistema().getTime(), getFechaDelSistema().get(Calendar.YEAR), uuidCxnMaestra);

            /**
             * ***********************crea
             * conexion***************************************************
             */
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            datosFormulas = new ArrayList<DatosEspeciales>();
            valoresConceptosEmpleados = new HashMap();
            valoresConceptosGlobales = new HashMap();
            ///valoresConceptosEmpleados.put("TipoNomina".toUpperCase(), "2");
            Calendar cIni = Calendar.getInstance(), cFin = Calendar.getInstance();
            if (rangoFechas.length > 0) {
                cIni.setTime(rangoFechas[0]);
                cFin.setTime(rangoFechas[1]);
            }

            int ini = cIni.get(Calendar.MONTH), fin = cFin.get(Calendar.MONTH);
            List<Integer> meses = new ArrayList<Integer>();
            if (ini > fin) {
                meses.add(ini + 1);
                meses.add(fin + 1);
            } else {
                for (i = ini; i <= fin; i++) {
                    meses.add(i + 1);
                }
            }

            valoresConceptosEmpleados.put(parametroFechaInicial, cIni);
            valoresConceptosEmpleados.put(parametroFechaFinal, cFin);
            /*
             * carga datos configuracion IMSS
             */
            if (isDatoImss) {
                cargaDatosVariableConfiguracionIMSS(rangoFechas[1]);
            }
            if (isParametro) {
                valoresConceptosGlobales.put("FactorElevMensual".toUpperCase(), factorMensual);
                valoresConceptosGlobales.put("FactorElevAnual".toUpperCase(), factorAnual);
            }
            ///////periodioAcumuladoPorRangoMeses(TipoAcumulado.BIMESTRAL, calendar, "p.diasPago");
            List<PlazasPorEmpleadosMov> plazasPorEmpleadosMov = null;
            List<Object> idsPlazasValidas = new ArrayList<Object>();
            Set<Object> claveEmpleados = new HashSet<Object>();
            Object claveTipoNominaGloblal = null, claveRazonSocialGlobal = null, claveTipoCorrida = corrida, uso = null, claveCentroCostoGlobal = null, claveImprimeListado = null, ClaveImprimeRecibo = null;
            manejaPagoDiasNaturales = manejaPagoDiasNaturales == null ? false : manejaPagoDiasNaturales;
            manejaPagoIMSSDiasNaturales = manejaPagoIMSSDiasNaturales == null ? false : manejaPagoIMSSDiasNaturales;
            ///********************************************************/
            if (isTablaZonaSalarial) {
                salarioMinimoDF = buscaSalarioPorZona('A').getSalario();
                valoresConceptosGlobales.put("SalarioMinDF".toUpperCase(), salarioMinimoDF);
                salarioMinimoDF = null;
            }
            Set<String> filtrosTipoMovimiento = new HashSet<String>();
            if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) {
                ///camposMostrar = Arrays.asList(camposMostrar).add();
                plazasPorEmpleadosMov = datosPlazaPorEmpleado(camposWhere, valoresWhere, camposWhereExtras);
                if (plazasPorEmpleadosMov.isEmpty()) {
                    mensajeResultado.setResultado(new ArrayList<Object>());
                    return mensajeResultado;
                }
                plazasPorEmpleadosMov = filtraPlazaEmpleadoPorRelacionLaboral(plazasPorEmpleadosMov);
                for (i = 0; i < plazasPorEmpleadosMov.size(); i++) {
                    idsPlazasValidas.add(plazasPorEmpleadosMov.get(i).getId());
                    claveEmpleados.add(plazasPorEmpleadosMov.get(i).getPlazasPorEmpleado().getEmpleados().getClave());
                }

                for (i = 0; i < camposWhere.length; i++) {
                    if (camposWhere[i].equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".tipoNomina.clave"))) {
                        claveTipoNominaGloblal = valoresWhere[i];
                        filtrosTipoMovimiento.add(MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"));
                    } else if (camposWhere[i].equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.razonesSociales.clave"))) {
                        claveRazonSocialGlobal = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"))) {
                        claveTipoCorrida = valoresWhere[i];
                        filtrosTipoMovimiento.add(camposWhere[i]);
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".uso"))) {
                        uso = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".centroDeCosto.clave"))) {
                        claveCentroCostoGlobal = valoresWhere[i];
                        filtrosTipoMovimiento.add(MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave"));
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.imprimirEnListadoNomina"))) {
                        claveImprimeListado = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.imprimirEnReciboNomina"))) {
                        ClaveImprimeRecibo = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave"))) {
                        filtrosTipoMovimiento.add(MovNomConcep.class.getSimpleName().concat(".empleado.clave"));
                    }
                }
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Movimientos")) {
                claveEmpleados.addAll(datosPlazaPorEmpleadoMovimientos(camposWhere, valoresWhere, camposWhereExtras));
//                for (i = 0; i < plazasPorEmpleados.size(); i++) {
//                    claveEmpleados.add(plazasPorEmpleados.get(i).getEmpleados().getClave());
//                }
                if (claveEmpleados.isEmpty()) {
                    return mensajeResultado;
                }
                for (i = 0; i < camposWhere.length; i++) {
                    if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"))) {
                        claveTipoNominaGloblal = valoresWhere[i];
                        filtrosTipoMovimiento.add(camposWhere[i]);
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".razonesSociales.clave"))) {
                        claveRazonSocialGlobal = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"))) {
                        filtrosTipoMovimiento.add(camposWhere[i]);
                        claveTipoCorrida = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".uso"))) {
                        uso = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave"))) {
                        claveCentroCostoGlobal = valoresWhere[i];
                        filtrosTipoMovimiento.add(camposWhere[i]);
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.imprimirEnListadoNomina"))) {
                        claveImprimeListado = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.imprimirEnReciboNomina"))) {
                        ClaveImprimeRecibo = valoresWhere[i];
                    } else if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".empleado.clave"))) {
                        filtrosTipoMovimiento.add(MovNomConcep.class.getSimpleName().concat(".empleado.clave"));
                    }
                }
            }

            if (camposWhereExtras != null) {
                String[] valores;
                for (i = 0; i < camposWhereExtras.length; i++) {
                    valores = camposWhereExtras[i].split("\\|");
                    if (valores[1].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".empleado.clave")) | valores[1].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave")) | valores[1].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"))
                            | valores[1].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"))) {
                        filtrosTipoMovimiento.add(valores[1]);
                    } else if (valores[1].equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".tipoNomina.clave"))) {
                        filtrosTipoMovimiento.add(MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"));
                    } else if (valores[1].equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave"))) {
                        filtrosTipoMovimiento.add(MovNomConcep.class.getSimpleName().concat(".empleado.clave"));
                    } else if (valores[1].equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".centroDeCosto.clave"))) {
                        filtrosTipoMovimiento.add(MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave"));
                    }
                }
                if (camposWhereExtras.length > 1) {
                    List<String> camposExtrasWhere = new ArrayList<String>();
                    String[] divideCampos = camposWhereExtras[0].split("\\|");
                    String inicio = "(";
                    if (divideCampos[0].length() == 0) {
                        inicio = "AND (";
                    } else {
                        inicio = divideCampos[0].concat(" (");
                    }
                    camposWhereExtras[0] = camposWhereExtras[0].substring(camposWhereExtras[0].indexOf("|"));
                    camposExtrasWhere.add(inicio);
                    for (i = 0; i < camposWhereExtras.length; i++) {
                        camposExtrasWhere.add(camposWhereExtras[i]);
                    }
                    camposExtrasWhere.add(")");
                    camposWhereExtras = camposExtrasWhere.toArray(new String[]{});
                }
            }

            int cont = 0;
            boolean isFinqCorrida = false;
            camposFiniquitos = new ArrayList<String>();
            valoresCamposFiniquitos = new ArrayList<Object>();
            for (i = 0; i < camposWhere.length; i++) {
                if (camposWhere[i].equalsIgnoreCase(MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"))) {
                    if (valoresWhere[i].equals("FIN")) {
                        isFinqCorrida = true;
                    }
                } else if (camposWhere[i].startsWith(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion"))) {
                    camposFiniquitos.add(camposWhere[i]);
                    valoresCamposFiniquitos.add(valoresWhere[i]);
                    cont++;
                }
            }

            cargaVariablesGlobales(claveEmpleados.toArray(), claveTipoNominaGloblal, claveRazonSocialGlobal, rangoFechas, claveTipoCorrida, uso, claveCentroCostoGlobal, claveImprimeListado, ClaveImprimeRecibo);
            cargarVariablesConceptosCompilador();
            valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
            ConstruyeQueries construyeQuerie = new ConstruyeQueries(propertieFuente, nombreFuenteDatos);
            /*agrega ranggo de meses */
            if (isMov2Meses) {
                if (usaMesesEnQuery) {
                    List<String> camposExtrasPeriodos = new ArrayList<String>();
                    List<Object> valoresExtrasPeriodos = new ArrayList<Object>();
                    camposExtrasPeriodos.addAll(0, Arrays.asList(camposWhere));
                    camposExtrasPeriodos.add("|".concat(MovNomConcep.class.getSimpleName().concat(".mes|IN")));
                    camposWhere = camposExtrasPeriodos.toArray(new String[]{});
                    valoresExtrasPeriodos.addAll(0, Arrays.asList(valoresWhere));
                    valoresExtrasPeriodos.add(meses);
                    valoresWhere = valoresExtrasPeriodos.toArray();
                    construyeQuerie.getParametrosQuery().put("mes", meses);
                    construyeQuerie.getListParametros().add("mes");
                }
            }

            if (cont > 0 & isFinqCorrida) {
                usaFiniquitos = true;
                construyeQuerie.setUsaFiniquitos(true);
            }
            construyeQuerie.setValoresDatosEspeciales(valoresDatosEspeciales);
            construyeQuerie.setCamposFiltroMovimientos(filtrosTipoMovimiento.toArray(new String[]{}));
            if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Movimientos")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, MovNomConcep.class.getSimpleName().concat(".empleado.clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_CentroDeCostos")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, CentroDeCosto.class.getSimpleName().concat(".clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_ConcepNomDefi")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, ConceptoPorTipoCorrida.class.getSimpleName().concat(".concepNomDefi.clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Departamentos")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, Departamentos.class.getSimpleName().concat(".clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_RegistroIncapacidad")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, RegistroIncapacidad.class.getSimpleName().concat(".empleados.clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_AhorrosMovimientos") | nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_CreditosMovimientos")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, CreditoMovimientos.class.getSimpleName().concat(".creditoPorEmpleado.empleados.clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Comprobante")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, CFDIEmpleado.class.getSimpleName().concat(".plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_CFDIEmpleado")) { //campo obligatorio
                List<String> mostrar = new ArrayList<String>(Arrays.asList(camposMostrar));
                mostrar.add(0, CFDIEmpleado.class.getSimpleName().concat(".plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave"));
                camposMostrar = mostrar.toArray(new String[]{});
            }

            construyeQuerie.generaListaTablasMapeadas(camposMostrar, camposWhere, camposWhereExtras, camposOrden);
            builder = new StringBuilder(construyeQuerie.construyeSelectDatos(camposMostrar)).append(" ");
//////        if (nombreFuente.equalsIgnoreCase("FuenteDatos_Movimientos")) {
//////            for (i = 0; i < construyeQuerie.getListDatosFormula().size(); i++) {
//////                if (constrzuyeQuerie.getListDatosFormula().get(i).getNodoConsulta() == TipoNodoConsulta.DATOREMPLAZO) {
//////                }
//////            }
//////        }

            builder.append(construyeQuerie.construyeFromConsulta(ConstruyeQueries.LEFTJOIN)).append(" ");

            boolean isEntidadSalarioDiario = false;
            List<String> camposExtrasWhere = new ArrayList<String>(camposWhereExtras == null ? new ArrayList<String>() : Arrays.asList(camposWhereExtras));
            String nombreTablaFuente = PlazasPorEmpleadosMov.class.getSimpleName();
            if (tablasRelacionadas != null & "FuenteDatos_Empleados".equalsIgnoreCase(nombreFuenteDatos)) { ///tablas relacionadas entidades
                String path[];
                String tablaPadre;
                construyeQuerie.mapeaTablasCampo(tablasRelacionadas);
                Class entidadRelacionada;
                boolean usaMovim = false;
                List<String> camposExtrasPeriodos = new ArrayList<String>();
                List<Object> valoresExtrasPeriodos = new ArrayList<Object>();
                for (i = 0; i < tablasRelacionadas.length; i++) {
                    path = tablasRelacionadas[i].split("\\.");
                    tablaPadre = path[0];
                    if (tablaPadre.startsWith(SalariosIntegrados.class.getSimpleName())) {
                        isEntidadSalarioDiario = true;
                    } else if (tablaPadre.equals(MovNomConcep.class.getSimpleName())) {
                        usaMovim = true;
                    }
                    if (path.length > 1) {
                        for (j = 1; j < path.length - 1; j++) {
                            builder.append(" ").append(ConstruyeQueries.RIGHTJOIN).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablaPadre));
                            tablaPadre = tablaPadre.concat("_").concat(path[j]);
                            builder.append(".").append(path[j]).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablaPadre)).append(" ");
                        }
                        ////builder.append(" ").append(construyeQuerie.construyeAgregaCampoFrom(tablasRelacionadas[i], ConstruyeQueries.RIGHTJOIN));
////                    builder.append(" ").append(ConstruyeQueries.RIGHTJOIN).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablaPadre));
////                    builder.append(".").append(path[1]).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablasRelacionadas[i].substring(0, tablasRelacionadas[i].lastIndexOf(".")).replace(".", "_"))).append(" ");
                        entidadRelacionada = construyeQuerie.buscarTipoDatoCampo(tablasRelacionadas[i].substring(0, tablasRelacionadas[i].lastIndexOf(".")));
                        if (entidadRelacionada.equals(Empleados.class)) {
                            camposExtrasWhere.add("|".concat(tablasRelacionadas[i]).concat("|=|").concat(PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave")));
                        } else if (entidadRelacionada.equals(RazonesSociales.class)) {
                            camposExtrasWhere.add("|".concat(tablasRelacionadas[i]).concat("|=|").concat(PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.razonesSociales.clave")));
                        } else if (entidadRelacionada.equals(PeriodosNomina.class)) {
////                        if (construyeQuerie.getAliasTablaOuter().containsKey(MovNomConcep.class.getSimpleName().concat("periodosNomina"))) {
////                            camposExtrasWhere.add("|".concat(tablasRelacionadas[i]).concat("|=|").concat(MovNomConcep.class.getSimpleName().concat(".periodosNomina.clave")));
////                        } else {
                            if (claveTipoCorrida == null ? false : claveTipoCorrida.toString().trim().length() > 0) {
                                camposExtrasPeriodos.add(tablasRelacionadas[i].substring(0, tablasRelacionadas[i].lastIndexOf(".")).concat(".tipoCorrida.clave"));
                                valoresExtrasPeriodos.add(claveTipoCorrida);
                            }
                            camposExtrasPeriodos.add("(|".concat(tablasRelacionadas[i].substring(0, tablasRelacionadas[i].lastIndexOf(".")).concat(".fechaInicial|BETWEEN")));
                            valoresExtrasPeriodos.add(rangoFechas);
                            camposExtrasPeriodos.add("OR|".concat(tablasRelacionadas[i].substring(0, tablasRelacionadas[i].lastIndexOf(".")).concat(".fechaFinal|BETWEEN|)")));
                            valoresExtrasPeriodos.add(rangoFechas);
////////                        } 
                        }
                    }
                }

                if (camposExtrasPeriodos.size() > 0 & camposWhere.length > 0) {
                    for (i = 0; i < camposWhere.length; i++) {
                        j = 0;
                        while (j < camposExtrasPeriodos.size()) {
                            if (camposWhere[i].equalsIgnoreCase(camposExtrasPeriodos.get(j))) {
                                camposExtrasPeriodos.remove(j);
                                break;
                            } else {
                                j++;
                            }
                        }
                        if (camposExtrasPeriodos.isEmpty()) {
                            break;
                        }
                    }
                    camposExtrasPeriodos.addAll(0, Arrays.asList(camposWhere));
                    camposWhere = camposExtrasPeriodos.toArray(new String[]{});
                    valoresExtrasPeriodos.addAll(0, Arrays.asList(valoresWhere));
                    valoresWhere = valoresExtrasPeriodos.toArray();
                }
                if (camposWhereExtras != null) {
                    for (i = 0; i < camposWhereExtras.length; i++) {
                        if (camposWhereExtras[i].replace("|", "").startsWith(MovNomConcep.class.getSimpleName())) {
                            usaMovim = true;
                            break;
                        }
                    }
                }

                if (!usaMovim) {
                    List<String> camposWhereFiltrado = new ArrayList<String>();
                    List<Object> valoresWhereFiltrado = new ArrayList<Object>();
                    String[] datos;
                    String valor;
                    for (i = 0; i < camposWhere.length; i++) {
                        datos = camposWhere[i].split("\\|");
                        if (datos.length > 1) {
                            valor = datos[1];
                        } else {
                            valor = datos[0];
                        }
                        if (!valor.startsWith(MovNomConcep.class.getSimpleName())) {
                            camposWhereFiltrado.add(camposWhere[i]);
                            valoresWhereFiltrado.add(valoresWhere[i]);
                        }
                    }
                    camposWhere = camposWhereFiltrado.toArray(new String[]{});
                    valoresWhere = valoresWhereFiltrado.toArray();
                }
                camposWhereExtras = camposExtrasWhere.toArray(new String[]{});
                construyeQuerie.mapeaTablasCampo(camposWhereExtras);
                camposWhereExtras = camposExtrasWhere.toArray(new String[]{});
            } else if (tablasRelacionadas != null & nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Movimientos")) { ///tablas relacionadas entidades
                String path[];
                String tablaPadre;
                Class entidadRelacionada;
                nombreTablaFuente = MovNomConcep.class.getSimpleName();
                construyeQuerie.mapeaTablasCampo(tablasRelacionadas);
                for (i = 0; i < tablasRelacionadas.length; i++) {
                    path = tablasRelacionadas[i].split("\\.");
                    tablaPadre = path[0];
                    if (path.length > 1) {
                        for (j = 1; j < path.length - 1; j++) {
                            builder.append(" ").append(ConstruyeQueries.RIGHTJOIN).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablaPadre));
                            tablaPadre = tablaPadre.concat("_").concat(path[j]);
                            builder.append(".").append(path[j]).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablaPadre)).append(" ");
                        }
//////                    builder.append(" ").append(ConstruyeQueries.RIGHTJOIN).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablaPadre));
//////                    builder.append(".").append(path[1]).append(" ").append(construyeQuerie.getAliasTablaOuter().get(tablasRelacionadas[i].substring(0, tablasRelacionadas[i].lastIndexOf(".")).replace(".", "_"))).append(" ");

                        entidadRelacionada = construyeQuerie.buscarTipoDatoCampo(tablasRelacionadas[i].substring(0, tablasRelacionadas[i].lastIndexOf(".")));
                        if (entidadRelacionada.equals(Empleados.class)) {
                            camposExtrasWhere.add("|".concat(tablasRelacionadas[i]).concat("|=|").concat(MovNomConcep.class.getSimpleName().concat(".empleado.clave")));
                        } else if (entidadRelacionada.equals(RazonesSociales.class)) {
                            camposExtrasWhere.add("|".concat(tablasRelacionadas[i]).concat("|=|").concat(MovNomConcep.class.getSimpleName().concat(".razonesSociales.clave")));
                        }
                    }
                }
                /*
                 * usado si en campos where se escogio tipo de salario
                 */
                int pos = 0;
                for (i = 0; i < camposWhere.length; i++) {
                    path = camposWhere[i].split("\\|");
                    if (path.length > 1) {
                        pos = 1;
                    }

                    if (path[pos].equalsIgnoreCase(SalariosIntegrados.class.getSimpleName().concat(".tipoDeSalario"))) {
                        isEntidadSalarioDiario = true;
                        camposExtrasWhere.add("|".concat(SalariosIntegrados.class.getSimpleName()).concat(".empleados.id|=|").concat(MovNomConcep.class.getSimpleName().concat(".empleado.id")));
                    }
                    pos = 0;
                }
                camposWhereExtras = camposExtrasWhere.toArray(new String[]{});
                construyeQuerie.mapeaTablasCampo(camposWhereExtras);
            }

            builder.append(construyeQuerie.construyeWhereConParametro(camposWhere, valoresWhere, camposWhereExtras)).append(" ");

            if (isValorParametroOConcepto) { //*usada para cuando se agrege campo select en movparametro el campo valor se agrege a subconsulta rango de fechas**/
                construyeQuerie.getListParametros().add("ValorFechaInicial");
                Calendar fechaFinal = (Calendar) valoresConceptosEmpleados.get(parametroFechaInicial);
                construyeQuerie.getParametrosQuery().put("ValorFechaInicial", fechaFinal.getTime());
                construyeQuerie.getListParametros().add("ValorFechaFinal");
                fechaFinal = (Calendar) valoresConceptosEmpleados.get(parametroFechaFinal);
                construyeQuerie.getParametrosQuery().put("ValorFechaFinal", fechaFinal.getTime());
            }
            if (isEntidadSalarioDiario) {
                builder.append("AND ").append(construyeWhereSalarioIntegrado(nombreTablaFuente, construyeQuerie.getAliasTablaOuter()));
                /*
                 * usada fecha en salario integrado buscar mas actual
                 */
                construyeQuerie.getListParametros().add(parametroFechaFinal);
                Calendar fechaFinal = (Calendar) valoresConceptosEmpleados.get(parametroFechaFinal);
                construyeQuerie.getParametrosQuery().put(parametroFechaFinal, fechaFinal.getTime());
            }
            if (idsPlazasValidas.size() > 0) {
                builder.append("AND ").append(construyeQuerie.getAliasTablaOuter().get(PlazasPorEmpleadosMov.class.getSimpleName()));
                builder.append(".id IN (:IdPlazasEmpleadoMov) ");
                construyeQuerie.getListParametros().add("IdPlazasEmpleadoMov");
                construyeQuerie.getParametrosQuery().put("IdPlazasEmpleadoMov", idsPlazasValidas);
            }
//////        if (nombreFuente.equalsIgnoreCase("FuenteDatos_Empleados")) {
//////           ////////builder.append(construyeQuerie.construyeGroupBy(camposMostrar)).append(" ");
//////        }
            if (camposOrden.length == 0) {
                if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) {
                    camposOrden = new String[]{PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_CentroDeCostos")) {
                    camposOrden = new String[]{CentroDeCosto.class.getSimpleName().concat(".clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_ConcepNomDefi")) {
                    camposOrden = new String[]{ConceptoPorTipoCorrida.class.getSimpleName().concat(".concepNomDefi.clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Departamentos")) {
                    camposOrden = new String[]{Departamentos.class.getSimpleName().concat(".clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_RegistroIncapacidad")) {
                    camposOrden = new String[]{RegistroIncapacidad.class.getSimpleName().concat(".empleados.clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_AhorrosMovimientos") | nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_CreditosMovimientos")) {
                    camposOrden = new String[]{CreditoMovimientos.class.getSimpleName().concat(".creditoPorEmpleado.empleados.clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Movimientos")) {
                    camposOrden = new String[]{MovNomConcep.class.getSimpleName().concat(".empleado.clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Comprobante")) {
                    camposOrden = new String[]{CFDIEmpleado.class.getSimpleName().concat(".plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave")};
                } else if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_CFDIEmpleado")) {
                    camposOrden = new String[]{CFDIEmpleado.class.getSimpleName().concat(".plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave")};
                }
            } else {
////            List<String> agregaOrden = new ArrayList<String>();
////            agregaOrden.addAll(Arrays.asList(camposOrden));
////            boolean band = false;
////            for (i = 0; i < agregaOrden.size(); i++) {
////                if (PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave").equalsIgnoreCase(agregaOrden.get(i))) {
////                } else if (MovNomConcep.class.getSimpleName().concat(".empleado.clave").equalsIgnoreCase(agregaOrden.get(i))) {
////                }
////            }
////            if (nombreFuente.equalsIgnoreCase("FuenteDatos_Empleados")) {
////                camposOrden = new String[]{PlazasPorEmpleadosMov.class.getSimpleName().concat(".plazasPorEmpleado.empleados.clave")};
////            } else {
////                camposOrden = new String[]{MovNomConcep.class.getSimpleName().concat(".empleado.clave")};
////            }
            }
            construyeQuerie.mapeaTablasCampo(camposOrden);
            builder.append(construyeQuerie.construyeOrderBy(camposOrden, ordenado));
            if (!nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Comprobante")) {
                builder.replace(0, "SELECT".length(), "SELECT DISTINCT ");
            }
//            System.out.println("Query " + builder);
            resultados = (List<Object>) ejecutaQueryList(builder.toString(), true, construyeQuerie.getListParametros(), construyeQuerie.getParametrosQuery(), 0);
            resultados = (resultados == null ? new ArrayList<Object>() : resultados);
            if (resultados.isEmpty()) {
                mensajeResultado.setResultado(resultados);
                return mensajeResultado;
            }

            if (isMov2Meses) {
                construyeQuerie.getParametrosQuery().put("mes", meses);
                construyeQuerie.getListParametros().add("mes");
            }

            datosFormulas = construyeQuerie.getListDatosFormula();
            Object[] valores;
            if (datosFormulas.size() > 0) {
                boolean usaFormula = false, usaDatoCalculo = false;
                for (i = 0; i < datosFormulas.size(); i++) {
                    if (datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.DATOCALCULO | datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.DATOCALCULOSINPARAMETRO) {
                        usaDatoCalculo = true;
                    } else if (datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.FORMULA || datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.DATOREMPLAZO) {
                        usaFormula = true;
                    }
                }
                Object[] valoresResultado;
                int pos = 0;
                plazasPorEmpleadosMov = (plazasPorEmpleadosMov == null ? new ArrayList<PlazasPorEmpleadosMov>() : plazasPorEmpleadosMov);
                if (usaDatoCalculo) {
                    for (i = 0; i < datosFormulas.size(); i++) {
                        if (datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.DATOCALCULO | datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.DATOCALCULOSINPARAMETRO) {
                            Object dato = null;
                            if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados") | nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Movimientos")) {
                                pos = 1;
                            }
                            List<Object> resultCalculo;
                            boolean isValorMovParametro = false;
                            if ("NetoAPagar_Path".equalsIgnoreCase(datosFormulas.get(i).getExpresion())) {
                                resultCalculo = calculaNetoAPagar(datosFormulas.get(i), camposWhereExtras);
                            } else {
                                String pathCampoMostrar = propertieFuente.getProperty(datosFormulas.get(i).getExpresion());
                                if (pathCampoMostrar.startsWith(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
                                    isValorMovParametro = true;
                                }
                                resultCalculo = construyeConsultaDatosEspeciales(datosFormulas.get(i).getExpresion(), datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.DATOCALCULOSINPARAMETRO ? "" : valoresDatosEspeciales[datosFormulas.get(i).getPosicion() - pos], camposWhereExtras);
                            }
                            for (k = 0; k < resultados.size(); k++) {
                                valoresResultado = (Object[]) resultados.get(k);
                                for (j = 0; j < resultCalculo.size(); j++) {
                                    if (isValorMovParametro) {
                                        valores = (Object[]) resultCalculo.get(j);
                                        dato = valores[1];
                                        break;
                                    } else {
                                        valores = (Object[]) resultCalculo.get(j);
                                        if (valores[0].equals(valoresResultado[0].toString())) {
                                            dato = valores[1];
                                            break;
                                        }
                                    }
                                }
                                if (dato == null) {
                                    dato = 0.0;
                                }
                                valoresResultado[datosFormulas.get(i).getPosicion()] = dato;
                                resultados.set(k, valoresResultado);
                                dato = null;
                            }
                            ///DatoCampoConsulta datoCalculadoStr = construyeQuerie.constuyeConsultaCampoEspecial(datosFormulas.get(i).getExpresion(), valoresDatosEspeciales.length == 0 ? "" : valoresDatosEspeciales[datosFormulas.get(i).getPosicion() - 1]);
                            ////valores[datosFormulas.get(i).getPosicion()] = obtenerValorFormula(datosFormulas.get(i).getExpresion());
                        }
                    }
                }
                if (usaFormula) {
                    if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) {

////            periodosNomina = new PeriodosNomina();
////            periodosNomina.setFechaInicial(rangoFechas[0]);
////            periodosNomina.setFechaFinal(rangoFechas[1]);
////            periodosNomina.setFechaCierre(rangoFechas[1]);
////            periodosNomina.setFechaPago(rangoFechas[1]);
                        SimpleDateFormat sdf;
                        String claveTempNomina = "", claveEmplTemp = "", expresion;
                        valoresDatosEspecialesFormula = "";
                        for (j = 0; j < resultados.size(); j++) {
                            valores = (Object[]) resultados.get(j);
                            for (i = 0; i < datosFormulas.size(); i++) {
                                if (datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.FORMULA) {
                                    if (nombreFuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados") /*| nombreFuente.equalsIgnoreCase("FuenteDatos_Movimientos")*/) {
                                        pos = 1;
                                    }
                                    if (valoresDatosEspeciales.length > 0) {
                                        valoresDatosEspecialesFormula = valoresDatosEspeciales[datosFormulas.get(i).getPosicion() - pos];
                                    }
                                    for (k = 0; k < plazasPorEmpleadosMov.size(); k++) {
                                        if (plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getEmpleados().getClave().equalsIgnoreCase(valores[0].toString())) {
                                            expresion = buscaValorFuente(datosFormulas.get(i).getExpresion(), "_TipoDato");
                                            if (expresion.equalsIgnoreCase("Edad")) {
                                                Date edad = plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getEmpleados().getFechaNacimiento();
                                                valores[datosFormulas.get(i).getPosicion()] = edad == null ? 0 : calcularEdad(edad);
                                                break;
                                            } else if (expresion.equalsIgnoreCase("Cumple")) {
                                                Date edad = plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getEmpleados().getFechaNacimiento();
                                                sdf = new SimpleDateFormat("dd/MMM");
                                                valores[datosFormulas.get(i).getPosicion()] = edad == null ? "" : sdf.format(edad).toUpperCase();
                                                break;
                                            } else if (expresion.equalsIgnoreCase("Antiguedad")) {
                                                Date age = plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getFechaPrestaciones();
                                                valores[datosFormulas.get(i).getPosicion()] = age == null ? 0 : calcularAntiguedadExacta(age, TipoAntiguedad.ANTIGUEDADENTERO);
                                                break;
                                            } else if (expresion.equalsIgnoreCase("SalarioMin")) {
                                                Character emplZonaGeog = plazasPorEmpleadosMov.get(k).getZonaGeografica() == ZonaGeografica.ZonaGeograficaA ? 'A' : plazasPorEmpleadosMov.get(k).getZonaGeografica() == ZonaGeografica.ZonaGeograficaB ? 'B' : 'C';
                                                valores[datosFormulas.get(i).getPosicion()] = buscaSalarioPorZona(emplZonaGeog).getSalario();
                                                break;
                                            } else if (expresion.equalsIgnoreCase("AntiguedadExacta")) {
                                                valores[datosFormulas.get(i).getPosicion()] = calcularAntiguedadExacta(plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.ANTIGUEDADEXACTA);
                                                break;
                                            } else if (expresion.equalsIgnoreCase("PorcionAntiguedad")) {
                                                valores[datosFormulas.get(i).getPosicion()] = calcularAntiguedadExacta(plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.PORCIONANTIGUEDAD);
                                                break;
                                            } else if (expresion.equalsIgnoreCase("PorcionDias")) {
                                                valores[datosFormulas.get(i).getPosicion()] = calcularAntiguedadExacta(plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getFechaPrestaciones(), TipoAntiguedad.PORCIONDIAS);
                                                break;
                                            } else {
                                                if (!claveEmplTemp.equalsIgnoreCase(plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getEmpleados().getClave())) {
                                                    claveEmplTemp = plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getEmpleados().getClave();
                                                    valoresConceptosEmpleados.clear();
                                                    if (periodosNomina != null) {
                                                        inicializaValoresPeriodoNomina(periodosNomina);//JSA11
                                                    }
                                                }
                                                if (periodosNomina == null | !claveTempNomina.equalsIgnoreCase(plazasPorEmpleadosMov.get(k).getTipoNomina().getClave())) {//JSA11
                                                    claveTempNomina = plazasPorEmpleadosMov.get(k).getTipoNomina().getClave();
                                                    strQuery.delete(0, strQuery.length()).append("Select p ");
                                                    strQuery.append("From PeriodosNomina p inner join p.tipoNomina  t inner join p.tipoCorrida c ");
                                                    strQuery.append("Where :fecha BETWEEN p.fechaInicial AND  p.fechaFinal ");
                                                    strQuery.append("and t.clave = :claveTipoNomina and c.clave = :claveTipoCorrida ");
                                                    periodosNomina = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), new String[]{"fecha", "claveTipoNomina", "claveTipoCorrida"},
                                                            new Object[]{rangoFechas[1], plazasPorEmpleadosMov.get(k).getTipoNomina().getClave(), claveTipoCorrida});
                                                    inicializaValoresPeriodoNomina(periodosNomina);//JSA11

                                                }
                                                valoresConceptosEmpleados.putAll(valoresConceptosGlobales);
                                                cargarVariablesGlobalesEmpleadoPorPlaza(plazasPorEmpleadosMov.get(k), isTablaFactorIntegracion, isTablaZonaSalarial, TipoSueldos.SUELDODIARIOFINAL, null, null, null, null);
                                                valores[datosFormulas.get(i).getPosicion()] = obtenerValorFormula(expresion);
                                                break;
                                            }
                                        }
                                    }

                                } else if (datosFormulas.get(i).getNodoConsulta() == TipoNodoConsulta.DATOREMPLAZO) {
                                    for (k = 0; k < plazasPorEmpleadosMov.size(); k++) {
                                        if (plazasPorEmpleadosMov.get(k).getPlazasPorEmpleado().getEmpleados().getClave().equalsIgnoreCase(valores[0].toString())) {
                                            valores[datosFormulas.get(i).getPosicion()] = plazasPorEmpleadosMov.get(k).getImporte();
                                            break;
                                        }
                                    }
                                }

                            }
                            resultados.set(j, valores);
                        }
                    }
                }
            }
            Object[] valoresNew;
            List<Object[]> resultadoNew = new ArrayList<Object[]>();
            for (i = 0; i < resultados.size(); i++) {
                valores = (Object[]) resultados.get(i);
                valoresNew = new Object[camposMostrar.length - 1];
                for (j = 0; j < valores.length; j++) {
                    if (j > 0) {
                        valoresNew[j - 1] = valores[j];
                    }
                }
                resultadoNew.add(valoresNew);
            }

            if (resultadoNew.size() > 1) {
                i = 0;
                int contEx = 0, total = resultadoNew.size();
                boolean existe;
                Object[] tmp;
                String valor1, valor2;
                while (i < resultadoNew.size()) {
                    valores = (Object[]) resultadoNew.get(i);
                    j = i + 1;
                    if (j < resultadoNew.size()) {
                        while (j < resultadoNew.size()) {
                            tmp = (Object[]) resultadoNew.get(j);
                            existe = true;
                            for (k = 0; k < valores.length; k++) {
                                if (datoEsNumerico(valores[k] == null ? String.class : valores[k].getClass())) {
                                    valor1 = new DecimalFormat("##.###").format(valores[k]);
                                } else {
                                    valor1 = valores[k] == null ? "" : valores[k].toString().replace("0", "").replace(".", "").length() == 0 ? "0.0" : valores[k].toString();
                                }
                                if (datoEsNumerico(tmp[k] == null ? String.class : tmp[k].getClass())) {
                                    valor2 = new DecimalFormat("##.###").format(tmp[k]);
                                } else {
                                    valor2 = tmp[k] == null ? "" : tmp[k].toString().replace("0", "").replace(".", "").length() == 0 ? "0.0" : tmp[k].toString();
                                }
                                if (!valor1.equalsIgnoreCase(valor2.toString())) {
                                    existe = false;
                                    break;
                                }
                            }
                            if (existe) {
                                resultadoNew.remove(j);
                                contEx++;
                            } else {
                                j++;
                            }
                        }
                    }
                    i++;
                }
//            System.out.println(" cont elim " + contEx + " resultado " + resultadoNew.size() + " total " + total);
            }

            variablesGlobalesANull();
            mensajeResultado.setResultado(resultadoNew);
        } catch (Exception exception) {
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exception.getClass()));
            mensajeResultado.setError(exception.getMessage());
            mensajeResultado.setResultado(null);
        }
//        System.out.println("tiempo busquedaQueryConsultaEmpleados " + Calendar.getInstance().getTime());
        return mensajeResultado;
    }

    private boolean datoEsNumerico(Class tipoDato) {
        if (tipoDato.isPrimitive()) {
            if (tipoDato.equals(int.class) | tipoDato.equals(long.class) | tipoDato.equals(byte.class)
                    | tipoDato.equals(short.class) | tipoDato.equals(float.class) | tipoDato.equals(double.class)) {
                return true;
            }
        } else if (tipoDato.equals(Integer.class) | tipoDato.equals(Long.class) | tipoDato.equals(Byte.class)
                | tipoDato.equals(Short.class) | tipoDato.equals(Float.class) | tipoDato.equals(Double.class)) {
            return true;
        }
        return false;
    }

    private List<Object> calculaNetoAPagar(DatosEspeciales datoEspecial, String[] camposWhereExtras) {
        List<Object> resultados = new ArrayList<Object>();
        String pathCampoMostrar = propertieFuente.getProperty(datoEspecial.getExpresion());
        if (pathCampoMostrar == null) {
            pathCampoMostrar = "TotalPercepcion_Path-TotalDeduccion_Path";
        } else if (pathCampoMostrar.trim().isEmpty()) {
            pathCampoMostrar = "TotalPercepcion_Path-TotalDeduccion_Path";
        }
        String[] variables = obtieneVariablesFormula(pathCampoMostrar);
        List<Object> resultadoVariables = new ArrayList<Object>();
        if (propertieFuente != null) {
            List<String> nombreVariables = new ArrayList<String>();
            Object[] valor;
            int cantidad = 0;
            for (String var : variables) {
                if (propertieFuente.containsKey(var)) {
                    nombreVariables.add(var);
                    resultadoVariables.add(construyeConsultaDatosEspeciales(var, "", camposWhereExtras));
                }
            }
            String var1 = nombreVariables.get(0), var2 = nombreVariables.get(1);
            List<Object> valores1 = (List<Object>) resultadoVariables.get(0), valores2 = (List<Object>) resultadoVariables.get(1);
            int i, j, pos = 0;
            if (valores1.size() == valores2.size()) {
                cantidad = valores1.size();
                for (j = 0; j < cantidad; j++) {
                    compEjec.addVariableExtraNro(var1.toUpperCase());
                    valor = (Object[]) valores1.get(j);
                    valoresConceptosEmpleados.put(var1.toUpperCase(), valor.length > 1 ? valor[1] : valor[0]);
                    compEjec.addVariableExtraNro(var2.toUpperCase());
                    valor = (Object[]) valores2.get(j);
                    valoresConceptosEmpleados.put(var2.toUpperCase(), valor.length > 1 ? valor[1] : valor[0]);
                    valor[valor.length > 1 ? 1 : 0] = ejecutaFormula(pathCampoMostrar);
                    resultados.add(valor);
                }
            } else {
                if (valores1.size() < valores2.size()) {
                    List<Object> temp = (List<Object>) resultadoVariables.get(0);
                    String strTemp = var1;
                    var1 = var2;
                    var2 = strTemp;
                    valores1 = valores2;
                    valores2 = temp;
                }
                boolean usaClave = false;
                Object value = 0;
                Object[] valor2 = null;
                for (i = 0; i < valores1.size(); i++) {
                    compEjec.addVariableExtraNro(var1.toUpperCase());
                    valor = (Object[]) valores1.get(i);
                    if (valor.length > 1) {
                        usaClave = true;
                    }
                    valoresConceptosEmpleados.put(var1.toUpperCase(), valor.length > 1 ? valor[1] : valor[0]);
                    value = 0;
                    j = 0;
                    while (j < valores2.size()) {
                        valor2 = (Object[]) valores2.get(j);
                        if (usaClave) {
                            if (valor[0].equals(valor2[0])) {
                                value = valor2[1];
                                valores2.remove(j);
                                break;
                            } else {
                                j++;
                            }
                        } else {
                            j++;
                        }
                    }
                    compEjec.addVariableExtraNro(var2.toUpperCase());
                    valoresConceptosEmpleados.put(var2.toUpperCase(), value);
                    valor[valor.length > 1 ? 1 : 0] = ejecutaFormula(pathCampoMostrar);
                    resultados.add(valor);
                }
            }
        }
        return resultados;
    }

    public String buscaValorFuente(String campo, String complemento) {
        if (propertieFuente == null) {
            return campo;
        }
        if (propertieFuente.containsKey(campo.concat(complemento == null ? "" : complemento))) {
            return campo;
        } else if (propertieFuente.containsKey(campo)) {
            campo = propertieFuente.getProperty(campo);
            campo = buscaValorFuente(campo, complemento);
        }
        return campo;
    }

    private void cargaDatosCalculoIMSS(Date fechaFinal) {
        /**
         * ***********************************IMSS******************************************************************
         */
        if (valoresConceptosGlobales == null) {
            valoresConceptosGlobales = new HashMap();
        }
        configuracionIMSS = (ConfiguracionIMSS) ejecutaQueryUnico("from ConfiguracionIMSS where fechaAplica = ( select max(fechaAplica) from ConfiguracionIMSS where fechaAplica <= :fecha ) ", new String[]{"fecha"}, new Object[]{fechaFinal});//JSA01
        if (mensajeResultado.getNoError() == -100 || configuracionIMSS == null) {
            configuracionIMSS = (ConfiguracionIMSS) ejecutaQueryUnico("from ConfiguracionIMSS where fechaAplica = ( select min(fechaAplica) from ConfiguracionIMSS where fechaAplica >= :fecha ) ", new String[]{"fecha"}, new Object[]{fechaFinal});
        }
        valoresConceptosGlobales.put("TasaExcedenteEmp".toUpperCase(), configuracionIMSS.getTasaEspecieEnfermeMater());
        valoresConceptosGlobales.put("TasaGtosPensEmp".toUpperCase(), configuracionIMSS.getTasaGastosPension());
        valoresConceptosGlobales.put("TasaPrestDinEmp".toUpperCase(), configuracionIMSS.getTasaDineEnfermeMater());
        valoresConceptosGlobales.put("TasaInvYVidaEmp".toUpperCase(), configuracionIMSS.getTasaInvalidezVida());
        valoresConceptosGlobales.put("TasaCesYVejezEmp".toUpperCase(), configuracionIMSS.getTasaCesantiaVejez());

        valoresConceptosGlobales.put("TasaInfonavit".toUpperCase(), configuracionIMSS.getTasaAportacionInfonavitPatron());
        valoresConceptosGlobales.put("TasaCesYVejezPat".toUpperCase(), configuracionIMSS.getTasaCesanVejezPatron());
        valoresConceptosGlobales.put("TasaFijaPatron".toUpperCase(), configuracionIMSS.getTasaFijaPatron());
        valoresConceptosGlobales.put("TasaExcedentePat".toUpperCase(), configuracionIMSS.getTasaExcedentePatron());
        valoresConceptosGlobales.put("TasaGuarderiasPat".toUpperCase(), configuracionIMSS.getTasaGuarderiaPatron());
        valoresConceptosGlobales.put("TasaInvYVidaPat".toUpperCase(), configuracionIMSS.getTasaInvaliVidaPatron());
        valoresConceptosGlobales.put("TasaGtosPensPat".toUpperCase(), configuracionIMSS.getTasaGastosPensPatron());
        valoresConceptosGlobales.put("TasaRetiro".toUpperCase(), configuracionIMSS.getTasaAportacionRetiroPatron());
        valoresConceptosGlobales.put("TasaRiesgoTrabajoPat".toUpperCase(), configuracionIMSS.getTasaRiesgosPatron());

        valoresConceptosGlobales.put("TopeEnfermedadYMat".toUpperCase(), configuracionIMSS.getTopeEnfermedadMaternidad());
        valoresConceptosGlobales.put("TopeRiesgoTrabajoGuarderia".toUpperCase(), configuracionIMSS.getTopeRiesgoTrabajoGuarderias());
        valoresConceptosGlobales.put("TopeInvalidezYVida".toUpperCase(), configuracionIMSS.getTopeCesanVejezInvaliVida());
        valoresConceptosGlobales.put("TopeRetiro".toUpperCase(), configuracionIMSS.getTopeRetiro());
        valoresConceptosGlobales.put("TopeCuotaInfonavit".toUpperCase(), configuracionIMSS.getTopeInfonavit());
//            valoresConceptos.put("TasaRetiro".toUpperCase(), configuracionIMSS.getSalarioRetiro());
//            valoresConceptos.put("TasaRetiro".toUpperCase(), configuracionIMSS.getSalarioRiesgoTrabajoGuarderias());
        //strQuery = null;

    }

    private Object obtenerValorFormula(String formula) {
        Object valor = "";
        Object[] valores;
        String[] variables = formula.split("\\|");
        String tipoDato = "", variable;
        StringBuilder variablesFormula = new StringBuilder();
        int contVariables = 0;
        boolean ejecutaCompilador = false, tieneVariable = false;
        TipoClasificacionFormula tcf;
        ////TipoAcumulado tipoAcumulado;

        for (String str : variables) {
            String funcion = str;
            if (str.indexOf('(') != -1) {
                str = str.substring(0, str.indexOf('('));
            }
            if (propertieFuente.containsKey(str)) {
                contVariables++;
                if (propertieFuente.containsKey(str.concat("_TipoDato"))) {
                    variable = str;
                } else {
                    variable = propertieFuente.getProperty(str);
                }
                tieneVariable = true;
                tcf = TipoClasificacionFormula.getEnum(propertieFuente.getProperty(variable.concat("_TipoDato")));
//////                if (propertieFuente.getProperty(variable.concat("_Tipo")).equalsIgnoreCase("N") | propertieFuente.getProperty(variable.concat("_Tipo")).equalsIgnoreCase("B")) {
//////                    tipoDato = propertieFuente.getProperty(variable.concat("_Tipo"));
//////                    ejecutaCompilador = true;
//////                } else {
//////                    ejecutaCompilador = false;
//////                }

                variablesFormula.append(variable.toUpperCase());

                if (tcf == TipoClasificacionFormula.DATOCALCULO | tcf == TipoClasificacionFormula.DATOPERIODO | tcf == TipoClasificacionFormula.DATOMENSUAL | tcf == TipoClasificacionFormula.DATOBIMESTRAL | tcf == TipoClasificacionFormula.DATOANUAL) {
                    // | tcf == TipoClasificacionFormula.DATOPERIODO
                    if (!valoresConceptosEmpleados.containsKey(variable.toUpperCase())) {
                        valoresConceptosEmpleados.put(variable.toUpperCase(), "");
                    }
                    /**
                     * Pendiente*
                     */
//////                    else {
//////                        valor = valoresConceptosEmpleados.get(variable.toUpperCase());
//////                    }
//////                    valor = (valor == null ? "" : valor);
//////                    if (valor.toString().length() == 0) {
                    buscaVaricablesCalcular(variable, tcf);
//////                    }
//////                    valor = "";
                    /**
                     * *****
                     */
                } else if (tcf == TipoClasificacionFormula.DATOFUNCION) {
                    Calendar fecha = Calendar.getInstance();
                    fecha.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
                    variablesTipoFuncion(funcion.toUpperCase(), tcf, fecha);
                    funcion = funcion.replace("(", "").replace("'", "").replace(",", "").replace(")", "");
                    variablesFormula.delete(0, variablesFormula.length());
                    variablesFormula.append(funcion.toUpperCase());

                } else if (tcf == TipoClasificacionFormula.DATOTABLA) {
                    funcion = funcion.replace("(", "").replace("'", "").replace(",", "").replace(")", "");
                    variablesTipoTabla(funcion);
                }
            } else {
                contVariables++;
                variablesFormula.append(str.toUpperCase());
            }
        }
        if (contVariables == 1 & tieneVariable) {
            ejecutaCompilador = false;
        } else if (contVariables > 1) {
            ejecutaCompilador = true;
        }
        if (contVariables > 0 & ejecutaCompilador) {
            valores = compilador.compila(variablesFormula.toString(), new Reg_Polish[]{}, "", false);
            if ((Boolean) valores[2]) {
                valor = 0.0;
                valor = compilador.calcula((Reg_Polish[]) valores[0], valoresConceptosEmpleados, (Double) valor);
                if (tipoDato.equalsIgnoreCase("B")) {
                    valor = (Double) valor == 0.0 ? false : true;
                }
            }
        } else if (contVariables == 1 & isDoubleString(variablesFormula.toString())) {
            valor = Double.parseDouble(variablesFormula.toString());
        } else {
            valor = (Object) valoresConceptosEmpleados.get(variablesFormula.toString());
        }
        return valor;
    }

    /**
     * Busca periodos que estan en determinado mes
     *
     * @param cantidadDeMeses especifica rango de meses a buscar positivo suma
     * mes y negativo resta mes
     * @param fechaPeriodoNomina fecha extra el mes del periodo de nomina a
     * buscar
     * @see #buscarPeriodosPorRangoMeses
     */
    private Object periodioAcumuladoPorRangoMeses(TipoClasificacionFormula tipoAcumulado, Calendar fechaPeriodoNomina, String variable) {
        Object valor = 0.0;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT p FROM ").append(PeriodosNomina.class.getSimpleName()).append(" p INNER JOIN p.tipoNomina t WHERE t.clave = :claveTipoNomina AND p.año = :yearPeriodo ");
            Calendar fechaRango = Calendar.getInstance();
            fechaRango.setTime(fechaPeriodoNomina.getTime());
            int mesIni = -1, mesFin = -1;
            if (tipoAcumulado == TipoClasificacionFormula.DATOPERIODO) {
                strQuery.append("AND p.clave = (SELECT periodo.clave FROM ").append(PeriodosNomina.class.getSimpleName()).append(" periodo INNER JOIN periodo.tipoNomina nomina ");
                strQuery.append("WHERE (:fechaActual BETWEEN periodo.fechaInicial AND periodo.fechaFinal + 1) AND nomina.clave = :claveTipoNomina)");
                camposParametro.add("fechaActual");
                valoresParametro.add(fechaPeriodoNomina.getTime());
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
                if ((fechaPeriodoNomina.get(Calendar.MONTH) + 1) % 2 == 0) {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH);
                    fechaRango.set(Calendar.MONTH, fechaPeriodoNomina.get(Calendar.MONTH) - 1);
                } else {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 2;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                }

            } else if (tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {
                mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOANUAL) {
                strQuery.append("AND p.fechaInicial >= :fechaInicial AND p.fechaFinal <= :fechaFinal OR (:fechaFinal BETWEEN p.fechaInicial AND p.fechaFinal AND t.clave = :claveTipoNomina AND p.año = :yearPeriodo) ");
                camposParametro.add("fechaInicial");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial));
                fecha.set(Calendar.DATE, 1);
                fecha.set(Calendar.MONTH, Calendar.JANUARY);
                valoresParametro.add(fecha.getTime());
                camposParametro.add("fechaFinal");
                fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.getTime());
            }

            camposParametro.add("claveTipoNomina");
            camposParametro.add("yearPeriodo");
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(fechaPeriodoNomina.get(Calendar.YEAR));

            if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL | tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {
                if (fechaRango.get(Calendar.MONTH) == Calendar.JANUARY) {
                    strQuery.append("AND (MONTH(p.AcumularAMes) <= :mesPeriodoRango AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
                    strQuery.append("OR (t.clave = :claveTipoNomina AND p.año = :yearPeriodo - 1 AND MONTH(p.AcumularAMes) = :mesPeriodo AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
                } else {
                    strQuery.append("AND (MONTH(p.AcumularAMes) >= :mesPeriodo AND MONTH(p.AcumularAMes) <= :mesPeriodoRango) AND p.año = :yearPeriodo ");
                }
                camposParametro.add("mesPeriodo");
                camposParametro.add("mesPeriodoRango");
                valoresParametro.add(mesIni);
                valoresParametro.add(mesFin);
                camposParametro.add("yearPeriodo");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.get(Calendar.YEAR));
            }
            /*
             * PENDIENTE CHECAR
             */
            List<PeriodosNomina> periodosNominas = (List<PeriodosNomina>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            periodosNominas = (periodosNominas == null) ? new ArrayList<PeriodosNomina>() : periodosNominas;
            int dias = 0, diasDif = 0;
            for (PeriodosNomina p : periodosNominas) {
                if (manejaPagoDiasNaturales || manejaPagoIMSSDiasNaturales) {
                    dias = dias + (cantidadDiasEntreDosFechas(p.getFechaInicial(), p.getFechaFinal()) + 1); //+1 para contar el dia actual
                } else {
                    dias = dias + p.getTipoNomina().getPeriodicidad().getDias().intValue();
                }
            }
//            System.out.println("---------------------------- Tipo acumulado " + tipoAcumulado);
//            System.out.println("fechaPeriodoNomina " + fechaPeriodoNomina.getTime() + " dias " + dias);
            if (!periodosNominas.isEmpty()) {
                Calendar fechaInicial = Calendar.getInstance(), fechaIMSS = Calendar.getInstance(), fechaBaja = Calendar.getInstance();
                fechaInicial.setTime((Date) valoresConceptosEmpleados.get("FechaAlta".toUpperCase()));
                fechaIMSS.setTime((Date) valoresConceptosEmpleados.get("FechaAltaIMSS".toUpperCase()));
                fechaBaja.setTime((Date) valoresConceptosEmpleados.get("FechaBaja".toUpperCase()));
//////                System.out.println("fechaInicial " + fechaInicial.getTime());
//////                System.out.println("fechaIMSS " + fechaIMSS.getTime());
//////                System.out.println("fechaBaja " + fechaBaja.getTime());
//////                System.out.println("periodosNominas.getFechaInicial() " + periodosNominas.get(0).getFechaInicial());
//////                System.out.println("periodosNominas.getFechaFinal()" + periodosNominas.get(periodosNominas.size() - 1).getFechaFinal());
                if (variable.toUpperCase().contains("DiasPago".toUpperCase())) {
                    if (fechaInicial.getTime().compareTo(periodosNominas.get(0).getFechaInicial()) > 0) {
                        diasDif += cantidadDiasEntreDosFechas(periodosNominas.get(0).getFechaInicial(), fechaInicial.getTime());
//////                        System.out.println("DiasPago diasDif " + diasDif);
                    }
                } else if (variable.toUpperCase().contains("DiasCotizados".toUpperCase())) {
                    if (fechaIMSS.getTime().compareTo(periodosNominas.get(0).getFechaInicial()) > 0) {
                        diasDif += cantidadDiasEntreDosFechas(periodosNominas.get(0).getFechaInicial(), fechaIMSS.getTime());
//////                        System.out.println("DiasCotizados diasDif " + diasDif);
                    }
                }
                if (fechaBaja.getTime().compareTo(periodosNominas.get(periodosNominas.size() - 1).getFechaFinal()) < 0) {
                    if (fechaPeriodoNomina.getTime().compareTo(fechaBaja.getTime()) < 0) {
                        diasDif += cantidadDiasEntreDosFechas(fechaPeriodoNomina.getTime(), periodosNominas.get(periodosNominas.size() - 1).getFechaFinal());
                    } else {
                        diasDif += cantidadDiasEntreDosFechas(fechaBaja.getTime(), periodosNominas.get(periodosNominas.size() - 1).getFechaFinal());
                    }
//////                    System.out.println("fechaFinal Final diasDif " + diasDif);
                } else if (fechaPeriodoNomina.getTime().compareTo(periodosNominas.get(periodosNominas.size() - 1).getFechaFinal()) < 0) {
                    diasDif += cantidadDiasEntreDosFechas(fechaPeriodoNomina.getTime(), periodosNominas.get(periodosNominas.size() - 1).getFechaFinal());
//////                    System.out.println("fechaPeriodoNomina Final diasDif " + diasDif);
                }
            }
            valor = dias - diasDif;
//////            System.out.println("Valor " + valor);
            ///valor = (Object) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(62);
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(62);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarPeriodosPorRangoMeses()1_Error: ").append(ex));
        }
        camposParametro = null;
        valoresParametro = null;
        //strQuery = null;
        return valor;
    }

    /**
     * Busca periodos que estan en determinado mes
     *
     * @param cantidadDeMeses especifica rango de meses a buscar positivo suma
     * mes y negativo resta mes
     * @param fechaPeriodoNomina fecha extra el mes del periodo de nomina a
     * buscar
     * @see #asistenciasAcumuladoPorRangoMeses
     */
    private Object asistenciasAcumuladoPorRangoMeses(TipoClasificacionFormula tipoAcumulado, Calendar fechaPeriodoNomina, Object[] claveExcepcion) {
        Object valor = 0.0;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT COUNT(a) FROM ").append(Asistencias.class.getSimpleName()).append(" a INNER JOIN a.excepciones ex INNER JOIN a.empleados em INNER JOIN a.razonesSociales rs INNER JOIN a.tipoNomina t ");
            strQuery.append("INNER JOIN a.periodosNomina p Left Outer Join a.centroDeCosto cc WHERE em.clave = :claveEmp AND rs.clave = :razonSocial AND t.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida AND ex.clave IN (:claveExcepcion) ");
            String cc = valoresConceptosEmpleados.get(CentroDeCosto.class.getSimpleName().toUpperCase()) == null ? "" : valoresConceptosEmpleados.get(CentroDeCosto.class.getSimpleName().toUpperCase().toUpperCase()).toString();
            if (cc.trim().length() > 0) {
                strQuery.append("AND cc.clave = :CentroDeCosto ");
                camposParametro.add("CentroDeCosto");
                valoresParametro.add(valoresConceptosEmpleados.get("CentroDeCosto".toUpperCase()));
            }
            Calendar fechaRango = Calendar.getInstance();
            fechaRango.setTime(fechaPeriodoNomina.getTime());
            int mesIni = -1, mesFin = -1;
            if (tipoAcumulado == TipoClasificacionFormula.DATOPERIODO) {
                strQuery.append("AND a.fecha BETWEEN :fechaInicial AND :fechaFinal ");
                camposParametro.add("fechaInicial");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial));
                valoresParametro.add(fecha.getTime());
                camposParametro.add("fechaFinal");
                fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.getTime());
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
                if ((fechaPeriodoNomina.get(Calendar.MONTH) + 1) % 2 == 0) {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH);
                    fechaRango.set(Calendar.MONTH, fechaPeriodoNomina.get(Calendar.MONTH) - 1);
                } else {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 2;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                }

            } else if (tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {
                mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOANUAL) {
                strQuery.append("AND a.fecha BETWEEN :fechaInicial AND :fechaFinal ");
                camposParametro.add("fechaInicial");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial));
                fecha.set(Calendar.DATE, 1);
                fecha.set(Calendar.MONTH, Calendar.JANUARY);
                valoresParametro.add(fecha.getTime());
                valoresParametro.add(fecha.getTime());
                camposParametro.add("fechaFinal");
                fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.getTime());
            }
            camposParametro.add("claveEmp");
            camposParametro.add("razonSocial");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("claveTipoCorrida");
            camposParametro.add("claveExcepcion");
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
            valoresParametro.add(claveExcepcion);

            if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL | tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {
                if (fechaRango.get(Calendar.MONTH) == Calendar.JANUARY) {
                    strQuery.append("AND (MONTH(p.AcumularAMes) <= :mesPeriodoRango AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
                    strQuery.append("OR (t.clave = :claveTipoNomina AND p.año = :yearPeriodo - 1 AND MONTH(p.AcumularAMes) = :mesPeriodo AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
                } else {
                    strQuery.append("AND (MONTH(p.AcumularAMes) >= :mesPeriodo AND MONTH(p.AcumularAMes) <= :mesPeriodoRango) AND p.año = :yearPeriodo ");
                }
                camposParametro.add("mesPeriodo");
                camposParametro.add("mesPeriodoRango");
                valoresParametro.add(mesIni);
                valoresParametro.add(mesFin);
                camposParametro.add("yearPeriodo");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.get(Calendar.YEAR));
            }

            valor = (Object) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(62);
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(62);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("asistenciasAcumuladoPorRangoMeses()1_Error: ").append(ex));
        }
        camposParametro = null;
        valoresParametro = null;
        //strQuery = null;
        return valor;
    }

    /**
     * Busca periodos que estan en determinado mes
     *
     * @param cantidadDeMeses especifica rango de meses a buscar positivo suma
     * mes y negativo resta mes
     * @param fechaPeriodoNomina fecha extra el mes del periodo de nomina a
     * buscar
     * @see #isrAcumuladoPorRangoMeses
     */
    private Object isrAcumuladoPorRangoMeses(TipoClasificacionFormula tipoAcumulado, Calendar fechaPeriodoNomina, String campo) {
        Object valor = 0.0;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            strQuery.delete(0, strQuery.length()).append("SELECT CASE WHEN (COUNT(isr) = 0) THEN 0.0 ELSE SUM(isr.").append(campo).append(") END FROM ").append(CalculoISR.class.getSimpleName());//JSA23
            strQuery.append(" isr INNER JOIN isr.movNomConcep mov INNER JOIN mov.empleado em INNER JOIN mov.razonesSociales rs INNER JOIN mov.concepNomDefi con ");
            strQuery.append(" INNER JOIN mov.tipoCorrida tipoCorri ");
            if (usaFiniquitos) {
                if (camposFiniquitos != null) {
                    strQuery.append("LEFT OUTER JOIN mov.finiqLiquidCncNom fc LEFT OUTER JOIN fc.finiquitosLiquidacion fl ");
                }
            }
            strQuery.append(" INNER JOIN mov.periodosNomina p INNER JOIN mov.tipoNomina t Left Outer Join mov.centroDeCosto cc WHERE em.clave = :claveEmp AND rs.clave = :razonSocial ");
            camposParametro.add("claveEmp");
            camposParametro.add("razonSocial");
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
            String cc = valoresConceptosEmpleados.get(CentroDeCosto.class.getSimpleName().toUpperCase()) == null ? "" : valoresConceptosEmpleados.get(CentroDeCosto.class.getSimpleName().toUpperCase().toUpperCase()).toString();
            if (cc.trim().length() > 0) {
                strQuery.append("AND cc.clave = :CentroDeCosto ");
                camposParametro.add("CentroDeCosto");
                valoresParametro.add(valoresConceptosEmpleados.get("CentroDeCosto".toUpperCase()));
            }
            if (valoresConceptosEmpleados.containsKey("TipoNomina".toUpperCase())) {
                String nomina = valoresConceptosEmpleados.get("TipoNomina".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("TipoNomina".toUpperCase()).toString();
                if (nomina.length() > 0) {
                    strQuery.append("AND t.clave = :TipoNomina ");
                    camposParametro.add("TipoNomina");
                    valoresParametro.add(nomina);
                }
            }
            if (valoresConceptosEmpleados.get("uso".toUpperCase()) != null) {
                strQuery.append(" AND mov.uso = :uso ");
                camposParametro.add("uso");
                valoresParametro.add(valoresConceptosEmpleados.get("uso".toUpperCase()));
            }

            if (valoresConceptosEmpleados.get("ImprimeListado".toUpperCase()) != null) {
                strQuery.append(" AND con.imprimirEnListadoNomina = :ImprimeListado ");
                camposParametro.add("ImprimeListado");
                valoresParametro.add(valoresConceptosEmpleados.get("ImprimeListado".toUpperCase()));
            }

            if (valoresConceptosEmpleados.get("ImprimeRecibo".toUpperCase()) != null) {
                strQuery.append(" AND con.imprimirEnReciboNomina = :ImprimeRecibo ");
                camposParametro.add("ImprimeRecibo");
                valoresParametro.add(valoresConceptosEmpleados.get("ImprimeRecibo".toUpperCase()));
            }

            if (valoresConceptosEmpleados.containsKey("ClaveTipoCorrida".toUpperCase())) {
                String corrida = valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()).toString();
                if (corrida.length() > 0) {
                    strQuery.append(" AND tipoCorri.clave = :claveTipoCorrida AND p.tipoCorrida.clave = :claveTipoCorrida ");
                    camposParametro.add("claveTipoCorrida");
                    valoresParametro.add(corrida);
                }
            }

            if (usaFiniquitos) {
                int i;
                if (camposFiniquitos != null & valoresCamposFiniquitos != null) {
                    String campoFiniq;
                    for (i = 0; i < camposFiniquitos.size(); i++) {
                        campoFiniq = getCampoFinal(camposFiniquitos.get(i));
                        strQuery.append(" AND fl.").append(campoFiniq).append(" = :parametro").append(i);
                        camposParametro.add("parametro".concat(String.valueOf(i)));
                        valoresParametro.add(valoresCamposFiniquitos.get(i));
                    }
                }
            }

            Calendar fechaRango = Calendar.getInstance();
            fechaRango.setTime(fechaPeriodoNomina.getTime());
            int mesIni = -1, mesFin = -1;
            if (tipoAcumulado == TipoClasificacionFormula.DATOPERIODO) {
                strQuery.append("AND p.clave = (SELECT periodo.clave FROM ").append(PeriodosNomina.class.getSimpleName()).append(" periodo INNER JOIN periodo.tipoNomina nomina ");
                strQuery.append("WHERE (:fechaActual BETWEEN periodo.fechaInicial AND periodo.fechaFinal + 1) AND nomina.clave = :claveTipoNomina ");
                if (valoresConceptosEmpleados.containsKey("ClaveTipoCorrida".toUpperCase())) {
                    String corrida = valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()).toString();
                    if (corrida.length() > 0) {
                        strQuery.append(" AND periodo.tipoCorrida.clave = :claveTipoCorrida ");
                    }
                }
                strQuery.append(") AND p.año = :yearPeriodo ");
                camposParametro.add("fechaActual");
                valoresParametro.add(fechaPeriodoNomina.getTime());
                camposParametro.add("claveTipoNomina");
                valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
                camposParametro.add("yearPeriodo");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.get(Calendar.YEAR));
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
                if ((fechaPeriodoNomina.get(Calendar.MONTH) + 1) % 2 == 0) {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH);
                    fechaRango.set(Calendar.MONTH, fechaPeriodoNomina.get(Calendar.MONTH) - 1);
                } else {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 2;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                }
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOANUAL) {
                strQuery.append("AND ((p.fechaInicial BETWEEN :fechaInicial AND :fechaFinal) ");
                strQuery.append("OR (p.fechaFinal BETWEEN :fechaInicial AND :fechaFinal)) ");
                camposParametro.add("fechaInicial");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial));
                fecha.set(Calendar.DATE, 1);
                fecha.set(Calendar.MONTH, Calendar.JANUARY);
                valoresParametro.add(fecha.getTime());
                camposParametro.add("fechaFinal");
                fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.getTime());
            }

            if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
//////                if (fechaRango.get(Calendar.MONTH) == Calendar.JANUARY) {
//////                    strQuery.append("AND (MONTH(p.AcumularAMes) <= :mesPeriodoRango AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
//////                    strQuery.append("OR (t.clave = :claveTipoNomina AND p.año = :yearPeriodo - 1 AND MONTH(p.AcumularAMes) = :mesPeriodo AND YEAR(p.AcumularAMes) = :yearPeriodo) ");
//////                    camposParametro.add("claveTipoNomina");
//////                    valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
//////                } else {
//////                    strQuery.append("AND (MONTH(p.AcumularAMes) >= :mesPeriodo AND MONTH(p.AcumularAMes) <= :mesPeriodoRango) AND p.año = :yearPeriodo ");
//////                }
                strQuery.append(" AND p.año = :yearPeriodo AND (mov.mes = :mesIni OR mov.mes = :mesFin) ");
                camposParametro.add("mesIni");
                camposParametro.add("mesFin");
                valoresParametro.add(mesIni);
                valoresParametro.add(mesFin);
                camposParametro.add("yearPeriodo");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.get(Calendar.YEAR));
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {
                strQuery.append(" AND p.año = :yearPeriodo AND mov.mes = :mesActual  ");
                camposParametro.add("yearPeriodo");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                valoresParametro.add(fecha.get(Calendar.YEAR));
                camposParametro.add("mesActual");
                valoresParametro.add(fecha.get(Calendar.MONTH) + 1);
            }

            valor = (Object) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(62);
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(62);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("isrAcumuladoPorRangoMeses()1_Error: ").append(ex));
        }
        camposParametro = null;
        valoresParametro = null;
        //strQuery = null;
        return valor;
    }

    private String getCampoFinal(String campoSelect) {
        String[] ruta = campoSelect.split("\\.");
        campoSelect = ruta[ruta.length - 1];
        return campoSelect;
    }

    enum TipoMostrarCampo {

        SUMA, COUNT, NORMAL, OPERACION;
    }

    /**
     * Busca periodos que estan en determinado mes
     *
     * @param cantidadDeMeses especifica rango de meses a buscar positivo suma
     * mes y negativo resta mes
     * @param fechaPeriodoNomina fecha extra el mes del periodo de nomina a
     * buscar
     * @see #movimientosAcumuladoPorRangoMeses
     */
    private Object movimientosAcumuladoPorRangoMeses(TipoClasificacionFormula tipoAcumulado, Calendar fechaPeriodoNomina, String campoMostrar, String[] camposWhere, Object[] valoresWhere, TipoMostrarCampo tmc, String campoOmitir, Object valorOmitir) {
        Object valor = 0.0;
        int i, posicionParametro = 0;
        try {
            campoOmitir = campoOmitir == null ? "" : campoOmitir;
            String tablPadre = MovNomConcep.class.getSimpleName(), path;
            String[] tablasPublicas = new String[]{tablPadre.concat(".empleado.clave"), tablPadre.concat(".periodosNomina.clave"), tablPadre.concat(".razonesSociales.clave"), tablPadre.concat(".tipoNomina.clave"),
                tablPadre.concat(".centroDeCosto.clave"), tablPadre.concat(".tipoCorrida.clave"), tablPadre.concat(".concepNomDefi.clave")};
            ConstruyeQueries cq = new ConstruyeQueries();
            String[] variables = obtieneVariablesFormula(campoMostrar);
            List<String> mapeoVariables = new ArrayList<String>();
            mapeoVariables.add(tablPadre.concat(".resultado"));
            for (String var : variables) {
                if (var.split("\\.").length > 2) {
                    mapeoVariables.add(var);
                }
            }
            if (!campoOmitir.isEmpty()) {
                mapeoVariables.add(campoOmitir);

            }
            cq.generaListaTablasMapeadas(mapeoVariables.toArray(new String[]{}), camposWhere, tablasPublicas, null);
            ////aliasTablaPadre = cq.getAliasTablaOuter().get(tablPadre).toString();
            if (usaFiniquitos) {
                if (camposFiniquitos != null) {
                    cq.mapeaTablasCampo(camposFiniquitos.toArray(new String[]{}));
                }
            }
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            camposWhere = camposWhere == null ? new String[]{} : camposWhere;
            valoresWhere = valoresWhere == null ? new String[]{} : valoresWhere;
            strQuery.delete(0, strQuery.length()).append("SELECT ");
            path = campoMostrar.substring(0, campoMostrar.lastIndexOf("."));
            if (tmc == TipoMostrarCampo.COUNT) {
                for (String var : variables) {
                    if (var.split("\\.").length > 2) {
                        path = var;
                        break;
                    }
                }
                strQuery.append("COUNT(").append(cq.getAliasTablaOuter().get(path.replace(".", "_"))).append(campoMostrar.substring(campoMostrar.lastIndexOf("."))).append(")");
            } else if (tmc == TipoMostrarCampo.SUMA) {
//////                path = cq.getAliasTablaOuter().get(path.replace(".", "_"));
//////                path = path.concat(campoMostrar.substring(campoMostrar.lastIndexOf(".")));
                strQuery.append("CASE WHEN (COUNT(").append(cq.getAliasTablaOuter().get(tablPadre)).append(") = 0 ) THEN 0.0 ELSE (SUM(");
                for (String var : variables) {
                    if (var.split("\\.").length < 2) {
                        strQuery.append(var);
                    } else {
                        path = var.substring(0, var.lastIndexOf("."));
                        path = cq.getAliasTablaOuter().get(path.replace(".", "_"));
                        ////path = path.concat(campoMostrar.substring(campoMostrar.lastIndexOf(".")));
                        strQuery.append("CASE WHEN (").append(path).append(" IS NULL) THEN 0.0 ELSE CASE WHEN(").append(path).append(var.substring(var.lastIndexOf("."))).append(" IS NULL) THEN 0.0 ELSE ");
                        strQuery.append(path).append(var.substring(var.lastIndexOf("."))).append(" END END");
                    }
                }
                ////strQuery.append("CASE WHEN (").append(path).append(" IS NULL) THEN 0.0 ELSE (").append(path).append(") END)) END ");
                strQuery.append(")) END ");
            } else {
                strQuery.append(cq.getAliasTablaOuter().get(path.replace(".", "_"))).append(campoMostrar.substring(campoMostrar.lastIndexOf(".")));
            }
            strQuery.append(" ").append(cq.construyeFromConsulta(ConstruyeQueries.LEFTJOIN));
            strQuery.append(" WHERE ").append(cq.getAliasTablaOuter().get(tablPadre.concat("_empleado"))).append(".clave = :claveEmp AND ").append(cq.getAliasTablaOuter().get(tablPadre.concat("_razonesSociales"))).append(".clave = :razonSocial ");
            camposParametro.add("claveEmp");
            camposParametro.add("razonSocial");
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
            if (valoresConceptosEmpleados.containsKey("TipoNomina".toUpperCase())) {
                String nomina = valoresConceptosEmpleados.get("TipoNomina".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("TipoNomina".toUpperCase()).toString();
                if (nomina.length() > 0) {
                    path = cq.getAliasTablaOuter().get(tablPadre.concat("_tipoNomina"));
                    strQuery.append(" AND ").append(path).append(".clave = :TipoNomina");
                    camposParametro.add("TipoNomina");
                    valoresParametro.add(nomina);
                }
            }
            if (valoresConceptosEmpleados.containsKey("ClaveTipoCorrida".toUpperCase())) {
                String corrida = valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()).toString();
                if (corrida.length() > 0) {
                    path = cq.getAliasTablaOuter().get(tablPadre.concat("_tipoCorrida"));
                    strQuery.append(" AND ").append(path).append(".clave = :TipoCorrida");
                    camposParametro.add("TipoCorrida");
                    valoresParametro.add(corrida);
                }
            }
            if (valoresConceptosEmpleados.containsKey(CentroDeCosto.class.getSimpleName().toUpperCase())) {
                String centro = valoresConceptosEmpleados.get("CentroDeCosto".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("CentroDeCosto".toUpperCase()).toString();
                if (centro.length() > 0) {
                    path = cq.getAliasTablaOuter().get(tablPadre.concat("_centroDeCosto"));
                    strQuery.append(" AND ").append(path).append(".clave = :CentroDeCosto");
                    camposParametro.add("CentroDeCosto");
                    valoresParametro.add(centro);
                }
            }
            if (valoresConceptosEmpleados.get("uso".toUpperCase()) != null) {
                path = cq.getAliasTablaOuter().get(tablPadre);
                strQuery.append(" AND ").append(path).append(".uso = :uso");
                camposParametro.add("uso");
                valoresParametro.add(valoresConceptosEmpleados.get("uso".toUpperCase()));
            }

            if (valoresConceptosEmpleados.get("ImprimeListado".toUpperCase()) != null) {
                path = cq.getAliasTablaOuter().get(tablPadre.concat("_concepNomDefi"));
                strQuery.append(" AND ").append(path).append(".imprimirEnListadoNomina = :ImprimeListado");
                camposParametro.add("ImprimeListado");
                valoresParametro.add(valoresConceptosEmpleados.get("ImprimeListado".toUpperCase()));
            }

            if (valoresConceptosEmpleados.get("ImprimeRecibo".toUpperCase()) != null) {
                path = cq.getAliasTablaOuter().get(tablPadre.concat("_concepNomDefi"));
                strQuery.append(" AND ").append(path).append(".imprimirEnReciboNomina = :ImprimeRecibo");
                camposParametro.add("ImprimeRecibo");
                valoresParametro.add(valoresConceptosEmpleados.get("ImprimeRecibo".toUpperCase()));
            }
            if (!campoOmitir.isEmpty() & valorOmitir != null) {
                //variable
                String value[] = campoOmitir.toString().split("#");
                String alias = value[0].substring(0, value[0].lastIndexOf("."));
                path = cq.getAliasTablaOuter().get(alias.replace(".", "_"));
                //path = cq.getAliasTablaOuter().get(tablPadre.concat("_concepNomDefi"));
                strQuery.append(" AND NOT ").append(path);
                strQuery.append(value[0].substring(value[0].lastIndexOf("."))).append(" ");
                if (value.length > 1) {
                    if (value[1].equalsIgnoreCase("LIKE")) {
                        strQuery.append("LIKE ");
                        strQuery.append("'%").append(valorOmitir).append("%' ");
                    } else {
                        strQuery.append(value[1]).append(":valorOmitir");
                        camposParametro.add("valorOmitir");
                        valoresParametro.add(valorOmitir);
                    }

                } else {
                    strQuery.append("= :valorOmitir");
                    camposParametro.add("valorOmitir");
                    valoresParametro.add(valorOmitir);
                }

            }

            if (usaFiniquitos) {
                if (camposFiniquitos != null & valoresCamposFiniquitos != null) {
                    for (i = 0; i < camposFiniquitos.size(); i++) {
                        path = camposFiniquitos.get(i).substring(0, camposFiniquitos.get(i).lastIndexOf("."));
                        strQuery.append(" AND ").append(cq.getAliasTablaOuter().get(path.replace(".", "_"))).append(camposFiniquitos.get(i).substring(camposFiniquitos.get(i).lastIndexOf("."))).append(" = :parametro").append(posicionParametro);
                        camposParametro.add("parametro".concat(String.valueOf(posicionParametro)));
                        valoresParametro.add(valoresCamposFiniquitos.get(i));
                        posicionParametro++;
                    }
                }
            }

            Calendar fechaRango = Calendar.getInstance();
            fechaRango.setTime(fechaPeriodoNomina.getTime());
            int mesIni = -1, mesFin = -1;
            if (tipoAcumulado == TipoClasificacionFormula.DATOPERIODO) {

                path = cq.getAliasTablaOuter().get(tablPadre.concat("_periodosNomina"));
                strQuery.append(" AND ").append(path).append(".id in (SELECT periodo.id FROM ").append(PeriodosNomina.class.getSimpleName()).append(" periodo INNER JOIN periodo.tipoNomina nomina ");

                if (periodosNomina != null) {//JSA29
                    if (clavePeriodoFuncion.equalsIgnoreCase("Anterior")) {
                        strQuery.append("WHERE periodo.fechaInicial = (Select MAX(pp.fechaInicial)  from  PeriodosNomina pp where pp.tipoNomina.clave = :claveTipoNomina  ");
                        if (valoresConceptosEmpleados.containsKey("ClaveTipoCorrida".toUpperCase())) {
                            String corrida = valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()).toString();
                            if (corrida.length() > 0) {
                                strQuery.append(" AND pp.tipoCorrida.clave = :TipoCorrida ");
                            }
                        }
                        strQuery.append("AND (pp.fechaInicial < :fechaActual AND :fechaActual not between pp.fechaInicial AND pp.fechaFinal))");
                    } else if (isNumericaString(clavePeriodoFuncion)) {
                        strQuery.append("WHERE periodo.clave = :clavePeriodo AND nomina.clave = :claveTipoNomina ");
                        camposParametro.add("clavePeriodo");
                        valoresParametro.add(clavePeriodoFuncion);
                    } else if (periodosNomina.getTipoNomina().getPeriodicidad().getDias() == 1) {
                        strQuery.append("WHERE (:fechaActual BETWEEN periodo.fechaInicial AND periodo.fechaFinal) AND nomina.clave = :claveTipoNomina ");
                    } else {
                        strQuery.append("WHERE (:fechaActual BETWEEN periodo.fechaInicial AND periodo.fechaFinal) AND nomina.clave = :claveTipoNomina ");
                    }
                } else if (clavePeriodoFuncion.equalsIgnoreCase("Anterior")) {
                    strQuery.append("WHERE periodo.fechaInicial = (Select MAX(pp.fechaInicial)  from  PeriodosNomina pp where pp.tipoNomina.clave = :claveTipoNomina  ");
                    if (valoresConceptosEmpleados.containsKey("ClaveTipoCorrida".toUpperCase())) {
                        String corrida = valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()).toString();
                        if (corrida.length() > 0) {
                            strQuery.append(" AND pp.tipoCorrida.clave = :TipoCorrida ");
                        }
                    }
                    strQuery.append("AND (pp.fechaInicial < :fechaActual AND :fechaActual not between pp.fechaInicial AND pp.fechaFinal))");
                } else if (isNumericaString(clavePeriodoFuncion)) {
                    strQuery.append("WHERE periodo.clave = :clavePeriodo AND nomina.clave = :claveTipoNomina ");
                    camposParametro.add("clavePeriodo");
                    valoresParametro.add(clavePeriodoFuncion);
                } else {
                    strQuery.append("WHERE (:fechaActual BETWEEN periodo.fechaInicial AND periodo.fechaFinal + 1) AND nomina.clave = :claveTipoNomina ");
                }
                if (valoresConceptosEmpleados.containsKey("ClaveTipoCorrida".toUpperCase())) {
                    String corrida = valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()).toString();
                    if (corrida.length() > 0) {
                        strQuery.append(" AND periodo.tipoCorrida.clave = :TipoCorrida ");
                    }
                }

                if (isNumericaString(clavePeriodoFuncion)) {
                    strQuery.append(") AND ").append(path).append(".año = :yearPeriodo ");
                    camposParametro.add("yearPeriodo");
                    Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                    valoresParametro.add(fecha.get(Calendar.YEAR));
                } else {
                    strQuery.append(") ");
                    camposParametro.add("fechaActual");
                    valoresParametro.add(fechaPeriodoNomina.getTime());
                }

                camposParametro.add("claveTipoNomina");
                valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));

            } else if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
                if ((fechaPeriodoNomina.get(Calendar.MONTH) + 1) % 2 == 0) {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH);
                    fechaRango.set(Calendar.MONTH, fechaPeriodoNomina.get(Calendar.MONTH) - 1);
                } else {
                    mesFin = fechaPeriodoNomina.get(Calendar.MONTH) + 2;
                    mesIni = fechaPeriodoNomina.get(Calendar.MONTH) + 1;
                }

            } else if (tipoAcumulado == TipoClasificacionFormula.DATOANUAL) {
                path = cq.getAliasTablaOuter().get(tablPadre.concat("_periodosNomina"));
                strQuery.append(" AND ((").append(path).append(".fechaInicial BETWEEN :fechaInicial AND :fechaFinal) ");
                strQuery.append(" OR (").append(path).append(".fechaFinal BETWEEN :fechaInicial AND :fechaFinal)) ");
                camposParametro.add("fechaInicial");
                Calendar fechaFinal = ((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial));
                Calendar fecha = Calendar.getInstance();
                fecha.set(fechaFinal.get(Calendar.YEAR), Calendar.JANUARY, 1);
                if (clavePeriodoFuncion.equalsIgnoreCase("Anterior") | isNumericaString(clavePeriodoFuncion)) {
                    fechaFinal.setTime(fechaPeriodoNomina.getTime());
                    fecha.set(fechaFinal.get(Calendar.YEAR), Calendar.JANUARY, 1);
                }
                valoresParametro.add(fecha.getTime());
                camposParametro.add("fechaFinal");
                valoresParametro.add(fechaFinal.getTime());
            }

            if (tipoAcumulado == TipoClasificacionFormula.DATOBIMESTRAL) {
                path = cq.getAliasTablaOuter().get(tablPadre.concat("_periodosNomina"));
                strQuery.append(" AND ").append(path).append(".año = :yearPeriodo ").append(" AND ");
                camposParametro.add("yearPeriodo");
                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                if (clavePeriodoFuncion.equalsIgnoreCase("Anterior") | isNumericaString(clavePeriodoFuncion)) {
                    fecha.setTime(fechaPeriodoNomina.getTime());
                }
                valoresParametro.add(fecha.get(Calendar.YEAR));
                camposParametro.add("mesIni");
                // camposParametro.add("mesFin");
                valoresParametro.add(mesIni);
                //valoresParametro.add(mesFin);
                if (mesIni == fecha.get(Calendar.MONTH) + 1) {
                    strQuery.append(cq.getAliasTablaOuter().get(tablPadre)).append(".mes = :mesIni");
                    if (!periodosNomina.isCierreMes()) {
                        Calendar fechainicio = Calendar.getInstance();
                        fechainicio.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
                        strQuery.append(" AND ((").append(path).append(".fechaInicial BETWEEN :fechainical and :fechafinal) OR (");
                        strQuery.append(path).append(".fechaFinal BETWEEN :fechainical and :fechafinal)) ");
                        valoresParametro.add(fechainicio.getTime());
                        valoresParametro.add(fecha.getTime());
                        camposParametro.add("fechainical");
                        camposParametro.add("fechafinal");
                    }

                } else if (mesFin == fecha.get(Calendar.MONTH) + 1) {

                    camposParametro.add("mesFin");
                    valoresParametro.add(mesFin);
                    if (!periodosNomina.isCierreMes()) {
                        strQuery.append(" (").append(cq.getAliasTablaOuter().get(tablPadre)).append(".mes = :mesIni OR  ");
                        Calendar fechainicio = Calendar.getInstance();
                        fechainicio.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
                        strQuery.append("  (").append(cq.getAliasTablaOuter().get(tablPadre)).append(".mes = :mesFin AND ").append("(").append(path).append(".fechaInicial BETWEEN :fechainical and :fechafinal) OR (");
                        strQuery.append(path).append(".fechaFinal BETWEEN :fechainical and :fechafinal))) ");
                        valoresParametro.add(fechainicio.getTime());
                        valoresParametro.add(fecha.getTime());
                        camposParametro.add("fechainical");
                        camposParametro.add("fechafinal");
                    } else {
                        strQuery.append(" (").append(cq.getAliasTablaOuter().get(tablPadre)).append(".mes = :mesIni OR  ").append(cq.getAliasTablaOuter().get(tablPadre)).append(".mes = :mesFin) ");
                    }

                }
            } else if (tipoAcumulado == TipoClasificacionFormula.DATOMENSUAL) {

                path = cq.getAliasTablaOuter().get(tablPadre.concat("_periodosNomina"));
                strQuery.append(" AND ").append(path).append(".año = :yearPeriodo ").append(" AND ").append(cq.getAliasTablaOuter().get(tablPadre)).append(".mes = :mesActual  ");

                camposParametro.add("yearPeriodo");

                Calendar fecha = ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal));
                if (clavePeriodoFuncion.equalsIgnoreCase("Anterior") | isNumericaString(clavePeriodoFuncion)) {
                    fecha.setTime(fechaPeriodoNomina.getTime());
                }
                valoresParametro.add(fecha.get(Calendar.YEAR));
                camposParametro.add("mesActual");
                valoresParametro.add(fecha.get(Calendar.MONTH) + 1);
                if (!periodosNomina.isCierreMes()) {
                    Calendar fechainicio = Calendar.getInstance();
                    fechainicio.set(fecha.get(Calendar.YEAR), fecha.get(Calendar.MONTH), 1);
                    strQuery.append(" AND ((").append(path).append(".fechaInicial BETWEEN :fechainical and :fechafinal) OR (");
                    strQuery.append(path).append(".fechaFinal BETWEEN :fechainical and :fechafinal)) ");
                    valoresParametro.add(fechainicio.getTime());
                    valoresParametro.add(fecha.getTime());
                    camposParametro.add("fechainical");
                    camposParametro.add("fechafinal");
                }

            }

            for (i = 0; i < camposWhere.length; i++) {
                path = camposWhere[i].substring(0, camposWhere[i].lastIndexOf("."));
                strQuery.append(" AND ").append(cq.getAliasTablaOuter().get(path.replace(".", "_"))).append(camposWhere[i].substring(camposWhere[i].lastIndexOf("."))).append(" = :parametro").append(posicionParametro);
                camposParametro.add("parametro".concat(String.valueOf(posicionParametro)));
                valoresParametro.add(valoresWhere[i]);
                posicionParametro++;
            }
            valor = (Object) ejecutaQueryUnico(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray());
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(62);
            }
        } catch (Exception ex) {
            mensajeResultado.setNoError(62);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("movimientosAcumuladoPorRangoMeses()1_Error: ").append(ex));
        }
        camposParametro = null;
        valoresParametro = null;
        //strQuery = null;
        return valor;
    }

    private String eliminaCaracteresSeparador(String texto) {
        StringBuilder sb = new StringBuilder();
        texto = texto == null ? "" : texto;

        for (int i = 0; i < texto.length(); i++) {
            if (!Character.isWhitespace(texto.charAt(i))) {
                sb.append(texto.charAt(i));
            }
        }
        return sb.toString();
    }

    private List<MovNomConceParam> creaMovNomConceParam(ConcepNomDefi concepNomDefi, MovNomConcep mnc) {
        List<MovNomConceParam> movNomConceParam = new ArrayList<MovNomConceParam>(0);
        MovNomConceParam m;
        if (mnc.getMovNomConceParam() == null ? true : mnc.getMovNomConceParam().isEmpty()) {
            for (ParaConcepDeNom afecConcepNom : concepNomDefi.getParametroConceptosDeNominas()) {
                m = new MovNomConceParam();
                m.setParaConcepDeNom(afecConcepNom);
                m.setMovNomConcep(mnc);
                m.setValor("0");
                movNomConceParam.add(m);
            }
        } else if (concepNomDefi.getParametroConceptosDeNominas().isEmpty()) {
            if (!mnc.getMovNomConceParam().isEmpty()) {
                for (int j = 0; j < mnc.getMovNomConceParam().size(); j++) {
                    getSession().delete(mnc.getMovNomConceParam().get(j));
                }
            }
        } else {
            List<MovNomConceParam> movNominaBaseAfectasTmp = new ArrayList<MovNomConceParam>();
            movNominaBaseAfectasTmp.addAll(mnc.getMovNomConceParam());
            if (movNominaBaseAfectasTmp == null) {
                movNominaBaseAfectasTmp = new ArrayList<MovNomConceParam>();
                mnc.setMovNomConceParam(movNominaBaseAfectasTmp);
            }
            for (int i = 0; i < concepNomDefi.getParametroConceptosDeNominas().size(); i++) {
                for (int j = 0; j < mnc.getMovNomConceParam().size(); j++) {
                    boolean existe = false;
                    if (concepNomDefi.getParametroConceptosDeNominas().get(i).getId().equals(mnc.getMovNomConceParam().get(j).getParaConcepDeNom().getId())) {
                        existe = true;
                    }
                    if (existe) {
                        movNomConceParam.add(mnc.getMovNomConceParam().get(j));
                        movNominaBaseAfectasTmp.remove(mnc.getMovNomConceParam().get(j));
                    } else {
                        m = new MovNomConceParam();
                        m.setParaConcepDeNom(concepNomDefi.getParametroConceptosDeNominas().get(i));
                        m.setMovNomConcep(mnc);
                        m.setValor("0");
                        movNomConceParam.add(m);
                    }
                }
            }
            if (!movNominaBaseAfectasTmp.isEmpty()) {
                for (int j = 0; j < movNominaBaseAfectasTmp.size(); j++) {
                    getSession().delete(movNominaBaseAfectasTmp.get(j));
                }
            }
        }
        return movNomConceParam;
    }

    private MovNomConcep creaMovNomConceptoSubsidio(MovNomConcep movNomi, ConcepNomDefi concepNomDefi) {
        MovNomConcep movNomConcepSubsidio = new MovNomConcep();
        try {
            movNomConcepSubsidio.setEmpleado(movNomi.getPlazasPorEmpleado().getEmpleados());
            movNomConcepSubsidio.setPlazasPorEmpleado(movNomi.getPlazasPorEmpleado());
            movNomConcepSubsidio.setPeriodosNomina(periodosNomina);
            movNomConcepSubsidio.setConcepNomDefi(concepNomDefi);
            movNomConcepSubsidio.setTipoCorrida(tipoCorrida);
            movNomConcepSubsidio.setTipoNomina(periodosNomina.getTipoNomina());
            movNomConcepSubsidio.setCentroDeCosto(centroDeCostoMovimiento);
            movNomConcepSubsidio.setRazonesSociales(razonesSociales);
            if (concepNomDefi.getBaseAfecConcepNomi() != null) {
                movNomConcepSubsidio.setMovNomBaseAfecta(creaMovimBaseAfectar(concepNomDefi.getBaseAfecConcepNomi(), movNomConcepSubsidio));
            }

            movNomConcepSubsidio.setFechaCierr(periodosNomina.getFechaCierre());
            movNomConcepSubsidio.setFechaIni(periodosNomina.getFechaInicial());
            movNomConcepSubsidio.setTipoPantalla(tipoPantallaSistema);
            movNomConcepSubsidio.setOrdenId(movNomi.getOrdenId() == null ? 0 : movNomi.getOrdenId() + 1);
            movNomConcepSubsidio.setResultado(0.0);
            movNomConcepSubsidio.setNumero(movNomi.getNumero() == null ? 1 : movNomi.getNumero() + 1);
            movNomConcepSubsidio.setCalculado(0.0);
            movNomConcepSubsidio.setMes(movNomi.getMes());
            movNomConcepSubsidio.setEjercicio(movNomi.getEjercicio());
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(53);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("creaMovNomConceptoSubsidio()1_Error: ").append(ex));
        }
        return movNomConcepSubsidio;
    }

    private void cargaValoresDiasPago(PlazasPorEmpleadosMov plazasPorEmpleadosMovEjecutandose, boolean primeraPlazaPorEmpleadosMov, PlazasPorEmpleadosMov plazasPorEmpleadosMovSiguiente, CalculoUnidades calculoUnidades, boolean inicia2doMes, Boolean modificarDiasTrabajados) {
        int diasDif = 0;
        descontarDiasPago = 0.0;
        Calendar fechaIni = Calendar.getInstance(), fechaFin = Calendar.getInstance(), fechaIniAsistenVacacion = Calendar.getInstance(), fechaFinAsistenVacacion = Calendar.getInstance();
        fechaIni.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
        fechaFin.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
        fechaIniAsistenVacacion.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
        fechaFinAsistenVacacion.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
        if (periodosNomina != null & !isMov2Meses) {//JSA20
            fechaIni.setTime(periodosNomina.getFechaInicial());
            fechaFin.setTime(periodosNomina.getFechaFinal());
            fechaIniAsistenVacacion.setTime(periodosNomina.getFechaInicial());
            fechaFinAsistenVacacion.setTime(periodosNomina.getFechaFinal());
        }
        if (((Date) valoresConceptosEmpleados.get("FechaAlta".toUpperCase())).compareTo(fechaIni.getTime()) > 0)//Es mayor fechaAlta a fechaInicial
        {
            diasDif += cantidadDiasEntreDosFechas(fechaIni.getTime(), (Date) valoresConceptosEmpleados.get("FechaAlta".toUpperCase()));
            fechaIniAsistenVacacion.setTime((Date) valoresConceptosEmpleados.get("FechaAlta".toUpperCase()));
        } else if (plazasPorEmpleadosMovEjecutandose.getFechaInicial().compareTo(fechaIni.getTime()) > 0 & !primeraPlazaPorEmpleadosMov | plazasPorEmpleadosMovSiguiente != null) {//aqui huvo una promocion o modificacion. inicaPlazaPorEmpleadosMov es para saber si existen mas de una promocion o es unica
            if (plazasPorEmpleadosMovEjecutandose.getFechaInicial().compareTo(fechaIni.getTime()) > 0 & !inicia2doMes) {
                diasDif += cantidadDiasEntreDosFechas(fechaIni.getTime(), plazasPorEmpleadosMovEjecutandose.getFechaInicial());
                fechaFinAsistenVacacion.setTime(plazasPorEmpleadosMovEjecutandose.getFechaInicial());
                fechaFinAsistenVacacion.set(Calendar.DATE, fechaFinAsistenVacacion.get(Calendar.DATE) - 1);
            }
            if (plazasPorEmpleadosMovSiguiente != null) {
                if (plazasPorEmpleadosMovEjecutandose.getFechaInicial().compareTo(fechaFin.getTime()) == 0 | plazasPorEmpleadosMovSiguiente.getFechaInicial().compareTo(fechaFin.getTime()) == 0) {
                    diasDif += 1;
                    if (plazasPorEmpleadosMovEjecutandose.getFechaInicial().compareTo(fechaFin.getTime()) == 0) {
                        fechaFinAsistenVacacion.setTime(plazasPorEmpleadosMovEjecutandose.getFechaInicial());
                    } else {
                        fechaFinAsistenVacacion.setTime(plazasPorEmpleadosMovSiguiente.getFechaInicial());
                    }
                    fechaFinAsistenVacacion.set(Calendar.DATE, fechaFinAsistenVacacion.get(Calendar.DATE) - 1);
                } else {
                    diasDif += cantidadDiasEntreDosFechas(plazasPorEmpleadosMovSiguiente.getFechaInicial(), fechaFin.getTime());
                    diasDif += 1;
                    fechaFinAsistenVacacion.setTime(plazasPorEmpleadosMovSiguiente.getFechaInicial());
                    fechaFinAsistenVacacion.set(Calendar.DATE, fechaFinAsistenVacacion.get(Calendar.DATE) - 1);
                }
            }
        }

        if (plazasPorEmpleadosMovEjecutandose.getPlazasPorEmpleado().getFechaFinal().compareTo(fechaFin.getTime()) < 0 || fechaBajaFiniq != null) {
            if (fechaBajaFiniq == null) {
                diasDif = diasDif + cantidadDiasEntreDosFechas(plazasPorEmpleadosMovEjecutandose.getPlazasPorEmpleado().getFechaFinal(), fechaFin.getTime());
            } else if (fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaInicial()) >= 0 & fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaFinal()) <= 0) {
                if (fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaInicial()) != 0) {//esto es cuando ambas fechas son la misma. si se hace ejecuta el metodo cantidadDiasEntreDosFechas me va a regresar 365 dias y esta mal.
                    if (fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaFinal()) != 0) {
                        diasDif = diasDif + cantidadDiasEntreDosFechas(fechaBajaFiniq.getTime(), periodosNomina.getFechaFinal());
                    }
                } else if (manejaPagoDiasNaturales) {
                    diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("DiasNaturalesDelPeriodo".toUpperCase());
                } else {
                    diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("PeriodicidadEnDias".toUpperCase());
                }
            } else //este es para cuando se calcule el finiquito desde la ventana de finiquitos por si se calcula en un 
            //periodo distinto al del periodo que tiene la fecha de baja.
            if (manejaPagoDiasNaturales) {
                diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("DiasNaturalesDelPeriodo".toUpperCase());
            } else {
                diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("PeriodicidadEnDias".toUpperCase());
            }
        }
        ((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).setTime(fechaIniAsistenVacacion.getTime());
        ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).setTime(fechaFinAsistenVacacion.getTime());
        cargarVariablesEmpleadoAsistencias(fechaIniAsistenVacacion.getTime(), fechaFinAsistenVacacion.getTime(), calculoUnidades, modificarDiasTrabajados);
        cargarVariablesEmpleadoVacaciones(fechaIniAsistenVacacion.getTime(), fechaFinAsistenVacacion.getTime(), calculoUnidades, plazasPorEmpleadosMovEjecutandose);
        if (manejaPagoDiasNaturales) {
            valoresConceptosEmpleados.put("DiasNormalesAPagar".toUpperCase(), valoresConceptosEmpleados.get("DiasNaturalesDelPeriodo".toUpperCase()));
            int diasVacaciones = tipoCorrida == null ? (Integer) valoresConceptosEmpleados.get("diasVacaciones".toUpperCase()) : tipoCorrida.getClave().equalsIgnoreCase("VAC") ? (Integer) valoresConceptosEmpleados.get("diasVacaciones".toUpperCase()) : 0;
            valoresConceptosEmpleados.put("DiasPago".toUpperCase(),
                    (Integer.parseInt(valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase()).toString())
                    - (diasDif
                    + ((Integer) valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase())
                    + (Double) valoresConceptosEmpleados.get("Faltas".toUpperCase())
                    + (Integer) valoresConceptosEmpleados.get("Ausentismo".toUpperCase())
                    + diasVacaciones))) + pagarPrimero3Dias);//JSA08
            descontarDiasPago = (diasDif
                    + ((Integer) valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase())
                    + (Double) valoresConceptosEmpleados.get("Faltas".toUpperCase())
                    + (Integer) valoresConceptosEmpleados.get("Ausentismo".toUpperCase())
                    + diasVacaciones)) - pagarPrimero3Dias;
        } else {
            valoresConceptosEmpleados.put("DiasNormalesAPagar".toUpperCase(), Integer.parseInt(valoresConceptosEmpleados.get("PeriodicidadEnDias".toUpperCase()).toString()));
            int diasVacaciones = tipoCorrida == null ? (Integer) valoresConceptosEmpleados.get("diasVacaciones".toUpperCase()) : tipoCorrida.getClave().equalsIgnoreCase("VAC") ? (Integer) valoresConceptosEmpleados.get("diasVacaciones".toUpperCase()) : 0;
            valoresConceptosEmpleados.put("DiasPago".toUpperCase(),
                    (Integer.parseInt(valoresConceptosEmpleados.get("DiasNormalesAPagar".toUpperCase()).toString())
                    - (diasDif
                    + ((Integer) valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase())
                    + (Double) valoresConceptosEmpleados.get("Faltas".toUpperCase())
                    + (Integer) valoresConceptosEmpleados.get("Ausentismo".toUpperCase())
                    + diasVacaciones))) + pagarPrimero3Dias);//JSA08
            descontarDiasPago = (diasDif
                    + ((Integer) valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase())
                    + (Double) valoresConceptosEmpleados.get("Faltas".toUpperCase())
                    + (Integer) valoresConceptosEmpleados.get("Ausentismo".toUpperCase()) /*+ diasVacaciones*/)) - pagarPrimero3Dias;
        }
        if (calculoUnidades != null) {
            calculoUnidades.setDiasTrabajados(((Double) valoresConceptosEmpleados.get("DiasPago".toUpperCase())).doubleValue());
        }
    }

    private void cargaValoresDiasCotizados(Date fechaIMSSEjecutandose, Date fechaFinalEjecutandose, boolean inicaPlazaPorEmpleadosMov, SalariosIntegrados plazasPorEmpleadosMovSiguiente, CalculoUnidades calculoUnidades, boolean inicia2doMes, Boolean modificarDiasTrabajados) {
        int diasDif = 0;
        Calendar fechaIni = Calendar.getInstance(), fechaFin = Calendar.getInstance(), fechaIniAsistenVacacion = Calendar.getInstance(), fechaFinAsistenVacacion = Calendar.getInstance();
        fechaIni.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
        fechaFin.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
        fechaIniAsistenVacacion.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
        fechaFinAsistenVacacion.setTime(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
        if (periodosNomina != null & !isMov2Meses) {//JSA20
            fechaIni.setTime(periodosNomina.getFechaInicial());
            fechaFin.setTime(periodosNomina.getFechaFinal());
            fechaIniAsistenVacacion.setTime(periodosNomina.getFechaInicial());
            fechaFinAsistenVacacion.setTime(periodosNomina.getFechaFinal());
        }
        boolean seAplicoDiasDif = false;
        if (((Date) valoresConceptosEmpleados.get("FechaAltaIMSS".toUpperCase())).compareTo(fechaIni.getTime()) > 0)//Es mayor fechaAlta a fechaInicial
        {
            diasDif += cantidadDiasEntreDosFechas(fechaIni.getTime(), ((Date) valoresConceptosEmpleados.get("FechaAltaIMSS".toUpperCase())));
        } else if (fechaIMSSEjecutandose.compareTo(fechaIni.getTime()) > 0 & !inicaPlazaPorEmpleadosMov | plazasPorEmpleadosMovSiguiente != null) {//aqui huvo una promocion o modificacion. inicaPlazaPorEmpleadosMov es para saber si existen mas de una promocion o es unica
            if (fechaIMSSEjecutandose.compareTo(fechaIni.getTime()) > 0 & !inicia2doMes) {
                diasDif += cantidadDiasEntreDosFechas(fechaIni.getTime(), fechaIMSSEjecutandose);

                fechaFinAsistenVacacion.setTime(fechaIMSSEjecutandose);
                fechaFinAsistenVacacion.set(Calendar.DATE, fechaFinAsistenVacacion.get(Calendar.DATE) - 1);
                seAplicoDiasDif = true;
            }
            if (plazasPorEmpleadosMovSiguiente != null) {
                if (fechaIMSSEjecutandose.compareTo(fechaFin.getTime()) == 0 | plazasPorEmpleadosMovSiguiente.getFecha().compareTo(fechaFin.getTime()) == 0) {
                    diasDif += 1;
                    if (fechaIMSSEjecutandose.compareTo(fechaFin.getTime()) == 0) {
                        fechaFinAsistenVacacion.setTime(fechaIMSSEjecutandose);
                    } else {
                        fechaFinAsistenVacacion.setTime(plazasPorEmpleadosMovSiguiente.getFecha());
                    }
                    fechaFinAsistenVacacion.set(Calendar.DATE, fechaFinAsistenVacacion.get(Calendar.DATE) - 1);
                } else {
                    if (plazasPorEmpleadosMovSiguiente.getFecha().after(fechaFin.getTime())) {
                        diasDif += cantidadDiasEntreDosFechas(fechaFin.getTime(), plazasPorEmpleadosMovSiguiente.getFecha());
                    } else {
                        diasDif += cantidadDiasEntreDosFechas(plazasPorEmpleadosMovSiguiente.getFecha(), fechaFin.getTime());
                    }
                    diasDif += 1;
                    fechaFinAsistenVacacion.setTime(plazasPorEmpleadosMovSiguiente.getFecha());
                    fechaFinAsistenVacacion.set(Calendar.DATE, fechaFinAsistenVacacion.get(Calendar.DATE) - 1);
                }
                seAplicoDiasDif = true;
            }
        }

        if (fechaFinalEjecutandose.compareTo(fechaFin.getTime()) < 0 || fechaBajaFiniq != null) {
            if (fechaBajaFiniq == null) {
                diasDif = diasDif + cantidadDiasEntreDosFechas(fechaFinalEjecutandose, fechaFin.getTime());
            } else if (plazasPorEmpleadosMovSiguiente == null ? false : seAplicoDiasDif) {
//                    System.out.println("ekelele");
            } else if (fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaInicial()) >= 0 & fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaFinal()) <= 0) {
                if (fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaInicial()) != 0) {//esto es cuando ambas fechas son la misma. si se hace ejecuta el metodo cantidadDiasEntreDosFechas me va a regresar 365 dias y esta mal.
                    if (fechaBajaFiniq.getTime().compareTo(periodosNomina.getFechaFinal()) != 0) {
                        diasDif = diasDif + cantidadDiasEntreDosFechas(fechaBajaFiniq.getTime(), periodosNomina.getFechaFinal());
                    }
                } else if (manejaPagoIMSSDiasNaturales) {
                    diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("DiasNaturalesDelPeriodo".toUpperCase());
                } else {
                    diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("PeriodicidadEnDias".toUpperCase());
                }
            } else //este es para cuando se calcule el finiquito desde la ventana de finiquitos por si se calcula en un 
            //periodo distinto al del periodo que tiene la fecha de baja.
            if (manejaPagoIMSSDiasNaturales) {
                diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("DiasNaturalesDelPeriodo".toUpperCase());
            } else {
                diasDif = diasDif + (Integer) valoresConceptosEmpleados.get("PeriodicidadEnDias".toUpperCase());
            }
        }
        cargarVariablesEmpleadoAsistencias(fechaIniAsistenVacacion.getTime(), fechaFinAsistenVacacion.getTime(), calculoUnidades, modificarDiasTrabajados);
        if (manejaPagoIMSSDiasNaturales) {
            valoresConceptosEmpleados.put("DiasCotizados".toUpperCase(), (Integer.parseInt(valoresConceptosEmpleados.get("DiasNaturalesDelPeriodo".toUpperCase()).toString()) - (diasDif
                    + ((Integer) valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase())
                    + (Integer) valoresConceptosEmpleados.get("Ausentismo".toUpperCase())))));
        } else {
            valoresConceptosEmpleados.put("DiasCotizados".toUpperCase(), (Integer.parseInt(valoresConceptosEmpleados.get("PeriodicidadEnDias".toUpperCase()).toString()) - (diasDif
                    + ((Integer) valoresConceptosEmpleados.get("DiasIncapacidadEmpleado".toUpperCase())
                    + (Integer) valoresConceptosEmpleados.get("Ausentismo".toUpperCase())))));
        }
//        System.out.println("DiasCotizados  = ".toUpperCase() + valoresConceptosEmpleados.get("DiasCotizados".toUpperCase()) + " DiasNaturalesDelPeriodo " + valoresConceptosEmpleados.get("DiasNaturalesDelPeriodo".toUpperCase()));

    }

    private void cargarVariablesEmpleadoVacaciones(Date fechaInicial, Date fechaFinal, CalculoUnidades calculoUnidades, PlazasPorEmpleadosMov plazasPorEmpleadosMovEjecutandose) {
        int x;
        int diasVacaciones = 0;
        double diasPrimaVacacional = 0.0;
        try {
            boolean corridaVacaciones = false;
            if (valoresConceptosEmpleados.containsKey("ClaveTipoCorrida".toUpperCase())) {
                String claveCorrida = valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()) == null ? "" : valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()).toString();
                if (claveCorrida.equalsIgnoreCase("VAC")) {
                    corridaVacaciones = true;
                }
            }
            List<VacacionesAplicacion> vacacionesAplicacion = obtenerVacaciones(periodosNomina, plazasPorEmpleadosMovEjecutandose, corridaVacaciones);
            Calendar fechaSalidaVacaciones = Calendar.getInstance(), fechaRegresoVacaciones = Calendar.getInstance();
            fechaSalidaVacaciones.set(Calendar.YEAR, 1900);
            fechaRegresoVacaciones.set(Calendar.YEAR, 1900);
            TiposVacaciones tipoVacaciones = null;
            if (vacacionesAplicacion != null) {
                for (x = 0; x < vacacionesAplicacion.size(); x++) {
                    fechaSalidaVacaciones.setTime(vacacionesAplicacion.get(x).getVacacionesDisfrutadas().getSalidaVacac());
                    fechaRegresoVacaciones.setTime(vacacionesAplicacion.get(x).getVacacionesDisfrutadas().getRegresoVac());
                    if (vacacionesAplicacion.get(x).getDiasVac() == null) {
                        vacacionesAplicacion.get(x).setDiasVac(0);
                    }
                    diasVacaciones += vacacionesAplicacion.get(x).getDiasVac();
                    if (vacacionesAplicacion.get(x).getDiasPrima() == null) {
                        vacacionesAplicacion.get(x).setDiasPrima(0.0);
                    }
                    diasPrimaVacacional += vacacionesAplicacion.get(x).getDiasPrima();
                    tipoVacaciones = vacacionesAplicacion.get(x).getVacacionesDisfrutadas().getTiposVacaciones();
                    if (corridaVacaciones) {
                        boolean asigno = false;
                        if (vacacionesAplicacion.get(x).getDiasPrima() > 0.0) {
                            vacacionesAplicacion.get(x).getVacacionesDisfrutadas().setPeriodoprimaVacacional(periodosNomina);
                            asigno = true;
                        }
                        if (vacacionesAplicacion.get(x).getDiasVac() > 0) {
                            vacacionesAplicacion.get(x).getVacacionesDisfrutadas().setPeriodoVacacional(periodosNomina);
                            asigno = true;
                        }
                        if (asigno) {
                            getSession().saveOrUpdate(vacacionesAplicacion.get(x).getVacacionesDisfrutadas());
                        }
                    }
//                    fechaContador.setTime(fechaInicialVacaciones.getTime());
//                    while (!fechaContador.after(fechaRegresoVacaciones)) {
//                        if ((fechaContador.getTime().compareTo(fechaInicial) > 0 || fechaContador.getTime().compareTo(fechaInicial) == 0)
//                                & (fechaContador.getTime().compareTo(fechaFinal) == 0 || fechaContador.getTime().compareTo(fechaFinal) < 0)) {
//                            diasVacacionesDisfPeriodo += 1;
//                        }
//
//                        fechaContador.add(Calendar.DATE, 1);
//                    }
                }
            }
            valoresConceptosEmpleados.put("fechaSalidaVacaciones".toUpperCase(), (Date) fechaSalidaVacaciones.getTime());
            valoresConceptosEmpleados.put("fechaRegresoVacaciones".toUpperCase(), (Date) fechaRegresoVacaciones.getTime());
////////            valoresConceptosEmpleados.put("fechaInicialTrabajadas".toUpperCase(), (Date) fechaInicialTrabajadas.getTime());
////////            valoresConceptosEmpleados.put("fechaFinalTrabajadas".toUpperCase(), (Date) fechaFinalTrabajadas.getTime());
////////////            valoresConceptosEmpleados.put("diasVacacionesDisfrutadas".toUpperCase(), (Integer) diasVacacionesDisfrutadas);
////////////            valoresConceptosEmpleados.put("diasVacacionesTrabajadas".toUpperCase(), (Integer) diasVacacionesTrabajadas);
////////            valoresConceptosEmpleados.put("diasVacacionesDisfrutadas".toUpperCase(), (Integer) diasVacacionesDisfPeriodo);
////////            valoresConceptosEmpleados.put("diasVacacionesTrabajadas".toUpperCase(), (Integer) diasVacacionesTrabPeriodo);
////////////            valoresConceptosEmpleados.put("diasVacacionesDisfPeriodo".toUpperCase(), (Integer) diasVacacionesDisfPeriodo);
////////////            valoresConceptosEmpleados.put("diasVacacionesTrabPeriodo".toUpperCase(), (Integer) diasVacacionesTrabPeriodo);

            valoresConceptosEmpleados.put("diasVacaciones".toUpperCase(), diasVacaciones);
            valoresConceptosEmpleados.put("diasPrima".toUpperCase(), diasPrimaVacacional);
            valoresConceptosEmpleados.put("tipoVacaciones".toUpperCase(), tipoVacaciones == null ? "" : tipoVacaciones.getNombre());
            if (calculoUnidades != null) {
                calculoUnidades.setDiasPrimaVacacional(diasPrimaVacacional);
                calculoUnidades.setDiasVacaciones(diasVacaciones);
                calculoUnidades.setTiposVacaciones(tipoVacaciones);
            }
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(70);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("cargarVariablesGlobalesEmpleadoVacaciones()1_Error: ").append(ex));
        }
    }

    private void cargarVariablesEmpleadoAsistencias(Date fechaInicial, Date fechaFinal, CalculoUnidades calculoUnidades, Boolean modificarDiasTrabajados) {//JSA30
        Integer diasAusencias = 0, diasIncapacidadEnfermedad = 0,
                diasIncapacidadAccidente = 0, diasIncapacidadMaternidad = 0, diasOtrasIncapacidad = 0,
                festivo = 0, descanso = 0, laborados = 0;
        Double hrsExtraDoble = 0.0, hrsExtraTriple = 0.0, retardos = 0.0, permisoSinSueldo = 0.0, permisoConSueldo = 0.0,
                descansoLaborado = 0.0, festivoLaborado = 0.0, domingoLaborado = 0.0, diasRetardos = 0.0, diasFaltas = 0.0;
        List<Asistencias> listAsistencias = obtenerAsistencias(fechaInicial, fechaFinal);
        int x;
//        System.out.println("************************* Fecha Inicial " + fechaInicial + "   Fecha Final " + fechaFinal);
        List<Asistencias> listAsistenciasIncapacidadEnfermedad = new ArrayList<Asistencias>();
        for (x = 0; x < listAsistencias.size(); x++) {
            switch (Integer.parseInt(listAsistencias.get(x).getExcepciones().getClave())) {
                case 0://Laborado = "0"
                    laborados++;
                    break;
                case 1://Retardo = "1";
                    retardos += listAsistencias.get(x).getCantidad();
                    diasRetardos++;
                    break;
                case 2://Falta = "2";
                    if (listAsistencias.get(x).getCantidad() == null) {
                        diasFaltas++;
                    } else if (listAsistencias.get(x).getCantidad().doubleValue() == 0.50) {
                        diasFaltas = diasFaltas + 0.5;
                    } else {
                        diasFaltas++;
                    }
                    break;
                case 3://Ausentismo = "3";
                    diasAusencias++;
                    break;
                case 4://PermisoConSueldo = "4";
                    permisoConSueldo++;
                    break;
                case 5://PermisoSinSueldo = "5";
                    permisoSinSueldo++;
                    break;
                case 6://IncapacidadPorEnfermedad = "6";
                    listAsistenciasIncapacidadEnfermedad.add(listAsistencias.get(x));
                    diasIncapacidadEnfermedad++;
                    break;
                case 7://IncapacidadPorAccidente = "7";
                    diasIncapacidadAccidente++;
                    break;
                case 8://IncapacidadPorMaternidad = "8";
                    diasIncapacidadMaternidad++;
                    break;
                case 9://OtrasIncapacidades = "9";
                    diasOtrasIncapacidad++;
                    break;
                case 10://DescansoLaborado = "10";
                    descansoLaborado += listAsistencias.get(x).getCantidad();
                    break;
                case 11://FestivoLaborado = "11";
                    if (listAsistencias.get(x).getExcepciones().getTipoDatoExcepcion() == TipoDatoExcepcion.SINDATO) {
                        festivoLaborado++;
                    } else {
                        festivoLaborado += listAsistencias.get(x).getCantidad();
                    }
                    break;
                case 12://DomingoLaborado = "12";
                    domingoLaborado += listAsistencias.get(x).getCantidad();
                    break;
                case 13://TiempoExtra = "13";
                    break;
                case 14://ExtraDoble = "14";
                    hrsExtraDoble += listAsistencias.get(x).getCantidad();
                    break;
                case 15://ExtraTriple = "15";
                    hrsExtraTriple += listAsistencias.get(x).getCantidad();
                    break;
                case 16://Festivo = "16";
                    festivo++;
                    break;
                case 17://Descanso = "17";
                    descanso++;
                    break;

            }
        }
        if (listAsistencias.isEmpty()) {
            if (((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime().compareTo(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime()) == 0) {
                laborados = 1;
            } else {
                laborados = cantidadDiasEntreDosFechas(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime(), ((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime()) + 1;
            }
        }
        if (modificarDiasTrabajados == null ? true : modificarDiasTrabajados) {
            valoresConceptosEmpleados.put("Laborado".toUpperCase(), laborados);
        }
        valoresConceptosEmpleados.put("HorasExtrasDobles".toUpperCase(), hrsExtraDoble);
        valoresConceptosEmpleados.put("HorasExtrasTriples".toUpperCase(), hrsExtraTriple);
        int diasAPagar = 0;
        int diasApagarIMSS = 0;
        if (!listAsistenciasIncapacidadEnfermedad.isEmpty()) {
            Utilerias.ordena(listAsistenciasIncapacidadEnfermedad, "Fecha");
            List<RegistroIncapacidad> listRegistroIncapacidad = obtenerIncapacidadesPorEnfermedad(listAsistenciasIncapacidadEnfermedad.get(0).getFecha(), listAsistenciasIncapacidadEnfermedad.get(listAsistenciasIncapacidadEnfermedad.size() - 1).getFecha());
            pagarPrimero3Dias = 0;

            for (int i = 0; i < listRegistroIncapacidad.size(); i++) {
                if (listRegistroIncapacidad.get(i).isPagarTresPrimeroDias() == null ? false : listRegistroIncapacidad.get(i).isPagarTresPrimeroDias()) {

                    int diasIncapacidadEnf = listRegistroIncapacidad.get(i).getDiasIncapacidad();
                    diasApagarIMSS = diasIncapacidadEnf;
                    if (diasIncapacidadEnf <= 3) {
                        diasAPagar = diasIncapacidadEnf;
                    } else {
                        diasAPagar = 3;
                    }
                    Calendar fechaIncap = Calendar.getInstance();
                    fechaIncap.setTime(listRegistroIncapacidad.get(i).getFechaInicial());
                    for (int dias = 0; dias < diasIncapacidadEnf; dias++) {
                        if ((fechaIncap.getTime().compareTo(periodosNomina.getFechaInicial()) > 0 || fechaIncap.getTime().compareTo(periodosNomina.getFechaInicial()) == 0)
                                & (fechaIncap.getTime().compareTo(periodosNomina.getFechaFinal()) == 0 || fechaIncap.getTime().compareTo(periodosNomina.getFechaFinal()) < 0)) {
                            if (diasAPagar > 0) {
                                pagarPrimero3Dias++;
                            }
                            diasAPagar--;
                            diasApagarIMSS--;
                        } else if (fechaIncap.getTime().compareTo(periodosNomina.getFechaInicial()) < 0) {
                            diasAPagar--;
                            diasApagarIMSS--;
                        } else if (fechaIncap.getTime().compareTo(periodosNomina.getFechaFinal()) > 0) {
                            break;
                        }
                        if (diasAPagar == 0 | pagarPrimero3Dias == 3) {
                            break;
                        }
                        fechaIncap.set(Calendar.DATE, fechaIncap.get(Calendar.DATE) + 1);
                    }
                    //diasIncapacidadEnfermedad = diasIncapacidadEnfermedad > 3 ? diasIncapacidadEnfermedad - 3 : 0;
                    ///diasIncapacidadEnfermedad = diasIncapacidadEnfermedad - diasUsadosPagar;
                }
            }
        }
        valoresConceptosEmpleados.put("IncapacidadEnfermedad".toUpperCase(), diasIncapacidadEnfermedad);
        valoresConceptosEmpleados.put("IncapacidadAccidente".toUpperCase(), diasIncapacidadAccidente);
        valoresConceptosEmpleados.put("IncapacidadMaternidad".toUpperCase(), diasIncapacidadMaternidad);
        valoresConceptosEmpleados.put("OtrasIncapacidad".toUpperCase(), diasOtrasIncapacidad);
        valoresConceptosEmpleados.put("DiasIncapacidadEmpleado".toUpperCase(), diasIncapacidadEnfermedad + diasIncapacidadAccidente + diasIncapacidadMaternidad + diasOtrasIncapacidad);
        valoresConceptosEmpleados.put("Faltas".toUpperCase(), diasFaltas);
        if (modificarDiasTrabajados == null ? true : !modificarDiasTrabajados) {
            valoresConceptosEmpleados.put("Ausentismo".toUpperCase(), diasAusencias);
        }
        valoresConceptosEmpleados.put("TExtrasDiaDescanso".toUpperCase(), descansoLaborado);
        valoresConceptosEmpleados.put("TExtrasDiaFestivo".toUpperCase(), festivoLaborado);
        valoresConceptosEmpleados.put("TExtrasDiaDomingo".toUpperCase(), domingoLaborado);
        valoresConceptosEmpleados.put("Retardos".toUpperCase(), retardos);
        valoresConceptosEmpleados.put("DiasRetardos".toUpperCase(), diasRetardos);
        valoresConceptosEmpleados.put("PermisoConSueldo".toUpperCase(), permisoConSueldo);
        valoresConceptosEmpleados.put("PermisoSinSueldo".toUpperCase(), permisoSinSueldo);
        valoresConceptosEmpleados.put("DiasFestivos".toUpperCase(), festivo);
        valoresConceptosEmpleados.put("DiasDescanso".toUpperCase(), descanso);
        valoresConceptosEmpleados.put("DiasIncapacidadEmpresa".toUpperCase(), pagarPrimero3Dias);
        valoresConceptosEmpleados.put("DiasIncapacidadIMSS".toUpperCase(), diasApagarIMSS);
        if (calculoUnidades != null) {
            if (modificarDiasTrabajados == null ? true : modificarDiasTrabajados) {
                calculoUnidades.setDiasTrabajados(laborados.doubleValue());
            }
            calculoUnidades.setHrsExtraDoble(hrsExtraDoble);
            calculoUnidades.setHrsExtraTriple(hrsExtraTriple);
            calculoUnidades.setDiasIncapacidadEnfermedad(diasIncapacidadEnfermedad);
            calculoUnidades.setDiasIncapacidadAccidente(diasIncapacidadAccidente);
            calculoUnidades.setDiasIncapacidadMaternidad(diasIncapacidadMaternidad);
            calculoUnidades.setDiasOtrasIncapacidades(diasOtrasIncapacidad);
            calculoUnidades.setDiasFalta(diasFaltas);
            if (modificarDiasTrabajados == null ? true : !modificarDiasTrabajados) {
                calculoUnidades.setDiasAusentismo(diasAusencias);
            }
            calculoUnidades.setDiasDescansoLaborado(descansoLaborado);
            calculoUnidades.setDiasFestivoLaborado(festivoLaborado);
            calculoUnidades.setDiasDomingoLaborado(domingoLaborado);
            calculoUnidades.setDiasRetardo(retardos);
            calculoUnidades.setDiasPermisoconsueldo(permisoConSueldo);
            calculoUnidades.setDiasPermisosinsueldo(permisoSinSueldo);
            calculoUnidades.setDiasFestivo(festivo);
            calculoUnidades.setDiasDescanso(descanso);
        }

    }

    private List<Asistencias> obtenerAsistencias(Date fechaInicial, Date fechaFinal) {
        List<Asistencias> listAsistencias = null;
        try {
            if (periodosNomina == null) {
                return new ArrayList<Asistencias>();
            }
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            camposParametro.add("claveEmpleado");
            camposParametro.add("claveTipoNomina");
            camposParametro.add("claveTipoCorrida");

            camposParametro.add("fechaInicio");
            camposParametro.add("fechaFin");
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("TipoNomina".toUpperCase()));
            valoresParametro.add(valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase()));
            if (fechaBajaFiniq != null) {
                strQuery.delete(0, strQuery.length()).append("Select p ");
                strQuery.append(" from PeriodosNomina p inner join p.tipoNomina  t ");
                strQuery.append(" Where (:fecha BETWEEN p.fechaInicial AND p.fechaFinal + 1) ");
                strQuery.append(" and t.clave = :claveTipoNomina AND p.tipoCorrida.clave = :claveTipoCorrida ");
                PeriodosNomina periodosNominaTmp = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveTipoNomina", "fecha", "claveTipoCorrida"},
                        new Object[]{valoresConceptosEmpleados.get("TipoNomina".toUpperCase()), fechaBajaFiniq.getTime(), valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase())});
                if (periodosNominaTmp != null) {
                    valoresParametro.add(periodosNominaTmp.getFechaInicial());
                    valoresParametro.add(periodosNominaTmp.getFechaFinal());
                } else {
                    valoresParametro.add(((Calendar) valoresConceptosEmpleados.get(parametroFechaInicial)).getTime());
                    valoresParametro.add(((Calendar) valoresConceptosEmpleados.get(parametroFechaFinal)).getTime());
                }
            } else {
                valoresParametro.add(fechaInicial);
                valoresParametro.add(fechaFinal);
            }
            camposParametro.add("razonSocial");
            valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
            strQuery.delete(0, strQuery.length()).append("Select a From Asistencias a Inner Join a.tipoNomina tn Inner Join a.empleados em INNER JOIN a.razonesSociales rs Inner Join a.excepciones ex  Inner Join a.periodosNomina p ");
            strQuery.append("Where em.clave = :claveEmpleado And tn.clave = :claveTipoNomina AND rs.clave  = :razonSocial And a.fecha between :fechaInicio And :fechaFin AND p.tipoCorrida.clave = :claveTipoCorrida ");
            listAsistencias = (List<Asistencias>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), null);
            listAsistencias = (listAsistencias == null ? new ArrayList<Asistencias>() : listAsistencias);
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(51);
                return listAsistencias;
            }
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(53);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerAsistencias()1_Error: ").append(ex));
        }
        return listAsistencias;
    }

    private List<VacacionesAplicacion> obtenerVacaciones(PeriodosNomina periodo, PlazasPorEmpleadosMov plazasPorEmpleadosMovEjecutandose, boolean isCorridaVacaciones) {
        List<VacacionesAplicacion> listVacacionesAplicacion = null;
        try {
            if (periodo == null) {
                return new ArrayList<VacacionesAplicacion>();
            }
            camposParametro.clear();
            valoresParametro.clear();

            if (isCorridaVacaciones) {
                strQuery.delete(0, strQuery.length());
                strQuery.append("Select va from VacacionesAplicacion va inner join va.vacacionesDisfrutadas vd inner join vd.empleados em  ");
                strQuery.append("inner join vd.periodoAplicacion pa  ");
                strQuery.append("where em.id = :idEmp AND (pa.fechaInicial BETWEEN :fechaInicial AND :fechaFinal) ");
                camposParametro.add("fechaInicial");
                valoresParametro.add(periodo.getFechaInicial());
                camposParametro.add("fechaFinal");
                valoresParametro.add(periodo.getFechaFinal());
            } else {
                if (fechaBajaFiniq != null) {
                    strQuery.delete(0, strQuery.length()).append("Select p ");
                    strQuery.append(" from PeriodosNomina p inner join p.tipoNomina  t inner join p.tipoCorrida  c ");
                    strQuery.append(" Where (:fecha BETWEEN p.fechaInicial AND p.fechaFinal + 1) ");
                    strQuery.append(" and t.clave = :claveTipoNomina and c.clave = :claveTipoCorrida  ");
                    PeriodosNomina periodosNominaTmp = (PeriodosNomina) ejecutaQueryUnico(strQuery.toString(), new String[]{"claveTipoNomina", "fecha", "claveTipoCorrida"},
                            new Object[]{valoresConceptosEmpleados.get("TipoNomina".toUpperCase()), fechaBajaFiniq.getTime(), valoresConceptosEmpleados.get("ClaveTipoCorrida".toUpperCase())});
                    if (periodosNominaTmp != null) {
                        valoresParametro.add(periodosNominaTmp.getId());
                    } else {
                        valoresParametro.add(periodo.getId());
                    }
                } else {
                    valoresParametro.add(periodo.getId());
                }
                strQuery.delete(0, strQuery.length());
                strQuery.append("Select va from VacacionesAplicacion va inner join va.vacacionesDisfrutadas vd inner join vd.empleados em  ");
                strQuery.append("inner join vd.periodoAplicacion pa  ");
                strQuery.append("where pa.id = :idPeriodo and em.id = :idEmp ");
                camposParametro.add("idPeriodo");
            }
            camposParametro.add("idEmp");
            valoresParametro.add(plazasPorEmpleadosMovEjecutandose.getPlazasPorEmpleado().getEmpleados().getId());
            listVacacionesAplicacion = (List<VacacionesAplicacion>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), 0);
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(71);
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerRegistraVacaciones()1_Error: "));
                return listVacacionesAplicacion;
            }
            camposParametro = null;
            valoresParametro = null;
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(71);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerRegistraVacaciones()2_Error: ").append(ex));
        }
        return listVacacionesAplicacion;
    }

    private List<RegistroIncapacidad> obtenerIncapacidadesPorEnfermedad(Date fechaInicial, Date fechaFinal) {
        List<RegistroIncapacidad> listRegistroIncapacidad = null;
        try {
            camposParametro = new ArrayList<String>(0);
            valoresParametro = new ArrayList<Object>(0);
            camposParametro.add("claveEmpleado");
            camposParametro.add("fechaInicio");
            camposParametro.add("fechaFin");
            camposParametro.add("razonSocial");
            valoresParametro.add(valoresConceptosEmpleados.get("NumEmpleado".toUpperCase()));
            valoresParametro.add(fechaInicial);
            valoresParametro.add(fechaFinal);
            valoresParametro.add(valoresConceptosEmpleados.get("RazonSocial".toUpperCase()));
            strQuery.delete(0, strQuery.length()).append("Select a From RegistroIncapacidad a Inner Join a.empleados em INNER JOIN a.empleados.razonesSociales rs ");
            strQuery.append("Where a.ramoSeguro = 1 and em.clave = :claveEmpleado  AND rs.clave  = :razonSocial And (a.fechaInicial between :fechaInicio And :fechaFin or a.fechaFinal between :fechaInicio And :fechaFin) Order by a.fechaInicial");
            listRegistroIncapacidad = (List<RegistroIncapacidad>) ejecutaQueryList(strQuery.toString(), camposParametro.toArray(new String[]{}), valoresParametro.toArray(), null);
            listRegistroIncapacidad = (listRegistroIncapacidad == null ? new ArrayList<RegistroIncapacidad>() : listRegistroIncapacidad);
            if (mensajeResultado.getNoError() == -100) {
                mensajeResultado.setNoError(51);
                return listRegistroIncapacidad;
            }
        } catch (HibernateException ex) {
            mensajeResultado.setNoError(53);
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerAsistencias()1_Error: ").append(ex));
        }
        return listRegistroIncapacidad;
    }

    public String textformateaValorAMascara(String valorAFormatear, String mascara) {
        try {
            DecimalFormat decimalFormat = new DecimalFormat(mascara.replace("#", "0"));
            valorAFormatear = decimalFormat.format(Double.valueOf(valorAFormatear));
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("textformateaValorAMascara()2_Error: ").append(ex));
        }
        return valorAFormatear;
    }

    public static String creaMascaraGenerica(String mascara) {
        String mascaraGenerica = "";
        if (mascara != null) {
            StringBuilder nuevaMascara = new StringBuilder();
            for (int i = 0; i < mascara.length(); i++) {
                if (mascara.charAt(i) == '#' | mascara.charAt(i) == 'U' | mascara.charAt(i) == 'L' | mascara.charAt(i) == 'A'
                        | mascara.charAt(i) == 'H' | mascara.charAt(i) == '?') {
                    nuevaMascara.append("*");
                } else {
                    nuevaMascara.append(mascara.charAt(i));
                }
            }
            mascaraGenerica = nuevaMascara.toString();
        }
        return mascaraGenerica;
    }

    private CalculoISR contruirISRRetenido(CalculoISR iSRRetenido) {
        iSRRetenido.setIsrRetenidoNormal(isrNormal);
        iSRRetenido.setIsrRetenidoDirecto(isrDirecto);
        iSRRetenido.setIsrRetenidoAnual(isrAnual);
        iSRRetenido.setIsrACargoAnual(retenido.getIsrACargoAnual());
        iSRRetenido.setIsrACargoDirecto(retenido.getIsrACargoDirecto());
        iSRRetenido.setIsrACargoNormal(retenido.getIsrACargoNormal());
        iSRRetenido.setIsrNetoAnual(retenido.getIsrNetoAnual());
        iSRRetenido.setIsrNetoDirecto(retenido.getIsrNetoDirecto());
        iSRRetenido.setIsrNetoNormal(retenido.getIsrNetoNormal());
        iSRRetenido.setIsrSubsidioAnual(retenido.getIsrSubsidioAnual());
        iSRRetenido.setIsrSubsidioDirecto(retenido.getIsrSubsidioDirecto());
        iSRRetenido.setIsrSubsidioNormal(retenido.getIsrSubsidioNormal());
        iSRRetenido.setSubsidioEmpleoAnual(retenido.getSubsidioEmpleoAnual());
        iSRRetenido.setSubsidioEmpleoDirecto(retenido.getSubsidioEmpleoDirecto());
        iSRRetenido.setSubsidioEmpleoNormal(retenido.getSubsidioEmpleoNormal());
        return iSRRetenido;
    }

    public Mensaje getParametrosYListCrucePorModuloYClaves(String claveModulo, Object[] clavesParametros) {//JSA03
        List<Object[]> listParametrosYListCruce = new ArrayList<Object[]>();
        try {
            inicializaVariableMensaje();
//            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            //getSession().beginTransaction();
            q = getSession().createQuery("from Parametros p where p.modulo.clave = :claveModulo and p.clave in(:clavesParametros)");
            q.setString("claveModulo", claveModulo);
            q.setParameterList("clavesParametros", clavesParametros);
            List<Parametros> listparametros = q.list();
            if (!listparametros.isEmpty()) {
                for (int i = 0; i < listparametros.size(); i++) {
                    List<Cruce> values;//Si el parametro no tiene seleccionado elementos de aplicacion quiere decir que no se va filtrar o profuncidar por algun elemento de aplicacion
                    if (listparametros.get(i).getElementosAplicacion() == null ? false : !listparametros.get(i).getElementosAplicacion().isEmpty()) {
                        q = getSession().createQuery("from Cruce c where c.parametros.clave = :parametro  and c.elementosaplicacion in (:values) order by c.elementosaplicacion.ordenId desc");
                        q.setParameter("parametro", listparametros.get(i).getClave());
                        q.setParameterList("values", listparametros.get(i).getElementosAplicacion());
                        values = q.list();
                    } else {
                        values = new ArrayList<Cruce>();
                    }
                    Object[] objects = new Object[2];
                    objects[0] = listparametros.get(i);
                    objects[1] = values;
                    listParametrosYListCruce.add(objects);
                    values = null;
                }
            }
            mensajeResultado.setResultado(listParametrosYListCruce);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            //getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getParametrosYListCrucePorModuloYClaves()1_Error: ").append(ex));
//            try {
//                if (getSession().getTransaction().isActive()) {
//                    getSession().getTransaction().rollback();
//                }
//                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
//                mensajeResultado.setError(ex.getLocalizedMessage());
//            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
//                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
//                mensajeResultado.setError(exc.getLocalizedMessage());
//            }
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    public Object valorParametroCruce(Parametros parametrosGlobal, List<Cruce> listCruce, boolean regresarValor/*true=valor,false=byte[]*/, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        Object valorParametros = null;
        try {
            if (regresarValor) {
                valorParametros = parametrosGlobal.getValor();
            } else {
                valorParametros = parametrosGlobal.getImagen();
            }
            if (listCruce.size() > 1) {
                Collections.sort(listCruce, new ordenaCruce());
            }
            for (int i = listCruce.size() - 1; i > -1; i--) {
                if (findClaveElementoAplicacion(listCruce.get(i).getElementosaplicacion().getEntidad(), listCruce.get(i).getClaveElemento(), listValoresElementosAplicacion)) {
                    if (regresarValor) {
                        valorParametros = listCruce.get(0).getValor();
                    } else {
                        valorParametros = listCruce.get(0).getImagen();
                    }
                    break;
                }
            }
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("valorParametroCruce()1_Error: ").append(ex));
        }
        return valorParametros;
    }

    private boolean findClaveElementoAplicacion(Class classElementoAplicacion, String valorCruce, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        boolean continuar = false;
        for (int i = 0; i < listValoresElementosAplicacion.size(); i++) {
            if (classElementoAplicacion.equals(listValoresElementosAplicacion.get(i).getClassElementoAplicacion())) {
                if (valorCruce.equalsIgnoreCase(listValoresElementosAplicacion.get(i).getValor())) {
                    continuar = true;
                }
            }
        }
        return continuar;
    }

    public Double parametroFactorAplicacionTablaAnual(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        Double topeHorasDoblesDiario = 3.0;
        String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        topeHorasDoblesDiario = Double.parseDouble(valorParametros);
        return topeHorasDoblesDiario;
    }

    public Object[] parametroPagosPorHora(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        Boolean manejarPagosXHora = false;
        List<Cruce> listCrucesPagosHoraaRegistroPatronal = new ArrayList<Cruce>();
        String valorParametroPagosPorHora = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        manejarPagosXHora = valorParametroPagosPorHora.equalsIgnoreCase("1");
        for (int i = 0; i < listCruces.size(); i++) {
            if (listCruces.get(i).getElementosaplicacion().getEntidad() == RegistroPatronal.class) {
                listCrucesPagosHoraaRegistroPatronal.add(listCruces.get(i));
            }
        }
        if (listCrucesPagosHoraaRegistroPatronal.size() > 0) {
            manejarPagosXHora = true;
        }
        return new Object[]{manejarPagosXHora, listCrucesPagosHoraaRegistroPatronal};
    }

    public ManejoHorasPor parametroManejarHorasPor(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        ManejoHorasPor tipoManejoDeHoraPor;
        String valorParametroHoraPor = "2";
        valorParametroHoraPor = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        if (valorParametroHoraPor.equalsIgnoreCase("1")) {
            tipoManejoDeHoraPor = ManejoHorasPor.HORASNATURALES;
        } else {
            tipoManejoDeHoraPor = ManejoHorasPor.HSM;
        }
        return tipoManejoDeHoraPor;
    }

    public ManejoSalarioDiario parametroManejarSalarioDiarioPor(Parametros parametros, List<Cruce> listCruces, boolean pagaDiasNaturales, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        ManejoSalarioDiario tipoManejoSalarioDiario = ManejoSalarioDiario.QUINCENAL;
        if (pagaDiasNaturales) {
            tipoManejoSalarioDiario = ManejoSalarioDiario.DIARIO;
        } else {
            String valorParametro = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
            if (valorParametro.equalsIgnoreCase("1")) {
                tipoManejoSalarioDiario = ManejoSalarioDiario.DIARIO;
            } else if (valorParametro.equalsIgnoreCase("2")) {
                tipoManejoSalarioDiario = ManejoSalarioDiario.SEMANAL;
            } else if (valorParametro.equalsIgnoreCase("4")) {
                tipoManejoSalarioDiario = ManejoSalarioDiario.MENSUAL;
            } else {//por default es quincenal = 3
                tipoManejoSalarioDiario = ManejoSalarioDiario.QUINCENAL;
            }
        }
        return tipoManejoSalarioDiario;
    }

    public TipoTablaISR parametroTipoTablaISRAUtilizar(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        TipoTablaISR tipoTablaISRTmp = TipoTablaISR.NORMAL;
        String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        //parametro tabla isr es normal, por periodicidad o anual
        if (valorParametros.equals(ClavesParametrosModulos.opcionParametroTablaISRPeriodicidad)) {
            tipoTablaISRTmp = TipoTablaISR.PERIODICIDAD;
        } else if (valorParametros.equals(ClavesParametrosModulos.opcionParametroTablaISRMensual)) {
            tipoTablaISRTmp = TipoTablaISR.NORMAL;
        }
        return tipoTablaISRTmp;
    }

    public Double parametroFactorAplicacionTablaMensual(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        Double factorTablaMensual = 30.4;
        String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        try {
            factorTablaMensual = Double.parseDouble(valorParametros);
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("parametroFactorAplicacionTablaMensual()1_Error: ").append(ex));
        }
        return factorTablaMensual;
    }

    public Integer parametroModoAjustarIngresosAlMes(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        Integer factorTablaMensual = 1;
        String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        try {
            factorTablaMensual = Integer.parseInt(valorParametros);
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("parametroModoAjustarIngresosAlMes()1_Error: ").append(ex));
        }
        return factorTablaMensual;
    }

    public DesgloseInternoISR parametroDesgloseInternoISR(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        DesgloseInternoISR desgloseInternoISR;
        String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        if (valorParametros.equalsIgnoreCase("1")) {
            desgloseInternoISR = DesgloseInternoISR.DESGLOSEISRNORMALANUAL;
        } else if (valorParametros.equalsIgnoreCase("2")) {
            desgloseInternoISR = DesgloseInternoISR.DESGLOSEISRNORMALDIRECTOANUAL;
        } else {
            desgloseInternoISR = DesgloseInternoISR.DESGLOSEISRANUAL;
        }
        return desgloseInternoISR;
    }

    public boolean pagarNominaDiasNaturales(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        boolean pagaDiasNaturales = false;
        String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        pagaDiasNaturales = valorParametros.equalsIgnoreCase("1");
        return pagaDiasNaturales;
    }

    public Integer parametroVersionCalculoPrestamoAhorro(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        Integer versionCalculo = 1;
        String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
        try {
            versionCalculo = Integer.parseInt(valorParametros);
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("parametroVersionCalculoPrestamoAhorro()1_Error: ").append(ex));
        }
        return versionCalculo;
    }

    public boolean pagarIMSSDiasNaturales(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        boolean pagarIMSS = false;
        try {
            String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
            pagarIMSS = valorParametros.equalsIgnoreCase("1");
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("parametroPagarIMSSDiasNaturales()1_Error: ").append(ex));
        }
        return pagarIMSS;
    }

    public boolean descontarFaltasModoAjustaMes(Parametros parametros, List<Cruce> listCruces, List<ValoresElementosAplicacion> listValoresElementosAplicacion) {
        boolean descontarFaltas = false;
        try {
            String valorParametros = (String) valorParametroCruce(parametros, listCruces, true, listValoresElementosAplicacion);
            descontarFaltas = valorParametros.equalsIgnoreCase("1");
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("descontarFaltasModoAjustaMes()1_Error: ").append(ex));
        }
        return descontarFaltas;
    }

    class ordenaCruce implements Comparator<Cruce> {

        @Override
        public int compare(Cruce o1, Cruce o2) {

            int resultado = Double.compare(o1.getElementosaplicacion().getOrdenId(), o2.getElementosaplicacion().getOrdenId());
            if (resultado != 0) {
                return resultado;
            }
            resultado = o1.getElementosaplicacion().getOrdenId().compareTo(o2.getElementosaplicacion().getOrdenId()) > 0 ? 1 : -1;
            if (resultado != 0) {
                return resultado;
            }
            return resultado;
        }
    }

    class ValoresElementosAplicacion {

        private Class classElementoAplicacion;
        private String valor;

        public ValoresElementosAplicacion() {
        }

        public ValoresElementosAplicacion(Class classElementoAplicacion, String valor) {
            this.classElementoAplicacion = classElementoAplicacion;
            this.valor = valor;
        }

        public Class getClassElementoAplicacion() {
            return classElementoAplicacion;
        }

        public void setClassElementoAplicacion(Class classElementoAplicacion) {
            this.classElementoAplicacion = classElementoAplicacion;
        }

        public String getValor() {
            return valor;
        }

        public void setValor(String valor) {
            this.valor = valor;
        }
    }

    enum TipoAcumulado {

        NORMAL, PERIODO, MENSUAL, BIMESTRAL, ANUAL;
    }

    enum TipoSueldos {

        SUELDODIARIOINICIAL, SUELDODIARIOFINAL, SUELDODIARIOVIGENTE, PERCEP_PLAZA, PERCEP_PLAZA_VIGENTE
    }
}
