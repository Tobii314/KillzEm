package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.screens.GameScreen.*;

public class Enemy extends Actor {
    private final Texture texture;
    private float earning;
    private float hitPoints;
    private final float shield;
    private final float regeneration;
    private final double poisonResistance;
    private final double freezeResistance;

    public Enemy(Texture texture, float earning, float hitPoints, float shield, float regeneration, double poisonResistance, double freezeResistance) {
        this.texture = texture;
        this.earning = earning;
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
    public float getEarning() {
        return this.earning;
    }
    public float setHitPoints(float damage) {
        float currentHitPoints;
        currentHitPoints = this.hitPoints;
        currentHitPoints -= damage;
        this.hitPoints = currentHitPoints;
        if (this.hitPoints <= 0) {
            KiilzEm.listOfDead.set(counterForEnemies-1, true);
            ParametersFields.setMoney(earningEnemies.get(counterForEnemies));
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
    public double getPoisonResistance() {
        return this.poisonResistance;
    }
    public double getFreezeResistance() {
        return this.freezeResistance;
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
        private float earning;
        private float hitPoints;
        private float shield;
        private float regeneration;
        private double poisonResistance;
        private double freezeResistance;
        public EnemyBuilder texture(Texture texture) {
            this.texture = texture;
            return this;
        }
        public EnemyBuilder earning(float earning) {
            this.earning = earning;
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
            return new Enemy(texture, earning, hitPoints, shield,
                    regeneration, poisonResistance, freezeResistance);
        }
    }
}
