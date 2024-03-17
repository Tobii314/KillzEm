package com.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
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

        listOfParameters.get(3).addListener(new ClickListener() {  /// damage
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                ParametersFields.setDamage(0.004001f);
                Label label = (Label) listOfParameters.get(1);
                label.setText("DAMAGE: " + gameScreen.parseToString(ParametersFields.damage));
            }
        });
        listOfParameters.get(6).addListener(new ClickListener() {  ///max temp
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
            }
        });
        listOfParameters.get(9).addListener(new ClickListener() {  /// refrigeration
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
            }
        });
        listOfParameters.get(12).addListener(new ClickListener() { /// chance
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.criticalChanceLevel < 50) {
                    ParametersFields.setCriticalChance(1);
                    Label label = (Label) listOfParameters.get(10);
                    label.setText("CRIT CHANCE: " + ParametersFields.criticalChance + "%");
                }
            }
        });
        listOfParameters.get(15).addListener(new ClickListener() {   /// critdamage
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.criticalDamageLevel < 100) {
                    ParametersFields.setCriticalDamage(0.025);
                    Label label = (Label) listOfParameters.get(13);
                    label.setText(String.format(Locale.US,"CRIT DAMAGE: %.1f%%", (ParametersFields.criticalDamage * 100)));
                }
            }
        });
        listOfParameters.get(18).addListener(new ClickListener() { ///  poison
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.poisonChanceLevel < 50) {
                    ParametersFields.setPoisonChance(1);
                    Label label = (Label) listOfParameters.get(16);
                    label.setText(String.format(Locale.US,"POISON CHANCE: %.1f%%", (ParametersFields.poisonChance / 2)));
                }
            }
        });
        listOfParameters.get(21).addListener(new ClickListener() { /// freeze
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (ParametersFields.freezeChanceLevel < 50) {
                    ParametersFields.setFreezeChance(1);
                    Label label = (Label) listOfParameters.get(19);
                    label.setText(String.format(Locale.US,"FREEZE CHANCE: %.1f%%", (ParametersFields.freezeChance / 2)));
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
