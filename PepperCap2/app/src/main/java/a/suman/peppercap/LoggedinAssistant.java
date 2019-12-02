package a.suman.peppercap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class LoggedinAssistant extends AppCompatActivity {

    UserInfo userInfo;
    FirebaseUser fbUser;
    FirebaseAuth firebaseAuth;
    TextView nameV, idV, emailV, logout;
    ImageView dpic;
    int N_users;
    int i;
    RecyclerView rView;
    ArrayList<String> UserIDs=new ArrayList<>();;
    UserListAdapter ulAdapter;
    DocumentSnapshot ds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loggedin_assistant);
        firebaseAuth = FirebaseAuth.getInstance();
        fbUser = FirebaseAuth.getInstance().getCurrentUser();

        rView= findViewById(R.id.recycler_view1);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(new LinearLayoutManager(this));



        nameV = findViewById(R.id.textView2);
        emailV = findViewById(R.id.textView3);
        idV = findViewById(R.id.textView4);
        logout = findViewById(R.id.logout);

        dpic = findViewById(R.id.dpic);

        userInfo = new UserInfo();
        userInfo.setId(getIntent().getStringExtra("ID"));
        userInfo.setName(getIntent().getStringExtra("Name"));
        userInfo.setEmail(getIntent().getStringExtra("Email"));
        userInfo.setLevel(getIntent().getStringExtra("Level"));

        FirebaseFirestore.getInstance().collection("Users").document(fbUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
               N_users= Integer.parseInt(documentSnapshot.get("N_user").toString());
               UserIDs.clear();
               ds=documentSnapshot;
               i=1;
               func();
               }
        });




        FirebaseStorage.getInstance().getReference().child("dpic"+"/"+userInfo.getId()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(LoggedinAssistant.this).load(uri).into(dpic);
            }
        });

        nameV.setText(userInfo.getName());
        emailV.setText(userInfo.getEmail());
        idV.setText(userInfo.getId());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(LoggedinAssistant.this);
                builder.setMessage("Are you sure you want to logout?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        Intent intent = new Intent(LoggedinAssistant.this, MainActivity.class);
                        startActivity(intent);
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                userInfo=null;
                System.gc();
                AlertDialog alert = builder.create();
                alert.show();

            }
        });
    }
    void func(){
        FirebaseFirestore.getInstance().collection("Users").document(ds.get("CAID_"+i).toString()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                UserIDs.add(documentSnapshot.get("ID").toString());
                if (i == (N_users)) {
                    ulAdapter = new UserListAdapter(LoggedinAssistant.this, UserIDs);
                    rView.setAdapter(ulAdapter);
                }else {
                    i++;
                    func();
                }
            }
        });
    }
}
