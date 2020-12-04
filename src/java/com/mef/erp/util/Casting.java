package com.mef.erp.util;

import com.mef.erp.modelo.entidad.ModoBaja;
import com.mef.erp.modelo.entidad.TipoBaja;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Victor Lopez
 */
public class Casting {

    private Integer vInt;
    private Long vLong;
    private Double vDouble;
    private Boolean vBoolean;
    private Character vCharacter;
    private Byte vByte;
    private Short vShort;
    private Float vFloat;
    private String vString;
    private Date vDate;
    private List vList;
    private ModoBaja modoBaja;
    private TipoBaja tipoBaja;

    public Integer getInteger() {
        return vInt;
    }

    public void setInteger(String value) {
        vInt = Integer.parseInt(value);
    }

    public Long getLong() {
        return vLong;
    }

    public void setLong(String value) {
        vLong = Long.parseLong(value);
    }

    public Double getDouble() {
        return vDouble;
    }

    public void setDouble(String value) {
        vDouble = Double.parseDouble(value);
    }

    public Boolean getBoolean() {
        return vBoolean;
    }

    public void setBoolean(String value) {
        Integer valueBoolean = -1;
        try {
            valueBoolean = Integer.parseInt(value);
        } catch (Exception ex) {
           // System.out.println("ERROR Casting setBoolean" + ex.getMessage());
        }
        if (valueBoolean == -1) {
            vBoolean = Boolean.parseBoolean(value);
        } else {
            vBoolean = valueBoolean == 1 ? true : false;
        }
    }

    public Character getCharacter() {
        return vCharacter;
    }

    public void setCharacter(String value) {
        vCharacter = value.charAt(0);
    }

    public Byte getByte() {
        return vByte;
    }

    public void setByte(String value) {
        vByte = Byte.parseByte(value);
    }

    public Short getShort() {
        return vShort;
    }

    public void setShort(String value) {
        vShort = Short.parseShort(value);
    }

    public Float getFloat() {
        return vFloat;
    }

    public void setFloat(String value) {
        vFloat = Float.parseFloat(value);
    }

    public String getString() {
        return vString;
    }

    public void setString(String value) {
        vString = value;
    }

    public Date getDate() {
        return vDate;
    }

    public void setDate(String value) {
        vDate = Date.valueOf(value);
    }

    public List getList() {
        return vList;
    }

    public void setList(String value) {
        if (value.equals("null") | value.equals("[]")) {
            vList = new ArrayList() {
            };
        }
    }

    public ModoBaja getModoBaja() {
        return modoBaja;
    }

//////    public void setModoBaja(ModoBaja modoBaja) {
//////        this.modoBaja = modoBaja;
//////    }

    public void setModoBaja(String modoBaja) {
        this.modoBaja = ModoBaja.getEnumString(modoBaja);
    }

    public TipoBaja getTipoBaja() {
        return tipoBaja;
    }

//////    public void setTipoBaja(TipoBaja tipoBaja) {
//////        this.tipoBaja = tipoBaja;
//////    }

    public void setTipoBaja(String modoBaja) {
        this.tipoBaja = TipoBaja.getEnumString(modoBaja);
    }
}
