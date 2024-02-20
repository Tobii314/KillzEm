package com.game.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.game.MusicsAndSounds;
import com.game.screens.SettingsScreen;

public class SettingButtons {
    private boolean isDownloaded = true;
    SettingsScreen settingsScreen;
    public Slider finalMusicSlider;
    public Slider finalSoundSlider;
    public Table table;
    private final Skin skin;
    final String STR_BACK = "BACK";
    final String STR_MUSIC = "Music";
    final String STR_SOUND = "Sound";
    final String STR_REPORT = "Report of bugs";


    public SettingButtons(SettingsScreen settingsScreen) {
        skin = new Skin(Gdx.files.internal("skin2/skin.json"));
        table = new Table();
        table.setFillParent(true);
        this.settingsScreen = settingsScreen;
        addButtons();
    }
    public void addButtons() {
        TextButton buttonBack = new TextButton(STR_BACK, skin);
        buttonBack.setSize(300,50);
        buttonBack.setPosition(450,Gdx.graphics.getHeight());
        table.addActor(buttonBack);

        TextButton buttonReport = new TextButton(STR_REPORT, skin);
        buttonReport.setSize(300,50);
        buttonReport.setPosition(1150,Gdx.graphics.getHeight());
        table.addActor(buttonReport);


        Slider musicSlider = new Slider(0, 1, 0.1f, false, skin);
        musicSlider.setSize(300,40);
        musicSlider.setPosition(800, Gdx.graphics.getHeight()+400);
        if (MusicsAndSounds.counter > 0) {
            musicSlider.setValue(MusicsAndSounds.musicSliderValue);
        } else {
            musicSlider.setValue(1f);
        }
        table.addActor(musicSlider);
        finalMusicSlider = musicSlider;

        Slider soundSlider = new Slider(0, 1, 0.1f, false, skin);
        soundSlider.setSize(300,40);
        soundSlider.setPosition(800, Gdx.graphics.getHeight()+250);
        if (MusicsAndSounds.counter > 0) {
            soundSlider.setValue(MusicsAndSounds.soundSliderValue);
        } else {
            soundSlider.setValue(1f);
        }
        table.addActor(soundSlider);
        finalSoundSlider = soundSlider;


        Label musicText = new Label(STR_MUSIC, skin);
        musicText.setSize(0,0);
        musicText.setFontScale(1.5f);
        musicText.setPosition(900,Gdx.graphics.getHeight()+475);
        table.addActor(musicText);

        Label soundText = new Label(STR_SOUND, skin);
        soundText.setSize(0,0);
        soundText.setFontScale(1.5f);
        soundText.setPosition(900,Gdx.graphics.getHeight()+325);
        table.addActor(soundText);
        isDownloaded = false;
    }
}
