package com.zjc.news.controller;


import android.app.Activity;
import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZJC on 2018-06-01.
 */

public class ActivityController extends AppCompatActivity {

    private static List<Activity> activityList = new ArrayList<>();

    public static void addActivity(Activity activity) {
        activityList.add(activity);
    }

    public static void removeActivity(Activity activity) {
        activityList.remove(activity);
    }

    public static void finishAll() {
        activityList.removeAll(activityList);
        for (Activity activity:activityList) {
            activity.finish();
        }
        activityList.clear();
    }
}
