package com.mygdx.shooterengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	private Texture playerTexture;  // hold player texture
	private Texture enemyTexture;   // hold enemy texture
	private SpriteBatch batch;
	private Player player;   // hold player instance
	private Enemy enemy;     //

	private List<Enemy> enemyList = new ArrayList<>();         // list of enemies on screen
	private ArrayList<Bullet> bulletList = new ArrayList<>();  // list of bullets shot from enemies on screen
	private int totalEnemy; // max amount of enemies 

	private Random random;

	EntityManager(SpriteBatch sb){
		batch = sb;
		random = new Random();
		playerTexture = new Texture(Gdx.files.internal("EntitySprites\\player.png"));
		enemyTexture = new Texture(Gdx.files.internal("EntitySprites\\enemy2.png"));
		totalEnemy = 5;
	}
	
	// Function to spawn player
	public void SpawnPlayer() {
		player = new Player(100, 50, 200f, playerTexture, batch, 300f, 100f);
	}

	// Function to spawn enemy	
	public void SpawnEnemy() {
		float speed = random.nextFloat() * (200f - 100f) + 100f;
		enemy = new Enemy(100, 10, speed, enemyTexture, batch, 300f, 400f, this);
		enemyList.add(enemy);
		//return enemy;
	}

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

	// Function to restart game by resetting the list of enemies, bullets and player position
	public void restartGame()
	{
		if (player != null) 
		{
			player.setX(300f);
			player.setY(100f);
			player.setHealth();
		}
		enemyList.clear();
		bulletList.clear();
	}

	public void dispose(){
		playerTexture.dispose();
		enemyTexture.dispose();
	}
}
