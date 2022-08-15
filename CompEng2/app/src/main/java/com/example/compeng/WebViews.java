package com.example.compeng;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class WebViews extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        Toolbar toolbar;
        String tool=getIntent().getExtras().getString("toolbar");

        toolbar = findViewById(R.id.submenu);
        toolbar.setTitle(tool);
        setSupportActionBar(toolbar);
        progressBar=findViewById(R.id.progress_circular);
        webView=findViewById(R.id.webView);

        webView.clearCache(true);
        webView.clearFormData();
        webView.clearHistory();
        webView.clearSslPreferences();


        webView.setWebChromeClient(new WebChromeClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setSupportMultipleWindows(true);


        if (getIntent().getExtras().getString("url").contains(".pdf")) {
            webView.loadUrl("https://drive.google.com/viewerng/viewer?embedded=true&url=" + getIntent().getExtras().getString("url"));
        }
        else {
            webView.loadUrl(getIntent().getExtras().getString("url"));

        }
        webView.setWebViewClient(webViewClient);
    }

WebViewClient webViewClient=new WebViewClient(){
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon){
            super.onPageStarted(view,url,favicon);
            progressBar.setVisibility(View.VISIBLE);
            Toast.makeText(WebViews.this, getResources().getString(R.string.yükleniyor), Toast.LENGTH_LONG).show();
        }
        @Override
        public void onPageFinished(WebView view,String url){
            super.onPageFinished(view,url);
            progressBar.setVisibility(View.GONE);
        }

};

//geri butonu
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}