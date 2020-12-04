/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mef.erp.scriptlet;

import static java.lang.String.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import net.sf.jasperreports.engine.JRDefaultScriptlet;

/**
 *
 * @author Ernesto
 */
public class ScriptReciboNominaCFDI extends JRDefaultScriptlet {

    private int flag;
    public int numero;
    public String importe_parcial;
    public String num;
    public String num_letra;
    public String num_letras;
    public String num_letram;
    public String num_letradm;
    public String num_letracm;
    public String num_letramm;
    public String num_letradmm;

    public ScriptReciboNominaCFDI() {
        this.numero = 0;
        this.flag = 0;
    }

    public ScriptReciboNominaCFDI(int n) {
        this.numero = n;
        this.flag = 0;
    }

    private String unidad(int numero) {
        switch (numero) {
            case 9:
                this.num = "nueve";
                break;
            case 8:
                this.num = "ocho";
                break;
            case 7:
                this.num = "siete";
                break;
            case 6:
                this.num = "seis";
                break;
            case 5:
                this.num = "cinco";
                break;
            case 4:
                this.num = "cuatro";
                break;
            case 3:
                this.num = "tres";
                break;
            case 2:
                this.num = "dos";
                break;
            case 1:
                if (this.flag == 0) {
                    this.num = "uno";
                    return this.num;
                }
                this.num = "un";
                break;
            case 0:
                this.num = "";
        }

        return this.num;
    }

    private String decena(int numero) {
        if ((numero >= 90) && (numero <= 99)) {
            this.num_letra = "noventa ";
            if (numero > 90) {
                this.num_letra = this.num_letra.concat("y ").concat(unidad(numero - 90));
            }
        } else if ((numero >= 80) && (numero <= 89)) {
            this.num_letra = "ochenta ";
            if (numero > 80) {
                this.num_letra = this.num_letra.concat("y ").concat(unidad(numero - 80));
            }
        } else if ((numero >= 70) && (numero <= 79)) {
            this.num_letra = "setenta ";
            if (numero > 70) {
                this.num_letra = this.num_letra.concat("y ").concat(unidad(numero - 70));
            }
        } else if ((numero >= 60) && (numero <= 69)) {
            this.num_letra = "sesenta ";
            if (numero > 60) {
                this.num_letra = this.num_letra.concat("y ").concat(unidad(numero - 60));
            }
        } else if ((numero >= 50) && (numero <= 59)) {
            this.num_letra = "cincuenta ";
            if (numero > 50) {
                this.num_letra = this.num_letra.concat("y ").concat(unidad(numero - 50));
            }
        } else if ((numero >= 40) && (numero <= 49)) {
            this.num_letra = "cuarenta ";
            if (numero > 40) {
                this.num_letra = this.num_letra.concat("y ").concat(unidad(numero - 40));
            }
        } else if ((numero >= 30) && (numero <= 39)) {
            this.num_letra = "treinta ";
            if (numero > 30) {
                this.num_letra = this.num_letra.concat("y ").concat(unidad(numero - 30));
            }
        } else if ((numero >= 20) && (numero <= 29)) {
            if (numero == 20) {
                this.num_letra = "veinte ";
            } else {
                this.num_letra = "veinti".concat(unidad(numero - 20));
            }
        } else {
            if ((numero >= 10) && (numero <= 19));
            switch (numero) {
                case 10:
                    this.num_letra = "diez ";
                    break;
                case 11:
                    this.num_letra = "once ";
                    break;
                case 12:
                    this.num_letra = "doce ";
                    break;
                case 13:
                    this.num_letra = "trece ";
                    break;
                case 14:
                    this.num_letra = "catorce ";
                    break;
                case 15:
                    this.num_letra = "quince ";
                    break;
                case 16:
                    this.num_letra = "dieciseis ";
                    break;
                case 17:
                    this.num_letra = "diecisiete ";
                    break;
                case 18:
                    this.num_letra = "dieciocho ";
                    break;
                case 19:
                    this.num_letra = "diecinueve ";
                    break;
                default:
                    this.num_letra = unidad(numero);
            }
        }
        label571:
        return this.num_letra;
    }

    private String centena(int numero) {
        if (numero >= 100) {
            if ((numero >= 900) && (numero <= 999)) {
                this.num_letra = "novecientos ";
                if (numero > 900) {
                    this.num_letra = this.num_letra.concat(decena(numero - 900));
                }
            } else if ((numero >= 800) && (numero <= 899)) {
                this.num_letra = "ochocientos ";
                if (numero > 800) {
                    this.num_letra = this.num_letra.concat(decena(numero - 800));
                }
            } else if ((numero >= 700) && (numero <= 799)) {
                this.num_letra = "setecientos ";
                if (numero > 700) {
                    this.num_letra = this.num_letra.concat(decena(numero - 700));
                }
            } else if ((numero >= 600) && (numero <= 699)) {
                this.num_letra = "seiscientos ";
                if (numero > 600) {
                    this.num_letra = this.num_letra.concat(decena(numero - 600));
                }
            } else if ((numero >= 500) && (numero <= 599)) {
                this.num_letra = "quinientos ";
                if (numero > 500) {
                    this.num_letra = this.num_letra.concat(decena(numero - 500));
                }
            } else if ((numero >= 400) && (numero <= 499)) {
                this.num_letra = "cuatrocientos ";
                if (numero > 400) {
                    this.num_letra = this.num_letra.concat(decena(numero - 400));
                }
            } else if ((numero >= 300) && (numero <= 399)) {
                this.num_letra = "trescientos ";
                if (numero > 300) {
                    this.num_letra = this.num_letra.concat(decena(numero - 300));
                }
            } else if ((numero >= 200) && (numero <= 299)) {
                this.num_letra = "doscientos ";
                if (numero > 200) {
                    this.num_letra = this.num_letra.concat(decena(numero - 200));
                }
            } else if ((numero >= 100) && (numero <= 199)) {
                if (numero == 100) {
                    this.num_letra = "cien ";
                } else {
                    this.num_letra = "ciento ".concat(decena(numero - 100));
                }
            }
        } else {
            this.num_letra = decena(numero);
        }
        return this.num_letra;
    }

    private String miles(int numero) {
        if ((numero >= 1000) && (numero < 2000)) {
            this.num_letram = "mil ".concat(centena(numero % 1000));
        }
        if ((numero >= 2000) && (numero < 10000)) {
            this.flag = 1;
            this.num_letram = unidad(numero / 1000).concat(" mil ").concat(centena(numero % 1000));
        }
        if (numero < 1000) {
            this.num_letram = centena(numero);
        }
        return this.num_letram;
    }

    private String decmiles(int numero) {
        if (numero == 10000) {
            this.num_letradm = "diez mil";
        }
        if ((numero > 10000) && (numero < 20000)) {
            this.flag = 1;
            this.num_letradm = decena(numero / 1000).concat("mil ").concat(centena(numero % 1000));
        }
        if ((numero >= 20000) && (numero < 100000)) {
            this.flag = 1;
            this.num_letradm = decena(numero / 1000).concat(" mil ").concat(miles(numero % 1000));
        }

        if (numero < 10000) {
            this.num_letradm = miles(numero);
        }
        return this.num_letradm;
    }

    private String cienmiles(int numero) {
        if (numero == 100000) {
            this.num_letracm = "cien mil";
        }
        if ((numero >= 100000) && (numero < 1000000)) {
            this.flag = 1;
            this.num_letracm = centena(numero / 1000).concat(" mil ").concat(centena(numero % 1000));
        }
        if (numero < 100000) {
            this.num_letracm = decmiles(numero);
        }
        return this.num_letracm;
    }

    private String millon(int numero) {
        if ((numero >= 1000000) && (numero < 2000000)) {
            this.flag = 1;
            this.num_letramm = "Un millon ".concat(cienmiles(numero % 1000000));
        }
        if ((numero >= 2000000) && (numero < 10000000)) {
            this.flag = 1;
            this.num_letramm = unidad(numero / 1000000).concat(" millones ").concat(cienmiles(numero % 1000000));
        }
        if (numero < 1000000) {
            this.num_letramm = cienmiles(numero);
        }
        return this.num_letramm;
    }

    private String decmillon(int numero) {
        if (numero == 10000000) {
            this.num_letradmm = "diez millones";
        }
        if ((numero > 10000000) && (numero < 20000000)) {
            this.flag = 1;
            this.num_letradmm = decena(numero / 1000000).concat("millones ").concat(cienmiles(numero % 1000000));
        }
        if ((numero >= 20000000) && (numero < 100000000)) {
            this.flag = 1;
            this.num_letradmm = decena(numero / 1000000).concat(" milllones ").concat(millon(numero % 1000000));
        }

        if (numero < 10000000) {
            this.num_letradmm = millon(numero);
        }
        return this.num_letradmm;
    }

    public String convertirLetras(int numero) {
        this.num_letras = decmillon(numero);
        return this.num_letras;
    }

    public String SimpleDateFormat(Date fecha) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        String DateToStr = format.format(fecha);
        ////System.out.println(DateToStr);
//        try {
//            Date strToDate = format.parse(DateToStr);
//            System.out.println(strToDate);
//        } catch (ParseException e) {
//
//            e.printStackTrace();
//
//        }
        return DateToStr;
    }
}
