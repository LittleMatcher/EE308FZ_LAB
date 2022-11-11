package com.example.bobing_1;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;


public class first_page extends AppCompatActivity {

    Button button_menu;
    private ProgressBar progressBar;
    private int maxProgress;
    private int currentProgress = 0;

    private Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    progressBar.setProgress(currentProgress);
                    break;
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        progressBar = findViewById(R.id.pb_determinate);
        maxProgress = progressBar.getMax();
        button_menu=findViewById(R.id.first_button_next_menu);
        button_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(first_page.this,MainActivity.class);//从MainActivity跳转到nextActivity
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        new Thread() {
            @Override
            public void run(){
                while(true){
                    try {
                        for(int i=0;i<maxProgress;i++){
                            Thread.sleep(10);
                            currentProgress += 1;
                            if(currentProgress == 100){
                                break;
                            }
                            mHandler.sendEmptyMessage(0);
                        }
                    }
                    catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}