package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.mygdx.game.character.inputProcesors.PinkMonsterInputProcessor;

public class PinkMonster {
    private final World world;
    private final Texture texture;
    private final Sprite sprite;
    private Body body;

    public PinkMonster(World world) {
        this.world = world;
        makeBody();
        texture = new Texture("characters/Pink_Monster/Pink_Monster.png");
        sprite = new Sprite(texture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

        PinkMonsterInputProcessor inputProcessor = new PinkMonsterInputProcessor(body);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    private void makeBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(32, 32);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render(SpriteBatch batch) {
        sprite.setPosition(body.getPosition().x - sprite.getWidth() / 2,
                body.getPosition().y - sprite.getHeight() / 2);

        sprite.setRotation(body.getAngle() * MathUtils.radiansToDegrees);

        sprite.draw(batch);
    }

    public void dispose() {
        texture.dispose();
    }
}
