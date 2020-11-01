package android.customview;

import android.app.Activity;
import android.customview.view.CustomView;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener {
    private Button btn_change;
    private CustomView customView;
    private List<String> methodList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(android.R.style.Theme_Light);//必须在setContentView前调用
        setContentView(R.layout.activity_main);

        customView = (CustomView) findViewById(R.id.customView);
        btn_change = (Button) findViewById(R.id.change);
        findViewById(R.id.change).setOnClickListener(this);

        methodList.add("setTextColor");
        methodList.add("setTextSize");
        methodList.add("setText");
    }

    private int index;

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.change:
                if (index == 0) {
                    customView.setVisibility(View.GONE);
                    btn_change.setText("GONE");
                } else if (index == 1) {
                    customView.setVisibility(View.VISIBLE);
                    btn_change.setText("VISIBLE");
                } else if (index == 2) {
                    customView.setVisibility(View.INVISIBLE);
                    btn_change.setText("INVISIBLE");
                } else if (index == 3) {
                    customView.setVisibility(View.VISIBLE);
                    btn_change.setText("VISIBLE");
                } else if (index == 4) {
                    customView.setTextColor(Color.YELLOW);
                    btn_change.setText("setTextColor");
                } else if (index == 5) {
                    customView.setTextSize(30);
                    btn_change.setText("setTextSize");
                } else if (index == 6) {
                    customView.setText("修改后的文字");
                    btn_change.setText("setText");
                }
                index++;
                break;
        }
    }
}
