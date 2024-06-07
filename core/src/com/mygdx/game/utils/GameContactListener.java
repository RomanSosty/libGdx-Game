package com.mygdx.game.utils;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.game.world.GroundMap;
import com.mygdx.game.world.candies.BlueCandy;
import com.mygdx.game.world.character.Player;
import com.mygdx.game.world.character.WoodEnemy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GameContactListener implements ContactListener {
    private final Logger logger = LogManager.getLogger(GameContactListener.class);
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
            playerTakeCandy();
            lastCollisionTime = currentTime;
        }
    }

    private void setCollisionObject(Contact contact) {
        objectA = contact.getFixtureA().getBody().getUserData();
        objectB = contact.getFixtureB().getBody().getUserData();
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
            ((WoodEnemy) objectA).setIsDestroyed();
        } else if (isEnemyAndPlayer(objectB, objectA) && ((Player) objectA).isAttacking()) {
            ((WoodEnemy) objectB).setIsDestroyed();
        }
    }

    private void playerTakeCandy() {
        if (isPlayerAndCandy(objectA, objectB)) {
            ((Player) objectA).setUpHealth();
            ((BlueCandy) objectB).setIsDestroyed();
            logger.info("Player took candy");
        } else if (isPlayerAndCandy(objectB, objectA)) {
            ((Player) objectB).setUpHealth();
            ((BlueCandy) objectA).setIsDestroyed();
            logger.info("Player took candy");
        }
    }

    private boolean isEnemyAndPlayer(Object obj1, Object obj2) {
        return obj1 instanceof WoodEnemy && obj2 instanceof Player;
    }

    private boolean isEnemyAndWall(Object obj1, Object obj2) {
        return obj1 instanceof WoodEnemy && obj2 instanceof GroundMap;
    }

    private boolean isPlayerAndCandy(Object obj1, Object obj2) {
        return obj1 instanceof Player && obj2 instanceof BlueCandy;
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
