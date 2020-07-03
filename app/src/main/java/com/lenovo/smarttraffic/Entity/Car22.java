package com.lenovo.smarttraffic.Entity;

import java.util.List;

public class Car22 {


            /**
             * image : http://106.14.2.80:8080/ts/images/bmw.jpg
             * balance : 100
             * user : 张三
             * card : 辽A10001
             * carId : 1
             */

            private String image;
            private int balance;
            private String user;
            private String card;
            private int carId;
            private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getBalance() {
                return balance;
            }

            public void setBalance(int balance) {
                this.balance = balance;
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

            public int getCarId() {
                return carId;
            }

            public void setCarId(int carId) {
                this.carId = carId;
            }


}
