package com.recyclerview2;

import java.util.ArrayList;


public class DemoApp {
    //  获取要显示的数据（初始化数据）
    public static ArrayList<SampleModel> getSampleData(int size) {
        ArrayList<SampleModel> sampleData = new ArrayList<SampleModel>(size);
        for (int i = 0; i < size; i++) {
            //  每一项数据后面都有相应的序号
            sampleData.add(new SampleModel("新的列表项<" + i + ">"));
        }

        return sampleData;
    }
}
