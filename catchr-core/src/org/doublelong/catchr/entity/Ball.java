package org.doublelong.catchr.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball
{
	private final World world;

	private final CircleShape shape = new CircleShape();

	private final Body body;
	public Body getBody() { return this.body; }

	private final BodyDef bodyDef = new BodyDef();

	private final Fixture fixture;
	public Fixture getFixture() { return this.fixture; }

	private final FixtureDef fixtureDef = new FixtureDef();

	public Ball(World world)
	{
		this.world = world;
		this.bodyDef.type = BodyType.DynamicBody;
		this.bodyDef.position.set(new Vector2(300f, 300f));
		this.body = this.world.createBody(this.bodyDef);

		this.shape.setRadius(10f);
		this.fixtureDef.shape = this.shape;
		this.fixtureDef.density = 500f;
		this.fixtureDef.friction = .2f;
		this.fixtureDef.restitution = .01f;
		this.fixture = this.body.createFixture(this.fixtureDef);
	}


	public void update()
	{
	}

	public void collides(Paddle paddle)
	{

	}

	public void dispose()
	{
		this.shape.dispose();
	}
}
