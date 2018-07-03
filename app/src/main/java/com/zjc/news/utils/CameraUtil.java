package com.zjc.news.utils;

import android.net.Uri;
import android.os.Environment;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ZJC on 2018-06-24.
 */

public class CameraUtil {

    public  int TYPE_TAKE_PHOTO;//Uri获取类型判断

    public  int CODE_TAKE_PHOTO;//相机RequestCode

    public Uri getMediaFileUri(int type){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "相册名字");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        //创建Media File
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == TYPE_TAKE_PHOTO) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
        return Uri.fromFile(mediaFile);
    }
}
