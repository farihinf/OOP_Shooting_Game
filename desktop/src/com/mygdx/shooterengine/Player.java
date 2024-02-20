package com.mygdx.shooterengine;
import java.util.ArrayList;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Player extends Entity implements iEntity{
	private int health;

	private PlayerMovementManager movement = new PlayerMovementManager();
	private ArrayList<Bullet> bulletList = new ArrayList<>();


	Player(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
		this.health = health;
	}

	// getter function to return health
	public int getHealth(){
		return health;
	}

	// function to move player, using playercontrolmaanger
	@Override
	public void Move() {
		movement.Move(this);
		this.collisionRect.attachRect(posX, posY);
	}

	// function to shoot
	@Override
	public void Shoot(int direction) {
		if (IOManager.GetInstance().isKeyJustPressed(Keys.SPACE)) {
			//Bullet bullet = new Bullet(10, 10, this.texture, this.batch, 100, 100);
			bulletList.add(new Bullet(this.damage, 1000, this.texture, this.batch, posX, posY));
			AudioManager.GetInstance().PlaySound("Audio\\Shoot.mp3");
		}

		if(!bulletList.isEmpty())
		{
			for(Bullet bullet : bulletList)
			{
				bullet.Draw();
				bullet.UpdateBullet(direction);
			}
		}
	
	}

	// function to reduce health 
	@Override
	public void TakeDamage(int damage){
		health -= damage;
		System.out.println("Health: " + health);
	}

	// getter function to return bullet list
	public ArrayList<Bullet> GetBulletList()
	{
		return bulletList;	
	}
	
	// getter function to return texture of player
	public Texture GetTexture(){
		return texture;
	}
}
