package com.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Enemy extends Actor {
    private final Texture texture;
    private final long hitPoints;
    private final double shield;
    private final long regeneration;
    private final double poisonResistance;
    private final double freezeResistance;

    public Enemy(Texture texture, long hitPoints, double shield, long regeneration, double poisonResistance, double freezeResistance) {
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
    public static EnemyBuilder builder() {
        return new EnemyBuilder();
    }

    public static class EnemyBuilder {
        private Texture texture;
        private long hitPoints;
        private double shield;
        private long regeneration;
        private double poisonResistance;
        private double freezeResistance;
        public EnemyBuilder texture(Texture texture) {
            this.texture = texture;
            return this;
        }
        public EnemyBuilder hitPoints(long hitPoints) {
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
