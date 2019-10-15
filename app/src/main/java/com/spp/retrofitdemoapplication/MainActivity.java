package com.spp.retrofitdemoapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ListView listView;

    List<Hero> heroList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = (ListView) findViewById(R.id.listviewmain);


        //SPINNER......

       final Spinner spinner = (Spinner) findViewById(R.id.spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView,View selectedItemview,int position,long id) {
             //your code here

                String Str=spinner.getSelectedItem().toString();
                Toast.makeText(MainActivity.this,Str,Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                  //your code here
            }
        });


        //AUTOCOMPLETE........

        AutoCompleteTextView textView = (AutoCompleteTextView)
                findViewById(R.id.autoCompleteTextView);

        ArrayAdapter<CharSequence> autoadapter = ArrayAdapter.createFromResource(this,
                R.array.countries_array, android.R.layout.simple_spinner_item);

        textView.setAdapter(autoadapter);





        GetData();

    }

    private void GetData() {
        //Creating a retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()) //Here we are using the GsonConverterFactory to directly convert json data to object
                .build();
        //creating the api interface
        ApiClient api = retrofit.create(ApiClient.class);
        //now making the call object , Here we are using the api method that we created inside the api interface
        Call<List<Hero>> call = api.getHeroes();
        //then finallly we are making the call using enqueue() it takes callback interface as an argument and callback is having two methods onRespnose() and onFailure
        //if the request is successfull we will get the correct response and onResponse will be executed  if there is some error we will get inside the onFailure() method
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call,Response<List<Hero>> response) {
                //In this point we got our hero list thats damn easy right ;)
                heroList = response.body();

                CustomAdapter adapter = new CustomAdapter(MainActivity.this,heroList);
                listView.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<List<Hero>> call,Throwable t) {
                Toast.makeText(getApplicationContext(),t.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

    }
}

//public class CustomAdapter extends BaseAdapter {
//
//    public CustomAdapter(MainActivity mainActivity,List<Hero> heroList) {
//
//    }
//
//    @Override
//    public int getCount() {
//        return heros.size();
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return 0;
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return null;
//    }
//
//    @Override
//    public View getView(int i,View view,ViewGroup viewGroup) {
//        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        view = inflater.inflate(R.layout.listviewitem,null);
//
//
//        TextView textViewname=(TextView)view.findViewById(R.id.textView);
//        TextView textViewRealName=(TextView)view.findViewById(R.id.textViewRealName);
//        TextView textViewTeam=(TextView)view.findViewById(R.id.textViewTeam);
//        TextView textViewFirstApperance=(TextView)view.findViewById(R.id.textViewFirstApperance);
//        TextView textViewcreatedby=(TextView)view.findViewById(R.id.textViewCreatedby);
//        ImageView imageView=(ImageView) view.findViewById(R.id.imageView4);
//        TextView textViewpublisher=(TextView)view.findViewById(R.id.textViewPublisher);
//        TextView textViewbio=(TextView)view.findViewById(R.id.textViewBio);
//
//
//
//        textViewname.setText(heros.get(position).getName());
//        textViewRealName.setText(heros.get(position).getRealName());
//        textViewTeam.setText(heros.get(position).getTeam());
//        textViewFirstApperance.setText(heros.get(position).getFirstApperance());
//        textViewcreatedby.setText(heros.get(position).getcreatedby());
//        imageView.setImageResource(heros.get(position).getimageView());
//        textViewpublisher.setText(heros.get(position).getpublisher());
//        textViewbio.setText(heros.get(position).getbio());
//
//
//        return view;
//    }
//}
//
