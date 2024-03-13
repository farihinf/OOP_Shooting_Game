package com.mygdx.shooterengine;

import java.util.Iterator;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Class in charge of the overall game flow, the managing of objects such as the managers, entities and scenes */
public class GameManager extends Game {
	private static GameManager gmInstance;
	private SpriteBatch batch;
	private GameScene gamescreen;
	private ShipSelection ShipSelection;
	private EntityManager em;
	private SceneManager sm;
	private float spawnRate;
	private static float SPAWNRATE = 0.5f;

	public static GameManager getInstance(){
		if (gmInstance == null){
			gmInstance = new GameManager();
		}
		return gmInstance;
	}

	public void create() {
		// Initialize the entity, scene manager and sprite batch.
		gmInstance = GameManager.getInstance();
		batch = SpriteBatchSingleton.getInstance();
		em = EntityManager.getInstance();
		sm = new SceneManager();
		// set screen to main menu
		sm.changeScene(new MainMenu(sm));
		sm.mainMenu.draw();
		//set screen to selection
		// sm.changeScene(new MainMenu(sm));
		// sm.mainMenu.draw();
		// spawn player
		spawnRate = 0f;
	}

	public void render() {
		Screen currentScreen = sm.getScreen();
		if (currentScreen != null) {
			if (currentScreen instanceof MainMenu) {
				((MainMenu) currentScreen).initialise();

			} else if (currentScreen instanceof ShipSelection) {
                ((ShipSelection) currentScreen).initialise();

			} else if (currentScreen instanceof EndScene) {
				((EndScene) currentScreen).initialise();
			} else {
				gamescreen = (GameScene) currentScreen;
				gamescreen.initialise();

				if (!gamescreen.isPaused()) {
					batch.begin();

					em.getPlayer().Shoot(1);
					em.getPlayer().Draw();
					em.getPlayer().Move();
					spawnRate -= Gdx.graphics.getDeltaTime();
					if (em.getEnemyList().size() == 0 && spawnRate < 0) {
						em.spawnNextWave(); // Call spawnNextWave() only once
						spawnRate = SPAWNRATE;
					}
					/* 
					if (em.getEnemyList().size() == 0 && spawnRate < 0) {
						for (int i = 0; i < em.getTotalEnemy(); i++){
							em.SpawnEnemy();
						}
						spawnRate = SPAWNRATE;
					}
					*/
					
					enemyColChecker();
					// If player HP reaches <0, change to EndScene
					if (em.getPlayer().getHealth() <= 0) 
					{
						sm.changeScene(new EndScene(sm));
					}

					batch.end();
				}
			}
		}
	}
	public void showSelectionScreen() {
        ShipSelection = new ShipSelection(sm);
        sm.changeScene(ShipSelection);
    }
	public void enemyColChecker(){
		Iterator<Enemy> eIterator = em.getEnemyList().iterator();
		while (eIterator.hasNext()) {
			Enemy enemy = eIterator.next();
			enemy.Draw();
			enemy.Move();
			enemy.Shoot(-1);
			em.DrawBullet(-1);
			if (enemy.getHealth() <= 0) {
				eIterator.remove(); // Remove the current enemy using the iterator
			}

			// Collision with player
			if (enemy.GetCollsionRect().CollidesWith(em.getPlayer().GetCollsionRect())) {
				em.getPlayer().TakeDamage(50);
				System.out.println(enemy.GetCollsionRect() + "Hit" + em.getPlayer().GetCollsionRect());
			}

			if (!em.GetBulletList().isEmpty()) {
				Iterator<Bullet> enemyI = em.GetBulletList().iterator();
				// Bullet Collision with Enemy
				while (enemyI.hasNext()) {
					Bullet eBullet = enemyI.next();
					if (eBullet.OutOfBounds()) {
						enemyI.remove();
						System.out.println("Enemy Bullet removed due to Out of bounds");
					}

					if (eBullet.GetCollsionRect().CollidesWith(em.getPlayer().GetCollsionRect())) {
						em.getPlayer().TakeDamage(eBullet.damage);
						enemyI.remove();
						System.out.println(
								eBullet.GetCollsionRect() + "Hit" + em.getPlayer().GetCollsionRect());
					}
				}
			}

			// Collision for player bullet
			if (!em.getPlayer().GetBulletList().isEmpty()) {
				Iterator<Bullet> iterator = em.getPlayer().GetBulletList().iterator();
				// Bullet Collision with Enemy
				while (iterator.hasNext()) {
					Bullet bullets = iterator.next();
					if (bullets.GetCollsionRect().CollidesWith(enemy.GetCollsionRect())) {
						enemy.TakeDamage(bullets.getDamage());
						iterator.remove(); // Remove the current bullet using the iterator
						System.out.println(bullets.GetCollsionRect() + "Hit" + enemy.GetCollsionRect());
					}

					if (bullets.OutOfBounds()) {
						iterator.remove(); // Remove the current bullet using the iterator
						System.out.println("Bullet removed due to Out of Bounds");
					}
				}
			}
		}
	}

	// Restart the game
	public void restart() 
	{
		em.restartGame();
	}

	public void dispose()
	 {
		batch.dispose();
		sm.dispose();
	}
}
