package com.example.stonecollection.models;

import java.util.ArrayList;

public class StoneCollection {
    private int id;
    private String name;
    public ArrayList<Stone> stones=new ArrayList<>();
    public StoneCollection(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public StoneCollection(String name) {
        this.name = name;
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

    public double getPrice(){
        double fullprice=0;
        for (Stone s: stones) {
            fullprice+=s.getPrice();
        }
       return fullprice;
    }

    public int getPreciousAmount(){
        int preciousAmount=0;
        for (Stone s: stones) {
            if(s instanceof Precious)
                preciousAmount++;
        }
       return preciousAmount ;
    }

    public int getSemiPreciousAmount(){
        int semipreciousAmount=0;
        for (Stone s: stones) {
            if(s instanceof SemiPrecious)
                semipreciousAmount++;
        }
        return semipreciousAmount;
    }

    public StoneCollection(int id, String name, ArrayList<Stone> stones) {
        this.id = id;
        this.name = name;
        this.stones = stones;
    }
}
