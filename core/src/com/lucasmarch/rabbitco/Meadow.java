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

    private LeavingObjects leavingObjs;

    public Meadow(HashMap<Vector, Entity> entities, int index, World world) {
        this.world = world;
        this.index = index;
        this.entities = entities;
        this.carrots = new HashMap<>();
        this.animals = new HashMap<>();
        this.leavingObjs = new LeavingObjects();
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

    public void update(float elapsedTime){
        for(Entity e : this.entities.values()){
            e.update(elapsedTime);
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
        clearDay(day);
        if(day%1==0) {
            prepareCarrots();
        }
    }

    public void clearDay(float day){
        for(Animal animal : animals.values()){
            this.leavingObjs = animal.nextDay(day);
        }
        if(this.leavingObjs != null){
            for(Animal a: this.leavingObjs.getAnimals()){
                this.leave(a);
            }
            for(Carrot c: this.leavingObjs.getCarrots()){
                this.removeCarrot(c);
            }
        }
        this.leavingObjs.clear();
    }

    public Meadow compareMeadows(Animal a){
        return world.compareMeadows(a, this);
    }

    public void leave(Animal a){
        if(a!=null){
            animals.remove(a.getPos(), a);
        }
    }

    public void join(Animal a){
        if(a!=null){
            animals.put(new Vector(600, 400, true), a);
        }
    }

    public void prepareCarrots(){
        int nbNewCarrots = (int)(2 + Math.random() * (5-2));
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
        int nbFirstBunnies = (int)(Math.random() * 4);
        for (int i = 0; i < nbFirstBunnies; i++) {
            this.rabbitScore += 1;
            Vector tempPos = new Vector(800, 600, true);
            Rabbit tempRabbit = new Rabbit(tempPos, this);
            animals.put(tempPos, tempRabbit);
        }
        if(this.carrots.size() < this.animals.size()){
            this.rabbitScore-=15;
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
            System.out.println("Carrot eaten");
            this.carrots.remove(carrot.getPos(), carrot);
        }
    }


}
