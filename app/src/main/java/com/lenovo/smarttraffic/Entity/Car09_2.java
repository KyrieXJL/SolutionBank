package com.lenovo.smarttraffic.Entity;

import java.util.List;

public class Car09_2 {

    /**
     * code : 0
     * serverinfo : {"list":[{"image":"http://106.14.2.80:8080/ts/images/bmw.jpg","balance":100,"user":"张三","card":"辽A10001","carId":1},{"image":"http://106.14.2.80:8080/ts/images/dz.jpg","balance":99,"user":"李四","card":"辽A10002","carId":2},{"image":"http://106.14.2.80:8080/ts/images/maybach.jpg","balance":103,"user":"王五","card":"辽A10003","carId":3},{"image":"http://106.14.2.80:8080/ts/images/toyota.jpg","balance":1,"user":"赵六","card":"辽A10004","carId":4},{"image":"http://106.14.2.80:8080/ts/images/toyota.jpg","balance":70,"user":"赵六","card":"辽A10004","carId":5},{"image":"http://106.14.2.80:8080/ts/images/toyota.jpg","balance":100,"user":"赵六","card":"辽A10004","carId":6}]}
     */

    private int code;
    private ServerinfoBean serverinfo;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public ServerinfoBean getServerinfo() {
        return serverinfo;
    }

    public void setServerinfo(ServerinfoBean serverinfo) {
        this.serverinfo = serverinfo;
    }

    public static class ServerinfoBean {
        private List<ListBean> list;

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
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
    }
}
