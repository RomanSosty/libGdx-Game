package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.screen.MainMenu;
import com.mygdx.game.settings.Assets;

public class MyGdxGame extends Game {
    private SpriteBatch batch;

    @Override
    public void create() {
        batch = new SpriteBatch();
        Assets.load();
        setScreen(new MainMenu(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        Assets.dispose();
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
