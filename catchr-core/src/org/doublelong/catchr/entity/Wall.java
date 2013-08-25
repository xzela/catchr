package org.doublelong.catchr.entity;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Wall
{
	private final World world;

	private final Body body;

	private final BodyDef bodyDef = new BodyDef();

	private final Fixture fixture;

	private final FixtureDef fixtureDef = new FixtureDef();

	private final PolygonShape shape = new PolygonShape();

	public Wall(World world, Vector2 position, Vector2 bounds)
	{
		this.world = world;

		this.bodyDef.type = BodyType.StaticBody;
		this.bodyDef.position.set(position);
		this.shape.setAsBox(bounds.x, bounds.y);

		this.fixtureDef.shape = this.shape;

		this.body = this.world.createBody(this.bodyDef);
		this.fixture = this.body.createFixture(this.fixtureDef);
	}
}
