package com.example.promobiassignment.modules.feeds;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.promobiassignment.R;
import com.example.promobiassignment.database.entities.Feed;
import com.example.promobiassignment.modules.WebviewActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeedDetailsActivity extends AppCompatActivity {

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
        final Feed feed = (Feed) intent.getSerializableExtra("feed");

        setDataOnUi(feed);

        linkToWebViewTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String link = feed.getLink().getUrl();
                startActivity(new Intent(FeedDetailsActivity.this, WebviewActivity.class).putExtra("url",link));
            }
        });

    }

    private void setDataOnUi(Feed feed) {
        try {
            if (null != feed.getHeadline())
                headlineTextView.setText(feed.getHeadline());
            if (null != feed.getDisplayTitle())
                titleTextView.setText(feed.getDisplayTitle());
            if (null != feed.getByline())
                reviewerTextView.setText(feed.getByline());
            if (null != feed.getCriticsPick())
                criticsTextView.setText(feed.getCriticsPick()+"");
            if (null != feed.getPublicationDate())
                publicationDateTextView.setText(feed.getPublicationDate());
            if (null != feed.getSummaryShort())
                summaryTextView.setText(feed.getSummaryShort());
            if (null != feed.getLink().getSuggestedLinkText())
                linkToWebViewTextView.setText(feed.getLink().getSuggestedLinkText());

            if (null != feed.getMultimedia())
                if (null != feed.getMultimedia().getSrc())
                    Glide.with(FeedDetailsActivity.this).load(feed.getMultimedia().getSrc())
                            .thumbnail(0.5f)
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
