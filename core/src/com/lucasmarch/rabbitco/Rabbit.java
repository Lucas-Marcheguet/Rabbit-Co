package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Rabbit extends Animal {

    private Vector position;
    private Sprite sprite;
    private InfoText energyFont;
    private boolean died = false;

    public Rabbit(Vector position, Meadow currentMeadow) {
        super(currentMeadow);
        this.energyFont = new InfoText();
        this.position = position;
        this.ageMaturity = 2;
        this.ageDeath = 10;
        makeSprite();
        this.sprite.setPosition(position.getX(), position.getY());
        this.energyFont.setScale(0.9f);
    }

    public void nextDay(float day){
        this.mated=false;
        if(day%1!=0){
            double rdm = Math.random();
            Meadow bestMeadow = compareMeadows(this);
            if(bestMeadow != currentMeadow && rdm > 0.5 && !this.isHungry()){
                this.changeMeadow(bestMeadow);
            }
        }
        else {
            this.grow();
            compareActions();

        }
    }

    @Override
    public void compareActions() {
        float matingScore = 1;
        ArrayList<Rabbit> opposedSex = new ArrayList<>();
        for (Rabbit rabbit : this.currentMeadow.getAllRabits()) {
            if (rabbit.getSex() != this.sex) {
                opposedSex.add(rabbit);
                matingScore += 1;
            }
        }
        float matingWeight = 1 - (1 / matingScore);
        double rdm = Math.random() * matingWeight;
        if (rdm > 0.5 * matingWeight && !this.isHungry() && !this.isBaby() && opposedSex.size()>0) {
            Rabbit r = opposedSex.get((int) (Math.random() * opposedSex.size()));
            if(!r.getMated()){
                this.mated = true;
                this.reproduce(r.getPos());
            }
        }
        else {
            this.goEat();
        }
        this.eventNoEat();
        this.eventNoEat();
        this.eventNoEat();
        this.eventNoEat();
    }

    @Override
    public void goEat() {
        HashMap<Vector, Carrot> carrots = this.currentMeadow.getAllCarrots();
        if(carrots.size()!=0){
            int randIndex = (int) (Math.random() * carrots.size());
            Carrot carrot = carrots.get(carrots.keySet().toArray()[randIndex]);
            this.moveOn(carrot.getPos());
            this.currentMeadow.removeCarrot(carrot);
            this.eventEat();
        }
    }

    @Override
    public void die(){
        this.died = true;
        this.sprite.setAlpha(100);
        this.currentMeadow.removeRabbit(this);
    }

    @Override
    public void reproduce(Vector pos) {
        this.moveNext(pos);
        this.createChild(pos);
        this.moveNext(position);
    }

    @Override
    void createChild(Vector position) {
        if(this.currentMeadow != null){
            this.currentMeadow.addRabbit(new Rabbit(position.add(new Vector(15, 15, true)), this.currentMeadow));
        }
    }


    @Override
    public Vector getPos(){
        return this.position;
    }

    @Override
    public void update(float elapsedTime, SpriteBatch batch) {
        return;
    }

    @Override
    public void prepare() {
        return;
    }

    @Override
    public void spawn(SpriteBatch batch) {
        if(!this.died){
            this.energyFont.setPosition(this.position.getX(), this.position.getY()+50);
            this.energyFont.draw(batch, "Energie : " + this.hungerControler.getReserveE());
            this.sprite.draw(batch);
            if(this.hungerControler.isHungry()){
                this.energyFont.setColor(new Color(Color.RED));
            }
            else {
                this.energyFont.setColor(new Color(Color.WHITE));
            }
            if(this.ageControler.isBaby()){
                this.sprite.setScale(1.0f);
            }
            else {
                this.sprite.setScale(1.5f);
            }
        }
        else {
            this.sprite.draw(batch);
            this.sprite.setAlpha(255);
        }
    }

    public void makeSprite(){
        setRdmSex();
        if(this.sex == "Male"){
            this.sprite = new Sprite(new Texture("bunnyM.png"));
        }
        else {
            this.sprite = new Sprite(new Texture("bunnyF.png"));
        }
    }

    @Override
    public void moveOn(Vector movePosition){
        this.position = new Vector(movePosition.getX(), movePosition.getY());
        this.sprite.setPosition(movePosition.getX(), movePosition.getY());
    }

    @Override
    public void moveNext(Vector movePosition){
        this.position = new Vector(movePosition.getX()-10, movePosition.getY());
        this.sprite.setPosition(movePosition.getX()-10, movePosition.getY());
    }

    public boolean isBaby(){
        return this.ageControler.isBaby();
    }

    public boolean isHungry(){
        return this.hungerControler.isHungry();
    }

    public boolean getMated(){
        return this.mated;
    }
}

