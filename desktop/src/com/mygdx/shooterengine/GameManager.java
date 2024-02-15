package com.mygdx.shooterengine;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager extends Game
{
	private SpriteBatch batch;
	private GameScreen gamescreen;
	private EntityManager em;
	private SceneManager sm;
	// private Player player;
	private Enemy enemy;
	private PlayerControlManager playerMovement;

	
	public void create() 
	{
		batch = new SpriteBatch();
        em = new EntityManager(batch);
        sm = new SceneManager(batch);
        
        sm.changeScene(new MainMenu(sm));
        sm.mainmenu.draw();
        
        // player = em.SpawnPlayer();
        enemy = em.SpawnEnemy();

		//control movement of Player1
		playerMovement = em.PlayerControls();
		InputHandler.setPlayerControl(playerMovement);
	}
	
	public void render() 
	{
	    Screen currentScreen = sm.getScreen();
	    if (currentScreen != null) 
	    {
	        if (currentScreen instanceof MainMenu) 
	        {
	            ((MainMenu) currentScreen).initialise();
	        } 
	        else 
	        {
	        	gamescreen = (GameScreen) currentScreen;
	        	
	        	gamescreen.initialise();
	        	
	        	if(!gamescreen.isPaused()) 
	        	{
	        		batch.begin();
	        		// player.Draw();
	        		enemy.Draw();
	        		enemy.Move();
	        		// player.Move();
					InputHandler.checkInput();
					playerMovement.Move(false, false, false, false);
					playerMovement.Draw();
	        		batch.end();
	        	}
	        }
	    }
	}
}
