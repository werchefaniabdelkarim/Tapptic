package com.tapptic.abwe.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.tapptic.abwe.datamodels.ItemList;
import com.tapptic.abwe.R;

import java.util.ArrayList;
import java.util.List;

public class SimpleItemRecyclerViewAdapter extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final MainActivity mParentActivity;
    private final List<ItemList> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            ItemList item = (ItemList) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(DetailFragment.ARG_ITEM_ID, item.name);
                DetailFragment fragment = new DetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailFragment.ARG_ITEM_ID, item.name);

                context.startActivity(intent);
            }
        }
    };

    public SimpleItemRecyclerViewAdapter(MainActivity parent,
                                         boolean twoPane) {
        mValues = new ArrayList<>();
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);//item_list_content
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        ItemList market = mValues.get(position);
        holder.txtPrice.setText(market.name);
        Picasso.get()
                .load(market.image)
                .into(holder.imgMarket);

        holder.cardView.setTag(mValues.get(position));
        holder.cardView.setOnClickListener(mOnClickListener);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setData(List<ItemList> marketList) {
        this.mValues.addAll(marketList);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgMarket;
        public TextView txtPrice;
        public CardView cardView;

        ViewHolder(View view) {
            super(view);
            imgMarket = view.findViewById(R.id.txtMarket);
            txtPrice = view.findViewById(R.id.txtPrice);
            cardView = view.findViewById(R.id.cardView);
        }
    }
}