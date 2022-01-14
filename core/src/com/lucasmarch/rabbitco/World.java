package com.lucasmarch.rabbitco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;

public class World {

    //Variables fonctionnelles importantes
    private World world = null;
    private ArrayList<Meadow> meadows;
    private float day;

    //Variable utilisées pour le visionnage
    private Vector dimensions;
    private Meadow shownMeadow;
    private int nbOfMeadows;
    private boolean alreadyUsed = false;
    private float dayTime;

    //Fonctions importantes
    private World(Vector dimensions){
        this.world = this;
        this.dimensions = dimensions;
        this.meadows = new ArrayList<>();
        this.nbOfMeadows = this.dimensions.getX()*this.dimensions.getY();
    }

    public static World getInstance(Vector dimensions){
        return new World(dimensions);
    }

    public void nextDay(float elapsedTime, float dayTimeAdd){
        if((int)(elapsedTime)>2f){
            int timeMult = (int) (elapsedTime%5);
            if(timeMult==0){
                if(alreadyUsed == false) {
                    alreadyUsed = true;
                    this.day += 0.5;
                    for (Meadow m : meadows) {
                        m.nextDay(this.day);
                    }
                }
            }
            dayTime+=dayTimeAdd;
            if(dayTime > 5.0f){
                alreadyUsed = false;
                dayTime = 0;
            }
        }
    }

    public Meadow compareMeadows(Animal a, Meadow m){
        int[] neighborsIndex = getNeighbors(m.getIndex());
        Meadow bestMeadow = meadows.get(neighborsIndex[0]);
        if(a instanceof Rabbit){
            for(int index : neighborsIndex){
                if(index>=0 && index<this.nbOfMeadows-1){
                    Meadow currMeadow = this.meadows.get(index);
                    if(bestMeadow.getRabbitScore() < currMeadow.getRabbitScore()){
                        bestMeadow = currMeadow;
                    }
                }
            }
        }
        return bestMeadow;
    }

    public int[] getNeighbors(int index){
        int[] res = {index, index-this.dimensions.getX(), index+1, index+this.dimensions.getX(), index-1};
        return res;
    }

    public void prepareWorld(int screenWidth, int screenHeight){
        for (int index = 0; index < this.nbOfMeadows; index++) {
            RandomMapGen generator = new RandomMapGen(50);
            meadows.add(new Meadow(generator.createMap(screenWidth, screenHeight), index, this));
        }
    }

    public void updateWorld(float elapsedTime, SpriteBatch batch){
        HUD.time = elapsedTime;
        HUD.day = this.day;
        for (Meadow meadow: this.meadows) {
            meadow.update(elapsedTime, batch);
        }
    }

    public void spawnMeadow(int meadowNb, SpriteBatch batch){
        shownMeadow = new Meadow(this.meadows.get(meadowNb));
        shownMeadow.spawn(batch);
    }

    public void destroy(int meadowNb){
        shownMeadow.destroy();
    }



    //Fonctions moins importantes
    public int handleMeadow(int meadowNb, SpriteBatch batch){
        this.spawnMeadow(meadowNb, batch);
        if(Gdx.input.isKeyJustPressed(Input.Keys.RIGHT)){
            this.destroy(meadowNb);
            meadowNb+=1;
            if(meadowNb > this.nbOfMeadows-1){
                meadowNb=0;
            }
        }
        if(Gdx.input.isKeyJustPressed(Input.Keys.LEFT)){
            this.destroy(meadowNb);
            meadowNb-=1;
            if(meadowNb < 0){
                meadowNb=this.nbOfMeadows-1;
            }
        }
        return meadowNb;
    }

    public float getDay(){
        return this.day;
    }

    public Meadow getMeadow(int meadowNb){
        return this.meadows.get(meadowNb);
    }

    public int getNbRabitsTotal() {
        int cpt = 0;
        for(Meadow m: this.meadows){
            cpt += m.getNbRabbits();
        }
        return cpt;
    }
}
