package app.oscarbernt.economyapp;

import java.util.Date;


public class Purchase {


    String desc;
    String category;
    String date;
    int id;
    double cost;

    public Purchase(){}

    public Purchase(String desc, String category, double cost, String date){
        this.desc = desc;
        this.category = category;
        this.cost = cost;
        this.date = date;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
