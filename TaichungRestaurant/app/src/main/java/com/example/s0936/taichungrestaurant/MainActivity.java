package com.example.s0936.taichungrestaurant;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Base64;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {
    RecyclerView Taichungclub;
    FirebaseDatabase taichungdata;
    DatabaseReference mtaichung;
    Button vv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Taichungclub = findViewById(R.id.Taichungclub);
        Taichungclub.setHasFixedSize(true);
        Taichungclub.setLayoutManager(new LinearLayoutManager(this));

        taichungdata = FirebaseDatabase.getInstance();
        mtaichung = taichungdata.getReference("Taichungcity");//firebase的資料名稱
        PrintHashKey();
    }

    private void firebaseSearch(String searchText) {//查詢
        Query firebaseSearchQuery = mtaichung.orderByChild("title")//在database搜尋title的文字
                .startAt(searchText).endAt(searchText + "\uf0ff");//search Text 尋找文本

        FirebaseRecyclerAdapter<Taichungmodel, TaichungHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Taichungmodel, TaichungHolder>(
                        Taichungmodel.class,//去model找資料
                        R.layout.taichung_city_ui,//設計
                        TaichungHolder.class,//
                        firebaseSearchQuery
                ) {
                    @Override
                    protected void populateViewHolder(TaichungHolder viewHolder,
                                                      Taichungmodel model,
                                                      int position) {
                        viewHolder.setThdata(//git firsebase
                                getApplicationContext(),
                                model.getTitle(),
                                model.getThimage(),
                                model.getPhone(),
                                model.getWeb(),
                                model.getMap(),
                                model.getCityname(),
                                model.getEngineertext(),
                                model.getEngcity(),
                                model.getMytack());

                    }


                    public TaichungHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        TaichungHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new TaichungHolder.ClickListener() {

                            @Override
                            public void onItenClick(View view, int position) {

                                if (position == 0) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//北區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();
                                    intent.putExtra("cityname", cityname);//傳送抬頭名稱
                                    intent.putExtra("Restaurname", cityTitle);//firebase資料夾名稱
                                    startActivity(intent);
                                }

                                if (position == 1) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//中區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);//Centralarea
                                }
                                if (position == 2) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//南區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 3) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//西區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                //西區之後Activity都是tichng_rsturnt_city_ui
                                if (position == 4) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//東區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 5) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//西屯區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 6) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//南屯
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 7) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//北屯區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 8) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//太平區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 9) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//大里
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 10) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//霧峰
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 11) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//烏日
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 12) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//豐原
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 13) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//后里
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 14) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//神岡
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 15) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//東勢
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 16) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//新社
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 17) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//潭子
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 18) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//大雅
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 19) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//大肚                                    startActivity(intent);
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 20) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//沙鹿                                    startActivity(intent);
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 21) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//龍井                                    startActivity(intent);
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);

                                    startActivity(intent);
                                }

                                if (position == 22) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//龍井                                    startActivity(intent);
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);

                                    String cityTitle = mTitleTv.getText().toString();


                                    intent.putExtra("Restaurname", cityTitle);

                                    startActivity(intent);
                                }


                            }


                        });
                        return viewHolder;
                    }
/*
  Intent intent = new Intent(MainActivity.this, engineerActivity.class);//作者                                    startActivity(intent);

                                    //TextView mTitleTv = view.findViewById(R.id.cityRestaurant);//寫URL
                                    TextView mtitle = view.findViewById(R.id.engineertalk);//寫我的話

                                    String cityname = mtitle.getText().toString();
                                    // String cityTitle = mTitleTv.getText().toString();


                                    intent.putExtra("cityname", cityname);
                                    // intent.putExtra("Restaurname", cityTitle);

                                    startActivity(intent);//這裡按下正常喔作者北
*/
                };
        Taichungclub.setAdapter(firebaseRecyclerAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Taichungmodel, TaichungHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Taichungmodel, TaichungHolder>(
                        Taichungmodel.class,
                        R.layout.taichung_city_ui,
                        TaichungHolder.class,
                        mtaichung

                ) {
                    @Override
                    protected void populateViewHolder(TaichungHolder
                                                              viewHolder, Taichungmodel model,
                                                      int position) {
                        viewHolder.setThdata(
                                getApplicationContext(),
                                model.getTitle(),
                                model.getThimage(),
                                model.getPhone(),
                                model.getWeb(),
                                model.getMap(),
                                model.getCityname(),
                                model.getEngineertext(),
                                model.getEngcity(),
                                model.getMytack());

                    }

                    public TaichungHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                        TaichungHolder viewHolder = super.onCreateViewHolder(parent, viewType);
                        viewHolder.setOnClickListener(new TaichungHolder.ClickListener() {

                            @Override
                            public void onItenClick(View view, int position) {

                                if (position == 0) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//北區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();
                                    intent.putExtra("cityname", cityname);//傳送抬頭名稱
                                    intent.putExtra("Restaurname", cityTitle);//firebase資料夾名稱

                                    startActivity(intent);
                                }

                                if (position == 1) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//中區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);//Centralarea
                                }
                                if (position == 2) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//南區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 3) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//西區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                //西區之後Activity都是tichng_rsturnt_city_ui
                                if (position == 4) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//東區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 5) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//西屯區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 6) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//南屯
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 7) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//北屯區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 8) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//太平區
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 9) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//大里
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 10) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//霧峰
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 11) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//烏日
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 12) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//豐原
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 13) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//后里
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 14) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//神岡
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 15) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//東勢
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 16) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//新社
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 17) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//潭子
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 18) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//大雅
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 19) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//大肚                                    startActivity(intent);
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 20) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//沙鹿                                    startActivity(intent);
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);
                                    startActivity(intent);
                                }
                                if (position == 21) {
                                    Intent intent = new Intent(MainActivity.this, Centralarea.class);//龍井                                    startActivity(intent);
                                    TextView mTitleTv = view.findViewById(R.id.cityRestaurant);
                                    TextView mtitle = view.findViewById(R.id.cityname);

                                    String cityname = mtitle.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    intent.putExtra("cityname", cityname);
                                    intent.putExtra("Restaurname", cityTitle);

                                    startActivity(intent);
                                }
                                if (position == 22) {
                                    Intent intent = new Intent(MainActivity.this, engineerActivity.class);//作者                                    startActivity(intent);

                                    TextView mTitleTv = view.findViewById(R.id.engtack);//寫URL
                                    TextView mtitlee = view.findViewById(R.id.engineertalk);//寫我的話

                                    String c = mtitlee.getText().toString();
                                    String cityTitle = mTitleTv.getText().toString();

                                    Log.d("SSCHANG", "cityyname is :" + c);

                                    intent.putExtra("cityname123", c);
                                    intent.putExtra("Restaurname", cityTitle);

                                    Log.e("SSCHANG", "cityname99=" + c);

                                    startActivity(intent);//這裡按下正常喔
                                }


                            }

                        });//作者
                        return viewHolder;
                    }


                };
        Taichungclub.setAdapter(firebaseRecyclerAdapter);
    }

    public static class TaichungHolder extends RecyclerView.ViewHolder {
        View Thview;

        public TaichungHolder(View Thitem) {
            super(Thitem);
            Thview = Thitem;

            Thitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ThClickListener.onItenClick(view, getAdapterPosition());

                }
            });

        }

        public void setThdata(Context ctx,
                              String title,
                              String image,
                              String phone,
                              String Web,
                              String Map,
                              String cityname,
                              String engineer,
                              String engcity,
                              String mytackha) {

            TextView Thtitle = Thview.findViewById(R.id.cityname);
            ImageView TaichugImage = Thview.findViewById(R.id.cityphoto);
            TextView Cityfirebase = Thview.findViewById(R.id.cityRestaurant);
            TextView Engineertext = Thview.findViewById(R.id.engineertalk);
            TextView Engcityy = Thview.findViewById(R.id.engname);
            TextView MMyack = Thview.findViewById(R.id.engtack);

            Cityfirebase.setText(cityname);
            Thtitle.setText(title);
            Engineertext.setText(engineer);
            Engcityy.setText(engcity);
            MMyack.setText(mytackha);

            Picasso.get().load(image).into(TaichugImage);
        }

        private TaichungHolder.ClickListener ThClickListener;

        public interface ClickListener {
            void onItenClick(View view, int position);

        }

        public void setOnClickListener
                (TaichungHolder.ClickListener clickListener) {
            ThClickListener = clickListener;
        }


    }

    public boolean onCreateOptionsMenu(Menu menu) {//查詢
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                firebaseSearch(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                firebaseSearch(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub

        if (keyCode == KeyEvent.KEYCODE_BACK) { // 攔截返回鍵
            new AlertDialog.Builder(MainActivity.this)
                    .setTitle("確認視窗")
                    .setMessage("確定要結束應用程式嗎?")
                    .setIcon(R.drawable.zvmta)
                    .setPositiveButton("確定",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    finish();
                                }
                            })
                    .setNegativeButton("取消",
                            new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog,
                                                    int which) {
                                    // TODO Auto-generated method stub

                                }
                            }).show();
        }
        return true;
    }

    private void PrintHashKey() {

        try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.example.user.facebook_long_in",
                    PackageManager.GET_SIGNATURES);
            for (android.content.pm.Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }


}
