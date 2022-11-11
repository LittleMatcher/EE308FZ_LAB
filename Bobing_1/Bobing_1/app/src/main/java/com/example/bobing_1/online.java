package com.example.bobing_1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;

public class online extends AppCompatActivity {

    Button Btn1,online_createroom,online_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.online);

        Btn1=findViewById(R.id.button_back);//按钮id为btn_1
        online_createroom=findViewById(R.id.button_createroom);
        online_join=findViewById(R.id.button_join);
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(online.this,MainActivity.class);//从MainActivity跳转到nextActivity
                startActivity(intent);
            }
        });
        online_createroom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(online.this,online_createroom.class);//从MainActivity跳转到nextActivity
                startActivity(intent);
            }
        });
        online_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(online.this,online_join.class);//从MainActivity跳转到nextActivity
                startActivity(intent);
            }
        });

    }
}