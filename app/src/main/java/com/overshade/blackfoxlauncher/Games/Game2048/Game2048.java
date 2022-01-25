package com.overshade.blackfoxlauncher.Games.Game2048;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.app.Dialog;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;

import com.overshade.blackfoxlauncher.Games.PegSolitaire.Peg;
import com.overshade.blackfoxlauncher.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Game2048 {
    private View gameView;
    private Tile[][] tiles;
    private boolean turnDone = false;

    public Game2048(View gameView) { this.gameView = gameView;}

    //GAME LOGIC

    private void setOnTouchListener() {
        gameView.setOnTouchListener(new OnSwipeListener(gameView.getContext()) {

            @Override
            public void onSwipeRight() {
                System.out.println("Swiped right");
                for (int x = tiles.length-1; x >= 0; x--) {
                    for (int y = tiles[0].length-1; y >= 0 ; y--) {
                        //if tile has a value and is not in the border
                        if (tiles[x][y].getValue() != 0 && x != tiles.length-1) {
                            System.out.println("TILE :{"+x+","+y+"}");
                            //while the next tile is empty, we move this tile
                            boolean nextTileIsEmpty = tiles[x+1][y].getValue() == 0;
                            while (nextTileIsEmpty && x < tiles.length-1) {
                                System.out.println("has a space on its right");
                                moveTile(tiles[x][y], tiles[x+1][y]);
                                x++;
                                if (x < tiles.length-1) {
                                    nextTileIsEmpty = tiles[x+1][y].getValue() == 0;
                                } else {
                                    nextTileIsEmpty = false;
                                }
                            }
                            //when finishing moving, we check if we can collide a tile
                            if (x < tiles.length-1) {
                                if (checkColision(tiles[x][y], tiles[x+1][y]) && tiles[x][y].canMove()) {
                                    moveTile(tiles[x][y], tiles[x+1][y]);
                                    tiles[x+1][y].setCanMove(false);
                                }
                            }
                        }
                    }
                }
                if (turnDone) {
                    addRandomTile();
                    for (Tile[] tileArray: tiles) {
                        for (Tile tile : tileArray) {
                            tile.setCanMove(true);
                        }
                    }
                    turnDone = false;
                }
            }

            @Override
            public void onSwipeLeft() {
                System.out.println("Swiped left");
                for (int x = 0; x <= tiles.length-1; x++) {
                    for (int y = 0; y <= tiles[0].length-1 ; y++) {
                        //if tile has a value and is not in the border
                        if (tiles[x][y].getValue() != 0 && x != 0) {
                            System.out.println("TILE :{"+x+","+y+"}");
                            //while the next tile is empty, we move this tile
                            boolean nextTileIsEmpty = tiles[x-1][y].getValue() == 0;
                            while (nextTileIsEmpty) {
                                System.out.println("has a space on its left");
                                moveTile(tiles[x][y], tiles[x-1][y]);
                                x--;
                                if (x > 0) {
                                    nextTileIsEmpty = tiles[x-1][y].getValue() == 0;
                                } else {
                                    nextTileIsEmpty = false;
                                }
                            }
                            //when finishing moving, we check if we can collide a tile
                            if (x > 0) {
                                if (checkColision(tiles[x][y], tiles[x-1][y]) && tiles[x][y].canMove()) {
                                    moveTile(tiles[x][y], tiles[x-1][y]);
                                    tiles[x-1][y].setCanMove(false);
                                }
                            }
                        }
                    }
                }
                if (turnDone) {
                    addRandomTile();
                    for (Tile[] tileArray: tiles) {
                        for (Tile tile : tileArray) {
                            tile.setCanMove(true);
                        }
                    }
                    turnDone = false;
                }
            }

            @Override
            public void onSwipeTop() {
                System.out.println("Swiped top");
                for (int x = 0; x <= tiles.length-1; x++) {
                    for (int y = 0; y <= tiles[0].length-1 ; y++) {
                        //if tile has a value and is not in the border
                        if (tiles[x][y].getValue() != 0 && y != 0) {
                            System.out.println("TILE :{"+x+","+y+"}");
                            //while the next tile is empty, we move this tile
                            boolean nextTileIsEmpty = tiles[x][y-1].getValue() == 0;
                            while (nextTileIsEmpty) {
                                System.out.println("has a space on top");
                                moveTile(tiles[x][y], tiles[x][y-1]);
                                y--;
                                if (y > 0) {
                                    nextTileIsEmpty = tiles[x][y-1].getValue() == 0;
                                } else {
                                    nextTileIsEmpty = false;
                                }
                            }
                            //when finishing moving, we check if we can collide a tile
                            if (y > 0) {
                                if (checkColision(tiles[x][y], tiles[x][y-1]) && tiles[x][y].canMove()) {
                                    moveTile(tiles[x][y], tiles[x][y-1]);
                                    tiles[x][y-1].setCanMove(false);
                                }
                            }
                        }
                    }
                }
                if (turnDone) {
                    addRandomTile();
                    for (Tile[] tileArray: tiles) {
                        for (Tile tile : tileArray) {
                            tile.setCanMove(true);
                        }
                    }
                    turnDone = false;
                }
            }

            @Override
            public void onSwipeBottom() {
                System.out.println("Swiped bottom");
                for (int x = tiles.length-1; x >= 0; x--) {
                    for (int y = tiles[0].length-1; y >= 0 ; y--) {
                        //if tile has a value and is not in the border
                        if (tiles[x][y].getValue() != 0 && y != tiles[0].length-1) {
                            System.out.println("TILE :{"+x+","+y+"}");
                            //while the next tile is empty, we move this tile
                            boolean nextTileIsEmpty = tiles[x][y+1].getValue() == 0;
                            while (nextTileIsEmpty && y < tiles[0].length-1) {
                                System.out.println("has a space on bottom");
                                moveTile(tiles[x][y], tiles[x][y+1]);
                                y++;
                                if (y < tiles[0].length-1) {
                                    nextTileIsEmpty = tiles[x][y+1].getValue() == 0;
                                } else {
                                    nextTileIsEmpty = false;
                                }
                            }
                            //when finishing moving, if we check if we can collide a tile
                            if (y < tiles[0].length-1) {
                                if (checkColision(tiles[x][y], tiles[x][y+1]) && tiles[x][y].canMove()) {
                                    moveTile(tiles[x][y], tiles[x][y+1]);
                                    tiles[x][y+1].setCanMove(false);
                                }
                            }
                        }
                    }
                }
                if (turnDone) {
                    addRandomTile();
                    for (Tile[] tileArray: tiles) {
                        for (Tile tile : tileArray) {
                            tile.setCanMove(true);
                        }
                    }
                    turnDone = false;
                }
            }
        });
    }

    private void moveTile(Tile moving, Tile objective) {
        if (objective.getValue() == 0) {
            objective.changeValue(moving.getValue());
        } else {
            objective.changeValue(moving.getValue()*2);
        }
        moving.changeValue(0);
        turnDone = true;
    }

    private boolean checkColision(Tile tile, Tile tilecolider) {
        if (tile.getValue() == 0) {
            return false;
        }
        if (tilecolider.getValue() == 0) {
            return true;
        }
        return tile.getValue() == tilecolider.getValue();
    }

    private List<Tile> getEmptyPositions() {
        List<Tile> emptyTiles = new ArrayList<>();

        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[0].length; y++) {
                if (tiles[x][y].getValue() == 0) {
                    emptyTiles.add(tiles[x][y]);
                }
            }
        }

        return emptyTiles;
    }

    private void addRandomTile() {
        List<Tile> emptyPositions = getEmptyPositions();
        if (!emptyPositions.isEmpty()) {
            Random r = new Random();
            int value;
            if (r.nextInt(10) < 9) {
                value = 2;
            } else {
                value = 4;
            }
            int position = r.nextInt(emptyPositions.size());
            emptyPositions.get(position).changeValue(value);
        }
    }

    //GAME INITIALIZER

    public void restart() {
        assignViewToVariables();
        for (int i = 0; i < 2; i++) {
            addRandomTile();
        }
        setOnTouchListener();
    }

    private void assignViewToVariables() {
        tiles = new Tile[4][4];
        tiles[0][0] = new Tile(gameView.findViewById(R.id.f0c0), 0, 0);
        tiles[1][0] = new Tile(gameView.findViewById(R.id.f0c1), 1, 0);
        tiles[2][0] = new Tile(gameView.findViewById(R.id.f0c2), 2, 0);
        tiles[3][0] = new Tile(gameView.findViewById(R.id.f0c3), 3, 0);

        tiles[0][1] = new Tile(gameView.findViewById(R.id.f1c0), 0, 1);
        tiles[1][1] = new Tile(gameView.findViewById(R.id.f1c1), 1, 1);
        tiles[2][1] = new Tile(gameView.findViewById(R.id.f1c2), 2, 1);
        tiles[3][1] = new Tile(gameView.findViewById(R.id.f1c3), 3, 1);

        tiles[0][2] = new Tile(gameView.findViewById(R.id.f2c0), 0, 2);
        tiles[1][2] = new Tile(gameView.findViewById(R.id.f2c1), 1, 2);
        tiles[2][2] = new Tile(gameView.findViewById(R.id.f2c2), 2, 2);
        tiles[3][2] = new Tile(gameView.findViewById(R.id.f2c3), 3, 2);

        tiles[0][3] = new Tile(gameView.findViewById(R.id.f3c0), 0, 3);
        tiles[1][3] = new Tile(gameView.findViewById(R.id.f3c1), 1, 3);
        tiles[2][3] = new Tile(gameView.findViewById(R.id.f3c2), 2, 3);
        tiles[3][3] = new Tile(gameView.findViewById(R.id.f3c3), 3, 3);
    }
}
