package com.mygdx.game.character;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.inputProcesors.PinkMonsterInputProcessor;

public class PinkMonster {
    private final World world;
    private final Texture texture, walkSheet;
    private final Sprite sprite;
    private boolean isWalking = false;
    private Animation<TextureRegion> walkAnimation;
    private Body body;
    private float stateTime = 0f;

    public PinkMonster(World world) {
        this.world = world;
        makeBody();
        texture = new Texture("characters/Pink_Monster/Pink_Monster.png");
        walkSheet = new Texture("characters/Pink_Monster/Pink_Monster_Walk_6.png");
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 6, walkSheet.getHeight());

        Array<TextureRegion> walkFrames = new Array<>();
        for (int i = 0; i < 6; i++) {
            walkFrames.add(tmp[0][i]);
        }

        walkAnimation = new Animation<>(0.1f, walkFrames);
        sprite = new Sprite(texture);
        sprite.setPosition(body.getPosition().x, body.getPosition().y);

        PinkMonsterInputProcessor inputProcessor = new PinkMonsterInputProcessor(this);
        Gdx.input.setInputProcessor(inputProcessor);
    }

    private void makeBody() {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(32, 48);

        body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(0.5f, 1f);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;

        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void update(float delta) {
        stateTime += delta;

        if (isWalking) {
            sprite.setRegion(walkAnimation.getKeyFrame(stateTime, true));
        } else {
            sprite.setRegion(texture);
        }
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

    public Body getBody() {
        return this.body;
    }

    public void setIsWalking(boolean isWalking) {
        this.isWalking = isWalking;
    }
}
