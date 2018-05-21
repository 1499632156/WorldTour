package cn.mepstudio.worldtour;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import cn.mepstudio.worldtour.bikenavi.BNaviMainActivity;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private ViewPagerAdapter mAdapter;

    private Button map_click,ar_click,panorama_click,navi_click;

    //定义一个队列来保存各个布局文件
    private ArrayList<View> views;

    private MenuItem menuItem;
    private BottomNavigationView bottomNavigationView;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        //注意该方法要再setContentView方法之前实现
     //   SDKInitializer.initialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);

        //队列来保存各个布局文件
        views=new ArrayList<View>();

        //获取界面的布局加载器，将布局文件加载到程序中
        LayoutInflater li=getLayoutInflater();
        //将布局加载到程序中

        View viewPager_home = li.inflate(R.layout.activity_main_home, null,false);
        View viewPager_news = li.inflate(R.layout.activity_main_news, null,false);
        View viewPager_local =li.inflate(R.layout.activity_main_local, null,false);
        View viewPager_find = li.inflate(R.layout.activity_main_find, null,false);
        View viewPager_mine = li.inflate(R.layout.activity_main_mine, null,false);


        views.add(viewPager_home);
        views.add(viewPager_news);
        views.add(viewPager_local);
        views.add(viewPager_find);
        views.add(viewPager_mine);

        mAdapter = new ViewPagerAdapter(views);
        viewPager.setAdapter(mAdapter);

        WebView web_home=(WebView) viewPager_home.findViewById(R.id.home_web);
        WebView web_news = (WebView) viewPager_news.findViewById(R.id.news_web);
        WebView web_find = (WebView) viewPager_find.findViewById(R.id.find_web);
        WebSettings webSettings_home = web_home.getSettings();
        WebSettings webSettings_find = web_find.getSettings();
        WebSettings webSettings_news = web_news.getSettings();


        web_home.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Handle the error
                if(errorCode==-2) {
                    view.loadUrl("file:///android_asset/time_out.html");
                }
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        web_news.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Handle the error
                if(errorCode==-2) {
                    view.loadUrl("file:///android_asset/time_out.html");
                }
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        web_find.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url)
            { //  重写此方法表明点击网页里面的链接还是在当前的webview里跳转，不跳到浏览器那边
                view.loadUrl(url);
                return true;
            }
            @SuppressWarnings("deprecation")
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                // Handle the error
                if(errorCode==-2) {
                    view.loadUrl("file:///android_asset/time_out.html");
                }
            }

            @TargetApi(android.os.Build.VERSION_CODES.M)
            @Override
            public void onReceivedError(WebView view, WebResourceRequest req, WebResourceError rerr) {
                onReceivedError(view, rerr.getErrorCode(), rerr.getDescription().toString(), req.getUrl().toString());
            }
        });

        webSettings_home.setJavaScriptEnabled(true);//设置支持javascript
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            webSettings_find.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webSettings_home.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
            webSettings_news.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }

        web_home.loadUrl("https://lvyou.baidu.com/#main");
        web_news.loadUrl("https://m.baidu.com/from=1000953f/bd_page_type=1/ssid=2774c3fcd4cbc3bbd3d0b5bcd1ddf52e/uid=0/pu=usm%402%2Csz%40320_1002%2Cta%40iphone_2_6.0_2_12137.1/baiduid=973EEF52D3DEC4DFD024270ADE701750/w=0_10_/t=iphone/l=3/tc?ref=www_iphone&lid=7382171454823398260&order=1&fm=alop&tj=www_normal_1_0_10_title&vit=osres&m=8&srd=1&cltj=cloud_title&asres=1&title=%E6%97%85%E6%B8%B8%E5%8A%A8%E6%80%81%E8%B5%84%E8%AE%AF%E5%88%97%E8%A1%A8%2C%E8%9C%82%E7%AA%9D%E8%B5%84%E8%AE%AF-%E9%A9%AC%E8%9C%82%E7%AA%9D&dict=32&wd=&eqid=6672bdd363c6fc00100000025abb4f73&w_qd=IlPT2AEptyoA_ykzquMb5vixJUtVkoC&tcplug=1&sec=28640&di=9c9c2610d7a61bf0&bdenc=1&tch=124.0.283.182.0.0&nsrc=IlPT2AEptyoA_yixCFOxXnANedT62v3IEQGG_yFZ2Du5mlasxP4lZQRAViHuRnqKXkfws8H0sqcFrXS7_8km6so4g43&clk_info=%7B%22srcid%22%3A1599%2C%22tplname%22%3A%22www_normal%22%2C%22t%22%3A1522225014944%2C%22xpath%22%3A%22div-a-h3%22%7D");
        web_find.loadUrl("https://lvyou.baidu.com/zhongguo/jingdian");


        map_click=(Button) viewPager_local.findViewById(R.id.map_click);
        map_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, MapActivity.class);
                startActivity(intent);
            }
        });

        ar_click=(Button) viewPager_local.findViewById(R.id.ar_click);
        ar_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, ARActivity.class);
                startActivity(intent);
            }
        });

        panorama_click=(Button) viewPager_local.findViewById(R.id.panorama_click);
        panorama_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, PanoramaActivity.class);
                startActivity(intent);
            }
        });

        navi_click=(Button) viewPager_local.findViewById(R.id.navi_click);
        navi_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(MainActivity.this, BNaviMainActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        //默认 >3 的选中效果会影响ViewPager的滑动切换时的效果，故利用反射去掉
        BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.item_home:
                                viewPager.setCurrentItem(0);
                                break;
                            case R.id.item_news:
                                viewPager.setCurrentItem(1);
                                break;
                            case R.id.item_local:
                                viewPager.setCurrentItem(2);
                                break;
                            case R.id.item_find:
                                viewPager.setCurrentItem(3);
                                break;
                            case R.id.item_mine:
                                viewPager.setCurrentItem(4);
                                break;
                        }
                        return false;
                    }
                });


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            //    menuItem = bottomNavigationView.getMenu().getItem(2).setChecked(true);
            }
            @Override
            public void onPageSelected(int position) {
                if (menuItem != null) {
                    menuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                menuItem = bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //禁止ViewPager滑动
//        viewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });

      //  setupViewPager(viewPager);
    }

    // 用来计算返回键的点击间隔时间
    private long exitTime = 0;
    //双击返回退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 1500) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

/*
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(BaseFragment.newInstance("新闻"));
        adapter.addFragment(BaseFragment.newInstance("图书"));
        adapter.addFragment(BaseFragment.newInstance("发现"));
        adapter.addFragment(BaseFragment.newInstance("更多"));
        viewPager.setAdapter(adapter);
    }
*/
}