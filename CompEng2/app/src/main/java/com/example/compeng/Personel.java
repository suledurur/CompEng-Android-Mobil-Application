package com.example.compeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Personel extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listHashMap;
    ExpandableListAdapter adapter;
    Toolbar toolbar;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personel);

        toolbar = findViewById(R.id.submenu);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        expandableListView=findViewById(R.id.exp1);
        listGroup=new ArrayList<>();
        listHashMap=new HashMap<>();
        adapter=new ExpandableListAdapter(this,listGroup,listHashMap);
        expandableListView.setAdapter(adapter);
        initListData();

    }
    private void initListData(){
        listGroup.add(getString(R.string.personel1));
        listGroup.add(getString(R.string.personel2));
        listGroup.add(getString(R.string.personel3));
        listGroup.add(getString(R.string.personel4));
        listGroup.add(getString(R.string.personel5));


        String[] array;

        List<String> empty=new ArrayList<>();
        array=getResources().getStringArray(R.array.empty);
        for (String item:array){
            empty.add(item);
        }

        listHashMap.put(listGroup.get(0),empty);
        listHashMap.put(listGroup.get(1),empty);
        listHashMap.put(listGroup.get(2),empty);
        listHashMap.put(listGroup.get(3),empty);
        listHashMap.put(listGroup.get(4),empty);
        adapter.notifyDataSetChanged();
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (listHashMap.get(listGroup.get(i))!=null) {
                    switch (listGroup.get(i)) {
                        case "Bölüm Başkanlığı":
                        case "Head of Department":
                            url=getResources().getString(R.string.bölümbaşk_url);
                            break;
                        case "Akademik Personel":
                        case "Academical Personal":
                            url=getResources().getString(R.string.akademikpers_url);
                            break;
                        case "İdari Personel":
                        case "Administrative Staff":
                            url=getResources().getString(R.string.idaripers_url);
                            break;
                        case "Komisyonlar":
                        case "Commissions":
                            url=getResources().getString(R.string.komisyonlar_url);
                            break;
                        case "Emekli Öğretim Üyeleri":
                        case "Retired Faculty Members":
                            url=getResources().getString(R.string.emekliöğr_url);
                            break;
                        default:
                            break;

                    }
                    Intent intent=new Intent(Personel.this,WebViews.class);
                    intent.putExtra("url",url);
                    intent.putExtra("toolbar",listGroup.get(i));
                    startActivity(intent);
                }
                    return false;
            }

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