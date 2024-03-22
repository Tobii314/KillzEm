package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ParametersFields {
    public static float damage = 0.000001f; // 1
    public static float damagePrice = 0.000001f;
    public static int damageLevel = 1;
    public static int damagePower = 0;

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

    public static int maxTemperature = 70;
    public static int currentTemperature = 10;
    public static float maxTemperaturePrice = 0.000001f;
    public static int maxTemperatureLevel = 0;
    public static Texture texture1 = new Texture(Gdx.files.internal("Bullets/cannonBall.png"));
    public static Texture texture2 = new Texture(Gdx.files.internal("Bullets/cannonFreezeBall.png"));
    public static Texture texture3 = new Texture(Gdx.files.internal("Bullets/cannonPoisonBall.png"));
    public static String damageLabel = "DamageString/damageString.fnt";
    public static String critDamageLabel = "DamageString/critDamage.fnt";
    public static int localCounter = 0;
    public static boolean isDisabled = true;
    public static int localeCounterDebuff = 0;

    public static void setDamage(float currentDamage) {
        if (damageLevel < 10) {
            damage+= (damageLevel * 1.75f) / 1_000_000f;  /// 1 - 80
            damageLevel++;
            System.out.println("tier 1");
        } else if ((damagePower == 0) && (damageLevel >= 10)) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel / 50;  /// 80 - 650
            damageLevel++;
            System.out.println("tier 2");
        } else if (damagePower == 1) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel;   /// 3200 - 155k
            damageLevel++;
            System.out.println("tier 3");
        } else if (damagePower == 2) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 5;  /// 775k - 3.5m
            damageLevel++;
            System.out.println("tier 4");
        } else if (damagePower == 3) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 50; /// 38m - 55m
            damageLevel++;
            System.out.println("tier 5");
        } else if (damagePower == 4) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 150;   /// 18m - 125m
            damageLevel++;
            System.out.println("tier 6");
        } else if (damagePower == 5){
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 500;  /// 600m - 1.7b
            damageLevel++;
            System.out.println("tier 7");
        }
        System.out.println( + damage);
    }
    public static void setMaxTemperature(int temperature) {
        maxTemperature += temperature;
    }

    public static void setCurrentTemperature(int refrigeration) {
        if (ParametersFields.currentTemperature <= 10) {
            ParametersFields.currentTemperature = 10;
        } else {
            ParametersFields.currentTemperature -= refrigeration;
        }

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
