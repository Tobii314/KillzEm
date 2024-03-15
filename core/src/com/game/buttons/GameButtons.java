package com.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.game.screens.GameScreen;

public class GameButtons {
    GameScreen stage;
    private final Skin skinPanzer;
    private final Skin skinMenuButton;
    private final Skin rightArrow;
    private final Skin leftArrow;
    private final Skin skinParameters;



    public GameButtons(GameScreen stage) {
        skinPanzer = new Skin(Gdx.files.internal("skin2/skinPanzer.json"));
        skinMenuButton = new Skin(Gdx.files.internal("skin2/menuButton.json"));
        rightArrow = new Skin(Gdx.files.internal("ArrowRight/skinArrowRight.json"));
        leftArrow = new Skin(Gdx.files.internal("ArrowLeft/skinArrow.json"));
        skinParameters = new Skin(Gdx.files.internal("skin1/button.json"));
        this.stage = stage;
        addButtons();
    }

    public void addButtons() {
        ImageButton panzer = new ImageButton(skinPanzer);
        panzer.setPosition(0, 0);


        stage.buttonStage.addActor(panzer);

        ImageTextButton menu = new ImageTextButton("MENU", skinMenuButton);
        menu.setPosition(50,925);
        menu.setSize(100f,100f);
        stage.buttonStage.addActor(menu);

        ImageButton rightArrowButton = new ImageButton(rightArrow);
        rightArrowButton.setPosition(1650, 850);
        stage.buttonStage.addActor(rightArrowButton);

        ImageButton leftArrowButton = new ImageButton(leftArrow);
        leftArrowButton.setPosition(1550, 850);
        stage.buttonStage.addActor(leftArrowButton);

        TextButton parametersButton = new TextButton("Parameters", skinParameters);
        parametersButton.setSize(450,100);
        parametersButton.setPosition(350,925);
        stage.buttonStage.addActor(parametersButton);

        ImageTextButton pause = new ImageTextButton("PAUSE", skinMenuButton);
        pause.setPosition(175,925);
        pause.setSize(100f,100f);
        stage.buttonStage.addActor(pause);
    }
}
