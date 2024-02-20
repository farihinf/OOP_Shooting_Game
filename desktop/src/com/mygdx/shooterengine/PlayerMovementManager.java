package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PlayerMovementManager{

    //handle player movement based on input
	public void Move(Player player) {

        if (IOManager.GetInstance().isKeyPressed(Keys.LEFT) || IOManager.GetInstance().isKeyPressed(Keys.A)) {
            //player move left
            if(player.getX() > 0){
                player.setX(player.getX() - player.getSpeed() * Gdx.graphics.getDeltaTime());
            }           
        }
        if (IOManager.GetInstance().isKeyPressed(Keys.RIGHT) || IOManager.GetInstance().isKeyPressed(Keys.D)) {
			//player move right
            if(player.getX() < Gdx.graphics.getWidth() - player.GetTexture().getWidth()){
                player.setX(player.getX() + player.getSpeed() * Gdx.graphics.getDeltaTime());
            }
        }
        if (IOManager.GetInstance().isKeyPressed(Keys.UP) || IOManager.GetInstance().isKeyPressed(Keys.W)) {
			//player move up
            if (player.getY() < Gdx.graphics.getHeight() - player.GetTexture().getHeight()) {
                player.setY(player.getY() + player.getSpeed() * Gdx.graphics.getDeltaTime());  
            }
        }
        if (IOManager.GetInstance().isKeyPressed(Keys.DOWN) || IOManager.GetInstance().isKeyPressed(Keys.S)) {
			//player move Down
            if (player.getY() > 0) {
                player.setY(player.getY() - player.getSpeed() * Gdx.graphics.getDeltaTime());  
            }
        }

    }
}
