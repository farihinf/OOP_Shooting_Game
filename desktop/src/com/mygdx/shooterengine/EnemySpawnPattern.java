package com.mygdx.shooterengine;

import java.util.ArrayList;
import java.util.List;

public class EnemySpawnPattern {
    private List<EnemyConfig> waveConfigs;

    public EnemySpawnPattern() {
        waveConfigs = new ArrayList<>();
        // Add your wave configurations here
        // Example: Wave 1 - 3 big enemies, 2 small enemies
        waveConfigs.add(new EnemyConfig(2, EnemyType.BIG));
        waveConfigs.add(new EnemyConfig(3, EnemyType.NORMAL));
        // Example: Wave 2 - 3 normal enemies, 2 big enemies
        //waveConfigs.add(new EnemyConfig(3, EnemyType.NORMAL));
        //waveConfigs.add(new EnemyConfig(2, EnemyType.BIG));
        // Add more wave configurations as needed
    }

    public List<EnemyConfig> getWaveConfigs() {
        return waveConfigs;
    }
}

class EnemyConfig {
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

enum EnemyType {
    SMALL, NORMAL, BIG
}

