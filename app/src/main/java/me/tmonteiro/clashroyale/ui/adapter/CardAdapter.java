package me.tmonteiro.clashroyale.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.picasso.Picasso;

import java.util.List;

import me.tmonteiro.clashroyale.R;
import me.tmonteiro.clashroyale.ui.viewholder.CardViewHolder;
import me.tmonteiro.clashroyale.vo.card.CardInfo;

public class CardAdapter extends RecyclerView.Adapter {

    private final Context context;
    private final CardAdapterItemSelected cardAdapterItemSelected;
    private List<CardInfo> cardInfoList;


    public CardAdapter(Context context, CardAdapterItemSelected cardAdapterItemSelected, List<CardInfo> cardInfoModelList) {
        this.context = context;
        this.cardAdapterItemSelected = cardAdapterItemSelected;
        this.cardInfoList = cardInfoModelList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_home, parent, false);
        final CardViewHolder holder = new CardViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int itemPosition = holder.getAdapterPosition();
                final CardInfo cardInfo = cardInfoList.get(itemPosition);
                if(cardInfo != null){
                    cardAdapterItemSelected.itemSelected(cardInfo.getIdName());
                }

            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        CardViewHolder holder = (CardViewHolder) viewHolder;
        CardInfo cardInfo = cardInfoList.get(position);
        holder.getName().setText(cardInfo.getName());
        Picasso.get().load(cardInfo.getIconUrl()).into(holder.getIcon());
    }

    @Override
    public int getItemCount() {
        return cardInfoList == null ? 0 : cardInfoList.size();
    }



}
