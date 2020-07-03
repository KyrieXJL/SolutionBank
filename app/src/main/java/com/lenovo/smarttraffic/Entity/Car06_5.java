package com.lenovo.smarttraffic.Entity;

import android.support.annotation.NonNull;

public class Car06_5  implements Comparable{


        /**
         * cdate : 2017-12-10 20:11:57
         * carno : 苏D-A0123B
         */

        private String cdate;
        private String carno;


    public Car06_5(String cdate, String carno) {
        this.cdate = cdate;
        this.carno = carno;
    }

    public String getCdate() {
            return cdate;
        }

        public void setCdate(String cdate) {
            this.cdate = cdate;
        }

        public String getCarno() {
            return carno;
        }

        public void setCarno(String carno) {
            this.carno = carno;
        }

    @Override
    public int compareTo(@NonNull Object o) {
        Car06_5 s= (Car06_5) o;
        return this.carno.compareTo(s.carno);
    }
}
