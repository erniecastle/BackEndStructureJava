/**
 * @author: Jose Armando Compañía: Macropro. Descripción del programa: Esta
 * clase es para las consultas especiales.
 * -----------------------------------------------------------------------------
 * MODIFICACIONES:
 * -----------------------------------------------------------------------------
 * Clave: VIC01 Trabajo: Macropro Autor: Victor Lopez Fecha: 04/10/2012
 * Descripcion:Metodos para los criterios de consulta
 * -----------------------------------------------------------------------------
 * Clave: JSA01 Trabajo: Macropro Autor: Jose Armando Fecha: 21/01/2013
 * Descripcion: se mejoro la programacion para obtener las plazas por empleado.
 * -----------------------------------------------------------------------------
 * Clave: JSA02 Trabajo: Macropro Autor: Jose Armando Fecha: 06/09/2013
 * Descripcion: Se agrego el parametro razones sociales y se cambio el filtro de
 * la fecha final del empleado, ya que si un empleado se da de baja y se desea
 * calcular el finiquito o nomina el empleado recien dado de baja no se puede
 * seleccionar.
 * -----------------------------------------------------------------------------
 * Clave: JSA03 Trabajo: Macropro Autor: Jose Armando Fecha: 09/10/2013
 * Descripcion: Se modifico la programacion para que cuando el tipo corrida
 * fuera FIN o LIQ no limitara a los empleado dados de bajas.
 * -----------------------------------------------------------------------------
 */
package com.mef.erp.modelo;

import com.mef.erp.modelo.entidad.DatosPlazasEmpleado;
import com.mef.erp.modelo.entidad.ManejoHorasPor;
import com.mef.erp.modelo.entidad.ManejoSalarioDiario;
import com.mef.erp.modelo.entidad.Mensaje;
import com.mef.erp.modelo.entidad.MovNomBaseAfecta;
import com.mef.erp.modelo.entidad.MovNomConceParam;
import com.mef.erp.modelo.entidad.MovNomConcep;
import com.mef.erp.modelo.entidad.ParaConcepDeNom;
import com.mef.erp.modelo.entidad.Parametros;
import com.mef.erp.modelo.entidad.PlazasPorEmpleadosMov;
import com.mef.erp.modelo.entidad.SalariosIntegrados;
import com.mef.erp.modelo.entidad.TipoCorrida;
import com.mef.erp.util.ControlErroresHibernate;
import com.mef.erp.util.HibernateUtil;
import com.mef.funciones.ClavesParametrosModulos;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.mapping.Table;

public class ConsultaGenericaEspecialesDAO extends GenericHibernateDAO<Object, Integer>
        implements ConsultaGenericaEspecialesDAOIF {

    private final String msgError = builder.append("ServerERP.MSERR_C_").append(this.getClass().getName()).append(".").toString();
    private Map<String, String[]> aliasTablaExtras;
    private int contTablaOuter;
    List<String> ordenTablasOuter;
//    public String uuidCxnMaestra;

    public Mensaje existeClave(String identificador, String[] campowhere, Object[] valoreswhere, String uuidCxn, String uuidCxnMaestra) {
        return generarQuery(identificador, null, null, campowhere, valoreswhere, true, uuidCxn, uuidCxnMaestra);
    }

    public Mensaje consultaPorRangosFiltro(String identificador, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra) {//JSA01
        return consultaGenericaPorRangos(identificador, inicio, rango, camposWhere, valoresWhere, uuidCxn, uuidCxnMaestra);
    }

    private Mensaje consultaGenericaPorRangos(String identificador, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra) {
        return generarQuery(identificador, inicio, rango, camposWhere, valoresWhere, false, uuidCxn, uuidCxnMaestra);

    }

    private Mensaje generarQuery(String identificador, Integer inicio, Integer rango, String[] camposWhere, Object[] valoresWhere, boolean uniqueResult, String uuidCxn, String uuidCxnMaestra) {//JSA01
        int i = -1;
        try {
            inicializaVariableMensaje();
            mensajeResultado = ObtenerQueryPerzonalizado(identificador, camposWhere, valoresWhere, uuidCxn, uuidCxnMaestra);
            if (mensajeResultado.getNoError() == 0) {
                List<Object> Objectos = (List<Object>) mensajeResultado.getResultado();
                consulta = new StringBuilder(Objectos.get(0).toString());
                camposWhere = (String[]) Objectos.get(1);

                getSession().beginTransaction();
                q = getSession().createQuery(consulta.toString());
                if (camposWhere != null) {
                    for (i = 0; i < camposWhere.length; i++) {
                        if (identificador.equalsIgnoreCase("QueryEmpleadoEspecial")
                                || identificador.equalsIgnoreCase("QueryEmpleadoEspecialMovimientosNomina")) {//JSA03
                            if (valoresWhere[i] != null) {//caso especial por los finiquitos-liquidacion pueda ser que la corrida venga NULL
                                q.setParameter("parametros".concat(String.valueOf(i)), valoresWhere[i]);
                            }
                        } else {
                            q.setParameter("parametros".concat(String.valueOf(i)), valoresWhere[i]);
                        }
                    }

                }
                if (inicio != null && rango != null) {//AAP02
                    q.setFirstResult(inicio);
                    q.setMaxResults(rango);
                }
                if (uniqueResult) {
                    mensajeResultado.setResultado(q.uniqueResult());
                    return mensajeResultado;
                } else {
                    mensajeResultado.setResultado(q.list());
                    return mensajeResultado;
                }
            } else {
                return mensajeResultado;
            }
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("generarQuery()1_Error: ").append(ex));
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
            return mensajeResultado;
        }
    }

    private String construyeConcatString(String alias, String campo) {
        String[] campos = campo.split(",");
        if (campos.length > 0) {
            StringBuilder sb = new StringBuilder(" CONCAT(");
            int i;
            for (i = 0; i < campos.length; i++) {
                sb.append("CASE WHEN (").append(alias).append(campos[i]).append(" IS NULL) THEN '' ELSE ").append(campos[i]).append(" END").append("@");
            }
            campo = sb.substring(0, sb.length() - 1);
            return campo.replace("@", ",' ',").concat(")");
        }
        return "";
    }

    private Mensaje ObtenerQueryPerzonalizado(String identificador, String[] camwhere, Object[] valoresWhere, String uuidCxn, String uuidCxnMaestra) {
        List<Object> camposWhere = new ArrayList<Object>();
        int i = -1;
        String nombreTabla = "";
        mensajeResultado.setNoError(0);
        mensajeResultado.setError("");
        try {
            if (identificador.equalsIgnoreCase("QueryEmpleadoEspecial")
                    || identificador.equalsIgnoreCase("QueryEmpleadoEspecialMovimientosNomina")) {
                consulta = new StringBuilder();
                consulta.append(identificador.equalsIgnoreCase("QueryEmpleadoEspecial")
                        ? "select o.tipoNomina,o.plazasPorEmpleado.empleados"
                        : "select o, o.plazasPorEmpleado.empleados");
                consulta.append(" from PlazasPorEmpleadosMov o where o.id IN ( ");
                consulta.append(" Select MAX(m.id) from PlazasPorEmpleadosMov m ");
                consulta.append(" where ");
                if (camwhere == null ? false : camwhere.length > 0) {
                    List<String> lstDatosGlobal = new ArrayList<String>();
                    String campoGlobal = "";
                    consulta.append(" ( ");
                    for (int j = 0; j < camwhere.length; j++) {
                        String[] y = null;
                        if (camwhere[j].contains("#")) {
                            String x = camwhere[j].substring(camwhere[j].indexOf("#"));
                            camwhere[j] = camwhere[j].substring(0, camwhere[j].indexOf("#"));
                            if (x.charAt(0) == '#' & x.charAt(x.length() - 1) == '#') {
                                x = x.replace("#", "").trim();
                                y = x.split("\\|");
                                if (!y[0].trim().isEmpty()) {
                                    consulta.append(" not ");
                                }
                            }
                        } else {
                            if (camwhere[j].split(",").length == 1) {
                                campoGlobal = "o.".concat(camwhere[j]);
                            }
                        }
                        if (camwhere[j].split(",").length > 1) {
                            consulta.append(construyeConcatString(" m.", camwhere[j]));
                        } else {
                            consulta.append(" m.").append(camwhere[j]);
                        }
                        if (y != null) {
                            if (!y[1].trim().isEmpty()) {
                                consulta.append(" like ");
                            } else {
                                consulta.append(" = ");
                            }
                        } else {
                            consulta.append(" = ");
                        }
                        consulta.append(":parametros").append(++i);
                        if (campoGlobal.length() > 0) {
                            lstDatosGlobal.add(campoGlobal.concat("=:parametros").concat(String.valueOf(i)));
                        }
                        if (j < camwhere.length - 1) {
                            if (y != null) {
                                if (y.length > 2) {
                                    if (y != null) {
                                        consulta.append(y[2].trim().equalsIgnoreCase("AND") ? " AND " : " or ");
                                    } else {
                                        consulta.append(" AND ");
                                    }
                                } else {
                                    consulta.append(" AND ");
                                }
                            } else {
                                consulta.append(" AND ");
                            }
                        }

                    }
                    consulta.append(" ) ");
                    consulta.append(" AND ");
                    for (int j = 0; j < lstDatosGlobal.size(); j++) {
                        consulta.append(lstDatosGlobal.get(j)).append(" AND ");
                    }
                }
                consulta.append(" m.plazasPorEmpleado.razonesSociales.clave =:parametros").append(++i);
                //JSA03
                boolean aplicarRestriccionEmpleadoDadoBaja = true;
                i++;
                if (valoresWhere[i] != null) {
                    if (valoresWhere[i] instanceof TipoCorrida) {
                        if (((TipoCorrida) valoresWhere[i]).getClave().equalsIgnoreCase("FIN") || ((TipoCorrida) valoresWhere[i]).getClave().equalsIgnoreCase("LIQ")) {
                            aplicarRestriccionEmpleadoDadoBaja = false;
                        }
                    }
                    consulta.append(" AND :parametros").append(i).append("= :parametros").append(i);
                }

                int parametroFechaInicial = ++i, parametroFechaFinal = ++i;
                if (aplicarRestriccionEmpleadoDadoBaja) {
                    consulta.append(" AND ( ( m.fechaInicial <= :parametros").append(parametroFechaInicial);
                    consulta.append(" ) OR ( m.fechaInicial  between :parametros").append(parametroFechaInicial);
                    consulta.append(" AND :parametros").append(parametroFechaFinal).append(" ) ) ");
                    consulta.append(" AND ( ( m.plazasPorEmpleado.fechaFinal >= :parametros").append(parametroFechaFinal).append(" ) ");
                    consulta.append(" OR (m.plazasPorEmpleado.fechaFinal  between :parametros").append(parametroFechaInicial);
                    consulta.append(" AND :parametros").append(parametroFechaFinal).append(" ) ) ");
                } else {
                    consulta.append(" AND :parametros").append(parametroFechaInicial).append("= :parametros").append(parametroFechaInicial);
                    consulta.append(" AND :parametros").append(parametroFechaFinal).append("= :parametros").append(parametroFechaFinal);
                }

                consulta.append(" group by m.plazasPorEmpleado.empleados.clave ) ");
                consulta.append(" order by o.plazasPorEmpleado.empleados.clave, o.fechaInicial ");
                camposWhere.add(consulta.toString());
                if (camwhere != null) {//JSA03
                    camposWhere.add(new String[4 + camwhere.length]);
                } else {
                    camposWhere.add(new String[4]);
                }
                nombreTabla = "PlazasPorEmpleadosMov";
            } else if (identificador.equalsIgnoreCase("queryPlazasEmpleadoEspecial")) {//JSA01
                nombreTabla = Parametros.class.getSimpleName();
                if (existeTablaMEFMaster(nombreTabla)) {
                    setSession(HibernateUtil.currentSession(uuidCxnMaestra));
                } else {
                    setSession(HibernateUtil.currentSession(uuidCxn));
                }
                getSession().beginTransaction();
                StringBuilder strQuery = new StringBuilder();
                //<editor-fold defaultstate="collapsed" desc="manejaPagosPorHora">
                strQuery.append("SELECT cr.valor FROM Cruce cr ");
                strQuery.append(" INNER JOIN cr.parametros pr ");
                strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" and ea.clave = :elementoAplicacion ");
                strQuery.append(" and cr.claveElemento = :claveElemento");
                boolean manejaPagosPorHora;
                ManejoHorasPor manejoHorasPor = ManejoHorasPor.HSM;
                ManejoSalarioDiario manejoSalarioDiario = ManejoSalarioDiario.QUINCENAL;
                String valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroPagosPorHora, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, valoresWhere[valoresWhere.length - 1]});

                valor = (valor == null ? "" : valor);
                if (valor.isEmpty()) {
                    strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                    strQuery.append(" FROM Parametros pr ");
                    strQuery.append(" INNER JOIN pr.modulo m ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" AND m.clave =:modulo");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroPagosPorHora, ClavesParametrosModulos.claveModuloGlobal});
                }

                if (valor.equals(ClavesParametrosModulos.opcionParametroPagarPorHoras)) {
                    manejaPagosPorHora = true;
                } else {
                    manejaPagosPorHora = false;
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="manejoHorasPor">
                strQuery.delete(0, strQuery.length()).append("SELECT cr.valor ");
                strQuery.append(" FROM Cruce cr ");
                strQuery.append(" INNER JOIN cr.parametros pr ");
                strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" and ea.clave = :elementoAplicacion ");
                strQuery.append(" and cr.claveElemento = :claveElemento");
                valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroManejarHorasPor, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, valoresWhere[valoresWhere.length - 1]});
                valor = (valor == null ? "" : valor);
                if (valor.isEmpty()) {
                    strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                    strQuery.append(" FROM Parametros pr");
                    strQuery.append(" INNER JOIN pr.modulo m ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" AND m.clave =:modulo");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroManejarHorasPor, ClavesParametrosModulos.claveModuloGlobal});
                }
                if (valor.equals(ClavesParametrosModulos.opcionParametroHorasNaturales)) {
                    manejoHorasPor = ManejoHorasPor.HORASNATURALES;
                } else {
                    manejoHorasPor = ManejoHorasPor.HSM;
                }
                //</editor-fold>
                //<editor-fold defaultstate="collapsed" desc="manejoSalarioDiario">
                strQuery.delete(0, strQuery.length()).append("SELECT cr.valor ");
                strQuery.append(" FROM Cruce cr ");
                strQuery.append(" INNER JOIN cr.parametros pr ");
                strQuery.append(" INNER JOIN cr.elementosaplicacion ea ");
                strQuery.append(" WHERE pr.clave = :parametro ");
                strQuery.append(" and ea.clave = :elementoAplicacion ");
                strQuery.append(" and cr.claveElemento = :claveElemento");
                valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "elementoAplicacion", "claveElemento"}, new Object[]{ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor, ClavesParametrosModulos.claveElementoAplicacionRazonSocial, valoresWhere[valoresWhere.length - 1]});
                valor = (valor == null ? "" : valor);
                if (valor.isEmpty()) {
                    strQuery.delete(0, strQuery.length()).append("SELECT pr.valor ");
                    strQuery.append(" FROM Parametros pr");
                    strQuery.append(" INNER JOIN pr.modulo m ");
                    strQuery.append(" WHERE pr.clave = :parametro ");
                    strQuery.append(" AND m.clave =:modulo");
                    valor = (String) ejecutaQueryUnico(strQuery.toString(), new String[]{"parametro", "modulo"}, new Object[]{ClavesParametrosModulos.claveParametroManejarSalarioDiarioPor, ClavesParametrosModulos.claveModuloGlobal});
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
                //</editor-fold>

                if (!getSession().getTransaction().wasRolledBack()) {
                    try {
                        if (getSession().getTransaction().isActive()) {
                            getSession().getTransaction().rollback();
                        }
                    } catch (HibernateException exc) { //cuando marca error al hacer rollback pro ejemplo se cae conexion servidor
                        mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(exc.getClass()));
                        mensajeResultado.setError(exc.getLocalizedMessage());
                        mensajeResultado.setResultado(null);
                        return mensajeResultado;
                    }
                }
                nombreTabla = DatosPlazasEmpleado.class.getSimpleName();
                consulta = new StringBuilder("SELECT new DatosPlazasEmpleado( ");
                consulta.append("p, ");
                consulta.append("(SELECT ingresos.fechaIngreso FROM IngresosReingresosBajas ingresos WHERE ingresos.id = ");
                consulta.append("(SELECT MAX(o1.id) FROM IngresosReingresosBajas o1 Where  o1.fechaIngreso <= ");
                consulta.append("(SELECT MAX(o2.fechaIngreso) FROM IngresosReingresosBajas o2 Where o2.fechaBaja >= current_date() ");
                consulta.append("and o2.empleados.clave = p.plazasPorEmpleado.empleados.clave))) ");
                consulta.append(", ").append(manejaPagosPorHora ? 0 : 1);
                consulta.append(", ").append(manejoHorasPor.getManejoHorasPor());
                consulta.append(", ").append(manejoSalarioDiario.getManejoSalarioDiario());
                consulta.append(" ) ");
                consulta.append("from PlazasPorEmpleadosMov p ");
                consulta.append(" where ");
                if (valoresWhere == null ? false : (valoresWhere.length > 1 ? true : false)) {
                    for (int j = 0; j < camwhere.length; j++) {
                        String[] y = null;
                        if (camwhere[j].contains("#")) {
                            String x = camwhere[j].substring(camwhere[j].indexOf("#"));
                            camwhere[j] = camwhere[j].substring(0, camwhere[j].indexOf("#"));
                            if (x.charAt(0) == '#' & x.charAt(x.length() - 1) == '#') {
                                x = x.replace("#", "").trim();
                                y = x.split("\\|");
                                if (!y[0].trim().isEmpty()) {
                                    consulta.append(" not ");
                                }
                            }
                        }
                        //ClaveEmpleado,Nombre,ClavePlazaEmpleado,FechaInicial,FechaFinal,FechaIngreso,FechaFinUltReingreso,Horas,
                        //Importe,DescripcionCentroCosto,DescripcionPuesto,ClavePlaza
                        String texto = camwhere[j].trim();
                        if (texto.equalsIgnoreCase("ClaveEmpleado")) {
                            consulta.append("p.plazasPorEmpleado.empleados.clave");
                        } else if (texto.toLowerCase().contains("nombre".toLowerCase())) {
                            texto = "plazasPorEmpleado.empleados." + texto;
                            texto = texto.replace(",", ",plazasPorEmpleado.empleados.");
                            //    consulta.append("p.plazasPorEmpleado.empleados.nombre");
                            consulta.append(construyeConcatString(" p.", texto));
                        } else if (texto.equalsIgnoreCase("ClavePlazaEmpleado")) {
                            consulta.append("p.plazasPorEmpleado.clave");
                        } else if (texto.equalsIgnoreCase("FechaInicial")) {
                            consulta.append("p.fechaInicial");
                        } else if (texto.equalsIgnoreCase("FechaFinal")) {
                            consulta.append("p.plazasPorEmpleado.fechaFinal");
                        } else if (texto.equalsIgnoreCase("FechaIngreso")) {
                            //consulta.append("p.plazasPorEmpleado.empleados.nombre");
                        } else if (texto.equalsIgnoreCase("FechaFinUltReingreso")) {
                            //consulta.append("p.plazasPorEmpleado.empleados.nombre");
                        } else if (texto.equalsIgnoreCase("Horas")) {
                            consulta.append("p.horas");
                        } else if (texto.equalsIgnoreCase("Importe")) {
                            if (valoresWhere[j] != null) {
                                if (valoresWhere[j].getClass().equals(Double.class) | valoresWhere[j].getClass().equals(double.class)) {
                                    valoresWhere[j] = ((Double) valoresWhere[j]).floatValue();
                                }
                            }
                            consulta.append("p.importe");
                        } else if (texto.equalsIgnoreCase("DescripcionCentroCosto")) {
                            consulta.append("p.centroDeCosto.descripcion");
                        } else if (texto.equalsIgnoreCase("DescripcionPuesto")) {
                            consulta.append("p.puestos.descripcion");
                        } else if (texto.equalsIgnoreCase("ClavePlaza")) {
                            consulta.append("p.plazas.clave");
                        }
                        if (y != null) {
                            if (!y[1].trim().isEmpty()) {
                                consulta.append(" like ");
                            } else {
                                consulta.append(" = ");
                            }
                        } else {
                            consulta.append(" = ");
                        }
                        consulta.append(":parametros").append(++i);
                        if (j < camwhere.length - 1) {
                            if (y != null) {
                                if (y.length > 2) {
                                    if (y != null) {
                                        consulta.append(y[2].trim().equalsIgnoreCase("AND") ? " AND " : " or ");
                                    } else {
                                        consulta.append(" AND ");
                                    }
                                } else {
                                    consulta.append(" AND ");
                                }
                            } else {
                                consulta.append(" AND ");
                            }
                        }

                    }
                    consulta.append(" and ");
                }
                consulta.append(" p.id IN ( ");
                consulta.append(" SELECT MAX(m.id) FROM PlazasPorEmpleadosMov m WHERE  m.plazasPorEmpleado.razonesSociales.clave = :parametros").append(++i);
                consulta.append(" AND current_date()  BETWEEN  m.fechaInicial AND m.plazasPorEmpleado.fechaFinal + 1 ");
                consulta.append(" GROUP BY m.plazasPorEmpleado.clave ) ");
                consulta.append(" ORDER BY p.plazasPorEmpleado.empleados.clave, p.fechaInicial");
                camposWhere.add(consulta.toString());
                if (camwhere != null) {
                    camposWhere.add(new String[1 + camwhere.length]);
                } else {
                    camposWhere.add(new String[1]);
                }
            }
            consulta = null;
            if (existeTablaMEFMaster(nombreTabla)) {
                setSession(HibernateUtil.currentSession(uuidCxnMaestra));
            } else {
                setSession(HibernateUtil.currentSession(uuidCxn));
            }
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("ObtenerQueryEmpleadoPerzonalizado()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
            return mensajeResultado;
        }
        mensajeResultado.setResultado(camposWhere);
        return mensajeResultado;//camposWhere;
    }

    private Object ejecutaQueryUnico(String strQuery, String[] camposParametro, Object[] valoresParametro) {
        Object result = null;
        try {
            Query query = getSession().createQuery(strQuery);
            int i;
            if (camposParametro != null & valoresParametro != null) {
                for (i = 0; i < camposParametro.length; i++) {
                    query.setParameter(camposParametro[i], valoresParametro[i]);
                }
            }
            query.setMaxResults(1);//JSA02
            result = query.uniqueResult();
        } catch (HibernateException ex) {
//            mensajeRespuesta.setError(ex.getMessage());
//            mensajeRespuesta.setNoError(-100);
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("ejecutaQueryUnico()1_Error: ").append(ex));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(ex.getClass()));
            mensajeResultado.setError(ex.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        } catch (Exception e) {
//            mensajeRespuesta.setError(e.getMessage());
//            mensajeRespuesta.setNoError(-100);
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("ejecutaQueryUnico()1_Error: ").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        return result;
    }

//    private void queryPlazasEmpleadoEspecial(Object[] camwhere, boolean manejaPagosPorHora, ManejoHorasPor manejoHorasPor, ManejoSalarioDiario manejoSalarioDiario) {
//        consulta = new StringBuilder("SELECT new DatosPlazasEmpleado( ");
//        consulta.append("p, ");
//        consulta.append("(SELECT ingresos.fechaIngreso FROM IngresosReingresosBajas ingresos WHERE ingresos.id = ");
//        consulta.append("(SELECT MAX(o1.id) FROM IngresosReingresosBajas o1 Where  o1.fechaIngreso <= ");
//        consulta.append("(SELECT MAX(o2.fechaIngreso) FROM IngresosReingresosBajas o2 Where o2.fechaBaja >= :parametros ");
//        consulta.append("and o2.empleados.clave = p.plazasPorEmpleado.empleados.clave))) ");
//        consulta.append(", ").append(manejaPagosPorHora ? 0 : 1);
//        consulta.append(", ").append(manejoHorasPor.getHORAS());
//        consulta.append(", ").append(manejoSalarioDiario.getSalario());
//        consulta.append(" ) ");
//        consulta.append("from PlazasPorEmpleadosMov p where p.id IN ( ");
//        consulta.append("SELECT MAX(m.id) FROM PlazasPorEmpleadosMov m WHERE  m.plazasPorEmpleado.razonesSociales.clave =:parametros1 ");
//        consulta.append("AND :parametros  BETWEEN  m.fechaInicial AND m.plazasPorEmpleado.fechaFinal + 1 ");
//        consulta.append("GROUP BY m.plazasPorEmpleado.clave ) ");
//        consulta.append("order by p.plazasPorEmpleado.empleados.clave, p.fechaInicial");
//
//    }

    /*
     * permite agregar varias tablas pero siempre y cuando sean de la misma base
     * de datos los campos where deben tener toda la ruta de la tabla principal
     * para sustituirse por su alias ejemplo tabla = paises, campoWhere =
     * paises.clave en alias por ejemplo quedaria p.clave etc. aplica al
     * campoOrden y datos a mostrar
     */
    public Mensaje obtenerDatosCriterioConsulta(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden, String uuidCxn) {//VIC01
        List<?> values = null;
        try {
            camposOrden = camposOrden == null ? new String[]{} : camposOrden;
            camposWhere = camposWhere == null ? new String[]{} : camposWhere;
            camposMostrar = camposMostrar == null ? new String[]{} : camposMostrar;
            inicializaVariableMensaje();
            setSession(HibernateUtil.currentSession(uuidCxn));
            getSession().beginTransaction();
            values = generarQueryCriterioConsulta(tablas, camposMostrar, camposWhere, valoresWhere, camposOrden);
            mensajeResultado.setResultado(values);
            mensajeResultado.setNoError(0);
            mensajeResultado.setError("");
            getSession().getTransaction().commit();
        } catch (HibernateException ex) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("obtenerDacosConsulta()1_Error: ").append(ex));
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

    private List<?> generarQueryCriterioConsulta(String[] tablas, String[] camposMostrar, String[] camposWhere, Object[] valoresWhere, String[] camposOrden) {//VIC01
        int i;
        try {
            builder = new StringBuilder(construyeQueryConsultaCriterioOuter(tablas, camposMostrar, camposWhere, camposOrden));
            //////System.out.println("***************");
//////            Object[] x = builder.toString().split(",");
//////            for (i = 0; i < x.length; i++) {
//////                System.out.println("i " + i + " -- " + x[i]);
//////            }

            q = getSession().createQuery(builder.toString());
            /*
             * cuando la variable "camposWhere" viene diferente de null, trae
             * campos para filtrar en la busqueda por rangos o en la busqueda
             * general
             */
            String usandoTablaIntegrados = "";
            for (i = 0; i < tablas.length; i++) {
                if (tablas[i].equalsIgnoreCase(SalariosIntegrados.class.getSimpleName())) {
                    usandoTablaIntegrados = tablas[i];
                    break;
                }
            }

            if (camposWhere.length > 0) {
                String[] campos;
                for (i = 0; i < camposWhere.length; i++) {
                    campos = camposWhere[i].split("#");
                    q.setParameter("parametros".concat(String.valueOf(i)), valoresWhere[i]);
                    if (campos[0].equalsIgnoreCase("movNomConcep.empleado.clave") & usandoTablaIntegrados.length() > 0) {
                        q.setParameter("parametrosExtra".concat(String.valueOf(i)), valoresWhere[i]);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("generarQueryCriterioConsulta()_Error").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }

        return q.list();
    }

    /*
     * usa para evaluar cuando la entidad viene de mas de una entidad evaluando
     * que las entidades medias puedan ser nulas ejemplo
     * movimientos.empleados.clave se pasa en campo movimientos.empleados y en
     * split clave
     */
    private String construyeCamposCaseWhen(String campo, List<String> split) {
        StringBuilder campoSlit = new StringBuilder();
        StringBuilder path = new StringBuilder(campo);
        campoSlit.append("CASE WHEN (").append(campo).append(" IS NULL) THEN '' ELSE ");
        if (split.size() > 1) {
            path.append(".").append(split.get(0));
            campoSlit.append(construyeCamposCaseWhen(path.toString(), split.subList(1, split.size())));
        } else {
            campoSlit.append(campo).append(".").append(split.get(0));
        }
        campoSlit.append(" END ");
        return campoSlit.toString();
    }

    private String construyeQueryConsultaCriterio(String[] tablas, String[] camposMostrar, String[] camposWhere, String[] camposOrden) {//VIC01
        /*
         * ---@ Para SubConsultas ("from tabla t where t.id = 1") ---#Para
         * Indicar como deseas Filtrar (>,<,!=,IN) Ejemplos al usar canpos de
         * filtrados: 1.- NombreCampo#!=,NombreCampo#>,NombreCampo#< 2.-
         * NombreCampo@ 3.- NombreCampo#IN@
         */
        construyeQueryConsultaCriterioOuter(tablas, camposMostrar, camposWhere, camposOrden);
        try {
            int i, j, ex = 0;
            Map<String, String> aliasTabla = new HashMap<String, String>();
            for (i = 0; i < tablas.length; i++) {
                aliasTabla.put(tablas[i].toLowerCase(), "o".concat(String.valueOf(i)));
            }

            builder = new StringBuilder("");
            StringBuilder campoNuevo = new StringBuilder("");
            List<String> baseAfecta = new ArrayList<String>();
            if (camposMostrar.length > 0) {
                builder.append("SELECT ");
                String ruta[];
                for (String campo : camposMostrar) {
                    campoNuevo.append(campo);
                    for (i = 0; i < tablas.length; i++) {
                        if (campo.toLowerCase().startsWith(tablas[i].toLowerCase())) {
                            if (campo.toLowerCase().contains(MovNomBaseAfecta.class.getSimpleName().toLowerCase())) {
                                /*
                                 * busca MovNomBaseAfecta son de tipo lista y se
                                 * debe agregar como outer joun consulta
                                 */
                                campoNuevo.replace(0, tablas[i].length(), aliasTabla.get(tablas[i].toLowerCase()));
                                int pos = campoNuevo.toString().toLowerCase().indexOf(MovNomBaseAfecta.class.getSimpleName().toLowerCase());
                                baseAfecta.add(campoNuevo.substring(0, MovNomBaseAfecta.class.getSimpleName().length() + pos) + " mov" + ex);
                                campoNuevo.replace(0, MovNomBaseAfecta.class.getSimpleName().length() + "mov".concat(String.valueOf(ex)).length() - 1, "mov" + ex);
                                String temp = campoNuevo.toString();
                                campoNuevo.delete(0, campoNuevo.length());
                                campoNuevo.append("CASE WHEN (mov").append(ex).append(" is null) THEN '' ELSE ").append(temp).append(" END ");
                                ex++;
                            } else if (campo.toLowerCase().contains(MovNomConceParam.class.getSimpleName().toLowerCase())) {
                                /*
                                 * busca MovNomConceParam son de tipo lista y se
                                 * debe agregar como outer joun consulta
                                 */
                                campoNuevo.replace(0, tablas[i].length(), aliasTabla.get(tablas[i].toLowerCase()));
                                int pos = campoNuevo.toString().toLowerCase().indexOf(MovNomConceParam.class.getSimpleName().toLowerCase());
                                baseAfecta.add(campoNuevo.substring(0, MovNomBaseAfecta.class.getSimpleName().length() + pos) + " mov" + ex);
                                campoNuevo.replace(0, MovNomConceParam.class.getSimpleName().length() + "mov".concat(String.valueOf(ex)).length() - 1, "mov" + ex);
                                String temp = campoNuevo.toString();
                                campoNuevo.delete(0, campoNuevo.length());
                                if (temp.toLowerCase().contains(ParaConcepDeNom.class.getSimpleName().toLowerCase())) {
                                    //*valor buscar viene entidad ParaConcepDeNom**/
                                    campoNuevo.append(temp);
                                    pos = campoNuevo.toString().toLowerCase().indexOf(ParaConcepDeNom.class.getSimpleName().toLowerCase());
                                    baseAfecta.add(campoNuevo.substring(0, ParaConcepDeNom.class.getSimpleName().length() + pos) + " param" + ex);
                                    campoNuevo.replace(0, ParaConcepDeNom.class.getSimpleName().length() + "param".concat(String.valueOf(ex)).length() - 1, "param" + ex);
                                    temp = campoNuevo.toString();
                                    campoNuevo.delete(0, campoNuevo.length());
                                    campoNuevo.append("CASE WHEN (mov").append(ex).append(" is null) THEN '' ELSE ").append("CASE WHEN (param").append(ex).append(" is null) THEN '' ELSE ").append(temp).append(" END END ");
                                } else {
                                    campoNuevo.append("CASE WHEN (mov").append(ex).append(" is null) THEN '' ELSE ").append(temp).append(" END ");
                                }
                                ex++;
                            } else {
                                campoNuevo.replace(0, tablas[i].length(), aliasTabla.get(tablas[i].toLowerCase()));
                                ruta = campoNuevo.toString().split("\\.");
                                if (ruta.length > 2) {
                                    StringBuilder strCampo = new StringBuilder();
                                    strCampo.append(aliasTabla.get(tablas[i].toLowerCase())).append(".").append(ruta[1]);
                                    List<String> listCampos = Arrays.asList(ruta);
                                    campoNuevo.delete(0, campoNuevo.length());
                                    campoNuevo.append(construyeCamposCaseWhen(strCampo.toString(), listCampos.subList(2, listCampos.size())));
                                }
                            }
                            builder.append(campoNuevo).append(",");

                            break;
                        }
                    }
                    campoNuevo.delete(0, campoNuevo.length());
                }

                if (builder.length() > 0) {
                    builder.replace(builder.length() - 1, builder.length(), " ");
                }
            } else {
                builder.append("SELECT ").append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase()));
            }

            builder.append(" FROM ");

            for (String tabla : tablas) {
                builder.append(tabla).append(" ").append(aliasTabla.get(tabla.toLowerCase())).append(",");
            }

            if (builder.length() > 0) {
                builder.replace(builder.length() - 1, builder.length(), " ");
            }
            for (String tablaExtra : baseAfecta) {
                builder.append(" LEFT OUTER JOIN ").append(tablaExtra);
            }

            String[] campos;
            builder.append(" WHERE ");
            //necesarios
            builder.append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase())).append(".plazasPorEmpleado.clave = ").append(aliasTabla.get(PlazasPorEmpleadosMov.class.getSimpleName().toLowerCase())).append(".plazasPorEmpleado.clave ");
            String usandoTablaIntegrados = "";
            for (i = 0; i < tablas.length; i++) {
                if (tablas[i].equalsIgnoreCase(SalariosIntegrados.class.getSimpleName())) {
                    builder.append("AND ").append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase())).append(".empleado.id = ");
                    builder.append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".empleados.id ");
                    builder.append("AND ").append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".fecha = (SELECT MAX (s.fecha) FROM SalariosIntegrados s WHERE (s.fecha BETWEEN ");
                    builder.append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase()));
                    builder.append(".periodosNomina.fechaInicial AND ").append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase())).append(".periodosNomina.fechaFinal + 1) ");
                    builder.append("AND s.empleados.id = ").append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".empleados.id)) ");
                    usandoTablaIntegrados = tablas[i];
                    break;
                }
            }

            builder.append(" AND ");
            ////
            if (camposWhere != null) {
                for (i = 0; i < camposWhere.length; i++) {
                    campos = camposWhere[i].split("#");
                    if (i > 0) {
                        builder.append(" AND ");
                    }
                    campoNuevo.append(campos[0]);
                    for (j = 0; j < tablas.length; j++) {
                        if (campos[0].toLowerCase().startsWith(tablas[j].toLowerCase())) {
                            campoNuevo.replace(0, tablas[j].length(), aliasTabla.get(tablas[j].toLowerCase()));
                            break;
                        }
                    }
                    builder.append(campoNuevo);
                    campoNuevo.delete(0, campoNuevo.length());
                    if (campos.length > 1) {
                        builder.append(" ").append(campos[1]).append(" :").append("parametros").append(String.valueOf(i));
                    } else {
                        builder.append(" = :").append("parametros").append(String.valueOf(i));
                    }
                    if (campos[0].equalsIgnoreCase("movNomConcep.empleado.clave") & usandoTablaIntegrados.length() > 0) { //usado salario integrado comparacion
                        builder.append(" AND ").append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".empleados.clave");
                        if (campos.length > 1) {
                            builder.append(" ").append(campos[1]).append(" :").append("parametrosExtra").append(String.valueOf(i));
                        } else {
                            builder.append(" = :").append("parametrosExtra").append(String.valueOf(i));
                        }
                    }
                }

                if (camposOrden.length > 0) {
                    StringBuilder orden = new StringBuilder(" ORDER BY ");
                    for (i = 0; i < camposOrden.length; i++) {
                        for (j = 0; j < tablas.length; j++) {
                            campoNuevo.append(camposOrden[i]);
                            if (camposOrden[i].toLowerCase().startsWith(tablas[j].toLowerCase())) {
                                campoNuevo.replace(0, tablas[j].length(), aliasTabla.get(tablas[j].toLowerCase()));
                            }
                            orden.append(campoNuevo).append(",");
                            campoNuevo.delete(0, campoNuevo.length());
                            break;
                        }
                    }
                    if (orden.length() > 0) {
                        orden.replace(orden.length() - 1, orden.length(), " ");
                    }
                    builder.append(orden);
                }
            }
        } catch (Exception e) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("construyeQueryConsultaCriterio()_Error").append(e));
        }
        return builder.toString();
    }

    /*
     * usa para evaluar cuando la entidad viene de mas de una entidad evaluando
     * que las entidades medias puedan ser nulas ejemplo
     * movimientos.empleados.clave se pasa en campo movimientos.empleados y en
     * split clave
     */
    private String construyeCamposCaseWhenOuter(String tabla, List<String> split, String valorCaseWhen) {
        StringBuilder campoSlit = new StringBuilder();
        String nombreTabla = split.get(0).substring(0, 2).concat("Outer");
        if (split.size() > 2) {
            String outer = split.get(0);
            if (!aliasTablaExtras.containsKey(split.get(0).toLowerCase())) {
                String[] bt = new String[2];
                bt[0] = tabla.concat(".").concat(split.get(0));
                bt[1] = nombreTabla + contTablaOuter;
                aliasTablaExtras.put(split.get(0).toLowerCase(), bt);
                ordenTablasOuter.add(split.get(0).toLowerCase());
            } else {
                outer = ((String[]) aliasTablaExtras.get(split.get(0).toLowerCase()))[1];
            }
            campoSlit.append(construyeCamposCaseWhenOuter(outer, split.subList(1, split.size()), valorCaseWhen));
        } else {
            String outer = nombreTabla + contTablaOuter;
            if (!aliasTablaExtras.containsKey(split.get(0).toLowerCase())) {
                String[] bt = new String[2];
                if (aliasTablaExtras.containsKey(tabla.toLowerCase())) {
                    String[] datos = aliasTablaExtras.get(tabla.toLowerCase());
                    tabla = datos[1];
                }
                bt[0] = tabla.concat(".").concat(split.get(0));
                bt[1] = outer;
                aliasTablaExtras.put(split.get(0).toLowerCase(), bt);
                ordenTablasOuter.add(split.get(0).toLowerCase());
            } else {
                outer = ((String[]) aliasTablaExtras.get(split.get(0).toLowerCase()))[1];
            }
            campoSlit.append("CASE WHEN (").append(outer).append(" IS NULL) THEN ").append(valorCaseWhen).append(" ELSE CASE WHEN (");
            campoSlit.append(outer).append(".").append(split.get(1)).append(" IS NULL) THEN ").append(valorCaseWhen).append(" ELSE ");
            campoSlit.append(outer).append(".").append(split.get(1)).append(" END END,");
            contTablaOuter++;
        }
        return campoSlit.toString();
    }

    private String construyeQueryConsultaCriterioOuter(String[] tablas, String[] camposMostrar, String[] camposWhere, String[] camposOrden) {//VIC01
        /*
         * ---@ Para SubConsultas ("from tabla t where t.id = 1") ---#Para
         * Indicar como deseas Filtrar (>,<,!=,IN) Ejemplos al usar canpos de
         * filtrados: 1.- NombreCampo#!=,NombreCampo#>,NombreCampo#< 2.-
         * NombreCampo@ 3.- NombreCampo#IN@
         */
        try {
            int i, j;
            contTablaOuter = 0;
            Map<String, String> aliasTabla = new HashMap<String, String>();
            ordenTablasOuter = new ArrayList<String>();
            aliasTablaExtras = new HashMap<String, String[]>();
            for (i = 0; i < tablas.length; i++) {
                aliasTabla.put(tablas[i].toLowerCase(), "o".concat(String.valueOf(i)));
            }
            List<String> camposBuscar = new ArrayList<String>(Arrays.asList(camposMostrar));
            if (camposOrden.length > 0) {
                for (String strOrden : camposOrden) {
                    boolean noExiste = true;
                    for (String strBuscar : camposMostrar) {
                        if (strBuscar.trim().equalsIgnoreCase(strOrden.trim())) {
                            noExiste = false;
                            break;
                        }
                    }
                    if (noExiste) {
                        camposBuscar.add(strOrden);
                    }
                }

            }
            camposMostrar = camposBuscar.toArray(new String[]{});
            builder = new StringBuilder("");
            StringBuilder strCampos = new StringBuilder("SELECT "), strTablas = new StringBuilder(" FROM "), strWhere = new StringBuilder(" WHERE "), strOrden = new StringBuilder(" ORDER BY ");
            StringBuilder campoNuevo = new StringBuilder("");
            String ruta[];
            if (camposMostrar.length > 0) {
                String valorCaseWhen;
                Class tipoDato;
                for (String campo : camposMostrar) {
                    for (i = 0; i < tablas.length; i++) {
                        campoNuevo.delete(0, campoNuevo.length());
                        campoNuevo.append(campo);
                        if (campoNuevo.toString().trim().substring(0, tablas[i].length()).equalsIgnoreCase(tablas[i])) {
                            campoNuevo.replace(0, tablas[i].length(), aliasTabla.get(tablas[i].toLowerCase()));
                            tipoDato = buscarTipoDatoCampo(campo);
                            valorCaseWhen = valorCasePorTipoDato(tipoDato);
                            ruta = campoNuevo.toString().split("\\.");
                            if (ruta.length > 2) {
                                List<String> listCampos = Arrays.asList(ruta);
                                strCampos.append(construyeCamposCaseWhenOuter(ruta[0], listCampos.subList(1, listCampos.size()), valorCaseWhen));
                                break;
                            } else {
                                strCampos.append("CASE WHEN (").append(campoNuevo).append(" IS NULL) THEN ").append(valorCaseWhen).append(" ELSE ");
                                strCampos.append(campoNuevo).append(" END,");
                                break;
                            }
                        }
                    }
                }
                if (strCampos.length() > 0) {
                    strCampos.replace(strCampos.length() - 1, strCampos.length(), " ");
                }
                builder.append(strCampos);
            } else {
                builder.append("SELECT ").append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase()));
            }

            if (builder.length() > 0) {
                builder.replace(builder.length() - 1, builder.length(), " ");
            }
            /**
             * carga tablas
             */
            for (String tabla : tablas) {
                strTablas.append(tabla).append(" ").append(aliasTabla.get(tabla.toLowerCase())).append(",");
            }

            if (strTablas.length() > 0) {
                strTablas.replace(strTablas.length() - 1, strTablas.length(), " ");
            }
            if (aliasTablaExtras.size() > 0) {
                String[] outer;
                for (String tablaOuter : ordenTablasOuter) {
                    outer = aliasTablaExtras.get(tablaOuter);
                    strTablas.append(" LEFT OUTER JOIN ").append(outer[0]).append(" ").append(outer[1]).append(" ");
                }
            }

            builder.append(strTablas);

            String[] campos;
            //necesarios
            strWhere.append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase())).append(".plazasPorEmpleado.empleados.clave = ").append(aliasTabla.get(PlazasPorEmpleadosMov.class.getSimpleName().toLowerCase())).append(".plazasPorEmpleado.empleados.clave ");
            String usandoTablaIntegrados = "";
            for (i = 0; i < tablas.length; i++) {
                if (tablas[i].equalsIgnoreCase(SalariosIntegrados.class.getSimpleName())) {
                    strWhere.append("AND ").append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase())).append(".empleado.id = ");
                    strWhere.append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".empleados.id ");
                    strWhere.append("AND ").append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".fecha = (SELECT MAX (s.fecha) FROM SalariosIntegrados s WHERE (s.fecha BETWEEN ");
                    strWhere.append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase()));
                    strWhere.append(".periodosNomina.fechaInicial AND ").append(aliasTabla.get(MovNomConcep.class.getSimpleName().toLowerCase())).append(".periodosNomina.fechaFinal + 1) ");
                    strWhere.append("AND s.empleados.id = ").append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".empleados.id)) ");
                    usandoTablaIntegrados = tablas[i];
                    break;
                }
            }

            strWhere.append(" AND ");
            if (camposWhere != null) {
                for (i = 0; i < camposWhere.length; i++) {
                    campos = camposWhere[i].split("#");
                    if (i > 0) {
                        strWhere.append(" AND ");
                    }
                    campoNuevo.delete(0, campoNuevo.length());
                    campoNuevo.append(campos[0]);
                    for (j = 0; j < tablas.length; j++) {
                        if (campos[0].toLowerCase().startsWith(tablas[j].toLowerCase())) {
                            campoNuevo.replace(0, tablas[j].length(), aliasTabla.get(tablas[j].toLowerCase()));
                            break;
                        }
                    }
                    ruta = campoNuevo.toString().split("\\.");
                    if (aliasTablaExtras.size() > 0 & ruta.length > 2) {
                        if (aliasTablaExtras.containsKey(ruta[ruta.length - 2].toLowerCase())) {
                            String[] outer = aliasTablaExtras.get(ruta[ruta.length - 2].toLowerCase());
                            campoNuevo.delete(0, campoNuevo.length());
                            campoNuevo.append(outer[1]).append(".").append(ruta[ruta.length - 1]);
                        }
                    }

                    strWhere.append(campoNuevo);
                    campoNuevo.delete(0, campoNuevo.length());
                    if (campos.length > 1) {
                        strWhere.append(" ").append(campos[1]).append(" :").append("parametros").append(String.valueOf(i));
                    } else {
                        strWhere.append(" = :").append("parametros").append(String.valueOf(i));
                    }
                    if (campos[0].equalsIgnoreCase("movNomConcep.empleado.clave") & usandoTablaIntegrados.length() > 0) { //usado salario integrado comparacion
                        strWhere.append(" AND ").append(aliasTabla.get(SalariosIntegrados.class.getSimpleName().toLowerCase())).append(".empleados.clave");
                        if (campos.length > 1) {
                            strWhere.append(" ").append(campos[1]).append(" :").append("parametrosExtra").append(String.valueOf(i));
                        } else {
                            strWhere.append(" = :").append("parametrosExtra").append(String.valueOf(i));
                        }
                    }
                }
                builder.append(strWhere);

                if (camposOrden.length > 0) {
                    for (i = 0; i < camposOrden.length; i++) {
                        for (j = 0; j < tablas.length; j++) {
                            campoNuevo.delete(0, campoNuevo.length());
                            campoNuevo.append(camposOrden[i]);
                            if (campoNuevo.toString().trim().toLowerCase().startsWith(tablas[j].toLowerCase())) {
                                campoNuevo.replace(0, tablas[j].length(), aliasTabla.get(tablas[j].toLowerCase()));
                                campos = campoNuevo.toString().split("\\.");
                                if (campos.length < 2) {
                                    strOrden.append(campoNuevo).append(",");
                                } else {
                                    if (aliasTablaExtras.containsKey(campos[campos.length - 2].toLowerCase())) {
                                        String[] outer = aliasTablaExtras.get(campos[campos.length - 2].toLowerCase());
                                        campoNuevo.delete(0, campoNuevo.length());
                                        campoNuevo.append(outer[1]).append(".").append(campos[campos.length - 1]);
                                    }
                                    strOrden.append(campoNuevo).append(",");
                                }
                                break;
                            }
                        }
                    }
                    if (strOrden.length() > 0) {
                        strOrden.replace(strOrden.length() - 1, strOrden.length(), " ");
                    }
                    builder.append(strOrden);
                }
            }
        } catch (Exception e) {
            System.err.println(builder.delete(0, builder.length()).append(msgError).append("construyeQueryConsultaCriterioOuter()_Error").append(e));
            mensajeResultado.setNoError(ControlErroresHibernate.buscaNoErrorPorExcepcion(e.getClass()));
            mensajeResultado.setError(e.getLocalizedMessage());
            mensajeResultado.setResultado(null);
        }
        aliasTablaExtras = null;
        ordenTablasOuter = null;
//////        int x = 0;
//////        System.out.println("parte 1 " + builder.substring(x, (builder.length() / 2)));
//////        System.out.println("parte 2 " + builder.substring((builder.length() / 2), builder.length()));

        return builder.toString();
    }

    private Boolean existeTablaMEFMaster(String tabla) {//JSA01
        Iterator iterator;
        Boolean existeTabla = false;
        Boolean continuar = true;
        iterator = HibernateUtil.getConfigDBMaestra().getTableMappings();
        // iterator = HibernateUtil.getConfigMEFMaster().getTableMappings();
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
//////    public ConsultaGenericaEspecialesDAO() {
//////        String[] tablas = new String[]{MovNomConcep.class.getSimpleName(), PlazasPorEmpleadosMov.class.getSimpleName()};
//////        String camposMostrar = "movNomConcep.concepNomDefi.descripcionAbreviada,plazasPorEmpleadosMov.bancos.descripcion,movNomConcep.concepNomDefi.descripcion,plazasPorEmpleadosMov.departamentos.descripcion,plazasPorEmpleadosMov.formasDePago.descripcion,movNomConcep.movNomConceParam.paraConcepDeNom.descripcion,movNomConcep.periodosNomina.descripcion";
//////        camposMostrar = "movNomConcep.concepNomDefi.descripcionAbreviada,plazasPorEmpleadosMov.bancos.descripcion";
//////        String camposOrden = "movNomConcep.periodosNomina.clave,movNomConcep.concepNomDefi.descripcionAbreviada";
//////        String[] camposWhere; /////= ///new String[]{"movNomConcep.razonesSociales.clave", "movNomConcep.empleado.clave#>=", "movNomConcep.empleado.clave#<=", "movNomConcep.tipoNomina.clave", "movNomConcep.periodosNomina.clave", "salariosIntegrados.tipoDeSalario"};
//////        camposWhere = new String[]{"movNomConcep.razonesSociales.clave", "movNomConcep.tipoNomina.clave", "movNomConcep.periodosNomina.clave"};
//////        Object[] valoresWhere = new Object[]{"1", "5"};
//////        generarQueryCriterioConsulta(tablas, camposMostrar.split(","), camposWhere, valoresWhere, camposOrden.split(","));
//////    }
//////
//////    public static void main(String[] args) {
//////        new ConsultaGenericaEspecialesDAO();
//////
//////    }
}
