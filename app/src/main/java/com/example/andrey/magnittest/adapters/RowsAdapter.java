package com.example.andrey.magnittest.adapters;


import android.graphics.drawable.ClipDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.andrey.magnittest.R;
import com.example.andrey.magnittest.entities.Item;
import com.example.andrey.magnittest.storage.OnListItemClickListener;

import java.util.HashMap;
import java.util.List;

public class RowsAdapter extends RecyclerView.Adapter<RowsAdapter.RowsHolder> {

    private List<Item> items;
    private OnListItemClickListener clickListener;

    public RowsAdapter(List<Item> compItems, OnListItemClickListener clickListener) {
        this.items = compItems;
        this.clickListener = clickListener;
    }

    @Override
    public RowsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new RowsHolder(v);
    }

    @Override
    public void onBindViewHolder(RowsHolder holder, int position) {
        holder.bind(items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class RowsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView number;
        Button btn;

        public RowsHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            number = (TextView) itemView.findViewById(R.id.row_number);
            btn = (Button) itemView.findViewById(R.id.button);
        }

        public void bind(Item item) {
            number.setText(String.valueOf(item.getNumber()));
            ClipDrawable drawable = (ClipDrawable) btn.getBackground();
            drawable.setLevel((int)(item.getRate()*10000));
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }
    }

}