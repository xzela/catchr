package org.doublelong.catchr;

import org.doublelong.catchr.screens.LoadingScreen;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

public class Catchr extends Game
{
	public final String WINDOW_TITLE = "Catchr - catching objects";
	public static final int WINDOW_HEIGHT = 600;
	public static final int WINDOW_WIDTH = 400;

	private LoadingScreen loadingScreen;

	// initializing the AssetManager
	public AssetManager manager = new AssetManager();

	@Override
	public void create()
	{
		// Prevents the: Texture width and height must be powers of two: 190x49 Exception
		Texture.setEnforcePotImages(false);
		this.initialize();
		this.setScreen(this.loadingScreen);
	}

	private void initialize()
	{
		this.loadingScreen = new LoadingScreen(this);
	}
}
