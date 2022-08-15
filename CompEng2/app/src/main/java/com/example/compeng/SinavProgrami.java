package com.example.compeng;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class SinavProgrami extends AppCompatActivity {
    ExpandableListView expandableListView;
    List<String> listGroup;
    HashMap<String, List<String>> listHashMap;
    ExpandableListAdapter adapter;
    Toolbar toolbar;
    String _url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinav_programi);
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
        listGroup.add(getString(R.string.sınavprogramı1));
        listGroup.add(getString(R.string.sınavprogramı2));
        listGroup.add(getString(R.string.sınavprogramı3));

        String[] array;

        array = getResources().getStringArray(R.array.empty);
        List<String> empty = new ArrayList<>(Arrays.asList(array));

        listHashMap.put(listGroup.get(0), empty);
        listHashMap.put(listGroup.get(1), empty);
        listHashMap.put(listGroup.get(2), empty);
        adapter.notifyDataSetChanged();

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                if (listHashMap.get(listGroup.get(i)).size() == 0) {
                    switch (listGroup.get(i)) {
                        case "Lisans Final Programı":
                        case "Undergraduate":
                            _url = getResources().getString(R.string.lisanssınavprog_url);
                            break;
                        case "Lisansüstü Final Programı":
                        case "Graduate":
                            _url = getResources().getString(R.string.lisansüstüsınavprog_url);
                            break;
                        case "Bütünleme Programı":
                        case "Makeup Exam":
                            _url = getResources().getString(R.string.bütünlemeprog_url);
                            break;
                        default:
                            break;
                    }
                    Intent intent = new Intent(SinavProgrami.this, WebViews.class);
                    intent.putExtra("url", _url);
                    intent.putExtra("toolbar", listGroup.get(i));
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