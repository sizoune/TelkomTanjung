package com.kp.mwi.telkomtanjung.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.kp.mwi.telkomtanjung.Model.ODP;
import com.kp.mwi.telkomtanjung.R;

import java.util.ArrayList;

/**
 * Created by mwi on 7/21/17.
 */

public class LihatDataAdapter extends RecyclerView.Adapter<LihatDataAdapter.LihatDataViewHolder> {
    private ArrayList<ODP> filterListODP = new ArrayList<>();
    private Context context;
    ArrayList<ODP> dataodp = new ArrayList<>();

    public LihatDataAdapter(Context context) {
        this.context = context;
    }

    @Override
    public LihatDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new LihatDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(LihatDataViewHolder holder, int position) {
        ODP odp = filterListODP.get(position);
        holder.namaODP.setText(odp.getNama());
        holder.portOLT.setText(odp.getPortolt());
        holder.itemCLick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked !", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return filterListODP.size();
    }

    class LihatDataViewHolder extends RecyclerView.ViewHolder {
        public TextView namaODP, portOLT;
        public View itemCLick;

        public LihatDataViewHolder(View itemView) {
            super(itemView);

            namaODP = (TextView) itemView.findViewById(R.id.txtNamaODP);
            portOLT = (TextView) itemView.findViewById(R.id.txtNamaPortOLT);
            itemCLick = (View) itemView.findViewById(R.id.itemList);
        }
    }

    public void filter(String keyword, String kategori) {
        filterListODP.clear();
        for (ODP odp : dataodp) {
            if (kategori.equals("Nama ODP")) {
                if (odp.getNama().toLowerCase().contains(keyword.toLowerCase())) {
                    filterListODP.add(odp);
                }
            } else {
                if (odp.getPortolt().toLowerCase().contains(keyword.toLowerCase())) {
                    filterListODP.add(odp);
                }
            }
        }
        notifyDataSetChanged();
    }

    public void addData(ODP odp) {
        dataodp.add(odp);
        filterListODP.add(odp);
        notifyDataSetChanged();
    }
}
