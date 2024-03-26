package com.mygdx.shooterengine.Entities;

import com.mygdx.shooterengine.Enums.EnemyType;

public class EnemyConfig {
    private int quantity;
    private EnemyType type;

    public EnemyConfig(int quantity, EnemyType type) {
        this.quantity = quantity;
        this.type = type;
    }

    public int getQuantity() {
        return quantity;
    }

    public EnemyType getType() {
        return type;
    }
}