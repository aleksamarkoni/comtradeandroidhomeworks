package com.example.code.autoperionica;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CarWashAdapter extends RecyclerView.Adapter<CarWashAdapter.AutoViewHolder> {

    private static final String TAG = CarWashAdapter.class.getSimpleName();
    private List<Auto> autoList;

    public CarWashAdapter(List<Auto> autoList) {
        this.autoList = autoList;
    }

    @Override
    public AutoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d(TAG, "On create view Holder");
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.auto_list_item, parent, false);
        return new CarWashAdapter.AutoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AutoViewHolder holder, int position) {
        Auto auto = autoList.get(position);
        Log.d(TAG, "On Bind View Holder: " + auto);
        holder.registracijaTextView.setText(auto.getRegistracija());
        holder.markaTextView.setText(auto.getMarka());
        holder.cenaTextView.setText("" + auto.getCenaPranja());
        //TODO boja mora da se definise, to cemo videti kako
    }

    @Override
    public int getItemCount() {
        return autoList.size();
    }

    public static class AutoViewHolder extends RecyclerView.ViewHolder {
        public TextView cenaTextView;
        public TextView markaTextView;
        public TextView registracijaTextView;
        public View bojaView;
        public AutoViewHolder(View itemView) {
            super(itemView);
            cenaTextView = itemView.findViewById(R.id.cena_text_view);
            markaTextView = itemView.findViewById(R.id.marka_text_view);
            registracijaTextView = itemView.findViewById(R.id.registracija_text_view);
            bojaView = itemView.findViewById(R.id.car_color_view);
        }
    }
}
