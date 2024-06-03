package com.mygdx.game.inputProcesors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.character.Player;

public class PlayerInputProcessor extends InputAdapter {
    private final Body body;
    private final Player player;

    public PlayerInputProcessor(Player player) {
        this.player = player;
        this.body = player.getBody();
    }

    @Override
    public boolean keyDown(int keycode) {
        //TODO: divide walk, attack and othet move to different methods
        switch (keycode) {
            case Input.Keys.A:
                body.setLinearVelocity(-player.getSpeed(), body.getLinearVelocity().y);
                player.setIsWalking(true);
                break;
            case Input.Keys.D:
                body.setLinearVelocity(player.getSpeed(), body.getLinearVelocity().y);
                player.setIsWalking(true);
                break;
            case Input.Keys.W:
                body.setLinearVelocity(body.getLinearVelocity().x, player.getSpeed());
                player.setIsWalking(true);
                break;
            case Input.Keys.S:
                body.setLinearVelocity(body.getLinearVelocity().x, -player.getSpeed());
                player.setIsWalking(true);
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

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
            case Input.Keys.D:
                body.setLinearVelocity(0, body.getLinearVelocity().y);
                player.setIsWalking(false);
                break;
            case Input.Keys.W:
            case Input.Keys.S:
                body.setLinearVelocity(body.getLinearVelocity().x, 0);
                player.setIsWalking(false);
                break;
            case Input.Keys.SPACE:
                player.setIsAttacking(false);
                break;
        }
        return true;
    }
}
