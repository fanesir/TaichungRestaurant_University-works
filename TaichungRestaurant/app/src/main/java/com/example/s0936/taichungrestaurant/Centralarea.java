package com.example.s0936.taichungrestaurant;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;

public class Centralarea extends AppCompatActivity {
    RecyclerView CentralRecy;
    FirebaseDatabase CentraFirebase;
    DatabaseReference CentraDatabase;
    String Firebasecity,FirebaseTitle;
    ImageView Cityimage;
    @Override//中區
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_centralarea);

        CentralRecy = findViewById(R.id.CentralRecy);
        CentralRecy.setHasFixedSize(true);
        CentralRecy.setLayoutManager(new LinearLayoutManager(this));



        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String name=bundle.getString("Restaurname");
        String cityname=bundle.getString("cityname");

        /*if (name.startsWith("http")) {
            name = "taichung";
        }*/

        Firebasecity=name;
        FirebaseTitle=cityname;//北區

        // Initialize with secondary app.

        Log.e("SSCHANG","Firebaseciry="+Firebasecity);


        CentraFirebase = FirebaseDatabase.getInstance();
        CentraDatabase = CentraFirebase.getReference(Firebasecity);
        //CentraDatabase = CentraFirebase.getReference();

        ActionBar actionBar = getSupportActionBar();//抬頭
        actionBar.setTitle(cityname);//設定藍色抬頭西


    }
    protected void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Taichungmodel, CentraHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Taichungmodel, CentraHolder>(
                        Taichungmodel.class,
                        R.layout.central_city_ui,//這裡放置設計的layout
                        CentraHolder.class,
                        CentraDatabase

                ) {

                    protected void populateViewHolder
                            (CentraHolder viewHolder, Taichungmodel model,
                             int position) {
                        viewHolder.setThdata(
                                getApplicationContext(),
                                model.getTitle(),
                                model.getThimage(),
                                model.getPhone(),
                                model.getWeb(),
                                model.getMap(),
                                model.getImageURL(),
                                model.getImagetext());

                    }

                    public CentraHolder onCreateViewHolder(ViewGroup prent, int viewtype) {
                        CentraHolder viewHolder = super.onCreateViewHolder(prent, viewtype);
                        viewHolder.setOnClickListener(new CentraHolder.ClickListener() {


                            @Override
                            public void onItenClick(View view, int position) {
                                Intent intent = new Intent(view.getContext(), Restaurantview.class);
                                //ImageView mImageview = view.findViewById(R.id.Centraphoto);
                                TextView mtitle=view.findViewById(R.id.Centraname);
                                TextView mphone = view.findViewById(R.id.Centraphone);
                                TextView mmap   = view.findViewById(R.id.Centramap);
                                TextView mweb   = view.findViewById(R.id.Centraweb);
                                TextView mimURL = view.findViewById(R.id.CentrURL);
                                TextView mImagetext = view.findViewById(R.id.Imagetext);

                                String Mtitle = mtitle.getText().toString();
                                String Mphone = mphone.getText().toString();
                                String Mmap   = mmap.getText().toString();
                                String Mweb   = mweb.getText().toString();
                                String MimURL = mimURL.getText().toString();
                                String MImagetext = mImagetext.getText().toString();

                                //Drawable mDrawable = mImageview.getDrawable();
                               // Bitmap mBitmap=((BitmapDrawable) mDrawable).getBitmap();
                                //ByteArrayOutputStream stream = new ByteArrayOutputStream();
                                //mBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                                //byte[] bytes = stream.toByteArray();

                                //intent.putExtra("image",bytes);
                                intent.putExtra("Resname",Mtitle);
                                intent.putExtra("Myimagetext",MImagetext);
                                intent.putExtra("phone",Mphone);
                                intent.putExtra("map",Mmap);
                                intent.putExtra("web",Mweb);
                                intent.putExtra("url",MimURL);
                                startActivity(intent);
                            }
                        });

                        return viewHolder;
                    }


                };
        CentralRecy.setAdapter(firebaseRecyclerAdapter);
    }

    public static class CentraHolder extends RecyclerView.ViewHolder {
        View Thview;

        public CentraHolder(View Thitem) {
            super(Thitem);
            Thview = Thitem;

            Thitem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CentraClickListener.onItenClick(view, getAdapterPosition());

                }
            });

        }

        public void setThdata(Context ctx,
                              String title,
                              String image,
                              String phone,
                              String Web,
                              String Map,
                              String imageURL,
                              String imagetext) {
            TextView Centratitle = Thview.findViewById(R.id.Centraname);
            ImageView CentraImage = Thview.findViewById(R.id.Centraphoto);
            TextView  Centraphone = Thview.findViewById(R.id.Centraphone);
            TextView  Centraweb = Thview.findViewById(R.id.Centraweb);
            TextView  Centramap = Thview.findViewById(R.id.Centramap);
            TextView CentraURL = Thview.findViewById(R.id.CentrURL);
            TextView CentImagetext = Thview.findViewById(R.id.Imagetext);
            Centratitle.setText(title);
            Centraphone.setText(phone);
            Centraweb.setText(Web);
            Centramap.setText(Map);
            CentraURL.setText(imageURL);
            CentImagetext.setText(imagetext);
            Picasso.get().load(image).into(CentraImage);
        }

        private CentraHolder.ClickListener CentraClickListener;

        public interface ClickListener {
            void onItenClick(View view, int position);

        }

        public void setOnClickListener
                (CentraHolder.ClickListener clickListener) {
            CentraClickListener = clickListener;
        }


    }















}
