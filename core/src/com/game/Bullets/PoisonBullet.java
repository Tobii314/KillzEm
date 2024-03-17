package com.game.Bullets;

import com.badlogic.gdx.graphics.Texture;

public class PoisonBullet extends Bullet{
    private double poisonChance;
    public Texture texture;

    public PoisonBullet(Texture texture, float damage, double critDamage, double critChance, double poisonChance) {
        super(texture,damage,critDamage,critChance);
        this.texture = texture;
        this.poisonChance = poisonChance;
    }

}
