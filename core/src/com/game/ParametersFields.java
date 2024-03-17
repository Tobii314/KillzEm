package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

import java.util.ArrayList;

public class ParametersFields {
    public static float damage = 0.000001f;
    public static float damagePrice = 0.000001f;
    public static int damageLevel = 0;

    public static double criticalChance = 0d;
    public static float criticalChancePrice = 0.000001f;
    public static int criticalChanceLevel = 0;

    public static double criticalDamage = 1.5d;
    public static float criticalDamagePrice = 0.000001f;
    public static int criticalDamageLevel = 0;

    public static double poisonChance = 0; //500% от урона за 3 секунды
    public static float poisonChancePrice = 0.000001f;
    public static int poisonChanceLevel = 0;

    public static double freezeChance = 0;
    public static float freezeChancePrice = 0.000001f;
    public static int freezeChanceLevel = 0;

    public static int refrigeration = 2;
    public static float refrigerationPrice = 0.000001f;
    public static int refrigerationLevel = 0;

    public static int maxTemperature = 60;
    public static float maxTemperaturePrice = 0.000001f;
    public static int maxTemperatureLevel = 0;
    public static Texture texture1 = new Texture(Gdx.files.internal("Bullets/cannonBall.png"));
    public static Texture texture2 = new Texture(Gdx.files.internal("Bullets/cannonFreezeBall.png"));
    public static Texture texture3 = new Texture(Gdx.files.internal("Bullets/cannonPoisonBall.png"));
    public static String damageLabel = "DamageString/damageString.fnt";
    public static String critDamageLabel = "DamageString/critDamage.fnt";

    public static void setDamage(float plusDamage) {
        damage += plusDamage;
    }
    public static void setMaxTemperature(int temperature) {
        maxTemperature += temperature;
    }

    public static void setCriticalChance(double plusCriticalChance) {
        criticalChance += plusCriticalChance;
        criticalChanceLevel++;
    }
    public static void setCriticalDamage(double plusCriticalDamage) {
        criticalDamage += plusCriticalDamage;
        criticalDamageLevel++;
    }
    public static void setPoisonChance(double plusPoisonChance) {
        poisonChance += plusPoisonChance;
        poisonChanceLevel++;
    }
    public static void setFreezeChance(double plusFreezeChance) {
        freezeChance += plusFreezeChance;
        freezeChanceLevel++;
    }

}
