package com.lucasmarch.rabbitco;

import java.util.ArrayList;

public class LeavingObjects {
    private ArrayList<Animal> animals;
    private ArrayList<Carrot> carrots;

    public LeavingObjects(){
        animals = new ArrayList<>();
        carrots = new ArrayList<>();
    }

    public void addAnimal(Animal a){
        animals.add(a);
    }

    public ArrayList<Animal> getAnimals(){
        return this.animals;
    }

    public void addCarrot(Carrot c){
        this.carrots.add(c);
    }

    public ArrayList<Carrot> getCarrots(){
        return this.carrots;
    }

    public void clear(){
        this.carrots.clear();
        this.animals.clear();
    }


}
