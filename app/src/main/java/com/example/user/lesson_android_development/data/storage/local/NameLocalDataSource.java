package com.example.user.lesson_android_development.data.storage.local;

import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.data.storage.NameRepository;
import com.example.user.lesson_android_development.util.AppExecutors;

import java.util.List;

public class NameLocalDataSource {

    private static NameLocalDataSource sInstance = null;

    private NameDao mNameDao;
    private AppExecutors mAppExecutors;

    private NameLocalDataSource(NameDao nameDao,
                                   AppExecutors appExecutors) {
        mNameDao = nameDao;
        mAppExecutors = appExecutors;
    }

    public static NameLocalDataSource getInstance(NameDao nameDao,
                                                     AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new NameLocalDataSource(nameDao, appExecutors);
        }
        return sInstance;
    }

    /**
     * insert name in table name_table
     */
    public void insertName(final String name) {
        mAppExecutors.diskIO().execute(() -> mNameDao.insertName(new Name(name)));
    }

    /**
     * get name from name_table with help of nameId
     */
//    public void getNameItem(long nameId){
//        mAppExecutors.diskIO().execute(() -> mNameDao.getNameItem(nameId));
//
//    }

    /**
     * get delete from table one row
     */
    public void getDelete(long id){
        mAppExecutors.diskIO().execute(() -> mNameDao.clearName(id));
    }

    /**
     * get all from table name_table
     */
    public void getName(final NameRepository.GetNameCallback callback) {
        mAppExecutors.diskIO().execute(() -> {

            List<Name> names = mNameDao.getName();

            mAppExecutors.mainThread().execute(() ->
                    callback.onSuccess(names));
        });
    }

}