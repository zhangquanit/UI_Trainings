package com.recycleview;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.recycleview.galley.GalleyActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 你想要控制其显示的方式，请通过布局管理器LayoutManager
 * 你想要控制Item间的间隔（可绘制），请通过ItemDecoration
 * 你想要控制Item增删的动画，请通过ItemAnimator
 * https://github.com/gabrielemariotti/RecyclerViewItemAnimators
 * <p>
 * 参考：
 * ---基本使用
 * http://blog.csdn.net/lmj623565791/article/details/45059587
 * ---添加headView和footView
 * http://blog.csdn.net/lmj623565791/article/details/51854533
 * http://blog.csdn.net/qibin0506/article/details/49716795
 * ---adapter封装
 * https://github.com/hongyangAndroid/baseAdapter
 */
public class MainActivity extends AppCompatActivity {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private SimpleAdapter simpleAdapter;
    private List<String> mDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();

    }

    private void initView() {
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        int size = mDataList.size();
                        List<String> data = new ArrayList<>();
                        int count = mDataList.size() + 1;
                        for (int i = size; i < count; i++) {
                            data.add("data->" + i);
                        }
                        mDataList.addAll(0, data);
                        simpleAdapter.notifyDataSetChanged();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 2 * 1000);
            }
        });

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        simpleAdapter = new SimpleAdapter(this, mDataList);
        mRecyclerView.setAdapter(simpleAdapter);

        /*
         * 设置垂直显示
         * public LinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
         * orientation:排列方向，水平还是垂直
         * reverseLayout：顺序排序还是 倒序排序
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        //设置分隔线
        DividerItemDecoration decor = new DividerItemDecoration(this, LinearLayoutManager.VERTICAL);//
        decor.setDivider(getResources().getDrawable(R.drawable.divider_02));
        mRecyclerView.addItemDecoration(decor);

        //设置动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        //事件回调
        simpleAdapter.setOnItemClickListener(new SimpleAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int pos) {
                Toast.makeText(MainActivity.this, "click : " + pos, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View v, int pos) {
                Toast.makeText(MainActivity.this, "long click : " + pos, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initData() {
        mDataList = new ArrayList<>();
        for (int i = 'A'; i <= 'Z'; i++) {
            mDataList.add("" + (char) i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add: //添加动画
                simpleAdapter.add(1);
                break;
            case R.id.action_delete: //删除动画
                simpleAdapter.delete(1);
                break;
            case R.id.action_listview://ListView
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.action_gridview://GridView
                mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));//1行3列
                break;
            case R.id.action_hor_listview:
                linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
                mRecyclerView.setLayoutManager(linearLayoutManager);
                break;
            case R.id.action_hor_gridview: //水平GridView
                /**
                 * StaggeredGridLayoutManager.VERTICAL代表有多少列；那么传入的如果是StaggeredGridLayoutManager.HORIZONTAL就代表有多少行
                 */
                //设置分隔线
                DividerGridItemDecoration decor = new DividerGridItemDecoration(this);//
                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.HORIZONTAL);
                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);
                break;
            case R.id.action_staggered: //瀑布流
                Intent intent = new Intent(this, StaggerGridViewActivity.class);
                startActivity(intent);
                break;
            case R.id.action_galleydAct: //Galley
                intent = new Intent(this, GalleyActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
