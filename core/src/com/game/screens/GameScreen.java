package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.ConfigurationFields;
import com.game.KiilzEm;
import com.game.buttons.GameButtons;

public class GameScreen implements Screen {
    Array<Actor> listOfButtons = new Array<>();
    public Stage stage;
    Texture image;
    KiilzEm game;
    public GameScreen(KiilzEm game) {
        this.game = game;
    }
    @Override
    public void show() {
        image = new Texture("GameScreen.jpg");

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        new GameButtons(this);
        listOfButtons = stage.getActors();

        listOfButtons.get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {

            }
        } );
        listOfButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.sound.play(ConfigurationFields.soundSliderValue);
                game.setScreen(new MainMenuScreen(game));
                stage.dispose();
            }
        } );

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.batch.end();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
