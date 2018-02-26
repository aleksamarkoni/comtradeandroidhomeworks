package com.example.dell.probatodo;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PerionicaAdapter extends RecyclerView.Adapter<PerionicaAdapter.PerionicaViewHolder> {

    private List<Auto> autoList;
    private OnAutoListener onAutoListener;

    public PerionicaAdapter(List<Auto> autoList) {
        this.autoList = autoList;
    }

    public interface OnAutoListener {
        void onDoneClicked(Auto auto);
    }

    @Override
    public PerionicaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.auto, parent, false);
        CheckBox pranje = view.findViewById(R.id.pranje);
        pranje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int position = (Integer) compoundButton.getTag();
                Auto auto = autoList.get(position);
                auto.setPranje(b);
                if (onAutoListener != null) {
                    onAutoListener.onDoneClicked(auto);
                }
            }
        });
        CheckBox voskiranje = view.findViewById(R.id.voskiranje);
        voskiranje.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                int position = (Integer) compoundButton.getTag();
                Auto auto = autoList.get(position);
                auto.setVoskiranje(b);
                if (onAutoListener != null) {
                    onAutoListener.onDoneClicked(auto);
                }
            }
        });
        return new PerionicaAdapter.PerionicaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PerionicaViewHolder holder, int position) {
        final Auto auto = autoList.get(position);
        holder.model.setText(auto.getModel());
        holder.registracija.setText(auto.getRegistracija());
        holder.pranje.setTag(position);
        holder.pranje.setChecked(auto.isPranje());
        holder.voskiranje.setTag(position);
        holder.voskiranje.setChecked(auto.isVoskiranje());

    }

    @Override
    public int getItemCount() {
        return autoList.size();
    }

    static class PerionicaViewHolder extends RecyclerView.ViewHolder {

        private TextView model;
        private TextView registracija;
        private CheckBox pranje;
        private CheckBox voskiranje;
        private ImageView slika;

        public PerionicaViewHolder(View itemView) {
            super(itemView);
            model = itemView.findViewById(R.id.model);
            registracija = itemView.findViewById(R.id.registracija);
            pranje = itemView.findViewById(R.id.pranje);
            voskiranje = itemView.findViewById(R.id.voskiranje);
            slika = itemView.findViewById(R.id.slika);
        }
    }
}
