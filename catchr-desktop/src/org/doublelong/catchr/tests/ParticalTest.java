package org.doublelong.catchr.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ParticalTest implements ApplicationListener
{

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
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		effect = new ParticleEffect();
		font = new BitmapFont();
		camera.setToOrtho(false, 600, 600);

		effect.load(Gdx.files.internal("data/squrt.p"), Gdx.files.internal("data"));
		effect.setPosition(100f, 100f);
		effect.start();

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
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		camera.update();

		batch.begin();
		font.draw(batch, "string", 100f, 100f);
		effect.draw(batch, Gdx.graphics.getDeltaTime());
		batch.end();
	}

	@Override
	public void resize(int width, int height)
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub
	}
}