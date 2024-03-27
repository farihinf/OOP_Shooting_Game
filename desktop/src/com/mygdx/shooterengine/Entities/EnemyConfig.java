package com.mygdx.shooterengine.Entities;

import com.mygdx.shooterengine.Enums.EnemyType;

// Class for Enemy Configuration
public class EnemyConfig {
    private int quantity;  // amount of enemies
    private EnemyType type; // type of enemies

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