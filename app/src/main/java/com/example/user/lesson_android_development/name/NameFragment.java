package com.example.user.lesson_android_development.name;

import android.app.Dialog;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.data.storage.NameRepository;
import com.example.user.lesson_android_development.databinding.NameItemBinding;
import com.example.user.lesson_android_development.util.RecyclerViewClickListener;
import com.example.user.lesson_android_development.util.ViewModelFactory;
import com.example.user.lesson_android_development.databinding.NameFragBinding;

public class NameFragment extends Fragment implements RecyclerViewClickListener {

    private static final String TAG = NameFragment.class.getSimpleName();

    private NameFragBinding mNameFragBinding;
    private DialogFragment newFragment;
    private NameViewModel mNameViewModel;
    private NameAdapter adapter;

    public static NameFragment sInstance(int id) {

        NameFragment nameFragment = new NameFragment();
        Bundle b = new Bundle();
        b.putInt("id", id);

        nameFragment.setArguments(b);
        return nameFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mNameFragBinding = NameFragBinding.inflate(inflater, container, false);

        mNameViewModel = ViewModelFactory.obtainViewModel(getActivity(), NameViewModel.class);
        mNameViewModel.start();

        mNameFragBinding.setViewModel(mNameViewModel);

        onCLickButton();
        setupRecycle();

        return mNameFragBinding.getRoot();
    }

    public void setupRecycle() {

        adapter = new NameAdapter(getActivity(), NameFragment.this);
        mNameFragBinding.rvMain.setLayoutManager(new LinearLayoutManager(
                getActivity(),
                LinearLayoutManager.VERTICAL,
                false));
        mNameFragBinding.rvMain.setAdapter(adapter);

    }

    private void onCLickButton() {
        mNameFragBinding.btnAdd.setOnClickListener(v -> {
            newFragment = MainDialogFragment.newInstance();
            newFragment.show(getFragmentManager(), "dialog");
        });

    }



    @Override
    public void recyclerViewListClicked(View v, Name name) {
        mNameViewModel.getOpenImageEvent().setValue(name);
        mNameViewModel.getNames();
    }

    @Override
    public void recyclerViewListClickedName(View v, Name name) {
        mNameViewModel.getOpenNameEvent().setValue(name);
        newFragment = MainDialogFragment.newInstance();
        newFragment.show(getFragmentManager(), "dialog");

    }
}
