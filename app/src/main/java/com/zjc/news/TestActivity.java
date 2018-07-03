package com.zjc.news;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zjc.news.view.adapter.MyFragmentPagerAdapter;
import com.zjc.news.view.adapter.NewsAdapter;

public class TestActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    private SwipeRefreshLayout swipeRefresh;    //下拉刷新

    private TabLayout tabLayout;

    private ViewPager viewPager;

    private MyFragmentPagerAdapter pagerAdapter;

    private NewsAdapter adapter;

    private TabLayout.Tab one;
    private TabLayout.Tab two;
    private TabLayout.Tab three;
    private TabLayout.Tab four;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        //初始化视图
        initViews();
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.navigation_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.drawable.menu);
        }
        navigationView.setCheckedItem(R.id.location);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawers();
                return true;
            }
        });
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

        //设置Tab的图标
//        one.setIcon(R.mipmap.ic_launcher);

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
                adapter.notifyDataSetChanged();
                swipeRefresh.setRefreshing(false);
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
