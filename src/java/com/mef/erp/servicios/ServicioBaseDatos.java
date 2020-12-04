  /**
 * @author: Jose Armando Fecha de Creación: 12/09/2011 Compañía: Macropro
 * Descripción del programa: Esta clase servira para inicializar una bds.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Autor: Armando Fecha: 18-05-2012 Descripción: se agregaron los
 * parametconteneros de los conceptos, tambien se le hicieron cambios a los
 * parametros ya que se les añadio el campo de opcionesParametros
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Armando Fecha:20-06-2012 Descripción: Se cambio el nombre
 * de plazas por PosicionOrganigrama. Se añadio lo inicial ya hechas.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Armando Fecha:23-07-2012 Descripción:Se hizo el cambio a
 * configuracionIMSS por los ajustes en el.
 * -----------------------------------------------------------------------------
 * Clave:JSA05 Autor:Armando Fecha:23/05/2013 Descripción:Se agrego el tipo de
 * tabla PERSONALIZADO. Se agregaron las tablas bases ISR ANUAL, SUBSIDIO ANUAL,
 * ISR POR PERIODICIDAD, SUBSIDIO POR PERIODICIDAD. se agregaron las tablas
 * datos de ISR ANUAL, SUBSIDIO ANUAL.
 * -----------------------------------------------------------------------------
 * Clave:JSA06 Autor:Armando Fecha:08/10/2014 Descripción:Se reestructuraron las
 * claves de las tablas.
 * -----------------------------------------------------------------------------
 * Clave:JSA07 Autor:Armando Fecha:08/10/2014 Descripción:Se agrego la ventana
 * ConfigurarMascaras
 * -----------------------------------------------------------------------------
 * Clave:JSA08 Autor:Armando Fecha:11/08/2015 Descripción:Se cambiaron las
 * ubicaciones de los estados para generar el archivo DIM que se exporta para
 * importar en el dim del sat. OJO NO CAMBIAR SI SALE OTRO PROGRAMA QUE OCUPE
 * OTRO CAMBIO DE ESTADOS, LO TENDREMOS QUE AJUSTAR.
 * -----------------------------------------------------------------------------
 *
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.*;
import com.mef.erp.modelo.entidad.cfdi.ConfigConceptosSat;
import com.mef.erp.modelo.entidad.contabilidad.DatosDisponiblesCxnConta;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import com.mef.erp.util.InicializaReportes;
import com.mef.erp.util.Utilerias;
import com.mef.valida.MensajeLicencia;
import com.mef.valida.Status;
import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

public class ServicioBaseDatos implements ServicioBaseDatosIF {
    
    private StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP").append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();
    private List<Object> listContenido = new ArrayList<Object>();
    HibernateUtil hibernateUtil = new HibernateUtil();//Pendiente
    List<Paises> listPaises = new ArrayList<Paises>();
    List<Estados> listEstados = new ArrayList<Estados>();
    List<Municipios> listMunicipios = new ArrayList<Municipios>();
    List<Ciudades> listCiudades = new ArrayList<Ciudades>();
    List<Cp> listCp = new ArrayList<Cp>();
    private Mensaje mensaje;
    private String pathArchivosKey;
    Utilerias util = new Utilerias();//Pendiente
    
    @Override
    public Mensaje inicializarNuevaMEFMaster(String uuidCxn, int rango, String nombreBaseDatos) {
        mensaje = new Mensaje();
        boolean inicializacionCompleta = true;
        Session session = HibernateUtil.currentSession(uuidCxn);
        
        List<TipoHerramienta> listTipoHerramienta = new ArrayList<TipoHerramienta>();
        TipoHerramienta tipoHerramienta = null;
        List<TipoElemento> listTipoElementos = new ArrayList<TipoElemento>();
        TipoElemento tipoElemento = null;
        List<Perfiles> listPerfiles = new ArrayList<Perfiles>();
        Perfiles perfiles = null;
        List<Usuario> listUsuario = new ArrayList<Usuario>();
        Usuario usuario = null;
        List<RazonSocial> listRazonSocial = new ArrayList<RazonSocial>();
        RazonSocial razonSocial = null;
        List<RazonSocialConfiguracion> listRazonSocialConfiguracion = new ArrayList<RazonSocialConfiguracion>();
        RazonSocialConfiguracion razonSocialConfiguracion = null;
        List<Herramienta> listHerramienta = new ArrayList<Herramienta>();
        Herramienta herramienta = null;
        List<Contenedor> listContenedor = new ArrayList<Contenedor>();
        Contenedor contenedor = null;
        List<Sistemas> listSistemas = new ArrayList<Sistemas>();
        Sistemas sistemas = null;
        List<Modulo> listModulo = new ArrayList<Modulo>();
        Modulo modulo = null;
        List<Ventana> listVentana = new ArrayList<Ventana>();
        Ventana ventana = null;
        List<TipoTabla> listTipoTabla = new ArrayList<TipoTabla>();
        TipoTabla tipoTabla = null;
        List<Parametros> listParametros = new ArrayList<Parametros>();
        Parametros parametros = null;
        List<TablaBase> listTablaBase = new ArrayList<TablaBase>();
        TablaBase tablaBase = null;
        List<ElementosAplicacion> listElementosAplicacion = new ArrayList<ElementosAplicacion>();
        ElementosAplicacion elementosAplicacion = null;
        List<ContenedorPersonalizado> listContenedorPersonalizado = new ArrayList<ContenedorPersonalizado>();
        ContenedorPersonalizado contenedorPersonalizado = null;
        List<TablaDatos> listTablaDatos = new ArrayList<TablaDatos>();
        TablaDatos tablaDatos = null;
        int i = 0, contador = 0;
        try {
            session.beginTransaction();

            /*evalua si ya esta inicializada base de datos*/
            Query query = session.createQuery("SELECT COUNT(c) FROM " + Contenedor.class.getSimpleName() + " c");
            boolean existe = (Long) query.uniqueResult() > 0;
            if (existe) {
                mensaje.setResultado(true);
                mensaje.setNoError(0);
                mensaje.setError("Existe");
                session.getTransaction().commit();
                return mensaje;
            }

            //<editor-fold defaultstate="collapsed" desc="TIPO DE HERRAMIENTA">
            /**
             * ******************
             * TIPO DE HERRAMIENTA ******************
             */
            tipoHerramienta = new TipoHerramienta();
            tipoHerramienta.setId(1);
            tipoHerramienta.setNombre("JMenuBar");
            listTipoHerramienta.add(tipoHerramienta);
            
            tipoHerramienta = new TipoHerramienta();
            tipoHerramienta.setId(2);
            tipoHerramienta.setNombre("JToolBar");
            listTipoHerramienta.add(tipoHerramienta);
            
            for (i = 0; i < listTipoHerramienta.size(); i++) {
                session.save(listTipoHerramienta.get(i));
            }
            //</editor-fold>
            contador = contador + listTipoHerramienta.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="TIPO ELEMENTO">
            /**
             * **************************************
             * TIPO ELEMENTO --Externo: 0=false, 1=true
             * ***************************************
             */
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JMenu");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JMenuItem");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JRadioButtonMenuItem");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JCheckBoxMenuItem");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JSeparator");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JButton");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JCheckBox");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(false);
            tipoElemento.setNombre("JRadioButton");
            listTipoElementos.add(tipoElemento);
            
            tipoElemento = new TipoElemento();
            tipoElemento.setExterno(true);
            tipoElemento.setNombre("Externo");
            listTipoElementos.add(tipoElemento);
            
            for (i = 0; i < listTipoElementos.size(); i++) {
                session.save(listTipoElementos.get(i));
            }
            //</editor-fold>
            contador = contador + listTipoElementos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="PERFILES">
            /**
             * **************************************
             * Perfiles Niveles de acceso: 0=Sistema 1=Administrador 2=Usuario
             * comun ***************************************
             */
            perfiles = new Perfiles();
            perfiles.setClave("01");
            perfiles.setNombre("Sistema");
            perfiles.setNivelAccesoSistema(Byte.valueOf("0"));
            listPerfiles.add(perfiles);
            
            perfiles = new Perfiles();
            perfiles.setClave("02");
            perfiles.setNombre("Administrador");
            perfiles.setNivelAccesoSistema(Byte.valueOf("1"));
            listPerfiles.add(perfiles);
            
            perfiles = new Perfiles();
            perfiles.setClave("03");
            perfiles.setNombre("Operador");
            perfiles.setNivelAccesoSistema(Byte.valueOf("2"));
            listPerfiles.add(perfiles);
            
            for (i = 0; i < listPerfiles.size(); i++) {
                session.save(listPerfiles.get(i));
            }
            //</editor-fold>
            contador = contador + listPerfiles.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="USUARIOS">
            /**
             * **************************************
             * Usuarios Activa Fecha Ex False=0, True=1
             * ***************************************
             */
            usuario = new Usuario();
            usuario.setActivaFechaEx(false);
            usuario.setClave("0001");
            usuario.setNombre("Sistemas");
            usuario.setPassword("827CCB0EEA8A706C4C34A16891F84E7B");
            usuario.setRestringeDominio(false);
            usuario.setRestringeIP(false);
            usuario.setRestringeSubRed(false);
            usuario.setPerfiles(listPerfiles.get(0));
            listUsuario.add(usuario);
            
            usuario = new Usuario();
            usuario.setActivaFechaEx(false);
            usuario.setClave("0002");
            usuario.setNombre("Administrador");
            usuario.setPassword("827CCB0EEA8A706C4C34A16891F84E7B");
            usuario.setRestringeDominio(false);
            usuario.setRestringeIP(false);
            usuario.setRestringeSubRed(false);
            usuario.setPerfiles(listPerfiles.get(1));
            listUsuario.add(usuario);
            
            usuario = new Usuario();
            usuario.setActivaFechaEx(false);
            usuario.setClave("0003");
            usuario.setNombre("Operador");
            usuario.setPassword("827CCB0EEA8A706C4C34A16891F84E7B");
            usuario.setRestringeDominio(false);
            usuario.setRestringeIP(false);
            usuario.setRestringeSubRed(false);
            usuario.setPerfiles(listPerfiles.get(2));
            listUsuario.add(usuario);
            
            for (i = 0; i < listUsuario.size(); i++) {
                session.save(listUsuario.get(i));
            }
            //</editor-fold>
            contador = contador + listUsuario.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="RAZONSOCIAL">
            razonSocial = new RazonSocial();
            razonSocial.setClaveRazonSocial("0001");
            razonSocial.setNombreRazonSocial("Empresa Prueba");
            listRazonSocial.add(razonSocial);
            
            listRazonSocial.add(razonSocial);
            
            for (i = 0; i < listRazonSocial.size(); i++) {
                session.save(listRazonSocial.get(i));
            }
            //</editor-fold>
            contador = contador + listRazonSocial.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="RazonSocialConfiguracion">
            razonSocialConfiguracion = new RazonSocialConfiguracion();
            razonSocialConfiguracion.setRazonSocial(listRazonSocial.get(0));
            razonSocialConfiguracion.setPermitido(false);
            razonSocialConfiguracion.setUsuario(listUsuario.get(0));
            listRazonSocialConfiguracion.add(razonSocialConfiguracion);
            
            razonSocialConfiguracion = new RazonSocialConfiguracion();
            razonSocialConfiguracion.setRazonSocial(listRazonSocial.get(0));
            razonSocialConfiguracion.setPermitido(false);
            razonSocialConfiguracion.setUsuario(listUsuario.get(1));
            listRazonSocialConfiguracion.add(razonSocialConfiguracion);
            
            razonSocialConfiguracion = new RazonSocialConfiguracion();
            razonSocialConfiguracion.setRazonSocial(listRazonSocial.get(0));
            razonSocialConfiguracion.setPermitido(true);
            razonSocialConfiguracion.setUsuario(listUsuario.get(2));
            listRazonSocialConfiguracion.add(razonSocialConfiguracion);
            
            for (i = 0; i < listRazonSocialConfiguracion.size(); i++) {
                session.save(listRazonSocialConfiguracion.get(i));
            }
            //</editor-fold>
            contador = contador + listRazonSocialConfiguracion.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="HERRAMIENTA">
            /**
             * **************************************
             * Herramienta ***************************************
             */
            herramienta = new Herramienta();
            herramienta.setId(1);
            herramienta.setHabilitado(true);
            herramienta.setNombre("Menu Principal");
            herramienta.setPrincipal(true);
            herramienta.setSecundario(false);
            herramienta.setVisible(true);
            herramienta.setCompartir(true);
            herramienta.setTipoHerramienta(listTipoHerramienta.get(0));
            listHerramienta.add(herramienta);
            
            herramienta = new Herramienta();
            herramienta.setId(2);
            herramienta.setHabilitado(true);
            herramienta.setNombre("Menu Acceso Rapido");
            herramienta.setPrincipal(false);
            herramienta.setSecundario(true);
            herramienta.setVisible(true);
            herramienta.setCompartir(true);
            herramienta.setTipoHerramienta(listTipoHerramienta.get(0));
            listHerramienta.add(herramienta);
            
            herramienta = new Herramienta();
            herramienta.setId(3);
            herramienta.setHabilitado(true);
            herramienta.setNombre("Barra herramienta Principal");
            herramienta.setPrincipal(true);
            herramienta.setSecundario(false);
            herramienta.setVisible(true);
            herramienta.setCompartir(true);
            herramienta.setTipoHerramienta(listTipoHerramienta.get(1));
            listHerramienta.add(herramienta);
            
            for (i = 0; i < listHerramienta.size(); i++) {
                session.save(listHerramienta.get(i));
            }
            //</editor-fold>
            contador = contador + listHerramienta.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="SISTEMAS">
            /**
             * ******************
             * SISTEMAS ******************
             */
            sistemas = new Sistemas();
            sistemas.setNombre("NRH");
            sistemas.setClave("1");
            listSistemas.add(sistemas);
            
            sistemas = new Sistemas();
            sistemas.setNombre("ERP");
            sistemas.setClave("2");
            listSistemas.add(sistemas);
            
            for (i = 0; i < listSistemas.size(); i++) {
                session.save(listSistemas.get(i));
            }
            //</editor-fold>
            contador = contador + listSistemas.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="VENTANA">
            /**
             * ******************
             * VENTANA ******************
             */
            ventana = new Ventana();
            ventana.setClave(1);
            ventana.setNombre("AbrirPeriodosNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(2);
            ventana.setNombre("RegistroAhorro");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(3);
            ventana.setNombre("Asistencia");  /// no
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(4);
            ventana.setNombre("CalculoDeNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(5);
            ventana.setNombre("CalculoSDI");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(6);
            ventana.setNombre("CapturaMovimientosNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(7);
            ventana.setNombre("CatalogoBancos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(8);
            ventana.setNombre("CatalogoCategoriasPuestos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(9);
            ventana.setNombre("CatalogoCentroDeCostos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(10);
            ventana.setNombre("CatalogoCiudades");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(11);
            ventana.setNombre("CatalogoConceptosDeNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(12);
            ventana.setNombre("CatalogoConceptosPorTipoCorrida");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(13);
            ventana.setNombre("CatalogoCp");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(14);
            ventana.setNombre("DefinirParametros");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(15);
            ventana.setNombre("CatalogoDepartamentos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(16);
            ventana.setNombre("CatalogoEmpleados");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(17);
            ventana.setNombre("CatalogoEstados");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            ventana = new Ventana();
            
            ventana.setClave(18);
            ventana.setNombre("CatalogoGrupos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(19);
            ventana.setNombre("CatalogoHorario");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(20);
            ventana.setNombre("CatalogoModulos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(21);
            ventana.setNombre("CatalogoMonedas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(22);
            ventana.setNombre("CatalogoMunicipios");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(23);
            ventana.setNombre("CatalogoNivelesClasificacion");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(24);
            ventana.setNombre("CatalogoPaises");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(25);
            ventana.setNombre("CatalogoPerfiles");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGODIALOG);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(26);
            ventana.setNombre("CatalogoPeriodicidad");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(27);
            ventana.setNombre("CatalogoPosicionOrganigrama");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(28);
            ventana.setNombre("CatalogoPuestos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(29);  ///no esta
            ventana.setNombre("CatalogoRazonSocial");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(30);
            ventana.setNombre("CatalogoRegistroPatronal");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(31);
            ventana.setNombre("CatalogoSistemas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(32);
            ventana.setNombre("TablaCruce");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(33);
            ventana.setNombre("CatalogoTipoCentroCostos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(34);
            ventana.setNombre("CatalogoTipoNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(35);
            ventana.setNombre("CapturaTiposDeCambio");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(36);
            ventana.setNombre("CatalogoTiposDeRedondeo");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(37);
            ventana.setNombre("CatalogoTurnos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(38);
            ventana.setNombre("CatalogoUsuarios");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(39);
            ventana.setNombre("CerrarPeriodosNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();  /// no esta
            ventana.setClave(40);
            ventana.setNombre("ConfigFiniquitosLiquidacion");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(41);
            ventana.setNombre("ConfiguracionAhorro");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(42);
            ventana.setNombre("ConfiguracionAsistencia");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(43);
            ventana.setNombre("ConfiguracionCredito");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(44);
            ventana.setNombre("ConfiguracionDespensa");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(45);
            ventana.setNombre("ConfiguracionElementosAplicacion");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(46);
            ventana.setNombre("ConfiguracionesIMSS");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(47);
            ventana.setNombre("ConfiguracionInicialModoCatalogo");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(48);
            ventana.setNombre("ConfiguracionPeriodosNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(49);
            ventana.setNombre("ConfigurarConsultas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();  //---no
            ventana.setClave(50);
            ventana.setNombre("ConstruyeConsulta");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONSULTA);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(51); //--no
            ventana.setNombre("ConstruyeReporte");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(52);
            ventana.setNombre("RegistroCredito");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(53);
            ventana.setNombre("GeneraTimbrado");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(54);
            ventana.setNombre("DefinirConceptosEspeciales");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(55);   //---no
            ventana.setNombre("DefinirParametros");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(56);
            ventana.setNombre("CatalogoDefinirTablas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(57);
            ventana.setNombre("EliminaSaldos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(58);
            ventana.setNombre("CancelaTimbrado");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(59);
            ventana.setNombre("GridTipoTabla");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.GRID);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(60);
            ventana.setNombre("HistorialAhorros");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(61);
            ventana.setNombre("HistorialCreditos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(62);
            ventana.setNombre("CatalogoTiposVacaciones");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(63);
            ventana.setNombre("MenuDinamicoSistema");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(64); ///no
            ventana.setNombre("MovimientosDeNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(65);
            ventana.setNombre("NuevasPlazasYReingresos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(66);
            ventana.setNombre("PromocionModificacionPlazas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(67);
            ventana.setNombre("RegistrarIncapacidades");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(68);
            ventana.setNombre("ConfiguracionVacaciones");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();  //---no
            ventana.setClave(69);
            ventana.setNombre("ConfiguracionTimbrado");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(70);
            ventana.setNombre("TablaValores");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.BASEFORM);
            listVentana.add(ventana);
            
            ventana = new Ventana();             //--- no
            ventana.setClave(71);
            ventana.setNombre("ReporteNominas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.REPORTE);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(72);
            ventana.setNombre("FiniquitosNormal");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(73);
            ventana.setNombre("FiniquitosComplementario");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(74);
            ventana.setNombre("FiniquitosProyeccion");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(75);
            ventana.setNombre("LiquidacionesNormal");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(76);
            ventana.setNombre("LiquidacionesComplementario");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(77);
            ventana.setNombre("LiquidacionesProyeccion");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(78);
            ventana.setNombre("GeneracionDatosTimbre");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(79);   //---no
            ventana.setNombre("BajaEmpleado");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(80);
            ventana.setNombre("ConfigurarReportes");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(81);
            ventana.setNombre("CatalogoDefinirTablasUsuario");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(82);
            ventana.setNombre("ConfiguracionAsistenciasExcepciones");   //--no
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(83);
            ventana.setNombre("ConfigurarTeclasAcceso");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(84);
            ventana.setNombre("CatalogoParentesco");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(85);
            ventana.setNombre("CatalogoEstudios");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(86);
            ventana.setNombre("CatalogoCursos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(87);
            ventana.setNombre("RegistroAbono");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(88);
            ventana.setNombre("RegistroDescuento");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(89);
            ventana.setNombre("RegistroCargos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(90);
            ventana.setNombre("RegistroBloqueos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(91);
            ventana.setNombre("RegistroOtros");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(92);
            ventana.setNombre("ConfiguracionImpresionReportes");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(93);
            ventana.setNombre("CatalogoTipoCorrida");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(94);
            ventana.setNombre("CatalogoFirmas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(95);  ///-no
            ventana.setNombre("RegistroAbonoAhorro");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana(); ///-no
            ventana.setClave(96);
            ventana.setNombre("RegistroDescuentoAhorro");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana(); ///-no
            ventana.setClave(97);
            ventana.setNombre("RegistroCargosAhorro");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana(); ///-no
            ventana.setClave(98);
            ventana.setNombre("RegistroBloqueosAhorro");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana(); ///-no
            ventana.setClave(99);
            ventana.setNombre("RegistroOtrosAhorro");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.MOVIMIENTO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(100);
            ventana.setNombre("ConfiguracionConceptosSat");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(101);
            ventana.setNombre("CatalogoBaseDeNomina");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(102);
            ventana.setNombre("ConfiguracionMascaras");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(103);
            ventana.setNombre("ConfiguracionFoliacion");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(104);
            ventana.setNombre("ConfiguracionConceptosDIM");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(105);
            ventana.setNombre("ConsultaRazonesSociales");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(106);
            ventana.setNombre("ParametroCuentaContable");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(107);
            ventana.setNombre("CatalagoCuentasContables");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(108);
            ventana.setNombre("CatalagoFormatoCuentasContables");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(109);
            ventana.setNombre("GeneradorPolizas");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(110);
            ventana.setNombre("CalculoPtu");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(111);
            ventana.setNombre("CatalogoGenero");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(112);
            ventana.setNombre("Inasistencias");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CATALOGO);
            listVentana.add(ventana);
            
            ventana = new Ventana();
            ventana.setClave(113);
            ventana.setNombre("ConfiguracionAguinaldos");
            ventana.setSistemas(listSistemas.get(0));
            ventana.setTipoVentana(TipoVentana.CONFIGURACION);
            listVentana.add(ventana);
            
            for (i = 0; i < listVentana.size(); i++) {
                session.save(listVentana.get(i));
            }
            //</editor-fold>
            contador = contador + listVentana.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="CONTENEDORES">
            //<editor-fold defaultstate="collapsed" desc="Menu Principal">   
            //<editor-fold defaultstate="collapsed" desc="parendId=0">
            contenedor = new Contenedor();
            contenedor.setId(1);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("captura.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Captura");
            contenedor.setOrdenId(1);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.ACCIONMULTIPLE);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(2);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("calculos.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cálculos");
            contenedor.setOrdenId(2);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(3);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("consulta.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Consultas");
            contenedor.setOrdenId(3);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTAPADRE);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(4);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("reportes.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reportes del diseñador");
            contenedor.setOrdenId(5);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTEPADRE);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(5);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("otros_procesos.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("O");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Otros Procesos");
            contenedor.setOrdenId(7);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(6);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("vacaciones.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("V");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Vacaciones");
            contenedor.setOrdenId(8);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(7);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("finiquitos_liquidaciones.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("F");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Finiquitos y Liquidaciones");
            contenedor.setOrdenId(9);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(8);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("utilerias.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("U");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Utilerias");
            contenedor.setOrdenId(10);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(9);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("personalizacion.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Personalización");
            contenedor.setOrdenId(11);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(10);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("catalogos.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Catálogos");
            contenedor.setOrdenId(12);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(11);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("configuraciones.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("n");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuraciones");
            contenedor.setOrdenId(13);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(12);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("listas.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("L");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Listas");
            contenedor.setOrdenId(14);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(13);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono("diseñadores.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Diseñador de tablas");
            contenedor.setOrdenId(15);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(14);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("sistema.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("S");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Sistema");
            contenedor.setOrdenId(16);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(15);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono("salir.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("S");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Salir");
            contenedor.setOrdenId(17);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.SINACCION);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(16);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("reportes.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reportes del sistema");
            contenedor.setOrdenId(4);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOREPORTEPADRE);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(17);
            contenedor.setAccion(null);
            contenedor.setHabilitado(true);
            contenedor.setIcono("CFDI.png");
            contenedor.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("CFDI");
            contenedor.setOrdenId(6);
            contenedor.setParentId(0);
            contenedor.setTipoAcciones(TipoAcciones.ACCIONMULTIPLE);
            contenedor.setVisible(true);
            contenedor.setCompartir(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(0));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=1">
            contenedor = new Contenedor();
            contenedor.setId(48);
            contenedor.setAccion("Sistema");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Movtos. masivos por concepto");
            contenedor.setOrdenId(460);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOMOVIMIENTONOMINA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(1L);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(49);
            contenedor.setAccion("Sistema");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Movtos. masivos por empleado");
            contenedor.setOrdenId(461);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOMOVIMIENTONOMINA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(2L);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(45);
            contenedor.setAccion("Sistema");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Asistencias por empleado");
            contenedor.setOrdenId(463);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOASISTENCIA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(1L);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(46);
            contenedor.setAccion("Sistema");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Asistencia por excepción");
            contenedor.setOrdenId(464);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOASISTENCIA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(2L);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(20);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registrar Incapacidades");
            contenedor.setOrdenId(20);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(66));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(21);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro de Créditos");
            contenedor.setOrdenId(21);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(51));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(22);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro de Ahorro");
            contenedor.setOrdenId(22);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(1));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(23);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Historial de Créditos");
            contenedor.setOrdenId(23);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(60));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(24);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Historial Ahorro");
            contenedor.setOrdenId(24);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(59));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(25);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro de Cargos");
            contenedor.setOrdenId(25);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(88));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(26);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("B");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro de Bloqueos");
            contenedor.setOrdenId(26);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(89));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(27);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("O");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro Otros");
            contenedor.setOrdenId(27);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(90));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(28);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("A");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro Abono");
            contenedor.setOrdenId(28);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(86));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(29);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro Descuento");
            contenedor.setOrdenId(29);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(87));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(151);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Inasistencias por hora");
            contenedor.setOrdenId(151);
            contenedor.setParentId(1);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(111));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=2">
            contenedor = new Contenedor();
            contenedor.setId(30);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Calcular Nómina");
            contenedor.setOrdenId(30);
            contenedor.setParentId(2);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(3));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(31);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("F");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cálculo SDI");
            contenedor.setOrdenId(31);
            contenedor.setParentId(2);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(4));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(32);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("O");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cálculo PTU");
            contenedor.setOrdenId(31);
            contenedor.setParentId(2);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(109));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=5">
            contenedor = new Contenedor();
            contenedor.setId(40);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cerrar Período");
            contenedor.setOrdenId(40);
            contenedor.setParentId(5);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(38));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(41);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reabrir Período");
            contenedor.setOrdenId(41);
            contenedor.setParentId(5);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(0));
            listContenedor.add(contenedor);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=6">
//            contenedor = new Contenedor();
//            contenedor.setId(50);
//            contenedor.setAccion(null);
//            contenedor.setCompartir(true);
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(null);
//            contenedor.setKeyCode("V");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Registrar Vacaciones");
//            contenedor.setOrdenId(50);
//            contenedor.setParentId(6);
//            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
//            contenedor.setVisible(true);
//            contenedor.setHerramienta(listHerramienta.get(0));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            contenedor.setVentana(listVentana.get(67));
//            listContenedor.add(contenedor);

//            contenedor = new Contenedor();
//            contenedor.setId(51);
//            contenedor.setAccion(null);
//            contenedor.setCompartir(true);
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(null);
//            contenedor.setKeyCode("H");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Historial Vacacional");
//            contenedor.setOrdenId(51);
//            contenedor.setParentId(6);
//            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
//            contenedor.setVisible(true);
//            contenedor.setHerramienta(listHerramienta.get(0));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            contenedor.setVentana(listVentana.get(61));
//            listContenedor.add(contenedor);
            contenedor = new Contenedor();
            contenedor.setId(50);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("H");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuración de Vacaciones");
            contenedor.setOrdenId(465);
            contenedor.setParentId(6);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(67));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=7">
            contenedor = new Contenedor();
            contenedor.setId(60);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("N");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Finiquitos Normales");
            contenedor.setOrdenId(60);
            contenedor.setParentId(7);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(71));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(61);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Finiquitos Complementarios");
            contenedor.setOrdenId(61);
            contenedor.setParentId(7);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(72));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(62);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Finiquitos Proyección");
            contenedor.setOrdenId(62);
            contenedor.setParentId(7);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(73));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(63);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Liquidaciones Normales");
            contenedor.setOrdenId(63);
            contenedor.setParentId(7);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(74));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(64);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Liquidaciones Complementarios");
            contenedor.setOrdenId(64);
            contenedor.setParentId(7);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(75));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(65);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Liquidaciones de proyeccion");
            contenedor.setOrdenId(65);
            contenedor.setParentId(7);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(76));
            listContenedor.add(contenedor);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=8">
            contenedor = new Contenedor();
            contenedor.setId(66);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("H");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Elimina cálculo de nómina");
            contenedor.setOrdenId(66);
            contenedor.setParentId(8);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(56));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(150);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Conexión contable");
            contenedor.setOrdenId(148);
            contenedor.setParentId(8);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(108));
            listContenedor.add(contenedor);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=9">

            contenedor = new Contenedor();
            contenedor.setId(71);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Tablas de Cruce");
            contenedor.setOrdenId(71);
            contenedor.setParentId(9);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(31));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(72);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Definir parámetros");
            contenedor.setOrdenId(72);
            contenedor.setParentId(9);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(13));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(73);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Tipos de Tablas");
            contenedor.setOrdenId(73);
            contenedor.setParentId(9);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(58));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(76);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Capturar Tablas");
            contenedor.setOrdenId(76);
            contenedor.setParentId(9);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(69));
            listContenedor.add(contenedor);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=10">
            contenedor = new Contenedor();
            contenedor.setId(80);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Conceptos de Nómina");
            contenedor.setOrdenId(80);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(10));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(81);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Categorías de Puestos");
            contenedor.setOrdenId(81);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(7));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(82);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Posición organigrama");
            contenedor.setOrdenId(82);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(26));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(97);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Departamentos");
            contenedor.setOrdenId(83);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(14));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(54);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Centros de Costo");
            contenedor.setOrdenId(84);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(8));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(83);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Puestos");
            contenedor.setOrdenId(85);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(27));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(84);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("H");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Horarios");
            contenedor.setOrdenId(86);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(18));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(85);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Turnos");
            contenedor.setOrdenId(87);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(36));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(86);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("E");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Empleados");
            contenedor.setOrdenId(88);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(15));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(87);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Ingresos y Reingresos");
            contenedor.setOrdenId(89);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(64));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(88);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Promociones y modificaciones");
            contenedor.setOrdenId(90);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(65));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(148);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cuentas contables");
            contenedor.setOrdenId(148);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(106));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(149);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Formato cuentas contables");
            contenedor.setOrdenId(149);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(107));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(460);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("A");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Géneros");
            contenedor.setOrdenId(467);
            contenedor.setParentId(10);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(110));
            listContenedor.add(contenedor);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=11">
            contenedor = new Contenedor();
            contenedor.setId(90);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("B");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Bancos");
            contenedor.setOrdenId(90);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(6));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(91);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("A");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Registro Patronal");
            contenedor.setOrdenId(91);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(29));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(92);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Tipos de Cambio");
            contenedor.setOrdenId(92);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(34));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(93);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Políticas de Redondeo");
            contenedor.setOrdenId(93);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(35));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(94);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("N");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Niveles de Clasificacion");
            contenedor.setOrdenId(94);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(22));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(95);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Tipos Centro Costos");
            contenedor.setOrdenId(95);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(32));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(96);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("G");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Grupos");
            contenedor.setOrdenId(96);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(17));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(99);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuraciones IMSS");
            contenedor.setOrdenId(99);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(45));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(100);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configurar Créditos");
            contenedor.setOrdenId(100);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(42));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(101);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("A");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configurar Ahorros");
            contenedor.setOrdenId(101);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(40));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(102);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuracion Despensa");
            contenedor.setOrdenId(102);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(43));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(128);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Tipo de corrida");
            contenedor.setOrdenId(103);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(2));
            contenedor.setVentana(listVentana.get(92));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(135);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Conceptos para Corrida");
            contenedor.setOrdenId(104);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(11));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(130);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Periodicidades");
            contenedor.setOrdenId(105);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(25));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(103);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Tipos de Nómina");
            contenedor.setOrdenId(106);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(33));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(104);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Períodos de Nómina");
            contenedor.setOrdenId(107);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(47));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(106);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configura reportes a usar");
            contenedor.setOrdenId(109);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(91));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(107);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuracion conceptos Sat");
            contenedor.setOrdenId(110);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(99));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(119);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("J");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Firmas");
            contenedor.setOrdenId(112);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(2));
            contenedor.setVentana(listVentana.get(93));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(120);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("J");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuracion campos dim");
            contenedor.setOrdenId(120);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(2));
            contenedor.setVentana(listVentana.get(103));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(121);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Tipos de vacaciones");
            contenedor.setOrdenId(121);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(2));
            contenedor.setVentana(listVentana.get(61));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(461);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("A");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuracion Aguinaldo");
            contenedor.setOrdenId(467);
            contenedor.setParentId(11);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(2));
            contenedor.setVentana(listVentana.get(112));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=12">
            contenedor = new Contenedor();
            contenedor.setId(110);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Código Postal");
            contenedor.setOrdenId(110);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(12));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(111);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Países");
            contenedor.setOrdenId(111);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(23));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(112);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("E");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Estados");
            contenedor.setOrdenId(112);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(16));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(113);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Municipios");
            contenedor.setOrdenId(113);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(21));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(114);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Ciudades");
            contenedor.setOrdenId(114);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(9));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(115);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Monedas");
            contenedor.setOrdenId(115);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(20));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(116);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Parentesco");
            contenedor.setOrdenId(116);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(83));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(117);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("E");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Estudios");
            contenedor.setOrdenId(117);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(84));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(118);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cursos");
            contenedor.setOrdenId(118);
            contenedor.setParentId(12);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(85));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=13">
            contenedor = new Contenedor();
            contenedor.setId(140);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Diseñar Consultas");
            contenedor.setOrdenId(140);
            contenedor.setParentId(13);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(48));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(141);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Diseñar Reportes");
            contenedor.setOrdenId(141);
            contenedor.setParentId(13);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(79));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(74);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Definir Tablas del Sistema");
            contenedor.setOrdenId(74);
            contenedor.setParentId(13);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(55));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(75);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("D");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Definir Tablas del Usuario");
            contenedor.setOrdenId(75);
            contenedor.setParentId(13);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(80));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=14">
            contenedor = new Contenedor();
            contenedor.setId(125);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("S");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Sistemas");
            contenedor.setOrdenId(125);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(30));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(126);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Módulos de NRH");
            contenedor.setOrdenId(126);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(19));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(127);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Estructura Menú");
            contenedor.setOrdenId(127);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(62));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(70);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("E");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Elementos de Personalización");//Elementos de Aplicacion
            contenedor.setOrdenId(128);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(44));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(105);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("G");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configurar teclas de acceso");
            contenedor.setOrdenId(129);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(82));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(129);
            contenedor.setAccion(null);
            contenedor.setCompartir(false);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("P");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Perfiles Usuarios");
            contenedor.setOrdenId(130);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(24));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(131);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configurar Mov. Nónima");
            contenedor.setOrdenId(131);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(5));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(132);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configurar Asistencias");
            contenedor.setOrdenId(132);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(41));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(133);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Modos de Catálogo");
            contenedor.setOrdenId(133);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(46));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(134);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Definir Conceptos Especiales");
            contenedor.setOrdenId(134);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(53));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(136);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("E");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuracion asistencias excepciones");
            contenedor.setOrdenId(136);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(81));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(137);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("B");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Bases de nomina");
            contenedor.setOrdenId(137);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(100));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(143);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuración Mascaras");
            contenedor.setOrdenId(143);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(101));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(144);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configuración Foliación");
            contenedor.setOrdenId(144);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(102));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(108);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Configurar timbrado");
            contenedor.setOrdenId(145);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(68));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(147);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Parametros Cuentas contables");
            contenedor.setOrdenId(147);
            contenedor.setParentId(14);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(105));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=15">
            contenedor = new Contenedor();
            contenedor.setId(142);
            contenedor.setAccion("accion.Salir");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("S");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Salir");
            contenedor.setOrdenId(142);
            contenedor.setParentId(15);
            contenedor.setTipoAcciones(TipoAcciones.SALIRSISTEMA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(145);
            contenedor.setAccion("accion.CerrarSesion");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("Y");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cerrar Sesion");
            contenedor.setOrdenId(145);
            contenedor.setParentId(15);
            contenedor.setTipoAcciones(TipoAcciones.CERRARSESSION);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(146);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("Y");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cambiar razón social");
            contenedor.setOrdenId(146);
            contenedor.setParentId(15);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(104));
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=16">
            contenedor = new Contenedor();
            contenedor.setId(170);
            contenedor.setAccion("ReporteNomina|1|1");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de nómina");
            contenedor.setOrdenId(1);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(171);
            contenedor.setAccion("ReporteMovimientos|2|1");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de movimientos");
            contenedor.setOrdenId(2);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(172);
            contenedor.setAccion("ReciboNomina|4|1");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de recibo de nómina");
            contenedor.setOrdenId(3);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(173);
            contenedor.setAccion("ResumenPercepDeducc|5|1");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de percepción y deducción");
            contenedor.setOrdenId(4);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(174);
            contenedor.setAccion("ReporteIncapacidad|3|2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de incapacidades");
            contenedor.setOrdenId(5);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(175);
            contenedor.setAccion("ReporteIncidencias|0|8");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de incidencias");
            contenedor.setOrdenId(6);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(176);
            contenedor.setAccion("ReporteBasesGravables|6|4");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de bases gravables");
            contenedor.setOrdenId(7);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(177);
            contenedor.setAccion("ReporteIntegrados|0|3");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de integrados");
            contenedor.setOrdenId(8);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(178);
            contenedor.setAccion("ReporteImpuestoSobreNomina|0|7");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de impuesto sobre nómina");
            contenedor.setOrdenId(9);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(179);
            contenedor.setAccion("ReporteCFDI|0|5");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de CFDI");
            contenedor.setOrdenId(10);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(180);
            contenedor.setAccion("ReporteCFDI|7|5");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de resumen CFDI");
            contenedor.setOrdenId(11);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(181);
            contenedor.setAccion("ReporteReciboNominaCFDI|11|5");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte recibo nomina CFDI");
            contenedor.setOrdenId(12);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(182);
            contenedor.setAccion("Otros|8|6");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte de acumulados de nómina");
            contenedor.setOrdenId(13);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(183);
            contenedor.setAccion("ReportePTU|13|10");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Reporte PTU");
            contenedor.setOrdenId(14);
            contenedor.setParentId(16);
            contenedor.setTipoAcciones(TipoAcciones.REPORTESFIJOS);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            listContenedor.add(contenedor);

            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="parentid=17">
            contenedor = new Contenedor();
            contenedor.setId(42);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("G");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Genera datos timbrado");
            contenedor.setOrdenId(42);
            contenedor.setParentId(17);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(77));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(43);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("T");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Genera timbrado");
            contenedor.setOrdenId(43);
            contenedor.setParentId(17);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(52));
            listContenedor.add(contenedor);
            
            contenedor = new Contenedor();
            contenedor.setId(44);
            contenedor.setAccion(null);
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("Cancela timbrado");
            contenedor.setOrdenId(44);
            contenedor.setParentId(17);
            contenedor.setTipoAcciones(TipoAcciones.VENTANA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(listVentana.get(57));
            listContenedor.add(contenedor);

            //</editor-fold>
//            //<editor-fold defaultstate="collapsed" desc="Menu Acceso Rapido (Secundario)">
//            //Menu Catalogo
//            contenedor = new Contenedor();
//            contenedor.setId(50);
//            contenedor.setAccion(".");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("C");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Catalogos");
//            contenedor.setOrdenId(1);
//            contenedor.setParentId(0);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//
//            //Menu Configuracion
//            contenedor = new Contenedor();
//            contenedor.setId(51);
//            contenedor.setAccion(".");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("C");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Configuracion");
//            contenedor.setOrdenId(2);
//            contenedor.setParentId(0);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(0));
//            listContenedor.add(contenedor);
//
//            //Menu Salir
//            contenedor = new Contenedor();
//            contenedor.setId(52);
//            contenedor.setAccion("accion.Salir");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("S");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Salir");
//            contenedor.setOrdenId(3);
//            contenedor.setParentId(0);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(5));
//            listContenedor.add(contenedor);
//            //<editor-fold defaultstate="collapsed" desc="Items Menu Catalogo">
//            contenedor = new Contenedor();
//            contenedor.setId(53);
//            contenedor.setAccion("CatalogoRegistroPatronal");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("R");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Registros Patronales");
//            contenedor.setOrdenId(1);
//            contenedor.setParentId(50);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//
//            contenedor = new Contenedor();
//            contenedor.setId(54);
//            contenedor.setAccion("CatalogoCentroDeCostos");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("C");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Centro de costos");
//            contenedor.setOrdenId(2);
//            contenedor.setParentId(50);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//
//            contenedor = new Contenedor();
//            contenedor.setId(55);
//            contenedor.setAccion("CatalogoDepartamentos");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("D");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Departamentos");
//            contenedor.setOrdenId(3);
//            contenedor.setParentId(50);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//
//            contenedor = new Contenedor();
//            contenedor.setId(56);
//            contenedor.setAccion("CatalogoPlazas");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("P");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Plazas");
//            contenedor.setOrdenId(4);
//            contenedor.setParentId(50);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//
//            contenedor = new Contenedor();
//            contenedor.setId(57);
//            contenedor.setAccion("CatalogoTipoNomina");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("T");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Tipo de Nomina");
//            contenedor.setOrdenId(5);
//            contenedor.setParentId(50);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//
//            contenedor = new Contenedor();
//            contenedor.setId(58);
//            contenedor.setAccion("CatalogoHorario");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("H");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Horario");
//            contenedor.setOrdenId(6);
//            contenedor.setParentId(50);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//
//            contenedor = new Contenedor();
//            contenedor.setId(59);
//            contenedor.setAccion("CatalogoTurnos");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("T");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Turnos");
//            contenedor.setOrdenId(7);
//            contenedor.setParentId(50);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//            //</editor-fold>
//
//            //<editor-fold defaultstate="collapsed" desc="Items Menu Configuracion">           
//            contenedor = new Contenedor();
//            contenedor.setId(60);
//            contenedor.setAccion("CatalogoTiposDeCambio");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("T");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Tipo de cambio");
//            contenedor.setOrdenId(1);
//            contenedor.setParentId(51);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//            //</editor-fold>
//
//            //<editor-fold defaultstate="collapsed" desc="Items Menu Salir">
//            contenedor = new Contenedor();
//            contenedor.setId(61);
//            contenedor.setAccion("accion.Salir");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("S");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Salir");
//            contenedor.setOrdenId(1);
//            contenedor.setParentId(52);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(1));
//            contenedor.setTipoElemento(listTipoElementos.get(1));
//            listContenedor.add(contenedor);
//            //</editor-fold>
//
//            //</editor-fold>
//
//            //<editor-fold defaultstate="collapsed" desc="index Tools Principal">
//            contenedor = new Contenedor();
//            contenedor.setId(21);
//            contenedor.setAccion("accion.Salir");
//            contenedor.setHabilitado(true);
//            contenedor.setIcono(".");
//            contenedor.setKeyCode("S");
//            contenedor.setModifiers("CTRL");
//            contenedor.setNombre("Salir");
//            contenedor.setOrdenId(1);
//            contenedor.setParentId(0);
//            contenedor.setVisible(true);
//            contenedor.setCompartir(true);
//            contenedor.setHerramienta(listHerramienta.get(2));
//            contenedor.setTipoElemento(listTipoElementos.get(5));
//            listContenedor.add(contenedor);
//            //</editor-fold>
            for (i = 0; i < listContenedor.size(); i++) {
                System.out.println(listContenedor.get(i).getId() + " " + listContenedor.get(i).getNombre());
                session.save(listContenedor.get(i));
            }
            //</editor-fold>
            contador = contador + listContenedor.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="MODULO">
            /**
             * ******************
             * MODULO ******************
             */
            modulo = new Modulo();
            modulo.setClave("1");
            modulo.setNombre("Globales");
            modulo.setSistemas(listSistemas.get(0));
            listModulo.add(modulo);
            
            modulo = new Modulo();
            modulo.setClave("2");
            modulo.setNombre("Nomina");
            modulo.setSistemas(listSistemas.get(0));
            listModulo.add(modulo);
            
            modulo = new Modulo();
            modulo.setClave("3");
            modulo.setNombre("Pre-Nomina");
            modulo.setSistemas(listSistemas.get(0));
            listModulo.add(modulo);
            
            for (i = 0; i < listModulo.size(); i++) {
                session.save(listModulo.get(i));
            }
            //</editor-fold>
            contador = contador + listModulo.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="TIPO DE TABLA">
            /**
             * ******************
             * TIPO DE TABLA ******************
             */
            tipoTabla = new TipoTabla();
            tipoTabla.setClave("1");
            tipoTabla.setNombre("ISR");
            tipoTabla.setSistema(true);
            listTipoTabla.add(tipoTabla);
            
            tipoTabla = new TipoTabla();
            tipoTabla.setClave("2");
            tipoTabla.setNombre("SUBSIDIO");
            tipoTabla.setSistema(true);
            listTipoTabla.add(tipoTabla);
            
            tipoTabla = new TipoTabla();
            tipoTabla.setClave("3");
            tipoTabla.setNombre("SALARIOSMÍNIMOS");
            tipoTabla.setSistema(true);
            listTipoTabla.add(tipoTabla);
            
            tipoTabla = new TipoTabla();
            tipoTabla.setClave("4");
            tipoTabla.setNombre("FACTORINTEGRACION");
            tipoTabla.setSistema(true);
            listTipoTabla.add(tipoTabla);
            
            tipoTabla = new TipoTabla();
            tipoTabla.setClave("5");
            tipoTabla.setNombre("DIASFESTIVOS");
            tipoTabla.setSistema(true);
            listTipoTabla.add(tipoTabla);
            
            tipoTabla = new TipoTabla();//JSA05
            tipoTabla.setClave("6");
            tipoTabla.setNombre("PERSONALIZADOS");
            tipoTabla.setSistema(false);
            listTipoTabla.add(tipoTabla);
            
            for (i = 0; i < listTipoTabla.size(); i++) {
                session.save(listTipoTabla.get(i));
            }
            //</editor-fold>
            contador = contador + listTipoTabla.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="ELEMENTOAPLICACION">
            /**
             * ******************
             * ELEMENTO APLICACION ******************
             */
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("1"));
            elementosAplicacion.setClave("01");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.RazonesSociales"));
            elementosAplicacion.setNombre("Empresas");
            elementosAplicacion.setOrdenId(Long.valueOf("0"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("2"));
            elementosAplicacion.setClave("02");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.RegistroPatronal"));
            elementosAplicacion.setNombre("Reg.Patro");
            elementosAplicacion.setOrdenId(Long.valueOf("1"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("3"));
            elementosAplicacion.setClave("03");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.CentroDeCosto"));
            elementosAplicacion.setNombre("Centro de costo");
            elementosAplicacion.setOrdenId(Long.valueOf("2"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("4"));
            elementosAplicacion.setClave("04");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Departamentos"));
            elementosAplicacion.setNombre("Departamento");
            elementosAplicacion.setOrdenId(Long.valueOf("3"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("5"));
            elementosAplicacion.setClave("05");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Empleados"));
            elementosAplicacion.setNombre("Empleado");
            elementosAplicacion.setOrdenId(Long.valueOf("4"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("6"));
            elementosAplicacion.setClave("06");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.TipoNomina"));
            elementosAplicacion.setNombre("Tipo de nomina");
            elementosAplicacion.setOrdenId(Long.valueOf("5"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("7"));
            elementosAplicacion.setClave("07");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.CategoriasPuestos"));
            elementosAplicacion.setNombre("Categoria de puesto");
            elementosAplicacion.setOrdenId(Long.valueOf("6"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("8"));
            elementosAplicacion.setClave("08");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Puestos"));
            elementosAplicacion.setNombre("Puesto");
            elementosAplicacion.setOrdenId(Long.valueOf("7"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("9"));
            elementosAplicacion.setClave("09");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.TipoCorrida"));
            elementosAplicacion.setNombre("Tipo de corrida");
            elementosAplicacion.setOrdenId(Long.valueOf("8"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("10"));
            elementosAplicacion.setClave("10");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.TipoCentroCostos"));
            elementosAplicacion.setNombre("Tipo centro de costo");
            elementosAplicacion.setOrdenId(Long.valueOf("10"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("11"));
            elementosAplicacion.setClave("11");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.NivelesClasificacion"));
            elementosAplicacion.setNombre("Niveles de clasificacion");
            elementosAplicacion.setOrdenId(Long.valueOf("11"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("12"));
            elementosAplicacion.setClave("12");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Grupo"));
            elementosAplicacion.setNombre("Grupo de nomina");
            elementosAplicacion.setOrdenId(Long.valueOf("12"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("13"));
            elementosAplicacion.setClave("13");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Periodicidad"));
            elementosAplicacion.setNombre("Periodicidad");
            elementosAplicacion.setOrdenId(Long.valueOf("13"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("14"));
            elementosAplicacion.setClave("14");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Usuario"));
            elementosAplicacion.setNombre("Usuario");
            elementosAplicacion.setOrdenId(Long.valueOf("14"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);
            
            elementosAplicacion = new ElementosAplicacion();
            elementosAplicacion.setId(Long.valueOf("15"));
            elementosAplicacion.setClave("15");
            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Estados"));
            elementosAplicacion.setNombre("Estados");
            elementosAplicacion.setOrdenId(Long.valueOf("15"));
            elementosAplicacion.setParentId(Long.valueOf("0"));
            listElementosAplicacion.add(elementosAplicacion);

//            elementosAplicacion = new ElementosAplicacion();
//            elementosAplicacion.setId(Long.valueOf("15"));
//            elementosAplicacion.setClave("15");
//            elementosAplicacion.setEntidad(Class.forName("com.mef.erp.modelo.entidad.Horario"));
//            elementosAplicacion.setNombre("Horario");
//            elementosAplicacion.setOrdenId(Long.valueOf("9"));
//            elementosAplicacion.setParentId(Long.valueOf("0"));
//            listElementosAplicacion.add(elementosAplicacion);
            for (i = 0; i < listElementosAplicacion.size(); i++) {
                session.save(listElementosAplicacion.get(i));
            }

            //</editor-fold>
            contador = contador + listElementosAplicacion.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Parametros">
            List<ElementosAplicacion> listRazonSocialElemento, listTurnoElement;
            listRazonSocialElemento = new ArrayList<ElementosAplicacion>();
            listRazonSocialElemento.add(listElementosAplicacion.get(0));
//            listTurnoElement = new ArrayList<ElementosAplicacion>();
//            listTurnoElement.add(listElementosAplicacion.get(0));
            /**
             * ******************
             * Parametros ******************
             */
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("1"));
            parametros.setNombre("PermiteTipoCostos");
            parametros.setOrdenId(1);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("2"));
            parametros.setNombre("Dia de inicio de semana.");
            parametros.setOrdenId(2);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Domingo,Lunes,Martes,Miercoles,Jueves,Viernes,Sabado");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Domingo|Lunes|Martes|Miercoles|Jueves|Viernes|Sabado");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("3"));
            parametros.setNombre("Margen de tiempo de Tolerancia para considerar como Checadas Repetidas");
            parametros.setOrdenId(3);
            parametros.setValor("10");
            parametros.setOpcionesParametros("El margen es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("4"));
            parametros.setNombre("Tiempo de Tolerancia Antes de Hora de Entrada");
            parametros.setOrdenId(4);
            parametros.setValor("5");
            parametros.setOpcionesParametros("El margen es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("5"));
            parametros.setNombre("Ajustar los periodos de nomina");
            parametros.setOrdenId(5);
            parametros.setValor("2");
            parametros.setOpcionesParametros("Esto le permitira que los periodos se generen exactos al mes.");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("6"));
            parametros.setNombre("¿Manejar Organigrama por Plaza?");
            parametros.setOrdenId(6);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("7"));
            parametros.setNombre("¿Tipo de tabla de ISR a utilizar?");
            parametros.setOrdenId(7);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Se puede utilizar la tabla mensual o bien por cada periodicidad un tabla correspondiente a ella.");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Mensual|Periodicidad");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.ISR);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("8"));
            parametros.setNombre("Factor de aplicación tabla mensual");
            parametros.setOrdenId(8);
            parametros.setValor("30.4");
            parametros.setOpcionesParametros("introduzca un numero ejemplo: 30 o 30.4");
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|2");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.ISR);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("9"));
            parametros.setNombre("Factor de aplicación tabla anual");
            parametros.setOrdenId(9);
            parametros.setValor("365");
            parametros.setOpcionesParametros("Introduzca un numero");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("3|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.ISR);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("10"));
            parametros.setNombre("¿Desglose interno del ISR?");
            parametros.setOrdenId(10);
            parametros.setValor("2");
            parametros.setOpcionesParametros("Normal y Anual. :: Normal, Anual y Directo. :: Tabla Anual.");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Normal y Anual|Normal, Anual y Directo|Tabla Anual");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.ISR);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("11"));
            parametros.setNombre("¿Modo para ajustar el ISR en el mes?");
            parametros.setOrdenId(11);
            parametros.setValor("1");
            parametros.setOpcionesParametros("1=Se proporciona en cada periodo independiente :: 2=Proporciona cada periodo ajustando al mes :: 3=Proporciona cada periodo independiente según días naturales :: 4=Proporciona cada periodo independiente y ajusta el último periodo del mes :: 5=En el último periodo se ajusta sin proporcionar al mes :: 6=Proporcional con la tabla Anual");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Proporciona c/periodo independiente|Proporciona c/periodo ajustando al mes|Proporciona c/periodo independiente según días naturales|Proporciona c/periodo independiente y ajusta el último periodo del mes|Último periodo sin ajustar al mes|Proporciona con la tabla anual");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.ISR);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("12"));
            parametros.setNombre("¿Calcular en automatico el SDI?");
            parametros.setOrdenId(12);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.IMSS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("13"));
            parametros.setNombre("¿Manejar pagos por hora?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(13);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("14"));
            parametros.setNombre("¿Manejar horas Por?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(14);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Natural|HSM");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("15"));
            parametros.setNombre("¿Manejar tablas de prestaciones?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(15);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.PRESTACIONES);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("16"));
            parametros.setNombre("¿Activar topes salariales?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(16);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.IMSS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("17"));
            parametros.setNombre("¿Activar control de puestos por Registro Patronal?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(17);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("18"));
            parametros.setNombre("¿Manejar salario por tabulador?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(18);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("19"));
            parametros.setNombre("¿Permitir manejar multiplazas?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(19);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.INFORMACIONNOMINAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("20"));
            parametros.setNombre("¿Permitir manejar Departamentos?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(20);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("21"));
            parametros.setNombre("¿Permitir manejar Centros de costos?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(21);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("22"));
            parametros.setNombre("¿Manejar datos de la nomina del empleado por folio o referencia?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(22);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.INFORMACIONNOMINAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("23"));
            parametros.setNombre("¿Activar funcionalidad de catalogo a Movimientos?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(23);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.MOVIMIENTOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("24"));
            parametros.setNombre("¿Minimizar en automatico datos de la nomina?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(24);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("25"));
            parametros.setNombre("¿Forma de alimentar el sueldo o salario del empleado?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(25);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Diario|Semanal|Quincenal|Mensual");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("26"));
            parametros.setNombre("¿Insercion automatica modo grid?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(26);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("27"));
            parametros.setNombre("¿Capturar datos de nomina del empleado separado?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(27);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.INFORMACIONNOMINAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("28"));
            parametros.setNombre("¿Manejar orden calculo en conceptos?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(28);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.CONCEPTOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("29"));
            parametros.setNombre("¿Pagar nomina sobre dias naturales?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(29);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("30"));
            parametros.setNombre("¿Mostrar conceptos con valor 0?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(30);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.CONCEPTOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("31"));
            parametros.setNombre("¿Mostrar captura registro incapacidades?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(31);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("32"));
            parametros.setNombre("¿Mostrar busqueda en los catalogos en automatico?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(32);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("33"));
            parametros.setNombre("Año activo para periodos");
            parametros.setOpcionesParametros("Ingresar el año del periodo a mostrar");
            parametros.setOrdenId(33);
            parametros.setValor("2016");
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("4|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("34"));
            parametros.setNombre("Pais predeterminado");
            parametros.setOpcionesParametros("Mostrar pais predeterminado");
            parametros.setOrdenId(34);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(3);
            parametros.setPropiedadConfig("Paises|Clave|Descripcion");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("35"));
            parametros.setNombre("Generar datos de timbrado al cerrar el periodo?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(35);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("36"));
            parametros.setNombre("Combinar detallado del concepto para el recibo de nómina?");
            parametros.setOpcionesParametros("");
            parametros.setOrdenId(35);
            parametros.setValor("2");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("37"));
            parametros.setNombre("Tiempo de Tolerancia Antes de Hora de Entrada");
            parametros.setOrdenId(37);
            parametros.setValor("5");
            parametros.setOpcionesParametros("El tiempo es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("38"));
            parametros.setNombre("Considerar como retardo si excede tolerancia después de Entrada");
            parametros.setOrdenId(6);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("39"));
            parametros.setNombre("Activar tiempo límite en entrada para considerar como ausentismo");
            parametros.setOrdenId(7);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("40"));
            parametros.setNombre("Tiempo límite para considerar como ausentismo");
            parametros.setOrdenId(8);
            parametros.setValor("5");
            parametros.setOpcionesParametros("El tiempo es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("41"));
            parametros.setNombre("Impedir registro de entrada si llega despues del tiempo límite para ausentismo");
            parametros.setOrdenId(9);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("42"));
            parametros.setNombre("Tolerancia Antes de Hora de Salida");
            parametros.setOrdenId(10);
            parametros.setValor("1");
            parametros.setOpcionesParametros("El tiempo es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("43"));
            parametros.setNombre("Restringir salida antes del tiempo");
            parametros.setOrdenId(11);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("44"));
            parametros.setNombre("Si salida es antes de este tiempo se considera ausentismo");
            parametros.setOrdenId(12);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

//////            parametros = new Parametros();
//////            parametros.setClave(Long.valueOf("45"));
//////            parametros.setNombre("Modalidad para establecer Margen de Tiempo para considerar horas extras");
//////            parametros.setOrdenId(13);
//////            parametros.setValor("1");
//////            parametros.setOpcionesParametros("");//JSA01
//////            parametros.setTipoConfiguracion(1);
//////            parametros.setPropiedadConfig("");
//////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////            parametros.setModulo(listModulo.get(0));
//////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////            listParametros.add(parametros);
//////
//////            parametros = new Parametros();
//////            parametros.setClave(Long.valueOf("46"));
//////            parametros.setNombre("Establecer en minutos");
//////            parametros.setOrdenId(14);
//////            parametros.setValor("1");
//////            parametros.setOpcionesParametros("");//JSA01
//////            parametros.setTipoConfiguracion(1);
//////            parametros.setPropiedadConfig("");
//////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////            parametros.setModulo(listModulo.get(0));
//////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////            listParametros.add(parametros);
//////
//////            parametros = new Parametros();
//////            parametros.setClave(Long.valueOf("47"));
//////            parametros.setNombre("Tiempo en minutos predeterminado para horas extras");
//////            parametros.setOrdenId(15);
//////            parametros.setValor("1");
//////            parametros.setOpcionesParametros("");//JSA01
//////            parametros.setTipoConfiguracion(1);
//////            parametros.setPropiedadConfig("");
//////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////            parametros.setModulo(listModulo.get(0));
//////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////            listParametros.add(parametros);
//////
//////            parametros = new Parametros();
//////            parametros.setClave(Long.valueOf("48"));
//////            parametros.setNombre("Establecer en hora fija");
//////            parametros.setOrdenId(16);
//////            parametros.setValor("1");
//////            parametros.setOpcionesParametros("");//JSA01
//////            parametros.setTipoConfiguracion(1);
//////            parametros.setPropiedadConfig("");
//////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////            parametros.setModulo(listModulo.get(0));
//////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("49"));
            parametros.setNombre("Utilizar tope diario para horas extras");
            parametros.setOrdenId(17);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("50"));
            parametros.setNombre("Tope Horas Dobles Diario");
            parametros.setOrdenId(18);
            parametros.setValor("3");
            parametros.setOpcionesParametros("El tope default es 3");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("1|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("51"));
            parametros.setNombre("Tope Horas Dobles Semanal");
            parametros.setOrdenId(19);
            parametros.setValor("9");
            parametros.setOpcionesParametros("El tope default es 9");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("1|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("52"));
            parametros.setNombre("Solicitar autorización si excede cierta cantidad de hrs extras");
            parametros.setOrdenId(20);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("53"));
            parametros.setNombre("Cantidad de horas extras que no requieren autorización");
            parametros.setOrdenId(21);
            parametros.setValor("0");
            parametros.setOpcionesParametros("La cantidad es de minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("1|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("54"));
            parametros.setNombre("Tipo de Redondeo para horas ordinarias");
            parametros.setOrdenId(22);
            parametros.setValor("1");
            parametros.setOpcionesParametros("El personalizado se puede establecer el redondeo y el factor del mismo, con la opcion de tabla se selecciona una opcion guardado desde el catalago de tipo de redondeos");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Personalizado|Tabla");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("55"));
//            parametros.setNombre("Redondeo y Factor de Redondeo");
//            parametros.setOrdenId(23);
//            parametros.setValor("1");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
//
//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("56"));
//            parametros.setNombre("Tabla");
//            parametros.setOrdenId(24);
//            parametros.setValor("1");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("57"));
            parametros.setNombre("Activar Primer Coffe Break");
            parametros.setOrdenId(25);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("58"));
            parametros.setNombre("Activar checada salida y entrada para primer Coffe Break");
            parametros.setOrdenId(26);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("59"));
//            parametros.setNombre("Etiqueta Primer Coffe Break");
//            parametros.setOrdenId(27);
//            parametros.setValor("Primer coffe break");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("60"));
//            parametros.setNombre("Género para Primer Coffe Break");
//            parametros.setOrdenId(28);
//            parametros.setValor("M");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("61"));
            parametros.setNombre("Activar Tiempo de Comida");
            parametros.setOrdenId(29);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("62"));
            parametros.setNombre("Modalidad del tiempo para comer");
            parametros.setOrdenId(30);
            parametros.setValor("2");
            parametros.setOpcionesParametros("Se especifica el tiempo y un margen de tiempo para tomar comida :: Se especifica el horario");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Por tiempo y margen|Por horario");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

//////////////////////////            parametros = new Parametros();
//////////////////////////            parametros.setClave(Long.valueOf("63"));
//////////////////////////            parametros.setNombre("Se especifica el tiempo y un margen de tiempo para tomar comida");
//////////////////////////            parametros.setOrdenId(31);
//////////////////////////            parametros.setValor("1");
//////////////////////////            parametros.setOpcionesParametros("");//JSA01
//////////////////////////            parametros.setTipoConfiguracion(1);
//////////////////////////            parametros.setPropiedadConfig("");
//////////////////////////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////////////////////////            parametros.setModulo(listModulo.get(0));
//////////////////////////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////////////////////////            listParametros.add(parametros);
//////////////////////////
//////////////////////////            parametros = new Parametros();
//////////////////////////            parametros.setClave(Long.valueOf("64"));
//////////////////////////            parametros.setNombre("Se especifica el horario");
//////////////////////////            parametros.setOrdenId(32);
//////////////////////////            parametros.setValor("1");
//////////////////////////            parametros.setOpcionesParametros("");//JSA01
//////////////////////////            parametros.setTipoConfiguracion(1);
//////////////////////////            parametros.setPropiedadConfig("");
//////////////////////////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////////////////////////            parametros.setModulo(listModulo.get(0));
//////////////////////////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////////////////////////            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("65"));
            parametros.setNombre("Checar salida para comer");
            parametros.setOrdenId(33);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("66"));
            parametros.setNombre("Tolerancia Antes en Comida");
            parametros.setOrdenId(34);
            parametros.setValor("0");
            parametros.setOpcionesParametros("El tiempo es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("67"));
            parametros.setNombre("Restringir salida a comer antes del tiempo");
            parametros.setOrdenId(35);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("68"));
            parametros.setNombre("Tolerancia Después en Comida");
            parametros.setOrdenId(36);
            parametros.setValor("0");
            parametros.setOpcionesParametros("El tiempo es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("69"));
            parametros.setNombre("Considerar como retardo si excede tolerancia después de Entrada");
            parametros.setOrdenId(37);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("70"));
            parametros.setNombre("Tratamiento de Tiempo para comer");
            parametros.setOrdenId(38);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Incluir en el tiempo ordinario :: No considerar como tiempo ordinario");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Incluir en el tiempo ordinario|No considerar como tiempo ordinario");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

//////////////////////            parametros = new Parametros();
//////////////////////            parametros.setClave(Long.valueOf("71"));
//////////////////////            parametros.setNombre("Incluir en el tiempo ordinario");
//////////////////////            parametros.setOrdenId(39);
//////////////////////            parametros.setValor("1");
//////////////////////            parametros.setOpcionesParametros("");//JSA01
//////////////////////            parametros.setTipoConfiguracion(1);
//////////////////////            parametros.setPropiedadConfig("");
//////////////////////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////////////////////            parametros.setModulo(listModulo.get(0));
//////////////////////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////////////////////            listParametros.add(parametros);
//////////////////////
//////////////////////            parametros = new Parametros();
//////////////////////            parametros.setClave(Long.valueOf("72"));
//////////////////////            parametros.setNombre("No considerar como tiempo ordinario");
//////////////////////            parametros.setOrdenId(40);
//////////////////////            parametros.setValor("1");
//////////////////////            parametros.setOpcionesParametros("");//JSA01
//////////////////////            parametros.setTipoConfiguracion(1);
//////////////////////            parametros.setPropiedadConfig("");
//////////////////////            parametros.setElementosAplicacion(listRazonSocialElemento);
//////////////////////            parametros.setModulo(listModulo.get(0));
//////////////////////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//////////////////////            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("73"));
            parametros.setNombre("Activar Segundo Coffe Break");
            parametros.setOrdenId(41);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("74"));
            parametros.setNombre("Activar checada salida y entrada para Segundo Coffe Break");
            parametros.setOrdenId(42);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("75"));
//            parametros.setNombre("Etiqueta Segundo Coffe Break");
//            parametros.setOrdenId(43);
//            parametros.setValor("Segundo Coffe Break");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("76"));
//            parametros.setNombre("Género para Segundo Coffe Break");
//            parametros.setOrdenId(44);
//            parametros.setValor("M");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("77"));
            parametros.setNombre("El empleado tiene tiempo extra cuando llega antes de su hora");
            parametros.setOrdenId(45);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("78"));
            parametros.setNombre("El empleado tiene tiempo extra si dispone de su hora de comida");
            parametros.setOrdenId(46);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("79"));
            parametros.setNombre("Tratamiento para tiempo extra cuando el empleado asiste en días festivos");
            parametros.setOrdenId(47);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("80"));
            parametros.setNombre("Indicar si se paga horas extras");
            parametros.setOrdenId(48);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("81"));
            parametros.setNombre("Tope");
            parametros.setOrdenId(49);
            parametros.setValor("2");
            parametros.setOpcionesParametros("Diario o Semanal");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Diario|Semanal");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("82"));
            parametros.setNombre("Indicar si se paga el dia festivo trabajado");
            parametros.setOrdenId(50);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("83"));
            parametros.setNombre("Tratamiento para tiempo extra cuando el empleado asiste en días de descanso");
            parametros.setOrdenId(51);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("84"));
            parametros.setNombre("Indicar si se paga horas extras");
            parametros.setOrdenId(52);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("85"));
            parametros.setNombre("Tope ");
            parametros.setOrdenId(53);
            parametros.setValor("2");
            parametros.setOpcionesParametros("Diario o Semanal");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Diario|Semanal");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("86"));
            parametros.setNombre("Indicar si se paga el descanso trabajado");
            parametros.setOrdenId(54);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("87"));
            parametros.setNombre("El empleado tiene tiempo extra cuando sale después de su hora de salida");
            parametros.setOrdenId(55);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("88"));
            parametros.setNombre("Usar tiempo extra para compensar tiempo ordinario no completado");
            parametros.setOrdenId(56);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("89"));
            parametros.setNombre("Tiempo mínimo para considerar tiempo extra");
            parametros.setOrdenId(57);
            parametros.setValor("0");
            parametros.setOpcionesParametros("El tiempo es en minutos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("90"));
            parametros.setNombre("Tipo de Redondeo para horas extras");
            parametros.setOrdenId(58);
            parametros.setValor("1");
            parametros.setOpcionesParametros("El personalizado se puede establecer el redondeo y el factor del mismo, con la opcion de tabla se selecciona una opcion guardado desde el catalago de tipo de redondeos");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Personalizado|Tabla");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("91"));
//            parametros.setNombre("Redondeo y Factor de Redondeo");
//            parametros.setOrdenId(59);
//            parametros.setValor("1");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
//
//            parametros = new Parametros();
//            parametros.setClave(Long.valueOf("92"));
//            parametros.setNombre("Tabla");
//            parametros.setOrdenId(60);
//            parametros.setValor("1");
//            parametros.setOpcionesParametros("");//JSA01
//            parametros.setTipoConfiguracion(1);
//            parametros.setPropiedadConfig("");
//            parametros.setElementosAplicacion(listRazonSocialElemento);
//            parametros.setModulo(listModulo.get(0));
//            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
//            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("93"));
            parametros.setNombre("Aplicar regla para Retardos ('x' retardos en 'y' días equivale a 'z' faltas)");
            parametros.setOrdenId(61);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("94"));
            parametros.setNombre("Número de Retardos");
            parametros.setOrdenId(62);
            parametros.setValor("0");
            parametros.setOpcionesParametros("Ingresar el número de retardos");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("95"));
            parametros.setNombre("Número de dias naturales en que ocurrieron retardos");
            parametros.setOrdenId(63);
            parametros.setValor("0");
            parametros.setOpcionesParametros("Ingresar los dias");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("96"));
            parametros.setNombre("Número de faltas a considerar");
            parametros.setOrdenId(64);
            parametros.setValor("0");
            parametros.setOpcionesParametros("Ingresar las faltas");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("97"));
            parametros.setNombre("Tratamiento de Séptimo Día");
            parametros.setOrdenId(65);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Si es pagar completo ingresar el mínimo a trabajar..");//JSA01
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Proporcional|Pagar completo");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);

////////////            parametros = new Parametros();
////////////            parametros.setClave(Long.valueOf("98"));
////////////            parametros.setNombre("Proporcional");
////////////            parametros.setOrdenId(66);
////////////            parametros.setValor("1");
////////////            parametros.setOpcionesParametros("");//JSA01
////////////            parametros.setTipoConfiguracion(1);
////////////            parametros.setPropiedadConfig("");
////////////            parametros.setElementosAplicacion(listRazonSocialElemento);
////////////            parametros.setModulo(listModulo.get(0));
////////////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
////////////            listParametros.add(parametros);
////////////            parametros = new Parametros();
////////////            parametros.setClave(Long.valueOf("99"));
////////////            parametros.setNombre("Pagar completo");
////////////            parametros.setOrdenId(67);
////////////            parametros.setValor("1");
////////////            parametros.setOpcionesParametros("");//JSA01
////////////            parametros.setTipoConfiguracion(1);
////////////            parametros.setPropiedadConfig("");
////////////            parametros.setElementosAplicacion(listRazonSocialElemento);
////////////            parametros.setModulo(listModulo.get(0));
////////////            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
////////////            listParametros.add(parametros);
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("100"));
            parametros.setNombre("Mínimo a trabajar para pago de séptimo día");
            parametros.setOrdenId(68);
            parametros.setValor("8");
            parametros.setOpcionesParametros("Ingresar las horas");//JSA01
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("2|@");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.HORARIOSTURNO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("101"));
            parametros.setNombre("¿Tipo de accion a aplicar sobre el resultado del concepto en los movimientos?");
            parametros.setOrdenId(31);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Truncado|Redondeo");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.CONCEPTOS);
            listParametros.add(parametros);
            parametros = new Parametros();
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("102"));
            parametros.setNombre("Mascara para el resultado del concepto");
            parametros.setOrdenId(32);
            parametros.setValor("############.##");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(5);
            parametros.setPropiedadConfig("12|2");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.CONCEPTOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("103"));
            parametros.setNombre("Mascara para sueldos");
            parametros.setOrdenId(30);
            parametros.setValor("############.##");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(5);
            parametros.setPropiedadConfig("12|2");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("104"));
            parametros.setNombre("Mascara para sueldo diario");
            parametros.setOrdenId(31);
            parametros.setValor("############.##");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(5);
            parametros.setPropiedadConfig("12|2");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("105"));
            parametros.setNombre("Mascara para salario diario integrado");
            parametros.setOrdenId(32);
            parametros.setValor("############.##");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(5);
            parametros.setPropiedadConfig("12|2");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("106"));
            parametros.setNombre("Mascara para horas");
            parametros.setOrdenId(33);
            parametros.setValor("##.##");
            parametros.setOpcionesParametros("horas:minutos (hh:mm)o en numeros (##.##)");//JSA01
            parametros.setTipoConfiguracion(5);
            parametros.setPropiedadConfig("2|2");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.SUELDOSYSALARIOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("107"));
            parametros.setNombre("¿Permitir manejar categoria de puesto?");
            parametros.setOrdenId(36);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("108"));
            parametros.setNombre("¿Permitir manejar subcuenta en los departamentos?");
            parametros.setOrdenId(36);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");//JSA01
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            String ruta = buscaRutaArchivos(System.getProperty("user.dir"), archivosReportes);
            if (ruta.isEmpty()) {
                ruta = "C:\\REPORTESSISTEMAS";
            }
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("109"));
            parametros.setNombre("Ubicación de los reportes del sistema");
            parametros.setOrdenId(37);
            parametros.setValor(ruta);
            parametros.setOpcionesParametros("Esta es la ubicación de los reportes fijos en el servidor");//JSA01
            parametros.setTipoConfiguracion(6);
            parametros.setPropiedadConfig("255");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("110"));
            parametros.setNombre("Carpeta del reporte de nomina");
            parametros.setOrdenId(38);
            parametros.setValor("REPORTENOMINA");
            parametros.setOpcionesParametros("Esta es la carpeta del reporte de nómina que se encuentra dentro de los reportes fijos del sistema.");//JSA01
            parametros.setTipoConfiguracion(8);
            parametros.setPropiedadConfig("255");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("111"));
            parametros.setNombre("Logo para los reportes del sistema");
            parametros.setOrdenId(38);
            parametros.setValor("C:\\REPORTESSISTEMAS\\LOGOS\\nrh logo-03.png");
            parametros.setOpcionesParametros("Este es el logo que tendran los reportes del sistema");//JSA01
            parametros.setTipoConfiguracion(7);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            String c = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D2249535222203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22323722203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224C696D697465496E666572696F722220746974756C6F3D224C696D69746520496E666572696F7222207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E4C696D69746520496E666572696F723C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4C6F776572204C696D69743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22323722203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2243756F746146696A612220746974756C6F3D2243756F74612046696A6122207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E43756F74612046696A613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E466C6174204665653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223022203E3C636F6C756D6E6120706F733D223322206E6F6D627265436F6C3D22506F7263656E74616A652220746974756C6F3D22506F7263656E74616A6522207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223522202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E506F7263656E74616A653C2F65733E3C656E206E6F6D6272653D22496E676C6573223E506F7263656E74616A653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22566967656E7465206465736465222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A58446174655069636B657222206964656E746966696361646F723D22436F6E74726F6C506F724665636861222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            byte[] imagen = new byte[c.length() / 2];
            for (i = 0; i < c.length(); i += 2) {
                imagen[i / 2] = (byte) ((Character.digit(c.charAt(i), 16) << 4)
                        + Character.digit(c.charAt(i + 1), 16));
            }
            parametros.setImagen(imagen);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("112"));
            parametros.setNombre("Tasa de impuesto estatal");
            parametros.setOrdenId(112);
            parametros.setValor("2.00");
            parametros.setOpcionesParametros("El valor de la tasa impuesto global");
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("3|2");
            List<ElementosAplicacion> listElemEstado = new ArrayList<ElementosAplicacion>();
            listElemEstado.add(listElementosAplicacion.get(14)); //Estados
            parametros.setElementosAplicacion(listElemEstado);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("113"));
            parametros.setNombre("¿Abrir al exportar los reportes?");
            parametros.setOrdenId(113);
            parametros.setValor("1");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("114"));
            parametros.setNombre("Incluir Concepto: Dato,Calculo ó Informativo en reporte de nómina");
            parametros.setOrdenId(114);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Dato. :: Cálculo. :: Informativo.");
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Dato|Cálculo|Informativo");
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.REPORTES);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("115"));
            parametros.setNombre("¿Seleccionar texto al recibir foco?");
            parametros.setOrdenId(115);
            parametros.setValor("1");
            parametros.setOpcionesParametros("1=Si :: 2=No");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("116"));
            parametros.setNombre("¿Mantenerse el último filtro utilizado en los movimientos de nómina?");
            parametros.setOrdenId(116);
            parametros.setValor("1");
            parametros.setOpcionesParametros("1=Si :: 2=No");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            //Elemento por  razon social y usuario
            parametros.setElementosAplicacion(new ArrayList<ElementosAplicacion>(Arrays.asList(listElementosAplicacion.get(0), listElementosAplicacion.get(12))));
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("117"));
            parametros.setNombre("¿Versión del cálculo para crédito y ahorro?");
            parametros.setOrdenId(117);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("1|0");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.CALCULO);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("118"));
            parametros.setNombre("¿Pagar IMSS sobre días naturales?");
            parametros.setOrdenId(118);
            parametros.setValor("1");
            parametros.setOpcionesParametros("");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.IMSS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("119"));
            parametros.setNombre("Carpeta del recibo CFDI");
            parametros.setOrdenId(119);
            parametros.setValor("ReporteReciboNominaCFDI");
            parametros.setOpcionesParametros("Esta es la carpeta del recibo CFDI");
            parametros.setTipoConfiguracion(8);
            parametros.setPropiedadConfig("255");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("120"));
            parametros.setNombre("Carpeta del recibo de CDFI asimilado");
            parametros.setOrdenId(120);
            parametros.setValor("ReporteAsimilados");
            parametros.setOpcionesParametros("Esta es la carpeta del recibo CFDI asimilado");
            parametros.setTipoConfiguracion(8);
            parametros.setPropiedadConfig("255");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("121"));
            parametros.setNombre("Asignacion concepto despensa");
            parametros.setOrdenId(121);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Concepto despensas para cuentas contables");
            parametros.setTipoConfiguracion(3);
            parametros.setPropiedadConfig("ConcepNomDefi|Clave|Descripcion");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.CONCEPTOS);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("122"));
            parametros.setNombre("Control de Registros Patronales");
            parametros.setOrdenId(122);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Definir el Nivel de Control de Registros Patronales");
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Registro Patronal único|Registro Patronal por Tipo de Nómina|Registro Patronal por Plaza");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("123"));
            parametros.setNombre("Activar Credito infonavit");
            parametros.setOrdenId(123);
            parametros.setValor("1");
            parametros.setOpcionesParametros("Activa captura de Infonavit en catalogo de empleados");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("124"));
            parametros.setNombre("Permitir pagar prima vacacional aparte");
            parametros.setOrdenId(124);
            parametros.setValor("1");
            parametros.setOpcionesParametros("1=Si :: 2=No");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            //Elemento por  razon social y usuario
            parametros.setElementosAplicacion(new ArrayList<ElementosAplicacion>(Arrays.asList(listElementosAplicacion.get(0), listElementosAplicacion.get(12))));
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("125"));
            parametros.setNombre("Definir días para derecho a PTU");
            parametros.setOrdenId(125);
            parametros.setValor("60");
            parametros.setOpcionesParametros("Configuración para definir dias para derecho a PTU");
            parametros.setTipoConfiguracion(4);
            parametros.setPropiedadConfig("60|@");
            //Elemento por  razon social y usuario
            parametros.setElementosAplicacion(new ArrayList<ElementosAplicacion>(Arrays.asList(listElementosAplicacion.get(0), listElementosAplicacion.get(12))));
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("126"));
            parametros.setNombre("Opción de cálculo ISR por PTU");
            parametros.setOrdenId(126);
            parametros.setValor("2");
            parametros.setOpcionesParametros("Art. 113 LISR ó Art. 142 RLISR");
            parametros.setTipoConfiguracion(2);
            parametros.setPropiedadConfig("Art. 113 LISR |Art. 142 RLISR");
            //Elemento por  razon social y usuario
            parametros.setElementosAplicacion(new ArrayList<ElementosAplicacion>(Arrays.asList(listElementosAplicacion.get(0), listElementosAplicacion.get(12))));
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("127"));
            parametros.setNombre("Pagar PTU en el primer periodo activo después del cálculo del PTU");
            parametros.setOrdenId(126);
            parametros.setValor("1");
            parametros.setOpcionesParametros("1=Si :: 2=No");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            //Elemento por  razon social y usuario
            parametros.setElementosAplicacion(new ArrayList<ElementosAplicacion>(Arrays.asList(listElementosAplicacion.get(0), listElementosAplicacion.get(12))));
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("128"));
            parametros.setNombre("Permitir modificar variables en calculo de PTU");
            parametros.setOrdenId(127);
            parametros.setValor("2");
            parametros.setOpcionesParametros("1=Si :: 2=No");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            //Elemento por  razon social y usuario
            parametros.setElementosAplicacion(new ArrayList<ElementosAplicacion>(Arrays.asList(listElementosAplicacion.get(0), listElementosAplicacion.get(12))));
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.GLOBAL);
            listParametros.add(parametros);
            
            parametros = new Parametros();
            parametros.setClave(Long.valueOf("129"));
            parametros.setNombre("¿Descontar faltas al modo ajustar al mes?");
            parametros.setOrdenId(129);
            parametros.setValor("2");
            parametros.setOpcionesParametros("");
            parametros.setTipoConfiguracion(1);
            parametros.setPropiedadConfig("");
            parametros.setElementosAplicacion(listRazonSocialElemento);
            parametros.setModulo(listModulo.get(0));
            parametros.setClasificacion(Clasificacion.ISR);
            listParametros.add(parametros);
            
            for (i = 0; i < listParametros.size(); i++) {
                session.save(listParametros.get(i));
            }
            //</editor-fold>
            contador = contador + listParametros.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="TABLA BASE">
            /**
             * ******************
             * TABLA BASE ******************
             */
            tablaBase = new TablaBase();
            tablaBase.setClave("01");
            tablaBase.setControladores("ControlPorFecha");
            tablaBase.setDescripcion("ISR");
            tablaBase.setDescripcionAbreviada("ISR");
            String s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D2249535222203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22323722203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224C696D697465496E666572696F722220746974756C6F3D224C696D69746520696E666572696F7222207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313522202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E4C696D69746520696E666572696F723C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4C6F776572206C696D69743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22323722203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2243756F746146696A612220746974756C6F3D2243756F74612066696A6122207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E43756F74612066696A613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E466C6174206665653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223022203E3C636F6C756D6E6120706F733D223322206E6F6D627265436F6C3D22506F7263656E74616A652220746974756C6F3D22506F7263656E74616A6522207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E506F7263656E74616A653C2F65733E3C656E206E6F6D6272653D22496E676C6573223E506F7263656E74616A653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22566967656E7465206465736465222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A58446174655069636B657222206964656E746966696361646F723D22436F6E74726F6C506F724665636861222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            byte[] data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(0));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();
            tablaBase.setClave("02");
            tablaBase.setControladores("ControlPorFecha");
            tablaBase.setDescripcion("SUBSIDIO EMPLEO");
            tablaBase.setDescripcionAbreviada("SUBSIDIO EMPLEO");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D22535542534944494F22203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223222203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224C696D697465496E666572696F722220746974756C6F3D224C696D69746520696E666572696F7222207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E4C696D69746520696E666572696F723C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4C6F776572206C696D69743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223222203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2243756F74612220746974756C6F3D2243756F746122207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E43756F74613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E53686172653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22566967656E7465206465736465222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A58446174655069636B657222206964656E746966696361646F723D22436F6E74726F6C506F724665636861222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(1));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();
            tablaBase.setClave("03");
            tablaBase.setControladores("ControlPorFecha");
            tablaBase.setDescripcion("DIAS FESTIVOS");
            tablaBase.setDescripcionAbreviada("DIAS FESTIVOS");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D22444941534645535449564F5322203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223522203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D2266656368612220746974756C6F3D22466368612064C3AD61206665737469766F22207469706F3D22446174652220666F726D61746F3D2264642F4D4D2F79797979222074616D436F6C3D22313522202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E466368612064C3AD61206665737469766F3C2F65733E3C656E206E6F6D6272653D22496E676C6573223E5075626C696320686F6C69646179733C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223522203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D226465736372697063696F6E2220746974756C6F3D224465736372697063696F6E22207469706F3D22537472696E672220666F726D61746F3D223130222074616D436F6C3D22333522202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E4465736372697063696F6E3C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4465736372697074696F6E3C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22566967656E7465206465736465222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A58446174655069636B657222206964656E746966696361646F723D22436F6E74726F6C506F724665636861222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(4));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();
            tablaBase.setControladores("ControlPorFecha");
            tablaBase.setClave("04");//JSA05
            tablaBase.setDescripcion("SALARIOS MÍNIMOS");
            tablaBase.setDescripcionAbreviada("SALARIOS MÍNIMOS");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D2253414C4152494F534DC38D4E494D4F5322203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223122203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224465736372697063696F6E2220746974756C6F3D225A6F6E6122207469706F3D22537472696E672220666F726D61746F3D223130222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E5A6F6E613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E5A6F6E653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223122203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2253616C6172696F2220746974756C6F3D2253616C6172696F22207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E53616C6172696F3C2F65733E3C656E206E6F6D6272653D22496E676C6573223E576167653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22566967656E7465206465736465222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A58446174655069636B657222206964656E746966696361646F723D22436F6E74726F6C506F724665636861222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(2));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();
            tablaBase.setClave("05");//JSA05
            tablaBase.setControladores("ControlPorEntidad");
            tablaBase.setDescripcion("FACTORES DE INTEGRACIÓN");
            tablaBase.setDescripcionAbreviada("FACT. DE INTEG.");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D22464143544F52494E544547524143494F4E22203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22313122203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D22616E74696775656461642220746974756C6F3D22416E746967756564616422207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E416E74696775656461643C2F65733E3C656E206E6F6D6272653D22496E676C6573223E45786167653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22313122203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D22666163746F722220746974756C6F3D22466163746F7220696E74656772616369C3B36E22207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E303030307C222074616D436F6C3D22313522202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E466163746F7220696E74656772616369C3B36E3C2F65733E3C656E206E6F6D6272653D22496E676C6573223E466163746F7220696E746567726174696F6E3C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22313122203E3C636F6C756D6E6120706F733D223322206E6F6D627265436F6C3D2264696173416775696E616C646F2220746974756C6F3D224469617320616775696E616C646F22207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E4469617320616775696E616C646F3C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4461797320626F6E75733C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22313122203E3C636F6C756D6E6120706F733D223422206E6F6D627265436F6C3D22646961735661636163696F6E65732220746974756C6F3D2244696173207661636163696F6E657322207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E44696173207661636163696F6E65733C2F65733E3C656E206E6F6D6272653D22496E676C6573223E44617973207661636174696F6E733C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D22313022203E3C636F6C756D6E6120706F733D223522206E6F6D627265436F6C3D227072696D615661636163696F6E616C2220746974756C6F3D225072696D61207661636163696F6E616C22207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E5072696D61207661636163696F6E616C3C2F65733E3C656E206E6F6D6272653D22496E676C6573223E5661636174696F6E207072656D69756D3C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22456D707265736173222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E52617A6F6E6573536F6369616C657322206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164222F3E3C436F6E74726F6C61646F72207469706F3D225265672E506174726F222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E526567697374726F506174726F6E616C22206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164222F3E3C436F6E74726F6C61646F72207469706F3D225469706F206465206E6F6D696E61222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E5469706F4E6F6D696E6122206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164222F3E3C436F6E74726F6C61646F72207469706F3D2243617465676F7269612064652070756573746F222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E43617465676F7269617350756573746F7322206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(3));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();//JSA05
            tablaBase.setClave("06");
            tablaBase.setControladores("ControlPorAño");
            tablaBase.setDescripcion("ISR ANUAL");
            tablaBase.setDescripcionAbreviada("ISR ANUAL 2016");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D2249535222203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223722203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224C696D697465496E666572696F722220746974756C6F3D224C696D69746520696E666572696F7222207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313522202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C383C692C382C2B16F6C223E4C696D69746520696E666572696F723C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4C6F776572206C696D69743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223722203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2243756F746146696A612220746974756C6F3D2243756F74612066696A6122207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C383C692C382C2B16F6C223E43756F74612066696A613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E466C6174206665653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223722203E3C636F6C756D6E6120706F733D223322206E6F6D627265436F6C3D22506F7263656E74616A652220746974756C6F3D22506F7263656E74616A6522207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C383C692C382C2B16F6C223E506F7263656E74616A653C2F65733E3C656E206E6F6D6272653D22496E676C6573223E50657263656E743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D224361707475726120706F722061C3B16F222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A5370696E6E657222206964656E746966696361646F723D22436F6E74726F6C506F72416E696F222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(0));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();
            tablaBase.setClave("07");
            tablaBase.setControladores("ControlPorAño");
            tablaBase.setDescripcion("SUBSIDIO AL EMPLEADO ANUAL 2016");
            tablaBase.setDescripcionAbreviada("SUBSIDIO EMP. ANUAL 2016");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D22535542534944494F22203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223422203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224C696D697465496E666572696F722220746974756C6F3D224C696D69746520696E666572696F7222207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C383C692C382C2B16F6C223E4C696D69746520696E666572696F723C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4C6F776572206C696D69743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223422203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2243756F74612220746974756C6F3D2243756F746122207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D22313022202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C383C692C382C2B16F6C223E43756F74613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E53686172653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D224361707475726120706F722061C3B16F222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A5370696E6E657222206964656E746966696361646F723D22436F6E74726F6C506F72416E696F222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(1));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();//JSA05
            tablaBase.setClave("08");
            tablaBase.setControladores("ControlPorFecha,ControlPorEntidad");
            tablaBase.setDescripcion("ISR POR PERIODICIDAD");
            tablaBase.setDescripcionAbreviada("ISR POR PERIODICIDAD");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D2249535222203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223022203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224C696D697465496E666572696F722220746974756C6F3D224C696D69746520496E666572696F7222207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E4C696D69746520496E666572696F723C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4C6F776572204C696D69743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223022203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2243756F746146696A612220746974756C6F3D2243756F74612046696A6122207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E43756F74612046696A613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E466C6174204665653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223022203E3C636F6C756D6E6120706F733D223322206E6F6D627265436F6C3D22506F7263656E74616A652220746974756C6F3D22506F7263656E74616A6522207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E506F7263656E74616A653C2F65733E3C656E206E6F6D6272653D22496E676C6573223E50657263656E743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22566967656E7465206465736465222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A58446174655069636B657222206964656E746966696361646F723D22436F6E74726F6C506F724665636861222F3E3C436F6E74726F6C61646F72207469706F3D22506572696F64696369646164222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E506572696F6469636964616422206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(0));
            listTablaBase.add(tablaBase);
            
            tablaBase = new TablaBase();//JSA05
            tablaBase.setClave("09");
            tablaBase.setControladores("ControlPorFecha,ControlPorEntidad");
            tablaBase.setDescripcion("SUBSIDIO POR PERIODICIDAD");
            tablaBase.setDescripcionAbreviada("SUBSIDIO POR PERIODICIDAD");
            s = "3C3F786D6C2076657273696F6E3D27312E302720656E636F64696E673D275554462D38273F3E3C7461626C65206E616D653D22535542534944494F22203E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223022203E3C636F6C756D6E6120706F733D223122206E6F6D627265436F6C3D224C696D697465496E666572696F722220746974756C6F3D224C696D69746520496E666572696F7222207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E4C696D69746520496E666572696F723C2F65733E3C656E206E6F6D6272653D22496E676C6573223E4C6F7765204C696D69743C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C70726F70657274792073697374656D613D223122207374617475733D2230222063616E743D223022203E3C636F6C756D6E6120706F733D223222206E6F6D627265436F6C3D2243756F74612220746974756C6F3D2243756F746122207469706F3D22466C6F61742220666F726D61746F3D227C3126616D703B3233347C2E30307C222074616D436F6C3D223822202F3E3C4964696F6D61733E3C6573206E6F6D6272653D2245737061C3B16F6C223E43756F74613C2F65733E3C656E206E6F6D6272653D22496E676C6573223E53686172653C2F656E3E3C2F4964696F6D61733E3C2F70726F70657274793E3C436F6E74726F6C61646F7265733E3C436F6E74726F6C61646F72207469706F3D22566967656E7465206465736465222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6775692E706572736F6E616C697A61646F732E504A58446174655069636B657222206964656E746966696361646F723D22436F6E74726F6C506F724665636861222F3E3C436F6E74726F6C61646F72207469706F3D22506572696F64696369646164222073697374656D613D22312220656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E506572696F6469636964616422206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164222F3E3C2F436F6E74726F6C61646F7265733E3C2F7461626C653E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaBase.setFileXml(data);
            tablaBase.setRenglonSeleccionado(true);
            tablaBase.setTipoTabla(listTipoTabla.get(1));
            listTablaBase.add(tablaBase);
            
            for (i = 0; i < listTablaBase.size(); i++) {
                session.save(listTablaBase.get(i));
            }
            //</editor-fold>
            contador = contador + listTablaBase.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="TABLA DATOS">
            /**
             * ******************
             * TABLA DATOS ******************
             */
            Calendar fecha = Calendar.getInstance();
            fecha.set(2016, Calendar.JANUARY, 1);
            tablaDatos = new TablaDatos();
            tablaDatos.setControlPorFecha(fecha.getTime());
            tablaDatos.setControladores("");
            tablaDatos.setDescripcion("ISR 2016");
            //ISR
            s = "3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C7461626C613E3C6461746F2069643D2231223E3C4C696D697465496E666572696F723E302E30313C2F4C696D697465496E666572696F723E3C43756F746146696A613E302E303C2F43756F746146696A613E3C506F7263656E74616A653E312E39323C2F506F7263656E74616A653E3C2F6461746F3E3C6461746F2069643D2232223E3C4C696D697465496E666572696F723E3439362E30383C2F4C696D697465496E666572696F723E3C43756F746146696A613E392E35323C2F43756F746146696A613E3C506F7263656E74616A653E362E343C2F506F7263656E74616A653E3C2F6461746F3E3C6461746F2069643D2233223E3C4C696D697465496E666572696F723E343231302E34323C2F4C696D697465496E666572696F723E3C43756F746146696A613E3234372E32333C2F43756F746146696A613E3C506F7263656E74616A653E31302E38383C2F506F7263656E74616A653E3C2F6461746F3E3C6461746F2069643D2234223E3C4C696D697465496E666572696F723E373339392E34333C2F4C696D697465496E666572696F723E3C43756F746146696A613E3539342E32343C2F43756F746146696A613E3C506F7263656E74616A653E31362E303C2F506F7263656E74616A653E3C2F6461746F3E3C6461746F2069643D2235223E3C4C696D697465496E666572696F723E383630312E35313C2F4C696D697465496E666572696F723E3C43756F746146696A613E3738362E35353C2F43756F746146696A613E3C506F7263656E74616A653E31372E39323C2F506F7263656E74616A653E3C2F6461746F3E3C6461746F2069643D2236223E3C4C696D697465496E666572696F723E31303239382E33363C2F4C696D697465496E666572696F723E3C43756F746146696A613E313039302E36323C2F43756F746146696A613E3C506F7263656E74616A653E32312E33363C2F506F7263656E74616A653E3C2F6461746F3E3C6461746F2069643D2237223E3C4C696D697465496E666572696F723E32303737302E333C2F4C696D697465496E666572696F723E3C43756F746146696A613E333332372E34323C2F43756F746146696A613E3C506F7263656E74616A653E32332E35323C2F506F7263656E74616A653E3C2F6461746F3E3C6461746F2069643D2238223E3C4C696D697465496E666572696F723E33323733362E38343C2F4C696D697465496E666572696F723E3C43756F746146696A613E363134312E39353C2F43756F746146696A613E3C506F7263656E74616A653E33302E303C2F506F7263656E74616A653E3C2F6461746F3E3C2F7461626C613E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaDatos.setFileXml(data);
            tablaDatos.setRenglonSeleccionado(true);
            tablaDatos.setTablaBase(listTablaBase.get(0));
            listTablaDatos.add(tablaDatos);
            
            tablaDatos = new TablaDatos();
            tablaDatos.setControladores("");
            tablaDatos.setControlPorFecha(fecha.getTime());
            tablaDatos.setDescripcion("SUBSIDIO 2016");
            //SUBSIDIO
            s = "3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C7461626C613E3C6461746F2069643D2231223E3C4C696D697465496E666572696F723E302E30313C2F4C696D697465496E666572696F723E3C43756F74613E3430372E30323C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2232223E3C4C696D697465496E666572696F723E313736382E39373C2F4C696D697465496E666572696F723E3C43756F74613E3430362E38333C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2233223E3C4C696D697465496E666572696F723E323635332E33393C2F4C696D697465496E666572696F723E3C43756F74613E3430362E36323C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2234223E3C4C696D697465496E666572696F723E333437322E38353C2F4C696D697465496E666572696F723E3C43756F74613E3339322E37373C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2235223E3C4C696D697465496E666572696F723E333533372E38383C2F4C696D697465496E666572696F723E3C43756F74613E3338322E34363C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2236223E3C4C696D697465496E666572696F723E343434362E31363C2F4C696D697465496E666572696F723E3C43756F74613E3335342E32333C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2237223E3C4C696D697465496E666572696F723E343731372E31393C2F4C696D697465496E666572696F723E3C43756F74613E3332342E38373C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2238223E3C4C696D697465496E666572696F723E353333352E34333C2F4C696D697465496E666572696F723E3C43756F74613E3239342E36333C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2239223E3C4C696D697465496E666572696F723E363232342E36383C2F4C696D697465496E666572696F723E3C43756F74613E3235332E35343C2F43756F74613E3C2F6461746F3E3C6461746F2069643D223130223E3C4C696D697465496E666572696F723E373131332E39313C2F4C696D697465496E666572696F723E3C43756F74613E3231372E36313C2F43756F74613E3C2F6461746F3E3C6461746F2069643D223131223E3C4C696D697465496E666572696F723E373338322E33343C2F4C696D697465496E666572696F723E3C43756F74613E302E303C2F43756F74613E3C2F6461746F3E3C2F7461626C613E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaDatos.setFileXml(data);
            tablaDatos.setRenglonSeleccionado(true);
            tablaDatos.setTablaBase(listTablaBase.get(1));
            listTablaDatos.add(tablaDatos);
            
            tablaDatos = new TablaDatos();
            tablaDatos.setControladores("");
            tablaDatos.setControlPorFecha(fecha.getTime());
            tablaDatos.setDescripcion("DIAS FESTIVOS 2016");
            //DIAS FESTIVOS
            s = "3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C7461626C613E3C6461746F2069643D2231223E3C66656368613E30312F30312F323031333C2F66656368613E3C6465736372697063696F6E3E41C3B16F206E7565766F3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2232223E3C66656368613E30362F30312F323031333C2F66656368613E3C6465736372697063696F6E3E44696173206465206C6F732073616E746F73202052657965733C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2233223E3C66656368613E30352F30322F323031333C2F66656368613E3C6465736372697063696F6E3E446961206465206C6120436F6E737469747563696F6E3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2234223E3C66656368613E32342F30322F323031333C2F66656368613E3C6465736372697063696F6E3E446961206465206C612062616E64657261204E6163696F6E616C3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2235223E3C66656368613E32312F30332F323031333C2F66656368613E3C6465736372697063696F6E3E4E6174616C6963696F2062656E69746F204A756172657A3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2236223E3C66656368613E32382F30332F323031333C2F66656368613E3C6465736372697063696F6E3E4A75657665732053616E746F3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2237223E3C66656368613E32392F30332F323031333C2F66656368613E3C6465736372697063696F6E3E566965726E65732053616E746F3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2238223E3C66656368613E33302F30342F323031333C2F66656368613E3C6465736372697063696F6E3E4469612064656C204E69C3B16F3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D2239223E3C66656368613E30312F30352F323031333C2F66656368613E3C6465736372697063696F6E3E4469612064656C2074726162616A6F3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223130223E3C66656368613E30352F30352F323031333C2F66656368613E3C6465736372697063696F6E3E426174616C6C6120646520707565626C613C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223131223E3C66656368613E31302F30352F323031333C2F66656368613E3C6465736372697063696F6E3E446961206465206C6173206D61647265733C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223132223E3C66656368613E30312F30392F323031333C2F66656368613E3C6465736372697063696F6E3E496E666F726D6520507265736964656E6369616C3C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223133223E3C66656368613E31362F30392F323031333C2F66656368613E3C6465736372697063696F6E3E446961206465206C6120696E646570656E64656E6369613C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223134223E3C66656368613E30312F31312F323031333C2F66656368613E3C6465736372697063696F6E3E44696120646520746F646F73206C6F732073616E746F733C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223135223E3C66656368613E30322F31312F323031333C2F66656368613E3C6465736372697063696F6E3E446961206465206C6F73206D756572746F733C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223136223E3C66656368613E32302F31312F323031333C2F66656368613E3C6465736372697063696F6E3E5265766F6C7563696F6E206D65786963616E613C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223137223E3C66656368613E31322F31322F323031333C2F66656368613E3C6465736372697063696F6E3E4469612064652076697267656E2064652067756164616C7570653C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223138223E3C66656368613E32342F31322F323031333C2F66656368613E3C6465736372697063696F6E3E4E6F636865206275656E613C2F6465736372697063696F6E3E3C2F6461746F3E3C6461746F2069643D223139223E3C66656368613E32352F31322F323031333C2F66656368613E3C6465736372697063696F6E3E4E6176696461643C2F6465736372697063696F6E3E3C2F6461746F3E3C2F7461626C613E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaDatos.setFileXml(data);
            tablaDatos.setRenglonSeleccionado(true);
            tablaDatos.setTablaBase(listTablaBase.get(2));
            listTablaDatos.add(tablaDatos);
            
            tablaDatos = new TablaDatos();
            tablaDatos.setControladores("");
            tablaDatos.setControlPorFecha(fecha.getTime());
            tablaDatos.setDescripcion("SALARIOS MÍNIMOS 2016");
            //SALARIOS MÍNIMOS
            s = "3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C7461626C613E0A2020203C6461746F2069643D2231223E0A2020202020203C4465736372697063696F6E3E413C2F4465736372697063696F6E3E0A2020202020203C53616C6172696F3E37302E31303C2F53616C6172696F3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2232223E0A2020202020203C4465736372697063696F6E3E423C2F4465736372697063696F6E3E0A2020202020203C53616C6172696F3E36362E34353C2F53616C6172696F3E0A2020203C2F6461746F3E0A3C2F7461626C613E0A";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaDatos.setFileXml(data);
            tablaDatos.setRenglonSeleccionado(true);
            tablaDatos.setTablaBase(listTablaBase.get(3));
            listTablaDatos.add(tablaDatos);
            
            tablaDatos = new TablaDatos();
            tablaDatos.setControladores("RazonesSociales0001");
            tablaDatos.setDescripcion("FACTOR DE INTEGRACION 2016");
            //FACTOR DE INTEGRACION
            s = "3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C7461626C613E0A2020203C436F6E74726F6C61646F722063616D706F42757371756564613D22636C617665220A20202020202020202020202020202020656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E52617A6F6E6573536F6369616C6573220A202020202020202020202020202020206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164220A202020202020202020202020202020206E6F6D6272653D22456D707265736173220A2020202020202020202020202020202076616C6F7242757371756564613D2230303031223E456D7072657361205072756562613C2F436F6E74726F6C61646F723E0A2020203C436F6E74726F6C61646F722063616D706F42757371756564613D22636C617665220A20202020202020202020202020202020656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E526567697374726F506174726F6E616C220A202020202020202020202020202020206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164220A202020202020202020202020202020206E6F6D6272653D225265672E506174726F220A2020202020202020202020202020202076616C6F7242757371756564613D22222F3E0A2020203C436F6E74726F6C61646F722063616D706F42757371756564613D22636C617665220A20202020202020202020202020202020656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E5469706F4E6F6D696E61220A202020202020202020202020202020206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164220A202020202020202020202020202020206E6F6D6272653D225469706F206465206E6F6D696E61220A2020202020202020202020202020202076616C6F7242757371756564613D22222F3E0A2020203C436F6E74726F6C61646F722063616D706F42757371756564613D22636C617665220A20202020202020202020202020202020656E74696461643D22636F6D2E6D65662E6572702E6D6F64656C6F2E656E74696461642E43617465676F7269617350756573746F73220A202020202020202020202020202020206964656E746966696361646F723D22436F6E74726F6C506F72456E7469646164220A202020202020202020202020202020206E6F6D6272653D2243617465676F7269612064652070756573746F220A2020202020202020202020202020202076616C6F7242757371756564613D22222F3E0A2020203C6461746F2069643D2231223E0A2020202020203C616E74696775656461643E313C2F616E74696775656461643E0A2020202020203C666163746F723E312E303435323C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E363C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2232223E0A2020202020203C616E74696775656461643E323C2F616E74696775656461643E0A2020202020203C666163746F723E312E303436363C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E383C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2233223E0A2020202020203C616E74696775656461643E333C2F616E74696775656461643E0A2020202020203C666163746F723E312E303437393C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E31303C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2234223E0A2020202020203C616E74696775656461643E343C2F616E74696775656461643E0A2020202020203C666163746F723E312E303439333C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E31323C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2235223E0A2020202020203C616E74696775656461643E393C2F616E74696775656461643E0A2020202020203C666163746F723E312E303530373C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E31343C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2236223E0A2020202020203C616E74696775656461643E31343C2F616E74696775656461643E0A2020202020203C666163746F723E312E303532313C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E31363C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2237223E0A2020202020203C616E74696775656461642F3E0A2020202020203C666163746F723E312E303533343C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E31383C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2238223E0A2020202020203C616E74696775656461643E32343C2F616E74696775656461643E0A2020202020203C666163746F723E312E303534383C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E32303C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2239223E0A2020202020203C616E74696775656461643E32393C2F616E74696775656461643E0A2020202020203C666163746F723E312E303536323C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E32323C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D223130223E0A2020202020203C616E74696775656461643E33343C2F616E74696775656461643E0A2020202020203C666163746F723E312E303537353C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E32343C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A2020203C6461746F2069643D223131223E0A2020202020203C616E74696775656461643E33393C2F616E74696775656461643E0A2020202020203C666163746F723E312E303538393C2F666163746F723E0A2020202020203C64696173416775696E616C646F3E31353C2F64696173416775696E616C646F3E0A2020202020203C646961735661636163696F6E65733E32363C2F646961735661636163696F6E65733E0A2020202020203C7072696D615661636163696F6E616C3E32353C2F7072696D615661636163696F6E616C3E0A2020203C2F6461746F3E0A3C2F7461626C613E0A";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaDatos.setFileXml(data);
            tablaDatos.setRenglonSeleccionado(true);
            tablaDatos.setTablaBase(listTablaBase.get(4));
            listTablaDatos.add(tablaDatos);
            
            tablaDatos = new TablaDatos();//JSA05
            tablaDatos.setControladores("");
            tablaDatos.setControlPorAnio(2016);
            tablaDatos.setDescripcion("ISR ANUAL 2016");
            //ISR ANUAL
            s = "3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C7461626C613E0A2020203C6461746F2069643D2231223E0A2020202020203C4C696D697465496E666572696F723E302E30313C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E302E303C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E312E39323C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2232223E0A2020202020203C4C696D697465496E666572696F723E353935322E38353C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E3131342E32393C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E362E343C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2233223E0A2020202020203C4C696D697465496E666572696F723E35303532342E39333C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E323936362E39313C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E31302E38383C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2234223E0A2020202020203C4C696D697465496E666572696F723E38383739332E30353C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E373133302E34383C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E31362E303C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2235223E0A2020202020203C4C696D697465496E666572696F723E3130333231382E30313C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E393433382E34373C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E31372E39323C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2236223E0A2020202020203C4C696D697465496E666572696F723E3132333538302E32313C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E31333038372E33373C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E32312E33363C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2237223E0A2020202020203C4C696D697465496E666572696F723E3234393234332E34393C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E33393932392E30353C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E32332E35323C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2238223E0A2020202020203C4C696D697465496E666572696F723E3339323834312E39373C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E37333730332E34313C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E33302E303C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D2239223E0A2020202020203C4C696D697465496E666572696F723E3735303030302E30313C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E3138303835302E38323C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E33323C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D223130223E0A2020202020203C4C696D697465496E666572696F723E313030303030302E30313C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E3236303835302E38313C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E33343C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A2020203C6461746F2069643D223131223E0A2020202020203C4C696D697465496E666572696F723E333030303030302E30313C2F4C696D697465496E666572696F723E0A2020202020203C43756F746146696A613E3934303835302E38313C2F43756F746146696A613E0A2020202020203C506F7263656E74616A653E33353C2F506F7263656E74616A653E0A2020203C2F6461746F3E0A3C2F7461626C613E0A";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaDatos.setFileXml(data);
            tablaDatos.setRenglonSeleccionado(true);
            tablaDatos.setTablaBase(listTablaBase.get(5));
            listTablaDatos.add(tablaDatos);
            
            tablaDatos = new TablaDatos();
            tablaDatos.setControladores("");
            tablaDatos.setControlPorAnio(2016);
            tablaDatos.setDescripcion("SUBSIDIO ANUAL 2016");
            //SUBSIDIO ANUAL
            s = "3C3F786D6C2076657273696F6E3D22312E302220656E636F64696E673D225554462D38223F3E0A3C7461626C613E3C6461746F2069643D2231223E3C4C696D697465496E666572696F723E302E30313C2F4C696D697465496E666572696F723E3C43756F74613E343838342E32343C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2232223E3C4C696D697465496E666572696F723E32313232372E36343C2F4C696D697465496E666572696F723E3C43756F74613E343838312E39363C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2233223E3C4C696D697465496E666572696F723E33313834302E36383C2F4C696D697465496E666572696F723E3C43756F74613E343837392E34343C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2234223E3C4C696D697465496E666572696F723E34313637342E32303C2F4C696D697465496E666572696F723E3C43756F74613E343731332E32343C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2235223E3C4C696D697465496E666572696F723E34323435342E35363C2F4C696D697465496E666572696F723E3C43756F74613E343538392E35323C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2236223E3C4C696D697465496E666572696F723E35333335332E39323C2F4C696D697465496E666572696F723E3C43756F74613E343235302E37363C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2237223E3C4C696D697465496E666572696F723E35363630362E32383C2F4C696D697465496E666572696F723E3C43756F74613E333839382E34343C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2238223E3C4C696D697465496E666572696F723E36343032352E31363C2F4C696D697465496E666572696F723E3C43756F74613E333533352E35363C2F43756F74613E3C2F6461746F3E3C6461746F2069643D2239223E3C4C696D697465496E666572696F723E37343639362E31363C2F4C696D697465496E666572696F723E3C43756F74613E333034322E34383C2F43756F74613E3C2F6461746F3E3C6461746F2069643D223130223E3C4C696D697465496E666572696F723E38353336362E39323C2F4C696D697465496E666572696F723E3C43756F74613E323631312E33323C2F43756F74613E3C2F6461746F3E3C6461746F2069643D223131223E3C4C696D697465496E666572696F723E38383538382E30383C2F4C696D697465496E666572696F723E3C43756F74613E302E303C2F43756F74613E3C2F6461746F3E3C2F7461626C613E";
            data = new byte[s.length() / 2];
            for (i = 0; i < s.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                        + Character.digit(s.charAt(i + 1), 16));
            }
            tablaDatos.setFileXml(data);
            tablaDatos.setRenglonSeleccionado(true);
            tablaDatos.setTablaBase(listTablaBase.get(6));
            listTablaDatos.add(tablaDatos);
            
            for (i = 0; i < listTablaDatos.size(); i++) {
                session.save(listTablaDatos.get(i));
            }
            //</editor-fold>
            contador = contador + listTablaBase.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="CONTENEDORES PERSONALIZADAS">
            /**
             * ******************
             * CONTENEDORES PERSONALIZADAS ******************
             */
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("diseñadores.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("D");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Diseñador de tablas");
            contenedorPersonalizado.setOrdenId(13);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            int j;
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 13) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("T");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Tablas de Cruce");
            contenedorPersonalizado.setOrdenId(71);
            contenedorPersonalizado.setParentId(9);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 71) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configurar Créditos");
            contenedorPersonalizado.setOrdenId(100);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 100) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("A");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configurar Ahorros");
            contenedorPersonalizado.setOrdenId(101);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 101) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("D");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configuracion Despensa");
            contenedorPersonalizado.setOrdenId(102);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 102) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configurar Mov. Nónima");
            contenedorPersonalizado.setOrdenId(131);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 131) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configurar Asistencias");
            contenedorPersonalizado.setOrdenId(132);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 132) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Definir Conceptos Especiales");
            contenedorPersonalizado.setOrdenId(134);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 134) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("D");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Definir parámetros");
            contenedorPersonalizado.setOrdenId(72);
            contenedorPersonalizado.setParentId(9);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 72) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Conceptos para Corrida");
            contenedorPersonalizado.setOrdenId(135);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 135) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("P");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Periodicidades");
            contenedorPersonalizado.setOrdenId(130);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 130) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("calculos.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Cálculos");
            contenedorPersonalizado.setOrdenId(2);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 2) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configura reportes a usar");
            contenedorPersonalizado.setOrdenId(106);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 106) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configuracion conceptos Sat");
            contenedorPersonalizado.setOrdenId(107);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 107) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configuración Foliación");
            contenedorPersonalizado.setOrdenId(469);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 144) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configuración Mascara");
            contenedorPersonalizado.setOrdenId(468);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 143) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configurar Asistencias por excepciones");
            contenedorPersonalizado.setOrdenId(465);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 136) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configurar teclas de acceso");
            contenedorPersonalizado.setOrdenId(105);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 105) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Configurar timbrado");
            contenedorPersonalizado.setOrdenId(108);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 108) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("consulta.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("C");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Consultas");
            contenedorPersonalizado.setOrdenId(3);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 3) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("D");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Definir tablas del sistema");
            contenedorPersonalizado.setOrdenId(446);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 74) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("D");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Definir tablas del usuario");
            contenedorPersonalizado.setOrdenId(467);
            contenedorPersonalizado.setParentId(11);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 75) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("E");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Elementos de Personalización");
            contenedorPersonalizado.setOrdenId(70);
            contenedorPersonalizado.setParentId(9);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 70) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("E");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Elimina cálculo de nómina");
            contenedorPersonalizado.setOrdenId(66);
            contenedorPersonalizado.setParentId(8);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 66) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("E");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Estructura Menú");
            contenedorPersonalizado.setOrdenId(127);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 127) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("finiquitos_liquidaciones.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("F");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Finiquitos y Liquidaciones");
            contenedorPersonalizado.setOrdenId(7);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 7) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("M");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Módulos de NRH");
            contenedorPersonalizado.setOrdenId(126);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 126) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("otros_procesos.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("O");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Otros Procesos");
            contenedorPersonalizado.setOrdenId(5);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 5) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("P");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Perfiles Usuarios");
            contenedorPersonalizado.setOrdenId(129);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 129) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("reportes.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("R");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Reportes");
            contenedorPersonalizado.setOrdenId(4);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 4) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("S");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Sistemas");
            contenedorPersonalizado.setOrdenId(128);
            contenedorPersonalizado.setParentId(14);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 125) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono(null);
            contenedorPersonalizado.setKeyCode("T");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Tipos de Tablas");
            contenedorPersonalizado.setOrdenId(73);
            contenedorPersonalizado.setParentId(9);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 73) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("utilerias.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("U");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Utilerias");
            contenedorPersonalizado.setOrdenId(8);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 8) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            contenedorPersonalizado = new ContenedorPersonalizado();
            contenedorPersonalizado.setHabilitado(true);
            contenedorPersonalizado.setIcono("reportes.png");
            contenedorPersonalizado.setTipoIcono(TipoIcono.ICONOSISTEMA);
            contenedorPersonalizado.setKeyCode("R");
            contenedorPersonalizado.setModifiers("CTRL");
            contenedorPersonalizado.setNombre("Reportes del sistema");
            contenedorPersonalizado.setOrdenId(5);
            contenedorPersonalizado.setParentId(0);
            contenedorPersonalizado.setVisible(false);
            for (j = 0; j < listContenedor.size(); j++) {
                if (listContenedor.get(j).getId() == 16) {
                    break;
                }
            }
            contenedorPersonalizado.setContenedor(listContenedor.get(j));
            contenedorPersonalizado.setHerramienta(listHerramienta.get(0));
            contenedorPersonalizado.setPerfil(listPerfiles.get(2));
            contenedorPersonalizado.setUsuario(null);
            listContenedorPersonalizado.add(contenedorPersonalizado);
            
            for (i = 0; i < listContenedorPersonalizado.size(); i++) {
                session.save(listContenedorPersonalizado.get(i));
            }
            //</editor-fold>
            contador = contador + listContenedorPersonalizado.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            
            //<editor-fold defaultstate="collapsed" desc="Reporte de fuente">
            List<ReporteFuenteDatos> listReportFuenteDatos = new ArrayList<ReporteFuenteDatos>();
            ReporteFuenteDatos reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("1");
            reporteFuenteDatos.setNombre("Informacion Empleado");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_Empleados");
            reporteFuenteDatos.setOrden(1);
            reporteFuenteDatos.setUsaFormulas(true);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("2");
            reporteFuenteDatos.setNombre("Informacion de los movimientos de la nomina");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_Movimientos");
            reporteFuenteDatos.setOrden(2);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("3");
            reporteFuenteDatos.setNombre("Informacion Centro costos");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_CentroDeCostos");
            reporteFuenteDatos.setOrden(3);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("4");
            reporteFuenteDatos.setNombre("Informacion Conceptos de Nomina");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_ConcepNomDefi");
            reporteFuenteDatos.setOrden(4);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("5");
            reporteFuenteDatos.setNombre("Informacion Departamentos");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_Departamentos");
            reporteFuenteDatos.setOrden(5);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("6");
            reporteFuenteDatos.setNombre("Informacion registro incapacidades");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_RegistroIncapacidad");
            reporteFuenteDatos.setOrden(6);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("7");
            reporteFuenteDatos.setNombre("Informacion Ahorros");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_AhorrosMovimientos");
            reporteFuenteDatos.setOrden(7);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("8");
            reporteFuenteDatos.setNombre("Informacion Creditos");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_CreditosMovimientos");
            reporteFuenteDatos.setOrden(8);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("9");
            reporteFuenteDatos.setNombre("Informacion Recibos CFDI");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_Comprobante");
            reporteFuenteDatos.setOrden(9);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            reporteFuenteDatos = new ReporteFuenteDatos();
            reporteFuenteDatos.setClave("10");
            reporteFuenteDatos.setNombre("Informacion Recibos CFDI desde tablas");
            reporteFuenteDatos.setNombreEntidad("FuenteDatos_CFDIEmpleado");
            reporteFuenteDatos.setOrden(10);
            reporteFuenteDatos.setUsaFormulas(false);
            listReportFuenteDatos.add(reporteFuenteDatos);
            
            for (j = 0; j < listReportFuenteDatos.size(); j++) {
                session.save(listReportFuenteDatos.get(j));
            }
            contador = contador + listReportFuenteDatos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="Reportes Dinamicos">
            List<ReporteDinamico> listReporteDinamico = new ArrayList<ReporteDinamico>();
            listReporteDinamico = InicializaReportes.inicializadorReporteDinamicos(listReportFuenteDatos, listRazonSocial, listHerramienta, listTipoElementos);

            //</editor-fold>
            for (j = 0; j < listReporteDinamico.size(); j++) {
                session.save(listReporteDinamico.get(j));
            }
            contador = contador + listReporteDinamico.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="ParametrosConsulta">
            List<ParametrosConsulta> listParametrosConsulta = new ArrayList<ParametrosConsulta>();
            ParametrosConsulta parametrosConsulta;

            //<editor-fold defaultstate="collapsed" desc="ANTIGUEDAD EN LA EMPRESA">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("ANTIGUEDAD EN LA EMPRESA");
            parametrosConsulta.setNombreAbreviado("ANTIGUEDAD EN LA EMPRESA");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave,FDE2_6_ApellidosNombre,FDE9_256_fechaInicial,@Empleados_Antiguedad");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("10,40,12,7");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,|1234||");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Clave,Empleado,Fecha ingreso,Antiguedad laboral");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(351);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("A");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("ANTIGUEDAD EN LA EMPRESA");
            contenedor.setOrdenId(1);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(1L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="ANTIGUEDAD POR EMPLEADO">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("ANTIGUEDAD POR EMPLEADO");
            parametrosConsulta.setNombreAbreviado("ANTIGUEDAD POR EMPLEADO");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave,FDE2_6_ApellidosNombre,FDP73_327_descripcion,FDE9_256_fechaInicial");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("10,40,30,10");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,NULL");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Empleado,Nombre,Departamento,Fecha Ingreso");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(352);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("A");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("ANTIGUEDAD POR EMPLEADO");
            contenedor.setOrdenId(2);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(2L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="CONCENTRADO DE ALTAS Y BAJAS">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("CONCENTRADO DE ALTAS Y BAJAS");
            parametrosConsulta.setNombreAbreviado("CONCENTRADO DE ALTAS Y BAJAS");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave,FDE2_5_nombreYApellidos,FDE9_257_fechaIMSS,FDE9_44_fechaFinal");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("10,40,10,10");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,NULL");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Empleado,Nombre,Fecha IMSS,Fecha final");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(353);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("CONCENTRADO DE ALTAS Y BAJAS");
            contenedor.setOrdenId(3);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(3L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="EXPORTAR FINIQUITOS Y LIQUID.">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("EXPORTAR FINIQUITOS Y LIQUIDACIONES");
            parametrosConsulta.setNombreAbreviado("EXPORTAR FINIQUITOS Y LIQUID.");
            parametrosConsulta.setCamposMostrar("ZFS2_11_causaBaja,FDP42_191_clave,FDP42_193_descripcionPrevia,FDE2_3_clave,FDE2_5_nombreYApellidos,FDE2_35_RFC,FDE75_260_descripcion,FDE9_256_fechaInicial,FDE9_512_importe,FDE9_514_horas,FDE9_510_cuentaBancaria");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("5,10,30,10,40,10,30,10,10,5,20");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,NULL,NULL,UUUU-######-AAA,NULL,dd/MM/yyyy,|1234|.00|,NULL,NULL");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Causa baja,C.C.,Nombre,Empleado,Nombre,RFC,Tipo nomina,Fecha inicial,Importe,Horas,Cuenta bancaria");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,,,,,,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(354);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("E");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("EXPORTAR FINIQUITOS Y LIQUID.");
            contenedor.setOrdenId(4);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(4L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="LISTA EMPLEADOS ALFABETICO">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("LISTADO DE EMPLEADOS ALFABETICO");
            parametrosConsulta.setNombreAbreviado("LISTA EMPLEADOS ALFABETICO");
            parametrosConsulta.setCamposMostrar("FDE2_5_nombreYApellidos,FDE9_43_clave,FDE9_518_tipoRelacionLaboral,FDP73_326_clave,FDP80_357_descripcion,FDE2_35_RFC,FDE9_512_importe,ZFS1_7_salarioDiarioIntegrado");
            parametrosConsulta.setCamposTotalizar("FDE9_512_importe");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("40,10,8,10,30,15,8,8");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,NULL,NULL,UUUU-######-AAA,NULL,|1234|.00|");
            parametrosConsulta.setTipoFormatoTotales("|1&234|.00|");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Empleado,Referencia,R.L.,Depto.,Puesto,RFC,Sueldo,S.D.I.");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,,,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(355);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("L");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("LISTA DE EMPLEADOS ALFABETICO");
            contenedor.setOrdenId(5);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(5L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="MOVIMIENTOS NOMINA DEL CONCEPTO SUELDOS">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("MOVIMIENTOS DE NOMINA DEL CONCEPTO SUELDOS");
            parametrosConsulta.setNombreAbreviado("MOV. NOMINA CONCP. SUELDOS");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave|FDE2_5_nombreYApellidos,FDE2_41_fechaIngresoEmpresa,FDE9_256_fechaInicial,FDE9_44_fechaFinal,FDE2_35_RFC,FDE2_37_IMSS,FDE2_36_CURP,FDCE1_5_ConceptoImporte");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("50,10,10,10,15,10,18,15");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,NULL,UUUU-######-AAA,NULL,UUUU-######-UUUUUUA#,|1&234|.00|");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Empleado,F. Ingreso,F. Inicial,F. Final,RFC,IMSS,CURP,Acum. Sueldo");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,,,,SUELDO:001|");
            parametrosConsulta.setCamposWhereExtras("AND|ZFM4_35_clave|=|001");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(356);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("M");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("MOV. NOMINA CONCP. SUELDOS");
            contenedor.setOrdenId(6);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(6L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="RELACION DE VACACIONES">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("RELACION DE VACACIONES");
            parametrosConsulta.setNombreAbreviado("RELACION DE VACACIONES");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave,FDE2_5_nombreYApellidos,FDP73_326_clave,FDP80_357_descripcion,VAC194_1351_salidaVacac,VAC194_1352_regresoVac,VAC2_1051_diasVacaciones");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("10,40,10,30,10,10,8");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,NULL,NULL,NULL,NULL");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Empleado,Nombre,Depto.,Puesto,Salida. Vac.,Regreso. Vac.,Dias");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(357);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("RELACION DE VACACIONES");
            contenedor.setOrdenId(7);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(7L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="RELACION POR CONCEPTO">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("RELACION POR CONCEPTO");
            parametrosConsulta.setNombreAbreviado("RELACION POR CONCEPTO");
            parametrosConsulta.setCamposMostrar("ZFM3_7_clave,FDP73_326_clave,FDE2_3_clave,FDE2_6_ApellidosNombre,FDCE1_23_ValorParametro,FDCE1_5_ConceptoImporte,FDCE1_5_ConceptoImporte");
            parametrosConsulta.setCamposTotalizar("FDCE1_23_ValorParametro,FDCE1_5_ConceptoImporte,FDCE1_5_ConceptoImporte");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("8,10,10,40,8,10,10");
            parametrosConsulta.setTipoFormatoCamposMostrar("###,NULL,#####,NULL,|1&234|.00|,|1&234|.00|,|1&234|.00|");
            parametrosConsulta.setTipoFormatoTotales("|1&234|.00|,|1&234|.00|,|1&234|.00|");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("T.Period.,Depto.,Empleado,Nombre,Cantidad,ISR,IMSS");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,1,ISR:800|,IMSS:801|");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(358);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("C");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("RELACION POR CONCEPTO");
            contenedor.setOrdenId(8);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(8L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="REPORTE NOMINA">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("REPORTE NOMINA");
            parametrosConsulta.setNombreAbreviado("REPORTE NOMINA");
            parametrosConsulta.setCamposAgrupar("FDE75_259_clave|FDE75_260_descripcion,ZFM3_7_clave|ZFM3_9_descripcion,FDE2_3_clave|FDE2_5_nombreYApellidos");
            parametrosConsulta.setCamposMostrar("ZFM4_35_clave|ZFM4_38_descripcionAbreviada,ZFM5_49_valor,FDCE1_15_ConceptoTipoDato,FDCE1_13_Percepcion,FDCE1_14_Deduccion");
            parametrosConsulta.setCamposTotalizar("FDCE1_14_Deduccion,FDCE1_13_Percepcion");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("50,8,10,10,10");
            parametrosConsulta.setTipoFormatoCamposMostrar("|1234||,NULL,|1&234|.00|,|1&234|.00|,|1&234|.00|");
            parametrosConsulta.setTipoFormatoTotales("|1&234|.00|,|1&234|.00|");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Concep.,Dato,Dato Concep,Percepciones,Deducciones");
            parametrosConsulta.setTituloGrupoVisualizar("Tipo Nomina,Periodo Nomina,Empleado");
            parametrosConsulta.setTotalizarGrupos("FM3_7_clave|ZFM3_9_descripcion,FDE2_3_clave|FDE2_5_nombreYApellidos");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(359);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("N");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("REPORTE NOMINA");
            contenedor.setOrdenId(9);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(9L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
             //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="RESUM PERCEP/DEDUC">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("RESUMEN PERCEP/DEDUC");
            parametrosConsulta.setNombreAbreviado("RESUM PERCEP/DEDUC");
            parametrosConsulta.setCamposMostrar("ZFM4_35_clave|ZFM4_37_descripcion,FDCE1_13_Percepcion,FDCE1_14_Deduccion");
            parametrosConsulta.setCamposTotalizar("FDCE1_13_Percepcion,FDCE1_14_Deduccion");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("40,10,10");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,|1&234|.00|,|1&234|.00|");
            parametrosConsulta.setTipoFormatoTotales("|1&234|.00|,|1&234|.00|");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Concepto,Percepciones,Deducciones");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(360);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("SHIFT");
            contenedor.setNombre("RESUMEN PERCEP/DEDUC");
            contenedor.setOrdenId(10);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(10L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="RESUMEN AUSENTISMO">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("RESUMEN DE AUSENTISMO");
            parametrosConsulta.setNombreAbreviado("RESUMEN AUSENTISMO");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave|FDE2_5_nombreYApellidos,FDE2_35_RFC,FDE2_36_CURP,FDE2_37_IMSS,ZFM10_68_fecha,ZFM11_74_excepcion");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("50,10,18,10,10,15");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,UUUU-######-AAA,UUUU-######-UUUUUUA#,NULL,NULL,NULL");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Empleado,RFC,CURP,IMSS,Fecha,Excepcion");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,,");
            parametrosConsulta.setCamposWhereExtras("AND|ZFM11_74_excepcion|=|Ausentismo");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(361);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("R");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("RESUMEN DE AUSENTISMO");
            contenedor.setOrdenId(11);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(11L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="SDI">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("SDI");
            parametrosConsulta.setNombreAbreviado("SDI");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave,FDE2_5_nombreYApellidos,FDE9_43_clave,ZFS1_7_salarioDiarioIntegrado,ZFS1_2_fecha,ZFS4_27_fechaCambio,ZFS4_26_importe");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("10,40,10,8,10,10,8");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,|1234|.00|,NULL,NULL,|1234|.00|");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setTituloCamposVisualizar("Empleado,Nombre,Referencia,SDI,Fecha SDI,Fecha cambio,Importe SDI");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,,,,");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(362);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("S");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("SDI");
            contenedor.setOrdenId(12);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(12L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //<editor-fold defaultstate="collapsed" desc="VERIFICADOR FALTAS TOTALES">
            parametrosConsulta = new ParametrosConsulta();
            parametrosConsulta.setNombre("VERIFICADOR DE FALTAS TOTALES");
            parametrosConsulta.setNombreAbreviado("VERIFICADOR DE FALTAS TOTALES");
            parametrosConsulta.setCamposMostrar("FDE2_3_clave|FDE2_5_nombreYApellidos,FDP73_327_descripcion,ZFM11_74_excepcion,ZFM10_70_cantidad");
            parametrosConsulta.setCamposTotalizar("ZFM10_70_cantidad");
            parametrosConsulta.setModoVisualizarTabla(true);
            parametrosConsulta.setSizeColumnas("50,30,10,5");
            parametrosConsulta.setTipoFormatoCamposMostrar("NULL,NULL,NULL,NULL");
            parametrosConsulta.setTotalGlobal(true);
            parametrosConsulta.setContenedorGrupo(buscarContenedor(3, listContenedor));
            parametrosConsulta.setTituloCamposVisualizar("Empleado,Depto.,Excepcion,Cantidad");
            parametrosConsulta.setReporteFuenteDatos(listReportFuenteDatos.get(0));
            parametrosConsulta.setDatosEspecialesConsulta(",,,");
            parametrosConsulta.setCamposWhereExtras("AND|ZFM11_74_excepcion|=|Falta");
            parametrosConsulta.setTipoFormatoTotales("|1234||");
            parametrosConsulta.setUsaFiltroCorrida(true);
            
            contenedor = new Contenedor();
            contenedor.setId(363);
            contenedor.setAccion("CriteriosConsultaTablaV2");
            contenedor.setCompartir(true);
            contenedor.setHabilitado(true);
            contenedor.setIcono(null);
            contenedor.setKeyCode("V");
            contenedor.setModifiers("CTRL");
            contenedor.setNombre("VERIFICADOR DE FALTAS TOTALES");
            contenedor.setOrdenId(13);
            contenedor.setParentId(3);
            contenedor.setTipoAcciones(TipoAcciones.GRUPOCONSULTA);
            contenedor.setVisible(true);
            contenedor.setHerramienta(listHerramienta.get(0));
            contenedor.setTipoElemento(listTipoElementos.get(1));
            contenedor.setVentana(null);
            contenedor.setIdMultiUsos(13L);
            parametrosConsulta.setContenedorGrupo(contenedor);
            listParametrosConsulta.add(parametrosConsulta);
            //</editor-fold>

            //</editor-fold>
            for (j = 0; j < listParametrosConsulta.size(); j++) {
                session.save(listParametrosConsulta.get(j));
            }
            contador = contador + listParametrosConsulta.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="PERMISOS">
            Permisos permisos = null;
            for (j = 0; j < listPerfiles.size(); j++) {
                for (int k = 0; k < listVentana.size(); k++) {
                    if (listVentana.get(k).getTipoVentana() == TipoVentana.CATALOGO || listVentana.get(k).getTipoVentana() == TipoVentana.CATALOGODIALOG) {
                        permisos = new Permisos();
                        permisos.setConsultar(true);
                        permisos.setEliminar(true);
                        permisos.setImprimir(true);
                        permisos.setInsertar(true);
                        permisos.setModificar(true);
                        permisos.setVentana(listVentana.get(k));
                        permisos.setPerfil(listPerfiles.get(j));
                        session.save(permisos);
                        contador++;
                        if (contador % rango == 0 & contador > 0) {
                            session.flush();
                            session.clear();
                            contador = 0;
                        }
                    }
                }
                
            }

            //</editor-fold>
            mensaje.setResultado(true);
            mensaje.setNoError(0);
            mensaje.setError("");
            session.getTransaction().commit();
        } catch (ClassNotFoundException ex) {
            System.out.println("i " + i);
            System.out.println("i contendor " + listContenedor.get(i).getId());
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("inicializarNuevaMEFMaster()1_Error: ").append(ex));
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensaje.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensaje.setError(exc.getLocalizedMessage());
            }
            mensaje.setResultado(null);
        } catch (HibernateException ex) {
            System.out.println("i " + i);
            if (!listContenedor.isEmpty()) {
                System.out.println("i contendor " + listContenedor.get(i).getId());
            }
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("inicializarNuevaMEFMaster()1_Error: ").append(ex));
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensaje.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensaje.setError(exc.getLocalizedMessage());
            }
            mensaje.setResultado(null);
        }
        return mensaje;
    }
    
    private final String archivosKey = System.getProperty("file.separator").concat("REPORTESSISTEMAS").concat(System.getProperty("file.separator")).concat("CONFIG");
    private final String archivosReportes = System.getProperty("file.separator").concat("REPORTESSISTEMAS");
    
    private String buscaRutaArchivos(String ruta, String pathBuscar) {
        File file = new File(ruta);
        String path = "";
        
        if (file.exists()) {
            Path root = file.toPath();
            if (root.getRoot().toString().equalsIgnoreCase(ruta)) {
                ruta = ruta.replace(System.getProperty("file.separator"), "").concat(pathBuscar);
            }
            File config = new File(ruta.concat(pathBuscar));
            if (config.exists()) {
                return config.getAbsolutePath();
            } else {
                if (file.getParentFile() != null) {
                    path = buscaRutaArchivos(file.getParentFile().getAbsolutePath(), pathBuscar);
                }
            }
        }
        return path;
    }
    
    private Contenedor buscarContenedor(Integer id, List<Contenedor> listContenedor) {
        Contenedor c = null;
        for (int i = 0; i < listContenedor.size(); i++) {
            if (listContenedor.get(i).getId() == id) {
                return listContenedor.get(i);
            }
        }
        return c;
    }
    
    @Override
    public Mensaje inicializarNuevaMEF(String uuidCxn, int rango, String nombreBaseDatos) {
        mensaje = new Mensaje();
        Session session = HibernateUtil.currentSession(uuidCxn);
        listPaises = new ArrayList<Paises>();
        Paises paises = null;
        listEstados = new ArrayList<Estados>();
        Estados estados = null;
        listMunicipios = new ArrayList<Municipios>();
        Municipios municipios = null;
        listCiudades = new ArrayList<Ciudades>();
        Ciudades ciudades = null;
        listCp = new ArrayList<Cp>();
        Cp cp = null;
        List<RazonesSociales> listRazonesSociales = new ArrayList<RazonesSociales>();
        RazonesSociales razonesSociales = null;
        List<Periodicidad> listPeriodicidad = new ArrayList<Periodicidad>();
        Periodicidad periodicidad = null;
        List<Monedas> listMonedas = new ArrayList<Monedas>();
        Monedas monedas = null;
        List<BaseNomina> listBaseNomina = new ArrayList<BaseNomina>();
        BaseNomina baseNomina = null;
        List<TipoCorrida> listTipoCorrida = new ArrayList<TipoCorrida>();
        TipoCorrida tipoCorrida = null;
        List<CampoDIM> listCampoDIM = new ArrayList<CampoDIM>();
        CampoDIM campoDIM = null;
        List<Incidencias> listIncidencias = new ArrayList<Incidencias>();
        Incidencias incidencias = null;
        List<Excepciones> listExcepciones = new ArrayList<Excepciones>();
        Excepciones excepciones = null;
        List<ConfiguraMovimiento> listConfiguraMovimiento = new ArrayList<ConfiguraMovimiento>();
        ConfiguraMovimiento configuraMovimiento = null;
        List<ConfiguracionAsistencias> listConfiguraAsistencias = new ArrayList<ConfiguracionAsistencias>();
        ConfiguracionAsistencias configuraAsistencias = null;
        List<ConceptoDeNomina> listConceptoDeNominas = new ArrayList<ConceptoDeNomina>();
        ConceptoDeNomina conceptoDeNomina = null;
        List<ParaConcepDeNom> listParaConcepDeNom = new ArrayList<ParaConcepDeNom>();
        ParaConcepDeNom paraConcepDeNom = null;
        List<ConcepNomDefi> listConceptoDeNominaDefinicion = new ArrayList<ConcepNomDefi>();
        ConcepNomDefi conceptoDeNominaDefinicion = null;
        List<TipoNomina> listTipoNomina = new ArrayList<TipoNomina>();
        TipoNomina tipoNomina = null;
        List<RegistroPatronal> listRegistroPatronal = new ArrayList<RegistroPatronal>();
        RegistroPatronal registroPatronal = null;
        List<CentroDeCosto> listCentroDeCosto = new ArrayList<CentroDeCosto>();
        CentroDeCosto centroDeCosto = null;
        List<CategoriasPuestos> listCategoriaPuestos = new ArrayList<CategoriasPuestos>();
        CategoriasPuestos categoriasPuestos = null;
        List<Puestos> listPuestos = new ArrayList<Puestos>();
        Puestos puestos = null;
        List<Departamentos> listDepartamentos = new ArrayList<Departamentos>();
        Departamentos departamentos = null;
        List<Jornada> listJornada = new ArrayList<Jornada>();
        Jornada jornada = null;
        List<Horario> listHorario = new ArrayList<Horario>();
        Horario horario = null;
        List<Turnos> listTurnos = new ArrayList<Turnos>();
        Turnos turnos = null;
        List<Plazas> listPlazas = new ArrayList<Plazas>();
        Plazas plazas = null;
        List<Empleados> listEmpleado = new ArrayList<Empleados>();
        Empleados empleados = null;
        List<FormasDePago> listFormasDePago = new ArrayList<FormasDePago>();
        FormasDePago formasDePago = null;
        List<TipoContrato> listTipoContratos = new ArrayList<TipoContrato>();
        TipoContrato tipoContrato = null;
        List<Bancos> listBancos = new ArrayList<Bancos>();
        Bancos bancos = null;
        List<Parentesco> listParentesco = new ArrayList<Parentesco>();
        Parentesco parentesco = null;
        List<Cursos> listCursos = new ArrayList<Cursos>();
        Cursos cursos = null;
        List<Estudios> listEstudios = new ArrayList<Estudios>();
        Estudios estudios = null;
        List<Firmas> listFirmas = new ArrayList<Firmas>();
        Firmas firmas = null;
        List<PeriodosNomina> listPeriodosNominas = new ArrayList<PeriodosNomina>();
        PeriodosNomina periodoNomina;
        List<CreditoAhorro> listCreditoAhorro = new ArrayList<CreditoAhorro>();
        CreditoAhorro creditoAhorro;
        List<DatosDisponiblesCxnConta> listDatosDisponibleCxnConta = new ArrayList<DatosDisponiblesCxnConta>();
        DatosDisponiblesCxnConta datosDisponiblesCxnConta = null;
        List<TiposVacaciones> listTiposVacaciones = new ArrayList<TiposVacaciones>();
        TiposVacaciones tiposVacaciones = null;
        int i, contador = 0;
        try {
            session.beginTransaction();
            /*evalua si ya esta inicializada base de datos*/
            Query query = session.createQuery("SELECT COUNT(b) FROM " + Bancos.class.getSimpleName() + " b");
            boolean existe = (Long) query.uniqueResult() > 0;
            if (existe) {
                mensaje.setResultado(true);
                mensaje.setNoError(0);
                mensaje.setError("Existe");
                session.getTransaction().commit();
                return mensaje;
            }

            //<editor-fold defaultstate="collapsed" desc="Paises">
            /**
             * ******************
             * Paises ******************
             */
            paises = new Paises();
            paises.setClave("MEX");
            paises.setDescripcion("Mexico");
            paises.setNacionalidad("Mexicana");
            listPaises.add(paises);
            
            paises = new Paises();
            paises.setClave("USA");
            paises.setDescripcion("Estados Unidos");
            paises.setNacionalidad("Estadounidense");
            listPaises.add(paises);
            
            paises = new Paises();
            paises.setClave("CAN");
            paises.setDescripcion("Canada");
            paises.setNacionalidad("Canadiense");
            listPaises.add(paises);
            
            for (i = 0; i < listPaises.size(); i++) {
                session.save(listPaises.get(i));
            }
            //</editor-fold>
            contador = contador + listPaises.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Estados">
            /**
             * ******************
             * Estados ******************
             */
            estados = new Estados();
            estados.setClave("AGU");
            estados.setDescripcion("Aguascalientes");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("BCN");
            estados.setDescripcion("Baja California");//JSA08
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("BCS");
            estados.setDescripcion("Baja California Sur");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("CAM");
            estados.setDescripcion("Campeche");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("CHP");
            estados.setDescripcion("Chiapas");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("CHH");
            estados.setDescripcion("Chihuahua");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("COA");
            estados.setDescripcion("Coahuila");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("COL");
            estados.setDescripcion("Colima");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("DIF");
            estados.setDescripcion("Ciudad de México");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("DUR");
            estados.setDescripcion("Durango");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("GUA");
            estados.setDescripcion("Guanajuato");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("GRO");
            estados.setDescripcion("Guerrero");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("HID");
            estados.setDescripcion("Hidalgo");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("JAL");
            estados.setDescripcion("Jalisco");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MEX");
            estados.setDescripcion("Estado de México");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MIC");
            estados.setDescripcion("Michoacán");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MOR");
            estados.setDescripcion("Morelos");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NAY");
            estados.setDescripcion("Nayarit");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NLE");
            estados.setDescripcion("Nuevo León");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("OAX");
            estados.setDescripcion("Oaxaca");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("PUE");
            estados.setDescripcion("Puebla");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("QUE");
            estados.setDescripcion("Querétaro");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("ROO");
            estados.setDescripcion("Quintana Roo");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("SLP");
            estados.setDescripcion("San Luis Potosí");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("SIN");
            estados.setDescripcion("Sinaloa");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("SON");
            estados.setDescripcion("Sonora");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("TAB");
            estados.setDescripcion("Tabasco");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("TAM");
            estados.setDescripcion("Tamaulipas");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("TLA");
            estados.setDescripcion("Tlaxcala");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("VER");
            estados.setDescripcion("Veracruz");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("YUC");
            estados.setDescripcion("Yucatán");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("ZAC");
            estados.setDescripcion("Zacatecas");
            estados.setPaises(listPaises.get(0));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("AL");
            estados.setDescripcion("Alabama");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("Ak");
            estados.setDescripcion("Alaska");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("AZ");
            estados.setDescripcion("Arizona");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("AR");
            estados.setDescripcion("Arkansas");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("CA");
            estados.setDescripcion("California");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NC");
            estados.setDescripcion("Carolina del Norte");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("SC");
            estados.setDescripcion("Carolina del Sur");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("CO");
            estados.setDescripcion("Colorado");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("CT");
            estados.setDescripcion("Connecticut");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("ND");
            estados.setDescripcion("Dakota del Norte");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("SD");
            estados.setDescripcion("Dakota del Sur");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("DE");
            estados.setDescripcion("Delaware");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("FL");
            estados.setDescripcion("Florida");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("GA");
            estados.setDescripcion("Georgia");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("HI");
            estados.setDescripcion("Hawái");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("ID");
            estados.setDescripcion("Idaho");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("IL");
            estados.setDescripcion("Illinois");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("IN");
            estados.setDescripcion("Indiana");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("AI");
            estados.setDescripcion("Iowa");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("KS");
            estados.setDescripcion("Kansas");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("KY");
            estados.setDescripcion("Kentucky");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("LA");
            estados.setDescripcion("Luisiana");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("ME");
            estados.setDescripcion("Maine");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MD");
            estados.setDescripcion("Maryland");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MA");
            estados.setDescripcion("Massachusetts");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MI");
            estados.setDescripcion("Michigan");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MN");
            estados.setDescripcion("Minesota");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MS");
            estados.setDescripcion("Misisipi");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MO");
            estados.setDescripcion("Misuri");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MT");
            estados.setDescripcion("Montana");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NE");
            estados.setDescripcion("Nebraska");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NV");
            estados.setDescripcion("Nevada");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NJ");
            estados.setDescripcion("Nueva Jersey");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NY");
            estados.setDescripcion("Nueva York");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NH");
            estados.setDescripcion("Nuevo Hampshire");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NM");
            estados.setDescripcion("Nuevo México");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("OH");
            estados.setDescripcion("Ohio");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("OK");
            estados.setDescripcion("Oklahoma");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("OR");
            estados.setDescripcion("Oregon");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("PA");
            estados.setDescripcion("Pensilvania");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("RI");
            estados.setDescripcion("Rhode Island");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("TN");
            estados.setDescripcion("Tennessee");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("TX");
            estados.setDescripcion("Texas");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("UT");
            estados.setDescripcion("Utah");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("VT");
            estados.setDescripcion("Vermont");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("VA");
            estados.setDescripcion("Virginia");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("WV");
            estados.setDescripcion("Virginia Occidental");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("WA");
            estados.setDescripcion("Washington");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("WI");
            estados.setDescripcion("Wisconsin");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("WY");
            estados.setDescripcion("Wyoming");
            estados.setPaises(listPaises.get(1));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("ON");
            estados.setDescripcion("Ontario");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("QC");
            estados.setDescripcion("Quebec");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NS");
            estados.setDescripcion("Nueva Escocia");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NB");
            estados.setDescripcion("Nuevo Brunswick ");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("MB");
            estados.setDescripcion("Manitoba");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("BC");
            estados.setDescripcion("Columbia Británica");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("PE");
            estados.setDescripcion(" Isla del Príncipe Eduardo");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("SK");
            estados.setDescripcion("Saskatchewan");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("AB");
            estados.setDescripcion("Alberta");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NL");
            estados.setDescripcion("Terranova y Labrador");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("NT");
            estados.setDescripcion("Territorios del Noroeste");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("YT");
            estados.setDescripcion("Yukón");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            estados = new Estados();
            estados.setClave("UN");
            estados.setDescripcion("Nunavut");
            estados.setPaises(listPaises.get(2));
            listEstados.add(estados);
            
            for (i = 0; i < listEstados.size(); i++) {
                session.save(listEstados.get(i));
            }
            //</editor-fold>
            contador = contador + listEstados.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Municipios">
            /**
             * ******************
             * Municipios ******************
             */
            //Municipios de Aguascaliente
            municipios = new Municipios();
            municipios.setClave("001");
            municipios.setDescripcion("Aguascalientes");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("002");
            municipios.setDescripcion("Asientos");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("003");
            municipios.setDescripcion("Calvillo");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("004");
            municipios.setDescripcion("Cosío");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("005");
            municipios.setDescripcion("Jesús María");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("006");
            municipios.setDescripcion("Pabellón de Arteaga");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("007");
            municipios.setDescripcion("Rincón de Romos");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("008");
            municipios.setDescripcion("San José de Gracia");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("009");
            municipios.setDescripcion("Tepezalá");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("010");
            municipios.setDescripcion("El Llano");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("011");
            municipios.setDescripcion("San Francisco de los Romo");
            municipios.setEstados(listEstados.get(0));
            listMunicipios.add(municipios);

            //Municipios de Sinaloa
            municipios = new Municipios();
            municipios.setClave("012");
            municipios.setDescripcion("Ahome");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("013");
            municipios.setDescripcion("Angostura");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("014");
            municipios.setDescripcion("Badiraguato");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("015");
            municipios.setDescripcion("Concordia");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("016");
            municipios.setDescripcion("Cosalá");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("017");
            municipios.setDescripcion("Culiacán");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("018");
            municipios.setDescripcion("Choix");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("019");
            municipios.setDescripcion("Elota");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("020");
            municipios.setDescripcion("Escuinapa");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("021");
            municipios.setDescripcion("El Fuerte");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("022");
            municipios.setDescripcion("Guasave");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("023");
            municipios.setDescripcion("Mazatlán");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("024");
            municipios.setDescripcion("Mocorito");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("025");
            municipios.setDescripcion("Rosario");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("026");
            municipios.setDescripcion("Salvador Alvarado");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("027");
            municipios.setDescripcion("San Ignacio");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("028");
            municipios.setDescripcion("Sinaloa");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            municipios = new Municipios();
            municipios.setClave("029");
            municipios.setDescripcion("Navolato");
            municipios.setEstados(listEstados.get(24));
            listMunicipios.add(municipios);
            
            for (i = 0; i < listMunicipios.size(); i++) {
                session.save(listMunicipios.get(i));
            }
            //</editor-fold>
            contador = contador + listMunicipios.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Ciudades">
            /**
             * ******************
             * Ciudades de Ahome posicion 11 ******************
             */
            ciudades = new Ciudades();
            ciudades.setClave("0001");
            ciudades.setDescripcion("Los Mochis");
            ciudades.setMunicipios(listMunicipios.get(11));
            listCiudades.add(ciudades);
            
            ciudades = new Ciudades();
            ciudades.setClave("0002");
            ciudades.setDescripcion("Ahome");
            ciudades.setMunicipios(listMunicipios.get(11));
            listCiudades.add(ciudades);
            
            ciudades = new Ciudades();
            ciudades.setClave("0003");
            ciudades.setDescripcion("Higuera de Zaragoza");
            ciudades.setMunicipios(listMunicipios.get(11));
            listCiudades.add(ciudades);
            
            ciudades = new Ciudades();
            ciudades.setClave("0004");
            ciudades.setDescripcion("Topolobampo");
            ciudades.setMunicipios(listMunicipios.get(11));
            listCiudades.add(ciudades);
            
            ciudades = new Ciudades();
            ciudades.setClave("0005");
            ciudades.setDescripcion("Culiacán Rosales");
            ciudades.setMunicipios(listMunicipios.get(16));
            listCiudades.add(ciudades);
            
            for (i = 0; i < listCiudades.size(); i++) {
                session.save(listCiudades.get(i));
            }

            //</editor-fold>
            contador = contador + listCiudades.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Cp">
            /**
             * ******************
             * Cp ******************
             */
            String descripcion;
            cp = new Cp();
            descripcion = "81200";
            cp.setClave(descripcion);
            descripcion = cp.getClave() + " " + listCiudades.get(0).getDescripcion();
            if (descripcion.length() > 30) {
                cp.setDescripcion(descripcion.substring(0, 30));
            } else {
                cp.setDescripcion(descripcion);
            }
            cp.setCiudades(listCiudades.get(0));
            listCp.add(cp);
            
            cp = new Cp();
            descripcion = "81315";
            cp.setClave(descripcion);
            descripcion = cp.getClave() + " " + listCiudades.get(1).getDescripcion();
            if (descripcion.length() > 30) {
                cp.setDescripcion(descripcion.substring(0, 30));
            } else {
                cp.setDescripcion(descripcion);
            }
            cp.setCiudades(listCiudades.get(1));
            listCp.add(cp);
            
            cp = new Cp();
            descripcion = "81330";
            cp.setClave(descripcion);
            descripcion = cp.getClave() + " " + listCiudades.get(2).getDescripcion();
            if (descripcion.length() > 30) {
                cp.setDescripcion(descripcion.substring(0, 30));
            } else {
                cp.setDescripcion(descripcion);
            }
            cp.setCiudades(listCiudades.get(2));
            listCp.add(cp);
            
            cp = new Cp();
            descripcion = "81370";
            cp.setClave(descripcion);
            descripcion = cp.getClave() + " " + listCiudades.get(3).getDescripcion();
            if (descripcion.length() > 30) {
                cp.setDescripcion(descripcion.substring(0, 30));
            } else {
                cp.setDescripcion(descripcion);
            }
            cp.setCiudades(listCiudades.get(3));
            listCp.add(cp);
            
            cp = new Cp();
            descripcion = "80000";
            cp.setClave(descripcion);
            descripcion = cp.getClave() + " " + listCiudades.get(4).getDescripcion();
            if (descripcion.length() > 30) {
                cp.setDescripcion(descripcion.substring(0, 30));
            } else {
                cp.setDescripcion(descripcion);
            }
            cp.setCiudades(listCiudades.get(4));
            listCp.add(cp);
            
            for (i = 0; i < listCp.size(); i++) {
//                cp = listCp.get(i);
                session.save(listCp.get(i));
            }
            //</editor-fold>
            contador = contador + listCp.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="ConfiguraTimbrado">
            /**
             * ******************
             * ConfiguraTimbrado ******************
             */
            List<ConfiguraTimbrado> listConfiguraTimbrados = new ArrayList<ConfiguraTimbrado>();
            ConfiguraTimbrado configuraTimbrado = new ConfiguraTimbrado();
            configuraTimbrado.setClave("1");
            configuraTimbrado.setContraseña("N1hTyjCbltI=");
            configuraTimbrado.setDescripcion("BIINFORM");
            configuraTimbrado.setURL("http://biinform.com.mx/srvCFDiprueba/service1.asmx");
            configuraTimbrado.setUsuario("prueba");
            configuraTimbrado.setPrincipal(true);
            listConfiguraTimbrados.add(configuraTimbrado);
            
            configuraTimbrado = new ConfiguraTimbrado();
            configuraTimbrado.setClave("2");
            configuraTimbrado.setContraseña("N1hTyjCbltI=");
            configuraTimbrado.setDescripcion("MACROPRO");
            configuraTimbrado.setURL("http://portalmacropro.com.mx/srvCFDiprueba/service1.asmx");
            configuraTimbrado.setUsuario("prueba");
            configuraTimbrado.setPrincipal(false);
            listConfiguraTimbrados.add(configuraTimbrado);
            
            for (i = 0; i < listConfiguraTimbrados.size(); i++) {
                session.save(listConfiguraTimbrados.get(i));
            }
            //</editor-fold>
            contador = contador + listConfiguraTimbrados.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="RazonesSociales">
            /**
             * ******************
             * RazonesSociales ******************
             */
            razonesSociales = new RazonesSociales();
            razonesSociales.setClave("0001");
            razonesSociales.setRazonsocial("Empresa Prueba");
            razonesSociales.setRfc("AAA010101AAA");
            razonesSociales.setRepresentantelegal("Representante Prueba");
            razonesSociales.setTelefono("9999999999");
            razonesSociales.setCalle("Calle Desconocido");
            razonesSociales.setColonia("Colonia Desconocido");
            razonesSociales.setCp(listCp.get(0));
            razonesSociales.setCiudades(listCiudades.get(0));
            razonesSociales.setMunicipios(listMunicipios.get(11));
            razonesSociales.setEstados(listEstados.get(24));
            razonesSociales.setPaises(listPaises.get(0));
            razonesSociales.setNumeroex("000");
            razonesSociales.setNumeroin("000");
            razonesSociales.setFolio("000000000001");
            razonesSociales.setRegimenFiscal("601");
            String file = "3082046130820349A00302010202143230303031303030303030323030303031343238300D06092A864886F70D01010505003082015C311A301806035504030C11412E432E20322064652070727565626173312F302D060355040A0C26536572766963696F2064652041646D696E69737472616369C3B36E205472696275746172696131383036060355040B0C2F41646D696E69737472616369C3B36E20646520536567757269646164206465206C6120496E666F726D616369C3B36E3129302706092A864886F70D010901161A617369736E657440707275656261732E7361742E676F622E6D783126302406035504090C1D41762E20486964616C676F2037372C20436F6C2E20477565727265726F310E300C06035504110C053036333030310B3009060355040613024D583119301706035504080C10446973747269746F204665646572616C3112301006035504070C09436F796F6163C3A16E3134303206092A864886F70D0109020C25526573706F6E7361626C653A2041726163656C692047616E64617261204261757469737461301E170D3133303530373136303132395A170D3137303530373136303132395A3081DB3129302706035504031320414343454D20534552564943494F5320454D50524553415249414C45532053433129302706035504291320414343454D20534552564943494F5320454D50524553415249414C455320534331293027060355040A1320414343454D20534552564943494F5320454D50524553415249414C455320534331253023060355042D131C414141303130313031414141202F2048454754373631303033345332311E301C06035504051315202F20484547543736313030334D44464E535230383111300F060355040B130870726F647563746F30819F300D06092A864886F70D010101050003818D0030818902818100A4BF6DE515CBA13768E0DA36E2DDD92DCF5DA42B7B4B46C66613794C5AE79C76B7FEFDD7DB6D80EA4A17A84F1792AF958878CEDA90A8FE32115F878C7EF2CBFD9DFFE7BBB7E27AD634EB6DD2EA4F7583D3EB397C22FA24EAC5E21AB2FFF10675FBD87ED74644DAD91FE7408D65A69764BF86B37D694C6B692E6AA8ED5BEDBB650203010001A31D301B300C0603551D130101FF04023000300B0603551D0F0404030206C0300D06092A864886F70D01010505000382010100023D7016657D83B8A89956FDF9452D5614A813DAE6D43BD0BE6F27D96A957A67360D3A20382A6A64A8753F6E4647A8A8D1F91E7D86862155D9FE0A550896265FD8284787CB346B4BC144D2772077C0D5A916773A1C6192D78F3DE6D04AF7D4163EB92DEA594B4FB0E346543C992FA21404152516879C4213FD00EDFD7ABF3321B794D9BD0C3012710ADE2F6C3285BD3F62DD5D0FBA8A3E9269A47F5E43DAB04647E76B77C1FC620F130C614A7F6CCA991B204FB9C87A8BBA5A97D8A79EF4588BCB25063E7EC329DF7E03D39529E72918480C5419ADF445D533038AF1379853F96F708565460FCF130F7DCDC39E6E35151C44EDD95151E6756446F868E195FB03";
            byte[] data = new byte[file.length() / 2];
            for (i = 0; i < file.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(file.charAt(i), 16) << 4)
                        + Character.digit(file.charAt(i + 1), 16));
            }
            razonesSociales.setCertificadoSAT(data);
            file = "308202C6304006092A864886F70D01050D3033301B06092A864886F70D01050C300E040802010002818100A402020400301406082A864886F70D03070408308202760201003004820280FE5B4A92EDCBBAA5752A56BDF1300BDF9DF4EF99D6333C78D7878201E399B6B7AE884A1CCF690ACDCE3F2ACB50440DAA2B47FB18307E323C2F9E8C87E63FEA8BB940B1B0D69048F2DCF54624A4C3F9E4B3B97F7B2AE0229C82820C29541C84E7EF516CC5CF5E91C63588920C196EC779B47CA360C08CC19653374E66C5D00DFABF4715B181F5702512543295FE325AD7EDE1320107AC4EA623BE94A197273C9CB2D2BE49F1696FBFD1E0B61A106F15EB27467EACC36547BFF28488D91C0EED1373916A546E55CDB95B9C3FB3DCAED4166EAE3FD001B01629534D64F20271BBC53B83192B90187FDCB3A4C41C73FA13A1A33B641B6B5554982F77641E9E72D402E6EB42272414BF08F3DCCF652C3B979CD392C1009FFD7523A47F0D16442031EA0C5ED0C5B10D27DC9DDFF7CECE2B7B965ED18C085F12DCD878397D055271B9CCFEFE7664B71038C8E1E5D4C4699DDA28055BC05F74F51B18BB1DECAEC7E88A289077CFFE636C5BC20733B2207E5EC41D0FAC6C114DE4A1F259046F2CAE6CC10F2413715363991AD2247CBCAB90E7F0F766DFC8BF163B6C415F85CA0F9B237E64F29C132AA93D4A6C817485962A6CA8286B74D419B4B8015537ECE92E233BA00F0391042D0A2F50DCCC1AA5DDDAE41FF25CF1CA972C45223093D7D9D11F84475F43CCC2337EBD43A76162BDBC83CAB1E3E08F205EC0DF7EB5BA54E8DE8EFEC3E5CFEBACE37C6E8B0E2625CA8D716BAD3D8605D4D92BF4A6EE9EA30064219562ED7559AEE56A473085DE22AD63AC4B8333A56C5023003D47B3CEF6C05BD49955D51B3A7B10309B7B959C4020B386C24053157E70D89B13F4385BBE081B7FD7782910D71ADEF29EA5588A7F56F3ECDEB1C907636C04428FD36380E1164249EDAA7D";
            data = new byte[file.length() / 2];
            for (i = 0; i < file.length(); i += 2) {
                data[i / 2] = (byte) ((Character.digit(file.charAt(i), 16) << 4)
                        + Character.digit(file.charAt(i + 1), 16));
            }
            razonesSociales.setLlaveSAT(data);
            razonesSociales.setRutaCert("CSD01_AAA010101AAA.cer");
            razonesSociales.setRutaLlave("CSD01_AAA010101AAA.key");
            razonesSociales.setPassword("bqs/BQFE5V7lnyzo7VPN2w==");
            razonesSociales.setUbicacionXML("C:\\Temp");
            razonesSociales.setDescripcionRecibo("Recibo por razon social");
            razonesSociales.setSerie("PR");
            listRazonesSociales.add(razonesSociales);
            
            for (i = 0; i < listRazonesSociales.size(); i++) {
                session.save(listRazonesSociales.get(i));
            }
            //</editor-fold>
            contador = contador + listRazonesSociales.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Periodicidad">
            /**
             * ******************
             * Periodicidad ******************
             */
            periodicidad = new Periodicidad();
            periodicidad.setClave("01");
            periodicidad.setDescripcion("Diario");
            periodicidad.setDias(1L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("02");
            periodicidad.setDescripcion("Semanal");
            periodicidad.setDias(7L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("03");
            periodicidad.setDescripcion("Catorcenal");
            periodicidad.setDias(14L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("04");
            periodicidad.setDescripcion("Quincenal");
            periodicidad.setDias(15L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("05");
            periodicidad.setDescripcion("Mensual");
            periodicidad.setDias(30L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("06");
            periodicidad.setDescripcion("Bimestral");
            periodicidad.setDias(60L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("07");
            periodicidad.setDescripcion("Unidad obra");
            periodicidad.setDias(1L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("08");
            periodicidad.setDescripcion("Comisión");
            periodicidad.setDias(1L);
            listPeriodicidad.add(periodicidad);
            
            periodicidad = new Periodicidad();
            periodicidad.setClave("09");
            periodicidad.setDescripcion("Precio alzado");
            periodicidad.setDias(1L);
            listPeriodicidad.add(periodicidad);

            periodicidad = new Periodicidad();
            periodicidad.setClave("99");
            periodicidad.setDescripcion("Otra Periodicidad");
            periodicidad.setDias(1L);
            listPeriodicidad.add(periodicidad);
            
            for (i = 0; i < listPeriodicidad.size(); i++) {
                session.save(listPeriodicidad.get(i));
            }
            //</editor-fold>
            contador = contador + listPeriodicidad.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Monedas">
            /**
             * ******************
             * Monedas ******************
             */
            monedas = new Monedas();
            monedas.setClave("1");
            monedas.setIdentificador("MXN");
            monedas.setSimbolo("$");
            monedas.setCentimosSingular("Centavo");
            monedas.setCentimosPlural("Centavos");
            monedas.setGeneroCentimos(false);
            monedas.setMonedaSingular("Peso");
            monedas.setMonedaPlural("Pesos");
            monedas.setGeneroMoneda(false);
            monedas.setDecimales(3);
            listMonedas.add(monedas);
            
            monedas = new Monedas();
            monedas.setClave("2");
            monedas.setIdentificador("Dollar");
            monedas.setSimbolo("$");
            monedas.setCentimosSingular("penny");
            monedas.setCentimosPlural("pennys");
            monedas.setGeneroCentimos(false);
            monedas.setMonedaSingular("Dollar");
            monedas.setMonedaPlural("Dollars");
            monedas.setGeneroMoneda(false);
            monedas.setDecimales(3);
            listMonedas.add(monedas);
            
            for (i = 0; i < listMonedas.size(); i++) {
                session.save(listMonedas.get(i));
            }
            //</editor-fold>

            contador = contador + listMonedas.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Base Nomina">
            /**
             * ******************
             * Base Nomina ******************
             */
            baseNomina = new BaseNomina();
            baseNomina.setClave("01");
            baseNomina.setDescripcion("ISR");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            baseNomina = new BaseNomina();
            baseNomina.setClave("02");
            baseNomina.setDescripcion("IMSS");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            baseNomina = new BaseNomina();
            baseNomina.setClave("03");
            baseNomina.setDescripcion("INFONAVIT");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            baseNomina = new BaseNomina();
            baseNomina.setClave("04");
            baseNomina.setDescripcion("PTU");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            baseNomina = new BaseNomina();
            baseNomina.setClave("05");
            baseNomina.setDescripcion("ISN");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            baseNomina = new BaseNomina();
            baseNomina.setClave("06");
            baseNomina.setDescripcion("Despensa");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            baseNomina = new BaseNomina();
            baseNomina.setClave("07");
            baseNomina.setDescripcion("Fondo Ahorro");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            baseNomina = new BaseNomina();
            baseNomina.setClave("08");
            baseNomina.setDescripcion("Aguinaldo");
            baseNomina.setReservado(true);
            listBaseNomina.add(baseNomina);
            
            for (i = 0; i < listBaseNomina.size(); i++) {
                session.save(listBaseNomina.get(i));
            }
            //</editor-fold>
            contador = contador + listBaseNomina.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Tipos Corrida">
            /**
             * ******************
             * Tipos Corrida ******************
             */
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("PER");
            tipoCorrida.setDescripcion("Periodica");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 1);
            tipoCorrida.setTipoDeCalculo((short) 1);
            tipoCorrida.setUsaCorrPeriodica(true);
            tipoCorrida.setMostrarMenuCalc(true);
            listTipoCorrida.add(tipoCorrida);
            
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("VAC");
            tipoCorrida.setDescripcion("Vacaciones");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 2);
            tipoCorrida.setTipoDeCalculo((short) 6);
            tipoCorrida.setUsaCorrPeriodica(true);
            tipoCorrida.setMostrarMenuCalc(true);
            listTipoCorrida.add(tipoCorrida);
            
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("AGI");
            tipoCorrida.setDescripcion("Aguinaldo");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 3);
            tipoCorrida.setTipoDeCalculo((short) 3);
            tipoCorrida.setUsaCorrPeriodica(false);
            tipoCorrida.setMostrarMenuCalc(true);
            listTipoCorrida.add(tipoCorrida);
            
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("FDA");
            tipoCorrida.setDescripcion("Fondo de Ahorro");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 4);
            tipoCorrida.setTipoDeCalculo((short) 7);
            tipoCorrida.setUsaCorrPeriodica(false);
            tipoCorrida.setMostrarMenuCalc(true);
            listTipoCorrida.add(tipoCorrida);
            
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("FIN");
            tipoCorrida.setDescripcion("Finiquito");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 5);
            tipoCorrida.setTipoDeCalculo((short) 4);
            tipoCorrida.setUsaCorrPeriodica(true);
            tipoCorrida.setMostrarMenuCalc(false);
            listTipoCorrida.add(tipoCorrida);
            
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("LIQ");
            tipoCorrida.setDescripcion("Liquidaciones");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 6);
            tipoCorrida.setTipoDeCalculo((short) 5);
            tipoCorrida.setUsaCorrPeriodica(true);
            tipoCorrida.setMostrarMenuCalc(false);
            listTipoCorrida.add(tipoCorrida);
            
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("PTU");
            tipoCorrida.setDescripcion("PTU");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 7);
            tipoCorrida.setTipoDeCalculo((short) 2);
            tipoCorrida.setUsaCorrPeriodica(false);
            tipoCorrida.setMostrarMenuCalc(false);
            listTipoCorrida.add(tipoCorrida);
            
            tipoCorrida = new TipoCorrida();
            tipoCorrida.setClave("ASI");
            tipoCorrida.setDescripcion("Asimilados");
            tipoCorrida.setSistema(true);
            tipoCorrida.setOrden((short) 8);
            tipoCorrida.setTipoDeCalculo((short) 8);
            tipoCorrida.setUsaCorrPeriodica(false);
            tipoCorrida.setMostrarMenuCalc(true);
            listTipoCorrida.add(tipoCorrida);
            
            for (i = 0; i < listTipoCorrida.size(); i++) {
                session.save(listTipoCorrida.get(i));
            }
            //</editor-fold>
            contador = contador + listTipoCorrida.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="DatosDisponiblesCxnConta">
            /**
             * ******************
             * DatosDisponiblesCxnConta ******************
             */
            datosDisponiblesCxnConta = new DatosDisponiblesCxnConta();
            datosDisponiblesCxnConta.setClave("001");
            datosDisponiblesCxnConta.setDescripcion("Neto a pagar");
            listDatosDisponibleCxnConta.add(datosDisponiblesCxnConta);
            
            datosDisponiblesCxnConta = new DatosDisponiblesCxnConta();
            datosDisponiblesCxnConta.setClave("002");
            datosDisponiblesCxnConta.setDescripcion("Despensa");
            listDatosDisponibleCxnConta.add(datosDisponiblesCxnConta);
            
            datosDisponiblesCxnConta = new DatosDisponiblesCxnConta();
            datosDisponiblesCxnConta.setClave("003");
            datosDisponiblesCxnConta.setDescripcion("Neto sin Despensa");
            listDatosDisponibleCxnConta.add(datosDisponiblesCxnConta);
            
            datosDisponiblesCxnConta = new DatosDisponiblesCxnConta();
            datosDisponiblesCxnConta.setClave("004");
            datosDisponiblesCxnConta.setDescripcion("Conceptos");
            listDatosDisponibleCxnConta.add(datosDisponiblesCxnConta);
            
            datosDisponiblesCxnConta = new DatosDisponiblesCxnConta();
            datosDisponiblesCxnConta.setClave("005");
            datosDisponiblesCxnConta.setDescripcion("Percepciones");
            listDatosDisponibleCxnConta.add(datosDisponiblesCxnConta);
            
            datosDisponiblesCxnConta = new DatosDisponiblesCxnConta();
            datosDisponiblesCxnConta.setClave("006");
            datosDisponiblesCxnConta.setDescripcion("Deducciones");
            listDatosDisponibleCxnConta.add(datosDisponiblesCxnConta);
            
            datosDisponiblesCxnConta = new DatosDisponiblesCxnConta();
            datosDisponiblesCxnConta.setClave("007");
            datosDisponiblesCxnConta.setDescripcion("Conceptos Nomina");
            listDatosDisponibleCxnConta.add(datosDisponiblesCxnConta);
            
            for (int j = 0; j < listDatosDisponibleCxnConta.size(); j++) {
                session.save(listDatosDisponibleCxnConta.get(j));
            }
            //</editor-fold>

            contador = contador + listDatosDisponibleCxnConta.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Campos DIM">
            /**
             * ******************
             * Campos DIM ******************
             */
            campoDIM = new CampoDIM();
            campoDIM.setClave("033");
            campoDIM.setDescripcion("Ingresos totales por pago en parcialidades");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("034");
            campoDIM.setDescripcion("Monto diario percibido por jubilaciones, pensiones o haberes de retiro en parcialidades");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("035");
            campoDIM.setDescripcion("Cantidad que se hubiera percibido en el periodo de no haber pago único por jubilaciones, pensiones o haberes de retiro en una sola exhibición");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("036");
            campoDIM.setDescripcion("Monto total del pago en una sola exhibición");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("037");
            campoDIM.setDescripcion("Número de días");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("038");
            campoDIM.setDescripcion("Ingresos exentos");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("039");
            campoDIM.setDescripcion("Ingresos gravables");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("040");
            campoDIM.setDescripcion("Ingresos acumulables");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("041");
            campoDIM.setDescripcion("Ingresos no acumulables");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("042");
            campoDIM.setDescripcion("Impuesto retenido");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("043");
            campoDIM.setDescripcion("Monto total pagado de otros pagos por separación");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("044");
            campoDIM.setDescripcion("Número de años de servicio del trabajador");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("045");
            campoDIM.setDescripcion("Ingresos exentos");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("046");
            campoDIM.setDescripcion("Ingresos gravados");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("047");
            campoDIM.setDescripcion("Ingresos acumulables (último sueldo mensual ordinario)");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("048");
            campoDIM.setDescripcion("Impuesto correspondiente al último sueldo mensual ordinario");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("049");
            campoDIM.setDescripcion("Ingresos no acumulables");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("050");
            campoDIM.setDescripcion("Impuesto retenido");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("051");
            campoDIM.setDescripcion("Ingresos asimilados a salarios");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("052");
            campoDIM.setDescripcion("Impuesto retenido durante el ejercicio");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("053");
            campoDIM.setDescripcion("Indique si ejerció la opción otorgada por el empleador para adquirir acciones o títulos valor");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("054");
            campoDIM.setDescripcion("Valor de mercado de las acciones o títulos valor al ejercer la opción");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("055");
            campoDIM.setDescripcion("Precio establecido al otorgarse la opción de ingresos en acciones o títulos valor");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("056");
            campoDIM.setDescripcion("Ingreso Acumulable");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("057");
            campoDIM.setDescripcion("Impuesto retenido durante el ejercicio");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("058");
            campoDIM.setDescripcion("Sueldos, salarios, rayas y jornales gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("059");
            campoDIM.setDescripcion("Sueldos, salarios, rayas y jornales exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("060");
            campoDIM.setDescripcion("Gratificación anual gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("061");
            campoDIM.setDescripcion("Gratificación anual exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("062");
            campoDIM.setDescripcion("Viáticos y gastos de viaje gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("063");
            campoDIM.setDescripcion("Viáticos y gastos de viaje exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("064");
            campoDIM.setDescripcion("Tiempo extraordinario gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("065");
            campoDIM.setDescripcion("Tiempo extraordinario exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("066");
            campoDIM.setDescripcion("Prima vacacional gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("067");
            campoDIM.setDescripcion("Prima vacacional exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("068");
            campoDIM.setDescripcion("Prima dominical gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("069");
            campoDIM.setDescripcion("Prima dominical exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("070");
            campoDIM.setDescripcion("Participación de los trabajadores en las utilidades (PTU) gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("071");
            campoDIM.setDescripcion("Participación de los trabajadores en las utilidades (PTU) exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("072");
            campoDIM.setDescripcion("Reembolso de gastos médicos, dentales y hospitalarios gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("073");
            campoDIM.setDescripcion("Reembolso de gastos médicos, dentales y hospitalarios exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("074");
            campoDIM.setDescripcion("Fondo de ahorro gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("075");
            campoDIM.setDescripcion("Fondo de ahorro exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("076");
            campoDIM.setDescripcion("Caja de ahorro gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("077");
            campoDIM.setDescripcion("Caja de ahorro exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("078");
            campoDIM.setDescripcion("Vales para despensa gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("079");
            campoDIM.setDescripcion("Vales para despensa exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("080");
            campoDIM.setDescripcion("Ayuda para gastos de funeral gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("081");
            campoDIM.setDescripcion("Ayuda para gastos de funeral exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("082");
            campoDIM.setDescripcion("Contribuciones a cargo del trabajador pagadas por el patrón gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("083");
            campoDIM.setDescripcion("Contribuciones a cargo del trabajador pagadas por el patrón exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("084");
            campoDIM.setDescripcion("Premios por puntualidad gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("085");
            campoDIM.setDescripcion("Premios por puntualidad exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("086");
            campoDIM.setDescripcion("Prima de seguro de vida gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("087");
            campoDIM.setDescripcion("Prima de seguro de vida exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("088");
            campoDIM.setDescripcion("Seguro de gastos médicos mayores gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("089");
            campoDIM.setDescripcion("Seguro de gastos médicos mayores exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("090");
            campoDIM.setDescripcion("Vales para restaurante gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("091");
            campoDIM.setDescripcion("Vales para restaurante exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("092");
            campoDIM.setDescripcion("Vales para gasolina gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("093");
            campoDIM.setDescripcion("Vales para gasolina exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("094");
            campoDIM.setDescripcion("Vales para ropa gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("095");
            campoDIM.setDescripcion("Vales para ropa exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("096");
            campoDIM.setDescripcion("Ayuda para renta gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("097");
            campoDIM.setDescripcion("Ayuda para renta exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("098");
            campoDIM.setDescripcion("Ayuda para artículos escolares gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("099");
            campoDIM.setDescripcion("Ayuda para artículos escolares exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("100");
            campoDIM.setDescripcion("Dotación o ayuda para anteojos gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("101");
            campoDIM.setDescripcion("Dotación o ayuda para anteojos exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("102");
            campoDIM.setDescripcion("Ayuda para transporte gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("103");
            campoDIM.setDescripcion("Ayuda para transporte exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("104");
            campoDIM.setDescripcion("Cuotas sindicales pagadas por el patrón gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("105");
            campoDIM.setDescripcion("Cuotas sindicales pagadas por el patrón exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("106");
            campoDIM.setDescripcion("Subsidios por incapacidad gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("107");
            campoDIM.setDescripcion("Subsidios por incapacidad exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("108");
            campoDIM.setDescripcion("Becas para trabajadores y/o sus hijos gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("109");
            campoDIM.setDescripcion("Becas para trabajadores y/o sus hijos exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("110");
            campoDIM.setDescripcion("Pagos efectuados por otros empleadores (sólo si el patrón que declara realizó cálculo anual) gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("111");
            campoDIM.setDescripcion("Pagos efectuados por otros empleadores (sólo si el patrón que declara realizó cálculo anual) exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("112");
            campoDIM.setDescripcion("Otros ingresos por salarios gravado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("113");
            campoDIM.setDescripcion("Otros ingresos por salarios exento");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("114");
            campoDIM.setDescripcion("Suma del ingreso GRAVADO por sueldos y salarios");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("115");
            campoDIM.setDescripcion("Suma del ingreso EXENTO por sueldos y salarios");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("116");
            campoDIM.setDescripcion("Impuesto retenido durante el ejercicio que declara");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("117");
            campoDIM.setDescripcion("Impuesto retenido por otro(s) patrón(es) durante el ejercicio que declara");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("118");
            campoDIM.setDescripcion("Saldo a favor determinado en el ejercicio que declara, que el patrón compensará durante el siguiente ejercicio o solicitará su devolución");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("119");
            campoDIM.setDescripcion("Saldo a favor del ejercicio anterior no compensado durante el ejercicio que declara");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("120");
            campoDIM.setDescripcion("Suma de las cantidades que por concepto de crédito al salario le correspondió al trabajador");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("121");
            campoDIM.setDescripcion("Crédito al salario entregado en efectivo al trabajador durante el ejercicio que declara");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("122");
            campoDIM.setDescripcion("Monto total de ingresos obtenidos por concepto de prestaciones de previsión social");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("123");
            campoDIM.setDescripcion("Suma de ingresos exentos por concepto de prestaciones de previsión social");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("124");
            campoDIM.setDescripcion("Suma de ingresos por sueldos y salarios");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("125");
            campoDIM.setDescripcion("Monto del impuesto local a los ingresos por sueldos, salarios y en general por la prestación de un servicio personal subordinado retenido");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("126");
            campoDIM.setDescripcion("Monto del subsidio para el empleo entregado en efectivo al trabajador durante el ejercicio que declara");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("127");
            campoDIM.setDescripcion("Total de las aportaciones voluntarias deducibles");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("128");
            campoDIM.setDescripcion("ISR conforme a la tarifa anual");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("129");
            campoDIM.setDescripcion("Subsidio acreditable");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("130");
            campoDIM.setDescripcion("Subsidio no acreditable");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("131");
            campoDIM.setDescripcion("Impuesto sobre ingresos acumulables");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("132");
            campoDIM.setDescripcion("Impuesto sobre ingresos no acumulables");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("133");
            campoDIM.setDescripcion("Impuesto local a los ingresos por sueldos, salarios y en general por la prestación de un servicio personal subordinado");
            listCampoDIM.add(campoDIM);
            
            campoDIM = new CampoDIM();
            campoDIM.setClave("134");
            campoDIM.setDescripcion("Monto del subsidio para el empleo que le correspondió al trabajador durante el ejercicio");
            listCampoDIM.add(campoDIM);
            
            for (i = 0; i < listCampoDIM.size(); i++) {
                session.save(listCampoDIM.get(i));
            }
            //</editor-fold>
            contador = contador + listCampoDIM.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Parametros de los conceptos">
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("HORASEXTRASDOBLES");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("##.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("HORAS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("HORASTIEMPOEXTRAS");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("##.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("HORAS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("TIEMPOEXTRADIADESCANSO");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("##.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("HORAS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("TIEMPOEXTRAFESTIVO");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("##.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("HORAS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("TIEMPOEXTRADOMINGO");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("##.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("HORAS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("INCENTIVO");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("####.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("OTROS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("PREVISIONSOCIAL");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("####.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("OTROS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("BECAS");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("####.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("OTROS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("DEVOLUCION");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("####.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("OTROS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            paraConcepDeNom = new ParaConcepDeNom();
            paraConcepDeNom.setDescripcion("OTRASPERCEPCIONES");
            paraConcepDeNom.setClasificadorParametro(ClasificadorParametro.ENTRADA);
            paraConcepDeNom.setMascara("####.##");
            paraConcepDeNom.setNumero(1);
            paraConcepDeNom.setTipo("INTEGER");
            paraConcepDeNom.setUnidad("OTROS");
            listParaConcepDeNom.add(paraConcepDeNom);
            
            for (i = 0; i < listParaConcepDeNom.size(); i++) {
                session.save(listParaConcepDeNom.get(i));
            }

            //</editor-fold>//JSA01
            contador = contador + listParaConcepDeNom.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="ConceptoDeNominaDefinicion">
            Calendar calendar = Calendar.getInstance();
            Calendar s = Calendar.getInstance();
            s.set(Calendar.HOUR, 12);
            s.set(Calendar.MINUTE, 0);
            s.set(Calendar.SECOND, 0);
            calendar.set(2012, 1, 12);
            java.util.Date dateAlta = calendar.getTime();
            List<TipoCorrida> listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            List<ParaConcepDeNom> listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
            
            ConceptoPorTipoCorrida ctc = new ConceptoPorTipoCorrida();
            ctc.setTipoCorrida(listTipoCorrida.get(0));

            //PERCEPCIONES 001-599
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("001");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("SUELDO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("SUELDO");
            conceptoDeNominaDefinicion.setFormulaConcepto("SUELDODIARIOFINAL*CONCEP_901");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 001");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(1);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("002");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("COMISIONES");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("COMISIONES");
            conceptoDeNominaDefinicion.setFormulaConcepto("SALARIOMIN*DIASPAGO*10.0");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 008");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(2);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);            
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("100");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("TIEMPO EXTRA DOBLE");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("TIEMPOEXTDBLE");
            conceptoDeNominaDefinicion.setFormulaConcepto("(SUELDODIARIOFINAL/HRSTURNO)*HorasExtrasDobles*2");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 002");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(3);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
//            listParaConcepNomina = null;
//            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
//            listParaConcepNomina.add(listParaConcepDeNom.get(0));
//            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            for (int j = 0; j < conceptoDeNominaDefinicion.getBaseAfecConcepNomi().size(); j++) {
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setFormulaExenta("IF (((SUELDODIARIOFINAL/HRSTURNO)*HorasExtrasDobles)/2)<=(SalarioMin*10.7) THEN (((SUELDODIARIOFINAL/HRSTURNO)*HorasExtrasDobles)/2) ELSE (SalarioMin*10.7)");
            }
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("101");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("TIEMPO EXTRA TRIPLE");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("TIEMPOEXTTPLE");
            conceptoDeNominaDefinicion.setFormulaConcepto("(SUELDODIARIOFINAL/HRSTURNO)*HorasExtrasTriples*3");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 002");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(4);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
//            listParaConcepNomina = null;
//            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
//            listParaConcepNomina.add(listParaConcepDeNom.get(1));
//            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("102");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("TIEMPO EXTRA DIA DE DESCANSO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("TIEMPOEXTDESCAN");
            conceptoDeNominaDefinicion.setFormulaConcepto("(SUELDODIARIOFINAL/HRSTURNO)*TExtrasDiaDescanso*2");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 002");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(5);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
//            listParaConcepNomina = null;
//            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
//            listParaConcepNomina.add(listParaConcepDeNom.get(2));
//            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("103");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("TIEMPO EXTRA DIA FESTIVO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("TIEMPOEXTFESTI");
            conceptoDeNominaDefinicion.setFormulaConcepto("(SUELDODIARIOFINAL/HRSTURNO)*TExtrasDiaFestivo*2");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 002");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(6);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
//            listParaConcepNomina = null;
//            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
//            listParaConcepNomina.add(listParaConcepDeNom.get(3));
//            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("104");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("PRIMA DOMINICAL");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("PRIMADOMINI");
            conceptoDeNominaDefinicion.setFormulaConcepto("(SUELDODIARIOFINAL/HRSTURNO)*TExtrasDiaDomingo*0.25");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 003");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(7);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
//            listParaConcepNomina = null;
//            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
//            listParaConcepNomina.add(listParaConcepDeNom.get(3));
//            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            for (int j = 0; j < conceptoDeNominaDefinicion.getBaseAfecConcepNomi().size(); j++) {
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setFormulaExenta("SalarioMin*(TExtrasDiaDomingo/HrsTurno)");
            }
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("200");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("VACACIONES");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("VACACIONES");
            conceptoDeNominaDefinicion.setFormulaConcepto("IF CONCEP_902>0 THEN (CONCEP_902)*SUELDODIARIOFINAL ELSE 0");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 005");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(8);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(1));
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("201");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("PRIMA VACACIONAL");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("PRIMA");
            conceptoDeNominaDefinicion.setFormulaConcepto("(SUELDODIARIOFINAL*CONCEP_902)*CONCEP_903");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 005");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(9);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            for (int j = 0; j < conceptoDeNominaDefinicion.getBaseAfecConcepNomi().size(); j++) {
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setFormulaExenta("SalarioMin*15");
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setTipoAfecta(2);
            }
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("210");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("AGUINALDO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("AGUINALDO");
            conceptoDeNominaDefinicion.setFormulaConcepto("SUELDODIARIOFINAL*FACTORDIASAGUINALDO");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 006");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(10);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(2));
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            for (int j = 0; j < conceptoDeNominaDefinicion.getBaseAfecConcepNomi().size(); j++) {
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setFormulaExenta("SalarioMin*30");
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setTipoAfecta(2);
            }
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("220");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("REPARTO DE UTILIDADES");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("REPARTOUTILIDADES");
            conceptoDeNominaDefinicion.setFormulaConcepto("");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 003");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(11);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(6));
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            for (int j = 0; j < conceptoDeNominaDefinicion.getBaseAfecConcepNomi().size(); j++) {
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setFormulaExenta("SalarioMin*30");
                conceptoDeNominaDefinicion.getBaseAfecConcepNomi().get(j).setTipoAfecta(2);
            }
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("300");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("PREMIO POR PUNTUALIDAD");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("PREMIOPUNTUA");
            conceptoDeNominaDefinicion.setFormulaConcepto("IF (RETARDOS+FALTAS+INCAPACIDADENFERMEDAD+INCAPACIDADACCIDENTE+PERMISOSINSUELDO+PERMISOCONSUELDO)=0.0 THEN CONCEP_001*0.03");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 003");
            conceptoDeNominaDefinicion.setTipo(Tipo.REPETITIVO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(12);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("301");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("INCENTIVOS");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("INCENTIVOS");
            conceptoDeNominaDefinicion.setFormulaConcepto("Param1");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 003");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(13);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listParaConcepNomina = null;
            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
            listParaConcepNomina.add(listParaConcepDeNom.get(5));
            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("400");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("PREVISION SOCIAL");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("PREVISION");
            conceptoDeNominaDefinicion.setFormulaConcepto("Param1");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 010");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(14);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listParaConcepNomina = null;
            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
            listParaConcepNomina.add(listParaConcepDeNom.get(6));
            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("401");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("BECAS");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("BECAS");
            conceptoDeNominaDefinicion.setFormulaConcepto("Param1");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 010");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(15);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listParaConcepNomina = null;
            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
            listParaConcepNomina.add(listParaConcepDeNom.get(7));
            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("500");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("APORTACION PATRONAL F. AHORRO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("APORTACION");
            conceptoDeNominaDefinicion.setFormulaConcepto("CONCEP_001*0.01");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 010");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(16);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("550");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("DEVOLUCION DEL F. DE AHORRO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("DEVOLUCION");
            conceptoDeNominaDefinicion.setFormulaConcepto("Param1");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta(null);
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(17);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listParaConcepNomina = null;
            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
            listParaConcepNomina.add(listParaConcepDeNom.get(8));
            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("590");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("OTRAS PERCEPCIONES");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("OTRASPERCEP");
            conceptoDeNominaDefinicion.setFormulaConcepto("Param1");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 009");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(18);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listParaConcepNomina = null;
            listParaConcepNomina = new ArrayList<ParaConcepDeNom>();
            listParaConcepNomina.add(listParaConcepDeNom.get(9));
            conceptoDeNominaDefinicion.setParametroConceptosDeNominas(listParaConcepNomina);
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("598");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("SUBSIDIO AL EMPLEO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("SUBSIDIO");
            conceptoDeNominaDefinicion.setFormulaConcepto("ISRSubsidio");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("1125 999");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(599);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("599");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("PRIMA DE AJUSTE POR REDONDE");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("PRIMAAJUSTEREDONDEO");
            conceptoDeNominaDefinicion.setFormulaConcepto("");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 008");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(20);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);

            //DEDUCCINES 600-900
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("600");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("RETARDO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("RETARDO");
            conceptoDeNominaDefinicion.setFormulaConcepto("IF DiasJornada=6 THEN (SueldoDiarioFinal/HrsTurno)*Retardos*1.1666 ELSE (SueldoDiarioFinal/HrsTurno)*Retardos*1.4");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 001");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(600);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            conceptoDeNominaDefinicion.setBaseAfecConcepNomi(buscarBaseAfecta(listBaseNomina, conceptoDeNominaDefinicion));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("610");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("FALTAS INJUSTIFICADAS");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("FALTASINJUSTI");
            conceptoDeNominaDefinicion.setFormulaConcepto("");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 001");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(601);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("620");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("INCAPACIDADES POR ENFERMEDAD");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("INCAP X ENFERM");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 001");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(602);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("630");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("INCAPACIDADES POR ACCIDENTE");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("INCAP X ACCIDEN");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 001");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(603);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("635");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("INCAPACIDAD POR MATERNIDAD");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("INCAP X MATER");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(604);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("640");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("PERMISOS SIN SUELDO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("PERMISOS");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setSubCuenta("511D 001");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(605);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("700");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("CUOTA SINDICAL");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("CUOTASINDICAL");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(606);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("750");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("CREDITO INFONAVIT");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("CRED. INFO.");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(750);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("755");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("TASA CREDITO INFONAVIT");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("TASA CRED. INFO.");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(755);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("760");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("PRESTAMOS PERSONALES");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("PREST. PERS.");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.REPETITIVO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(760);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("850");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("FONDO DE AHORRO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("FONDOAHORRO");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setFormulaConcepto("(TotalPercepcionesPeriodo-Concep_598)*0.14");
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(997);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("890");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("OTRAS DEDUCCIONES");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("OTRASDEDUCCIONES");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(608);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("801");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("IMSS");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("IMSS");
            conceptoDeNominaDefinicion.setFormulaConcepto("CalculoIMSS");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setSubCuenta("2130 002");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(801);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("800");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("ISR");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("ISR");
            conceptoDeNominaDefinicion.setFormulaConcepto("CalculoISR");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setSubCuenta("2130 001");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(900);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(1));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(2));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(4));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(5));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(6));
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("900");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("AJUSTE POR REDONDEO");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("AJUSTEREDONDEO");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DEDUCCION);
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(609);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);

            //901-?
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("901");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("DIAS TRABAJADOS");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("DIAS TRAB.");
            conceptoDeNominaDefinicion.setFormulaConcepto("DiasPago");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DATO);
            conceptoDeNominaDefinicion.setSubCuenta("511D 102");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(901);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(1));
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("902");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("DIAS VACACIONES");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("DIAS VAC.");
            conceptoDeNominaDefinicion.setFormulaConcepto("DiasVacaciones");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DATO);
            conceptoDeNominaDefinicion.setSubCuenta("511D 102");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(902);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(1));
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("903");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("DIAS PRIMA VACACIONAL");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("DIAS PRIMA VAC.");
            conceptoDeNominaDefinicion.setFormulaConcepto("diasPrima");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.DATO);
            conceptoDeNominaDefinicion.setSubCuenta("511D 102");
            conceptoDeNominaDefinicion.setTipo(Tipo.AUTOMATICO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(903);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(1));
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);

            //CONCEPTOS ESPECIALES DEDUCCION 
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("950");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("IMSS PATRONAL");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("IMSSPATRONAL");
            conceptoDeNominaDefinicion.setFormulaConcepto("CalculoIMSSPatronal");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.INFORMATIVO);
            conceptoDeNominaDefinicion.setSubCuenta("511D 101");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(950);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("951");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("5% INFONAVIT");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("5%INFONAVIT");
            conceptoDeNominaDefinicion.setFormulaConcepto("BASEINFONAVIT*0.05");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.INFORMATIVO);
            conceptoDeNominaDefinicion.setSubCuenta("511D 102");
            conceptoDeNominaDefinicion.setTipo(Tipo.REPETITIVO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(951);
            listTipoCorrida_Concept_Nomi = null;
            listTipoCorrida_Concept_Nomi = new ArrayList<TipoCorrida>();
            listTipoCorrida_Concept_Nomi.add(listTipoCorrida.get(0));
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("952");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("2% SAR");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("2%SAR");
            conceptoDeNominaDefinicion.setFormulaConcepto("BASESAR*0.02");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.INFORMATIVO);
            conceptoDeNominaDefinicion.setSubCuenta("511D 103");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(952);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            
            conceptoDeNominaDefinicion = new ConcepNomDefi();
            conceptoDeNominaDefinicion.setClave("953");
            conceptoDeNominaDefinicion.setFecha(dateAlta);
            conceptoDeNominaDefinicion.setComportamiento(null);
            conceptoDeNominaDefinicion.setDescripcion("2% ESTATAL");
            conceptoDeNominaDefinicion.setDescripcionAbreviada("2%ESTATAL");
            conceptoDeNominaDefinicion.setFormulaConcepto("TOTALPERCEPCIONES*0.02");
            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.INFORMATIVO);
            conceptoDeNominaDefinicion.setSubCuenta("511D 104");
            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
            conceptoDeNominaDefinicion.setActivado(true);
            conceptoDeNominaDefinicion.setPrioridadDeCalculo(953);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);

//            conceptoDeNominaDefinicion = new ConcepNomDefi();
//            conceptoDeNominaDefinicion.setClave("998");
//            conceptoDeNominaDefinicion.setFecha(dateAlta);
//            conceptoDeNominaDefinicion.setComportamiento(null);
//            conceptoDeNominaDefinicion.setDescripcion("FONDO DE AHORRO");
//            conceptoDeNominaDefinicion.setDescripcionAbreviada("FONDOAHORRO");
//            conceptoDeNominaDefinicion.setImprimirEnListadoNomina(false);
//            conceptoDeNominaDefinicion.setImprimirEnReciboNomina(false);
//            conceptoDeNominaDefinicion.setNaturaleza(Naturaleza.PERCEPCION);
//            conceptoDeNominaDefinicion.setTipo(Tipo.PERIODO);
//            conceptoDeNominaDefinicion.setActivado(true);
//            conceptoDeNominaDefinicion.setFormulaConcepto("(TotalPercepcionesPeriodo-CONCEP_598)*0.07");
//            conceptoDeNominaDefinicion.setPrioridadDeCalculo(998);
            //conceptoDeNominaDefinicion.setTipoCorridas(listTipoCorrida_Concept_Nomi);
            //conceptoDeNominaDefinicion.setConceptoPorTipoCorrida(listConcep_tipCorr(conceptoDeNominaDefinicion, listTipoCorrida_Concept_Nomi));
            //listConceptoDeNominaDefinicion.add(conceptoDeNominaDefinicion);
            int j = 0;
            //<editor-fold defaultstate="collapsed" desc="ConceptoDeNomina">
            for (j = 0; j < listConceptoDeNominaDefinicion.size(); j++) {
                listConceptoDeNominaDefinicion.get(j).setCondicionConcepto("");
                conceptoDeNomina = new ConceptoDeNomina();
                conceptoDeNomina.setClave(listConceptoDeNominaDefinicion.get(j).getClave());
                listConceptoDeNominas.add(conceptoDeNomina);
            }
            
            for (i = 0; i < listConceptoDeNominas.size(); i++) {
                session.save(listConceptoDeNominas.get(i));
            }
            //</editor-fold>
            contador = contador + listConceptoDeNominas.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            for (j = 0; j < listConceptoDeNominas.size(); j++) {
                listConceptoDeNominaDefinicion.get(j).setConceptoDeNomina(listConceptoDeNominas.get(j));
            }
            for (i = 0; i < listConceptoDeNominaDefinicion.size(); i++) {
                session.save(listConceptoDeNominaDefinicion.get(i));
            }
            //</editor-fold>//JSA01
            contador = contador + listConceptoDeNominaDefinicion.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Incidencias">
            /**
             * ******************
             * Incidencias ******************
             */
            incidencias = new Incidencias();
            incidencias.setDescripcion("Faltas injustificadas");
            incidencias.setDescontar(true);
            listIncidencias.add(incidencias);
            
            incidencias = new Incidencias();
            incidencias.setDescripcion("Permisos sin goce de sueldo");
            incidencias.setDescontar(true);
            listIncidencias.add(incidencias);
            
            incidencias = new Incidencias();
            incidencias.setDescripcion("Suspensión");
            incidencias.setDescontar(true);
            listIncidencias.add(incidencias);
            
            incidencias = new Incidencias();
            incidencias.setDescripcion("Incapacidades por Enfermedad General");
            incidencias.setDescontar(true);
            listIncidencias.add(incidencias);
            
            incidencias = new Incidencias();
            incidencias.setDescripcion("Incapacidades por Maternidad");
            incidencias.setDescontar(true);
            listIncidencias.add(incidencias);
            
            incidencias = new Incidencias();
            incidencias.setDescripcion("Incapacidades por Riesgo de Trabajo");
            incidencias.setDescontar(true);
            listIncidencias.add(incidencias);
            
            for (i = 0; i < listIncidencias.size(); i++) {
                session.save(listIncidencias.get(i));
            }
            //</editor-fold>
            contador = contador + listIncidencias.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="ConfiguraMovimientos">
//////            /**
//////             * ******************
//////             * CONFIGURAMOVIMIENTO CON RAZON SOCIAL ******************
//////             */
//////            
//////            configuraMovimiento = new ConfiguraMovimiento();
//////            configuraMovimiento.setNombre("Movtos. masivos por concepto");
//////            configuraMovimiento.setFiltro("Concepto");
//////            configuraMovimiento.setMovimiento("Empleado");
//////            configuraMovimiento.setMovimientoExistente(true);
//////            //Empleado,Concepto,Centro de costos
//////            configuraMovimiento.setActivadosFiltro("0,1,0"); //valores en true 1, 0 false
//////            //Empleado,Concepto,Centro de costos,Plazas,FechaInicio,FechaCierre
//////            configuraMovimiento.setActivadosMovimientos("1,0,0,0,0,0"); //valores en true 1, 0 false
//////            configuraMovimiento.setOrdenId(1);
//////            configuraMovimiento.setRazonesSociales(listRazonesSociales.get(0));
//////            listConfiguraMovimiento.add(configuraMovimiento);
//////
//////            configuraMovimiento = new ConfiguraMovimiento();
//////            configuraMovimiento.setNombre("Movtos. masivos por empleado");
//////            configuraMovimiento.setFiltro("Empleado");
//////            configuraMovimiento.setMovimiento("Concepto");
//////            configuraMovimiento.setMovimientoExistente(true);
//////            configuraMovimiento.setActivadosFiltro("1,0,0");
//////            configuraMovimiento.setActivadosMovimientos("0,1,0,0,0,0");
//////            configuraMovimiento.setOrdenId(2);
//////            configuraMovimiento.setRazonesSociales(listRazonesSociales.get(0));
//////            listConfiguraMovimiento.add(configuraMovimiento);
            /**
             * ******************
             * CONFIGURAMOVIMIENTO SIN RAZON SOCIAL GLOBALES******************
             */
            configuraMovimiento = new ConfiguraMovimiento();
            configuraMovimiento.setNombre("Movtos. masivos por concepto");
            configuraMovimiento.setFiltro("Concepto");
            configuraMovimiento.setMovimiento("Empleado");
            configuraMovimiento.setMovimientoExistente(true);
            //Empleado,Concepto,Centro de costos
            configuraMovimiento.setActivadosFiltro("0,1,0"); //valores en true 1, 0 false
            //Empleado,Concepto,Centro de costos,Plazas,FechaInicio,FechaCierre
            configuraMovimiento.setActivadosMovimientos("1,0,0,0,0,0"); //valores en true 1, 0 false
            configuraMovimiento.setOrdenId(1);
            configuraMovimiento.setSistema(true);
            configuraMovimiento.setRazonesSociales(null);
            listConfiguraMovimiento.add(configuraMovimiento);
            
            configuraMovimiento = new ConfiguraMovimiento();
            configuraMovimiento.setNombre("Movtos. masivos por empleado");
            configuraMovimiento.setFiltro("Empleado");
            configuraMovimiento.setMovimiento("Concepto");
            configuraMovimiento.setMovimientoExistente(true);
            configuraMovimiento.setActivadosFiltro("1,0,0");
            configuraMovimiento.setActivadosMovimientos("0,1,0,0,0,0");
            configuraMovimiento.setOrdenId(2);
            configuraMovimiento.setSistema(true);
            configuraMovimiento.setRazonesSociales(null);
            listConfiguraMovimiento.add(configuraMovimiento);
            
            for (i = 0; i < listConfiguraMovimiento.size(); i++) {
                session.save(listConfiguraMovimiento.get(i));
            }
//            //</editor-fold>
            contador = contador + listConfiguraMovimiento.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Excepciones">
            /**
             * ******************
             * Excepciones ******************
             */
            excepciones = new Excepciones();
            excepciones.setClave("0");
            excepciones.setOrden(0);
            excepciones.setExcepcion("Laborado");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("1");
            excepciones.setOrden(1);
            excepciones.setExcepcion("Retardo");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.HORASMINUTOS);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("2");
            excepciones.setOrden(2);
            excepciones.setExcepcion("Falta");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(true);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(21));
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("3");
            excepciones.setOrden(3);
            excepciones.setExcepcion("Ausentismo");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(true);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("4");
            excepciones.setOrden(4);
            excepciones.setExcepcion("Permiso con sueldo");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(false);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("5");
            excepciones.setOrden(5);
            excepciones.setExcepcion("Permiso sin sueldo ");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(false);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("6");
            excepciones.setOrden(6);
            excepciones.setExcepcion("Incapacidad por enfermedad");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(true);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(22));
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("7");
            excepciones.setOrden(7);
            excepciones.setExcepcion("Incapacidad por accidente");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(true);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(23));
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("8");
            excepciones.setOrden(8);
            excepciones.setExcepcion("Incapacidad por maternidad");
            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(true);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(24));
            listExcepciones.add(excepciones);

//            excepciones = new Excepciones();
//            excepciones.setClave("9");
//            excepciones.setOrden(9);
//            excepciones.setExcepcion("Otras incapacidades");
//            excepciones.setNaturaleza(Naturaleza.DEDUCCION);
//            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
//            listExcepciones.add(excepciones);
            excepciones = new Excepciones();
            excepciones.setClave("10");
            excepciones.setOrden(10);
            excepciones.setExcepcion("Descanso laborado");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.HORASMINUTOS);
            excepciones.setUnico(false);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(4));
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("11");
            excepciones.setOrden(11);
            excepciones.setExcepcion("Festivo laborado");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            excepciones.setUnico(false);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(5));
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("12");
            excepciones.setOrden(12);
            excepciones.setExcepcion("Domingo laborado");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.HORASMINUTOS);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("13");
            excepciones.setOrden(13);
            excepciones.setExcepcion("Tiempo extra");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.HORASMINUTOS);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("14");
            excepciones.setOrden(14);
            excepciones.setExcepcion("Extra doble");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.HORASMINUTOS);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(2));
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("15");
            excepciones.setOrden(15);
            excepciones.setExcepcion("Extra triple");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.HORASMINUTOS);
            excepciones.setConcepNomDefi(listConceptoDeNominaDefinicion.get(3));
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("16");
            excepciones.setOrden(16);
            excepciones.setExcepcion("Descanso");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            listExcepciones.add(excepciones);
            
            excepciones = new Excepciones();
            excepciones.setClave("17");
            excepciones.setOrden(17);
            excepciones.setExcepcion("Festivo");
            excepciones.setNaturaleza(Naturaleza.PERCEPCION);
            excepciones.setUnico(false);
            excepciones.setTipoDatoExcepcion(TipoDatoExcepcion.SINDATO);
            listExcepciones.add(excepciones);
            
            for (i = 0; i < listExcepciones.size(); i++) {
                session.save(listExcepciones.get(i));
            }

            //</editor-fold>
            contador = contador + listExcepciones.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="ConfiguracionAsistencias">
            //--With Razon
            listExcepciones.remove(13);//se remueve las excepciones de tiempo extra doble y triples ya que existe la excepcion de tiempo extra.
            listExcepciones.remove(13);

            /**
             * Se quito razon social por que seran ConfiguracionAsistencias
             * globales no se eliminaran ni modificaran se usaran en todas las
             * razones sociales*
             */
            configuraAsistencias = new ConfiguracionAsistencias();
            configuraAsistencias.setActivadosFiltro("1,0,0");
            configuraAsistencias.setFiltro("Empleado");
            configuraAsistencias.setActivadosMovimientos("0,0,1");
            configuraAsistencias.setMovimiento("Excepcion");
            configuraAsistencias.setNombre("Asistencias por empleado");
            configuraAsistencias.setOrdenId(1);
            //configuraAsistencias.setRazonesSociales(listRazonesSociales.get(0));
            configuraAsistencias.setExcepciones(listExcepciones);
            configuraAsistencias.setSistema(true);
            listConfiguraAsistencias.add(configuraAsistencias);
            
            configuraAsistencias = new ConfiguracionAsistencias();
            configuraAsistencias.setActivadosFiltro("0,0,1");
            configuraAsistencias.setFiltro("Excepcion");
            configuraAsistencias.setActivadosMovimientos("1,0,0");
            configuraAsistencias.setMovimiento("Empleado");
            configuraAsistencias.setNombre("Asistencia por excepción");
            configuraAsistencias.setOrdenId(2);
            //configuraAsistencias.setRazonesSociales(listRazonesSociales.get(0));
            configuraAsistencias.setExcepciones(listExcepciones);
            configuraAsistencias.setSistema(true);
            listConfiguraAsistencias.add(configuraAsistencias);
            
            for (i = 0; i < listConfiguraAsistencias.size(); i++) {
                session.save(listConfiguraAsistencias.get(i));
            }

            //</editor-fold>
            contador = contador + listConfiguraAsistencias.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Configuracion IMSS">
            ConfiguracionIMSS configuracionIMSS = new ConfiguracionIMSS();//JSA03
            s.set(Calendar.HOUR, 12);
            s.set(Calendar.MINUTE, 0);
            s.set(Calendar.SECOND, 0);
            configuracionIMSS.setFechaAplica(s.getTime());
            configuracionIMSS.setExcedenteEspecie(3F);
            configuracionIMSS.setCuotaFijaPatron(1F);
            //<editor-fold defaultstate="collapsed" desc="Tasas empleado">
            configuracionIMSS.setTasaEspecieEnfermeMater(0.40F);
            configuracionIMSS.setTasaDineEnfermeMater(0.25F);
            configuracionIMSS.setTasaGastosPension(0.38F);
            configuracionIMSS.setTasaInvalidezVida(0.63F);
            configuracionIMSS.setTasaCesantiaVejez(1.13F);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Tasas Patrona">
            configuracionIMSS.setTasaFijaPatron(1.10F);
            configuracionIMSS.setTasaExcedentePatron(20.40F);
            configuracionIMSS.setTasaGastosPensPatron(1.05F);
            configuracionIMSS.setTasaRiesgosPatron(0.54F);
            configuracionIMSS.setTasaInvaliVidaPatron(1.75F);
            configuracionIMSS.setTasaGuarderiaPatron(1.00F);
            configuracionIMSS.setTasaPrestDinePatron(0.70F);
            configuracionIMSS.setTasaCesanVejezPatron(3.15F);
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="Topes">
            configuracionIMSS.setTopeEnfermedadMaternidad(25F);
            configuracionIMSS.setTopeRiesgoTrabajoGuarderias(25F);
            configuracionIMSS.setTopeCesanVejezInvaliVida(25F);
            configuracionIMSS.setTopeRetiro(25F);
            configuracionIMSS.setTopeInfonavit(25F);
            //</editor-fold>
            configuracionIMSS.setTasaAportacionRetiroPatron(2F);
            configuracionIMSS.setTasaAportacionInfonavitPatron(5F);
            
            session.save(configuracionIMSS);

            //</editor-fold>
            contador = contador + 1;
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Tipo Nomina">
            tipoNomina = new TipoNomina();
            tipoNomina.setClave("0001");
            tipoNomina.setDescripcion("Diario");
            tipoNomina.setPeriodicidad(listPeriodicidad.get(0));
            listTipoNomina.add(tipoNomina);
            
            tipoNomina = new TipoNomina();
            tipoNomina.setClave("0002");
            tipoNomina.setDescripcion("Semanal");
            tipoNomina.setPeriodicidad(listPeriodicidad.get(1));
            listTipoNomina.add(tipoNomina);
            
            tipoNomina = new TipoNomina();
            tipoNomina.setClave("0003");
            tipoNomina.setDescripcion("Decenal");
            tipoNomina.setPeriodicidad(listPeriodicidad.get(9));
            listTipoNomina.add(tipoNomina);
            
            tipoNomina = new TipoNomina();
            tipoNomina.setClave("0004");
            tipoNomina.setDescripcion("Catorcenal");
            tipoNomina.setPeriodicidad(listPeriodicidad.get(2));
            listTipoNomina.add(tipoNomina);
            
            tipoNomina = new TipoNomina();
            tipoNomina.setClave("0005");
            tipoNomina.setDescripcion("Quincenal");
            tipoNomina.setPeriodicidad(listPeriodicidad.get(3));
            tipoNomina.setDetalleConceptoRecibo("Quincenal");
            listTipoNomina.add(tipoNomina);
            
            tipoNomina = new TipoNomina();
            tipoNomina.setClave("0006");
            tipoNomina.setDescripcion("Mensual");
            tipoNomina.setPeriodicidad(listPeriodicidad.get(4));
            listTipoNomina.add(tipoNomina);
            
            tipoNomina = new TipoNomina();
            tipoNomina.setClave("0007");
            tipoNomina.setDescripcion("Anual");
            tipoNomina.setPeriodicidad(listPeriodicidad.get(8));
            listTipoNomina.add(tipoNomina);
            
            for (i = 0; i < listTipoNomina.size(); i++) {
                session.save(listTipoNomina.get(i));
            }
            //</editor-fold>
            contador = contador + listTipoNomina.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Registro Patronal">
            registroPatronal = new RegistroPatronal();
            registroPatronal.setClave("001");
            registroPatronal.setCalle("Conocido");
            registroPatronal.setColonia("Centro");
            registroPatronal.setConvenio(false);
            registroPatronal.setNombreregtpatronal("Empresa pruebas");
            registroPatronal.setNumeroex("55");
            registroPatronal.setNumeroin("56");
            registroPatronal.setRegistropatronal("E4500125555");
            registroPatronal.setTelefono("0008102515");
            registroPatronal.setRiesgoPuesto("1");
            registroPatronal.setCp(listCp.get(0));
            registroPatronal.setCiudades(listCiudades.get(0));
            registroPatronal.setMunicipios(listMunicipios.get(11));
            registroPatronal.setEstados(listEstados.get(24));
            registroPatronal.setPaises(listPaises.get(0));
            registroPatronal.setRazonesSociales(listRazonesSociales.get(0));
            
            listRegistroPatronal.add(registroPatronal);
            for (i = 0; i < listRegistroPatronal.size(); i++) {
                session.save(listRegistroPatronal.get(i));
            }
            //</editor-fold>
            contador = contador + listRegistroPatronal.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Centro de Costos">
            centroDeCosto = new CentroDeCosto();
            centroDeCosto.setClave("001");
            centroDeCosto.setCalle("Conocido 2");
            centroDeCosto.setColonia("Centro");
            centroDeCosto.setDescripcion("Ventas");
            centroDeCosto.setDescripcionPrevia("V1");
            centroDeCosto.setNumeroExterior("15");
            centroDeCosto.setNumeroInterior("14");
            centroDeCosto.setSubCuenta("11125111");
            centroDeCosto.setTelefono("6681521521");
            centroDeCosto.setCp(listCp.get(0));
            centroDeCosto.setCiudades(listCiudades.get(0));
            centroDeCosto.setMunicipios(listMunicipios.get(11));
            centroDeCosto.setEstados(listEstados.get(24));
            centroDeCosto.setPaises(listPaises.get(0));
            centroDeCosto.setRazonesSociales(listRazonesSociales.get(0));
            centroDeCosto.setRegistroPatronal(listRegistroPatronal.get(0));
            listCentroDeCosto.add(centroDeCosto);
            for (i = 0; i < listCentroDeCosto.size(); i++) {
                session.save(listCentroDeCosto.get(i));
            }
            //</editor-fold>
            contador = contador + listCentroDeCosto.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Categoria Puesto">
            categoriasPuestos = new CategoriasPuestos();
            categoriasPuestos.setClave("001");
            categoriasPuestos.setDescripcion("Gerente");
            categoriasPuestos.setEstado(true);
            categoriasPuestos.setPagarPorHoras(false);
            listCategoriaPuestos.add(categoriasPuestos);
            
            for (i = 0; i < listCategoriaPuestos.size(); i++) {
                session.save(listCategoriaPuestos.get(i));
            }
            //</editor-fold>
            contador = contador + listCategoriaPuestos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Puestos">
            puestos = new Puestos();
            puestos.setClave("001");
            puestos.setDescripcion("Gerente");
            puestos.setDescripcionPrevia("G1");
            puestos.setFunciones("Administrar");
            puestos.setMaximo(200);
            puestos.setMinimo(100);
            puestos.setSalarioTabular(100f);
            puestos.setCategoriasPuestos(listCategoriaPuestos.get(0));
            puestos.setRegistroPatronal(listRegistroPatronal.get(0));
            listPuestos.add(puestos);
            for (i = 0; i < listPuestos.size(); i++) {
                session.save(listPuestos.get(i));
            }

            //</editor-fold>
            contador = contador + listPuestos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Departamento">
            departamentos = new Departamentos();
            departamentos.setClave("001");
            departamentos.setDescripcion("Administrativo");
            departamentos.setSubCuenta("111111012");
            departamentos.setRazonesSociales(listRazonesSociales.get(0));
            listDepartamentos.add(departamentos);
            
            for (i = 0; i < listDepartamentos.size(); i++) {
                session.save(listDepartamentos.get(i));
            }
            //</editor-fold>
            contador = contador + listDepartamentos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Horiario">
            horario = new Horario();
            horario.setClave("001");
            horario.setDescripcion("MATUTINO");
            horario.setRazonesSociales(listRazonesSociales.get(0));
            s = Calendar.getInstance();
            s.set(Calendar.HOUR, 8);
            s.set(Calendar.MINUTE, 0);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.AM);
            horario.setHoraEntrada(s.getTime());
            horario.setTiempoComer(2.0);
            s.set(Calendar.HOUR, 1);
            s.set(Calendar.MINUTE, 00);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraInicioComer(s.getTime());
            s.set(Calendar.HOUR, 3);
            s.set(Calendar.MINUTE, 00);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraFinalComer(s.getTime());
            s.set(Calendar.HOUR, 6);
            s.set(Calendar.MINUTE, 30);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraSalida(s.getTime());
            horario.setTiempo1erCoffeBreak(10.0);
            s.set(Calendar.HOUR, 12);
            s.set(Calendar.MINUTE, 00);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraInicio1erCoffeBreak(s.getTime());
            s.set(Calendar.HOUR, 12);
            s.set(Calendar.MINUTE, 10);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraFinal1erCoffeBreak(s.getTime());
            horario.setTiempo2doCoffeBreak(10.0);
            s.set(Calendar.HOUR, 5);
            s.set(Calendar.MINUTE, 30);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraInicio2doCoffeBreak(s.getTime());
            s.set(Calendar.HOUR, 5);
            s.set(Calendar.MINUTE, 40);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraFinal2doCoffeBreak(s.getTime());
            horario.setTopeDiarioHrsExtras(3.0);
            s.set(Calendar.HOUR, 6);
            s.set(Calendar.MINUTE, 31);
            s.set(Calendar.SECOND, 0);
            s.set(Calendar.AM_PM, Calendar.PM);
            horario.setHoraInicioHrsExtra(s.getTime());
            listHorario.add(horario);
            for (i = 0; i < listHorario.size(); i++) {
                session.save(listHorario.get(i));
            }
            //</editor-fold>
            contador = contador + listHorario.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Jornada">
            jornada = new Jornada();
            jornada.setClave("01");
            jornada.setDescripcion("Diurna");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("02");
            jornada.setDescripcion("Nocturna");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("03");
            jornada.setDescripcion("Mixta");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("04");
            jornada.setDescripcion("Por hora");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("05");
            jornada.setDescripcion("Reducida");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("06");
            jornada.setDescripcion("Continuada");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("07");
            jornada.setDescripcion("Partida");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("08");
            jornada.setDescripcion("Por turnos");
            listJornada.add(jornada);
            
            jornada = new Jornada();
            jornada.setClave("09");
            jornada.setDescripcion("Otra Jornada");
            listJornada.add(jornada);
            
            for (i = 0; i < listJornada.size(); i++) {
                session.save(listJornada.get(i));
            }
            //</editor-fold>
            contador = contador + listJornada.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Turnos">
            turnos = new Turnos();
            turnos.setClave("001");
            turnos.setDescripcion("Administrativo");
            turnos.setDiasJornada(6);
            turnos.setHoraJornada((float) 8.0);
            turnos.setTopeHorasDoblesSemanal(9);
            turnos.setJornada(listJornada.get(0));
            turnos.setPrimerDiaSemana(2);
            turnos.setTipoDeJornadaIMSS(1);
            turnos.setTipoDeTurno(1);
            turnos.setRazonesSociales(listRazonesSociales.get(0));
            listTurnos.add(turnos);
            
            for (i = 0; i < listTurnos.size(); i++) {
                session.save(listTurnos.get(i));
            }

            //</editor-fold>
            contador = contador + listTurnos.size();
            
            for (i = 0; i < listTurnos.size(); i++) {
                List<TurnosHorariosFijos> list = new ArrayList<TurnosHorariosFijos>();
                TurnosHorariosFijos turnosHorariosFijos = new TurnosHorariosFijos();
                turnosHorariosFijos.setDiaSemana(DiaSemana.Lunes);
                turnosHorariosFijos.setHorario(listHorario.get(0));
                turnosHorariosFijos.setOrdenDia(2);
                turnosHorariosFijos.setRazonesSociales(listRazonesSociales.get(0));
                turnosHorariosFijos.setTurnos(listTurnos.get(0));
                turnosHorariosFijos.setStatusDia(0);
                list.add(turnosHorariosFijos);
                
                turnosHorariosFijos = new TurnosHorariosFijos();
                turnosHorariosFijos.setDiaSemana(DiaSemana.Martes);
                turnosHorariosFijos.setHorario(listHorario.get(0));
                turnosHorariosFijos.setOrdenDia(3);
                turnosHorariosFijos.setRazonesSociales(listRazonesSociales.get(0));
                turnosHorariosFijos.setTurnos(listTurnos.get(0));
                turnosHorariosFijos.setStatusDia(0);
                list.add(turnosHorariosFijos);
                
                turnosHorariosFijos = new TurnosHorariosFijos();
                turnosHorariosFijos.setDiaSemana(DiaSemana.Miercoles);
                turnosHorariosFijos.setHorario(listHorario.get(0));
                turnosHorariosFijos.setOrdenDia(4);
                turnosHorariosFijos.setRazonesSociales(listRazonesSociales.get(0));
                turnosHorariosFijos.setTurnos(listTurnos.get(0));
                turnosHorariosFijos.setStatusDia(0);
                list.add(turnosHorariosFijos);
                
                turnosHorariosFijos = new TurnosHorariosFijos();
                turnosHorariosFijos.setDiaSemana(DiaSemana.Jueves);
                turnosHorariosFijos.setHorario(listHorario.get(0));
                turnosHorariosFijos.setOrdenDia(5);
                turnosHorariosFijos.setRazonesSociales(listRazonesSociales.get(0));
                turnosHorariosFijos.setTurnos(listTurnos.get(0));
                turnosHorariosFijos.setStatusDia(0);
                list.add(turnosHorariosFijos);
                
                turnosHorariosFijos = new TurnosHorariosFijos();
                turnosHorariosFijos.setDiaSemana(DiaSemana.Viernes);
                turnosHorariosFijos.setHorario(listHorario.get(0));
                turnosHorariosFijos.setOrdenDia(6);
                turnosHorariosFijos.setRazonesSociales(listRazonesSociales.get(0));
                turnosHorariosFijos.setTurnos(listTurnos.get(0));
                turnosHorariosFijos.setStatusDia(0);
                list.add(turnosHorariosFijos);
                
                turnosHorariosFijos = new TurnosHorariosFijos();
                turnosHorariosFijos.setDiaSemana(DiaSemana.Sabado);
                turnosHorariosFijos.setHorario(listHorario.get(0));
                turnosHorariosFijos.setOrdenDia(7);
                turnosHorariosFijos.setRazonesSociales(listRazonesSociales.get(0));
                turnosHorariosFijos.setTurnos(listTurnos.get(0));
                turnosHorariosFijos.setStatusDia(0);
                list.add(turnosHorariosFijos);
                listTurnos.get(i).setListTurnosHorariosFijos(list);
                session.saveOrUpdate(listTurnos.get(i));
            }
            
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Tipo de Contrato">
            tipoContrato = new TipoContrato();//JSA02
            tipoContrato.setClave("01");
            tipoContrato.setDescripcion("Contrato de trabajo por tiempo indeterminado");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("02");
            tipoContrato.setDescripcion("Contrato de trabajo para obra determinada");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("03");
            tipoContrato.setDescripcion("Contrato de trabajo por tiempo determinado");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("04");
            tipoContrato.setDescripcion("Contrato de trabajo por temporada");
            tipoContrato.setEsSindicalizado(true);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("05");
            tipoContrato.setDescripcion("Contrato de trabajo sujeto a prueba");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("06");
            tipoContrato.setDescripcion("Contrato de trabajo con capacitación inicial");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("07");
            tipoContrato.setDescripcion("Modalidad de contratación por pago de hora laborada");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("08");
            tipoContrato.setDescripcion("Modalidad de trabajo por comisión laboral");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("09");
            tipoContrato.setDescripcion("Modalidades de contratación donde no existe relación de trabajo");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("10");
            tipoContrato.setDescripcion("Jubilación, pensión, retiro.");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            tipoContrato = new TipoContrato();
            tipoContrato.setClave("99");
            tipoContrato.setDescripcion("Otro contrato");
            tipoContrato.setEsSindicalizado(false);
            listTipoContratos.add(tipoContrato);
            
            for (i = 0; i < listTipoContratos.size(); i++) {
                session.save(listTipoContratos.get(i));
            }
            //</editor-fold>
            contador = contador + listTipoContratos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Formas de Pago">
            formasDePago = new FormasDePago();
            formasDePago.setId(1L);
            formasDePago.setClave("1");
            formasDePago.setDescripcion("Efectivo");
            listFormasDePago.add(formasDePago);
            
            formasDePago = new FormasDePago();
            formasDePago.setId(2L);
            formasDePago.setClave("2");
            formasDePago.setDescripcion("Cheque");
            listFormasDePago.add(formasDePago);
            
            formasDePago = new FormasDePago();
            formasDePago.setId(3L);
            formasDePago.setClave("3");
            formasDePago.setDescripcion("Transferencia Electronica");
            listFormasDePago.add(formasDePago);
            
            for (i = 0; i < listFormasDePago.size(); i++) {
                session.save(listFormasDePago.get(i));//JSA02
            }
            //</editor-fold>
            contador = contador + listFormasDePago.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Bancos">//JSA02
            bancos = new Bancos();
            bancos.setClave("002");
            bancos.setDescripcion("BANAMEX");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Nacional de México, S.A., Institución de Banca Múltiple, Grupo Financiero Banamex");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("006");
            bancos.setDescripcion("BANCOMEXT");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Nacional de Comercio Exterior, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("009");
            bancos.setDescripcion("BANOBRAS");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Nacional de Obras y Servicios Públicos, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("012");
            bancos.setDescripcion("BBVA BANCOMER");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("BBVA Bancomer, S.A., Institución de Banca Múltiple, Grupo Financiero BBVA Bancomer");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("014");
            bancos.setDescripcion("SANTANDER");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Santander (México), S.A., Institución de Banca Múltiple, Grupo Financiero Santander");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("019");
            bancos.setDescripcion("BANJERCITO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Nacional del Ejército, Fuerza Aérea y Armada, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("021");
            bancos.setDescripcion("HSBC");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("HSBC México, S.A., institución De Banca Múltiple, Grupo Financiero HSBC");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("030");
            bancos.setDescripcion("BAJIO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco del Bajío, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("032");
            bancos.setDescripcion("IXE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("IXE Banco, S.A., Institución de Banca Múltiple, IXE Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("036");
            bancos.setDescripcion("INBURSA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Inbursa, S.A., Institución de Banca Múltiple, Grupo Financiero Inbursa");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("037");
            bancos.setDescripcion("INTERACCIONES");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Interacciones, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("042");
            bancos.setDescripcion("MIFEL");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banca Mifel, S.A., Institución de Banca Múltiple, Grupo Financiero Mifel");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("044");
            bancos.setDescripcion("SCOTIABANK");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Scotiabank Inverlat, S.A.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("058");
            bancos.setDescripcion("BANREGIO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Regional de Monterrey, S.A., Institución de Banca Múltiple, Banregio Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("059");
            bancos.setDescripcion("INVEX");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Invex, S.A., Institución de Banca Múltiple, Invex Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("060");
            bancos.setDescripcion("BANSI");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Bansi, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("062");
            bancos.setDescripcion("AFIRME");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banca Afirme, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("072");
            bancos.setDescripcion("BANORTE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Mercantil del Norte, S.A., Institución de Banca Múltiple, Grupo Financiero Banorte");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("102");
            bancos.setDescripcion("THE ROYAL BANK");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("The Royal Bank of Scotland México, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("103");
            bancos.setDescripcion("AMERICAN EXPRESS");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("American Express Bank (México), S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("106");
            bancos.setDescripcion("BAMSA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Bank of America México, S.A., Institución de Banca Múltiple, Grupo Financiero Bank of America");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("108");
            bancos.setDescripcion("TOKYO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Bank of Tokyo-Mitsubishi UFJ (México), S.A.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("112");
            bancos.setDescripcion("BMONEX");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Monex, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("113");
            bancos.setDescripcion("VE POR MAS");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Ve Por Mas, S.A. Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("116");
            bancos.setDescripcion("ING");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("ING Bank (México), S.A., Institución de Banca Múltiple, ING Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("124");
            bancos.setDescripcion("DEUTSCHE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Deutsche Bank México, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("126");
            bancos.setDescripcion("CREDIT SUISSE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Credit Suisse (México), S.A. Institución de Banca Múltiple, Grupo Financiero Credit Suisse (México)");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("127");
            bancos.setDescripcion("AZTECA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Azteca, S.A. Institución de Banca Múltiple.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("128");
            bancos.setDescripcion("AUTOFIN");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Autofin México, S.A. Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("129");
            bancos.setDescripcion("BARCLAYS");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Barclays Bank México, S.A., Institución de Banca Múltiple, Grupo Financiero Barclays México");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("130");
            bancos.setDescripcion("COMPARTAMOS");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Compartamos, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("131");
            bancos.setDescripcion("BANCO FAMSA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Ahorro Famsa, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("132");
            bancos.setDescripcion("BMULTIVA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Multiva, S.A., Institución de Banca Múltiple, Multivalores Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("133");
            bancos.setDescripcion("ACTINVER");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Actinver, S.A. Institución de Banca Múltiple, Grupo Financiero Actinver");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("134");
            bancos.setDescripcion("WAL-MART");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Wal-Mart de México Adelante, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("135");
            bancos.setDescripcion("NAFIN");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Nacional Financiera, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("136");
            bancos.setDescripcion("INTERBANCO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Inter Banco, S.A. Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("137");
            bancos.setDescripcion("BANCOPPEL");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("BanCoppel, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("138");
            bancos.setDescripcion("ABC CAPITAL");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("ABC Capital, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("139");
            bancos.setDescripcion("UBS BANK");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("UBS Bank México, S.A., Institución de Banca Múltiple, UBS Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("140");
            bancos.setDescripcion("CONSUBANCO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Consubanco, S.A. Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("141");
            bancos.setDescripcion("VOLKSWAGEN");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Volkswagen Bank, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("143");
            bancos.setDescripcion("CIBANCO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("CIBanco, S.A.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("145");
            bancos.setDescripcion("BBASE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco Base, S.A., Institución de Banca Múltiple");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("166");
            bancos.setDescripcion("BANSEFI");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Banco del Ahorro Nacional y Servicios Financieros, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("168");
            bancos.setDescripcion("HIPOTECARIA FEDERAL");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Sociedad Hipotecaria Federal, Sociedad Nacional de Crédito, Institución de Banca de Desarrollo");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("600");
            bancos.setDescripcion("MONEXCB");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Monex Casa de Bolsa, S.A. de C.V. Monex Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("601");
            bancos.setDescripcion("GBM");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("GBM Grupo Bursátil Mexicano, S.A. de C.V. Casa de Bolsa");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("602");
            bancos.setDescripcion("MASARI");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Masari Casa de Bolsa, S.A.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("605");
            bancos.setDescripcion("VALUE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Value, S.A. de C.V. Casa de Bolsa");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("606");
            bancos.setDescripcion("ESTRUCTURADORES");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Estructuradores del Mercado de Valores Casa de Bolsa, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("607");
            bancos.setDescripcion("TIBER");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Casa de Cambio Tiber, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("608");
            bancos.setDescripcion("VECTOR");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Vector Casa de Bolsa, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("610");
            bancos.setDescripcion("B&B");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("B y B, Casa de Cambio, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("614");
            bancos.setDescripcion("ACCIVAL");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Acciones y Valores Banamex, S.A. de C.V., Casa de Bolsa");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("615");
            bancos.setDescripcion("MERRILL LYNCH");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Merrill Lynch México, S.A. de C.V. Casa de Bolsa");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("616");
            bancos.setDescripcion("FINAMEX");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Casa de Bolsa Finamex, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("617");
            bancos.setDescripcion("VALMEX");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Valores Mexicanos Casa de Bolsa, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("618");
            bancos.setDescripcion("UNICA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Unica Casa de Cambio, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("619");
            bancos.setDescripcion("MAPFRE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("MAPFRE Tepeyac, S.A.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("620");
            bancos.setDescripcion("PROFUTURO");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Profuturo G.N.P., S.A. de C.V., Afore");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("621");
            bancos.setDescripcion("CB ACTINVER");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Actinver Casa de Bolsa, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("622");
            bancos.setDescripcion("OACTIN");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("OPERADORA ACTINVER, S.A. DE C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("623");
            bancos.setDescripcion("SKANDIA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Skandia Vida, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("626");
            bancos.setDescripcion("CBDEUTSCHE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Deutsche Securities, S.A. de C.V. CASA DE BOLSA");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("627");
            bancos.setDescripcion("ZURICH");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Zurich Compañía de Seguros, S.A.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("628");
            bancos.setDescripcion("ZURICHVI");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Zurich Vida, Compañía de Seguros, S.A.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("629");
            bancos.setDescripcion("SU CASITA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Hipotecaria Su Casita, S.A. de C.V. SOFOM ENR");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("630");
            bancos.setDescripcion("CB INTERCAM");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Intercam Casa de Bolsa, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("631");
            bancos.setDescripcion("CI BOLSA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("CI Casa de Bolsa, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("632");
            bancos.setDescripcion("BULLTICK CB");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Bulltick Casa de Bolsa, S.A., de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("633");
            bancos.setDescripcion("STERLING");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Sterling Casa de Cambio, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("634");
            bancos.setDescripcion("FINCOMUN");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Fincomún, Servicios Financieros Comunitarios, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("636");
            bancos.setDescripcion("HDI SEGUROS");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("HDI Seguros, S.A. de C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("637");
            bancos.setDescripcion("ORDER");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Order Express Casa de Cambio, S.A. de C.V");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("638");
            bancos.setDescripcion("AKALA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Akala, S.A. de C.V., Sociedad Financiera Popular");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("640");
            bancos.setDescripcion("CB JPMORGAN");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("J.P. Morgan Casa de Bolsa, S.A. de C.V. J.P. Morgan Grupo Financiero");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("642");
            bancos.setDescripcion("REFORMA");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Operadora de Recursos Reforma, S.A. de C.V., S.F.P.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("646");
            bancos.setDescripcion("STP");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Sistema de Transferencias y Pagos STP, S.A. de C.V.SOFOM ENR");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("647");
            bancos.setDescripcion("TELECOMM");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Telecomunicaciones de México");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("648");
            bancos.setDescripcion("EVERCORE");
            bancos.setDomicilio("");
            bancos.setNotas("Evercore Casa de Bolsa, S.A. de C.V.");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("649");
            bancos.setDescripcion("SKANDIA");
            bancos.setDomicilio("");
            bancos.setNotas("Skandia Operadora de Fondos, S.A. de C.V.");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("651");
            bancos.setDescripcion("SEGMTY");
            bancos.setDomicilio("");
            bancos.setNotas("Seguros Monterrey New York Life, S.A de C.V");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("652");
            bancos.setDescripcion("ASEA");
            bancos.setDomicilio("");
            bancos.setNotas("Solución Asea, S.A. de C.V., Sociedad Financiera Popular");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("653");
            bancos.setDescripcion("KUSPIT");
            bancos.setDomicilio("");
            bancos.setNotas("Kuspit Casa de Bolsa, S.A. de C.V.");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("655");
            bancos.setDescripcion("SOFIEXPRESS");
            bancos.setDomicilio("");
            bancos.setNotas("J.P. SOFIEXPRESS, S.A. de C.V., S.F.P.");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("656");
            bancos.setDescripcion("UNAGRA");
            bancos.setDomicilio("");
            bancos.setNotas("UNAGRA, S.A. de C.V., S.F.P.");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("659");
            bancos.setDescripcion("OPCIONES EMPRESARIALES DEL NOROESTE");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("OPCIONES EMPRESARIALES DEL NORESTE, S.A. DE C.V., S.F.P.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("670");
            bancos.setDescripcion("LIBERTAD");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Libertad Servicios Financieros, S.A. De C.V.");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("901");
            bancos.setDescripcion("CLS");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("Cls Bank International");
            listBancos.add(bancos);
            
            bancos = new Bancos();
            bancos.setClave("902");
            bancos.setDescripcion("INDEVAL");
            bancos.setDomicilio("");
            bancos.setPaginaweb("");
            bancos.setRFC("");
            bancos.setNotas("SD. Indeval, S.A. de C.V.");
            listBancos.add(bancos);
            
            for (i = 0; i < listBancos.size(); i++) {
                session.save(listBancos.get(i));
            }
            //</editor-fold>
            contador = contador + listBancos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Parentesco">//JSA02
            parentesco = new Parentesco();
            parentesco.setClave("01");
            parentesco.setDescripcion("Madre");
            listParentesco.add(parentesco);
            
            parentesco = new Parentesco();
            parentesco.setClave("02");
            parentesco.setDescripcion("Padre");
            listParentesco.add(parentesco);
            
            parentesco = new Parentesco();
            parentesco.setClave("03");
            parentesco.setDescripcion("Hermano(a)");
            listParentesco.add(parentesco);
            
            parentesco = new Parentesco();
            parentesco.setClave("04");
            parentesco.setDescripcion("Primo(a)");
            listParentesco.add(parentesco);
            
            for (i = 0; i < listParentesco.size(); i++) {
                session.save(listParentesco.get(i));
            }
            //</editor-fold>
            contador = contador + listParentesco.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Cursos">
            cursos = new Cursos();
            cursos.setClave("01");
            cursos.setDescripcion("Curso1");
            listCursos.add(cursos);
            
            cursos = new Cursos();
            cursos.setClave("02");
            cursos.setDescripcion("Curso2");
            listCursos.add(cursos);
            
            for (i = 0; i < listCursos.size(); i++) {
                session.save(listCursos.get(i));
            }
            //</editor-fold>
            contador = contador + listCursos.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
                
            }
            //<editor-fold defaultstate="collapsed" desc="Estudios">
            estudios = new Estudios();
            estudios.setClave("01");
            estudios.setDescripcion("Primaria");
            listEstudios.add(estudios);
            
            estudios = new Estudios();
            estudios.setClave("02");
            estudios.setDescripcion("Secundaria");
            listEstudios.add(estudios);
            
            estudios = new Estudios();
            estudios.setClave("03");
            estudios.setDescripcion("Preparatoria");
            listEstudios.add(estudios);
            
            estudios = new Estudios();
            estudios.setClave("04");
            estudios.setDescripcion("Carrera Profesional");
            listEstudios.add(estudios);
            
            for (i = 0; i < listEstudios.size(); i++) {
                session.save(listEstudios.get(i));
            }
            //</editor-fold>
            contador = contador + listEstudios.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;

                //<editor-fold defaultstate="collapsed" desc="Firmas">//JSA02
                firmas = new Firmas();
                firmas.setClave("01");
                firmas.setPuesto("Gerente");
                firmas.setDescripcion("AAAAAA");
                listFirmas.add(firmas);
                
                for (i = 0; i < listFirmas.size(); i++) {
                    session.save(listFirmas.get(i));
                }
                //</editor-fold>
                contador = contador + listFirmas.size();
                if (contador % rango == 0 & contador > 0) {
                    session.flush();
                    session.clear();
                    contador = 0;
                }
                
            }
            //<editor-fold defaultstate="collapsed" desc="Plazas">
            plazas = new Plazas();
            plazas.setClave("01");
            plazas.setHoras(8d);
            plazas.setImporte(200);
            plazas.setTipoRelacionLaboral(1);
            plazas.setTipoSalario(1);
            plazas.setSalarioPor(2);
            plazas.setTipoContrato(listTipoContratos.get(0));
            plazas.setCategoriasPuestos(listCategoriaPuestos.get(0));
            plazas.setCentroDeCosto(listCentroDeCosto.get(0));
            plazas.setDepartamentos(listDepartamentos.get(0));
            plazas.setPuestos(listPuestos.get(0));
            plazas.setRegistroPatronal(listRegistroPatronal.get(0));
            plazas.setTipoNomina(listTipoNomina.get(4));
            plazas.setTurnos(listTurnos.get(0));
            plazas.setCantidadPlazas(1);
            plazas.setRazonesSociales(listRazonesSociales.get(0));
            listPlazas.add(plazas);
            
            for (i = 0; i < listPlazas.size(); i++) {
                session.save(listPlazas.get(i));
            }
            //</editor-fold>
            contador = contador + listPlazas.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="Empleado">
//CURP, IMSS, RFC,  clinicaIMSS, colonia, fechaFinal, fechaIMSS, fechaIngreso, fechaInicial, fechaPrestaciones, hms, salario, tipoContrato, tipoRelacionLaboral, tipoSalario, tipoSindicato, domicilio, estadoCivil, estadoNacionalidad, fechaNacimiento, genero, lugarNacimiento, nacionalidad, nombre, numeroExt, numeroInt, status, cp_id, categoriasPuestos_id, departamentos_id, puestos_id, registroPatronal_id, tipoNomina_id, turnos_id, paisOrigen_id, razonesSociales_ID from EMpleados
//			Ahome		2012-03-28 00:00:00.000	2012-03-28 00:00:00.000	2012-03-28 00:00:00.000	2012-03-28 00:00:00.000	2012-03-28 00:00:00.000	8	250	0	1	1	0		1	Sinaloa	2012-03-28 00:00:00.000	1	Texcoco	Mexicano	Quintana	15	15	1	1	1	1	1	1	5	1	1	1            
            List<Genero> generos = new ArrayList<>();
            Genero genMujer = new Genero();
            genMujer.setClave("001");
            genMujer.setDescripcion("Femenino");
            Genero genHombre = new Genero();
            genHombre.setClave("002");
            genHombre.setDescripcion("Masculino");
            generos.add(genHombre);
            generos.add(genMujer);
            for (i = 0; i < generos.size(); i++) {
                session.save(generos.get(i));
            }
            empleados = new Empleados();
            empleados.setClave("00001");
            empleados.setRazonesSociales(listRazonesSociales.get(0));
            empleados.setApellidoPaterno("Lopez");
            empleados.setApellidoMaterno("Lopez");
            empleados.setNombre("Quintana Jesus");
            empleados.setNombreAbreviado("Quintanilla");
            empleados.setDomicilio("Av. Velasquez");
            empleados.setNumeroExt("12");
            empleados.setNumeroInt("15");
            empleados.setColonia("Centro");
            empleados.setCp(listCp.get(0));
            empleados.setCiudades(listCiudades.get(0));
            empleados.setMunicipios(listMunicipios.get(11));
            empleados.setEstados(listEstados.get(24));
            empleados.setPaises(listPaises.get(0));
            calendar.set(1990, Calendar.MARCH, 15);
            empleados.setFechaNacimiento(calendar.getTime());
            calendar.set(2013, Calendar.JULY, 9);
            empleados.setFechaIngresoEmpresa(calendar.getTime());
            empleados.setPaisOrigen(listPaises.get(0));
            empleados.setNacionalidad("Mexicano");
            empleados.setEstadoNacimiento(listEstados.get(23));
            empleados.setLugarNacimiento("Ahome");
            empleados.setRFC("LOGQ1202126SA");
            empleados.setCURP("LOGQ120212HSPPTNA2");
            empleados.setIMSS("23152415211");
            empleados.setClinicaIMSS("Ahome");
            empleados.setEstadoCivil(1);
            empleados.setGenero(generos.get(0));
            empleados.setStatus(true);
            
            listEmpleado.add(empleados);
            
            for (i = 0; i < listEmpleado.size(); i++) {
                session.save(listEmpleado.get(i));
            }
            //JSA02
            List<PlazasPorEmpleado> listPlazasPorEmpleados = new ArrayList<PlazasPorEmpleado>();
            PlazasPorEmpleado plazasPorEmpleado = new PlazasPorEmpleado();
            plazasPorEmpleado.setEmpleados(listEmpleado.get(0));
            Calendar c = Calendar.getInstance();
            c.set(2013 + 100, Calendar.JULY, 9, 12, 0, 0);
            plazasPorEmpleado.setFechaFinal(c.getTime());
            c.set(2013, Calendar.JULY, 9, 12, 0, 0);
            plazasPorEmpleado.setFechaPrestaciones(calendar.getTime());
            plazasPorEmpleado.setRazonesSociales(listRazonesSociales.get(0));
            plazasPorEmpleado.setClave("000000000000001");
            plazasPorEmpleado.setRegistroPatronal(listRegistroPatronal.get(0));
            plazasPorEmpleado.setPlazaPrincipal(true);
            listPlazasPorEmpleados.add(plazasPorEmpleado);
            
            session.save(listPlazasPorEmpleados.get(0));
            
            List<PlazasPorEmpleadosMov> listPlazasPorEmpleadosMovs = new ArrayList<PlazasPorEmpleadosMov>();
            PlazasPorEmpleadosMov plazasPorEmpleadosMov = new PlazasPorEmpleadosMov();
            c.set(2013, Calendar.JULY, 9, 12, 0, 0);
            plazasPorEmpleadosMov.setFechaInicial(calendar.getTime());
            plazasPorEmpleadosMov.setFechaIMSS(calendar.getTime());
            plazasPorEmpleadosMov.setTipoNomina(listTipoNomina.get(4));
            plazasPorEmpleadosMov.setCambioTipoDeNomina(false);
            plazasPorEmpleadosMov.setCentroDeCosto(listCentroDeCosto.get(0));
            plazasPorEmpleadosMov.setCambioCentroDeCostos(false);
            plazasPorEmpleadosMov.setDepartamentos(listDepartamentos.get(0));
            plazasPorEmpleadosMov.setCambioDepartamento(false);
            plazasPorEmpleadosMov.setPuestos(listPuestos.get(0));
            plazasPorEmpleadosMov.setCambioPuestos(false);
            plazasPorEmpleadosMov.setTurnos(listTurnos.get(0));
            plazasPorEmpleadosMov.setCambioTurno(false);
            plazasPorEmpleadosMov.setTipoContrato(listTipoContratos.get(0));
            plazasPorEmpleadosMov.setCambioTipoContrato(false);
            plazasPorEmpleadosMov.setHoras(null);
            plazasPorEmpleadosMov.setCambioHoras(false);
            plazasPorEmpleadosMov.setCambioPlazasPosOrganigrama(false);
            plazasPorEmpleadosMov.setSalarioPor(2);
            plazasPorEmpleadosMov.setCambioSalarioPor(false);
            plazasPorEmpleadosMov.setImporte(233.00F);
            plazasPorEmpleadosMov.setSueldoDiario(0.0);
            plazasPorEmpleadosMov.setFormasDePago(listFormasDePago.get(0));
            plazasPorEmpleadosMov.setCambioFormaDePago(false);
            plazasPorEmpleadosMov.setBancos(listBancos.get(0));
            plazasPorEmpleadosMov.setCambioBanco(false);
            plazasPorEmpleadosMov.setCuentaBancaria("3333-3333-3333-3333");
            plazasPorEmpleadosMov.setClabe("11111111113");
            plazasPorEmpleadosMov.setCambioCuentaBancaria(false);
            plazasPorEmpleadosMov.setTipoRelacionLaboral(Integer.valueOf("1"));
            plazasPorEmpleadosMov.setCambioTipoRelacionLaboral(false);
            plazasPorEmpleadosMov.setZonaGeografica(ZonaGeografica.ZonaGeograficaB);
            plazasPorEmpleadosMov.setCambioZonaGeografica(false);
            plazasPorEmpleadosMov.setPlazasPorEmpleado(listPlazasPorEmpleados.get(0));
            plazasPorEmpleadosMov.setRegimenContratacion("02");
            plazasPorEmpleadosMov.setCambioRegimenContratacion(false);
            listPlazasPorEmpleadosMovs.add(plazasPorEmpleadosMov);
            
            session.save(listPlazasPorEmpleadosMovs.get(0));

            //</editor-fold>
            contador = contador + listEmpleado.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="Ingresos, Reingresos y bajas">
            IngresosReingresosBajas ingresosReingresosBajas = new IngresosReingresosBajas();
            ingresosReingresosBajas.setEmpleados(listEmpleado.get(0));
            calendar.set(2013, Calendar.JULY, 9, 12, 0, 0);
            ingresosReingresosBajas.setFechaIngreso(calendar.getTime());
            calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + 100);
            ingresosReingresosBajas.setFechaBaja(calendar.getTime());
            ingresosReingresosBajas.setPlazasPorEmpleado(listPlazasPorEmpleados.get(0));
            ingresosReingresosBajas.setRazonesSociales(listRazonesSociales.get(0));
            ingresosReingresosBajas.setRegistroPatronal(listRegistroPatronal.get(0));
            ingresosReingresosBajas.setTipoMovimiento(IngresosReingresosBajas.TipoMovimiento.I);
            session.save(ingresosReingresosBajas);
            //</editor-fold>
            contador = contador + 1;
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="SalarioIntegrados">
            SalariosIntegrados salariosIntegrados = new SalariosIntegrados();
            salariosIntegrados.setEmpleados(listEmpleado.get(0));
            salariosIntegrados.setFactorIntegracion(0D);
            salariosIntegrados.setTipoDeSalario(0);
            salariosIntegrados.setSalarioDiarioIntegrado(243.8799);
            salariosIntegrados.setSalarioDiarioVariable(0f);
            salariosIntegrados.setSalarioDiarioFijo(243.8799);
            salariosIntegrados.setRegistroPatronal(listRegistroPatronal.get(0));
            calendar.set(2013, Calendar.JULY, 9, 12, 0, 0);
            salariosIntegrados.setFecha(calendar.getTime());
            session.save(salariosIntegrados);
            //</editor-fold>
            contador = contador + 1;
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="ConfigConceptosSat">
            List<ConfigConceptosSat> listConceptosSat = new ArrayList<ConfigConceptosSat>();
            /*PERCEPCIONES SAT*/
            ConfigConceptosSat conceptosSat = new ConfigConceptosSat();  //SUELDO
            conceptosSat.setConcepNomDefi(listConceptoDeNominaDefinicion.get(0));
            conceptosSat.setConceptoSatClave("001");
            conceptosSat.setOtroPago(false);
            listConceptosSat.add(conceptosSat);
            
            conceptosSat = new ConfigConceptosSat(); //COMISIONES
            conceptosSat.setConcepNomDefi(listConceptoDeNominaDefinicion.get(1));
            conceptosSat.setConceptoSatClave("028");
            conceptosSat.setOtroPago(false);
            listConceptosSat.add(conceptosSat);
            
            conceptosSat = new ConfigConceptosSat(); //SUBSIDIO EMPLEO
            conceptosSat.setConcepNomDefi(listConceptoDeNominaDefinicion.get(18));
            conceptosSat.setConceptoSatClave("002");
            conceptosSat.setOtroPago(true);
            listConceptosSat.add(conceptosSat);

            /*DEDUCCIONES SAT*/
            conceptosSat = new ConfigConceptosSat(); //IMSS
            conceptosSat.setConcepNomDefi(listConceptoDeNominaDefinicion.get(32));
            conceptosSat.setConceptoSatClave("001");
            conceptosSat.setOtroPago(false);
            listConceptosSat.add(conceptosSat);
            
            conceptosSat = new ConfigConceptosSat(); //ISR
            conceptosSat.setConcepNomDefi(listConceptoDeNominaDefinicion.get(33));
            conceptosSat.setConceptoSatClave("002");
            conceptosSat.setOtroPago(false);
            listConceptosSat.add(conceptosSat);
            
            for (i = 0; i < listConceptosSat.size(); i++) {
                session.save(listConceptosSat.get(i));
            }
            //</editor-fold>
            contador = contador + listConceptosSat.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

            //<editor-fold defaultstate="collapsed" desc="CreditoAhorro">
            creditoAhorro = new CreditoAhorro();
            creditoAhorro.setClave("001");
            creditoAhorro.setDescripcion("PRESTAMOS PERSONALES");
            creditoAhorro.setTipoConfiguracion("1");
            creditoAhorro.setDescripcionAbrev("PRESTAMOS");
            creditoAhorro.setAsignaAutoNumCredAho(true);
            creditoAhorro.setLongitudNumCredAho("8");
            creditoAhorro.setMascaraNumCredAho("########");
            creditoAhorro.setInicioDescuento(true);
            creditoAhorro.setDefinirNumEmp(false);
            creditoAhorro.setLongitudNumEmp("");
            creditoAhorro.setModoDescuento((byte) 1);
            creditoAhorro.setPorcentaje(false);
            creditoAhorro.setVsmg(false);
            creditoAhorro.setModoCapturaDescuento(0);
            creditoAhorro.setNumDecimalesDescuento(2);
            creditoAhorro.setModoCapturaDescuentoVSMG(null);
            creditoAhorro.setNumDecimalesDescuentoVSMG(null);
            creditoAhorro.setModoCapturaDescuentoPorc(null);
            creditoAhorro.setNumDecimalesDescuentoPorc(null);
            creditoAhorro.setCuotaFija(true);
            creditoAhorro.setDescPropDiasPer(true);
            creditoAhorro.setSolicitarFecVen(false);
            creditoAhorro.setFondoAhorro(false);
            creditoAhorro.setModoAgregarCredAhoIngEmp((byte) 0);
            creditoAhorro.setModoDescontarCredAhoFini((byte) 0);
            creditoAhorro.setModoDescontarCredAhoLiqu((byte) 0);
            creditoAhorro.setTasaIntMens("");
            creditoAhorro.setCorteMesDia(Calendar.getInstance().getTime());
            creditoAhorro.setRazonesSociales(listRazonesSociales.get(0));
            for (int k = 0; k < listConceptoDeNominaDefinicion.size(); k++) {
                if (listConceptoDeNominaDefinicion.get(k).getClave().equalsIgnoreCase("760")) {
                    creditoAhorro.setConcepNomiDefin(listConceptoDeNominaDefinicion.get(k));
                    break;
                }
            }
            creditoAhorro.setcNDInteresMensual(null);
            creditoAhorro.setcNDescuento(null);
            creditoAhorro.setConceptoDcto("");
            creditoAhorro.setPeriodicidadDescuento(0);
            creditoAhorro.setCuandoDescontar(-1);
            creditoAhorro.setModoManejoDescuento((byte) 2);
            creditoAhorro.setImporteDescuento(null);
            creditoAhorro.setActivarManejoDescuento(false);
            creditoAhorro.setFactorProporcional((byte) 1);
            listCreditoAhorro.add(creditoAhorro);
            
            creditoAhorro = new CreditoAhorro();
            creditoAhorro.setClave("005");
            creditoAhorro.setDescripcion("INFONAVIT");
            creditoAhorro.setTipoConfiguracion("1");
            creditoAhorro.setDescripcionAbrev("INFONAVIT");
            creditoAhorro.setAsignaAutoNumCredAho(false);
            creditoAhorro.setLongitudNumCredAho("11");
            creditoAhorro.setMascaraNumCredAho("###########");
            creditoAhorro.setInicioDescuento(false);
            creditoAhorro.setDefinirNumEmp(false);
            creditoAhorro.setLongitudNumEmp("");
            creditoAhorro.setModoDescuento((byte) 1);
            creditoAhorro.setPorcentaje(true);
            creditoAhorro.setVsmg(true);
            creditoAhorro.setModoCapturaDescuento(1);
            creditoAhorro.setNumDecimalesDescuento(4);
            creditoAhorro.setModoCapturaDescuentoVSMG(1);
            creditoAhorro.setNumDecimalesDescuentoVSMG(4);
            creditoAhorro.setModoCapturaDescuentoPorc(0);
            creditoAhorro.setNumDecimalesDescuentoPorc(4);
            creditoAhorro.setCuotaFija(true);
            creditoAhorro.setDescPropDiasPer(false);
            creditoAhorro.setSolicitarFecVen(false);
            creditoAhorro.setFondoAhorro(false);
            creditoAhorro.setModoAgregarCredAhoIngEmp((byte) 0);
            creditoAhorro.setModoDescontarCredAhoFini((byte) 0);
            creditoAhorro.setModoDescontarCredAhoLiqu((byte) 0);
            creditoAhorro.setTasaIntMens("");
            creditoAhorro.setCorteMesDia(Calendar.getInstance().getTime());
            creditoAhorro.setRazonesSociales(listRazonesSociales.get(0));
            for (int k = 0; k < listConceptoDeNominaDefinicion.size(); k++) {
                if (listConceptoDeNominaDefinicion.get(k).getClave().equalsIgnoreCase("750")) {
                    creditoAhorro.setConcepNomiDefin(listConceptoDeNominaDefinicion.get(k));
                    break;
                }
            }
            creditoAhorro.setcNDInteresMensual(null);
            for (int k = 0; k < listConceptoDeNominaDefinicion.size(); k++) {
                if (listConceptoDeNominaDefinicion.get(k).getClave().equalsIgnoreCase("755")) {
                    creditoAhorro.setcNDescuento(listConceptoDeNominaDefinicion.get(k));
                    break;
                }
            }
            creditoAhorro.setConceptoDcto("SEGURO");
            creditoAhorro.setPeriodicidadDescuento(0);
            creditoAhorro.setCuandoDescontar(-1);
            creditoAhorro.setModoManejoDescuento((byte) 2);
            creditoAhorro.setImporteDescuento(1.875);
            creditoAhorro.setActivarManejoDescuento(true);
            creditoAhorro.setFactorProporcional((byte) 1);
            listCreditoAhorro.add(creditoAhorro);
            for (i = 0; i < listCreditoAhorro.size(); i++) {
                session.save(listCreditoAhorro.get(i));
            }
            //</editor-fold>
            contador = contador + listCreditoAhorro.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="TiposVacaciones">
            tiposVacaciones = new TiposVacaciones();
            tiposVacaciones.setClave("01");
            tiposVacaciones.setNombre("Vacaciones Disfrutadas");
            listTiposVacaciones.add(tiposVacaciones);
            
            tiposVacaciones = new TiposVacaciones();
            tiposVacaciones.setClave("02");
            tiposVacaciones.setNombre("Vacaciones Trabajadas");
            listTiposVacaciones.add(tiposVacaciones);
            
            for (i = 0; i < listTiposVacaciones.size(); i++) {
                session.save(listTiposVacaciones.get(i));
            }
            //</editor-fold>
            contador = contador + listTiposVacaciones.size();
            if (contador % rango == 0 & contador > 0) {
                session.flush();
                session.clear();
                contador = 0;
            }

//////            //<editor-fold defaultstate="collapsed" desc="PeriodosNomina Quincenal">
//////            Calendar tmp = Calendar.getInstance();
//////            tmp.set(tmp.get(Calendar.YEAR), 01, 15);
//////            int dias = Integer.valueOf(listTipoNomina.get(4).getPeriodicidad().getDias().toString());
//////            int año = tmp.get(Calendar.YEAR);
//////            int p15 = 0;
//////            int ajustaPeriodo = 0;
//////            int dias2;
//////            boolean continuar = true;
//////            Calendar cf = Calendar.getInstance();
//////            Calendar fechaInicial = Calendar.getInstance();
//////
//////            Calendar fechaFinal = Calendar.getInstance();
//////            Calendar fechaInicial2 = Calendar.getInstance();
//////            Calendar fechaFinal2 = Calendar.getInstance();
//////            cf.set(cf.get(Calendar.YEAR), 01, 01);
//////            boolean aplicaAjuste = true;
//////            if (continuar) {
//////                if (dias == 30 || dias == 15) {
//////                    // <editor-fold defaultstate="collapsed" desc="Generar periodos Mensual o quincenal"> 
//////                    int diaMes;
//////                    for (int periodo = 1; periodo <= 12; periodo++) {
//////                        fechaInicial = Calendar.getInstance();
//////                        fechaFinal = Calendar.getInstance();
//////                        fechaInicial.clear();
//////                        fechaFinal.clear();
//////
//////                        fechaInicial.set(Calendar.YEAR, c.get(Calendar.YEAR));
//////                        fechaInicial.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
//////                        diaMes = c.get(Calendar.DAY_OF_MONTH);
//////                        c.add(Calendar.MONTH, 1);
//////                        c.set(Calendar.DAY_OF_MONTH, 1);
//////                        fechaFinal.set(Calendar.YEAR, c.get(Calendar.YEAR));
//////                        fechaFinal.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
//////                        fechaFinal.add(Calendar.DAY_OF_YEAR, -1);
//////                        PeriodosNomina p = new PeriodosNomina();
//////                        if (dias == 15 && diaMes < 15) {
//////                            p15++;
//////                            p.setClave(asignarMascara(String.valueOf(p15)));//JSA01
//////                            p.setCierreMes(false);
//////                        } else {
//////                            p.setClave(asignarMascara(String.valueOf(periodo)));//JSA01
//////                            p.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(periodo)), fechaInicial, fechaFinal));
//////                            p.setCierreMes(true);
//////                        }
//////                        p.setAcumularAMes(fechaInicial.getTime());
//////                        p.setBloquear(false);
//////                        p.setFechaAsistenciInicial(fechaInicial.getTime());
//////                        p.setFechaAsistenciaFinal(fechaFinal.getTime());
//////                        p.setFechaFinal(fechaFinal.getTime());
//////                        p.setFechaInicial(fechaInicial.getTime());
//////                        p.setFechaCierre(fechaFinal.getTime());
//////                        p.setFechaModificado(Calendar.getInstance().getTime());
//////                        p.setFechaPago(fechaFinal.getTime());
//////                        p.setTipoNomina(tn);
//////                        p.setAño(año);
//////                        p.setDescontarAhorro(false);
//////                        p.setDescontarPrestamo(false);
//////                        p.setDiasIMSS(dias);
//////                        p.setDiasPago(dias);
//////                        p.setIncluirBajas(false);
//////                        p.setSoloPercepciones(false);
//////                        p.setStatus(true);
//////                        p.setTipoUso(0);
//////                        p.setClaveUsuario(MainPrincipal.getUsuarioActual().getClave());
//////
//////                        if (dias == 15 && diaMes < 15) {
//////                            fechaInicial2 = Calendar.getInstance();
//////                            fechaFinal2 = Calendar.getInstance();
//////                            fechaInicial2.clear();
//////                            fechaFinal2.clear();
//////                            fechaInicial2.set(Calendar.YEAR, fechaInicial.get(Calendar.YEAR));
//////                            fechaFinal2.set(Calendar.YEAR, fechaInicial.get(Calendar.YEAR));
//////                            fechaFinal2.set(Calendar.DAY_OF_YEAR, fechaFinal.get(Calendar.DAY_OF_YEAR));
//////
//////                            fechaFinal.set(Calendar.DAY_OF_YEAR, fechaInicial.get(Calendar.DAY_OF_YEAR));
//////                            fechaFinal.add(Calendar.DAY_OF_YEAR, (15 - diaMes));
//////                            fechaInicial2.set(Calendar.DAY_OF_YEAR, fechaFinal.get(Calendar.DAY_OF_YEAR));
//////                            fechaInicial2.add(Calendar.DAY_OF_YEAR, 1);
//////                            p15++;
//////
//////                            PeriodosNomina pq = new PeriodosNomina();
//////                            pq.setClave(asignarMascara(String.valueOf(p15)));//JSA01
//////                            pq.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(p15)), fechaInicial2, fechaFinal2));
//////                            pq.setAcumularAMes(fechaInicial2.getTime());
//////                            pq.setBloquear(false);
//////                            pq.setCierreMes(true);
//////                            pq.setFechaAsistenciInicial(fechaInicial2.getTime());
//////                            pq.setFechaAsistenciaFinal(fechaFinal2.getTime());
//////                            pq.setFechaFinal(fechaFinal2.getTime());
//////                            pq.setFechaInicial(fechaInicial2.getTime());
//////                            pq.setFechaCierre(fechaFinal2.getTime());
//////                            pq.setFechaModificado(Calendar.getInstance().getTime());
//////                            pq.setFechaPago(fechaFinal2.getTime());
//////                            pq.setTipoNomina(tn);
//////                            pq.setAño(año);
//////                            pq.setDescontarAhorro(false);
//////                            pq.setDescontarPrestamo(false);
//////                            pq.setDiasIMSS(dias);
//////                            pq.setDiasPago(dias);
//////                            pq.setIncluirBajas(false);
//////                            pq.setSoloPercepciones(false);
//////                            pq.setStatus(true);
//////                            pq.setTipoUso(0);
//////                            pq.setClaveUsuario(MainPrincipal.getUsuarioActual().getClave());
//////
//////                            p.setFechaAsistenciInicial(fechaInicial.getTime());
//////                            p.setFechaAsistenciaFinal(fechaFinal.getTime());
//////                            p.setFechaFinal(fechaFinal.getTime());
//////                            p.setFechaInicial(fechaInicial.getTime());
//////                            p.setFechaCierre(fechaFinal.getTime());
//////                            p.setFechaModificado(Calendar.getInstance().getTime());
//////                            p.setFechaPago(fechaFinal.getTime());
//////                            p.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(p15 - 1)), fechaInicial, fechaFinal));
//////
//////                            data.add(p);
//////                            data.add(pq);
//////                        } else {
//////                            data.add(p);
//////                        }
//////                        if (c.get(Calendar.YEAR) == año + 1) {
//////                            break;
//////                        }
//////                    }// </editor-fold>
//////                } else if (dias >= 365) {
//////                    // <editor-fold defaultstate="collapsed" desc="Generar periodo Anual"> 
//////                    fechaInicial = Calendar.getInstance();
//////                    fechaFinal = Calendar.getInstance();
//////                    fechaInicial.clear();
//////                    fechaFinal.clear();
//////
//////                    fechaInicial.set(Calendar.YEAR, c.get(Calendar.YEAR));
//////                    fechaInicial.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
//////
////////                c.add(Calendar.MONTH, 1);
//////                    fechaFinal.set(Calendar.YEAR, c.get(Calendar.YEAR) + 1);
//////                    fechaFinal.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
//////                    fechaFinal.add(Calendar.DAY_OF_YEAR, -1);
//////                    PeriodosNomina p = new PeriodosNomina();//                    
//////                    p.setClave(asignarMascara("1"));//JSA01
//////                    p.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(1)), fechaInicial, fechaFinal));
//////                    p.setCierreMes(true);
//////                    p.setAcumularAMes(fechaInicial.getTime());
//////                    p.setBloquear(false);
//////                    p.setFechaAsistenciInicial(fechaInicial.getTime());
//////                    p.setFechaAsistenciaFinal(fechaFinal.getTime());
//////                    p.setFechaFinal(fechaFinal.getTime());
//////                    p.setFechaInicial(fechaInicial.getTime());
//////                    p.setFechaCierre(fechaFinal.getTime());
//////                    p.setFechaModificado(Calendar.getInstance().getTime());
//////                    p.setFechaPago(fechaFinal.getTime());
//////                    p.setTipoNomina(tn);
//////                    p.setAño(año);
//////                    p.setDescontarAhorro(false);
//////                    p.setDescontarPrestamo(false);
//////                    p.setDiasIMSS(dias);
//////                    p.setDiasPago(dias);
//////                    p.setIncluirBajas(false);
//////                    p.setSoloPercepciones(false);
//////                    p.setStatus(true);
//////                    p.setTipoUso(0);
//////                    p.setClaveUsuario(MainPrincipal.getUsuarioActual().getClave());
//////                    data.add(p);// </editor-fold>
//////                } else {
//////                    // <editor-fold defaultstate="collapsed" desc="Generar periodos Normal"> 
//////                    for (int periodo = 1;; periodo++) {
//////////                        if (d == null) {
//////                        fechaInicial = Calendar.getInstance();
//////                        fechaInicial.clear();
//////                        fechaInicial.set(Calendar.YEAR, c.get(Calendar.YEAR));
//////
//////                        fechaInicial.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
//////                        c.add(Calendar.DAY_OF_YEAR, dias);
//////
//////                        fechaFinal.clear();
//////                        fechaFinal = Calendar.getInstance();
//////                        fechaFinal.set(Calendar.YEAR, c.get(Calendar.YEAR));
//////                        fechaFinal.set(Calendar.DAY_OF_YEAR, c.get(Calendar.DAY_OF_YEAR));
//////                        fechaFinal.add(Calendar.DAY_OF_YEAR, -1);
//////                        PeriodosNomina p = new PeriodosNomina();
//////
//////                        if (ajusta && aplicaAjuste) {
//////                            ajustaPeriodo++;
//////                            p.setClave(asignarMascara(String.valueOf(ajustaPeriodo)));//JSA01
//////                            p.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(ajustaPeriodo)), fechaInicial, fechaFinal));
//////                        } else {
//////                            p.setClave(asignarMascara(String.valueOf(periodo)));//JSA01
//////                            p.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(periodo)), fechaInicial, fechaFinal));
//////                        }
//////                        p.setAcumularAMes(fechaInicial.getTime());
//////                        p.setBloquear(false);
//////                        if (fechaInicial.get(Calendar.MONTH) == fechaFinal.get(Calendar.MONTH) && fechaFinal.get(Calendar.MONTH) == c.get(Calendar.MONTH)) {
//////                            p.setCierreMes(false);
//////                        } else {
//////                            p.setCierreMes(true);
//////                        }
//////                        p.setFechaAsistenciInicial(fechaInicial.getTime());
//////                        p.setFechaAsistenciaFinal(fechaFinal.getTime());
//////                        p.setFechaFinal(fechaFinal.getTime());
//////                        p.setFechaInicial(fechaInicial.getTime());
//////                        p.setFechaCierre(fechaFinal.getTime());
//////                        p.setFechaModificado(Calendar.getInstance().getTime());
//////                        p.setFechaPago(fechaFinal.getTime());
//////                        p.setTipoNomina(tn);
//////                        p.setAño(año);
//////                        p.setDescontarAhorro(false);
//////                        p.setDescontarPrestamo(false);
//////                        p.setDiasIMSS(dias);
//////                        p.setDiasPago(dias);
//////                        p.setIncluirBajas(false);
//////                        p.setSoloPercepciones(false);
//////                        p.setStatus(true);
//////                        p.setTipoUso(0);
//////                        p.setClaveUsuario(MainPrincipal.getUsuarioActual().getClave());
//////                        if (ajusta && fechaInicial.get(Calendar.MONTH) != fechaFinal.get(Calendar.MONTH) && aplicaAjuste) {
//////                            fechaInicial2 = Calendar.getInstance();
//////                            fechaFinal2 = Calendar.getInstance();
//////                            fechaInicial2.clear();
//////                            fechaFinal2.clear();
//////                            fechaInicial2.set(Calendar.YEAR, fechaInicial.get(Calendar.YEAR));
//////                            fechaFinal2.set(Calendar.YEAR, fechaFinal.get(Calendar.YEAR));
//////                            fechaFinal2.set(Calendar.DAY_OF_YEAR, fechaFinal.get(Calendar.DAY_OF_YEAR));
//////                            for (;;) {
//////                                fechaFinal.add(Calendar.DAY_OF_YEAR, -1);
//////                                if (fechaFinal.get(Calendar.MONTH) == fechaInicial.get(Calendar.MONTH)) {
//////                                    break;
//////                                }
//////                            }
//////                            fechaInicial2.set(Calendar.DAY_OF_YEAR, fechaFinal.get(Calendar.DAY_OF_YEAR));
//////                            fechaInicial2.add(Calendar.DAY_OF_YEAR, 1);
//////                            ajustaPeriodo++;
//////                            dias2 = (fechaFinal2.get(Calendar.DAY_OF_YEAR) - fechaInicial2.get(Calendar.DAY_OF_YEAR)) + 1;
//////                            PeriodosNomina pq = new PeriodosNomina();
//////                            pq.setClave(asignarMascara(String.valueOf(ajustaPeriodo)));//JSA01
//////                            pq.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(ajustaPeriodo)), fechaInicial2, fechaFinal2));
//////                            pq.setAcumularAMes(fechaInicial2.getTime());
//////                            pq.setBloquear(false);
//////                            pq.setCierreMes(false);
//////                            pq.setFechaAsistenciInicial(fechaInicial2.getTime());
//////                            pq.setFechaAsistenciaFinal(fechaFinal2.getTime());
//////                            pq.setFechaFinal(fechaFinal2.getTime());
//////                            pq.setFechaInicial(fechaInicial2.getTime());
//////                            pq.setFechaCierre(fechaFinal2.getTime());
//////                            pq.setFechaModificado(Calendar.getInstance().getTime());
//////                            pq.setFechaPago(fechaFinal2.getTime());
//////                            pq.setTipoNomina(tn);
//////                            pq.setAño(año);
//////                            pq.setDescontarAhorro(false);
//////                            pq.setDescontarPrestamo(false);
//////                            pq.setDiasIMSS(dias2);
//////                            pq.setDiasPago(dias2);
//////                            pq.setIncluirBajas(false);
//////                            pq.setSoloPercepciones(false);
//////                            pq.setStatus(true);
//////                            pq.setTipoUso(0);
//////                            pq.setClaveUsuario(MainPrincipal.getUsuarioActual().getClave());
//////                            p.setFechaAsistenciInicial(fechaInicial.getTime());
//////                            p.setFechaAsistenciaFinal(fechaFinal.getTime());
//////                            p.setFechaFinal(fechaFinal.getTime());
//////                            p.setFechaInicial(fechaInicial.getTime());
//////                            p.setFechaCierre(fechaFinal.getTime());
//////                            p.setFechaModificado(Calendar.getInstance().getTime());
//////                            p.setFechaPago(fechaFinal.getTime());
//////                            p.setDescripcion(generaDescripcion(tn.getPeriodicidad().getDescripcion(), asignarMascara(String.valueOf(ajustaPeriodo - 1)), fechaInicial, fechaFinal));
//////                            p.setDiasIMSS(dias - dias2);
//////                            p.setDiasPago(dias - dias2);
//////
//////                            data.add(p);
//////                            if (fechaInicial2.get(Calendar.YEAR) == año) {
//////                                data.add(pq);
//////                            } else {
//////                                break;
//////                            }
//////                        } else {
//////                            data.add(p);
//////                        }
//////                        if (c.get(Calendar.YEAR) == año + 1) {
//////                            break;
//////                        }
//////
//////                    }
//////                }
//////
//////                DateCellEditor dateCellEditor = new DateCellEditor();
//////                tblPeriodosNomina.getColumnModel().getColumn(2).setCellEditor(dateCellEditor);
//////                tblPeriodosNomina.getColumnModel().getColumn(3).setCellEditor(dateCellEditor);
//////                tblPeriodosNomina.getColumnModel().getColumn(4).setCellEditor(dateCellEditor);
//////                tblPeriodosNomina.getColumnModel().getColumn(5).setCellEditor(dateCellEditor);
//////                // </editor-fold>
//////            } 
//////            // </editor-fold> 
//////            
//////            contador = contador + listPeriodosNominas.size();
//////            if (contador % rango == 0 & contador > 0) {
//////                session.flush();
//////                session.clear();
//////                contador = 0;
//////            }
            mensaje.setResultado(true);
            mensaje.setNoError(0);
            mensaje.setError("");
            session.getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("inicializarNuevaMEF()1_Error: ").append(ex));
            try {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensaje.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensaje.setError(exc.getLocalizedMessage());
            }
            mensaje.setResultado(null);
        }
        return mensaje;
    }
    
    @Override
    public Mensaje obtenerConexion(String usuario, String password, String BaseDatosConfig, String BaseDatos, String tipoServidor, String Ubicacionservidor, String puertoServidor, boolean cambiarConfiguracion) {
        mensaje = new Mensaje();
        String nombreSesionMaestra, nombreSesionSimple;
        try {
            String[] sessiones = new String[2];
            nombreSesionMaestra = usuario + "|" + BaseDatosConfig + "|" + tipoServidor + "|"
                    + Ubicacionservidor + "|" + puertoServidor + "|" + "M";
            //OJO SI SE CAMBIA LAS POSICIONES FAVOR DE CHECAR LA CLASE ConfigMascarasDAO YA QUE PARA CREAR EL ARCHIVO
            //DE MASCARA TOMO EL NOMBRE DE LA MEF COMO REFERENCIA PARA SEPARAR LAS MASCARAS POR BDS. CHECAR EL METODO getNameFileConfigurationMask
            nombreSesionSimple = usuario + "|" + BaseDatos + "|" + tipoServidor + "|"
                    + Ubicacionservidor + "|" + puertoServidor + "|" + "N";
            
            String conexionBaseMaestra = concatena.delete(0, concatena.length()).append(usuario).append("|").append(password)
                    .append("|").append(BaseDatosConfig).append("|").append(tipoServidor).append("|").append(Ubicacionservidor).append("|")
                    .append(puertoServidor).append("|").append("M").toString();
            
            String conexionBaseSimple = concatena.delete(0, concatena.length()).append(usuario).append("|").append(password)
                    .append("|").append(BaseDatos).append("|").append(tipoServidor).append("|").append(Ubicacionservidor).append("|")
                    .append(puertoServidor).append("|").append("N").toString();

            //maybe back the session here
            boolean master = hibernateUtil.cambiarConfiguraciones(conexionBaseMaestra);
            boolean mef = hibernateUtil.cambiarConfiguraciones(conexionBaseSimple);
            
            sessiones[0] = nombreSesionMaestra;
            sessiones[1] = nombreSesionSimple;

            // hibernateUtil.cambiarConfiguraciones(usuario, password, BaseDatosConfig, BaseDatos, tipoServidor, Ubicacionservidor, puertoServidor, cambiarConfiguracion);
            //   HibernateUtil.getConfigDBMaestra() != null && HibernateUtil.getConfigDBSimple() != null &&
            //HibernateUtil.getSessionFactorys().get(nombreSesionSimple) != null
            if (master && mef) {
                listContenido.clear();
                listContenido.add(sessiones);
                listContenido.add(hibernateUtil.getMensajeError());
                mensaje.setResultado(listContenido);
                mensaje.setNoError(0);
                mensaje.setError("");
            } else {
                listContenido.clear();
                listContenido.add(null);
                listContenido.add(hibernateUtil.getMensajeError());
                mensaje.setResultado(null);
                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion((Class) hibernateUtil.getMensajeError()[0]));
                mensaje.setError((String) hibernateUtil.getMensajeError()[2]);
                hibernateUtil.setMensajeError(null);
            }
            
        } catch (Exception ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerConexion()1_Error: ").append(ex));
            mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensaje.setError(ex.getLocalizedMessage());
            mensaje.setResultado(null);
        }
        return mensaje;
    }
    
    private List<ConceptoPorTipoCorrida> listConcep_tipCorr(ConcepNomDefi c, List<TipoCorrida> ltc) {
        List<ConceptoPorTipoCorrida> lc_tc = new ArrayList<ConceptoPorTipoCorrida>();
        
        for (TipoCorrida tc : ltc) {
            ConceptoPorTipoCorrida c_tc = new ConceptoPorTipoCorrida();
            c_tc.setConcepNomDefi(c);
            c_tc.setTipoCorrida(tc);
            c_tc.setCantidad(0);
            c_tc.setDescuentoCreditos(DescuentoCreditos.DescontarSoloMes);
            c_tc.setIncluir(false);
            c_tc.setModificarCantidad(false);
            c_tc.setModificarImporte(false);
            c_tc.setMostrar(false);
            c_tc.setOpcional(false);
            lc_tc.add(c_tc);
        }
        return lc_tc;
    }
    
    public Cp buscarCp(String clave, String claveCp, String claveMunicipio, String claveEstados, String clavePais, Session session) {
        Cp cp = null;
        Query query = null;
        try {
            query = session.createQuery("from " + Cp.class.getSimpleName() + " o where o.clave = :clave "
                    + "and o.ciudades.clave = :claveCp "
                    + "and o.ciudades.municipios.clave = :claveMunicipios "
                    + "and o.ciudades.municipios.estados.clave = :claveEstados "
                    + "and o.ciudades.municipios.estados.paises.clave = :clavePais ");
            query.setParameter("clave", clave);
            query.setParameter("claveCp", claveCp);
            query.setParameter("claveMunicipios", claveMunicipio);
            query.setParameter("claveEstados", claveEstados);
            query.setParameter("clavePais", clavePais);
            cp = (Cp) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarCp()1_Error: ").append(ex));
            session.getTransaction().rollback();
        }
        return cp;
    }
    
    public Ciudades buscarCiudades(String clave, String claveMunicipio, String claveEstados, String clavePais, Session session) {
        Ciudades ciudades = null;
        Query query = null;
        try {
            query = session.createQuery("from " + Ciudades.class.getSimpleName() + " o where o.clave = :clave "
                    + "and o.municipios.clave = :claveMunicipios "
                    + "and o.municipios.estados.clave = :claveEstados "
                    + "and o.municipios.estados.paises.clave = :clavePais ");
            query.setParameter("clave", clave);
            query.setParameter("claveMunicipios", claveMunicipio);
            query.setParameter("claveEstados", claveEstados);
            query.setParameter("clavePais", clavePais);
            ciudades = (Ciudades) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarCiudades()1_Error: ").append(ex));
            session.getTransaction().rollback();
        }
        return ciudades;
    }
    
    public Municipios buscarMunicipio(String clave, String claveEstados, String clavePais, Session session) {
        Municipios municipios = null;
        Query query = null;
        try {
            query = session.createQuery("from " + Municipios.class.getSimpleName() + " o where o.clave = :clave "
                    + "and o.estados.clave = :claveEstados "
                    + "and o.estados.paises.clave = :clavePais ");
            query.setParameter("clave", clave);
            query.setParameter("claveEstados", claveEstados);
            query.setParameter("clavePais", clavePais);
            municipios = (Municipios) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarMunicipio()1_Error: ").append(ex));
            session.getTransaction().rollback();
        }
        return municipios;
    }
    
    public Estados buscarEstados(String clave, String clavePais, Session session) {
        Estados estados = null;
        Query query = null;
        try {
            query = session.createQuery("from " + Estados.class.getSimpleName() + " o where o.clave = :clave and o.paises.clave = :clavePais ");
            query.setParameter("clave", clave);//AAP01
            query.setParameter("clavePais", clavePais);//AAP01
            estados = (Estados) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarEstados()1_Error: ").append(ex));
            session.getTransaction().rollback();
        }
        return estados;
    }
    
    public Paises buscarPais(String clave, Session session) {
        Paises paises = null;
        Query query = null;
        try {
            query = session.createQuery("from " + Paises.class.getSimpleName() + " where clave = :clave");
            query.setParameter("clave", clave);//AAP01
            paises = (Paises) query.uniqueResult();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("buscarPais()1_Error: ").append(ex));
            session.getTransaction().rollback();
        }
        return paises;
    }
    
    private List<BaseAfecConcepNom> buscarBaseAfecta(List<BaseNomina> listBaseNomina, ConcepNomDefi concepNomDefi) {
        List<BaseAfecConcepNom> listBaseAfecta = new ArrayList<BaseAfecConcepNom>();
        BaseAfecConcepNom baseAfecConcepNom = new BaseAfecConcepNom();
        baseAfecConcepNom.setBaseNomina(listBaseNomina.get(0));//base ISR
        baseAfecConcepNom.setConceptoDeNominaDefinicion(concepNomDefi);
        baseAfecConcepNom.setTipoAfecta(0);
        listBaseAfecta.add(baseAfecConcepNom);
        return listBaseAfecta;
    }

//    //boolean
//    public Mensaje baseDatosInicializadas(String uuidCxnMaestra, String uuidCxn) {
//        boolean[] db = new boolean[]{false, false};
//        mensaje = new Mensaje();
//        Session session;
//        Transaction transaction = null;
//        Query query;
//        try {
//            session = HibernateUtil.currentSession(uuidCxnMaestra);
//            transaction = session.beginTransaction();
//            query = session.createQuery("SELECT COUNT(c) FROM " + Contenedor.class.getSimpleName() + " c");
//            db[0] = (Long) query.uniqueResult() > 0;
//            session = HibernateUtil.currentSession(uuidCxn);
//            transaction = session.beginTransaction();
//            query = session.createQuery("SELECT COUNT(b) FROM " + Bancos.class.getSimpleName() + " b");
//            db[1] = (Long) query.uniqueResult() > 0;
//            mensaje.setNoError(0);
//            mensaje.setError("");
//            mensaje.setResultado(db);
//            transaction.commit();
//        } catch (HibernateException ex) {
//            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("baseDatosInicializadas()1_Error: ").append(ex));
//            try {
//                if (transaction != null) {
//                    transaction.rollback();
//                }
//                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
//                mensaje.setError(ex.getLocalizedMessage());
//            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
//                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
//                mensaje.setError(exc.getLocalizedMessage());
//            }
//            mensaje.setResultado(null);
//        }
//        return mensaje;
//    }
    @Override
    public Mensaje validaArchivoKey(String uuidCxn, String rutaArchivoRFC) { //byte[] archivoKey
//        boolean validacionKey = false;
        boolean validacionWeb = false;
        mensaje = new Mensaje();
        mensaje.setError("");
        mensaje.setNoError(0);
        mensaje.setResultado(null);
        byte[] archivoBytes;
        String nombreArchivo;
        
        try {
            if (rutaArchivoRFC == null ? true : rutaArchivoRFC.trim().isEmpty()) {
                mensaje.setNoError(200);
                mensaje.setError("No fue Posible validar la licencia");
                return mensaje;
            }
            
            File fileKey = new File(rutaArchivoRFC);
            if (!fileKey.exists()) {
                nombreArchivo = fileKey.getName();
                String ruta = buscaRutaArchivos(System.getProperty("user.dir"), archivosKey);
                if (ruta.isEmpty()) {
                    mensaje.setNoError(200);
                    mensaje.setError("No fue Posible validar la licencia");
                    return mensaje;
                }
                fileKey = new File(ruta.concat(System.getProperty("file.separator")).concat(nombreArchivo));
            }
            if (fileKey.exists()) {
                mensaje = Utilerias.convierteFileToBytes(fileKey);
                if (mensaje.getNoError() != 0) {
                    return mensaje;
                }
                archivoBytes = (byte[]) mensaje.getResultado();
            } else {
                mensaje.setNoError(200);
                mensaje.setError("No fue Posible validar la licencia");
                return mensaje;
            }
            
            if (archivoBytes != null) {
//                MefLicenciasDetalle detalle = (MefLicenciasDetalle) mensajeLic.getResultado();
//                mensaje = validaEnBaseDeDatos(uuidCxn, detalle.getMefLicencias().getMefRfc(), detalle.getMefLicencias().getMefRazonSocial());
//                if (mensaje.getNoError() == 0) {
//                    if ((Boolean) mensaje.getResultado() == true) {
//                        validacionKey = true;
//                    }
//                }
                mensaje = validaEnWebService(archivoBytes);
                if (mensaje.getNoError() == 0) {
                    if ((Boolean) mensaje.getResultado() == true) {
                        validacionWeb = true;
                    }
                }
            }
            
        } catch (Exception ex) {
            mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensaje.setError(ex.getMessage() == null ? "valor vacio o nulo" : ex.getMessage());
            mensaje.setResultado(null);
        }
        mensaje.setResultado(validacionWeb);
        return mensaje;
    }
    
    private Mensaje validaEnWebService(byte[] archivoKey) {
        mensaje = new Mensaje();
        mensaje.setError("");
        mensaje.setNoError(0);
        /*valida con web service*/
        String macAddress = Utilerias.getMacAddress();
        String noDiscoDuro = Utilerias.getNumeroSerieDiscoDuro();
        
        MensajeLicencia mensajeLic = util.validaLicenciaKey(archivoKey, noDiscoDuro, macAddress);
        if (mensajeLic.getNoError() == 0) {
            mensaje.setError(mensajeLic.getError());
            mensaje.setNoError(mensajeLic.getNoError());
            if (mensajeLic.getResultado() == null) {
                mensaje.setResultado(false);
            } else if (mensajeLic.getResultado() instanceof Status) {
                if ((Status) mensajeLic.getResultado() == Status.ACTIVADA) {
                    mensaje.setResultado(true);
                } else {
                    mensaje.setResultado(false);
                }
            } else {
                mensaje.setResultado(false);
            }
            return mensaje;
        } else {
            mensaje.setError(mensajeLic.getError());
            mensaje.setNoError(mensajeLic.getNoError());
            mensaje.setResultado(mensajeLic.getResultado());
            return mensaje;
        }
        
    }

//    private Mensaje validaEnBaseDeDatos(String uuidCxn, String rfc, String rasonSocial) {
//        mensaje = new Mensaje();
//        mensaje.setError("");
//        mensaje.setNoError(0);
//        mensaje.setResultado(null);
//        try {
//            Session session = null;
//            try {
//                session = HibernateUtil.currentSession(uuidCxn);
//                session.beginTransaction();
//                /*evalua si ya esta inicializada base de datos*/
//                Query query = session.createQuery("FROM " + RazonesSociales.class.getSimpleName() + " r WHERE r.rfc = :RFC AND r.razonsocial = :razonsocial");
//                query.setParameter("RFC", rfc);
//                query.setParameter("razonsocial", rasonSocial);
//                RazonesSociales r = (RazonesSociales) query.uniqueResult();
//                if (r == null) {
//                    mensaje.setResultado(false);
//                } else {
//                    mensaje.setResultado(true);
//                }
//
//                mensaje.setNoError(0);
//                mensaje.setError("");
//                session.getTransaction().commit();
//            } catch (HibernateException ex) {
//                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("validaArchivoKey()1_Error: ").append(ex));
//                try {
//                    if (session.getTransaction().isActive()) {
//                        session.getTransaction().rollback();
//                    }
//                    mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
//                    mensaje.setError(ex.getLocalizedMessage());
//                } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
//                    mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
//                    mensaje.setError(exc.getLocalizedMessage());
//                }
//                mensaje.setResultado(null);
//            } catch (Exception ex) {
//                mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
//                if (session == null) {
//                    mensaje.setError("Error al crear la conexion base de datos o esta nula");
//                } else {
//                    mensaje.setError("Error en la conexion base de datos");
//                }
//                mensaje.setResultado(null);
//            }
//        } catch (Exception ex) {
//            mensaje.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
//            mensaje.setError(ex.getMessage() == null ? "valor vacio o nulo" : ex.getMessage());
//            mensaje.setResultado(null);
//        }
//        return mensaje;
//    }
    @Override
    public String getVersion() {
        return "1.0";
    }
}
