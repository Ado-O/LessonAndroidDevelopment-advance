package com.example.user.lesson_android_development.util;

import android.view.View;

import com.example.user.lesson_android_development.data.Name;

public interface RecyclerViewClickListener {
    void recyclerViewListClicked(View v, Name name);
    void recyclerViewListClickedName(View v, Name name);

}
