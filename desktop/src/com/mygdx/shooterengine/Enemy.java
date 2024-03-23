package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Enemy class that inherits from Entity and implements iEntity interface
 * Contain's enemy functions and attributes
 * enemy's movement is dictated by the EnemyAI class
 */
public class Enemy extends Entity implements iEntity{
	private EnemyAI ai;  
	private int health;
	private float shootingCD = 2f;
	private EntityManager em;
	private EnemyVar enemyVar;
	
	// enemy constructor
	public Enemy(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y, EnemyVar enemyVar) {
		super(damage, speed, texture, sb, x, y);
		ai = new EnemyAI(this);  // Create instance of EnemyAI class for ai movement
		this.health = health;
		this.em = EntityManager.getInstance();
		this.enemyVar = enemyVar;
	}

	// getter function to return health
	public int getHealth(){
		return health;
	}
	
	@Override
	public void Move() {
		if(enemyVar == EnemyVar.SHOOTER){
			ai.AiMovement();  // calls AiMovement function from enemyAi
		}
		else if(enemyVar == EnemyVar.CHASER){
			ai.TrackerMovement();
		}
		
		collisionRect.attachRect(posX, posY);
	}

	@Override
	public void Shoot(int direction) {
		if (enemyVar == EnemyVar.SHOOTER) {
			shootingCD -= Gdx.graphics.getDeltaTime();
			// if shootingcd is less than 0, enemy can shoot again
			if(shootingCD < 0)
			{
				// spawn bullet, and reset the shootingcd back to 2f
				em.SetBulletList(new Bullet(this.damage, 300, this.texture, this.batch, posX, posY));
				shootingCD = 2f;
			}
		}		
	}

	@Override
	public void TakeDamage(int damage){
		health -= damage;
		System.out.println(health);
	}

	public EnemyVar GetEnemyVar(){
		return enemyVar;
	}

}

enum EnemyVar{
	CHASER, SHOOTER
}
