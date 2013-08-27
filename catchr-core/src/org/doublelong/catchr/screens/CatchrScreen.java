package org.doublelong.catchr.screens;

import java.util.List;

import org.doublelong.catchr.Catchr;
import org.doublelong.catchr.entity.Ball;
import org.doublelong.catchr.entity.Paddle;
import org.doublelong.catchr.entity.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;

public class CatchrScreen implements Screen
{
	private final World world;
	private final OrthographicCamera cam;
	public OrthographicCamera getCam() { return this.cam; }

	private final Box2DDebugRenderer debugRenderer;

	private final Paddle player;
	private final Wall[] walls;
	private final Ball ball;
	private final Ball[] balls;

	private final boolean debug;

	private final SpriteBatch batch;
	private final BitmapFont font;

	private static final float BOX_STEP = 1/60f;
	private static final int BOX_VELOCITY_ITERATIONS = 6;
	private static final int BOX_POSITION_ITERATIONS = 2;
	private static final float WORLD_TO_BOX = 0.01f;
	private static final float BOX_WORLD_TO = 100f;


	public CatchrScreen(Catchr game, boolean debug)
	{
		this.debug = debug;
		this.world = new World(new Vector2(0f, -500f), false);
		this.cam = new OrthographicCamera();
		this.cam.viewportHeight = game.WINDOW_HEIGHT;
		this.cam.viewportWidth = game.WINDOW_WIDTH;
		this.cam.position.set(this.cam.viewportWidth / 2, this.cam.viewportHeight / 2, 0f);
		this.cam.update();

		this.debugRenderer = new Box2DDebugRenderer();
		this.player = new Paddle(this.world, new Vector2(this.cam.viewportWidth / 2, 100f));

		this.ball = new Ball(this.world);
		this.balls = this.generateBalls();
		this.walls = this.generateWalls(3);


		this.batch = new SpriteBatch();
		this.font = new BitmapFont();
	}

	private Ball[] generateBalls()
	{
		Ball[] balls = new Ball[3];
		for(int i = 0; i < balls.length; i++)
		{
			balls[i] = new Ball(this.world);
		}
		return balls;
	}

	private Wall[] generateWalls(int n)
	{
		// there are only two walls
		Wall[] walls = new Wall[n];
		// left wall starts at: (0,0), bounds: (10f, viewportHeight)
		walls[0] = new Wall(this.world, new Vector2(11, 1), new Vector2(10f, this.cam.viewportHeight));
		// right wall starts at (viewportWidth - wall width,0), bounds: (10f, viewportHeight)
		walls[1] = new Wall(this.world, new Vector2(this.cam.viewportWidth - 10, 1), new Vector2(10f, this.cam.viewportHeight));
		// bottom wall: starts at (0, 0)
		walls[2] = new Wall(this.world, new Vector2(10f, 1f), new Vector2(this.cam.viewportWidth, 10f));
		return walls;
	}

	@Override
	public void dispose()
	{
		// TODO Auto-generated method stub
		this.ball.dispose();
		this.player.dispose();
		this.world.dispose();
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
		boolean touched = false;
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		this.player.controller.processControls();
		this.ball.update();

		this.debugRenderer.render(this.world, this.cam.combined);

		List<Contact> contactList = this.world.getContactList();
		for(int i = 0; i < contactList.size(); i++)
		{
			Contact contact = contactList.get(i);
			// test the contacts
			if (contact.isTouching() && (contact.getFixtureA() == this.player.getSensorFixture() || contact.getFixtureB() == this.player.getSensorFixture()))
			{
				System.out.println("something is hitting the player!");
				if (contact.getFixtureA() == this.ball.getFixture() || contact.getFixtureB() == this.ball.getFixture())
				{
					touched = true;
					break;
					//this.ball.dispose();
				}
			}
		}

		if (touched)
		{
			this.world.destroyBody(this.ball.getBody());
		}

		this.world.step(BOX_STEP, BOX_VELOCITY_ITERATIONS, BOX_POSITION_ITERATIONS);

		if (this.debug)
		{
			String str = "Angle Vel: " + this.player.getBody().getAngularVelocity() + "\n";
			str += "Angle: " + this.player.getBody().getAngle() + "\n";
			str += "Linera Vel" + this.player.getBody().getLinearVelocity() + "\n";
			this.batch.begin();
			this.font.drawMultiLine(this.batch, str, 10f, 100f);
			this.batch.end();
		}
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
