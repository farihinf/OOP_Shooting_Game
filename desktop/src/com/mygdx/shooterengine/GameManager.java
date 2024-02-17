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

	
	public void create() 
	{
		batch = new SpriteBatch();
        em = new EntityManager(batch);
        sm = new SceneManager(batch);
        
        sm.changeScene(new MainMenu(sm));
        sm.mainmenu.draw();
        
        player = em.SpawnPlayer();

		for(int i = 0; i < em.getTotalEnemy(); i++){
			em.SpawnEnemy();
		}

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
	        		player.Move();			
	        		if (em.getEnemyCount() < em.getTotalEnemy() ){
						em.SpawnEnemy();
					}
					for(Enemy enemy : em.getEnemyList()){
						enemy.Draw();
						enemy.Move();
					}
	        		batch.end();
					if (!player.GetBulletList().isEmpty()) {
						Iterator<Bullet> iterator = player.GetBulletList().iterator();
						while (iterator.hasNext()) {
							Bullet bullets = iterator.next();
							for(Enemy enemy : em.getEnemyList())
							{
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

	public void dispose(){
		batch.dispose();
		em.dispose();
	}
}
