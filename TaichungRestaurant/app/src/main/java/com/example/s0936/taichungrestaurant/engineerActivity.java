package com.example.s0936.taichungrestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.View;
import com.squareup.picasso.Picasso;
import android.content.Intent;
import android.net.Uri;

public class engineerActivity extends AppCompatActivity {
        TextView textView,textView2;
        ImageView mtphoto;

        String i2;
       @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_engineer);


            textView =findViewById(R.id.text20);//我的話
           mtphoto=findViewById(R.id.Myphoto);
           String mytack = getIntent().getStringExtra("cityname123");//我的話
           String myphoto=getIntent().getStringExtra("Restaurname");

           Log.e("SSCHANG","cityname3="+mytack);

          i2=myphoto;


        textView.setText(mytack);//我的話

           Picasso.get().load(i2).into(mtphoto);


    }




}
