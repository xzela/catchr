package org.doublelong.catchr.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Paddle
{
	private final World world;
	private final Body body;

	private final BodyDef bodyDef = new BodyDef();

	private final Fixture fixture;

	private final FixtureDef fixtureDef = new FixtureDef();

	private final PolygonShape shape = new PolygonShape();

	public Paddle(World world, Vector2 position)
	{
		this.world = world;
		this.bodyDef.type = BodyType.KinematicBody;
		this.bodyDef.position.set(position); //set starting position

		this.shape.setAsBox(50f, 10f);

		this.fixtureDef.shape = this.shape;
		this.fixtureDef.density = 0.01f;
		this.fixtureDef.friction = 0f;
		this.fixtureDef.restitution = 0.5f;

		this.body = this.world.createBody(this.bodyDef);
		this.fixture = this.body.createFixture(this.fixtureDef);
	}
}
