package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Enemy extends Entity implements iEntity{
	private EnemyAI ai;  
	private int health;
	private float shootingCD = 2f;
	private EntityManager em;
	
	// enemy constructor
	public Enemy(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y, EntityManager em) {
		super(damage, speed, texture, sb, x, y);
		ai = new EnemyAI(this);  // Create instance of EnemyAI class for ai movement
		this.health = health;
		this.em = em;
	}

	// getter function to return health
	public int getHealth(){
		return health;
	}
	
	@Override
	public void Move() {
		ai.AiMovement();  // calls AiMovement function from enemyAi
		collisionRect.attachRect(posX, posY);
	}

	@Override
	public void Shoot(int direction) {
		shootingCD -= Gdx.graphics.getDeltaTime();
		// if shootingcd is less than 0, enemy can shoot again
		if(shootingCD < 0)
		{
			// spawn bullet, and reset the shootingcd back to 2f
			em.SetBulletList(new Bullet(this.damage, 50, this.texture, this.batch, posX, posY));
			shootingCD = 2f;
		}
		/* 
		if(!em.GetBulletList().isEmpty())
		{
			for(Bullet bullet : em.GetBulletList())
			{
				bullet.Draw();
				bullet.UpdateBullet(direction);
			}
		}
		*/
	}

	@Override
	public void TakeDamage(int damage){
		health -= damage;
		System.out.println(health);
	}

}
