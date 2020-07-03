package com.lenovo.smarttraffic.Entity;

public class Car22_1 {
    private String date;
    private String user;
    private String carid;
    private int money;
    private int balance;

    public Car22_1(String date, String user, String carid, int money, int balance) {
        this.date = date;
        this.user = user;
        this.carid = carid;
        this.money = money;
        this.balance = balance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCarid() {
        return carid;
    }

    public void setCarid(String carid) {
        this.carid = carid;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
