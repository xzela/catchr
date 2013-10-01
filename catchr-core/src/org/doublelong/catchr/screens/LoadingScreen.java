package org.doublelong.catchr.screens;

import org.doublelong.catchr.Catchr;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.scenes.scene2d.Stage;

public class LoadingScreen extends AbstractScreen
{
	private Stage stage;

	public LoadingScreen(Catchr game)
	{
		super(game);
	}

	@Override
	public void show()
	{
		this.game.manager.load("assets/sounds/contemplation_2.mp3", Music.class);
		this.game.manager.load("assets/sounds/laser1.mp3", Sound.class);
		//		this.game.manager.finishLoading();
	}

	@Override
	public void render(float delta)
	{
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if(this.game.manager.update())
		{
			game.setScreen(new CatchrScreen(this.game, true));
		}
	}
}
