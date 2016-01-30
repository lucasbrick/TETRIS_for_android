package com.lucasbrick.whc_tetris.version1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.lucasbrick.whc_tetris.*;

public class MainActivity extends AppCompatActivity {
    TetrisView tetrisView;
    ImageButton left;
    ImageButton right;
    ImageButton down;
    ImageButton rotate;
    ImageButton spacebar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tetrisView = (TetrisView)findViewById(R.id.tetrisView);
        left = (ImageButton)findViewById(R.id.left);
        right = (ImageButton)findViewById(R.id.right);
        down = (ImageButton)findViewById(R.id.down);
        rotate = (ImageButton)findViewById(R.id.rotate);
        spacebar = (ImageButton)findViewById(R.id.spacebar);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_LEFT);
                tetrisView.invalidate();
            }
        });
        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_RIGHT);
                tetrisView.invalidate();
            }
        });
        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_DOWN);
                tetrisView.invalidate();
            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_UP);
                tetrisView.invalidate();
            }
        });
        spacebar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.directDown();
                tetrisView.invalidate();
            }
        });
    }
}
