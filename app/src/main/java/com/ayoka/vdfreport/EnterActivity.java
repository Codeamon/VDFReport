package com.ayoka.vdfreport;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ahmetyildirim on 3.5.2016.
 */
public class EnterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        Thread enterScreen = new Thread() {
            public void run() {
                try {
                    sleep(5000);
                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                } catch (InterruptedException e) {
                    ;
                } finally {
                    finish();
                }
            }
        };
        enterScreen.start();
    }
}
