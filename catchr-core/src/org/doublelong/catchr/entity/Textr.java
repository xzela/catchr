package org.doublelong.catchr.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Textr
{
	private Vector2 position;
	public Vector2 getPosition() { return this.position; }

	public Textr(Vector2 position)
	{
		this.position = position;
	}

	public Textr(Ball ball)
	{
		this.position = ball.getBody().getPosition();
	}

	public void update(float delta)
	{

	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{

	}
}
