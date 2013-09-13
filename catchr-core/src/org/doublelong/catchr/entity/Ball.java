package org.doublelong.catchr.entity;

import org.doublelong.catchr.Catchr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;

public class Ball
{
	private final static float MIN_X = 30f;
	private final static float MAX_X = Catchr.WINDOW_WIDTH - MIN_X;
	private final Board board;

	private final CircleShape shape = new CircleShape();

	private final Body body;
	public Body getBody() { return this.body; }

	private final BodyDef bodyDef = new BodyDef();

	private final Fixture fixture;
	public Fixture getFixture() { return this.fixture; }

	private final FixtureDef fixtureDef = new FixtureDef();

	private final FreeTypeFontGenerator generator;
	private final BitmapFont font;

	private float points = 1;
	public float getPoints() { return this.points; }
	public void setPoints(float points) { this.points = points; }

	private int bounceCount = 0;
	public int getBounceCount() { return this.bounceCount; }
	public void setBounceCount(int c) { this.bounceCount = c; }

	public Ball(Board board)
	{
		this.board = board;
		this.generator = new FreeTypeFontGenerator(Gdx.files.internal("data/kenpixel_future_square.ttf"));
		this.font = this.generator.generateFont(14);

		this.bodyDef.type = BodyType.DynamicBody;
		this.bodyDef.position.set(new Vector2(this.getRandomX(), 560f));
		this.body = this.board.getWorld().createBody(this.bodyDef);

		this.shape.setRadius(10f);
		this.fixtureDef.shape = this.shape;
		this.fixtureDef.density = .5f;
		this.fixtureDef.friction = .2f;
		this.fixtureDef.restitution = 4f;
		this.fixture = this.body.createFixture(this.fixtureDef);
		int d = (Math.random() > .5) ? -1 : 1;
		this.body.applyLinearImpulse(new Vector2(d * 10, 0f), this.body.getPosition());

	}

	private float getRandomX()
	{
		float r = (float) Math.random() * (MAX_X);
		if (r < MIN_X)
		{
			return MIN_X;
		}
		return r;
	}

	public void dispose()
	{
		this.shape.dispose();
	}

	public void explode(ParticleEmitter emitter)
	{
		System.out.println(this.body.getPosition());
		emitter.setPosition(this.body.getPosition().x, this.body.getPosition().y);
		emitter.start();
	}

	public void showPoints(SpriteBatch batch)
	{
		this.font.draw(batch, "word", 100f, 100f);
	}
}
