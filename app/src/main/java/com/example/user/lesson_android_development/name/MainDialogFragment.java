package com.example.user.lesson_android_development.name;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.util.ViewModelFactory;
import com.example.user.lesson_android_development.databinding.NameDialogBinding;

public class MainDialogFragment extends DialogFragment {

    public final static String TAG = MainDialogFragment.class.getSimpleName();

    private NameDialogBinding mNameDialogBinding;
    private NameViewModel mNameViewModel;

    static MainDialogFragment newInstance() {
        return new MainDialogFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mNameDialogBinding = NameDialogBinding.inflate(inflater, container, false);

        mNameViewModel = ViewModelFactory.obtainViewModel(getActivity(), NameViewModel.class);


        addButton();
        dissmissButton();
        editText();
        return mNameDialogBinding.getRoot();
    }

    /**
     * add data in edit text
     */
    public void editText() {
        mNameViewModel.getOpenNameEvent().observe(getActivity(), name ->
                mNameDialogBinding.myEditText.setText(name.getName())
        );
    }

    /**
     * add data from user and send to db
     */
    public void addButton() {
        mNameDialogBinding.add.setOnClickListener(v -> {

            mNameViewModel.addNameItems(mNameDialogBinding.myEditText.getText().toString());
            dismiss();
            mNameViewModel.getNames();

        });

    }

    /**
     * dissmis dialogFragment
     */
    public void dissmissButton() {
        mNameDialogBinding.dissmiss.setOnClickListener(v ->
                dismiss());
    }


}
