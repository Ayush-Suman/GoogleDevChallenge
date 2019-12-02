package a.suman.peppercap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;

import java.io.File;
import java.util.List;

public class Evaluation extends AppCompatActivity {
    RecyclerView rView;
    ImageView imageView;
    List<Bitmap> UserIDs;
    String UserID, ShareType;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);
        UserID=getIntent().getStringExtra("UserID");
        ShareType=getIntent().getStringExtra("ShareType");

    }
}
