package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class InfoText extends Actor {

    private BitmapFont font = new BitmapFont();

    public void setColor(Color c){
        this.font.setColor(c);
    }

    public void setScale(float scale){
        this.font.getData().setScale(scale);
    }

    public void draw(SpriteBatch batch, String text){
        this.font.draw(batch, text, this.getX(), this.getY());
    }
}
