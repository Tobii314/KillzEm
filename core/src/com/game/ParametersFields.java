package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ParametersFields {
    public static long damage = 0;
    public static double criticalChance = 0;
    public static long criticalDamage = 0;
    public static double poisonChance = 10;
    public static double freezeChance = 10;
    public static Texture texture1 = new Texture(Gdx.files.internal("Bullets/cannonBall.png"));
    public static Texture texture2 = new Texture(Gdx.files.internal("Bullets/cannonFreezeBall.png"));
    public static Texture texture3 = new Texture(Gdx.files.internal("Bullets/cannonPoisonBall.png"));

    public void setDamage(long plusDamage) {
        damage += plusDamage;
    }
    public void setCriticalChance(double plusCriticalChance) {
        criticalChance += plusCriticalChance;
    }
    public void setCriticalDamage(long plusCriticalDamage) {
        criticalDamage += plusCriticalDamage;
    }
    public void setPoisonChance(double plusPoisonChance) {
        poisonChance += plusPoisonChance;
    }
    public void setFreezeChance(double plusFreezeChance) {
        freezeChance += plusFreezeChance;
    }

}
