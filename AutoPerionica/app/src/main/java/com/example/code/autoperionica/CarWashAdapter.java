package com.example.code.autoperionica;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CarWashAdapter extends RecyclerView.Adapter<CarWashAdapter.AutoViewHolder> {

    @Override
    public AutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, int position) {
        TextView cenaTextView = holder.itemView.findViewById(R.id.cena_text_view);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class AutoViewHolder extends RecyclerView.ViewHolder {

        public AutoViewHolder(View itemView) {
            super(itemView);
        }
    }
}
