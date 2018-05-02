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


public class facultyfs extends ActionBarActivity implements View.OnClickListener {
    Button bfesContacts, bfesStructure, bfesMentor;
    WebView fesNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facultyfs);
        bfesContacts = (Button) findViewById(R.id.bfesContacts);
        bfesStructure = (Button) findViewById(R.id.bfesStructure);
        bfesMentor = (Button) findViewById(R.id.bfesMentor);
        fesNews = (WebView) findViewById(R.id.fesNews);

        bfesContacts.setOnClickListener(this);
        bfesStructure.setOnClickListener(this);
        bfesMentor.setOnClickListener(this);
        fesNews.setWebViewClient(new Browser());
        WebSettings webSettings = fesNews.getSettings();
        webSettings.setJavaScriptEnabled(true);
        fesNews.loadUrl("http://192.168.172.1/info");

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
            case R.id.bfesContacts:

                startActivity(new Intent(this, FesContacts.class));
                break;
            case R.id.bfesMentor:

                startActivity(new Intent(this, FesMentor.class));
                break;
            case R.id.bfesStructure:

                startActivity(new Intent(this, FesStructure.class));
                break;
        }

    }
}