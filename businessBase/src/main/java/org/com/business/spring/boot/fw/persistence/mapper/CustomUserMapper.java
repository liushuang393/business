package org.com.business.spring.boot.fw.persistence.mapper;

import java.util.List;

import org.com.business.spring.boot.fw.persistence.entity.User;

public interface CustomUserMapper extends UserMapper {

    /**
     * @param MessageInfo
     * @return
     */
    List<User> selectList(User entity);
    /**
     *
     * @param userId
     * @return
     */
    String selectMaxKey(String userId);
}