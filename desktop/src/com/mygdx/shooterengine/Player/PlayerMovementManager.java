package com.mygdx.shooterengine.Player;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.mygdx.shooterengine.Managers.IOManager;

public class PlayerMovementManager{
    //responsible for moving the player based on input
	public void Move(Player player) {

        if (IOManager.GetInstance().isKeyPressed(Keys.LEFT) || IOManager.GetInstance().isKeyPressed(Keys.A)) {
            //player move Left
            if(player.getX() > 0){
                //speed of player movement is determined by the player's speed attribute,
                //and the time elapsed since the last frame
                player.setX(player.getX() - player.getSpeed() * Gdx.graphics.getDeltaTime());
            }           
        }
        if (IOManager.GetInstance().isKeyPressed(Keys.RIGHT) || IOManager.GetInstance().isKeyPressed(Keys.D)) {
			//player move Right
            if(player.getX() < Gdx.graphics.getWidth() - player.GetTexture().getWidth()){
                player.setX(player.getX() + player.getSpeed() * Gdx.graphics.getDeltaTime());
            }
        }
        if (IOManager.GetInstance().isKeyPressed(Keys.UP) || IOManager.GetInstance().isKeyPressed(Keys.W)) {
			//player move Up
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
