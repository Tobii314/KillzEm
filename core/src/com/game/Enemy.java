package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.game.screens.GameScreen.counterForEnemies;
import static com.game.screens.GameScreen.hpEnemies;

public class Enemy extends Actor {
    private boolean wasDead = false;
    private final Texture texture;
    private float hitPoints;
    private final double shield;
    private final long regeneration;
    private final double poisonResistance;
    private final double freezeResistance;

    public Enemy(Texture texture, float hitPoints, double shield, long regeneration, double poisonResistance, double freezeResistance) {
        this.texture = texture;
        this.hitPoints = hitPoints;
        this.shield = shield;
        this.regeneration = regeneration;
        this.poisonResistance = poisonResistance;
        this.freezeResistance = freezeResistance;
    }
    public Boolean wasDead() {
        return this.wasDead;
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
    public static EnemyBuilder builder() {
        return new EnemyBuilder();
    }

    public static class EnemyBuilder {
        private Texture texture;
        private float hitPoints;
        private double shield;
        private long regeneration;
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

        public EnemyBuilder shield(double shield) {
            this.shield = shield;
            return this;
        }
        public EnemyBuilder regeneration(long regeneration) {
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
