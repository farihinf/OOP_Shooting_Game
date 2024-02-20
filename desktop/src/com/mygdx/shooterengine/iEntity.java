package com.mygdx.shooterengine;

// Interface for player and enemy class
public interface iEntity {
    public void Move();
    public void Shoot(int direction);
    public void TakeDamage(int damage);
}
