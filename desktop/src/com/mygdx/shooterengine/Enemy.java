package com.mygdx.shooterengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends Entity implements iEntity{
	private EnemyAI ai;
	private int health;
	public Enemy(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
		ai = new EnemyAI(50f, 600f, this);
		this.health = health;
	}

	public int getHealth(){
		return health;
	}
	
	@Override
	public void Move() {
		ai.AiMovement();
	}

	@Override
	public void Shoot() {

	}

	@Override
	public void TakeDamage(){
		
	}
}
