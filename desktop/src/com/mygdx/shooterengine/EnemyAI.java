package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;

public class EnemyAI 
{

	private int currentPointIndex; // Current index in the 2d array of points
	private Enemy enemy;  // Enemy instance
	private float[][] points = {{50f, 400f}, {300f, 400f}, {600f, 400f}, {600f, 300f}, {300f, 300f}, {50f, 300f}}; // The coordinates of the points

	EnemyAI(Enemy e){
		enemy = e;
		currentPointIndex = 0;
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
            currentPointIndex++;
            if (currentPointIndex >= points.length) {
                currentPointIndex = 0; // Loop back to the beginning
            }
        }
	}
}
