package org.doublelong.catchr.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class Textr
{
	private Vector2 position;
	public Vector2 getPosition() { return this.position; }

	private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/kenpixel_future_square.ttf"));
	private BitmapFont font;

	private String message;
	public void setMessage(String message) { this.message = message; }

	private int timer = 0;
	public int getTimer() { return this.timer; }

	public Textr(Vector2 position)
	{
		this.position = position;
		this.font = this.generator.generateFont(14);
	}

	public Textr(Ball ball)
	{
		this.position = ball.getBody().getPosition();
		this.font = this.generator.generateFont(14);
	}

	public void update(float delta)
	{

	}

	public void render(SpriteBatch batch, OrthographicCamera cam)
	{
		this.font.draw(batch, this.message, this.position.x, this.position.y);
		this.timer++;
	}
}
