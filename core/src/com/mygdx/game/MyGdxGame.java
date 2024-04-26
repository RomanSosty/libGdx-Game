package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mygdx.game.screen.MainMenu;

public class MyGdxGame extends Game {

    public SpriteBatch batch;
    public ShapeRenderer shapeRenderer;

    @Override
    public void create() {
        batch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        setScreen(new MainMenu(this));
    }

    @Override
    public void dispose() {
        batch.dispose();
        shapeRenderer.dispose();
    }

}
