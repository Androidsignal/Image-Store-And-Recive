package com.example.akshar.finaldatabse;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Akshar on 9/4/2017.
 */

public class MyAdapter extends BaseAdapter {
    ArrayList<BeanClass>arrayList;
    Context context;

    public MyAdapter(Context context, ArrayList<BeanClass> arrayList) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }
/*
    public static Bitmap decodeBase64(String input) {
        byte[] decodedBytes = Base64.decode(input, 0);
        return BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);
    }*/

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view=layoutInflater.inflate(R.layout.my_layout,null);


        TextView txtid=(TextView)view.findViewById(R.id.txtid);
        TextView txtname=(TextView)view.findViewById(R.id.txtname);
        TextView txtnumber=(TextView)view.findViewById(R.id.txtnumber);
        ImageView imageView=(ImageView)view.findViewById(R.id.txtphoto);
        Bitmap bitmapImage;

        BeanClass beanClass=arrayList.get(i);
        txtid.setText("id :"+beanClass.getId());
        txtname.setText("name :"+beanClass.getName());
        txtnumber.setText("number :"+beanClass.getNumber());

        Log.i("Test", "getView: "+beanClass.toString());
        if (beanClass.getImage() != null){
            bitmapImage= InsertData.decodeBase64(beanClass.getImage());
            imageView.setImageBitmap(bitmapImage);
        }
        return view;
    }
}
