package com.ayoka.vdfreport;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.renderscript.Type;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by ahmetyildirim on 24.4.2016.
 */
public class ChartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activty_chart);

        int[] firstData={23,145,67,78,86,190,46,78,167,164};
        int[] secondData={83,45,168,138,67,150,64,87,144,188};
    }


}
