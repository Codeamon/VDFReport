package com.ayoka.vdfreport;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

/**
 * Created by ahmetyildirim on 3.5.2016.
 */
public class EnterActivity extends AppCompatActivity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    ImageView appIcon;
    Thread splashTread;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter);
        StartAnimations();
    }

    //    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_enter);
//
//        Thread enterScreen = new Thread() {
//            public void run() {
//                try {
//                    StartAnimations();
//                    sleep(3000);
//                    startActivity(new Intent(getApplicationContext(),LoginActivity.class));
//                } catch (InterruptedException e) {
//                    ;
//                } finally {
//                    finish();
//                }
//            }
//        };
//        enterScreen.start();
//    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l = (RelativeLayout) findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.splashvdf);
        iv.clearAnimation();
        iv.startAnimation(anim);





       splashTread = new Thread() {
           @Override
           public void run() {
               try {
                   int waited = 0;
                   // Splash screen pause time
                   while (waited < 3500) {
                       sleep(300);
                       waited += 300;
                   }
                   Intent intent = new Intent(EnterActivity.this,
                           LoginActivity.class);
                   intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                   startActivity(intent);
                   EnterActivity.this.finish();
               } catch (InterruptedException e) {
                   // do nothing
               } finally {
                   EnterActivity.this.finish();
               }
           }
       };
       splashTread.start();

    }
}
