/**
 * @author: Abraham Daniel Arjona Peraza Fecha de Creación: 04/08/2011 Compañía:
 * Exito Software. Descripción del programa: clase configuracion de mascaras
 * para llamados a metodos de HIBERNATE
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Sanchez Acosta Fecha:10/11/2011 Descripción:Se
 * cambiaron todos los metodos hibernate al dao
 * -----------------------------------------------------------------------------
 * Clave:JSA02 Autor:Jose Armando Sanchez Acosta Fecha:08/10/2014 Descripción:Se
 * agrego el el metodo getSaveMascarasAfectaClaves para reEstructurar las claves
 * asi como tmb las tablas datos.
 * -----------------------------------------------------------------------------
 * Clave:JSA03 Autor:Jose Armando Sanchez Acosta Fecha:18/02/2015 Descripción:
 * Se le agrego al metodo getSaveMascarasAfectaClaves el generar el archivo de
 * properties de las mascaras. Se agrego el metodo getFilePropertiesMascara para
 * obtener el nombre del properties de las mascara por base de datos ya que se
 * puede tener varias bds en el mismo servidor.
 * -----------------------------------------------------------------------------
 * Clave:JSA04 Autor:Jose Armando Sanchez Acosta Fecha:30/04/2015 Descripción:
 * Se modifico el query para modificar las claves cuando la mascara es
 * alfanumerica. Tambien para reducir las claves y se contemplo si la mascara es
 * numerica convertirlo a long para ver si hubo en las claves letras y no
 * provoque algun error.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.Mascaras;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.RazonSocial;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.TipoControlador;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.exception.SQLGrammarException;
import org.hibernate.mapping.Table;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ConfigMascarasDAO extends GenericHibernateDAO<Mascaras, String>
        implements ConfigMascarasDAOIF {

    private final StringBuilder concatena = new StringBuilder(200);
    private final String msgError = concatena.append("ServerERP"/*util.getContexto()*/).append(".MSERR_F_").append(this.getClass().getName()).append(".").toString();
//    public String uuidCxnMaestra;
    private final String DEFAULT_FILE = "ConfigMascaras";

    public Mensaje agregar(Mascaras entity, String uuidCxn) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            Mascaras mask = makePersistent(entity);
            mensajeResultado.setResultado(mask);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("agregar()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje actualizar(Mascaras entity, String uuidCxn) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makePersistent(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("actualizar()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje eliminar(Mascaras entity, String uuidCxn) {//JSA01
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            makeTransient(entity);
            mensajeResultado.setResultado(true);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("eliminar()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje getConfigMascarasAll(String uuidCxn) {//JSA01
        List<Mascaras> values;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Mascaras");
            values = q.list();
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getConfigMascarasAll()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje getConfigMascarasPorClave(String clave, String uuidCxn) {//JSA01
        Mascaras p;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            q = getSession().createQuery("from Mascaras where clave = :clave");
            q.setParameter("clave", clave);
            p = (Mascaras) q.uniqueResult();
            mensajeResultado.setResultado(p);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getConfigMascarasPorClave()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje getSaveMascarasAfectaClaves(List<Mascaras> listMascaras, boolean soloAplicarReEstructuracion, byte[] propertiesMascara, String ubicacion, String uuidCxn, String uuidCxnMaestra) {//JSA02
        mensajeResultado.setNoError(0);
        mensajeResultado.setError("");
        try {
            inicializaVariableMensaje();
            try {
                Object obj = null;
                ByteArrayInputStream bis = null;
                ObjectInputStream ois = null;
                try {
                    bis = new ByteArrayInputStream(propertiesMascara);
                    ois = new ObjectInputStream(bis);
                    try {
                        obj = ois.readObject();
                        ((Properties) obj).store(new FileOutputStream(ubicacion + getNameFileConfigurationMask(uuidCxn)), null);//JSA03
                    } catch (ClassNotFoundException ex) {
                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                        mensajeResultado.setError(ex.getLocalizedMessage());
                        return mensajeResultado;
                    }
                } finally {
                    if (bis != null) {
                        bis.close();
                    }
                    if (ois != null) {
                        ois.close();
                    }
                }
            } catch (FileNotFoundException ex) {
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
                return mensajeResultado;
            } catch (IOException ex) {
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ubicacion + getNameFileConfigurationMask(uuidCxn) + " (" + ex.getLocalizedMessage() + ")");
                return mensajeResultado;
            }
            int i;
            for (i = 0; i < listMascaras.size(); i++) {
                if (!soloAplicarReEstructuracion) {
                    setSession(HibernateUtil.currentSession(uuidCxn));
                    getSession().beginTransaction();
                    makePersistent(listMascaras.get(i));
                    getSession().getTransaction().commit();
                }
                if (listMascaras.get(i).getMascara().length() > 0) {
                    String tabla = listMascaras.get(i).getClave().substring(0, listMascaras.get(i).getClave().indexOf("Clave"));
                    ///////System.out.println("Tabla " + tabla);
                    if (existeTablaMEFMaster(tabla)) {
                        setSession(HibernateUtil.currentSession(uuidCxnMaestra));
                    } else {
                        setSession(HibernateUtil.currentSession(uuidCxn));
                    }
                    getSession().beginTransaction();
                    concatena.delete(0, concatena.length()).append("UPDATE ").append(tabla).append(" e ");
                    concatena.append("SET ").append(" e.clave = ");
                    String mascaras;
                    boolean mascaraAlfanumerico = false;
                    if (listMascaras.get(i).getMascara().indexOf("A") > -1) {
                        mascaraAlfanumerico = true;
                    }
                    mascaras = listMascaras.get(i).getMascara().replace("#", "0");
                    if (!mascaraAlfanumerico) {//JSA04
                        if (HibernateUtil.usaTypeBigInt) {
                            concatena.append("(CASE WHEN CAST(e.clave as int) > 0 THEN ");
                        } else {
                            concatena.append("(CASE WHEN CAST(e.clave as long) > 0 THEN ");
                        }
                        concatena.append("(CASE WHEN length('").append(mascaras).append("') = length(e.clave) THEN e.clave ");
                        concatena.append("when length(e.clave) < length('").append(mascaras).append("') then (concat(SUBSTRING('").append(mascaras).append("', 1, ");
                        concatena.append("length('").append(mascaras).append("') - length(e.clave)),e.clave)) ");
                        concatena.append("else SUBSTRING(e.clave, length(e.clave) - length('").append(mascaras).append("')+1,length(e.clave) ) END)  END )");
                    } else {
                        concatena.append("(CASE WHEN length('").append(mascaras).append("') = length(e.clave) or length(e.clave) < length('").append(mascaras).append("') ");
                        concatena.append("THEN e.clave else SUBSTRING(e.clave, length(e.clave) - length('").append(mascaras).append("')+1,length(e.clave) ) END )");
                    }
                    try {
                       ///////// System.out.println("salida mascaraAlfanumerico " + mascaraAlfanumerico + " " + concatena.toString());
                        q = getSession().createQuery(concatena.toString());
                        int x = q.executeUpdate();
                        getSession().getTransaction().commit();
                    } catch (HibernateException ex) {
                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                        mensajeResultado.setError(ex.getLocalizedMessage());
                        listMascaras.get(i).setMessage(ex.getMessage());
                        try {
                            if (getSession().getTransaction().isActive()) {
                                getSession().getTransaction().rollback();
                            }
                            if (ex instanceof SQLGrammarException || ex instanceof ConstraintViolationException) {
                                mensajeResultado.setNoError(1);
                                if (ex instanceof ConstraintViolationException) {
                                    listMascaras.get(i).setMessage("Se generan claves duplicadas.");
                                } else {
                                    listMascaras.get(i).setMessage("Existen letras en estas claves y no se puede convertir a numerico.");
                                }
                                if (listMascaras.get(i).getMascaraOriginal() != null) {
                                    listMascaras.get(i).setMascara(listMascaras.get(i).getMascaraOriginal());
                                }
                                if (!soloAplicarReEstructuracion) {
                                    setSession(HibernateUtil.currentSession(uuidCxn));
                                    getSession().beginTransaction();
                                    makePersistent(listMascaras.get(i));
                                    getSession().getTransaction().commit();
                                }
                                Properties properties = obtenerPropertiesMascara(ubicacion, uuidCxn);
                                if (properties != null) {
                                    properties.setProperty(listMascaras.get(i).getClave(), listMascaras.get(i).getMascara());
                                    listMascaras.get(i).setMascaraOriginal(null);
                                    try {
                                        properties.store(new FileOutputStream(ubicacion + getNameFileConfigurationMask(uuidCxn)), null);
                                    } catch (IOException exx) {
                                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exx.getClass()));
                                        mensajeResultado.setError(exx.getLocalizedMessage());
                                    }
                                }
                            }
                        } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                            mensajeResultado.setError(exc.getLocalizedMessage());
                        }
                        mensajeResultado.setResultado(null);
                    }
                    if (RazonesSociales.class.getSimpleName().equalsIgnoreCase(tabla)) {
                        tabla = RazonSocial.class.getSimpleName();
                        ////////System.out.println("Tabla " + tabla);
                        if (existeTablaMEFMaster(tabla)) {
                            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
                        } else {
                            setSession(HibernateUtil.currentSession(uuidCxn));
                        }
                        getSession().beginTransaction();
                        concatena.delete(0, concatena.length()).append("UPDATE ").append(tabla).append(" e ");
                        concatena.append("SET ").append(" e.claveRazonSocial = ");
                        if (!mascaraAlfanumerico) {//JSA04
                            if (HibernateUtil.usaTypeBigInt) {
                                concatena.append("(CASE WHEN CAST(e.claveRazonSocial as int) > 0 THEN ");
                            } else {
                                 concatena.append("(CASE WHEN CAST(e.claveRazonSocial as long) > 0 THEN ");
                            }
                            concatena.append("(CASE WHEN length('").append(mascaras).append("') = length(e.claveRazonSocial) THEN e.claveRazonSocial ");
                            concatena.append("when length(e.claveRazonSocial) < length('").append(mascaras).append("') then (concat(SUBSTRING('").append(mascaras).append("', 1, ");
                            concatena.append("length('").append(mascaras).append("') - length(e.claveRazonSocial)),e.claveRazonSocial)) ");
                            concatena.append("else SUBSTRING(e.claveRazonSocial, length(e.claveRazonSocial) - length('").append(mascaras).append("')+1,length(e.claveRazonSocial) ) END)  END )");
                        } else {
                            concatena.append("(CASE WHEN length('").append(mascaras).append("') = length(e.claveRazonSocial) or length(e.claveRazonSocial) < length('").append(mascaras).append("') ");
                            concatena.append("THEN e.claveRazonSocial else SUBSTRING(e.claveRazonSocial, length(e.claveRazonSocial) - length('").append(mascaras).append("')+1,length(e.claveRazonSocial) ) END )");
                        }
                        try {
                            q = getSession().createQuery(concatena.toString());
                            int x = q.executeUpdate();
                            getSession().getTransaction().commit();
                        } catch (Exception ex) {
                            mensajeResultado.setNoError(1);
                            listMascaras.get(i).setMessage(tabla + " " + ex.getMessage());
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
                    }
                }
            }
            if (getSession() != null) {
                if (getSession().isOpen()) {
                    getSession().flush();
                }
            }
            setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            getSession().beginTransaction();
            concatena.delete(0, concatena.length()).append("from TablaDatos td where length(trim(td.controladores)) > 0");
            List<TablaDatos> listTablaDato;
            try {
                q = getSession().createQuery(concatena.toString());
                listTablaDato = q.list();
                int j, k;
                if (listTablaDato != null) {
                    for (i = 0; i < listTablaDato.size(); i++) {
                        List<String> controladoresEntidad = convierteXML(listTablaDato.get(i).getTablaBase() != null ? listTablaDato.get(i).getTablaBase().getFileXml() : listTablaDato.get(i).getTablaPersonalizada().getFileXml());
                        if (controladoresEntidad == null) {
                            controladoresEntidad = new ArrayList<String>();
                        }
                        String[] controladores = listTablaDato.get(i).getControladores().split("#");
                        for (j = 0; j < controladores.length; j++) {
                            for (k = 0; k < controladoresEntidad.size(); k++) {
                                String[] controlador = controladoresEntidad.get(k).split("\\.");
                                if (controladores[j].contains(controlador[controlador.length - 1])) {
                                    String tabla, valor;
                                    tabla = controladores[j].substring(0, controlador[controlador.length - 1].length());
                                    valor = controladores[j].substring(controlador[controlador.length - 1].length());
                                    Mascaras m = buscarMascara(tabla, listMascaras);
                                    if (m != null) {
                                        String mascaras = m.getMascara().replace("#", "0");
                                        controladores[j] = tabla + (mascaras.substring(0, mascaras.length() - valor.length()) + valor);
                                    }
                                    break;
                                }
                            }
                        }
                        concatena.delete(0, concatena.length());
                        if (controladores.length > 1) {
                            concatena.append(controladores[0]);
                            for (j = 1; j < controladores.length; j++) {
                                concatena.append("#").append(controladores[j]);
                            }
                            listTablaDato.get(i).setControladores(concatena.toString());
                        } else {
                            listTablaDato.get(i).setControladores(controladores[0]);
                        }
                        getSession().saveOrUpdate(listTablaDato.get(i));
                    }
                }
                getSession().getTransaction().commit();
            } catch (HibernateException ex) {
                mensajeResultado.setNoError(3);
                mensajeResultado.setError("Error tabla datos xml.- " + ex.getMessage());
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
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getSaveMascarasAfectaClaves()1_Error: ").append(ex));
            mensajeResultado.setNoError(2);
            mensajeResultado.setError(ex.getMessage());
            try {
                if (getSession() != null) {
                    if (getSession().getTransaction() != null) {
                        if (!getSession().getTransaction().wasRolledBack()) {
                            getSession().getTransaction().rollback();
                        }
                    }
                }
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
            } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                mensajeResultado.setError(exc.getLocalizedMessage());
            }
            mensajeResultado.setResultado(null);
        }
        if (mensajeResultado.getNoError() == 1) {
            mensajeResultado.setResultado(listMascaras);
        } else if (mensajeResultado.getNoError() == 0) {
            mensajeResultado.setResultado(listMascaras);
        }
        return mensajeResultado;
    }

    private List<String> convierteXML(byte[] xmlString) {
        List<String> controladores = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builderXml = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(new String(xmlString)));
            Document xmlDoc = builderXml.parse(is);

            Node node = xmlDoc.getDocumentElement();

            NodeList list;
            int i;

            node = xmlDoc.getElementsByTagName("Controladores").item(0);
            if (node != null) {
                list = node.getChildNodes();
                controladores = new ArrayList<String>();
                for (i = 0; i < list.getLength(); i++) {
                    Element element = (Element) list.item(i);
                    if (element.hasAttributes()) {
                        if (TipoControlador.getEnum(element.getAttribute("identificador")) == TipoControlador.CONTROLADORENTIDAD) {
                            element.getAttribute("entidad");
                            element.getAttribute("sistema");
                            controladores.add(element.getAttribute("entidad"));
                        }
                    }
                }
            }

        } catch (SAXException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("convierteXML()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        } catch (IOException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("convierteXML()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        } catch (ParserConfigurationException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("convierteXML()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return controladores;
    }

    private Mascaras buscarMascara(String Tabla, List<Mascaras> listMask) {
        for (int i = 0; i < listMask.size(); i++) {
            if (listMask.get(i).getClave().substring(0, listMask.get(i).getClave().indexOf("Clave")).equalsIgnoreCase(Tabla) & listMask.get(i).getMessage() == null) {
                return listMask.get(i);
            }
        }
        return null;
    }

    private Boolean existeTablaMEFMaster(String tabla) {//JSA01
        Iterator iterator;
        Boolean existeTabla = false;
        Boolean continuar = true;
        iterator = HibernateUtil.getConfigDBMaestra().getTableMappings();
        //iterator = HibernateUtil.getConfigMEFMaster().getTableMappings();
        while (continuar) {
            if (iterator.hasNext()) {
                Table element = (Table) iterator.next();
                if (element.getName().equals(tabla)) {
                    existeTabla = true;
                    continuar = false;
                }
            } else {
                continuar = false;
            }
        }
        return existeTabla;
    }

    public Mensaje consultaPorRangos(Integer inicio, Integer rango, String uuidCxn) {//JSA01
        List<Mascaras> listEsp;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            listEsp = (List<Mascaras>) consultaPorRangos(inicio, rango, null, null);
            mensajeResultado.setResultado(listEsp);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("consultaPorRangos()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje existeDato(String campo, Object valor, String uuidCxn) {//JSA01
        Boolean b;
        try {
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            b = existeDato("Mascaras", campo, valor);
            mensajeResultado.setResultado(b);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("existeDato()1_Error: ").append(ex));
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
        return mensajeResultado;
    }

    public Mensaje getFilePropertiesMascara(String directorioReportesDelSistema, String uuidCxn) {//JSA03
        try {
            inicializaVariableMensaje();
            Properties properties = obtenerPropertiesMascara(directorioReportesDelSistema, uuidCxn);
            byte[] serializedPrint;
            if (properties != null) {
                serializedPrint = convertirObjectToByte(properties);
                if (mensajeResultado.getNoError() == 0) {
                    mensajeResultado.setResultado(serializedPrint);
                }
            }
        } catch (HibernateException ex) {
            System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("getConfigMascarasAll()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return mensajeResultado;
    }

    private Properties obtenerPropertiesMascara(String directorioReportesDelSistema, String uuidCxn) {
        concatena.delete(0, concatena.length());
        concatena.append(directorioReportesDelSistema);
        if (!directorioReportesDelSistema.substring(directorioReportesDelSistema.length() - 2, directorioReportesDelSistema.length()).contains(System.getProperty("file.separator"))) {
            concatena.append(System.getProperty("file.separator"));
        }
        concatena.append(getNameFileConfigurationMask(uuidCxn));
        String ubicacionFile = concatena.toString();
        File file = new File(ubicacionFile);
        Properties properties = null;
        if (file.exists()) {
            properties = abrirPropiedad(ubicacionFile);
        } else {
            InputStream is = getClass().getResourceAsStream(concatena.delete(0, concatena.length()).append("/com/mef/erp/util/").append(DEFAULT_FILE).append(".properties").toString());
            try {
                properties = new Properties();
                properties.load(is);
            } catch (IOException ex) {
                System.err.println(concatena.delete(0, concatena.length()).append(msgError).append("obtenerPropertiesMascara()1_Error: ").append(ex));
                mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
                mensajeResultado.setError(ex.getLocalizedMessage());
                mensajeResultado.setResultado(null);
            }
        }
        return properties;
    }

    private byte[] convertirObjectToByte(Object print) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        byte[] serializedPrint = null;

        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(print);
            serializedPrint = byteArrayOutputStream.toByteArray();
        } catch (IOException ex) {
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return serializedPrint;
    }

    private Properties abrirPropiedad(String file) {
        Properties properties = null;
        try {
            properties = new Properties();
            properties.load(new FileInputStream(file));

        } catch (IOException ex) {
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return properties;
    }

    private String getNameFileConfigurationMask(String uuidCxn) {//JSA03
        String[] estructuraConexion = uuidCxn.split("\\|");
        return DEFAULT_FILE + estructuraConexion[1] + ".properties";
    }
}
