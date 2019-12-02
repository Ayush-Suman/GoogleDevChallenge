package a.suman.peppercap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class MainActivity extends AppCompatActivity {
    public UserInfo uinfo;
    ImageView login;
    EditText user;
    EditText password;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener authStateListener;
    FirebaseFirestore db;
    String level;
    ProgressBar progressBar;
    String[] Users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login= findViewById(R.id.login);
        progressBar = findViewById(R.id.spinkit);
        firebaseAuth=FirebaseAuth.getInstance();
        db= FirebaseFirestore.getInstance();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if((!TextUtils.isEmpty(user.getText()))&&(!TextUtils.isEmpty(user.getText()))){
              progressBar.setVisibility(View.VISIBLE);
                login.setClickable(false);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                firebaseAuth.signInWithEmailAndPassword(user.getText().toString(), password.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                        if(!task.isSuccessful()){
                            login.setClickable(true);
                            progressBar.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Wrong Credentials", Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
            }
            else{
            Toast.makeText(MainActivity.this, "Enter the credentials", Toast.LENGTH_LONG).show();
            }
            }
        });
        FirebaseUser fbUser= firebaseAuth.getCurrentUser();
        if (fbUser!=null){
            firebaseAuth.signOut();
        }
        authStateListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser fbUser= firebaseAuth.getCurrentUser();
                if (fbUser!=null){
                 db.collection("Users").document(fbUser.getUid()).get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                     @Override
                     public void onSuccess(DocumentSnapshot documentSnapshot) {
                         uinfo=new UserInfo();
                         uinfo.setLevel(documentSnapshot.get("Level").toString());
                         uinfo.setName(documentSnapshot.get("Name").toString());
                         uinfo.setEmail(documentSnapshot.get("Email").toString());
                         uinfo.setId(documentSnapshot.get("ID").toString());
                         if(uinfo.getLevel().equals("3")){
                             uinfo.setAssistid(documentSnapshot.get("AssistID").toString());
                         }
                         progressBar.setVisibility(View.GONE);
                         loginaction();
                     }
                 });
                }
                }
        };
        firebaseAuth.addAuthStateListener(authStateListener);
    }
    private void loginaction(){
        Intent intent;
        level=uinfo.getLevel();
        switch (level){
            case "1": intent=new Intent(this,LoggedinAuth.class);
                intent.putExtra("ID",uinfo.getId());
                intent.putExtra("Name",uinfo.getName());
                intent.putExtra("Email",uinfo.getEmail());
                intent.putExtra("Level",uinfo.getLevel());
                login.setClickable(true);
                uinfo=null;
                System.gc();
                startActivity(intent);
                break;
            case "2":intent=new Intent(this,LoggedinAssistant.class);
                intent.putExtra("ID",uinfo.getId());
                intent.putExtra("Name",uinfo.getName());
                intent.putExtra("Email",uinfo.getEmail());
                intent.putExtra("Level",uinfo.getLevel());
                login.setClickable(true);
                uinfo=null;
                System.gc();
                startActivity(intent);
                break;
            case "3":
                intent=new Intent(this,Logedin.class);
                intent.putExtra("ID",uinfo.getId());
                intent.putExtra("Name",uinfo.getName());
                intent.putExtra("Email",uinfo.getEmail());
                intent.putExtra("Level",uinfo.getLevel());
                intent.putExtra("AssistID", uinfo.getAssistid());
                login.setClickable(true);
                uinfo=null;
                System.gc();
                startActivity(intent);
                break;
        }

    }
}
