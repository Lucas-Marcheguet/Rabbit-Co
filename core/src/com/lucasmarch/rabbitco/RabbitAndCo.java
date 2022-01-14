package com.lucasmarch.rabbitco;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class RabbitAndCo extends ApplicationAdapter {
	Viewport viewport;
	Camera camera;
	World world;
	SpriteBatch batch;
	SpriteBatch hudBatch;
	float elapsedTime;
	int nbMeadows = 6;
	int meadowNb = 0;


	@Override
	public void create () {
		world = World.getInstance(new Vector(3, 3));
		batch = new SpriteBatch();
		hudBatch = new SpriteBatch();
		camera = new PerspectiveCamera();
		viewport = new FitViewport(800, 600, camera);
		world.prepareWorld(800,600);
	}

	@Override
	public void render () {
		elapsedTime += Gdx.graphics.getDeltaTime();
		ScreenUtils.clear(0.45f, 0.68f, 0.25f, 1);

		batch.begin();


		meadowNb = world.handleMeadow(meadowNb, batch);
		HUD.meadow=meadowNb;
		HUD.nbCarrots=world.getMeadow(meadowNb).getNbCarrots();
		HUD.nbRabbits = world.getMeadow(meadowNb).getNbRabbits();
		HUD.nbRabbitsTotal=world.getNbRabitsTotal();
		HUD.rScore=world.getMeadow(meadowNb).getRabbitScore();

		world.updateWorld(elapsedTime, batch);
		world.nextDay(elapsedTime, Gdx.graphics.getDeltaTime());

		batch.end();



		hudBatch.begin();

		HUD.draw(hudBatch);

		hudBatch.end();
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
