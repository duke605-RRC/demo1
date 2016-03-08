package io.github.duke605.gsondemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import io.github.duke605.gsondemo.task.TaskReadApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new TaskReadApi(this).execute("http://modlist.mcf.li/api/v3/all.json");
    }
}
