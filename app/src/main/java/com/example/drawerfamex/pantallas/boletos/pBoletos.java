package com.example.drawerfamex.pantallas.boletos;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.example.drawerfamex.R;
import com.example.drawerfamex.adapters.pMenu;

public class pBoletos extends pMenu {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.aaz_24_boletos);
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.aaz_24_boletos);
        webView = (WebView) findViewById(R.id.wbvBoletos);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://www.f-airmexico.com.mx/landingboletos.html");

        /*this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.widthPixels;
        //setContentView(R.layout.aaz_24_boletos);
        nFragmentsMUMA = 4;*/

        webView.setWebViewClient(new pBoletos.MyWebViewClient());
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.loadUrl("https://www.f-airmexico.com.mx/landingboletos.html");
    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}