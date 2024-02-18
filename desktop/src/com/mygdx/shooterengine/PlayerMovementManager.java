package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class PlayerMovementManager{

	private IOManager ioManager = new IOManager();

	public void Move(Player player) {

        if (ioManager.CheckInput(Keys.LEFT) || ioManager.CheckInput(Keys.A)) {
            //playermoveLeft
            if(player.getX() > 0){
                player.setX(player.getX() - player.getSpeed() * Gdx.graphics.getDeltaTime());
            }
                
        }
        if (ioManager.CheckInput(Keys.RIGHT) || ioManager.CheckInput(Keys.D)) {
			//playermoveRight
            if(player.getX() < Gdx.graphics.getWidth() - player.GetTexture().getWidth()){
                player.setX(player.getX() + player.getSpeed() * Gdx.graphics.getDeltaTime());
            }
        }
        if (ioManager.CheckInput(Keys.UP) || ioManager.CheckInput(Keys.W)) {
			//playermoveUp
            if (player.getY() < Gdx.graphics.getHeight() - player.GetTexture().getHeight()) {
                player.setY(player.getY() + player.getSpeed() * Gdx.graphics.getDeltaTime());  
            }
        }
        if (ioManager.CheckInput(Keys.DOWN) || ioManager.CheckInput(Keys.S)) {
			//playermoveDown
            if (player.getY() > 0) {
                player.setY(player.getY() - player.getSpeed() * Gdx.graphics.getDeltaTime());  
            }
        }

    }
}
