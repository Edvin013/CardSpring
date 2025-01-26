package org.example.service;

import org.example.model.User;

public interface UserService {
    void add(User user);

    User get(long id);

    User get(String login, String password);

    User delete(long id);

}
