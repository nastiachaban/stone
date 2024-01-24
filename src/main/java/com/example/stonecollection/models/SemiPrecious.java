package com.example.stonecollection.models;

public class  SemiPrecious extends Stone {
    private int quality;
    public SemiPrecious(int id,int quality,String name,double price,Color color,int weight,int stonecollection_id){
        super(id,name,price,color,weight, stonecollection_id) ;
        this.quality=quality;

    }

    public SemiPrecious(String name, double price, Color color, int weight, int quality,int stonecollectionid) {
        super(name, price, color, weight,stonecollectionid);
        this.quality=quality;
    }

    public int getQuality() {
        return quality;
    }

    public void setQuality(int quality) {
        this.quality = quality;

    }

    @Override
    public String toString() {
        return super.toString()+
                " quality " + quality ;
    }

}
