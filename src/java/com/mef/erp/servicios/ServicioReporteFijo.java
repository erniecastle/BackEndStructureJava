/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Armando Fecha: 07-11-2014 Descripción: se agrego el
 * codigo para para hacer el logo dinamico.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.GenericHibernateDAO;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.Naturaleza;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.RegistroPatronal;
import com.mef.erp.modelo.entidad.TipoNomina;
import com.mef.erp.modelo.entidad.TipoReporteFijo;
import com.mef.erp.modelo.entidad.ZonaGeografica;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.query.JRHibernateQueryExecuterFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Ernesto
 */
public class ServicioReporteFijo extends GenericHibernateDAO<ServicioReporteFijo, Long> implements ServicioReporteFijoIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_C_").append(this.getClass().getName()).append(".").toString();
    Mensaje mensajeResultado;

    private String nombreComplemento = "";

    /*byte[]*/
    public Mensaje loadReportesFijos(String ruta, TipoReporteFijo nombreReporte,
            Map<String, Object> valoresFiltro, Map<String, Object> valoresTemplate,
            Map<String, Object> valoresSubreporte1, boolean agrupar, Object[] exportaReporte, String uuidCxn) {
        mensajeResultado = new Mensaje();
        mensajeResultado.setError("");
        mensajeResultado.setNoError(0);
        /*usado para acompletar el nombre del template en caso de ocuparlo*/
        byte[] serializedPrint = null;
        boolean errorImagen = false;
        Boolean datosExcel = false;
        if (nombreReporte.equals(TipoReporteFijo.RESUMENINCIDENCIAS_A_EXCEL)) {
            datosExcel = REPORTEINCIDENCIAS_A_EXCEL(ruta, valoresFiltro, uuidCxn);
        } else if (nombreReporte.equals(TipoReporteFijo.EXPORTARACUMULADOSBASES_A_EXCEL)) {
            boolean incluirPercepciones = (Boolean) valoresFiltro.get("incluirPercepciones"), incluirDeducciones = (Boolean) valoresFiltro.get("incluirDeducciones"),
                    incluirTotalPercepciones = (Boolean) valoresFiltro.get("incluirTotalPercepciones"), incluirTotalDeducciones = (Boolean) valoresFiltro.get("incluirTotalDeducciones"), incluirParteGravable_Exenta = (Boolean) valoresFiltro.get("incluirParteGravable_Exenta");
            datosExcel = REPORTEACUMULADOSNOMINA_A_EXCEL(ruta, valoresFiltro, incluirPercepciones, incluirDeducciones, incluirTotalPercepciones, incluirTotalDeducciones, incluirParteGravable_Exenta, uuidCxn);
        } else if (nombreReporte.equals(TipoReporteFijo.EXPORTARACUMULADOSDIMM)) {
            datosExcel = REPORTEACUMULADOSDIMM(ruta, valoresFiltro, uuidCxn);
        } else {
            nombreComplemento = "";
            if (valoresFiltro.containsKey("NombreComplemento")) {
                nombreComplemento = valoresFiltro.get("NombreComplemento") == null ? "" : valoresFiltro.get("NombreComplemento").toString();
            }

            //***************************
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream((byte[]) valoresFiltro.get("REPORT_LOCALE"));
            ObjectInputStream objectInputStream;
            Locale locale = null;
            try {
                objectInputStream = new ObjectInputStream(byteArrayInputStream);
                locale = (Locale) objectInputStream.readObject();
            } catch (IOException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos()1_Error: ").append(ex));
                excepcionMensage(ex);
            } catch (ClassNotFoundException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos()2_Error: ").append(ex));
                excepcionMensage(ex);
            }
            if (locale == null) {
                locale = new Locale("es", "MX");
            }
            InputStream myInputStream = new ByteArrayInputStream((byte[]) valoresFiltro.get("Logo"));//JSA01
            try {
                BufferedImage image = ImageIO.read(myInputStream);
                valoresFiltro.put("TituloLogo", image);
                errorImagen = false;
            } catch (IOException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos()2_Error: ").append(ex));
                valoresFiltro.put("TituloLogo", null);
                errorImagen = true;
            }
            valoresFiltro.put(JRParameter.REPORT_LOCALE, locale);
            mensajeResultado = crearReporte(ruta, nombreReporte, valoresFiltro, valoresTemplate, valoresSubreporte1, agrupar, exportaReporte, uuidCxn);
        }
        JasperPrint print = null;

        if (nombreReporte.equals(TipoReporteFijo.EXPORTARACUMULADOSBASES_A_EXCEL) || nombreReporte.equals(TipoReporteFijo.RESUMENINCIDENCIAS_A_EXCEL)) {
            if (mensajeResultado.getNoError() == 0) {
                if (datosExcel) {
                    /*codigo para abrir y mandar archivo excel a el cliente*/
                    File file = new File(ruta);
                    byte[] bFile = new byte[(int) file.length()];
                    try {
                        FileInputStream fileInputStream = new FileInputStream(file);
                        fileInputStream.read(bFile);
                        fileInputStream.close();
                    } catch (Exception e) {
                        System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos()1convertirFileToByte_Error: ").append(e));
                        excepcionMensage(e);
                        bFile = null;
                    }
                    if (bFile != null) {
                        mensajeResultado.setResultado(bFile);
                    }
                } else {
                    mensajeResultado.setResultado(false);
                }
            }
        } else {
            if (mensajeResultado.getNoError() == 0) {
                if (exportaReporte == null) {
                    print = (JasperPrint) mensajeResultado.getResultado();
                    if (print != null) {
                        serializedPrint = convertirJasperPrintToByte(print);
                        if (mensajeResultado.getNoError() == 0) {
                            mensajeResultado.setResultado(serializedPrint);
                            if (errorImagen) {
                                mensajeResultado.setError("NO_IMAGEN");
                            }
                        }
                    }
                }
            }
        }

        return mensajeResultado;
    }
    /*JasperPrint*/

    private Mensaje crearReporte(String ruta, TipoReporteFijo nombreReporte,
            Map<String, Object> valoresFiltro, Map<String, Object> valoresTemplate,
            Map<String, Object> valoresSubreporte1, boolean agrupar, Object[] exportaReporte, String uuidCxn) {
        JasperPrint print = null;
        mensajeResultado = new Mensaje();
        mensajeResultado.setError("");
        mensajeResultado.setNoError(0);
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            JasperReport report = null, subReport1 = null;
            valoresFiltro.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, getSession());
            valoresFiltro.put("SUBREPORT_DIR", ruta);
            if (nombreReporte.equals(TipoReporteFijo.REPORTENOMINA) && agrupar) {
                subReport1 = JasperCompileManager.compileReport(
                        cambiarSubreportes("Template_Subreport1.jrxml", ruta, valoresSubreporte1));
            }
            if (subReport1 != null) {
                valoresFiltro.put("Template_Subreport1", subReport1);
            }
            InputStream is = null;
            if (exportaReporte == null || exportaReporte.length == 0) {
                is = cambiarAgrupacion(ruta, valoresTemplate, agrupar);
                report = JasperCompileManager.compileReport(is);
                print = JasperFillManager.fillReport(report, valoresFiltro);
                if (mensajeResultado.getNoError() == 0) {
                    mensajeResultado.setResultado(print);
                }
            } else {
                if (mensajeResultado.getNoError() == 0) {
                    List<byte[]> fileBytes = new ArrayList<byte[]>();
                    if (exportaReporte[0].equals("PDF")) {
                        if (exportaReporte[1].equals("individual")) {
                            List<String[]> keys = clavesEmpleadosImplica(valoresFiltro, uuidCxn);
                            if (keys != null) {
                                for (int i = 0; i < keys.size(); i++) {
                                    valoresFiltro.put("PDFPorEmpleado", " AND emp.clave = '" + ((Object[]) keys.get(i))[0].toString() + "'");
                                    is = cambiarAgrupacion(ruta, valoresTemplate, agrupar);
                                    report = JasperCompileManager.compileReport(is);
                                    print = JasperFillManager.fillReport(report, valoresFiltro);
                                    //print.get
                                    byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
                                    fileBytes.add(pdfBytes);
                                    is = null;
                                }
                            }
                            Object[] envioServidor = new Object[2];
                            if (mensajeResultado.getNoError() == 0) {
                                envioServidor[0] = keys;
                                envioServidor[1] = fileBytes;
                                mensajeResultado.setResultado(envioServidor);
                            }
                        } else if (exportaReporte[1].equals("masivo")) {
                            is = cambiarAgrupacion(ruta, valoresTemplate, agrupar);
                            report = JasperCompileManager.compileReport(is);
                            print = JasperFillManager.fillReport(report, valoresFiltro);
                            byte[] pdfBytes = JasperExportManager.exportReportToPdf(print);
                            fileBytes.add(pdfBytes);
                            if (mensajeResultado.getNoError() == 0) {
                                mensajeResultado.setResultado(fileBytes);
                            }
                        }
                    }
                }
            }

        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos()1_Error: ").append(ex));
            excepcionMensage(ex);
        }
        return mensajeResultado;
    }

    private Boolean REPORTEACUMULADOSNOMINA_A_EXCEL(String ruta, Map<String, Object> valoresFiltro, boolean incluirPercepciones, boolean incluirDeducciones,
            boolean incluirTotalPercepciones, boolean incluirTotalDeducciones, boolean incluirParteGravable_Exenta, String uuidCxn) {
        Boolean existeInfo = false;
        StringBuilder builder = new StringBuilder();
        List<Object> listValue = null, listTituloConcepto = null;

        builder.append("select e.clave, CASE WHEN (e.apellidoMaterno IS NULL)  THEN concat(e.apellidoPaterno,' ', e.nombre) ELSE  concat(e.apellidoPaterno,' ', e.apellidoMaterno,' ', e.nombre) END, e.RFC, e.CURP, e.IMSS, pto.descripcion AS PUESTO, ing.fechaIngreso, ing.fechaBaja ");
        //builder.append("select e.clave,concat(e.apellidoPaterno,' ', e.apellidoMaterno,' ', e.nombre), e.RFC, e.CURP, e.IMSS, pto.descripcion AS PUESTO, ing.fechaIngreso, ing.fechaBaja ");
        builder.append(", (select CASE WHEN (SUM(cu.diasTrabajados) IS NULL)  THEN 0.0 ELSE SUM(cu.diasTrabajados) END from CalculoUnidades cu inner join cu.plazasPorEmpleado plCu  inner join cu.periodosNomina cup where plCu.id = pl.id and (cup.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR cup.fechaFinal BETWEEN :fechaInicial AND  :fechaFinal)) as diasTrabajados ");//JEVC02
        // builder.append(", (select CASE WHEN  (COUNT(ex)  = 0) THEN 0.0 ELSE count(ex.excepcion)   END from Asistencias a inner join a.periodosNomina p inner join a.empleados e2 inner join a.excepciones ex where (p.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and ex.clave in  (:valoresExcepciones) and e2.clave = e.clave ) as diasDescontar ");
        builder.append(", CASE WHEN (pm.salarioPor IS 1) THEN pm.puestos.salarioTabular ELSE pm.importe  END ");
        Integer posicion = 9;
        if (incluirDeducciones | incluirPercepciones | incluirParteGravable_Exenta) {
            builder.append(",c.naturaleza, c.descripcion ");
            posicion++;//posicion de la naturaleza
            posicion++;//posicion de la descripcion del concepto
        }
        HashMap posicionDatos = new HashMap();
        if (incluirPercepciones) {
            //<editor-fold defaultstate="collapsed" desc="importePercepcion">
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_PERCEPCION, posicion);
            builder.append(", CASE WHEN (c.naturaleza IS NULL) THEN 0.0 else (CASE WHEN (c.naturaleza = 0) then (CASE WHEN (SUM(mov.resultado) IS NULL) THEN 0.0 ELSE SUM(mov.resultado) END) else 0.0 END) END as importePercepcion ");
            //</editor-fold>
        }
        if (incluirParteGravable_Exenta) {
            //<editor-fold defaultstate="collapsed" desc="importeGravadoPercepcion">
            builder.append(", CASE WHEN (c.naturaleza IS NULL) THEN 0.0 else (CASE WHEN (c.naturaleza = 0) then (CASE WHEN (SUM(movafec.resultado) IS NULL) THEN 0.0 ELSE SUM(movafec.resultado) END) else 0.0 END) END as importeGravadoPercepcion ");
            //</editor-fold>                
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_PERCEPCION_GRAVABLE, posicion);
            //<editor-fold defaultstate="collapsed" desc="importeExentoPercepcion">
            builder.append(", CASE WHEN (c.naturaleza IS NULL) THEN 0.0 else (CASE WHEN (c.naturaleza = 0) then (CASE WHEN (SUM(movafec.resultadoExento) IS NULL) THEN 0.0 ELSE SUM(movafec.resultadoExento) END) else 0.0 END) END as importeExentoPercepcion ");
            //</editor-fold>
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_PERCEPCION_EXENTO, posicion);
        }
        if (incluirTotalPercepciones) {
            //<editor-fold defaultstate="collapsed" desc="TotalPercepciones">
            builder.append(", (select CASE WHEN (SUM(mov2.resultado) IS NULL) THEN 0.0 ELSE SUM(mov2.resultado) END ");
            builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
            builder.append("where (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) AND c2.naturaleza = 0 and e2.clave=e.clave ) as TotalPercepciones ");
            //</editor-fold>
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_TOTAL_PERCEPCION, posicion);
        }
        if (incluirDeducciones) {
            //<editor-fold defaultstate="collapsed" desc="importeDeduccion">
            builder.append(", CASE WHEN (c.naturaleza IS NULL) THEN 0.0 else (CASE WHEN (c.naturaleza = 1) then (CASE WHEN (SUM(mov.resultado) IS NULL) THEN 0.0 ELSE SUM(mov.resultado) END) else 0.0 END) END as importeDeduccion ");
            //</editor-fold>
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_DEDUCCION, posicion);
        }
        if (incluirParteGravable_Exenta) {
            //<editor-fold defaultstate="collapsed" desc="importeGravadoDeduccion">
            builder.append(", CASE WHEN (c.naturaleza IS NULL) THEN 0.0 else (CASE WHEN (c.naturaleza = 1) then (CASE WHEN (SUM(movafec.resultado) IS NULL) THEN 0.0 ELSE SUM(movafec.resultado) END) else 0.0 END) END as importeGravadoDeduccion ");
            //</editor-fold>
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_DEDUCCION_GRAVABLE, posicion);
            //<editor-fold defaultstate="collapsed" desc="importeExentoDeducion">
            builder.append(", CASE WHEN (c.naturaleza IS NULL) THEN 0.0 else (CASE WHEN (c.naturaleza = 1) then (CASE WHEN (SUM(movafec.resultadoExento) IS NULL) THEN 0.0 ELSE SUM(movafec.resultadoExento) END) else 0.0 END) END as importeExentoDeducion ");
            //</editor-fold>
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_DEDUCCION_EXENTO, posicion);
        }
        if (incluirTotalDeducciones) {
            //<editor-fold defaultstate="collapsed" desc="TotalDeducciones">
            builder.append(", (select CASE WHEN (SUM(mov2.resultado) IS NULL) THEN 0.0 ELSE SUM(mov2.resultado) END ");
            builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
            builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) AND c2.naturaleza = 1 and e2.clave=e.clave ) as TotalDeducciones ");
            //</editor-fold>
            posicion++;
            posicionDatos.put(PosicionDatosEspeciales.INCLUIR_TOTAL_DEDUCCION, posicion);
        }
        //<editor-fold defaultstate="collapsed" desc="NETO">
        builder.append(", (SELECT CASE WHEN (COUNT(mov2) = 0) THEN 0.0 ELSE (SUM(CASE WHEN (c2.naturaleza = 0) then mov2.resultado ELSE 0.0 END - CASE WHEN (c2.naturaleza = 1) then mov2.resultado  ELSE 0.0 END) ) END ");
        builder.append("FROM MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
        builder.append("where (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) AND (c2.naturaleza = 0 OR c2.naturaleza = 1 ) AND  e2.clave = e.clave ) as NETO ");
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="INGRESOGRAVADO">
        builder.append(", (select CASE WHEN (SUM(movafec2.resultado) IS NULL) THEN 0.0 ELSE SUM(movafec2.resultado) END ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 left outer  join  mov2.movNomBaseAfecta movafec2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.clave=e.clave ) AS INGRESOGRAVADO ");
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="INGRESOEXENTO">
        builder.append(", (select CASE WHEN (SUM(movafec2.resultadoExento) IS NULL) THEN 0.0 ELSE SUM(movafec2.resultadoExento) END ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 left outer  join  mov2.movNomBaseAfecta movafec2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.clave=e.clave ) AS INGRESOEXENTO ");
        //</editor-fold>        
        //<editor-fold defaultstate="collapsed" desc="SUBSIDIOALEMPLEO">
        builder.append(", (select SUM(mov2.resultado) ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.clave=e.clave and c2.formulaConcepto = 'SUBSEEMPLEOCALCULADO' ) as SUBSIDIOALEMPLEO ");
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="DIFXSUBSIDIOALEMPLEO">
        builder.append(", (select SUM(mov2.resultado) ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.clave=e.clave and c2.formulaConcepto = 'ISRSUBSIDIO' ) as DIFXSUBSIDIOALEMPLEO ");
        //</editor-fold>        
        //<editor-fold defaultstate="collapsed" desc="ISRRETENIDO">
        builder.append(", (select SUM(mov2.resultado) ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.clave=e.clave  and c2.formulaConcepto = 'CALCULOISR' ) as ISRRETENIDO ");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="queryPrincipal">
        builder.append("from MovNomConcep mov inner join mov.concepNomDefi c inner join mov.periodosNomina p inner join mov.empleado e left outer  join mov.movNomBaseAfecta movafec  inner join mov.razonesSociales r ");
        builder.append("inner join mov.tipoNomina t ");
        builder.append(", PlazasPorEmpleadosMov pm INNER JOIN pm.plazasPorEmpleado pl inner join pl.empleados em ");
        builder.append("inner join pm.puestos pto inner join pl.registroPatronal regis ,IngresosReingresosBajas ing inner join ing.empleados ingEmp ");
        builder.append("where  (p.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p.fechaFinal BETWEEN :fechaInicial AND  :fechaFinal) ");
        builder.append("AND (c.naturaleza = 0 or c.naturaleza=1) ");
        builder.append("and e.id=em.id ");
        builder.append("and e.id=ingEmp.id ");
        builder.append("and mov.resultado is not null ");
        builder.append("and mov.resultado > 0 ");
        if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) & valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
            builder.append("and e.clave BETWEEN :inicioEmpleado AND :finalEmpleado ");
        } else if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName())) {
            builder.append("and e.clave >= :inicioEmpleado ");
        } else if (valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
            builder.append("and e.clave <= :finalEmpleado ");
        }
        if (valoresFiltro.containsKey(TipoNomina.class.getSimpleName())) {
            builder.append("and t.clave = :tipoNomina ");
        }
        if (valoresFiltro.containsKey(RegistroPatronal.class.getSimpleName())) {
            builder.append("and regis.clave = :registroPatronal ");
        }
        builder.append("and r.clave = :clave").append(RazonesSociales.class.getSimpleName()).append(" ");
        builder.append("AND pm.id IN (SELECT x0.id FROM PlazasPorEmpleadosMov x0 LEFT OUTER JOIN x0.plazasPorEmpleado x1 LEFT OUTER JOIN x1.razonesSociales x2 ");
        builder.append("LEFT OUTER JOIN x1.empleados x3 WHERE x2.clave = :clave").append(RazonesSociales.class.getSimpleName()).append(" AND x0.id IN (SELECT x0X.id FROM PlazasPorEmpleadosMov x0X LEFT OUTER JOIN x0X.plazasPorEmpleado x1X ");
        builder.append("LEFT OUTER JOIN x1X.empleados x3X WHERE (:fechaFinal >= x0X.fechaInicial) AND x3.clave = x3X.clave)) ");
        builder.append("GROUP BY ");
        builder.append("e.clave, e.apellidoPaterno, e.apellidoMaterno, e.nombre, e.RFC, e.CURP, e.IMSS, pto.descripcion, ing.fechaIngreso, ing.fechaBaja , pm.salarioPor, pto.salarioTabular, pm.importe ");
        builder.append(", c.clave, c.descripcion, c.naturaleza, pl.id, p.id ");
        builder.append("order by e.clave, c.clave");
        //</editor-fold>
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            org.hibernate.Query q = getSession().createQuery(builder.toString());
            q.setParameter("clave" + RazonesSociales.class.getSimpleName(), valoresFiltro.get(RazonesSociales.class.getSimpleName()));
            q.setParameter("fechaInicial", valoresFiltro.get("fechaInicial"));
            q.setParameter("fechaFinal", valoresFiltro.get("fechaFinal"));
            if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) & valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                q.setParameter("inicioEmpleado", valoresFiltro.get("inicio" + Empleados.class.getSimpleName()));
                q.setParameter("finalEmpleado", valoresFiltro.get("final" + Empleados.class.getSimpleName()));
            } else if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) | valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName())) {
                    q.setParameter("inicioEmpleado", valoresFiltro.get("inicio" + Empleados.class.getSimpleName()));
                } else {
                    q.setParameter("finalEmpleado", valoresFiltro.get("final" + Empleados.class.getSimpleName()));
                }
            }
            if (valoresFiltro.containsKey(TipoNomina.class.getSimpleName())) {
                q.setParameter("tipoNomina", valoresFiltro.get(TipoNomina.class.getSimpleName()));
            }
            if (valoresFiltro.containsKey(RegistroPatronal.class.getSimpleName())) {
                q.setParameter("registroPatronal", valoresFiltro.get(RegistroPatronal.class.getSimpleName()));
            }
//            Object[] valoresExcepciones = new Object[]{ClavesParametrosModulos.claveExcepcionFalta,
//                ClavesParametrosModulos.claveExcepcionAusentismo,
//                ClavesParametrosModulos.claveExcepcionPermisoConSueldo,
//                ClavesParametrosModulos.claveExcepcionPermisoSinSueldo,
//                ClavesParametrosModulos.claveExcepcionIncapacidadPorEnfermedad,
//                ClavesParametrosModulos.claveExcepcionIncapacidadPorAccidente,
//                ClavesParametrosModulos.claveExcepcionIncapacidadPorMaternidad};
//            q.setParameterList("valoresExcepciones", valoresExcepciones);

            listValue = q.list();
            if (listValue.isEmpty()) {
                existeInfo = false;
            } else {
                existeInfo = true;
                //<editor-fold defaultstate="collapsed" desc="ObtenerConceptosImplicados">
                builder.delete(0, builder.length());
                builder.append("select c.naturaleza, c.descripcion ");
                builder.append("from MovNomConcep mov inner join mov.concepNomDefi c inner join mov.periodosNomina p inner join mov.empleado e left outer  join mov.movNomBaseAfecta movafec  inner join mov.razonesSociales r ");
                builder.append("inner join mov.tipoNomina t ");
                builder.append(", PlazasPorEmpleadosMov pm INNER JOIN pm.plazasPorEmpleado pl inner join pl.empleados em ");
                builder.append("inner join pl.registroPatronal regis ,IngresosReingresosBajas ing inner join ing.empleados ingEmp ");
                builder.append("where  (p.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) ");
                builder.append("AND (c.naturaleza = 0 or c.naturaleza = 1) and e.id=em.id and e.id=ingEmp.id and mov.resultado is not null and mov.resultado > 0  ");
                if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) & valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                    builder.append("and e.clave BETWEEN :inicioEmpleado AND :finalEmpleado ");
                } else if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName())) {
                    builder.append("and e.clave >= :inicioEmpleado ");
                } else if (valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                    builder.append("and e.clave <= :finalEmpleado ");
                }
                if (valoresFiltro.containsKey(TipoNomina.class.getSimpleName())) {
                    builder.append("and t.clave = :tipoNomina ");
                }
                if (valoresFiltro.containsKey(RegistroPatronal.class.getSimpleName())) {
                    builder.append("and regis.clave = :registroPatronal ");
                }
                builder.append("and r.clave = :clave").append(RazonesSociales.class.getSimpleName()).append(" ");
                builder.append("AND pm.id IN (SELECT x0.id FROM PlazasPorEmpleadosMov x0 LEFT OUTER JOIN x0.plazasPorEmpleado x1 LEFT OUTER JOIN x1.razonesSociales x2 LEFT OUTER JOIN x1.empleados x3 ");
                builder.append("WHERE x2.clave = :clave").append(RazonesSociales.class.getSimpleName()).append(" AND x0.id IN (SELECT x0X.id FROM PlazasPorEmpleadosMov x0X LEFT OUTER JOIN x0X.plazasPorEmpleado x1X LEFT OUTER JOIN x1X.empleados x3X WHERE ( :fechaFinal >= x0X.fechaInicial) AND x3.clave = x3X.clave) ) ");
                builder.append("GROUP BY c.naturaleza, c.clave, c.descripcion ");
                builder.append("order by  c.naturaleza,c.clave ");
                //</editor-fold>
                q = getSession().createQuery(builder.toString());
                q.setParameter("clave" + RazonesSociales.class.getSimpleName(), valoresFiltro.get(RazonesSociales.class.getSimpleName()));
                q.setParameter("fechaInicial", valoresFiltro.get("fechaInicial"));
                q.setParameter("fechaFinal", valoresFiltro.get("fechaFinal"));
                if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) & valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                    q.setParameter("inicioEmpleado", valoresFiltro.get("inicio" + Empleados.class.getSimpleName()));
                    q.setParameter("finalEmpleado", valoresFiltro.get("final" + Empleados.class.getSimpleName()));
                } else if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) | valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                    if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName())) {
                        q.setParameter("inicioEmpleado", valoresFiltro.get("inicio" + Empleados.class.getSimpleName()));
                    } else {
                        q.setParameter("finalEmpleado", valoresFiltro.get("final" + Empleados.class.getSimpleName()));
                    }
                }
                if (valoresFiltro.containsKey(TipoNomina.class.getSimpleName())) {
                    q.setParameter("tipoNomina", valoresFiltro.get(TipoNomina.class.getSimpleName()));
                }
                if (valoresFiltro.containsKey(RegistroPatronal.class.getSimpleName())) {
                    q.setParameter("registroPatronal", valoresFiltro.get(RegistroPatronal.class.getSimpleName()));
                }
                listTituloConcepto = q.list();
                getSession().getTransaction().commit();
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("REPORTEACUMULADOSNOMINA_A_EXCEL()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
            existeInfo = false;
        }
        if (!listValue.isEmpty()) {
            crearExcel(ruta, valoresFiltro, listValue, listTituloConcepto, incluirPercepciones, incluirDeducciones, incluirTotalPercepciones, incluirTotalDeducciones, incluirParteGravable_Exenta, posicionDatos);
        }
        return existeInfo;
    }

    private Boolean REPORTEACUMULADOSDIMM(String ruta, Map<String, Object> valoresFiltro, String uuidCxn) {
        Boolean existeInfo = false;//Checar
        StringBuilder builder = new StringBuilder();
        List<Object> listValue = null;

        builder.append("select ing.fechaIngreso,ing.fechaBaja,e.RFC,e.CURP,e.apellidoPaterno,e.apellidoMaterno,e.nombre,pm.zonaGeografica,max('CAM9'),max('CAM10'),max('CAM11'),max('CAM12'),pm.tipoContrato.clave,pm.regimenContratacion,");
        builder.append("r.estados.clave,max('RFC16'),max('RFC17'),max('RFC18'),max('RFC19'),max('RFC20'),max('RFC21'),max('RFC22'),max('RFC23'),max('RFC24'),max('RFC25'),max('CAMP26'),max('CAM27'),max('CAM28'),max('CAM29'),");
        //<editor-fold defaultstate="collapsed" desc="TIENE FINIQUITOS O LIQUIDACION">
        builder.append("(select COUNT(finiqui) from FiniquitosLiquidaciones finiqui inner join finiqui.empleados e2 ");
        builder.append("where finiqui.modoBaja=0 and finiqui.status <5 and e2.id=e.id) as finiquito_liquidacion,");
//</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="tipoNomina.asimiladosAsalarios">
        builder.append("mov.tipoNomina.asimiladosAsalarios as asimilado ,");
        //</editor-fold>
        builder.append("(CASE WHEN (SUM(mov) > 0) THEN 1 ELSE 2 end ) as PAGODELPATRON,max('CAM31'),max('CAM32'),max('CAM33'),max('CAM36'),max('CAM37'),max('CAM38'),max('CAM39'),max('CAM40'),");
        builder.append("max('CAM41'),max('CAM42'),max('CAM43'),max('CAM44'),max('CAM45'),max('CAM46'),max('CAM47'),max('CAM48'),max('CAM49'),max('CAM50'),");
        //<editor-fold defaultstate="collapsed" desc="Ingresos asimilados">
        builder.append("(SUM(CASE WHEN (mov.tipoNomina.asimiladosAsalarios = 1) THEN mov.resultado ELSE 0.0 end)) as ingresosAsimiladosASalario ,");
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="ISRRETENIDOASIMILADO">
        builder.append("(select SUM(CASE WHEN (tn2.asimiladosAsalarios = 1) THEN mov2.resultado ELSE 0.0 end) ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 inner join mov2.tipoNomina tn2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.id=e.id  and c2.formulaConcepto = 'CALCULOISR' and tn2=mov.tipoNomina ) as ISRRETENIDOASIMILADO ,");
        //</editor-fold>

        builder.append("0,max('CAM54'),max('CAM55'),max('CAM56'),max('CAM57'),");
        builder.append("CASE WHEN (SUM(movafec.resultado) IS NULL) THEN 0.0 ELSE SUM(movafec.resultado) END as importeGravada,");
        builder.append("CASE WHEN (SUM(movafec.resultadoExento) IS NULL) THEN 0.0 ELSE SUM(movafec.resultadoExento) END as importeExento,");
        builder.append("max('CAM114'),max('CAM115'),max('CAM116'),max('CAM117'),max('CAM118'),max('CAM119'),max('CAM120'),max('CAM121'),");
        builder.append("max('CAM122'),max('CAM123'),max('CAM124'),max('CAM125'),max('CAM126'),");
        builder.append("max('CAM127'),max('CAM128'),max('CAM129'),max('CAM130'),");
        builder.append("max('CAM131'),max('CAM132'),");
        //<editor-fold defaultstate="collapsed" desc="ISRRETENIDO">
        builder.append("(select SUM(mov2.resultado) ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.id=e.id  and c2.formulaConcepto = 'CALCULOISR' ) as ISRRETENIDO ,");
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="DIFXSUBSIDIOALEMPLEO">
        builder.append("(select SUM(mov2.resultado) ");
        builder.append("from MovNomConcep mov2 inner join mov2.concepNomDefi c2 inner join mov2.periodosNomina p2 inner join mov2.empleado e2 ");
        builder.append("where  (p2.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p2.fechaFinal BETWEEN :fechaInicial AND :fechaFinal) and e2.id=e.id and c2.formulaConcepto = 'ISRSUBSIDIO' ) as DIFXSUBSIDIOALEMPLEO ");
        //</editor-fold> 

        //<editor-fold defaultstate="collapsed" desc="queryPrincipal">
        builder.append("from MovNomConcep mov inner join mov.periodosNomina p inner join mov.concepNomDefi c inner join mov.empleado e left outer  join mov.movNomBaseAfecta movafec  inner join mov.razonesSociales r ");
        builder.append(", PlazasPorEmpleadosMov pm INNER JOIN pm.plazasPorEmpleado pl inner join pl.empleados em ");
        builder.append(",IngresosReingresosBajas ing inner join ing.empleados ingEmp ");
        builder.append("where  (p.fechaInicial BETWEEN :fechaInicial AND :fechaFinal OR p.fechaFinal BETWEEN :fechaInicial AND  :fechaFinal) ");
        builder.append("and e.id=em.id ");
        builder.append("and e.id=ingEmp.id ");
        builder.append("and mov.resultado is not null ");
        builder.append("and mov.resultado > 0 ");
        //cehcar es opcional
        if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) & valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
            builder.append("and e.clave BETWEEN :inicioEmpleado AND :finalEmpleado ");
        } else if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName())) {
            builder.append("and e.clave >= :inicioEmpleado ");
        } else if (valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
            builder.append("and e.clave <= :finalEmpleado ");
        }
        builder.append("and r.clave = :clave").append(RazonesSociales.class.getSimpleName()).append(" ");
        builder.append("AND pm.id IN (SELECT x0.id FROM PlazasPorEmpleadosMov x0 LEFT OUTER JOIN x0.plazasPorEmpleado x1 LEFT OUTER JOIN x1.razonesSociales x2 ");
        builder.append("LEFT OUTER JOIN x1.empleados x3 WHERE x2.clave = :clave").append(RazonesSociales.class.getSimpleName()).append(" AND x0.id IN (SELECT MAX (x0X.id) FROM PlazasPorEmpleadosMov x0X LEFT OUTER JOIN x0X.plazasPorEmpleado x1X ");
        builder.append("LEFT OUTER JOIN x1X.empleados x3X WHERE (:fechaFinal >= x0X.fechaInicial) AND x3.id = x3X.id AND  YEAR(x0X.fechaInicial) = :anio))  and e.id= 1 ");
        builder.append("GROUP BY ");
        builder.append("ing.fechaIngreso, ing.fechaBaja ,e.RFC, e.CURP,e.id,e.apellidoPaterno,e.apellidoMaterno,e.nombre,pm.zonaGeografica,pm.tipoContrato.clave,pm.regimenContratacion,");
        builder.append("r.estados.clave,pm,mov.tipoNomina.id,mov.tipoNomina.asimiladosAsalarios ");
        builder.append("order by e.id");
        //</editor-fold>
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            System.out.println("query " + builder.toString());
            Query q = getSession().createQuery(builder.toString());
            q.setParameter("clave" + RazonesSociales.class.getSimpleName(), valoresFiltro.get(RazonesSociales.class.getSimpleName()));
            q.setParameter("fechaInicial", valoresFiltro.get("fechaInicial"));
            q.setParameter("fechaFinal", valoresFiltro.get("fechaFinal"));
            q.setParameter("anio", valoresFiltro.get("ejercicio"));
            if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) & valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                q.setParameter("inicioEmpleado", valoresFiltro.get("inicio" + Empleados.class.getSimpleName()));
                q.setParameter("finalEmpleado", valoresFiltro.get("final" + Empleados.class.getSimpleName()));
            } else if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName()) | valoresFiltro.containsKey("final" + Empleados.class.getSimpleName())) {
                if (valoresFiltro.containsKey("inicio" + Empleados.class.getSimpleName())) {
                    q.setParameter("inicioEmpleado", valoresFiltro.get("inicio" + Empleados.class.getSimpleName()));
                } else {
                    q.setParameter("finalEmpleado", valoresFiltro.get("final" + Empleados.class.getSimpleName()));
                }
            }
            listValue = q.list();
            if (listValue.isEmpty()) {
                existeInfo = false;
            } else {
                existeInfo = true;
            }
            getSession().getTransaction().commit();

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("REPORTEACUMULADOSNOMINA_A_EXCEL()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
            existeInfo = false;
        }
        if (!listValue.isEmpty()) {
            crearTxt(ruta, listValue, valoresFiltro);
        }
        return existeInfo;
    }

    private Boolean REPORTEINCIDENCIAS_A_EXCEL(String ruta, Map<String, Object> valoresFiltro, String uuidCxn) {
        Boolean existeInfo = false;
        StringBuilder builder = new StringBuilder();
        List<Object> listValue = null;
        builder.append("SELECT\n"
                + "\n"
                + "CASE WHEN (emp is NULL ) THEN '' ELSE CASE WHEN (emp.clave is NULL) THEN \n"
                + "'' ELSE emp.clave END END ,\n"
                + " CONCAT (CASE WHEN(emp IS NULL ) THEN '' ELSE CASE WHEN (emp.nombre IS NULL ) THEN '' ELSE emp.nombre END END,' ',"
                + " CASE WHEN (emp is NULL ) THEN '' ELSE CASE WHEN (emp.apellidoPaterno is NULL) THEN '' ELSE emp.apellidoPaterno END END,' ', "
                + " CASE WHEN (emp is NULL) THEN '' ELSE CASE WHEN ( emp.apellidoMaterno is NULL) THEN '' ELSE emp.apellidoMaterno END END) ,\n"
                + "CASE WHEN (emp IS NULL ) THEN '' ELSE CASE WHEN (emp.RFC is NULL ) THEN '' ELSE emp.RFC END END ,\n"
                + "CASE WHEN (emp is NULL ) THEN '' ELSE CASE WHEN (emp.CURP is NULL) THEN '' ELSE emp.CURP END END ,\n"
                + "CASE WHEN (emp is NULL) THEN '' ELSE CASE WHEN (emp.IMSS is NULL )THEN '' ELSE emp.IMSS END END ,\n"
                //+ "CASE WHEN (emp is NULL ) THEN '' ELSE CASE WHEN (emp.apellidoPaterno is NULL) THEN '' ELSE emp.apellidoPaterno END END ,\n"
                //+ "CASE WHEN (emp is NULL) THEN '' ELSE CASE WHEN ( emp.apellidoMaterno is NULL) THEN '' ELSE emp.apellidoMaterno END END  ,\n"
                + "COUNT(excep)\n"
                + "\n"
                + "FROM Asistencias asis inner join asis.empleados as emp , PlazasPorEmpleadosMov pm\n"
                + "LEFT OUTER JOIN pm.plazasPorEmpleado pl LEFT OUTER JOIN pl.registroPatronal reg\n"
                + "LEFT OUTER JOIN pl.razonesSociales rs LEFT OUTER JOIN asis.periodosNomina per LEFT OUTER JOIN asis.tipoNomina tipNom\n"
                + "LEFT OUTER JOIN asis.excepciones excep \n"
                + "\n"
                + "WHERE  asis.empleados = pl.empleados\n"
                + "AND excep.clave =:tipoExcepcion\n"
                + "AND asis.fecha  BETWEEN :fechaInicio AND :fechaFin AND rs.clave =:claveRazonsocial\n");

        if (valoresFiltro.containsKey("filtrosPersonalizados")) {
            builder.append(valoresFiltro.get("filtrosPersonalizados"));
        }
        builder.append("GROUP BY  emp.id,  emp.clave , emp.nombre , emp.apellidoPaterno, emp.apellidoMaterno , emp.RFC, emp.CURP , emp.IMSS");
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            org.hibernate.Query q = getSession().createQuery(builder.toString());
            q.setParameter("tipoExcepcion", valoresFiltro.get("tipoExcepcion"));
            q.setParameter("claveRazonsocial", valoresFiltro.get("claveRazonsocial"));
            q.setParameter("fechaInicio", valoresFiltro.get("fechaInicio"));
            q.setParameter("fechaFin", valoresFiltro.get("fechaFin"));
            listValue = q.list();
            getSession().getTransaction().commit();

        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("REPORTEINCIDENCIAS_A_EXCEL()1_Error: ").append(ex));
            try {
                if (getSession().getTransaction().isActive()) {
                    getSession().getTransaction().rollback();
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) {  //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
            return null;
        }
        if (listValue.isEmpty()) {
            existeInfo = false;
        } else {
            existeInfo = true;
            crearExcelIncidencias(ruta, valoresFiltro, listValue);
        }

        return existeInfo;
    }

    private void crearExcel(String archivoDestino, Map<String, Object> valoresFiltro, List<Object> listValue, List<Object> listTituloConcepto, boolean incluirPercepciones, boolean incluirDeducciones,
            boolean incluirTotalPercepciones, boolean incluirTotalDeducciones, boolean incluirParteGravable_Exenta, HashMap posicionDatos) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            String[] columnas1 = new String[]{"EMPLEADO", "NOMBRE", "RFC", "CURP", "IMSS", "PUESTO", "F. INGRESO", "F. BAJA", "DIAS TRAB", "SALARIO DIARIO"};
            String[] columnas2 = new String[]{"NETO", "INGRESO GRAVADO", "INGRESO EXCENTO", "SUBSIDIO EMPLEO", "DIF.SUB.EMPLEO.", "ISR RETENIDO"};
            List<String> listColumnas = new ArrayList<String>();
            listColumnas.addAll(Arrays.asList(columnas1));
            String nombreParteGravable = " (G)", nombreParteExento = " (E)";
            String nombreTotalPercepcion = "TOTAL PERCEPCIONES", nombreTotalDeduccion = "TOTAL DEDUCCIONES";
            if (incluirDeducciones | incluirPercepciones | incluirParteGravable_Exenta) {//ME FALTA SI SOLO SE QUIERE INCLUIR PARTE GRAVABLE Y EXENTA.
                for (int i = 0; i < listTituloConcepto.size(); i++) {
                    Naturaleza naturaleza = (Naturaleza) ((Object[]) listTituloConcepto.get(i))[0];
                    String nombreColumna = ((Object[]) listTituloConcepto.get(i))[1].toString();
                    if (naturaleza == Naturaleza.PERCEPCION) {
                        if (incluirPercepciones) {
                            listColumnas.add(nombreColumna);
                        }
                        if (incluirParteGravable_Exenta) {
                            listColumnas.add(nombreColumna + nombreParteGravable);
                            listColumnas.add(nombreColumna + nombreParteExento);
                        }
                        if (incluirTotalPercepciones) {
                            if ((Naturaleza) ((Object[]) listTituloConcepto.get(i + 1))[0] == Naturaleza.DEDUCCION) {
                                listColumnas.add(nombreTotalPercepcion);
                            }
                        }
                    } else {
                        if (incluirDeducciones) {
                            listColumnas.add(nombreColumna);
                        }
                        if (incluirParteGravable_Exenta) {
                            listColumnas.add(nombreColumna + nombreParteGravable);
                            listColumnas.add(nombreColumna + nombreParteExento);
                        }
                        if (incluirTotalDeducciones) {
                            if (i + 1 >= listTituloConcepto.size()) {
                                listColumnas.add(nombreTotalDeduccion);
                            }
                        }
                    }
                }
            } else if (incluirTotalDeducciones | incluirTotalPercepciones) {
                listColumnas.add(nombreTotalPercepcion);
                listColumnas.add(nombreTotalDeduccion);
            }
            listColumnas.addAll(Arrays.asList(columnas2));
            //Create a blank sheet
            XSSFSheet sheet = workbook.createSheet("Hoja1");
            //Iterate over data and write to sheet
            int rowNumExcel = -1, i;
            Row row;
            if (valoresFiltro.containsKey("TituloNombreEmpresa")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("TituloNombreEmpresa").toString());
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
            }
            if (valoresFiltro.containsKey("TituloDomicilio")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("TituloDomicilio").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            if (valoresFiltro.containsKey("TituloPeriodo")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("TituloPeriodo").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            if (valoresFiltro.containsKey("msgEncabezado")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("msgEncabezado").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            int inicioColumnasConceptos = columnas1.length, finalColumnaConcepto = columnas1.length + (listColumnas.size() - columnas1.length - columnas2.length) - 1;
            HashMap posicionColumnaConcepto = new HashMap();
            row = sheet.createRow(++rowNumExcel);
            //<editor-fold defaultstate="collapsed" desc="Se añaden los titulos de las columnas">
            for (i = 0; i < listColumnas.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(listColumnas.get(i));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOCOLUMNAS, workbook, TIPO_VALUE.GENERAL);
                if (i >= inicioColumnasConceptos & i <= finalColumnaConcepto) {
                    posicionColumnaConcepto.put(listColumnas.get(i), i);
                }
            }
            //</editor-fold>
            row = null;
            boolean crearFila = true;
            //<editor-fold defaultstate="collapsed" desc="Se añade el detalle de las columnas">
            if (listValue.isEmpty()) {
                //hereaquiyahora
            } else {
                for (i = 0; i < listValue.size(); i++) {
//                System.out.println("i = " + i);
                    Object[] objArr = (Object[]) listValue.get(i);
                    int cellnum = 0;
                    int columna = 0;
                    if (crearFila) {
                        //<editor-fold defaultstate="collapsed" desc="Se añaden los datos que no se repiten como por ejemplo la clave y nombre del empleado, RFC, CURP, etc">
                        row = sheet.createRow(++rowNumExcel);
                        int ultimasColumnas = finalColumnaConcepto;
                        Cell cell;
                        for (columna = 0; columna < objArr.length; columna++) {
                            if (columna < columnas1.length | columna >= objArr.length - columnas2.length) {
                                if (columna < columnas1.length) {
                                    cell = row.createCell(cellnum++);
                                    Object object = objArr[columna];//here
                                    if (columna == 7) {//JEVC02 valida fecha de baja,parq poner valor en celda
                                        Date fechaFinal, min, max;
                                        fechaFinal = (Date) object;
                                        min = (Date) valoresFiltro.get("fechaInicial");
                                        max = (Date) valoresFiltro.get("fechaFinal");
                                        if (fechaFinal != null && min != null && max != null) {
                                            if (fechaFinal.compareTo(min) >= 0 && fechaFinal.compareTo(max) <= 0) {
                                                object = objArr[columna];
                                            } else {
                                                object = "";
                                            }
                                        }
                                    }
//                                    if (columna == columnas1.length - 2) {
//                                        Calendar fechaIngreso = Calendar.getInstance(), fechaBaja = Calendar.getInstance();
//                                        fechaIngreso.setTime((Date) objArr[columnas1.length - 4]);
//                                        fechaBaja.setTime((Date) objArr[columnas1.length - 3]);
////                                    DateFormat df = DateFormat.getDateInstance();
////                                    System.out.println("Emp " + objArr[0] + " -- fechaIngreso " + df.format(fechaIngreso.getTime()) + " -- fechaBaja " + df.format(fechaBaja.getTime()));
//                                        int diasDif = 0, diasTotal = cantidadDiasEntreDosFechas((Date) valoresFiltro.get("fechaInicial"), (Date) valoresFiltro.get("fechaFinal")) + 1;
//                                        if (fechaIngreso.getTime().compareTo((Date) valoresFiltro.get("fechaInicial")) > 0)//Es mayor fechaAlta a fechaInicial
//                                        {
//                                            diasDif += cantidadDiasEntreDosFechas((Date) valoresFiltro.get("fechaInicial"), fechaIngreso.getTime());
//                                        }
//
//                                        if (fechaBaja.getTime().compareTo((Date) valoresFiltro.get("fechaFinal")) < 0) {
//                                            if (fechaBaja.getTime().compareTo((Date) valoresFiltro.get("fechaFinal")) != 0) {
//                                                diasDif = diasDif + cantidadDiasEntreDosFechas(fechaBaja.getTime(), (Date) valoresFiltro.get("fechaFinal"));
//                                            }
//                                        }
//                                        diasTotal = diasTotal - diasDif - ((Double) object).intValue();
//                                        insertarValorCell(workbook, diasTotal, cell, Estilo.VALORES_COLUMNAS);
//                                    } else {
                                    insertarValorCell(workbook, object, cell, Estilo.VALORES_COLUMNAS);
//                                    }
                                } else {
                                    ultimasColumnas++;
                                    cell = row.createCell(ultimasColumnas);
                                    insertarValorCell(workbook, objArr[columna], cell, Estilo.VALORES_COLUMNAS);
                                }
                            } else {
                                if (incluirTotalPercepciones) {
                                    if (columna == Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_TOTAL_PERCEPCION).toString())) {
                                        cell = row.createCell((Integer) posicionColumnaConcepto.get(nombreTotalPercepcion));
                                        insertarValorCell(workbook, objArr[columna], cell, Estilo.VALORES_COLUMNAS);
                                    }
                                }
                                if (incluirTotalDeducciones) {
                                    if (columna == Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_TOTAL_DEDUCCION).toString())) {
                                        cell = row.createCell((Integer) posicionColumnaConcepto.get(nombreTotalDeduccion));
                                        insertarValorCell(workbook, objArr[columna], cell, Estilo.VALORES_COLUMNAS);
                                    }
                                }

                            }
                        }
                        crearFila = false;
                        //</editor-fold>
                    }
                    try {
                        Cell cell;
                        if (incluirPercepciones | incluirDeducciones | incluirParteGravable_Exenta) {
                            Naturaleza naturaleza = (Naturaleza) objArr[columnas1.length];
                            String nombreConcepto = (String) objArr[columnas1.length + 1];
                            Integer posicionColumna, posicionColumnaGravable, posicionColumnaExenta;
//                    System.out.println("columna " + columna);
                            if (naturaleza == Naturaleza.PERCEPCION) {
                                if (incluirPercepciones) {
                                    posicionColumna = (Integer) posicionColumnaConcepto.get(nombreConcepto);
                                    cell = row.createCell(posicionColumna);
                                    insertarValorCell(workbook, objArr[Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_PERCEPCION).toString())], cell, Estilo.VALORES_COLUMNAS);
                                }
                                if (incluirParteGravable_Exenta) {
                                    posicionColumnaGravable = (Integer) posicionColumnaConcepto.get(nombreConcepto + nombreParteGravable);
                                    cell = row.createCell(posicionColumnaGravable);
                                    insertarValorCell(workbook, objArr[Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_PERCEPCION_GRAVABLE).toString())], cell, Estilo.VALORES_COLUMNAS);
                                    posicionColumnaExenta = Integer.parseInt(posicionColumnaConcepto.get(nombreConcepto + nombreParteExento).toString());
                                    cell = row.createCell(posicionColumnaExenta);
                                    insertarValorCell(workbook, objArr[Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_PERCEPCION_EXENTO).toString())], cell, Estilo.VALORES_COLUMNAS);
                                }
                            } else {
                                if (incluirDeducciones) {
                                    posicionColumna = (Integer) posicionColumnaConcepto.get(nombreConcepto);
                                    cell = row.createCell(posicionColumna);
                                    insertarValorCell(workbook, objArr[Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_DEDUCCION).toString())], cell, Estilo.VALORES_COLUMNAS);
                                }

                                if (incluirParteGravable_Exenta) {
                                    posicionColumnaGravable = Integer.parseInt(posicionColumnaConcepto.get(nombreConcepto + nombreParteGravable).toString());
                                    cell = row.createCell(posicionColumnaGravable);
                                    insertarValorCell(workbook, objArr[Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_DEDUCCION_GRAVABLE).toString())], cell, Estilo.VALORES_COLUMNAS);
                                    posicionColumnaExenta = Integer.parseInt(posicionColumnaConcepto.get(nombreConcepto + nombreParteExento).toString());
                                    cell = row.createCell(posicionColumnaExenta);
                                    insertarValorCell(workbook, objArr[Integer.parseInt(posicionDatos.get(PosicionDatosEspeciales.INCLUIR_DEDUCCION_EXENTO).toString())], cell, Estilo.VALORES_COLUMNAS);
                                }
                            }
                        }
                        boolean llenarDatosVacios = false;
                        if (i + 1 < listValue.size()) {
                            if (!((Object[]) listValue.get(i + 1))[0].toString().equalsIgnoreCase(objArr[0].toString())) {
                                crearFila = true;
                                llenarDatosVacios = true;
                            }
                        } else {
                            if (i + 1 == listValue.size()) {
                                llenarDatosVacios = true;
                            }
                        }
                        if (llenarDatosVacios) {
                            for (short colIx = row.getFirstCellNum(); colIx < row.getLastCellNum(); colIx++) {
                                cell = row.getCell(colIx);
                                if (cell == null) {
                                    cell = row.createCell(colIx);
                                    insertarValorCell(workbook, 0, cell, Estilo.VALORES_COLUMNAS);
                                } else {
                                    if (cell.getColumnIndex() > 10) {
                                        if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
                                            insertarValorCell(workbook, 0, cell, Estilo.VALORES_COLUMNAS);
                                        } else if (cell.getCellType() != HSSFCell.CELL_TYPE_BOOLEAN & cell.getCellType() != HSSFCell.CELL_TYPE_NUMERIC) {
                                            if (cell.getRichStringCellValue().length() == 0) {
                                                insertarValorCell(workbook, 0, cell, Estilo.VALORES_COLUMNAS);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    } catch (Exception ex) {
                        System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("crearExcel()1_Error: ").append(ex));
                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                        mensajeResultado.setError(ex.getLocalizedMessage());
                        return;
                    }
                }
            }
            //</editor-fold>
            for (int j = 0; j < 8; j++) {
                sheet.autoSizeColumn(j);
            }
            if (valoresFiltro.containsKey("msgPie")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("msgPie").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(archivoDestino));
            workbook.write(out);
            out.close();
//            Runtime runtime = Runtime.getRuntime();
//            runtime.exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + archivoDestino);
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("crearExcel()2_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
        }
    }

    private void crearExcelIncidencias(String archivoDestino, Map<String, Object> valoresFiltro, List<Object> listValue) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            String campo = valoresFiltro.get("TituloNumExcepciones").toString();
            String[] columnas1 = new String[]{"EMPLEADO", "NOMBRE", "RFC", "CURP", "IMSS", campo};
            List<String> listColumnas = new ArrayList<String>();
            listColumnas.addAll(Arrays.asList(columnas1));

            XSSFSheet sheet = workbook.createSheet("Hoja1");

            int rowNumExcel = -1, i;
            Row row;
            if (valoresFiltro.containsKey("TituloNombreEmpresa")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("TituloNombreEmpresa").toString());
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
            }
            if (valoresFiltro.containsKey("TituloDomicilio")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("TituloDomicilio").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }

            if (valoresFiltro.containsKey("TituloNombreReporte")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("TituloNombreReporte").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            if (valoresFiltro.containsKey("TituloPeriodo")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("TituloPeriodo").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            if (valoresFiltro.containsKey("msgEncabezado")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("msgEncabezado").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            //<editor-fold defaultstate="collapsed" desc="Se añaden los titulos de las columnas">
            row = sheet.createRow(++rowNumExcel);
            for (i = 0; i < listColumnas.size(); i++) {
                Cell cell = row.createCell(i);
                cell.setCellValue(listColumnas.get(i));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOCOLUMNAS, workbook, TIPO_VALUE.GENERAL);
            }
            // agrega el valor a las columnas 
            if (listValue != null) {
                for (i = 0; i < listValue.size(); i++) {
                    Object[] objArr = (Object[]) listValue.get(i);
                    row = sheet.createRow(++rowNumExcel);
                    for (int j = 0; j < objArr.length; j++) {
                        Cell cell = row.createCell(j);
                        Object object = objArr[j];
                        insertarValorCell(workbook, object, cell, Estilo.VALORES_COLUMNAS);
                    }
                }
            }
            //ajusta las columnas
            for (int j = 0; j < 6; j++) {
                sheet.autoSizeColumn(j);
            }
            if (valoresFiltro.containsKey("msgPie")) {
                row = sheet.createRow(++rowNumExcel);
                Cell cell = row.createCell(0);
                cell.setCellValue(valoresFiltro.get("msgPie").toString());
                sheet.addMergedRegion(new CellRangeAddress(rowNumExcel, rowNumExcel, 0, listColumnas.size() - 1));
                aplicarEstilo(cell, Estilo.ESTILO_TITULOS, workbook, TIPO_VALUE.GENERAL);
            }
            //Write the workbook in file system
            FileOutputStream out = new FileOutputStream(new File(archivoDestino));
            workbook.write(out);
            out.close();
//            Runtime runtime = Runtime.getRuntime();
//            runtime.exec("rundll32 SHELL32.DLL,ShellExec_RunDLL " + archivoDestino);
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("crearExcelIncidencias()2_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
        }
    }

    private void crearTxt(String archivoDestino, List<Object> listValue, Map<String, Object> valoresFiltro) {

        try {
            FileWriter fichero = null;
            try {
                fichero = new FileWriter(archivoDestino);
                StringBuilder sb = new StringBuilder();
                boolean newRow = true;
                boolean continuarLlenandoFila = false;
                Integer campoInicio = 58;
                Integer campoFinal = 113;
                Integer campoObject = null;
                for (int i = 0; i < listValue.size(); i++) {
                    Object[] listObject = (Object[]) listValue.get(i);
                    for (int j = 0; j < listObject.length; j++) {
                        if (!newRow & !continuarLlenandoFila) {
                            if (j == 0) {
                                j = 57;
                            }
                        }
                        //j < 57 || j > 59
                        if (j < 59) {//Solo los campos DIM menores del campo 58 y mayores al campo 113 reciben este tratamiento, es decir indice menor al indice 57 y mayor al indice 58, el resto son especiales .
                            //@-Omite poner caracter Pipe
                            Object valueDIMM = obtenerCampoDIMM(j, listObject, valoresFiltro);
                            if (!valueDIMM.equals("@")) {
                                sb.append(valueDIMM).append("|");
                            } else {
                                System.out.println("");
                            }
                        } else {
                            newRow = false;
//////                            //El indica 57 es la clave de campo DIM y con el sabre cuales campos DIM mayores del campo 58
////////no fueron utilizar para asi concatenar el char del separador "|".
//////                            try {
//////                                campoObject = Integer.valueOf(listObject[j].toString());
//////                                if (campoInicio < campoObject) {
//////                                    if (campoObject % 2 != 0) {
//////                                        campoObject++;
//////                                    }
//////                                    for (int k = 0; k < campoObject - campoInicio; k++) {
//////                                        sb./*append("CAM").append(campoInicio + k).append("_").*/append("0|");
//////                                    }
//////
//////                                    Float b1 = Float.valueOf(listObject[j + 1].toString());
//////                                    Float b2 = Float.valueOf(listObject[j + 2].toString());
////////                                    System.out.println("------------------");
////////                                    System.out.println("Object " + listObject[j + 1].toString() + " - " + listObject[j + 2].toString());
////////                                    System.out.println("Float " + b1 + " - " + b2);
////////                                    System.out.println("------------------");
//////                                    sb.append(b1).append("|").append(b2).append("|");
//////                                    campoInicio = campoObject + 2;
//////                                } else {
//////                                    Float b1 = Float.valueOf(listObject[j + 1].toString());
//////                                    Float b2 = Float.valueOf(listObject[j + 2].toString());
////////                                    System.out.println("------------------");
////////                                    System.out.println("Object " + listObject[j + 1].toString() + " - " + listObject[j + 2].toString());
////////                                    System.out.println("Float " + b1 + " - " + b2);
////////                                    System.out.println("------------------");
//////                                    sb.append(b1).append("|").append(b2).append("|");
//////                                    campoInicio = campoObject + 2;
//////                                }
//////                                if (i + 1 < listValue.size()) {
//////                                    if (!((Object[]) listValue.get(i + 1))[2].toString().equalsIgnoreCase(listObject[2].toString())) {//y son diferentes RFC entonces comienza una nueva linea.
//////                                        continuarLlenandoFila = true;
//////                                        j = j + 2;
//////                                        if (campoInicio < campoFinal) {
//////                                            for (int k = 0; k < campoFinal - campoInicio + 1; k++) {
//////                                                sb./*append("CAM").append(campoInicio + k).append("_").*/append("0|");
//////                                            }
//////                                        }
//////                                    } else {
//////                                        j = listObject.length;
//////                                    }
//////                                } else {
//////                                    continuarLlenandoFila = true;
//////                                    j = j + 2;
//////                                    if (campoInicio < campoFinal) {
//////                                        for (int k = 0; k < campoFinal - campoInicio + 1; k++) {
//////                                            sb./*append("CAM").append(campoInicio + k).append("_").*/append("0|");
//////                                        }
//////                                    }
//////                                }
//////                            } catch (Exception ex) {
//////                                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("crearTxt()1_Error: ").append(ex).append(" i= ").append(i).append(" j= ").append(j).append(" ").append(listObject[j] == null ? "NULL" : listObject[j].toString()));
//////                            }
                        }
                    }
                    if (i + 1 < listValue.size()) {
                        if (!((Object[]) listValue.get(i + 1))[2].toString().equalsIgnoreCase(listObject[2].toString())) {//y son diferentes RFC entonces comienza una nueva linea.
                            newRow = true;
                            continuarLlenandoFila = false;
                            fichero.write(sb.toString() + "\n");
                            sb.delete(0, sb.length());
                            campoInicio = 58;
                            campoFinal = 113;
                        }
                    } else {
                        if (i + 1 == listValue.size()) {
                            fichero.write(sb.toString() + "\n");
                            sb.delete(0, sb.length());
                            campoInicio = 58;
                            campoFinal = 113;
                        }
                    }
                }

                fichero.close();

            } catch (Exception ex) {
                System.out.println("Mensaje de la excepción: " + ex.getMessage());
            }
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("crearExcel()2_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
        }
    }

    String valorCD30 = null;
    Integer valorCD14 = null;
    Integer irToDim = null;//Para brincar hasta DIM

    private Object obtenerCampoDIMM(Integer indiceArreglo, Object[] listObject, Map<String, Object> valoresFiltro) {
        Object obj = null;
        StringBuilder sb = new StringBuilder();
        Calendar c = null;
        Integer ejercicio = null;
        Object object = listObject[indiceArreglo];
        try {
            if (indiceArreglo == 56) {
                System.out.println("s");
            }
            if (irToDim != null) {
                obj = "@";
                if (irToDim == indiceArreglo) {
                    irToDim = null;
                }
            }
            if (irToDim == null) {
                switch (indiceArreglo) {
                    case 0://CAMPODIM 1
                        c = Calendar.getInstance();
                        c.setTime((Date) object);
                        ejercicio = (Integer) valoresFiltro.get("ejercicio");
                        sb.delete(0, sb.length());
                        if (ejercicio > c.get(Calendar.YEAR)) {
                            sb.append("01");
                        } else if (ejercicio == c.get(Calendar.YEAR)) {
                            sb.append(c.get(Calendar.MONTH) + 1);
                            if (sb.length() == 1) {
                                sb.insert(0, "0");
                            }
                        }
                        obj = sb.toString();
                        break;
                    case 1://CAMPODIM 2
                        c = Calendar.getInstance();
                        c.setTime((Date) object);
                        ejercicio = (Integer) valoresFiltro.get("ejercicio");
                        sb.delete(0, sb.length());
                        if (ejercicio < c.get(Calendar.YEAR)) {
                            sb.append("12");
                        } else if (ejercicio == c.get(Calendar.YEAR)) {
                            sb.append(c.get(Calendar.MONTH) + 1);
                            if (sb.length() == 1) {
                                sb.insert(0, "0");
                            }
                        }
                        obj = sb.toString();
                        break;
                    case 7://CAMPODIM 8
                        if (object == null) {
                            obj = "0";
                        } else if (ZonaGeografica.ZonaGeograficaA == object) {
                            obj = "01";
                        } else {
                            obj = "02";
                        }
                        break;
                    case 8://CAMPODIM 9
                        obj = 2;
                        break;
                    case 9://CAMPODIM 10
                        obj = 1;
                        break;
                    case 10://CAMPODIM 11
                        obj = 2;
                        break;
                    case 11://CAMPODIM 12
                        obj = "1.0000";
                        break;
                    case 12://CAMPODIM 13
                        if (object == null) {
                            obj = "0";
                        } else if (object.toString().equalsIgnoreCase("4")) {
                            obj = "1";
                        } else {
                            obj = "2";
                        }
                        break;
                    case 13://CAMPODIM 14
                        if (object == null) {
                            object = 0;
                        } else {
                            Integer regimenContratacion = (Integer) object;
                            switch (regimenContratacion) {
                                case 5:
                                    obj = "A";
                                    break;
                                case 6:
                                    obj = "B";
                                    break;
                                case 7:
                                    obj = "C";
                                    break;
                                case 8:
                                    obj = "D";
                                    break;
                                case 9:
                                    obj = "E";
                                    break;
                                case 10:
                                    obj = "G";
                                    break;
                                default:
                                    obj = "0";
                            }
                        }
                        valorCD14 = 13;
                        break;
                    case 14://CAMPODIM 15
                        if (object == null) {
                            obj = "0";
                        } else {
                            sb.delete(0, sb.length()).append(object.toString());
                            if (sb.length() == 1) {
                                sb.insert(0, "0");
                            }
                            obj = sb.toString();
                        }
                        break;//check
                    case 15://CAMPODIM 16
                    case 16://CAMPODIM 17
                    case 17://CAMPODIM 18
                    case 18://CAMPODIM 19
                    case 19://CAMPODIM 20
                    case 20://CAMPODIM 21
                    case 21://CAMPODIM 22
                    case 22://CAMPODIM 23
                    case 23://CAMPODIM 24
                    case 24://CAMPODIM 25
                    case 25://CAMPODIM 26
                        obj = "";
                        break;
                    case 26://CAMPODIM 27
                        obj = "0";
                        break;
                    case 27://CAMPODIM 28
                    case 28://CAMPODIM 29
                        obj = "";
                        break;
                    case 29://CAMPODIM 30
                        if (object == null) {
                            obj = "0";
                        } else {
                            Long b = (Long) object;
                            if (b > 0) {
                                obj = "1";
                            } else {
                                obj = "2";
                            }
                        }
                        valorCD30 = (String) obj;
                        break;
                    case 30://CAMPODIM 31
                        Object cd = listObject[valorCD14]; //Verifica Campo DIM14
                        if (cd == null) { //Es 0
                            obj = "0";
                        } else {
                            Integer v = (Integer) cd;
                            if (v < 5) {//Es 0
                                obj = "2";
                            } else {
                                obj = "1";
                            }
                        }
                        break;
                    case 31://CAMPODIM 32
                        cd = listObject[valorCD14]; //Verifica Campo DIM14
                        if (cd == null) {//Es 0
                            obj = "0";
                        } else {
                            Integer v = (Integer) cd;
                            if (v < 5) {//Es 0
                                obj = "1";
                            } else {
                                obj = "2";
                            }
                        }//si 32 es 1 ir hasta 58
                        irToDim = 57;
                        break;
                    case 32://CAMPODIM 33
                        if (valorCD30.equals("1")) {
                            //
                            //
                            //Metodo
                            obj = "0";
                        }
                        break;
                    case 33://CAMPODIM 34
                        if (valorCD30.equals("1")) {
                            obj = "0";
                        }
                        break;
                    case 34://CAMPODIM 35
                        if (valorCD30.equals("1")) {
                            obj = "0";
                        }
                        break;
                    case 36://CAMPODIM 37
                        if (((Date) listObject[1]).compareTo(((Date) valoresFiltro.get("fechaFinal"))) > 0) {
                            obj = "";//No esta dado de baja
                        } else {
                            c = Calendar.getInstance();
                            c.setTime((Date) listObject[0]);
                            c.set(Calendar.DAY_OF_MONTH, 1);
                            c.set(Calendar.MONTH, Calendar.JANUARY);
                            c.set(Calendar.YEAR, (Integer) valoresFiltro.get("ejercicio"));
                            obj = (Integer) (calcularAntiguedadEntera(c.getTime(), (Date) listObject[1], TipoAntiguedad.PORCIONDIAS));
                        }
                        break;
                    case 42://CAMPODIM 43
                        obj = "0";
                        break;
                    case 43://CAMPODIM 44
                        if (((Date) listObject[1]).compareTo(((Date) valoresFiltro.get("fechaFinal"))) > 0) {
                            obj = "";//No esta dado de baja
                        } else {
                            obj = (Integer) (calcularAntiguedadEntera((Date) listObject[0], (Date) listObject[1], TipoAntiguedad.ANTIGUEDADENTERO));
                        }
                        break;
                    case 51://CAMPODIM 52
                        if (object == null) {
                            obj = "0.0";
                        } else {
                            obj = object.toString();
                        }
                        break;
                    case 53://CAMPODIM 54
                    case 54://CAMPODIM 55
                        obj = "0";
                        break;
                    case 55://CAMPODIM 56
                        obj = "0";
                        break;
                    case 56://CAMPODIM 57
                        obj = "0";
                        break;
                    case 57://CAMPODIM 58
                        obj = "0";
                        break;
                    case 58://CAMPODIM 59 
                        obj = "0";
                        break;
                    case 73://CAMPODIM 127
                        obj = "0";
                        break;
                    case 74://CAMPODIM 128
                        obj = "0";
                        break;
                    case 75://CAMPODIM 129
                        obj = "0";
                        break;
                    case 76://CAMPODIM 130
                        obj = "0";
                        break;
                    case 77://CAMPODIM 131
                        obj = "0";
                        break;
                    case 78://CAMPODIM 132
                        obj = "0";
                        break;
                    case 79://CAMPODIM 133
                        if (object == null) {
                            obj = "0";
                        } else {
                            Float b = Float.valueOf(object.toString());
                            obj = b;
                        }
                        break;
                    case 80://CAMPODIM 134
                        if (object == null) {
                            obj = "0";
                        } else {
                            Float b = Float.valueOf(object.toString());
                            obj = b;
                        }
                        break;
                    default:
                        obj = object;
                }
            }
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerCampoDIMM()1_Error: ").append(ex).append(" indiceArreglo ").append(indiceArreglo).append(object == null ? "NULL" : object.toString()));
        }
        return obj;
    }

    private void insertarValorCell(XSSFWorkbook workbook, Object valor, Cell cell, Estilo estilo) {
        if (valor != null) {
            if (valor instanceof Date) {
                Date date = null;
                try {
                    date = (Date) valor;
                    cell.setCellValue(date);
                } catch (Exception ex) {
                    System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("Exception Date()1_Error: ").append(ex));
                    cell.setCellValue((Date) valor);
                }
                aplicarEstilo(cell, Estilo.VALORES_COLUMNAS, workbook, TIPO_VALUE.DATE);
            } else if (valor instanceof Double) {
                cell.setCellValue((Double) valor);
                aplicarEstilo(cell, Estilo.VALORES_COLUMNAS, workbook, TIPO_VALUE.DOUBLE);
            } else if (valor instanceof String) {
                cell.setCellValue((String) valor);
                aplicarEstilo(cell, Estilo.VALORES_COLUMNAS, workbook, TIPO_VALUE.GENERAL);
            } else if (valor instanceof Long) {
                cell.setCellValue((Long) valor);
                aplicarEstilo(cell, Estilo.VALORES_COLUMNAS, workbook, TIPO_VALUE.DOUBLE);
            } else if (valor instanceof Integer) {
                cell.setCellValue((Integer) valor);
                aplicarEstilo(cell, Estilo.VALORES_COLUMNAS, workbook, TIPO_VALUE.DOUBLE);
            } else if (valor instanceof Float) {
                cell.setCellValue(((Float) valor).doubleValue());
                aplicarEstilo(cell, Estilo.VALORES_COLUMNAS, workbook, TIPO_VALUE.DOUBLE);
            }
        } else {
            cell.setCellValue("");
        }
    }

    private void aplicarEstilo(Cell cell, Estilo estilo, XSSFWorkbook workbook, TIPO_VALUE tipo_value) {
        XSSFCellStyle style = workbook.createCellStyle();
        style.setBorderTop((short) 1); // single lines border
        style.setBorderBottom((short) 1); // single line border
        style.setBorderLeft((short) 1); // single lines border
        style.setBorderRight((short) 1); // single line border
        XSSFFont hSSFFont = workbook.createFont();
        //hSSFFont.setFontName(HSSFFont.FONT_ARIAL);
        hSSFFont.setFontHeightInPoints((short) 10);
        if (estilo == Estilo.ESTILO_TITULOCOLUMNAS) {
            style.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setWrapText(true);
            hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        } else if (estilo == Estilo.ESTILO_TITULOS) {
            style.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setWrapText(true);
            hSSFFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            hSSFFont.setColor(IndexedColors.WHITE.getIndex());
            style.setBorderTop((short) 0); // single lines border
            style.setBorderBottom((short) 0); // single line border
            style.setBorderLeft((short) 0); // single lines border
            style.setBorderRight((short) 0); // single line border
        }
        if (tipo_value == TIPO_VALUE.DATE) {
            style.setDataFormat(workbook.createDataFormat().getFormat("dd/MM/yyyy"));
        } else if (tipo_value == TIPO_VALUE.DOUBLE) {
            style.setDataFormat(workbook.createDataFormat().getFormat("#,##0.00"));
        }
        style.setFont(hSSFFont);
//        cellStyle.setFont(hSSFFont);
        cell.setCellStyle(style);
    }

    private byte[] convertirJasperPrintToByte(Object print) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        byte[] serializedPrint = null;

        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(print);
            serializedPrint = byteArrayOutputStream.toByteArray();
        } catch (IOException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("convertirJasperPrintToByte()1_Error: ").append(ex));
            excepcionMensage(ex);
        }
        return serializedPrint;
    }

    private InputStream cambiarAgrupacion(String ruta, Map<String, Object> valoresTemplate, boolean agrupar) {
        InputStream is = null;
        try {//here valoresTemplate desde aqui validar
            nombreComplemento = nombreComplemento == null ? "" : nombreComplemento.trim();
            File fXmlFile = new File(ruta + "Template" + nombreComplemento + ".jrxml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            if (agrupar) {
                String def = convertDocumentToString(doc);
                if (!valoresTemplate.isEmpty()) {
                    if (valoresTemplate.containsKey("NombreGrupo")) {
                        def = def.replace("NombreGrupo", valoresTemplate.get("NombreGrupo").toString());
                    }
                    if (valoresTemplate.containsKey("NombreCampoAgrupar")) {
                        def = def.replace("NombreCampoAgrupar", valoresTemplate.get("NombreCampoAgrupar").toString());
                    }
                    if (valoresTemplate.containsKey("NombreCampoMostrarGrupo")) {
                        def = def.replace("NombreCampoMostrarGrupo", valoresTemplate.get("NombreCampoMostrarGrupo").toString());
                    }
                    if (valoresTemplate.containsKey("NombreSubreporteSumatorias")) {
                        def = def.replace("NombreSubreporteSumatorias", valoresTemplate.get("NombreSubreporteSumatorias").toString());
                    }
                }
                doc = convertStringToDocument(def);
            } else {
                if (valoresTemplate.containsKey("variablesGroupRemove")) {
                    List<String> variables = (List<String>) valoresTemplate.get("variablesGroupRemove");
                    for (String briefVariable : variables) {
                        String[] briefs = briefVariable.toString().split(",");
                        removeByAttrName(doc, briefs[0], briefs[1], briefs[2]);
                    }
                }

                removeRecursively(doc, Node.ELEMENT_NODE, "group");
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Source xmlSource = new DOMSource(doc);
            Result outputTarget = new StreamResult(outputStream);
            TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
            is = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (ParserConfigurationException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_ParserConfigurationException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (SAXException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_SAXException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (IOException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_IOException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (TransformerConfigurationException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_TransformerConfigurationException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (TransformerException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_TransformerException()1_Error: ").append(ex));
            excepcionMensage(ex);
        }
        return is;
    }

    private InputStream cambiarSubreportes(String template, String ruta, Map<String, Object> valoresSubreporte) {
        InputStream is = null;
        try {
            File fXmlFile = new File(ruta + template);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            String def = convertDocumentToString(doc);
            for (Map.Entry<String, Object> entry : valoresSubreporte.entrySet()) {
                String key = entry.getKey();
                def = def.replace(key, entry.getValue().toString());
            }

            doc = convertStringToDocument(def);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            Source xmlSource = new DOMSource(doc);
            Result outputTarget = new StreamResult(outputStream);
            TransformerFactory.newInstance().newTransformer().transform(xmlSource, outputTarget);
            is = new ByteArrayInputStream(outputStream.toByteArray());
        } catch (ParserConfigurationException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_ParserConfigurationException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (SAXException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_SAXException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (IOException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_IOException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (TransformerConfigurationException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_TransformerConfigurationException()1_Error: ").append(ex));
            excepcionMensage(ex);
        } catch (TransformerException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("loadReportesFijos_TransformerException()1_Error: ").append(ex));
            excepcionMensage(ex);
        }
        return is;
    }

    private String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            // below code to remove XML declaration
            // transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("convertDocumentToString()1_Error: ").append(e));
        }

        return null;
    }

    private Document convertStringToDocument(String xmlStr) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        try {
            builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(xmlStr)));
            return doc;
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("convertStringToDocument()1_Error: ").append(ex));
        }
        return null;
    }

    public void removeByAttrName(Document doc, String name, String searchAttrName, String identAttr) {
        NodeList node = doc.getElementsByTagName(name);
        for (int i = 0; i < node.getLength(); i++) {
            NamedNodeMap nameNodes = node.item(i).getAttributes();
            Node attrName = nameNodes.getNamedItem(searchAttrName);
            if (attrName.getTextContent().equalsIgnoreCase(identAttr)) {
                removeRecursively(node.item(i), Node.ELEMENT_NODE, name);
                break;
                //System.out.println(attrName + " = \"" + attrName.getTextContent() + "\"");
            }
        }
    }

    public void removeRecursively(Node node, short nodeType, String name) {
        if (node.getNodeType() == nodeType && (name == null || node.getNodeName().equals(name))) {
            node.getParentNode().removeChild(node);
        } else {
            // check the children recursively 
            NodeList list = node.getChildNodes();
            for (int i = 0; i < list.getLength(); i++) {
                removeRecursively(list.item(i), nodeType, name);
            }
        }
    }

    public Mensaje existeUbicacionReporteSistemas(String ruta) {
        mensajeResultado = new Mensaje();
        mensajeResultado.setError("");
        mensajeResultado.setNoError(0);
        try {
            File f = new File(ruta);
            mensajeResultado.setResultado(f.exists());
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("fillParametrosReporte()1_Error: ").append(ex));
            excepcionMensage(ex);
        }
        return mensajeResultado;
    }

    public Mensaje fillParametrosReporte(byte[] fileReport, String uuidCxn) {
        JasperPrint print;
        mensajeResultado = new Mensaje();
        mensajeResultado.setError("");
        mensajeResultado.setNoError(0);
        mensajeResultado.setResultado(null);
        try {
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            Map<String, Object> values = new HashMap<String, Object>();
            values.put(JRHibernateQueryExecuterFactory.PARAMETER_HIBERNATE_SESSION, getSession());
            values.put("claveRazonsocial", "0001");
            values.put("claveTipoCorrida", "NOR");
            JasperReport report = null;
            report = JasperCompileManager.compileReport(new ByteArrayInputStream(fileReport));
            print = JasperFillManager.fillReport(report, values);
            mensajeResultado.setResultado(print);
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("fillParametrosReporte()1_Error: ").append(ex));
            excepcionMensage(ex);
        }
        return mensajeResultado;
    }

    private void excepcionMensage(Exception ex) {
        if (mensajeResultado == null) {
            mensajeResultado = new Mensaje();
        }
        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
        mensajeResultado.setError(ex.getLocalizedMessage());
        mensajeResultado.setResultado(null);
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
    }

    private List<String[]> clavesEmpleadosImplica(Map filtros, String uuidCxn) {
        List<String[]> clavesEmpleados = null;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            concatena.delete(0, concatena.length());//Extrae claves implicadas de empleados
            concatena.append("select (CASE WHEN (cfEmp.plazaPorEmpleadoMov IS NULL) THEN '' ELSE CASE WHEN (cfEmp.plazaPorEmpleadoMov.plazasPorEmpleado IS NULL) ");
            concatena.append("THEN '' ELSE CASE WHEN (cfEmp.plazaPorEmpleadoMov.plazasPorEmpleado IS NULL) THEN '' ELSE CASE WHEN ");
            concatena.append("(cfEmp.plazaPorEmpleadoMov.plazasPorEmpleado.empleados IS NULL) THEN '' ");
            concatena.append("ELSE cfEmp.plazaPorEmpleadoMov.plazasPorEmpleado.empleados.clave END END END END) as claveEmpleado, ");
            concatena.append("CONCAT(CASE WHEN (cfEmp.cfdiRecibo IS NULL) THEN '' ELSE cfEmp.cfdiRecibo.serieCFDI END, CASE WHEN (cfEmp.cfdiRecibo IS NULL) THEN '' ELSE cfEmp.cfdiRecibo.folioCFDI END) as folioCompleto ");
            concatena.append("from CFDIEmpleado cfEmp ");
            concatena.append("LEFT OUTER JOIN cfEmp.tipoNomina tipNom ");
            concatena.append("LEFT OUTER JOIN cfEmp.periodosNomina per ");
            concatena.append("LEFT OUTER JOIN cfEmp.tipoCorrida tipcorr ");
            concatena.append("LEFT OUTER JOIN cfEmp.razonesSociales razonsocialX ");
            concatena.append("LEFT OUTER JOIN cfEmp.plazaPorEmpleadoMov.centroDeCosto cent ");
            concatena.append("LEFT OUTER JOIN cfEmp.plazaPorEmpleadoMov.departamentos dep ");
            concatena.append("LEFT OUTER JOIN cfEmp.plazaPorEmpleadoMov.plazasPorEmpleado.empleados emp ");
            concatena.append("WHERE  razonsocialX.clave = :razonSocial and cfEmp.plazaPorEmpleadoMov.id IN (SELECT x0 FROM PlazasPorEmpleadosMov x0 LEFT OUTER JOIN x0.plazasPorEmpleado x1 LEFT OUTER JOIN x1.razonesSociales ");
            concatena.append("x2 LEFT OUTER JOIN x1.empleados x3 WHERE x2.clave = :razonSocial ");
            concatena.append("AND x0.id IN (SELECT x0X.id FROM PlazasPorEmpleadosMov x0X LEFT OUTER JOIN x0X.plazasPorEmpleado x1X ");
            concatena.append("LEFT OUTER JOIN x1X.empleados x3X WHERE (CURRENT_DATE() >= x0X.fechaInicial) AND x3.id = x3X.id)) ");
            if (filtros.containsKey("filtrosPersonalizados")) {
                concatena.append(filtros.get("filtrosPersonalizados"));
            }
            Query q = getSession().createQuery(concatena.toString());
            if (filtros.containsKey("claveRazonsocial")) {
                q.setParameter("razonSocial", filtros.get("claveRazonsocial"));
            }
            clavesEmpleados = q.list();
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("clavesEmpleadosImplica()1_Error: ").append(ex));
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
        return clavesEmpleados;

    }

    private Object calcularAntiguedadEntera(Date fechaInicial, Date fechaBaja, TipoAntiguedad tipoAntiguedad) {
        Date fechaFinal = fechaBaja;
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

    enum TipoAntiguedad {

        ANTIGUEDADENTERO, ANTIGUEDADEXACTA, PORCIONANTIGUEDAD, PORCIONDIAS;
    }

    public enum PosicionDatosEspeciales {

        INCLUIR_PERCEPCION, INCLUIR_PERCEPCION_GRAVABLE, INCLUIR_PERCEPCION_EXENTO, INCLUIR_TOTAL_PERCEPCION,
        INCLUIR_DEDUCCION, INCLUIR_DEDUCCION_GRAVABLE, INCLUIR_DEDUCCION_EXENTO, INCLUIR_TOTAL_DEDUCCION
    }

    public enum Estilo {

        ESTILO_TITULOS, ESTILO_TITULOCOLUMNAS, VALORES_COLUMNAS
    }

    public enum TIPO_VALUE {

        GENERAL, DATE, DOUBLE
    }
}
