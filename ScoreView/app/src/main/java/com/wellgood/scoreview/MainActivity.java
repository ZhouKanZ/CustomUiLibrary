package com.wellgood.scoreview;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RelativeLayout main = (RelativeLayout) findViewById(R.id.activity_main);

        ScoreView testView = (ScoreView) findViewById(R.id.score);
        testView.setAim(80);
        testView.setScoreChangeListener(new OnScoreChangeListener() {
            @Override
            public void scoreChangeListener(final int score) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (score>0 && score<10){
                            main.setBackgroundColor(Color.RED);
                        }else if (score < 20){
                            main.setBackgroundColor(Color.GREEN);
                        }else if (score<30){
                            main.setBackgroundColor(Color.GRAY);
                        }else {
                            main.setBackgroundColor(Color.BLUE);
                        }
                    }
                });

            }
        });
    }
}
