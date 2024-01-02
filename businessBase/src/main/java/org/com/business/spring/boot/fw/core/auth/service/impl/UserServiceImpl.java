package org.com.business.spring.boot.fw.core.auth.service.impl;

import javax.annotation.Resource;

import org.com.business.spring.boot.fw.core.auth.service.UserService;
import org.com.business.spring.boot.fw.persistence.entity.User;
import org.com.business.spring.boot.fw.persistence.mapper.UserMapper;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;

    @Override
    public User getUserById(String id) {
        log.debug("開始：{}fff", "init");
        log.info("UserServiceImpl.getUserById()：{}", id);

        User user = userMapper.selectByPrimaryKey(id);

        log.debug("終了：{}", "init");
        return user;
    }
}
