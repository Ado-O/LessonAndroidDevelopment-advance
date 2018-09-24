package com.example.user.lesson_android_development.name;

import android.app.Dialog;
import android.arch.lifecycle.Observer;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.user.lesson_android_development.R;
import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.databinding.NameItemBinding;
import com.example.user.lesson_android_development.util.ActivityUtils;
import com.example.user.lesson_android_development.databinding.NameActBinding;
import com.example.user.lesson_android_development.util.ViewModelFactory;

public class NameActivity extends AppCompatActivity {

    private static final String TAG = NameActivity.class.getSimpleName();

    private NameActBinding mNameActBinding;
    private NameViewModel mNameViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.name_act);

        //find view
        mNameActBinding = DataBindingUtil.setContentView(this, R.layout.name_act);
        mNameViewModel = ViewModelFactory.obtainViewModel(this, NameViewModel.class);

        //setup
        setupFragment();
        addData();
    }

    /**
     * add fragment
     */
    private void setupFragment() {
        NameFragment nameFragment = (NameFragment) getSupportFragmentManager().findFragmentById(
                mNameActBinding.fragName.getId()
        );
        if (nameFragment == null) {
            nameFragment = NameFragment.sInstance(getIntent().getIntExtra("id", 0));
            ActivityUtils.replaceFragmentInActivity(
                    getSupportFragmentManager(), nameFragment, R.id.frag_name
            );
        }
    }

    private void addData(){
        mNameViewModel.getOpenImageEvent().observe(this, name ->
                mNameViewModel.clearName(name.getId())
        );

    }


}
