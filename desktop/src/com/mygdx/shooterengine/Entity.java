package com.mygdx.shooterengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Entity {
	protected int damage;
	protected Texture texture;
	protected float speed;
	protected SpriteBatch batch;
	protected float posX;
	protected float posY;
	
	public Entity(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		this.damage = damage;
		this.texture = texture;
		this.speed = speed;
		this.batch = sb;
		this.posX = x;
		this.posY = y;
	}
	
	public float getX() {
		return posX;
	}
	
	public float getY() {
		return posY;
	}
	
	public void setX(float x) {
		posX = x;
	}
	
	public void setY(float y) {
		posY = y;
	}
	public float getSpeed() {
		return speed;
	}

	public void Draw() {
		batch.draw(texture, posX, posY, texture.getWidth(), texture.getHeight());
	}
}
