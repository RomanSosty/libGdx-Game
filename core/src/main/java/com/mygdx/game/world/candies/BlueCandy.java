package com.mygdx.game.world.candies;

import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;
import com.mygdx.game.world.GameObject;

public class BlueCandy extends GameObject {

    public BlueCandy(World world, int x, int y) {
        super(world, Assets.blueCandy);
        makeBody(x, y, this, world);
        makeSprite();
    }
}
