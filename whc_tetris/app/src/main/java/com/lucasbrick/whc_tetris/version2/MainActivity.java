package com.lucasbrick.whc_tetris.version2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.lucasbrick.whc_tetris.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    TetrisView tetrisView;
    ImageButton left;
    ImageButton right;
    ImageButton down;
    ImageButton rotate;
    ImageButton spacebar;
    Timer timer;
    TimerTask timerTask;
    TextView deleteLineCount;
    MediaPlayer mp;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tetrisView = (TetrisView) findViewById(R.id.tetrisView);
        left = (ImageButton) findViewById(R.id.left);
        right = (ImageButton) findViewById(R.id.right);
        down = (ImageButton) findViewById(R.id.down);
        rotate = (ImageButton) findViewById(R.id.rotate);
        spacebar = (ImageButton) findViewById(R.id.spacebar);
        deleteLineCount = (TextView) findViewById(R.id.deleteLineCount);
        left.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_LEFT);
                tetrisView.invalidate();
            }
        });
        right.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_RIGHT);
                tetrisView.invalidate();
            }
        });
        down.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_DOWN);
                tetrisView.invalidate();
            }
        });
        rotate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_UP);
                tetrisView.invalidate();
            }
        });
        spacebar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                tetrisView.tetrisManager.directDown();
                tetrisView.invalidate();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mp.stop();
        mp.release();
        timer.cancel();
        timer = null;
    }

    @Override
    protected void onUserLeaveHint() {
        super.onUserLeaveHint();
        mp.stop();
        mp.release();
        timer.cancel();
        timer = null;
    }

    @Override
    protected void onResume() {
        super.onResume();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                tetrisView.tetrisManager.changeBoardByDirection(TetrisManager.DIRECTION_DOWN);
                if (tetrisView.tetrisManager.canChangeFixedBlock() == true) {
                    if(count == 1){
                        int gameStatus = tetrisView.tetrisManager.processFixedCase();
                        if (gameStatus == TetrisManager.GAME_OVER) {
                            timer.cancel();
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    //Toast.makeText(MainActivity.this, "지운 라인 수: " + tetrisView.tetrisManager.deletedLineCount, Toast.LENGTH_SHORT).show();
                                    AlertDialog.Builder dialogue = new AlertDialog.Builder(MainActivity.this);
                                    dialogue.setPositiveButton("종료", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            finish();
                                        }
                                    });
                                    //finish();
                                    dialogue.setMessage("지운 라인 수: " + tetrisView.tetrisManager.deletedLineCount);
                                    dialogue.show();
                                    mp.stop();
                                    mp.release();
                                }
                            });
                        }
                        count = 0;
                    }
                    else{
                        count = 1;
                    }
                    tetrisView.tetrisManager.deleteLine();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            deleteLineCount.setText("지운 라인 수: " + tetrisView.tetrisManager.deletedLineCount);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tetrisView.invalidate();
                    }
                });
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 500, 500);
        mp = MediaPlayer.create(this, R.raw.tetris);
        mp.setLooping(true);
        mp.start();
    }
}
