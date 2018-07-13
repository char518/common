package com.charl.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.charl.common.domin.User;
import com.charl.common.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @program: common
 * @description:
 * @author: charl
 * @create: 2018-06-26 14:35
 **/
@Service
public class UserService implements IUserService {

    private static final String PRE_FIX = "USER:";
    private static final String HASH_PRE_FIX = "USER_HASH:";

    private AtomicLong incrId = new AtomicLong();

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int addUser(User user) {
        long l = incrId.incrementAndGet();
        user.setId(l+"");
        //1.String 类型，value只能是string或者字符串
//        redisTemplate.opsForValue().set(PRE_FIX + l, JSON.toJSONString(user));

        //2.hash 类型，value存放hashmap
        redisTemplate.opsForHash().put(HASH_PRE_FIX, l+"", user);

        //3.list 类型：双向链表，用于队列
        return 1;
    }

    @Override
    public int updateUser(User user) {
        redisTemplate.opsForValue().set(PRE_FIX + user.getId(), user);
        return 1;
    }

    @Override
    public User queryUserById(Long id) {
        String s = (String) redisTemplate.opsForValue().get(PRE_FIX + id);
        return JSONObject.parseObject(s, User.class);
    }

    @Override
    public List<User> queryUsers() {
        return redisTemplate.opsForValue().multiGet(Arrays.asList(PRE_FIX+"1",PRE_FIX+"2",PRE_FIX+"3",PRE_FIX+"4",PRE_FIX+"5"));
    }
}
