package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.Bullets.Bullet;
import com.game.Bullets.FreezeBullet;
import com.game.Bullets.PoisonBullet;
import com.game.ConfigurationFields;
import com.game.KiilzEm;
import com.game.ParametersFields;
import com.game.buttons.GameButtons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class GameScreen implements Screen {
    boolean freeze;
    double result;
    Bullet bullet;
    Array<Actor> listOfButtons = new Array<>();
    ArrayList<Bullet> listOfBullets = new ArrayList<>();
    public Stage buttonStage;
    public Stage bulletStage;
    Texture image;
    KiilzEm game;
    public GameScreen(KiilzEm game) {
        this.game = game;
    }
    @Override
    public void show() {
        image = new Texture("GameScreen.jpg");
        InputMultiplexer multiplexer = new InputMultiplexer();
        buttonStage = new Stage(new ScreenViewport());
        bulletStage = new Stage(new ScreenViewport());
        multiplexer.addProcessor(buttonStage);
        multiplexer.addProcessor(bulletStage);
        Gdx.input.setInputProcessor(multiplexer);

        new GameButtons(this);
        listOfButtons = buttonStage.getActors();

        listOfButtons.get(0).addListener(clickListener);
        listOfButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.sound.play(ConfigurationFields.soundSliderValue);
                game.setScreen(new MainMenuScreen(game));
                buttonStage.dispose();
                bulletStage.dispose();
            }
        } );
    }
    ClickListener clickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            result = RandomNumber();
            freeze = coinFlip();
            if ((result < ParametersFields.poisonChance) && (!freeze)) {
                bullet = new PoisonBullet(ParametersFields.texture3, ParametersFields.damage,
                        ParametersFields.criticalDamage, ParametersFields.criticalChance,
                        ParametersFields.poisonChance);
                bulletStage.addActor(bullet);
                listOfBullets.add(bullet);
            }
            else if ((result < ParametersFields.freezeChance) && (freeze)) {
                bullet = new FreezeBullet(ParametersFields.texture2, ParametersFields.damage,
                        ParametersFields.criticalDamage, ParametersFields.criticalChance,
                        ParametersFields.freezeChance);
                bulletStage.addActor(bullet);
                listOfBullets.add(bullet);
            } else {
                bullet = new Bullet(ParametersFields.texture1, ParametersFields.damage,
                        ParametersFields.criticalDamage, ParametersFields.criticalChance);
                bulletStage.addActor(bullet);
                listOfBullets.add(bullet);
            }
        }
    };
    int deltaX = 25;
    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(image, 0, 0);

        if (!listOfBullets.isEmpty()) {
            Iterator<Bullet> iterator = listOfBullets.iterator();
            while (iterator.hasNext()) {
                Bullet currentBullet = iterator.next();
                game.batch.draw(currentBullet.getTexture(), currentBullet.getX(), currentBullet.getY());
                currentBullet.setX(deltaX);
                if (currentBullet.getX() > 1500) {
                    iterator.remove();
                }
            }
        }
        game.batch.end();

        buttonStage.act();
        buttonStage.draw();

        bulletStage.act();
        bulletStage.draw();
    }
    public boolean coinFlip() {
        Random random = new Random();
        return random.nextBoolean();
    }
    private double RandomNumber() {
        Random random = new Random();
        int randomInt = random.nextInt(101);
        double randomDecimal = random.nextInt(10) / 10.0;
        result = randomInt + randomDecimal;
        System.out.println(result);
        return result;
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