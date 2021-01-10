package com.example.s0936.taichungrestaurant;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.pm.PackageManager;
import com.squareup.picasso.Picasso;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class Restaurantview extends AppCompatActivity {
TextView textView;
ImageView mImageView;
String i,phone,map,web;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurantview);

        mImageView=findViewById(R.id.restauranyphoto);

        textView =findViewById(R.id.textView);

        byte[] bytes = getIntent().getByteArrayExtra("image");
        String Title=getIntent().getStringExtra("Resname");
        String Phone = getIntent().getStringExtra("phone");
        String Map = getIntent().getStringExtra("map");
        String Web = getIntent().getStringExtra("web");
        String URL = getIntent().getStringExtra("url");
        String IMAgetext = getIntent().getStringExtra("Myimagetext");

         ActionBar actionBar = getSupportActionBar();//抬頭
            actionBar.setTitle(Title);

        i=URL;
        phone=Phone;
        map =Map;
        web = Web;

        Picasso.get().load(i).into(mImageView);
        textView.setText(IMAgetext);
        requestPermission();

}
    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= 23) {  // Androis 6.0 以上
            // 判斷是否已取得授權
            int hasPermission = ActivityCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE);
            if (hasPermission != PackageManager.PERMISSION_GRANTED) {  // 未取得授權
                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE}, 1);
            }
        }
        // 如果裝置版本是 Androis 6.0 以下，
        // 或是裝置版本是6.0（包含）以上，使用者已經授權
        // 允許執行程式
    }

    // 使用者完成授權的選擇以後，會呼叫 onRequestPermissionsResult 方法
    //     第一個參數：請求授權代碼
    //     第二個參數：請求的授權名稱
    //     第三個參數：使用者選擇授權的結果
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {  //按 拒絕 鈕
                Toast.makeText(this, "未取得授權！", Toast.LENGTH_SHORT).show();
                finish();  //結束應用程式
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


public void phonetoush(View v){


    Uri uri = Uri.parse("tel:"+phone);

    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
    startActivity(intent);
}


public void webtoush(View v){
    Uri uri = Uri.parse(web);
    Intent intent = new Intent(Intent.ACTION_VIEW,uri);
    startActivity(intent);
}



public void maptoush(View v){
    Uri gmmIntentUri = Uri.parse("geo:"+map);
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
    mapIntent.setPackage("com.google.android.apps.maps");
    startActivity(mapIntent);
 }











}
