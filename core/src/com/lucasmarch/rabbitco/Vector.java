package com.lucasmarch.rabbitco;

public class Vector {
    private int x;
    private int y;

    public Vector(int x, int y){
        this.x = x;
        this.y =y;
    }

    public Vector(int width, int height, boolean random){
        this.x =(int)(Math.random() * width);
        this.y =(int)(Math.random() * height);
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public boolean isNear(int dist, Vector v2){
        if((this.x - dist == v2.getX() ||  this.x + dist == v2.getX()) &&  (this.y + dist == v2.getY() ||  this.y + dist == v2.getY()))
            return true;
        return false;
    }
}
