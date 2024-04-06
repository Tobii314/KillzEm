package com.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class ParametersFields {
    public static float money;
    public static float damage;
    public static float damagePrice;
    public static int damageLevel;
    public static int damagePower;

    public static double criticalChance;
    public static float criticalChancePrice;
    public static int criticalChanceLevel;

    public static double criticalDamage;
    public static float criticalDamagePrice;
    public static int criticalDamageLevel;

    public static double poisonChance; //500% от урона за 3 секунды
    public static float poisonChancePrice;
    public static int poisonChanceLevel;

    public static double freezeChance;
    public static float freezeChancePrice;
    public static int freezeChanceLevel;

    public static int refrigeration;
    public static float refrigerationPrice;
    public static int refrigerationLevel;

    public static int maxTemperature;
    public static int currentTemperature = 10;
    public static float maxTemperaturePrice;
    public static int maxTemperatureLevel;
    public static Texture texture1 = new Texture(Gdx.files.internal("picture/Bullets/cannonBall.png"));
    public static Texture texture2 = new Texture(Gdx.files.internal("picture/Bullets/cannonFreezeBall.png"));
    public static Texture texture3 = new Texture(Gdx.files.internal("picture/Bullets/cannonPoisonBall.png"));
    public static String damageLabel = "font/DamageString/damageString.fnt";
    public static String critDamageLabel = "font/DamageString/critDamage.fnt";
    public static int localCounter;
    public static boolean isDisabled = true;
    public static int localeCounterDebuff = 0;
    public static void setMoney(float currentMoney) {
        money += currentMoney;
    }
    public static void setMinusMoney(float currentMoney) {
        money -= currentMoney;
        if (money < 0) {
            money = 0;
        }
    }


    public static void setDamage(float currentDamage) {
        if (damageLevel < 10) {
            damage+= (damageLevel * 1.75f) / 1_000_000f;  /// 1 - 80  cost: 15 - 45
            damageLevel++;
        } else if ((damagePower == 0) && (damageLevel >= 10)) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel / 60;  /// 80 - 519   (10 - 52)  cost: 50 - 6.7k
            damageLevel++;
        } else if (damagePower == 1) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel / 1.715f;   /// 1815 - 57k    в 31 раз   (52 - 103)  cost: 7.6k - 1.7m
            damageLevel++;
        } else if (damagePower == 2) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 8.5f;  /// 200k - 4.56m  в 23 раз     (103 - 154)  cost: 2m - 227m
            damageLevel++;
        } else if (damagePower == 3) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 80; /// 16m - 290m  в 18 раз    (154 - 205)    cost: 245m - 11.5b
        } else if (damagePower == 4) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 550;   /// 1.02b - 12.06b   в 12 раз   (205 - 256)  cost: 12.5b - 600b
            damageLevel++;
        } else if (damagePower == 5) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 4_500;  /// 42.2b - 490b       в 11.5 раз   (256 - 307)   cost: 630b - 12t
            damageLevel++;
        } else if (damagePower == 6) {
            damage += (float)Math.sqrt(currentDamage) / damageLevel * 20_825;  /// 1.72t - 10.16t    в 6 раз  (307 - 358)   cost: 12.7t - 123t
            damageLevel++;
        } else if (damagePower > 6) {
            damage = damage + (damage * 0.0195f);  /// 35t ++  (358++)   cost: 127t - 730t
            damageLevel++;
        }
        if (damageLevel < 70) {
            damagePrice = (damagePrice + (damagePrice * 0.13f));
        } else if (damageLevel < 140) {
            damagePrice = (damagePrice + (damagePrice * 0.1075f));
        } else if (damageLevel < 210) {
            damagePrice = (damagePrice + (damagePrice * 0.08f));
        } else if (damageLevel < 280) {
            damagePrice = (damagePrice + (damagePrice * 0.059f));
        } else if (damageLevel < 350) {
            damagePrice = (damagePrice + (damagePrice * 0.036f));
        } else {
            damagePrice = (damagePrice + (damagePrice * 0.0225f));
        }
    }
    public static void setMaxTemperature(int temperature) {
        maxTemperature += temperature;
        maxTemperatureLevel++;
        maxTemperaturePrice = (maxTemperaturePrice + (maxTemperaturePrice *
                ((99 - maxTemperatureLevel) / 100f)));
    }
    public static void setRefrigeration(int refrigerationn) {
        refrigeration += refrigerationn;
        refrigerationLevel++;
        float localRefrigerationPrice = 0.15f;
        refrigerationPrice = (float)(Math.pow(localRefrigerationPrice * 100, refrigerationLevel) / 120);
    }
    public static void setCriticalChance(double plusCriticalChance) {
        criticalChance += plusCriticalChance;
        criticalChanceLevel++;
        criticalChancePrice = (criticalChancePrice + (criticalChancePrice *
                ((110 - criticalChanceLevel) / 175f)));
    }
    public static void setCriticalDamage(double plusCriticalDamage) {
        criticalDamage += plusCriticalDamage;
        criticalDamageLevel++;
        criticalDamagePrice = (criticalDamagePrice + (criticalDamagePrice *
                ((110 - criticalDamageLevel) / 175f)));
    }
    public static void setPoisonChance(double plusPoisonChance) {
        poisonChance += plusPoisonChance;
        poisonChanceLevel++;
        poisonChancePrice = (poisonChancePrice + (poisonChancePrice *
                ((100 - poisonChanceLevel) / 100f)));
    }
    public static void setFreezeChance(double plusFreezeChance) {
        freezeChance += plusFreezeChance;
        freezeChanceLevel++;
        freezeChancePrice = (freezeChancePrice + (freezeChancePrice *
                ((102 - freezeChanceLevel) / 100f)));
    }
    public static void setCurrentTemperature(int refrigeration) {
        if (ParametersFields.currentTemperature <= 10) {
            ParametersFields.currentTemperature = 10;
        } else {
            ParametersFields.currentTemperature -= refrigeration;
        }

    }

}
