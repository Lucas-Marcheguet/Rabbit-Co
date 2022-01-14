package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Texture;

public abstract class Animal implements IEventAge, IEventHunger, Entity {

    boolean mated;
    int tresholdE = 1;
    int ageMaturity = 3;
    int ageDeath = 12;
    int baseE = 3;
    String sex;
    HungerControler hungerControler;
    AgeControler ageControler;
    Meadow currentMeadow;
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

    abstract void nextDay(float day);

    public void changeMeadow(Meadow newMeadow){
        this.currentMeadow.leave(this);
        newMeadow.join(this);
        this.currentMeadow = newMeadow;
        this.eventNoEat();
    }

    public abstract void die();

    abstract void createChild(Vector postion);

    abstract void compareActions();
    abstract void goEat();

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

    public abstract void moveOn(Vector movePosition);

    abstract void moveNext(Vector movePosition);

    public Vector getPos(){
        return this.pos;
    }

    public int getAgeMaturity(){
        return this.ageMaturity;
    }

    public abstract void reproduce(Vector pos);
}
