package com.lenovo.smarttraffic.Entity;

public class Car31 {
    private String biaoti;
    private String date;
    private String zhuangtai;
    private String neirong;
    private String tel;

    public Car31(String biaoti, String date, String zhuangtai, String neirong, String tel) {
        this.biaoti = biaoti;
        this.date = date;
        this.zhuangtai = zhuangtai;
        this.neirong = neirong;
        this.tel = tel;
    }

    public String getBiaoti() {
        return biaoti;
    }

    public void setBiaoti(String biaoti) {
        this.biaoti = biaoti;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getZhuangtai() {
        return zhuangtai;
    }

    public void setZhuangtai(String zhuangtai) {
        this.zhuangtai = zhuangtai;
    }

    public String getNeirong() {
        return neirong;
    }

    public void setNeirong(String neirong) {
        this.neirong = neirong;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
}
