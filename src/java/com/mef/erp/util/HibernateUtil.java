/**
 * @author: Armando Sanchez Fecha de Creación: 19/10/2011 Compañía: MacroPro.
 * Descripción del programa: Aqui se asiga la sesion actual que se pudo
 * conectar.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Jose Armando Sanchez Acosta Fecha: 24-10-2011
 * Descripción: Se agrego el metodo cambiarConfiguraciones para estar cambiando
 * la configuracion de las sesiones. Tambien se comento el el constructor static
 * para que no se inicializara. Se agregaron validaciones de conexion para
 * SQLServer.
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Fecha:29/08/2012 Descripción:Se agrego la
 * entidad conceptosEspeciales.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Fecha:24/11/2012 Descripción:Se cambio el
 * origen de los datos.
 * -----------------------------------------------------------------------------
 * Clave:JSA04 Autor:Jose Armando Fecha:21/02/2013 Descripción:Se cambio el
 * origen de los datos.
 * -----------------------------------------------------------------------------
 * Clave: JSA05 Autor: Armando Fecha: 11/05/2015 Descripcion:Se renombro la
 * tabla de ISRRetenido a CalculoISR para tener todos los calculos cerca y tener
 * todo lo relacionado con el resultado del calculo de la nomina en la misma
 * nomenclatura del calculo...
 * -----------------------------------------------------------------------------
 * Clave: JSA06 Autor: Armando Fecha: 03/06/2015 Descripcion:Se agrego la tabla
 * CalculoUnidades para guardar la informacion de las unidades del calculo.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.util;

import com.mef.erp.modelo.entidad.*;
import com.mef.erp.modelo.entidad.cfdi.CFDIEmpleado;
import com.mef.erp.modelo.entidad.cfdi.CFDIRecibo;
import com.mef.erp.modelo.entidad.cfdi.CFDIReciboConcepto;
import com.mef.erp.modelo.entidad.cfdi.CFDIReciboHrsExtras;
import com.mef.erp.modelo.entidad.cfdi.CFDIReciboIncapacidad;
import com.mef.erp.modelo.entidad.cfdi.ConfigConceptosSat;
import com.mef.erp.modelo.entidad.contabilidad.ConfiguracionNivelCuenta;
import com.mef.erp.modelo.entidad.contabilidad.CuentasContables;
import com.mef.erp.modelo.entidad.contabilidad.DatosDisponiblesCxnConta;
import com.mef.erp.modelo.entidad.contabilidad.EstrucCuenta;
import com.mef.erp.modelo.entidad.contabilidad.FormatoCnxConta;
import com.mef.erp.modelo.entidad.contabilidad.FormatosCnxContaDet;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Settings;
import org.hibernate.connection.ConnectionProvider;
import org.hibernate.impl.SessionFactoryImpl;

public class HibernateUtil {

    //Se Configuran cada una de las SessionFactory con las cuales se desea trabajar
    private static SessionFactory sessionFactoryMEFMaster = null;
    private static SessionFactory sessionFactoryMEF = null;
    //private static AnnotationConfiguration configMEFMaster = null;

    //  public static final String DEFAULT = "default";
    private static AnnotationConfiguration configDBMaestra = null;
    private static AnnotationConfiguration configDBSimple = null;
    private static SessionFactory sessionToBuild = null;
    public static String funcionLength = "len";
    public static boolean usaTypeBigInt = false;

    //JEVC
    /**
     * Mapa donde se guardan los SessionFactory para cada uno de los ficheros de
     * configuración.
     */
    private static final Map<String, SessionFactory> sessionFactorys = new HashMap();

    private Object[] mensajeError;
    Object[] objects = null;

    public Object[] getMensajeError() {
        return mensajeError;
    }

    public void setMensajeError(Object[] mensajeError) {
        this.mensajeError = mensajeError;
    }

    public HibernateUtil() {
    }

    //JEVC06 || PARA HQLRUNNER
    public void HQLRunnerConfig(String usuario, String password, String BaseDatosConfig, String BaseDatos, String tipoServidor, String Ubicacionservidor, String puertoServidor,
            boolean cambiarConfiguracion) {
        AnnotationConfiguration configuracionMEFMaster = null, configuracionMEF = null;
        objects = new Object[3];
        boolean asignarValores = true;
        boolean cambioPropiedadesConexion = true;
        try {
            if (cambioPropiedadesConexion) {
                //Configuracion MEFMaster
                configuracionMEFMaster = generarMappingMEFMaster(usuario, password, BaseDatosConfig, tipoServidor, Ubicacionservidor, puertoServidor);
                Settings settings = configuracionMEFMaster.buildSettings();
                ConnectionProvider connectionProvider = settings.getConnectionProvider();
                connectionProvider.getConnection();

                //ConfiguracionMEF
                configuracionMEF = generarMappingMEF(usuario, password, BaseDatos, tipoServidor, Ubicacionservidor, puertoServidor);
                Settings s = configuracionMEF.buildSettings();
                ConnectionProvider connectionProvider1 = s.getConnectionProvider();
                connectionProvider1.getConnection();
            } else {
                asignarValores = false;
            }
        } catch (SQLException ex) {
            // Log the exception. 
            //System.err.println("Initial SessionFactory creation failed." + ex);
            System.out.println("Error SQLException" + ex);
            objects[0] = ex.getClass();
            objects[1] = ex.getErrorCode();
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
            asignarValores = false;
        } catch (HibernateException ex) {
            // Log the exception. 
            //    System.err.println("Initial SessionFactory creation failed." + ex);
            System.out.println("Error HibernateException" + ex);
            asignarValores = false;
            objects[0] = ex.getClass();
            objects[1] = "cambiarConfiguraciones.error.HibernateException";
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
        } catch (Exception ex) {
            // Log the exception. 
            //            System.err.println("Initial SessionFactory creation failed." + ex);
            System.out.println("Error Exception" + ex);
            asignarValores = false;
            objects[0] = ex.getClass();
            objects[1] = "cambiarConfiguraciones.error.Exception";
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
        }
        if (asignarValores) {
            try {
                sessionFactoryMEF = configuracionMEF.buildSessionFactory();
                sessionFactoryMEFMaster = configuracionMEFMaster.buildSessionFactory();

                sessionToBuild = configuracionMEFMaster.buildSessionFactory();

                ////  configMEFMaster = configuracionMEFMaster;
                configuracionMEF = null;
                configuracionMEFMaster = null;
                setMensajeError(null);
            } catch (HibernateException ex) {
                sessionFactoryMEF = null;
                //sessionFactoryMEFMaster = null;
                sessionToBuild = null;

                ////configMEFMaster = null;
                objects[0] = ex.getClass();
                objects[1] = "cambiarConfiguraciones.error.HibernateException";
                objects[2] = ex.getMessage();
                setMensajeError(objects);
                objects = null;
                System.out.println("Error HibernateException" + ex);

            } catch (Exception ex) {
                sessionFactoryMEF = null;
                // sessionFactoryMEFMaster = null;
                sessionToBuild = null;
                ////  configMEFMaster = null;
                objects[0] = ex.getClass();
                objects[1] = "cambiarConfiguraciones.error.Exception";
                objects[2] = ex.getMessage();
                setMensajeError(objects);
                objects = null;
                System.out.println("Error Exception" + ex);
            }
        }
    }

    private boolean noChanges(SessionFactoryImpl sessionFactory, String[] dataAcces) {
        if (sessionFactory != null) {
            try {
                Field f = SessionFactoryImpl.class.getDeclaredField("properties");
                f.setAccessible(true);
                Properties p = (Properties) f.get(sessionFactory);
                if (p.get("hibernate.connection.driver_class").equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
                    if (!p.get("hibernate.connection.url").equals("jdbc:sqlserver://" + dataAcces[4] + ":" + dataAcces[5] + ";" + "databaseName=" + dataAcces[2])) {
                        return false;
                    }
                }//mas tipos de servidores...
                if (!p.get("hibernate.connection.username").equals(dataAcces[0])) {
                    return false;
                }
                if (!p.get("hibernate.connection.password").equals(dataAcces[1])) {
                    return false;
                }

            } catch (NoSuchFieldException ex) {
                Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SecurityException ex) {
                Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalArgumentException ex) {
                Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(HibernateUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    //JVC05
    public boolean cambiarConfiguraciones(String configuraciones) {
        AnnotationConfiguration configuracionMEFMaster = null, configuracionMEF = null;

        objects = new Object[3];
        boolean asignarValores = true;
        String[] dataAcces = configuraciones.split("\\|");
        String usuario, password, baseDatos, tipoServidor, ubicacionservidor, puertoServidor, tipoDeBaseDatos;
        usuario = dataAcces[0];
        password = dataAcces[1];
        baseDatos = dataAcces[2];
        tipoServidor = dataAcces[3];
        ubicacionservidor = dataAcces[4];
        puertoServidor = dataAcces[5];
        tipoDeBaseDatos = dataAcces[6];
        String keyDatabase = usuario + "|" + baseDatos + "|" + tipoServidor + "|"
                + ubicacionservidor + "|" + puertoServidor + "|" + tipoDeBaseDatos;
        boolean cambioPropiedadesConexion;
        if (sessionFactorys.containsKey(keyDatabase) && noChanges((SessionFactoryImpl) sessionFactorys.get(keyDatabase), dataAcces)) {
            cambioPropiedadesConexion = false;
        } else {
            cambioPropiedadesConexion = true;
        }

        try {
            if (cambioPropiedadesConexion) {
                if (tipoDeBaseDatos.equals("M")) {
                    //Configuracion MEFMaster
                    configuracionMEFMaster = generarMappingMEFMaster(usuario, password, baseDatos, tipoServidor, ubicacionservidor, puertoServidor);
                    Settings settings = configuracionMEFMaster.buildSettings();
                    ConnectionProvider connectionProvider = settings.getConnectionProvider();
                    connectionProvider.getConnection();
                } else if (tipoDeBaseDatos.equals("N")) {
                    //ConfiguracionMEF
                    configuracionMEF = generarMappingMEF(usuario, password, baseDatos, tipoServidor, ubicacionservidor, puertoServidor);
                    Settings s = configuracionMEF.buildSettings();
                    ConnectionProvider connectionProvider1 = s.getConnectionProvider();
                    connectionProvider1.getConnection();
                }
            } else {
                asignarValores = false;
            }
        } catch (SQLException ex) {
            // Log the exception. 
            //System.err.println("Initial SessionFactory creation failed." + ex);
            System.out.println("Error SQLException" + ex);
            objects[0] = ex.getClass();
            objects[1] = ex.getErrorCode();
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
            asignarValores = false;
            if (tipoDeBaseDatos.equals("M")) {
                configDBMaestra = null;
            } else {
                configDBSimple = null;
            }
            return false;
        } catch (HibernateException ex) {
            // Log the exception. 
            // System.err.println("Initial SessionFactory creation failed." + ex);
            System.out.println("Error HibernateException" + ex);
            asignarValores = false;
            objects[0] = ex.getClass();
            objects[1] = "cambiarConfiguraciones.error.HibernateException";
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
            if (tipoDeBaseDatos.equals("M")) {
                configDBMaestra = null;
            } else {
                configDBSimple = null;
            }
            return false;
        } catch (Exception ex) {
            // Log the exception. 
//            System.err.println("Initial SessionFactory creation failed." + ex);
            System.out.println("Error Exception" + ex);
            asignarValores = false;
            objects[0] = ex.getClass();
            objects[1] = "cambiarConfiguraciones.error.Exception";
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
            if (tipoDeBaseDatos.equals("M")) {
                configDBMaestra = null;
            } else {
                configDBSimple = null;
            }
            return false;
        }
        if (asignarValores) {
            try {
                if (configuracionMEF != null) {
                    configDBSimple = configuracionMEF;
                    sessionToBuild = configuracionMEF.buildSessionFactory();

                    sessionFactorys.put(keyDatabase, sessionToBuild);
                    setMensajeError(null);
                }
                if (configuracionMEFMaster != null) {
                    configDBMaestra = configuracionMEFMaster;
                    sessionToBuild = configuracionMEFMaster.buildSessionFactory();
                    sessionFactorys.put(keyDatabase, sessionToBuild);
                    setMensajeError(null);
                }
            } catch (HibernateException ex) {
                sessionToBuild = null;
                if (sessionFactorys.containsKey(keyDatabase)) {
                    sessionFactorys.remove(keyDatabase);
                }
                if (tipoDeBaseDatos.equals("M")) {
                    configDBMaestra = null;
                } else {
                    configDBSimple = null;
                }
                objects[0] = ex.getClass();
                objects[1] = "cambiarConfiguraciones.error.HibernateException";
                objects[2] = ex.getMessage();
                setMensajeError(objects);
                objects = null;
                System.out.println("Error HibernateException" + ex);
                sessionFactorys.remove(keyDatabase);
                return false;
            }
        }

        return true;
    }

    private AnnotationConfiguration generarMappingMEFMaster(String usuario, String password, String BaseDatosConfig, String tipoServidor, String Ubicacionservidor, String puertoServidor) {
        AnnotationConfiguration configuracionMEFMaster = new AnnotationConfiguration();
        try {
            if (tipoServidor.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
                funcionLength = "len";
                usaTypeBigInt = false;
                configuracionMEFMaster.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
                configuracionMEFMaster.setProperty("hibernate.connection.driver_class", tipoServidor);
                configuracionMEFMaster.setProperty("hibernate.connection.url", "jdbc:sqlserver://" + Ubicacionservidor + ":" + puertoServidor + ";databaseName=" + BaseDatosConfig);
            } else if (tipoServidor.equals("com.mysql.jdbc.Driver")) {
                funcionLength = "length";
                usaTypeBigInt = true;
                configuracionMEFMaster.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuracionMEFMaster.setProperty("hibernate.connection.driver_class", tipoServidor);
                configuracionMEFMaster.setProperty("hibernate.connection.url", "jdbc:mysql://" + Ubicacionservidor + ":" + puertoServidor + "/" + BaseDatosConfig);
            } else {
                funcionLength = "length";
                usaTypeBigInt = true;
                configuracionMEFMaster.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
                configuracionMEFMaster.setProperty("hibernate.connection.driver_class", tipoServidor);
                configuracionMEFMaster.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@" + Ubicacionservidor + ":" + puertoServidor + ":" + BaseDatosConfig);
            }
            configuracionMEFMaster.setProperty("hibernate.connection.username", usuario);
            configuracionMEFMaster.setProperty("hibernate.connection.password", password);
            configuracionMEFMaster.setProperty("hibernate.show_sql", "false");
            configuracionMEFMaster.setProperty("hibernate.hbm2ddl.auto", "update");
            configuracionMEFMaster.setProperty("hibernate.current_session_context_class", "thread");
            configuracionMEFMaster.setProperty("hibernate.jdbc.batch_size", "50");
//            configuracionMEFMaster.setProperty("hibernate.c3p0.min_size", "5");
//            configuracionMEFMaster.setProperty("hibernate.c3p0.max_size", "100");
//            configuracionMEFMaster.setProperty("hibernate.c3p0.timeout", "1800");
//            configuracionMEFMaster.setProperty("hibernate.c3p0.max_statements", "50");
            //configuracionMEFMaster.setProperty("hibernate.connection.isolation", "8");
            configuracionMEFMaster.addAnnotatedClass(AsignaTipoReporte.class);
            configuracionMEFMaster.addAnnotatedClass(Contenedor.class);
            configuracionMEFMaster.addAnnotatedClass(ContenedorPersonalizado.class);
            configuracionMEFMaster.addAnnotatedClass(Cruce.class);
            configuracionMEFMaster.addAnnotatedClass(ElementosAplicacion.class);
            configuracionMEFMaster.addAnnotatedClass(ElementoExterno.class);
            configuracionMEFMaster.addAnnotatedClass(ExternoPersonalizado.class);
            configuracionMEFMaster.addAnnotatedClass(Herramienta.class);
            configuracionMEFMaster.addAnnotatedClass(HerramientaPersonalizada.class);
            configuracionMEFMaster.addAnnotatedClass(Modulo.class);
            configuracionMEFMaster.addAnnotatedClass(Parametros.class);
            configuracionMEFMaster.addAnnotatedClass(Clasificacion.class);
            configuracionMEFMaster.addAnnotatedClass(ParametrosConsulta.class);
            configuracionMEFMaster.addAnnotatedClass(Permisos.class);
            configuracionMEFMaster.addAnnotatedClass(Perfiles.class);
            configuracionMEFMaster.addAnnotatedClass(RazonSocial.class);
            configuracionMEFMaster.addAnnotatedClass(Restriccion.class);
            configuracionMEFMaster.addAnnotatedClass(Sistemas.class);
            configuracionMEFMaster.addAnnotatedClass(TablaDatos.class);
            configuracionMEFMaster.addAnnotatedClass(TablaBase.class);
            configuracionMEFMaster.addAnnotatedClass(TablaPersonalizada.class);
            configuracionMEFMaster.addAnnotatedClass(TipoElemento.class);
            configuracionMEFMaster.addAnnotatedClass(TipoHerramienta.class);
            configuracionMEFMaster.addAnnotatedClass(TipoTabla.class);
            configuracionMEFMaster.addAnnotatedClass(Usuario.class);
            configuracionMEFMaster.addAnnotatedClass(Ventana.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteFuenteDatos.class);
//            configuracionMEFMaster.addAnnotatedClass(ReporteDatos.class);
            configuracionMEFMaster.addAnnotatedClass(DatosConsulta.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteCamposEncabezado.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteDinamico.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteDatosIncluir.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteOpcionGrupos.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteDatosResumen.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteEstilos.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteOrdenGrupo.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteCamposWhere.class);
            configuracionMEFMaster.addAnnotatedClass(ReporteOtrosDatosEncabezado.class);
            configuracionMEFMaster.addAnnotatedClass(RazonSocialConfiguracion.class);
        } catch (Exception ex) {
            System.err.println("generarMappingMEFMaster()Initial AnnotationConfiguration creation failed." + ex);
            objects[0] = ex.getClass();
            objects[1] = "generarMappingMEFMaster()Initial AnnotationConfiguration creation failed.error.HibernateException";
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
        }
        return configuracionMEFMaster;
    }

    private AnnotationConfiguration generarMappingMEF(String usuario, String password, String BaseDatos, String tipoServidor, String Ubicacionservidor, String puertoServidor) {
        AnnotationConfiguration configMEF = new AnnotationConfiguration();
        try {
            if (tipoServidor.equals("com.microsoft.sqlserver.jdbc.SQLServerDriver")) {
                funcionLength = "len";
                usaTypeBigInt = false;
                configMEF.setProperty("hibernate.dialect", "org.hibernate.dialect.SQLServerDialect");
                configMEF.setProperty("hibernate.connection.driver_class", tipoServidor);
                configMEF.setProperty("hibernate.connection.url", "jdbc:sqlserver://" + Ubicacionservidor + ":" + puertoServidor + ";databaseName=" + BaseDatos);
            } else if (tipoServidor.equals("com.mysql.jdbc.Driver")) {
                funcionLength = "length";
                usaTypeBigInt = true;
                configMEF.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configMEF.setProperty("hibernate.connection.driver_class", tipoServidor);
                configMEF.setProperty("hibernate.connection.url", "jdbc:mysql://" + Ubicacionservidor + ":" + puertoServidor + "/" + BaseDatos);
            } else {
                funcionLength = "length";
                usaTypeBigInt = true;
                configMEF.setProperty("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
                configMEF.setProperty("hibernate.connection.driver_class", tipoServidor);
                configMEF.setProperty("hibernate.connection.url", "jdbc:oracle:thin:@" + Ubicacionservidor + ":" + puertoServidor + ":" + BaseDatos);
            }
            configMEF.setProperty("hibernate.connection.username", usuario);
            configMEF.setProperty("hibernate.connection.password", password);
            configMEF.setProperty("hibernate.show_sql", "false");
            configMEF.setProperty("hibernate.format_sql", "true");//JEVC07
            configMEF.setProperty("hibernate.use_sql_comments", "true");//JEVC07
            configMEF.setProperty("hibernate.hbm2ddl.auto", "update");
            configMEF.setProperty("hibernate.current_session_context_class", "thread");
            configMEF.setProperty("hibernate.jdbc.batch_size", "50");
//            configMEF.setProperty("hibernate.c3p0.min_size", "5");
//            configMEF.setProperty("hibernate.c3p0.max_size", "100");
//            configMEF.setProperty("hibernate.c3p0.timeout", "1800");
//            configMEF.setProperty("hibernate.c3p0.max_statements", "50");
//            configMEF.setProperty("hibernate.connection.isolation", "8");
           // configMEF.setProperty("hibernate.order_inserts", "true");
            configMEF.setProperty("hibernate.order_updates", "true");
            configMEF.setProperty("hibernate.connection.isolation", "1");
            //configMEF.setProperty("hibernate.show_sql", "true");
            configMEF.addAnnotatedClass(Asistencias.class);
            configMEF.addAnnotatedClass(Bancos.class);
            configMEF.addAnnotatedClass(BaseAfecConcepNom.class);
            configMEF.addAnnotatedClass(BaseAfectadaGrupo.class);
            configMEF.addAnnotatedClass(BaseNomina.class);
            configMEF.addAnnotatedClass(CampoDIM.class);
            configMEF.addAnnotatedClass(CamposDimConceptos.class);
            configMEF.addAnnotatedClass(Capacitaciones.class);
            configMEF.addAnnotatedClass(CentroDeCosto.class);
            configMEF.addAnnotatedClass(Ciudades.class);
            configMEF.addAnnotatedClass(CreditoAhorro.class);
            configMEF.addAnnotatedClass(Contactos.class);
            configMEF.addAnnotatedClass(ConceptoDeNomina.class);
            configMEF.addAnnotatedClass(ConcepNomDefi.class);
            configMEF.addAnnotatedClass(ConfigConceptosSat.class);
            configMEF.addAnnotatedClass(ConfiguracionAsistencias.class);
            configMEF.addAnnotatedClass(ConfiguracionNivelCuenta.class);
            configMEF.addAnnotatedClass(ConfiguraTimbrado.class);
            configMEF.addAnnotatedClass(ControlVacDeveng.class);
            configMEF.addAnnotatedClass(Cp.class);
            configMEF.addAnnotatedClass(CuentasContables.class);
            configMEF.addAnnotatedClass(Cursos.class);
            configMEF.addAnnotatedClass(DatosDisponiblesCxnConta.class);
            configMEF.addAnnotatedClass(Departamentos.class);
            configMEF.addAnnotatedClass(DetalleAsistencia.class);
            configMEF.addAnnotatedClass(Documentacion.class);
            configMEF.addAnnotatedClass(Estados.class);
            configMEF.addAnnotatedClass(EstrucCuenta.class);
            configMEF.addAnnotatedClass(Estudios.class);
            configMEF.addAnnotatedClass(Empleados.class);
            configMEF.addAnnotatedClass(Excepciones.class);
            configMEF.addAnnotatedClass(ExperienciaLaboralExterna.class);
            configMEF.addAnnotatedClass(ExperienciaLaboralInterna.class);
            configMEF.addAnnotatedClass(Familiares.class);
            configMEF.addAnnotatedClass(Firmas.class);
            configMEF.addAnnotatedClass(FormacionEconomica.class);
            configMEF.addAnnotatedClass(FormasDePago.class);
            configMEF.addAnnotatedClass(FormatoCnxConta.class);
            configMEF.addAnnotatedClass(FormatosCnxContaDet.class);
            configMEF.addAnnotatedClass(Horario.class);
            configMEF.addAnnotatedClass(ImportaCampos.class);
            configMEF.addAnnotatedClass(ConfigFoliacion.class);
            configMEF.addAnnotatedClass(Mascaras.class);
            configMEF.addAnnotatedClass(Monedas.class);
            configMEF.addAnnotatedClass(Municipios.class);
            configMEF.addAnnotatedClass(Paises.class);
            configMEF.addAnnotatedClass(ParaConcepDeNom.class);
            configMEF.addAnnotatedClass(Parentesco.class);
            configMEF.addAnnotatedClass(Periodicidad.class);
            configMEF.addAnnotatedClass(PlazasPorEmpleado.class);
            configMEF.addAnnotatedClass(PlazasPorEmpleadosMov.class);
            configMEF.addAnnotatedClass(Primas.class);
            configMEF.addAnnotatedClass(RazonesSociales.class);
            configMEF.addAnnotatedClass(RegistroIncapacidad.class);
            configMEF.addAnnotatedClass(RegistroPatronal.class);
            configMEF.addAnnotatedClass(TipoCorrida.class);
            configMEF.addAnnotatedClass(TipoNomina.class);
            configMEF.addAnnotatedClass(TiposDeCambio.class);
            configMEF.addAnnotatedClass(ConfiguracionIMSS.class);
            configMEF.addAnnotatedClass(TipoCentroCostos.class);
            configMEF.addAnnotatedClass(TipoDeRedondeo.class);
            configMEF.addAnnotatedClass(DatosTipoValor.class);
            configMEF.addAnnotatedClass(NivelesClasificacion.class);
            configMEF.addAnnotatedClass(Grupo.class);
            configMEF.addAnnotatedClass(Genero.class);
            configMEF.addAnnotatedClass(CategoriasPuestos.class);
            configMEF.addAnnotatedClass(PercepcionesFijas.class);
            configMEF.addAnnotatedClass(Puestos.class);
            configMEF.addAnnotatedClass(Plazas.class);
            configMEF.addAnnotatedClass(Jornada.class);
            configMEF.addAnnotatedClass(TurnosHorariosFijos.class);
            configMEF.addAnnotatedClass(Turnos.class);
            configMEF.addAnnotatedClass(MovNomConcep.class);
            configMEF.addAnnotatedClass(MovNomConceParam.class);
            configMEF.addAnnotatedClass(MovNomBaseAfecta.class);
            configMEF.addAnnotatedClass(ConfiguraMovimiento.class);
            configMEF.addAnnotatedClass(PeriodosNomina.class);
            configMEF.addAnnotatedClass(DetalleImportaCampos.class);
            configMEF.addAnnotatedClass(Incidencias.class);
            configMEF.addAnnotatedClass(Despensa.class);
            configMEF.addAnnotatedClass(CreditoPorEmpleado.class);
            configMEF.addAnnotatedClass(CreditoMovimientos.class);
            configMEF.addAnnotatedClass(CalculoISR.class);
            configMEF.addAnnotatedClass(Resultado.class);
            configMEF.addAnnotatedClass(ConceptoPorTipoCorrida.class);
            configMEF.addAnnotatedClass(TipoContrato.class);
            configMEF.addAnnotatedClass(SalariosIntegrados.class);
            configMEF.addAnnotatedClass(SalariosIntegradosDet.class);
            configMEF.addAnnotatedClass(FiniqLiquidCncNom.class);
            configMEF.addAnnotatedClass(FiniqLiquidPlazas.class);
            configMEF.addAnnotatedClass(FiniquitosLiquidaciones.class);
            configMEF.addAnnotatedClass(IngresosReingresosBajas.class);
            configMEF.addAnnotatedClass(ConceptosEspeciales.class);
            configMEF.addAnnotatedClass(CalculoIMSS.class);
            configMEF.addAnnotatedClass(CalculoIMSSPatron.class);
            configMEF.addAnnotatedClass(CalculoUnidades.class);//JSA06
            configMEF.addAnnotatedClass(DatosPlazasEmpleado.class);
            configMEF.addAnnotatedClass(PtuEmpleados.class);
            configMEF.addAnnotatedClass(PtuDatosGenerales.class);
            configMEF.addAnnotatedClass(CFDIRecibo.class);
            configMEF.addAnnotatedClass(CFDIEmpleado.class);
            configMEF.addAnnotatedClass(CFDIReciboConcepto.class);
            configMEF.addAnnotatedClass(CFDIReciboHrsExtras.class);
            configMEF.addAnnotatedClass(CFDIReciboIncapacidad.class);
            configMEF.addAnnotatedClass(SemaforoCalculoNomina.class);
            configMEF.addAnnotatedClass(SemaforoTimbradoPac.class);
            configMEF.addAnnotatedClass(TiposVacaciones.class);
            configMEF.addAnnotatedClass(VacacionesDevengadas.class);
            configMEF.addAnnotatedClass(VacacionesDisfrutadas.class);
            configMEF.addAnnotatedClass(VacacionesAplicacion.class);
            configMEF.addAnnotatedClass(InasistenciaPorHora.class);
            configMEF.addAnnotatedClass(AguinaldoFechas.class);
            configMEF.addAnnotatedClass(AguinaldoPagos.class);
            configMEF.addAnnotatedClass(AguinaldoConfiguracion.class);
            configMEF.addAnnotatedClass(DiasAguinaldo.class);
        } catch (Exception ex) {
            System.err.println("generarMappingMEF()Initial AnnotationConfiguration creation failed." + ex);
            objects[0] = ex.getClass();
            objects[1] = "generarMappingMEF()Initial AnnotationConfiguration creation failed.error.HibernateException";
            objects[2] = ex.getMessage();
            setMensajeError(objects);
            objects = null;
        }

        return configMEF;
    }

    /**
     * Añade un nuevo fichero de configuración, creando el SessionFactory
     * correspondiente. Para encontrar el fichero de configuración, este debe
     * encontrarse en el classpath, junto al fichero de configuración por
     * defecto de hibernate "hibernate.cfg.xml".
     *
     * @param fileName nombre del fichero de configuración que se quiere añadir.
     * @param key clave con la que se hará referencia a esta configuración en
     * las siguientes llamadas.
     * @throws HibernateException si no se puede crear el SessionFactory basado
     * en el fichero que se pasa como argumento.
     */
    public static void addConfigFile(String fileName, String key) throws HibernateException {
        SessionFactory sessionFactory = new Configuration().configure(fileName).buildSessionFactory();
        sessionFactorys.put(key, sessionFactory);
    }

    /**
     * Devuelve la sesión, para el thread que hace la llamada. Es decir cada
     * thread tiene su propia sesión.
     *
     * @param key clave que identifica la sesión a la que se quiere acceder.
     * @return sesión por defecto, para el thread que hace la llamada.
     * @throws HibernateException
     */
    public static Session currentSession(String key) throws HibernateException {
        Session s = sessionFactorys.get(key).getCurrentSession();
        return s;
    }

    /**
     * Cierra la sesión, para el thread que hace la llamada.
     *
     * @param key clave que identifica la sesión que se quiere cerrar.
     * @throws HibernateException
     */
    public static void closeSession(String key) throws HibernateException {
        sessionFactorys.get(key).close();
    }

    public static Map<String, SessionFactory> getSessionFactorys() {
        return sessionFactorys;
    }

    public static AnnotationConfiguration getConfigDBMaestra() {
        return configDBMaestra;
    }

    public static AnnotationConfiguration getConfigDBSimple() {
        return configDBSimple;
    }

    ///FOR HQL Runner ------------------>>>>>>>>>>>>>>>>>>>>>>>
    public static SessionFactory getSessionFactoryMEFMaster() {
        return sessionFactoryMEFMaster;
    }

    public static SessionFactory getSessionFactoryMEF() {
        return sessionFactoryMEF;
    }

    //------------------------------->>>>>>>>>>>>>>>>>>>>>>>>>
//    public static AnnotationConfiguration getConfigMEFMaster() {
//        return configMEFMaster;
//    }
}
