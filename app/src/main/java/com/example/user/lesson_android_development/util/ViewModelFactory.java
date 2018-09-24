package com.example.user.lesson_android_development.util;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;

import com.example.user.lesson_android_development.Injection;
import com.example.user.lesson_android_development.data.storage.NameRepository;
import com.example.user.lesson_android_development.name.NameViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;

    private final NameRepository mNameRepository;

    public static ViewModelFactory getInstance(Application application) {

        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            application,
                            Injection.provideNameRepository(application)
                    );
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application, NameRepository nameRepository) {
        mApplication = application;

        mNameRepository = nameRepository;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(NameViewModel.class)) {
            return (T) new NameViewModel(mApplication, mNameRepository);
        }
        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }

    public static <T extends AndroidViewModel> T obtainViewModel(FragmentActivity activity, Class<T> modelClass) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        T viewModel =
                ViewModelProviders.of(activity, factory).get(modelClass);

        return viewModel;
    }
}