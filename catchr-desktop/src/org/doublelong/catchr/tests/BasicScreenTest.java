package org.doublelong.catchr.tests;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class BasicScreenTest implements ApplicationListener
{

	private OrthographicCamera camera;
	private SpriteBatch batch;
	private BitmapFont font;

	@Override
	public void create ()
	{
		camera = new OrthographicCamera();
		batch = new SpriteBatch();
		font = new BitmapFont();

		camera.setToOrtho(false, 600, 600);

	}

	@Override
	public void render ()
	{
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT); // This cryptic line clears the screen.
		camera.update();
		batch.begin();
		font.draw(batch, "string", 100f, 100f);
		// Drawing goes here!
		batch.end();
	}

	@Override
	public void resize (int width, int height) { }

	@Override
	public void pause () { }

	@Override
	public void resume () { }

	@Override
	public void dispose () { }

	public static void main(String args[])
	{
		new LwjglApplication(new BasicScreenTest(), "My Basic Screen Test", 600, 600, false);
	}
}