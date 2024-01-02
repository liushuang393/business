package com.co.jp.liushuang.controller.form;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;

import com.co.jp.liushuang.core.annotation.NotEmpty;
import com.co.jp.liushuang.persistence.entity.Department;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class DepartmentForm implements Serializable {

    private List<Department> departmentList = new ArrayList<>();

    @Length(min = 3, max = 3)

    private String departmentId;
    @NotEmpty(field = "departmentName")
    private String departmentName;

    private String responsiblePerson;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String establishmentDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatetime;

    /**
     * @return updatetime
     */
    public Date getUpdatetime() {
        return updatetime;
    }

    /**
     * @param updatetime セットする updatetime
     */
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * @return departmentList
     */
    public List<Department> getDepartmentList() {
        return departmentList;
    }

    /**
     * @return departmentId
     */
    public String getDepartmentId() {
        return departmentId;
    }

    /**
     * @param departmentId セットする departmentId
     */
    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    /**
     * @return departmentName
     */
    public String getDepartmentName() {
        return departmentName;
    }

    /**
     * @param departmentName セットする departmentName
     */
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    /**
     * @return responsiblePerson
     */
    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    /**
     * @param responsiblePerson セットする responsiblePerson
     */
    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    /**
     * @return establishmentDate
     */
    public String getEstablishmentDate() {
        return establishmentDate;
    }

    /**
     * @param establishmentDate セットする establishmentDate
     */
    public void setEstablishmentDate(String establishmentDate) {
        this.establishmentDate = establishmentDate;
    }

    /**
     * @param departmentList セットする departmentList
     */
    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

}
