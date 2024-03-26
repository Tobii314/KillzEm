package com.game;

import com.badlogic.gdx.graphics.Texture;

public class AddEnemys {
    public Enemy firstTierEnemy(Texture texture, float earning, float hitPoints) {
        return Enemy.builder()
                .texture(texture)
                .earning(earning)
                .hitPoints(hitPoints)
                .build();
    }
    public Enemy secondTierEnemy(Texture texture, float earning, float hitPoints, float shield) {
        return Enemy.builder()
                .texture(texture)
                .earning(earning)
                .hitPoints(hitPoints)
                .shield(shield)
                .build();
    }
    public Enemy thirdTierEnemy(Texture texture, float earning, float hitPoints, float shield, float regen) {
        return Enemy.builder()
                .texture(texture)
                .earning(earning)
                .hitPoints(hitPoints)
                .shield(shield)
                .regeneration(regen)
                .build();
    }
    public Enemy fourthTierEnemy(Texture texture, float earning, float hitPoints, float shield, float regen, double poisonResist) {
        return Enemy.builder()
                .texture(texture)
                .earning(earning)
                .hitPoints(hitPoints)
                .shield(shield)
                .regeneration(regen)
                .poisonResistance(poisonResist)
                .build();
    }
    public Enemy fifthTierEnemy(Texture texture, float earning, float hitPoints, float shield, float regen, double poisonResist, double freezeResist) {
        return Enemy.builder()
                .texture(texture)
                .earning(earning)
                .hitPoints(hitPoints)
                .shield(shield)
                .regeneration(regen)
                .poisonResistance(poisonResist)
                .freezeResistance(freezeResist)
                .build();
    }
}
