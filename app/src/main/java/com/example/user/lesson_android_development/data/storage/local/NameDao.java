package com.example.user.lesson_android_development.data.storage.local;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.example.user.lesson_android_development.data.Name;

import java.util.List;

@Dao
public interface NameDao {

    //add name
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertName(Name name);

    //get all from table
    @Query("SELECT * FROM name_table")
    List<Name> getName();

//    //edit one item
//    @Query("SELECT * FROM name_table WHERE name_id=:nameId")
//    List<Name> getNameItem(long nameId);
//
    //delete everting from table
    @Query("DELETE FROM name_table WHERE _id=:Id")
    void clearName(long Id);


}
