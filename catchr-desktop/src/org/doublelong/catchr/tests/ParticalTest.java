package org.doublelong.catchr.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParticalTest implements ApplicationListener, Screen {

	private SpriteBatch batch;
	private ParticleEffect effect;
	private BitmapFont font;
	private OrthographicCamera camera;

	public static void main (String[] argv)
	{
		new LwjglApplication(new ParticalTest(), "My Partical Test", 600, 600, false);
	}

	@Override
	public void create()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
		float aspectRatio = (float) width / (float) height;
		camera = new OrthographicCamera(2f * aspectRatio, 2f);

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}


	@Override
	public void show()
	{
		batch = new SpriteBatch();
		effect = new ParticleEffect();
		font = new BitmapFont();

		camera.update();
		camera.apply(Gdx.gl10);

		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// TODO Auto-generated method stub

		batch.setProjectionMatrix(camera.combined);

		effect.load(Gdx.files.internal("data/fire.p"), Gdx.files.internal("data/particale.png"));
		effect.setPosition(100f, 100f);
		effect.start();
	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void render(float delta)
	{
		// TODO Auto-generated method stub

		batch.begin();
		font.draw(batch, "string", 100f, 100f);
		effect.draw(batch, delta);
		batch.end();

	}
}