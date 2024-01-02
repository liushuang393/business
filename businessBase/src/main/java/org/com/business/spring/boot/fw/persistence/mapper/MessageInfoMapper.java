package org.com.business.spring.boot.fw.persistence.mapper;

import org.com.business.spring.boot.fw.persistence.entity.MessageInfo;
import org.com.business.spring.boot.fw.persistence.entity.MessageInfoKey;

public interface MessageInfoMapper {
    int deleteByPrimaryKey(MessageInfoKey key);

    int insert(MessageInfo record);

    int insertSelective(MessageInfo record);

    MessageInfo selectByPrimaryKey(MessageInfoKey key);

    int updateByPrimaryKeySelective(MessageInfo record);

    int updateByPrimaryKey(MessageInfo record);
}