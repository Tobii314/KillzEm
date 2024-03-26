package com.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.game.ConfigurationFields;
import com.game.KiilzEm;
import com.game.ParametersFields;
import com.game.buttons.ParametersButtons;

import java.util.Locale;


public class GameParametersScreen implements Screen {
    KiilzEm game;
    GameScreen gameScreen;
    private final Stage overlayStage;
    Image overlayImage;
    Array<Actor> listOfParameters;
    ImageButton powerButton;
    public GameParametersScreen(KiilzEm game, GameScreen gameScreen, Stage stage) {
        this.game = game;
        this.gameScreen = gameScreen;
        this.overlayStage = stage;
        Texture texture = new Texture("menuParameters.png");
        overlayImage = new Image(texture);
        overlayImage.setPosition(350,240);
        overlayStage.addActor(overlayImage);
        new ParametersButtons(overlayStage, gameScreen);
        listOfParameters = overlayStage.getActors();
        powerButton = (ImageButton) listOfParameters.get(4);

        listOfParameters.get(3).addListener(new ClickListener() {  /// damage
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                ParametersFields.setDamage(ParametersFields.damage);
                Label label = (Label) listOfParameters.get(1);
                label.setText("DAMAGE: " + gameScreen.parseToString(ParametersFields.damage));
                ParametersFields.localCounter++;
                Label label2 = (Label) listOfParameters.get(2);
                label2.setText("Cost: " + gameScreen.parseToString(ParametersFields.damagePrice));

                if (ParametersFields.localCounter > 50) {
                    ParametersFields.isDisabled = false;
                    powerButton.setDisabled(false);
                    ParametersFields.localCounter = 0;
                }
            }
        });
        listOfParameters.get(4).addListener(new ClickListener() { /// power
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (powerButton.isDisabled()) {
                    } else {
                        ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                        ParametersFields.damage *= 5;
                        Label label = (Label) listOfParameters.get(1);
                        label.setText("DAMAGE: " + gameScreen.parseToString(ParametersFields.damage));
                        ParametersFields.damagePower++;
                        ParametersFields.isDisabled = true;
                        powerButton.setDisabled(true);
                    }
                }
            });
        listOfParameters.get(8).addListener(new ClickListener() {  ///max temp
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ParametersFields.maxTemperatureLevel >= 47) {
                } else {
                    ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                    ParametersFields.setMaxTemperature(5);
                    Label label = (Label) listOfParameters.get(6);
                    label.setText("MAX TEMP: " + ParametersFields.maxTemperature);
                    Label label2 = (Label) listOfParameters.get(7);
                    label2.setText("Cost: " + gameScreen.parseToString(ParametersFields.maxTemperaturePrice));
                }
            }
        });
        listOfParameters.get(11).addListener(new ClickListener() {  /// refrigeration
            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (ParametersFields.refrigerationLevel >= 9) {
                    return;
                } else {
                    ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                    ParametersFields.setRefrigeration(1);
                    Label label = (Label) listOfParameters.get(9);
                    label.setText("REFRIGERATION: " + ParametersFields.refrigeration);
                    Label label2 = (Label) listOfParameters.get(10);
                    label2.setText("Cost: " + gameScreen.parseToString(ParametersFields.refrigerationPrice));
                }
            }
        });
        listOfParameters.get(14).addListener(new ClickListener() { /// chance
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.criticalChanceLevel < 101) {
                    ParametersFields.setCriticalChance(0.5);
                    Label label = (Label) listOfParameters.get(12);
                    label.setText("CRIT CHANCE: " + ParametersFields.criticalChance + "%");
                    Label label2 = (Label) listOfParameters.get(13);
                    label2.setText("Cost: " + gameScreen.parseToString(ParametersFields.criticalChancePrice));
                }
            }
        });
        listOfParameters.get(17).addListener(new ClickListener() {   /// critdamage
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.criticalDamageLevel < 101) {
                    ParametersFields.setCriticalDamage(0.025);
                    Label label = (Label) listOfParameters.get(15);
                    label.setText(String.format(Locale.US,"CRIT DAMAGE: %.1f%%",
                            (ParametersFields.criticalDamage * 100)));
                    Label label2 = (Label) listOfParameters.get(16);
                    label2.setText("Cost: " + gameScreen.parseToString(ParametersFields.criticalDamagePrice));
                }
            }
        });
        listOfParameters.get(20).addListener(new ClickListener() { ///  poison
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.poisonChanceLevel < 51) {
                    ParametersFields.setPoisonChance(1);
                    Label label = (Label) listOfParameters.get(18);
                    label.setText(String.format(Locale.US,"POISON CHANCE: %.1f%%",
                            (ParametersFields.poisonChance / 2)));
                    Label label2 = (Label) listOfParameters.get(19);
                    label2.setText("Cost: " + gameScreen.parseToString(ParametersFields.poisonChancePrice));
                }
            }
        });
        listOfParameters.get(23).addListener(new ClickListener() { /// freeze
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.freezeChanceLevel < 51) {
                    ParametersFields.setFreezeChance(1);
                    Label label = (Label) listOfParameters.get(21);
                    label.setText(String.format(Locale.US,"FREEZE CHANCE: %.1f%%",
                            (ParametersFields.freezeChance / 2)));
                    Label label2 = (Label) listOfParameters.get(22);
                    label2.setText("Cost: " + gameScreen.parseToString(ParametersFields.freezeChancePrice));
                }
            }
        });


    }
    @Override
    public void show() {
        //Gdx.input.setInputProcessor(overlayStage);
    }

    @Override
    public void render(float v) {
        overlayStage.act(Math.min(v, 1 / 30f));
        overlayStage.draw();
    }

    @Override
    public void resize(int i, int i1) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {
        overlayStage.getRoot().setVisible(true);
    }

    @Override
    public void hide() {
        //Gdx.input.setInputProcessor(null);
        overlayStage.getRoot().setVisible(false);
    }

    @Override
    public void dispose() {

    }
}
