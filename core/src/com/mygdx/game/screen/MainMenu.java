package com.mygdx.game.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.MyGdxGame;
import com.mygdx.game.settings.GameSettings;


public class MainMenu extends ScreenAdapter {
    public OrthographicCamera camera;
    private Music music;
    private MyGdxGame game;
    private Texture background;
    private TextureRegion backgroundRegion;
    private Texture play, quit, options, title;

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

        background = new Texture("mainBackground.png");
        backgroundRegion = new TextureRegion(background, 0, 0, 300, 150);

        play = new Texture("fonts/play.png");
        quit = new Texture("fonts/quit.png");
        options = new Texture("fonts/options.png");

        title = new Texture("fonts/title.png");

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

    private boolean isQuit(float x, float y) {

        int width = 50 + play.getWidth();
        int height = 100 + play.getHeight();

        return x >= 50 && x <= width
                && y >= 110 && y <= height;
    }

    private boolean isPlay(float x, float y) {

        int width = 50 + play.getWidth();
        int height = 200 + play.getHeight();

        return x >= 50 && x <= width
                && y >= 210 && y <= height;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(0, 0, 0.2f, 1);
        camera.update();
        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        game.batch.draw(backgroundRegion, 0, 0, GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT);
        game.batch.draw(play, 50, 200);
        game.batch.draw(options, 50, 150);
        game.batch.draw(quit, 50, 100);
        game.batch.draw(title, 80, 0);
        game.batch.end();
    }

    @Override
    public void hide() {
        music.dispose();
        Gdx.input.setInputProcessor(null);
    }

}
