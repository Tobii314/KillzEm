package com.game;

import com.badlogic.gdx.graphics.Texture;

public class AddEnemys {
    public Enemy firstTierEnemy(Texture texture, long hitPoints) {
        return Enemy.builder()
                .texture(texture)
                .hitPoints(hitPoints)
                .build();
    }
    public Enemy secondTierEnemy(Texture texture, long hitPoints, double shield) {
        return Enemy.builder()
                .texture(texture)
                .hitPoints(hitPoints)
                .shield(shield)
                .build();
    }
    public Enemy thirdTierEnemy(Texture texture, long hitPoints, double shield, long regen) {
        return Enemy.builder()
                .texture(texture)
                .hitPoints(hitPoints)
                .shield(shield)
                .regeneration(regen)
                .build();
    }
    public Enemy fourthTierEnemy(Texture texture, long hitPoints, double shield, long regen, double poisonResist) {
        return Enemy.builder()
                .texture(texture)
                .hitPoints(hitPoints)
                .shield(shield)
                .regeneration(regen)
                .poisonResistance(poisonResist)
                .build();
    }
    public Enemy fifthTierEnemy(Texture texture, long hitPoints, double shield, long regen, double poisonResist, double freezeResist) {
        return Enemy.builder()
                .texture(texture)
                .hitPoints(hitPoints)
                .shield(shield)
                .regeneration(regen)
                .poisonResistance(poisonResist)
                .freezeResistance(freezeResist)
                .build();
    }
}
