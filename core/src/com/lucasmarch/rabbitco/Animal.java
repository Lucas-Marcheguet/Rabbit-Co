package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Texture;

public abstract class Animal implements IEventAge, IEventHunger, Entity {

    int tresholdE = 3;
    int moveCost = 1;
    int ageMaturity;
    int ageDeath = 50;
    int baseE = 5;
    String sex;
    HungerControler hungerControler;
    AgeControler ageControler;
    Meadow currentMeadow;
    Texture texture;
    Vector pos;
    boolean dead = false;

    public Animal(Meadow currentMeadow){
        this.currentMeadow = currentMeadow;
        hungerControler = new HungerControler(this);
        ageControler = new AgeControler(this);
    }

    public void setRdmSex(){
        double rdm = Math.random();
        if(rdm >= 0.5f){
            this.sex = "Male";
        }
        else {
            this.sex = "Female";
        }
    }

    public Meadow compareMeadows(Animal a){
        return currentMeadow.compareMeadows(a);
    }

    public Animal changeMeadow(Meadow newMeadow){
        this.currentMeadow = newMeadow;
        newMeadow.join(this);
        this.eventNoEat();
        return this;
    }

    public abstract void die();

    abstract void createChild(Vector postion);

    abstract Carrot compareActions();
    abstract Carrot goEat();

    @Override
    public void eventEat() { this.hungerControler.eventEat(); }

    @Override
    public void eventNoEat() {
        this.hungerControler.eventNoEat();
    }

    @Override
    public void grow(){
        this.ageControler.grow();
    }

    //Fonctions pas importantes
    public String getSex(){
        return this.sex;
    }

    public void moveNext(Vector movePosition){
        this.pos = new Vector(movePosition.getX() + 15, movePosition.getY() + 15);
    }

    public Vector getPos(){
        return this.pos;
    }

    public int getAgeMaturity(){
        return this.ageMaturity;
    }

    public abstract void reproduce(Vector pos);

    public abstract LeavingObjects nextDay(float day);
}
