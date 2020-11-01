package com.readystatesoftware.systembartint.sample;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.readystatesoftware.systembartint.SystemBarTintManager;

/**
 * @author 张全
 */

public abstract class StatusBarActivity extends Activity {
    protected SystemBarTintManager tintManager;
    protected boolean fitsSystemWindows = true;//true：不占用状态栏位置； false：占用状态栏位置

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        tintManager = new SystemBarTintManager(this);
        //支持显示状态栏
        tintManager.setStatusBarTintEnabled(true);
        //支持底部NavigationBar
        tintManager.setNavigationBarTintEnabled(true);

        init();

        //设置setFitsSystemWindows属性
        if (fitsSystemWindows && Build.VERSION.SDK_INT >= 14) {
            ViewGroup contentFrameLayout = (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
            View parentView = contentFrameLayout.getChildAt(0);
            if (null != parentView) parentView.setFitsSystemWindows(true);
        }
    }

    public abstract int getLayoutId();

    public abstract void init();
}
