package com.example.user.lesson_android_development;

import android.content.Context;

import com.example.user.lesson_android_development.data.storage.NameRepository;
import com.example.user.lesson_android_development.data.storage.local.AppDatabase;
import com.example.user.lesson_android_development.data.storage.local.NameLocalDataSource;
import com.example.user.lesson_android_development.util.AppExecutors;

public class Injection {

    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext());
    }

    public static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }


    public static NameLocalDataSource provideNameLocalDataSource(Context context) {
        return NameLocalDataSource.getInstance(
                provideAppDatabase(context.getApplicationContext()).getNameDao(),
                provideAppExecutors()
        );
    }

    public static NameRepository provideNameRepository(Context context) {
        return NameRepository.getInstance(
                provideNameLocalDataSource(context)
        );
    }
}
