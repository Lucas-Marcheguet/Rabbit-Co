package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
    public static float day;
    public static float time;
    static BitmapFont dayFont = new BitmapFont();
    static BitmapFont timeFont = new BitmapFont();


    public static void draw(SpriteBatch batch){
        dayFont.draw(batch, "Day " + HUD.day, 50, 550);
        timeFont.draw(batch, "Elapsed Time " + HUD.time, 50, 530);
    }
}
