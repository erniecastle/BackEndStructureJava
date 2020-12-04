package com.mef.erp.util;

import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Admin
 */
public class UtilidadesXML {

    /**
     * Este método convierte un xml que esta en bytes[] a doc xml de tipo
     * org.w3c.dom.Document.
     *
     * @param xmlString: son los bytes del xml a convertir.
     * @return
     */
    public static int ERROR_XML = 0;

    public static Document convierteBytesToXML(byte[] xmlString) {
        Document doc = null;
        try {
            ERROR_XML = 0;
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(new String(xmlString, Charset.forName("UTF-8"))));
            is.setEncoding("UTF-8");
            doc = builder.parse(is);
        } catch (SAXException ex) {
            ERROR_XML = 1;
        } catch (IOException ex) {
            ERROR_XML = 2;
        } catch (ParserConfigurationException ex) {
            ERROR_XML = 3;
        }
        return doc;
    }

    public static String nodeToString(Node node) {
        StringWriter sw = new StringWriter();
        try {
            ERROR_XML = 0;
            Transformer t = TransformerFactory.newInstance().newTransformer();
            t.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
            t.transform(new DOMSource(node), new StreamResult(sw));
        } catch (TransformerException te) {
            ERROR_XML = 4;
        }
        return sw.toString();
    }

    private void removerEspacioBlancoXML(Document doc) {
        ERROR_XML = 0;
        try {
            if (doc != null) {
                XPath xPath = XPathFactory.newInstance().newXPath();
                NodeList nodeList = (NodeList) xPath.evaluate("//text()[normalize-space()='']",
                        doc,
                        XPathConstants.NODESET);

                for (int i = 0; i < nodeList.getLength(); ++i) {
                    Node node = nodeList.item(i);
                    node.getParentNode().removeChild(node);
                }
            }
        } catch (XPathExpressionException e) {
            ERROR_XML = 5;
        } catch (DOMException e) {
            ERROR_XML = 5;
        }
    }

    public String docXmlToString(Document doc) {
        String xmlString = "";
        try {
            ERROR_XML = 0;
            removerEspacioBlancoXML(doc);
            if (ERROR_XML == 0) {
                OutputFormat format = new OutputFormat(doc);
                format.setLineWidth(0);
                format.setPreserveSpace(false);
                CharArrayWriter salidaXML = new CharArrayWriter();
                XMLSerializer serializer = new XMLSerializer((Writer) salidaXML, format);
                serializer.asDOMSerializer();
                serializer.serialize(doc);
                xmlString = salidaXML.toString();
            }
        } catch (Exception ex) {
            ERROR_XML = 5;
        }
        return xmlString;
    }

    public static Object[][] extraeValoresNodos(Document doc) {
        Object[][] valores = null;
        try {
            ERROR_XML = 0;
            XPath xpath = XPathFactory.newInstance().newXPath();
            /*cuenta cuantos nodos con etiqueta dato hay*/
            XPathExpression expr = xpath.compile("count(//dato)");
            int cantDatos = ((Double) expr.evaluate(doc.getDocumentElement(), XPathConstants.NUMBER)).intValue();
            if (cantDatos > 0) {
                /*cuenta nodos hijos en etiqueta datos*/
                expr = xpath.compile("count(//dato[1]/*)");
                int cantNodos = ((Double) expr.evaluate(doc, XPathConstants.NUMBER)).intValue();
                if (cantNodos > 0) {
                    valores = new Object[cantDatos][cantNodos];
                    expr = xpath.compile("//dato/*/text()");
                    Object result = expr.evaluate(doc, XPathConstants.NODESET);
                    NodeList nodes = (NodeList) result;
                    int col = 0, ren = 0;
                    for (int i = 0; i < nodes.getLength(); i++) {
                        valores[ren][col] = nodes.item(i).getNodeValue();
                        if (col < cantNodos - 1) { //-1 por que inicia en 0 el arreglo
                            col++;
                        } else {
                            ren++;
                            col = 0;
                        }
                    }
                    result = null;
                    nodes = null;
                }
            }
            xpath = null;
            expr = null;
        } catch (XPathExpressionException ex) {
            ERROR_XML = 6;
            Logger.getLogger(UtilidadesXML.class.getName()).log(Level.SEVERE, null, ex);
        }
        return valores;
    }

    /**
     * Este método busca un fichero de tipo XML en el classpath crea un objeto
     * de tipo org.w3c.dom.Document.
     *
     * @param fichero: El nombre del fichero a procesar.
     * @return
     * @throws Exception
     */
    public static Document File2Document(String fichero) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        ClassLoader loader = (UtilidadesXML.class).getClassLoader();
        URL urlfichero = loader.getResource(fichero);
        Document XMLDoc = factory.newDocumentBuilder().parse(new InputSource(urlfichero.openStream()));
        return XMLDoc;
    }

    /**
     * Este método convierte un objeto de tipo org.w3c.dom.Node a String
     *
     * @param nodo
     * @return
     * @throws Exception
     */
    public static String Node2String(Node nodo) throws Exception {
        StringWriter sw = new StringWriter();
        StreamResult sR = new StreamResult(sw);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(nodo), sR);
        return sw.toString();
    }

    /**
     * Este método convierte un objeto de tipo org.w3c.dom.NodeList a String
     * Este método solo sirve para pintar los resultados.
     *
     * @param nodo
     * @return
     * @throws Exception
     */
    public static String Nodes2String(NodeList nodes) throws Exception {
        StringBuilder lista = new StringBuilder();

        for (int i = 0; i < nodes.getLength(); i++) {
            Node node = (Node) nodes.item(i);
            lista.append(UtilidadesXML.Node2String(node));
        }

        return lista.toString();
    }

    /**
     * Devuelve true si el nodo es de tipo Node.ELEMENT_NODE y se llama "nombre"
     *
     * @param nodo
     * @param nombre
     * @return
     */
    public static boolean esNodo(Node nodo, String nombre) {
        return (esNodo(nodo) && (nodo.getNodeName().equalsIgnoreCase(nombre)));
    }

    /**
     * Devuelve true si el nodo es de tipo Node.ELEMENT_NODE
     *
     * @param nodo
     * @param nombre
     * @return
     */
    public static boolean esNodo(Node nodo) {
        return (nodo.getNodeType() == Node.ELEMENT_NODE);
    }

    /**
     * Devuelve true si el nodo es de tipo TEXT_NODE
     *
     * @param nodo
     * @return
     */
    public static boolean esTexto(Node nodo) {
        return (nodo.getNodeType() == Node.TEXT_NODE);
    }

    /**
     * Devuelve el texto de un nodo: <tag>TEXTO</tag>
     *
     * @param n
     * @return
     */
    public static String getTexto(Node n) {
        NodeList nl = n.getChildNodes();
        Node act = null;
        for (int i = 0; i < nl.getLength(); i++) {
            act = nl.item(i);
            if (act == null) {
                return null;
            }
            if ((act.getNodeType() == Node.CDATA_SECTION_NODE) || (act.getNodeType() == Node.TEXT_NODE)) {
                return act.getNodeValue();
            }
        }
        return "";
    }

    /**
     * Devuelve el valor del atributo "nombre" de un nodo
     *
     * @param nombre
     * @param nodo
     * @return
     */
    public static String dameAtributoNodo(String nombre, Node nodo) {
        NamedNodeMap mapa = nodo.getAttributes();
        String valor = null;
        if (mapa != null) {
            Node nodoAt = mapa.getNamedItem(nombre);
            if (nodoAt != null) {
                valor = nodoAt.getNodeValue();
            }
        }
        return valor;
    }
}
