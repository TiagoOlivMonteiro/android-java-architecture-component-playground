package me.tmonteiro.clashroyale.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import me.tmonteiro.clashroyale.R;
import me.tmonteiro.clashroyale.di.Injectable;
import me.tmonteiro.clashroyale.ui.adapter.CardAdapter;
import me.tmonteiro.clashroyale.ui.adapter.CardAdapterItemSelected;
import me.tmonteiro.clashroyale.viewmodel.card.CardViewModel;
import me.tmonteiro.clashroyale.viewmodel.ViewModelProviderWrapper;
import me.tmonteiro.clashroyale.vo.card.CardComposition;
import me.tmonteiro.clashroyale.vo.card.CardInfo;

public class HomeActivity extends AppCompatActivity implements Injectable, CardAdapterItemSelected, View.OnClickListener {

    @Inject
    ViewModelProvider.Factory factory;

    @Inject
    ViewModelProviderWrapper providerWrapper;

    CardViewModel cardViewModel;

    RecyclerView recyclerView;
    View tryAgainView;
    TextView tvTryAgain;
    ProgressBar pbLoading;

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
        this.tvTryAgain = findViewById(R.id.tv_try_again);
        this.tryAgainView = findViewById(R.id.try_again_container);
        this.pbLoading = findViewById(R.id.pb_loading);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        this.tvTryAgain.setOnClickListener(this);
    }

    private void setupViewModel() {
        this.cardViewModel = providerWrapper.of(this, factory)
                .get(CardViewModel.class);
    }

    private void loadCards() {
        this.handleProgressVisibility(true);
        this.cardViewModel.getCardList().observe(this, new Observer<CardComposition>() {
            @Override
            public void onChanged(@Nullable CardComposition cardComposition) {

                if(cardComposition == null || cardComposition.getStatus() == null){
                    handleError();
                }

                switch (cardComposition.getStatus()) {
                    case SUCCESS:
                        handleSuccess(cardComposition.getResult());
                        break;
                    case ERROR:
                        handleError();
                        break;
                    case LOADING:
                        handleProgress();
                        break;
                }
            }
        });
    }

    private void handleProgress(){
        handleProgressVisibility(true);
    }

    private void handleSuccess(List<CardInfo> cardInfoList){
        handleProgressVisibility(false);
        handleTryAgainVisibility(false);
        fetchRecyclerViewer(cardInfoList);
    }

    private void handleError(){
        handleProgressVisibility(false);
        handleTryAgainVisibility(true);
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

    private void handleTryAgainVisibility(boolean visible){
        if(visible){
            tryAgainView.setVisibility(View.VISIBLE);
        } else {
            tryAgainView.setVisibility(View.GONE);
        }
    }

    private void handleProgressVisibility(boolean visible) {
        if(visible){
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }

    }

    @Override
    public void itemSelected(String idName) {
        navigateToDetail(idName);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.tv_try_again){
            loadCards();
        }
    }

    @VisibleForTesting
    public void setFactory(ViewModelProvider.Factory factory){
        this.factory = factory;
    }

    @VisibleForTesting
    public void setProviderWrapper(ViewModelProviderWrapper wrapper){
        this.providerWrapper = wrapper;
    }
}
