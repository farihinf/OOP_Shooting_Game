package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//class might be redundant and can be removed? Shoot/Controls add in PlayerControlManager class ***
//ignore this class if required

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
		
	}
	@Override
	public void Shoot() {

	}

	@Override
	public void TakeDamage(){
		
	}
}
