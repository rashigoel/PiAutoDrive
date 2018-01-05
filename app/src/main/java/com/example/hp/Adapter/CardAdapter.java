package com.example.hp.Adapter;

import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.hp.rg.R;
import com.google.android.gms.maps.GoogleMap;

import java.util.List;

/**
 * Created by Rashi on 09-07-2016.
 */
public class CardAdapter extends RecyclerView.Adapter<CardAdapter.MyViewHolder> implements RecyclerView.OnItemTouchListener {
    private List<Data> CardList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView description, date;
        GoogleMap mMap;


        public MyViewHolder(View view) {
            super(view);
            description = (TextView) view.findViewById(R.id.description);
            date = (TextView) view.findViewById(R.id.date);
        }
    }

    public CardAdapter(List<Data> CardList){
        this.CardList= CardList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardlist, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data data = CardList.get(position);
        holder.description.setText(Data.getDescription());
        holder.date.setText(Data.getDate());

    }

    @Override
    public int getItemCount() {
        return CardList.size();
    }

    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }





}
