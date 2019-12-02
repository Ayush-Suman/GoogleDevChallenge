package a.suman.peppercap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ShareType extends AppCompatActivity {
    String UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_type);
        UserID=getIntent().getStringExtra("UserID");
        View.OnClickListener onClickListener= new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent= new Intent(ShareType.this, Evaluation.class);
                intent.putExtra("UserID", UserID);
                intent.putExtra("ShareType",((TextView)view).getText());
                startActivity(intent);
            }
        };
        findViewById(R.id.textView6).setOnClickListener(onClickListener);
        findViewById(R.id.textView7).setOnClickListener(onClickListener);
        findViewById(R.id.textView8).setOnClickListener(onClickListener);
        findViewById(R.id.textView9).setOnClickListener(onClickListener);
    }
}
