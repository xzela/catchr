package org.doublelong.catchr.entity;

import org.doublelong.catchr.Catchr;

import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
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

	public Ball(Board board)
	{
		this.board = board;
		this.bodyDef.type = BodyType.DynamicBody;
		this.bodyDef.position.set(new Vector2(this.getRandomX(), 560f));
		this.body = this.board.getWorld().createBody(this.bodyDef);

		this.shape.setRadius(10f);
		this.fixtureDef.shape = this.shape;
		this.fixtureDef.density = 0f;
		this.fixtureDef.friction = 0f;
		this.fixtureDef.restitution = 4f;
		this.fixture = this.body.createFixture(this.fixtureDef);
		int d = (Math.random() > .5) ? -1 : 1;
		this.body.applyLinearImpulse(new Vector2(d * 10, 0f), this.body.getPosition());

		//		this.effect = new ParticleEffect();
		//		this.effect.load(Gdx.files.internal("data/squrt.p"), Gdx.files.internal("data"));
		//		this.effect.setPosition(100f, 100f);
		//		this.effect.start();
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
}
