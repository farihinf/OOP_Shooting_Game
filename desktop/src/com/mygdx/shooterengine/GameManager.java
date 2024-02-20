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
	private boolean gameOver = false;

	public void create()
	{
		batch = new SpriteBatch();
		em = new EntityManager(batch);
		sm = new SceneManager(batch, this);

		sm.changeScene(new MainMenu(sm));
		em.SpawnPlayer();
	}

	public void render() 
	{
		Screen currentScreen = sm.getScreen();
		if (currentScreen != null) {
			if (currentScreen instanceof MainMenu) 
			{
				((MainMenu) currentScreen).initialise();
			} 
			else if (currentScreen instanceof EndScene) 
			{
                ((EndScene) currentScreen).initialise();
			} 
			else 
			{
				gamescreen = (GameScreen) currentScreen;
				gamescreen.initialise();

				if (!gamescreen.isPaused()) 
				{
					batch.begin();

					em.getPlayer().Shoot(1);
					em.getPlayer().Draw();
					em.getPlayer().Move();

					if (em.getEnemyList().size() < em.getTotalEnemy()) 
					{
						em.SpawnEnemy();
					}

					Iterator<Enemy> eIterator = em.getEnemyList().iterator();
					while (eIterator.hasNext()) 
					{
						Enemy enemy = eIterator.next();
						enemy.Draw();
						enemy.Move();
						enemy.Shoot(-1);
						if (enemy.getHealth() <= 0) 
						{
							eIterator.remove(); // Remove the current enemy using the iterator
						}

						// Collision with player
						if (enemy.GetCollsionRect().CollidesWith(em.getPlayer().GetCollsionRect())) 
						{
							em.getPlayer().TakeDamage(50);
							System.out.println(enemy.GetCollsionRect() + "Hit" + em.getPlayer().GetCollsionRect());
						}

						if (!em.GetBulletList().isEmpty()) 
						{
							Iterator<Bullet> enemyI = em.GetBulletList().iterator();
							// Bullet Collision with Enemy
							while (enemyI.hasNext()) 
							{
								Bullet eBullet = enemyI.next();
								if (eBullet.OutOfBounds()) 
								{
									enemyI.remove();
									System.out.println("Enemy Bullet removed due to Out of bounds");
								}

								if (eBullet.GetCollsionRect().CollidesWith(em.getPlayer().GetCollsionRect())) 
								{
									em.getPlayer().TakeDamage(eBullet.damage);
									enemyI.remove();
									System.out.println(eBullet.GetCollsionRect() + "Hit" + em.getPlayer().GetCollsionRect());
								}
							}
						}

						// Collision for player bullet
						if (!em.getPlayer().GetBulletList().isEmpty()) 
						{
							Iterator<Bullet> iterator = em.getPlayer().GetBulletList().iterator();
							// Bullet Collision with Enemy
							while (iterator.hasNext()) {
								Bullet bullets = iterator.next();
								if (bullets.GetCollsionRect().CollidesWith(enemy.GetCollsionRect())) 
								{
									enemy.TakeDamage(bullets.getDamage());
									iterator.remove(); // Remove the current bullet using the iterator
									System.out.println(bullets.GetCollsionRect() + "Hit" + enemy.GetCollsionRect());
								}

								if (bullets.OutOfBounds()) 
								{
									iterator.remove(); // Remove the current bullet using the iterator
									System.out.println("Bullet removed due to Out of Bounds");
								}
							}
						}
					}

					if (em.getPlayer().getHealth() <= 0) 
					{
						gameOver = true;
					}

					batch.end();
				}
			}
		}

		if (gameOver) 
		{
			if (!(sm.getScreen() instanceof EndScene)) 
			{
            	sm.changeScene(new EndScene(sm));
        	}
		}
	}

	public void restart() 
	{
		em.restartGame();
	}

	public void dispose() 
	{
		batch.dispose();
		sm.dispose();
		em.dispose();
	}
}
