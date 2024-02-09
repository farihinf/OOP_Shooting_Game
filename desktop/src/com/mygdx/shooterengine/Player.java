package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity implements iEntity{
	private int health;

	public Player(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
		this.health = health;
	}

	public int getHealth(){
		return health;
	}

	@Override
	public void Move() {
		float currX = getX();
		
		if(Gdx.input.isKeyPressed(Keys.LEFT))setX(currX -= speed * Gdx.graphics.getDeltaTime());
		if(Gdx.input.isKeyPressed(Keys.RIGHT))setX(currX += speed * Gdx.graphics.getDeltaTime());
	}
	@Override
	public void Shoot() {

	}

	@Override
	public void TakeDamage(){
		
	}
}
