package com.readystatesoftware.systembartint.sample;

import android.graphics.Color;

/**
 * @author 张全
 */

public class ImageBgActivity extends StatusBarActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_img;
    }

    @Override
    public void init() {
        //占用状态栏位置
        fitsSystemWindows = false;
        tintManager.setStatusBarTintColor(Color.TRANSPARENT);
    }
}
