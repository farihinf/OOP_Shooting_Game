package com.mygdx.shooterengine.Factories;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.shooterengine.Entities.Enemy;
import com.mygdx.shooterengine.Entities.EntitySpriteBatchSingleton;
import com.mygdx.shooterengine.Entities.Pickup;
import com.mygdx.shooterengine.Enums.EnemyType;
import com.mygdx.shooterengine.Enums.PickupType;
import com.mygdx.shooterengine.Player.Player;

import java.util.Random;

public class EntityFactory {
    private Texture[] playerTexture;  // hold player texture
    private Texture[] enemyTexture;   // hold enemy texture
    private Texture[] pickupTexture; // hold pickup texture
    private float maxSpeed = 125f;  // max speed of enemies
    private float minSpeed = 80f;  // min speed of enemies
    private float maxX = 600f;   // max X axis (boundary)
    private float minX = 50f;   // min X axis (boundary)
    private SpriteBatch batch;  
    private Random random = new Random();

    public EntityFactory(){
        // Load textures
        playerTexture = new Texture[]{
            new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_blue.png")),
            new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_red.png")),
            new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_grey.png")),
        };

        enemyTexture = new Texture[]{
            new Texture(Gdx.files.internal("EntitySprites\\Entity\\enemy.png")),
            new Texture(Gdx.files.internal("EntitySprites\\Entity\\enemy2.png")),
            new Texture(Gdx.files.internal("EntitySprites\\Entity\\BigEnemy.png"))

        };

        pickupTexture = new Texture[]{
            new Texture(Gdx.files.internal("EntitySprites\\Entity\\Health.png")),
            new Texture(Gdx.files.internal("EntitySprites\\Entity\\Damage.png")),
            new Texture(Gdx.files.internal("EntitySprites\\Entity\\Speed.png"))
        };

        batch = EntitySpriteBatchSingleton.getInstance();
    }

    // create Shooter Enemy Type
    public Enemy createShooter(){
        return new Enemy(500, 10, 200, enemyTexture[0], batch, 300f, 500f, EnemyType.SHOOTER);
    }

    // create Chaser Enemy type
    public Enemy createChaser() {
        Random random = new Random();
        
        // Generate random speed within a range
        float randomSpeed = random.nextFloat() * (maxSpeed - minSpeed) + minSpeed;
        
        // Generate random x position within a range
        float randomX = random.nextFloat() * (maxX - minX) + minX;
        
        return new Enemy(500, 10, randomSpeed, enemyTexture[1], batch, randomX, 500f, EnemyType.CHASER);
    }

    // create Big Enemy type
    public Enemy createBig(){
        return new Enemy(800, 25, 50f, enemyTexture[2], batch, 300f, 500f, EnemyType.SHOOTER);
    }

    // create Player based on chosen plane
    public Player createPlayer(int textureIndex){
        int health;
        int damage;
        float speed;
        switch (textureIndex){
            case 0:
                health = 100;
                damage = 30;
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
        return new Player(health, damage, speed, playerTexture[textureIndex], batch, 300f, 100f, textureIndex);
    }

    // Create pickup randomly
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
        for (Texture pickup : pickupTexture) {
            pickup.dispose();
        }
    }
}