package com.nomadlab.myrecyclerview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.nomadlab.myrecyclerview.R;
import com.nomadlab.myrecyclerview.interfaces.OnFoodItemClickListener;
import com.nomadlab.myrecyclerview.model.Food;

import java.util.ArrayList;

/* 뷰홀더 먼저 생성 */
public class FoodListViewAdapter extends RecyclerView.Adapter<FoodListViewAdapter.ViewHolder> {

    ArrayList<Food> list;
    Context context;
    OnFoodItemClickListener foodItemClickListener;

    public FoodListViewAdapter(ArrayList<Food> list, Context context, OnFoodItemClickListener foodItemClickListener) {
        this.list = list;
        this.context = context;
        this.foodItemClickListener = foodItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 아이템 하나를 만들어 줄 뷰
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 데이터 맵핑
        Food food = list.get(position);
        Glide.with(context)
                .load(food.getThumbnail())
                .centerCrop()
                .transform(new CenterCrop(), new RoundedCorners(30))
                .into(holder.thumbnail);
        holder.titleTextView.setText(food.getTitle());
        holder.subTitleView.setText(food.getSubTitle());
        holder.detailTextView.setText(food.getDetail());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView thumbnail;
        TextView titleTextView;
        TextView subTitleView;
        TextView detailTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            thumbnail = itemView.findViewById(R.id.thumbnailImageView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            subTitleView = itemView.findViewById(R.id.subTitleTextView);
            detailTextView = itemView.findViewById(R.id.detailTextView);

            itemView.setOnClickListener(view -> {
                Toast.makeText(view.getContext(), "TEST " + getLayoutPosition(), Toast.LENGTH_SHORT).show();
                foodItemClickListener.onItemClicked(itemView, getLayoutPosition());
            });

        }
    }
}
