package com.mygdx.shooterengine;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager extends Game
{
	private SpriteBatch batch;
	private GameScreen gamescreen;
	private EntityManager em;
	private SceneManager sm;
	private Player player;
	private Enemy enemy;
	//private PlayerMovementManager playerMovement;

	
	public void create() 
	{
		batch = new SpriteBatch();
        em = new EntityManager(batch);
        sm = new SceneManager(batch);
        
        sm.changeScene(new MainMenu(sm));
        sm.mainmenu.draw();
        
        player = em.SpawnPlayer();
        enemy = em.SpawnEnemy();

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
					player.Shoot();
	        		player.Draw();
	        		enemy.Draw();
	        		enemy.Move();
	        		player.Move();			
	        		batch.end();
					if (!player.GetBulletList().isEmpty()) {
						Iterator<Bullet> iterator = player.GetBulletList().iterator();
						while (iterator.hasNext()) {
							Bullet bullets = iterator.next();
							if (bullets.GetCollsionRect().CollidesWith(enemy.GetCollsionRect())) {
								iterator.remove(); // Remove the current bullet using the iterator
								System.out.println("Hit" + enemy.GetCollsionRect());
							}
						}
					}
					
	        	}
	        }
	    }
	}
}
