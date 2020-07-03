package com.lenovo.smarttraffic.Entity;

import java.util.List;

public class Car28 {


            /**
             * cars : [{"distance":1000,"people":21,"carid":1},{"distance":100,"people":101,"carid":1}]
             * station : 中医院站
             */

            private String station;
            private List<CarsBean> cars;

            public String getStation() {
                return station;
            }

            public void setStation(String station) {
                this.station = station;
            }

            public List<CarsBean> getCars() {
                return cars;
            }

            public void setCars(List<CarsBean> cars) {
                this.cars = cars;
            }

            public static class CarsBean {
                /**
                 * distance : 1000
                 * people : 21
                 * carid : 1
                 */

                private int distance;
                private int people;
                private int carid;

                public int getDistance() {
                    return distance;
                }

                public void setDistance(int distance) {
                    this.distance = distance;
                }

                public int getPeople() {
                    return people;
                }

                public void setPeople(int people) {
                    this.people = people;
                }

                public int getCarid() {
                    return carid;
                }

                public void setCarid(int carid) {
                    this.carid = carid;
                }
            }


}
