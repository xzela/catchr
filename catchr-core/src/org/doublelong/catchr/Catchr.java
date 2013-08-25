package org.doublelong.catchr;

import org.doublelong.catchr.screens.CatchrScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;

public class Catchr extends Game
{
	public final String WINDOW_TITLE = "Catchr";
	public final int WINDOW_HEIGHT = 600;
	public final int WINDOW_WIDTH = 800;

	private CatchrScreen catchrScreen;

	@Override
	public void create()
	{
		Texture.setEnforcePotImages(false);
		this.initialize();
		this.setScreen(this.catchrScreen);
	}

	private void initialize()
	{
		this.catchrScreen = new CatchrScreen(this, true);
	}
}
