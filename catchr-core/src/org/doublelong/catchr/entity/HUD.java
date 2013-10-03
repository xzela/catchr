package org.doublelong.catchr.entity;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class HUD
{
	private Textr textr =  new Textr();
	private Textr score;
	private Board board;

	public HUD(Board board)
	{
		this.board = board;
		this.score = new Textr(new Vector2(30f, 580f));
	}

	public void render(SpriteBatch batch, OrthographicCamera camera)
	{
		this.score.render(batch, camera);
	}

	public void update(float delta)
	{
		this.score.setMessage("Score: " + String.valueOf(Math.round(this.board.getPlayer().getScore())));
	}
}
