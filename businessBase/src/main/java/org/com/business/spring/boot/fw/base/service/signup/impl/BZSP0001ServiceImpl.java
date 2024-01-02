package org.com.business.spring.boot.fw.base.service.signup.impl;

import java.util.Calendar;

import javax.annotation.Resource;

import org.com.business.spring.boot.common.utils.BZBeanUtils;
import org.com.business.spring.boot.fw.base.common.form.BZSP0001Form;
import org.com.business.spring.boot.fw.base.service.signup.BZSP0001Service;
import org.com.business.spring.boot.fw.persistence.entity.User;
import org.com.business.spring.boot.fw.persistence.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service("BZSP0001Service")
public class BZSP0001ServiceImpl implements BZSP0001Service {

    @Resource
    private UserMapper userMapper;

    @Override
    public int signUpUser(BZSP0001Form form) {

        User user = new User();

        BZBeanUtils.copyBeanProperties(form, user);
        user.setCreateuserid("systemId");
        user.setUpdateuserid("systemId");

        if (userMapper.selectByPrimaryKey(user.getUserId()) != null) {
        	return 0;
        }
        return userMapper.insert(user);
    }

}