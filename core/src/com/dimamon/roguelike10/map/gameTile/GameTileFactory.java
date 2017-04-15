package com.dimamon.roguelike10.map.gameTile;

import com.dimamon.roguelike10.map.tiles.TileFactory;


public class GameTileFactory {

    public static GameTile getFloor(){
        return new GameTile(TileFactory.getFloor());
    }

    //*********WALLS**********************************
    public static GameTile getWall(){
        return new GameTile(TileFactory.getWall());
    }

    public static GameTile getWallRight(){
        return new GameTile(TileFactory.getWallRight());
    }

    public static GameTile getWallUp(){
        return new GameTile(TileFactory.getWallUp());
    }

    public static GameTile getWallLeft(){
        return new GameTile(TileFactory.getWallLeft());
    }
    //*************************************************

    public static GameTile getRock(){
        return new GameTile(TileFactory.getRock());
    }

}
