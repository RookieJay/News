package com.zjc.news.view.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.zjc.news.BaseActivity;
import com.zjc.news.controller.ActivityController;
import com.zjc.news.model.User;
import com.zjc.news.utils.ToastUtil;
import com.zjc.news.view.adapter.MyFragmentPagerAdapter;
import com.zjc.news.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;

//    private SwipeRefreshLayout swipeRefresh;    //下拉刷新

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private MyFragmentPagerAdapter pagerAdapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;
    private TabLayout.Tab five;
    private TabLayout.Tab six;
    private TabLayout.Tab seven;
    private TabLayout.Tab eight;
    private TabLayout.Tab nine;
    private TabLayout.Tab ten;

    private CircleImageView circleImageView;

    private ImageView header_bg;

    private TextView username_txt;

    private TextView email_txt;

    //相机
    public final int TYPE_TAKE_PHOTO = 1;//Uri获取类型判断

    public final int CODE_TAKE_PHOTO = 1;//相机RequestCode


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        initSystemBar(true);
        //初始化视图
        initViews();
//        initPersonalData();
//        swipeRefresh = findViewById(R.id.swipe_refresh);
//        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                refreshNews();
//            }
//        });
//        swipeRefresh.setRefreshing(false);
    }

    private void initViews() {

        //使用适配器将viewPager和fragment绑定在一起
        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        //将tabLayout和viewPager绑定在一起
        tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);

        //指定Tab的位置
        one = tabLayout.getTabAt(0);
        two = tabLayout.getTabAt(1);
        three = tabLayout.getTabAt(2);
        four = tabLayout.getTabAt(3);
        five = tabLayout.getTabAt(4);
        six = tabLayout.getTabAt(5);
        seven = tabLayout.getTabAt(6);
        eight = tabLayout.getTabAt(7);
        nine = tabLayout.getTabAt(8);
        ten = tabLayout.getTabAt(9);

        //设置Tab的图标
//        one.setIcon(R.mipmap.ic_launcher);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }

        Intent intent = getIntent();
        final User user = (User) intent.getSerializableExtra("user");
        final String username = intent.getStringExtra("userName");
        final String email = intent.getStringExtra("email");
        final String avatar = intent.getStringExtra("avatar");
        Log.d(">>>>接收intent", username+email+avatar);

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setItemIconTintList(null);   //设置默认icon颜色
        navigationView.setItemTextColor(null);  //设置title文字默认颜色：黑色

        //引入header和menu
        // 注意：在这里使用inflateHeaderView(R.layout.nav_header)会使布局重复,menu同理
//        navigationView.inflateHeaderView(R.layout.nav_header);
//        navigationView.inflateMenu(R.menu.navigation_menu);
        //获取头部布局

            final View navHeaderView = navigationView.getHeaderView(0);
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    item.setCheckable(false);
                    switch (item.getItemId()) {
                        case R.id.location:
                            ToastUtil.showToast(navHeaderView.getContext(), "点击了位置");
                            startActivity(new Intent(MainActivity.this,MapMainActivity.class));
//                            item.setCheckable(true);//设置选项可选
//                            item.setChecked(true);//设置选项被选中
                            break;

                        case R.id.nav_camera:
                            Intent takeIntent = new Intent(MainActivity.this, ShotActivity.class);
                            startActivity(takeIntent);
                            break;
                        case R.id.nav_scan:
                            break;
                        //跳转到天猫APP首页
                        case R.id.nav_shopping:
                            if (isAppInstalled(MainActivity.this,"com.tmall.wireless")) {
                                Intent intent2;
                                intent2 = getPackageManager().getLaunchIntentForPackage("com.tmall.wireless"); //这行代码比较重要
                                intent2.setAction("android.intent.action.VIEW");
                                startActivity(intent2);
                            } else {
                                ToastUtil.showToast(MainActivity.this,"您还未安装此程序");
                            }
                            break;
                        case R.id.nav_shake:
                            startActivity(new Intent(MainActivity.this, ShakeSensorActivity.class));
                            break;
                        case R.id.exit:
                            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                            builder.setCancelable(false)
                                   .setTitle("警告")
                                   .setMessage("您确定要退出程序吗？")
                                   .setPositiveButton("去意已决", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                                ActivityController.finishAll();
                                                android.os.Process.killProcess(android.os.Process.myPid()); //杀进程
                                        }
                                    })
                                   .setNegativeButton("我再想想", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog,
                                                            int which) {
                                        }
                                    })
                                   .show();
                        default:
                            break;
                    }
                    drawerLayout.closeDrawers();
                    return true;
                }
            });
            header_bg = navHeaderView.findViewById(R.id.header_bg);
            circleImageView = navHeaderView.findViewById(R.id.avatar);
            username_txt = navHeaderView.findViewById(R.id.username_tv);
            email_txt = navHeaderView.findViewById(R.id.email_tv);
            if (username==null||email==null||avatar==null) {
                Log.d(TAG, "进入条件");
                Glide.with(MainActivity.this).load(R.drawable.bg_avatar).into(header_bg);
                username_txt.setText("未登录");
                email_txt.setText("110110110@qq.com");
                Glide.with(MainActivity.this).load(R.drawable.mg).into(circleImageView);
                circleImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this,LoginActivity.class));
                        finish();
                    }
                });
            } else {
                Glide.with(MainActivity.this).load(R.drawable.bg_avatar).into(header_bg);
                username_txt.setText(username);
                email_txt.setText(email);
                Glide.with(MainActivity.this).load(avatar).into(circleImageView);
                circleImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent userInfoIntent = new Intent(MainActivity.this,UserInfoActivity.class);
                        userInfoIntent.putExtra("user", user);
                        userInfoIntent.putExtra("username",username);
                        userInfoIntent.putExtra("avatar",avatar);
                        startActivity(userInfoIntent);
                    }
                });
            }

    }


    private boolean isAppInstalled(Context context, String uri) {
        PackageManager pm = context.getPackageManager();
        boolean installed;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            installed = false;
        }
        return installed;
    }

    private void refreshNews() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000); //由于刷新非常快，马上就结束了需要将线程沉睡两秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                swipeRefresh.setRefreshing(false);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                Toast.makeText(this,"You clicked Backup",Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this,"You clicked Delete",Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this,"You clicked Settings",Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            default:
                break;
        }
        return true;
    }
}
