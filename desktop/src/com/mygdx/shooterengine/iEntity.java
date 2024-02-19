package com.mygdx.shooterengine;

public interface iEntity {
    public void Move();
    public void Shoot(int direction);
    public void TakeDamage(int damage);
}
