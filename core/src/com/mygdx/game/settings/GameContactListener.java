package com.mygdx.game.settings;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.character.WoodEnemy;
import com.mygdx.game.world.GroundMap;

public class GameContactListener implements ContactListener {
    private long lastCollisionTime = 0;

    @Override
    public void beginContact(Contact contact) {
        //Time for duplicate collision
        long currentTime = System.currentTimeMillis();

        Object objectA = contact.getFixtureA().getBody().getUserData();
        Object objectB = contact.getFixtureB().getBody().getUserData();

        //Detect collision for Enemy and wall
        if (currentTime - lastCollisionTime > 1) {
            if (objectA instanceof WoodEnemy && objectB instanceof GroundMap) {
                ((WoodEnemy) objectA).turnAroud();
            } else if (objectA instanceof GroundMap && objectB instanceof WoodEnemy) {
                ((WoodEnemy) objectB).turnAroud();
            }
            lastCollisionTime = currentTime;
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }
}
