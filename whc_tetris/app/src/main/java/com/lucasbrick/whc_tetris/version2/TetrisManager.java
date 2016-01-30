package com.lucasbrick.whc_tetris.version2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class TetrisManager {

    public static final int EMPTY = 0;
    public static final int MOVING_BLOCK = 1;
    public static final int FIXED_BLOCK = 2;
    public static final int LEFT_WALL = 3;
    public static final int RIGHT_WALL = 4;
    public static final int BOTTOM_WALL = 5;
    public static final int TOP_WALL = 6;
    public static final int LEFT_TOP_EDGE = 7;
    public static final int RIGHT_TOP_EDGE = 8;
    public static final int LEFT_BOTTOM_EDGE = 9;
    public static final int RIGHT_BOTTOM_EDGE = 10;
    public static final int GAME_OVER = -1;
    public static final int GAME_CONTINUE = 1;
    public static final int DIRECTION_UP = 0;
    public static final int DIRECTION_RIGHT = 1;
    public static final int DIRECTION_DOWN = 2;
    public static final int DIRECTION_LEFT = 3;

    int deletedLineCount;
    private int board[][];
    private Block block;

    public TetrisManager() {
        board = new int[20][14];
        for (int i = 0; i < 20; i++) {
            board[i][0] = LEFT_WALL;
            board[i][13] = RIGHT_WALL;
        }
        for (int i = 0; i < 14; i++) {
            board[0][i] = TOP_WALL;
            board[19][i] = BOTTOM_WALL;
        }
        board[0][0] = LEFT_TOP_EDGE;
        board[0][13] = RIGHT_TOP_EDGE;
        board[19][0] = LEFT_BOTTOM_EDGE;
        board[19][13] = RIGHT_BOTTOM_EDGE;
        block = new Block();
        deletedLineCount = 0;
    }

    public int checkValidPosition(int direction) {
        Block temp = new Block();
        temp.copy(block);

        // 0 : up / 1 : right / 2 : down / 3 : left
        if (direction == 0) {
            temp.rotageRight();
        } else if (direction == 1) {
            temp.moveToRight();
        } else if (direction == 2) {
            temp.moveToDown();
        } else if (direction == 3) {
            temp.moveToLeft();
        }

        for (int i = 0; i < 4; i++) {
            int x = temp.getCurrentX()[temp.getDirection()][i];
            int y = temp.getCurrentY()[temp.getDirection()][i];

            if (y < 0) {
                return TOP_WALL;
            }

            if (board[y][x] != EMPTY && board[y][x] != MOVING_BLOCK) {
                return board[y][x];
            }
        }

        return EMPTY;
    }

    public void print(Canvas canvas, Paint paint) {
        int x = 80;
        int y = 10;
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 14; j++) {

                if (board[i][j] >= LEFT_WALL && board[i][j] <= RIGHT_BOTTOM_EDGE) {
                    canvas.drawRect(x, y, x + 45, y + 45, paint);
                } else if (board[i][j] == EMPTY) {
                    paint.setStyle(Paint.Style.STROKE);
                    canvas.drawRect(x, y, x + 45, y + 45, paint);
                    paint.setStyle(Paint.Style.FILL);
                } else if (board[i][j] == MOVING_BLOCK) {
                    int [] colors = {
                            Color.CYAN, Color.rgb(255,165,0), Color.rgb(50,205,50),Color.RED, Color.BLUE, Color.YELLOW, Color.rgb(160,32,240),
                    };
                    paint.setColor(colors[block.type]);
                    canvas.drawRect(x, y, x + 45, y + 45, paint);
                    paint.setColor(Color.BLACK);
                } else if (board[i][j] == FIXED_BLOCK) {
                    paint.setColor(Color.GRAY);
                    canvas.drawRect(x, y, x + 45, y + 45, paint);
                    paint.setColor(Color.BLACK);
                }

                x += 45;
            }
            y += 45;
            x -= 45 * 14;
        }
        block.printNext(canvas, paint);
    }

    public void changeBoardByStatus(int status) {
        for (int i = 0; i < 4; i++) {
            int x = block.currentX[block.direction][i];
            int y = block.currentY[block.direction][i];

            if (y == 0) {
                return;
            }

            board[y][x] = status;
        }
    }

    public void changeBoardByDirection(int direction) {
        changeBoardByStatus(EMPTY);
        int result = checkValidPosition(direction);
        if (result == EMPTY) {
            if (direction == 0) {
                block.rotageRight();
            } else if (direction == 1) {
                block.moveToRight();
            } else if (direction == 2) {
                block.moveToDown();
            } else if (direction == 3) {
                block.moveToLeft();
            }
        }
        changeBoardByStatus(MOVING_BLOCK);
    }

    public boolean canChangeFixedBlock() {
        for (int i = 0; i < 4; i++) {
            int x = block.currentX[block.direction][i];
            int y = block.currentY[block.direction][i];
            if (board[y + 1][x] != EMPTY && board[y + 1][x] != MOVING_BLOCK) {
                return true;
            }
        }
        return false;
    }

    public void directDown() {
        while (canChangeFixedBlock() == false) {
            changeBoardByDirection(2);
        }
    }

    public int processFixedCase() {
        changeBoardByStatus(FIXED_BLOCK);
        block = new Block(block.next);
        boolean result = canChangeFixedBlock();
        if (result == true) {
            return GAME_OVER;
        } else {
            return GAME_CONTINUE;
        }
    }

    public void deleteLine() {
        int[][] temp = new int[20][14];
        int k = 18;
        for (int i = 18; i > 1; i--) {
            boolean canDelete = true;
            for (int j = 1; j < 13; j++) {
                if (board[i][j] != FIXED_BLOCK) {
                    canDelete = false;
                    break;
                }
            }
            if (canDelete == false) {
                for (int j = 1; j < 13; j++) {
                    temp[k][j] = board[i][j];
                }
                k--;
            } else {
                deletedLineCount++;
            }
        }
        for (int i = 1; i < 19; i++) {
            for (int j = 1; j < 13; j++) {
                board[i][j] = temp[i][j];
            }
        }
    }
}
