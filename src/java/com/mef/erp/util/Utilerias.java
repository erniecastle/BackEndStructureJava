/**
 * @author: Fecha de Creación: Compañía: Descripción del programa:
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave:JSA01 Autor:Jose Armando Fecha:05/05/2015 Descripción: Se agrego el
 * codigo para order una lista.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.util;

import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.valida.MensajeLicencia;
import com.mef.valida.ws.WSControlLicencias;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author User
 */
public class Utilerias {
    /*
     * castea un string al tipo de clase de la columna del grid
     */

    private WSControlLicencias controlLicencias;

    public static Object castStringTo(String tipoDato, String valor) {
        Class<?> cls;
        Method method;
        Casting casting = new Casting();
        Object o = null;
        tipoDato = tipoDato.substring(tipoDato.lastIndexOf('.') + 1);
        cls = Casting.class;
        try {
            method = cls.getDeclaredMethod("set".concat(tipoDato), String.class);
            method.invoke(casting, valor);
            method = cls.getDeclaredMethod("get".concat(tipoDato), (Class<?>[]) null);
            o = method.invoke(casting, (Object[]) null);
        } catch (IllegalAccessException ex) {
            System.err.println("castStringTo()1_Error: ".concat(ex.getMessage()));
        } catch (InvocationTargetException ex) {
            System.err.println("castStringTo()1_Error: ".concat(ex.getMessage()));
        } catch (SecurityException e) {
            System.err.println("castStringTo()1_Error: ".concat(e.getMessage()));
        } catch (NoSuchMethodException e) {
            System.err.println("castStringTo()1_Error: ".concat(e.getMessage()));
        } catch (IllegalArgumentException e) {
            System.err.println("castStringTo()1_Error: ".concat(e.getMessage()));
        }
        return o;
    }

    public static Class buscarTipoDatoCampo(String pathCampo) {
        Class tipoDato = null;
        try {
            String[] path = pathCampo.split("\\.");
            tipoDato = Class.forName("com.mef.erp.modelo.entidad.".concat(path[0]));

            if (path.length > 1) {
                Field field = tipoDato.getDeclaredField(path[1]);
                if (field.getType().equals(List.class)) {
                    tipoDato = tipoDatoDeLista(field);
                } else {
                    tipoDato = field.getType();
                }
                if (path.length > 2) {
                    int i;
                    StringBuilder ruta = new StringBuilder(tipoDato.getSimpleName());
                    for (i = 2; i < path.length; i++) {
                        ruta.append(".").append(path[i]);
                    }
                    tipoDato = buscarTipoDatoCampo(ruta.toString());
                }
            }
        } catch (ClassNotFoundException ex) {
            System.err.println("buscarTipoDatoCampo()_Error".concat(ex.getMessage()));
        } catch (NoSuchFieldException ex) {
            System.err.println("buscarTipoDatoCampo()_Error".concat(ex.getMessage()));
        } catch (SecurityException ex) {
            System.err.println("buscarTipoDatoCampo()_Error".concat(ex.getMessage()));
        }
        return tipoDato;
    }

    private static Class tipoDatoDeLista(Field method) {
        try {
            java.lang.reflect.Type returnType = method.getGenericType();
            if (returnType instanceof ParameterizedType) {
                ParameterizedType type = (ParameterizedType) returnType;
                java.lang.reflect.Type[] typeArguments = type.getActualTypeArguments();
                Class typeArgClass = null;
                for (java.lang.reflect.Type typeArgument : typeArguments) {
                    typeArgClass = (Class) typeArgument;
                }
                if (typeArgClass != null) {
                    return typeArgClass;
                }
            }
        } catch (Exception e) {
            System.err.println("tipoDatoDeLista()_Error".concat(e.getMessage()));
        }
        return Class.class;
    }

    public static void ordena(List lista, final String propiedad) {
        Collections.sort(lista, new Comparator() {

            @Override
            public int compare(Object obj1, Object obj2) {

                Class clase = obj1.getClass();

                String getter;
                ///@deja normal texto de la propiedad
                if (propiedad.startsWith("@")) {
                    getter = propiedad.substring(1, propiedad.length());
                } else {
                    getter = "get" + Character.toUpperCase(propiedad.charAt(0)) + propiedad.substring(1);
                }
                try {
                    Method getPropiedad = clase.getMethod(getter);
                    boolean isString = false;
                    Class tipoDato = getPropiedad.getReturnType();
                    Object propiedad1, propiedad2;
                    if (tipoDato.equals(String.class)) {
                        isString = true;
                    }

                    if (tipoDato.equals(Date.class)) {
                        propiedad1 = getPropiedad.invoke(obj1);
                        propiedad2 = getPropiedad.invoke(obj2);
                    } else {
                        propiedad1 = getPropiedad.invoke(obj1).toString();
                        propiedad2 = getPropiedad.invoke(obj2).toString();
                    }
                    if (isString) { ///convierte a minusculas los valores para el ordenar los valores string
                        propiedad1 = String.valueOf(propiedad1).toLowerCase();
                        propiedad2 = String.valueOf(propiedad2).toLowerCase();
                    }
                    if (propiedad1 instanceof Comparable && propiedad2 instanceof Comparable) {
                        Comparable prop1 = (Comparable) propiedad1;
                        Comparable prop2 = (Comparable) propiedad2;
                        if (tipoDato.equals(Date.class)) {
                            Date date1 = (Date) propiedad1;
                            Date date2 = (Date) propiedad2;
                            return date1.compareTo(date2);
                        }
                        return prop1.compareTo(prop2);
                    }//CASO DE QUE NO SEA COMPARABLE  
                    else {
                        if (tipoDato.equals(Date.class)) {
                            Date date1 = (Date) propiedad1;
                            Date date2 = (Date) propiedad2;
                            return date1.compareTo(date2);
                        } else {
                            if (propiedad1.equals(propiedad2)) {
                                return 0;
                            } else {
                                return 1;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("ERROR ordena " + e.getMessage());
                }
                return 0;
            }
        });
    }

    public static String getNumeroSerieDiscoDuro() {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") >= 0) {
            return Utilerias.getHDSerialWindows("C");
        } else if ((OS.indexOf("nux") >= 0)) {
            return getVolHardDiskLinux();
        } else {
            return "";
        }
    }

    private static String getHDSerialWindows(String drive) {
        String result = "";
        try {
            //File file = File.createTempFile("tmp",".vbs");  
            File file = File.createTempFile("tmp", ".vbs");
            file.deleteOnExit();
            FileWriter fw = new java.io.FileWriter(file);

            String vbs = "Set objFSO = CreateObject(\"Scripting.FileSystemObject\")\n" + "Set colDrives = objFSO.Drives\n"
                    + "Set objDrive = colDrives.item(\"" + drive + "\")\n" + "Wscript.Echo objDrive.SerialNumber";
            fw.write(vbs);
            fw.close();
            Process p = Runtime.getRuntime().exec("cscript //NoLogo " + file.getPath());
            BufferedReader input
                    = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (Exception e) {

        }
        if (result.trim().length() < 1 || result == null) {
            result = "NO_DISK_ID";

        }

        return result.trim();
    }

    private static String getVolHardDiskLinux() {
        String query = "/sbin/udevadm info --query=property --name=sda";
        String serial = "";
        try {
            Runtime rt = Runtime.getRuntime();
            InputStream is = rt.exec(query).getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            for (String outputLine = ""; outputLine != null; outputLine = br.readLine()) {
                System.out.println(outputLine);
                if (outputLine.startsWith("ID_SERIAL_SHORT")) {
                    serial = outputLine.substring(outputLine.lastIndexOf("=") + 1);
                    break;
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            serial = "";
        }
        return serial;
    }

    public static String getMacAddress() {
        InetAddress ip;
        try {

            String mac;
            ip = InetAddress.getLocalHost();

            Enumeration e = NetworkInterface.getNetworkInterfaces();

            while (e.hasMoreElements()) {

                NetworkInterface n = (NetworkInterface) e.nextElement();
                Enumeration ee = n.getInetAddresses();
                while (ee.hasMoreElements()) {
                    InetAddress i = (InetAddress) ee.nextElement();
                    if (!i.isLoopbackAddress() && !i.isLinkLocalAddress() && i.isSiteLocalAddress()) {
                        ip = i;
                    }
                }
            }

            NetworkInterface network = NetworkInterface.getByInetAddress(ip);
            byte[] mac_byte = network.getHardwareAddress();

            StringBuilder sb = new StringBuilder();

            if (mac_byte.length > 5) {
                for (int i = 0; i < mac_byte.length - 1; i++) {
                    sb.append(String.format("%02X%s", mac_byte[i], (i < mac_byte.length - 2) ? ":" : ""));
                }
            } else {
                for (int i = 0; i < mac_byte.length; i++) {
                    sb.append(String.format("%02X%s", mac_byte[i], (i < mac_byte.length - 1) ? ":" : ""));
                }
            }

            mac = sb.toString();
            return mac;
        } catch (UnknownHostException e) {
            e.printStackTrace();
            return "";
        } catch (SocketException e) {
            e.printStackTrace();
            return "";
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    private static String obtainMacAddressWinLin() {
        try {
            boolean isWin = System.getProperty("os.name").toLowerCase().indexOf("win") != -1;

            Process aProc = Runtime.getRuntime().exec(
                    isWin ? "ipconfig /all" : "/sbin/ifconfig -a");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(new DataInputStream(aProc.getInputStream())));

            Pattern macAddressPattern = Pattern.compile(
                    "((\\p{XDigit}\\p{XDigit}"
                    + (isWin ? "-" : ":")
                    + "){5}\\p{XDigit}\\p{XDigit})"
            );

            for (String outputLine = ""; outputLine != null; outputLine = br.readLine()) {
                Matcher macAddressMatcher = macAddressPattern.matcher(outputLine);
                if (macAddressMatcher.find()) {
                    return macAddressMatcher.group(0);
                }
            }

            aProc.destroy();
            aProc.waitFor();

            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return "";
        } catch (InterruptedException ex) {
            ex.printStackTrace();
            return "";
        }
    }

    public static Mensaje convierteFileToBytes(File file) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError("");
        mensaje.setNoError(0);
        byte[] bFile = new byte[(int) file.length()];
        FileInputStream fileInputStream;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(bFile);
            fileInputStream.close();
            mensaje.setResultado(bFile);
        } catch (FileNotFoundException ex) {
            mensaje.setError(ex.getMessage());
            mensaje.setNoError(100);
            mensaje.setResultado(null);
        } catch (IOException ex) {
            mensaje.setError(ex.getMessage());
            mensaje.setNoError(100);
            mensaje.setResultado(null);
        }
        return mensaje;
    }

    /*String*/
    private Mensaje readFile(File file) {
        Mensaje mensaje = new Mensaje();
        mensaje.setError("");
        mensaje.setNoError(0);
        FileInputStream in = null;
        try {
            StringBuilder key = new StringBuilder();
            in = new FileInputStream(file);
            int c;
            while ((c = in.read()) != -1) {
                key.append((char) c);
            }
            if (key.toString().trim().length() == 0) {
                mensaje.setError("Archivo llave esta vacio");
                mensaje.setNoError(200);
                mensaje.setResultado(null);
            } else {
                mensaje.setResultado(key.toString());
            }
        } catch (IOException e) {
            mensaje.setError(e.getMessage());
            mensaje.setNoError(200);
            mensaje.setResultado(null);
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                    mensaje.setError(ex.getMessage());
                    mensaje.setNoError(200);
                    mensaje.setResultado(null);
                }
            }
        }
        return mensaje;
    }

    public MensajeLicencia validaLicenciaKey(byte[] archivoKey, String numeroDiscoDuro, String macAddress) {
        if (controlLicencias == null) {
            controlLicencias = new WSControlLicencias();
        }
        WSControlLicencias.URL = "http://10.10.1.50:8080/ControlLicenciasWS/MEFLicenciaWS?WSDL";
        return controlLicencias.validaLicenciaKey(archivoKey, numeroDiscoDuro, macAddress);
    }

    public static void bitacora(String text) {
        if (text == null) {
            return;
        }
        try {
            BufferedWriter log = new BufferedWriter(new FileWriter("ServidorERP.log", true));

            log.write(text + System.getProperty("line.separator"));
            log.flush();
            log.close();
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static int diasBimestre(Date fechaEvaluar) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(fechaEvaluar);
        //+1 es por que enero inicia en 0
        if ((fecha.get(Calendar.MONTH) + 1) % 2 == 0) {
            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 2);
        } else {
            fecha.set(Calendar.MONTH, fecha.get(Calendar.MONTH) - 1);
        }
        if (fecha.get(Calendar.MONTH) == Calendar.JANUARY || fecha.get(Calendar.MONTH) == Calendar.FEBRUARY) {
            int year = fecha.get(Calendar.YEAR);
            if ((year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0))) {
                return 60;
            } else {
                return 59;
            }
        } else if (fecha.get(Calendar.MONTH) == Calendar.MARCH || fecha.get(Calendar.MONTH) == Calendar.APRIL) {
            return 61;
        } else if (fecha.get(Calendar.MONTH) == Calendar.MAY || fecha.get(Calendar.MONTH) == Calendar.JUNE) {
            return 61;
        } else if (fecha.get(Calendar.MONTH) == Calendar.JULY || fecha.get(Calendar.MONTH) == Calendar.AUGUST) {
            return 62;
        } else if (fecha.get(Calendar.MONTH) == Calendar.SEPTEMBER || fecha.get(Calendar.MONTH) == Calendar.OCTOBER) {
            return 61;
        } else if (fecha.get(Calendar.MONTH) == Calendar.NOVEMBER || fecha.get(Calendar.MONTH) == Calendar.DECEMBER) {
            return 61;
        }
        return -1;
    }
    
    public static double truncateDecimal(double valor, int decimales) {
        BigDecimal numTruncado;
        if (valor > 0) {
            numTruncado = new BigDecimal(String.valueOf(valor)).setScale(decimales, BigDecimal.ROUND_FLOOR);
        } else {
            numTruncado = new BigDecimal(String.valueOf(valor)).setScale(decimales, BigDecimal.ROUND_CEILING);
        }
        return numTruncado.doubleValue();
    }
}
