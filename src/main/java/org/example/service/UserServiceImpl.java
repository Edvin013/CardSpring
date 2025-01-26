package org.example.service;

import org.example.model.User;
import org.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void add(User user) {
        try {
            this.userRepository.save(user);
        } catch (DataIntegrityViolationException e) {
            throw new IllegalArgumentException("Такой пользователь уже есть в базе");
        }
    }

    @Override
    public User get(long id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти пользователя"));
    }

    @Override
    public User get(String login, String password) {
        return this.userRepository.findUserByLoginAndPassword(login, password)
                .orElseThrow(() -> new IllegalArgumentException("Не удалось найти пользователя"));
    }

    @Override
    public User delete(long id) {
        User user = get(id);
        this.userRepository.deleteById(id);
        return user;
    }

}
