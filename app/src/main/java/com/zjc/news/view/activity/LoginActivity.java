package com.zjc.news.view.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zjc.news.BaseActivity;
import com.zjc.news.R;
import com.zjc.news.model.User;
import com.zjc.news.utils.ToastUtil;


import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ZJC on 2018-06-21.
 */

public class LoginActivity extends BaseActivity {

    private static final String TAG = "LoginActivity";

    private SharedPreferences pref;

    private  SharedPreferences.Editor editor;

    private EditText accountEdit;

    private EditText passwordEdit;

    private Button login;

    private Button offline_login;

    private CheckBox remember_pwd;

    private TextView to_reg_txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //        用SharedPreferences储存数据
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        accountEdit = findViewById(R.id.et_account);
        passwordEdit = findViewById(R.id.et_userpwd);
        remember_pwd=findViewById(R.id.remember_pwd);
        login = findViewById(R.id.lg_bt);
        Boolean isRemember = pref.getBoolean("remember_password",false);
        if (isRemember){
            String account = pref.getString("account","");
            String password = pref.getString("password","");
            //将账号密码设置到文本框中
            accountEdit.setText(account);
            passwordEdit.setText(password);
            remember_pwd.setChecked(true);
        }
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //同步操作需要开启子线程，异步操作不需要，但两者都需要开启主线程进行UI操作
                Thread netThread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        postJson();
                    }
                });
                netThread.start();

            }
        });
        offline_login = findViewById(R.id.offline_login);
        offline_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                finish();
            }
        });
        to_reg_txt = findViewById(R.id.to_reg_txt);
        to_reg_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    private void postJson() {
        Log.d(TAG, "当前线程"+Thread.currentThread().getName());
        OkHttpClient okHttpClient = null;
        String userName = accountEdit.getText().toString();
        String userPwd = passwordEdit.getText().toString();
        if (userName.isEmpty() || userPwd.isEmpty()){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    ToastUtil.showToast(LoginActivity.this,"用户名或密码不能为空");
                }
            });
        }
        else {
            //创建一个OkHttpClient对象
            try{
                okHttpClient = new OkHttpClient.Builder()
                    .connectTimeout(1, TimeUnit.SECONDS)
                    .writeTimeout(1,TimeUnit.SECONDS)
                    .readTimeout(2, TimeUnit.SECONDS)
                    .build();
            } catch (Exception e) {
                Log.d(TAG, "请求超时");
                e.printStackTrace();
            }
            FormBody.Builder builder = new FormBody.Builder();
            builder.add("userName", userName);
            builder.add("userPwd", userPwd);
            //创建一个RequestBody(参数1：数据类型 参数2传递的json串)
            RequestBody requestBody = builder.build();
            //创建一个请求对象
            Request request = new Request.Builder()
                    .url("http://zhoujuncai.vicp.io:57414/car/getJson")
                    .post(requestBody)
                    .build();
            okhttp3.Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    if (e instanceof SocketTimeoutException){
                        Log.d(TAG, "登录超时");
                    }
                    if (e instanceof ConnectException) {
                        Log.d(TAG, "连接异常，请检查网络");
                    }
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    //打印服务端返回结果
                    String userString = response.body().string();
                    Log.i(TAG, userString);
                    if (!userString.isEmpty()) {
                        Log.d(TAG, "匹配成功");
                        showResponse(userString);
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ToastUtil.showToast(LoginActivity.this, "用户名或密码错误");
                            }
                        });
                        Log.d(TAG, "匹配失败");
                    }
                }
            });
        }
    }

    private void showResponse(final String userString) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                String userName = accountEdit.getText().toString();
                String userPwd = passwordEdit.getText().toString();
                if (!userString.isEmpty()) {
                    editor = pref.edit();
                    if(remember_pwd.isChecked()){
                        editor.putBoolean("remember_password",true);
                        editor.putString("account",userName);
                        editor.putString("password",userPwd);
                    }else{
                        editor.clear();
                    }
                    editor.apply();
                    Gson gson = new Gson();
                    User user = gson.fromJson(userString,User.class);
                    Log.d(TAG, "解析得到的user"+user.getUserName()+user.getEmail()+user.getAvatar());
                    Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                    intent.putExtra("user", user);
                    intent.putExtra("userName",user.getUserName());
                    intent.putExtra("email",user.getEmail());
                    intent.putExtra("avatar","http://zhoujuncai.vicp.io:57414/car/"+user.getAvatar());
                    startActivity(intent);
                    Log.d(TAG, "最终登录成功");
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this,"用户名或密码无效",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
