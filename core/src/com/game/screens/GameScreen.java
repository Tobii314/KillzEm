package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.game.*;
import com.game.Bullets.Bullet;
import com.game.Bullets.FreezeBullet;
import com.game.Bullets.PoisonBullet;
import com.game.buttons.GameButtons;

import java.util.*;

public class GameScreen implements Screen {
    boolean freeze;
    double result;
    Bullet bullet;
    Array<Actor> listOfButtons = new Array<>();
    ArrayList<Bullet> listOfBullets = new ArrayList<>();
    public Stage buttonStage;
    public Stage bulletStage;
    public Stage enemiesStage;
    Texture image;
    Texture explosionImage;
    KiilzEm game;
    int deltaX = 25;
    Map<Integer, Enemy> hashMap = new HashMap<>();
    AddEnemys addEnemys;

    public Map<Integer, Enemy> addMap() {
        Map<Integer, Enemy> map = new HashMap<>();
        ///1 тир
        map.put(1, addEnemys.firstTierEnemy(EnemiesTexture.texture1, 100));
        map.put(2, addEnemys.firstTierEnemy(EnemiesTexture.texture2, 250));
        map.put(3, addEnemys.firstTierEnemy(EnemiesTexture.texture3, 800));
        map.put(4, addEnemys.firstTierEnemy(EnemiesTexture.texture4, 3_000));
        map.put(5, addEnemys.firstTierEnemy(EnemiesTexture.texture5, 12_000));
        //// 2 тир
        map.put(6, addEnemys.secondTierEnemy(EnemiesTexture.texture6, 90_000, 0.5));
        map.put(7, addEnemys.secondTierEnemy(EnemiesTexture.texture7, 350_000, 1.0));
        map.put(8, addEnemys.secondTierEnemy(EnemiesTexture.texture8, 1_300_000, 1.8));
        map.put(9, addEnemys.secondTierEnemy(EnemiesTexture.texture9, 4_500_000, 3.0));
        map.put(10, addEnemys.secondTierEnemy(EnemiesTexture.texture10, 15_500_000, 4.5));
        //// 3 тир
        map.put(11, addEnemys.thirdTierEnemy(EnemiesTexture.texture11, 120_000_000, 6.0, 10_000)); //reg 0.5%
        map.put(12, addEnemys.thirdTierEnemy(EnemiesTexture.texture12, 500_000_000, 8.0, 50_000)); // reg 0.625%
        map.put(13, addEnemys.thirdTierEnemy(EnemiesTexture.texture13, 1_750_000_000, 10.0, 220_000)); // reg 0.75%
        map.put(14, addEnemys.thirdTierEnemy(EnemiesTexture.texture14, 6_000_000_000L, 14.0, 1_000_000)); // reg 1%
        map.put(15, addEnemys.thirdTierEnemy(EnemiesTexture.texture15, 20_000_000_000L, 16.5, 4_150_000)); // reg 1.25%
        //// 4 тир
        map.put(16, addEnemys.fourthTierEnemy(EnemiesTexture.texture16, 125_000_000_000L, 20.0, 35_000_000, 3.0)); // reg 1.625%
        map.put(17, addEnemys.fourthTierEnemy(EnemiesTexture.texture17, 420_000_000_000L, 22.5, 280_000_000, 5.0)); // reg 2%
        map.put(18, addEnemys.fourthTierEnemy(EnemiesTexture.texture18, 1_000_000_000_000L, 25.0, 800_000_000, 7.5)); // reg 4.75%
        map.put(19, addEnemys.fourthTierEnemy(EnemiesTexture.texture19, 1_000_000_000_000L, 25.0, 800_000_000, 7.5)); // reg 4.75%
        map.put(20, addEnemys.fourthTierEnemy(EnemiesTexture.texture20, 1_000_000_000_000L, 25.0, 800_000_000, 7.5)); // reg 4.75%
        //// 5 тир
        map.put(21, addEnemys.fifthTierEnemy(EnemiesTexture.texture21, 999_999_999_999_999_999L, 25.0, 80_000_000_000_000L, 50.0, 50.0)); // reg 5%
        map.put(22, addEnemys.fifthTierEnemy(EnemiesTexture.texture22, 999_999_999_999_999_999L, 25.0, 80_000_000_000_000L, 50.0, 50.0)); // reg 5%
        map.put(23, addEnemys.fifthTierEnemy(EnemiesTexture.texture23, 999_999_999_999_999_999L, 25.0, 80_000_000_000_000L, 50.0, 50.0)); // reg 5%
        map.put(24, addEnemys.fifthTierEnemy(EnemiesTexture.texture24, 999_999_999_999_999_999L, 25.0, 80_000_000_000_000L, 50.0, 50.0)); // reg 5%
        map.put(25, addEnemys.fifthTierEnemy(EnemiesTexture.texture25, 999_999_999_999_999_999L, 25.0, 80_000_000_000_000L, 50.0, 50.0)); // reg 5%
        map.put(26, addEnemys.fifthTierEnemy(EnemiesTexture.texture26, 999_999_999_999_999_999L, 25.0, 80_000_000_000_000L, 50.0, 50.0)); // reg 5%
        return map;
    }


    public GameScreen(KiilzEm game) {
        this.game = game;
        addEnemys = new AddEnemys();
        hashMap = addMap();
    }

    @Override
    public void show() {
        image = new Texture("GameScreen.jpg");
        explosionImage = new Texture("explosion.png");
        InputMultiplexer multiplexer = new InputMultiplexer();
        buttonStage = new Stage(new ScreenViewport());
        bulletStage = new Stage(new ScreenViewport());
        enemiesStage = new Stage(new ScreenViewport());
        multiplexer.addProcessor(buttonStage);
        multiplexer.addProcessor(bulletStage);
        multiplexer.addProcessor(enemiesStage);
        Gdx.input.setInputProcessor(multiplexer);

        new GameButtons(this);
        enemiesStage.addActor(hashMap.get(1));
        listOfButtons = buttonStage.getActors();

        listOfButtons.get(0).addListener(clickListener);
        listOfButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.sound.play(ConfigurationFields.soundSliderValue);
                game.setScreen(new MainMenuScreen(game));
                buttonStage.dispose();
                bulletStage.dispose();
                enemiesStage.dispose();
            }
        });
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
            } else if ((result < ParametersFields.freezeChance) && (freeze)) {
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

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 1, 1, 1);
        game.batch.begin();
        game.batch.draw(image, 0, 0);

        //координаты для врагов 1000, -150
        game.batch.draw(hashMap.get(5).getTexture(), 1000, -150);

        if (!listOfBullets.isEmpty()) {
            Iterator<Bullet> iterator = listOfBullets.iterator();
            while (iterator.hasNext()) {
                Bullet currentBullet = iterator.next();
                game.batch.draw(currentBullet.getTexture(), currentBullet.getX(), currentBullet.getY());
                currentBullet.setX(deltaX);
                if (currentBullet.getX() > 1500) {
                    iterator.remove();
                    game.batch.draw(explosionImage, 1500, 125);
                }
            }
        }
        game.batch.end();

        buttonStage.act();
        buttonStage.draw();

        bulletStage.act();
        bulletStage.draw();

        enemiesStage.act();
        enemiesStage.draw();
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