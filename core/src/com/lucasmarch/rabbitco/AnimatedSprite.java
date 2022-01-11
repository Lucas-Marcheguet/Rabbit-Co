package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AnimatedSprite extends Sprite {

    private Texture[] frames;
    private int currentFrame;
    private float fps;
    private float timer;

    public AnimatedSprite(Texture[] frames, float framePerSecond){
        super(frames[0]);


        this.frames = frames;
        this.fps = framePerSecond;
        this.currentFrame = 0;
        this.timer = 0;
    }

    public void update(float elapsedTime)
    {
        if(timer < 1.0 / fps)
            this.timer += elapsedTime;
        else {
            this.timer -= 1.0/fps;
            nextFrame();
        }
    }

    public void nextFrame(){
        if(this.currentFrame < this.frames.length)
            this.currentFrame++;
        if(this.currentFrame == this.frames.length)
            this.currentFrame = 0;


        setTexture(this.frames[this.currentFrame]);
    }

}
