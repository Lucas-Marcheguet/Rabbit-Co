package com.lucasmarch.rabbitco;

import com.badlogic.gdx.graphics.Texture;

public class AnimLoader {

    public Texture[] getBushTA(){
        Texture[] textures = new Texture[14];
        for (int i = 0; i < 14; i++) {
            int nb = i+1;
            textures[i] = new Texture("AnimationFrames/Bush/bush32x32transparentanimated" + nb + ".png");
        }
        return textures;
    }

    public Texture[] getFlowerTA(){
        Texture[] textures = new Texture[14];
        for (int i = 0; i < 14; i++) {
            int nb = i+1;
            textures[i] = new Texture("AnimationFrames/Flower/flower32x32transparentanimated" + nb + ".png");
        }
        return textures;
    }

    public Texture[] getBLRiverTA(){
        Texture[] textures = new Texture[14];
        for (int i = 0; i < 14; i++) {
            int nb = i+1;
            textures[i] = new Texture("AnimationFrames/River_Bottom_Left/riverbottomleft64x64animated" + nb + ".png");
        }
        return textures;
    }

    public Texture[] getBMRiverTA(){
        Texture[] textures = new Texture[14];
        for (int i = 0; i < 14; i++) {
            int nb = i+1;
            textures[i] = new Texture("AnimationFrames/River_Bottom_Middle/riverbottom64x64transparentanimatednotbright" + nb + ".png");
        }
        return textures;
    }

    public Texture[] getTLRiverTA(){
        Texture[] textures = new Texture[14];
        for (int i = 0; i < 14; i++) {
            int nb = i+1;
            textures[i] = new Texture("AnimationFrames/River_Top_Left/rivertopleft64x64transparent" + nb + ".png");
        }
        return textures;
    }

    public Texture[] getTMRiverTA(){
        Texture[] textures = new Texture[14];
        for (int i = 0; i < 14; i++) {
            int nb = i+1;
            textures[i] = new Texture("AnimationFrames/River_Top_Middle/rivertop64x64transparentanimated" + nb + ".png");
        }
        return textures;
    }

    public Texture[] getTreeTA(){
        Texture[] textures = new Texture[14];
        for (int i = 0; i < 14; i++) {
            int nb = i+1;
            textures[i] = new Texture("AnimationFrames/Tree/tree96x96transparentanimated" + nb + ".png");
        }
        return textures;
    }
}
