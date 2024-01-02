package com.co.jp.liushuang.persistence;

import com.co.jp.liushuang.persistence.entity.Roll;

public interface RollMapper {
    int deleteByPrimaryKey(String rollId);

    int insert(Roll record);

    int insertSelective(Roll record);

    Roll selectByPrimaryKey(String rollId);

    int updateByPrimaryKeySelective(Roll record);

    int updateByPrimaryKey(Roll record);
}