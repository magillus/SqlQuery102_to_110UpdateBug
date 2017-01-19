package com.example.mat.sqlquery102_to_110updatebug.model;

import io.requery.Entity;
import io.requery.Generated;
import io.requery.Key;
import io.requery.ManyToOne;
import io.requery.Persistable;

/**
 * Created on 1/18/17.
 */

@Entity
public class User implements Persistable {

    @Generated
    @Key
    public long id;
    public String name;

    @ManyToOne
    public UserGroup userGroup;
}
