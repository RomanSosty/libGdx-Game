package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.settings.GameSettings;


public class MainMenu extends ScreenAdapter {
    private final MyGdxGame game;
    public OrthographicCamera camera;
    private Music music;
    private Texture play, quit, options;

    public MainMenu(MyGdxGame game) {
        this.game = game;
    }

    @Override
    public void show() {
        camera = new OrthographicCamera(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        camera.position.set(GameSettings.SCREEN_WIDTH / 2, GameSettings.SCREEN_HEIGHT / 2, 0);

        music = Gdx.audio.newMusic(Gdx.files.internal("music/the_field_of_dreams.mp3"));
        music.setLooping(true);
        music.play();

        play = new Texture("buttons/play.png");
        quit = new Texture("buttons/quit.png");
        options = new Texture("buttons/options.png");

        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {

                Vector3 clickCoordinates = new Vector3(screenX, screenY, 0);
                camera.unproject(clickCoordinates);

                if (isPlay(clickCoordinates.x, clickCoordinates.y)) {
                    game.setScreen(new GameScreen(game, camera));
                }
                if (isQuit(clickCoordinates.x, clickCoordinates.y)) {
                    Gdx.app.exit();
                }
                return false;
            }
        });
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0.22f, 0.25f, 0.24f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(play, GameSettings.SCREEN_WIDTH / 2 - play.getWidth() / 2, 200);
        game.batch.draw(options, GameSettings.SCREEN_WIDTH / 2 - options.getWidth() / 2, 150);
        game.batch.draw(quit, GameSettings.SCREEN_WIDTH / 2 - quit.getWidth() / 2, 100);
        game.batch.end();
    }

    @Override
    public void hide() {
        music.dispose();
        Gdx.input.setInputProcessor(null);
    }

    private boolean isQuit(float x, float y) {

        int width = (GameSettings.SCREEN_WIDTH / 2 - quit.getWidth() / 2) + quit.getWidth();
        int height = 100 + quit.getHeight();

        return x >= 50 && x <= width
                && y >= 110 && y <= height;
    }

    private boolean isPlay(float x, float y) {

        int width = (GameSettings.SCREEN_WIDTH / 2 - play.getWidth() / 2) + play.getWidth();
        int height = 200 + play.getHeight();

        return x >= 50 && x <= width
                && y >= 210 && y <= height;
    }

}
