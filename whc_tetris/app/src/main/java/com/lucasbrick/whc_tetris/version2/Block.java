package com.lucasbrick.whc_tetris.version2;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.Random;

public class Block {
    int[][][] startX = {{{5, 6, 7, 8}, {6, 6, 6, 6}, {5, 6, 7, 8}, {6, 6, 6, 6}},
            {{8, 6, 7, 8}, {7, 7, 7, 8}, {6, 7, 8, 6}, {6, 7, 7, 7}},
            {{7, 8, 6, 7}, {6, 6, 7, 7}, {7, 8, 6, 7}, {6, 6, 7, 7}},
            {{6, 7, 7, 8}, {8, 8, 7, 7}, {6, 7, 7, 8}, {8, 8, 7, 7}},
            {{6, 6, 7, 8}, {8, 7, 7, 7}, {6, 7, 8, 8}, {7, 7, 7, 6}},
            {{6, 7, 6, 7}, {6, 7, 6, 7}, {6, 7, 6, 7}, {6, 7, 6, 7}},
            {{7, 6, 7, 8}, {7, 7, 8, 7}, {6, 7, 8, 7}, {7, 6, 7, 7}}
    };

    int[][][] startY = {{{0, 0, 0, 0}, {-2, -1, 0, 1}, {0, 0, 0, 0}, {-2, -1, 0, 1}},
            {{0, 1, 1, 1}, {-1, 0, 1, 1}, {0, 0, 0, 1}, {-1, -1, 0, 1}},
            {{0, 0, 1, 1}, {-1, 0, 0, 1}, {0, 0, 1, 1}, {-1, 0, 0, 1}},
            {{0, 0, 1, 1}, {-1, 0, 0, 1}, {0, 0, 1, 1}, {-1, 0, 0, 1}},
            {{0, 1, 1, 1}, {-1, -1, 0, 1}, {0, 0, 0, 1}, {-1, 0, 1, 1}},
            {{0, 0, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 1}, {0, 0, 1, 1}},
            {{0, 1, 1, 1}, {-1, 0, 0, 1}, {0, 0, 0, 1}, {-1, 0, 0, 1}}
    };

    int type;
    int next;

    int[][] currentX;
    int[][] currentY;

    int direction;

    public Block() {
        currentX = new int[4][4];
        currentY = new int[4][4];
        direction = 0;
        Random r = new Random();
        type = r.nextInt(6);
        next = r.nextInt(6);
        copyStartToCurrent();
    }

    public Block(int next) {
        currentX = new int[4][4];
        currentY = new int[4][4];
        direction = 0;
        type = next;
        Random r = new Random();
        this.next = r.nextInt(6);
        copyStartToCurrent();
    }

    public void copy(Block other) {
        direction = other.direction;
        type = other.type;
        next = other.next;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentX[i][j] = other.currentX[i][j];
                currentY[i][j] = other.currentY[i][j];
            }
        }
    }

    public void printCurrent() {
//		System.out.println("[Block ����]");
//		System.out.println("���� ��� ���� : " + type);
//		System.out.println("���� ���� : " + direction);
//		for (int i = 0; i < 4; i++) {
//			System.out.print("(" + currentX[direction][i] + ", " + currentY[direction][i] + ")" + " ");
//		}
//		System.out.println();
//		System.out.println();
    }
    public void printNext(Canvas canvas, Paint paint) {
        int x = 1200;
        int y = 200;
        paint.setTextSize(50);
        canvas.drawText("Next", x, y, paint);
        y += 50;

        //ㅁㅁㅁㅁ
        if(next == 0){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(x, y, x + 40, y + 40, paint);
            canvas.drawRect(x + 40, y, x + 80, y + 40, paint);
            canvas.drawRect(x + 80, y, x + 120, y + 40, paint);
            canvas.drawRect(x + 120, y, x + 160, y + 40, paint);
            paint.setStyle(Paint.Style.FILL);
        }

        //    ㅁ
        //ㅁㅁㅁ
        else if(next == 1){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(x + 80, y, x + 120, y + 40, paint);
            canvas.drawRect(x, y + 40, x + 40, y + 80, paint);
            canvas.drawRect(x + 40, y + 40, x + 80, y + 80, paint);
            canvas.drawRect(x + 80, y + 40, x + 120, y + 80, paint);
            paint.setStyle(Paint.Style.FILL);
        }
        //  ㅁㅁ
        //ㅁㅁ
        else if(next == 2){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(x + 40, y, x + 80, y + 40, paint);
            canvas.drawRect(x + 80, y, x + 120, y + 40, paint);
            canvas.drawRect(x , y + 40, x + 40, y + 80, paint);
            canvas.drawRect(x + 40, y + 40, x + 80, y + 80, paint);
            paint.setStyle(Paint.Style.FILL);
        }

        //ㅁㅁ
        //  ㅁㅁ
        else if(next == 3){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(x , y, x + 40, y + 40, paint);
            canvas.drawRect(x + 40, y, x + 80, y + 40, paint);
            canvas.drawRect(x + 40, y + 40, x + 80, y + 80, paint);
            canvas.drawRect(x + 80, y + 40, x + 120, y + 80, paint);
            paint.setStyle(Paint.Style.FILL);
        }
        //ㅁ
        //ㅁㅁㅁ
        else if(next == 4){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(x, y, x + 40, y + 40, paint);
            canvas.drawRect(x, y + 40, x + 40, y + 80, paint);
            canvas.drawRect(x + 40, y + 40, x + 80, y + 80, paint);
            canvas.drawRect(x + 80, y + 40, x + 120, y + 80, paint);
            paint.setStyle(Paint.Style.FILL);
        }
        //ㅁㅁ
        //ㅁㅁ
        else if(next == 5){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(x, y, x + 40, y + 40, paint);
            canvas.drawRect(x + 40, y, x + 80, y + 40, paint);
            canvas.drawRect(x, y + 40, x + 40, y + 80, paint);
            canvas.drawRect(x + 40, y + 40, x + 80, y + 80, paint);
            paint.setStyle(Paint.Style.FILL);
        }
         // ㅁ
        //ㅁㅁㅁ
        else if(next == 6){
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(5);
            canvas.drawRect(x + 40, y, x + 80, y + 40, paint);
            canvas.drawRect(x, y + 40, x + 40, y + 80, paint);
            canvas.drawRect(x + 40, y + 40, x + 80, y + 80, paint);
            canvas.drawRect(x + 80, y + 40, x + 120, y + 80, paint);
            paint.setStyle(Paint.Style.FILL);
        }    }
    public int getNext() {
        return next;
    }

    public int[][] getCurrentX() {
        return currentX;
    }

    public int[][] getCurrentY() {
        return currentY;
    }

    public int getDirection() {
        return direction;
    }

    public void copyStartToCurrent() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentX[i][j] = startX[type][i][j];
                currentY[i][j] = startY[type][i][j];
            }
        }
    }

    public void moveToDown() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentY[i][j]++;
            }
        }
//        System.out.println("�Ʒ��� ��ĭ �̵�");
    }

    public void moveToLeft() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentX[i][j]--;
            }
        }
//        System.out.println("�������� ��ĭ �̵�");
    }

    public void moveToRight() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                currentX[i][j]++;
            }
        }
//        System.out.println("�������� ��ĭ �̵�");
    }

    public void rotageRight() {
        direction++;
        if (direction == 4) {
            direction = 0;
        }
//        System.out.println("������������ ȸ��");
    }
}
