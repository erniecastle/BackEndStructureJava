/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.util;

import com.mef.erp.modelo.entidad.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 *
 * @author Admin
 */
public class ConstruyeQueries {

    private final String msgError = "ServerERP.MSERR_C_".concat(this.getClass().getName()).concat(".");
    private Map<String, String> aliasTablaOuter;
    private int contTablasOuter = 0;
    private Map<String, Object> parametrosQuery;
    private List<String> tablasOuter;
    private List<String> listParametros;
    public static String LEFTJOIN = "LEFT OUTER JOIN";
    public static String RIGHTJOIN = "RIGHT OUTER JOIN";
    public static String INNERJOIN = "INNER JOIN";
    private List<DatosEspeciales> listDatosFormula;
    private String fuenteDatos = "";
    private String tipoOrdenado;
    Properties propertieFuenteDatos;
    public static String[] camposRaizMovimientos = new String[]{MovNomConcep.class.getSimpleName(), MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"), MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"), MovNomConcep.class.getSimpleName().concat(".periodosNomina.clave"), MovNomConcep.class.getSimpleName().concat(".concepNomDefi.clave"), MovNomConcep.class.getSimpleName().concat(".empleado.clave"), MovNomConcep.class.getSimpleName().concat(".plazasPorEmpleado.clave"), MovNomConcep.class.getSimpleName().concat(".razonesSociales.clave")};
    public static String[] camposSumarMov2Meses = new String[]{MovNomConcep.class.getSimpleName().concat(".resultado"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultado"), MovNomConcep.class.getSimpleName().concat(".movNomBaseAfecta.resultadoExento"), MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor")};
    private boolean isMov2Periodos = false, usaFiniquitos = false;
    private String[] valoresDatosEspeciales;
    private String[] camposFiltroMovimientos;

    //es el mes que se usara para la busqueda de resultados en los movimientos
//////    SELECT mn FROM MovNomConcep mn INNER JOIN mn.concepNomDefi con INNER JOIN mn.empleado em INNER JOIN mn.plazasPorEmpleado pem INNER JOIN mn.tipoCorrida tc INNER JOIN mn.tipoNomina tn INNER JOIN mn.razonesSociales rs 
//////INNER JOIN mn.movNomBaseAfecta mba INNER JOIN  mn.movNomConceParam mnp INNER JOIN mn.periodosNomina pn
    public ConstruyeQueries() {
        init();
////////        mapeaTablasCampo(camposRaizMovimientos);
////////        construyeMovim2Periodos(MovNomConcep.class.getSimpleName().concat(".resultado"), "0.0");
        ///System.out.println(construyeFromConsulta(camposRaizMovimientos, INNERJOIN, "H"));
//////        CompEjec compEjec = new CompEjec();
//////        propertieFuenteDatos = compEjec.abrirPropiedadBundle("FuentesDeDatos");
//////        construyeCampoConcatenar(new String[]{"Percepcion_Path", "Empleados_Apellidos_Nombre_Path"});
        // constuyeConsultaCampoEspecial("Empleados_Apellidos_Nombre_Path");
        ///|OR|x4.naturaleza)|=| 1
        ////construyeWhereExtras(new String[]{"AND |x4.naturaleza|=|0"});
        ///System.out.println(construyeQueryDatosEspeciales(new String[]{"MovNomConcep.resultado|SUM"}, new String[]{"MovNomConcep.concepNomDefi.clave"}, new Object[]{"1"}, new String[]{}));
    }

    public ConstruyeQueries(Properties propertieFuenteDatos, String fuenteDatos) {
        init();
        this.propertieFuenteDatos = propertieFuenteDatos;
        this.fuenteDatos = fuenteDatos;
        ////System.out.println(construyeQueryDatosEspeciales(new String[]{"MovNomConcep.resultado|SUM"}, new String[]{"MovNomConcep.concepNomDefi.clave"}, new Object[]{"1"}, new String[]{}));
    }

    private void init() {
        aliasTablaOuter = new HashMap<String, String>();
        parametrosQuery = new HashMap<String, Object>();
        tablasOuter = new ArrayList<String>();
        listParametros = new ArrayList<String>();
        listDatosFormula = new ArrayList<DatosEspeciales>();
        isMov2Periodos = false;
        usaFiniquitos = false;
    }

////    public static void main(String[] args) {
////        new ConstruyeQueries();
////    }
    public Map<String, String> getAliasTablaOuter() {
        return aliasTablaOuter;
    }

    public Map<String, Object> getParametrosQuery() {
        return parametrosQuery;
    }

    public List<String> getListParametros() {
        return listParametros;
    }

    public List<DatosEspeciales> getListDatosFormula() {
        return listDatosFormula;
    }

    public DatosQuery construyeQueryDatos(String[] campos, String[] camposWhere, Object[] valoresWhere, String[] camposWhereExtras, String[] groupBy, String[] orderBy) {
        aliasTablaOuter = new HashMap<String, String>();
        parametrosQuery = new HashMap<String, Object>();
        tablasOuter = new ArrayList<String>();
        listParametros = new ArrayList<String>();

        DatosQuery datosQuery = new DatosQuery();
        StringBuilder consulta = new StringBuilder();
        generaListaTablasMapeadas(campos, camposWhere, camposWhereExtras, orderBy);
        consulta.append(construyeSelectDatos(campos)).append(" ");
        consulta.append(construyeFromConsulta(LEFTJOIN)).append(" ");
        consulta.append(construyeWhereConParametro(camposWhere, valoresWhere, camposWhereExtras)).append(" ");
//////        consulta.append(construyeGroupBy(groupBy)).append(" ");
//////        consulta.append(construyeOrderBy(orderBy)).append(" ");
        datosQuery.setAliasTablas(aliasTablaOuter);
        datosQuery.setParametrosCampos(parametrosQuery);
        datosQuery.setQuery(consulta.toString());
        datosQuery.setListParametros(listParametros);
        datosQuery.setConParametros(true);

        return datosQuery;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        tablasOuter = null;
        aliasTablaOuter = null;
        parametrosQuery = null;
        listParametros = null;
        listDatosFormula = null;
    }

    public String construyeSelectDatos(String[] campos) {
        StringBuilder campoSelect = new StringBuilder("SELECT ");
        int i;
        for (i = 0; i < campos.length; i++) {
            isMov2Periodos = evaluaCampoTiene2Movim(campos[i]);
            if (isMov2Periodos) {
                break;
            }
        }
////////        if (propertieFuenteDatos != null) {
////////            for (i = 0; i < campos.length; i++) {
////////                if (propertieFuenteDatos.containsKey(campos[i])) {
////////                    if (propertieFuenteDatos.getProperty(campos[i]).startsWith(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
////////                        mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.id")});
////////                        break;
////////                    }
////////                }
////////            }
////////        }
        if (isMov2Periodos) {
            mapeaTablasCampo(camposRaizMovimientos);
        }
        campoSelect.append(construyeSintaxisCampos(campos, ModoSintaxis.SELECT));
        return campoSelect.toString();
    }

    public String construyeFromConsulta(String tipoJoin) {
        tipoJoin = (tipoJoin == null ? LEFTJOIN : tipoJoin.trim().length() == 0 ? LEFTJOIN : tipoJoin);
        StringBuilder campoFrom = new StringBuilder("FROM ");
        String ruta[];
        String tabla;
        int i;
        for (i = 0; i < tablasOuter.size(); i++) {
            tabla = tablasOuter.get(i);
            ruta = tabla.split("_");
            String campo = "";
            if (ruta.length == 1) {
                if (i > 0) {
                    campoFrom.append(",");
                }
                campo = (ruta[ruta.length - 1]) + (" ") + (aliasTablaOuter.get(ruta[ruta.length - 1]));
            } else {
                campo = (aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))) + (".") + (ruta[ruta.length - 1]) + (" ") + (aliasTablaOuter.get(tabla));
            }
            String simbolo = "";
            if (i > 0 && campoFrom.charAt(campoFrom.length() - 1) != ',') {
                if (tabla.contains("MovNomConcep") & (tabla.contains("MovNomConcep_movNomConceParam") | tabla.contains("MovNomConcep_movNomConceParam_paraConcepDeNom")
                        | tabla.contains("MovNomConcep_movNomBaseAfecta") | tabla.contains("MovNomConcep_movNomBaseAfecta_baseAfecConcepNom")
                        | tabla.contains("MovNomConcep_movNomBaseAfecta_baseAfecConcepNom_baseNomina"))) {
                    simbolo = "||";
                } else {
                    simbolo = "@";
                }
            }
            campoFrom.append(simbolo).append(campo);
        }
        return campoFrom.toString().replace("@", " ".concat(tipoJoin).concat(" ")).replace("||", " ".concat(RIGHTJOIN).concat(" "));
    }

    private String construyeGroupByDatosEspecial(String[] campos) {
        StringBuilder camposGroup = new StringBuilder("GROUP BY ");
        String valores[], ruta[];
        String path;
        for (String str : campos) {
            valores = str.split("\\|");
            path = valores[0];
            ruta = path.split("\\.");
            path = path.substring(0, path.length() - ruta[ruta.length - 1].length() - 1);
            path = path.replace(".", "_");
            if (aliasTablaOuter.containsKey(path)) {
                path = aliasTablaOuter.get(path);
                camposGroup.append(path).append(".").append(ruta[ruta.length - 1]).append(",");
            }
        }
        camposGroup.replace(camposGroup.length() - 1, camposGroup.length(), "");
        return camposGroup.toString();
    }

    /*
     * pendiente
     */
    public String construyeGroupBy(String[] campos) {
        StringBuilder camposGroup = new StringBuilder();
        if (campos != null) {
            boolean usaMovParamValor = false;
            boolean addCampoNaturalezaGrupo = false;
            if (campos.length > 0) {
                boolean isValorParametroOConcepto = false;

                String grupoTotalEmpleados = "";
                List<String> camposList = new ArrayList<String>();
                int i;
                String campTipo;
                for (i = 0; i < campos.length; i++) {
                    if (campos[i].lastIndexOf("#") > -1) {
                        campTipo = campos[i].substring(0, campos[i].lastIndexOf("#"));
                    } else {
                        campTipo = campos[i];
                    }
                    if (campTipo.startsWith("ValorParametroOConcepto_Path")) {
                        isValorParametroOConcepto = true;
                    }

                    if (!evaluaCampoTiene2Movim(campos[i])) {
                        /*omite campos especiales en el agrupado*/
                        if (!"Percepcion_Path".equalsIgnoreCase(campos[i]) & !"Deduccion_Path".equalsIgnoreCase(campos[i]) & !"ConceptoTipoDato_Path".equalsIgnoreCase(campos[i]) & !"ValorParametro_Path".startsWith(campTipo) & !"Etiqueta_Valor".equalsIgnoreCase(campos[i])
                                & !"ConceptoTipoCalculo_Path".equalsIgnoreCase(campos[i]) & !"ConceptoTipoInformativo_Path".equalsIgnoreCase(campos[i]) & !"ConceptoDifPercepDeduc_Path".equalsIgnoreCase(campos[i]) & !"ValorParametroOConcepto_Path".startsWith(campTipo)
                                & !"TotalImportePorConcepto_Path".equalsIgnoreCase(campos[i]) & !"TotalImporteExentoPorConcepto_Path".equalsIgnoreCase(campos[i]) & !"TotalCantidadPorConcepto_Path".startsWith(campTipo) & !"TotalImporteGravablePorConcepto_Path".equalsIgnoreCase(campos[i])
                                & !"TotalImportePorConceptoDato_Path".startsWith(campTipo) & !"TotalImporteGravablePorConceptoDato_Path".startsWith(campTipo) & !"TotalImporteExentoPorConceptoDato_Path".startsWith(campTipo) & !"TotalCantidadPorConceptoDato_Path".startsWith(campTipo)
                                & !"TotalBaseISR_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRNormal_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRDirecto_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRAnual_Path".equalsIgnoreCase(campos[i])
                                & !"TotalBaseISRGravable_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRGravableNormal_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRGravableDirecto_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRGravableAnual_Path".equalsIgnoreCase(campos[i])
                                & !"TotalBaseISRExento_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRExentoNormal_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRExentoDirecto_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseISRExentoAnual_Path".equalsIgnoreCase(campos[i])
                                & !"TotalBaseIMSS_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseIMSSFija_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseIMSSVariable_Path".equalsIgnoreCase(campos[i])
                                & !"TotalBaseIMSSGravado_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseIMSSGravadoFija_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseIMSSGravadoVariable_Path".equalsIgnoreCase(campos[i])
                                & !"TotalBaseIMSSExento_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseIMSSExentoFija_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseIMSSExentoVariable_Path".equalsIgnoreCase(campos[i])
                                & !"TotalBaseInfonavit_Path".equalsIgnoreCase(campos[i]) & !"TotalBasePTU_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseImpuestoNomina_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseDespensa_Path".equalsIgnoreCase(campos[i])
                                & !"TotalBaseFondoAhorro_Path".equalsIgnoreCase(campos[i]) & !"TotalBaseAguinaldo_Path".equalsIgnoreCase(campos[i]) & !"TotalEmpleadosDepartamento_Path".equalsIgnoreCase(campTipo) & !"TotalEmpleadosCentroCosto_Path".equalsIgnoreCase(campTipo)
                                & !"TotalEmpleadosPuesto_Path".equalsIgnoreCase(campTipo) & !"TotalBaseOtros_Path".equalsIgnoreCase(campTipo)) {
                            camposList.add(campos[i]);
                        }
                    }
                }
                if (isMov2Periodos) {
                    String field;
                    String[] camposSplit;
                    for (i = 0; i < campos.length; i++) {
                        field = campos[i];
                        if (field.contains("#")) {
                            camposSplit = field.split("#");
                            field = camposSplit[0];
                        } else if (field.contains("|")) {
                            camposSplit = field.split("\\|");
                            field = camposSplit[0];
                        }
                        if (propertieFuenteDatos.containsKey(field)) {
                            field = propertieFuenteDatos.getProperty(field);
                            if (field.contains("|")) {
                                camposSplit = field.split("\\|");
                                field = camposSplit[0];
                            }
                            if (field.equalsIgnoreCase("ValorParametroOConcepto_Path")) {
                                mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro"), MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo"), MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.numero"), MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")});
                                addCampoNaturalezaGrupo = true;
                            }
                            if (field.startsWith(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
                                ////mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.id")});
                                usaMovParamValor = true;
                            } else if (propertieFuenteDatos.containsKey(field)) {
                                if (propertieFuenteDatos.getProperty(field).startsWith(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
                                    //// mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.id")});
//////                                    if (field.equalsIgnoreCase("ValorParametroOConcepto_Path")) {
//////                                        mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")});
//////                                    }
                                    usaMovParamValor = true;
                                }
                            }
                        } else if (campos[i].startsWith(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor"))) {
                            /////mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.id")});
                            usaMovParamValor = true;
                        }
                    }
                }
                for (i = 0; i < campos.length; i++) {
                    if ("TotalEmpleadosDepartamento_Path".equalsIgnoreCase(campos[i]) | "TotalEmpleadosCentroCosto_Path".equalsIgnoreCase(campos[i]) | "TotalEmpleadosPuesto_Path".equalsIgnoreCase(campos[i])) {
                        if (propertieFuenteDatos.containsKey(campos[i])) {
                            String contenido = getAliasTablaCampo(propertieFuenteDatos.getProperty(campos[i]));
                            if (!grupoTotalEmpleados.contains(contenido)) {
                                grupoTotalEmpleados = grupoTotalEmpleados.concat(contenido).concat(",");
                            }
                        }
                    }
                }
                if (grupoTotalEmpleados.length() > 0) {
                    if (grupoTotalEmpleados.charAt(grupoTotalEmpleados.length() - 1) == ',') {
                        grupoTotalEmpleados = grupoTotalEmpleados.substring(0, grupoTotalEmpleados.length() - 1);
                    }
                }
                campos = camposList.toArray(new String[]{});
                camposGroup.append(construyeSintaxisCampos(campos, ModoSintaxis.AGRUPADO));
                if (grupoTotalEmpleados.length() > 0) {
                    camposGroup.append(",").append(grupoTotalEmpleados);
                }
//////                if (usaMovParamValor) {
//////                    camposGroup.append(",").append(aliasTablaOuter.get(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom").replace(".", "_")));
//////                }

                if (addCampoNaturalezaGrupo) {
                    camposGroup.append(",").append(getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"))).append(".").append(getCampoFinal(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")));
                }
                if (camposGroup.length() > 0) {
                    camposGroup.insert(0, "GROUP BY ");
                }
                if (isValorParametroOConcepto | usaMovParamValor) {
                    ////String[] grupoParametroOConcep = new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro"), MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo"), MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.numero"), MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza")};
                    if (camposGroup.toString().charAt(camposGroup.length() - 1) != ',') {
                        camposGroup.append(",");
                    }
                    mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro")});
                    String tabla = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro")), campoFinal;
                    if (usaMovParamValor) {
                        camposGroup.append(tabla).append(",");
                    }
                    campoFinal = getCampoFinal(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro"));
                    camposGroup.append(tabla).append(".").append(campoFinal).append(",");
                    campoFinal = getCampoFinal(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo"));
                    camposGroup.append(tabla).append(".").append(campoFinal).append(",");
                    campoFinal = getCampoFinal(MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.numero"));
                    camposGroup.append(tabla).append(".").append(campoFinal);
                    if (isValorParametroOConcepto) {
                        tabla = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"));
                        campoFinal = getCampoFinal(MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza"));
                        camposGroup.append(",").append(tabla).append(".").append(campoFinal);
                    }
                }
            }
        }
        if (isMov2Periodos) {
            if (camposGroup.length() == 0) {
                camposGroup.append("GROUP BY ");
            } else {
                camposGroup.append(",");
            }
            int i;
            String ruta[];
            String tabla;
            for (i = 0; i < camposRaizMovimientos.length; i++) {
                tabla = camposRaizMovimientos[i].replace(".", "_");
                ruta = tabla.split("_");
                if (ruta.length > 1) {
                    if (aliasTablaOuter.containsKey(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))) {
                        camposGroup.append(aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]);
                        if (camposRaizMovimientos.length - 1 > i) {
                            camposGroup.append(", ");
                        }
                    }
                }
            }
            if (usaFiniquitos) {
                camposGroup.append(", ").append(getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave")));
            }
        }

        return camposGroup.toString();
    }

    public String construyeOrderBy(String[] campos, String tipoOrdenado) {
        if (campos != null) {
            if (campos.length > 0) {
                this.tipoOrdenado = tipoOrdenado;
                StringBuilder camposOrder = new StringBuilder();
                camposOrder.append(construyeSintaxisCampos(campos, ModoSintaxis.ORDEN));
                if (camposOrder.length() > 0) {
                    camposOrder.insert(0, "ORDER BY ");
                }
                return camposOrder.toString();
            }
        }
        return "";
    }

    public enum ModoSintaxis {

        SELECT, AGRUPADO, ORDEN;
    }

    public String construyeSintaxisCampos(String[] campos, ModoSintaxis modoSintaxis) {
        StringBuilder sintaxisCampo = new StringBuilder();
        String valores[], ruta[];
        String operador = "", path, campTipo;
        String valorCaseWhen;
        Class tipoDato;
        boolean isCampoSumado;
        int pos = -1;
        tipoOrdenado = tipoOrdenado == null ? "" : tipoOrdenado.toLowerCase();
        if (tipoOrdenado.trim().length() > 0) {
            tipoOrdenado = " ".concat(tipoOrdenado);
        }
        TipoNodoConsulta tipoNodoConsulta = TipoNodoConsulta.CAMPO;

        for (String str : campos) {
            valores = str.split("\\|");
            path = valores[0];
            pos++;
            if (valores.length > 1 & !valores[0].toString().startsWith("@")) {
                ///if (ModoSintaxis.SELECT == modoSintaxis) {
                sintaxisCampo.append(construyeCampoConcatenar(valores, "|")).append(",");
                //// }
            } else {
                if (valores[0].lastIndexOf("#") > -1) {
                    campTipo = valores[0].substring(0, valores[0].lastIndexOf("#"));
                } else {
                    campTipo = valores[0];
                }
                if (propertieFuenteDatos != null) {
                    if (propertieFuenteDatos.containsKey(campTipo)) {
                        tipoNodoConsulta = getTipoNodoProperty(campTipo.concat("_TipoNodo"));
                    }
                }

                if (valores[0].toString().startsWith("@")) {
                    if (ModoSintaxis.SELECT == modoSintaxis) {
                        listDatosFormula.add(new DatosEspeciales(str.substring(1, str.length()), pos, TipoNodoConsulta.FORMULA));
                        sintaxisCampo.append("'Dato").append(pos).append("'").append(",");
                    }
                    tipoNodoConsulta = TipoNodoConsulta.CAMPO;
                } else if (tipoNodoConsulta == TipoNodoConsulta.CAMPOESPECIAL) {
                    if ("Percepcion_Path".equalsIgnoreCase(valores[0]) | "Deduccion_Path".equalsIgnoreCase(valores[0]) | "ConceptoTipoDato_Path".equalsIgnoreCase(valores[0])
                            | "ConceptoTipoCalculo_Path".equalsIgnoreCase(valores[0]) | "ConceptoTipoInformativo_Path".equalsIgnoreCase(valores[0]) | "ConceptoDifPercepDeduc_Path".equalsIgnoreCase(valores[0])
                            | "TotalImportePorConcepto_Path".equalsIgnoreCase(valores[0]) | "TotalImporteExentoPorConcepto_Path".equalsIgnoreCase(valores[0]) | "TotalImporteGravablePorConcepto_Path".equalsIgnoreCase(valores[0])
                            | "TotalCantidadPorConcepto_Path".equalsIgnoreCase(campTipo) | "ValorParametro_Path".startsWith(campTipo) | "ValorParametroOConcepto_Path".startsWith(campTipo)
                            | "TotalImportePorConceptoDato_Path".startsWith(campTipo) | "TotalImporteExentoPorConceptoDato_Path".startsWith(campTipo) | "TotalImporteGravablePorConceptoDato_Path".startsWith(campTipo) | "TotalCantidadPorConceptoDato_Path".startsWith(campTipo)
                            | "TotalBaseISR_Path".startsWith(campTipo) | "TotalBaseISRNormal_Path".startsWith(campTipo) | "TotalBaseISRDirecto_Path".startsWith(campTipo) | "TotalBaseISRAnual_Path".startsWith(campTipo)
                            | "TotalBaseISRGravable_Path".startsWith(campTipo) | "TotalBaseISRGravableNormal_Path".startsWith(campTipo) | "TotalBaseISRGravableDirecto_Path".startsWith(campTipo) | "TotalBaseISRGravableAnual_Path".startsWith(campTipo)
                            | "TotalBaseISRExento_Path".startsWith(campTipo) | "TotalBaseISRExentoNormal_Path".startsWith(campTipo) | "TotalBaseISRExentoDirecto_Path".startsWith(campTipo) | "TotalBaseISRExentoAnual_Path".startsWith(campTipo)
                            | "TotalBaseIMSS_Path".equalsIgnoreCase(campTipo) | "TotalBaseIMSSFija_Path".equalsIgnoreCase(campTipo) | "TotalBaseIMSSVariable_Path".equalsIgnoreCase(campTipo)
                            | "TotalBaseIMSSGravado_Path".startsWith(campTipo) | "TotalBaseIMSSGravadoFija_Path".startsWith(campTipo) | "TotalBaseIMSSGravadoVariable_Path".startsWith(campTipo)
                            | "TotalBaseIMSSExento_Path".startsWith(campTipo) | "TotalBaseIMSSExentoFija_Path".startsWith(campTipo) | "TotalBaseIMSSExentoVariable_Path".startsWith(campTipo)
                            | "TotalBaseInfonavit_Path".startsWith(campTipo) | "TotalBasePTU_Path".startsWith(campTipo) | "TotalBaseImpuestoNomina_Path".startsWith(campTipo) | "TotalBaseDespensa_Path".startsWith(campTipo)
                            | "TotalBaseFondoAhorro_Path".startsWith(campTipo) | "TotalBaseAguinaldo_Path".startsWith(campTipo) | "TotalEmpleadosDepartamento_Path".equalsIgnoreCase(campTipo) | "TotalEmpleadosCentroCosto_Path".equalsIgnoreCase(campTipo)
                            | "TotalEmpleadosPuesto_Path".equalsIgnoreCase(campTipo) | "TotalBaseOtros_Path".startsWith(campTipo)) {
                        String[] datos, valorDato, listCampos;
                        datos = valores[0].split("#");
                        String valorComp = "";
                        if (datos.length > 1) {
                            valorComp = datos[1];
                            valores[0] = datos[0];
                        }
                        String valorMostrar = propertieFuenteDatos.getProperty(valores[0]);
                        if ("TotalEmpleadosDepartamento_Path".equalsIgnoreCase(campTipo) | "TotalEmpleadosCentroCosto_Path".equalsIgnoreCase(campTipo) | "TotalEmpleadosPuesto_Path".equalsIgnoreCase(campTipo)) {
                            valorMostrar = contruyeTotalesPlazasEmpleados(valorMostrar);
                        } else {
                            datos = propertieFuenteDatos.getProperty(valores[0].concat("_Datos")).split(",");
                            Object[] valorNaturaleza = new Object[datos.length];
                            listCampos = new String[datos.length];
                            Naturaleza n;
                            EnumTipoDato tipo = EnumTipoDato.NUMERICO;
                            boolean addMapeoDato, isRemoved = false;
                            int i;
                            String camposRemover = "";
                            String[] tmp;
                            for (i = 0; i < datos.length; i++) {
                                tmp = datos[i].split("\\|");
                                valorDato = propertieFuenteDatos.getProperty(tmp[0].concat("_Path")).split("\\|");
                                addMapeoDato = true;
                                if (valorDato.length > 1) {
                                    if (valorDato[1].equalsIgnoreCase("REMOVE")) {
                                        addMapeoDato = false;
                                        isRemoved = true;
                                    }
                                }
                                if (addMapeoDato) {
                                    String[] variables = obtieneVariablesFormula(valorMostrar);
                                    List<String> mapeoVariables = new ArrayList<String>();
                                    mapeoVariables.add(valorDato[0]);
                                    for (String var : variables) {
                                        if (var.split("\\.").length > 2) {
                                            mapeoVariables.add(var);
                                        }
                                    }
                                    mapeaTablasCampo(mapeoVariables.toArray(new String[]{}));
                                }
                                if (isRemoved) {
                                    valorNaturaleza[i] = valorDato[1];
                                    camposRemover = camposRemover.concat(valorDato[0]).concat(",");
                                } else {
                                    Class c = buscarTipoDatoCampo(valorDato[0]);
                                    if (valorDato.length > 1) {
                                        //if (!valorDato[1].equalsIgnoreCase("REMOVE ") ) {
                                        if (c == Naturaleza.class) {
                                            n = Naturaleza.getEnum(valorDato[1].trim());
                                            valorNaturaleza[i] = n.getNaturaleza();
                                        } else {
                                            valorNaturaleza[i] = valorDato[1];
                                        }
                                        tipo = EnumTipoDato.NUMERICO;
                                    } else if (valorComp.trim().length() > 0) {
                                        if (c == String.class) {
                                            tipo = EnumTipoDato.STRING;
                                        } else if (c.getSuperclass() == Number.class) {
                                            tipo = EnumTipoDato.NUMERICO;
                                        }
                                        valorNaturaleza[i] = valorComp;
                                    }
                                }
                                listCampos[i] = valorDato[0] + (tmp.length > 1 ? ("|" + tmp[1]) : "");
                            }
                            if ("ValorParametro_Path".startsWith(campTipo) | "ValorParametroOConcepto_Path".startsWith(campTipo) | "TotalCantidadPorConcepto_Path".startsWith(campTipo)
                                    | "TotalCantidadPorConceptoDato_Path".startsWith(campTipo)) {
                                boolean isTotal = false;
                                if ("TotalCantidadPorConcepto_Path".startsWith(campTipo) | "TotalCantidadPorConceptoDato_Path".startsWith(campTipo)) {
                                    isTotal = true;
                                }
                                String[] valoresMostrar = valorMostrar.split("\\|");
                                Object[] valoresMostrarExtra = null;
                                String campoComparar2 = "";
                                if (valoresMostrar.length > 1) {
                                    valoresMostrarExtra = new Object[]{Naturaleza.CALCULO.getNaturaleza(), Naturaleza.DATO.getNaturaleza(), Naturaleza.INFORMATIVO.getNaturaleza()};
                                    campoComparar2 = MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza");
                                }
                                if (camposRemover.lastIndexOf(",") > -1) {
                                    camposRemover = camposRemover.substring(0, camposRemover.length() - 1);
                                }
                                valorMostrar = construyeMovimValorParametro(valoresMostrar[0], valoresMostrar.length > 1 ? valoresMostrar[1] : "", listCampos[0], campoComparar2, valorNaturaleza, valoresMostrarExtra, camposRemover, isTotal);
                            } else {
                                boolean isTotal = false;
                                if ("TotalImportePorConcepto_Path".equalsIgnoreCase(valores[0]) | "TotalImporteExentoPorConcepto_Path".equalsIgnoreCase(valores[0]) | "TotalImporteGravablePorConcepto_Path".equalsIgnoreCase(valores[0])
                                        | "TotalImportePorConceptoDato_Path".equalsIgnoreCase(valores[0]) | "TotalImporteExentoPorConceptoDato_Path".equalsIgnoreCase(valores[0]) | "TotalImporteGravablePorConceptoDato_Path".startsWith(campTipo)
                                        | "TotalBaseISR_Path".startsWith(campTipo) | "TotalBaseISRNormal_Path".startsWith(campTipo) | "TotalBaseISRDirecto_Path".startsWith(campTipo) | "TotalBaseISRAnual_Path".startsWith(campTipo)
                                        | "TotalBaseISRGravable_Path".startsWith(campTipo) | "TotalBaseISRGravableNormal_Path".startsWith(campTipo) | "TotalBaseISRGravableDirecto_Path".startsWith(campTipo) | "TotalBaseISRGravableAnual_Path".startsWith(campTipo)
                                        | "TotalBaseISRExento_Path".startsWith(campTipo) | "TotalBaseISRExentoNormal_Path".startsWith(campTipo) | "TotalBaseISRExentoDirecto_Path".startsWith(campTipo) | "TotalBaseISRExentoAnual_Path".startsWith(campTipo)
                                        | "TotalBaseIMSS_Path".equalsIgnoreCase(campTipo) | "TotalBaseIMSSFija_Path".equalsIgnoreCase(campTipo) | "TotalBaseIMSSVariable_Path".equalsIgnoreCase(campTipo)
                                        | "TotalBaseIMSSGravado_Path".startsWith(campTipo) | "TotalBaseIMSSGravadoFija_Path".startsWith(campTipo) | "TotalBaseIMSSGravadoVariable_Path".startsWith(campTipo)
                                        | "TotalBaseIMSSExento_Path".startsWith(campTipo) | "TotalBaseIMSSExentoFija_Path".startsWith(campTipo) | "TotalBaseIMSSExentoVariable_Path".startsWith(campTipo)
                                        | "TotalBaseInfonavit_Path".startsWith(campTipo) | "TotalBasePTU_Path".startsWith(campTipo) | "TotalBaseImpuestoNomina_Path".startsWith(campTipo) | "TotalBaseDespensa_Path".startsWith(campTipo)
                                        | "TotalBaseFondoAhorro_Path".startsWith(campTipo) | "TotalBaseAguinaldo_Path".startsWith(campTipo) | "TotalBaseOtros_Path".startsWith(campTipo)) {
                                    isTotal = true;
                                }
                                valorMostrar = construyeCampoEspecial2Periodos(valorMostrar, listCampos, valorNaturaleza, tipo, isTotal);
                            }
                        }
                        ///valorMostrar = construyeComparacionCampoEspecial(valorMostrar, valorDato[0], valorNaturaleza, tipo);
                        sintaxisCampo.append(valorMostrar).append(",");
                    } else {
                        DatoCampoConsulta campoConsulta = constuyeConsultaCampoEspecial(path);
                        if (campoConsulta != null) {
                            sintaxisCampo.append(campoConsulta.getCampo()).append(",");
                        }
                    }
                    tipoNodoConsulta = TipoNodoConsulta.CAMPO;
                } else if (tipoNodoConsulta == TipoNodoConsulta.DATOCALCULO | tipoNodoConsulta == TipoNodoConsulta.DATOCALCULOSINPARAMETRO | tipoNodoConsulta == TipoNodoConsulta.DATOXML) {
                    if ("Etiqueta_Valor".equalsIgnoreCase((valores[0]))) {
                        if (getValoresDatosEspeciales() != null) {
                            if (ModoSintaxis.SELECT == modoSintaxis) {
                                sintaxisCampo.append("'").append(valoresDatosEspeciales[pos - 1]).append("'").append(",");
                            }
                        }
                    } else {
                        if (ModoSintaxis.SELECT == modoSintaxis) {
                            listDatosFormula.add(new DatosEspeciales(valores[0], pos, tipoNodoConsulta));
                            sintaxisCampo.append("'Dato").append(pos).append("'").append(",");
                        }
                    }
                    tipoNodoConsulta = TipoNodoConsulta.CAMPO;
                } else {
                    /*
                     * TipoCampo
                     */
                    if (path.equalsIgnoreCase(PlazasPorEmpleadosMov.class.getSimpleName().concat(".importe")) & fuenteDatos.equalsIgnoreCase("FuenteDatos_Empleados")) {
                        if (ModoSintaxis.SELECT == modoSintaxis) {
                            listDatosFormula.add(new DatosEspeciales(valores[0], pos, TipoNodoConsulta.DATOREMPLAZO));
                            sintaxisCampo.append("'Dato").append(pos).append("'").append(",");
                        }
                    } else {
                        tipoDato = buscarTipoDatoCampo(path);
                        if (modoSintaxis == ModoSintaxis.AGRUPADO) {
                            if (tipoDato.getPackage() != null) {
                                if (tipoDato.getPackage().getName().equalsIgnoreCase("com.mef.erp.modelo.entidad")) {
                                    return "";
                                }
                            }
                        }
                        valorCaseWhen = valorCasePorTipoDato(tipoDato);
                        if (RegistroIncapacidad.class.getSimpleName().concat(".ramoSeguro").equalsIgnoreCase(path) | RegistroIncapacidad.class.getSimpleName().concat(".tipoRiesgo").equalsIgnoreCase(path)
                                | RegistroIncapacidad.class.getSimpleName().concat(".secuelaConsecuencia").equalsIgnoreCase(path) | RegistroIncapacidad.class.getSimpleName().concat(".controlIncapacidad").equalsIgnoreCase(path)) {
                            valorCaseWhen = "-1";
                        }
                        isCampoSumado = evaluaCampoTiene2Movim(path);
                        ruta = path.split("\\.");
                        if (ruta.length == 1) {
                            sintaxisCampo.append(aliasTablaOuter.get(path));
                            if (ModoSintaxis.ORDEN == modoSintaxis) {
                                sintaxisCampo.append(tipoOrdenado);
                            }
                            sintaxisCampo.append(",");
                        } else {
                            path = path.substring(0, path.length() - ruta[ruta.length - 1].length() - 1);
                            path = path.replace(".", "_");
                            if (aliasTablaOuter.containsKey(path)) {
                                path = aliasTablaOuter.get(path);
                                if (valores.length == 2) {
                                    operador = operador.concat(valores[1]).concat("(@)");
                                }
                                if (valores.length > 0) {
                                    if (isCampoSumado) {
                                        if (MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor").startsWith(valores[0])) {
                                            sintaxisCampo.append(construyeMovimValorParametro(valores[0], "", "", "", null, null, "", false));
                                        } else {
                                            sintaxisCampo.append(construyeMovim2Periodos(valores[0], valorCaseWhen));
                                        }
                                    } else {
                                        if (tipoDato.isArray()) {
                                            sintaxisCampo.append(path.concat(".").concat(ruta[ruta.length - 1]));
                                        } else {
                                            sintaxisCampo.append(construyeCamposCaseWhenOuter(path, ruta[ruta.length - 1], valorCaseWhen));
                                        }
                                    }
                                    if (ModoSintaxis.ORDEN == modoSintaxis) {
                                        sintaxisCampo.append(tipoOrdenado);
                                    }
                                    sintaxisCampo.append(",");
                                }

                                if (operador.trim().length() > 0) {
                                    operador = operador.replace("@", sintaxisCampo);
                                    sintaxisCampo.delete(0, sintaxisCampo.length());
                                    sintaxisCampo.append(operador).append(",");
                                }
                            }
                        }
                    }
                }
            }
            operador = "";
        }
        sintaxisCampo.replace(sintaxisCampo.length() - 1, sintaxisCampo.length(), "");
        return sintaxisCampo.toString();
    }

    private boolean evaluaCampoTiene2Movim(String campo) {
        int i;
        String[] campos;
        if (campo.contains("#")) {
            campos = campo.split("#");
            campo = campos[0];
        } else if (campo.contains("|")) {
            campos = campo.split("\\|");
            campo = campos[0];
        }
        TipoNodoConsulta tnc = TipoNodoConsulta.CLASIFICADOR;
        if (propertieFuenteDatos != null) {
            if (propertieFuenteDatos.containsKey(campo)) {
                tnc = getTipoNodoProperty(campo.concat("_TipoNodo"));
                campo = propertieFuenteDatos.getProperty(campo);
                if (campo.contains("|")) {
                    campos = campo.split("\\|");
                    campo = campos[0];
                }
            }
        }
        if (tnc == TipoNodoConsulta.CLASIFICADOR & campo.split("\\.").length > 1) {
            tnc = TipoNodoConsulta.CAMPO;
        }
        for (i = 0; i < camposSumarMov2Meses.length; i++) {
            if (campo.equalsIgnoreCase(camposSumarMov2Meses[i]) & tnc == TipoNodoConsulta.CAMPOESPECIAL) {
                return true;
            } else if (campo.equalsIgnoreCase(camposSumarMov2Meses[i]) & tnc == TipoNodoConsulta.CAMPO) {
                return true;
            }
        }
        return false;
    }

    public String construyeWhereConParametro(String[] camposWhere, Object[] valoresWhere, String[] camposWhereExtras) {
        camposWhere = camposWhere == null ? new String[]{} : camposWhere;
        camposWhereExtras = camposWhereExtras == null ? new String[]{} : camposWhereExtras;
        if (camposWhere.length == 0 & camposWhereExtras.length == 0) {
            return "";
        }
        StringBuilder campos = new StringBuilder("WHERE ");
        String valores[], ruta[];
        String path;
        int cont = 0, pos = 0;
        for (String str : camposWhere) {
            valores = str.split("\\|");
            if (valores.length > 1) {
                path = valores[1];
            } else {
                path = valores[0];
            }
            ruta = path.split("\\.");
            path = path.substring(0, path.length() - ruta[ruta.length - 1].length() - 1);
            path = path.replace(".", "_");
            if (aliasTablaOuter.containsKey(path)) {
                path = aliasTablaOuter.get(path);
                if (valores.length > 1) {
                    if (valores[0].equalsIgnoreCase("OR") | valores[0].equalsIgnoreCase("AND")) {
                        campos.replace(campos.length() - 1, campos.length(), " ".concat(valores[0]).concat(" "));
                    } else {
                        campos.append(" ".concat(valores[0]));
                    }
                }
                campos.append(path).append(".").append(ruta[ruta.length - 1]);
                if (valores.length > 1) {
                    campos.append(" ").append(valores[2]);

                    if (valores[2].equalsIgnoreCase("BETWEEN")) {
                        campos.append(" :parametros").append(String.valueOf(cont));

                        if (valoresWhere[pos].getClass().equals(Object[].class)) {
                            Object[] datos = (Object[]) valoresWhere[pos];
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), datos[0]);
                            cont++;
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), datos[1]);
                        } else if (valoresWhere[pos].getClass().equals(ArrayList.class)) {
                            List datos = (ArrayList) valoresWhere[pos];
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), datos.get(0));
                            cont++;
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), datos.get(1));
                        } else if (valoresWhere[pos] instanceof Object[]) {
                            Object[] datos = (Object[]) valoresWhere[pos];
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), datos[0]);
                            cont++;
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), datos[1]);
                        } else {
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), valoresWhere[pos]);
                            cont++;
                            listParametros.add("parametros".concat(String.valueOf(cont)));
                            parametrosQuery.put("parametros".concat(String.valueOf(cont)), valoresWhere[pos]);
                        }
                        campos.append(" AND ").append(" :parametros").append(String.valueOf(cont));
                    } else if (valores[2].equalsIgnoreCase("IN")) {
                        campos.append(" (:parametros").append(String.valueOf(cont)).append(") ");
                        listParametros.add("parametros".concat(String.valueOf(cont)));
                        parametrosQuery.put("parametros".concat(String.valueOf(cont)), valoresWhere[pos]);
                    } else {
                        campos.append(" :parametros").append(String.valueOf(cont));
                        listParametros.add("parametros".concat(String.valueOf(cont)));
                        parametrosQuery.put("parametros".concat(String.valueOf(cont)), valoresWhere[pos]);
                    }
                } else {
                    listParametros.add("parametros".concat(String.valueOf(cont)));
                    parametrosQuery.put("parametros".concat(String.valueOf(cont)), valoresWhere[pos]);
                    campos.append(" = :parametros").append(String.valueOf(cont));
                }
                if (valores.length > 3) {
                    campos.append(valores[3]).append(" ");
                }
                campos.append("@");
                cont++;
                pos++;
            }
        }
        campos.replace(campos.length() - 1, campos.length(), "");
        campos.append(construyeWhereExtras(camposWhereExtras));
        return campos.toString().replace("@", " AND ");
    }

    public String construyeWhereDatosSinParametros(String[] camposWhere, Object[] valoresWhere, String[] camposWhereExtras) {
        StringBuilder campos = new StringBuilder("");
        try {
            campos.append("WHERE ");
            String valores[], ruta[];
            String path;
            int cont = 0;
            for (String str : camposWhere) {
                valores = str.split("\\|");
                path = valores[0];
                ruta = path.split("\\.");
                path = path.substring(0, path.length() - ruta[ruta.length - 1].length() - 1);
                path = path.replace(".", "_");
                if (aliasTablaOuter.containsKey(path)) {
                    path = aliasTablaOuter.get(path);
                    campos.append(path).append(".").append(ruta[ruta.length - 1]);
                    if (valores.length > 1) {
                        campos.append(" ").append(valores[2]).append(" ").append(castDatosSegunOperadorWhere(valores[1], valores[2], valoresWhere[cont]));
                    } else {
                        campos.append(" = ").append(casteaValorPorTipoDato(valores[0], valoresWhere[cont]));
                    }
                    campos.append("@");
                    cont++;
                }
            }
            campos.replace(campos.length() - 1, campos.length(), "");
            campos.append(construyeWhereExtras(camposWhereExtras));
        } catch (Exception e) {
            System.err.println(msgError.concat("construyeWhereDatosSinParametros()_Error").concat(e.getMessage()));
        }
        return campos.toString().replace("@", " AND ");
    }

    private String castDatosSegunOperadorWhere(String campo, String operador, Object valor) {
        StringBuilder cadena = new StringBuilder();
        try {
            int i;
            List<Object> valores = new ArrayList<Object>();
            if (valor.getClass().equals(Object[].class)) {
                Object[] datos = (Object[]) valor;
                for (i = 0; i < datos.length; i++) {
                    valores.add(datos[i]);
                }
            } else if (valor.getClass().equals(ArrayList.class)) {
                valores.addAll((ArrayList) valor);
            } else {
                valores.add(valor);
            }
            if (operador.equalsIgnoreCase("BEETWEEN")) {
                cadena.append(casteaValorPorTipoDato(campo, valores.get(0))).append(" AND ").append(casteaValorPorTipoDato(campo, valores.get(1)));
            } else if (operador.equalsIgnoreCase("IN")) {
                cadena.append("(");
                for (i = 0; i < valores.size(); i++) {
                    cadena.append(casteaValorPorTipoDato(campo, valores.get(i))).append(",");
                }
                cadena.replace(cadena.length() - 1, cadena.length(), ")");
            } else {
                cadena.append(casteaValorPorTipoDato(campo, valores.get(0)));
            }
        } catch (Exception e) {
            System.err.println(msgError.concat("castDatosSegunOperadorWhere()_Error").concat(e.getMessage()));
        }
        return cadena.toString();
    }

    /*
     * formato wheres especiales OR(opcional)|CAMPO(no
     * opcional)|BETWEEN(operador no opcional)|valor1|AND|valor2 |clave|<|10,
     * AND NOT|clave|IN|10,11,12, etc SOLO CREA CONSULTAS SIN WHERE
     */
    private String construyeWhereExtras(String[] camposWhereExtras) {
        StringBuilder camposWhere = new StringBuilder("");
        try {
            if (camposWhereExtras != null) {
                int i, j;
                String[] cadenas, campos;
                String pathTabla;
                Class tipoDato;
                boolean contieneParentesis = false;
                for (i = 0; i < camposWhereExtras.length; i++) {
                    campos = camposWhereExtras[i].split("\\|");
                    if (campos[0].contains("(") | campos[0].contains(")")) {
                        contieneParentesis = true;
                        camposWhere.append(" ").append(campos[0]);
                        if (campos[0].contains(")")) {
                            contieneParentesis = false;
                        }
                    } else {
                        if (campos[0].trim().length() == 0) {
                            if (!contieneParentesis) {
                                camposWhere.append(" AND ");
                            }
                        } else {
                            camposWhere.append(" ").append(campos[0]).append(" ");
                        }
////////                        if (i == 0 & camposWhereExtras.length > 1) {
////////                            camposWhere.append(" ( ");
////////                        }
                        pathTabla = campos[1].substring(0, campos[1].lastIndexOf("."));
                        if (aliasTablaOuter.containsKey(pathTabla.replace(".", "_"))) {
                            camposWhere.append(aliasTablaOuter.get(pathTabla.replace(".", "_"))).append(".").append(campos[1].substring(campos[1].lastIndexOf(".") + 1)).append(" ");
                        }
                        camposWhere.append(" ").append(campos[2]).append(" ");
                        ///si es entidad comparacion
                        if (campos[3].lastIndexOf(".") > -1) {
                            pathTabla = campos[3].substring(0, campos[3].lastIndexOf("."));
                        }
                        if (aliasTablaOuter.containsKey(pathTabla.replace(".", "_")) & campos[3].lastIndexOf(".") > -1) {
                            camposWhere.append(aliasTablaOuter.get(pathTabla.replace(".", "_"))).append(".").append(campos[3].substring(campos[3].lastIndexOf(".") + 1)).append(" ");
                        } else {
                            tipoDato = buscarTipoDatoCampo(campos[1]);
                            if (tipoDato.isEnum()) {
                                campos[3] = obtenerValoresEnum(tipoDato, campos[3]).toString();
                            }
                            /////camposWhere.append(" ").append(campos[2]).append(" ");
                            if (tipoDato.equals(Date.class) | tipoDato.equals(String.class)) {
                                if (campos[2].equalsIgnoreCase("BETWEEN")) {
                                    camposWhere.append(" ").append(campos[3]).append(" ").append(campos[4]).append(" '").append(campos[5]).append("' ");
                                } else if (campos[2].equalsIgnoreCase("IN")) {
                                    camposWhere.append(" (");
                                    cadenas = campos[3].replace("^", ",").split(",");
                                    for (j = 0; j < cadenas.length; j++) {
                                        camposWhere.append("'").append(cadenas[j]).append("',");
                                    }
                                    camposWhere.delete(camposWhere.length() - 1, camposWhere.length());
                                    camposWhere.append(") ");
                                } else if (campos[2].equalsIgnoreCase("LIKE")) {
                                    camposWhere.append("'%").append(campos[3]).append("%' ");
                                } else {
                                    camposWhere.append("'").append(campos[3]).append("' ");
                                }
                            } else {
                                if (campos[2].equalsIgnoreCase("BETWEEN")) {
                                    camposWhere.append(campos[3]).append(campos[4]).append(" ").append(campos[5]);
                                } else if (campos[2].equalsIgnoreCase("IN")) {
                                    camposWhere.append(" (").append(campos[3].replace("^", ",")).append(") ");
                                } else {
                                    camposWhere.append(campos[3].replace("^", ","));
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println(msgError.concat("construyeWhereExtras()_Error").concat(e.getMessage()));
        }
        return camposWhere.toString();
    }

    /*
     * usado en mostrar nombre completo
     */
    public DatoCampoConsulta constuyeConsultaCampoEspecial(String campo) {
        DatoCampoConsulta campoConsulta = null;
        try {
            String[] valores;
            String campoResultado;
            if (propertieFuenteDatos != null) {
                StringBuilder creaCampo = new StringBuilder();
                TipoNodoConsulta tipoNodoConsulta = getTipoNodoProperty(campo.concat("_TipoNodo"));
                int i;
                if (tipoNodoConsulta == TipoNodoConsulta.CAMPOESPECIAL) {
                    valores = propertieFuenteDatos.getProperty(campo).split("\\|");
                    for (i = 0; i < valores.length; i++) {
                        mapeaTablasCampo(valores);
                    }
                    if (valores.length > 1) {
                        campoResultado = construyeCampoConcatenar(valores, " ");
                    } else {
                        if (propertieFuenteDatos.containsKey(campo.concat("_Datos"))) {
                            String[] datos = propertieFuenteDatos.getProperty(campo.concat("_Datos")).split(",");
                            Class tipoDato = buscarTipoDatoCampo(valores[0]);
                            String valorCaseWhen = valorCasePorTipoDato(tipoDato);
                            String[] tmp, valorDato;
                            List<String> camposCompararExtras = new ArrayList<String>();
                            List<Object> valoresCompararExtras = new ArrayList<Object>();
                            if (datos.length > 0) {
                                for (i = 0; i < datos.length; i++) {
                                    tmp = datos[i].split("\\|");
                                    valorDato = propertieFuenteDatos.getProperty(tmp[0].concat("_Path")).split("\\|");
                                    camposCompararExtras.add(valorDato[0]);
                                    valoresCompararExtras.add(valorDato[1]);
                                }
                            }
                            mapeaTablasCampo(camposCompararExtras.toArray(new String[]{}));
                            campoResultado = construyeCaseWhenCamposCompararExtras(valores[0], valorCaseWhen, camposCompararExtras.toArray(new String[]{}), valoresCompararExtras.toArray());
                        } else {
                            Class tipoDato = buscarTipoDatoCampo(valores[0]);
                            campoResultado = construyeCamposCaseWhenOuter(getAliasTablaCampo(valores[0]), getCampoFinal(valores[0]), valorCasePorTipoDato(tipoDato));
                        }

                    }
                    creaCampo.append(campoResultado);
                    campoConsulta = new DatoCampoConsulta(creaCampo.toString(), tipoNodoConsulta, null);
                    return campoConsulta;
                }
            }
        } catch (Exception e) {
            System.err.println(msgError.concat("constuyeConsultaCampoEspecial()_Error").concat(e.getMessage()));
        }
        return campoConsulta;
    }

    public DatosQuery construyeQueryDatosEspeciales(String[] campos, String[] camposWhere, Object[] valoresWhere, String[] groupBy, String[] orderBy) {
        DatosQuery datosQuery = new DatosQuery();
        StringBuilder consulta = new StringBuilder();
        /////generaListaTablasMapeadas(campos, camposWhere, null, orderBy);
        consulta.append(construyeSelectDatos(campos)).append(" ");
        consulta.append(construyeFromConsulta(LEFTJOIN)).append(" ");
        consulta.append(construyeWhereDatosSinParametros(camposWhere, valoresWhere, null)).append(" ").append(construyeGroupByDatosEspecial(campos)).append(" ");
        consulta.append(construyeGroupBy(groupBy)).append(" ");
        consulta.append(construyeOrderBy(orderBy, "")).append(" ");
        datosQuery.setAliasTablas(aliasTablaOuter);
        datosQuery.setParametrosCampos(parametrosQuery);
        datosQuery.setQuery(consulta.toString());
        datosQuery.setListParametros(listParametros);
        datosQuery.setConParametros(false);
        tablasOuter = null;
        aliasTablaOuter = null;
        parametrosQuery = null;
        listParametros = null;
        return datosQuery;
    }

    /*
     * comparacion agregada en el query principal de mostrado de datos
     */
    private String construyeComparacionCampoEspecial(String valorMostrar, String campoComparar, Object[] valorComparar, EnumTipoDato tipoDato) {
        StringBuilder campoSlit = new StringBuilder();
        String path = campoComparar.substring(0, campoComparar.lastIndexOf("."));
        path = path.replace(".", "_");
        path = aliasTablaOuter.get(path);
        campoSlit.append("CASE WHEN (").append(path).append(" IS NULL) THEN @ ELSE CASE WHEN (");
        campoSlit.append(path).append(".").append(campoComparar.substring(campoComparar.lastIndexOf(".") + 1)).append(" IS NULL) THEN @ ELSE ");
        campoSlit.append("CASE WHEN (");
        int i;
        for (i = 0; i < valorComparar.length; i++) {
            campoSlit.append(path).append(".").append(campoComparar.substring(campoComparar.lastIndexOf(".") + 1)).append(" = ").append(valorComparar[i]);
            if (i < valorComparar.length - 1) {
                campoSlit.append(" OR ");
            }
        }

        campoSlit.append(") THEN ");
        path = valorMostrar.substring(0, valorMostrar.lastIndexOf("."));
        path = path.replace(".", "_");
        path = aliasTablaOuter.get(path);
        campoSlit.append(path).append(".").append(valorMostrar.substring(valorMostrar.lastIndexOf(".") + 1)).append(" ELSE @ END END END");
        if (EnumTipoDato.NUMERICO == tipoDato) {
            return campoSlit.toString().replace("@", "0.0");
        } else {
            return campoSlit.toString().replace("@", "'0.0'");
        }
        ///return campoSlit.toString();
    }

    private String construyeCampoEspecial2Periodos(String valorMostrar, String[] campoComparar, Object[] valorComparar, EnumTipoDato tipoDato, boolean isTotal) {

        boolean isRemoverCampoComparar = false;
        int i, j;
        List<String> respaldo = new ArrayList<String>();
        List<Object> valoresComparar = new ArrayList<Object>();
        List<String> remover = new ArrayList<String>();
        for (i = 0; i < valorComparar.length; i++) {
            if (valorComparar[i].equals("REMOVE")) {
                isRemoverCampoComparar = true;
                remover.add(campoComparar[i]);
            } else {
                respaldo.add(campoComparar[i]);
                valoresComparar.add(valorComparar[i]);
            }
        }
        campoComparar = respaldo.toArray(new String[]{});
        valorComparar = valoresComparar.toArray();
        StringBuilder campoSlit = new StringBuilder("(SELECT SUM(");
        String subAlias = "H";
        String path;
        respaldo.clear();
        for (j = 0; j < camposRaizMovimientos.length; j++) {
            if (!existeAliasTablaCampo(camposRaizMovimientos[j])) {
                respaldo.add(camposRaizMovimientos[j]);
            }
        }

        mapeaTablasCampo(respaldo.toArray(new String[]{}));
//////        if (isRemoverCampoComparar) {
//////            path = valorMostrar.substring(0, valorMostrar.lastIndexOf("."));
//////            path = path.replace(".", "_");
//////            path = aliasTablaOuter.get(path);
//////            campoSlit.append("CASE WHEN (").append(subAlias).append(path).append(" IS NULL) THEN @ ELSE CASE WHEN (");
//////            campoSlit.append(subAlias).append(path).append(".").append(valorMostrar.substring(valorMostrar.lastIndexOf(".") + 1)).append(" IS NULL) THEN @ ELSE ");
//////            campoSlit.append(subAlias).append(path).append(".").append(valorMostrar.substring(valorMostrar.lastIndexOf(".") + 1)).append(" END END) ");
//////        } else {
        String[] variables;
        if (campoComparar.length == 0) {
            variables = obtieneVariablesFormula(valorMostrar);
            for (String var : variables) {
                if (var.split("\\.").length < 2) {
                    campoSlit.append(var);
                } else {
                    path = var.substring(0, var.lastIndexOf("."));
                    path = path.replace(".", "_");
                    path = aliasTablaOuter.get(path);
                    campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(path), var.substring(var.lastIndexOf(".") + 1), "@"));
                }
            }
            campoSlit.append(")");
        } else {
            String[] object;
            campoSlit.append("CASE WHEN (");
            for (j = 0; j < campoComparar.length; j++) {
                object = campoComparar[j].split("\\|");
                path = object[0].substring(0, object[0].lastIndexOf("."));
                path = path.replace(".", "_");
                path = aliasTablaOuter.get(path);
                campoSlit.append(subAlias).append(path).append(" IS NULL ");
                if (j < campoComparar.length - 1) {
                    campoSlit.append(" OR ");
                }
            }
            campoSlit.append(") THEN @ ELSE CASE WHEN (");
            for (j = 0; j < campoComparar.length; j++) {
                object = campoComparar[j].split("\\|");
                path = object[0].substring(0, object[0].lastIndexOf("."));
                path = path.replace(".", "_");
                path = aliasTablaOuter.get(path);
                campoSlit.append(subAlias).append(path).append(".").append(object[0].substring(object[0].lastIndexOf(".") + 1)).append(" IS NULL");
                if (j < campoComparar.length - 1) {
                    campoSlit.append(" OR ");
                }
            }
            campoSlit.append(") THEN @ ELSE CASE WHEN (");
            for (j = 0; j < campoComparar.length; j++) {
                object = campoComparar[j].split("\\|");
                path = object[0].substring(0, object[0].lastIndexOf("."));
                path = path.replace(".", "_");
                path = aliasTablaOuter.get(path);
                campoComparar[j] = object[0];
                campoSlit.append(subAlias).append(path).append(".").append(object[0].substring(object[0].lastIndexOf(".") + 1)).append(" = ").append(valorComparar[j]);
                if (j < campoComparar.length - 1) {
                    if (object.length == 1) {
                        campoSlit.append(" OR ");
                    } else {
                        if (object[1].equalsIgnoreCase("AND")) {
                            campoSlit.append(" AND ");
                        } else {
                            campoSlit.append(" OR ");
                        }
                    }
                }
            }

            campoSlit.append(") THEN ");
            variables = obtieneVariablesFormula(valorMostrar);
            campoSlit.append("(");
            for (String var : variables) {
                if (var.split("\\.").length < 2) {
                    campoSlit.append(var);
                } else {
                    path = var.substring(0, var.lastIndexOf("."));
                    path = path.replace(".", "_");
                    path = aliasTablaOuter.get(path);
                    campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(path), var.substring(var.lastIndexOf(".") + 1), "@"));
                }
            }
            campoSlit.append(")");
            /// campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(path), valorMostrar.substring(valorMostrar.lastIndexOf(".") + 1), "@"));
//////            campoSlit.append(subAlias).append(path).append(".").append(valorMostrar.substring(valorMostrar.lastIndexOf(".") + 1));
            campoSlit.append(" ELSE @ END END END) ");
//////        }
        }
        List<String> camposFrom = new ArrayList<String>(Arrays.asList(camposRaizMovimientos));
        for (j = 0; j < campoComparar.length; j++) {
            if (campoComparar[j].split("\\.").length > 2) {
                camposFrom.add(campoComparar[j]);
            }
        }
        for (String var : variables) {
            if (var.split("\\.").length > 2) {
                camposFrom.add(var);
            }
        }
        if (isRemoverCampoComparar) {
            String[] removerCampo;
            for (j = 0; j < remover.size(); j++) {
                removerCampo = remover.get(j).split(",");
                for (String delete : removerCampo) {
                    camposFrom.remove(delete);
                }
            }
        }
        if (usaFiniquitos) {
            camposFrom.add(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.id"));
            camposFrom.add(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave"));
        }
        if (isTotal) {
            String[] camposWhereRemover = new String[]{MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"), MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"), MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave"), MovNomConcep.class.getSimpleName().concat(".empleado.clave")};
            for (i = 0; i < camposWhereRemover.length; i++) {
                camposFrom.remove(camposWhereRemover[i]);
            }
            if (getCamposFiltroMovimientos() != null) {
                for (i = 0; i < getCamposFiltroMovimientos().length; i++) {
                    camposFrom.add(getCamposFiltroMovimientos()[i]);
                }
            }
        }

        campoSlit.append(construyeFromConsulta(camposFrom.toArray(new String[]{}), LEFTJOIN, subAlias));
        campoSlit.append(" WHERE ");
        String[] ruta;
        camposFrom.clear();
        camposFrom.addAll(Arrays.asList(camposRaizMovimientos));
        if (isTotal) {
            camposFrom.remove(MovNomConcep.class.getSimpleName().concat(".periodosNomina.clave"));
            String campoSelect = MovNomConcep.class.getSimpleName().concat(".periodosNomina.fechaInicial");
            String aliasTabla = getAliasTablaCampo(campoSelect);
            String campo = getCampoFinal(campoSelect);
            campoSlit.append("(").append(subAlias).append(aliasTabla).append(".").append(campo).append(" BETWEEN :ValorFechaInicial AND :ValorFechaFinal OR ");
            campoSelect = MovNomConcep.class.getSimpleName().concat(".periodosNomina.fechaFinal");
            campo = getCampoFinal(campoSelect);
            campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" BETWEEN :ValorFechaInicial AND :ValorFechaFinal) ");
        }
        if (isRemoverCampoComparar) {
            String[] removerCampo;
            for (j = 0; j < remover.size(); j++) {
                removerCampo = remover.get(j).split(",");
                for (String delete : removerCampo) {
                    camposFrom.remove(delete);
                }
            }
        }
        if (camposFrom.size() > 0 & isTotal) {
            campoSlit.append(" AND ");
        }
        if (isTotal) {
            String[] camposWhereRemover = new String[]{MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"), MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"), MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave"), MovNomConcep.class.getSimpleName().concat(".empleado.clave")};
            for (i = 0; i < camposWhereRemover.length; i++) {
                camposFrom.remove(camposWhereRemover[i]);
            }
            if (getCamposFiltroMovimientos() != null) {
                for (i = 0; i < getCamposFiltroMovimientos().length; i++) {
                    camposFrom.add(getCamposFiltroMovimientos()[i]);
                }
            }
        }
        for (i = 0; i < camposFrom.size(); i++) {
            path = camposFrom.get(i).replace(".", "_");
            ruta = path.split("_");
            if (ruta.length > 1) {
                campoSlit.append(subAlias).append(aliasTablaOuter.get(path.substring(0, path.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]).append(" = ").append(aliasTablaOuter.get(path.substring(0, path.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]);
                if (camposFrom.size() - 1 > i) {
                    campoSlit.append(" AND ");
                }
            }
        }
        if (isMov2Periodos) {
            path = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".mes"));
            String campo = getCampoFinal(MovNomConcep.class.getSimpleName().concat(".mes"));
            campoSlit.append(" AND ").append(subAlias).append(path).append(".").append(campo).append(" IN(:mes) ");
        }

        if (usaFiniquitos) {
            path = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave"));
            campoSlit.append(" AND ").append(subAlias).append(path).append(" = ").append(path);
        }
////////        if (!isRemoverCampoComparar) {
////////            path = campoComparar.substring(0, campoComparar.lastIndexOf("."));
////////            path = path.replace(".", "_");
////////            path = aliasTablaOuter.get(path);
////////            campoSlit.append(" AND (");
////////            for (i = 0; i < valorComparar.length; i++) {
////////                campoSlit.append(subAlias).append(path).append(".").append(campoComparar.substring(campoComparar.lastIndexOf(".") + 1)).append(" = ").append(valorComparar[i]);
////////                if (i < valorComparar.length - 1) {
////////                    campoSlit.append(" OR ");
////////                }
////////            }
////////            campoSlit.append(")");
////////        }

        campoSlit.append(") ");

        if (EnumTipoDato.NUMERICO == tipoDato) {
            return campoSlit.toString().replace("@", "0.0");
        } else {
            return campoSlit.toString().replace("@", "'0.0'");
        }
    }

    private String construyeCaseWhenCamposCompararExtras(String campo, String valoreCaseWhen, String[] camposCompararExtras, Object[] valorCompararExtra) {
        StringBuilder campoSlit = new StringBuilder();
        String aliasTabla = getAliasTablaCampo(campo);
        String pathCampo = getCampoFinal(campo);
        camposCompararExtras = camposCompararExtras == null ? new String[]{} : camposCompararExtras;
        valorCompararExtra = valorCompararExtra == null ? new Object[]{} : valorCompararExtra;
        campoSlit.append("CASE WHEN (").append(aliasTabla).append(" IS NULL) THEN ").append(valoreCaseWhen).append(" ELSE ");
        campoSlit.append("CASE WHEN (").append(aliasTabla).append(".").append(pathCampo).append(" IS NULL) THEN ").append(valoreCaseWhen).append(" ELSE ");
        if (camposCompararExtras.length > 0) {
            campoSlit.append("CASE WHEN (");
            int i;
            for (i = 0; i < camposCompararExtras.length; i++) {
                aliasTabla = getAliasTablaCampo(camposCompararExtras[i]);
                pathCampo = getCampoFinal(camposCompararExtras[i]);
                campoSlit.append(aliasTabla).append(".").append(pathCampo).append(" = ").append(valorCompararExtra[i]);
                if (i < camposCompararExtras.length - 1) {
                    campoSlit.append(" AND ");
                }
            }
            aliasTabla = getAliasTablaCampo(campo);
            pathCampo = getCampoFinal(campo);
            campoSlit.append(") THEN ").append(aliasTabla).append(".").append(pathCampo).append(" ELSE ");
            campoSlit.append(valoreCaseWhen).append(" END END END");
        } else {
            campoSlit.append(aliasTabla).append(".").append(pathCampo).append(" END END");
        }
        return campoSlit.toString();
    }

    private String construyeCamposEspecialCaseWhen(String aliasTabla, String campo, String valoreCaseWhen) {
        StringBuilder campoSlit = new StringBuilder();
        campoSlit.append("CASE WHEN (").append(aliasTabla).append(" IS NULL) THEN ").append(valoreCaseWhen).append(" ELSE ");
        campoSlit.append("CASE WHEN (").append(aliasTabla).append(".").append(campo).append(" IS NULL) THEN ");
        campoSlit.append(valoreCaseWhen).append(" ELSE ").append(aliasTabla).append(".").append(campo).append(" END END");
        return campoSlit.toString();
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
    /*
     * pendiente
     */

    private String construyeComparacionCampoEspecialV2(String valorMostrar1, String valorMostrar2, String campoComparar, String campoComparar2, Object[] valorComparar, Object[] valorCompararExtra, EnumTipoDato tipoDato) {
        StringBuilder campoSlit = new StringBuilder();
        int i;
        String valorCase, path = campoComparar2.substring(0, campoComparar2.lastIndexOf("."));
        campoSlit.append("(CASE WHEN (");
        path = path.replace(".", "_");
        path = aliasTablaOuter.get(path);
        for (i = 0; i < valorCompararExtra.length; i++) {
            campoSlit.append(path).append(".").append(campoComparar2.substring(campoComparar2.lastIndexOf(".") + 1)).append(" = ").append(valorCompararExtra[i]);
            if (i < valorCompararExtra.length - 1) {
                campoSlit.append(" OR ");
            }
        }
        campoSlit.append(") THEN ");
        path = valorMostrar2.substring(0, valorMostrar2.lastIndexOf("."));
        path = path.replace(".", "_");
        path = aliasTablaOuter.get(path);
        campoSlit.append(path).append(".").append(valorMostrar2.substring(valorMostrar2.lastIndexOf(".") + 1)).append(" ELSE @ END)");
        valorCase = campoSlit.toString();
        campoSlit.delete(0, campoSlit.length());

        path = campoComparar.substring(0, campoComparar.lastIndexOf("."));
        path = path.replace(".", "_");
        path = aliasTablaOuter.get(path);
        campoSlit.append("CASE WHEN (").append(path).append(" IS NULL) THEN ").append(valorCase).append(" ELSE CASE WHEN (");
        campoSlit.append(path).append(".").append(campoComparar.substring(campoComparar.lastIndexOf(".") + 1)).append(" IS NULL) THEN ");
        campoSlit.append(valorCase);
        campoSlit.append(" ELSE CASE WHEN(");
        path = campoComparar.substring(0, campoComparar.lastIndexOf("."));
        path = path.replace(".", "_");
        path = aliasTablaOuter.get(path);
        campoSlit.append(path).append(".").append(campoComparar.substring(campoComparar.lastIndexOf(".") + 1)).append(" = ").append(valorComparar[0]);
        campoSlit.append(") THEN CASE WHEN(CAST(");
        campoSlit.append(path).append(".").append(campoComparar.substring(campoComparar.lastIndexOf(".") + 1)).append(" AS float) = 0.0) THEN ").append(valorCase);
        campoSlit.append(" ELSE ");
        path = valorMostrar1.substring(0, valorMostrar1.lastIndexOf("."));
        path = path.replace(".", "_");
        path = aliasTablaOuter.get(path);
        campoSlit.append(path).append(".").append(valorMostrar1.substring(valorMostrar1.lastIndexOf(".") + 1)).append(" END ELSE @ END END END");

        if (EnumTipoDato.NUMERICO == tipoDato) {
            return campoSlit.toString().replace("@", "0.0");
        } else {
            return campoSlit.toString().replace("@", "'0.0'");
        }
        ///return campoSlit.toString();
    }

    private String construyeCampoConcatenar(String[] campos, String separadorAlterno) {
        if (campos != null) {
            if (campos.length > 1) {
                StringBuilder campoSlit = new StringBuilder("");
                separadorAlterno = separadorAlterno == null ? "" : separadorAlterno;
                String concatena = "CONCAT(@)";
                String sintaxisNumerica = "CAST(@ as string)", sintaxis = "@";
                String separador = ", '|',", caseWhen, path, campTipo;
                if (separadorAlterno.length() > 0) {
                    separador = ", '" + separadorAlterno + "',";
                }
                String ruta[];
                Class tipoDato;
                EnumTipoDato enumTipoDato;
                TipoNodoConsulta tipoNodoConsulta;
                for (String str : campos) {
                    tipoNodoConsulta = TipoNodoConsulta.CAMPO;
                    if (str.lastIndexOf("#") > -1) {
                        campTipo = str.substring(0, str.lastIndexOf("#"));
                    } else {
                        campTipo = str;
                    }
                    if (propertieFuenteDatos != null) {
                        if (propertieFuenteDatos.containsKey(campTipo)) {
                            tipoNodoConsulta = getTipoNodoProperty(campTipo.concat("_TipoNodo"));
                        }
                    }
                    if (tipoNodoConsulta == TipoNodoConsulta.CAMPOESPECIAL) {
                        if ("Percepcion_Path".equalsIgnoreCase(str) | "Deduccion_Path".equalsIgnoreCase(str) | "ConceptoTipoDato_Path".equalsIgnoreCase(str)
                                | "ConceptoTipoCalculo_Path".equalsIgnoreCase(str) | "ConceptoTipoInformativo_Path".equalsIgnoreCase(str) | "ConceptoDifPercepDeduc_Path".equalsIgnoreCase(str)
                                | "TotalImportePorConcepto_Path".equalsIgnoreCase(str) | "TotalImporteExentoPorConcepto_Path".equalsIgnoreCase(str) | "TotalImporteGravablePorConcepto_Path".equalsIgnoreCase(str)
                                | "TotalCantidadPorConcepto_Path".startsWith(campTipo) | "ValorParametro_Path".startsWith(campTipo) | "ValorParametroOConcepto_Path".startsWith(campTipo)
                                | "TotalImportePorConceptoDato_Path".startsWith(campTipo) | "TotalImporteGravablePorConceptoDato_Path".startsWith(campTipo) | "TotalImporteExentoPorConceptoDato_Path".startsWith(campTipo) | "TotalCantidadPorConceptoDato_Path".startsWith(campTipo)
                                | "TotalBaseISR_Path".startsWith(campTipo) | "TotalBaseISRNormal_Path".startsWith(campTipo) | "TotalBaseISRDirecto_Path".startsWith(campTipo) | "TotalBaseISRAnual_Path".startsWith(campTipo)
                                | "TotalBaseISRGravable_Path".startsWith(campTipo) | "TotalBaseISRGravableNormal_Path".startsWith(campTipo) | "TotalBaseISRGravableDirecto_Path".startsWith(campTipo) | "TotalBaseISRGravableAnual_Path".startsWith(campTipo)
                                | "TotalBaseISRExento_Path".startsWith(campTipo) | "TotalBaseISRExentoNormal_Path".startsWith(campTipo) | "TotalBaseISRExentoDirecto_Path".startsWith(campTipo) | "TotalBaseISRExentoAnual_Path".startsWith(campTipo)
                                | "TotalBaseIMSS_Path".equalsIgnoreCase(campTipo) | "TotalBaseIMSSFija_Path".equalsIgnoreCase(campTipo) | "TotalBaseIMSSVariable_Path".equalsIgnoreCase(campTipo)
                                | "TotalBaseIMSSGravado_Path".startsWith(campTipo) | "TotalBaseIMSSGravadoFija_Path".startsWith(campTipo) | "TotalBaseIMSSGravadoVariable_Path".startsWith(campTipo)
                                | "TotalBaseIMSSExento_Path".startsWith(campTipo) | "TotalBaseIMSSExentoFija_Path".startsWith(campTipo) | "TotalBaseIMSSExentoVariable_Path".startsWith(campTipo)
                                | "TotalBaseInfonavit_Path".startsWith(campTipo) | "TotalBasePTU_Path".startsWith(campTipo) | "TotalBaseImpuestoNomina_Path".startsWith(campTipo) | "TotalBaseDespensa_Path".startsWith(campTipo)
                                | "TotalBaseFondoAhorro_Path".startsWith(campTipo) | "TotalBaseAguinaldo_Path".startsWith(campTipo) | "TotalEmpleadosDepartamento_Path".equalsIgnoreCase(campTipo) | "TotalEmpleadosCentroCosto_Path".equalsIgnoreCase(campTipo)
                                | "TotalEmpleadosPuesto_Path".equalsIgnoreCase(campTipo) | "TotalBaseOtros_Path".startsWith(campTipo)) {
//////                            String[] datos, valorDato = null;
//////                            datos = str.split("#");
//////                            String valorComp = "";
//////                            if (datos.length > 1) {
//////                                valorComp = datos[1];
//////                                str = datos[0];
//////                            }
//////                            String valorMostrar = propertieFuenteDatos.getProperty(str);
//////                            datos = propertieFuenteDatos.getProperty(str.concat("_Datos")).split(",");
//////                            Object[] valorNaturaleza = new Object[datos.length];
//////                            Naturaleza n;
//////                            EnumTipoDato tipo = EnumTipoDato.NUMERICO;
//////                            boolean addMapeoDato;
//////                            int i;
//////                            for (i = 0; i < datos.length; i++) {
//////                                valorDato = propertieFuenteDatos.getProperty(datos[i].concat("_Path")).split("\\|");
//////                                addMapeoDato = true;
//////                                if (valorDato.length > 1) {
//////                                    if (valorDato[1].equalsIgnoreCase("REMOVE")) {
//////                                        addMapeoDato = false;
//////                                    }
//////                                }
//////                                if (addMapeoDato) {
//////                                    mapeaTablasCampo(new String[]{valorMostrar, valorDato[0]});
//////                                }
//////                                if (valorDato.length > 1) {
//////                                    if (!valorDato[1].equalsIgnoreCase("REMOVE")) {
//////                                        n = Naturaleza.getEnum(valorDato[1].trim());
//////                                        valorNaturaleza[i] = n.getNaturaleza();
//////                                    } else {
//////                                        valorNaturaleza[i] = valorDato[1];
//////                                    }
//////                                    tipo = EnumTipoDato.NUMERICO;
//////                                } else if (valorComp.trim().length() > 0) {
//////                                    valorNaturaleza[i] = valorComp;
//////                                    tipo = EnumTipoDato.STRING;
//////                                }
//////                            }
//////                            if ("ValorParametro_Path".startsWith(campTipo) | "ValorParametroOConcepto_Path".startsWith(campTipo)) {
//////                                String[] valoresMostrar = valorMostrar.split("\\|");
//////                                Object[] valoresMostrarExtra = null;
//////                                String campoComparar2 = "";
//////                                if (valoresMostrar.length > 1) {
//////                                    valoresMostrarExtra = new Object[]{Naturaleza.CALCULO.getNaturaleza(), Naturaleza.DATO.getNaturaleza(), Naturaleza.INFORMATIVO.getNaturaleza()};
//////                                    campoComparar2 = MovNomConcep.class.getSimpleName().concat(".concepNomDefi.naturaleza");
//////                                }
//////                                valorMostrar = construyeMovimValorParametro(valoresMostrar[0], valoresMostrar.length > 1 ? valoresMostrar[1] : "", valorDato[0], campoComparar2, valorNaturaleza, valoresMostrarExtra);
//////                            } else {
//////                                valorMostrar = construyeCompaCampoEspecial2Periodos(valorMostrar, valorDato[0], valorNaturaleza, tipo);
//////                            }
//////                            /valorMostrar = construyeComparacionCampoEspecial(valorMostrar, valorDato[0], valorNaturaleza, tipo);
//////                            ///valorMostrar = construyeComparacionCampoEspecial(valorMostrar, valorDato[0], valores, tipo);
//////                            campoSlit.append(sintaxisNumerica.replace("@", valorMostrar)).append(separador);
                        } else {
                            ruta = propertieFuenteDatos.getProperty(str).split("\\|");
                            int i;
                            for (i = 0; i < ruta.length; i++) {
                                mapeaTablasCampo(ruta);
                            }
                            campoSlit.append(sintaxis.replace("@", construyeCampoConcatenar(ruta, " "))).append(separador);
                        }
                    } else {
                        ruta = str.split("\\.");
                        path = str.substring(0, str.length() - ruta[ruta.length - 1].length() - 1);
                        path = path.replace(".", "_");
                        if (aliasTablaOuter.containsKey(path)) {
                            path = aliasTablaOuter.get(path);
                            tipoDato = buscarTipoDatoCampo(str);
                            boolean isCampoSumado = evaluaCampoTiene2Movim(str);
                            enumTipoDato = getEnumSegunTipoDato(tipoDato);
                            if (isCampoSumado) {
                                if (MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor").startsWith(str)) {
                                    caseWhen = construyeMovimValorParametro(str, "", "", "", null, null, "", false);
                                    campoSlit.append(sintaxis.replace("@", caseWhen)).append(separador);
                                } else {
                                    caseWhen = construyeMovim2Periodos(str, valorCasePorTipoDato(tipoDato));
                                    campoSlit.append(sintaxisNumerica.replace("@", caseWhen)).append(separador);
                                }
                            } else {
                                if (enumTipoDato == EnumTipoDato.STRING) {
                                    caseWhen = construyeCamposCaseWhenOuter(path, ruta[ruta.length - 1], "''");
                                    campoSlit.append(sintaxis.replace("@", caseWhen)).append(separador);
                                } else if (enumTipoDato == EnumTipoDato.NUMERICO) {
                                    caseWhen = construyeCamposCaseWhenOuter(path, ruta[ruta.length - 1], "''");
                                    campoSlit.append(sintaxisNumerica.replace("@", caseWhen)).append(separador);
                                } else if (enumTipoDato == EnumTipoDato.BOOLEANO) {
                                    caseWhen = construyeCamposCaseWhenConComparacion(path, ruta[ruta.length - 1], false, "false", "true");
                                    campoSlit.append(sintaxis.replace("@", caseWhen)).append(separador);
                                } else if (enumTipoDato == EnumTipoDato.FECHA) {
                                    caseWhen = construyeCamposConcatenarFecha(path, ruta[ruta.length - 1], '-');
                                    campoSlit.append(sintaxis.replace("@", caseWhen)).append(separador);
                                }
                            }
                        }
                    }
                }
                campoSlit.delete(campoSlit.length() - separador.length(), campoSlit.length());
                return concatena.replace("@", campoSlit);
            }
        }
        return "";
    }

    private String construyeCamposCaseWhenOuter(String aliasTabla, String campo, String valoreCaseWhen) {
        StringBuilder campoSlit = new StringBuilder();
        campoSlit.append("CASE WHEN (").append(aliasTabla).append(" IS NULL) THEN ").append(valoreCaseWhen).append(" ELSE CASE WHEN (");
        campoSlit.append(aliasTabla).append(".").append(campo).append(" IS NULL) THEN ").append(valoreCaseWhen).append(" ELSE ");
        campoSlit.append(aliasTabla).append(".").append(campo).append(" END END");
        return campoSlit.toString();
    }
    ////1

    private String construyeMovim2Periodos(String campoSelect, String valoreCaseWhen) {
        StringBuilder campoSlit = new StringBuilder("(SELECT SUM(");
        String subAlias = "H";

        String ruta[] = campoSelect.split("\\.");
        String tabla, aliasTabla;
        if (campoSelect.lastIndexOf(".") > 0) {
            aliasTabla = campoSelect.substring(0, campoSelect.lastIndexOf("."));
            aliasTabla = aliasTabla.replace(".", "_");
            aliasTabla = aliasTablaOuter.get(aliasTabla);
        } else {
            aliasTabla = aliasTablaOuter.get(campoSelect);
        }
        String campo = ruta[ruta.length - 1];

        campoSlit.append("CASE WHEN (").append(subAlias).append(aliasTabla).append(" IS NULL) THEN ").append(valoreCaseWhen).append(" ELSE CASE WHEN (");
        campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" IS NULL) THEN ").append(valoreCaseWhen).append(" ELSE ");
        campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" END END) "); ///.append("FROM ").append(tabla).append(" ").append(subAlias);
        List<String> camposFrom = new ArrayList<String>(Arrays.asList(camposRaizMovimientos));
        if (ruta.length > 2) {
            camposFrom.add(campoSelect);
        }
        if (usaFiniquitos) {
            camposFrom.add(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.id"));
            camposFrom.add(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave"));
        }
        campoSlit.append(construyeFromConsulta(camposFrom.toArray(new String[]{}), LEFTJOIN, subAlias));
        campoSlit.append(" WHERE ");
        int i;
        for (i = 0; i < camposRaizMovimientos.length; i++) {
            tabla = camposRaizMovimientos[i].replace(".", "_");
            ruta = tabla.split("_");
            if (ruta.length > 1) {
                campoSlit.append(subAlias).append(aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]).append(" = ").append(aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]);
                if (camposRaizMovimientos.length - 1 > i) {
                    campoSlit.append(" AND ");
                }
            }
        }
        if (isMov2Periodos) {
            aliasTabla = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".mes"));
            campo = getCampoFinal(MovNomConcep.class.getSimpleName().concat(".mes"));
            campoSlit.append(" AND ").append(subAlias).append(aliasTabla).append(".").append(campo).append(" IN(:mes) ");
        }
        if (usaFiniquitos) {
            aliasTabla = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave"));
            campoSlit.append(" AND ").append(subAlias).append(aliasTabla).append(" = ").append(aliasTabla);
        }
        campoSlit.append(") ");

        return campoSlit.toString();
    }

    private String contruyeTotalesPlazasEmpleados(String valorMostrar) {
        List<String> camposWhereParametros = new ArrayList<String>();
        camposWhereParametros.add(PlazasPorEmpleadosMov.class.getSimpleName());
        camposWhereParametros.add(valorMostrar);
        String subAlias = "H";
        mapeaTablasCampo(camposWhereParametros.toArray(new String[]{}));
        StringBuilder campoSlit = new StringBuilder("(SELECT COUNT(").append(subAlias).append(getAliasTablaCampo(valorMostrar)).append(") ");
        campoSlit.append(construyeFromConsulta(camposWhereParametros.toArray(new String[]{}), LEFTJOIN, subAlias));
        campoSlit.append(" WHERE ").append(subAlias).append(getAliasTablaCampo(valorMostrar)).append(" = ").append(getAliasTablaCampo(valorMostrar));
        campoSlit.append(" GROUP BY ").append(subAlias).append(getAliasTablaCampo(valorMostrar)).append(")");
        return campoSlit.toString();
    }

    private String construyeMovimValorParametro(String valorMostrar1, String valorMostrar2, String campoComparar, String campoCompararExtra, Object[] valorComparar, Object[] valorCompararExtra, String camposRemover, boolean isTotal) {
        String[] camposWhereParametros = new String[]{MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"), MovNomConcep.class.getSimpleName().concat(".concepNomDefi.clave"), MovNomConcep.class.getSimpleName().concat(".empleado.clave"), MovNomConcep.class.getSimpleName().concat(".razonesSociales.clave"), MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave")/*
         * ,
         * MovNomConcep.class.getSimpleName().concat(".periodosNomina.clave")
         */

        };
        campoComparar = campoComparar == null ? "" : campoComparar;
        campoCompararExtra = campoCompararExtra == null ? "" : campoCompararExtra;
        valorComparar = valorComparar == null ? new Object[]{} : valorComparar;
        valorCompararExtra = valorCompararExtra == null ? new Object[]{} : valorCompararExtra;
        camposRemover = camposRemover == null ? "" : camposRemover;
        List<String> respaldo = new ArrayList<String>();
        List<Object> valoresComparar = new ArrayList<Object>();
        int i;
        for (i = 0; i < valorComparar.length; i++) {
            if (!valorComparar[i].equals("REMOVE")) {
                respaldo.add(campoComparar);
                valoresComparar.add(valorComparar[i]);
            }
        }
        if (respaldo.isEmpty()) {
            campoComparar = "";
            valorComparar = new Object[]{};
        } else {
            valorComparar = valoresComparar.toArray();
        }
        StringBuilder campoSlit = new StringBuilder("(SELECT ");
        ///StringBuilder campoGroup = new StringBuilder(" GROUP BY ");
        String subAlias = "H";
        String campoSelect; /// = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.valor");
        String[] extrasMovParam = new String[]{MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo"), MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro")};
        mapeaTablasCampo(extrasMovParam);
        mapeaTablasCampo(camposWhereParametros);
        String tabla, aliasTabla;
        campoSelect = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo");
        aliasTabla = getAliasTablaCampo(campoSelect);
        String campo = getCampoFinal(campoSelect);
        if (isTotal) {
            campoSlit.append(" SUM(CASE WHEN(").append(subAlias).append(aliasTabla).append(" IS NULL ");
            if (campoCompararExtra.length() > 0 & valorCompararExtra.length > 0) {
                campoSlit.append(" AND (");
                aliasTabla = getAliasTablaCampo(campoCompararExtra);
                campo = getCampoFinal(campoCompararExtra);

                for (i = 0; i < valorCompararExtra.length; i++) {
                    campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" = ").append(valorCompararExtra[i]);
                    if (i < valorCompararExtra.length - 1) {
                        campoSlit.append(" OR ");
                    }
                }
                campoSlit.append(")");
            }
            campoSlit.append(" ) THEN '0.0' ELSE CASE WHEN (");
            campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" = 'INTEGER' AND ");
            campoSelect = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro");
            campo = getCampoFinal(campoSelect);
            campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" = '0' ");
            if (campoComparar.length() > 0 & valorComparar.length > 0) {
                campoSlit.append("AND (");
                aliasTabla = getAliasTablaCampo(campoComparar);
                campo = getCampoFinal(campoComparar);
                for (i = 0; i < valorComparar.length; i++) {
                    campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" = ").append(valorComparar[i]);
                    if (i < valorComparar.length - 1) {
                        campoSlit.append(" OR ");
                    }
                }
                campoSlit.append(")");
            }
            campoSlit.append(") THEN CAST(");
            campoSelect = valorMostrar1;
            aliasTabla = getAliasTablaCampo(campoSelect);
            campo = getCampoFinal(campoSelect);
            campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(aliasTabla), campo, "'0.0'")).append(" as float) ELSE ");
            ////campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" as float) ELSE ");
            if (campoComparar.length() > 0 & valorComparar.length > 0) {
                campoSlit.append(" CASE WHEN (");
                aliasTabla = getAliasTablaCampo(campoComparar);
                campo = getCampoFinal(campoComparar);
                for (i = 0; i < valorComparar.length; i++) {
                    campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" = ").append(valorComparar[i]);
                    if (i < valorComparar.length - 1) {
                        campoSlit.append(" OR ");
                    }
                }
                campoSlit.append(") THEN 1.0 ELSE 0.0 END END END) ");
            }
        } else {
            campoSlit.append(" CASE WHEN(").append(aliasTabla).append(" IS NULL ");
            if (campoCompararExtra.length() > 0 & valorCompararExtra.length > 0) {
                campoSlit.append(" AND (");
                aliasTabla = getAliasTablaCampo(campoCompararExtra);
                campo = getCampoFinal(campoCompararExtra);
                for (i = 0; i < valorCompararExtra.length; i++) {
                    campoSlit.append(aliasTabla).append(".").append(campo).append(" = ").append(valorCompararExtra[i]);
                    if (i < valorCompararExtra.length - 1) {
                        campoSlit.append(" OR ");
                    }
                }
                campoSlit.append(")");
            }
            campoSlit.append(" ) THEN  ");
            if (campoCompararExtra.length() > 0 & valorCompararExtra.length > 0) {
                aliasTabla = getAliasTablaCampo(valorMostrar2);
                campo = getCampoFinal(valorMostrar2);
                campoSlit.append("CAST(SUM(");
                campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(aliasTabla), campo, "0.0")).append(") as string) ");
                //////campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(") as string) ");
                campoSelect = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.tipo");
                aliasTabla = getAliasTablaCampo(campoSelect);
                campo = getCampoFinal(campoSelect);
            } else {
                campoSlit.append("'0.0'");
            }
            campoSlit.append(" ELSE CASE WHEN (");
            campoSlit.append(aliasTabla).append(".").append(campo).append(" = 'INTEGER' AND ");
            campoSelect = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro");
            campo = getCampoFinal(campoSelect);
            campoSlit.append(aliasTabla).append(".").append(campo).append(" = '0' ");
            if (campoComparar.length() > 0 & valorComparar.length > 0) {
                campoSlit.append("AND (");
                aliasTabla = getAliasTablaCampo(campoComparar);
                campo = getCampoFinal(campoComparar);
                ///////campoGroup.append(subAlias).append(aliasTabla).append(".").append(campo);
                for (i = 0; i < valorComparar.length; i++) {
                    campoSlit.append(aliasTabla).append(".").append(campo).append(" = ").append(valorComparar[i]);
                    if (i < valorComparar.length - 1) {
                        campoSlit.append(" OR ");
                    }
                }

                campoSlit.append(")");
            }
            campoSlit.append(") THEN CAST(SUM(CAST(");
            campoSelect = valorMostrar1;
            aliasTabla = getAliasTablaCampo(campoSelect);
            campo = getCampoFinal(campoSelect);
            campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(aliasTabla), campo, "'0.0'")).append(" as float)) as string) ELSE ");
            /////campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" as float)) as string) ELSE ");
            if (campoComparar.length() > 0 & valorComparar.length > 0) {
                campoSlit.append(" CASE WHEN (");
                aliasTabla = getAliasTablaCampo(campoComparar);
                campo = getCampoFinal(campoComparar);
                for (i = 0; i < valorComparar.length; i++) {
                    campoSlit.append(aliasTabla).append(".").append(campo).append(" = ").append(valorComparar[i]);
                    if (i < valorComparar.length - 1) {
                        campoSlit.append(" OR ");
                    }
                }
                campoSlit.append(") THEN ");
                campoSelect = valorMostrar1;
                aliasTabla = getAliasTablaCampo(campoSelect);
                campo = getCampoFinal(campoSelect);
            }
            campoSlit.append(" MAX(");
            campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(aliasTabla), campo, "'0.0'")).append(") ");
            /////campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(") "); ///.append("FROM ").append(tabla).append(" ").append(subAlias);
            if (campoComparar.length() > 0 & valorComparar.length > 0) {
                if (campoCompararExtra.length() > 0 & valorCompararExtra.length > 0) {
                    campoSlit.append(" ELSE CASE WHEN (");
                    aliasTabla = getAliasTablaCampo(campoCompararExtra);
                    campo = getCampoFinal(campoCompararExtra);
                    for (i = 0; i < valorCompararExtra.length; i++) {
                        campoSlit.append(aliasTabla).append(".").append(campo).append(" = ").append(valorCompararExtra[i]);
                        if (i < valorCompararExtra.length - 1) {
                            campoSlit.append(" OR ");
                        }
                    }
                    campoSlit.append(") THEN CAST(SUM(");
                    campoSelect = valorMostrar2;
                    aliasTabla = getAliasTablaCampo(campoSelect);
                    campo = getCampoFinal(campoSelect);
                    campoSlit.append(construyeCamposEspecialCaseWhen(subAlias.concat(aliasTabla), campo, "'0.0'")).append(") as string) ");
                    ////campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(") as string) ");
                    campoSlit.append(" ELSE '0.0' END END ");
                    campoSelect = valorMostrar1;
                } else {
                    campoSlit.append(" ELSE '0.0' END ");
                }
            }
            campoSlit.append(" END END ");
        }
        List<String> camposFrom = new ArrayList<String>(Arrays.asList(camposRaizMovimientos));
        String[] ruta = campoSelect.split("\\.");
        if (ruta.length > 2) {
            camposFrom.add(campoSelect);
        }
        if (usaFiniquitos) {
            camposFrom.add(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.id"));
            camposFrom.add(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave"));
            ///// mapeaTablasCampo(new String[]{MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave")});
        }
        camposFrom.addAll(Arrays.asList(extrasMovParam));

        if (camposRemover.trim().length() > 0) {
            String[] camposDelete = camposRemover.split(",");
            List<String> listCamposWhere = new ArrayList<String>(Arrays.asList(camposWhereParametros));
            for (i = 0; i < camposDelete.length; i++) {
                camposFrom.remove(camposDelete[i]);
                listCamposWhere.remove(camposDelete[i]);
            }
            camposWhereParametros = listCamposWhere.toArray(new String[]{});
        }
        if (isTotal) {
            String[] camposWhereRemover = new String[]{MovNomConcep.class.getSimpleName().concat(".tipoNomina.clave"), MovNomConcep.class.getSimpleName().concat(".tipoCorrida.clave"), MovNomConcep.class.getSimpleName().concat(".centroDeCosto.clave"), MovNomConcep.class.getSimpleName().concat(".empleado.clave")};
            List<String> listCamposWhere = new ArrayList<String>(Arrays.asList(camposWhereParametros));
            for (i = 0; i < camposWhereRemover.length; i++) {
                camposFrom.remove(camposWhereRemover[i]);
                listCamposWhere.remove(camposWhereRemover[i]);
            }

            if (getCamposFiltroMovimientos() != null) {
                for (i = 0; i < getCamposFiltroMovimientos().length; i++) {
                    camposFrom.add(getCamposFiltroMovimientos()[i]);
                    listCamposWhere.add(getCamposFiltroMovimientos()[i]);
                }
            }
            camposWhereParametros = listCamposWhere.toArray(new String[]{});
        }
        campoSlit.append(construyeFromConsulta(camposFrom.toArray(new String[]{}), LEFTJOIN, subAlias));
        campoSlit.append(" WHERE ");
        for (i = 0; i < camposWhereParametros.length; i++) {
            tabla = camposWhereParametros[i].replace(".", "_");
            ruta = tabla.split("_");
            if (ruta.length > 1) {
                campoSlit.append(subAlias).append(aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]).append(" = ").append(aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]);
                campoSlit.append(" AND ");
            }
        }
        campoSelect = MovNomConcep.class.getSimpleName().concat(".periodosNomina.fechaInicial");
        aliasTabla = getAliasTablaCampo(campoSelect);
        campo = getCampoFinal(campoSelect);
        campoSlit.append("(").append(subAlias).append(aliasTabla).append(".").append(campo).append(" BETWEEN :ValorFechaInicial AND :ValorFechaFinal OR ");
        campoSelect = MovNomConcep.class.getSimpleName().concat(".periodosNomina.fechaFinal");
        campo = getCampoFinal(campoSelect);
        campoSlit.append(subAlias).append(aliasTabla).append(".").append(campo).append(" BETWEEN :ValorFechaInicial AND :ValorFechaFinal) ");
        campoSlit.append(" AND ");
        campoSelect = MovNomConcep.class.getSimpleName().concat(".movNomConceParam.paraConcepDeNom.clasificadorParametro");
        aliasTabla = getAliasTablaCampo(campoSelect);
        campoSlit.append(subAlias).append(aliasTabla).append(" = ").append(aliasTabla);
        if (isMov2Periodos) {
            aliasTabla = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".mes"));
            campo = getCampoFinal(MovNomConcep.class.getSimpleName().concat(".mes"));
            campoSlit.append(" AND ").append(subAlias).append(aliasTabla).append(".").append(campo).append(" IN(:mes) ");
        }
        if (usaFiniquitos) {
            aliasTabla = getAliasTablaCampo(MovNomConcep.class.getSimpleName().concat(".finiqLiquidCncNom.finiquitosLiquidacion.clave"));
            campoSlit.append(" AND ").append(subAlias).append(aliasTabla).append(" = ").append(aliasTabla);
        }
        /////campoSlit.append(campoGroup).append(") ");
        campoSlit.append(") ");
        return campoSlit.toString();
    }

    private String getAliasTablaCampo(String campoSelect) {
        String aliasTabla = campoSelect.substring(0, campoSelect.lastIndexOf("."));
        aliasTabla = aliasTabla.replace(".", "_");
        aliasTabla = aliasTablaOuter.get(aliasTabla);
        return aliasTabla;
    }

    private boolean existeAliasTablaCampo(String campoSelect) {
        String aliasTabla = campoSelect;
        if (campoSelect.lastIndexOf(".") > -1) {
            aliasTabla = campoSelect.substring(0, campoSelect.lastIndexOf("."));
        }
        aliasTabla = aliasTabla.replace(".", "_");
        return aliasTablaOuter.containsKey(aliasTabla);
    }

    private String getCampoFinal(String campoSelect) {
        String[] ruta = campoSelect.split("\\.");
        campoSelect = ruta[ruta.length - 1];
        return campoSelect;
    }

    private String construyeFromConsulta(String[] campos, String tipoJoin, String subAlias) {
        tipoJoin = (tipoJoin == null ? LEFTJOIN : tipoJoin.trim().length() == 0 ? LEFTJOIN : tipoJoin);
        StringBuilder campoFrom = new StringBuilder("FROM "), s = new StringBuilder();
        subAlias = subAlias == null ? "" : subAlias;
        String ruta[];
        String tabla;
        int i, j;
        for (i = 0; i < campos.length; i++) {
            if (campos[i].lastIndexOf(".") > 0) {
                tabla = campos[i].substring(0, campos[i].lastIndexOf("."));
            } else {
                tabla = campos[i];
            }
            tabla = tabla.replace(".", "_");
            ruta = tabla.split("_");
            if (ruta.length == 1) {
                if (campoFrom.charAt(campoFrom.length() - 1) == '@') {
                    campoFrom.replace(campoFrom.length() - 1, campoFrom.length(), ",");
                }
                s.delete(0, s.length()).append(ruta[ruta.length - 1]).append(" ").append(subAlias).append(aliasTablaOuter.get(ruta[ruta.length - 1]));
                if (!campoFrom.toString().contains(s.toString())) {
                    campoFrom.append(ruta[ruta.length - 1]).append(" ").append(subAlias).append(aliasTablaOuter.get(ruta[ruta.length - 1]));
                } else if (campoFrom.charAt(campoFrom.length() - 1) == ',') {
                    campoFrom.replace(campoFrom.length() - 1, campoFrom.length(), "");
                }
            } else {
                if (ruta.length > 2) {
                    tabla = ruta[0];
                    for (j = 1; j < ruta.length; j++) {
                        s.delete(0, s.length()).append(subAlias).append(aliasTablaOuter.get(tabla)).append(".").append(ruta[j]).append(" ").append(subAlias).append(aliasTablaOuter.get(tabla.concat("_").concat(ruta[j])));
                        if (!campoFrom.toString().contains(s.toString())) {
                            campoFrom.append(subAlias).append(aliasTablaOuter.get(tabla)).append(".").append(ruta[j]).append(" ").append(subAlias).append(aliasTablaOuter.get(tabla.concat("_").concat(ruta[j]))).append("@");
                        } else if (campoFrom.charAt(campoFrom.length() - 1) == '@' & !campoFrom.toString().contains(s.toString())) {
                            campoFrom.replace(campoFrom.length() - 1, campoFrom.length(), "");
                        }
                        tabla = tabla.concat("_").concat(ruta[j]);
                    }
                    if (campoFrom.charAt(campoFrom.length() - 1) == '@') {
                        campoFrom.replace(campoFrom.length() - 1, campoFrom.length(), "");
                    }
                } else {
                    s.delete(0, s.length()).append(subAlias).append(aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]).append(" ").append(subAlias).append(aliasTablaOuter.get(tabla));
                    if (!campoFrom.toString().contains(s.toString())) {
                        campoFrom.append(subAlias).append(aliasTablaOuter.get(tabla.substring(0, tabla.length() - ruta[ruta.length - 1].length() - 1))).append(".").append(ruta[ruta.length - 1]).append(" ").append(subAlias).append(aliasTablaOuter.get(tabla));
                    } else if (campoFrom.charAt(campoFrom.length() - 1) == '@') {
                        campoFrom.replace(campoFrom.length() - 1, campoFrom.length(), "");
                    }
                }
            }
            campoFrom.append("@");
        }
        campoFrom.replace(campoFrom.length() - 1, campoFrom.length(), "");
        return campoFrom.toString().replace("@", " ".concat(tipoJoin).concat(" "));
    }

    private String construyeCamposCaseWhenConComparacion(String aliasTabla, String campo, Object valorComparacion, String valorThen, String valorElse) {
        StringBuilder campoSlit = new StringBuilder();
        valorThen = valorThen == null ? "''" : valorThen;
        valorElse = valorElse == null ? "''" : valorElse;
        campoSlit.append("CASE WHEN (").append(aliasTabla).append(" IS NULL) THEN '").append(valorThen).append("' ELSE CASE WHEN (");
        campoSlit.append(aliasTabla).append(".").append(campo).append(" IS NULL) THEN '").append(valorThen).append("' ELSE CASE WHEN (");
        campoSlit.append(aliasTabla).append(".").append(campo).append(" = ").append(valorComparacion).append(") THEN '").append(valorThen);
        campoSlit.append("' ELSE '").append(valorElse).append("' END END END");
        return campoSlit.toString();
    }

    private String construyeCamposConcatenarFecha(String aliasTabla, String campo, char separadorFecha) {
        StringBuilder campoSlit = new StringBuilder();
        campo = aliasTabla.concat(".").concat(campo);
        campoSlit.append("CASE WHEN (").append(aliasTabla).append(" IS NULL) THEN '' ELSE CASE WHEN (");
        campoSlit.append(campo).append(" IS NULL) THEN '' ELSE CONCAT(TRIM(STR(DAY(").append(campo).append("))),'").append(separadorFecha).append("',");
        campoSlit.append("TRIM(STR(MONTH(").append(campo).append("))),'").append(separadorFecha).append("',TRIM(STR(YEAR(").append(campo).append(")))) END END");
        return campoSlit.toString();
    }

    public void mapeaTablasCampo(String[] campos) {
        generaListaTablasMapeadas(campos, null, null, null);
    }

    private String listaCampoConPropiedad(String[] campos) {
        StringBuilder listCampos = new StringBuilder();
        if (campos != null) {
            int i;
            boolean tieneProperty = false;
            String[] varFormulas;
            for (i = 0; i < campos.length; i++) {
                if (propertieFuenteDatos != null) {
                    if (propertieFuenteDatos.containsKey(campos[i])) {
                        Object valores = propertieFuenteDatos.get(campos[i]);
                        valores = valores == null ? "" : valores;
                        if (valores.toString().trim().length() > 0) {
                            varFormulas = obtieneVariablesFormula(valores.toString());
                            for (String var : varFormulas) {
                                if (var.split("\\.").length > 2) {
                                    listCampos.append(listaCampoConPropiedad(var.split("\\|"))).append(",");
                                }
                            }
                        }
                        tieneProperty = true;
                    } else {
                        tieneProperty = false;
                    }
                }
                if (!campos[i].startsWith("@") & !tieneProperty) {
                    if (tipoDatoEsPathBD(campos[i])) {
                        listCampos.append(campos[i]).append(",");
                    }
                }
            }
        }
        if (listCampos.length() > 0) {
            listCampos.delete(listCampos.length() - 1, listCampos.length());
        }
        return listCampos.toString();
    }

    public void generaListaTablasMapeadas(String[] campos, String[] camposWhere, String[] camposWhereExtras, String[] orderBy) {
        try {
            List<String> mapeoTablas = new ArrayList<String>();
            mapeoTablas.addAll(Arrays.asList(campos == null ? new String[]{} : campos));
            mapeoTablas.addAll(Arrays.asList(camposWhere == null ? new String[]{} : camposWhere));
            mapeoTablas.addAll(Arrays.asList(camposWhereExtras == null ? new String[]{} : camposWhereExtras));
            mapeoTablas.addAll(Arrays.asList(orderBy == null ? new String[]{} : orderBy));
            StringBuilder rutaTabla = new StringBuilder("");
            int i, j, k, numAlias = contTablasOuter;
            boolean existeTabla = false;
            String ruta[];
            List<String> mapeoFiltrado = new ArrayList<String>();

            for (i = 0; i < mapeoTablas.size(); i++) {
                ruta = mapeoTablas.get(i).split("\\|");
                ruta = listaCampoConPropiedad(ruta).split(",");
                mapeoFiltrado.addAll(Arrays.asList(ruta));
            }
            int pos = 1;
            for (j = 0; j < mapeoFiltrado.size(); j++) {
                ruta = mapeoFiltrado.get(j).split("\\.");
                if (!ruta[0].startsWith("@")) {
                    if (ruta.length == 1) {
                        Class tipoDato = null;
                        if (ruta[0].trim().length() > 0) {
                            buscarTipoDatoCampo(ruta[0]);
                        }
                        if (tipoDato != null) {
                            if (tipoDato.getPackage().getName().equalsIgnoreCase("com.mef.erp.modelo.entidad")) {
                                pos = 0;
                            } else {
                                pos = 1;
                            }
                        }
                    } else {
                        pos = 1;
                    }
                    for (i = 0; i < ruta.length - pos; i++) {
                        rutaTabla.append(ruta[i]);

                        for (k = 0; k < tablasOuter.size(); k++) {
                            if (rutaTabla.toString().toUpperCase().equalsIgnoreCase(tablasOuter.get(k).toUpperCase())) {
                                existeTabla = true;
                                break;
                            }
                        }
                        if (!existeTabla) {
                            aliasTablaOuter.put(rutaTabla.toString(), "x".concat(String.valueOf(numAlias)));
                            numAlias++;
                            tablasOuter.add(rutaTabla.toString());
                        }
                        existeTabla = false;
                        rutaTabla.append("_");
                    }

                    rutaTabla.delete(0, rutaTabla.length());
                }
            }
            contTablasOuter = numAlias;
        } catch (Exception e) {
            System.err.println(msgError.concat("generaListaTablasMapeadas()_Error").concat(e.getMessage()));
        }
    }

    private String casteaValorPorTipoDato(String camposWhere, Object valor) {
        try {
            String resultado;
            Class tipoDato = buscarTipoDatoCampo(camposWhere);
            if (tipoDato.equals(Date.class)) {
                SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                resultado = formato.format((Date) valor);
                return resultado.concat("'").concat(resultado).concat("'");
            } else if (tipoDato.equals(String.class)) {
                return "'".concat(valor.toString()).concat("'");
            } else {
                return valor.toString();
            }
        } catch (Exception e) {
            System.err.println(msgError.concat("casteaValorPorTipoDato()_Error").concat(e.getMessage()));
        }
        return "";
    }

    public Class buscarTipoDatoCampo(String pathCampo) {
        Class tipoDato = null;
        try {
            String[] path = pathCampo.split("\\.");
            if (pathCampo.toUpperCase().startsWith("CFDI")) {
                tipoDato = Class.forName("com.mef.erp.modelo.entidad.cfdi.".concat(path[0]));
            } else {
                tipoDato = Class.forName("com.mef.erp.modelo.entidad.".concat(path[0]));
            }

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
            System.err.println(msgError.concat("buscarTipoDatoCampo()_Error").concat(ex.getMessage()));
        } catch (NoSuchFieldException ex) {
            System.err.println(msgError.concat("buscarTipoDatoCampo()_Error").concat(ex.getMessage()));
        } catch (SecurityException ex) {
            System.err.println(msgError.concat("buscarTipoDatoCampo()_Error").concat(ex.getMessage()));
        }
        return tipoDato;
    }

    private boolean tipoDatoEsPathBD(String pathCampo) {
        Class tipoDato;
        try {
            String[] path = pathCampo.split("\\.");
            if (pathCampo.toUpperCase().startsWith("CFDI")) {
                tipoDato = Class.forName("com.mef.erp.modelo.entidad.cfdi.".concat(path[0]));
            } else {
                tipoDato = Class.forName("com.mef.erp.modelo.entidad.".concat(path[0]));
            }
            //////tipoDato = Class.forName("com.mef.erp.modelo.entidad.".concat(path[0]));
            if (tipoDato == null) {
                return false;
            } else {
                return true;
            }
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SecurityException ex) {
            return false;
        }
    }

    private boolean isEntidadDato(String pathCampo) {
        Class tipoDato;
        try {
            String[] path = pathCampo.split("\\.");
            tipoDato = Class.forName("com.mef.erp.modelo.entidad.".concat(path[0]));
            if (tipoDato == null) {
                return false;
            } else {
                return true;
            }
        } catch (ClassNotFoundException ex) {
            return false;
        } catch (SecurityException ex) {
            return false;
        }
    }

    private Class tipoDatoDeLista(Field method) {
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
            System.err.println(msgError.concat("tipoDatoDeLista()_Error").concat(e.getMessage()));
        }
        return Class.class;
    }

    private String valorCasePorTipoDato(Class tipoDato) {
        try {
            if (tipoDato.isPrimitive()) {
                if (tipoDato.equals(int.class) | tipoDato.equals(long.class) | tipoDato.equals(byte.class)
                        | tipoDato.equals(short.class) | tipoDato.equals(boolean.class)) {
                    return "0";
                } else if (tipoDato.equals(float.class) | tipoDato.equals(double.class)) {
                    return "0.0";
                }
            } else {
                if (tipoDato.equals(Integer.class) | tipoDato.equals(Long.class) | tipoDato.equals(Byte.class)
                        | tipoDato.equals(Short.class) | tipoDato.equals(Boolean.class)) {
                    return "0";
                } else if (tipoDato.equals(Float.class) | tipoDato.equals(Double.class)) {
                    return "0.0";
                } else if (tipoDato.equals(Date.class)) {
                    return "cast('1900-01-01' as date)";
                }
            }
        } catch (Exception e) {
            System.err.println(msgError.concat("valorCasePorTipoDato()_Error").concat(e.getMessage()));
        }
        return "''";
    }

    enum EnumTipoDato {

        STRING, NUMERICO, BOOLEANO, FECHA, OTRO;
    }

    private EnumTipoDato getEnumSegunTipoDato(Class tipoDato) {
        try {
            if (tipoDato.isPrimitive()) {
                if (tipoDato.equals(int.class) | tipoDato.equals(long.class) | tipoDato.equals(byte.class)
                        | tipoDato.equals(short.class) | tipoDato.equals(float.class) | tipoDato.equals(double.class)) {
                    return EnumTipoDato.NUMERICO;
                } else if (tipoDato.equals(boolean.class)) {
                    return EnumTipoDato.BOOLEANO;
                }
            } else {
                if (tipoDato.equals(Integer.class) | tipoDato.equals(Long.class) | tipoDato.equals(Byte.class)
                        | tipoDato.equals(Short.class) | tipoDato.equals(Float.class) | tipoDato.equals(Double.class)) {
                    return EnumTipoDato.NUMERICO;
                } else if (tipoDato.equals(Boolean.class)) {
                    return EnumTipoDato.BOOLEANO;
                } else if (tipoDato.equals(Date.class)) {
                    return EnumTipoDato.FECHA;
                } else {
                    return EnumTipoDato.STRING;
                }
            }
        } catch (Exception e) {
            System.err.println(msgError.concat("valorCasePorTipoDato()_Error").concat(e.getMessage()));
        }
        return EnumTipoDato.STRING;
    }

    public TipoNodoConsulta getTipoNodoProperty(String valorPropiedad) {
        if (propertieFuenteDatos == null) {
            return null;
        }
        if (propertieFuenteDatos.containsKey(valorPropiedad)) {
            return TipoNodoConsulta.getEnum(propertieFuenteDatos.getProperty(valorPropiedad));
        }
        return TipoNodoConsulta.CAMPO;
    }

    public String getFuenteDatos() {
        return fuenteDatos;
    }

    public void setFuenteDatos(String fuenteDatos) {
        this.fuenteDatos = fuenteDatos;
    }

    public String[] getValoresDatosEspeciales() {
        return valoresDatosEspeciales;
    }

    public void setValoresDatosEspeciales(String[] valoresDatosEspeciales) {
        this.valoresDatosEspeciales = valoresDatosEspeciales;
    }

    public String[] getCamposFiltroMovimientos() {
        return camposFiltroMovimientos;
    }

    public void setCamposFiltroMovimientos(String[] camposFiltroMovimientos) {
        this.camposFiltroMovimientos = camposFiltroMovimientos;
    }

    public boolean isUsaFiniquitos() {
        return usaFiniquitos;
    }

    public void setUsaFiniquitos(boolean usaFiniquitos) {
        this.usaFiniquitos = usaFiniquitos;
    }

    private Object obtenerValoresEnum(Class clase, String valorBuscar) {

        Object valor = null;
        Enum<?> theOneAndOnly = null;
        try {
            Class<? extends Enum> enumType = clase;
            theOneAndOnly = Enum.valueOf(enumType, valorBuscar);

            Method getPropiedad = theOneAndOnly.getClass().getDeclaredMethod("get".concat(clase.getSimpleName()));
            valor = (Object) getPropiedad.invoke(theOneAndOnly);

            System.out.println(theOneAndOnly.name() + " valor: " + valor);
        } catch (NoSuchMethodException e) {
        } catch (SecurityException e) {
        } catch (IllegalAccessException e) {
        } catch (IllegalArgumentException e) {
        } catch (InvocationTargetException e) {
        }
        if (valor == null & theOneAndOnly == null) {
            return valorBuscar;
        } else if (valor == null) {
            valor = theOneAndOnly;
        }
        return valor;
    }
}
