package com.example.promobiassignment.modules.feeds.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.promobiassignment.R;
import com.example.promobiassignment.database.entities.Feed;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<Feed> data;
    private RecyclerViewAdapter.ClickListener clickListener;
    private Context mContext;

    public RecyclerViewAdapter(ClickListener clickListener) {
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        return new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.recycler_view_list_row, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {

        final int position = holder.getAdapterPosition();
        Feed feed = data.get(position);

        holder.txtName.setText(feed.getDisplayTitle());
        holder.txtBirthYear.setText(feed.getSummaryShort());
        holder.imageViewIcons.setImageDrawable(ContextCompat.getDrawable(mContext, R.drawable.ic_launcher_background));

        if (feed.getMultimedia() != null) {
            String url = feed.getMultimedia().getSrc();
            Glide.with(mContext).load(url)
                    .thumbnail(0.5f)
                    .into(holder.imageViewIcons);
        }

        holder.mainLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.launchIntent(position);   // data.get(position).films.get(0)
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtName;
        private TextView txtBirthYear;
        private ImageView imageViewIcons;
        private LinearLayout mainLinearLayout;

        ViewHolder(View itemView) {
            super(itemView);

            txtName = itemView.findViewById(R.id.txtName);
            txtBirthYear = itemView.findViewById(R.id.txtBirthYear);
            imageViewIcons = itemView.findViewById(R.id.imageViewIcons);
            mainLinearLayout = itemView.findViewById(R.id.mainLinearLayout);

        }
    }

    public interface ClickListener {
        void launchIntent(int position);
    }

    public void setData(List<Feed> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public ArrayList<Feed> refreshResults() {
        return (ArrayList<Feed>) data;
    }

}
