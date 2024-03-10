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
	public Music music;
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		ConfigurationFields.soundClick = Gdx.audio.newSound(Gdx.files.internal("click.mp3"));
		ConfigurationFields.cannonExplosion = Gdx.audio.newSound(Gdx.files.internal("ExplosionCannon.mp3"));
		ConfigurationFields.bulletExplosion = Gdx.audio.newSound(Gdx.files.internal("explosion.mp3"));
		///id = ConfigurationFields.soundClick.play();
		//soundClick.pause(id);
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
		font.dispose();
		music.dispose();
	}
	public void setListOfDead() {
		for (int i = 0; i < 26; i++) {
			listOfDead.add(false);
		}
	}
}
