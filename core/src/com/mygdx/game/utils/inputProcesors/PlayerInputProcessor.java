package com.mygdx.game.utils.inputProcesors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.settings.WalkDirection;
import com.mygdx.game.world.character.Player;

public class PlayerInputProcessor extends InputAdapter {
    private final Body body;
    private final Player player;

    public PlayerInputProcessor(Player player) {
        this.player = player;
        this.body = player.getBody();
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                playerMove(WalkDirection.LEFT, -player.getSpeed(), body.getLinearVelocity().y);
                break;
            case Input.Keys.D:
                playerMove(WalkDirection.RIGHT, player.getSpeed(), body.getLinearVelocity().y);
                break;
            case Input.Keys.W:
                playerMove(WalkDirection.UP, body.getLinearVelocity().x, player.getSpeed());
                break;
            case Input.Keys.S:
                playerMove(WalkDirection.DOWN, body.getLinearVelocity().x, -player.getSpeed());
                break;
            case Input.Keys.SPACE:
                player.setIsAttacking(true);
                break;
            case Input.Keys.ESCAPE:
                Gdx.app.exit();
                break;
        }
        return true;
    }

    private void playerMove(WalkDirection direction, float xSpeed, float ySpeed) {
        body.setLinearVelocity(xSpeed, ySpeed);
        player.setIsWalking(true);
        player.setWalkDirection(direction);
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                playerStopMove(0, body.getLinearVelocity().y);
                break;
            case Input.Keys.W:
            case Input.Keys.S:
                playerStopMove(body.getLinearVelocity().x, 0);
                break;
            case Input.Keys.SPACE:
                player.setIsAttacking(false);
                break;
        }
        return true;
    }

    private void playerStopMove(float xSpeed, float ySpeed) {
        body.setLinearVelocity(xSpeed, ySpeed);
        player.setIsWalking(false);
    }
}
