package com.example.promobiassignment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> implements Filterable {
    private List<Result> data;
    private RecyclerViewAdapter.ClickListener clickListener;
    private Context mContext;
    private Filter filter;


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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.txtName.setText(data.get(position).getDisplayTitle());
        holder.txtBirthYear.setText(data.get(position).getDisplayTitle());

        if (data.get(position).getMultimedia() != null) {
            String url = data.get(position).getMultimedia().getSrc();


            Glide.with(mContext).load(url)
                    .thumbnail(0.5f)
                    .crossFade()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
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

    public void setData(List<Result> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }


    @Override
    public Filter getFilter() {
        if (filter == null)
            filter = new AppFilter<Result>(data);
        return filter;
    }

    private class AppFilter<T> extends Filter {
        private ArrayList<T> sourceObjects;

        public AppFilter(List<T> objects) {
            sourceObjects = new ArrayList<T>();
            synchronized (this) {
                sourceObjects.addAll(objects);
            }
        }

        @Override
        protected FilterResults performFiltering(CharSequence chars) {

            String filterSeq = chars.toString().toLowerCase();
            FilterResults result = new FilterResults();
            if (filterSeq != null && filterSeq.length() > 0) {
                ArrayList<T> filter = new ArrayList<T>();

                for (T object : sourceObjects) {
                    // the filtering itself
                    try {
                        if (((Result) object).getDisplayTitle().toString().toLowerCase().contains(filterSeq))   //   || ((DriverData) object).getLicenseNo().toString().toLowerCase().contains(filterSeq)
                            filter.add(object);
                    }
                    catch (NullPointerException e){
                        e.printStackTrace();
                    }

                }
                result.count = filter.size();
                result.values = filter;

            } else {
                synchronized (this) {
                    result.values = sourceObjects;
                    result.count = sourceObjects.size();
                }
            }
            return result;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            ArrayList<Result> filtered = (ArrayList<Result>) results.values;
            clear();
            data.addAll(filtered);
            notifyDataSetChanged();
        }
    }

    private void clear() {
        if (null != data && data.size() > 0){
            data.clear();
            notifyDataSetChanged();
        }
    }

    public ArrayList<Result> refreshResults(){
        return (ArrayList<Result>) data;
    }

}
