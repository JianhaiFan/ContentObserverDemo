package com.xiaofan.contentobseverdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * @author: 范建海
 * @createTime: 2017/2/20 10:23
 * @className:  ProviderMainActivity
 * @description: 内容提供者演示界面
 * @changed by:
 */
public class ProviderMainActivity extends AppCompatActivity {

    private TextView tv_content;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provider);
        tv_content = (TextView) findViewById(R.id.tv_content);
    }

    public void onInsert(View v) {

        tv_content.setText("插入数据...");
    }

    public void onUpdate(View v) {

        tv_content.setText("更新数据...");
    }

    public void onDelete(View v) {

        tv_content.setText("删除数据...");
    }

    public void onQuery(View v) {

        tv_content.setText("查询数据...");
    }

}
