package com.example.mat.sqlquery102_to_110updatebug.model;

import android.content.Context;

import java.util.List;

import io.requery.Persistable;
import io.requery.android.sqlcipher.SqlCipherDatabaseSource;
import io.requery.android.sqlite.DatabaseSource;
import io.requery.proxy.PropertyState;
import io.requery.sql.EntityDataStore;
import io.requery.sql.TableCreationMode;

/**
 * Created on 1/18/17.
 */

public class DataStorage {


    private final EntityDataStore<Persistable> data;

    public DataStorage(Context context, boolean sqlCipher) {
        if (sqlCipher) {
            SqlCipherDatabaseSource cipherDatabaseSource = new SqlCipherDatabaseSource(context, Models.DEFAULT, "user", "pass", 2);
            cipherDatabaseSource.setTableCreationMode(TableCreationMode.DROP_CREATE);
            data = new EntityDataStore<>(cipherDatabaseSource.getConfiguration());
        } else {
            DatabaseSource source = new DatabaseSource(context, Models.DEFAULT, 2);
            source.setTableCreationMode(TableCreationMode.DROP_CREATE);
            data = new EntityDataStore<>(source.getConfiguration());
        }
    }

    public void storeGroup(UserGroupEntity userGroup) {
        data.delete(UserGroupEntity.class).get().value();
        UserGroupEntity.USER_LIST.getPropertyState().set(userGroup, PropertyState.MODIFIED);
        data.insert(userGroup);
    }

    public List<UserGroupEntity> getGroups() {
        return data.select(UserGroupEntity.class).get().toList();
    }

    public void storeUser(UserEntity user) {
        data.upsert(user);
    }


    public void close() {
        if (data != null) {
            data.close();
        }
    }

    public List<UserEntity> getUsers() {
        return data.select(UserEntity.class).get().toList();
    }
}
