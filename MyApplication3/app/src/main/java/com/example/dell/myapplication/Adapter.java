package com.example.dell.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.FilmHolder> {

    List<Film> filmList;

    public Adapter(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public FilmHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.deo_liste, parent, false);
        return null;
    }

    @Override
         public void onBindViewHolder(FilmHolder holder, int position) {

    }




    @Override
    public int getItemCount() {
        return filmList.size();
    }

    public static class FilmHolder extends RecyclerView.ViewHolder {
        public FilmHolder(View itemView) {
            super(itemView);
        }
    }
}
