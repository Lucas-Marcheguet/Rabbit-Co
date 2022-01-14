package com.lucasmarch.rabbitco;

import java.util.HashMap;

public class RandomMapGen {

    private int nbLakes;
    private int nbTrees;
    private int nbGrass;
    private int sum;

    public RandomMapGen(int maxEntities ){
        sum = maxEntities;
        if (maxEntities < 25) throw new IllegalArgumentException(
                "nombre d'entitées maximum trop bas");
        nbLakes = (int) (1 + Math.random() * (2-1));
        nbTrees = (int) (3 + Math.random() * (6-3));
        nbGrass = maxEntities - nbLakes - nbTrees;
    }

    public HashMap<Vector, Entity> createMap(int width, int height){
        HashMap<Vector, Entity> map = new HashMap<>();
        for (int i = 0; i < sum; i++) {
            if(i<nbLakes){
                Vector tempPos = new Vector(width, height, true);
                if(!map.containsKey(tempPos)){
                    map.put(tempPos, new Lake(tempPos));
                }
                else
                    i-=1;
            }
            if(i<nbTrees){
                Vector tempPos = new Vector(width, height, true);
                if(!map.containsKey(tempPos)){
                    map.put(tempPos, new Tree(tempPos));
                }
                else
                    i-=1;
            }
            if(i<nbGrass){
                Vector tempPos = new Vector(width, height, true);
                if(!map.containsKey(tempPos)){
                    map.put(tempPos, new Grass(tempPos, true, ""));
                }
                else
                    i-=1;
            }
        }
        return map;
    }
}
