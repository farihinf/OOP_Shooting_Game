package com.mygdx.shooterengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.softbody.btSoftColliders.CollideSDF_RS;

public class Enemy extends Entity implements iEntity{
	private EnemyAI ai;
	private int health;
	
	private CollisionRect collisionRect = null;

	public Enemy(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
		ai = new EnemyAI(this);
		this.health = health;
		collisionRect = new CollisionRect(this.posX, this.posY, this.texture.getWidth(), this.texture.getHeight());
	}

	public int getHealth(){
		return health;
	}
	
	@Override
	public void Move() {
		ai.AiMovement();
		collisionRect.attachRect(posX, posY);
	}

	@Override
	public void Shoot() {

	}

	@Override
	public void TakeDamage(){
		
	}

	public CollisionRect GetCollsionRect()
	{
		return collisionRect;
	}
}
