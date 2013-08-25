package org.doublelong.catchr.screens;

import org.doublelong.catchr.Catchr;
import org.doublelong.catchr.entity.Paddle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

public class CatchrScreen implements Screen
{
	private final World world;
	private final OrthographicCamera cam;
	private final Box2DDebugRenderer debugRenderer;

	private final Paddle player;

	private static final float BOX_STEP = 1/60f;
	private static final int BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;
	private static final float WORLD_TO_BOX = 0.01f;
	private static final float BOX_WORLD_TO = 100f;


	public CatchrScreen(Catchr game, boolean debug)
	{
		this.world = new World(new Vector2(0f, -10f), false);
		this.cam = new OrthographicCamera();
		this.debugRenderer = new Box2DDebugRenderer();
		this.player = new Paddle(this.world);

		this.cam.viewportHeight = game.WINDOW_HEIGHT;
		this.cam.viewportWidth = game.WINDOW_WIDTH;
		this.cam.position.set(this.cam.viewportWidth / 2, this.cam.viewportHeight / 2, 0f);
		this.cam.update();

	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void hide()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void pause()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.debugRenderer.render(this.world, this.cam.combined);
		this.world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
	}

	@Override
	public void resize(int arg0, int arg1)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void resume()
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void show()
	{
		// TODO Auto-generated method stub

	}
}
