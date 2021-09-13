package com.tbc.uncagedmist.cricketupdates.fragments;

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
import com.tbc.uncagedmist.cricketupdates.adapter.SeriesStatesAdapter;
import com.tbc.uncagedmist.cricketupdates.model.statsmodel;
import com.facebook.ads.AdSize;
import com.facebook.ads.AdView;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.NativeBannerAd;
//import com.cricket.live.line.activity.StartActivity;
//import com.cricket.live.line.adapter.SeriesStatesAdapter;
//import com.cricket.live.line.model.statsmodel;
//import com.cricket.live.line.utils.AdUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

import static com.tbc.uncagedmist.cricketupdates.Tools.AppTimeHandler.appStructureBase;

public class SeriesDetailStatsFragment extends AppCompatActivity {
    private ArrayList<statsmodel> arrayList;
    private SeriesStatesAdapter adapter;
    RecyclerView recyclerView;
    String link;
    LinearLayout layoutNoData;

//    private UnifiedNativeAd nativeAd;

    String newwwwwww;
    ProgressBar newsloaderjsoup;
//    String URL = "https://www.cricbuzz.com/cricket-series/2888/pakistan-tour-of-england-2020/points-table";
    Document document;
    private String title;
    private FrameLayout adViewNative;

    public static SeriesDetailStatsFragment newInstance() {
        return new SeriesDetailStatsFragment();
    }

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
            AppAdOrganizer.getInstance().loadAdMobBannerAd(SeriesDetailStatsFragment.this, adViewAdaptiveBanner, AppAdOrganizer.getInstance().getAdSize(Objects.requireNonNull(SeriesDetailStatsFragment.this), adViewAdaptiveBanner));
            adViewAdaptiveBanner.setVisibility(View.VISIBLE);
            adrelative.setVisibility(View.VISIBLE);
        }else {
            adViewAdaptiveBanner.setVisibility(View.GONE);
            fbnativeAdLayout.setVisibility(View.GONE);
            adrelative.setVisibility(View.GONE);
        }
















        link = getIntent().getExtras().getString("link");
        title = getIntent().getExtras().getString("title");

        newwwwwww=link.replace("/cricket-series/","/cricket-pointstable/");
        recyclerView = findViewById(R.id.rcSeriesDetailList);
        newsloaderjsoup = findViewById(R.id.progress_bar);
        layoutNoData = findViewById(R.id.layoutNoData);
        TextView titleprevious=findViewById(R.id.titleprevious);
        titleprevious.setText(title);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(SeriesDetailStatsFragment.this, 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        new Content().execute();
        getdata();
    }

    private void getdata() {
        try {
            document = Jsoup.connect(newwwwwww).get();
            Elements allarray = document.select("table[class=\"table table-condensed \"]");
            Elements thead = allarray.select("tbody");
            Elements headers = thead.select("tr").select("td[class=\"cbz-grid-table-fix \"]").select("b");
            Elements titlearray2 = allarray.select("tr[class=cb-srs-gray-strip]").select("td");
            Elements tbody = allarray.select("tbody");
            Elements allarray3 = tbody.select("tr[class]");



            if (allarray3.size() == 0) {
                layoutNoData.setVisibility(View.VISIBLE);
                newsloaderjsoup.setVisibility(View.GONE);
            }
            else {

                ArrayList<String> titlearray10 = new ArrayList<>();
                for (int j = 0; j < headers.size(); j++) {
                    titlearray10.add(headers.get(j).text());
                }
                ArrayList<String> titlearray11 = new ArrayList<>();
                for (int j = 0; j < titlearray2.size(); j++) {
                    titlearray11.add(titlearray2.get(j).text());
                }


                for (int i = 1; i < allarray3.size(); i++) {
                    statsmodel statsmodel = new statsmodel();
                    Elements dataarr = allarray3.get(i).select("td");
                    ArrayList<String> dataarrList = new ArrayList<>();
                    for (int j = 0; j < dataarr.size(); j++) {
                        dataarrList.add(dataarr.get(j).text());
                    }
                    HashMap<Integer, ArrayList<String>> listHashMap = new HashMap<>();
                    listHashMap.put(i, dataarrList);
                    statsmodel.setTxt1(String.valueOf(dataarrList.get(0)));
                    statsmodel.setTxt2(String.valueOf(dataarrList.get(1)));
                    statsmodel.setTxt3(String.valueOf(dataarrList.get(2)));
                    statsmodel.setTxt4(String.valueOf(dataarrList.get(3)));
                    statsmodel.setTxt5(String.valueOf(dataarrList.get(4)));
                    statsmodel.setTxt6(String.valueOf(dataarrList.get(5)));
                    statsmodel.setTitle1(String.valueOf(titlearray10.get(0)));
                    statsmodel.setTitle2(String.valueOf(titlearray10.get(1)));
                    statsmodel.setTitle3(String.valueOf(titlearray10.get(2)));
                    statsmodel.setTitle4(String.valueOf(titlearray10.get(3)));
                    statsmodel.setTitle5(String.valueOf(titlearray10.get(4)));
                    statsmodel.setTitle6(String.valueOf(titlearray10.get(5)));
                    arrayList.add(statsmodel);
                }
                adapter = new SeriesStatesAdapter(SeriesDetailStatsFragment.this, arrayList);
                recyclerView.setAdapter(adapter);
                recyclerView.setVisibility(View.VISIBLE);
                adapter.notifyDataSetChanged();
                newsloaderjsoup.setVisibility(View.GONE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }





}