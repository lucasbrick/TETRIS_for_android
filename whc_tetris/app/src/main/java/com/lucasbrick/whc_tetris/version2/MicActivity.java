package com.lucasbrick.whc_tetris.version2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.lucasbrick.whc_tetris.R;

import java.util.ArrayList;

/**
 * Created by Lucas Won on 2016-01-17.
 */
public class MicActivity extends Activity{
    ImageView mic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mic);
        mic = (ImageView)findViewById(R.id.mic);
        mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);            //intent 생성
                i.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getPackageName());    //호출한 패키지
                i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");                            //음성인식 언어 설정
                i.putExtra(RecognizerIntent.EXTRA_PROMPT, "\"테트리스 시작\"\n이라고 말해주세요.");                     //사용자에게 보여 줄 글자
                startActivityForResult(i, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String key = RecognizerIntent.EXTRA_RESULTS;
        ArrayList<String> results = data.getStringArrayListExtra(key);
        Toast.makeText(this, results.get(0) + "", Toast.LENGTH_LONG).show();
        if(results.get(0).equals("테트리스 시작")){
            Intent I = new Intent(MicActivity.this, MainActivity.class);
            startActivity(I);
        }
    }
}
