package com.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.game.screens.GameScreen;

public class GameButtons {
    GameScreen stage;
    private final Skin skinPanzer;
    private final Skin skinMenuButton;

    public GameButtons(GameScreen stage) {
        skinPanzer = new Skin(Gdx.files.internal("skin2/skinPanzer.json"));
        skinMenuButton = new Skin(Gdx.files.internal("skin2/menuButton.json"));
        this.stage = stage;
        addButtons();
    }

    public void addButtons() {
        ImageButton panzer = new ImageButton(skinPanzer);
        panzer.setPosition(0, 0);

        stage.buttonStage.addActor(panzer);

        ImageTextButton menu = new ImageTextButton("MENU", skinMenuButton);
        menu.setPosition(1700,925);
        menu.setSize(100f,100f);
        stage.buttonStage.addActor(menu);
    }
}
