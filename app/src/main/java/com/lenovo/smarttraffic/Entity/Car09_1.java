package com.lenovo.smarttraffic.Entity;

public class Car09_1 {
    private String date1;
    private String user;
    private String card;
    private int money;
    private int balance;
    private String date2;

    public Car09_1(String date1, String user, String card, int money, int balance, String date2) {
        this.date1 = date1;
        this.user = user;
        this.card = card;
        this.money = money;
        this.balance = balance;
        this.date2 = date2;
    }

    public String getDate1() {
        return date1;
    }

    public void setDate1(String date1) {
        this.date1 = date1;
    }

    public String getDate2() {
        return date2;
    }

    public void setDate2(String date2) {
        this.date2 = date2;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
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
