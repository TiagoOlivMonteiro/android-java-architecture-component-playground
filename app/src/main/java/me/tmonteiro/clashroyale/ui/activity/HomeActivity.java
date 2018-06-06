package me.tmonteiro.clashroyale.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import me.tmonteiro.clashroyale.R;
import me.tmonteiro.clashroyale.di.Injectable;
import me.tmonteiro.clashroyale.ui.adapter.CardAdapter;
import me.tmonteiro.clashroyale.ui.adapter.CardAdapterItemSelected;
import me.tmonteiro.clashroyale.viewmodel.card.CardViewModel;
import me.tmonteiro.clashroyale.vo.card.CardComposition;
import me.tmonteiro.clashroyale.vo.card.CardInfo;
import me.tmonteiro.clashroyale.vo.card.CardStatus;

public class HomeActivity extends AppCompatActivity implements Injectable, CardAdapterItemSelected {

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
        this.recyclerView = findViewById(R.id.rv_card);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void setupViewModel() {
        this.cardViewModel = ViewModelProviders.of(this, factory)
                .get(CardViewModel.class);
    }


    private void loadCards() {
        this.cardViewModel.getCardList().observe(this, new Observer<CardComposition>() {
            @Override
            public void onChanged(@Nullable CardComposition cardComposition) {
                if (cardComposition.getStatus() == CardStatus.SUCCESS) {
                    fetchRecyclerViewer(cardComposition.getResult());
                }
            }
        });
    }

    private void fetchRecyclerViewer(List<CardInfo> cardInfoList) {
        CardAdapter cardAdapter = new CardAdapter(getApplicationContext(), this, cardInfoList);
        recyclerView.setAdapter(cardAdapter);
    }

    private void navigateToDetail(String idName) {
        final Bundle bundle = new Bundle();
        bundle.putString(DetailActivity.EXTRA_ID_NAME, idName);
        final Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void itemSelected(String idName) {
        navigateToDetail(idName);
    }

}
