package com.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
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
    public ParametersButtons(Stage stage, GameScreen screen) {
        this.stage = stage;
        this.screen = screen;
        skin = new Skin(Gdx.files.internal("ParametersFields2/BigFields.json"));
        miniSkin = new Skin(Gdx.files.internal("ParametersFields/ParametersField.json"));
        plusButton = new Skin(Gdx.files.internal("ParametersFields2/ParametersFields.json"));
        addButtons();
    }
    private void addButtons() {
        Label damageLabel = new Label("DAMAGE: " + screen.parseToString(ParametersFields.damage), skin);
        damageLabel.setPosition(375, 875);
        stage.addActor(damageLabel);
        Label damageInfo = new Label("Cost: " + screen.parseToString(ParametersFields.damagePrice), miniSkin);
        damageInfo.setPosition(375, 850);
        stage.addActor(damageInfo);

        ImageButton plusButton1 = new ImageButton(plusButton);
        plusButton1.setPosition(725, 850);
        stage.addActor(plusButton1);

        Label temperature = new Label("MAX TEMP: " + ParametersFields.maxTemperature, skin);
        temperature.setPosition(375, 775);
        stage.addActor(temperature);
        Label temperatureInfo = new Label("Cost: " + screen.parseToString(ParametersFields.maxTemperaturePrice), miniSkin);
        temperatureInfo.setPosition(375, 750);
        stage.addActor(temperatureInfo);

        ImageButton plusButton2 = new ImageButton(plusButton);
        plusButton2.setPosition(725, 750);
        stage.addActor(plusButton2);

        Label refrigeration = new Label("REFRIGERATION: " + ParametersFields.refrigeration, skin);
        refrigeration.setPosition(375, 675);
        stage.addActor(refrigeration);
        Label refrigerationInfo = new Label("Cost: " + screen.parseToString(ParametersFields.refrigerationPrice), miniSkin);
        refrigerationInfo.setPosition(375, 650);
        stage.addActor(refrigerationInfo);

        ImageButton plusButton3 = new ImageButton(plusButton);
        plusButton3.setPosition(725, 650);
        stage.addActor(plusButton3);

        Label critChance = new Label("CRIT CHANCE: " + ParametersFields.criticalChance + "%", skin);
        critChance.setPosition(375, 575);
        stage.addActor(critChance);
        Label critChanceInfo = new Label("Cost: " + screen.parseToString(ParametersFields.criticalChancePrice), miniSkin);
        critChanceInfo.setPosition(375, 550);
        stage.addActor(critChanceInfo);

        ImageButton plusButton4 = new ImageButton(plusButton);
        plusButton4.setPosition(725, 550);
        stage.addActor(plusButton4);

        Label critDamage = new Label(String.format(Locale.US,"CRIT DAMAGE: %.1f%%", (ParametersFields.criticalDamage * 100)), skin);
        critDamage.setPosition(375, 475);
        stage.addActor(critDamage);
        Label critDamageInfo = new Label("Cost: " + screen.parseToString(ParametersFields.criticalDamagePrice), miniSkin);
        critDamageInfo.setPosition(375, 450);
        stage.addActor(critDamageInfo);

        ImageButton plusButton5 = new ImageButton(plusButton);
        plusButton5.setPosition(725, 450);
        stage.addActor(plusButton5);

        Label poisonChance = new Label("POISON CHANCE: " +  (ParametersFields.poisonChance / 2) + "%", skin);
        poisonChance.setPosition(375, 375);
        stage.addActor(poisonChance);
        Label poisonChanceInfo = new Label("Cost: " + screen.parseToString(ParametersFields.poisonChancePrice), miniSkin);
        poisonChanceInfo.setPosition(375, 350);
        stage.addActor(poisonChanceInfo);

        ImageButton plusButton6 = new ImageButton(plusButton);
        plusButton6.setPosition(725, 350);
        stage.addActor(plusButton6);

        Label freezeChance = new Label("FREEZE CHANCE: " + (ParametersFields.freezeChance / 2) + "%", skin);
        freezeChance.setPosition(375, 275);
        stage.addActor(freezeChance);
        Label freezeChanceInfo = new Label("Cost: " + screen.parseToString(ParametersFields.freezeChancePrice), miniSkin);
        freezeChanceInfo.setPosition(375, 250);
        stage.addActor(freezeChanceInfo);

        ImageButton plusButton7 = new ImageButton(plusButton);
        plusButton7.setPosition(725, 250);
        stage.addActor(plusButton7);
    }


}