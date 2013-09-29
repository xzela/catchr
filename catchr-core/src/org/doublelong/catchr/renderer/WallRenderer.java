package org.doublelong.catchr.renderer;

import org.doublelong.catchr.entity.Wall;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class WallRenderer
{
	private Wall wall;
	private Sprite sprite;
	private Texture texture;


	public WallRenderer(Wall wall)
	{
		this.wall = wall;
		this.texture = new Texture(Gdx.files.internal("assets/images/ground_dirt.png"));
		this.sprite = new Sprite(this.texture);

	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		Vector2 pos = this.wall.getBody().getPosition();
		this.sprite.setSize(Wall.WIDTH * 2, Wall.HEIGHT * 2);
		this.sprite.setPosition(pos.x - Wall.WIDTH, pos.y - Wall.HEIGHT);

		//this.sprite.draw(batch);
	}
}
