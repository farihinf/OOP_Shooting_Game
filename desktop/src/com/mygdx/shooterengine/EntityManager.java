package com.mygdx.shooterengine;

import java.util.ArrayList;
import java.util.List;
/*
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch; 
*/


/* Entity Manager is in charge of managing entities, from spawning, drawing and rendering. It is in charge of resetting the entity states when game restarts
 * as well as keeping track of enemies and bullets shot from enemies in a list to keep check for collission in game manager.
 */
public class EntityManager {
	private static EntityManager emInstance;
	private Player player;   // hold player instance
	// private Enemy enemy;     
	private EntityFactory ef;
	private EnemySpawnPattern enemySpawner;
	private int playerTextureIndex;

	private List<Enemy> enemyList = new ArrayList<>();         // list of enemies on screen
	private ArrayList<Bullet> bulletList = new ArrayList<>();  // list of bullets shot from enemies on screen
	private int totalEnemy; // max amount of enemies 

	private EntityManager(){
		ef = new EntityFactory();
		enemySpawner = new EnemySpawnPattern();
		totalEnemy = 5;
	}

	public static EntityManager getInstance(){
		if (emInstance == null){
			emInstance = new EntityManager();
		}
		return emInstance;
	}
	
	// Function to spawn player
	public void SpawnPlayer() {
		player = ef.createPlayer(playerTextureIndex);
	}

    public void spawnNextWave() {
        List<EnemyConfig> waveConfigs = enemySpawner.getWaveConfigs();
        for (EnemyConfig config : waveConfigs) {
            for (int i = 0; i < config.getQuantity(); i++) {
                Enemy enemy = null;
                switch (config.getType()) {
                    case SMALL:
                        enemy = ef.createSmall();
                        break;
                    case NORMAL:
                        enemy = ef.createStandard();
                        break;
                    case BIG:
                        enemy = ef.createBig();
                        break;
                    // Add more cases for other enemy types as needed
                }
                if (enemy != null) {
                    enemyList.add(enemy);
                }
            }
        }
    }

	/* 
	// Function to spawn enemy	
	public void SpawnEnemy() {
		enemy = ef.createStandard();
		enemyList.add(enemy);
	}
*/
	// Function to draw the bullets
	public void DrawBullet(int direction){
		if(!bulletList.isEmpty())
		{
			for(Bullet bullet : GetBulletList())
			{
				bullet.Draw();
				bullet.UpdateBullet(direction);
			}
		}
	}
	
	// Function to add bullet to list
	public void SetBulletList(Bullet bullet){
		bulletList.add(bullet);
	}

	// Getter function to return bulletlist
	public ArrayList<Bullet> GetBulletList(){
		return bulletList;
	}

	// Getter function to return total enemy
	public int getTotalEnemy(){
		return totalEnemy;
	}

	// Getter function to return enemy list
	public List<Enemy> getEnemyList(){
		return enemyList;
	}

	// Getter function to return player
	public Player getPlayer(){
		return player;
	}

	public void setTextureIndex(int index){
		System.out.println("called");
		playerTextureIndex = index;
	}

	// Function to restart game by resetting the list of enemies, bullets and player position
	public void restartGame()
	{
		if (player != null){
			player.GetTexture().dispose();
		}
		SpawnPlayer();
		enemyList.clear();
		bulletList.clear();
	}
}
