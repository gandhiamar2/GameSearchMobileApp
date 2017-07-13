package com.example.gandh.hw5;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import android.widget.VideoView;

/**
 * Created by gandh on 2/18/2017.
 */

public class Video extends AppCompatActivity {

    WebView webview;
    TextView head;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        head = (TextView) findViewById(R.id.header) ;
        head.setText(getIntent().getExtras().getString("key1"));
        webview = (WebView) findViewById(R.id.wv1);
        webview.setWebViewClient(new WebViewClient(){
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                view.loadUrl(request);
                return true;
            }
        });
        webview.getSettings().setLoadsImagesAutomatically(true);
        webview.getSettings().setJavaScriptEnabled(true);
       webview.loadUrl(getIntent().getExtras().getString("key"));
       // webview.loadUrl("https://www.youtube.com/watch?v=uH8Xn253XiQ");



    }
}
