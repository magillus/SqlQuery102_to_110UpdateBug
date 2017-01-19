package com.example.mat.sqlquery102_to_110updatebug.service;

import com.example.mat.sqlquery102_to_110updatebug.model.UserGroupEntity;
import com.example.mat.sqlquery102_to_110updatebug.model.UserEntity;

import io.requery.proxy.PropertyState;

/**
 * Created on 1/18/17.
 */

public class Generator {

    private RandomString randomString = new RandomString(8);

    public UserGroupEntity generateGroup(int userCount) {
        UserGroupEntity group = new UserGroupEntity();
        group.name = randomString.nextString();
        for (int i=0; i< userCount;i++) {
            group.userList.add(generateUser());
        }
        return group;
    }

    public UserEntity generateUser() {
        UserEntity user = new UserEntity();
        user.name = randomString.nextString();
        return user;
    }
}
