package com.mygdx.game.world;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.settings.Assets;

public class GroundMap {
    private final World world;
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer renderer;


    public GroundMap(World world, OrthographicCamera camera) {
        this.world = world;
        this.camera = camera;

        renderer = new OrthogonalTiledMapRenderer(Assets.map);
        mapsBodies();
    }

    private void mapsBodies() {
        TiledMapTileLayer tileLayer = (TiledMapTileLayer) Assets.map.getLayers().get("Collision");

        for (int y = 0; y < tileLayer.getHeight(); y++) {
            for (int x = 0; x < tileLayer.getWidth(); x++) {
                TiledMapTileLayer.Cell cell = tileLayer.getCell(x, y);
                if (cell != null) {

                    BodyDef bodyDef = new BodyDef();
                    bodyDef.type = BodyDef.BodyType.StaticBody;
                    bodyDef.position.set((x + 0.5f) * tileLayer.getTileWidth(), (y + 0.5f) * tileLayer.getTileHeight()); // Střed buňky
                    Body body = world.createBody(bodyDef);

                    PolygonShape shape = new PolygonShape();
                    shape.setAsBox(tileLayer.getTileWidth() / 2, tileLayer.getTileHeight() / 2);
                    body.createFixture(shape, 1.0f);
                    shape.dispose();
                }
            }
        }
    }

    public void render() {

        renderer.setView(camera);
        renderer.render();
    }
}
