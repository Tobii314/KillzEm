package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.MoveToAction;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.KiilzEm;
import com.game.buttons.SettingButtons;

public class SettingsScreen implements Screen {
    SettingButtons settingButtons;
    private Stage overlayStage;
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
    public SettingsScreen(KiilzEm game) {
        this.game = game;
        overlayStage = new Stage(new ScreenViewport());
        Texture overlayTexture = new Texture("SettingsScreen.png");
        overlayImage = new Image(overlayTexture);
        overlayImage.setPosition(0,Gdx.graphics.getHeight());
        overlayImage.setSize(1920, 1080);
        overlayStage.addActor(overlayImage);
        settingButtons = new SettingButtons();
        overlayStage.addActor(settingButtons.table);
        setupAnimation();
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
    }

    @Override
    public void dispose() {

    }
}
