package org.doublelong.catchr.entity;

import org.doublelong.catchr.controller.PaddleController;
import org.doublelong.catchr.renderer.PaddleRenderer;

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

public class Paddle
{
	public static final float WIDTH = 50f;
	public static final float HEIGHT = 10f;

	private final World world;
	public PaddleController controller;
	private PaddleRenderer renderer;

	private float score = 0;
	public float getScore() { return this.score; }
	public void addPoint(float point) { this.score = this.score + point;}

	private final Body body;
	public Body getBody() { return this.body; }

	private final BodyDef bodyDef = new BodyDef();

	private final Fixture fixture;
	public Fixture getFixture() { return this.fixture; }

	private final Fixture sensorFixture;
	public Fixture getSensorFixture() { return this.sensorFixture; }

	private final FixtureDef fixtureDef = new FixtureDef();

	private final PolygonShape shape = new PolygonShape();

	public Paddle(World world, Vector2 position)
	{
		this.world = world;

		this.bodyDef.type = BodyType.DynamicBody;
		this.bodyDef.position.set(position); //set starting position
		this.bodyDef.fixedRotation = true;

		this.shape.setAsBox(WIDTH, HEIGHT);

		this.fixtureDef.shape = this.shape;
		this.fixtureDef.density = 5f;
		this.fixtureDef.friction = 0f;
		this.fixtureDef.restitution = 1f;

		this.body = this.world.createBody(this.bodyDef);
		this.body.setGravityScale(0);
		this.fixture = this.body.createFixture(this.fixtureDef);

		this.sensorFixture = this.body.createFixture(this.shape, 0);
		this.controller = new PaddleController(this);

		this.renderer = new PaddleRenderer(this);
	}

	public void dispose()
	{
		this.shape.dispose();
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		this.renderer.render(batch, camera);
	}
}
