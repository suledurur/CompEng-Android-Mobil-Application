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

public class Arastirma extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String,List<String>> listHashMap;
    ExpandableListAdapter adapter;
    Toolbar toolbar;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arastirma);

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
        listGroup.add(getString(R.string.arastirma1));
        listGroup.add(getString(R.string.arastirma2));
        listGroup.add(getString(R.string.arastirma3));
        listGroup.add(getString(R.string.arastirma4));
        listGroup.add(getString(R.string.arastirma5));


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
                if (listHashMap.get(listGroup.get(i)) != null) {
                    switch (listGroup.get(i)) {
                        case "Laboratuvarlar":
                        case "Laboratories":
                            url=getResources().getString(R.string.laboratuvarlar_url);
                            break;
                        case "Projeler":
                        case "Projects":
                            url=getResources().getString(R.string.projeler_url);
                            break;
                        case "Yayınlar":
                        case "Publications":
                            url=getResources().getString(R.string.yayınlar_url);
                            break;
                        case "Patentler":
                        case "Patents":
                            url=getResources().getString(R.string.patenler_url);
                            break;
                        case "İşbirlikleri":
                        case "Collaborations":
                            url=getResources().getString(R.string.işbirlikleri_url);
                            break;
                        default:
                            break;

                    }
                    Intent intent=new Intent(Arastirma.this,WebViews.class);
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
