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

public class Lisans extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listHashMap;
    ExpandableListAdapter adapter;
    Toolbar toolbar;
    String[] url;
    String _url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisans);


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
        listGroup.add(getString(R.string.lisans1));
        listGroup.add(getString(R.string.lisans2));
        listGroup.add(getString(R.string.lisans3));
        listGroup.add(getString(R.string.lisans4));
        listGroup.add(getString(R.string.lisans5));

        String[] array;
        List<String> list1=new ArrayList<>();
        array=getResources().getStringArray(R.array.lisans1);
        for (String item:array){
            list1.add(item);
        }
        List<String> list2=new ArrayList<>();
        array=getResources().getStringArray(R.array.lisans2_3);
        for (String item:array){
            list2.add(item);
        }
        List<String> list3=new ArrayList<>();
        array=getResources().getStringArray(R.array.lisans4);
        for (String item:array){
            list3.add(item);
        }
        List<String>list4=new ArrayList<>();
        array=getResources().getStringArray(R.array.lisans5);
        for (String item:array){
            list4.add(item);
        }


        listHashMap.put(listGroup.get(0),list1);
        listHashMap.put(listGroup.get(1),list2);
        listHashMap.put(listGroup.get(2),list2);
        listHashMap.put(listGroup.get(3),list3);
        listHashMap.put(listGroup.get(4),list4);
        adapter.notifyDataSetChanged();

        expandableListView.setOnChildClickListener((expandableListView, view, i, i1, l) -> {
            if (listHashMap.get(listGroup.get(i)) != null) {
                switch (listGroup.get(i)) {
                    case "Genel Bilgi":
                    case "General Information":
                        url=getResources().getStringArray(R.array.lisansgenelbilgi_url);
                        break;
                    case "Müfredat":
                    case "Curriculum":
                        url=getResources().getStringArray(R.array.lisansmüfredat_url);
                        break;
                    case "Ders İçerikleri":
                    case "Course Contents":
                        url=getResources().getStringArray(R.array.lisansdersiçerikleri_url);
                        break;
                    case "Ders Sayfaları":
                    case "Course Pages":
                        url=getResources().getStringArray(R.array.lisansderssayfaları_url);
                        break;
                    case "Staj":
                    case "Internship":
                        url = getResources().getStringArray(R.array.lisansstaj_url);
                        break;
                    default:
                        break;

                }
                Intent intent=new Intent(Lisans.this,WebViews.class);
                intent.putExtra("url",url[i1]);
                intent.putExtra("toolbar",listHashMap.get(this.listGroup.get(i)).get(i1));
                startActivity(intent);
                return true;
            }
            else
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