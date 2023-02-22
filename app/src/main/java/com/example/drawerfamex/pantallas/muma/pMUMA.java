package com.example.drawerfamex.pantallas.muma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.drawerfamex.R;
import com.example.drawerfamex.TabAdapter3;
import com.google.android.material.tabs.TabLayout;

public class pMUMA extends AppCompatActivity {

    private ImageView fab,fabClose;
    private boolean open = false;
    private Dialog myDialog;
    private LayoutInflater layoutInflater;
    private View popupView;
    private FrameLayout pop;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private TabAdapter3 tabAdapter;
    private int width1;
    private int height1;
    private int SCREEN_HEIGHT;
    private int SCREEN_WIDTH;
    private Rect rect;

    private int nFragmentsMUMA;
    private LinearLayout llymuma;
    private TextView[] dots;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        DisplayMetrics dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        SCREEN_WIDTH = dm.widthPixels;
        SCREEN_HEIGHT = dm.widthPixels;
        setContentView(R.layout.aam_13_muma);
        nFragmentsMUMA = 4;

        //Assign Variable
        fab = findViewById(R.id.fabmumaopen);
        fabClose = findViewById(R.id.fabmumaclose);
        viewPager = findViewById(R.id.vprpop1);
        pop = findViewById(R.id.pppmuma);
        llymuma = findViewById(R.id.llymuma);

        addDots(0);
        loadViewPager();

        View backBTN = findViewById(R.id.hori_backBTN);

        backBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        //Move button
        int modificarX = 10;
        int modificarY = 10;

        WebView wbv = (WebView) findViewById(R.id.wbvMuma);
        String url = "https://webobook.com/public/62e35877f7f4f4058c1e32f2,en#";

        //webView.setWebViewClient(new MyWebViewClient());
        //WebSettings settings = webView.getSettings();
        //settings.setJavaScriptEnabled(true);

        wbv.setWebViewClient(new MyWebViewClient());
        WebSettings settings = wbv.getSettings();
        settings.setJavaScriptEnabled(true);
        wbv.loadUrl(url);

        final long[] prev = {0};
        final long[] current = {0};
        final long[] dif = {0};
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop.setVisibility(View.VISIBLE);
                fab.setVisibility(View.INVISIBLE);
                fabClose.setVisibility(View.VISIBLE);

            }
        });


        fabClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fab.setVisibility(View.VISIBLE);
                fabClose.setVisibility(View.INVISIBLE);
                pop.setVisibility(View.INVISIBLE);

            }
        });

    }

    public class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    public class vista extends View{
        public vista(Context context) {
            super(context);
            Display display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
            Point size = new Point();
            display.getSize(size);
            width1 = size.x;
            height1 = size.y;
            rect = new Rect(0,0,width1,height1);
        }

        protected void  onDraw(Canvas canvas){
            super.onDraw(canvas);
            canvas.drawBitmap(null,null,rect,null);
        }
    }



    //Carga / Añade fragments
    private void loadViewPager(){
        tabAdapter = new TabAdapter3(getSupportFragmentManager());
        tabAdapter.addFragments(new fragMUMA1(),"");
        tabAdapter.addFragments(new fragMUMA2(),"");
        tabAdapter.addFragments(new fragMUMA3(),"");
        tabAdapter.addFragments(new fragMUMA4(),"");
        viewPager.setAdapter(tabAdapter);
        viewPager.setOnPageChangeListener(pagerListener);
    }



    //Añade botones
    private void addDots(int currentPage){
        dots = new TextView[nFragmentsMUMA];
        llymuma.removeAllViews();
        for(int i = 0;i<dots.length;i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(40);
            if(i == currentPage){
                dots[i].setTextColor(Color.WHITE);
            } else{
                dots[i].setTextColor(Color.LTGRAY);
            }
            llymuma.addView(dots[i]);
        }
    }


    ViewPager.OnPageChangeListener pagerListener = new ViewPager.OnPageChangeListener(){

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            addDots(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

}