package com.game.Bullets;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Bullet extends Actor {
    public float damage;
    public float critDamage;
    public double critChance;
    public Texture texture;
    public float x;
    private float y;

    public Bullet(Texture texture, float damage, float critDamage, double critChance) {
        this.damage = damage;
        this.critDamage = critDamage;
        this.critChance = critChance;
        this.texture = texture;
        this.x = 375;
        this.y = 150;

    }

    public Texture getTexture() {
        return this.texture;
    }
    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public void setX(float x) {
        this.x += x;
    }

    public void setY(float y) {
        this.y = y;
    }
}

