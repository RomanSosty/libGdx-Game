package com.mygdx.game.character;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;

public class WoodEnemy extends Character {
    private float speed = 30f;
    private Body body;
    private Sprite sprite;

    public WoodEnemy(World world) {
        super(world);
        body = makeBody(400, 350, Assets.woodEnemyTexture, this);

        sprite = new Sprite(Assets.woodEnemyTexture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

    }

    public void update(float delta) {
        body.setLinearVelocity(speed, body.getLinearVelocity().y);
    }


    public void render(SpriteBatch batch) {

        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2);

        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);

        sprite.draw(batch);
    }

    public void turnAroud() {
        speed *= -1;
    }

}
