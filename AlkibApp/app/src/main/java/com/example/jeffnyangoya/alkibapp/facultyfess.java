package com.example.jeffnyangoya.alkibapp;

import android.content.Intent;
import android.provider.Browser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;


public class facultyfess extends ActionBarActivity implements View.OnClickListener {
    Button bfessContacts, bfessStructure, bfessMentor;
    WebView fessNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyfess);
        bfessContacts = (Button) findViewById(R.id.bfessContacts);
        bfessStructure = (Button) findViewById(R.id.bfessStructure);
        bfessMentor = (Button) findViewById(R.id.bfessMentor);
        fessNews = (WebView) findViewById(R.id.fessNews);

        bfessContacts.setOnClickListener(this);
        bfessStructure.setOnClickListener(this);
        bfessMentor.setOnClickListener(this);
        fessNews.setWebViewClient(new Browser());
        WebSettings webSettings = fessNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        fessNews.loadUrl("http://192.168.172.1/info");

    }

    private class Browser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bfessContacts:

                startActivity(new Intent(this, fessContact.class));
                break;
            case R.id.bfessMentor:

                startActivity(new Intent(this, fessMentor.class));
                break;
            case R.id.bfessStructure:

                startActivity(new Intent(this, fessStructure.class));
                break;
        }

    }
}