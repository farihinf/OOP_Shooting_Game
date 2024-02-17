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
	private PlayerMovementManager playerMovement;


	
	public void create() 
	{
		batch = new SpriteBatch();
        em = new EntityManager(batch);
        sm = new SceneManager(batch);
        
        sm.changeScene(new MainMenu(sm));
        sm.mainmenu.draw();
        
        // player = em.SpawnPlayer();
		for(int i = 0; i < em.totalEnemy; i++){
			em.SpawnEnemy();
		}

		//control movement of Player1
		playerMovement = em.PlayerControls();
		IOManager.setPlayerControl(playerMovement);
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
	        		if (em.enemyCount < em.totalEnemy){
						em.SpawnEnemy();
					}
					for(Enemy enemy : em.enemyList){
						enemy.Draw();
						enemy.Move();
					}
	        		// player.Move();
					IOManager.checkInput();
					playerMovement.Move(false, false, false, false);
					playerMovement.Draw();
	        		batch.end();
	        	}
	        }
	    }
	}
}
