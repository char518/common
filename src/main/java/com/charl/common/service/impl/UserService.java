package com.charl.common.service.impl;

import com.charl.common.domin.User;
import com.charl.common.service.IUserService;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-26 14:35
 **/
@Service
public class UserService implements IUserService {

    private ConcurrentHashMap<Object,Object> userMap = new ConcurrentHashMap();

    private AtomicLong incrId = new AtomicLong();

    @Override
    @CachePut(value = "user", key = "#user.id")
    public int addUser(User user) {
        long l = incrId.incrementAndGet();
        user.setId(l);
        userMap.put(user.getId(), user);
        return 1;
    }

    @Override
    @CachePut(value = "user",key = "#user.id")
    public int updateUser(User user) {
        userMap.put(user.getId(), user);
        return 1;
    }

    @Override
    @Cacheable(value = "user", key = "#id", sync = true)
    public User queryUserById(Long id) {
        User user = (User) userMap.get(id);
        return user;
    }

    @Override
    @Cacheable(value = "user", sync = true)
    public List<User> queryUsers() {
        List<User> users = Arrays.asList((User[]) userMap.values().toArray());
        return users;
    }
}
