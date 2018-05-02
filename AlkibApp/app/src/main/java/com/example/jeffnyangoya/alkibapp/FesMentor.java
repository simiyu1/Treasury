package com.example.jeffnyangoya.alkibapp;

import android.provider.Browser;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class FesMentor extends ActionBarActivity {


    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fes_mentor);
        webView = (WebView) findViewById(R.id.FesMentorsW);
        webView.setWebViewClient(new Browser());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://192.168.172.1/info");
    }

    private class Browser extends WebViewClient {

        public boolean shouldOverideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

}
