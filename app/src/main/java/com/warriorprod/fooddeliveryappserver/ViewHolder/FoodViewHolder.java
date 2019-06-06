package com.warriorprod.fooddeliveryappserver.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.warriorprod.fooddeliveryappserver.Interface.ItemCLickListener;
import com.warriorprod.fooddeliveryappserver.R;

import static com.warriorprod.fooddeliveryappserver.Common.Common.DELETE;
import static com.warriorprod.fooddeliveryappserver.Common.Common.UPDATE;

public class FoodViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {

    public TextView food_name;
    public ImageView food_image;

    private ItemCLickListener itemCLickListener;

    public void setItemCLickListener(ItemCLickListener itemCLickListener) {
        this.itemCLickListener = itemCLickListener;
    }


    public FoodViewHolder(@NonNull View itemView) {
        super(itemView);

        food_name = (TextView)itemView.findViewById(R.id.food_item);
        food_image = (ImageView)itemView.findViewById(R.id.food_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        itemCLickListener.onClick(v,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Select the action");

        menu.add(0,0,getAdapterPosition(),UPDATE);
        menu.add(0,1,getAdapterPosition(),DELETE);
    }
}
