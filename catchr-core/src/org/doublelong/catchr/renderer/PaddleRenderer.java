package org.doublelong.catchr.renderer;

import org.doublelong.catchr.entity.Paddle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class PaddleRenderer
{
	private Paddle paddle;
	private Sprite sprite;
	private Texture texture;

	public PaddleRenderer(Paddle paddle)
	{
		this.paddle = paddle;
		this.texture = new Texture(Gdx.files.internal("assets/images/buttonDefault.png"));
		this.sprite = new Sprite(this.texture);
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		Vector2 pos = this.paddle.getBody().getPosition();
		this.sprite.setBounds(pos.x, pos.y, Paddle.WIDTH, Paddle.HEIGHT);
		this.sprite.setPosition(pos.x, pos.y);

		//		batch.setProjectionMatrix(camera.combined);
		//		batch.begin();
		this.sprite.draw(batch);
		//		batch.end();
	}
}
