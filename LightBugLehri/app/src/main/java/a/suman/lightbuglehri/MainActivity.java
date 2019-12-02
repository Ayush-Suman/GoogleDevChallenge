package a.suman.lightbuglehri;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tv;
    TextView t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.iv);
        t= findViewById(R.id.textView);
        tv = findViewById(R.id.textView2);
            Animation a = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein);
            Animation a2 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein2);
            Animation a3 = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fadein3);
            imageView.setAnimation(a);
            tv.setAnimation(a2);
            t.setAnimation(a3);
        new Handler().postDelayed(new Runnable() {
                                      @Override
                                      public void run() {
                                          Intent i = new Intent(MainActivity.this, HomeActiviy.class);
                                          startActivity(i);
                                          finish();
                                      }
                                  }, 10000

        );
    }

}