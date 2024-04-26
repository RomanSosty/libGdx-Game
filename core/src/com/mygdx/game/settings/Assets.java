package com.mygdx.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;

public class Assets {

    public static Texture playButton, optionsButton, quitButton;
    public static Music music;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        playButton = loadTexture("buttons/play.png");
        optionsButton = loadTexture("buttons/options.png");
        quitButton = loadTexture("buttons/quit.png");


        music = Gdx.audio.newMusic(Gdx.files.internal("music/the_field_of_dreams.mp3"));
        music.setLooping(true);
        music.play();
    }

}
