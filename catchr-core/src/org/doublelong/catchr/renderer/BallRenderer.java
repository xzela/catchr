package org.doublelong.catchr.renderer;

import org.doublelong.catchr.entity.Ball;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class BallRenderer
{
	private Ball ball;
	private Sprite sprite;
	private Texture texture;

	public BallRenderer(Ball ball)
	{
		this.ball = ball;
		this.texture = new Texture(Gdx.files.internal("assets/images/ballBlue.png"));
		this.sprite = new Sprite(this.texture);
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		Vector2 pos = this.ball.getBody().getPosition();
		float rad = this.ball.getFixture().getShape().getRadius();
		this.sprite.setPosition(pos.x - rad, pos.y - rad);
		this.sprite.draw(batch);
	}

	public void changeTexture()
	{
		this.texture = new Texture(Gdx.files.internal("assets/images/ballGrey.png"));
		this.sprite = new Sprite(this.texture);
	}
}
