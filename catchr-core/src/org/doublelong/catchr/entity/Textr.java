package org.doublelong.catchr.entity;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;

public class Textr
{
	private float alpha = 1f;
	private Vector2 position;
	public Vector2 getPosition() { return this.position; }

	private FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/kenpixel_future_square.ttf"));
	public BitmapFont font;

	private String message;
	public void setMessage(String message) { this.message = message; }

	private int timer = 0;
	public int getTimer() { return this.timer; }

	private int size = 14;
	public void setSize(int size) { this.size = size; }

	private String direction = "up";
	public void setDirectrion(String direction) { this.direction = direction; }

	public Textr()
	{
		this.font = this.generator.generateFont(this.size);
	}

	public Textr(Vector2 position)
	{
		this.position = new Vector2(position);
		this.font = this.generator.generateFont(this.size);
	}

	public Textr(Vector2 position, int size)
	{
		this.position = new Vector2(position);
		this.font = this.generator.generateFont(size);
	}

	public void update(float delta)
	{
		if (this.alpha < 0)
		{
			this.alpha = 0;
		}
		else
		{
			this.alpha = this.alpha - .01f;
		}
		if (this.direction.equals("up"))
		{
			this.position.y = this.position.y + .7f;
		}
		else
		{
			this.position.y = this.position.y - .7f;
		}
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		this.font.setColor(2f, 2f, 2f, this.alpha);
		this.font.draw(batch, this.message, this.position.x, this.position.y);
		this.timer++;
	}
}
