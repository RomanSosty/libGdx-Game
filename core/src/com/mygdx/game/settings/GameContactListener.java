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
            enemyHitWall();
            enemyHitPlayer();
            playerHitEnemy();
            lastCollisionTime = currentTime;
        }
    }

    private void enemyHitWall() {
        if (isEnemyAndWall(objectA, objectB)) {
            ((WoodEnemy) objectA).turnAroud();
        } else if (isEnemyAndWall(objectB, objectA)) {
            ((WoodEnemy) objectB).turnAroud();
        }
    }

    private void enemyHitPlayer() {
        if (isEnemyAndPlayer(objectA, objectB)) {
            ((Player) objectB).setDownHealth();
        } else if (isEnemyAndPlayer(objectB, objectA)) {
            ((Player) objectA).setDownHealth();
        }
    }

    private void playerHitEnemy() {
        if (isEnemyAndPlayer(objectA, objectB) && ((Player) objectB).isAttacking()) {
            ((WoodEnemy) objectA).setIsDead();
        } else if (isEnemyAndPlayer(objectB, objectA) && ((Player) objectA).isAttacking()) {
            ((WoodEnemy) objectB).setIsDead();
        }
    }

    private void setCollisionObject(Contact contact) {
        objectA = contact.getFixtureA().getBody().getUserData();
        objectB = contact.getFixtureB().getBody().getUserData();
    }

    private boolean isEnemyAndPlayer(Object obj1, Object obj2) {
        return obj1 instanceof WoodEnemy && obj2 instanceof Player;
    }

    private boolean isEnemyAndWall(Object obj1, Object obj2) {
        return obj1 instanceof WoodEnemy && obj2 instanceof GroundMap;
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
