package com.mygdx.shooterengine.Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.shooterengine.Entities.Enemy;
import com.mygdx.shooterengine.Entities.EntitySpriteBatchSingleton;
import com.mygdx.shooterengine.Entities.Pickup;
import com.mygdx.shooterengine.Enums.EnemyVar;
import com.mygdx.shooterengine.Enums.PickupType;
import com.mygdx.shooterengine.Player.Player;

import java.util.Random;

public class EntityFactory {
    private Texture[] playerTexture;  // hold player texture
    private Texture[] enemyTexture;   // hold enemy texture
    private Texture[] pickupTexture;
    private SpriteBatch batch;
    private Random random = new Random();

    public EntityFactory(){
        playerTexture = new Texture[]{
            new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_blue.png")),
            new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_red.png")),
            new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_grey.png")),
        };

        enemyTexture = new Texture[]{
            new Texture(Gdx.files.internal("EntitySprites\\enemy.png")),
            new Texture(Gdx.files.internal("EntitySprites\\enemy2.png")),
        };

        pickupTexture = new Texture[]{
            new Texture(Gdx.files.internal("EntitySprites\\enemy.png")),
            new Texture(Gdx.files.internal("EntitySprites\\enemy2.png")),
            new Texture(Gdx.files.internal("EntitySprites\\Bullet2.png"))
        };

        batch = EntitySpriteBatchSingleton.getInstance();
    }

    public Enemy createStandard(){
        float whichEnemy = random.nextInt(4);
        if (whichEnemy == 0) {
            return new Enemy(100, 10, 100, enemyTexture[0], batch, 300f, 400f, EnemyVar.CHASER);
        }
        else{
            return new Enemy(100, 10, 200, enemyTexture[0], batch, 300f, 400f, EnemyVar.SHOOTER);
        }
    }
    public Enemy createBig(){
        float whichEnemy = random.nextInt(4);
        if (whichEnemy == 0) {
            return new Enemy(100, 10, 100, enemyTexture[1], batch, 300f, 400f, EnemyVar.CHASER);
        }
        else{
            return new Enemy(100, 10, 200, enemyTexture[1], batch, 300f, 400f, EnemyVar.SHOOTER);
        }
    }
    public Enemy createSmall(){
        float whichEnemy = random.nextInt(4);
        if (whichEnemy == 0) {
            return new Enemy(100, 10, 100, enemyTexture[1], batch, 300f, 400f, EnemyVar.CHASER);
        }
        else{
            return new Enemy(100, 10, 200, enemyTexture[1], batch, 300f, 400f, EnemyVar.SHOOTER);
        }
    }
    public Player createPlayer(int textureIndex){
        int health;
        int damage;
        float speed;
        switch (textureIndex){
            case 0:
                health = 100;
                damage = 50;
                speed = 200f;
                break;
            case 1:
                health = 200;
                damage = 50;
                speed = 150f;
                break;
            case 2:
                health = 75;
                damage = 20;
                speed = 300f;
                break;
            default:
                throw new IllegalArgumentException("Invalid texture index: " + textureIndex);    
        }
        return new Player(health, damage, speed, playerTexture[textureIndex], batch, 300f, 100f);
    }

    public Pickup createPickup(float x, float y){
        float whichPickup = random.nextInt(3);
        if (whichPickup == 0){
            return new Pickup(0, 0, pickupTexture[0], batch, x, y, PickupType.HEALTH);
        }
        else if (whichPickup == 1){
            return new Pickup(0, 0, pickupTexture[1], batch, x, y, PickupType.DAMAGE);
        }
        else if (whichPickup == 2){
            return new Pickup(0, 0, pickupTexture[2], batch, x, y, PickupType.SPEED);
        }

        return null;
    }

	public void dispose(){
        for (Texture player : playerTexture) {
            player.dispose();
        }
        for (Texture enemy : enemyTexture) {
            enemy.dispose();
        }
    }
}