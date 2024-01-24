package com.example.stonecollection.models;

public abstract class Stone {
    protected String name;
    protected double price;
    protected Color color;
    protected int weight;
    protected int stonecollection_id;
    protected int id;

    public Stone( int id,String name, double price, Color color, int weight,int stonecollection_id) {
        this.name = name;
        this.stonecollection_id=stonecollection_id;
        this.price = price;
        this.color = color;
        this.weight = weight;
        this.id = id;
    }

    public Stone( String name, double price, Color color, int weight,int stonecollection_id) {
        this.name = name;
        this.stonecollection_id=stonecollection_id;
        this.price = price;
        this.color = color;
        this.weight = weight;
    }

    public int getStonecollection_id() {
        return stonecollection_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStonecollection_id(int stonecollection_id) {
        this.stonecollection_id = stonecollection_id;
    }

    public Stone(String name, double price, Color color, int weight) {
        this.name = name;
        this.price = price;
        this.color = color;
        this.weight = weight;
    }

    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() +
                " name " + name +
                " price " + price +
                " color " + color +
                " weight " + weight ;
    }


}

