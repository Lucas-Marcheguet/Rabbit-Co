package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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
    public void create(SpriteBatch batch) {
        tree = new AnimatedSprite(loader.getTreeTA(), 14);
        tree.setPosition(pos.getX(), pos.getY());
        tree.draw(batch);
    }

}
