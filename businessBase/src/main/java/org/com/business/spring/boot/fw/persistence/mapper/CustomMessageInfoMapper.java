package org.com.business.spring.boot.fw.persistence.mapper;

import java.util.List;

import org.com.business.spring.boot.fw.persistence.entity.MessageInfo;

public interface CustomMessageInfoMapper extends MessageInfoMapper {

    /**
     * @param MessageInfo
     * @return
     */
    List<MessageInfo> selectList(MessageInfo entity);
    /**
     *
     * @param userId
     * @return
     */
    String selectMaxKey(String userId);
}