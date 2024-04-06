package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.MainMenuScreen;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KillzEm extends Game {
	public static ArrayList<Boolean> listOfDead = new ArrayList<Boolean>();
	public SpriteBatch batch;
	public SpriteBatch batch2;
	public BitmapFont font;
	public BitmapFont font1;
	public BitmapFont font2;
	public BitmapFont font3;
	public BitmapFont font4;
	public BitmapFont font5;
	public BitmapFont font6;
	public BitmapFont font7;
	public BitmapFont font8;
	public BitmapFont fontPause1;
	public BitmapFont fontPause2;
	public Music music;
	protected FileHandle readerArr;
	protected FileHandle readerConfig;
	protected FileHandle readerParameter;
	private FileHandle writerArr;
	private FileHandle writerConfig;
	private FileHandle writerParameter;
	private BufferedReader fileReaderArr;
	private BufferedReader fileReaderConfig;
	private BufferedReader fileReaderParameter;
	@Override
	public void create () {
		batch = new SpriteBatch();
		batch2 = new SpriteBatch();
		addFonts();
		readerArr = Gdx.files.internal("data/mob.txt");
		readerConfig = Gdx.files.internal("data/config.txt");
		readerParameter = Gdx.files.internal("data/parameter.txt");
		fileReaderArr = new BufferedReader(readerArr.reader());
		fileReaderConfig = new BufferedReader(readerConfig.reader());
		fileReaderParameter = new BufferedReader(readerParameter.reader());
		writerArr = Gdx.files.local("data/mob.txt");
		writerConfig = Gdx.files.local("data/config.txt");
		writerParameter = Gdx.files.local("data/parameter.txt");
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/music.mp3"));
		ConfigurationFields.soundClick = Gdx.audio.newSound(Gdx.files.internal("audio/click.mp3"));
		ConfigurationFields.cannonExplosion = Gdx.audio.newSound(Gdx.files.internal("audio/ExplosionCannon.mp3"));
		ConfigurationFields.bulletExplosion = Gdx.audio.newSound(Gdx.files.internal("audio/explosion.mp3"));
		setConfigurations();
		setParameter();
		music.setVolume(ConfigurationFields.musicSliderValue);
		music.setLooping(true);
		music.play();
		setListOfDead();
		this.setScreen(new MainMenuScreen(this));
	}
	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
		batch.dispose();
		batch2.dispose();
		music.dispose();
		disposeFonts();
		saveListOfDead();
		saveConfigurations();
		saveParameter();
	}
	public void setListOfDead() {
		String line;
		for (int i = 0; i < 26; i++) {
			try {
				line = fileReaderArr.readLine();
				if (Objects.equals(line.trim(), "0")) {
					listOfDead.add(false);
				} else {
					listOfDead.add(true);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	public void setConfigurations() {
		String line;
			try {
				line = fileReaderConfig.readLine();
				line = extractNumbers(line);
				ConfigurationFields.musicSliderValue = Float.parseFloat(line);
				line = fileReaderConfig.readLine();
				line = extractNumbers(line);
				ConfigurationFields.soundSliderValue = Float.parseFloat(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	public void setParameter() {
		ParametersFields.money = Float.parseFloat(readingStr());
		ParametersFields.damage = Float.parseFloat(readingStr());
		ParametersFields.damagePrice = Float.parseFloat(readingStr());
		ParametersFields.damageLevel = Integer.parseInt(readingStr());
		ParametersFields.damagePower = Integer.parseInt(readingStr());
		ParametersFields.criticalChance = Double.parseDouble(readingStr());
		ParametersFields.criticalChancePrice = Float.parseFloat(readingStr());
		ParametersFields.criticalChanceLevel = Integer.parseInt(readingStr());
		ParametersFields.criticalDamage = Double.parseDouble(readingStr());
		ParametersFields.criticalDamagePrice = Float.parseFloat(readingStr());
		ParametersFields.criticalDamageLevel = Integer.parseInt(readingStr());
		ParametersFields.poisonChance = Double.parseDouble(readingStr());
		ParametersFields.poisonChancePrice = Float.parseFloat(readingStr());
		ParametersFields.poisonChanceLevel = Integer.parseInt(readingStr());
		ParametersFields.freezeChance = Double.parseDouble(readingStr());
		ParametersFields.freezeChancePrice = Float.parseFloat(readingStr());
		ParametersFields.freezeChanceLevel = Integer.parseInt(readingStr());
		ParametersFields.refrigeration = Integer.parseInt(readingStr());
		ParametersFields.refrigerationPrice = Float.parseFloat(readingStr());
		ParametersFields.refrigerationLevel = Integer.parseInt(readingStr());
		ParametersFields.maxTemperature = Integer.parseInt(readingStr());
		ParametersFields.maxTemperaturePrice = Float.parseFloat(readingStr());
		ParametersFields.maxTemperatureLevel = Integer.parseInt(readingStr());
		ParametersFields.localCounter = Integer.parseInt(readingStr());
	}
	public void saveParameter() {
		writerParameter.writeString("", false);
		writerParameter.writeString("money:" + String.format(Locale.US,"%.6f", ParametersFields.money) + "\n", true);
		writerParameter.writeString("damage:" + String.format(Locale.US,"%.6f",ParametersFields.damage) + "\n", true);
		writerParameter.writeString("damagePrice:" + String.format(Locale.US,"%.6f",ParametersFields.damagePrice) + "\n", true);
		writerParameter.writeString("damageLevel:" + ParametersFields.damageLevel + "\n", true);
		writerParameter.writeString("damagePower:" + ParametersFields.damagePower + "\n", true);
		writerParameter.writeString("criticalChance:" + String.format(Locale.US,"%.2f",ParametersFields.criticalChance) + "\n", true);
		writerParameter.writeString("criticalChancePrice:" + String.format(Locale.US,"%.6f",ParametersFields.criticalChancePrice) + "\n", true);
		writerParameter.writeString("criticalChanceLevel:" + ParametersFields.criticalChanceLevel + "\n", true);
		writerParameter.writeString("criticalDamage:" + String.format(Locale.US, "%.2f",ParametersFields.criticalDamage) + "\n", true);
		writerParameter.writeString("criticalDamagePrice:" + String.format(Locale.US, "%.6f",ParametersFields.criticalDamagePrice) + "\n", true);
		writerParameter.writeString("criticalDamageLevel:" + ParametersFields.criticalDamageLevel + "\n", true);
		writerParameter.writeString("poisonChance:" + String.format(Locale.US, "%.2f",ParametersFields.poisonChance) + "\n", true);
		writerParameter.writeString("poisonChancePrice:" + String.format(Locale.US, "%.6f",ParametersFields.poisonChancePrice) + "\n", true);
		writerParameter.writeString("poisonChanceLevel:" + ParametersFields.poisonChanceLevel + "\n", true);
		writerParameter.writeString("freezeChance:" + String.format(Locale.US, "%.2f",ParametersFields.freezeChance) + "\n", true);
		writerParameter.writeString("freezeChancePrice:" + String.format(Locale.US, "%.6f",ParametersFields.freezeChancePrice) + "\n", true);
		writerParameter.writeString("freezeChanceLevel:" + ParametersFields.freezeChanceLevel + "\n", true);
		writerParameter.writeString("refrigeration:" + ParametersFields.refrigeration + "\n", true);
		writerParameter.writeString("refrigerationPrice:" + String.format(Locale.US, "%.6f",ParametersFields.refrigerationPrice) + "\n", true);
		writerParameter.writeString("refrigerationLevel:" + ParametersFields.refrigerationLevel + "\n", true);
		writerParameter.writeString("maxTemperature:" + ParametersFields.maxTemperature + "\n", true);
		writerParameter.writeString("maxTemperaturePrice:" + String.format(Locale.US, "%.6f",ParametersFields.maxTemperaturePrice) + "\n", true);
		writerParameter.writeString("maxTemperatureLevel:" + ParametersFields.maxTemperatureLevel + "\n", true);
		writerParameter.writeString("localCount:" + ParametersFields.localCounter + "\n", true);
	}
	private String readingStr() {
		String line;
        try {
            line = fileReaderParameter.readLine();
			line = extractNumbers(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
		return line;
	}
	public void saveListOfDead() {
		writerArr.writeString("", false);
		for (int i = 0; i < 26; i++) {
			writerArr.writeString(boolToString(listOfDead.get(i)) + "\n", true);
		}
	}
	public void saveConfigurations() {
		writerConfig.writeString("", false);
		writerConfig.writeString("musicValue:" + ConfigurationFields.musicSliderValue + "\n", true);
		writerConfig.writeString("soundValue:" + ConfigurationFields.soundSliderValue + "\n", true);
	}
	public String boolToString(boolean value) {
		if (value) {
			return "1";
		} else {
			return "0";
		}
	}
	private void addFonts() {
		font = new BitmapFont();
		font1 = new BitmapFont();
		font2 = new BitmapFont();
		font3 = new BitmapFont();
		font4 = new BitmapFont();
		font5 = new BitmapFont();
		font6 = new BitmapFont();
		font7 = new BitmapFont();
		font8 = new BitmapFont();
		fontPause1 = new BitmapFont();
		fontPause2 = new BitmapFont();
	}
	private void disposeFonts() {
		font.dispose();
		font2.dispose();
		font3.dispose();
		font4.dispose();
		font5.dispose();
		font6.dispose();
		font7.dispose();
		font8.dispose();
	}
	private static String extractNumbers(String line) {
		Pattern pattern = Pattern.compile("\\d+(\\.\\d+)?");
		Matcher matcher = pattern.matcher(line);
		StringBuilder result = new StringBuilder();
		while (matcher.find()) {
			result.append(matcher.group()).append(" ");
		}
		return result.toString().trim();
	}
}
