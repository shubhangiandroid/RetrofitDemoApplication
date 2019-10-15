package com.spp.retrofitdemoapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class SimpleListViewActivity extends AppCompatActivity {

    ListView listView;
    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_list_view);



        listView=(ListView)findViewById(R.id.listViewProducts);


        List<String>values=new ArrayList<>();
        for(int i=0;i<10;i++){

            values.add("Product"+i);

        }

        ArrayAdapter adapter =new ArrayAdapter<String>(this,android.R.layout.activity_list_item,android.R.id.text1,values);
        listView.setAdapter(adapter);



    }
}
