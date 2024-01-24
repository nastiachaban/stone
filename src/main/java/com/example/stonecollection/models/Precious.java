package com.example.stonecollection.models;

public class Precious extends Stone {
    private Rarity rarity;

    public Precious(int id,String name, double price, Color color, int weight, Rarity rarity,int stonecollection_id) {
        super(id,name, price, color, weight,stonecollection_id);
        this.rarity = rarity;
    }

    public Precious(String name, double price, Color color, int weight, Rarity rarity,int stonecollectionid) {
        super(name, price, color, weight,stonecollectionid);
        this.rarity = rarity;
    }

    public Rarity getRarity() {
        return rarity;
    }

    public void setRarity(Rarity rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return super.toString()+
                " rarity " + rarity ;
    }
}
