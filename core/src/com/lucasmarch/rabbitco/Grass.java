package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Grass implements Entity{

    private String letter;
    private Sprite grass;
    private Vector pos;

    public Grass(Vector position, boolean randomType, String grassLetter){
        if(randomType){
            String[] letters = {"a", "b", "c", "d", "e", "f", "g", "h", "i"};
            int index = (int)(Math.random() * letters.length-1);
            this.letter = letters[index];
        }
        else {
            if(grassLetter==""){
                System.out.println("Merci de choisir une lettre entre a et i");
            }
            else {
                this.letter = grassLetter;
            }
        }
        pos = position;
    }

    @Override
    public void update(float elapsedTime) {
        return;
    }

    @Override
    public void prepare() {
        grass = new Sprite(new Texture("grass"+ letter +"32x32transparent.png"));
        grass.setPosition(pos.getX(), pos.getY());
    }

    @Override
    public void spawn(SpriteBatch batch) {
        grass.draw(batch);
    }
}
