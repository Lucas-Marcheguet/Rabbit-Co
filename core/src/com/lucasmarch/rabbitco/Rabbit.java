package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Rabbit extends Animal {

    private Vector position;
    private Sprite sprite;
    private BitmapFont energyFont;
    private LeavingObjects leavingObjs;

    public Rabbit(Vector position, Meadow currentMeadow) {
        super(currentMeadow);
        this.energyFont = new BitmapFont();
        this.position = position;
        this.ageMaturity = 5;
        this.moveCost = 1;
        makeSprite();
        this.sprite.setPosition(position.getX(), position.getY());
        this.sprite.scale(1.0f);
        this.energyFont.getData().setScale(0.9f);
        this.leavingObjs = new LeavingObjects();
    }

    public LeavingObjects nextDay(float day){
        this.leavingObjs.clear();
        if(!this.dead){
            this.grow();
            if(day%1!=0){
                Meadow bestMeadow = compareMeadows(this);
                if(bestMeadow != currentMeadow){
                    leavingObjs.addAnimal(this.changeMeadow(bestMeadow));
                }
            }
            else {
                leavingObjs.addCarrot(compareActions());
            }
        }
        return leavingObjs;
    }

    @Override
    Carrot compareActions() {
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
        if (rdm > 0.5 * matingWeight && !this.hungerControler.isHungry()) {
            this.eventNoEat();
            this.reproduce(opposedSex.get((int) (Math.random() * opposedSex.size()-1)).getPos());
        } else {
            return this.goEat();
        }
        return null;
    }

    @Override
    Carrot goEat() {
        HashMap<Vector, Carrot> carrots = this.currentMeadow.getAllCarrots();
        int randIndex = (int) (Math.random() * carrots.size() - 1);
        Carrot carrot = carrots.get(carrots.keySet().toArray()[randIndex]);
        this.eventEat();
        return carrot;
    }

    @Override
    public void die(){
        System.out.println(this + "died");
        this.currentMeadow.leave(this);
        this.sprite.getTexture().dispose();
    }

    @Override
    public void reproduce(Vector pos) {
        this.createChild(pos);
    }

    @Override
    void createChild(Vector position) {
        if(this.currentMeadow != null){
            this.currentMeadow.getAllRabits().add(new Rabbit(position, this.currentMeadow));
        }
    }


    @Override
    public Vector getPos(){
        return this.position;
    }

    @Override
    public void update(float elapsedTime) {
        return;
    }

    @Override
    public void prepare() {
        return;
    }

    @Override
    public void spawn(SpriteBatch batch) {
        this.sprite.draw(batch);
        this.energyFont.draw(batch, "Energie : " + this.hungerControler.getReserveE(), this.position.getX(), this.position.getY()+40);
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
}

