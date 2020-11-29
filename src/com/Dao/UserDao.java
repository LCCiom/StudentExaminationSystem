package com.Dao;

import com.Entity.User;

public interface UserDao {
    User  queryByUserName(String username);

    void insert(User user);
}
