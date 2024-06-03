package com.mygdx.game.inputProcesors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.screen.GameScreen;
import com.mygdx.game.screen.MainMenu;

public class MainMenuInputProcessor extends InputAdapter {

    private final MainMenu mainMenu;
    private final MyGdxGame game;
    private final OrthographicCamera camera;

    public MainMenuInputProcessor(MainMenu mainMenu, MyGdxGame game) {
        this.mainMenu = mainMenu;
        this.game = game;
        this.camera = mainMenu.getCamera();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
        camera.unproject(clickCoordinates);

        if (mainMenu.selectPlay(clickCoordinates.x, clickCoordinates.y)) {
            game.setScreen(new GameScreen(game, camera));
        }
        if (mainMenu.selectQuit(clickCoordinates.x, clickCoordinates.y)) {
            Gdx.app.exit();
        }
        return false;
    }
}
