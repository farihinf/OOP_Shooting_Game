package com.mygdx.shooterengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	private Texture playerTexture;
	private Texture enemyTexture;
	private SpriteBatch batch;
	private Player player;
	private Enemy enemy;

	public List<Enemy> enemyList = new ArrayList<>();
	public int totalEnemy;
	public int enemyCount;

	private Random random;

	private PlayerMovementManager playerMovement;

	EntityManager(SpriteBatch sb){
		batch = sb;
		random = new Random();
		playerTexture = new Texture(Gdx.files.internal("EntitySprites\\player.png"));
		enemyTexture = new Texture(Gdx.files.internal("EntitySprites\\enemy.png"));
		totalEnemy = 5;
		enemyCount = 0;
	}
	
	public Player SpawnPlayer() {
		player = new Player(100, 10, 200f, playerTexture, batch, 300f, 100f);
		return player;
	}
	
	public Enemy SpawnEnemy() {
		float speed = random.nextFloat() * (200f - 100f) + 100f;
		enemy = new Enemy(100, 10, speed, enemyTexture, batch, 300f, 400f);
		enemyList.add(enemy);
		enemyCount++;
		return enemy;
	}

	//public Bullet SpawnBullet(p)
}
