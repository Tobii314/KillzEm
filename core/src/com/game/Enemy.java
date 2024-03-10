package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.screens.GameScreen.counterForEnemies;
import static com.game.screens.GameScreen.hpEnemies;

public class Enemy extends Actor {
    private final Texture texture;
    private float hitPoints;
    private final float shield;
    private final float regeneration;
    private final double poisonResistance;
    private final double freezeResistance;

    public Enemy(Texture texture, float hitPoints, float shield, float regeneration, double poisonResistance, double freezeResistance) {
        this.texture = texture;
        this.hitPoints = hitPoints;
        this.shield = shield;
        this.regeneration = regeneration;
        this.poisonResistance = poisonResistance;
        this.freezeResistance = freezeResistance;
    }
    public Texture getTexture() {
        return this.texture;
    }
    public float getHitPoints() {
        return this.hitPoints;
    }
    public float setHitPoints(float damage) {
        float currentHitPoints;
        currentHitPoints = this.hitPoints;
        currentHitPoints -= damage;
        this.hitPoints = currentHitPoints;
        if (this.hitPoints <= 0) {
            KiilzEm.listOfDead.set(counterForEnemies-1, true);
            this.hitPoints = hpEnemies.get(counterForEnemies);
        }
        return this.hitPoints;
    }
    public float setHitPointsPlus(float regen) {
        this.hitPoints += regen;
        if (this.hitPoints >= hpEnemies.get(counterForEnemies)) {
            this.hitPoints = hpEnemies.get(counterForEnemies);
        }
        return this.hitPoints;
    }
    public float setNewHitPoints(float hitPoints) {
        this.hitPoints = hitPoints;
        return this.hitPoints;
    }
    public float getShield() {
        return this.shield;
    }
    public float getRegeneration() {
        return this.regeneration;
    }
    public static EnemyBuilder builder() {
        return new EnemyBuilder();
    }

    public static class EnemyBuilder {
        private Texture texture;
        private float hitPoints;
        private float shield;
        private float regeneration;
        private double poisonResistance;
        private double freezeResistance;
        public EnemyBuilder texture(Texture texture) {
            this.texture = texture;
            return this;
        }
        public EnemyBuilder hitPoints(float hitPoints) {
            this.hitPoints = hitPoints;
            return this;
        }

        public EnemyBuilder shield(float shield) {
            this.shield = shield;
            return this;
        }
        public EnemyBuilder regeneration(float regeneration) {
            this.regeneration = regeneration;
            return this;
        }
        public EnemyBuilder poisonResistance(double poisonResistance) {
            this.poisonResistance = poisonResistance;
            return this;
        }
        public EnemyBuilder freezeResistance(double freezeResistance) {
            this.freezeResistance = freezeResistance;
            return this;
        }
        public Enemy build() {
            return new Enemy(texture, hitPoints, shield, regeneration, poisonResistance, freezeResistance);
        }

    }
}
