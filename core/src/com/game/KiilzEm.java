package com.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.game.screens.MainMenuScreen;

public class KiilzEm extends Game {
	public SpriteBatch batch;
	public BitmapFont font;
	public Music music;

	
	@Override
	public void create () {
		batch = new SpriteBatch();
		font = new BitmapFont();
		this.setScreen(new MainMenuScreen(this));
		music = Gdx.audio.newMusic(Gdx.files.internal("music.mp3"));
		MusicsAndSounds.sound = Gdx.audio.newSound(Gdx.files.internal("ae.mp3"));
		music.play();
		if (!music.isPlaying()) {
			music.play();
		}
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
}
