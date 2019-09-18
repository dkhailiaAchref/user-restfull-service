package com.achrefdkhailia.code.service.impl;

import com.achrefdkhailia.code.domain.User;
import com.achrefdkhailia.code.repository.UserRepository;
import com.achrefdkhailia.code.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User save(final User user) {
        return Optional.of(userRepository.save(user))
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User findById(final Long id) {
        User userFound=new User();
        if (!Objects.isNull(id)) {
             userFound= userRepository.findById(id)
                    .orElseThrow(NoSuchElementException::new);
        }
        return userFound;
    }

    @Override
    public List<User> findAll() {
        return Optional.of(userRepository.findAll())
                .orElseThrow(NoSuchElementException::new);
    }

    @Override
    public User update(final long id, final User user) {
        if (userRepository.findById(id).isPresent()) {
            user.setId(id);
            return this.save(user);
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public void deleteById(final long id) {
        userRepository.deleteById(id);
    }
}