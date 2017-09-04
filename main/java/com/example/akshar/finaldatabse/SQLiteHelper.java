package com.example.akshar.finaldatabse;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

/**
 * Created by Akshar on 9/4/2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    public String tbname="Raviname",id="id",name="name",number="number",image="image";
    SQLiteDatabase sqLiteDatabase;
    public SQLiteHelper(Context context){
        super(context, "Vithani", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    String sql=" create table "+tbname+" (" +id+ " integer primary key autoincrement , " +name+ " text,"+number+" text,"+image+" text)";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertdata(BeanClass beanClass){
        sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name,beanClass.getName());
        contentValues.put(number,beanClass.getNumber());
        contentValues.put(image,beanClass.getImage());
        sqLiteDatabase.insert(tbname,null,contentValues);
        sqLiteDatabase.close();

    }
    public void updatedata(BeanClass beanClass){
        sqLiteDatabase=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(name,beanClass.getName());
        contentValues.put(number,beanClass.getNumber());
        contentValues.put(image,beanClass.getImage());
        sqLiteDatabase.update(tbname,contentValues,id+"="+beanClass.getId(),null);
        sqLiteDatabase.close();

    }
    public void deletedata(String id){
        sqLiteDatabase=getWritableDatabase();
        String SQL="DELETE FROM "+tbname+" WHERE "+id+" = "+id;
        sqLiteDatabase.execSQL(SQL);
        sqLiteDatabase.close();
    }
    public ArrayList<BeanClass> displayData() {
        ArrayList<BeanClass> arrayList = new ArrayList<>();
        sqLiteDatabase = getReadableDatabase();
        String sql = " select * from "+tbname;
        Cursor cursor = sqLiteDatabase.rawQuery(sql, null);
        Log.d(TAG, "displayData: "+cursor.toString());
        while (cursor.moveToNext()) {
            BeanClass beanClass = new BeanClass();
            beanClass.setId(cursor.getInt(0)+"");
            beanClass.setName(cursor.getString(1));
            beanClass.setNumber(cursor.getString(2));
            beanClass.setImage(cursor.getString(3));
            arrayList.add(beanClass);
        }
        sqLiteDatabase.close();
        return arrayList;
    }
}
