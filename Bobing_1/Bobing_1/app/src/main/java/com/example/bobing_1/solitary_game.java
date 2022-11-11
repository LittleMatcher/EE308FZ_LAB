package com.example.bobing_1;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Random;
import java.util.zip.GZIPInputStream;

class player{
    public int[] award = new int [6];//0-状元  1-对堂  2-三红  3-四进  4-二举  5-一秀=
}

public class solitary_game extends AppCompatActivity {
    public TextView bobing_result,button_back,award_1,award_2,award_3,award_4,award_5,award_6,award_7;
    private SoundPool.Builder builder;
    private SoundPool soundpool;
    private int soundId;
    SharedPreferences preferences;
    final boolean On = true;
    int award_one=0;
    int award_two=0;
    int award_three=0;
    int award_four=0;
    int award_five=0;
    int award_six=0;
    int award_seven=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.solitary_play);
        bobing_result = findViewById(R.id.bobing_result);
        award_1 = findViewById(R.id.bobing_1);
        award_2 = findViewById(R.id.bobing_2);
        award_3 = findViewById(R.id.bobing_3);
        award_4 = findViewById(R.id.bobing_4);
        award_5 = findViewById(R.id.bobing_5);
        award_6 = findViewById(R.id.bobing_6);
        award_7 = findViewById(R.id.bobing_7);
        Button rollButton = findViewById(R.id.roll_button);
        button_back=findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(solitary_game.this,MainActivity.class);//从MainActivity跳转到nextActivity
                startActivity(intent);
            }
        });
        rollButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
//                产生随机数
                Random rand = new Random();
                String result="";
                int dice_array[]=new int[6];
                for(int i=0;i<6;i++){
                    dice_array[i]=rand.nextInt(6)+1;
                }
                //-------------------------------------------------------------
                int one_flag=0;
                int two_flag=0;
                int three_flag=0;
                int four_flag=0;
                int five_flag=0;
                int six_flag=0;

                for(int i=0;i<6;i++){
                    if(dice_array[i]==1){
                        one_flag++;
                    }
                    if(dice_array[i]==2){
                        two_flag++;
                    }
                    if(dice_array[i]==3){
                        three_flag++;
                    }
                    if(dice_array[i]==4){
                        four_flag++;
                    }
                    if(dice_array[i]==5){
                        five_flag++;
                    }
                    if(dice_array[i]==6){
                        six_flag++;
                    }
                }
                //状元
                if((two_flag==2&&four_flag==4)||(four_flag==6)||(one_flag==6)||(four_flag==5)||(two_flag==5)||(four_flag==4)){
                        result="状元";

                }
                //对堂
                else if(one_flag==1&&two_flag==1&&three_flag==1&&four_flag==1&&five_flag==1&&six_flag==1){
                        result="对堂";

                }
                //四进
                else if(two_flag==4){
                        result="四进";

                }
                //三红
                else if(four_flag==3){
                        result="三红";

                }
                //二举
                else if(four_flag==2){
                        result="二举";

                }
                //一秀
                else if(four_flag==1){
                        result="一秀";

                }else{
                        result="不中";

                }
                bobing_result.setText(result);
                if(result=="状元"){
                    award_1.setText(String.valueOf(++award_one));
                }
                if(result=="对堂"){
                    award_2.setText(String.valueOf(++award_two));
                }
                if(result=="四进"){
                    award_3.setText(String.valueOf(++award_three));
                }
                if(result=="三红"){
                    award_4.setText(String.valueOf(++award_four));
                }
                if(result=="二举"){
                    award_5.setText(String.valueOf(++award_five));
                }
                if(result=="一秀"){
                    award_6.setText(String.valueOf(++award_six));
                }
                if(result=="不中"){
                    award_7.setText(String.valueOf(++award_seven));
                }
                new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                            getDate();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();


//--------------------------------------------------------------
//                获取对ImageView对象的引用
                ImageView diceImage_1 = findViewById(R.id.dice_1);
                ImageView diceImage_2 = findViewById(R.id.dice_2);
                ImageView diceImage_3 = findViewById(R.id.dice_3);
                ImageView diceImage_4 = findViewById(R.id.dice_4);
                ImageView diceImage_5 = findViewById(R.id.dice_5);
                ImageView diceImage_6 = findViewById(R.id.dice_6);
                int drawableResource_1;
                int drawableResource_2;
                int drawableResource_3;
                int drawableResource_4;
                int drawableResource_5;
                int drawableResource_6;
                switch (dice_array[0]){
                    case 1: drawableResource_2 = R.drawable.value1; break;
                    case 2: drawableResource_2 = R.drawable.value2; break;
                    case 3: drawableResource_2 = R.drawable.value3; break;
                    case 4: drawableResource_2 = R.drawable.value4; break;
                    case 5: drawableResource_2 = R.drawable.value5; break;
                    case 6: drawableResource_2 = R.drawable.value6; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + dice_array[0]);
                }
//                将随机数与对应的图片资源联系起来
                switch (dice_array[1]){
                    case 1: drawableResource_1 = R.drawable.value1; break;
                    case 2: drawableResource_1 = R.drawable.value2; break;
                    case 3: drawableResource_1 = R.drawable.value3; break;
                    case 4: drawableResource_1 = R.drawable.value4; break;
                    case 5: drawableResource_1 = R.drawable.value5; break;
                    case 6: drawableResource_1 = R.drawable.value6; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " +dice_array[1]);
                }
                switch (dice_array[2]){
                    case 1: drawableResource_3 = R.drawable.value1; break;
                    case 2: drawableResource_3 = R.drawable.value2; break;
                    case 3: drawableResource_3 = R.drawable.value3; break;
                    case 4: drawableResource_3 = R.drawable.value4; break;
                    case 5: drawableResource_3 = R.drawable.value5; break;
                    case 6: drawableResource_3 = R.drawable.value6; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + dice_array[2]);
                }
                switch (dice_array[3]){
                    case 1: drawableResource_4 = R.drawable.value1; break;
                    case 2: drawableResource_4 = R.drawable.value2; break;
                    case 3: drawableResource_4 = R.drawable.value3; break;
                    case 4: drawableResource_4 = R.drawable.value4; break;
                    case 5: drawableResource_4 = R.drawable.value5; break;
                    case 6: drawableResource_4 = R.drawable.value6; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + dice_array[3]);
                }
                switch (dice_array[4]){
                    case 1: drawableResource_5 = R.drawable.value1; break;
                    case 2: drawableResource_5 = R.drawable.value2; break;
                    case 3: drawableResource_5 = R.drawable.value3; break;
                    case 4: drawableResource_5 = R.drawable.value4; break;
                    case 5: drawableResource_5 = R.drawable.value5; break;
                    case 6: drawableResource_5 = R.drawable.value6; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + dice_array[4]);
                }
                switch (dice_array[5]){
                    case 1: drawableResource_6 = R.drawable.value1; break;
                    case 2: drawableResource_6 = R.drawable.value2; break;
                    case 3: drawableResource_6 = R.drawable.value3; break;
                    case 4: drawableResource_6 = R.drawable.value4; break;
                    case 5: drawableResource_6 = R.drawable.value5; break;
                    case 6: drawableResource_6 = R.drawable.value6; break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + dice_array[5]);
                }


//                随机图片根据rangNumber的值对应drawableResource的int值，实例Drawable类
                Drawable drawable_1 = getBaseContext().getResources().getDrawable(drawableResource_1);
                Drawable drawable_2 = getBaseContext().getResources().getDrawable(drawableResource_2);
                Drawable drawable_3 = getBaseContext().getResources().getDrawable(drawableResource_3);
                Drawable drawable_4 = getBaseContext().getResources().getDrawable(drawableResource_4);
                Drawable drawable_5 = getBaseContext().getResources().getDrawable(drawableResource_5);
                Drawable drawable_6 = getBaseContext().getResources().getDrawable(drawableResource_6);
//-------------------rolling_motion-------------------------------------------
//                switch (v.getId()){
//                    case R.id.roll_button:
//                            anim.start();
//                            new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        // do something after 2s = 2000 miliseconds
//                        anim.stop();
//                        diceImage_1.setImageDrawable(drawable_1);
//                    }
//
//                }, 1900);break;
//
//                    default:
//                        throw new IllegalStateException("Unexpected value: " + v.getId());
//                }


//----------------------------------------------------------------------------
//                设置ImageView控件最终显示的图片
                diceImage_1.setImageDrawable(drawable_1);
                diceImage_2.setImageDrawable(drawable_2);
                diceImage_3.setImageDrawable(drawable_3);
                diceImage_4.setImageDrawable(drawable_4);
                diceImage_5.setImageDrawable(drawable_5);
                diceImage_6.setImageDrawable(drawable_6);
                preferences = getSharedPreferences("yinyue", Context.MODE_PRIVATE);
                Boolean state = preferences.getBoolean("flag",true);
                builder = new SoundPool.Builder();
                //AudioAttributes是一个封装音频各种属性的方法
                AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
                //设置音频流的合适的属性
                attrBuilder.setLegacyStreamType(AudioManager.STREAM_SYSTEM);
                soundpool = builder.build();
                soundId = soundpool.load(solitary_game.this, R.raw.sound_dice, 1);
                //是否加载完成的监听
                if(state==On){
                    soundpool.setOnLoadCompleteListener((soundPool, sampleId, status) -> {
                        //加载完毕后再播放
                        soundpool.play(soundId, 1f, 1f, 0, 0, 1);
                    });
                }

            }
        });
    }
    public void getDate() throws IOException {
        //根据地址创建URL对象(网络访问
        //发布文章的url)
//        URL url = new URL("http://47.107.225.197:8081/dice/checkResult");
        URL url = new URL("http://10.0.0.2:8081/user/first");
        HttpURLConnection conn = (HttpURLConnection)
                //设置请求的方式
                url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoInput(true);//发送POST请求必须设置允许输出
        conn.setDoOutput(true);//发送POST请求必须设置允许输入
        //设置请求的头
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("Charset", "utf-8");
        String data = "Unumber =" + URLEncoder.encode("123454", "UTF-8");//传递的数据
        conn.setRequestProperty("Content-Length", String.valueOf(data.getBytes().length));
        //获取输出流
        OutputStream os = conn.getOutputStream();
        os.write(data.getBytes());
        os.flush();
        //获取响应的输入流对象
        InputStreamReader is = new InputStreamReader(conn.getInputStream());
        BufferedReader bufferedReader = new BufferedReader(is);
        StringBuffer strBuffer = new StringBuffer();
        String line = null;
        //读取服务器返回信息
        while ((line = bufferedReader.readLine()) != null) {
            strBuffer.append(line);
        }
        String result = strBuffer.toString();//接收从服务器返回的数据
        System.out.println("收到的信息"+result);
        //关闭InputStream、关闭http连接
        is.close();
        conn.disconnect();
    }

}
