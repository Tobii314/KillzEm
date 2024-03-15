package com.game.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.game.KiilzEm;
import com.game.buttons.ParametersButtons;


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
