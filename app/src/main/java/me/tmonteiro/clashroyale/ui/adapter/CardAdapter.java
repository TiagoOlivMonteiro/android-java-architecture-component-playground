package me.tmonteiro.clashroyale.ui.adapter;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
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

    private final static String CARD_RARITY_COMMON = "Common";
    private final static String CARD_RARITY_RARE = "Rare";
    private final static String CARD_RARITY_EPIC = "Epic";
    private final static String CARD_RARITY_LEGENDARY = "Legendary";

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
                if (cardInfo != null) {
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
        handleCardRarity(holder, cardInfo);
        holder.getName().setText(cardInfo.getName());
        holder.getElixirCost().setText(String.format(context.getString(R.string.card_elixir_cost), cardInfo.getElixirCost()));
        holder.getArena().setText(String.format(context.getString(R.string.card_arena), cardInfo.getArena()));
        Picasso.get().load(cardInfo.getIconUrl()).into(holder.getIcon());
    }

    private void handleCardRarity(CardViewHolder holder, CardInfo cardInfo) {

        final View viewRarity = holder.getRarity();

        switch (cardInfo.getRarity()) {
            case CARD_RARITY_COMMON:
                viewRarity.setBackground(context.getResources().getDrawable(R.drawable.card_common));
                break;
            case CARD_RARITY_RARE:
                viewRarity.setBackground(context.getResources().getDrawable(R.drawable.card_rare));
                break;
            case CARD_RARITY_EPIC:
                viewRarity.setBackground(context.getResources().getDrawable(R.drawable.card_epic));
                break;
            case CARD_RARITY_LEGENDARY:
                viewRarity.setBackground(context.getResources().getDrawable(R.drawable.card_legendary));
                final AnimationDrawable animationDrawable = (AnimationDrawable) viewRarity.getBackground();
                animationDrawable.setEnterFadeDuration(2000);
                animationDrawable.setExitFadeDuration(2000);
                animationDrawable.start();
                break;
            default:
                viewRarity.setBackground(context.getResources().getDrawable(R.drawable.card_common));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return cardInfoList == null ? 0 : cardInfoList.size();
    }


}
