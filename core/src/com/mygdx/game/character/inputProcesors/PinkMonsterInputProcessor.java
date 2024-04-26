package com.mygdx.game.character.inputProcesors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.physics.box2d.Body;

public class PinkMonsterInputProcessor extends InputAdapter {
    private final Body body;
    private final float speed = 50f;

    public PinkMonsterInputProcessor(Body body) {
        this.body = body;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                body.setLinearVelocity(-speed, body.getLinearVelocity().y);
                break;
            case Input.Keys.D:
                body.setLinearVelocity(speed, body.getLinearVelocity().y);
                break;
            case Input.Keys.W:
                body.setLinearVelocity(body.getLinearVelocity().x, speed);
                break;
            case Input.Keys.S:
                body.setLinearVelocity(body.getLinearVelocity().x, -speed);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                body.setLinearVelocity(0, body.getLinearVelocity().y);
                break;
            case Input.Keys.W:
            case Input.Keys.S:
                body.setLinearVelocity(body.getLinearVelocity().x, 0);
                break;
        }
        return true;
    }
}
