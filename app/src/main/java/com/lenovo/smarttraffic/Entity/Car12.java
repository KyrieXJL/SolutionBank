package com.lenovo.smarttraffic.Entity;

import java.util.List;

public class Car12 {


        /**
         * score : 3
         * money : 242
         * carno :
         * count : 1
         * details : [{"score":2,"address":"医院路","money":100,"time":"2017-04-02 17:05:08","content":"驾驶机动车在高速公路，往市区方向下高速未按规定车道行驶"},{"score":0,"address":"医院路","money":200,"time":"2017-04-02 17:05:08","content":"驾驶机动车在高速公路，往市区方向下高速未按规定车道行驶"},{"score":1,"address":"钱胡路","money":0,"time":"2017-04-02 17:05:08","content":"驾驶机动车在高速公路，往市区方向下高速未按规定车道行驶"}]
         */

        private int score;
        private int money;
        private String carno;
        private int count;
        private List<DetailsBean> details;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }

        public String getCarno() {
            return carno;
        }

        public void setCarno(String carno) {
            this.carno = carno;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean {
            /**
             * score : 2
             * address : 医院路
             * money : 100
             * time : 2017-04-02 17:05:08
             * content : 驾驶机动车在高速公路，往市区方向下高速未按规定车道行驶
             */

            private int score;
            private String address;
            private int money;
            private String time;
            private String content;

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
                this.score = score;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public int getMoney() {
                return money;
            }

            public void setMoney(int money) {
                this.money = money;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }
        }

}
