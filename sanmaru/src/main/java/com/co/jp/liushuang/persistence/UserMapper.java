package com.co.jp.liushuang.persistence;

import java.util.List;
import java.util.Map;

import com.co.jp.liushuang.persistence.entity.User;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * @param user
     * @return
     */
    List<User> selectList(User user);

    /**
     * @param map
     * @return
     */
    List<User> selectListByMap(Map<String, Object> map);
}