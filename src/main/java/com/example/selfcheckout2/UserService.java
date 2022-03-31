package com.example.selfcheckout2;


public interface UserService {
    UserData saveUser(UserData user);

    UserData getUserById(final Long userId);

}
