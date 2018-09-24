package com.example.user.lesson_android_development.data.storage;

import com.example.user.lesson_android_development.data.Name;
import com.example.user.lesson_android_development.data.storage.local.NameLocalDataSource;

import java.util.List;

public class NameRepository {

    public static final String TAG = NameRepository.class.getSimpleName();

    private static NameRepository sNameRepository = null;

    private final NameLocalDataSource mNameLocalDataSource;

    public NameRepository(NameLocalDataSource nameLocalDataSource) {
        mNameLocalDataSource = nameLocalDataSource;

    }

    public static NameRepository getInstance(NameLocalDataSource nameLocalDataSource) {

        if (sNameRepository == null) {
            sNameRepository = new NameRepository(nameLocalDataSource);
        }
        return sNameRepository;
    }

    /**
     * get all from table
     */
    public void getName(final GetNameCallback callback){
        mNameLocalDataSource.getName(callback);
    }

    /**
     * delete from table from id
     */
    public void clearNameItem(long id) {
        mNameLocalDataSource.getDelete(id);
    }

    /**
     * insert in table
     */
    public void insertNameItem(String name) {
        mNameLocalDataSource.insertName(name);
    }

    /**
     * get from table name
     */
//    public void getNameItem(long nameId){
//        mNameLocalDataSource.getNameItem(nameId);
//    }

    public interface GetNameCallback {
        void onSuccess(List<Name> names);

        void onError();
    }


}
