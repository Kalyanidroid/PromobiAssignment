package com.example.promobiassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.promobiassignment.R;
import com.example.promobiassignment.pojo.Result;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private List<Result> data;
    private RecyclerViewAdapter.ClickListener clickListener;
    private Context mContext;

    @Inject
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
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.txtName.setText(data.get(position).getOpeningDate());
        holder.txtBirthYear.setText(data.get(position).getDisplayTitle());

        if(data.get(position).getMultimedia() != null){
        String url = data.get(position).getMultimedia().getSrc();


            Glide.with(mContext).load(url)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.imageViewIcons);

        }

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

            /*constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(data.get(getAdapterPosition()).films.get(0));
                }
            });*/
        }
    }

    public interface ClickListener {
        void launchIntent(String filmName);
    }

    public void setData(List<Result> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }
}
