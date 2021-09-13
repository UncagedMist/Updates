package com.tbc.uncagedmist.cricketupdates.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tbc.uncagedmist.cricketupdates.R;
//import com.cricket.live.line.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;


public class newsfullactivity extends AppCompatActivity {
    String url;
    TextView heading, textcaption;
    Document document;
    ImageView newsimage;

    String mainheading;
    String imagesection;
    String textparagraph;
    ImageView back;
    ScrollView scrolltextcaption;
    ProgressBar textloading;

    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsfullactivity);
        url = getIntent().getExtras().getString("link");

        heading = findViewById(R.id.headingmain);
        textcaption = findViewById(R.id.textcaption);
        newsimage = findViewById(R.id.imagenews);
        back = findViewById(R.id.back);
        scrolltextcaption = findViewById(R.id.scrolltextcaption);
        textloading = findViewById(R.id.textloading);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            textcaption.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        new Content().execute();
    }

    class Content extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();

        }

        @SuppressLint("WrongThread")
        @Override
        protected String doInBackground(Void... voids) {
            try {
                document = Jsoup.connect(url).get();
//                Elements alldata2 = document.select("div[class=cb-col cb-col-67 cb-nws-lft-col]");
//                Elements alldata = alldata2.select("div[id=news-list]");
                Elements allarray = document.select("div[class=cb-col cb-col-100 cb-bg-white]");
                imagesection = allarray.select("img[class=cursor-pointer]").attr("src");
                mainheading = allarray.select("h1").text();

//              textparagraph = allarray.select("p").text();
                String selectTags="div,li,p,ul,ol,span,table,tr,td,address,em,i,b";
                /*selecting some specific tags */
                Elements webContentElements = allarray.select(selectTags);
                String removeTags = "img,a,form";
                /*Removing some tags from selected elements*/
                webContentElements.select(removeTags).remove();
                textparagraph = allarray.select("p").toString();



            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @RequiresApi(api = Build.VERSION_CODES.N)
        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);



            heading.setText(mainheading);
            textcaption.setText(Html.fromHtml(textparagraph, Html.FROM_HTML_MODE_LEGACY));
            Glide.with(getApplicationContext())
                    .load("https://www.cricbuzz.com"+imagesection)
                    .into(newsimage);
            scrolltextcaption.setVisibility(View.VISIBLE);
            textloading.setVisibility(View.GONE);


//            new Content2().execute();
//            ldg.setVisibility(View.GONE);

        }
    }

}