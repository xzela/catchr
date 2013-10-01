package org.doublelong.catchr;

import org.doublelong.catchr.screens.CatchrScreen;
import org.doublelong.catchr.screens.LoadingScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Catchr extends Game
{
	public final String WINDOW_TITLE = "Catchr";
	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 400;

	public CatchrScreen catchrScreen;
	private LoadingScreen loadingScreen;

	public AssetManager manager = new AssetManager();

	@Override
	public void create()
	{
		Texture.setEnforcePotImages(false);
		this.initialize();
		this.setScreen(this.loadingScreen);
	}

	private void initialize()
	{
		//		this.catchrScreen = new CatchrScreen(this, true);
		this.loadingScreen = new LoadingScreen(this);
	}
}
