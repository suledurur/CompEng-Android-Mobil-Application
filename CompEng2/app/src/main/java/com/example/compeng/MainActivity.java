package com.example.compeng;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.google.android.material.navigation.NavigationView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Slider
        ViewPager viewPager;
        viewPager = findViewById(R.id.slider);
        Slider adapter = new Slider(this);
        viewPager.setAdapter(adapter);

//NavigateMenu
        DrawerLayout drawerLayout;
        NavigationView navigationView;
        Toolbar toolbar;

        drawerLayout = findViewById(R.id.drawerlayout);
        navigationView = findViewById(R.id.navigationView);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.anasayfa);

        //slider altı buton menüsü
        Button akademiktakvim, dersprogrami, sinavprogrami;
        akademiktakvim = findViewById(R.id.akademiktakvim);
        dersprogrami = findViewById(R.id.dersprogrami);
        sinavprogrami = findViewById(R.id.sinavprogrami);

    //cardview
        CardView duyurular,etkinlikler,seckiler,sks,obs,telgraf;
        duyurular=findViewById(R.id.duyurular);
        etkinlikler=findViewById(R.id.etkinlikler);
        seckiler=findViewById(R.id.seckiler);
        sks=findViewById(R.id.muhendislik);
        obs=findViewById(R.id.obs);
        telgraf=findViewById(R.id.telgraf);

        duyurular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WebViews.class);
                intent.putExtra("url",getResources().getString(R.string.duyurular_url));
                intent.putExtra("toolbar", getResources().getString(R.string.duyurular));
                startActivity(intent);
            }
        });
        seckiler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WebViews.class);
                intent.putExtra("url",getResources().getString(R.string.seçkiler_url));
                intent.putExtra("toolbar",getResources().getString(R.string.seçkiler));
                startActivity(intent);
            }
        });
        etkinlikler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WebViews.class);
                intent.putExtra("url",getResources().getString(R.string.etkinlikler_url));
                intent.putExtra("toolbar",getResources().getString(R.string.etkinlikler));
                startActivity(intent);
            }
        });

        sks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WebViews.class);
                intent.putExtra("url",getResources().getString(R.string.muhendislik_url));
                intent.putExtra("toolbar",getResources().getString(R.string.muhendislik));
                startActivity(intent);
            }
        });

        obs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WebViews.class);
                intent.putExtra("url",getResources().getString(R.string.obs_url));
                intent.putExtra("toolbar",getResources().getString(R.string.obs));
                startActivity(intent);
            }
        });
        telgraf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,WebViews.class);
                intent.putExtra("url",getResources().getString(R.string.telgraf_url));
                intent.putExtra("toolbar","TELGRAF");
                startActivity(intent);
            }
        });


        akademiktakvim.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                Intent intent = new Intent(MainActivity.this, WebViews.class);
                String url = getResources().getString(R.string.akademiktakvim_url);
                intent.putExtra("url", url);
                intent.putExtra("toolbar", getResources().getString(R.string.akademiktakvim));
                startActivity(intent);
            }
            });
       dersprogrami.setOnClickListener(new View.OnClickListener()

            {
                @Override
                public void onClick (View view){
                    Intent intent = new Intent(MainActivity.this, DersProgrami.class);
                    startActivity(intent);
            }
            });
       sinavprogrami.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(MainActivity.this,SinavProgrami.class);
               startActivity(intent);
           }
       });

        }
        @Override
        public boolean onNavigationItemSelected (@NonNull MenuItem item){
            switch (item.getItemId()) {
                case R.id.anasayfa:
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    break;
                case R.id.adayogrenci:
                    Intent intent1 = new Intent(MainActivity.this, AdayOgrenci.class);
                    startActivity(intent1);
                    break;
                case R.id.lisans:
                    Intent intent2 = new Intent(MainActivity.this, Lisans.class);
                    startActivity(intent2);
                    break;
                case R.id.lisansustu:
                    Intent intent3 = new Intent(MainActivity.this, Lisansustu.class);
                    startActivity(intent3);
                    break;
                case R.id.arastirma:
                    Intent intent4 = new Intent(MainActivity.this, Arastirma.class);
                    startActivity(intent4);
                    break;
                case R.id.personel:
                    Intent intent5 = new Intent(MainActivity.this, Personel.class);
                    startActivity(intent5);
                    break;
                case R.id.degisimprogramlari:
                    Intent intent6 = new Intent(MainActivity.this, DegisimProgramlari.class);
                    startActivity(intent6);
                    break;
                case R.id.akreditasyon:
                    Intent intent7 = new Intent(MainActivity.this, Akreditasyon.class);
                    startActivity(intent7);
                    break;
                case R.id.hakkimizda:
                    Intent intent8 = new Intent(MainActivity.this, Hakkimizda.class);
                    startActivity(intent8);
                    break;
                case R.id.iletisim:
                    Intent intent9 = new Intent(MainActivity.this, WebViews.class);
                    intent9.putExtra("url", getResources().getString(R.string.iletişim_url));
                    intent9.putExtra("toolbar", getResources().getString(R.string.iletişim));
                    startActivity(intent9);
                    break;
                case R.id.linkedin:
                    Intent intent10 = new Intent(MainActivity.this, WebViews.class);
                    intent10.putExtra("url", getResources().getString(R.string.linkedin_url));
                    intent10.putExtra("toolbar", "Linkedin");
                    startActivity(intent10);
                    break;
                case R.id.facebook:
                    Intent intent11 = new Intent(MainActivity.this, WebViews.class);
                    intent11.putExtra("url", getResources().getString(R.string.facebook_url));
                    intent11.putExtra("toolbar", "Facebook");
                    startActivity(intent11);
                    break;
                case R.id.twitter:
                    Intent intent12 = new Intent(MainActivity.this, WebViews.class);
                    intent12.putExtra("url", getResources().getString(R.string.twitter_url));
                    intent12.putExtra("toolbar", "Twitter");
                    startActivity(intent12);
                    break;
                case R.id.instagram:
                    Intent intent13 = new Intent(MainActivity.this, WebViews.class);
                    intent13.putExtra("url", getResources().getString(R.string.instagram_url));
                    intent13.putExtra("toolbar", "Instagram");
                    startActivity(intent13);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + item.getItemId());
            }
            return true;
        }

        //toolbar dil menüsü
        @Override
        public boolean onCreateOptionsMenu (Menu menu){

            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.dil_menu, menu);
            return true;
        }
        //dil seçeneği
    private void setAppLocale(String localeCode) {
        Resources resources = getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        Configuration config = resources.getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            config.setLocale(new Locale(localeCode.toLowerCase()));
        } else {
            config.locale = new Locale(localeCode.toLowerCase());
        }
        resources.updateConfiguration(config, dm);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.dilturkce:
                Intent intentTr = new Intent(this, MainActivity.class);
                setAppLocale("tr");
                startActivity(intentTr);
                break;
            case R.id.dilingilizce:
                Intent intentEn = new Intent(this, MainActivity.class);
                setAppLocale("en");
                startActivity(intentEn);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    }





