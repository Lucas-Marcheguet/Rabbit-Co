package com.lucasmarch.rabbitco;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RabbitAndCo extends ApplicationAdapter {
	Viewport viewport;
	Camera camera;
	Monde world;
	SpriteBatch batch;
	float elapsedTime;


	@Override
	public void create () {
		world = new Monde(1);
		batch = new SpriteBatch();
		camera = new PerspectiveCamera();
		viewport = new FitViewport(800, 600, camera);
	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0.54f, 0.83f, 0.31f, 1);
		batch.begin();
		//montre la premiere prairie
		world.createWorld(batch, 800, 600);

		//update each frames
		world.update(elapsedTime);
		//world.update(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height){
		viewport.update(width, height);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
