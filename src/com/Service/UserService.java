package com.Service;

import com.Entity.User;

public interface UserService {
    User queryByUserName(String username);

    void insert(User user);
}
