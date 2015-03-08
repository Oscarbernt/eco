package app.oscarbernt.economyapp;


public class Category {

    String name;
    int id;
    double limit;
    double currval;

    public Category(){}

    public Category(String name, double limit, double currval){
        this.name = name;
        this.limit = limit;
        this.currval = currval;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getCurrval() {
        return currval;
    }

    public void setCurrval(double currval) {
        this.currval = currval;
    }
}
