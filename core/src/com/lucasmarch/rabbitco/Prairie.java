package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class Prairie {

    private Monde monde;
    private HashMap<Vector, Entity> entities;

    private Vector pos;

    public Prairie(SpriteBatch batch, HashMap<Vector, Entity> entities) {
        this.entities = entities;
        for(Vector key : entities.keySet()){
            entities.get(key).create(batch);
        }
    }

    public void update(float elapsedTime){
        for(Vector key : entities.keySet()){
            entities.get(key).update(elapsedTime);
        }
    }


    public Vector getPos(){
        return this.pos;
    }

}
