package me.tmonteiro.clashroyale.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import me.tmonteiro.clashroyale.R;

public class CardViewHolder extends RecyclerView.ViewHolder {

    final TextView name;

    public CardViewHolder(View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.tv_name);
    }

    public TextView getName() {
        return name;
    }
}
