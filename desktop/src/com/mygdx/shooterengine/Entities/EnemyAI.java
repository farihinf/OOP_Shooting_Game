package com.mygdx.shooterengine.Entities;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.shooterengine.Managers.EntityManager;

/* EnemyAI manager that is attached to Enemy class 
 * this class is in charge of dictating the movement behaviour of the Enemy instance
*/
public class EnemyAI 
{
	private int currentPointIndex; // Current index in the 2d array of points
	private Enemy enemy;  // Enemy instance
	private float[][] points = {{50f, 400f}, {300f, 400f}, {600f, 400f}, {600f, 300f}, {300f, 300f}, {50f, 300f}}; // The coordinates of the points
    private Random random;

	EnemyAI(Enemy e){
		enemy = e;
        random = new Random();
		currentPointIndex = random.nextInt(points.length);
	}

    // Function responsible for enemy movement
	public void AiMovement() 
    {
		float deltaTime = Gdx.graphics.getDeltaTime();
		float[] currentPoint = points[currentPointIndex];

		float dx = currentPoint[0] - enemy.getX();
        float dy = currentPoint[1] - enemy.getY();
        float distance = (float)Math.sqrt(dx * dx + dy * dy); 

		if (distance > enemy.getSpeed() * deltaTime) {
            // Calculate movement vector
            float moveX = (dx / distance) * enemy.getSpeed() * deltaTime;
            float moveY = (dy / distance) * enemy.getSpeed() * deltaTime;
            
            // Move enemy towards point
            enemy.setX(enemy.getX() + moveX);
			enemy.setY(enemy.getY() + moveY);
        } else {
            // If close enough to current point, move to the next point
            /* 
            currentPointIndex++;
            if (currentPointIndex >= points.length) {
                currentPointIndex = 0; // Loop back to the beginning
            } */
            currentPointIndex = random.nextInt(points.length);
        }
	}

    public void TrackerMovement(){
        float dx = EntityManager.getInstance().getPlayer().getX() - enemy.getX();
        float dy = EntityManager.getInstance().getPlayer().getY() - enemy.getY();
        Vector2 direction = new Vector2(dx, dy).nor();

        // Move the enemy towards the player with tracking speed
        enemy.setX(enemy.getX() + direction.x * enemy.getSpeed() * Gdx.graphics.getDeltaTime());
        enemy.setY(enemy.getY() + direction.y * enemy.getSpeed() * Gdx.graphics.getDeltaTime());
    }
}
