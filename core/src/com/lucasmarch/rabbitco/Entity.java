package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {

    void update(float elapsedTime);
    void create(SpriteBatch batch);
}
