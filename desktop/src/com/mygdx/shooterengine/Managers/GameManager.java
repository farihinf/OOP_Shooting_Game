package com.mygdx.shooterengine.Managers;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.shooterengine.PopUpText;
import com.mygdx.shooterengine.Entities.EntitySpriteBatchSingleton;
import com.mygdx.shooterengine.Scene.EndScene;
import com.mygdx.shooterengine.Scene.GameScene;
import com.mygdx.shooterengine.Scene.MainMenu;
import com.mygdx.shooterengine.Scene.ShipSelection;

/* Class in charge of the overall game flow, the managing of objects such as the managers, entities and scenes */
public class GameManager extends Game 
{
	private static GameManager gmInstance;
	private SpriteBatch batch;
	private GameScene gamescreen;
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
		batch = EntitySpriteBatchSingleton.getInstance();
		em = EntityManager.getInstance();
		sm = SceneManager.GetInstance();
		// set screen to main menu
		sm.changeScene(new MainMenu(sm));
		sm.mainMenu.draw();
		spawnRate = 0f;
	}

	public void render() {
		Screen currentScreen = sm.getScreen();
		if (currentScreen != null) {
			if (currentScreen instanceof MainMenu) {
				((MainMenu) currentScreen).initialise();

			} else if (currentScreen instanceof ShipSelection) 
			{
                ((ShipSelection) currentScreen).initialise();

			} 
			else if (currentScreen instanceof EndScene) {
				((EndScene) currentScreen).initialise();
			} else {
				gamescreen = (GameScene) currentScreen;
				gamescreen.initialise();

				if (!gamescreen.isPaused()) {
					batch.begin();
					em.setGameTime(Gdx.graphics.getDeltaTime());

					em.getPlayer().Shoot(1);
					em.getPlayer().Draw();
					em.getPlayer().Move();
					em.DrawBullet(-1);
					em.DrawPickups();
					PopUpText.getInstance().DrawPopUpText();
					spawnRate -= Gdx.graphics.getDeltaTime();
					if (em.getEnemyList().size() == 0 && spawnRate < 0) {
						em.spawnNextWave(); // Call spawnNextWave() only once
						spawnRate = SPAWNRATE;
					}

					CollisionManager.GetInstance().CollisionChecker();
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
