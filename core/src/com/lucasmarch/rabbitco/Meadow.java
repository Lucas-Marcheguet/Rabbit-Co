package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Meadow {

    private World world;
    private int index;
    private int rabbitScore;
    private boolean alreadyUsed = false;

    private HashMap<Vector, Entity> entities;
    private HashMap<Vector, Animal> animals;
    private HashMap<Vector, Carrot> carrots;

    public Meadow(HashMap<Vector, Entity> entities, int index, World world) {
        this.world = world;
        this.index = index;
        this.entities = entities;
        this.carrots = new HashMap<>();
        this.animals = new HashMap<>();
        for(Entity e : this.entities.values()){
            e.prepare();
        }
        prepareCarrots();
        prepareAnimals();
    }

    public Meadow(Meadow meadow){
        this.entities = (HashMap<Vector, Entity>) meadow.entities.clone();
        this.carrots = (HashMap<Vector, Carrot>) meadow.carrots.clone();
        this.animals = (HashMap<Vector, Animal>) meadow.animals.clone();

        this.world = meadow.world;
    }

    public void update(float elapsedTime, SpriteBatch batch){
        for(Entity e : this.entities.values()){
            e.update(elapsedTime, batch);
        }
    }

    public void spawn(SpriteBatch batch){
        for(Entity e : this.entities.values()){
            e.spawn(batch);
        }
        spawnCarrots(batch);
        spawnAnimals(batch);
    }

    public void nextDay(float day){
        if(this.carrots.size() < this.animals.size() || this.animals.size() > 6){
            this.rabbitScore-=this.animals.size()*0.5;
        }
        clearDay(day);
        if(day%1==0) {
            prepareCarrots();
        }
    }

    public void clearDay(float day){
        int i = 0;
        while(i<=this.animals.size()-1){
            Vector key = (Vector) this.animals.keySet().toArray()[i];
            this.animals.get(key).nextDay(day);
            i++;
        }
    }

    public Meadow compareMeadows(Animal a){
        return world.compareMeadows(a, this);
    }

    public void leave(Animal a){
        animals.remove(a.getPos(), a);
    }

    public void join(Animal a){
        if(a!=null){
            animals.put(new Vector(600, 400, true), a);
        }
    }

    public void prepareCarrots(){
        int nbNewCarrots = 0;
        if(this.carrots.size() > 5){
            nbNewCarrots = 0;
        }
        else {
            nbNewCarrots = (int)(3+ Math.random()* (5-3));
        }
        for(int i=0; i<nbNewCarrots;i++){
            Vector tempPos = new Vector(800, 600, true);
            this.rabbitScore+=1;
            carrots.put(tempPos, new Carrot(tempPos, this));
        }
    }

    public void spawnCarrots(SpriteBatch batch){
        for(Carrot carrot: carrots.values()){
            carrot.spawn(batch);
        }
    }

    public void prepareAnimals(){
        int nbFirstRabbits = (int)(4 + Math.random() * 8 - 4);
        for (int i = 0; i < nbFirstRabbits; i++) {
            this.rabbitScore += 1;
            Vector tempPos = new Vector(500, 400, true);
            Rabbit tempRabbit = new Rabbit(tempPos, this);
            animals.put(tempPos, tempRabbit);
        }
    }

    public void spawnAnimals(SpriteBatch batch){
        for(Vector key : animals.keySet()){
            animals.get(key).spawn(batch);
        }
    }

    public void destroy(){
        this.entities.clear();
    }

    public ArrayList<Rabbit> getAllRabits(){
        ArrayList<Rabbit> res = new ArrayList<>();
        for(Animal animal: this.animals.values()){
            if(animal instanceof Rabbit){
                res.add((Rabbit) animal);
            }
        }
        return res;
    }

    /*public ArrayList<Fox> getAllFoxes(){
        ArrayList<Fox> res = new ArrayList<>();
        for(Animal animal: this.animals.values()){
            if(animal instanceof Fox){
                res.add((Fox) animal);
            }
        }
        return res;
    }*/

    public HashMap<Vector, Carrot> getAllCarrots(){
        return this.carrots;
    }

    public int getIndex(){
        return this.index;
    }

    public int getRabbitScore(){
        return this.rabbitScore;
    }

    public void removeCarrot(Carrot carrot){
        if(carrot!=null){
            this.carrots.remove(carrot.getPos(), carrot);
        }
    }

    public void addRabbit(Rabbit r){
        this.animals.put(r.getPos(), r);
    }

    public Vector findAnimalKey(Animal a){
        Vector res = null;
        for(Vector key: this.animals.keySet()){
            if(this.animals.get(key) == a){
                res = key;
            }
        }
        return res;
    }

    public void removeRabbit(Rabbit r) {
        this.animals.remove(findAnimalKey(r));
    }

    public int getNbCarrots() {
        return this.carrots.size();
    }

    public int getNbRabbits(){
        return this.getAllRabits().size();
    }
}

