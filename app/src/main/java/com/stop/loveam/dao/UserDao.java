package com.stop.loveam.dao;

import com.stop.loveam.entity.User;

public interface UserDao {

    boolean login(String email, String password);

    boolean register(User user);

    boolean send(String email);

    boolean modify_password(String email, String new_password);

    boolean modify_user_info(User user);

    User get_user_info(String email);
}
