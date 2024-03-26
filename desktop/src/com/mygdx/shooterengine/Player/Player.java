package com.mygdx.shooterengine.Player;
import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.shooterengine.Entities.Bullet;
import com.mygdx.shooterengine.Entities.Entity;
import com.mygdx.shooterengine.Interfaces.iEntity;
import com.mygdx.shooterengine.Managers.AudioManager;
import com.mygdx.shooterengine.Managers.IOManager;

/* Player class that inherits from Entity and implements the iEntity interface. 
* Contains player functions and attributes
* Uses playermovementmanager to dictate movement
Unlike Enemy, player stores its own bullet list in its own class */
public class Player extends Entity implements iEntity{
	private int health;

	private PlayerMovementManager movement = new PlayerMovementManager();
	private ArrayList<Bullet> bulletList = new ArrayList<>();
	private Texture healthTexture;
	private float initialHealthBarWidth;
	private static float SHOOTINGCD = 0.8f;
	private int score;
	private BitmapFont textRender;

	private float shootingCD = 0;


	public Player(int health, int damage, float speed, Texture texture, SpriteBatch sb, float x, float y) {
		super(damage, speed, texture, sb, x, y);
		this.health = health;
		healthTexture = new Texture("EntitySprites\\HealthBar.png");
	
		initialHealthBarWidth = Gdx.graphics.getWidth();
		score = 0;
		textRender = new BitmapFont();
	}

	// getter function to return health
	public int getHealth(){
		return health;
	}

	public void setHealth()
	{
		health = 100;
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
		shootingCD -= Gdx.graphics.getDeltaTime();

		if (IOManager.GetInstance().isKeyPressed(Keys.SPACE) && shootingCD < 0) {
			//Bullet bullet = new Bullet(10, 10, this.texture, this.batch, 100, 100);
			shootingCD = SHOOTINGCD;
			bulletList.add(new Bullet(this.damage, 1000, this.texture, this.batch, posX + (texture.getWidth() / 2), posY));
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

	public void setHealth(int health){
		this.health += health;
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

	public void SetScore(int num){
		score += num;
	}

	@Override
	public void Draw(){
		batch.draw(texture, posX, posY, texture.getWidth(), texture.getHeight());

        float healthBarWidth = (float) health / 100 * initialHealthBarWidth;

        batch.draw(healthTexture, 0, 0, healthBarWidth, healthTexture.getHeight());

		textRender.draw(batch, "" + score, Gdx.graphics.getWidth() / 2f, healthTexture.getHeight() + 20);
	}
}
