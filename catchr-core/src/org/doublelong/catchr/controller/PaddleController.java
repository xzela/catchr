package org.doublelong.catchr.controller;

import org.doublelong.catchr.entity.Paddle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector2;

public class PaddleController extends InputAdapter
{
	private static float Y_POS;
	private final static float MAX_VELOCTY = 500f;
	private final static float STEP_VELOCTY = 50f;
	private final Paddle paddle;

	private float stillTime = 0f;


	public PaddleController(Paddle paddle)
	{
		this.paddle = paddle;
		Y_POS = this.paddle.getBody().getPosition().y;
		Gdx.input.setInputProcessor(this);
	}

	public void processControls()
	{
		Vector2 vel = this.paddle.getBody().getLinearVelocity();
		Vector2 pos = this.paddle.getBody().getPosition();
		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT)
				&& !Gdx.input.isKeyPressed(Keys.UP) && !Gdx.input.isKeyPressed(Keys.DOWN))
		{
			this.stillTime += Gdx.graphics.getDeltaTime();
			this.paddle.getBody().setLinearVelocity(vel.x * 0.99f, vel.y * 0.99f);
			this.paddle.getBody().setAngularDamping(2f);
		}
		else
		{
			this.stillTime = 0;
		}

		if(!Gdx.input.isKeyPressed(Keys.LEFT) && !Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			this.paddle.getFixture().setFriction(100f);
			this.paddle.getSensorFixture().setFriction(100f);
		}
		else
		{
			this.paddle.getFixture().setFriction(0.2f);
			this.paddle.getSensorFixture().setFriction(0.2f);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT))
		{
			//this.paddle.getBody().applyLinearImpulse(-STEP_VELOCTY, 0f, pos.x, pos.y);
			this.paddle.getBody().setLinearVelocity(new Vector2(vel.x - 2f, vel.y));
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			//this.paddle.getBody().applyLinearImpulse(STEP_VELOCTY, 0f, pos.x, pos.y);
			this.paddle.getBody().setLinearVelocity(new Vector2(vel.x + 2f, vel.y));
		}
		if (pos.y != Y_POS)
		{
			this.paddle.getBody().setTransform(this.paddle.getBody().getPosition().x, Y_POS, this.paddle.getBody().getAngle());
		}
	}

}
