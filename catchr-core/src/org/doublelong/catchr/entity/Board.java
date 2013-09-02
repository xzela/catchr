package org.doublelong.catchr.entity;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.catchr.Catchr;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;

public class Board
{
	private static final float SPAWN_WAIT_TIME = 2f;
	private float time = 0;

	private final Vector2 gravity = new Vector2(0, -50);
	private final OrthographicCamera cam;

	public float BOARD_WIDTH;
	public float BOARD_HEIGHT;

	private final World world;
	public World getWorld() {return this.world; }

	private final Paddle player;
	public Paddle getPlayer() { return this.player; }

	private final Wall[] walls;
	private final List<Ball> balls;

	private final boolean debug;

	public Board(Catchr game, OrthographicCamera cam, boolean debug)
	{
		this.debug = debug;
		this.BOARD_WIDTH = game.WINDOW_WIDTH;
		this.BOARD_HEIGHT = game.WINDOW_HEIGHT;
		this.world = new World(this.gravity, false);

		this.cam = cam;
		this.player = new Paddle(this.world, new Vector2(this.cam.viewportWidth / 2, 100f));

		this.balls = this.generateBalls(1);
		this.walls = this.generateWalls(2);
	}

	public void dispose()
	{
		this.world.dispose();
		this.player.dispose();
	}

	public void tick(float delta)
	{
		this.time += delta;
		if (this.time >= SPAWN_WAIT_TIME)
		{
			this.spwanBall();
			this.time -= SPAWN_WAIT_TIME;
		}
	}

	public void update(float delta)
	{
		this.player.controller.processControls();
		this.testCollisions();
		this.tick(delta);
	}

	private void spwanBall()
	{
		this.balls.add(new Ball(this));
	}

	private List<Ball> generateBalls(int n)
	{
		List<Ball> balls = new ArrayList<Ball>();
		for(int i = 0; i < n; i++)
		{
			balls.add(new Ball(this));
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
		// walls[2] = new Wall(this.world, new Vector2(10f, 1f), new Vector2(this.cam.viewportWidth, 10f));
		return walls;
	}

	private void testCollisions()
	{
		List<Contact> contactList = this.world.getContactList();
		List<Ball> killList = new ArrayList<Ball>();

		for(int i = 0; i < contactList.size(); i++)
		{
			Contact contact = contactList.get(i);
			// test the contacts
			if (contact.isTouching() && (contact.getFixtureA() == this.player.getSensorFixture() || contact.getFixtureB() == this.player.getSensorFixture()))
			{
				for (int j = 0; j < this.balls.size(); j++)
				{
					Ball b =  this.balls.get(j);
					if (contact.getFixtureA() == b.getFixture() || contact.getFixtureB() == b.getFixture())
					{
						killList.add(b);
					}
				}
			}
		}

		if (killList.size() > 0)
		{
			for(int i = 0; i < killList.size(); i++)
			{
				Ball b = killList.get(i);
				killList.remove(b);

				this.world.destroyBody(b.getBody());
			}
		}
	}
}
