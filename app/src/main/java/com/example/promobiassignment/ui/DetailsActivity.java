package com.example.promobiassignment.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.promobiassignment.R;
import com.example.promobiassignment.pojo.Result;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailsActivity extends AppCompatActivity {

    @BindView(R.id.iv_poster)
    ImageView posterImageView;
    @BindView(R.id.tv_headline)
    TextView headlineTextView;
    @BindView(R.id.tv_title)
    TextView titleTextView;
    @BindView(R.id.tv_reviewer)
    TextView reviewerTextView;
    @BindView(R.id.tv_critics)
    TextView criticsTextView;
    @BindView(R.id.tv_publication_date)
    TextView publicationDateTextView;
    @BindView(R.id.tv_summary)
    TextView summaryTextView;
    @BindView(R.id.tv_link)
    TextView linkToWebViewTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        final Result result = (Result) intent.getSerializableExtra("result");

        setDataOnUi(result);

        linkToWebViewTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = result.getLink().getUrl();
                startActivity(new Intent(DetailsActivity.this, WebviewActivity.class).putExtra("url",link));
            }
        });

    }

    private void setDataOnUi(Result result) {
        try {
            if (null != result.getHeadline())
                headlineTextView.setText(result.getHeadline());
            if (null != result.getDisplayTitle())
                titleTextView.setText(result.getDisplayTitle());
            if (null != result.getByline())
                reviewerTextView.setText(result.getByline());
            if (null != result.getCriticsPick())
                criticsTextView.setText(result.getCriticsPick()+"");
            if (null != result.getPublicationDate())
                publicationDateTextView.setText(result.getPublicationDate());
            if (null != result.getSummaryShort())
                summaryTextView.setText(result.getSummaryShort());
            if (null != result.getLink().getSuggestedLinkText())
                linkToWebViewTextView.setText(result.getLink().getSuggestedLinkText());

            if (null != result.getMultimedia())
                if (null != result.getMultimedia().getSrc())
                    Glide.with(DetailsActivity.this).load(result.getMultimedia().getSrc())
                            .thumbnail(0.5f)
                            .crossFade()
                            .diskCacheStrategy(DiskCacheStrategy.ALL)
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(posterImageView);
        }
        catch (NullPointerException n){
            n.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
