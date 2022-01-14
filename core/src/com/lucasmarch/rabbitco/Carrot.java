package com.lucasmarch.rabbitco;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Carrot extends Sprite implements Entity {

    Vector position;
    Meadow meadow;

    public Carrot(Vector position, Meadow meadow){
        super(new Texture("32x32carrot.png"));
        this.meadow = meadow;
        this.setPosition(position.getX(), position.getY());
        this.scale(-0.1f);
    }

    public void eaten(){
        this.getTexture().dispose();
        this.meadow.removeCarrot(this);
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
        this.draw(batch);
    }

    public Vector getPos(){
        return this.position;
    }
}
