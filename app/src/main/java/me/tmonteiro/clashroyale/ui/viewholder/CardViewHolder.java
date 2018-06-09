package me.tmonteiro.clashroyale.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.tmonteiro.clashroyale.R;

public class CardViewHolder extends RecyclerView.ViewHolder {

    final TextView name;
    final ImageView icon;
    final View rarity;
    final TextView elixirCost;
    final TextView arena;

    public CardViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        icon = itemView.findViewById(R.id.iv_icon);
        rarity = itemView.findViewById(R.id.view_rarity);
        elixirCost = itemView.findViewById(R.id.tv_elixir_cost);
        arena = itemView.findViewById(R.id.tv_arena);
    }

    public TextView getName() {
        return name;
    }

    public ImageView getIcon() {
        return icon;
    }

    public View getRarity() {
        return rarity;
    }

    public TextView getElixirCost() {
        return elixirCost;
    }

    public TextView getArena() {
        return arena;
    }
}
