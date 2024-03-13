package com.mygdx.shooterengine;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class EntityFactory {
    private Texture[] playerTexture;  // hold player texture
    private Texture enemyTexture;   // hold enemy texture
    private SpriteBatch batch;

    EntityFactory(){
        playerTexture = new Texture[]{
                new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_blue.png")),
                new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_red.png")),
                new Texture(Gdx.files.internal("EntitySprites\\PlayerSprites\\player_grey.png")),
            };
		enemyTexture = new Texture(Gdx.files.internal("EntitySprites\\enemy.png"));
        batch = SpriteBatchSingleton.getInstance();
    }

    public Enemy createStandard(){
        return new Enemy(100, 30, 200, enemyTexture, batch, 300f, 400f);
    }
    public Enemy createBig(){
        return new Enemy(200, 40, 150, enemyTexture, batch, 300f, 400f);
    }
    public Enemy createSmall(){
        return new Enemy(50, 10, 220, enemyTexture, batch, 300f, 400f);
    }
    public Player createPlayer(int textureIndex){
        // temp
        textureIndex = 2;
        return new Player(100, 50, 200f, playerTexture[textureIndex], batch, 300f, 100f);
    }

	public void dispose(){
        for (Texture player : playerTexture) {
            player.dispose();
        }
		enemyTexture.dispose();
    }
}