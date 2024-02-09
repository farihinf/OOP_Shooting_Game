package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameScreen extends SceneManager implements Screen 
{
	//Screen
	private Camera camera;
	private Viewport viewport;
	
	//Graphics
	private SpriteBatch batch;
	private Texture background;
    private Texture overlayTexture;
	
	//Timing
	private int backgroundOffSet;
	private boolean pause = false;
	
	//World Parameters (Portrait Mode)
	private final int WORLD_WIDTH = 72;
	private final int WORLD_HEIGHT = 128;
    private float scale = 0.8f;
	
	GameScreen(SpriteBatch b)
	{
		camera = new OrthographicCamera();
		viewport = new StretchViewport(WORLD_WIDTH,WORLD_HEIGHT, camera);
		
		batch = b;
		
		background = new Texture("ScreenImages\\SpaceShooter_Background.png");
		backgroundOffSet = 0;
		
		overlayTexture = new Texture("ScreenImages\\PauseBackGround.jpg");
		
	}

	@Override
	public void render(float delta) 
	{
		batch.begin();
		
		if (!isPaused()) 
		{
            backgroundOffSet++;
            if (backgroundOffSet % WORLD_HEIGHT == 0) 
            {
                backgroundOffSet = 0;
            }
        }
		
		batch.draw(background, 0, backgroundOffSet - WORLD_HEIGHT, WORLD_WIDTH, WORLD_HEIGHT);
	    batch.draw(background, 0, backgroundOffSet, WORLD_WIDTH, WORLD_HEIGHT);
	    
	    if (isPaused()) 
	    {
            float scaledPauseBackgroundWidth = WORLD_WIDTH * scale;
            float scaledPauseBackgroundHeight = WORLD_HEIGHT * scale;
            batch.draw(overlayTexture, (WORLD_WIDTH - scaledPauseBackgroundWidth) / 2, (WORLD_HEIGHT - scaledPauseBackgroundHeight) / 2, scaledPauseBackgroundWidth, scaledPauseBackgroundHeight);
        }
	    
	    batch.end();
	}

	@Override
	public void resize(int width, int height) 
	{
		viewport.update(width, height, true);
		batch.setProjectionMatrix(viewport.getCamera().combined);
	}

	@Override
	public void pause() 
	{
		pause = true;	
	}

	@Override
	public void resume() 
	{
		pause = false;
	}
	
	private boolean isPaused() 
	{
        return pause;
    }
	
	 private void togglePause() 
	 {
		 if (isPaused()) 
	     {
	         resume();
	     } 
		 else 
		 {
	         pause();
	     }
	 }
	 
	@Override
	public void show() 
	{
		Gdx.input.setInputProcessor(new InputAdapter() 
		{
            @Override
            public boolean keyDown(int key) 
            {
                if (key == Input.Keys.ESCAPE) 
                {
                    togglePause();
                }
                return true;
            }
        });
	}

	@Override
	public void hide() 
	{
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() 
	{
		batch.dispose();
	    background.dispose();
	    overlayTexture.dispose();
	}


}
