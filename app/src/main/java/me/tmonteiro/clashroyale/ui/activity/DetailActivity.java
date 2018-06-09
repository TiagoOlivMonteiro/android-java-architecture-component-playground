package me.tmonteiro.clashroyale.ui.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import me.tmonteiro.clashroyale.R;
import me.tmonteiro.clashroyale.di.Injectable;
import me.tmonteiro.clashroyale.viewmodel.detail.CardDetailViewModel;
import me.tmonteiro.clashroyale.vo.detail.CardDetailComposition;
import me.tmonteiro.clashroyale.vo.detail.CardDetailInfo;

public class DetailActivity extends AppCompatActivity implements Injectable {

    public static final String EXTRA_ID_NAME = "idName";

    @Inject
    ViewModelProvider.Factory factory;

    private String idName;
    private CardDetailViewModel cardDetailViewModel;
    private TextView tvTitle;
    private TextView tvDescription;
    private ImageView ivIcon;

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
        this.cardDetailViewModel.getCard(this.idName).observe(this, new Observer<CardDetailComposition>() {
            @Override
            public void onChanged(@Nullable CardDetailComposition cardDetailComposition) {
                switch (cardDetailComposition.getStatus()) {
                    case SUCCESS:
                        setCardInfo(cardDetailComposition.getResult());
                        break;
                }
            }
        });
    }

    private void setCardInfo(CardDetailInfo cardDetailInfo) {
        this.tvDescription.setText(cardDetailInfo.getDescription());
        this.tvTitle.setText(cardDetailInfo.getTitle());
        Picasso.get().load(cardDetailInfo.getIconUrl()).into(ivIcon);
    }


}
