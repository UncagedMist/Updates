package com.tbc.uncagedmist.cricketupdates.fragments;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.tbc.uncagedmist.cricketupdates.R;
import com.tbc.uncagedmist.cricketupdates.adapter.SeriesPointsTableAdapter;
import com.tbc.uncagedmist.cricketupdates.model.pointtablemodel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class SeriesDetailPointsFragment extends AppCompatActivity {

    private ArrayList<pointtablemodel> arrayList;
    private SeriesPointsTableAdapter adapter;
    RecyclerView recyclerView;
    Document document;
    String link;
    public static SeriesDetailPointsFragment newInstance() {
        return new SeriesDetailPointsFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_series_detail_news);

        link = getIntent().getExtras().getString("link");




         recyclerView = findViewById(R.id.rcSeriesDetailList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(SeriesDetailPointsFragment.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setHasFixedSize(true);
        arrayList = new ArrayList<>();
//        new Content().execute();

        getdata();
    }

    private void getdata() {
        try {


            document = Jsoup.connect(removeLastChar(link)+"stats").get();
            Elements allarray = document.select("div[class=\"cb-bg-white cb-col-100 cb-col\"]");
            Elements allarray2 = allarray.select("div[class=\"cb-col-100 cb-col\"]");
            Elements table = allarray2.select("table[class=table table-responsive cb-series-stats]");
            Elements thead = table.select("thead");
            Elements headers = thead.select("tr").select("th");



            ArrayList<String> headersList = new ArrayList<>();
            for (int i = 0; i < headers.size(); i++) {
                headersList.add(headers.get(i).text());
            }
            Elements tbody = allarray2.select("tbody");
            Elements allarray3 = tbody.select("tr[class=\"cb-srs-stats-tr\"]");

            for (int i = 0; i < allarray3.size(); i++) {
                pointtablemodel pointtablemodel = new pointtablemodel();
                Elements dataarr = allarray3.get(i).select("td");
                ArrayList<String> dataarrList = new ArrayList<>();
                for (int j = 0; j < dataarr.size(); j++) {
                    dataarrList.add(dataarr.get(j).text());
                }
                HashMap<Integer, ArrayList<String>> listHashMap = new HashMap<>();
                listHashMap.put(i, dataarrList);
                pointtablemodel.setTxt1(String.valueOf(dataarrList.get(0)));
                pointtablemodel.setTxt2(String.valueOf(dataarrList.get(1)));
                pointtablemodel.setTxt3(String.valueOf(dataarrList.get(2)));
                pointtablemodel.setTxt4(String.valueOf(dataarrList.get(3)));
                pointtablemodel.setTxt5(String.valueOf(dataarrList.get(4)));
                pointtablemodel.setTxt6(String.valueOf(dataarrList.get(5)));
                pointtablemodel.setTxt7(String.valueOf(dataarrList.get(6)));
                pointtablemodel.setTxt8(String.valueOf(dataarrList.get(7)));
                pointtablemodel.setTxt9(String.valueOf(dataarrList.get(8)));
                pointtablemodel.setTitletxt1(String.valueOf(headersList.get(0)));
                pointtablemodel.setTitletxt2(String.valueOf(headersList.get(1)));
                pointtablemodel.setTitletxt3(String.valueOf(headersList.get(2)));
                pointtablemodel.setTitletxt4(String.valueOf(headersList.get(3)));
                pointtablemodel.setTitletxt5(String.valueOf(headersList.get(4)));
                pointtablemodel.setTitletxt6(String.valueOf(headersList.get(5)));
                pointtablemodel.setTitletxt7(String.valueOf(headersList.get(6)));
                pointtablemodel.setTitletxt8(String.valueOf(headersList.get(7)));
                pointtablemodel.setTitletxt9(String.valueOf(headersList.get(8)));
                arrayList.add(pointtablemodel);
            }
            adapter = new SeriesPointsTableAdapter(SeriesDetailPointsFragment.this, arrayList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();






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
                document = Jsoup.connect(removeLastChar(link)+"stats").get();
                Elements allarray = document.select("div[class=\"cb-bg-white cb-col-100 cb-col\"]");
                Elements allarray2 = allarray.select("div[class=\"cb-col-100 cb-col\"]");
                Elements table = allarray2.select("table[class=table table-responsive cb-series-stats]");
                Elements thead = table.select("thead");
                Elements headers = thead.select("tr").select("th");
                ArrayList<String> headersList = new ArrayList<>();
                for (int i = 0; i < headers.size(); i++) {
                    headersList.add(headers.get(i).text());
                }
                Elements tbody = allarray2.select("tbody");
                Elements allarray3 = tbody.select("tr[class=\"cb-srs-stats-tr\"]");

                for (int i = 0; i < allarray3.size(); i++) {
                    pointtablemodel pointtablemodel = new pointtablemodel();
                    Elements dataarr = allarray3.get(i).select("td");
                    ArrayList<String> dataarrList = new ArrayList<>();
                    for (int j = 0; j < dataarr.size(); j++) {
                        dataarrList.add(dataarr.get(j).text());
                    }
                    HashMap<Integer, ArrayList<String>> listHashMap = new HashMap<>();
                    listHashMap.put(i, dataarrList);

                    pointtablemodel.setTxt1(String.valueOf(dataarrList.get(0)));
                    pointtablemodel.setTxt2(String.valueOf(dataarrList.get(1)));
                    pointtablemodel.setTxt3(String.valueOf(dataarrList.get(2)));
                    pointtablemodel.setTxt4(String.valueOf(dataarrList.get(3)));
                    pointtablemodel.setTxt5(String.valueOf(dataarrList.get(4)));
                    pointtablemodel.setTxt6(String.valueOf(dataarrList.get(5)));
                    pointtablemodel.setTxt7(String.valueOf(dataarrList.get(6)));
                    pointtablemodel.setTxt8(String.valueOf(dataarrList.get(7)));
                    pointtablemodel.setTxt9(String.valueOf(dataarrList.get(8)));
                    pointtablemodel.setTitletxt1(String.valueOf(headersList.get(0)));
                    pointtablemodel.setTitletxt2(String.valueOf(headersList.get(1)));
                    pointtablemodel.setTitletxt3(String.valueOf(headersList.get(2)));
                    pointtablemodel.setTitletxt4(String.valueOf(headersList.get(3)));
                    pointtablemodel.setTitletxt5(String.valueOf(headersList.get(4)));
                    pointtablemodel.setTitletxt6(String.valueOf(headersList.get(5)));
                    pointtablemodel.setTitletxt7(String.valueOf(headersList.get(6)));
                    pointtablemodel.setTitletxt8(String.valueOf(headersList.get(7)));
                    pointtablemodel.setTitletxt9(String.valueOf(headersList.get(8)));
                    arrayList.add(pointtablemodel);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            adapter = new SeriesPointsTableAdapter(SeriesDetailPointsFragment.this, arrayList);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }
    }

    public static String removeLastChar(String str) {
        return removeLastChars(str, 7);
    }

    public static String removeLastChars(String str, int chars) {
        return str.substring(0, str.length() - chars);
    }


}