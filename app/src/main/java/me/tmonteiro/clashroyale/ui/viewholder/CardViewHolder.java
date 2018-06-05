package me.tmonteiro.clashroyale.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import me.tmonteiro.clashroyale.R;

public class CardViewHolder extends RecyclerView.ViewHolder {

    final TextView name;
    final ImageView icon;

    public CardViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
        icon = itemView.findViewById(R.id.iv_icon);
    }

    public TextView getName() {
        return name;
    }

    public ImageView getIcon() {
        return icon;
    }
}
