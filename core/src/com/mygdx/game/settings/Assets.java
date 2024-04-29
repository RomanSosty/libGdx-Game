package com.mygdx.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {

    public static Texture playButton, optionsButton, quitButton, playerUI, playerTexture, walkSheet;
    public static Music music;
    public static TiledMap map;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        playButton = loadTexture("buttons/play.png");
        optionsButton = loadTexture("buttons/options.png");
        quitButton = loadTexture("buttons/quit.png");
        playerUI = loadTexture("UI.png");
        playerTexture = loadTexture("characters/Pink_Monster/Pink_Monster.png");
        walkSheet = loadTexture("characters/Pink_Monster/Pink_Monster_Walk_6.png");


        map = new TmxMapLoader().load("map/level-1.tmx");


        music = Gdx.audio.newMusic(Gdx.files.internal("music/the_field_of_dreams.mp3"));
        music.setLooping(true);
        music.play();
    }

}
