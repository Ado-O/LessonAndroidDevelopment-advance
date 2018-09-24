package com.example.user.lesson_android_development.name;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.data.storage.NameRepository;
import com.example.user.lesson_android_development.util.SingleLiveEvent;

import java.util.List;


public class NameViewModel extends AndroidViewModel {

    private NameRepository mNameRepository;

    public final ObservableList<Name> mNames = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);

    private final SingleLiveEvent<Name> mOpenImageEvent = new SingleLiveEvent<>();
    private final SingleLiveEvent<Name> mOpenNameEvent = new SingleLiveEvent<>();


    public NameViewModel(@NonNull Application application, NameRepository nameRepository) {
        super(application);
        mNameRepository = nameRepository;
    }

    public void addNameItems(String name){
        mNameRepository.insertNameItem(name);
    }

    public void clearName(long id){ mNameRepository.clearNameItem(id);}

    public void start() {
        if (mNames.isEmpty()) {
            getNames();
        }
    }

    public void getNames() {
        mNameRepository.getName(new NameRepository.GetNameCallback() {
            @Override
            public void onSuccess(List<Name> names) {
                mNames.clear();
                mNames.addAll(names);
                mError.set(mNames.isEmpty());
            }

            @Override
            public void onError() {

            }
        });
    }

    public SingleLiveEvent<Name> getOpenImageEvent() {
        return mOpenImageEvent;
    }


    public SingleLiveEvent<Name> getOpenNameEvent() {
        return mOpenNameEvent;
    }

}
