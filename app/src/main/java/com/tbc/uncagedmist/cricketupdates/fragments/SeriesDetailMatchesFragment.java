package com.tbc.uncagedmist.cricketupdates.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.Tools.AppAdOrganizer;
import com.tbc.uncagedmist.cricketupdates.adapter.AllSeriesMatchesAdapter;
import com.tbc.uncagedmist.cricketupdates.model.matchesmodel;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

public class SeriesDetailMatchesFragment extends AppCompatActivity {

    public static SeriesDetailMatchesFragment newInstance() {
        return new SeriesDetailMatchesFragment();
    }

    private ArrayList<matchesmodel> arrayList;
    private ArrayList<matchesmodel> previous;
    private ArrayList<matchesmodel> next;
    private AllSeriesMatchesAdapter adapter;
    RecyclerView recyclerView;
    LinearLayout nodataavailable;
    ProgressBar progress_bar;
    //    String URL = "https://m.cricbuzz.com/cricket-series/3130/indian-premier-league-2020/matches";
    Document document;
    String link, title;


    private NativeAdLayout fbnativeAdLayout;
    private NativeBannerAd fbnativeBannerAd;
    FrameLayout adrelative;
    private AdView adView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_series_detail);
        fbnativeAdLayout = findViewById(R.id.fbnative_banner_ad_container);
        adrelative = findViewById(R.id.adrelative);
        FrameLayout adViewAdaptiveBanner = findViewById(R.id.adViewAdaptiveBanner);

        if (appStructureBase.getFb_banner_series_detail() == 1) {
            fbnativeAdLayout.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
            adView = new AdView(this, appStructureBase.getFb_banner_id(), AdSize.BANNER_HEIGHT_50);
            fbnativeAdLayout.addView(adView);
            adView.loadAd();

        } else if(appStructureBase.getGoogle_banner_series_detail() == 1) {
            AppAdOrganizer.getInstance().loadAdMobBannerAd(SeriesDetailMatchesFragment.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(SeriesDetailMatchesFragment.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
        link = getIntent().getExtras().getString("link");
        title = getIntent().getExtras().getString("title");
        TextView titleprevious = findViewById(R.id.titleprevious);
        titleprevious.setText(title);
        recyclerView = findViewById(R.id.rcSeriesDetailList);
        progress_bar = findViewById(R.id.progress_bar);
        nodataavailable = findViewById(R.id.layoutNoData);
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        nodataavailable.setVisibility(View.GONE);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(SeriesDetailMatchesFragment.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        next = new ArrayList<>();
        previous = new ArrayList<>();
        new Content().execute();
//        getdata();

    }

    private void getdata() {
        try {
            document = Jsoup.connect(link + "/matches ").get();
            Elements allarray = document.select("div[class=\"col-sm-12 col-sm-12 col-md-12 col-lg-12 cb-col-news cb-col-matches\"]");
            Elements title = allarray.select("div[class=\"list-group cb-list-group cb-list-group-two\"]").select("a").select("h4");
            if (allarray.size() == 0) {
                nodataavailable.setVisibility(View.VISIBLE);
                progress_bar.setVisibility(View.GONE);
            } else {
                nodataavailable.setVisibility(View.GONE);
                for (int i = 0; i < allarray.size(); i++) {
                    Elements date = allarray.select("h4[class=\"cb-list-main-header\"]");
                    Elements typeofmatch = allarray.select("a");
                    for (int j = 0; j < typeofmatch.size(); j++) {



                        String allarray6 = date.get(j).select("span").attr("ng-bind");
                        ArrayList<String> dataarrList = new ArrayList<>();
                        for (int l = 0; l < allarray.size(); l++) {
                            dataarrList.add(allarray6);
                        }
                        String allarray7 = date.get(0).select("span").attr("ng-bind");
                        Elements aaaaa = typeofmatch.get(j).select("div");
                        ArrayList<String> asdf = new ArrayList<>();
                        for (int l = 0; l < aaaaa.size(); l++) {
                            asdf.add(aaaaa.get(l).text());
                        }


                        String[] split2 = allarray6.split("[|]");
                        String pattern2 = " hh:mm aa";
                        Date datae = new Date(Long.parseLong(split2[0].trim()));
                        DateFormat dateForma = new SimpleDateFormat(pattern2);
                        String aaa = dateForma.format(datae);


                        String[] split = allarray6.split("[|]");
                        String pattern = "MMM d, yyyy (E)";
                        Date dateq = new Date(Long.parseLong(split[0].trim()));
                        DateFormat dateFormat = new SimpleDateFormat(pattern);
                        dateFormat.format(dateq);
                        String todayAsString = dateFormat.format(dateq);

                        Calendar c = Calendar.getInstance();
                        DateFormat df = new SimpleDateFormat(pattern);
                        String formattedDate = df.format(c.getTime());
                        String titelmain = title.get(j).text();
                        String matchid = typeofmatch.get(j).attr("href");

                        Date oldDate = null;
                        try {
                            oldDate = dateFormat.parse(todayAsString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        DateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
                        Calendar cal = Calendar.getInstance();
                        cal.add(Calendar.DATE, -1);
                        dateFormat.format(cal.getTime());

//                        long diff = currentDate.getTime() - oldDate.getTime();
//                        long seconds = diff / 1000;
//                        long minutes = seconds / 60;
//                        long hours = minutes / 60;
//                        long days = hours / 24;



                        if (oldDate.after(cal.getTime())) {
                        matchesmodel matchesmodel = new matchesmodel();
                            String[] separated = matchid.split("/");
                            matchesmodel.setMatchid(separated[2]);
                            matchesmodel.setMatchname(titelmain);
                            matchesmodel.setMatchtype(aaaaa.first().text());
                            matchesmodel.setMatchstatus(aaaaa.last().text());
                            matchesmodel.setMatchdate(todayAsString);
                            matchesmodel.setTime(aaa);
                            previous.add(matchesmodel);

                         }

                      if (oldDate.before(cal.getTime())){

                          matchesmodel aa = new matchesmodel();

                            String[] separated = matchid.split("/");
                          aa.setMatchid(separated[2]);
                          aa.setMatchname(titelmain);
                          aa.setMatchtype(aaaaa.first().text());
                          aa.setMatchstatus(aaaaa.last().text());
                          aa.setMatchdate(todayAsString);
                          aa.setTime(aaa);
                          next.add(aa);
                        }



                    }


                }

//

                arrayList.addAll(previous);
                arrayList.addAll(next);




                adapter = new AllSeriesMatchesAdapter(SeriesDetailMatchesFragment.this, arrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                progress_bar.setVisibility(View.GONE);

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
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
                try {
                    document = Jsoup.connect(link + "/matches ").get();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                Elements allarray = document.select("div[class=\"col-sm-12 col-sm-12 col-md-12 col-lg-12 cb-col-news cb-col-matches\"]");
                Elements title = allarray.select("div[class=\"list-group cb-list-group cb-list-group-two\"]").select("a").select("h4");
                if (allarray.size() == 0) {
                    nodataavailable.setVisibility(View.VISIBLE);
                    progress_bar.setVisibility(View.GONE);
                } else {
                    nodataavailable.setVisibility(View.GONE);
                    for (int i = 0; i < allarray.size(); i++) {
                        Elements date = allarray.select("h4[class=\"cb-list-main-header\"]");
                        Elements typeofmatch = allarray.select("a");
                        for (int j = 0; j < typeofmatch.size(); j++) {


                            String allarray6 = date.get(j).select("span").attr("ng-bind");
                            ArrayList<String> dataarrList = new ArrayList<>();
                            for (int l = 0; l < allarray.size(); l++) {
                                dataarrList.add(allarray6);
                            }
                            String allarray7 = date.get(0).select("span").attr("ng-bind");
                            Elements aaaaa = typeofmatch.get(j).select("div");
                            ArrayList<String> asdf = new ArrayList<>();
                            for (int l = 0; l < aaaaa.size(); l++) {
                                asdf.add(aaaaa.get(l).text());
                            }


                            String[] split2 = allarray6.split("[|]");
                            String pattern2 = " hh:mm aa";
                            Date datae = new Date(Long.parseLong(split2[0].trim()));
                            DateFormat dateForma = new SimpleDateFormat(pattern2);
                            String aaa = dateForma.format(datae);


                            String[] split = allarray6.split("[|]");
                            String pattern = "MMM d, yyyy (E)";
                            Date dateq = new Date(Long.parseLong(split[0].trim()));
                            DateFormat dateFormat = new SimpleDateFormat(pattern);
                            dateFormat.format(dateq);
                            String todayAsString = dateFormat.format(dateq);

                            Calendar c = Calendar.getInstance();
                            DateFormat df = new SimpleDateFormat(pattern);
                            String formattedDate = df.format(c.getTime());
                            String titelmain = title.get(j).text();
                            String matchid = typeofmatch.get(j).attr("href");

                            Date oldDate = null;
                            try {
                                oldDate = dateFormat.parse(todayAsString);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            DateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
                            Calendar cal = Calendar.getInstance();
                            cal.add(Calendar.DATE, -1);
                            dateFormat.format(cal.getTime());

//                        long diff = currentDate.getTime() - oldDate.getTime();
//                        long seconds = diff / 1000;
//                        long minutes = seconds / 60;
//                        long hours = minutes / 60;
//                        long days = hours / 24;


                            if (oldDate.after(cal.getTime())) {
                                matchesmodel matchesmodel = new matchesmodel();
                                String[] separated = matchid.split("/");
                                matchesmodel.setMatchid(separated[2]);
                                matchesmodel.setMatchname(titelmain);
                                matchesmodel.setMatchtype(aaaaa.first().text());
                                matchesmodel.setMatchstatus(aaaaa.last().text());
                                matchesmodel.setMatchdate(todayAsString);
                                matchesmodel.setTime(aaa);
                                previous.add(matchesmodel);

                            }

                            if (oldDate.before(cal.getTime())) {

                                matchesmodel aa = new matchesmodel();

                                String[] separated = matchid.split("/");
                                aa.setMatchid(separated[2]);
                                aa.setMatchname(titelmain);
                                aa.setMatchtype(aaaaa.first().text());
                                aa.setMatchstatus(aaaaa.last().text());
                                aa.setMatchdate(todayAsString);
                                aa.setTime(aaa);
                                next.add(aa);
                            }


                        }


                    }

//

                    arrayList.addAll(previous);
                    arrayList.addAll(next);


                }
            } catch (Exception e) {
                e.printStackTrace();
            }





//                    matchesmodel.setMatchname(String.valueOf(title));
//                    matchesmodel.setMatchtype(String.valueOf(typematch));
//
//
//
//
//                }



            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new AllSeriesMatchesAdapter(SeriesDetailMatchesFragment.this, arrayList);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            adapter.notifyDataSetChanged();
            progress_bar.setVisibility(View.GONE);

        }
    }

}