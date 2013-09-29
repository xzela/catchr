package org.doublelong.catchr.entity;

import org.doublelong.catchr.renderer.WallRenderer;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
	public final static float HEIGHT = 10;
	public final static float WIDTH = 10;

	private final World world;

	private final Body body;
	public Body getBody() { return this.body; }

	private final BodyDef bodyDef = new BodyDef();

	private final Fixture fixture;

	private final FixtureDef fixtureDef = new FixtureDef();

	private final PolygonShape shape = new PolygonShape();

	private WallRenderer renderer;

	public Wall(World world, Vector2 position)
	{
		this.world = world;

		this.bodyDef.type = BodyType.StaticBody;
		this.bodyDef.position.set(position);
		this.shape.setAsBox(WIDTH, HEIGHT);

		this.fixtureDef.shape = this.shape;

		this.body = this.world.createBody(this.bodyDef);
		this.fixture = this.body.createFixture(this.fixtureDef);

		this.renderer = new WallRenderer(this);
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		this.renderer.render(batch, camera);
	}
}
