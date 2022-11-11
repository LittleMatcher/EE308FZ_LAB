package com.example.bobing_1;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
public class setting extends AppCompatActivity {
    Dialog dialog;
    Button Btn1, yinping, yinyue;
    Switch music_switch, yinyue_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        yinping = findViewById(R.id.yinping);
        yinyue = findViewById(R.id.yinyue);

        music_switch = findViewById(R.id.switch_yinping);
            final boolean falg = true;
            final boolean falg1 = true;
            SharedPreferences preferences;
            music_switch = findViewById(R.id.switch_yinping);
            // 从SharedPreferences获取数据:
            preferences = getSharedPreferences("music1", Context.MODE_PRIVATE);
            if (preferences != null) {
                boolean name = preferences.getBoolean("flag", falg);
                music_switch.setChecked(name);
            }
            music_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    if (b) {
                        //将数据保存至SharedPreferences:
                        SharedPreferences preferences = getSharedPreferences("music1", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("flag", true);
                        editor.commit();
                    } else {
                        //将数据保存至SharedPreferences:
                        SharedPreferences preferences = getSharedPreferences("music1", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putBoolean("flag", false);
                        editor.commit();
                    }
                }
            });

        yinyue_switch = findViewById(R.id.switch_yinyue);
        SharedPreferences preferences1;
        preferences1 = getSharedPreferences("yinyue", Context.MODE_PRIVATE);
        if (preferences1 != null) {
            boolean name1 = preferences1.getBoolean("flag", falg1);
            yinyue_switch.setChecked(name1);
        }
        yinyue_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("yinyue", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", true);
                    editor.commit();
                } else {
                    //将数据保存至SharedPreferences:
                    SharedPreferences preferences = getSharedPreferences("yinyue", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("flag", false);
                    editor.commit();
                }
            }
        });

        Btn1 = findViewById(R.id.button_back);//按钮id为btn_1
        Btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(setting.this, MainActivity.class);//从MainActivity跳转到nextActivity
                startActivity(intent);
            }
        });
    }

    public void onClickDisagree(View v) {
        dialog.dismiss();
    }

    public void onClickPrivacy(View v) {
        showPrivacy("privacy.txt");//放在assets目录下的隐私政策文本文件
    }

    public void showPrivacy(String privacyFileName) {
        final View inflate = LayoutInflater.from(setting.this).inflate(R.layout.dialog_privacy_show, null);
        TextView tv_title = (TextView) inflate.findViewById(R.id.tv_title);
        tv_title.setText("搏了个搏隐私政策");
        TextView tv_content = (TextView) inflate.findViewById(R.id.tv_content);
        dialog = new AlertDialog
                .Builder(setting.this)
                .setView(inflate)
                .show();
        // 通过WindowManager获取
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        final WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = dm.widthPixels * 4 / 5;
        params.height = dm.heightPixels * 1 / 2;
        dialog.getWindow().setAttributes(params);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }

}