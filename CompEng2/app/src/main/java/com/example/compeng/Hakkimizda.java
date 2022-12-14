package com.example.compeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Hakkimizda extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listHashMap;
    ExpandableListAdapter adapter;
    Toolbar toolbar;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkimizda);

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
        listGroup.add(getString(R.string.hakkimizda1));
        listGroup.add(getString(R.string.hakkimizda2));
        listGroup.add(getString(R.string.hakkimizda3));
        listGroup.add(getString(R.string.hakkimizda4));





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

        adapter.notifyDataSetChanged();
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (listHashMap.get(listGroup.get(i)) != null) {
                    switch (listGroup.get(i)) {
                        case "B??l??m Hakk??nda":
                        case "About the Department":
                            url=getResources().getString(R.string.b??l??mhakk??nda_url);
                            break;
                        case "??zg??r?? ve ??zg??rev":
                        case "Mission and Vision":
                            url=getResources().getString(R.string.??zg??r????zg??rev_url);
                            break;
                        case "Ba??ar??lar??m??z":
                        case "Our Success":
                            url=getResources().getString(R.string.ba??ar??lar??m??z_url);
                            break;
                        case "B??l??m Krokisi":
                        case "Section Sketch":
                            url=getResources().getString(R.string.b??l??mkrokisi_url);
                            break;
                        default:
                            break;

                    }
                    Intent intent=new Intent(Hakkimizda.this,WebViews.class);
                    intent.putExtra("url",url);
                    intent.putExtra("toolbar",listGroup.get(i));
                    startActivity(intent);
                    return true;
                }
                else
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