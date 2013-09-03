package org.doublelong.catchr.screens;

import org.doublelong.catchr.Catchr;
import org.doublelong.catchr.entity.Board;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;

public class CatchrScreen implements Screen
{
	private final Board board;

	private final OrthographicCamera cam;
	public OrthographicCamera getCam() { return this.cam; }

	private final Box2DDebugRenderer debugRenderer;

	private final boolean debug;

	private static final float BOX_STEP = 1/60f;
	private static final int BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;


	public static final float WORLD_TO_BOX = 0.01f;
	public static float convertToBox(float x) { return x * WORLD_TO_BOX; }

	public static final float BOX_TO_WORLD = 100f;
	public static float convertToWorld(float x) { return x * BOX_TO_WORLD;}



	public CatchrScreen(Catchr game, boolean debug)
	{
		this.debug = debug;

		this.cam = new OrthographicCamera();
		this.cam.viewportHeight = Catchr.WINDOW_HEIGHT;
		this.cam.viewportWidth = Catchr.WINDOW_WIDTH;
		this.cam.position.set(this.cam.viewportWidth / 2, this.cam.viewportHeight / 2, 0f);
		this.cam.update();

		this.board = new Board(game, this.cam, this.debug);

		this.debugRenderer = new Box2DDebugRenderer();


	}

	@Override
	public void dispose()
	{
		this.board.dispose();
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
	public void render(float delta)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.debugRenderer.render(this.board.getWorld(), this.cam.combined);

		// test for collisions here!
		this.board.update(delta);
		this.board.getWorld().step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);
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

	@Override
	public void show()
	{
		// TODO Auto-generated method stub
	}
}
