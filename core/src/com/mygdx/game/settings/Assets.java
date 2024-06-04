package com.mygdx.game.settings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

public class Assets {

    public static Texture playButton, optionsButton, quitButton;
    public static Texture playerUI, playerTexture, playerWalkSheet, playerAttack, playerHealth;
    public static Texture woodEnemyTexture, woodEnemyWalkSheet;
    public static Texture blueCandy;
    public static Music music;
    public static TiledMap map;

    public static Texture loadTexture(String file) {
        return new Texture(Gdx.files.internal(file));
    }

    public static void load() {
        loadGameUI();
        loadPlayer();
        loadEnemy();
        loadGameSetting();
        loadCandies();
    }

    private static void loadGameUI() {
        playButton = loadTexture("buttons/play.png");
        optionsButton = loadTexture("buttons/options.png");
        quitButton = loadTexture("buttons/quit.png");
    }

    private static void loadGameSetting() {
        map = new TmxMapLoader().load("map/level-1.tmx");
        music = Gdx.audio.newMusic(Gdx.files.internal("music/the_field_of_dreams.mp3"));
        music.setLooping(true);
        music.play();
    }

    private static void loadPlayer() {
        playerUI = loadTexture("UI.png");
        playerHealth = loadTexture("health.png");
        playerTexture = loadTexture("characters/Pink_Monster/Pink_Monster.png");
        playerWalkSheet = loadTexture("characters/Pink_Monster/Pink_Monster_Walk_6.png");
        playerAttack = loadTexture("characters/Pink_Monster/Pink_Monster_Attack2_6.png");
    }

    private static void loadEnemy() {
        woodEnemyTexture = loadTexture("characters/WoodLog.png");
        woodEnemyWalkSheet = loadTexture("characters/logWalk.png");
    }

    private static void loadCandies() {
        blueCandy = loadTexture("yaycandies/size1/wrappedsolid_blue.png");
    }

    public static void dispose() {
        music.dispose();
        playButton.dispose();
        optionsButton.dispose();
        quitButton.dispose();
        map.dispose();

        playerUI.dispose();
        playerTexture.dispose();
        playerWalkSheet.dispose();
        playerAttack.dispose();

        woodEnemyTexture.dispose();
        woodEnemyTexture.dispose();
    }

}
