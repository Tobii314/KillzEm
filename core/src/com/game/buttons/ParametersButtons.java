package com.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.game.ParametersFields;
import com.game.screens.GameScreen;

import java.util.Locale;

public class ParametersButtons {
    Stage stage;
    Skin miniSkin;
    Skin skin;
    GameScreen screen;
    private final Skin plusButton;
    private final Skin powerButton;
    public ParametersButtons(Stage stage, GameScreen screen) {
        this.stage = stage;
        this.screen = screen;
        skin = new Skin(Gdx.files.internal("font/ParametersFields2/BigFields.json"));
        miniSkin = new Skin(Gdx.files.internal("font/ParametersFields/ParametersField.json"));
        plusButton = new Skin(Gdx.files.internal("font/ParametersFields2/ParametersFields.json"));
        powerButton = new Skin(Gdx.files.internal("graphics/plusButton/plusButtonPower.json"));
        addButtons();
    }
    private void addButtons() {
        Label damageLabel = new Label("DAMAGE: " + screen.parseToString(ParametersFields.damage), skin);
        damageLabel.setPosition(370, 875);
        stage.addActor(damageLabel);
        Label damageInfo = new Label("Cost: " + screen.parseToString(ParametersFields.damagePrice), miniSkin);
        damageInfo.setPosition(370, 850);
        stage.addActor(damageInfo);

        ImageButton plusButton1 = new ImageButton(plusButton);
        plusButton1.setPosition(725, 850);
        stage.addActor(plusButton1);

        ImageButton plusButtonPower = new ImageButton(powerButton);
        plusButtonPower.setPosition(650, 850);
        plusButtonPower.setDisabled(ParametersFields.isDisabled);
        stage.addActor(plusButtonPower);

        Label powerInfo = new Label("Cost: " + screen.parseToString(GameScreen.powerCosts.get(ParametersFields.damagePower)), miniSkin);
        powerInfo.setPosition(650, 825);
        stage.addActor(powerInfo);

        Label temperature = new Label("MAX TEMP: " + ParametersFields.maxTemperature, skin);
        temperature.setPosition(370, 775);
        stage.addActor(temperature);
        Label temperatureInfo = new Label("Cost: " + screen.parseToString(ParametersFields.maxTemperaturePrice), miniSkin);
        temperatureInfo.setPosition(370, 750);
        stage.addActor(temperatureInfo);

        ImageButton plusButton2 = new ImageButton(plusButton);
        plusButton2.setPosition(725, 750);
        stage.addActor(plusButton2);

        Label refrigeration = new Label("REFRIGERATION: " + ParametersFields.refrigeration, skin);
        refrigeration.setPosition(370, 675);
        stage.addActor(refrigeration);
        Label refrigerationInfo = new Label("Cost: " + screen.parseToString(ParametersFields.refrigerationPrice), miniSkin);
        refrigerationInfo.setPosition(370, 650);
        stage.addActor(refrigerationInfo);

        ImageButton plusButton3 = new ImageButton(plusButton);
        plusButton3.setPosition(725, 650);
        stage.addActor(plusButton3);

        Label critChance = new Label("CRIT CHANCE: " + ParametersFields.criticalChance + "%", skin);
        critChance.setPosition(370, 575);
        stage.addActor(critChance);
        Label critChanceInfo = new Label("Cost: " + screen.parseToString(ParametersFields.criticalChancePrice), miniSkin);
        critChanceInfo.setPosition(370, 550);
        stage.addActor(critChanceInfo);

        ImageButton plusButton4 = new ImageButton(plusButton);
        plusButton4.setPosition(725, 550);
        stage.addActor(plusButton4);

        Label critDamage = new Label(String.format(Locale.US,"CRIT DAMAGE: %.1f%%", (ParametersFields.criticalDamage * 100)), skin);
        critDamage.setPosition(370, 475);
        stage.addActor(critDamage);
        Label critDamageInfo = new Label("Cost: " + screen.parseToString(ParametersFields.criticalDamagePrice), miniSkin);
        critDamageInfo.setPosition(370, 450);
        stage.addActor(critDamageInfo);

        ImageButton plusButton5 = new ImageButton(plusButton);
        plusButton5.setPosition(725, 450);
        stage.addActor(plusButton5);

        Label poisonChance = new Label("POISON CHANCE: " +  (ParametersFields.poisonChance / 2) + "%", skin);
        poisonChance.setPosition(370, 375);
        stage.addActor(poisonChance);
        Label poisonChanceInfo = new Label("Cost: " + screen.parseToString(ParametersFields.poisonChancePrice), miniSkin);
        poisonChanceInfo.setPosition(370, 350);
        stage.addActor(poisonChanceInfo);

        ImageButton plusButton6 = new ImageButton(plusButton);
        plusButton6.setPosition(725, 350);
        stage.addActor(plusButton6);

        Label freezeChance = new Label("FREEZE CHANCE: " + (ParametersFields.freezeChance / 2) + "%", skin);
        freezeChance.setPosition(370, 275);
        stage.addActor(freezeChance);
        Label freezeChanceInfo = new Label("Cost: " + screen.parseToString(ParametersFields.freezeChancePrice), miniSkin);
        freezeChanceInfo.setPosition(370, 250);
        stage.addActor(freezeChanceInfo);

        ImageButton plusButton7 = new ImageButton(plusButton);
        plusButton7.setPosition(725, 250);
        stage.addActor(plusButton7);
    }


}