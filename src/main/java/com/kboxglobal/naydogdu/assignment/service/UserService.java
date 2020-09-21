package com.kboxglobal.naydogdu.assignment.service;

import com.kboxglobal.naydogdu.assignment.entity.User;
import com.kboxglobal.naydogdu.assignment.exception.UserNotFoundException;
import com.kboxglobal.naydogdu.assignment.paging.CursorPager;
import com.kboxglobal.naydogdu.assignment.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CursorPager<User> getAllUsers(String next) {
        List<User> users;
        String nextCursor = "";
        Long nextId;
        CursorPager<User> cursorPager;
        Long idBefore = 0L;
        if (!StringUtils.isEmpty(next)) {//if next is not empty then user wants to go to next page, if empty then it is the first page
            idBefore = Long.parseLong(next);
        }
        users = userRepository.findTop20ByIdAfterOrderByIdAsc(idBefore);
        if (users.size() >= 1) {
            nextCursor = String.valueOf(users.get(users.size() - 1).getId());
            cursorPager = new CursorPager<User>(users, 20, nextCursor, users.size());
            return cursorPager;
        }
        return null;
    }

    public User getUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User addUser(User user) {
        return userRepository.save(user);
    }

    public void removeUserById(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
        userRepository.delete(user);
    }
}
