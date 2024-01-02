package com.co.jp.liushuang.persistence;

import com.co.jp.liushuang.persistence.entity.Title;

public interface TitleMapper {
    int deleteByPrimaryKey(String titleId);

    int insert(Title record);

    int insertSelective(Title record);

    Title selectByPrimaryKey(String titleId);

    int updateByPrimaryKeySelective(Title record);

    int updateByPrimaryKey(Title record);
}