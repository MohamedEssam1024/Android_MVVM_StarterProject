package com.essam.starterproject.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.essam.starterproject.R;
import com.essam.starterproject.data.APIResponce.Operators;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    ArrayList<Operators> itemsList = new ArrayList();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,parent,false);
        return new RecyclerViewViewHolder(rootView);
    }

    public void insertData(List<Operators> newOperators){
        itemsList.addAll(newOperators);
        notifyDataSetChanged();
    }

    public void clear() {
//        itemsList.clear();
        itemsList = new ArrayList();
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Operators items = itemsList.get(position);
        RecyclerViewViewHolder viewHolder= (RecyclerViewViewHolder) holder;
        viewHolder.title.setText(items.getEmail());
        viewHolder.albumId.setText(items.getFirstName());
//        Picasso.get().load(items.imageurl).into(viewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    private class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView albumId;
        ImageView imageView;

        public RecyclerViewViewHolder(View rootView) {
            super(rootView);
            title = rootView.findViewById(R.id.textView2);
            albumId = itemView.findViewById(R.id.textView);
//            imageView = rootView.findViewById(R.id.imageView);
        }
    }

}
