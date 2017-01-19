package com.example.mat.sqlquery102_to_110updatebug.model;

import java.util.ArrayList;
import java.util.List;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.OneToMany;
import io.requery.Persistable;

/**
 * Created on 1/18/17.
 */
@Entity
public class UserGroup implements Persistable{

    @Generated
    @Key
    public long id;

    public String name;
    @OneToMany
    public List<UserEntity> userList;

    public UserGroup() {
        userList = new ArrayList<>();
    }
}
