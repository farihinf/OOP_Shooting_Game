package com.mygdx.shooterengine;
import com.badlogic.gdx.Gdx;

public class PlayerMovementManager{

	private IOManager ioManager = new IOManager();

	public void Move(Player player) {

		ioManager.checkInput();

        if (ioManager.moveLeft()) {
            //playermoveLeft
			player.setX(player.getX() - player.getSpeed() * Gdx.graphics.getDeltaTime());
        }
        if (ioManager.moveRight()) {
			//playermoveRight
			player.setX(player.getX() + player.getSpeed() * Gdx.graphics.getDeltaTime());
        }
        if (ioManager.moveUp()) {
			//playermoveUp
			player.setY(player.getY() + player.getSpeed() * Gdx.graphics.getDeltaTime());
        }
        if (ioManager.moveDown()) {
			//playermoveDown
			player.setY(player.getY() - player.getSpeed() * Gdx.graphics.getDeltaTime());
        }

    }
}
