package a.suman.peppercap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.UUID;

public class Logedin extends AppCompatActivity {
    UserInfo userInfo;
    FirebaseUser fbUser;
    FirebaseAuth firebaseAuth;
    TextView nameV, idV, emailV, logout;
    ImageView dpic, up1, up2, up3, up4;
    StorageReference storageReference;
    String uploadtype;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logedin);

        firebaseAuth = FirebaseAuth.getInstance();
        fbUser = FirebaseAuth.getInstance().getCurrentUser();


        nameV = findViewById(R.id.textView2);
        emailV = findViewById(R.id.textView3);
        idV = findViewById(R.id.textView4);
        logout = findViewById(R.id.logout);

        dpic = findViewById(R.id.dpic);
        up1 = findViewById(R.id.upload1);
        up2 = findViewById(R.id.upload2);
        up3 = findViewById(R.id.upload3);
        up4 = findViewById(R.id.upload4);
        userInfo = new UserInfo();
        userInfo.setId(getIntent().getStringExtra("ID"));
        userInfo.setName(getIntent().getStringExtra("Name"));
        userInfo.setEmail(getIntent().getStringExtra("Email"));
        userInfo.setLevel(getIntent().getStringExtra("Level"));
        userInfo.setAssistid(getIntent().getStringExtra("AssistID"));
        storageReference=FirebaseStorage.getInstance().getReference();
        FirebaseStorage.getInstance().getReference().child("dpic"+"/"+userInfo.getId()+".jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Glide.with(Logedin.this).load(uri).into(dpic);
            }
        });

        nameV.setText(userInfo.getName());
        emailV.setText(userInfo.getEmail());
        idV.setText(userInfo.getId());


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(Logedin.this);
                builder.setMessage("Are you sure you want to logout?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        firebaseAuth.signOut();
                        Intent intent = new Intent(Logedin.this, MainActivity.class);
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


        up1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtype="UploadType1";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        up2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtype="UploadType2";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        up3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtype="UploadType3";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });
        up4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uploadtype="UploadType4";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
            }
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.signOut();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Uri filePath;
        if (requestCode == 1 && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            if (filePath != null) {
                final ProgressDialog progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Uploading...");
                progressDialog.show();
                StorageReference ref = storageReference.child("images/" + userInfo.getAssistid()+"/"+userInfo.getId()+"/"+uploadtype+"/"+UUID.randomUUID().toString());
                ref.putFile(filePath)
                        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                progressDialog.dismiss();
                                Toast.makeText(Logedin.this, "Uploaded", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                                Toast.makeText(Logedin.this, "Failed " + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                            @Override
                            public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                                double progress = (100.0 * taskSnapshot.getBytesTransferred() / taskSnapshot
                                        .getTotalByteCount());
                                progressDialog.setMessage("Uploaded " + (int) progress + "%");
                            }
                        });
            }
        }
    }
}
