package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ScreenUtils;
import com.game.KiilzEm;
import com.game.buttons.MainMenuButtons;

public class MainMenuScreen implements Screen {
    public Stage stage;
    Texture image;
    KiilzEm game;
    public MainMenuScreen(KiilzEm game) {
        this.game = game;
    }
    @Override
    public void show() {
        image = new Texture("MainMenu.png");
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        MainMenuButtons mainMenuButtons = new MainMenuButtons(this, this);

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
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
