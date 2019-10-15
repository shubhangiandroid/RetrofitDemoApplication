package com.spp.retrofitdemoapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context mContext;
    List<Hero> heros;
   // private int position;

    public CustomAdapter(Context mContext,List<Hero> heros) {
        this.mContext = mContext;
        this.heros = heros;



}

    @Override
    public int getCount() {
        return heros.size();
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public View getView(int position,View view,ViewGroup viewGroup) {

        LayoutInflater inflater=(LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        view = inflater.inflate(R.layout.listviewitem,null);


        TextView textViewname=(TextView)view.findViewById(R.id.textView);
        TextView textViewRealName=(TextView)view.findViewById(R.id.textViewRealName);
        TextView textViewTeam=(TextView)view.findViewById(R.id.textViewTeam);
        TextView textViewFirstApperance=(TextView)view.findViewById(R.id.textViewFirstApperance);
        TextView textViewcreatedby=(TextView)view.findViewById(R.id.textViewCreatedby);
        ImageView imageView=(ImageView) view.findViewById(R.id.imageView4);
        TextView textViewpublisher=(TextView)view.findViewById(R.id.textViewPublisher);
        TextView textViewbio=(TextView)view.findViewById(R.id.textViewBio);



        textViewname.setText(heros.get(position).getName());
        textViewRealName.setText(heros.get(position).getRealname());
        textViewTeam.setText(heros.get(position).getTeam());
        textViewFirstApperance.setText(heros.get(position).getFirstappearance());
        textViewcreatedby.setText(heros.get(position).getCreatedby());
       // imageView.setImageResource(heros.get(position).getImageurl());
        Picasso.with(mContext).load(heros.get(position).getImageurl()).into(imageView);

        textViewpublisher.setText(heros.get(position).getPublisher());
        textViewbio.setText(heros.get(position).getBio());


        return view;
    }
}



