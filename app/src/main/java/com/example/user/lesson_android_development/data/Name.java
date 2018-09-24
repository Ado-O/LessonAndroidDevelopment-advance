package com.example.user.lesson_android_development.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "name_table")
public class Name implements Parcelable {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "name")
    private String mName;

    public Name(Integer id, String name) {
        mId = id;
        mName = name;
    }

    @Ignore
    public Name(String name) {
        mId = null;
        mName = name;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    protected Name(Parcel in) {
        if (in.readByte() == 0) {
            mId = null;
        } else {
            mId = in.readInt();
        }
        mName = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (mId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(mId);
        }
        dest.writeString(mName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };

    public static class Callback extends DiffUtil.Callback {

        private final List<Name> mOldItems;
        private final List<Name> mNewItems;

        public Callback(List<Name> oldItems, List<Name> newItems) {
            mOldItems = oldItems;
            mNewItems = newItems;
        }

        @Override
        public int getOldListSize() {
            return mOldItems.size();
        }

        @Override
        public int getNewListSize() {
            return mNewItems.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldItems.get(oldItemPosition).getId() == mNewItems.get(newItemPosition).getId();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return mOldItems.get(oldItemPosition).getName().equals(mNewItems.get(newItemPosition).getName());
        }
    }
}