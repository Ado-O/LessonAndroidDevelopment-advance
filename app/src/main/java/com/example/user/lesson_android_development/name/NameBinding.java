package com.example.user.lesson_android_development.name;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.user.lesson_android_development.util.RecyclerViewClickListener;

import java.util.List;

public class NameBinding {

    public static final String TAG = NameBinding.class.getSimpleName();

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:nameItems"})
    public static void setNameItems(RecyclerView recyclerView, List nameItems) {

        if (nameItems != null) {
            ((NameAdapter) recyclerView.getAdapter()).setItem(nameItems);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:listenerItem"})
    public static void setItem(TextView textView, List nameItems) {

        if (nameItems.isEmpty()) {
            textView.setText("List is empty");
        } else {
            textView.setText(" ");
        }
    }
}
