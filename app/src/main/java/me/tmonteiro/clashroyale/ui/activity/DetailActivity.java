package me.tmonteiro.clashroyale.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import me.tmonteiro.clashroyale.R;
import me.tmonteiro.clashroyale.di.Injectable;
import me.tmonteiro.clashroyale.viewmodel.detail.CardDetailViewModel;
import me.tmonteiro.clashroyale.vo.detail.CardDetailComposition;
import me.tmonteiro.clashroyale.vo.detail.CardDetailInfo;

public class DetailActivity extends AppCompatActivity implements Injectable, View.OnClickListener {

    public static final String EXTRA_ID_NAME = "idName";

    @Inject
    ViewModelProvider.Factory factory;

    private String idName;
    private CardDetailViewModel cardDetailViewModel;
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView ivIcon;

    View tryAgainView;
    TextView tvTryAgain;
    ProgressBar pbLoading;
    CardView cvInfoContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        handleExtra();
        setupViewModel();
        setupView();
        loadCard();
    }

    private void setupView() {
        this.ivIcon = findViewById(R.id.iv_icon);
        this.tvTitle = findViewById(R.id.tv_title);
        this.tvDescription = findViewById(R.id.tv_description);
        this.tvTryAgain = findViewById(R.id.tv_try_again);
        this.tryAgainView = findViewById(R.id.try_again_container);
        this.pbLoading = findViewById(R.id.pb_loading);
        this.cvInfoContainer = findViewById(R.id.cv_info_container);
        this.tvTryAgain.setOnClickListener(this);
    }

    private void handleExtra() {
        final Bundle bundle = getIntent().getExtras();
        this.idName = bundle.getString(EXTRA_ID_NAME);
    }

    private void setupViewModel() {
        this.cardDetailViewModel = ViewModelProviders.of(this, factory)
                .get(CardDetailViewModel.class);
    }

    private void loadCard() {
        handleProgressVisibility(true);
        this.cardDetailViewModel.getCard(this.idName).observe(this, new Observer<CardDetailComposition>() {
            @Override
            public void onChanged(@Nullable CardDetailComposition cardDetailComposition) {

                if(cardDetailComposition == null || cardDetailComposition.getStatus() == null){
                    handleError();
                    return;
                }

                switch (cardDetailComposition.getStatus()) {
                    case SUCCESS:
                        handleSuccesss(cardDetailComposition.getResult());
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

    private void handleSuccesss(CardDetailInfo cardDetailInfo) {
        handleProgressVisibility(false);
        handleTryAgainVisibility(false);
        handleCardViewInfoVisibility(true);
        this.tvDescription.setText(cardDetailInfo.getDescription());
        this.tvTitle.setText(cardDetailInfo.getTitle());
        Picasso.get().load(cardDetailInfo.getIconUrl()).into(ivIcon);
    }

    private void handleError() {
        handleCardViewInfoVisibility(false);
        handleProgressVisibility(false);
        handleTryAgainVisibility(true);
    }

    private void handleProgress() {
        handleProgressVisibility(true);
    }

    private void handleCardViewInfoVisibility(boolean visible) {
        if (visible) {
            cvInfoContainer.setVisibility(View.VISIBLE);
        } else {
            cvInfoContainer.setVisibility(View.GONE);
        }
    }


    private void handleTryAgainVisibility(boolean visible) {
        if (visible) {
            tryAgainView.setVisibility(View.VISIBLE);
        } else {
            tryAgainView.setVisibility(View.GONE);
        }
    }

    private void handleProgressVisibility(boolean visible) {
        if (visible) {
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            pbLoading.setVisibility(View.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.tv_try_again) {
            loadCard();
        }
    }
}
