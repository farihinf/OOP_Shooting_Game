package com.mygdx.shooterengine;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

//class might be redundant and can be removed? Shoot/Controls add in PlayerControlManager class ***
//ignore this class if required

public class Player extends Entity implements iEntity{
	private int health;

	private PlayerMovementManager movement = new PlayerMovementManager();
	private IOManager ioManager = new IOManager();
	private ArrayList<Bullet> bulletList = new ArrayList<>();


	public Player(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
		this.health = health;
	}

	public int getHealth(){
		return health;
	}

	@Override
	public void Move() {
		movement.Move(this);
	}

	@Override
	public void Shoot() {
		if (Gdx.input.isKeyJustPressed(Keys.SPACE)) {
			//Bullet bullet = new Bullet(10, 10, this.texture, this.batch, 100, 100);
			bulletList.add(new Bullet(10, 1000, this.texture, this.batch, posX, posY));
		}

		if(!bulletList.isEmpty())
		{
			for(Bullet bullet : bulletList)
			{
				bullet.Draw();
				bullet.UpdateBullet();
			}
		}
	
	}

	@Override
	public void TakeDamage(){
		
	}

	public ArrayList<Bullet> GetBulletList()
	{
		return bulletList;	
	}
}
