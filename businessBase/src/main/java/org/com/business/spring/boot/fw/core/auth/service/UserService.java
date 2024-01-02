package org.com.business.spring.boot.fw.core.auth.service;

import org.com.business.spring.boot.fw.persistence.entity.User;

public interface UserService {
    User getUserById(String userId);
}