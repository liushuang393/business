package org.com.business.spring.boot.fw.persistence.mapper;

import org.com.business.spring.boot.fw.persistence.entity.PersonnelIssuesRelated;

public interface PersonnelIssuesRelatedMapper {
    int deleteByPrimaryKey(String id);

    int insert(PersonnelIssuesRelated record);

    int insertSelective(PersonnelIssuesRelated record);

    PersonnelIssuesRelated selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PersonnelIssuesRelated record);

    int updateByPrimaryKey(PersonnelIssuesRelated record);
}