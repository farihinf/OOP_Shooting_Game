package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityManager {
	private Texture playerTexture;
	private Texture enemyTexture;
	private SpriteBatch batch;
	private Player player;
	private Enemy enemy;
	private PlayerControlManager playerMovement;

	EntityManager(SpriteBatch sb){
		batch = sb;
		playerTexture = new Texture(Gdx.files.internal("EntitySprites\\player.png"));
		enemyTexture = new Texture(Gdx.files.internal("EntitySprites\\enemy.png"));
	}
	
	public Player SpawnPlayer() {
		player = new Player(100, 10, 200f, playerTexture, batch, 300f, 100f);
		return player;
	}
	
	public Enemy SpawnEnemy() {
		enemy = new Enemy(100, 10, 200f, enemyTexture, batch, 300f, 400f);
		return enemy;
	}

	public PlayerControlManager PlayerControls() {
		playerMovement = new PlayerControlManager(100, 10, 200f, playerTexture, batch, 300f, 100f);
		return playerMovement;
	}
}
