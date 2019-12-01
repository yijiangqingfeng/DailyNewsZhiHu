package com.example.dailynews_zhihu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import cn.bingoogolapple.bgabanner.BGABanner;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerView;//用于显示的列表
    private RequestQueue mQueue;
    private ArrayList<ItemBean.StoriesBean> mDatas;//用于储存数据
    private int otherdate = -1;//从今日算起，倒数第几天
    private MyAdapter adapter;//适配器
    public static String URLBanner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //开始初始化
        initData();
        initView();
        initBanner();
        //上拉加载
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int totalItemCount;
            private int firstVisibleItem;
            private int visibleItemCount;

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                totalItemCount = layoutManager.getItemCount();
                firstVisibleItem = layoutManager.findFirstVisibleItemPosition();
                visibleItemCount = recyclerView.getChildCount();
                if (((totalItemCount - visibleItemCount) <= firstVisibleItem)) {
                    onLoadMore();
                }
            }

            private void onLoadMore() {
                getInfoFromNet();
            }
        });
    }

    private void initView() {
        //初始化页面
        Calendar ca = Calendar.getInstance();
        int month = ca.get(Calendar.MONTH);
        //获取月份
        int day = ca.get(Calendar.DATE);
        //获取日
        int hour = ca.get(Calendar.HOUR_OF_DAY);
        String mon = String.valueOf(month);
        String d = String.valueOf(day);
        String h = String.valueOf(hour);
        setTitle0("知乎日报");//设置标题
        TextView textView1, textView2, textView3;
        textView1 = findViewById(R.id.month);
        textView1.setText(mon + "月");
        textView2 = findViewById(R.id.day);
        textView2.setText(d);
        textView3 = findViewById(R.id.tv2);
        if (hour >= 23 || hour <= 5) {
            textView3.setText("夜深了，注意休息~");
        } else if (hour >= 6 && hour <= 8) {
            textView3.setText("早上好~");
        } else if (hour >= 9 && hour <= 12) {
            textView3.setText("上午好~");
        } else if (hour >= 13 && hour <= 14) {
            textView3.setText("中午好~");
        } else if (hour >= 15 && hour <= 18) {
            textView3.setText("下午好~");
        } else if (hour >= 19 && hour <= 22) {
            textView3.setText("今晚宜读文章~");
        } else {
            textView3.setText("这个时间好像忘写了...");
        }
        recyclerView = (RecyclerView) findViewById(R.id.id_recycler);//绑定RecycleView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局管理器，你可以通过这个来决定你是要做一个Listview还是瀑布流
        adapter = new MyAdapter(mDatas, MainActivity.this);//初始化适配器
        recyclerView.setAdapter(adapter);//为RecyclerView设置适配器

    }

    private void initData() {
        //初始化数据
        mDatas = new ArrayList<>();
        getInfoFromNet();

    }

    private void getInfoFromNet() {
        String url;
        otherdate++;
        url = "http://news.at.zhihu.com/api/4/news/before/" + getDate();
        //获取网络数据
        mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {

                    JSONArray list = null;
                    try {
                        list = response.getJSONArray("stories");
                        //获取返回数据的内容

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //开始解析数据
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject item = list.getJSONObject(i);

                        JSONArray images = item.getJSONArray("images");
                        ItemBean.StoriesBean listItem = new ItemBean.StoriesBean();
                        //创建list中的每一个对象，并设置数据
                        listItem.setTitle(item.getString("title"));
                        listItem.setImages(images.getString(0));
                        listItem.setUrl(item.getString("url"));
                        listItem.setHint(item.getString("hint"));
                        mDatas.add(listItem);
                    }
                    adapter.notifyDataSetChanged();//通知适配器 刷新数据啦 啊喂
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            //如果遇到异常，在这里通知用户
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "碰到了一点问题", Toast.LENGTH_SHORT);

            }
        });
        mQueue.add(jsonObjectRequest);//开始任务
    }

    private String getDate() {
        //获取当前需要加载的数据的日期
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.DAY_OF_MONTH, -otherdate + 1);//otherdate天前的日子
        String date = new SimpleDateFormat("yyyyMMdd").format(c.getTime());
        //将日期转化为20170520这样的格式
        return date;

    }

    //TopStories滚动图片
    private ArrayList<ItemBean.TopStoriesBean> bannerList;//banner控件

    private ArrayList<String> titles;//存放banner中的标题
    private ArrayList<String> hints;
    private ArrayList<String> images;//存放banner中的图片
    private ArrayList<String> urlTopStories;
    private ArrayList<String> ids;//存放每一项的id

    private void initBanner() {
        //初始化banner
        titles = new ArrayList<>();
        ids = new ArrayList<>();
        images = new ArrayList<>();
        urlTopStories = new ArrayList<>();
        bannerList = new ArrayList<>();
        hints = new ArrayList<>();
        mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://news-at.zhihu.com/api/4/news/latest", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    //解析banner中的数据
                    JSONArray topinfos = response.getJSONArray("top_stories");
                    Log.d("TAG", "onResponse: " + topinfos);
                    for (int i = 0; i < topinfos.length(); i++) {
                        JSONObject item = topinfos.getJSONObject(i);
                        ItemBean.TopStoriesBean item1 = new ItemBean.TopStoriesBean();
                        item1.setImage(item.getString("image"));
                        item1.setTitle(item.getString("title"));
                        item1.setId(item.getString("id"));
                        item1.setUrl(item.getString("url"));
                        item1.setHint(item.getString("hint"));
                        bannerList.add(item1);
                        titles.add(item1.getTitle());
                        images.add(item1.getImage());
                        urlTopStories.add(item1.getUrl());
                        ids.add(item1.getId());
                        hints.add(item1.getHint());
                    }


                    setHeader(recyclerView, images, titles, hints);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(jsonObjectRequest);


    }

    private void setHeader(RecyclerView view, ArrayList<String> urls, final ArrayList<String> titles, ArrayList<String> hint) {
        View header = LayoutInflater.from(this).inflate(R.layout.headview, view, false);
        //找到banner所在的布局
        BGABanner banner = (BGABanner) header.findViewById(R.id.banner);
        //绑定banner
        banner.setAdapter(new BGABanner.Adapter<ImageView, String>() {

            @Override
            public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
                Glide.with(MainActivity.this)
                        .load(model)
                        .centerCrop()
                        .dontAnimate()
                        .into(itemView);
                TextView textView;
                textView = (TextView) findViewById(R.id.topText);
                //此处有一个小bug没有修，是关于图片左滑和右滑的监听问题（现在的banner图片自动右滑
                //标题正确，但是左滑的时候位置-2);
                textView.setText(bannerList.get((position - 1) == -1 ? 4 : (position - 1)).getTitle());
            }
        });
        banner.setDelegate(new BGABanner.Delegate() {
            @Override
            public void onBannerItemClick(BGABanner banner, View itemView, Object model, int position) {
                //TopStories的点击跳转
                URLBanner = bannerList.get(position).getUrl();
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, Main3Activity.class);
                MainActivity.this.startActivity(intent);

            }
        });
        banner.setData(urls, hint);
        adapter.setHeadView(header);//向适配器中添加banner
    }
}
