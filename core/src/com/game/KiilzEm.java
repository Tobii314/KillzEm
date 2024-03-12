package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.MainMenuScreen;
import java.util.ArrayList;

import static com.game.ConfigurationFields.id;
import static com.game.ConfigurationFields.soundClick;

public class KiilzEm extends Game {
	public static ArrayList<Boolean> listOfDead = new ArrayList<Boolean>();
	public SpriteBatch batch;
	public BitmapFont font;
	public BitmapFont font2;
	public BitmapFont font3;
	public BitmapFont font4;
	public BitmapFont font5;
	public BitmapFont font6;
	public BitmapFont font7;
	public BitmapFont font8;


	public Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		addFonts();
		this.setScreen(new MainMenuScreen(this));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		ConfigurationFields.soundClick = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
		ConfigurationFields.cannonExplosion = Gdx.audio.newSound(Gdx.files.internal("ExplosionCannon.mp3"));
		ConfigurationFields.bulletExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
		music.setLooping(true);
		music.play();
		setListOfDead();
	}
	@Override
	public void render () {
		super.render();
	}
	@Override
	public void dispose () {
		batch.dispose();
		music.dispose();
		disposeFonts();
	}
	public void setListOfDead() {
		for (int i = 0; i < 26; i++) {
			listOfDead.add(false);
		}
	}
	private void addFonts() {
		font = new BitmapFont();
		font2 = new BitmapFont();
		font3 = new BitmapFont();
		font4 = new BitmapFont();
		font5 = new BitmapFont();
		font6 = new BitmapFont();
		font7 = new BitmapFont();
		font8 = new BitmapFont();
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
}
