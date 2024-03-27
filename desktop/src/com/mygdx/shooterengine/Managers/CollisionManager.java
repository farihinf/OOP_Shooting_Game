package com.mygdx.shooterengine.Managers;

import java.util.Iterator;

import com.mygdx.shooterengine.PopUpText;
import com.mygdx.shooterengine.Entities.Bullet;
import com.mygdx.shooterengine.Entities.Enemy;
import com.mygdx.shooterengine.Entities.EntitySpriteBatchSingleton;
import com.mygdx.shooterengine.Entities.Pickup;
import com.mygdx.shooterengine.Enums.EnemyVar;
import com.mygdx.shooterengine.Enums.PickupType;

public class CollisionManager {
    
    private EntityManager em = EntityManager.getInstance();
    private static CollisionManager instance = null;

    public void CollisionChecker(){
		Iterator<Enemy> eIterator = em.getEnemyList().iterator();
		while (eIterator.hasNext()) {
			Enemy enemy = eIterator.next();
			enemy.Draw();
			enemy.Move();
			enemy.Shoot(-1);
			if (enemy.getHealth() <= 0) {
				em.getPlayer().SetScore(10);
				//spawn the drops
				em.SpawnPickup(enemy.getX(), enemy.getY());
				eIterator.remove(); // Remove the current enemy using the iterator
			}

			// Collision with player
			if (enemy.GetCollsionRect().CollidesWith(em.getPlayer().GetCollsionRect())) {
				em.getPlayer().TakeDamage(20);
				if(enemy.GetEnemyVar() == EnemyVar.CHASER){
					eIterator.remove();
				}
				System.out.println(enemy.GetCollsionRect() + "Hit" + em.getPlayer().GetCollsionRect());
			}

			if (!em.GetBulletList().isEmpty()) {
				Iterator<Bullet> enemyI = em.GetBulletList().iterator();
				// Bullet Collision with Enemy
				while (enemyI.hasNext()) {
					Bullet eBullet = enemyI.next();
					if (eBullet.OutOfBounds()) {
						enemyI.remove();
						System.out.println("Enemy Bullet removed due to Out of bounds");
					}

					if (eBullet.GetCollsionRect().CollidesWith(em.getPlayer().GetCollsionRect())) {
						//em.getPlayer().TakeDamage(eBullet.damage);
						enemyI.remove();
						System.out.println(
								eBullet.GetCollsionRect() + "Hit" + em.getPlayer().GetCollsionRect());
					}
				}
			}

			// Collision for player bullet
			if (!em.getPlayer().GetBulletList().isEmpty()) {
				Iterator<Bullet> iterator = em.getPlayer().GetBulletList().iterator();
				// Bullet Collision with Enemy
				while (iterator.hasNext()) {
					Bullet bullets = iterator.next();
					if (bullets.GetCollsionRect().CollidesWith(enemy.GetCollsionRect())) {
						enemy.TakeDamage(bullets.getDamage());
						iterator.remove(); // Remove the current bullet using the iterator
						System.out.println(bullets.GetCollsionRect() + "Hit" + enemy.GetCollsionRect());
					}

					if (bullets.OutOfBounds()) {
						iterator.remove(); // Remove the current bullet using the iterator
						System.out.println("Bullet removed due to Out of Bounds");
					}
				}
			}
		}

		Iterator<Pickup> pIterator = em.getPickupList().iterator();
		while (pIterator.hasNext()) {
			Pickup pickup = pIterator.next();
			if(pickup.GetCollsionRect().CollidesWith(em.getPlayer().GetCollsionRect()))
			{
				if(pickup.getPickupType() == PickupType.DAMAGE){		
					em.getPlayer().setDamage(10);
					PopUpText.getInstance().PopUp(EntitySpriteBatchSingleton.getInstance(), "+10 Damage!", 0, 1, 0, pickup.getX(), pickup.getY());
					pIterator.remove();
				}
				else if(pickup.getPickupType() == PickupType.HEALTH){
					em.getPlayer().setHealth(10);
					PopUpText.getInstance().PopUp(EntitySpriteBatchSingleton.getInstance(), "+10 Health!", 0, 1, 0, pickup.getX(), pickup.getY());
					pIterator.remove();
				}
				else if(pickup.getPickupType() == PickupType.SPEED){
					em.getPlayer().setSpeed(10f);
					PopUpText.getInstance().PopUp(EntitySpriteBatchSingleton.getInstance(), "+10 Speed!", 0, 1, 0, pickup.getX(), pickup.getY());
					pIterator.remove();
				}
			}
		}
	}

    public static CollisionManager GetInstance(){
        if(instance == null){
            instance = new CollisionManager();
        }
        return instance;
    }
    
}
