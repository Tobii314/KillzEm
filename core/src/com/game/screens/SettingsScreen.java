package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.KiilzEm;
import com.game.MusicsAndSounds;
import com.game.buttons.SettingButtons;

import static com.game.MusicsAndSounds.musicSliderValue;
import static com.game.MusicsAndSounds.soundSliderValue;

public class SettingsScreen implements Screen {
    MainMenuScreen mainMenuScreen;
    Array<Actor> listOfButtons = new Array<>();
    SettingButtons settingButtons;
    private final Stage overlayStage;
    Image overlayImage;
    KiilzEm game;
    private void setupAnimation() {
        MoveToAction moveAction = new MoveToAction();
        MoveToAction moveAction2 = new MoveToAction();

        moveAction.setPosition(0, 0);
        moveAction.setDuration(0.5f);

        moveAction2.setPosition(0,-900);
        moveAction2.setDuration(0.5f);
        overlayImage.addAction(moveAction);
        settingButtons.table.addAction(moveAction2);
    }
    public SettingsScreen(KiilzEm game, MainMenuScreen mainMenuScreen) {
        this.game = game;
        this.mainMenuScreen = mainMenuScreen;
        overlayStage = new Stage(new ScreenViewport());
        Texture overlayTexture = new Texture("SettingsScreen.png");
        overlayImage = new Image(overlayTexture);
        overlayImage.setPosition(0,Gdx.graphics.getHeight());
        overlayImage.setSize(1920, 1080);
        overlayStage.addActor(overlayImage);
        settingButtons = new SettingButtons(this);
        overlayStage.addActor(settingButtons.table);
        setupAnimation();
        listOfButtons = settingButtons.table.getChildren();

        listOfButtons.get(0).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                mainMenuScreen.game.setScreen(new MainMenuScreen(game));
            }
        });
        listOfButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                //report
            }
        });
        settingButtons.finalMusicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                musicSliderValue = settingButtons.finalMusicSlider.getValue();
                game.music.setVolume(musicSliderValue);
                MusicsAndSounds.counter++;

            }
        });
        settingButtons.finalSoundSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                soundSliderValue = settingButtons.finalSoundSlider.getValue();
                long id = MusicsAndSounds.sound.play();
                MusicsAndSounds.sound.setVolume(id, soundSliderValue);
                MusicsAndSounds.counter++;

            }
        });
    }
    @Override
    public void show() {
        Gdx.input.setInputProcessor(overlayStage);
    }

    @Override
    public void render(float delta) {
        overlayStage.act(Math.min(delta, 1 / 30f));
        overlayStage.draw();
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
        Gdx.input.setInputProcessor(null);
        overlayStage.dispose();
    }

    @Override
    public void dispose() {

    }
}
