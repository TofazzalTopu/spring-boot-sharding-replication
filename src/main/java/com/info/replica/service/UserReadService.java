package com.info.replica.service;

import com.info.replica.entity.User;
import com.info.replica.repository.shard3.UserRepositoryShardThree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserReadService {

    private final UserRepositoryShardThree userRepositoryShardThree;

    @Autowired
    public UserReadService(UserRepositoryShardThree userRepositoryShardThree) {
        this.userRepositoryShardThree = userRepositoryShardThree;
    }

    public Optional<User> getUser(Long userId) {
        return userRepository(userId).findById(userId);
    }

    public List<User> findAll() {
        return userRepositoryShardThree.findAll(Sort.by("id").ascending());
    }

    public <T> CrudRepository userRepository(Long userId) {
        return userId % 2 == 0 ? userRepositoryShardThree : userRepositoryShardThree;
    }
}
