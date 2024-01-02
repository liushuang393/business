package com.co.jp.liushuang.persistence;

import com.co.jp.liushuang.persistence.entity.Position;

public interface PositionMapper {
    int deleteByPrimaryKey(String positionId);

    int insert(Position record);

    int insertSelective(Position record);

    Position selectByPrimaryKey(String positionId);

    int updateByPrimaryKeySelective(Position record);

    int updateByPrimaryKey(Position record);
}