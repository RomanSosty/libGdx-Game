package com.mygdx.game.settings;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.character.Player;
import com.mygdx.game.character.WoodEnemy;
import com.mygdx.game.world.GroundMap;

public class GameContactListener implements ContactListener {
    private long lastCollisionTime = 0;
    private Object objectA, objectB;

    @Override
    public void beginContact(Contact contact) {
        setCollisionObject(contact);

        //Time for delete duplicate collision
        long currentTime = System.currentTimeMillis();
        long diffCurrentAndLastCollisionTime = currentTime - lastCollisionTime;

        if (diffCurrentAndLastCollisionTime > 1) {
            System.out.println("objekt a :" + objectA + " objekt b:" + objectB);
            enemyHitWall();
            playerHitEnemy();
            lastCollisionTime = currentTime;
        }
    }

    private void enemyHitWall() {
        if (objectA instanceof WoodEnemy && objectB instanceof GroundMap) {
            ((WoodEnemy) objectA).turnAroud();
        } else if (objectA instanceof GroundMap && objectB instanceof WoodEnemy) {
            ((WoodEnemy) objectB).turnAroud();
        }
    }

    private void playerHitEnemy() {
        if (objectA instanceof WoodEnemy && objectB instanceof Player && ((Player) objectB).isAttacking()) {
            ((WoodEnemy) objectA).setIsDead();
        } else if (objectA instanceof Player && objectB instanceof WoodEnemy && ((Player) objectA).isAttacking()) {
            ((WoodEnemy) objectB).setIsDead();
        }
    }

    private void setCollisionObject(Contact contact) {
        objectA = contact.getFixtureA().getBody().getUserData();
        objectB = contact.getFixtureB().getBody().getUserData();
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
