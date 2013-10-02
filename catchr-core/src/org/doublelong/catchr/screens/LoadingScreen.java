package org.doublelong.catchr.screens;

import org.doublelong.catchr.Catchr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class LoadingScreen extends AbstractScreen
{
	private Stage stage;

	private Image logo;

	public LoadingScreen(Catchr game)
	{
		super(game);
	}

	@Override
	public void show()
	{
		this.game.manager.load("assets/sounds/contemplation_2.mp3", Music.class);
		this.game.manager.load("assets/sounds/laser1.mp3", Sound.class);
		this.game.manager.load("assets/images/catchr_logo.png", Texture.class);

		this.game.manager.finishLoading();

		this.stage = new Stage();

		this.logo = new Image(this.game.manager.get("assets/images/catchr_logo.png", Texture.class));

		this.stage.addActor(this.logo);


	}

	@Override
	public void resize(int width, int height)
	{
		stage.setViewport(width, height, false);
		this.logo.setX((width - logo.getWidth()) / 2);
		this.logo.setY((height - logo.getHeight()) / 2);
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if(this.game.manager.update())
		{
			if (Gdx.input.isKeyPressed(Keys.G))
			{
				game.setScreen(new CatchrScreen(this.game, true));
			}
		}
		this.stage.act();
		this.stage.draw();
	}
}
