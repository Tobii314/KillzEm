package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.KiilzEm;
import com.game.MusicsAndSounds;
import com.game.buttons.MainMenuButtons;

public class MainMenuScreen implements Screen {
    public MainMenuScreen savedObject;
    boolean isButtonClicked = false;
    private final SettingsScreen screen;
    Array<Actor> listOfbuttons = new Array<>();
    public Stage stage;
    Texture image;
    KiilzEm game;
    public MainMenuScreen(KiilzEm game) {
        this.game = game;
        screen = new SettingsScreen(game, this);
    }
    @Override
    public void show() {
        image = new Texture("MainMenu.png");
        game.font = new BitmapFont(Gdx.files.internal("text/text.fnt"));
        game.font.getData().setScale(2.0f);
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
        new MainMenuButtons(this, this);
        listOfbuttons = stage.getActors();

        listOfbuttons.get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                MusicsAndSounds.sound.play(MusicsAndSounds.soundSliderValue);
                game.setScreen(new GameScreen(game));
                stage.dispose();
            }
        });
        listOfbuttons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                isButtonClicked = true;
                stage.dispose();
                //hide();
            }
        });
        listOfbuttons.get(2).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                    Gdx.app.exit();
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(image, 0, 0);
        game.font.draw(game.batch, "KiilzEm", Gdx.graphics.getWidth() / 2 - 275, 950);
        game.batch.end();
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        if (isButtonClicked) {
            screen.show();
            screen.render(delta);
        }
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
        stage.dispose();
        image.dispose();

    }
}
