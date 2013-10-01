package org.doublelong.catchr.entity;

import java.util.ArrayList;
import java.util.List;

import org.doublelong.catchr.Catchr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;

public class Board
{
	private static final float SPAWN_WAIT_TIME = 2f;
	private static final Vector2 GRAVITY = new Vector2(0, -50);
	public static float UNIT_WIDTH = Catchr.WINDOW_WIDTH / 160;
	public static float UNIT_HEIGHT = Catchr.WINDOW_HEIGHT / 160;

	private float time = 0;
	private final OrthographicCamera cam;

	private final World world;
	public World getWorld() {return this.world; }

	private final Paddle player;
	public Paddle getPlayer() { return this.player; }

	private final Wall[] walls;

	private final List<Textr> bt = new ArrayList<Textr>();

	private int ballCount = 0;
	private final int ballLimit = 10;
	private final List<Ball> balls;

	private final boolean debug;

	private Textr score;

	private SpriteBatch batch;
	private BitmapFont font;

	private final ParticleEffect effect;
	private Array<ParticleEmitter> emitters;

	private Music music = Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/contemplation_2.mp3"));


	public Board(Catchr game, OrthographicCamera cam, boolean debug)
	{
		this.music.play();

		this.debug = debug;

		this.world = new World(GRAVITY, false);

		this.cam = cam;
		this.cam.setToOrtho(false, game.WINDOW_WIDTH, game.WINDOW_HEIGHT);

		this.player = new Paddle(this.world, new Vector2(this.cam.viewportWidth / 2, 100f));

		this.balls = this.generateBalls(1);
		this.walls = this.generateWalls();

		this.score = new Textr(new Vector2(30f, 580f));

		this.batch = new SpriteBatch();
		this.font = new BitmapFont();

		this.effect = new ParticleEffect();
		this.effect.load(Gdx.files.internal("assets/particles/squirt2.p"), Gdx.files.internal("assets/images"));
	}

	public void dispose()
	{
		this.world.dispose();
		this.player.dispose();
		this.music.dispose();
		this.effect.dispose();
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
		this.cam.update();

		this.player.controller.processControls();
		this.batch.begin();
		this.score.setMessage("Score: " + String.valueOf(Math.round(this.player.getScore())));
		this.score.render(this.batch, this.cam);

		this.player.render(this.batch, this.cam);
		for (Wall wall : this.walls)
		{
			wall.render(this.batch, this.cam);
		}
		for (int i = 0; i < this.balls.size(); i++)
		{
			Ball b = this.balls.get(i);
			b.renderer.renderer(this.batch, this.cam);
			if (b.getBody().getPosition().y < 0)
			{
				this.balls.remove(b);
				this.world.destroyBody(b.getBody());
			}
		}
		for (int i = 0; i < this.bt.size(); i++)
		{
			Textr t = this.bt.get(i);
			if (t.getTimer() < 100)
			{
				t.update(delta);
				t.render(this.batch, this.cam);
			}
		}

		this.testCollisions(delta, this.batch);
		this.effect.draw(this.batch, delta);

		this.batch.end();
		this.tick(delta);
	}

	private void spwanBall()
	{
		if (this.ballCount <= this.ballLimit)
		{
			this.balls.add(new Ball(this));
			this.ballCount++;
		}
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

	private Wall[] generateWalls()
	{
		int numberOfWallBlocks = Math.round(this.cam.viewportHeight / Wall.HEIGHT);
		Wall[] walls = new Wall[numberOfWallBlocks * 2]; // times 2 because we have two walls

		float y_pos = Wall.HEIGHT;
		for (int i = 0; i < numberOfWallBlocks; i++)
		{
			walls[i] = new Wall(this.world, new Vector2(Wall.WIDTH, y_pos));
			y_pos = y_pos + Wall.HEIGHT + 15;
		}

		y_pos = Wall.HEIGHT;
		for (int i = numberOfWallBlocks; i < numberOfWallBlocks * 2; i++)
		{
			walls[i] = new Wall(this.world, new Vector2(this.cam.viewportWidth - Wall.WIDTH, y_pos));
			y_pos = y_pos + Wall.HEIGHT + 15;
		}
		return walls;
	}

	private void testCollisions(float delta, SpriteBatch batch)
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
						float p = b.getPoints();
						this.bt.add(b.getScoreText());
						this.player.addPoint(p);
						b.playSound();
						b.setPoints(p + p);
						b.setBounceCount(b.getBounceCount() + 1);
						b.renderer.changeTexture();
						if (b.getBounceCount() > Ball.MAX_BOUNCE)
						{
							killList.add(b);
							this.balls.remove(j);
						}
					}
				}
			}
		}

		if (killList.size() > 0)
		{
			for(int i = 0; i < killList.size(); i++)
			{
				Ball b = killList.get(i);
				b.explode(this.effect.getEmitters().get(0));
				killList.remove(b);
				this.world.destroyBody(b.getBody());
			}
		}
	}
}
