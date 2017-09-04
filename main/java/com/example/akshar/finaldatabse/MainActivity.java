package com.example.akshar.finaldatabse;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ListView listView;
    ArrayList<BeanClass> arrayList;
    SQLiteHelper sqLiteHelper;
    MyAdapter myAdapter;
    TextView noItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        noItems=(TextView) findViewById(R.id.txtNoItem);
        listView = (ListView) findViewById(R.id.MyList);
        sqLiteHelper = new SQLiteHelper(MainActivity.this);
        arrayList = sqLiteHelper.displayData();
        myAdapter = new MyAdapter(MainActivity.this, arrayList);
        listView.setAdapter(myAdapter);
        registerForContextMenu(listView);
        if (arrayList.size() == 0 ){
            noItems.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("insert");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals("insert")) {
            startActivity(new Intent(MainActivity.this, InsertData.class));
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add("update");
        menu.add("delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int position = adapterContextMenuInfo.position;
        final BeanClass beanClass = arrayList.get(position);
        if (item.getTitle().equals("update")) {
            Intent intent = new Intent(MainActivity.this, UpdateData.class);
            intent.putExtra("imagearray", arrayList.get(position));
            //  Log.i("", "onContextItemSelected: ");
            startActivity(intent);
            finish();
        } else if (item.getTitle().equals("delete")) {

            AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
            alert.setTitle("Delete?");
            alert.setMessage("Are u sure want to delete?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    sqLiteHelper.deletedata(beanClass.getId());
                    arrayList.remove(position);
                    myAdapter.notifyDataSetChanged();
                    Toast.makeText(MainActivity.this, "Data Delete Success...", Toast.LENGTH_SHORT).show();
                }
            });
            alert.setNegativeButton("No", null);
            alert.show();
        }

        return super.onContextItemSelected(item);
    }
}
