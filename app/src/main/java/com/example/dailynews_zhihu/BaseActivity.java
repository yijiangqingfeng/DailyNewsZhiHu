package com.example.dailynews_zhihu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class BaseActivity extends AppCompatActivity {
    public Toolbar toolbar;

    /**
     * @param title 标题栏标题
     */
    public void setTitle0(String title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);//设置为actionbar
    }
}
