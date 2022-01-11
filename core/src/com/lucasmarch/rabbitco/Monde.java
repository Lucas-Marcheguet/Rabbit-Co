package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;
import java.util.HashMap;

public class Monde {
    private ArrayList<Prairie> prairies;
    private int nbOfPrairies;
    private int day;

    public Monde(int nbOfPrairies){
        this.nbOfPrairies = nbOfPrairies;
        this.prairies = new ArrayList<>();
    }

    public ArrayList<Prairie> getBiomesVoisins(Prairie p){
        ArrayList<Prairie> nearBiomes = new ArrayList<>();
        for (Prairie prairie: this.prairies) {
            if(p.getPos().isNear(1, prairie.getPos()))
                nearBiomes.add(prairie);
        }
        return nearBiomes;
    }

    public void createWorld(SpriteBatch batch, int screenWidth, int screenHeight){
        for (int x = 0; x < nbOfPrairies; x++) {
            RandomMapGen generator = new RandomMapGen(50);
            prairies.add(new Prairie(batch, generator.createMap(screenWidth, screenHeight)));
        }
    }

    public void update(float elapsedTime){
        for (Prairie prairie: this.prairies) {
            prairie.update(elapsedTime);
        }
    }

    public int getDay(){
        return this.day;
    }
}
