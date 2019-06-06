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

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener {

    public TextView txtMenuName;
    public ImageView imageView;

    private ItemCLickListener itemCLickListener;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);
        txtMenuName = (TextView)itemView.findViewById(R.id.menu_item);
        imageView = (ImageView)itemView.findViewById(R.id.menu_image);

        itemView.setOnCreateContextMenuListener(this);
        itemView.setOnClickListener(this);
    }
    public void setItemCLickListener(ItemCLickListener itemCLickListener){
        this.itemCLickListener=itemCLickListener;
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
