package com.db1;


    public class Dao {
        String t1,t2,t3;

        public String getT1() {
            return t1;
        }

        public void setT1(String t1) {
            this.t1 = t1;
        }

        public String getT2() {
            return t2;
        }

        public void setT2(String t2) {
            this.t2 = t2;
        }

        public String getT3() {
            return t3;
        }

        public void setT3(String t3) {
            this.t3 = t3;
        }

        public Dao(String t1, String t2, String t3) {
            this.t1 = t1;
            this.t2 = t2;
            this.t3 = t3;
        }
    }

