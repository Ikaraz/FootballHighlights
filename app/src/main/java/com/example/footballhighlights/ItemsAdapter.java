package com.example.footballhighlights;

import android.content.ClipData;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ItemsViewHolder> {
    private Context context;
    private ArrayList<Items> itemsArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener){
        this.listener = listener;
    }

    public ItemsAdapter(Context context, ArrayList<Items> itemsArrayList) {
        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @NonNull
    @Override
    public ItemsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(context).inflate(R.layout.items,viewGroup,false);
        return new ItemsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemsViewHolder itemsViewHolder, int i) {
        Items currentItem = itemsArrayList.get(i);

        String thumbnailUrl = currentItem.getThumbnailUrl();
        String gameTitle = currentItem.getGameTitle();
        String competition = currentItem.getCompetiton();

        itemsViewHolder.txtCompetition.setText(competition);
        itemsViewHolder.txtGameTitle.setText(gameTitle);
        Picasso.with(context).load(thumbnailUrl).fit().centerInside().into(itemsViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return itemsArrayList.size();
    }

    public class ItemsViewHolder extends RecyclerView.ViewHolder{
        public ImageView imageView;
        public TextView txtGameTitle;
        public TextView txtCompetition;

        public ItemsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            txtGameTitle = itemView.findViewById(R.id.txtGameTitle);
            txtCompetition = itemView.findViewById(R.id.txtCompetition);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
