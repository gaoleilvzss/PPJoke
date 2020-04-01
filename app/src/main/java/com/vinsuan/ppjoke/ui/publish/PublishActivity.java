package com.vinsuan.ppjoke.ui.publish;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.vinsuan.libnavannotation.ActivityDestination;
import com.vinsuan.ppjoke.R;
@ActivityDestination(pageUrl = "main/tabs/publish",asStarter = false)
public class PublishActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_publish);
    }
}
