package com.mygdx.shooterengine.Entities;

import java.util.ArrayList;
import java.util.List;

import com.mygdx.shooterengine.Enums.EnemyType;

public class EnemySpawnPattern {
    private List<EnemyConfig> waveConfigs;

    public EnemySpawnPattern() {
        waveConfigs = new ArrayList<>();
        // Add your wave configurations here
        waveConfigs.add(new EnemyConfig(3, EnemyType.SHOOTER));
        waveConfigs.add(new EnemyConfig(2, EnemyType.CHASER));
        waveConfigs.add(new EnemyConfig(5, EnemyType.SHOOTER));
        waveConfigs.add(new EnemyConfig(4, EnemyType.CHASER));
        waveConfigs.add(new EnemyConfig(1, EnemyType.SHOOTER));

    }

    public List<EnemyConfig> getWaveConfigs() {
        return waveConfigs;
    }
}

