package com.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
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
    public static boolean isDead;
    ProgressBar hitPointsBar;
    ProgressBar deBuffBar;
    public static Integer counterForEnemies = 1;
    boolean freeze;
    double result;
    Bullet bullet;
    Array<Actor> listOfButtons = new Array<>();
    ArrayList<Bullet> listOfBullets = new ArrayList<>();
    ArrayList<DamageLabel> numbersList = new ArrayList<>();
    public Stage buttonStage;
    public Stage bulletStage;
    public Stage enemiesStage;
    public Stage parametersStage;
    Texture image;
    Texture coinImage;
    Texture imagePause;
    Texture explosionImage;
    KiilzEm game;
    int deltaX = 25;
    public Map<Integer, Enemy> hashMap = new HashMap<>();
    public static Map<Integer, Float> hpEnemies = new HashMap<>();
    public static Map<Integer, Float> earningEnemies = new HashMap<>();
    private final Skin skinProgressBar;
    private final Skin skinDeBuffBar;
    AddEnemys addEnemys;
    int poisonCounter = 0;
    int freezeCounter = 0;
    boolean isPoisoned = false;
    boolean isFrozen = false;
    double resultOfCritChance;
    double resultOfResistPoison;
    double resultOfResistFreeze;
    Skin skinOfNumberDamage;
    Skin skinOfNumberCritDamage;
    private GameParametersScreen screen;
    boolean isButtonClicked = false;
    boolean isButtonDoubled = false;
    private ImageButton panzer;
    private int localeCounter = 0;
    private boolean paused;
    private int counterA = 0;
    private int coinX = 1350;
    private int coinY = 400;


    public GameScreen(KiilzEm game) {
        this.game = game;
        addEnemys = new AddEnemys();
        hashMap = addMap();
        hpEnemies = addMapHitPoints();
        earningEnemies = addEarnings();
        skinProgressBar = new Skin(Gdx.files.internal("ProgressBar/bar.json"));
        skinOfNumberDamage = new Skin(Gdx.files.internal("DamageString/damage.json"));
        skinOfNumberCritDamage = new Skin(Gdx.files.internal("DamageString/critDamage.json"));
        skinDeBuffBar = new Skin(Gdx.files.internal("ProgressBar/neon-ui.json"));
    }
    @Override
    public void show() {
        paused = false;
        image = new Texture("GameScreen.jpg");
        coinImage = new Texture("coin.png");
        imagePause = new Texture("GameScreen.png");
        addFonts();
        explosionImage = new Texture("explosion.png");
        InputMultiplexer multiplexer = new InputMultiplexer();
        buttonStage = new Stage(new ScreenViewport());
        bulletStage = new Stage(new ScreenViewport());
        enemiesStage = new Stage(new ScreenViewport());
        parametersStage = new Stage(new ScreenViewport());
        multiplexer.addProcessor(buttonStage);
        multiplexer.addProcessor(bulletStage);
        multiplexer.addProcessor(enemiesStage);
        multiplexer.addProcessor(parametersStage);
        Gdx.input.setInputProcessor(multiplexer);

        screen = new GameParametersScreen(game, this, parametersStage);
        new GameButtons(this);
        enemiesStage.addActor(hashMap.get(1));
        listOfButtons = buttonStage.getActors();

        hitPointsBar = new ProgressBar(0f, hashMap.get(GameScreen.counterForEnemies).getHitPoints(),
                hashMap.get(GameScreen.counterForEnemies).getHitPoints() / 1000f, false, skinProgressBar);
        hitPointsBar.setValue(hpEnemies.get(counterForEnemies));
        hitPointsBar.setSize(300,100);
        hitPointsBar.setPosition(1500,400);
        buttonStage.addActor(hitPointsBar);
        deBuffBar = new ProgressBar(0f, 600f, 1f, false, skinDeBuffBar);
        deBuffBar.setSize(300, 10);
        deBuffBar.setPosition(100,250);
        deBuffBar.setVisible(false);
        buttonStage.addActor(deBuffBar);
        panzer = (ImageButton) listOfButtons.get(0);

        panzer.addListener(clickListener);
        listOfButtons.get(1).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                game.setScreen(new MainMenuScreen(game));
                buttonStage.dispose();
                bulletStage.dispose();
            }
        });
        listOfButtons.get(2).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if ((counterForEnemies == 26) || !(KiilzEm.listOfDead.get(counterForEnemies-1))) {
                    return;
                } else {
                    counterForEnemies++;
                    changeProgressBar();
                }
            }
        });
        listOfButtons.get(3).addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                if (counterForEnemies == 1) {
                    return;
                } else {
                    counterForEnemies--;
                    changeProgressBar();
                }
            }
        });
        listOfButtons.get(4).addListener(new ClickListener() {
           @Override
           public void clicked(InputEvent event, float x, float y) {
               if (isButtonDoubled) {
                   ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                   screen.hide();
                   isButtonDoubled = false;
               } else {
                   ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
                   isButtonClicked = true;
                   isButtonDoubled = true;
                   screen.resume();
               }
           }
        });
        listOfButtons.get(5).addListener(new ClickListener() {
           public void clicked(InputEvent event, float x, float y) {
               ConfigurationFields.soundClick.play(ConfigurationFields.soundSliderValue);
               pause();
           }
        });



    }

    @Override
    public void render(float delta) {
        if (paused) {
            ScreenUtils.clear(1, 1, 1, 1);
            game.batch.begin();
            game.batch.draw(image, 0, 0);
            game.batch.draw(imagePause, 0, 0);
            game.fontPause1.draw(game.batch, "GAME PAUSED!", 400,700);
            game.fontPause2.draw(game.batch, "Click on the screen to resume", 450,500);
            if (Gdx.input.justTouched()) {
                resume();
                paused = false;
            }
            game.batch.end();
        } else {
            ScreenUtils.clear(1, 1, 1, 1);
            game.batch.begin();
            game.batch.draw(image, 0, 0);
            game.batch.draw(coinImage, 875, 950);
            game.font3.draw(game.batch, " " + parseToString(ParametersFields.money), 925, 987.5f);

            game.font1.draw(game.batch, "Heating: " + ParametersFields.currentTemperature + "/"
                    + ParametersFields.maxTemperature, 100, 350);

            if (localeCounter >= 60) {
                ParametersFields.setCurrentTemperature(ParametersFields.refrigeration);
                localeCounter = 0;
            } else {
                localeCounter++;
            }
            if (isDead) {
                game.batch.draw(coinImage, coinX, coinY);
                if (counterA > 30) {
                    isDead = false;
                    counterA = 0;
                    coinX = 1350;
                    coinY = 400;
                }
                coinX -= 2;
                coinY += 2;
                counterA++;
            }

            if (ParametersFields.currentTemperature >= ParametersFields.maxTemperature) {
                ParametersFields.localeCounterDebuff = 0;
                deBuffBar.setVisible(true);
                panzer.setDisabled(true);
                panzer.setTouchable(Touchable.disabled);
            }
            if (panzer.isDisabled()) {
                if (ParametersFields.localeCounterDebuff >= 600) {
                    deBuffBar.setVisible(false);
                    panzer.setDisabled(false);
                    panzer.setTouchable(Touchable.enabled);
                    ParametersFields.localeCounterDebuff = 0;
                } else {
                    deBuffBar.setValue(ParametersFields.localeCounterDebuff);
                    ParametersFields.localeCounterDebuff++;
                }
            }

            //координаты для врагов 1000, -150
            game.batch.draw(hashMap.get(counterForEnemies).getTexture(), 1000, -150);
            game.font3.draw(game.batch, "Health: " + parseToString(hashMap.get(counterForEnemies).
                    getHitPoints()), 1525, 750);
            game.font4.draw(game.batch, "Earning: " + parseToString(hashMap.get(counterForEnemies).getEarning()),
                    1525, 710);

            if (counterForEnemies >= 6) {
                game.font5.draw(game.batch, "Shield: " + hashMap.get(counterForEnemies).getShield(),
                        1525, 670);
            }
            if (counterForEnemies >= 11) {
                game.font6.draw(game.batch, "Regen: "  + parseToString(hashMap.get(counterForEnemies).
                        getRegeneration()),  1525, 630);
            }
            if (counterForEnemies >= 16) {
                game.font7.draw(game.batch, "Poison resist: " + hashMap.get(counterForEnemies).getPoisonResistance() + " %",
                        1525, 590);
            }
            if (counterForEnemies >= 21) {
                game.font8.draw(game.batch, "Freeze resist: " + hashMap.get(counterForEnemies).getFreezeResistance() + " %",
                        1525, 550);
            }

            if (!(hashMap.get(counterForEnemies).getRegeneration() == 0f)) {
                if (isFrozen) {
                    if (freezeCounter < 180) {
                        freezeCounter++;
                    } else {
                        isFrozen = false;
                        freezeCounter = 0;
                    }
                } else {
                    hashMap.get(counterForEnemies).setHitPointsPlus(hashMap.get(counterForEnemies).getRegeneration());
                    hitPointsBar.setValue(hashMap.get(counterForEnemies).getHitPoints() + hashMap.get(counterForEnemies).getRegeneration());
                }
            }

            if (isPoisoned) {
                if (poisonCounter < 180) {
                    hashMap.get(counterForEnemies).setHitPoints(ParametersFields.damage / 36);
                    hitPointsBar.setValue(hashMap.get(counterForEnemies).getHitPoints() - ParametersFields.damage / 36);
                    poisonCounter++;
                } else {
                    isPoisoned = false;
                    poisonCounter = 0;
                }
            }
            if (!listOfBullets.isEmpty()) {
                Iterator<Bullet> iterator = listOfBullets.iterator();
                while (iterator.hasNext()) {
                    Bullet currentBullet = iterator.next();
                    game.batch.draw(currentBullet.getTexture(), currentBullet.getX(), currentBullet.getY());
                    currentBullet.setX(deltaX);
                    if (currentBullet.getX() > 1500) {
                        if (currentBullet instanceof FreezeBullet) {
                            whenBulletComes(currentBullet);
                            resultOfResistFreeze = RandomNumber();
                            isFrozen = !(resultOfResistFreeze <= hashMap.get(counterForEnemies).getFreezeResistance());
                            iterator.remove();
                            game.batch.draw(explosionImage, 1500, 125);
                        } if (currentBullet instanceof PoisonBullet) {
                            whenBulletComes(currentBullet);
                            resultOfResistPoison = RandomNumber();
                            isPoisoned = !(resultOfResistPoison <= hashMap.get(counterForEnemies).getPoisonResistance());
                            iterator.remove();
                            game.batch.draw(explosionImage, 1500, 125);

                        } if (!(currentBullet instanceof PoisonBullet) && !(currentBullet instanceof FreezeBullet)) {
                            whenBulletComes(currentBullet);
                            iterator.remove();
                            game.batch.draw(explosionImage, 1500, 125);
                        }
                    }
                }
            }
            if (!(numbersList.isEmpty())) {
                Iterator<DamageLabel> labelIterator = numbersList.iterator();
                while (labelIterator.hasNext()) {
                    DamageLabel currentLabel = labelIterator.next();
                    if (currentLabel.getIsCrit()) {
                        game.font2.draw(game.batch, currentLabel.getNumber(),
                                currentLabel.getX(), currentLabel.getY());
                    } else {
                        game.font.draw(game.batch, currentLabel.getNumber(),
                                currentLabel.getX(), currentLabel.getY());
                    }
                    currentLabel.setX(3);
                    currentLabel.setY(3);
                    if (currentLabel.getX() < 1180) {
                        labelIterator.remove();
                    }
                }
            }
            game.batch.end();
            buttonStage.act();
            buttonStage.draw();
            bulletStage.act();
            bulletStage.draw();

            if (isButtonClicked) {
                screen.render(delta);
            }
        }
        }
    protected ClickListener clickListener = new ClickListener() {
        @Override
        public void clicked(InputEvent event, float x, float y) {
            if (panzer.isDisabled()) {

            } else {
                result = RandomNumber();
                freeze = coinFlip();
                ConfigurationFields.cannonExplosion.play(ConfigurationFields.soundSliderValue);
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
                ParametersFields.currentTemperature += 5;
            }
        }
    };
    private void changeProgressBar() {
        buttonStage.getRoot().removeActor(hitPointsBar);
        hashMap.get(counterForEnemies).setNewHitPoints(hpEnemies.get(counterForEnemies));
        hitPointsBar = new ProgressBar(0f, hpEnemies.get(counterForEnemies),
                hpEnemies.get(counterForEnemies) / 1000f, false, skinProgressBar);
        hitPointsBar.setSize(300,100);
        hitPointsBar.setPosition(1500,400);
        hitPointsBar.setValue(hpEnemies.get(counterForEnemies));
        buttonStage.addActor(hitPointsBar);
    }
    private void whenBulletComes(Bullet currentBullet) {
        ConfigurationFields.bulletExplosion.play(ConfigurationFields.soundSliderValue);
        resultOfCritChance = RandomNumber();
        addDamageFont();
        if (resultOfCritChance <= ParametersFields.criticalChance) {
            if (!(hashMap.get(counterForEnemies).getShield() == 0f)) {
                hashMap.get(counterForEnemies).setHitPoints(addShield(hashMap.get(counterForEnemies).getShield(), (float)(ParametersFields.damage * ParametersFields.criticalDamage)));
                hitPointsBar.setValue(hashMap.get(counterForEnemies).getHitPoints());

            } else {
                hashMap.get(counterForEnemies).setHitPoints((float) (currentBullet.damage * ParametersFields.criticalDamage));
                hitPointsBar.setValue(hashMap.get(counterForEnemies).getHitPoints());
            }
        } else {
            if (!(hashMap.get(counterForEnemies).getShield() == 0f)) {
                hashMap.get(counterForEnemies).setHitPoints(addShield(hashMap.get(counterForEnemies).getShield(), ParametersFields.damage));
                hitPointsBar.setValue(hashMap.get(counterForEnemies).getHitPoints());
            } else {
                hashMap.get(counterForEnemies).setHitPoints(currentBullet.damage);
                hitPointsBar.setValue(hashMap.get(counterForEnemies).getHitPoints());
            }
        }
    }
    private float addShield(float shield, float damage) {
        float hundred = 100;
        hundred -= shield;
        float activeHundred = hundred / 100f;
        return damage * activeHundred;
    }
    private void addDamageFont() {
        if (resultOfCritChance <= ParametersFields.criticalChance) {
            numbersList.add(new DamageLabel(parseToString((float)(addShield(hashMap.get(counterForEnemies).getShield(),
                    ParametersFields.damage) * ParametersFields.criticalDamage)), 1275,400,true));
        } else {
            numbersList.add(new DamageLabel(parseToString(addShield(hashMap.get(counterForEnemies).getShield(),
                    ParametersFields.damage)), 1275, 400, false));
        }
    }

    public String parseToString(float damage) {
        String string;
        if (damage < 0.01f) {
            string = (int)(damage * 1_000_000) + "";
        } else if (damage < 1.0f) {
            string = String.format(Locale.US, "%.1f" + "k",(damage * 1000));
        } else if (damage < 1_000.0f) {
            string = String.format(Locale.US, "%.2f" + "m", (damage));
        } else if (damage < 1_000_000.0f) {
            string = String.format(Locale.US, "%.2f" + "b", (damage / 1000));
        } else if (damage < 1_000_000_000.0f){
            string = String.format(Locale.US, "%.2f" + "t", (damage / 1_000_000));
        } else {
            string = String.format(Locale.US, "%.2f" + "q", (damage / 1_000_000_000));
        }
        return string;
    }
    private boolean coinFlip() {
        Random random = new Random();
        return random.nextBoolean();
    }

    private double RandomNumber() {
        Random random = new Random();
        int randomInt = random.nextInt(101);
        double randomDecimal = random.nextInt(10) / 10.0;
        result = randomInt + randomDecimal;
        return result;
    }


    public Map<Integer, Enemy> addMap() {
        Map<Integer, Enemy> map = new HashMap<>();
        ///1 тир
        map.put(1, addEnemys.firstTierEnemy(EnemiesParameters.texture1, EnemiesParameters.earning1, EnemiesParameters.hp1));
        map.put(2, addEnemys.firstTierEnemy(EnemiesParameters.texture2, EnemiesParameters.earning2, EnemiesParameters.hp2));
        map.put(3, addEnemys.firstTierEnemy(EnemiesParameters.texture3, EnemiesParameters.earning3, EnemiesParameters.hp3));
        map.put(4, addEnemys.firstTierEnemy(EnemiesParameters.texture4, EnemiesParameters.earning4, EnemiesParameters.hp4));
        map.put(5, addEnemys.firstTierEnemy(EnemiesParameters.texture5, EnemiesParameters.earning5, EnemiesParameters.hp5));
        //// 2 тир
        map.put(6, addEnemys.secondTierEnemy(EnemiesParameters.texture6, EnemiesParameters.earning6, EnemiesParameters.hp6, 1.5f));
        map.put(7, addEnemys.secondTierEnemy(EnemiesParameters.texture7, EnemiesParameters.earning7, EnemiesParameters.hp7, 3.0f));
        map.put(8, addEnemys.secondTierEnemy(EnemiesParameters.texture8, EnemiesParameters.earning8, EnemiesParameters.hp8, 5.25f));
        map.put(9, addEnemys.secondTierEnemy(EnemiesParameters.texture9, EnemiesParameters.earning9, EnemiesParameters.hp9, 7.5f));
        map.put(10, addEnemys.secondTierEnemy(EnemiesParameters.texture10, EnemiesParameters.earning10, EnemiesParameters.hp10, 11.25f));
        //// 3 тир
        map.put(11, addEnemys.thirdTierEnemy(EnemiesParameters.texture11, EnemiesParameters.earning11, EnemiesParameters.hp11, 13.5f, 0.01f)); //reg 4%  //0.0067f
        map.put(12, addEnemys.thirdTierEnemy(EnemiesParameters.texture12, EnemiesParameters.earning12, EnemiesParameters.hp12, 16.5f, 0.033f)); // reg 4%
        map.put(13, addEnemys.thirdTierEnemy(EnemiesParameters.texture13, EnemiesParameters.earning13, EnemiesParameters.hp13, 19.0f, 0.1125f)); // reg 5%
        map.put(14, addEnemys.thirdTierEnemy(EnemiesParameters.texture14, EnemiesParameters.earning14, EnemiesParameters.hp14, 22.5f, 0.47f)); // reg 7%
        map.put(15, addEnemys.thirdTierEnemy(EnemiesParameters.texture15, EnemiesParameters.earning15, EnemiesParameters.hp15, 26.0f, 1.6f)); // reg 8%
        //// 4 тир
        map.put(16, addEnemys.fourthTierEnemy(EnemiesParameters.texture16, EnemiesParameters.earning16, EnemiesParameters.hp16, 33.75f, 16.67f, 10d)); // reg 10%
        map.put(17, addEnemys.fourthTierEnemy(EnemiesParameters.texture17, EnemiesParameters.earning17, EnemiesParameters.hp17, 36.0f, 41.5f, 15d)); // reg 10%
        map.put(18, addEnemys.fourthTierEnemy(EnemiesParameters.texture18, EnemiesParameters.earning18, EnemiesParameters.hp18, 39.0f, 156.0f, 20d)); // reg 12.5%
        map.put(19, addEnemys.fourthTierEnemy(EnemiesParameters.texture19, EnemiesParameters.earning19, EnemiesParameters.hp19, 41.5f, 415f, 25d)); // reg 12.5%
        map.put(20, addEnemys.fourthTierEnemy(EnemiesParameters.texture20, EnemiesParameters.earning20, EnemiesParameters.hp20, 45.0f, 1_500f, 30d)); // reg 15%
        //// 5 тир
        map.put(21, addEnemys.fifthTierEnemy(EnemiesParameters.texture21, EnemiesParameters.earning21, EnemiesParameters.hp21, 52.5f, 13_125f, 35d, 10d)); // reg 17.5%
        map.put(22, addEnemys.fifthTierEnemy(EnemiesParameters.texture22, EnemiesParameters.earning22, EnemiesParameters.hp22, 55.75f, 40_000f, 37d, 17.5d)); // reg 17.5%
        map.put(23, addEnemys.fifthTierEnemy(EnemiesParameters.texture23, EnemiesParameters.earning23, EnemiesParameters.hp23, 60.0f, 167_000f, 40d, 25d)); // reg 20%
        map.put(24, addEnemys.fifthTierEnemy(EnemiesParameters.texture24, EnemiesParameters.earning24, EnemiesParameters.hp24, 63.5f, 500_000f, 43d, 30d)); // reg 20%
        map.put(25, addEnemys.fifthTierEnemy(EnemiesParameters.texture25, EnemiesParameters.earning25, EnemiesParameters.hp25, 67.0f, 1_650_000f, 45d, 40d)); // reg 22%
        map.put(26, addEnemys.fifthTierEnemy(EnemiesParameters.texture26, EnemiesParameters.earning26, EnemiesParameters.hp26, 70.0f, 4_162_500f, 50d, 50d)); // reg 25%
        return map;
    }
    public Map<Integer, Float> addEarnings() {
        Map<Integer, Float> map = new HashMap<>();
        map.put(1, EnemiesParameters.earning1);
        map.put(2, EnemiesParameters.earning2);
        map.put(3, EnemiesParameters.earning3);
        map.put(4, EnemiesParameters.earning4);
        map.put(5, EnemiesParameters.earning5);
        map.put(6, EnemiesParameters.earning6);
        map.put(7, EnemiesParameters.earning7);
        map.put(8, EnemiesParameters.earning8);
        map.put(9, EnemiesParameters.earning9);
        map.put(10, EnemiesParameters.earning10);
        map.put(11, EnemiesParameters.earning11);
        map.put(12, EnemiesParameters.earning12);
        map.put(13, EnemiesParameters.earning13);
        map.put(14, EnemiesParameters.earning14);
        map.put(15, EnemiesParameters.earning15);
        map.put(16, EnemiesParameters.earning16);
        map.put(17, EnemiesParameters.earning17);
        map.put(18, EnemiesParameters.earning18);
        map.put(19, EnemiesParameters.earning19);
        map.put(20, EnemiesParameters.earning20);
        map.put(21, EnemiesParameters.earning21);
        map.put(22, EnemiesParameters.earning22);
        map.put(23, EnemiesParameters.earning23);
        map.put(24, EnemiesParameters.earning24);
        map.put(25, EnemiesParameters.earning25);
        map.put(26, EnemiesParameters.earning26);
        return map;
    }

    public Map<Integer, Float> addMapHitPoints() {
        Map<Integer, Float> map = new HashMap<>();
        map.put(1, (EnemiesParameters.hp1));
        map.put(2, (EnemiesParameters.hp2));
        map.put(3, (EnemiesParameters.hp3));
        map.put(4, (EnemiesParameters.hp4));
        map.put(5, (EnemiesParameters.hp5));
        map.put(6, (EnemiesParameters.hp6));
        map.put(7, (EnemiesParameters.hp7));
        map.put(8, (EnemiesParameters.hp8));
        map.put(9, (EnemiesParameters.hp9));
        map.put(10, (EnemiesParameters.hp10));
        map.put(11, (EnemiesParameters.hp11));
        map.put(12, (EnemiesParameters.hp12));
        map.put(13, (EnemiesParameters.hp13));
        map.put(14, (EnemiesParameters.hp14));
        map.put(15, (EnemiesParameters.hp15));
        map.put(16, (EnemiesParameters.hp16));
        map.put(17, (EnemiesParameters.hp17));
        map.put(18, (EnemiesParameters.hp18));
        map.put(19, (EnemiesParameters.hp19));
        map.put(20, (EnemiesParameters.hp20));
        map.put(21, (EnemiesParameters.hp21));
        map.put(22, (EnemiesParameters.hp22));
        map.put(23, (EnemiesParameters.hp23));
        map.put(24, (EnemiesParameters.hp24));
        map.put(25, (EnemiesParameters.hp25));
        map.put(26, (EnemiesParameters.hp26));
        return map;
    }
    private void addFonts() {
        game.font = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font1 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font1.getData().setScale(0.7f);
        game.font2 = new BitmapFont(Gdx.files.internal(ParametersFields.critDamageLabel));
        game.font3 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font3.getData().setScale(0.5f);
        game.font4 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font4.getData().setScale(0.5f);
        game.font5 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font5.getData().setScale(0.5f);
        game.font6 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font6.getData().setScale(0.5f);
        game.font7 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font7.getData().setScale(0.5f);
        game.font8 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.font8.getData().setScale(0.5f);
        game.fontPause1 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
        game.fontPause1.getData().setScale(2f);
        game.fontPause2 = new BitmapFont(Gdx.files.internal(ParametersFields.damageLabel));
    }



        @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {
        paused = true;
        buttonStage.getRoot().setTouchable(Touchable.disabled);
        screen.hide();
    }

    @Override
    public void resume() {
        paused = false;
        buttonStage.getRoot().setTouchable(Touchable.enabled);
        isButtonDoubled = false;
    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}