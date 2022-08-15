package com.example.compeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Lisansustu extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listHashMap;
    ExpandableListAdapter adapter;
    Toolbar toolbar;
    String[] url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lisansustu);


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
        listGroup.add(getString(R.string.lisansustu1));
        listGroup.add(getString(R.string.lisansustu2));
        listGroup.add(getString(R.string.lisansustu3));


        String[] array;
        List<String> list1=new ArrayList<>();
        array=getResources().getStringArray(R.array.lisansustu1);
        for (String item:array){
            list1.add(item);
        }
        List<String> list2=new ArrayList<>();
        array=getResources().getStringArray(R.array.lisansustu2);
        for (String item:array){
            list2.add(item);
        }
        List<String> list3=new ArrayList<>();
        array=getResources().getStringArray(R.array.lisansustu3);
        for (String item:array){
            list3.add(item);
        }

        listHashMap.put(listGroup.get(0),list1);
        listHashMap.put(listGroup.get(1),list2);
        listHashMap.put(listGroup.get(2),list3);
        adapter.notifyDataSetChanged();
        expandableListView.setOnChildClickListener((expandableListView, view, i, i1, l) -> {
            if (listHashMap.get(listGroup.get(i)) != null) {
                switch (listGroup.get(i)) {
                    case "Genel Bilgi":
                    case "General Information":
                        url=getResources().getStringArray(R.array.lisansüstügenelbilgi_url);
                        break;
                    case "Tezli Yüksek Lisans":
                    case "Master with Thesis":
                        url=getResources().getStringArray(R.array.tezliyükseklisans_url);
                        break;
                    case "Doktora":
                    case "Doctorate":
                        url=getResources().getStringArray(R.array.doktora_url);
                        break;
                    default:
                        break;

                }
                Intent intent=new Intent(Lisansustu.this,WebViews.class);
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