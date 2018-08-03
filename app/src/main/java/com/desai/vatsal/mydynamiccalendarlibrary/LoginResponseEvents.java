package com.desai.vatsal.mydynamiccalendarlibrary;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LoginResponseEvents {
    public final ArrayList<WOListEvent> RetInfo;
    @SerializedName("Code")
    @Expose
    public  Integer Code;
    @SerializedName("Info")
    @Expose
    public String Info;

    public LoginResponseEvents(ArrayList<WOListEvent> retInfo, int code, String info) {
        RetInfo = retInfo;
        this.Code = code;
        this.Info = info;
    }

   /* @SerializedName("RowsAffected")
    @Expose
    private Integer rowsAffected;
    @SerializedName("Scalar")
    @Expose
    private String scalar;
    @SerializedName("Identity")
    @Expose
    private Integer identity;*/
   /* @SerializedName("RetInfo")
    @Expose
    private String retInfo;*/

   public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        this.Info= info;
    }


    public Integer getCode() {
        return Code;
    }

    public void setCode(Integer code) {
        this.Code= code;
    }

 /*   public Integer getRowsAffected() {
        return rowsAffected;
    }

    public void setRowsAffected(Integer rowsAffected) {
        this.rowsAffected = rowsAffected;
    }

    public String getScalar() {
        return scalar;
    }

    public void setScalar(String scalar) {
        this.scalar = scalar;
    }

    public Integer getIdentity() {
        return identity;
    }

    public void setIdentity(Integer identity) {
        this.identity = identity;
    }*/

  /*  public String getRetInfo() {
        return retInfo;
    }

    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }*/


}
