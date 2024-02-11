package com.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.game.screens.MainMenuScreen;

public class MainMenuButtons {
    MainMenuScreen screen;
    private Skin skin;
    MainMenuScreen stage;
    final String STR_PLAY = "PLAY";
    final String STR_SETTINGS ="SETTINGS";
    final String STR_EXIT ="EXIT";
    public MainMenuButtons(MainMenuScreen screen, MainMenuScreen stage) {
        this.screen = screen;
        this.stage = stage;
        skin = new Skin(Gdx.files.internal("skin/button.json"));
        addButtons();
    }

    public void addButtons() {
        TextButton button1 = new TextButton(STR_PLAY, skin);
        button1.setSize(300, 100);
        button1.setPosition(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 50);
        stage.stage.addActor(button1);

        TextButton button2 = new TextButton(STR_SETTINGS, skin);
        button2.setSize(300, 100);
        button2.setPosition(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 200);
        stage.stage.addActor(button2);

        TextButton button3 = new TextButton(STR_EXIT, skin);
        button3.setSize(300, 100);
        button3.setPosition(Gdx.graphics.getWidth() / 2 - 200, Gdx.graphics.getHeight() / 2 - 350);
        stage.stage.addActor(button3);
    }
}
