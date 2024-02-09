package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;

public class EnemyAI {
	private float leftbound;
	private float rightbound;
	private float currTarget;
	private Enemy enemy;

	EnemyAI(float leftBound, float rightBound, Enemy e){
		leftbound = leftBound;
		rightbound = rightBound;
		currTarget =  leftbound;
		enemy = e;
	}
	public void AiMovement() {
		float currX = enemy.getX();
		
		if (currTarget == leftbound) {
			if (currX > currTarget) {
				enemy.setX(currX -= enemy.getSpeed() * Gdx.graphics.getDeltaTime());
		        //Gdx.app.log("MyGame", "Float value: " + currX);
			}
			else {
				currTarget = rightbound;
			}
		}else {
			if (currX < currTarget) {
				enemy.setX(currX += enemy.getSpeed()* Gdx.graphics.getDeltaTime());
			}
			else {
				currTarget = leftbound;
			}
		}
	}
}
