package com.zjc.news.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zjc.news.BaseActivity;
import com.zjc.news.R;
import com.zjc.news.model.NewsBean;
import com.zjc.news.utils.NetworkUtil;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.zip.Inflater;

import okhttp3.Request;

public class ShakeSensorActivity extends BaseActivity implements View.OnClickListener{

    private SensorManager sensorManager;
    private Vibrator vibrator;
    private static final String TAG = "ShakeSensorActivity";
    private static final int SENSOR_SHAKE = 10;
    private String url = "http://v.juhe.cn/toutiao/index?key=65d4c89f2460e131bd8b288f3f70bff6";

    private ImageView newsImage;
    private TextView newsTitle;
    private TextView newsType;
    private TextView newsTime;
    private LinearLayout linearLayout;
    private ImageView backImage;

    /**动作执行*/
    @SuppressLint("HandlerLeak")
    private Handler handler = new  Handler(){
        public void handleMessage(Message msg){
            super.handleMessage(msg);
            switch (msg.what){
                case SENSOR_SHAKE:
                    Log.i(TAG,"检测到摇晃，执行操作");
                    NetworkUtil.getInstance().asyncGet(url, new NetworkUtil.HttpCallBack() {
                        @Override
                        public void onError(Request request, IOException e) {
                            Log.d(TAG, e.toString());
                        }

                        @Override
                        public void onSuccess(Request request, String result) {
                            Log.d(TAG, result);
                            List<NewsBean.Result.Data> newsList = parse(result);
                            //随机生成一条新闻
                            int randomIndex = new Random().nextInt(newsList.size());
                            final NewsBean.Result.Data randomNews = newsList.get(randomIndex);
                            Toast.makeText(ShakeSensorActivity.this,
                                    "您摇到一条"+randomNews.getCategory()+"新闻",Toast.LENGTH_SHORT).show();
                            Glide.with(ShakeSensorActivity.this)
                                    .load(randomNews.getThumbnail_pic_s()).into(newsImage);
                            newsTitle.setText(randomNews.getTitle());
                            newsType.setText(randomNews.getCategory());
                            newsTime.setText(randomNews.getDate());
                            linearLayout.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(ShakeSensorActivity.this,NewsInfoActivity.class);
                                    intent.putExtra("url", randomNews.getUrl());
                                    startActivity(intent);
                                }
                            });
                        }
                    });
                    break;
            }
        }
    };

    private List<NewsBean.Result.Data> parse(String responseData) {
        Log.i(TAG, "加载到parse()");
        Gson gson = new Gson();
        NewsBean newsBean = gson.fromJson(responseData, NewsBean.class);
        NewsBean.Result result = newsBean.result;
        List<NewsBean.Result.Data> datas = result.data;
        Log.d(">>>>>>>解析的标题：", datas.get(0).getTitle().toString());
        return datas;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shake_sensor);
        initSystemBar(true);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle("");
        }
        sensorManager= (SensorManager) getSystemService(SENSOR_SERVICE);
        vibrator= (Vibrator) getSystemService(VIBRATOR_SERVICE);
        newsImage = findViewById(R.id.iv_shake_news_list_image);
        newsTitle = findViewById(R.id.tv_shake_news_list_title);
        newsType = findViewById(R.id.tv_shake_news_list_real_type);
        newsTime = findViewById(R.id.tv_shake_news_list_time);
        linearLayout = findViewById(R.id.shake_linear_layout);
        backImage = findViewById(R.id.tool_bar_back);
        backImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tool_bar_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorManager!=null){//注册监听器
            sensorManager.registerListener(sensorEventListener,sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),SensorManager.SENSOR_DELAY_NORMAL);
            //第一个参数是Listener，第二个参数是所得传感器类型，第三个参数值获取传感器信息的频率
        }
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(sensorManager!=null){
            sensorManager.unregisterListener(sensorEventListener);
        }
    }
    /**
     * 重力感应监听
     * */
    private SensorEventListener sensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            //传感器信息改变时执行该方法
            float[] values=event.values;
            float x=values[0];    // x轴方向的重力加速度，向右为正
            float y=values[1];    // y轴方向的重力加速度，向前为正
            float z=values[2];    // z轴方向的重力加速度，向上为正
            Log.i(TAG,"x轴方向的重力加速度"+x+";y轴方向的重力加速度"+y+";z轴方向重力加速度"+z);
            //一般在这三个方向的重力加速度达到40就达到了摇晃手机的状态
            int medumValue =13;//如果不敏感请自行调低该数值，低于10的话就不行了，因为Z轴上的加速度本身就达到了10
            if (Math.abs(x) > medumValue || Math.abs(y) > medumValue || Math.abs(z) > medumValue) {
                vibrator.vibrate(200);
                Message msg = new Message();
                msg.what = SENSOR_SHAKE;
                handler.sendMessage(msg);
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };


}
