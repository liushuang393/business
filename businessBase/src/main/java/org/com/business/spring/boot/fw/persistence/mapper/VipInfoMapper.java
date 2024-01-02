package org.com.business.spring.boot.fw.persistence.mapper;

import org.com.business.spring.boot.fw.persistence.entity.VipInfo;

public interface VipInfoMapper {
    int deleteByPrimaryKey(String userId);

    int insert(VipInfo record);

    int insertSelective(VipInfo record);

    VipInfo selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(VipInfo record);

    int updateByPrimaryKey(VipInfo record);
}