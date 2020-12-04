/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.servicios;

import com.mef.erp.modelo.entidad.ControlVacDeveng;
import com.mef.erp.modelo.entidad.Empleados;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.PlazasPorEmpleado;
import com.mef.erp.modelo.entidad.PtuEmpleados;
import com.mef.erp.modelo.entidad.Puestos;
import com.mef.erp.modelo.entidad.RazonesSociales;
import com.mef.erp.modelo.entidad.TablaDatos;
import com.mef.erp.modelo.entidad.VacacionesDevengadas;
import com.mef.erp.util.HibernateUtil;
import com.mef.erp.util.UtilidadesXML;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Desarrollo99
 */
public class pruebasErn {

    /**
     * @param args the command line arguments
     */
    static Object[][] reglaFactor = null;

    public static void main(String[] args) {
        // TODO code application logic here
        ApplicationContext context = new ClassPathXmlApplicationContext("remoting-servlet.xml");
        ServicioBaseDatosIF base = (ServicioBaseDatosIF) context.getBean("servicioBaseDatos");
        base.obtenerConexion("sa", "adminadmin", "MEFMasterPro", "MEFPro", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "localhost", "1433", true);

        String uuidCxn = "sa|MEF|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|N";
        String uuidCxnMaestra = "sa|MEFMaster|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|M";

       // ServicioCalculoPtuIF service = (ServicioCalculoPtuIF) context.getBean("servicioCalculoPtu");
        // service.cargaDeAcumulados(2015, "0001", uuidCxn);
//        ServicioRazonesSocialesIF razones = (ServicioRazonesSocialesIF) context.getBean("servicioRazonesSociales");
//        Mensaje m1 = razones.getRazonesPorClave("0002", "sa|MEFJorge|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|N");
        Session session = HibernateUtil.currentSession(uuidCxn);
        session.beginTransaction();
        Query q = null;
        Session sessionM = HibernateUtil.currentSession(uuidCxnMaestra);
        sessionM.beginTransaction();
        StringBuilder query = new StringBuilder();

//        query.append("delete PtuEmpleados p where p.id ");
//        query.append(" IN ( Select ptu.id from PtuEmpleados ptu WHERE ptu.ejercicio =:anio  AND ptu.razonesSociales.clave =:claveRazonsocial) ");
//        q = session.createQuery(query.toString());
//        q.setParameter("claveRazonsocial", "0002");
//        q.setParameter("anio", 2016);
//        q.executeUpdate();
        Double dias = 365D;
        int ejercicio = 2016;
        if (isLeapYear(2016)) {
            dias = 366D;
        }

        Calendar fechaInicial = Calendar.getInstance(), fechaFinal = Calendar.getInstance();
        fechaInicial.set(Calendar.DAY_OF_MONTH, 1);
        fechaInicial.set(Calendar.MONTH, Calendar.JANUARY);
        fechaInicial.set(Calendar.YEAR, ejercicio);

        //Final
        fechaFinal.set(Calendar.DAY_OF_MONTH, 31);
        fechaFinal.set(Calendar.MONTH, Calendar.DECEMBER);
        fechaFinal.set(Calendar.YEAR, ejercicio);
        query.delete(0, query.length());
        //CASE WHEN COUNT(mov) = 0 THEN 0.0 ELSE SUM(CASE WHEN (mov.resultado IS NULL) THEN 0.0 ELSE mov.resultado END) END
        //Obtiene empleados dados de alta en la empresa durante el ejercicio
        query.append("SELECT  ");
        query.append("o.puestos,");
        query.append("o.plazasPorEmpleado.empleados,");
        query.append("CASE WHEN (o.fechaInicial IS NULL)  THEN cast('1900-01-01' as date) ELSE  o.fechaInicial END,");
        query.append("CASE WHEN (o.plazasPorEmpleado.fechaFinal >=year(:fechaActual)) THEN o.plazasPorEmpleado.fechaFinal ELSE  '' END,");
        query.append("CASE WHEN (o.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  o.tipoRelacionLaboral  END, ");

        query.append("(SELECT :noDias - COUNT(a)*1.0 as dias FROM Asistencias a INNER JOIN a.excepciones ex INNER JOIN a.empleados em ");
        query.append("INNER JOIN a.razonesSociales rs INNER JOIN a.tipoNomina t INNER JOIN a.periodosNomina p ");
        query.append("WHERE em.id=o.plazasPorEmpleado.empleados.id  AND rs.clave =:claveRazonsocial ");
        query.append("AND ex.clave NOT IN (3,5,6) AND a.fecha BETWEEN :fechaInicial AND :fechaFinal), ");

        query.append("(SELECT CASE WHEN COUNT(mov) = 0 THEN 0.0 ELSE SUM(CASE WHEN (mov.resultado IS NULL) THEN 0.0 ELSE mov.resultado END) END ");
        query.append("FROM MovNomConcep mov LEFT OUTER JOIN mov.periodosNomina per ");
        query.append("LEFT OUTER JOIN mov.concepNomDefi cnc LEFT OUTER JOIN mov.empleado emple ");
        query.append("LEFT OUTER JOIN mov.plazasPorEmpleado Hx11 LEFT OUTER JOIN mov.razonesSociales rs ");
        query.append("WHERE cnc.naturaleza = 0 AND emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
        query.append("AND per.año =:anio AND mov.mes IN(:meses)), ");

        query.append("(select Distinct CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
        query.append("FROM PlazasPorEmpleadosMov pm where pm.tipoRelacionLaboral=1 AND  pm.plazasPorEmpleado.id =(");
        query.append("select Distinct mov.plazasPorEmpleado.id FROM MovNomConcep mov  ");
        query.append("LEFT OUTER JOIN mov.empleado emple LEFT OUTER JOIN mov.razonesSociales rs ");
        query.append("WHERE emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
        query.append("AND year(pm.fechaInicial) =:anio )) as base, ");

        query.append("(select Distinct CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
        query.append("FROM PlazasPorEmpleadosMov pm where pm.tipoRelacionLaboral=2 AND  pm.plazasPorEmpleado.id =(");
        query.append("select Distinct mov.plazasPorEmpleado.id FROM MovNomConcep mov  ");
        query.append("LEFT OUTER JOIN mov.empleado emple LEFT OUTER JOIN mov.razonesSociales rs ");
        query.append("WHERE emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
        query.append("AND year(pm.fechaInicial) =:anio )) as eventual, ");

        query.append("(select Distinct CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
        query.append("FROM PlazasPorEmpleadosMov pm where pm.tipoRelacionLaboral=3 AND  pm.plazasPorEmpleado.id =(");
        query.append("select Distinct mov.plazasPorEmpleado.id FROM MovNomConcep mov  ");
        query.append("LEFT OUTER JOIN mov.empleado emple LEFT OUTER JOIN mov.razonesSociales rs ");
        query.append("WHERE emple.id = o.plazasPorEmpleado.empleados.id AND rs.clave =:claveRazonsocial ");
        query.append("AND year(pm.fechaInicial) =:anio )) as confianza, ");

        query.append("(SELECT CASE WHEN (pm.tipoRelacionLaboral IS NULL)  THEN ''  ELSE  pm.tipoRelacionLaboral END ");
        query.append("FROM PlazasPorEmpleadosMov pm WHERE pm.fechaInicial=(select Max(pm2.fechaInicial)  FROM PlazasPorEmpleadosMov pm2 ");
        query.append("LEFT OUTER JOIN pm2.plazasPorEmpleado.empleados emp2 LEFT OUTER JOIN pm2.plazasPorEmpleado.razonesSociales rs ");
        query.append(" where emp2.id=o.plazasPorEmpleado.empleados.id AND rs.clave=:claveRazonsocial AND year(pm2.fechaInicial) <=:anio ) ");
        query.append("and pm.plazasPorEmpleado.empleados.id =o.plazasPorEmpleado.empleados.id AND pm.plazasPorEmpleado.razonesSociales =:claveRazonsocial) ");
        query.append("from PlazasPorEmpleadosMov o where o.id IN (Select MAX(m.id) from PlazasPorEmpleadosMov m ");
        query.append("where  m.plazasPorEmpleado.razonesSociales.clave =:claveRazonsocial ");
        query.append("AND (year(m.fechaInicial) <=:anio AND year(m.plazasPorEmpleado.fechaFinal) >=:anio) ");
        query.append("OR (year(m.plazasPorEmpleado.fechaFinal) =:anio) ");
        query.append("group by m.plazasPorEmpleado.empleados.clave) ");

        //  query.append("AND o.plazasPorEmpleado.id IN (3,16,21)  ");//de momento
        //and o.plazasPorEmpleado.id <=6
        q = session.createQuery(query.toString());
        q.setParameter("claveRazonsocial", "0001");
        q.setParameter("anio", 2015);
        q.setParameter("fechaActual", new Date());
        q.setParameter("noDias", dias);
        q.setParameter("fechaInicial", fechaInicial.getTime());
        q.setParameter("fechaFinal", fechaFinal.getTime());
        q.setParameterList("meses", new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});

        ArrayList acumulados = (ArrayList) q.list();

//        RazonesSociales raz = (RazonesSociales) m1.getResultado();
//        Object[] info = (Object[]) acumulados.get(0);
//        PtuEmpleados ptuEmpleados = new PtuEmpleados();
//        ptuEmpleados.setRazonesSociales(raz);
//        ptuEmpleados.setEjercicio(2016);
//        ptuEmpleados.setEmpleados((Empleados) info[1]);
//        ptuEmpleados.setFechaBaja((Date) info[2]);
//        ptuEmpleados.setFechaIngreso((Date) info[3]);
//        ptuEmpleados.setPuestos((Puestos) info[0]);
//        ptuEmpleados.setClasificacion("B");
//        ptuEmpleados.setPtuDias(8500D);
//        ptuEmpleados.setPercepciones(5800D);
//        ptuEmpleados.setPtuDias(0D);
//        ptuEmpleados.setPtuPercepciones(0D);
//        ptuEmpleados.setObservaciones("");
//        ptuEmpleados.setParticipa(true);
//
//        session.saveOrUpdate(ptuEmpleados);
        session.getTransaction().commit();

//        listPtuEmpleados.add(//Pendiente fecha Baja
//                       ,
//                    
//                                 (Date) info[2], (Date) info[3], puesto,
//                                clasificacion,
//                                diasPTU, (Double) info[6], 0D, 0D, "", participa)
    }

    private static boolean isLeapYear(int year) {
        return (year % 400 == 0) || ((year % 100 != 0) && (year % 4 == 0));
    }

    private void Devengadas() {

        ApplicationContext context = new ClassPathXmlApplicationContext("remoting-servlet.xml");
        ServicioBaseDatosIF base = (ServicioBaseDatosIF) context.getBean("servicioBaseDatos");
        base.obtenerConexion("sa", "adminadmin", "MEFMasterJorge", "MEFJorge", "com.microsoft.sqlserver.jdbc.SQLServerDriver", "localhost", "1433", true);

        ServicioVacacionesDevengadasIF servicioPaises = (ServicioVacacionesDevengadasIF) context.getBean("servicioVacacionesDevengadas");

        ServicioRazonesSocialesIF razones = (ServicioRazonesSocialesIF) context.getBean("servicioRazonesSociales");
        Mensaje m1 = razones.getRazonesPorClave("0002", "sa|MEFJorge|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|N");

        RazonesSociales raz = (RazonesSociales) m1.getResultado();
//
        Mensaje m = servicioPaises.calcularVacacionesDevengadasEmpleados(raz, "sa|MEFJorge|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|N",
                "sa|MEFMasterJorge|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|M");
////        
        m.getResultado();
        String uuidCxn = "sa|MEFJorge|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|N";
        String uuidCxnMaestra = "sa|MEFMasterJorge|com.microsoft.sqlserver.jdbc.SQLServerDriver|localhost|1433|M";

        Session session = HibernateUtil.currentSession(uuidCxn);
        session.beginTransaction();

        Query q = null;
        //Obtiene primero los factores de integracion
        Session sessionM = HibernateUtil.currentSession(uuidCxnMaestra);
        sessionM.beginTransaction();

        StringBuilder query = new StringBuilder();

        //Obtiene el control de vacaciones
        query.append("from ControlVacDeveng o ");
        query.append("where o.razonesSociales.clave =:claveRazonsocial ");
        query.append("AND o.fecha = (select MAX(o.fecha) from ControlVacDeveng o) ");
        q = session.createQuery(query.toString());
        q.setParameter("claveRazonsocial", "0002");
        ControlVacDeveng control = (ControlVacDeveng) q.uniqueResult();

        Date fechaUltimDev = null;
        List<Date> diasPendientes = null;
        if (control == null) {
            diasPendientes = new ArrayList<Date>();
            diasPendientes.add(new Date());
        } else {
            fechaUltimDev = control.getFecha();
            diasPendientes = getDaysBetweenDates(fechaUltimDev, new Date());
        }

        query.delete(0, query.length());

        int d = 0;
        ControlVacDeveng controlCalculadas = null;
        RazonesSociales razon = null;
        List<PlazasPorEmpleado> plazasEmpleados = null;
        for (d = 0; d < diasPendientes.size(); d++) {
            if (!diasPendientes.isEmpty()) {
                //Obtiene empleados que cumplen aniversario al día
                query.append("select o.plazasPorEmpleado from PlazasPorEmpleadosMov o ");
                query.append("where o.id IN (Select MAX(m.id) from PlazasPorEmpleadosMov m   ");
                query.append("where  m.plazasPorEmpleado.razonesSociales.clave =:claveRazonsocial ");
                query.append("AND  m.plazasPorEmpleado.fechaFinal >= :fechaActual ");
                query.append("AND month(o.plazasPorEmpleado.fechaPrestaciones)=month(:fechaActual) ");
                query.append("AND day(o.plazasPorEmpleado.fechaPrestaciones)=day(:fechaActual)");
                query.append("group by m.plazasPorEmpleado.empleados.clave)");

                //ObtenerEmpleados por el filtro para saber cuando cumplen aniversario
                plazasEmpleados = null;

                q = session.createQuery(query.toString());
                q.setParameter("claveRazonsocial", "0002");
                q.setParameter("fechaActual", diasPendientes.get(d));
                plazasEmpleados = q.list();
                plazasEmpleados = plazasEmpleados == null ? new ArrayList<PlazasPorEmpleado>() : plazasEmpleados;
                query.delete(0, query.length());
                //System.out.println(plazasEmpleados);
                if (!plazasEmpleados.isEmpty()) {
                    //Obtiene  los factores de integracion
                    if (reglaFactor == null) {
                        query.append("from TablaDatos o where o.tablaBase.clave = :clave ");
                        query.append("AND o.id = (SELECT MAX(t.id) FROM TablaDatos t WHERE t.tablaBase.id = o.tablaBase.id)");

                        List<Object> values;
                        q = sessionM.createQuery(query.toString());
                        q.setParameter("clave", "05");
                        values = q.list();
                        values = values == null ? new ArrayList<Object>() : values;
                        if (values.size() > 0) {
                            byte[] convert = ((TablaDatos) values.get(0)).getFileXml();
                            reglaFactor = UtilidadesXML.extraeValoresNodos(UtilidadesXML.convierteBytesToXML(convert));
                        }
                        query.delete(0, query.length());
                    }

                    //LLenar tabla de Vacaciones Devengadas por día
                    VacacionesDevengadas vd = null;

                    for (int i = 0; i < plazasEmpleados.size(); i++) {
                        Double antiguedad = (Double) calcularAntiguedadExacta(plazasEmpleados.get(i).getFechaPrestaciones());
                        antiguedad.intValue();
                        //Obtiene vacaciones devengadas por año a ese empleado
                        query.append("select CASE WHEN (dev IS NULL) THEN 'NOCALCULADA' ELSE 'CALCULADA' END ");
                        query.append("from VacacionesDevengadas dev where dev.plazasPorEmpleado.id =:idplaza AND dev.ejercicio =:ejercicio");
                        q = session.createQuery(query.toString());
                        q.setParameter("idplaza", plazasEmpleados.get(i).getId());
                        q.setParameter("ejercicio", antiguedad.intValue());
                        String calculada = (String) q.uniqueResult();
                        if (calculada == null) {
                            vd = new VacacionesDevengadas();
                            razon = plazasEmpleados.get(i).getRazonesSociales();
                            vd.setRazonesSociales(plazasEmpleados.get(i).getRazonesSociales());
                            vd.setPlazasPorEmpleado(plazasEmpleados.get(i));
                            vd.setEjercicio(antiguedad.intValue());
                            Object[] factorEmpleado = (Object[]) obtieneFactorIntegracion(antiguedad.intValue());
                            query.delete(0, query.length());
                            query.append("select MAX(sdi.fecha),sdi.salarioDiarioFijo from SalariosIntegrados sdi WHERE ");
                            query.append("empleados.id= :idEmpleado AND fecha <= :fechaPrestacion GROUP BY sdi.salarioDiarioFijo");
                            q = session.createQuery(query.toString());
                            q.setParameter("idEmpleado", plazasEmpleados.get(i).getEmpleados().getId());
                            q.setParameter("fechaPrestacion", plazasEmpleados.get(i).getFechaPrestaciones());
                            Object[] salarioAniv = (Object[]) q.uniqueResult();
                            if (salarioAniv == null) {
                                vd.setSalarioAniversario(0D);
                            } else {
                                vd.setSalarioAniversario((Double) salarioAniv[1]);
                            }
                            vd.setFactorPrima(Double.parseDouble(factorEmpleado[4].toString()));
                            vd.setDiasVacaciones(Integer.parseInt(factorEmpleado[3].toString()));
                            vd.setRegistroInicial(false);
                            double primaVac = Double.parseDouble(factorEmpleado[4].toString())
                                    / 100 * Integer.parseInt(factorEmpleado[3].toString());
                            vd.setDiasPrimavaca(primaVac);
                            session.save(vd);
                        }
                        query.delete(0, query.length());
                    }
                }
            }
            controlCalculadas = new ControlVacDeveng();
            controlCalculadas.setFecha(diasPendientes.get(d));
            if (razon == null) {
                String sa = " from RazonesSociales where clave = :claveRazonsocial";
                q = session.createQuery(sa);
                q.setParameter("claveRazonsocial", "0002");
                razon = (RazonesSociales) q.uniqueResult();
            }
            controlCalculadas.setRazonesSociales(razon);
            session.save(controlCalculadas);

        }
        session.getTransaction().commit();

    }

    static Object obtieneFactorIntegracion(Integer antiguedad) {
        int i = 0;
        for (i = 0; i < reglaFactor.length; i++) {
            Integer dataFact = Integer.parseInt(reglaFactor[i][0].toString());
            if (antiguedad <= dataFact) {
                break;
            }
        }
        return reglaFactor[i];
    }

    static Object calcularAntiguedadExacta(Date fechaInicial) {
        Date fechaFinal = new Date();
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
            double dias, antiguedad;
            dias = Math.floor(diferencia / (1000 * 60 * 60 * 24));
            antiguedad = dias / 365;
            return antiguedad;
        } catch (ParseException ex) {
        }
        return 0.0;
    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);
        calendar.add(Calendar.DATE, 1);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }

}
