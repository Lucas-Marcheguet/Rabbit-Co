package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class HUD {
    public static float day;
    public static float time;
    public static int meadow;
    public static int nbCarrots;
    public static int nbRabbits;
    public static int nbRabbitsTotal;
    public static int rScore;
    static BitmapFont dayFont = new BitmapFont();
    static BitmapFont timeFont = new BitmapFont();
    static BitmapFont meadowFont = new BitmapFont();
    static BitmapFont nbCarrotsFont = new BitmapFont();
    static BitmapFont nbRabbitsMeadowFont = new BitmapFont();
    static BitmapFont nbRabbitsTotalFont = new BitmapFont();
    static BitmapFont RabbitScore = new BitmapFont();



    public static void draw(SpriteBatch batch){
        meadowFont.draw(batch, "Prairie : " + HUD.meadow, 400, 600);
        dayFont.draw(batch, "Day " + HUD.day, 50, 600);
        timeFont.draw(batch, "Elapsed Time " + HUD.time, 700, 650);
        nbCarrotsFont.draw(batch, "Nb de carottes : " + HUD.nbCarrots, 700, 635);
        nbRabbitsMeadowFont.draw(batch, "Nb de lapins : " + HUD.nbRabbits, 700, 620);
        nbRabbitsTotalFont.draw(batch, "Nb de lapins total : " + HUD.nbRabbitsTotal, 700, 605);
        RabbitScore.setColor(Color.RED);
        RabbitScore.draw(batch, "Rabbit score : " + HUD.rScore, 700, 590);
    }
}
