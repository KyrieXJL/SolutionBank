package com.lenovo.smarttraffic.Entity;

public class Car34 {
    private int id;
    private String card;
    private int money;
    private String date;

    public Car34(int id, String card, int money, String date) {
        this.id = id;
        this.card = card;
        this.money = money;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
