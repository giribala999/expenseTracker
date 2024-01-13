package com.app.expensetracker.controller;

import java.util.List;

public class CatTransResponse {
    String cat_id=null;
    List<Double> trans=null;

    public CatTransResponse() {
    }

    public CatTransResponse(String cat_id, List<Double> trans){
        this.cat_id=cat_id;
        System.out.println("In CatTransResponse ....ID :"+cat_id+".....trans :"+trans);
        this.trans=trans;
    }

    public String getCat_id() {
        return cat_id;
    }

    public void setCat_id(String cat_id) {
        this.cat_id = cat_id;
    }

    public List<Double> getTrans() {
        return trans;
    }

    public void setTrans(List<Double> trans) {
        this.trans = trans;
    }

    @Override
    public String toString() {
        return "CatTransResponse{" +
                "cat_id='" + cat_id + '\'' +
                ", trans=" + trans +
                '}';
    }
}
