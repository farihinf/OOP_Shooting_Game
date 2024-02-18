package com.mygdx.shooterengine;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface iEntity {
    public void Move();
    public void Shoot(int direction);
    public void TakeDamage(int damage);
}
