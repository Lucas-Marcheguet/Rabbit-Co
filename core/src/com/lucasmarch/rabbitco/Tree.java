package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.sql.Time;
import java.util.ArrayList;

public class Tree implements Entity{

    private AnimatedSprite tree;
    private AnimLoader loader;
    private Vector pos;

    public Tree(Vector position){
        loader = new AnimLoader();
        pos = position;
    }

    @Override
    public void update(float elapsedTime){
        tree.update(elapsedTime);
    }

    @Override
    public void prepare() {
        tree = new AnimatedSprite(loader.getTreeTA(), 1);
        tree.setPosition(pos.getX(), pos.getY());
    }

    public void spawn(SpriteBatch batch){
        tree.draw(batch);
    }

}
