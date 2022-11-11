package com.example.bobing_1;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class gamerule extends AppCompatActivity {

    Button Btn1,gamerule_rule,gamerule_history;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamerule);

        Btn1=findViewById(R.id.button_back);//按钮id为btn_1
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(gamerule.this,MainActivity.class);//从MainActivity跳转到nextActivity
                startActivity(intent);
            }
        });
    }
}