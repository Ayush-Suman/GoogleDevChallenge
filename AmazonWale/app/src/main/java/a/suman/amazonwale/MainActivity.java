package a.suman.amazonwale;

import androidx.appcompat.app.AppCompatActivity;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;



public class MainActivity extends AppCompatActivity {

    String imgSrc;
    Bitmap bitmap;
    String txtdata;
    String url = "";
    TextView search_Txt;
    TextView result_Txt;
    ImageView search_Btn;
    ImageView result_Img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search_Txt = findViewById(R.id.search_Txt);
        search_Btn = findViewById(R.id.search_Btn);
        result_Txt = findViewById(R.id.result_Txt);
        result_Img = findViewById(R.id.result_Img);
        search_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String search = search_Txt.getText().toString();
                url = "https://www.amazon.in/s?k=" + search + "&ref=nb_sb_noss_2";
                new BgHandler().execute();
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        });
    }


    public class BgHandler extends AsyncTask<Void, Void, Void> {
        ;

        @Override
        public Void doInBackground(Void... params) {
            try {
                Document doc = Jsoup.connect(url).get();
                Element img = doc.select(".s-image").first();
                imgSrc = img.attr("src");
                InputStream inputStream = new java.net.URL(imgSrc).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);
                txtdata=img.attr("alt");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public void onPostExecute(Void result) {

            result_Txt.setText(txtdata);
            result_Img.setImageBitmap(bitmap);
        }

    }
}