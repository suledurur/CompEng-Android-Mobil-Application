package com.example.compeng;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class AdayOgrenci extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<String>> listHashMap;
    ExpandableListAdapter adapter;
    Toolbar toolbar;
    String[] url;
    String _url;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aday_ogrenci);

        toolbar = findViewById(R.id.submenu);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView = findViewById(R.id.exp1);
        listGroup = new ArrayList<>();
        listHashMap = new HashMap<>();
        adapter = new ExpandableListAdapter(this, listGroup, listHashMap);
        expandableListView.setAdapter(adapter);
        initListData();

    }

    private void initListData() {
        listGroup.add(getString(R.string.adayogr1));
        listGroup.add(getString(R.string.adayogr2));
        listGroup.add(getString(R.string.adayogr3));
        listGroup.add(getString(R.string.adayogr4));
        listGroup.add(getString(R.string.adayogr5));
        listGroup.add(getString(R.string.adayogr6));
        String[] array;


        array = getResources().getStringArray(R.array.adayogr1);
        List<String> list1 = new ArrayList<>(Arrays.asList(array));

        array = getResources().getStringArray(R.array.adayogr2);
        List<String> list2 = new ArrayList<>(Arrays.asList(array));
        array = getResources().getStringArray(R.array.adayogr3);
        List<String> list3 = new ArrayList<>(Arrays.asList(array));
        array = getResources().getStringArray(R.array.empty);
        List<String> empty = new ArrayList<>(Arrays.asList(array));

        listHashMap.put(listGroup.get(0), list1);
        listHashMap.put(listGroup.get(1), list2);
        listHashMap.put(listGroup.get(2), list3);
        listHashMap.put(listGroup.get(3), empty);
        listHashMap.put(listGroup.get(4),empty);
        listHashMap.put(listGroup.get(5), empty);
        adapter.notifyDataSetChanged();

        expandableListView.setOnGroupClickListener((expandableListView, view, i, l) -> {
        if (Objects.requireNonNull(listHashMap.get(listGroup.get(i))).isEmpty()) {
            switch (listGroup.get(i)) {
                case "Öğrenci Toplulukları":
                case "Student Communities":
                    _url=getResources().getString(R.string.öğrtoplulukları_url);
                    break;
                case "Sosyal Etkinlikler":
                case "Social Events":
                    _url = getResources().getString(R.string.sosyaletkinlikler_url);
                    break;
                case "Faydalı Linkler":
                case "Useful Links":
                    _url = getResources().getString(R.string.faydalılinkler_url);
                    break;
                default:
                    break;

            }
            Intent intent = new Intent(AdayOgrenci.this, WebViews.class);
            intent.putExtra("url", _url);
            intent.putExtra("toolbar", listGroup.get(i));
            startActivity(intent);
        }
            return false;
        });


        expandableListView.setOnChildClickListener((expandableListView, view, i, i1, l) -> {

            if (listHashMap.get(listGroup.get(i)) != null) {
                switch (listGroup.get(i)) {
                    case "Bölüm Tanıtımı":
                    case "Promotion":
                        url=getResources().getStringArray(R.array.bölümtanıtımı_url);
                        break;
                    case "Bölümün Tarihçesi":
                    case "Department History":
                        url=getResources().getStringArray(R.array.bölümüntarihçesi_url);
                        break;
                    case "Uluslararası Aday Öğrenci":
                    case "International Student":
                        url=getResources().getStringArray(R.array.uluslararasıadayöğr_url);
                        break;

                    default:
                        break;

                }
                    Intent intent=new Intent(AdayOgrenci.this,WebViews.class);
                    intent.putExtra("url", url[i1]);
                    intent.putExtra("toolbar",(Objects.requireNonNull(listHashMap.get(this.listGroup.get(i)))).get(i1));
                    startActivity(intent);

                }

           return false;
            });

}



    //Geri Butonu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}