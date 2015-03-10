package com.rustan.currency;

import java.io.Serializable;


public class CurPOJO implements Serializable {
    private double cur_sale;
    private double cur_buy;

    public String getCur_name() {
        return cur_name;
    }

    public void setCur_name(String cur_name) {
        this.cur_name = cur_name;
    }

    private String cur_name;


    public double getCur_sale() {
        return cur_sale;
    }

    public void setCur_sale(double cur_sale) {
        this.cur_sale = cur_sale;
    }

    public double getCur_buy() {
        return cur_buy;
    }

    public void setCur_buy(double cur_buy) {
        this.cur_buy = cur_buy;
    }
}
