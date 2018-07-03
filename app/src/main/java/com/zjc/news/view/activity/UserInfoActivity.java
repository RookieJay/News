package com.zjc.news.view.activity;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.zjc.news.BaseActivity;
import com.zjc.news.R;
import com.zjc.news.controller.ActivityController;
import com.zjc.news.model.User;

import org.w3c.dom.Text;

import de.hdodenhof.circleimageview.CircleImageView;

public class UserInfoActivity extends BaseActivity {

    public static final String USER = "user";

    public static final String USER_NAME = "username";

    public static final String USER_AVATAR = "avatar";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        Intent intent = getIntent();
        User user = (User) intent.getSerializableExtra(USER);
        String userName = intent.getStringExtra(USER_NAME);
        String userAvatar = intent.getStringExtra(USER_AVATAR);
        String userTel = user.getTel();
        String userSex = user.getSex();
        String userEmail = user.getEmail();
        String userRole = user.getRole();
        Toolbar toolbar = findViewById(R.id.tool_bar);
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsing_toolbar);
        CircleImageView infoAvatar = findViewById(R.id.user_info_avatar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbarLayout.setTitle(userName);
        collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER);
        Glide.with(this).load(userAvatar).into(infoAvatar);
        TextView tv_tel = findViewById(R.id.tv_tel);
        TextView tv_sex = findViewById(R.id.tv_sex);
        TextView tv_email = findViewById(R.id.tv_email);
        TextView tv_role = findViewById(R.id.tv_role);
        tv_tel.setText(userTel);
        tv_sex.setText(userSex);
        tv_email.setText(userEmail);
        tv_role.setText(userRole);
        TextView test = findViewById(R.id.test);
        String data = generateText("一个非常优秀的人");
        test.setText(data);
        ImageView user_bg_view = findViewById(R.id.user_bg_view);
        Glide.with(UserInfoActivity.this).load(R.drawable.bg_avatar).into(user_bg_view);
    }

    private String generateText(String test) {
        StringBuilder test_txt = new StringBuilder();
        for (int i=0;i<500;i++){
            test_txt.append(test);
        }
        return test_txt.toString();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
