package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class Lake implements Entity {

    private ArrayList<AnimatedSprite> sprites;
    private AnimLoader loader;
    private Vector pos;
    private int spriteSize = 64;

    public Lake(Vector position){
        loader = new AnimLoader();
        sprites = new ArrayList<>();
        pos = position;
    }

    public void update(float elapsedTime){
        for (AnimatedSprite sprite: sprites) {
            sprite.update(elapsedTime);
        }
    }

    public void prepare() {
        AnimatedSprite tL = new AnimatedSprite(loader.getTLRiverTA(), 14);
        tL.setPosition(pos.getX(), pos.getY()+spriteSize);
        AnimatedSprite tR = new AnimatedSprite(loader.getTLRiverTA(), 14);
        tR.setFlip(true, false);
        tR.setPosition(pos.getX()+spriteSize, pos.getY()+spriteSize);
        AnimatedSprite bL = new AnimatedSprite(loader.getTLRiverTA(), 14);
        bL.setFlip(false, true);
        bL.setPosition(pos.getX(), pos.getY());
        AnimatedSprite bR = new AnimatedSprite(loader.getTLRiverTA(), 14);
        bR.setFlip(true, true);
        bR.setPosition(pos.getX()+spriteSize, pos.getY());

        this.sprites.add(tL);
        this.sprites.add(tR);
        this.sprites.add(bL);
        this.sprites.add(bR);
    }

    @Override
    public void spawn(SpriteBatch batch){
        for(AnimatedSprite sprite: this.sprites) {
            sprite.draw(batch);
        }
    }
}
