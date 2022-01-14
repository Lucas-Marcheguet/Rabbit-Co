package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface Entity {
    void update(float elapsedTime, SpriteBatch batch);
    void prepare();
    void spawn(SpriteBatch batch);
}
