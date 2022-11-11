package com.example.bobing_1;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button_setting,button_gemerule,button_login,button_solitary_game;
    private SoundPool.Builder builder;
    SharedPreferences preferences;
    final boolean On = true;
    private SoundPool soundpool;
    private int soundId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        findViewById(R.id.gamerule).setOnClickListener(this);
        Connect11.mymysql();
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        preferences = getSharedPreferences("music1", Context.MODE_PRIVATE);
//-----------------------------------music-----------------------------------------------------
        Boolean state = preferences.getBoolean("flag",true);
        builder = new SoundPool.Builder();
        //AudioAttributes是一个封装音频各种属性的方法
        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        //设置音频流的合适的属性
        attrBuilder.setLegacyStreamType(AudioManager.STREAM_SYSTEM);
        soundpool = builder.build();
        soundId = soundpool.load(MainActivity.this, R.raw.fight_landown_with_peasant, 1);
        //是否加载完成的监听
        if(state==On){
            soundpool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
                //加载完毕后再播放
                soundpool.play(soundId, 1f, 1f, 0, 0, 1);
            });
            //传递布尔值是否成功
            //Toast.makeText(MainActivity.this, "Button On!", Toast.LENGTH_LONG).show();
        }else if(state==false){
            //传递布尔值是否成功
            //Toast.makeText(MainActivity.this, "Button Off!", Toast.LENGTH_LONG).show();
        }

//--------------------------------------------------------------------------------
//-----------------------------------back_page/next_page--------------------------
        button_gemerule=findViewById(R.id.gamerule);
        button_setting=findViewById(R.id.setting);
        button_login=findViewById(R.id.online_game);
        button_solitary_game=findViewById(R.id.solitary_play);
        button_solitary_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,solitary_game.class);//从MainActivity跳转到nextActivity
                soundpool.stop(soundId);
                startActivity(intent);
            }
        });
        button_setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,setting.class);//从MainActivity跳转到nextActivity
                soundpool.stop(soundId);
                startActivity(intent);
            }
        });
        button_gemerule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,gamerule.class);//从MainActivity跳转到nextActivity
                soundpool.stop(soundId);
                startActivity(intent);
            }
        });
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);//从MainActivity跳转到login
                soundpool.stop(soundId);
                startActivity(intent);
            }
        });
//-------------------------------------------------------------------------------
    }
    @Override
    public void onClick(View view) {


    }


//-------------------------quit button function-----------------------------------
    public void showdialog(View view)
    {
        //定义一个新的对话框对象
        AlertDialog.Builder alertdialogbuilder=new AlertDialog.Builder(this);
        //设置对话框提示内容
        alertdialogbuilder.setMessage("确定要退出程序吗？");
        //定义对话框2个按钮标题及接受事件的函数
        alertdialogbuilder.setPositiveButton("确定",click1);
        alertdialogbuilder.setNegativeButton("取消",click2);
        //创建并显示对话框
        AlertDialog alertdialog1=alertdialogbuilder.create();
        alertdialog1.show();

    }
    private DialogInterface.OnClickListener click1=new DialogInterface.OnClickListener()
    {
        //使用该标记是为了增强程序在编译时候的检查，如果该方法并不是一个覆盖父类的方法，在编译时编译器就会报告错误。
        @Override

        public void onClick(DialogInterface arg0,int arg1)
        {
            //当按钮click1被按下时执行结束进程
            finishAffinity();
        }
    };
    private DialogInterface.OnClickListener click2=new DialogInterface.OnClickListener()
    {
        @Override
        public void onClick(DialogInterface arg0,int arg1)
        {
            //当按钮click2被按下时则取消操作
            arg0.cancel();
        }
    };
//--------------------------------------------------------------------------------

//-------------------------background_music/menu--------------------------------------

//--------------------------------------------------------------------------------

//--------------------------------------------------------------------------------


//--------------------------------------------------------------------------------

}