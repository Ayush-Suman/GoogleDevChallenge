package a.suman.lightbuglehri;

import android.media.MediaPlayer;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActiviy extends AppCompatActivity {
    byte[] b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_activiy);
    MediaPlayer m= MediaPlayer.create(HomeActiviy.this, R.raw.gmd);
    m.start();
    }
        }




