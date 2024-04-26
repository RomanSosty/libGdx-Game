package com.mygdx.game.inputProcesors;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.character.PinkMonster;

public class PinkMonsterInputProcessor extends InputAdapter {
    private final Body body;
    private final PinkMonster pinkMonster;
    private final float speed = 50f;

    public PinkMonsterInputProcessor(PinkMonster pinkMonster) {
        this.pinkMonster = pinkMonster;
        this.body = pinkMonster.getBody();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                body.setLinearVelocity(-speed, body.getLinearVelocity().y);
                pinkMonster.setIsWalking(true);
                break;
            case Input.Keys.D:
                body.setLinearVelocity(speed, body.getLinearVelocity().y);
                pinkMonster.setIsWalking(true);
                break;
            case Input.Keys.W:
                body.setLinearVelocity(body.getLinearVelocity().x, speed);
                pinkMonster.setIsWalking(true);
                break;
            case Input.Keys.S:
                body.setLinearVelocity(body.getLinearVelocity().x, -speed);
                pinkMonster.setIsWalking(true);
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
                pinkMonster.setIsWalking(false);
                break;
            case Input.Keys.W:
            case Input.Keys.S:
                body.setLinearVelocity(body.getLinearVelocity().x, 0);
                pinkMonster.setIsWalking(false);
                break;
        }
        return true;
    }
}
