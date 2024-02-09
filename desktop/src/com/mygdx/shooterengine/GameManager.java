package com.mygdx.shooterengine;

import com.badlogic.gdx.Game;

//import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameManager extends Game{
	
	private SpriteBatch batch;
	private EntityManager em;
	private Player player;
	private Enemy enemy;
	private SceneManager sm;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		em = new EntityManager(batch);

		player = em.SpawnPlayer();
		enemy = em.SpawnEnemy();
	}
	
	@Override
	public void render() {
		ScreenUtils.clear(0, 0, 0.2f, 1);
		enemy.Move();
		player.Move();
		batch.begin();
			player.Draw();
			enemy.Draw();
		batch.end();
	}
}
