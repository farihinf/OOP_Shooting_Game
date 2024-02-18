package com.mygdx.shooterengine;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.bullet.softbody.btSoftColliders.CollideSDF_RS;

public class Entity {
	protected int damage;
	protected Texture texture;
	protected float speed;
	protected SpriteBatch batch;
	protected float posX;
	protected float posY;
	protected CollisionRect collisionRect;
	
	public Entity(int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		this.damage = damage;
		this.texture = texture;
		this.speed = speed;
		this.batch = sb;
		this.posX = x;
		this.posY = y;
		collisionRect = new CollisionRect(x, y, texture.getWidth(), texture.getHeight());
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
	public int getDamage(){
		return damage;
	}
	

	public void Draw() {
		batch.draw(texture, posX, posY, texture.getWidth(), texture.getHeight());
	}

	public CollisionRect GetCollsionRect(){
		return collisionRect;
	}
}
