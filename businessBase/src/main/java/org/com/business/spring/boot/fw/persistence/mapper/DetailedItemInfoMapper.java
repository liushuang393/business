package org.com.business.spring.boot.fw.persistence.mapper;

import org.com.business.spring.boot.fw.persistence.entity.DetailedItemInfo;

public interface DetailedItemInfoMapper {
    int deleteByPrimaryKey(String companyId);

    int insert(DetailedItemInfo record);

    int insertSelective(DetailedItemInfo record);

    DetailedItemInfo selectByPrimaryKey(String companyId);

    int updateByPrimaryKeySelective(DetailedItemInfo record);

    int updateByPrimaryKey(DetailedItemInfo record);
}