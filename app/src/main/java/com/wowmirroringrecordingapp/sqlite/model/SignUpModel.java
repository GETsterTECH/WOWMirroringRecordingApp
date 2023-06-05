package com.wowmirroringrecordingapp.sqlite.model;

public class SignUpModel {

    public static final String TABLE = "Signup";
    // Labels Table Columns names
    public static final String KEY_cus_id = "cus_id";
    public static final String KEY_parish_name = "parish_name";
    public static final String KEY_cus_name = "cus_name";
    public static final String KEY_cus_psw = "cus_psw";
    public static final String KEY_cus_mobile = "cus_mobile";
    public static final String KEY_cus_type = "cus_type";
    public static final String KEY_trans_id = "trans_id";
    public static final String KEY_date = "date";

    private String cus_id = "", parish_name = "", cus_name = "", cus_psw = "", cus_mobile = "", cus_type = "",trans_id="",date="";

    public SignUpModel() {
    }

    public SignUpModel(String cus_id, String parish_name, String cus_name, String cus_psw, String cus_mobile, String cus_type, String trans_id, String date) {
        this.cus_id = cus_id;
        this.parish_name = parish_name;
        this.cus_name = cus_name;
        this.cus_psw = cus_psw;
        this.cus_mobile = cus_mobile;
        this.cus_type = cus_type;
        this.trans_id = trans_id;
        this.date = date;
    }

    public String getCus_id() {
        return cus_id;
    }

    public void setCus_id(String cus_id) {
        this.cus_id = cus_id;
    }

    public String getParish_name() {
        return parish_name;
    }

    public void setParish_name(String parish_name) {
        this.parish_name = parish_name;
    }

    public String getCus_name() {
        return cus_name;
    }

    public void setCus_name(String cus_name) {
        this.cus_name = cus_name;
    }

    public String getCus_psw() {
        return cus_psw;
    }

    public void setCus_psw(String cus_psw) {
        this.cus_psw = cus_psw;
    }

    public String getCus_mobile() {
        return cus_mobile;
    }

    public void setCus_mobile(String cus_mobile) {
        this.cus_mobile = cus_mobile;
    }

    public String getCus_type() {
        return cus_type;
    }

    public void setCus_type(String cus_type) {
        this.cus_type = cus_type;
    }

    public String getTrans_id() {
        return trans_id;
    }

    public void setTrans_id(String trans_id) {
        this.trans_id = trans_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
