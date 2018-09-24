package com.example.user.lesson_android_development.name;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.databinding.NameItemBinding;
import com.example.user.lesson_android_development.util.RecyclerViewClickListener;

public class NameViewHolder extends RecyclerView.ViewHolder {

    private NameItemBinding mNameItemBinding;
    private RecyclerViewClickListener mListener;

    public NameViewHolder(@NonNull NameItemBinding nameItemBinding, RecyclerViewClickListener listener) {
        super(nameItemBinding.getRoot());
        mNameItemBinding = nameItemBinding;
        mNameItemBinding.setListener(listener);
    }


    public void setup(Name name){
        mNameItemBinding.setName(name);
    }
}
