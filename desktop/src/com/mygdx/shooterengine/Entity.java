package com.mygdx.shooterengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/* Parent class for the entities to inherit from (player, bullet and enemy) */
public class Entity {
	protected int damage;
	protected Texture texture;
	protected float speed;
	protected SpriteBatch batch;
	protected float posX;
	protected float posY;
	protected CollisionRect collisionRect;
	
	Entity(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		this.damage = damage;
		this.texture = texture;
		this.speed = speed;
		this.batch = sb;
		this.posX = x;
		this.posY = y;
		collisionRect = new CollisionRect(x, y, texture.getWidth(), texture.getHeight());
	}
	
	// Getters and setters for x and y attribute
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
	// getters for speed and damage attribute
	public float getSpeed() {
		return speed;
	}
	public int getDamage(){
		return damage;
	}

	public void setSpeed(float speed){
		this.speed += speed;
	}

	public void setDamage(int damage){
		this.damage += damage;
	}
	
	// Function to draw the entity texture
	public void Draw() {
		batch.draw(texture, posX, posY, texture.getWidth(), texture.getHeight());
	}

	// Getter function to return collisionRect of entity
	public CollisionRect GetCollsionRect(){
		return collisionRect;
	}
}
