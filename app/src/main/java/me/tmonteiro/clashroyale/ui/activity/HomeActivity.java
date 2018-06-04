package me.tmonteiro.clashroyal.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import me.tmonteiro.clashroyal.R;
import me.tmonteiro.clashroyal.vo.card.CardInfo;
import me.tmonteiro.clashroyal.vo.card.CardComposition;
import me.tmonteiro.clashroyal.vo.card.CardStatus;
import me.tmonteiro.clashroyal.ui.adapter.CardAdapter;
import me.tmonteiro.clashroyal.viewmodel.card.CardViewModel;

public class HomeActivity extends AppCompatActivity {


    @Inject
    ViewModelProvider.Factory factory;

    CardViewModel cardViewModel;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        setupViewModel();
        setupView();
        loadCards();
    }

    private void setupView() {
        recyclerView = findViewById(R.id.rv_card);
    }

    private void setupViewModel(){
        this.cardViewModel = ViewModelProviders.of(this, factory)
                .get(CardViewModel.class);
    }


    private void loadCards() {
        this.cardViewModel.getCardList().observe(this, new Observer<CardComposition>() {
            @Override
            public void onChanged(@Nullable CardComposition cardComposition) {
                if(cardComposition.getStatus() == CardStatus.SUCCESS){
                    fetchRecycleViewer(cardComposition.getResult());
                }
            }
        });
    }

    private void fetchRecycleViewer(List<CardInfo> cardInfoList) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new CardAdapter(getApplicationContext(), cardInfoList));
    }

}
