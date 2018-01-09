package com.example.dell.autoperionica;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.AutoViewHolder> {

    private List<Auto> autoList;

    public Adapter(List<Auto> autoList) {
        this.autoList = autoList;
    }

    @Override
    public AutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.jedan_auto, parent, false);
        return new Adapter.AutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, int position) {
        Auto auto = autoList.get(position);
        holder.registracija.setText(auto.getRegistracija());
        holder.marka.setText(auto.getMarka());
        holder.cena.setText("" + auto.getBoja());
    }

    @Override
    public int getItemCount() {
        return autoList.size();
    }

    public static class AutoViewHolder extends RecyclerView.ViewHolder {
        public TextView marka;
        public TextView registracija;
        public TextView cena;
        public View boja;
        public AutoViewHolder(View itemView) {
            super(itemView);
            marka = itemView.findViewById(R.id.marka);
            registracija = itemView.findViewById(R.id.registracija);
            cena = itemView.findViewById(R.id.cena);
            boja = itemView.findViewById(R.id.boja);
        }
    }
}
