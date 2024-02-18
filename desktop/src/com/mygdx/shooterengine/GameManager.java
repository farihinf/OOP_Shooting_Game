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
					if(player.getHealth() <= 0){
						//goodluck bryan :D
					}
					
					player.Shoot(1);
	        		player.Draw();
	        		player.Move();			
	        		if (em.getEnemyList().size() < em.getTotalEnemy() ){
						em.SpawnEnemy();
					}

					Iterator<Enemy> eIterator = em.getEnemyList().iterator();
					while (eIterator.hasNext()) {
						Enemy enemy = eIterator.next();
						enemy.Draw();
						enemy.Move();
						enemy.Shoot(-1);
						if (enemy.getHealth() <= 0) {
							eIterator.remove(); // Remove the current enemy using the iterator
						}

						//Collision with player
						if(enemy.GetCollsionRect().CollidesWith(player.GetCollsionRect())){
							player.TakeDamage(50);
							System.out.println(enemy.GetCollsionRect() + "Hit" + player.GetCollsionRect());
						}

						if(!em.GetBulletList().isEmpty()){
							Iterator<Bullet> enemyI = em.GetBulletList().iterator();
							//Bullet Collision with Enemy
							while (enemyI.hasNext()) {
								Bullet eBullet = enemyI.next();
								if(eBullet.OutOfBounds()){
									enemyI.remove();
									System.out.println("Enemy Bullet removed due to Out of bounds");
								}

								if(eBullet.GetCollsionRect().CollidesWith(player.GetCollsionRect())){
									player.TakeDamage(eBullet.damage);
									enemyI.remove();
									System.out.println(eBullet.GetCollsionRect() + "Hit" + player.GetCollsionRect());
								}
							}

						}
						
						//Collision for player bullet
						if (!player.GetBulletList().isEmpty()) {
							Iterator<Bullet> iterator = player.GetBulletList().iterator();
							//Bullet Collision with Enemy
							while (iterator.hasNext()) {
								Bullet bullets = iterator.next();
								if (bullets.GetCollsionRect().CollidesWith(enemy.GetCollsionRect())) {
									enemy.TakeDamage(bullets.getDamage());
									iterator.remove(); // Remove the current bullet using the iterator									
									System.out.println(bullets.GetCollsionRect() + "Hit" + enemy.GetCollsionRect());
								}

								if(bullets.OutOfBounds()){
									iterator.remove(); // Remove the current bullet using the iterator									
									System.out.println("Bullet removed due to Out of Bounds");
								}
							}
						}
						
					}

					
					// if(!em.getEnemyList().isEmpty()){
					// 	//Iterator<Enemy> eIterator = em.getEnemyList().iterator();
					// 	for(Enemy enemy : em.getEnemyList()){
					// 		enemy.Draw();
					// 		enemy.Move();
					// 		// while (eIterator.hasNext()) {
					// 		// 	Enemy enemyI = eIterator.next();
					// 		// 	if (enemyI.getHealth() <= 0) {
					// 		// 		eIterator.remove();
					// 		// 	}
				
							
					// 		// }
							
					// 		//Player Collision with Enemy
					// 		if(enemy.GetCollsionRect().CollidesWith(player.GetCollsionRect())){
					// 			System.out.println("Hit" + player.GetCollsionRect());
					// 		}
					// 		if (!player.GetBulletList().isEmpty()) {
					// 			Iterator<Bullet> iterator = player.GetBulletList().iterator();
					// 			//Bullet Collision with Enemy
					// 			while (iterator.hasNext()) {
					// 				Bullet bullets = iterator.next();
					// 				if (bullets.GetCollsionRect().CollidesWith(enemy.GetCollsionRect())) {
					// 					enemy.TakeDamage(bullets.getDamage());
										
					// 					iterator.remove(); // Remove the current bullet using the iterator									
					// 					System.out.println("Hit" + enemy.GetCollsionRect());
					// 				}
					// 			}
					// 		}
					// 	}
					// }
		
	        		batch.end();

					
	        	}
	        }
	    }
	}

	public void dispose(){
		batch.dispose();
		em.dispose();
	}
}
