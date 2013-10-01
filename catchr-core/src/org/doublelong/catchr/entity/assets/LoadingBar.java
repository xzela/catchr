package org.doublelong.catchr.entity.assets;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class LoadingBar extends Actor
{
	private Animation animation;
	private TextureRegion region;
	private float stateTime;

	public LoadingBar(Animation animation)
	{
		this.animation = animation;
		this.region = animation.getKeyFrame(0);
	}

	@Override
	public void act(float delta)
	{
		this.stateTime += delta;
		this.region = animation.getKeyFrame(this.stateTime);
	}

	@Override
	public void draw(SpriteBatch batch, float parentAlpha)
	{
		// Actor.getX()
		// Actor.getY()
		batch.draw(this.region, this.getX(), this.getY());
	}
}
