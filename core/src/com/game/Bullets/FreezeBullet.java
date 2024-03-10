package com.game.Bullets;

import com.badlogic.gdx.graphics.Texture;

public class FreezeBullet extends Bullet{
    private double freezeChance;
    public Texture texture;

    public FreezeBullet(Texture texture, float damage, long critDamage, double critChance, double freezeChance) {
        super(texture, damage, critDamage, critChance);
        this.texture = texture;
        this.freezeChance = freezeChance;
    }

}
