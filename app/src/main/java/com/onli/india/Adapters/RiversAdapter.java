package com.onli.india.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.onli.india.utils.Model;
import com.onli.india.R;
import com.onli.india.RiverInfoActivity;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RiversAdapter extends RecyclerView.Adapter<RiversAdapter.ViewHolder> {

    ArrayList<Model> list;
    Context context;

    public RiversAdapter(Context context, ArrayList<Model> list) {
        this.list = list;
        this.context = context;
    }

    public void filterList(ArrayList<Model> filterllist) {
        list = filterllist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rivers_recycler_list_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.textView.setText(list.get(holder.getAdapterPosition()).getName());
        Picasso.get()
                .load(list.get(holder.getAdapterPosition()).getImage())
                .into(holder.image);

        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, RiverInfoActivity.class);
                intent.putExtra("riverdata", list.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView textView;
        LinearLayout layout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.rivers_list_image);
            textView = itemView.findViewById(R.id.rivers_list_name);
            layout = itemView.findViewById(R.id.list_layout);
        }
    }
}
