package com.example.user.lesson_android_development.name;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.databinding.NameItemBinding;
import com.example.user.lesson_android_development.util.RecyclerViewClickListener;

import java.util.ArrayList;
import java.util.List;

public class NameAdapter extends RecyclerView.Adapter {

    private ArrayList<Name> mList = new ArrayList<>();
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;

    public NameAdapter(Context context, RecyclerViewClickListener listener) {
        mListener = listener;
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NameViewHolder(NameItemBinding.inflate(mInflater, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((NameViewHolder) holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void setItems(List<Name> items) {
        List<Name> oldItems = mList;
        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new Name.Callback(oldItems, items));
        mList.clear();
        mList.addAll(items);
        result.dispatchUpdatesTo(this);
    }
}
